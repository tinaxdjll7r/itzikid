package iso3.pt.action;
   
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


import iso3.pt.dao.PtDAO;
import iso3.pt.model.Asignatura;
import iso3.pt.model.Evaluacion;
import iso3.pt.model.Unidad;


import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class SubjectAction extends ActionSupport implements Preparable
{ 
	private Asignatura asignatura;
	private int subjectId;
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


	public void prepare() throws Exception 
	{
		PtDAO dao = PtDAO.getInstance();
		this.asignatura = dao.getAsignatura(subjectId);
	}

	public String doShowSubjectUnits()   
	{
		PtDAO dao = PtDAO.getInstance();
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