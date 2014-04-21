package controllers;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import play.*;
import play.mvc.*;
import play.data.validation.Error;
import play.data.validation.Valid;

import javax.xml.crypto.Data;

import models.*;

public class Application extends Controller {

	public static void index(String error, String country, String town) {
    	
		StringBuilder builder = new StringBuilder();
		boolean f = false;
		if (country != null && !country.isEmpty()) {
			if (f)
				builder.append(" AND ");
			f = true;
			builder.append("(country LIKE \'" + country + "\')");
		}
		if (town != null && !town.isEmpty()) {
			if (f)
				builder.append(" AND ");
			f = true;
			builder.append("(town LIKE \'" + town + "\')");
		}
		
		List <Hotel> hotels = null;
		if (f) {
			hotels = Hotel.find(builder.toString()).fetch();	
			if(hotels == null || hotels.isEmpty()){
				if(town == null || town.isEmpty())
					renderArgs.put("countr", "В стране " + country + " отелей еще нет");
				else if(country == null || country.isEmpty())
					renderArgs.put("countr", "В городе " + town +  " отелей еще нет");
				else
					renderArgs.put("countr", "В стране " + country + " в городе " + town +  " отелей еще нет");
			}
		}
		else
			hotels = Hotel.findAll();
		//renderArgs.put("countr", "В стране" + country + "отелей еще нет");
		renderArgs.put("old_country", country);
		renderArgs.put("old_town", town);
    	render(hotels);
    }

	public static void GetHotel(String error, Long id_hotel){
		Hotel hotel = Hotel.findById(id_hotel);
		renderArgs.put("error", error);
		render(hotel);
	}
	
	public static void GetPermit(Long id_room, Long id_buyer, String period, Date date, Long id_transport)
	{
		if(period == null || period.isEmpty()){
			renderArgs.put("error", "Введите период");
		}
		else{
			try{
				Result(id_room, id_buyer, null, Integer.parseInt(period), date, id_transport);
			}catch(NumberFormatException e){
				renderArgs.put("error", "Период - целое число");
			}
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
	
	//	System.out.println("currentDate = " + sdf.format(currentDate));
		try{
			renderArgs.put("old_date", sdf.format(date));
		} catch (Exception e) {
			renderArgs.put("old_date", sdf.format(new Date()));
			//renderArgs.put("error", "Введите дату отправления");
		}
		renderArgs.put("old_period", period);
		renderArgs.put("tr" + String.valueOf(id_transport), id_transport);
		render(id_room, id_buyer);			
	}
	
	public static void Commit(Long id_permit, Integer check)
	{
		if (check == null) {
			((Permit)Permit.findById(id_permit)).delete();
			EndPage("Заказ отменен");
			return;
		}
		EndPage("Заказ принят");
	}
	
	public static void EndPage(String error)
	{
		List <Permit> permits = null;
		permits = Permit.findAll();
		renderArgs.put("error", error);
		render(permits);
		index(null, null, null);
	}
	
	public static void Result(Long id_room, Long id_buyer, Integer kol, Integer period, Date date, Long id_transport)
	{
		Room room = Room.findById(id_room);
		Buyer buyer = Buyer.findById(id_buyer);
		Transport transport = Transport.findById(id_transport);
		Season season = Season.findById((long)1);
		Permit permit = new Permit();
		permit.setBuyer(buyer);
		permit.setRoom(room);
		permit.setTransport(transport);
		
		permit.setPeriod(period);
		
		permit.setProvider(room.getHotel().getH_prov().get(0).getProvider());
		permit.setSeason(season);
		permit.setStart_date(new java.sql.Date(date.getTime()));
		permit.setVisa(0.0);
		permit.setFinal_sum((permit.getVisa() + permit.getPeriod() * permit.getRoom().getCost() + permit.getTransport().getSum_transport()) * (100.0 + permit.getSeason().getPercent_sum()) / 100.0);
		permit.save();
		render(permit);
	}
	
	public static void InputForm(String error, String name, String surname, String middlename, String passport_data, String zagran_passport, String login, String login2, Long id_room, Long id_hotel) 
	{
		if (id_room == null) {
			GetHotel("Не выбрана комната", id_hotel);
			return;
		}
		renderArgs.put("error", error);
		renderArgs.put("old_name", name);
		renderArgs.put("old_surname", surname);
		renderArgs.put("old_middlename", middlename);
		renderArgs.put("old_passport_data", passport_data);
		renderArgs.put("old_zagran_passport", zagran_passport);
		renderArgs.put("old_login", login);
		renderArgs.put("old_login2", login2);
		render(id_room);
	}
	
	public static void Reg(Long id_room, String name, String surname, String middlename, String passport_data, String zagran_passport, String login, String password)
	{
		if (login != null && !login.isEmpty()) {
			List<Buyer> buyers = Buyer.find("(login LIKE \'" + login + "\')").fetch();
			if (!buyers.isEmpty()) {
				InputForm("Пользователь c таким логином уже зарегестрирован", name, surname, middlename, passport_data, zagran_passport, login, null, id_room, null);
				return;
			}
			if (name == null || name.isEmpty()){
				InputForm("Введите имя", name, surname, middlename, passport_data, zagran_passport, login, null, id_room, null);
				return;
			}
			if(surname == null || surname.isEmpty() ){
				InputForm("Введите фамилию", name, surname, middlename, passport_data, zagran_passport, login, null, id_room, null);
				return;
			}	
			if(middlename == null || middlename.isEmpty()){
				InputForm("Введите отчество", name, surname, middlename, passport_data, zagran_passport, login, null, id_room, null);
				return;
			}
			if(passport_data == null || passport_data.isEmpty()){
				InputForm("Введите серию и номер паспорта РФ", name, surname, middlename, passport_data, zagran_passport, login, null, id_room, null);
				return;	
			}
			else{
				Pattern p = Pattern.compile("^[0-9]{4} [0-9]{6}$"); 
				Matcher m = p.matcher(passport_data); 
				if (!m.matches()){
					InputForm("Некорректный формат паспортных данных (1234 567890)", name, surname, middlename, null, zagran_passport, login, null, id_room, null);
					return;
				}
			}
			if(zagran_passport == null || zagran_passport.isEmpty()) {
				InputForm("Введите номер загранпаспорта", name, surname, middlename, passport_data, zagran_passport, login, null, id_room, null);
				return;
			}
			else{
				Pattern p = Pattern.compile("^[0-9]{2} [0-9]{7}$"); 
				Matcher m = p.matcher(zagran_passport); 
				if (!m.matches()){
					InputForm("Некорректный формат данных (71 0123456)", name, surname, middlename, passport_data, null, login, null, id_room, null);
					return;
				}
			}
			if (password == null || password.isEmpty()) {
				InputForm("Пароль не введен!", name, surname, middlename, passport_data, zagran_passport, login, null, id_room, null);
				return;
			}
			Buyer buyer = new Buyer();
			buyer.setLogin(login);
			buyer.setPassword(password);
			buyer.setName_buyer(name);
			buyer.setMiddle_name(middlename);
			buyer.setSurname(surname);
			buyer.setPassport_data(passport_data);
			buyer.setZagran_passport(zagran_passport);
			buyer.save();
			GetPermit(id_room, buyer.getId(), null, null, 1l);
		} else 
			InputForm("Логин не введен!", name, surname, middlename, passport_data, zagran_passport, login, null, id_room, null);
	}
	
	public static void Input(Long id_room, String login, String password)
	{
		System.out.println(id_room);
		if (login != null && !login.isEmpty() && password != null && !password.isEmpty()) {
			List<Buyer> buyers = Buyer.find("(login LIKE \'" + login + "\') AND (password LIKE \'" + password + "\')").fetch();
			if (buyers.isEmpty()) 
				InputForm("Неверная пара логин\\пароль", null, null, null, null, null, null, login, id_room, null);
			else 
				GetPermit(id_room, buyers.get(0).getId(), null, null, 1l);
		} 
		else 
			InputForm("Заполните поля!", null, null, null, null, null, null, login, id_room, null);
	}
}

