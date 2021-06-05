package iso3.pt.model;

import iso3.pt.dao.IncorrectPasswordException;
import iso3.pt.dao.PtDAO;
import iso3.pt.dao.UserNotFoundException;
import iso3.pt.service.PtDaoService;

import java.util.*;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
 

public class Test { 
	
		SessionFactory sessionFactory;
		
		public Test(){
			sessionFactory = new Configuration().configure().buildSessionFactory();
		}
	
		public void inserciones1(){
	    	
	        Session session = sessionFactory.openSession();
	        Transaction tx = session.beginTransaction();
	     
	        Profesor prof1 = new Profesor(1, "1", "a", "1", "a","1");
	        Profesor prof2 = new Profesor(2, "2", "b", "3", "b","2");	        
	        
	        
	        Asignatura asig1 = new Asignatura(1, "a", "1");
	        Asignatura asig2 = new Asignatura(2, "b", "2");
	        Asignatura asig3 = new Asignatura(3,"c","3");
	        
	        asig1.setProfesor(prof1);
	        asig2.setProfesor(prof2);
	        asig3.setProfesor(prof1);
	        
	        Unidad uni1 = new Unidad("aa", "1a","aaaaaa");
	        Unidad uni2 = new Unidad("bb", "1b","bbbbbb");
	        Unidad uni3 = new Unidad("cc", "1c","cccccc");
	        
	        
	        asig1.addUnidad(uni1);
	        asig1.addUnidad(uni3);
	        asig2.addUnidad(uni2); 
	        /*
	        asig1.removeUnidad(uni1);
	        asig2.removeUnidad(uni2);
	        */
	        Alumno alu1 = new Alumno(1,"1111","a","1234");
	        Alumno alu2 = new Alumno(2,"2222","b","4321");
	        
	        alu1.addAsignatura(asig1);
	        alu2.addAsignatura(asig2);
	        alu1.addAsignatura(asig3);
	        
	        asig1.addAlumno(alu1);
	        asig2.addAlumno(alu2);
	        asig3.addAlumno(alu1);
	        
	        Evaluacion eval1 = new Evaluacion("aaaa", 8);
	        Evaluacion eval2 = new Evaluacion("bbbb",9);
	        Evaluacion eval3 = new Evaluacion("cccc",10);
	 
	        
	        eval1.setAlumno(alu1);
	        eval2.setAlumno(alu2);
	        eval3.setAlumno(alu1);
	        
	        alu1.addEvaluacion(eval1);
	        alu2.addEvaluacion(eval2);
	        alu1.addEvaluacion(eval3);
	        
	        eval1.setAsignatura(asig1);
	        eval2.setAsignatura(asig2);
	        eval3.setAsignatura(asig3);
	        
	        
	        session.save(prof1); 
	        session.save(prof2);
	        
	        session.save(asig1); 
	        session.save(asig2);
	        session.save(asig3);
	        
	        session.save(uni1);
	        session.save(uni2);
	        session.save(uni3);
	        
	        session.save(alu1);
	        session.save(alu2);
	        
	        session.save(eval1);
	        session.save(eval2);
	        session.save(eval3);

	        
	        System.out.println(asig1.getUnidades().size());
	        
	        tx.commit();
	        session.close();
	        System.out.println("Done inserciones1!");
		}
		
		
		public void close(){
	        sessionFactory.close();
		}
		
		
	    public static void main(String[] args) {
	    	/*	Test t1 = new Test();
	    	t1.inserciones1();
	    	t1.busquedaPK();
	    	t1.inserciones2();
	    	t1.busquedaCompleja();
	    	t1.borrado();
	    	t1.inserciones3(); 
	    	t1.close();*/
	    	
	    	//--------------------------------------------------
	    	//INSERCIONES EN LA BASE DE DATOS
	    	//--------------------------------------------------
	    	/*
	    	Test t1 = new Test();
	    	t1.inserciones1();
	    	*/
	    	//--------------------------------------------------
	    	//FUNCIONES DE PRUEBA DE LA CLASE PtDAO
	    	//--------------------------------------------------
	    	
			  PtDaoService dao = new PtDaoService();
			  //getProfesor
			  System.out.println("Asig1 Profesor.id:" + dao.getProfesor(1).getId());
			  //getAlumnos
		      System.out.println("Asig1 Alumnos:" + dao.getAlumnos(1).size());
		      //getEvaluacionesOrderedByAsignatura
		      List<Evaluacion> evaluacionesAlumno1 = dao.getEvaluacionesOrderedByAsignatura(1);
		      System.out.println("Evaluaciones Alumno1 Order by Asig:");
		      for (Evaluacion evaluacion: evaluacionesAlumno1)
		      {
		    	  System.out.println(evaluacion.getId());
		      }
		      //getEvaluaciones
		      Set<Evaluacion> evaluacionesAs1Al1 = dao.getEvaluaciones(1, 1);
		      System.out.println("Evaluaciones Alumno1 Asig1:");
		      for (Evaluacion evaluacion: evaluacionesAs1Al1)
		      {
		    	  System.out.print(evaluacion.getId());
		      }
		      System.out.println();
		      //getUnidades
		      Set<Unidad> unidades = dao.getUnidades(1);
		      System.out.println("Unidades asig1:");
		      for (Unidad unidad: unidades)
		      {
		    	  System.out.println(unidad.getId());
		      }
		      //getAsignaturas
		      Set<Asignatura> asignaturas = dao.getAsignaturas();
		      System.out.println("Asignaturas:");
		      for (Asignatura asignatura: asignaturas)
		      {
		    	  System.out.println(asignatura.getId());
		      }
		      //getAlumno
		      Alumno alumno = dao.getAlumno(1);
		      System.out.println("Alumno1:" + alumno.getNombre());
		      //getAsignatura
		      Asignatura asignatura = dao.getAsignatura(1);
		      System.out.println("Asignatura1:" + asignatura.getNombre());
		      //addEvaluacion
		      dao.addEvaluacion("dddd", 7, 1, 2);
		      //getAsignaturas
		      Set<Asignatura> asignaturasAl = dao.getAsignaturas(1);
		      System.out.println("Asignaturas de alumno 1: ");
		      for(Asignatura asignaturaAl: asignaturasAl)
		      {
		    	  System.out.println(asignaturaAl.getId());
		      }
		      //getAsignaturasProfesor
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
		    
		     //getProfesorByDni
		     Profesor profesor = null;
			try
			{
				profesor = dao.getProfesorByDni(1);
			} catch (UserNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		     System.out.println("Profesor con dni " + profesor.getDni());
		     //getEvaluacionesAsignatura
		     List<Evaluacion> evaluaciones = dao.getEvaluacionesAsignatura(1);
		     System.out.println("Evaluaciones de asignatura 1: ");
		     for(Evaluacion evaluacionAs: evaluaciones)
		     {
		    	 System.out.println(evaluacionAs.getId());
		     }
		     //matricular
		      dao.matricular(1, 2);
		      Alumno alumnoPrueba = dao.getAlumno(1);
		      System.out.println("alumno " + alumnoPrueba.getDni());
		      Set<Asignatura> asignaturasPrueba = alumnoPrueba.getAsignaturas();
		      for(Asignatura asignaturaPrueba :asignaturasPrueba)
		      {
		    	  System.out.println(asignaturaPrueba.getId());
		      }	
		      //desmatricular
		      dao.desmatricular(1, 2);
		      Alumno alumnoPrueba1 = dao.getAlumno(1);
		      System.out.println("alumno " + alumnoPrueba1.getDni());
		      Set<Asignatura> asignaturasPrueba1 = alumnoPrueba1.getAsignaturas();
		      for(Asignatura asignaturaPrueba :asignaturasPrueba1)
		      {
		    	  System.out.println(asignaturaPrueba.getId());
		      }	
		      //alumnoLogin
		     Alumno alumnoLogin = null;
		     try
		     {
		    	 alumnoLogin = dao.loginAlumno(1, "1111");
		     }
		     catch (UserNotFoundException e){e.printStackTrace();}
		     catch (IncorrectPasswordException e) {e.printStackTrace();}
		     System.out.println(alumnoLogin.getNombre());
		     //profesorLogin
		     Profesor profesorLogin = null;
		     try
		     {
		    	 profesorLogin = dao.loginProfesor(1, "1");
		     }
		     catch (UserNotFoundException e){e.printStackTrace();}
		     catch (IncorrectPasswordException e) {e.printStackTrace();}
		     System.out.println(profesorLogin.getNombre()); 
	    }
	}
