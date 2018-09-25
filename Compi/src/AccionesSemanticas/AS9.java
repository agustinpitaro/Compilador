package AccionesSemanticas;

import AnalizadorLexico.AccionSemantica;
import AnalizadorLexico.AnalizadorLexico;
import AnalizadorLexico.Simbolo;


public class AS9 extends AccionSemantica{
	//FinString (quito la primer comilla y omito la final)
	
	public int ejecutar(char c, AnalizadorLexico AL) {
		String buffer = AL.getBuffer();
		String aux = buffer.substring(1, buffer.length());
		int codigo = AL.obtenerTS().obtenerSimbolo("cadena").getNumero(); //Busco valor de cadena
		Simbolo s = new Simbolo(codigo,false, buffer);
		AL.AgregarSimbolo(aux, s);
		AL.setBuffer(aux);
		return 0;
	}
}