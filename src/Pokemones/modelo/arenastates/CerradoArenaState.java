package Pokemones.modelo.arenastates;

import Pokemones.modelo.Arena;
import Pokemones.modelo.ArenaState;
/**
 * @author Grupo 9
 *<br>
 *Clase que representa un estado de la arena. En este caso la arena esta cerrada.
 */
public class CerradoArenaState implements ArenaState {
    private Arena arena;

    public CerradoArenaState(Arena a){
        arena = a;
    }

    @Override
    public void etapa() {
        System.out.println("La arena esta cerrada.");
    }
}
