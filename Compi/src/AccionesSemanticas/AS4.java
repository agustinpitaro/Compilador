package AccionesSemanticas;

import AnalizadorLexico.AccionSemantica;
import AnalizadorLexico.AnalizadorLexico;

public class AS4 extends AccionSemantica{
	//fin simbolo1: devuelve el literal y 
	
	//PREGUNTAR SI ESTA ACCION SEMANTICA ES NECESARIA, YA QUE SOLO SE VA A LLEGAR POR CAMINOS VALIDOS
	
	public int ejecutar(char c, AnalizadorLexico AL) {
		String buffer = AL.getBuffer();
		if (c == '=' && buffer.length() == 1)
		{
			buffer = buffer +c;
			AL.setBuffer(buffer);
			return 0;
		}
		else if ( c != '=' && buffer.length() == 1)
		{
			return 1;
		}
		else if(buffer.length() == 0) {
			AL.setBuffer(String.valueOf(c));
			return 0;
		}
		return 0;
	}
}
