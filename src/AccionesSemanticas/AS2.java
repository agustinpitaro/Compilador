package AccionesSemanticas;
import AnalizadorLexico.AccionSemantica;
import AnalizadorLexico.AnalizadorLexico;

public class AS2 extends AccionSemantica{
	//Agrega caracter al string
	@Override
	public void ejecutar(String buffer, char c, AnalizadorLexico AL) {
		buffer = buffer + c;
	}
}
