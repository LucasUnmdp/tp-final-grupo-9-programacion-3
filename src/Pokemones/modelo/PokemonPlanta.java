package Pokemones.modelo;

/**
 * @author Grupo9
 * Pokemon tipo Planta <br>
 * es un pokemon que tiene vitalidad=600 escudo=180 y fuerza=50
 */
public class PokemonPlanta extends Pokemon {
	public PokemonPlanta(String nombre) {
		super(nombre, 600, 180, 50);
	}
	/**
	 * Este metodo realiza un golpe final al adversario<br>
	 * <b>Pre: </b>El pokemon adversario debe ser distinto de null<br>
	 * <b>Post: </b>El pokemon actual realizara un golpe final al adversario<br>
	 * @param adversario es el adversario que recibe el golpe
	 */
	@Override
	public void golpeFinal(Pokemon adversario) {
		adversario.recibeDamage(this.vitalidad - this.fuerza);
		this.fuerza *= 1.5;
	}

	@Override
	/**
	 * Este metodo realiza una recarga de vitalidad<br>
	 * <b>Post: </b>El pokemon actual realizara una recarga<br>
	 */
	protected void recarga() {
		this.vitalidad *= 1.5;
	}

	/**
	 * Este metodo sirve para que pokemon actual reciba damage<br>
	 * <b>Pre: </b>damage debe ser positivo<br>
	 * <b>Post: </b>El pokemon actual sufre da�o<br>
	 * @param damage es el da�o que recibe el pokemon
	 */
	@Override
	public void recibeDamage(double damage) {
		double extraDamage = 0;
		damage *= 0.75;
		if (this.escudo > damage / 2)
			this.escudo -= damage / 2;
		else {
			extraDamage = damage / 2 - this.escudo;
			this.escudo = 0;
		}
		this.vitalidad -= (damage / 2 + extraDamage);
		if(this.vitalidad<0) {
			this.vitalidad=0;
		}
	}

	/**
	 * este metodo realiza los efectos del hechizo niebla sobre este pokemon, reduce su vitalidad
	 */
	@Override
	public void niebla() {
		this.fuerza *= 0.95;
	}

	/**
	 * este metodo realiza los efectos del hechizo viento sobre este pokemon, reduce su escudo
	 */
	@Override
	public void viento() {
		this.escudo *= 0.85;
	}
	/**
	 * este metodo realiza los efectos del hechizo tormenta sobre este pokemon, reduce su fuerza
	 */
	@Override
	public void tormenta() {
		this.vitalidad *= 0.65;
	}
	/** 
	 * Este metodo realiza la clonacion de un pokemon tipo planta, puede fallar.
	 * @throws CloneNotSupportedException Excepcion lanzada cuando el objeto no se pudo clonar, en esta caso el pokemon de tipo planta NO SE PUEDE CLONAR.
	 **/
	public Object clone() throws CloneNotSupportedException {

		throw new CloneNotSupportedException("No se puede clonar");
	}
}
