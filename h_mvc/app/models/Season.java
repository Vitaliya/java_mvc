package models;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class Season extends Model{

	private String name_season;
	@Column(columnDefinition = "numeric")
	private double percent_sum;
	
	@OneToMany
	@JoinColumn(name = "id_season")
	private List<Permit> permit = new LinkedList<Permit>();
	
	public String getName_season() {
		return name_season;
	}
	public void setName_season(String name_season) {
		this.name_season = name_season;
	}
	public double getPercent_sum() {
		return percent_sum;
	}
	public void setPercent_sum(double percent_sum) {
		this.percent_sum = percent_sum;
	}
	public List<Permit> getPermit() {
		return permit;
	}
	public void setPermit(List<Permit> permit) {
		this.permit = permit;
	}



}
