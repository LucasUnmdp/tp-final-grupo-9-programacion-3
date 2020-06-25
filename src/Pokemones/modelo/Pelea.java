package Pokemones.modelo;

import Pokemones.vista.Ventana;
import com.sun.jmx.remote.internal.ArrayQueue;
import sun.awt.image.ImageWatched;

import java.util.LinkedList;

public class Pelea extends Thread {
    private Arena arena;
    private Entrenador en1, en2;
    private LinkedList<String> log = new LinkedList<>();

    private Equipo ganador;
    private Equipo perdedor;

    public Pelea(Arena a){
        arena = a;
    }

    public void setEntrenadores(Entrenador en1, Entrenador en2){
        this.en1 = en1;
        this.en2 = en2;
    }

    @Override
    public void run() {
        arena.setEntrenadores(en1, en2);
        arena.getArenaState().etapa();
        arena.getArenaState().etapa();
    }

    public void log(String linea){
        log.add(linea);
    }

    public void imprimirLog(){
        Ventana vent = Ventana.getInstance();

        while(!log.isEmpty()){
            vent.appendAConsola(log.remove()+"\n");
        }
    }

    public void setGanadorYPerdedor(Equipo g, Equipo p){
        ganador = g;
        perdedor = p;
    }
}
