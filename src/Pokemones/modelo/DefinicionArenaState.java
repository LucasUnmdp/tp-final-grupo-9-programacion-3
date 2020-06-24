package Pokemones.modelo;

public class DefinicionArenaState implements ArenaState {
    private Arena arena;

    public DefinicionArenaState(Arena a){
        arena = a;
    }

    @Override
    public void etapa() {
        //Se define xd

        arena.setArenaState(new LimpiezaArenaState(arena));
        notifyAll();
    }
}
