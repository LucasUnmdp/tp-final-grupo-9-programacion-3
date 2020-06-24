package Pokemones.modelo;

/**
 * @author Grupo9
 *Pokemon de hielo <br>
 *es un pokemon que tiene vitalidad=500 escudo 120 y fuerza 100
 */
public class PokemonHielo extends Pokemon {
	private boolean granRecarga;
	
	public PokemonHielo(String nombre,boolean granRecarga) {
		super(nombre, 500, 120, 100);
		this.granRecarga=granRecarga;
	}
	@Override
	/**
	 * Este metodo realiza un golpe final al adversario<br>
	 * <b>Pre: </b>El pokemon adversario debe ser distinto de null<br>
	 * <b>Post: </b>El pokemon actual realizara un golpe final al adversario<br>
	 * @param adversario es el adversario que recibe el golpe
	 */
	public void golpeFinal(Pokemon adversario) {
		adversario.recibeDamage(this.fuerza*0.9);
	}
	/**
	 * Este metodo sirve para que pokemon actual reciba damage<br>
	 * <b>Pre: </b>damage debe ser positivo<br>
	 * <b>Post: </b>El pokemon actual sufre da�o<br>
	 * @param damage es el da�o que recibe el pokemon
	 */
	@Override
	public void recibeDamage(double damage) {
		double extraDamage=0;
		if(this.escudo>damage*0.75)
			this.escudo-=damage*0.75;
		else {
			extraDamage=damage*0.75-this.escudo;
			this.escudo=0;
		}
		this.vitalidad-=(damage*0.25+extraDamage);
		if(this.vitalidad<0) {
			this.vitalidad=0;
		}
	}

	@Override
	protected void recarga() {
		if(granRecarga){
			fuerza = 400;
		}else{
			super.recarga();
		}
	}

	public boolean isGranRecarga() {
		return granRecarga;
	}
	public void setGranRecarga(boolean granRecarga) {
		this.granRecarga = granRecarga;
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
		this.fuerza*=0.85;
	}
	/** 
	 * Este metodo realiza la clonacion de un pokemon de hielo, puede fallar.
	 * @throws CloneNotSupportedException Excepcion lanzada cuando el objeto no se pudo clonar, en esta caso el pokemon de hielo NO SE PUEDE CLONAR.
	 **/
	public Object clone() throws CloneNotSupportedException {
		
		throw new CloneNotSupportedException("No se puede clonar");
	}

	@Override
	String getTipo() {
		return "Hielo";
	}

	@Override
	public String toString() {
		return super.toString() + " (Gran recarga: "+ (granRecarga ? "SI" : "NO") +")";
	}
}
