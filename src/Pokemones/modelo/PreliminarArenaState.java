package Pokemones.modelo;

public class PreliminarArenaState implements ArenaState {
    private Arena arena;

    public PreliminarArenaState(Arena a){
        arena = a;
    }

    @Override
    public void etapa() {
        //Imprime los entrenadores que van a pelear
        arena.setArenaState(new BatallaArenaState(arena));
    }
}
