package AnalizadorLexico;

public class Simbolo {

	private int numero;
	private String lexema;
	private boolean esPR;

	public Simbolo(int numero, boolean esPR, String nombre ) { //constructor general
		super();
		this.numero = numero;
		this.lexema=nombre;
		this.esPR = esPR;
	}

	public Simbolo(int numero,  boolean esPR) { //constructor de PR
		super();
		this.numero = numero; //numero de palabra reservada (cada una tiene su numero)
		this.esPR = esPR;
	}
	
	public int getNumero() {
		return numero;
	}
	
	public boolean esPR() {
		return esPR;
	}
	
	public String getLexema() {
		return this.lexema;
	}
	
	public void print () {
		System.out.println("entro a ptrint");

		System.out.println("tipo: " + numero + " lexema " + lexema);
		System.out.println();
	}
}
