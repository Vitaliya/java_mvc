package models;
import java.security.Timestamp;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class Hotel_Provider extends Model{
	@ManyToOne
	@JoinColumn(name = "id_hotel")
	private Hotel hotel;
	
	@ManyToOne
	@JoinColumn(name = "id_provider")
	private Provider provider;
	
	private Date limit_of_time;
	private String dogovor;
	
	public Date getLimit_of_time() {
		return limit_of_time;
	}
	public void setLimit_of_time(Date limit_of_time) {
		this.limit_of_time = limit_of_time;
	}
	public String getDogovor() {
		return dogovor;
	}
	public void setDogovor(String dogovor) {
		this.dogovor = dogovor;
	}
	public Hotel getHotel() {
		return hotel;
	}
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	public Provider getProvider() {
		return provider;
	}
	public void setProvider(Provider provider) {
		this.provider = provider;
	}
}
