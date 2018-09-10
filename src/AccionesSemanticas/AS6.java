package AccionesSemanticas;

import AnalizadorLexico.AccionSemantica;
import AnalizadorLexico.AnalizadorLexico;


public class AS6 extends AccionSemantica{
	//Reconoce el Linteger y verifica rango, sino llama a error
	
	private final static double min = -2147483648.0;
	private final static double max = 2147483647.0;

	public void ejecutar(String buffer, char c, AnalizadorLexico AL) {
		String num = buffer.substring(0, buffer.length()-2); //fijarse si es -2 o -3 //para sacarle el sufijo _l
		double n = Double.parseDouble(num);
		if ( n>max || n<min ) { //si se va del rango
			AL.AgregarError(AL.getLinea(), "Numero LINTEGER fuera de rango");
		}
		
	}
}
