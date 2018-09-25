package AccionesSemanticas;

import AnalizadorLexico.AccionSemantica;
import AnalizadorLexico.AnalizadorLexico;


public class AS10 extends AccionSemantica{
	//sumo al contador de linea
	public int ejecutar(char c, AnalizadorLexico AL) {
		AL.AumentarLinea();	
		return 0;
	}
}