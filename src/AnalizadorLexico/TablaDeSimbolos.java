package AnalizadorLexico;
import java.util.Hashtable;

public class TablaDeSimbolos {

	private Hashtable<String, Simbolo> ts;
	
	public TablaDeSimbolos() {
		ts.clear();
		cargarTabla();
	}
	
	private void cargarTabla () {
		//Carga la tabla con las palabras reservadas y los lexemas iniciales
		Simbolo sID = new Simbolo(300, null);
		ts.put("id", sID); 
		
		Simbolo sCTE = new Simbolo(301, null);
		ts.put("cte", sCTE);
		
		Simbolo sIF = new Simbolo(310, true);
		ts.put("if", sIF);
		
		Simbolo sELSE = new Simbolo(311, true);
		ts.put("else", sELSE);
		
		Simbolo sENDIF = new Simbolo(312,true);
		ts.put("end_if", sENDIF);
		
		Simbolo sPRINT = new Simbolo(313,true);
		ts.put("print", sPRINT);
		
		//seguir
		
	}
	
	public void agregarSimbolo(String token , Simbolo simb) { //agrego tokens
		ts.put(token, simb);
	}
	
	public Simbolo obtenerSimbolo(String token) { // Obtenes el token entero
		return ts.get(token);
	}
	
	public boolean perteneceTS(String token){ //verificar existencia en ella
		return ts.contains(token);
	}
}
