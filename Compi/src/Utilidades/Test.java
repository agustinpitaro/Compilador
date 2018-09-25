package Utilidades;

import AnalizadorLexico.AnalizadorLexico;
import AnalizadorLexico.Simbolo;
import Parser.Parser; 

public class Test {
	
	public static void main (String [] args) {
		AnalizadorLexico al = new AnalizadorLexico("linteger _a,");
//		while (!al.isEOF()) {
//			Simbolo s = al.getSimbolo();
//			System.out.println(s.getNumero());
//			System.out.println();
//		}
		
		Parser p = new Parser("entro al parser");
		p.setAL(al);
		p.run();
		
	}
}
