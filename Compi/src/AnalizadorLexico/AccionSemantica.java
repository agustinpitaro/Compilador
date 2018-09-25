package AnalizadorLexico;

public abstract class AccionSemantica {

	public abstract int ejecutar(char c, AnalizadorLexico AL); // si retorna 1 hay que retroceder el
																				// cursor y si retorna 0 seguimoooo'

}
