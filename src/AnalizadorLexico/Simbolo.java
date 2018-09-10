package AnalizadorLexico;

public class Simbolo {

	private int tipo;
	private String valor; //PREGUNTAR DE QUE TIPO ES ESTA VARIABLE
	private boolean esPR;

	public Simbolo(int tipo, String valor) { //constructor general
		super();
		this.tipo = tipo;
		this.valor = valor;
		this.esPR = false;
	}

	public Simbolo(int tipo,  boolean esPR) { //constructor de PR
		super();
		this.tipo = tipo; //numero de palabra reservada (cada una tiene su numero)
		this.valor = null;
		this.esPR = esPR;
	}
	public int getTipo() {
		return tipo;
	}
	public String getValor() {
		return valor;
	}
	public boolean esPR() {
		return esPR;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}	
}
