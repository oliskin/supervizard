package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Thesis extends Model {

	@Id
	public Long id;
	@Required
	public String type;
	public Date startDate;
//	@ManyToOne
//	public Supervisor supervisor;
	public String topic;

	// public Person student;
	//
	// public Person firstExaminer;
	//
	// public Person secondExaminer;
	@OneToMany(cascade = CascadeType.ALL)
	public List<Task> tasks;

	public Thesis(String type, Date startDate, 
			String topic) {
		this.type = type;
		this.startDate = startDate;
//		this.supervisor = supervisor;
		this.topic = topic;
		this.tasks = new ArrayList<Task>();
	}
	
	

	public static Finder<Long, Thesis> find = new Finder<Long, Thesis>(
			Long.class, Thesis.class);

//	public static Thesis create(String type, Date startDate, Supervisor supervisor, String topic) {
//		Thesis thesis = new Thesis(type, startDate, supervisor, topic);
//		thesis.save();
//		return thesis;
//	}
	
	public static Thesis create(Thesis thesis){
		thesis.save();
		return thesis;
	}
}