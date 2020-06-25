package Pokemones.modelo.arenastates;

import Pokemones.modelo.Arena;
import Pokemones.modelo.ArenaState;

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
