package iso3.pt.dao;

import iso3.hib.ejemploCC.Asignatura;
import iso3.hib.ejemploCC.Evaluacion;
import iso3.hib.ejemploCC.Profesor;
import iso3.hib.ejemploCC.Alumno;
import iso3.hib.ejemploCC.Unidad;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javassist.bytecode.Descriptor.Iterator;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;



public class PtDAO implements IPtDAO
{
	 private static SessionFactory sessionFactory;
	 private static PtDAO ptDAOInstance = null;
	 private Session session;
	 
	 private PtDAO()
	 {
		 try {
	            // Create the SessionFactory from hibernate.cfg.xml
	            sessionFactory =  new Configuration().configure().buildSessionFactory();
	            this.session = sessionFactory.openSession();
	        }
	        catch (Throwable ex) {
	            // Make sure you log the exception, as it might be swallowed
	            System.err.println("Initial SessionFactory creation failed." + ex);
	            throw new ExceptionInInitializerError(ex);
	        }
	 }
	 
	 public static PtDAO getInstance()
	 {
		if(ptDAOInstance == null)
		{
			ptDAOInstance = new PtDAO();
		}
		 return ptDAOInstance;
	 }

	protected void finalize() throws Throwable 
	{
		super.finalize();
		sessionFactory.close();
	}

	public Profesor getProfesor(int idAsignatura)
    {
	    List<Asignatura> result = session.createQuery("from Asignatura where id = " + idAsignatura + "").list();
	    return result.get(0).getProfesor();
    }
    public Set<Alumno> getAlumnos(int idAsignatura)
    {
	    List<Asignatura> result = session.createQuery("from Asignatura where id = " + idAsignatura+"").list();
	    return result.get(0).getAlumnos();
    }
    public List<Evaluacion> getEvaluacionesOrderedByAsignatura(int idAlumno)
    {
    	List<Evaluacion> result = session.createQuery("from Evaluacion where alumno.id =" + idAlumno + " order by asignatura.id desc" ).list();
    	return result;
    }
    public Set<Evaluacion> getEvaluaciones(int idAsignatura, int idAlumno)
    {
    	List<Evaluacion> result = session.createQuery("from Evaluacion where alumno.id =" + idAlumno + "and asignatura.id =" + idAsignatura).list();
    	Set<Evaluacion> setEvaluaciones = new HashSet(result);
    	return setEvaluaciones;
    }
    public void addEvaluacion(String concepto, float nota, int idAsignatura, int idAlumno)
    {
    	Transaction tx = this.session.beginTransaction();
    	Evaluacion evaluacion = new Evaluacion(concepto, nota);
    	Asignatura asignatura = getAsignatura(idAsignatura);
    	Alumno alumno = getAlumno(idAlumno);
    	evaluacion.setNota(nota);
    	evaluacion.setAsignatura(asignatura);
    	evaluacion.setAlumno(alumno);
    	evaluacion.setConcepto(concepto);
    	session.save(evaluacion);
    	tx.commit();
    }
    public Set<Unidad> getUnidades(int idAsignatura)
    {
    	Asignatura asignatura = getAsignatura(idAsignatura);
    	return asignatura.getUnidades();
    }
    public Set<Asignatura> getAsignaturas()
    {
	    List<Asignatura> result = session.createQuery("from Asignatura").list();
	    Set<Asignatura> asignaturas = new HashSet(result);
	    return asignaturas;
    }
    public Alumno getAlumno(int id)
    {
    	Alumno result = null;
	    List<Alumno> alumnos = session.createQuery("from Alumno where id = " + id).list();
	    if (alumnos.size() != 0)
	    {
	    	result = alumnos.get(0);
	    }
	    return result;
    }
    public Asignatura getAsignatura(int id)
    {
	    List<Asignatura> result = session.createQuery("from Asignatura where id = " + id).list();
	    return result.get(0);
    }
	 //-----------------------------------------------------------   
	public Alumno loginAlumno(int dni, String pass) throws UserNotFoundException, IncorrectPasswordException //probada
	{
		List<Alumno> result = session.createQuery("from Alumno where dni = " + dni).list();
		//List<Alumno> result = (List<Alumno>) this.getAlumno(dni);
		if (result.isEmpty())
		{
			throw new UserNotFoundException("No se ha encontrado el alumno con DNI " + dni);
		}
		
		Alumno alumno = result.get(0);
		if(!(pass.equalsIgnoreCase(alumno.getPassword())))
		{
			
			System.out.println("password incorrecta");
			throw new IncorrectPasswordException("Password incorrecta");
		}
		else
		{
			System.out.println("password correcta");
			return alumno;
		}
	}
	
	    public Set<Asignatura> getAsignaturas(int idAlumno) //probada
	{
	    List<Alumno> result = session.createQuery("from Alumno where id = " + idAlumno).list();
	    Set<Asignatura> asignaturas = result.get(0).getAsignaturas();
	    return asignaturas;
	}
	public void matricular(int idAlumno, int idAsignatura) 
	{
		
		Transaction tx = session.beginTransaction();
		Alumno alumno = getAlumno(idAlumno);
		Asignatura asignatura = getAsignatura(idAsignatura);
		Set<Asignatura> asignaturas = alumno.getAsignaturas();
		boolean matriculado = asignaturas.contains(asignatura);
		if(!matriculado)
		{
			alumno.addAsignatura(asignatura);
			asignatura.addAlumno(alumno);
		}
		else{
			System.out.println("El alumno ya está matriculado");
		}
		tx.commit();
	}
	public void desmatricular(int idAlumno, int idAsignatura) 
	{
		Transaction tx = session.beginTransaction();
		Alumno alumno = getAlumno(idAlumno);
		Asignatura asignatura = getAsignatura(idAsignatura);
		Set<Asignatura> asignaturas = alumno.getAsignaturas();
		boolean matriculado = asignaturas.contains(asignatura);
		if(matriculado)
		{
			alumno.removeAsignatura(asignatura);
			asignatura.removeAlumno(alumno);
		}
		else
		{
			System.out.println("El alumno no está matriculado");
		}
		tx.commit();
	}
	
	public Profesor loginProfesor(int dni, String pass) throws UserNotFoundException, IncorrectPasswordException //probada
	{
		List<Profesor> result = session.createQuery("from Profesor where dni = " + dni).list();
		if(result.isEmpty())
		{
			throw new UserNotFoundException("No se ha encontrado el usuario profesor con DNI " + dni);
		}
		Profesor profesor = result.get(0);
		if(!(pass.equalsIgnoreCase(profesor.getPassword())))
		{
			System.out.println("password incorrecta");
			throw new IncorrectPasswordException("Password incorrecta");
		}
		else
		{
			System.out.println("password correcta");
			return profesor;
		}
	}
	
	public Set<Asignatura> getAsignaturasProfesor(int idProfesor) //probada
	{
	    List<Asignatura> result = session.createQuery("from Asignatura where pr_id = " + idProfesor).list();
	    //getProfesorByDni ???
	    if (result == null)
	    {
	    	System.out.println("result a null");
	    }
	    Set<Asignatura> asignaturas = new HashSet<Asignatura>();
	 //   asignaturas.add(result.get(0));
	    for(int i = 0; i < result.size(); i++)
	    {
	    	asignaturas.add(result.get(i));
	    }
	    return asignaturas;
    }
	
	public Profesor getProfesorByDni(int dni) throws UserNotFoundException //probada
	{
		List<Profesor> result = session.createQuery("from Profesor where pr_dni = " + dni).list();
		return result.get(0);
	}
	
	public List<Evaluacion> getEvaluacionesAsignatura(int idAsignatura) //probada
	{
		List<Evaluacion> result = session.createQuery("from Evaluacion where asignatura = " + idAsignatura).list();
		return result;
	}
	
}
