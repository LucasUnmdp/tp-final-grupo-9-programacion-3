package Pokemones.modelo;

/**
 * @author Grupo 9 
 * Objeto representativo de una ronda. Consiste en dos equipos
 * formados por un Entrenador y su Pokemon. Estos combaten y se
 * determina un ganador y un perdedor.
 */
public class Arena extends Thread{
	private static int cantArenas = 0;
	private String nombreArena;
	private ArenaState state;

    private Equipo equipo1;
    private Equipo equipo2;

    private Equipo ganador;
    private Equipo perdedor;

    @Override
    public void run(){

	}

	public Arena() {// el entrenador tiene que tener almenos un pokemon
		cantArenas++;
		nombreArena = "Arena "+cantArenas;
		state = new LimpiezaArenaState(this);
	}

	public void setEntrenadores(Entrenador e1, Entrenador e2){
		this.equipo1 = new Equipo(e1, e1.randomPokemon());
		this.equipo2 = new Equipo(e2, e2.randomPokemon());
	}

	public Equipo ganador() {
		return ganador;
	}

	public Equipo perdedor() {
		return perdedor;
	}

	/**
	 * Al iniciar el combate a cada entrenador se le da la opcion de utilizar una
	 * Carta Hechizo contra el otro pokemon. Luego se elige al azar que Pokemon
	 * ataca primero y se realizan los ataques en orden.
	 * 
	 */
	public void combate() throws PeleaNulaException {
		Pokemon p2 = equipo2.getP();
		Pokemon p1 = equipo1.getP();
		if (p2 != null && p1 != null) {
			try {
				equipo1.getE().hechiza(p2);
			} catch (LimiteDeHechizoException e) {
				System.out.println(e.getMessage());
			}
			try {
				equipo2.getE().hechiza(p1);
			} catch (LimiteDeHechizoException e) {
				System.out.println(e.getMessage());
			}
			if (Math.random() >= 0.5) {
				System.out.println(p1.getNombre() + " ataca a " + p2.getNombre());
				p1.ataca(p2);
				System.out.println(p2.getNombre() + " contrataca a " + p1.getNombre());
				p2.ataca(p1);
			} else {
				System.out.println(p2.getNombre() + " ataca a " + p1.getNombre());
				p2.ataca(p1);
				System.out.println(p1.getNombre() + " contrataca a " + p2.getNombre());
				p1.ataca(p2);
			}
		} else {
			throw new PeleaNulaException("Pelea perdida por abandono");
		}
	}

	public Entrenador getE1() {
		return equipo1.getE();
	}

	public Entrenador getE2() {
		return equipo2.getE();
	}

	/**
	 * Al momento de dar las recompensas el Pokemon perdedor se transfiere al
	 * Entrenador ganador. Se le entrega 3 XP al Entrenador ganador y 1 XP al
	 * perdedor. El ganador recibe una Carta Hechizo al azar.
	 */
	public void recompensas() {
		Pokemon p2 = equipo2.getP();
		Pokemon p1 = equipo1.getP();
		Entrenador ganador = this.ganador().getE();
		ganador.addCarta();
		if (ganador == equipo1.getE()) {
			equipo2.getE().removePokemon(p2);
			equipo1.getE().addPokemon(p2);
			// p1.addXp(3);
			// p2.addXp(1);
		} else {
			equipo1.getE().removePokemon(p1);
			equipo2.getE().addPokemon(p1);
			// p2.addXp(3);
			// p1.addXp(1);
		}
	}

	/**
	 * Equipo ganador.
	 * 
	 * @return Equipo ganador en base al puntaje de "calcularPuntaje"
	 */
	public void resultado() {
		double puntaje1 = 0, puntaje2 = 0;
		if (equipo1.getP() == null || equipo2.getP() == null) {
			if (equipo1.getP() == null && equipo2.getP() == null) {
				if (Math.random() >= 0.5) {
					this.ganador = equipo1;
					this.perdedor = equipo2;
				} else {
					this.ganador = equipo2;
					this.perdedor = equipo1;
				}
			} else if (equipo1.getP() == null) {
				this.ganador = equipo2;
				this.perdedor = equipo1;
			} else {
				this.ganador = equipo1;
				this.perdedor = equipo2;
			}
		} else {
			if (equipo1.getP().muerto() ^ equipo2.getP().muerto()) {
				if (equipo1.getP().muerto()) {
					this.ganador = equipo2;
					this.perdedor = equipo1;
				} else {
					this.ganador = equipo1;
					this.perdedor = equipo2;
				}
			} else {
				puntaje1 = calcularPuntaje(equipo1.getP());
				puntaje2 = calcularPuntaje(equipo2.getP());
				if (puntaje1 > puntaje2) {
					this.ganador = equipo1;
					this.perdedor = equipo2;
				} else {
					this.ganador = equipo2;
					this.perdedor = equipo1;
				}
			}
		}
	}

	/**
	 * Mediante mas de 100 millones de partidas jugadas por mas de 350 mil jugadores
	 * online alrededor del mundo analizamos los datos utilizando redes neuronales
	 * convolucionales para obtener unos coeficientes exactos que proveen un
	 * porcentaje de victoria matematicamente perfecto.
	 *
	 * Acto seguido borramos todo y los calculamos de tal manera que las
	 * estadisticas promedio de todos los Pokemones tengan exactamente el mismo peso
	 * en su suma.
	 * 
	 * @param p Pokemon del que se calcula el puntaje
	 * @return Puntaje de este Pokemon en el instante actual
	 */
	private double calcularPuntaje(Pokemon p) {
		return p.vitalidad * 0.183908045977 + p.escudo * 0.695652173913 + p.fuerza + Math.random();
	}

	/**
	 * Devuelve String resumiendo el resultado de la ronda.
	 * 
	 * @return String en formato <i>entrenador (pokemon)</i> Gano contra
	 *         <i>entrenador (pokemon)</i>
	 */
	@Override
	public String toString() {
		return "-- " + this.ganador().getE().getNombre() + " (" + this.ganador().getP().getNombre() + ") Gano contra "
				+ this.perdedor().getE().getNombre() + " (" + this.perdedor().getP().getNombre() + ")";
	}

	public void removerMuertos() {
		Entrenador ganador = this.ganador().getE();
		if (equipo1.getP().muerto()) {
			ganador.removePokemon(equipo1.getP());
		}
		if (equipo2.getP().muerto()) {
			ganador.removePokemon(equipo2.getP());
		}
	}

	public String getNombreArena() {
		return nombreArena;
	}

	public void setNombreArena(String nombreArena) {
		this.nombreArena = nombreArena;
	}

	public ArenaState getArenaState() {
		return state;
	}

	public void setArenaState(ArenaState state) {
		this.state = state;
	}

	public boolean estaLibre(){
		return state.getClass().equals(LimpiezaArenaState.class);
	}
}
