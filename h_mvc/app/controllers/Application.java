package controllers;
import java.util.Date;
import java.util.List;

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
		if (f) 
			hotels = Hotel.find(builder.toString()).fetch();	
		else
			hotels = Hotel.findAll();
		renderArgs.put("error", error);
    	render(hotels);
    }

	public static void GetHotel(String error, Long id_hotel){
		Hotel hotel = Hotel.findById(id_hotel);
		renderArgs.put("error", error);
		render(hotel);
	}
	
	public static void GetPermit(Long id_room, Long id_buyer)
	{
		render(id_room, id_buyer);			
	}
	
	public static void Commit(Long id_permit, Integer check)
	{
		if (check == null) {
			((Permit)Permit.findById(id_permit)).delete();
			index("Заказ отменен", null, null);
			return;
		}
		index("Заказ принят", null, null);
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
	
	public static void InputForm(String error, Long id_room, Long id_hotel) 
	{
		if (id_room == null) {
			GetHotel("Не выбрана комната", id_hotel);
			return;
		}
		renderArgs.put("error", error);
		render(id_room);
	}
	
	public static void Reg(Long id_room, String name, String surname, String middlename, String passport_data, String zagran_passport, String login, String password)
	{
		if (login != null && !login.isEmpty()) {
			List<Buyer> buyers = Buyer.find("(login LIKE \'" + login + "\')").fetch();
			if (!buyers.isEmpty()) {
				InputForm("Пользователь c таким логином уже зарегестрирован", id_room, null);
				return;
			}
			if (password == null || password.isEmpty()) {
				InputForm("Пароль не введен!", id_room, null);
				return;
			}
			if (name == null || name.isEmpty() || surname == null || surname.isEmpty() || middlename == null || middlename.isEmpty() || passport_data == null || passport_data.isEmpty() || zagran_passport == null || zagran_passport.isEmpty()) {
				InputForm("Невведены ключивые данные!", id_room, null);
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
			GetPermit(id_room, buyer.getId());
		} else 
			InputForm("Логин не введен!", id_room, null);
	}
	
	public static void Input(Long id_room, String login, String password)
	{
		System.out.println(id_room);
		if (login != null && !login.isEmpty() && password != null && !password.isEmpty()) {
			List<Buyer> buyers = Buyer.find("(login LIKE \'" + login + "\') AND (password LIKE \'" + password + "\')").fetch();
			if (buyers.isEmpty()) 
				InputForm("Неверная пара логин\\пароль", id_room, null);
			else 
				GetPermit(id_room, buyers.get(0).getId());
		} 
		else 
			InputForm("Заполните поля!", id_room, null);
	}
}