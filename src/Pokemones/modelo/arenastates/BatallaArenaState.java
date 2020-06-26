package Pokemones.modelo.arenastates;

import Pokemones.modelo.Arena;
import Pokemones.modelo.ArenaState;
import Pokemones.modelo.excepciones.PeleaNulaException;
/**
 * @author Grupo 9
 *<br>
 *Clase que representa un estado de la arena. En este caso se esta batallando en la arena.
 */
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
