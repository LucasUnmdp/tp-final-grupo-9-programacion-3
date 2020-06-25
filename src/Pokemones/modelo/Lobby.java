package Pokemones.modelo;

import Pokemones.control.ControladorGeneral;
import Pokemones.modelo.excepciones.YaExisteEntrenadorException;
import Pokemones.vista.Ventana;

import java.util.ArrayList;

public class Lobby {
    private static Lobby instance;
    private Ventana ventana;
    private ArrayList<Entrenador> listaEntrenadores;

	public static void main(String[] args) {
	    Lobby.getInstance();

		/*
		Torneo t= Torneo.getInstance();
		Entrenador e1=new Entrenador("Ash");
		e1.addPokemon(new PokemonAgua("Squirtle"));
		e1.addPokemon(new PokemonPlanta("Bulbasaur"));
		t.addEntrenador(e1);
		e1=new Entrenador("Misty");
		e1.addPokemon(new PokemonFantasma("Gastly"));
		e1.addPokemon(new PokemonFuego("Charizard"));
		t.addEntrenador(e1);
		e1=new Entrenador("Brook");
		e1.addPokemon(new PokemonAgua("Wartortle"));
		e1.addPokemon(new PokemonFuego("Charmander"));
		t.addEntrenador(e1);
		e1=new Entrenador("Nivea");
		e1.addPokemon(new PokemonHielo("Articuno",true));
		e1.addPokemon(new PokemonPlanta("Chikorita"));
		t.addEntrenador(e1);
		t.setCantidadDeEntrenadores(4);
		t.comienzaTorneo();
		t.historialDeRondas();
		*/
	}

	private Lobby(){
	    ControladorGeneral.getInstance();
	    Ventana.getInstance().inicializar();
	    Torneo.getInstance().inicializar();
        ventana = Ventana.getInstance();
        ventana.setVisible(true);
        listaEntrenadores = new ArrayList<>();
    }

    public void agregarEntrenador(Entrenador e) throws YaExisteEntrenadorException {
        for(Entrenador ent : listaEntrenadores){
            if(e.getNombre().equalsIgnoreCase(ent.getNombre())){
                throw new YaExisteEntrenadorException();
            }
        }

	    listaEntrenadores.add(e);
	    ventana.actualizarEntrenadores(listaEntrenadores);
    }

    public void eliminarEntrenador(Entrenador e){
	    listaEntrenadores.remove(e);
	    ventana.actualizarEntrenadores(listaEntrenadores);
    }

    public static Lobby getInstance(){
	    if(instance == null)
	        instance = new Lobby();
	    return instance;
    }
}
