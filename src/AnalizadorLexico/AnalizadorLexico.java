package AnalizadorLexico;

import java.util.ArrayList;
import Utilidades.Error;

public class AnalizadorLexico {

	private String fuente;
	private int indice;
	private AccionSemantica[][] mAS;
	private int[][] mEst;
	public TablaDeSimbolos ts;
	private int linea;
	private ArrayList<Error> errores;
	
	public AnalizadorLexico(String fuente, int indice, AccionSemantica[][] mAS, int[][] mEst, ArrayList<Error> errores) { //Crea el analizador lexico con el codigo fuente con ambas matrices
		super();
		this.fuente = fuente;
		this.indice = 0;
		this.mAS = mAS;
		this.mEst = mEst;
		this.errores=errores;
	}
	
	public Simbolo obtenerToken(){ //devolveria el simbolo
		String buffer = "";
		char c; // = fuente.charAt(indice);
		int estado = 0, estadoFuturo = 0, col = 0;
		while ((( estado != -1)/*1*/ && (indice< fuente.length())/*2*/ && (estado != 666))/*3*/ ) { //-1 = error 666=estado final
			c = fuente.charAt(indice);
			col = obtenerCol(c);
			if (col != 20){ //Verificas que no sea un simbolo extraño (Si es extraño va a buscar en un rango de la matriz inexistente y se rompe todo :v
				estadoFuturo = mEst[estado][col];
				mAS[estado][col].ejecutar(buffer, c, this); // mAS[estado] ( Si cae en un estado que no deberia, la AS tiene que agregar a la lista de errores (Linea de error, TipoError) )
				estado = estadoFuturo;
				indice++;
			}
			else {
				indice++; //lo ignoras y agregas a lista de errores
			}
			
	 	}
		if (estado == -1) { 
			/* Estas Acciones Semanticas son las que haria cuando llegue a un estado de error 
			 * AccError = devuelve un token error, y además lo agrega a una lista de errores para luego ser mostrado. 
			AccErrorDC: igual al anterior, pero además devuelve el carácter leído porque pertenece al siguiente 
						token. */
			indice--;  //(Retrocedo el puntero para que cuando me llamen nuevamente, tenerlo corregido)
			return obtenerToken(); //Me vuelvo a llamar hasta que retorne un token valido.
			
		}
		if( indice >= fuente.length()){
			return ts.obtenerSimbolo("fin de archivo"); // o algo asi jaja
		}
		//if ( estado == 666 ) Si se va del while es por 1,2,3 y como 1 y 2 ya las contemplamos si o si va a ser por 3
			//La ASF de cada tipo guarda en la TS el token ( por ejemplo cadena identifica que el token es una cadena de caracteres  y lo carga en la TS
		return ts.obtenerSimbolo(buffer);	
	}
	private int obtenerCol(char c) { //devuelve la columna de la matriz de estado correspondiente al caracter 
		int aux = 20;
        switch (c) {
        case 1: if (c>= 'a' && c<= 'Z' && c!='D' && c!= 'l') aux = 0;
             break;
        case 2: if (c>= '0' && c<= '9')  aux = 1;
        break;
        case 3: if (c== '*'||  c== '{'||  c== '}'|| c== ','|| c== ';'|| c== '('|| c== ')'||  c== '&')   aux = 2;
        break;
        case 4: if (c== '>')  aux = 3;
        break;
        case 5: if (c== '<')  aux = 4;
        break;
        case 6: if (c== ':')  aux = 5;
        break;
        case 7: if (c== '!')  aux = 6;
        break;
        case 8: if (c== '\n')  aux = 7;
        break;
        case 9: if (c== '=')  aux = 8;
        break;
        case 10: if (c== '-')  aux = 9;
        break;
        case 11: if (c== '.')  aux = 10;
        break;
        case 12: if (c== 'D')  aux = 11;
        break;
        case 13: if (c== '.')  aux = 12;
        break;
        case 14: if (c == 39)  aux = 13; //comilla simple = 39
        break;
        case 15: if (c== 'l')  aux = 14;
        break;
        case 16: if (c== ' ' || c == '\t')  aux = 15;
        break;
        case 17: if (c== '+')  aux = 16;
        break;
        default:  aux = 20; //Si detecta un simbolo fuera de mi lenguaje (S != L,D,etc) ya lo puedo detectar aca
        }
		return aux;
	}
	public void AgregarSimbolo(String token, Simbolo simbolo) { //Carga la tabla de simbolos manualmente (por ejemplo las palabras reservadas)
		if (!this.ts.perteneceTS(token)) {
			this.ts.agregarSimbolo(token, simbolo);
		}
	}
	public TablaDeSimbolos obtenerTS() {
		return this.ts;
	}
	public void decrementarIndice() {
		this.indice--;
	}
	
	public int getLinea() {
		return linea;
	}
	
	public void AumentarLinea() {
		linea++;
	}

	public void AgregarError(int linea, String detalle) {
		Error e = new Error(linea , detalle);
		errores.add(e);
	}
}