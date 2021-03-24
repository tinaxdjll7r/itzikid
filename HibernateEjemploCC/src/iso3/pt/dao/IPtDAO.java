package iso3.pt.dao;

import java.util.*;

import iso3.hib.ejemploCC.Alumno;
import iso3.hib.ejemploCC.Asignatura;
import iso3.hib.ejemploCC.Profesor;
import iso3.hib.ejemploCC.Unidad;
import iso3.hib.ejemploCC.Evaluacion;

public interface IPtDAO {
	
	public Profesor getProfesor(int idAsignatura);
	public Set<Alumno> getAlumnos(int idAsignatura);
	public List<Evaluacion> getEvaluacionesOrderedByAsignatura(int idAlumno);
	public Set<Evaluacion> getEvaluaciones(int idAsignatura, int idAlumno);
	public void addEvaluacion(String concepto, float nota, int idAsignatura, int idAlumno);
	public Set<Unidad> getUnidades(int idAsignatura);
	public Set<Asignatura> getAsignaturas();
	public Alumno getAlumno(int id);
	public Asignatura getAsignatura(int id);
	public Alumno loginAlumno(int dni, String pass) throws UserNotFoundException, IncorrectPasswordException;
	public Set<Asignatura> getAsignaturas(int idAlumno);
	public void matricular(int idAlumno, int idAsignatura);
	public void desmatricular(int idAlumno, int idAsignatura);
	public Profesor loginProfesor(int dni, String pass) throws UserNotFoundException, IncorrectPasswordException;
	public Set<Asignatura> getAsignaturasProfesor(int idProfesor);
	public Profesor getProfesorByDni(int dni) throws UserNotFoundException;
	public List<Evaluacion> getEvaluacionesAsignatura(int idAsignatura);
}
