package Pokemones.modelo.arenastates;

import Pokemones.modelo.Arena;
import Pokemones.modelo.ArenaState;

public class LimpiezaArenaState implements ArenaState {
    private Arena arena;

    public LimpiezaArenaState(Arena a){
        arena = a;
    }

    @Override
    public void etapa() {
        arena.getPeleaActual().log("-- " + arena.ganador().getE().getNombre() + " (" + arena.ganador().getP().getNombre() + ") Gano contra "
                + arena.perdedor().getE().getNombre() + " (" + arena.perdedor().getP().getNombre() + ")");
        //System.out.println("La arena \""+arena.getNombreArena()+"\" se esta limpiando para la siguiente batalla...");
        arena.setArenaState(new PreliminarArenaState(arena));
        notifyAll();
    }
}
