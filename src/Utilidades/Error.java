package Utilidades;

public class Error {
	private String detalle;
	private int linea;
	
	public String getDetalle() {
		return detalle;
	}
	public int getLinea() {
		return linea;
	}
	public Error(int linea, String detalle) {
		super();
		this.detalle = detalle;
		this.linea = linea;
	}
	
}
