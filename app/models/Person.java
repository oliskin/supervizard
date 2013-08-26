package models;

import javax.persistence.*;

import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

@Entity
public class Person extends Model{

	@Id
	public String email;
	
	public String prename;
	
	public String lastname;

	public Person(String email, String prename, String lastname) {
		this.email = email;
		this.prename = prename;
		this.lastname = lastname;
	}	
	
	
	
}
