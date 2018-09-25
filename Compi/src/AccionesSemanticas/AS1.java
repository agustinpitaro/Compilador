package AccionesSemanticas;
import AnalizadorLexico.AccionSemantica;
import AnalizadorLexico.AnalizadorLexico;

public class AS1 extends AccionSemantica{
	//Inicializa el buffer y agrega c a el mismo.
	@Override
	public int ejecutar(char c, AnalizadorLexico AL) {
		String buffer = "";
		buffer = buffer + c;
		AL.setBuffer(buffer);
		return 0;
	}

}
