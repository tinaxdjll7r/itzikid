package iso3.pt.dao;

import iso3.pt.model.Alumno;
import iso3.pt.model.Asignatura;
import iso3.pt.model.Evaluacion;
import iso3.pt.model.Profesor;
import iso3.pt.model.Unidad;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


 
public class PtDAO implements IPtDAO
{
	 private static SessionFactory sessionFactory;
	 private static PtDAO ptDAOInstance = null;
	 private Session session;
	 private HashMap<Integer,Asignatura> asignaturas;
	 
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
	     cachearAsignaturas();
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

	public void cachearAsignaturas()
	{
		List<Asignatura> asignaturasList = session.createQuery("from Asignatura").list();
		this.asignaturas = new HashMap<Integer,Asignatura> ();
		for (Asignatura asignatura: asignaturasList)
		{
			this.asignaturas.put(asignatura.getId(),asignatura);
		}
	}
	
	public Profesor getProfesor(int idAsignatura)
    {
		Asignatura asig = this.asignaturas.get(idAsignatura);
	    return asig.getProfesor();
    }
	
    public Set<Alumno> getAlumnos(int idAsignatura)
    {
	    Asignatura asig = this.asignaturas.get(idAsignatura);
	    return asig.getAlumnos();
    }
    
    public List<Evaluacion> getEvaluacionesOrderedByAsignatura(int idAlumno)
    {
    	List<Evaluacion> result = session.createQuery("from Evaluacion where alumno.id =" + idAlumno + " order by asignatura.id desc" ).list();
    	return result;
    }
    
    public Set<Evaluacion> getEvaluaciones(int idAsignatura, int idAlumno)
    {
    	Set<Evaluacion> setEvaluaciones = new HashSet<Evaluacion> ();
    	Asignatura asig = this.getAsignatura(idAsignatura);
    	Alumno alum = this.getAlumno(idAlumno);
    	Set<Evaluacion> evaluaciones =  alum.getEvaluaciones();
    	for (Evaluacion evaluacion: evaluaciones)
    	{
			if(evaluacion.getAsignatura().equals(asig))
			{
				setEvaluaciones.add(evaluacion);
			}
    	}
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
	    Set<Asignatura> asignaturas = new HashSet<Asignatura>(result);
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
	    Asignatura asig = this.asignaturas.get(id);
	    return asig;
    }
	 //-----------------------------------------------------------   
	public Alumno loginAlumno(int dni, String pass) throws UserNotFoundException, IncorrectPasswordException //probada
	{
		Alumno alumno = this.getAlumno(dni);
		if (alumno == null)
		{
			throw new UserNotFoundException("No se ha encontrado el alumno con DNI " + dni);
		}
		
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
	    Alumno alum = this.getAlumno(idAlumno);
	    Set<Asignatura> asignaturas = alum.getAsignaturas();
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
			System.out.println("El alumno ya est� matriculado");
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
			System.out.println("El alumno no est� matriculado");
		}
		tx.commit();
	}
	
	public Profesor loginProfesor(int dni, String pass) throws UserNotFoundException, IncorrectPasswordException //probada
	{
		Profesor profesor = this.getProfesorByDni(dni);
		if(profesor == null)
		{
			throw new UserNotFoundException("No se ha encontrado el usuario profesor con DNI " + dni);
		}
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
		Set<Asignatura> setAsignaturas = new HashSet<Asignatura>();
	
		for (Asignatura asignatura: this.asignaturas.values())
		{
			if (asignatura.getProfesor().getId() == idProfesor)
			{
				setAsignaturas.add(asignatura);
			}
		}
    	return setAsignaturas;
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
