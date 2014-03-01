package models;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class Transport extends Model{

	@Column(columnDefinition = "numeric")
	private double sum_transport;
	private String type_of_transport;
	
	@OneToMany
	@JoinColumn(name = "id_transport")
	private List<Permit> permit = new LinkedList<Permit>();

	public List<Permit> getPermit() {
		return permit;
	}

	public void setPermit(List<Permit> permit) {
		this.permit = permit;
	}

	public double getSum_transport() {
		return sum_transport;
	}

	public void setSum_transport(double sum_transport) {
		this.sum_transport = sum_transport;
	}

	public String getType_of_transport() {
		return type_of_transport;
	}

	public void setType_of_transport(String type_of_transport) {
		this.type_of_transport = type_of_transport;
	}

}
