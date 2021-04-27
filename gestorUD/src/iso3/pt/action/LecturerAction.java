package iso3.pt.action;
 


import iso3.pt.model.Alumno;
import iso3.pt.model.Asignatura;
import iso3.pt.model.Profesor;
import iso3.pt.service.PtDaoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class LecturerAction extends ActionSupport implements Preparable
{ 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Profesor profesor;
	private List<Asignatura> listaAsignaturas;
	private List<Alumno> listaAlumnos;
	private int subjectId;
	
	
	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

	public List<Asignatura> getListaAsignaturas() {
		return listaAsignaturas;
	}

	public void setListaAsignaturas(List<Asignatura> listaAsignaturas) {
		this.listaAsignaturas = listaAsignaturas;
	}

	public List<Alumno> getListaAlumnos() {
		return listaAlumnos;
	}

	public void setListaAlumnos(List<Alumno> listaAlumnos) {
		this.listaAlumnos = listaAlumnos;
	}

	
	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	@Override
	public void prepare() throws Exception
	{
		this.listaAsignaturas = new ArrayList<Asignatura>();
		PtDaoService dao = new PtDaoService();
		if(this.listaAsignaturas.size() == 0)
		{
			this.profesor = (Profesor)ActionContext.getContext().getSession().get("logged");
			Set<Asignatura> asignaturaSet = dao.getAsignaturasProfesor(this.profesor.getId());
			for (Asignatura asignatura: asignaturaSet)
			{
				this.listaAsignaturas.add(asignatura);
			}
		}
	}

	public String doListSubjects()
	{
		return SUCCESS;
	}
	
	public String doLogout()
	{
		Map session = ActionContext.getContext().getSession();
		if(session.get("logged")!=null)
		{
			session.remove("logged");
		}
		return "logout";
	}
	
	public String doShowSubjectStudents()
	{
		PtDaoService dao = new PtDaoService();
		this.listaAlumnos = new ArrayList<Alumno>();
		if(this.listaAlumnos.size() == 0)
		{
			Set<Alumno> alumnosSet = dao.getAlumnos(subjectId);
			for(Alumno alumno: alumnosSet)
			{
				this.listaAlumnos.add(alumno);
			}
		}
		return "studentsList";
	}
	
	public String doAddMark()
	{
		return "addMark";
	}
}
