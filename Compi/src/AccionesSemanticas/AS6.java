package AccionesSemanticas;

import AnalizadorLexico.AccionSemantica;
import AnalizadorLexico.AnalizadorLexico;
import AnalizadorLexico.Simbolo;


public class AS6 extends AccionSemantica{
	//Reconoce el Linteger y verifica rango, sino agrega error
	private final static double max = 2147483648.0;//era 47 pero como el negativo es mayor se toma en cuenta este numero.

	public int ejecutar(char c, AnalizadorLexico AL) {
		String buffer = AL.getBuffer();
		buffer = buffer +c;
		String num = buffer.substring(0, buffer.length()-2); //fijarse si es -2 o -3 //para sacarle el sufijo _l
		double n = Double.parseDouble(num);
		if ( n>max) { //si se va del rango
			Simbolo s= new Simbolo(262, false);
			AL.obtenerTS().agregarSimbolo(String.valueOf(max), s);
			AL.AgregarError(AL.getLinea(), "WARNING: Numero LINTEGER fuera de rango");
		}
		else {
			Simbolo s= new Simbolo(262, false);
			AL.obtenerTS().agregarSimbolo(buffer, s);
		}
		AL.setBuffer(buffer);
		return 0;		
	}
}
