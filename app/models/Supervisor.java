package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

@Entity
public class Supervisor extends Person {

	public String password;

	public static Finder<String, Supervisor> find = new Finder<String, Supervisor>(
			String.class, Supervisor.class);

	public static Supervisor authenticate(String email, String password) {
		return find.where().eq("email", email).eq("password", password)
				.findUnique();

	}
	
	public Supervisor(String email, String password, String prename, String lastname){
		super(email, prename, lastname);
		this.password = password;
	}
	
	public Supervisor create(String email, String password, String prename, String lastname){
		Supervisor result = new Supervisor(email, password, prename, lastname);
		result.save();
		return result;		
	}

}
