package iso3.pt.action;
   
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import iso3.pt.model.Asignatura;
import iso3.pt.model.Evaluacion;
import iso3.pt.model.Unidad;
import iso3.pt.service.PtDaoService;


import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class SubjectAction extends ActionSupport implements Preparable
{ 
	private int subjectId;
	private int studentDni;
	private Asignatura asignatura;
	private List<Evaluacion> listaEvaluaciones;
	private List<Unidad> listaUnidades;
	
	public Asignatura getAsignatura() {
		return asignatura;
	}


	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}


	public int getSubjectId() {
		return subjectId;
	}


	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}


	public List<Evaluacion> getListaEvaluaciones() {
		return listaEvaluaciones;
	}


	public void setListaEvaluaciones(List<Evaluacion> listaEvaluaciones) {
		this.listaEvaluaciones = listaEvaluaciones;
	}


	public List<Unidad> getListaUnidades() {
		return listaUnidades;
	}


	public void setListaUnidades(List<Unidad> listaUnidades) {
		this.listaUnidades = listaUnidades;
	}

	
	public int getStudentDni() {
		return studentDni;
	}


	public void setStudentDni(int studentDni) {
		this.studentDni = studentDni;
	}


	public void prepare() throws Exception 
	{
		PtDaoService dao = new PtDaoService();
		this.asignatura = dao.getAsignatura(subjectId);
	}

	public String doShowSubjectUnits()   
	{
		PtDaoService dao = new PtDaoService();
		this.listaUnidades = new ArrayList<Unidad>();
		if(this.listaUnidades.size() == 0)
		{
			Set<Unidad> unidadSet = dao.getUnidades(subjectId);
			for (Unidad unidad: unidadSet)
			{
				listaUnidades.add(unidad);
			}
		}
		return "showUnitList";
	}
}
