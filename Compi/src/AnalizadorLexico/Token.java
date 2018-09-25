package AnalizadorLexico;

public class Token {
	private String lexema;
	private Integer linea;
	
	public Token(String lexema, Integer linea) {
		this.lexema= lexema;
		this.linea = linea;
	}
	public String getLexema() {
		return lexema;
	}
	public void setLexema(String lexema) {
		this.lexema = lexema;
	}
	public Integer getLinea() {
		return linea;
	}
	public void setLinea(Integer linea) {
		this.linea = linea;
	}
}
