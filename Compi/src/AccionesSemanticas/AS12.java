package AccionesSemanticas;
import AnalizadorLexico.AccionSemantica;
import AnalizadorLexico.AnalizadorLexico;

public class AS12 extends AccionSemantica{
	//verifica PR
	@Override
	public int ejecutar(char c, AnalizadorLexico AL) {
		String buffer = AL.getBuffer();
		boolean pertenece = AL.obtenerTS().pertenece(buffer);
		if (pertenece) {
			boolean esPR = AL.obtenerTS().obtenerSimbolo(buffer).esPR();

			if (!esPR) {
				AL.AgregarError(AL.getLinea(), "Error PR no encontrada");
				return 1;
			}
		}
		else  {
			AL.AgregarError(AL.getLinea(), "Error PR no encontrada");
			return 1;
		}
		return 0;
	}
}
