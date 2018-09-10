package AccionesSemanticas;

import AnalizadorLexico.AccionSemantica;
import AnalizadorLexico.AnalizadorLexico;


public class AS7 extends AccionSemantica{
	//Reconoce el Linteger y verifica rango, sino llama a error
	
	//Podemos unir la accion semantica 7 y la 6, pasando los numeros min y max como parametros al cosntructor. Lo que perderiaamos seria que en el msje no podriamos decir si fue un error de double o de linteger
	private final static double min = 1.17549435 * Math.pow(10, -38);
	private final static double max = 3.40282347 * Math.pow(10, 38);
	
	public void ejecutar(String buffer, char c, AnalizadorLexico AL) {
		String num = buffer.substring(0, buffer.length()-2); //fijarse si es -2 o -3 //para sacarle el sufijo _l		
		double n = Double.parseDouble(num);
		if ( n>max || n<min ) { //si se va del rango
			AL.AgregarError(AL.getLinea(), "Numero DOUBLE fuera de rango");
		}
		
	}
}
