package Pokemones.modelo.excepciones;

@SuppressWarnings("serial")
public class PeleaNulaException extends Exception{
		public PeleaNulaException(String mensaje) {
			super(mensaje);
	}
}
