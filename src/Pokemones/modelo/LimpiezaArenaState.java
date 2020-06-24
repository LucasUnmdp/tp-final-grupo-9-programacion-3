package Pokemones.modelo;

public class LimpiezaArenaState implements ArenaState {
    private Arena arena;

    public LimpiezaArenaState(Arena a){
        arena = a;
    }

    @Override
    public void etapa() {
        System.out.println("La arena \""+arena.getNombreArena()+"\" se esta limpiando para la siguiente batalla...");
    }
}
