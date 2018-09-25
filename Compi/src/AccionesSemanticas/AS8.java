package AccionesSemanticas;

import AnalizadorLexico.AccionSemantica;
import AnalizadorLexico.AnalizadorLexico;


public class AS8 extends AccionSemantica{
	//Reconoce /n en 
	
	public int ejecutar(char c, AnalizadorLexico AL) {
		String buffer = AL.getBuffer();
		if (c == '\n' ) {
			String aux = buffer.substring(0, buffer.length()-1);
			AL.setBuffer(aux);
		}
		else {
			buffer = buffer + c;
			AL.setBuffer(buffer);
		}
		return 0;
	}
}
