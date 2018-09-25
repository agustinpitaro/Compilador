%{
package Parser;
import AnalizadorLexico.AnalizadorLexico;
import AnalizadorLexico.Simbolo;
import AnalizadorLexico.Token;

import java.util.ArrayList;
import java.util.List;


%}


%token ID CTE ELSE END_IF PRINT LINTEGER DOUBLE LOOP UNTIL LET MAYIGUAL MENIGUAL IF DISTINTO ASIG MUT EOF CADENA ERROR
		
		
%left '+' '-'
%left '*' '/'


%start programa

/* Comienzo del programa */

%%

programa				: sentDec sentEjec 
						; 
               
sentDec					: dVar { AgregarAccion("Uso la regla sentDec->dVar"); }
						| sentDec dVar { AgregarAccion("Uso la regla sentDec->sentDec dVar"); }          
						;
						
sentEjec				: sentencia ',' { AgregarAccion("Uso la regla sentEJEC->sentencia ,"); }
						| sentEjec sentencia ',' { System.out.println("Uso la regla sentEJEC-> sentejec sentencia ,"); }
						
						;


/* Sentencias declarativas */


dVar					: tipo listVar ',' { AgregarAccion("Uso la regla dVar -> tipo listVar"); }
						| LET MUT tipo listVarMut ','
						| LET tipo asignacionLET ','
						;

		

listVar					: ID  	{ AgregarAccion("Uso la regla listVar -> ID "); }			
						| listVar ';' ID 
						;
						
listVarMut				: VarMut 
						| listVarMut ';' VarMut

						;
						
VarMut                  : ID
                        | '*' ID		
						;
						
tipo					: LINTEGER							
						| DOUBLE							
						;


/* Sentencias Ejecutables */

sentencia				: estructIf 
						| estructLoop 
						| asignacion { AgregarAccion("sentencia -> asignacion "); }	
						| imprimir
						;


/* Asignacion */

asignacion 				: asignSimple  
                        | asignDeclar
						;
						
asignSimple             : ID ASIG expresion { AgregarAccion("asignacion -> ID ASIG expresion "); }	
						| '*' ID ASIG expresion
						;
						
asignDeclar             : tipo ID ASIG expresion { AgregarAccion("asignacion -> tipo ID ASIG expresion "); }
						;
/* Estructuras si y mientras */

estructIf				: IF '(' condicion ')' bloqueSent END_IF 					
						| IF '(' condicion ')' bloqueSent ELSE bloqueSent END_IF   
						;


estructLoop 			: LOOP  bloqueSent UNTIL '(' condicion ')' ','
						;


bloqueSent				: sentencia ',' 
						| '{' conjSent '}'
						;

conjSent   				: sentencia ','
						| conjSent sentencia ','
						;


/* Condiciones */    
               	  
condicion				: expresion  comparador expresion
						;

comparador				: '<'			
						| '>'			
						| MENIGUAL	
						| MAYIGUAL	
						| '='			
						| DISTINTO			
						;



asignacionLET           : ID ASIG CTE  
						;

/* Expresiones */

expresion 				: expresion '+' termino 
				        | expresion '-' termino 
				        | termino  { AgregarAccion("expresion -> termino "); }
						;
				       	

termino					: termino '*' factor 
						| termino '/' factor 
        				| factor { AgregarAccion("termino -> factor "); }
						;

factor					: ID  { AgregarAccion("factor -> ID "); }
						| CTE { AgregarAccion("factor -> CTE "); }
						| '-' CTE
						|'&' ID
						|'*' ID
						;


/* Imprimir */

imprimir				: PRINT '(' CADENA ')' 
						;

%%

private AnalizadorLexico al;
private List<String> acciones;
private List<Error> errores;

public void setAL(AnalizadorLexico al){
	this.al=al;
}

public Parser(String fuente){
		errores = new ArrayList <>();
		acciones = new ArrayList <>();
}


public int yylex()	{
	System.out.println("pide token");
	
	Simbolo s = this.al.getSimbolo();
	Token t = new Token (s.getLexema(), al.getLinea());
    yylval = new ParserVal(t);
    if (s.getNumero() == 273) return 0;
	return s.getNumero();
}

public void yyerror(String e){
	System.out.println(e);
}

public void AgregarAccion(String s){
	System.out.println(s);
	acciones.add(s + "en la linea "+ al.getLinea());
}
