package AnalizadorLexico;

import java.util.ArrayList;
import Utilidades.Error;
import AccionesSemanticas.*;

public class AnalizadorLexico {
	//hacer la variable de clase string y un metodo de clase actualizarString que lo vaya modificando de afuera
	
	private boolean EOF;
	public boolean isEOF() {
		return EOF;
	}
	private String fuente;
	private int indice;
	private AccionSemantica[][] mAS;
	private int[][] mEst;
	public TablaDeSimbolos ts;
	public int linea;
	private ArrayList<Error> errores;
	private String buffer;
	public String yyval;
	
	public AnalizadorLexico(String fuente) { //Crea el analizador lexico con el codigo fuente con ambas matrices
		this.fuente = fuente;
		this.indice = 0;
		this.mEst = CargarMatrizEstados();
		this.mAS = CargarMatrizSemantica();
		this.errores=new ArrayList<>();
		this.linea=1;
		this.ts = new TablaDeSimbolos();
		this.buffer="";
		this.EOF=false;
	}
	
	public TablaDeSimbolos getTS() {
		return this.ts;
	}
	private int [][] CargarMatrizEstados() {
		int [][] m = {  //69 estado final, -1 error
			{7,9,69,3,4,5,6,0,69,69,1,7,11,15,7,0,69,69},
			{2,2,-1,-1,-1,-1,-1,-1,-1,-1,14,2,-1,-1,2,-1,-1,-1},
			{2,2,69,69,69,69,69,69,69,69,69,2,69,69,2,69,69,69},
			{69,69,69,69,69,69,69,69,69,69,69,69,69,69,69,69,69,69},
			{69,69,69,69,69,69,69,69,69,69,69,69,69,69,69,69,69,69},
			{-1,-1,-1,-1,-1,-1,-1,-1,69,-1,-1,-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1,-1,69,-1,-1,-1,-1,-1,-1,-1,-1,-1},
			{7,69,69,69,69,69,69,69,69,69,7,7,69,69,7,69,69,69},
			{15,15,-1,-1,-1,-1,-1,15,-1,-1,-1,-1,-1,-1,-1,15,-1,-1},
			{-1,9,-1,-1,-1,-1,-1,-1,-1,-1,10,-1,11,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,69,-1,-1,-1},
			{69,11,69,69,69,69,69,69,69,69,69,12,69,69,69,69,69,69},
			{69,13,69,69,69,69,69,69,69,13,69,69,69,69,69,69,13,69},
			{69,13,69,69,69,69,69,69,69,69,69,69,69,69,69,69,69,69},
			{14,14,14,14,14,14,14,0,14,14,14,14,14,14,14,14,14,11}, //preguntar si en los comentarios puede venir cualquier cosa o restringimos
			{15,15,15,15,15,15,15,15,15,8,15,15,15,69,15,15,15,-1}};
		return m;
	}

	private AccionSemantica [][] CargarMatrizSemantica() {
		AccionSemantica a1 = new AS1();
		AccionSemantica a2 = new AS2();
		AccionSemantica a3 = new AS3();
		AccionSemantica a4 = new AS4();
		AccionSemantica a6 = new AS6();
		AccionSemantica a7 = new AS7();
		AccionSemantica a8 = new AS8();
		AccionSemantica a9 = new AS9();
		AccionSemantica a10 = new AS10();
		AccionSemantica a11 = new AS11();
		AccionSemantica a12 = new AS12();
		AccionSemantica aE = new ASE();

		AccionSemantica [][] m = {
			{a1,a1,a4,a1,a1,a1,a1,a10,a4,a4,a1,a1,a1,a1,a1,a11,a4,a11},
			{a2,a2,aE,aE,aE,aE,aE,aE,aE,aE,a2,aE,aE,a2,aE,aE,aE},
			{a2,a2,a3,a3,a3,a3,a3,a3,a3,a3,a3,a2,a3,a3,a2,a3,a3,a3},
			{a4,a4,a4,a4,a4,a4,a4,a4,a4,a4,a4,a4,a4,a4,a4,a4,a4,a4},
			{a4,a4,a4,a4,a4,a4,a4,a4,a4,a4,a4,a4,a4,a4,a4,a4,a4,a4},
			{aE,aE,aE,aE,aE,aE,aE,aE,a4,aE,aE,aE,aE,aE,aE,aE,aE,aE},
			{aE,aE,aE,aE,aE,aE,aE,aE,a4,aE,aE,aE,aE,aE,aE,aE,aE,a12},
			{a2,a12,a12,a12,a12,a12,a12,a12,a12,a12,a2,a2,a12,a12,a2,a12,a12,aE},
			{a8,a8,a8,a8,a8,a8,a8,a8,a8,a8,a8,a8,a8,a8,a8,a8,a8,aE}, //preguntar que onda con el guion y el /n
			{aE,a2,aE,aE,aE,aE,aE,aE,aE,aE,a2,aE,a2,aE,aE,aE,aE,aE,aE},
			{aE,aE,aE,aE,aE,aE,aE,aE,aE,aE,aE,aE,aE,aE,a6,aE,aE,aE},
			{a7,a2,a7,a7,a7,a7,a7,a7,a7,a7,a7,a2,a7,a7,a7,a7,a7,a7},
			{a7,a2,a7,a7,a7,a7,a7,a7,a7,a2,a7,a7,a7,a7,a7,a7,a2,a7},
			{a7,a2,a7,a7,a7,a7,a7,a7,a7,a7,a7,a7,a7,a7,a7,a7,a7,a7},
			{a2,a2,a2,a2,a2,a2,a2,a10,a2,a2,a2,a2,a2,a2,a2,a2,a2,a11}, //preguntar si en los comentarios puede venir cualquier cosa o restringimos
			{a2,a2,a2,a2,a2,a2,a2,aE,a2,a2,a2,a2,a2,a9,a2,a2,a2,aE}};
		return m;
	}
	
	
	
	public Simbolo getSimbolo(){ //devolveria el simbolo
		if (indice == fuente.length()){
			EOF=true;
			Simbolo s = new Simbolo(273, false);
			return s;
		}
		else
	    {
			char c= fuente.charAt(indice);
			int estado = 0, estadoFuturo = 0, col = 0,retroceder=0;

			while ((( estado != -1)/*1*/ && (indice< fuente.length())/*2*/ && (estado != 69))/*3*/ ) { //-1 = error 69=estado final
				c = fuente.charAt(indice);

				col = obtenerCol(c);

				if (col != 20){ //Verificas que no sea un simbolo extraño (Si es extraño va a buscar en un rango de la matriz inexistente y se rompe todo :v
					estadoFuturo = mEst[estado][col];
					retroceder = mAS[estado][col].ejecutar(c, this); // mAS[estado] ( Si cae en un estado que no deberia, la AS tiene que agregar a la lista de errores (Linea de error, TipoError) )
					if (retroceder==1) {
						indice--;
					}
					estado = estadoFuturo;
					indice++;
				}
				else {
					indice++; //lo ignoras y agregas a lista de errores
					Error e = new Error(linea,"Caracter invalido");
					errores.add(e);
				}
				
		 	}
			if (estado == -1) { 
				/* Estas Acciones Semanticas son las que haria cuando llegue a un estado de error 
				 * AccError = devuelve un token error, y además lo agrega a una lista de errores para luego ser mostrado. 
				AccErrorDC: igual al anterior, pero además devuelve el carácter leído porque pertenece al siguiente 
							token. */
				indice--;  //(Retrocedo el puntero para que cuando me llamen nuevamente, tenerlo corregido)
				return getSimbolo(); //Me vuelvo a llamar hasta que retorne un token valido.
				
			}
			if( estado != 69){ //Columna 17 = EoF
					retroceder = mAS[estado][17].ejecutar(c, this); // mAS[estado] ( Si cae en un estado que no deberia, la AS tiene que agregar a la lista de errores (Linea de error, TipoError) )
					estadoFuturo = mEst[estado][col];
					EOF=true;
					if (estadoFuturo==-1) {
						Error e = new Error(linea,"Caracter FINAL invalido");
						errores.add(e);
					}	
			}
			//La ASF de cada tipo guarda en la TS el token ( por ejemplo cadena identifica que el token es una cadena de caracteres  y lo carga en la TS
			Simbolo s= ts.obtenerSimbolo(buffer);
			if (s==null && buffer.length() == 1) {
				int aux = buffer.charAt(0);
				s = new Simbolo(aux, false);
			}
			else {
				if (s== null && buffer.length() >1) {
					Error e = errores.get(errores.size()-1);
					s = new Simbolo(275, false, e.getLinea() + " " + e.getDetalle());
				}
			}
			buffer = "";
			return s;	
		}
	}
	
	
	private int obtenerCol(char c) { //devuelve la columna de la matriz de estado correspondiente al caracter 
		int aux = 20;
		if (Character.isLetter(c) && c!='D' && c!= 'l') return 0;
        if (Character.isDigit(c)) return 1;
        if (c== '*'||  c== '{'||  c== '}'|| c== '/' || c== ','|| c== ';'|| c== '('|| c== ')'||  c== '&')    return 2;
        if (c== '>')   return 3;
        if (c== '<')   return 4;
        if (c== ':')   return 5;
        if (c== '!')   return 6;
        if (c== '\n')   return 7;
        if (c== '=')   return 8;
        if (c== '-')   return 9;
        if (c== '_')   return 10;
        if (c== 'D')   return 11;
        if (c== '.')   return 12;
        if (c == 39)   return 13; //comilla simple = 39
        if (c== 'l')   return 14;
        if (c== ' ' || c == '\t')   return 15;
        if (c== '+')   return 16;
        return aux;
	}
	
	public ArrayList<Error> getErrores(){
		return errores;
	}
	
	public void AgregarSimbolo(String buffer, Simbolo simbolo) { //Carga la tabla de simbolos manualmente (por ejemplo las palabras reservadas)
		if (!this.ts.pertenece(buffer)) {
			this.ts.agregarSimbolo(buffer, simbolo);
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

	public Simbolo AgregarError(int linea, String detalle) {
		Error e = new Error(linea , detalle);
		errores.add(e);
		Simbolo salida = new Simbolo (275, false, detalle+ " " + linea);
		return salida;
	}
	
	public void setBuffer(String buffer) {
		this.buffer = buffer;
		
	}
	public String getBuffer() {
		return this.buffer;
	}

	public void ImprimirErrores() {
		for (Error e : errores)
			System.out.println(e.getLinea() + "  " + e.getDetalle());
	}
}