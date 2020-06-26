package Pokemones.modelo.arenastates;

import Pokemones.modelo.Arena;
import Pokemones.modelo.ArenaState;
import Pokemones.modelo.arenastates.BatallaArenaState;
/**
 * @author Grupo 9
 *<br>
 *Clase que representa un estado de la arena. En este caso la arena esta presentando a los Entrenadores que van a pelear.
 */
public class PreliminarArenaState implements ArenaState {
    private Arena arena;

    public PreliminarArenaState(Arena a){
        arena = a;
    }

    @Override
    public void etapa() {
        arena.getPeleaActual().log("----Pelea: " + arena.getE1().getNombre() + " Vs " + arena.getE2().getNombre() + "----");
        arena.setArenaState(new BatallaArenaState(arena));
    }
}
