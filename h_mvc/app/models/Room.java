package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class Room extends Model{
	@ManyToOne
	@JoinColumn(name = "id_hotel")
	private Hotel hotel;
	
	private char bar;
	@Column(columnDefinition = "numeric")
	private double cost;
	private int num;
	private int full_room;
	private int type_of_room;
	

	public int getFull_room() {
		return full_room;
	}
	public void setFull_room(int full_room) {
		this.full_room = full_room;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public char getBar() {
		return bar;
	}
	public void setBar(char bar) {
		this.bar = bar;
	}
	public Hotel getHotel() {
		return hotel;
	}
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	public int getType_of_room() {
		return type_of_room;
	}
	public void setType_of_room(int type_of_room) {
		this.type_of_room = type_of_room;
	}	

}
