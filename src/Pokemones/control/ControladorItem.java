package Pokemones.control;

import Pokemones.modelo.Torneo;
import Pokemones.vista.Ventana;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
/**
 * @author Grupo 9
 *<br>
 *Clase controlador que se encarga de mantener la cantidad de jugadores en al ventana igual al del modelo.
 */
public class ControladorItem implements ActionListener, ItemListener {
    private static ControladorItem instance;
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static ControladorItem getInstance(){
        if(instance == null)
            instance = new ControladorItem();
        return instance;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        Torneo.getInstance().setCantidadDeEntrenadores(Ventana.getInstance().getCantParticipantes());
        System.out.println(Torneo.getInstance().getCantidadDeEntrenadores());
    }
}
