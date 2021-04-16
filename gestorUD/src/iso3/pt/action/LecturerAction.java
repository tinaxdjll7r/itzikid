package iso3.pt.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import iso3.pt.dao.PtDAO;
import iso3.pt.model.Asignatura;
import iso3.pt.model.Profesor;

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

	@Override
	public void prepare() throws Exception
	{
		this.listaAsignaturas = new ArrayList<Asignatura>();
		PtDAO dao = PtDAO.getInstance();
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
}
