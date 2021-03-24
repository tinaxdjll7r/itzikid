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
	 private static final SessionFactory sessionFactory = buildSessionFactory();

	    private static SessionFactory buildSessionFactory() {
	        try {
	            // Create the SessionFactory from hibernate.cfg.xml
	            return new Configuration().configure().buildSessionFactory();
	        }
	        catch (Throwable ex) {
	            // Make sure you log the exception, as it might be swallowed
	            System.err.println("Initial SessionFactory creation failed." + ex);
	            throw new ExceptionInInitializerError(ex);
	        }
	    }

	    public static SessionFactory getSessionFactory() {
	        return sessionFactory;
	    }
		protected void finalize() throws Throwable 
		{
			super.finalize();
			sessionFactory.close();
		}

		public Profesor getProfesor(int idAsignatura)
	    {
	    	Session session = getSessionFactory().openSession();
		    List<Asignatura> result = session.createQuery("from Asignatura where id = " + idAsignatura + "").list();
		    return result.get(0).getProfesor();
	    }
	    public Set<Alumno> getAlumnos(int idAsignatura)
	    {
	    	Session session = getSessionFactory().openSession();
		    List<Asignatura> result = session.createQuery("from Asignatura where id = " + idAsignatura+"").list();
		    return result.get(0).getAlumnos();
	    }
	    public List<Evaluacion> getEvaluacionesOrderedByAsignatura(int idAlumno)
	    {
	    	Session session = getSessionFactory().openSession();
	    	List<Evaluacion> result = session.createQuery("from Evaluacion where alumno.id =" + idAlumno + " order by asignatura.id desc" ).list();
	    	return result;
	    }
	    public Set<Evaluacion> getEvaluaciones(int idAsignatura, int idAlumno)
	    {
	    	Session session = getSessionFactory().openSession();
	    	List<Evaluacion> result = session.createQuery("from Evaluacion where alumno.id =" + idAlumno + "and asignatura.id =" + idAsignatura).list();
	    	Set<Evaluacion> setEvaluaciones = new HashSet(result);
	    	return setEvaluaciones;
	    }
	    public void addEvaluacion(String concepto, float nota, int idAsignatura, int idAlumno)
	    {
	    	Session session = getSessionFactory().openSession();
	    	Evaluacion evaluacion = new Evaluacion(concepto, nota);
	    	Asignatura asignatura = getAsignatura(idAsignatura);
	    	Alumno alumno = getAlumno(idAlumno);
	    	evaluacion.setAsignatura(asignatura);
	    	evaluacion.setAlumno(alumno);
	    	session.save(evaluacion);
	    }
	    public Set<Unidad> getUnidades(int idAsignatura)
	    {
	    	Asignatura asignatura = getAsignatura(idAsignatura);
	    	return asignatura.getUnidades();
	    }
	    public Set<Asignatura> getAsignaturas()
	    {
	    	Session session = getSessionFactory().openSession();
		    List<Asignatura> result = session.createQuery("from Asignatura").list();
		    Set<Asignatura> asignaturas = new HashSet(result);
		    return asignaturas;
	    }
	    public Alumno getAlumno(int id)
	    {
	    	Session session = getSessionFactory().openSession();
		    List<Alumno> result = session.createQuery("from Alumno where id = " + id).list();
		    return result.get(0);
	    }
	    public Asignatura getAsignatura(int id)
	    {
	    	Session session = getSessionFactory().openSession();
		    List<Asignatura> result = session.createQuery("from Asignatura where id = " + id).list();
		    return result.get(0);
	    }
	 //-----------------------------------------------------------   
	public Alumno loginAlumno(int dni, String pass) throws UserNotFoundException, IncorrectPasswordException //probada
	{
		Session session = getSessionFactory().openSession();
		List<Alumno> result = session.createQuery("from Alumno where dni = " + dni).list();
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
		Session session = getSessionFactory().openSession();
	    List<Alumno> result = session.createQuery("from Alumno where id = " + idAlumno).list();
	    Set<Asignatura> asignaturas = result.get(0).getAsignaturas();
	    return asignaturas;
	}
	public void matricular(int idAlumno, int idAsignatura) //no guarda la sesion
	{
	/*	Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Alumno alumno = getAlumno(idAlumno);
		Asignatura asignatura = getAsignatura(idAsignatura);
		alumno.addAsignatura(asignatura);
		asignatura.addAlumno(alumno);
		tx.commit(); */
	/*	Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		List<Alumno> result = session.createQuery("from Alumno where id =" + idAlumno).list();
	/*	if(result == null)
		{
			Alumno alumno = new Alumno(3333, "ccc", "ccc", "333");
			Asignatura asignatura = new Asignatura();
			asignatura = this.getAsignatura(idAsignatura);
			alumno.addAsignatura(asignatura);
			session.save(alumno);
		}
		else */
	//	{
	/*		Alumno alumno = result.get(0);
			Set<Asignatura> asignaturas = alumno.getAsignaturas();
			boolean estaMatriculado = asignaturas.contains(session.createQuery("from Asignatura where id = " + idAsignatura).list().get(0));
			System.out.println(estaMatriculado);
			while(!estaMatriculado)
			{
				Asignatura asignatura = getAsignatura(idAsignatura);
				alumno.addAsignatura(asignatura);
			//	session.save(alumno);
				estaMatriculado = true;
			}
			tx.commit();
	//	} */
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Alumno alumno = getAlumno(idAlumno);
		Asignatura asignatura = getAsignatura(idAsignatura);
		alumno.addAsignatura(asignatura);
		asignatura.addAlumno(alumno);
		tx.commit();
	}
	public void desmatricular(int idAlumno, int idAsignatura) //no guarda la sesion
	{
		Session session = getSessionFactory().openSession();
		List<Alumno> result = session.createQuery("from Alumno where id =" + idAlumno).list();
		Alumno alumno = result.get(0);
		Set<Asignatura> asignaturas = alumno.getAsignaturas();
		// contains devuelve true o false si el set contiene la asignaura o no
		boolean estaMatriculado = asignaturas.contains(session.createQuery("from Asignatura where id = " + idAsignatura).list().get(0));
		System.out.println(estaMatriculado);
		if(estaMatriculado)
		{
			alumno.removeAsignatura(this.getAsignatura(idAsignatura));
			session.save(alumno);
		}
	}
	
	public Profesor loginProfesor(int dni, String pass) throws UserNotFoundException, IncorrectPasswordException //probada
	{
		Session session = getSessionFactory().openSession();
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
		Session session = getSessionFactory().openSession();
	    List<Asignatura> result = session.createQuery("from Asignatura where pr_id = " + idProfesor).list();
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
		Session session = getSessionFactory().openSession();
		List<Profesor> result = session.createQuery("from Profesor where pr_dni = " + dni).list();
		return result.get(0);
	}
	public List<Evaluacion> getEvaluacionesAsignatura(int idAsignatura) //probada
	{
		Session session = getSessionFactory().openSession();
		List<Evaluacion> result = session.createQuery("from Evaluacion where asignatura = " + idAsignatura).list();
		return result;
	}
	

	public static void main(String args[])
	{
	      PtDAO dao =  new PtDAO();
	      System.out.println("Asig1 Profesor.id:" + dao.getProfesor(1).getId());
	      System.out.println("Asig1 Alumnos:" + dao.getAlumnos(1).size());
	      List<Evaluacion> evaluacionesAlumno1 = dao.getEvaluacionesOrderedByAsignatura(1);
	      System.out.println("Evaluaciones Alumno1 Order by Asig:");
	      for (Evaluacion evaluacion: evaluacionesAlumno1)
	      {
	    	  System.out.println(evaluacion.getId());
	      }
	      Set<Evaluacion> evaluacionesAs1Al1 = dao.getEvaluaciones(1, 1);
	      System.out.println("Evaluaciones Alumno1 Asig1:");
	      for (Evaluacion evaluacion: evaluacionesAs1Al1)
	      {
	    	  System.out.print(evaluacion.getId());
	      }
	      System.out.println();
	      Set<Unidad> unidades = dao.getUnidades(1);
	      System.out.println("Unidades asig1:");
	      for (Unidad unidad: unidades)
	      {
	    	  System.out.println(unidad.getId());
	      }
	      Set<Asignatura> asignaturas = dao.getAsignaturas();
	      System.out.println("Asignaturas:");
	      for (Asignatura asignatura: asignaturas)
	      {
	    	  System.out.println(asignatura.getId());
	      }
	      Alumno alumno = dao.getAlumno(1);
	      System.out.println("Alumno1:" + alumno.getNombre());
	      Asignatura asignatura = dao.getAsignatura(1);
	      System.out.println("Asignatura1:" + asignatura.getNombre());
	      dao.addEvaluacion("dddd", 7, 1, 2);
	      
	      Set<Asignatura> asignaturasAl = dao.getAsignaturas(1);
	      System.out.println("Asignaturas de alumno 1: ");
	      for(Asignatura asignaturaAl: asignaturasAl)
	      {
	    	  System.out.println(asignaturaAl.getId());
	      }
	      
	      Set<Asignatura> asignaturasPr = dao.getAsignaturasProfesor(1);
	      System.out.println("Asignaturas profesor 1: ");
	      if(asignaturasPr == null)
	      {
	    	  System.out.println("esta a null");
	      }
	      for(Asignatura asignaturaPr: asignaturasPr)
	      {
	    	  System.out.println(asignaturaPr.getId());
	      }
	    
	     
	/*     Profesor profesor = null;
		try
		{
			profesor = dao.getProfesorByDni(1);
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     System.out.println("Profesor con dni " + profesor.getDni());
	     
	     List<Evaluacion> evaluaciones = dao.getEvaluacionesAsignatura(1);
	     System.out.println("Evaluaciones de asignatura 1: ");
	     for(Evaluacion evaluacionAs: evaluaciones)
	     {
	    	 System.out.println(evaluacionAs.getId());
	     }*/
	      dao.matricular(1, 2);
	      Alumno alumnoPrueba = dao.getAlumno(1);
	      System.out.println("alumno " + alumnoPrueba.getDni());
	      Set<Asignatura> asignaturasPrueba = alumnoPrueba.getAsignaturas();
	      for(Asignatura asignaturaPrueba :asignaturasPrueba)
	      {
	    	  System.out.println(asignaturaPrueba.getId());
	      }	
	 //     dao.desmatricular(2, 2);
	/*     Alumno alumnoLogin = null;
	     try
	     {
	    	 alumnoLogin = dao.loginAlumno(1, "1111");
	     }
	     catch (UserNotFoundException e){e.printStackTrace();}
	     catch (IncorrectPasswordException e) {e.printStackTrace();}
	     System.out.println(alumnoLogin.getNombre());
	     
	     Profesor profesorLogin = null;
	     try
	     {
	    	 profesorLogin = dao.loginProfesor(1, "1");
	     }
	     catch (UserNotFoundException e){e.printStackTrace();}
	     catch (IncorrectPasswordException e) {e.printStackTrace();}
	     System.out.println(profesorLogin.getNombre()); */
	}
	
}
