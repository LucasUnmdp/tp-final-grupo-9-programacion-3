package Pokemones.modelo;

import Pokemones.modelo.arenastates.CerradoArenaState;
import Pokemones.modelo.excepciones.TorneoParticipantesException;
import Pokemones.modelo.excepciones.YaExisteEntrenadorException;
import Pokemones.vista.Ventana;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author Grupo9
 * Torneo <br>
 * esta clase simula un torneo, tiene entrenadores que se enfrentaran en batallas, tambien guarda un historial de esos enfrentamientos.
 */
public class Torneo implements Serializable {
    private static Torneo instance = null;
    private int cantidadDeEntrenadores;
    private ArrayList<Entrenador> participantes = new ArrayList<Entrenador>();
    public static final int cantidadDeHechizosMax = 4;
    private ArrayList<Arena> arenas = new ArrayList<>();
    private boolean enPreparacion = true;

    private Torneo() {
    }

    public void inicializar() {
        setCantidadDeEntrenadores(8);
        crearArenas();
    }

    /**
     * La clase torneo responde al patron singleton.
     */
    public static Torneo getInstance() {
        if (instance == null)
            instance = new Torneo();
        return Torneo.instance;
    }

    private void crearArenas() {
        for (int i = 0; i < 4; i++)
            arenas.add(new Arena());
        Arena aCerrada = new Arena();
        aCerrada.setArenaState(new CerradoArenaState(aCerrada));
        arenas.add(aCerrada);
    }


    /**
     * Este metodo agrega entrenador a la lista de entrenadores del torneo.
     * <b>Pre: </b>el pametro entrenador debe ser distinto de null<br>
     * <b>Post: </b>el entrenador es agregado al torneo si hay lugar<br>
     *
     * @param entrenador es el entrenador que se agrega
     */
    public void addEntrenador(Entrenador entrenador) throws YaExisteEntrenadorException {
        if (participantes.contains(entrenador)) {
            throw new YaExisteEntrenadorException();
        }

        if (participantes.size() < this.cantidadDeEntrenadores)
            participantes.add(entrenador);
    }

    public int getCantidadDeEntrenadores() {
        return cantidadDeEntrenadores;
    }

    /**
     * <b>Pre: </b>La cantidad de entrenadores debera ser pontencia de 2, osea 2,4,8,16...<br>
     * <b>Post: </b>Es establecida la cantidad de entrenadores que tendra el torneo<br>
     *
     * @param cantidadDeEntrenadores sera la cantidad de jugadores que admitira el torneo
     */
    public void setCantidadDeEntrenadores(int cantidadDeEntrenadores) {
        this.cantidadDeEntrenadores = cantidadDeEntrenadores;
        Ventana.getInstance().setCantParticipantes(cantidadDeEntrenadores, true);
    }

    /**
     * <b>Pre: </b>El entrenador e debe pertener a la lista y distinto de null<br>
     * <b>Post: </b>el entrenador sera removio de la lista de entrenadores del toreno<br>
     *
     * @param e es un entrenador que sera removido
     */
    public void removeEntrenador(Entrenador e) {
        participantes.remove(e);
        Ventana.getInstance().actualizarTorneo(participantes);
    }


    public void siguienteEtapa() throws TorneoParticipantesException {
        if (enPreparacion) {
            if (participantes.size() != cantidadDeEntrenadores)
                throw new TorneoParticipantesException();
            else {
                enPreparacion = false;
                bracketTorneo();
            }
        } else {
            bracketTorneo();
        }
    }

    /**
     * Este metodo realiza todos los cruces del torneo, octavos de final , cuartos de final, etc. Dependiendo de la cantidadDeEntrenadores. Realiza todos los enfrentamientos y guarda en el historial los resultados.
     * <b>Pre: </b>la cantidad de entrenadores en la lista de entrenadores es igual a la cantidadDeEntrenadores establecida<br>
     * <b>Post: </b>Se ejecuta el torneo<br>
     */
    public void bracketTorneo() {
        ArrayList<Pelea> peleas = new ArrayList<>();

        //System.out.println("\n***Nuevo bracket***\n");
        Ventana.getInstance().appendAConsola("Comienza Bracket (Participantes: " + participantes.size() + ")");

        for (int i = 0; i < participantes.size(); i += 2) {


            //VIEJO
			/*Arena prox = arenaLibre();
			while(prox==null){
				try {
					wait();
					prox = arenaLibre();
				} catch (InterruptedException e) { e.printStackTrace(); }
			}*/

            Arena prox = arenaLibre();
            if (prox == null) {
                prox = arenas.get(new Random().nextInt(arenas.size() - 1));
            }
            //prox.setEntrenadores(participantes.get(i), participantes.get(i+1));
            prox.setLibre(false);
            Pelea p = new Pelea(prox);
            p.setEntrenadores(participantes.get(i), participantes.get(i + 1));
            peleas.add(p);
            p.setEnProgreso(true);
            p.start();
        }

        for (Pelea p : peleas) {
            p.imprimirLog();
        }

        for (Pelea p : peleas) {
            participantes.remove(p.getPerdedor().getE());
        }

        if (participantes.size() == 1) {
            Ventana.getInstance().appendAConsola("EL GANADOR DEL TORNEO ES: " + participantes.get(0).getNombre());
            enPreparacion = true;
        }
        Ventana.getInstance().actualizarTorneo(participantes);

    }

    public ArrayList<Entrenador> getParticipantes() {
        return participantes;
    }

    private Arena arenaLibre() {
        Arena encontrada = null;
        for (Arena a : arenas) {
            if (a.estaLibre()) {
                encontrada = a;
                break;
            }
        }
        return encontrada;
    }

    public void setParticipantes(ArrayList<Entrenador> participantes) {
        this.participantes = participantes;
    }

    public ArrayList<Arena> getArenas() {
        return arenas;
    }

    public void setArenas(ArrayList<Arena> arenas) {
        this.arenas = arenas;
    }

    public boolean isEnPreparacion() {
        return enPreparacion;
    }

    public void setEnPreparacion(boolean enPreparacion) {
        this.enPreparacion = enPreparacion;
    }
}
