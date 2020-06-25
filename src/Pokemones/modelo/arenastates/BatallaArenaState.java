package Pokemones.modelo.arenastates;

import Pokemones.modelo.Arena;
import Pokemones.modelo.ArenaState;
import Pokemones.modelo.excepciones.PeleaNulaException;

public class BatallaArenaState implements ArenaState {
    private Arena arena;

    public BatallaArenaState(Arena a){
        arena = a;
    }

    @Override
    public void etapa() {
        //Se batalla (verbo)
        try {
            arena.combate();
            arena.setArenaState(new DefinicionArenaState(arena));
            //historial.add(r.toString());
        } catch (PeleaNulaException e) {
            arena.setArenaState(new AbandonoArenaState(arena));
        }
    }
}
