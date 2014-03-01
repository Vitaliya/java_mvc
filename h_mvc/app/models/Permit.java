package models;

import java.security.Timestamp;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class Permit extends Model{
	@ManyToOne
	@JoinColumn(name = "id_provider")
	private Provider provider;
	
	@ManyToOne
	@JoinColumn(name = "id_season")
	private Season season;
	
	@ManyToOne
	@JoinColumn(name = "id_transport")
	private Transport transport;

	@ManyToOne
	@JoinColumn(name = "id_buyer")
	private Buyer buyer;
	
	@ManyToOne
	@JoinColumn(name = "id_room")
	private Room room;
	
	@Column(columnDefinition = "numeric")
	private double final_sum;
	@Column(columnDefinition = "numeric")
	private double visa;
	private Date start_date;
	private int period;
	
	public Provider getProvider() {
		return provider;
	}
	public void setProvider(Provider provider) {
		this.provider = provider;
	}
	public Season getSeason() {
		return season;
	}
	public void setSeason(Season season) {
		this.season = season;
	}
	public Transport getTransport() {
		return transport;
	}
	public void setTransport(Transport transport) {
		this.transport = transport;
	}
	public Buyer getBuyer() {
		return buyer;
	}
	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public double getFinal_sum() {
		return final_sum;
	}
	public void setFinal_sum(double final_sum) {
		this.final_sum = final_sum;
	}
	public double getVisa() {
		return visa;
	}
	public void setVisa(double visa) {
		this.visa = visa;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public int getPeriod() {
		return period;
	}
	public void setPeriod(int period) {
		this.period = period;
	} 		
	
}
