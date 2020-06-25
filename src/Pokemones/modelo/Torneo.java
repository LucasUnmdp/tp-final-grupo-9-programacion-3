package Pokemones.modelo;

import Pokemones.modelo.excepciones.YaExisteEntrenadorException;
import Pokemones.vista.Ventana;

import java.util.ArrayList;

/**
 * @author Grupo9
 * Torneo <br>
 * esta clase simula un torneo, tiene entrenadores que se enfrentaran en batallas, tambien guarda un historial de esos enfrentamientos.
 */
public class Torneo {
	private static Torneo instance =null;
	private int cantidadDeEntrenadores;
	private ArrayList<Entrenador> participantes = new ArrayList<Entrenador>();
	public static final int cantidadDeHechizosMax=4;
	private ArrayList<String> historial = new ArrayList<String>();
	private ArrayList<Arena> arenas = new ArrayList<>();

    private Torneo(){
		crearArenas();
	}

	public void inicializar(){
    	setCantidadDeEntrenadores(8);
	}

	/**
	 * La clase torneo responde al patron singleton.
	 */
	public static Torneo getInstance() {
		if(instance==null) 
			instance=new Torneo();
		return Torneo.instance;
	}

	private void crearArenas(){
		for(int i=0; i<4; i++)
			arenas.add(new Arena());
		Arena aCerrada = new Arena();
		aCerrada.setArenaState(new CerradoArenaState(aCerrada));
		arenas.add(aCerrada);
	}


	/**
	 * Este metodo agrega entrenador a la lista de entrenadores del torneo.
	 * <b>Pre: </b>el pametro entrenador debe ser distinto de null<br>
	 * <b>Post: </b>el entrenador es agregado al torneo si hay lugar<br> 
	 * @param entrenador es el entrenador que se agrega 
	 */
	public void addEntrenador(Entrenador entrenador) throws YaExisteEntrenadorException{
		if(participantes.contains(entrenador)){
			throw new YaExisteEntrenadorException();
		}

		if(participantes.size()<this.cantidadDeEntrenadores)
			participantes.add(entrenador);
	}
	
	public int getCantidadDeEntrenadores() {
		return cantidadDeEntrenadores;
	}
	/**
	 * <b>Pre: </b>La cantidad de entrenadores debera ser pontencia de 2, osea 2,4,8,16...<br>
	 * <b>Post: </b>Es establecida la cantidad de entrenadores que tendra el torneo<br>
	 * @param cantidadDeEntrenadores sera la cantidad de jugadores que admitira el torneo
	 */
	public void setCantidadDeEntrenadores(int cantidadDeEntrenadores) {
		this.cantidadDeEntrenadores = cantidadDeEntrenadores;
		Ventana.getInstance().setCantParticipantes(cantidadDeEntrenadores, true);
	}
	
	/**
	 * <b>Pre: </b>El entrenador e debe pertener a la lista y distinto de null<br>
	 * <b>Post: </b>el entrenador sera removio de la lista de entrenadores del toreno<br>
	 * @param e es un entrenador que sera removido
	 */
	public void removeEntrenador(Entrenador e) {
		participantes.remove(e);
		Ventana.getInstance().actualizarTorneo(participantes);
	}
	/**
	 * Este metodo realiza todos los cruces del torneo, octavos de final , cuartos de final, etc. Dependiendo de la cantidadDeEntrenadores. Realiza todos los enfrentamientos y guarda en el historial los resultados.
	 * <b>Pre: </b>la cantidad de entrenadores en la lista de entrenadores es igual a la cantidadDeEntrenadores establecida<br>
	 * <b>Post: </b>Se ejecuta el torneo<br>
	 */
	public void comienzaTorneo() {
		ArrayList<Entrenador> participantes = new ArrayList<Entrenador>(this.participantes);
		while(participantes.size()>1) {
			System.out.println("\n***Nuevo bracket***\n");
			for(int i=0;i<participantes.size();i+=2) {
				Arena prox = arenaLibre();
				while(prox==null){
					try {
						wait();
						prox = arenaLibre();
					} catch (InterruptedException e) { e.printStackTrace(); }
				}
				prox.setEntrenadores(participantes.get(i), participantes.get(i+1));
			}
			/*
			for(Arena r: arenas) {

				System.out.println("\n----Pelea: " + r.getE1().getNombre() + " Vs " + r.getE2().getNombre() + "----");
				try {
					r.combate();
					r.resultado();
					r.recompensas();
					r.removerMuertos();
					historial.add(r.toString());
				} catch (PeleaNulaException e) {
					r.resultado();
					historial.add("-- Ganador por abandono " + r.ganador().getE().getNombre());
					System.out.println(e.getMessage());
				}
				participantes.remove(r.perdedor().getE());
				System.out.println("Gano el entrenador " + r.ganador().getE().getNombre());
			}*/
		}
		System.out.println("\n\n****!!!!! El ganador del torneo es el entrenador "+participantes.get(0).getNombre()+" !!!!!****");
	}
	public ArrayList<Entrenador> getParticipantes() {
		return participantes;
	}

	/**
	 * Este metodo muestra el historial de rondas. Es preferible ejecutar antes el metodo comienzaTorneo(), para que existan algunas rondas.
	 */
	public void historialDeRondas() {
		System.out.println("\n");
		for(String s:historial) {
			System.out.println(s);
		}
	}

	private Arena arenaLibre(){
		Arena encontrada = null;
		for(Arena a : arenas){
			if(a.estaLibre()) {
				encontrada = a;
				break;
			}
		}
		return encontrada;
	}
}
