package iso3.hib.ejemploCC;

import java.util.HashSet;
import java.util.Set;

public class Asignatura
{
	private int id;
	private int codigo;
	private String nombre;
	private String creditos;
	private Profesor profesor;
	private Set<Unidad> unidades;
	private Set<Alumno> alumnos;
	
	public Asignatura() {
		super();
	}
	public Asignatura(int codigo, String nombre, String creditos) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.creditos = creditos;
		this.unidades = new HashSet<Unidad>();
		this.alumnos = new HashSet<Alumno>();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCreditos() {
		return creditos;
	}
	public void setCreditos(String creditos) {
		this.creditos = creditos;
	}
	public Profesor getProfesor() {
		return profesor;
	}
	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}
	public Set<Unidad> getUnidades() {
		return unidades;
	}
	public void setUnidades(Set<Unidad> unidades) {
		this.unidades = unidades;
	}
	
	
	public Set<Alumno> getAlumnos() {
		return alumnos;
	}
	public void setAlumnos(Set<Alumno> alumnos) {
		this.alumnos = alumnos;
	}
	public void addUnidad(Unidad unidad)
	{
		this.unidades.add(unidad);
	}
	public void removeUnidad(Unidad unidad)
	{
		this.unidades.remove(unidad);
	}
	
	public void addAlumno (Alumno alumno)
	{
		this.alumnos.add(alumno);
	}
	public void removeAlumno(Alumno alumno)
	{
		this.alumnos.remove(alumno);
	}
	public boolean estaMatriculado(Alumno alumno) 
	{
		return this.getAlumnos().contains(alumno);
	}
}
