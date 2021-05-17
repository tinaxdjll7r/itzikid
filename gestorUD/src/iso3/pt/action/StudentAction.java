package iso3.pt.action;

import iso3.pt.dao.PtDAO;
import iso3.pt.model.Alumno;
import iso3.pt.model.Asignatura;
import iso3.pt.model.Evaluacion;
import iso3.pt.model.Profesor;

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
	private Alumno alumno;
	private static final long serialVersionUID = 1L;
	private int subjectId;
	
	
	
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
		PtDAO dao = PtDAO.getInstance();
		if(this.listaAsignaturas.size() == 0)
		{
			this.alumno = (Alumno)ActionContext.getContext().getSession().get("logged");
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
		return "evaluacionList";
	}
	
	public String doFormularioMatricular()
	{
		this.listaAsignaturas = new ArrayList<Asignatura>();
		PtDAO dao = PtDAO.getInstance();
		
		for (Iterator<Asignatura> i = dao.getAsignaturas().iterator(); i.hasNext();)
		{
			Asignatura asig = i.next();
			this.listaAsignaturas.add(asig);
		}
		return "formularioMatricular";
	}
	
	public String doMatricular()
	{	
		PtDAO dao = PtDAO.getInstance();
		dao.matricular(this.alumno.getDni(), this.subjectId);
		return "matricular";
	}
	public String doDesmatricular()
	{
		PtDAO dao = PtDAO.getInstance();
		dao.desmatricular(this.alumno.getDni(), this.subjectId);
		return "desmatricular";
	}

}
