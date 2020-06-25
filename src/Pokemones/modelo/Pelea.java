package Pokemones.modelo;

import Pokemones.vista.Ventana;
import com.sun.jmx.remote.internal.ArrayQueue;
import sun.awt.image.ImageWatched;

import java.util.LinkedList;
import java.util.Random;

public class Pelea extends Thread {
    private Arena arena;
    private Entrenador en1, en2;
    private LinkedList<String> log = new LinkedList<>();

    private Equipo ganador;
    private Equipo perdedor;

    private boolean enProgreso = false;

    public Pelea(Arena a){
        arena = a;
        arena.setPeleaActual(this);
    }

    public void setEntrenadores(Entrenador en1, Entrenador en2){
        this.en1 = en1;
        this.en2 = en2;
    }

    @Override
    public void run() {
        synchronized (arena) {
            realizarPelea();
        }
    }

    private synchronized void realizarPelea(){
        try{
            Thread.sleep(500 + new Random().nextInt(1000)); //Para simular el tiempo de la pelea
        }catch(Exception ex) {}
        arena.setEntrenadores(en1, en2);
        arena.getArenaState().etapa(); //Presenta los entrenadores
        arena.getArenaState().etapa(); //BATALLA
        arena.getArenaState().etapa(); //Post-batalla
        ganador = arena.ganador();
        perdedor = arena.perdedor();
        arena.getArenaState().etapa();
        arena.setLibre(true);
        enProgreso = false;
        notifyAll();
    }

    public void log(String linea){
        log.add(linea);
    }

    public synchronized void imprimirLog(){
        while(enProgreso){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Ventana vent = Ventana.getInstance();
        System.out.println("IMPRIMIENDO LOG");
        while(log.size()>0){
            vent.appendAConsola(log.remove()+"\n");
        }
    }

    public void setGanadorYPerdedor(Equipo g, Equipo p){
        ganador = g;
        perdedor = p;
    }

    public boolean isEnProgreso() {
        return enProgreso;
    }

    public void setEnProgreso(boolean enProgreso) {
        this.enProgreso = enProgreso;
    }

    public Equipo getGanador() {
        return ganador;
    }

    public void setGanador(Equipo ganador) {
        this.ganador = ganador;
    }

    public Equipo getPerdedor() {
        return perdedor;
    }

    public void setPerdedor(Equipo perdedor) {
        this.perdedor = perdedor;
    }
}
