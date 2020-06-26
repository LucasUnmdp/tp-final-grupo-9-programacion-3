package Pokemones.modelo.arenastates;

import Pokemones.modelo.Arena;
import Pokemones.modelo.ArenaState;
/**
 * @author Grupo 9
 *<br>
 *Clase que representa un estado de la arena. En este caso la arena se esta definiendo los resultados.
 */
public class DefinicionArenaState implements ArenaState {
    private Arena arena;

    public DefinicionArenaState(Arena a){
        arena = a;
    }

    @Override
    public void etapa() {
        //Se define xd
        arena.resultado();
        arena.recompensas();
        arena.removerMuertos();
        arena.setArenaState(new LimpiezaArenaState(arena));
    }
}
