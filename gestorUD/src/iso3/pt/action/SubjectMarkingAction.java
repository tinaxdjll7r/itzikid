package iso3.pt.action;

import iso3.pt.model.Alumno;
import iso3.pt.model.Asignatura;
import iso3.pt.service.PtDaoService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class SubjectMarkingAction extends ActionSupport implements Preparable
{
	private int studentDni;
	private int subjectId;
	private Alumno alumno;
	private Asignatura asignatura;
	private String concepto;
	private float nota;
	
	
	public int getStudentDni() {
		return studentDni;
	}

	public void setStudentDni(int studentDni) {
		this.studentDni = studentDni;
	}

	
	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public Asignatura getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public float getNota() {
		return nota;
	}

	public void setNota(float nota) {
		this.nota = nota;
	}

	@Override
	public void prepare() throws Exception 
	{
		PtDaoService dao = new PtDaoService();
		this.alumno = dao.getAlumno(studentDni);
		this.asignatura = dao.getAsignatura(subjectId);	
	}
	
	public String doMark()
	{
		return SUCCESS;
	}
	
	public String doSubmitMark()
	{
		PtDaoService dao = new PtDaoService();
		dao.addEvaluacion(this.concepto, this.nota, this.subjectId, this.studentDni);
		return "showStudentMarks";
	}
}
