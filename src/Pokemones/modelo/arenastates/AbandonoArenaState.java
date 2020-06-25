package Pokemones.modelo.arenastates;

import Pokemones.modelo.Arena;
import Pokemones.modelo.ArenaState;

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
