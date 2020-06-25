package Pokemones.control;

import Pokemones.modelo.Torneo;
import Pokemones.vista.Ventana;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

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
    }
}
