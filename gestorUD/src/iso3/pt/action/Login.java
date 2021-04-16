package iso3.pt.action;
import java.util.Map;

import iso3.pt.dao.IncorrectPasswordException;
import iso3.pt.dao.PtDAO;
import iso3.pt.dao.UserNotFoundException;
import iso3.pt.model.Alumno;
import iso3.pt.model.Profesor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


/**
 * <p> Validate a user login. </p>
 */
public class Login  extends ActionSupport
{
    public String execute() throws Exception 
    {
    	 Map session = ActionContext.getContext().getSession();
    	 String result = INPUT;
    	 PtDAO dao = PtDAO.getInstance();
    	 if (getUsername() == null || getPassword() == null) 
    	 {
 			addActionError("Compulsory to specify both username and password!");
 			result = INPUT;
 		 }
    	 else if (this.type.equals(getText("label.login.alumno")))
    	 {
	    	 Alumno alumnoLogin = null;
		     try
		     {
		    	 alumnoLogin = dao.loginAlumno(Integer.parseInt(getUsername()),getPassword());
		     }
		     catch (UserNotFoundException e){}
		     catch (IncorrectPasswordException e) {}
		     if(alumnoLogin == null)
				{
		            addActionError(getText("errors.invalid.login.details"));
		            result = ERROR;
				}else{
					session.put("logged",alumnoLogin);
					result = SUCCESS;
				}
    	 }
    	 else if (this.type.equals(getText("label.login.profesor")))
    	 {
    		 Profesor profesorLogin = null;
		     try
		     {
		    	 profesorLogin = dao.loginProfesor(Integer.parseInt(getUsername()),getPassword());
		     }
		     catch (UserNotFoundException e){e.printStackTrace();}
		     catch (IncorrectPasswordException e) {e.printStackTrace();}
		     if(profesorLogin == null)
				{
		            addActionError(getText("errors.invalid.login.details"));
		            result = ERROR;
				}else
				{
					session.put("logged",profesorLogin);
					result = "succesProfesor";
				}
    	 }
	     System.out.println("Validating login");
        return result;
	}


    // ---- Username property ----

    /**
     * <p>Field to store User username.</p>
     * <p/>
     */
    private String username = null;


    /**
     * <p>Provide User username.</p>
     *
     * @return Returns the User username.
     */
    public String getUsername() 
    {
        return username;
    }

    /**
     * <p>Store new User username</p>
     *
     * @param value The username to set.
     */
    public void setUsername(String value) 
    {
        username = value;
    }

    // ---- Username property ----

    /**
     * <p>Field to store User password.</p>
     * <p/>
     */
    private String password = null;


    /**
     * <p>Provide User password.</p>
     *
     * @return Returns the User password.
     */
    public String getPassword() 
    {
        return password;
    }

    /**
     * <p>Store new User password</p>
     *
     * @param value The password to set.
     */
    public void setPassword(String value)
    {
        this.password = value;
    }

    private String type = null;
    
    public void setType(String value)
    {
    	this.type = value;
    }
    
    public String getType()
    {
    	return this.type;
    }
}
