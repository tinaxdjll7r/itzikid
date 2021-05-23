package iso3.pt.action;


import iso3.pt.model.Alumno;
import iso3.pt.model.Asignatura;
import iso3.pt.model.Evaluacion;
import iso3.pt.model.Profesor;
import iso3.pt.model.Unidad;
import iso3.pt.service.PtDaoService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class StudentAction extends ActionSupport implements Preparable {

	private List<Asignatura> listaAsignaturas;
	private List<Asignatura> listaTodasAsignaturas;
	private Alumno alumno;
	private int studentDni;
	private static final long serialVersionUID = 1L;
	private int subjectId;
	
	
	
	public List<Asignatura> getListaTodasAsignaturas() {
		return listaTodasAsignaturas;
	}

	public void setListaTodasAsignaturas(List<Asignatura> listaTodasAsignaturas) {
		this.listaTodasAsignaturas = listaTodasAsignaturas;
	}

	public int getStudentDni() {
		return studentDni;
	}

	public void setStudentDni(int studentDni) {
		this.studentDni = studentDni;
	}

	public List<Asignatura> getListaAsignaturas() {
		return listaAsignaturas;
	}

	public void setListaAsignaturas(List<Asignatura> listaAsignaturas) {
		this.listaAsignaturas = listaAsignaturas;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	@Override
	public void prepare() throws Exception {
		this.listaAsignaturas = new ArrayList<Asignatura>();
		PtDaoService dao = new PtDaoService();
		this.alumno = (Alumno)ActionContext.getContext().getSession().get("logged");
		if(this.listaAsignaturas.size() == 0)
		{
			Set<Asignatura> asignaturaSet = dao.getAsignaturas(this.alumno.getDni());
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
	
	public String doShowMarks()
	{
		this.alumno = (Alumno)ActionContext.getContext().getSession().get("logged");
		return "evaluacionList";
	}
	
	public String doFormularioMatricular()
	{
		PtDaoService dao = new PtDaoService();
		this.listaTodasAsignaturas = new ArrayList<Asignatura>();
		Set<Asignatura> asignaturasSet = dao.getAsignaturas();
		
		for (Asignatura asignatura: asignaturasSet)
		{
			listaTodasAsignaturas.add(asignatura);
		}
		
		return "formularioMatricular";
	}
	
	public String doMatricular()
	{	
		PtDaoService dao = new PtDaoService();
		dao.matricular(this.alumno.getDni(), this.subjectId);
		this.cargarLista();
		return "matricular";
	}
	public String doDesmatricular()
	{
		PtDaoService dao = new PtDaoService();
		dao.desmatricular(this.alumno.getDni(), this.subjectId);
		this.cargarLista();
		return "desmatricular";
	}
	
	public void cargarLista()
	{
		try
		{
			this.prepare();
		}
		catch (Exception e) {e.printStackTrace();}
	}

}
