package controllers;

import static play.data.Form.form;
import models.Supervisor;
import models.Thesis;
import play.*;
import play.data.*;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {
	
	static Form<Thesis> thesisForm = Form.form(Thesis.class);


	@Security.Authenticated(Secured.class)
    public static Result index() {
        return ok();
    }
    
    public static Result login(){
    	return ok(login.render(form(Login.class)));
    }
    
    @Security.Authenticated(Secured.class)
    public static Result newThesis() {
    	Form<Thesis> filledForm = thesisForm.bindFromRequest();
    	  if(filledForm.hasErrors()) {
    	    return badRequest(
//    	      views.html.index.render(Thesis.all(), filledForm)
    	    		views.html.thesis.create.render(filledForm)
//    	      views.html.index.render("Hallo")
    	    );
    	  } else {
    	    Thesis.create(filledForm.get());
//    	    return redirect(routes.Application.tasks());  
    	    return redirect(routes.Application.index());  
    	  }
    }
    
    @Security.Authenticated(Secured.class)
    public static Result showThesisForm(){
    	return ok(views.html.thesis.create.render(thesisForm));
    }
    

	public static class Login {

		public String email;
		public String password;

		public String validate() {
			if (Supervisor.authenticate(email, password) == null) {
				return "Ungültiger Benutzername oder Passwort.";
			}
			return null;
		}

	}

	public static Result authenticate() {

		Form<Login> loginForm = form(Login.class).bindFromRequest();

		if (loginForm.hasErrors()) {
			return badRequest(login.render(loginForm));
		} else {
			session().clear();
			session("email", loginForm.get().email);
			return redirect(routes.Application.index());
		}
	}

}
