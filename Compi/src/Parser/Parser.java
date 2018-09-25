//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";






//#line 2 "gram.y"
package Parser;
import AnalizadorLexico.AnalizadorLexico;
import AnalizadorLexico.Simbolo;
import AnalizadorLexico.Token;

import java.util.ArrayList;
import java.util.List;


//#line 27 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//public class ParserVal is defined in ParserVal.java


String   yytext;//user variable to return contextual strings
ParserVal yyval; //used to return semantic vals from action routines
ParserVal yylval;//the 'lval' (result) I got from yylex()
ParserVal valstk[];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
void val_init()
{
  valstk=new ParserVal[YYSTACKSIZE];
  yyval=new ParserVal();
  yylval=new ParserVal();
  valptr=-1;
}
void val_push(ParserVal val)
{
  if (valptr>=YYSTACKSIZE)
    return;
  valstk[++valptr]=val;
}
ParserVal val_pop()
{
  if (valptr<0)
    return new ParserVal();
  return valstk[valptr--];
}
void val_drop(int cnt)
{
int ptr;
  ptr=valptr-cnt;
  if (ptr<0)
    return;
  valptr = ptr;
}
ParserVal val_peek(int relative)
{
int ptr;
  ptr=valptr-relative;
  if (ptr<0)
    return new ParserVal();
  return valstk[ptr];
}
final ParserVal dup_yyval(ParserVal val)
{
  ParserVal dup = new ParserVal();
  dup.ival = val.ival;
  dup.dval = val.dval;
  dup.sval = val.sval;
  dup.obj = val.obj;
  return dup;
}
//#### end semantic value section ####
public final static short ID=257;
public final static short CTE=258;
public final static short ELSE=259;
public final static short END_IF=260;
public final static short PRINT=261;
public final static short LINTEGER=262;
public final static short DOUBLE=263;
public final static short LOOP=264;
public final static short UNTIL=265;
public final static short LET=266;
public final static short MAYIGUAL=267;
public final static short MENIGUAL=268;
public final static short IF=269;
public final static short DISTINTO=270;
public final static short ASIG=271;
public final static short MUT=272;
public final static short EOF=273;
public final static short CADENA=274;
public final static short ERROR=275;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    2,    2,    3,    3,    3,    6,    6,
    7,    7,    9,    9,    5,    5,    4,    4,    4,    4,
   12,   12,   14,   14,   15,   10,   10,   11,   18,   18,
   19,   19,   17,   20,   20,   20,   20,   20,   20,    8,
   16,   16,   16,   21,   21,   21,   22,   22,   22,   22,
   22,   13,
};
final static short yylen[] = {                            2,
    2,    1,    2,    2,    3,    3,    5,    4,    1,    3,
    1,    3,    1,    2,    1,    1,    1,    1,    1,    1,
    1,    1,    3,    4,    4,    6,    8,    7,    2,    3,
    2,    3,    3,    1,    1,    1,    1,    1,    1,    3,
    3,    3,    1,    3,    3,    1,    1,    1,    2,    2,
    2,    4,
};
final static short yydefred[] = {                         0,
   15,   16,    0,    0,    0,    2,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    3,    0,    0,   17,   18,
   19,   20,   21,   22,    9,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    4,    0,
    6,    0,   13,    0,    0,   11,    0,    8,   47,   48,
    0,    0,    0,    0,    0,   46,    0,    0,    0,   29,
    0,    0,    0,    0,    0,    5,    0,   10,   14,    7,
    0,   40,   49,   51,   50,    0,    0,    0,    0,   52,
   31,   30,    0,    0,   37,   36,   39,   34,   35,   38,
    0,    0,    0,    0,   12,    0,    0,   44,   45,   32,
    0,    0,    0,    0,    0,   26,   28,    0,   27,
};
final static short yydgoto[] = {                          4,
    5,   15,    6,   33,   34,   26,   45,   29,   46,   19,
   20,   21,   22,   23,   24,   63,   64,   35,   59,   91,
   55,   56,
};
final static short yysindex[] = {                      -190,
    0,    0, -215,    0,  -19,    0, -251, -217, -246, -255,
   19,   -9,   28, -239,   10,    0,   -2, -222,    0,    0,
    0,    0,    0,    0,    0,  -22,  -27, -201,   33,   18,
 -194,   10,   39, -173, -180,   18, -184,   42,    0, -183,
    0, -168,    0, -167,   -5,    0, -166,    0,    0,    0,
 -165, -163, -162,   22,   11,    0,   50,   52,    1,    0,
 -183,   57,  -11,   58,   18,    0,   18,    0,    0,    0,
  -27,    0,    0,    0,    0,   18,   18,   18,   18,    0,
    0,    0,   54,   18,    0,    0,    0,    0,    0,    0,
   18,   -9,   22,   22,    0,   11,   11,    0,    0,    0,
   59,   22, -185,   60,   -9,    0,    0, -159,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  102,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   -4,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   62,  -41,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   63,   64,    0,  -36,  -31,    0,    0,    0,
    0,   68,    0,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,    0,   98,   12,   61,    0,    0,    0,   34,    0,
    0,    0,    0,    0,    0,  -29,   26,  -64,    0,    0,
    2,    3,
};
final static int YYTABLESIZE=279;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         43,
   54,   43,   43,   43,   41,   25,   41,   41,   41,   42,
   28,   42,   42,   42,   44,   30,   17,   37,   43,   43,
   43,   41,   14,   41,   41,   41,   38,  103,   42,   42,
   42,   76,   14,   77,   40,   93,   42,   94,   70,    9,
  108,   39,   14,   58,    1,    2,    1,    2,   88,   90,
   89,   14,   78,   71,    9,   53,    8,   79,   31,   52,
    7,  102,   51,    9,   76,   18,   77,   36,   27,   47,
   83,    1,    2,  105,  106,    3,   48,   96,   97,   57,
   98,   99,   60,   61,   62,   66,   65,   67,   68,   69,
   80,   72,   73,   74,   75,   81,   84,  100,   92,  104,
  109,    1,   16,  107,   95,   23,   24,   25,   33,  101,
    0,    0,    0,   32,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   82,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   43,   43,    0,   43,   43,
   41,   41,    0,   41,    0,   42,   42,   10,   42,    0,
    0,   11,    1,    2,   12,    0,    3,   10,    0,   13,
    0,   11,    1,    2,   12,   85,   86,   10,   87,   13,
    0,   11,    1,    2,   12,    0,   10,    0,    0,   13,
   11,    1,    2,   12,   49,   50,    0,    0,   13,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         41,
   30,   43,   44,   45,   41,  257,   43,   44,   45,   41,
  257,   43,   44,   45,   42,  271,    5,  257,   60,   61,
   62,   44,   42,   60,   61,   62,   15,   92,   60,   61,
   62,   43,   42,   45,  257,   65,   59,   67,   44,   44,
  105,   44,   42,   32,  262,  263,  262,  263,   60,   61,
   62,   42,   42,   59,   59,   38,  272,   47,   40,   42,
    0,   91,   45,    3,   43,    5,   45,   40,    8,  271,
   59,  262,  263,  259,  260,  266,   44,   76,   77,  274,
   78,   79,   44,  257,  265,   44,  271,  271,  257,  257,
   41,  258,  258,  257,  257,   44,   40,   44,   41,   41,
  260,    0,    5,   44,   71,   44,   44,   44,   41,   84,
   -1,   -1,   -1,  123,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  125,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  267,  268,   -1,  270,  257,
  267,  268,   -1,  270,   -1,  267,  268,  257,  270,   -1,
   -1,  261,  262,  263,  264,   -1,  266,  257,   -1,  269,
   -1,  261,  262,  263,  264,  267,  268,  257,  270,  269,
   -1,  261,  262,  263,  264,   -1,  257,   -1,   -1,  269,
  261,  262,  263,  264,  257,  258,   -1,   -1,  269,
};
}
final static short YYFINAL=4;
final static short YYMAXTOKEN=275;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,"'&'",null,"'('","')'","'*'","'+'",
"','","'-'",null,"'/'",null,null,null,null,null,null,null,null,null,null,null,
"';'","'<'","'='","'>'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,"'{'",null,"'}'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,"ID","CTE","ELSE","END_IF","PRINT",
"LINTEGER","DOUBLE","LOOP","UNTIL","LET","MAYIGUAL","MENIGUAL","IF","DISTINTO",
"ASIG","MUT","EOF","CADENA","ERROR",
};
final static String yyrule[] = {
"$accept : programa",
"programa : sentDec sentEjec",
"sentDec : dVar",
"sentDec : sentDec dVar",
"sentEjec : sentencia ','",
"sentEjec : sentEjec sentencia ','",
"dVar : tipo listVar ','",
"dVar : LET MUT tipo listVarMut ','",
"dVar : LET tipo asignacionLET ','",
"listVar : ID",
"listVar : listVar ';' ID",
"listVarMut : VarMut",
"listVarMut : listVarMut ';' VarMut",
"VarMut : ID",
"VarMut : '*' ID",
"tipo : LINTEGER",
"tipo : DOUBLE",
"sentencia : estructIf",
"sentencia : estructLoop",
"sentencia : asignacion",
"sentencia : imprimir",
"asignacion : asignSimple",
"asignacion : asignDeclar",
"asignSimple : ID ASIG expresion",
"asignSimple : '*' ID ASIG expresion",
"asignDeclar : tipo ID ASIG expresion",
"estructIf : IF '(' condicion ')' bloqueSent END_IF",
"estructIf : IF '(' condicion ')' bloqueSent ELSE bloqueSent END_IF",
"estructLoop : LOOP bloqueSent UNTIL '(' condicion ')' ','",
"bloqueSent : sentencia ','",
"bloqueSent : '{' conjSent '}'",
"conjSent : sentencia ','",
"conjSent : conjSent sentencia ','",
"condicion : expresion comparador expresion",
"comparador : '<'",
"comparador : '>'",
"comparador : MENIGUAL",
"comparador : MAYIGUAL",
"comparador : '='",
"comparador : DISTINTO",
"asignacionLET : ID ASIG CTE",
"expresion : expresion '+' termino",
"expresion : expresion '-' termino",
"expresion : termino",
"termino : termino '*' factor",
"termino : termino '/' factor",
"termino : factor",
"factor : ID",
"factor : CTE",
"factor : '-' CTE",
"factor : '&' ID",
"factor : '*' ID",
"imprimir : PRINT '(' CADENA ')'",
};

//#line 154 "gram.y"

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
//#line 363 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 2:
//#line 30 "gram.y"
{ AgregarAccion("Uso la regla sentDec->dVar"); }
break;
case 3:
//#line 31 "gram.y"
{ AgregarAccion("Uso la regla sentDec->sentDec dVar"); }
break;
case 4:
//#line 34 "gram.y"
{ AgregarAccion("Uso la regla sentEJEC->sentencia ,"); }
break;
case 5:
//#line 35 "gram.y"
{ System.out.println("Uso la regla sentEJEC-> sentejec sentencia ,"); }
break;
case 6:
//#line 43 "gram.y"
{ AgregarAccion("Uso la regla dVar -> tipo listVar"); }
break;
case 9:
//#line 50 "gram.y"
{ AgregarAccion("Uso la regla listVar -> ID "); }
break;
case 19:
//#line 72 "gram.y"
{ AgregarAccion("sentencia -> asignacion "); }
break;
case 23:
//#line 83 "gram.y"
{ AgregarAccion("asignacion -> ID ASIG expresion "); }
break;
case 25:
//#line 87 "gram.y"
{ AgregarAccion("asignacion -> tipo ID ASIG expresion "); }
break;
case 43:
//#line 131 "gram.y"
{ AgregarAccion("expresion -> termino "); }
break;
case 46:
//#line 137 "gram.y"
{ AgregarAccion("termino -> factor "); }
break;
case 47:
//#line 140 "gram.y"
{ AgregarAccion("factor -> ID "); }
break;
case 48:
//#line 141 "gram.y"
{ AgregarAccion("factor -> CTE "); }
break;
//#line 564 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
