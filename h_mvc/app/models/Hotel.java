package models;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class Hotel extends Model {
	
	private String name_hotel;
	private String country;
	private int stars;
	private String town;
	@OneToMany
	@JoinColumn(name = "id_hotel")
	private List<Hotel_Provider> h_prov = new LinkedList<Hotel_Provider>();

	@OneToMany
	@JoinColumn(name = "id_hotel")
	private List<Room> room = new LinkedList<Room>();
	
	public int getStars() {
		return stars;
	}
	public void setStars(int stars) {
		this.stars = stars;
	}
	public String getName_hotel() {
		return name_hotel;
	}
	public void setName_hotel(String name_hotel) {
		this.name_hotel = name_hotel;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public List<Hotel_Provider> getH_prov() {
		return h_prov;
	}
	public void setH_prov(List<Hotel_Provider> h_prov) {
		this.h_prov = h_prov;
	}
	public List<Room> getRoom() {
		return room;
	}
	public void setRoom(List<Room> room) {
		this.room = room;
	}
	
	
}
