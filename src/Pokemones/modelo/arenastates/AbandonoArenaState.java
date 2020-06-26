package Pokemones.modelo.arenastates;

import Pokemones.modelo.Arena;
import Pokemones.modelo.ArenaState;
/**
 * @author Grupo 9
 *<br>
 *Clase que representa un estado de la arena. En este caso un jugador perdió por abandono.
 */
public class AbandonoArenaState implements ArenaState {
    private Arena arena;

    public AbandonoArenaState(Arena a){
        arena = a;
    }

    @Override
    public void etapa() {
        arena.resultado();
        arena.setArenaState(new LimpiezaArenaState(arena));
    }
}
