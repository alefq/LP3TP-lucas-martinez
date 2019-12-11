package py.edu.uca.lp3exceptions;

public class InscripcionException extends Exception {

	private String contacto;

	public InscripcionException() {
		// TODO Auto-generated constructor stub
	}

	public InscripcionException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public InscripcionException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public InscripcionException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public InscripcionException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}


	public String getContacto() {
		return contacto;
	}

	public void setContacto(String contacto) {
		this.contacto = contacto;
	}
	
	

}
