package Pokemones.modelo.arenastates;

import Pokemones.modelo.Arena;
import Pokemones.modelo.ArenaState;
import Pokemones.vista.Ventana;
/**
 * @author Grupo 9
 *<br>
 *Clase que representa un estado de la arena. En este caso la arena se esta limpiando para el proximo combate.
 */
public class LimpiezaArenaState implements ArenaState {
    private Arena arena;

    public LimpiezaArenaState(Arena a){
        arena = a;
    }

    @Override
    public void etapa() {
        arena.getPeleaActual().log("---" + arena.ganador().getE().getNombre() + " Gano contra "
                + arena.perdedor().getE().getNombre() + "---");
        arena.getPeleaActual().log("La arena \"" + arena.getNombreArena() + "\" se esta limpiando para la siguiente batalla...");
        arena.setArenaState(new PreliminarArenaState(arena));
    }
}
