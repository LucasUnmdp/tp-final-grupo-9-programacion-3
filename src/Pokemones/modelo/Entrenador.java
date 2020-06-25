package Pokemones.modelo;

import Pokemones.modelo.excepciones.LimiteDeHechizoException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Random;

/**
 * @author Grupo 9
 *<br>
 *Clase que representa a un entrenador Pokemon. Contiene un nombre, una lista de pokemones y de cartas.
 */
public class Entrenador implements Cloneable, IClasificable, Serializable {
	private String nombre;
	private ArrayList<Pokemon> pokemones = new ArrayList<Pokemon>();
	private HashMap<String, Integer> cartas = new HashMap<String, Integer>();
	private int hechizosmax = Torneo.cantidadDeHechizosMax;

	/**
	 * Constructor con el parametro nombre del entrenador.
	 * <b>Pre:</b> El nombre del entrenador no puede ser nulo.
	 * @param nombre Nombre del entrenador.
	 */
	public Entrenador(String nombre) {
		this.nombre = nombre;
		cartas.put("TORMENTA", 1);
		cartas.put("NIEBLA", 1);
		cartas.put("VIENTO", 1);
	}

	public Entrenador(){}

	public String getNombre() {
		return nombre;
	}

	/**
	 * Metodo que le agrega una carta aleatoria del juego al entrenador.
	 */
	public void addCarta() {
		double random = Math.random();
		if (random <= 0.33)
			addCarta("NIEBLA");
		else if (random <= 0.66)
			addCarta("TORMENTA");
		else
			addCarta("VIENTO");
	}

	/**
	 * Agrega una carta especifica del juego al jugador.
	 * <b>Pre:</b> Se debe ingresar una carta existente, no puede ser nulo.
	 * @param carta Nombre de una carta especifica del juego.
	 */
	public void addCarta(String carta) {
		int aux;
		aux = cartas.get(carta.toUpperCase()) + 1;
		cartas.replace(carta.toUpperCase(), aux);
	}

	public ArrayList<Pokemon> getPokemones() {
		return pokemones;
	}

	public void addPokemon(Pokemon p) {
		pokemones.add(p);
	}

	public void removePokemon(Pokemon p) {
		pokemones.remove(p);
	}

	public Pokemon getPokemon(String nombre) {
		Pokemon p = null;
		boolean bandera = true;
		Iterator<Pokemon> it = pokemones.iterator();
		while (it.hasNext() && bandera) {
			p = it.next();
			if (p.getNombre().equalsIgnoreCase(nombre))
				bandera = false;
		}
		return p;
	}

	/**
	 * Metodo para obtener un pokemon aleatorio del entrenador.
	 * @return Pokemon aleatorio de la lista del entrenador.
	 */
	public Pokemon randomPokemon() {
		Random random = new Random();
		Pokemon retorno=null;
		if(pokemones.size()>0)
				retorno=pokemones.get(random.nextInt(pokemones.size()));
		return retorno;
	}

	/**
	 * Metodo que hechiza a un pokemon enemigo de forma aleatoria, si el entrenador no tiene la carta aleatoria que le toco no hace nada.
	 * <b>Pre:</b>El pokemon enemigo no puede ser nulo.
	 * @param p Pokemon enemigo al cual se quiere hechizar.
	 * @throws LimiteDeHechizoException Excepcion lanzada cuando el entrenador quiere lanzar un hechizo y llego a su limite de hechizos.
	 */
	public String hechiza(Pokemon p) throws LimiteDeHechizoException {
		double random = Math.random();
		String logLocal = "";

		int aux;
		if (this.hechizosmax > 0) {
			if (random <= 0.33 && cartas.get("NIEBLA") > 0) {
				this.hechizosmax--;
				p.niebla();
				logLocal = this.nombre + " utiliza NIEBLA contra " + p.getNombre();
				aux = cartas.get("NIEBLA") - 1;
				cartas.replace("NIEBLA", aux);
			} else if (random <= 0.66 && cartas.get("VIENTO") > 0) {
				this.hechizosmax--;
				p.viento();
				logLocal = this.nombre + " utiliza VIENTO contra " + p.getNombre();
				aux = cartas.get("VIENTO") - 1;
				cartas.replace("VIENTO", aux);
			} else if (cartas.get("TORMENTA") > 0) {
				this.hechizosmax--;
				p.tormenta();
				logLocal = this.nombre + " utiliza TORMENTA contra " + p.getNombre();
				aux = cartas.get("TORMENTA") - 1;
				cartas.replace("TORMENTA", aux);
			} else
				logLocal = this.nombre + "No utiliza hechizo";
		} else {
			throw new LimiteDeHechizoException("Limite de hechizos lanzados por " + this.nombre);
		}
		return logLocal;
	}

	/**
	 *Hace una clonacion profunda de un entrenador.
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		Entrenador clon = null;
		clon = (Entrenador) super.clone();
		clon.cartas = new HashMap<String, Integer>();
		for (Entry<String, Integer> e : cartas.entrySet()) {
			String key = e.getKey();
			Integer value = e.getValue();
			clon.cartas.put(key, value);
		}


		/**TODO
		 * Comment de la catedra: Al clonar los entrenadores usa NEW de la lista de Pokemones
		 * no permite que haya clases hijas de entrenador
		 */



		clon.pokemones = new ArrayList<Pokemon>();
		for (Pokemon p : pokemones) {
			clon.addPokemon((Pokemon) p.clone());
		}

		return clon;
	}

	/**
	 *Metodo que obtiene la categoria de un entrenador.
	 */
	@Override
	public String getCategoria() {
		String retorno;
		int xp=0;
		for(Pokemon p:pokemones)
			xp+=p.getXp();
		if(xp<=5) {
			retorno="Bronce";
		}else if(xp<=10) {
			retorno="Plata";
		}else if(xp<=15) {
			retorno="Oro";
		}else if(xp<=20) {
			retorno="Platino";
		}else
			retorno="Diamante";
		return retorno;
	}

	public void eliminarPokemon(Pokemon p){
		pokemones.remove(p);
	}

	@Override
	public String toString() {
		return this.nombre + "(Categoria: "+getCategoria()+")";
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setPokemones(ArrayList<Pokemon> pokemones) {
		this.pokemones = pokemones;
	}

	public HashMap<String, Integer> getCartas() {
		return cartas;
	}

	public void setCartas(HashMap<String, Integer> cartas) {
		this.cartas = cartas;
	}

	public int getHechizosmax() {
		return hechizosmax;
	}

	public void setHechizosmax(int hechizosmax) {
		this.hechizosmax = hechizosmax;
	}
}
