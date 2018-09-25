package AccionesSemanticas;
import AnalizadorLexico.AccionSemantica;
import AnalizadorLexico.AnalizadorLexico;

public class AS2 extends AccionSemantica{
	//Agrega caracter al string
	@Override
	public int ejecutar(char c, AnalizadorLexico AL) {
		String buffer = AL.getBuffer();
		buffer = buffer + c;
		AL.setBuffer(buffer);
		return 0;
	}
}
