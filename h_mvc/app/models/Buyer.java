package models;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class Buyer extends Model{
	
	private String name_buyer;
	private String surname;
	private String middle_name;
	private String passport_data;
	private String zagran_passport;
	private String login;
	private String password;
	
	@OneToMany
	@JoinColumn(name = "id_buyer")
	private List<Permit> permit = new LinkedList<Permit>();
	
	public String getName_buyer() {
		return name_buyer;
	}
	public void setName_buyer(String name_buyer) {
		this.name_buyer = name_buyer;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getMiddle_name() {
		return middle_name;
	}
	public void setMiddle_name(String middle_name) {
		this.middle_name = middle_name;
	}
	public String getPassport_data() {
		return passport_data;
	}
	public void setPassport_data(String passport_data) {
		this.passport_data = passport_data;
	}
	public String getZagran_passport() {
		return zagran_passport;
	}
	public void setZagran_passport(String zagran_passport) {
		this.zagran_passport = zagran_passport;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Permit> getPermit() {
		return permit;
	}
	public void setPermit(List<Permit> permit) {
		this.permit = permit;
	}

}
