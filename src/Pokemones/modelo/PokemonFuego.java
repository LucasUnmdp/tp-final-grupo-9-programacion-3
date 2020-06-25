package Pokemones.modelo;

/**
 * @author Grupo9
 *Pokemon de fuego <br>
 *es un pokemon que tiene vitalidad=530 escudo 200 y fuerza 80
 */
public class PokemonFuego extends Pokemon {

	public PokemonFuego(){super();}

	public PokemonFuego(String nombre) {
		super(nombre, 530, 200, 80);
	}
	
	/**
	 * Este metodo realiza un golpe final al adversario<br>
	 * <b>Pre: </b>El pokemon adversario debe ser distinto de null<br>
	 * <b>Post: </b>El pokemon actual realizara un golpe final al adversario<br>
	 * @param adversario es el adversario que recibe el golpe
	 */
	@Override
	public void golpeFinal(Pokemon adversario) {
		adversario.recibeDamage(this.fuerza);
		this.fuerza/=2;
	}
	@Override
	/**
	 * Este metodo realiza una recarga de fuerza y vitalidad<br>
	 * <b>Post: </b>El pokemon actual realizara una recarga<br>
	 */
	protected String recarga() {
		this.fuerza*=1.1;
		this.vitalidad*=1.1;
		return getNombre() + " recargó.";
	}
	/**
	 * Este metodo sirve para que pokemon actual reciba damage<br>
	 * <b>Pre: </b>damage debe ser positivo<br>
	 * <b>Post: </b>El pokemon actual sufre da�o<br>
	 * @param damage es el da�o que recibe el pokemon
	 */
	@Override
	public void recibeDamage(double damage) {
		if(this.escudo>damage)
			this.escudo-=damage;
		else {
			this.vitalidad-=damage-this.escudo;
			this.escudo=0;
		}
		if(this.vitalidad<0) {
			this.vitalidad=0;
		}
	}
	/**
	 * este metodo realiza los efectos del hechizo niebla sobre este pokemon, reduce su vitalidad
	 */
	@Override
	public void niebla() {
		this.vitalidad*=0.75;
	}
	/**
	 * este metodo realiza los efectos del hechizo viento sobre este pokemon, reduce su escudo
	 */
	@Override
	public void viento() {
		this.escudo*=0.8;
	}
	/**
	 * este metodo realiza los efectos del hechizo tormenta sobre este pokemon, reduce su fuerza
	 */
	@Override
	public void tormenta() {
		this.fuerza*=0.7;
	}
	/** 
	 * Este metodo realiza la clonacion de un pokemon de fuego, puede fallar.
	 * @throws CloneNotSupportedException Excepcion lanzada cuando el objeto no se pudo clonar, en esta caso el pokemon de fuego NO SE PUEDE CLONAR.
	 **/
	@Override
	public Object clone() throws CloneNotSupportedException {
		
		throw new CloneNotSupportedException("No se puede clonar");
	}

	@Override
	String getTipo() {
		return "Fuego";
	}
	
}
