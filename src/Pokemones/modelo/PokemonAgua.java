package Pokemones.modelo;

/**
 * @author Grupo 9
 * Pokemon especifico de tipo Agua<br>
 * Vitalidad: 500<br>
 * Escudo: 100<br>
 * Fuerza: 120
 */
public class PokemonAgua extends Pokemon {

	public PokemonAgua(){
		super();
	}

	public PokemonAgua(String nombre) {
		super(nombre, 500, 100, 120);
	}

	/**
	 * El golpe final de este Pokemon causa daño igual al 125% su fuerza y luego se divide su
	 * fuerza por 2.
	 * <b>Pre:</b> Adversario no debe ser null.
	 * @param adversario Adversario al que se le aplica el daño
	 */
	@Override
	public void golpeFinal(Pokemon adversario) {
		adversario.recibeDamage(this.fuerza*1.25);
		this.fuerza/=2;
	}

	/**
	 * La recarga de este Pokemon le sube un 10% su fuerza y vitalidad.
	 */
	@Override
	protected String recarga() {
		this.fuerza*=1.1;
		this.vitalidad*=1.1;
		return getNombre() + " recargó.";
	}

	/**
	 * Al recibir daño este Pokemon lo distribuye 50/50 entre su escudo y su vida.
	 * <b>Pre:</b> Damage debe ser mayor que cero.
	 * @param damage Daño a aplicar
	 */
	@Override
	public void recibeDamage(double damage) {
		double extraDamage=0;
		if(this.escudo>damage/2)
			this.escudo-=damage/2;
		else {
			extraDamage=damage/2-this.escudo;
			this.escudo=0;
		}
		this.vitalidad-=(damage/2+extraDamage);
		if(this.vitalidad<0) {
			this.vitalidad=0;
		}
	}

	/**
	 * Niebla le reduce la fuerza un 25%.
	 */
	@Override
	public void niebla() {
		this.fuerza*=0.75;
	}

	/**
	 * Viento le reduce la vitalidad a la mitad.
	 */
	@Override
	public void viento() {
		this.vitalidad*=0.5;
	}

	/**
	 * Tormenta le reduce el escudo un 65%
	 */
	@Override
	public void tormenta() {
		this.escudo*=0.35;
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
		return "Agua";
	}


}
