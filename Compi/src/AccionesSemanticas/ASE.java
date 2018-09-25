package AccionesSemanticas;
import AnalizadorLexico.AccionSemantica;
import AnalizadorLexico.AnalizadorLexico;

public class ASE extends AccionSemantica{
	//error
	public int ejecutar(char c, AnalizadorLexico AL) {
		AL.AgregarError(AL.getLinea(), "Error: Token desconocido");
		return 1;
	}
}