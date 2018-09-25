package AnalizadorLexico;
import java.util.Hashtable;

public class TablaDeSimbolos {

	private Hashtable<String, Simbolo> ts;
	
	public TablaDeSimbolos() {
		this.ts = new Hashtable<String, Simbolo>();
		ts.clear();
		cargarTabla();
	}
	
	private void cargarTabla () {
		//Carga la tabla con las palabras reservadas y los lexemas iniciales
		Simbolo sID = new Simbolo(257, false,"id"); //no podia ser 256
		ts.put(new String("id"), sID); 
		
		Simbolo sCTE = new Simbolo(258, false, "cte");
		ts.put(new String("cte"), sCTE);
		
		Simbolo sIF = new Simbolo(269, true, "if");
		ts.put(new String("if"), sIF);
		
		Simbolo sELSE = new Simbolo(259, true, "else");
		ts.put(new String("else"), sELSE);
		
		Simbolo sENDIF = new Simbolo(260,true, "end_if");
		ts.put(new String("end_if"), sENDIF);
		
		Simbolo sPRINT = new Simbolo(261,true, "print");
		ts.put(new String("print"), sPRINT);
		
		Simbolo sLINTEGER = new Simbolo(262,true, "linteger");
		ts.put(new String("linteger"), sLINTEGER);
		
		Simbolo sDOUBLE = new Simbolo(263,true, "double");
		ts.put(new String("double"), sDOUBLE);
		
		Simbolo sLOOP = new Simbolo(264,true, "loop");
		ts.put(new String("loop"), sLOOP);
		
		Simbolo sUNTIL = new Simbolo(265,true, "until");
		ts.put(new String("until"), sUNTIL);
		
		Simbolo sLET = new Simbolo(266,true, "let");
		ts.put(new String("let"), sLET);
		
		Simbolo sMayorIgual = new Simbolo(267,false,"mayigual");
		ts.put(new String(">="), sMayorIgual);
				
		Simbolo sMenorIgual = new Simbolo(268,false, "menigual"); ////////////PREGUNTAR
		ts.put(new String("<="), sMenorIgual);
		
		Simbolo sDISTINTO = new Simbolo(270,false, "distinto");
		ts.put(new String("!="), sDISTINTO);
		
		Simbolo sASIGNACION = new Simbolo(271,false,"asig");
		ts.put(new String(":="), sASIGNACION);
		
		Simbolo sMUT = new Simbolo(272,true, "mut");
		ts.put(new String("mut"), sMUT);

		Simbolo sFin = new Simbolo(273,false, "eof");
		ts.put(new String("eof"), sFin);
		
		Simbolo sCADENA = new Simbolo(274,false, "cadena");
		ts.put(new String("cadena"), sCADENA);
		
		///275 ES ERROR
		
	}
	
	public void agregarSimbolo(String buffer , Simbolo simb) { //agrego tokens
		ts.put(buffer, simb);
	}
	
	public Simbolo obtenerSimbolo(String buffer) { // Obtenes el token entero
		Simbolo salida = ts.get(buffer);
		return salida;
	}
	
	public boolean pertenece(String buffer){ //verificar existencia en ella
		boolean asd = ts.containsKey(buffer);
		return asd;
	}
	
	
}
