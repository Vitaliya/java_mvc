package models;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class Provider extends Model{
	
	private String name_provider;
	@OneToMany
	@JoinColumn(name = "id_provider")
	private List<Hotel_Provider> h_prov = new LinkedList<Hotel_Provider>();
	
	@OneToMany
	@JoinColumn(name = "id_provider")
	private List<Permit> permit = new LinkedList<Permit>();


	public String getName_provider() {
		return name_provider;
	}

	public void setName_provider(String name_provider) {
		this.name_provider = name_provider;
	}

	public List<Permit> getPermit() {
		return permit;
	}

	public void setPermit(List<Permit> permit) {
		this.permit = permit;
	}

	public List<Hotel_Provider> getH_prov() {
		return h_prov;
	}

	public void setH_prov(List<Hotel_Provider> h_prov) {
		this.h_prov = h_prov;
	}


}
