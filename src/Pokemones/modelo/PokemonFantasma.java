package Pokemones.modelo;

/**
 * @author Grupo 9
 * Pokemon especifico de tipo Agua<br>
 * Vitalidad: 480<br>
 * Escudo: 90<br>
 * Fuerza: 130
 */
public class PokemonFantasma extends Pokemon {
	public PokemonFantasma(String nombre) {
		super(nombre, 480, 90, 130);
	}

	/**
	 * El golpe final de este Pokemon causa daño igual a su fuerza y luego pierde 50
	 * de vitalidad.
	 * <b>Pre:</b> Adversario no debe ser null.
	 * @param adversario Adversario al que se le aplica el daño
	 */
	@Override
	public void golpeFinal(Pokemon adversario) {
		adversario.recibeDamage(this.fuerza);
		this.vitalidad-=50;
	}

	/**
	 * Al recibir daño este Pokemon tiene una probabilidad del 25% de esquivar el golpe y no recibir daño.
	 * Caso contrario recibira el doble del daño aplicado primero a su armadura y luego a su vida.
	 * <b>Pre:</b> Damage debe ser mayor que cero.
	 * @param damage Daño a aplicar
	 */
	@Override
	public void recibeDamage(double damage) {
		if(Math.random()>0.25) {
			if(this.escudo>damage*2)
				this.escudo-=damage*2;
			else {
				this.vitalidad=damage*2-this.escudo;
				this.escudo=0;
			}
		}
		else
			System.out.println(this.getNombre()+" esquivo el golpe");
	}

	/**
	 * Niebla le reduce el escudo en un 10%.
	 */
	@Override
	public void niebla() {
		this.escudo*=0.9;
	}

	/**
	 * Viento le reduce la vitalidad en un 20%.
	 */
	@Override
	public void viento() {
		this.vitalidad*=0.8;
	}

	/**
	 * Tormenta le reduce la fuerza un 25%
	 */
	@Override
	public void tormenta() {
		this.fuerza*=0.75;
	}

	/**
	 * Este Pokemon acepta clonacion.
	 * @return Un clon exacto de este Pokemon
	 */
	@Override
	public Object clone() {
		Object clon = null;
		try {
			clon = super.clone();
		} catch (CloneNotSupportedException e) {
				//nunca se ejecuta
			e.printStackTrace();
		}
		return clon;
	}

	@Override
	String getTipo() {
		return "Fantasma";
	}
	
}
