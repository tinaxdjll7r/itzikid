package iso3.pt.model;

import iso3.pt.dao.IncorrectPasswordException;
import iso3.pt.dao.PtDAO;
import iso3.pt.dao.UserNotFoundException;

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
	       /* 
	        Departamento dep1 = new Departamento("Ingenieria del Software");
	        Departamento dep2 = new Departamento("Servicios - ESIDE");
	        
	        Empleado emp1 = new Empleado(111, "Asier Perallos", "profesor", "perallos@eside.deusto.es");
	        Empleado emp2 = new Empleado(222, "Diego Lopez de Ipina", "profesor", "dipina@eside.deusto.es");
	        Empleado emp3 = new Empleado(333, "Maite Sanchez", "secretaria", "msanchez@eside.deusto.es");
	        Empleado emp4 = new Empleado(444, "Ana Carrera", "secretaria", "acarrera@eside.deusto.es");
	        
	        dep1.addEmpleado(emp1);
	        dep1.addEmpleado(emp2);
	        emp1.setDepartamento(dep1);
	        emp2.setDepartamento(dep1);
	        
	        session.save(dep1); // Se salvan los dos empleados del "dep1" en cascada
	        session.save(dep2);
	        
	        dep2.addEmpleado(emp3);
	        dep2.addEmpleado(emp4);
	        emp3.setDepartamento(dep2);
	        emp4.setDepartamento(dep2);
	        
	        session.save(emp3); // Innecesarias, se insertar�an los dos empleados al actualizar el "dep2"
	        session.save(emp4);
	        */
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
		
/*		public void busquedaPK(){

	        Session session = sessionFactory.openSession();
	        Transaction tx = session.beginTransaction();
	        
	        Empleado emp1 = (Empleado) session.get(Empleado.class, 111);
	        System.out.println(emp1);
	        emp1.setNombre("Asier Perallos Ruiz");
	        session.flush();
	        
	        Departamento dep = emp1.getDepartamento();
	        System.out.println(dep);
	        
	        Set<Empleado> empleados = dep.getEmpleados();
	        for (Iterator<Empleado> iter = empleados.iterator(); iter.hasNext();) {
	        	Empleado emp2 = iter.next();
	        	System.out.println(emp2);	
	        }
	        
	        tx.commit();
	        session.close();
	        System.out.println("Done busquedaPK!");
		}
		
		public void inserciones2(){
	    	
	        Session session = sessionFactory.openSession();
	        Transaction tx = session.beginTransaction();
	        
	        Direccion dir1 = new Direccion("Calle1 - Bilbao", 48007);
	        Direccion dir2 = new Direccion("Calle2 - Portugalete", 48920);
	        
	        session.save(dir1);
	        session.save(dir2);
	        
	        Empleado emp1 = (Empleado) session.get(Empleado.class, 111);
	        Empleado emp2 = (Empleado) session.get(Empleado.class, 222);
	        
	        dir1.addEmpleado(emp1);
	        dir1.addEmpleado(emp2);
	        
	        emp1.addDireccion(dir1);
	        emp2.addDireccion(dir1);
	        
	        dir2.addEmpleado(emp1);
	        emp1.addDireccion(dir2);
	        
	        tx.commit();
	        session.close();
	        System.out.println("Done inserciones2!");
	        
		}
		
		public void busquedaCompleja(){
	    	
	        Session session = sessionFactory.openSession();
	        Transaction tx = session.beginTransaction();
	        
	        List<Empleado> empleados = session.createQuery("from Empleado as emp where emp.departamento.nombre = 'Servicios - ESIDE' and emp.puesto = 'secretaria'").list();
	        
	        for (Iterator<Empleado> iter = empleados.iterator(); iter.hasNext();) {
	            Empleado emp1 = iter.next();
	            System.out.println(emp1);
	        }
	        
	        tx.commit();
	        session.close();
	        System.out.println("Done busquedaCompleja!");
	        
	        
		}
		
		public void borrado(){
	    	
	        Session session = sessionFactory.openSession();
	        Transaction tx = session.beginTransaction();
	        
	        Empleado emp1 = (Empleado) session.get(Empleado.class, 222);
	        
	        Set<Direccion> direcciones = emp1.getDirecciones();
	        
	        for (Iterator<Direccion> iter = direcciones.iterator(); iter.hasNext();) {
	            Direccion dir = iter.next();
	            emp1.removeDireccion(dir);
	        }
	        emp1.getDirecciones().clear();
	        emp1.getDepartamento().removeEmpleado(emp1);
	        
	        session.delete(emp1);
	        
	        tx.commit();
	        session.close();
	        System.out.println("Done borrado!");
		}
		
		public void inserciones3(){
	    	
	        Session session = sessionFactory.openSession();
	        Transaction tx = session.beginTransaction();
	        
	        Departamento dep3 = new Departamento("Comercial");
	        
	        Externo ext1 = new Externo(555, "Externo1", "profesor", "ext1@unicomer.es", "Iberdrola", "ext1@iberdrola.es", "bla, bla,...");
	        
	        ext1.setDepartamento(dep3);
	        dep3.addEmpleado(ext1);
	        
	        session.save(dep3);
	        session.save(ext1);
	        
	        Externo ext = (Externo) session.get(Externo.class, 555);
	        System.out.println(ext);
	        
	        tx.commit();
	        session.close();
	        System.out.println("Done inserciones3!");
		}
*/		
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
	    	//FUNCIONES DE PRUEBA DE LA CLASE PtDAO
	    	//--------------------------------------------------
	    	
			  PtDAO dao = PtDAO.getInstance();
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
