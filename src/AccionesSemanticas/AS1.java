package AccionesSemanticas;
import AnalizadorLexico.AccionSemantica;
import AnalizadorLexico.AnalizadorLexico;

public class AS1 extends AccionSemantica{
	//Inicializa el buffer y agrega c a el mismo.
	@Override
	public void ejecutar(String buffer, char c, AnalizadorLexico AL) {
		buffer = new String("");
		buffer = buffer + c;
	}
}
