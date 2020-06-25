package Pokemones.modelo.arenastates;

import Pokemones.modelo.Arena;
import Pokemones.modelo.ArenaState;
import Pokemones.modelo.arenastates.BatallaArenaState;

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
