package Pokemones.modelo;

/**
 * @author Grupo 9
 *Clase que representa a un entrenador junto a su pokemon en un combate.
 */
public class Equipo {
	private Entrenador e;
	private Pokemon p;
	
	public Equipo(Entrenador e, Pokemon p) {
		this.e = e;
		this.p = p;
	}
	public Entrenador getE() {
		return e;
	}
	public Pokemon getP() {
		return p;
	}
}
