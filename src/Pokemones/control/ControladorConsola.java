package Pokemones.control;

import com.sun.jmx.remote.internal.ArrayQueue;

public class ControladorConsola {
    private static ControladorConsola instance;

    public static ControladorConsola getInstance(){
        if(instance == null)
            instance = new ControladorConsola();
        return instance;
    }

    public void imprimirPeleas(){

    }
}
