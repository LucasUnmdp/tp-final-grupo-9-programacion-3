package Pokemones.modelo;

public class BatallaArenaState implements ArenaState {
    private Arena arena;

    public BatallaArenaState(Arena a){
        arena = a;
    }

    @Override
    public void etapa() {
        //Se batalla (verbo)

        arena.setArenaState(new DefinicionArenaState(arena));
    }
}
