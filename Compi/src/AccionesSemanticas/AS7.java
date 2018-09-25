package AccionesSemanticas;

import AnalizadorLexico.AccionSemantica;
import AnalizadorLexico.AnalizadorLexico;
import AnalizadorLexico.Simbolo;

public class AS7 extends AccionSemantica {
	// Reconoce el double y verifica rango, sino llama a error

// Podemos unir la accion semantica 7 y la 6, pasando los numeros min y max
// como parametros al cosntructor. Lo que perderiaamos seria que en el msje
// no podriamos decir si fue un error de double o de linteger
private final static double max = 1.7976931348623157 * Math.pow(10, 308);
private final static double min = 2.2250738585072014 * Math.pow(10, -308);

public int ejecutar(char c, AnalizadorLexico AL) {
	String buffer = AL.getBuffer();
//	buffer = buffer +c;
	double n = getvalor(buffer);
	boolean bmin = n<min;
	boolean bmax = n > max;
	if ( bmin) { //si se va del rango
		Simbolo s= new Simbolo(263, false);
		AL.obtenerTS().agregarSimbolo(String.valueOf(min), s);
		AL.AgregarError(AL.getLinea(), "WARNING: Numero DOUBLE fuera de rango");
	}
	else{
		if (bmax){
			Simbolo s= new Simbolo(263, false);
			AL.obtenerTS().agregarSimbolo(String.valueOf(max), s);
			AL.AgregarError(AL.getLinea(), "WARNING: Numero DOUBLE fuera de rango");

		}
		else{
			Simbolo s= new Simbolo(263, false);
			AL.obtenerTS().agregarSimbolo(buffer, s);
		}
	}
	AL.setBuffer(buffer);
	return 0;
}

private double getvalor(String buffer) {
	int puntero = buffer.indexOf('D');
		if (puntero != -1) {
			String mantisa = buffer.substring(0, puntero);
			String exp = buffer.substring(puntero + 1, buffer.length());
			double salida = Double.parseDouble(mantisa) * Math.pow(10, Double.parseDouble(exp));
			return salida;
		} else {
			return Double.parseDouble(buffer);
		}
	}

}
