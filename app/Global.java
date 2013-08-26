import play.*;
import play.libs.*;
import com.avaje.ebean.Ebean;
import models.*;
import java.util.*;

public class Global extends GlobalSettings {
	
	@Override
	public void onStart(Application app){
		
		//If the application is started and there is no user registered, we want the dummy data set to be loaded.
		if(Supervisor.find.findRowCount() == 0){
			Ebean.save((List) Yaml.load("supervisor-data.yml"));
		}
	}

}
