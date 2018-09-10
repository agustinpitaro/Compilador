package AccionesSemanticas;

import AnalizadorLexico.AccionSemantica;
import AnalizadorLexico.AnalizadorLexico;


public class AS8 extends AccionSemantica{
	//Reconoce el Linteger y verifica rango, sino llama a error

	
	public void ejecutar(String buffer, char c, AnalizadorLexico AL) {
		if (true) { //VER COMO PONEMOS EL /N      //Verificamos que venga un /n porque podria venir un d,l,' '
			String cadena = buffer.substring(0, buffer.length()-2); //fijarse si es -2 o -3 //para sacarle el /n	
			buffer=cadena; 
		}
	}
}
