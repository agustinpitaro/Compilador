package AnalizadorLexico;

public abstract class AccionSemantica {

	public abstract void ejecutar(String buffer, char c, AnalizadorLexico AL); // si retorna 1 hay que retroceder el cursor y si retorna 0 seguimoooo'
}
