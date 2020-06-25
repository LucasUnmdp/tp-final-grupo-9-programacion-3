package Pokemones.modelo.arenastates;

import Pokemones.modelo.Arena;
import Pokemones.modelo.ArenaState;

public class CerradoArenaState implements ArenaState {
    private Arena arena;

    public CerradoArenaState(Arena a){
        arena = a;
    }

    @Override
    public void etapa() {
        System.out.println("La arena esta cerrada. >:(");
    }
}
