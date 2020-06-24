package Pokemones.control;

import Pokemones.modelo.*;
import Pokemones.modelo.excepciones.YaExisteEntrenadorException;
import Pokemones.vista.Ventana;
import sun.awt.OverrideNativeWindowHandle;

import javax.naming.ldap.Control;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Controlador implements ActionListener, ListSelectionListener {
    private static Controlador instance;
    //private Ventana ventana;

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        if(e.getActionCommand().equalsIgnoreCase("CREAR ENTRENADOR")){
            agregarEntrenador();
        }else if(e.getActionCommand().equalsIgnoreCase("ELIMINAR ENTRENADOR")){
            eliminarEntrenador();
        }else if(e.getActionCommand().equalsIgnoreCase("CREAR POKEMON")){
            crearPokemon();
        }else if(e.getActionCommand().equalsIgnoreCase("ELIMINAR POKEMON")){
            eliminarPokemon();
        }else if(e.getActionCommand().equalsIgnoreCase("AGREGAR TORNEO")){
            agregarTorneo();
        }else if(e.getActionCommand().equalsIgnoreCase("ELIMINAR TORNEO")){
            eliminarTorneo();
        }else if(e.getActionCommand().equalsIgnoreCase("CAMBIADO TIPO POKEMON")){
            if(Ventana.getInstance().getTipoPokemon().equalsIgnoreCase("Hielo")){
                Ventana.getInstance().getCBGranRecarga().setEnabled(true);
            }else{
                Ventana.getInstance().getCBGranRecarga().setEnabled(false);
            }
        }

    }

    private void agregarTorneo() {
        Ventana vent = Ventana.getInstance();
        Entrenador ent = vent.getEntrenadorSeleccionado();
        if(ent == null){
            vent.appendAConsola("Primero seleccione un entrenador.");
        }else{
            try {
                Torneo.getInstance().addEntrenador(ent);
            } catch (YaExisteEntrenadorException e) {
                vent.appendAConsola("El entrenador ya esta en el torneo.");
            }
        }
        vent.actualizarTorneo(Torneo.getInstance().getParticipantes());
    }

    private void eliminarTorneo() {
        Ventana vent = Ventana.getInstance();
        Entrenador ent = vent.getTorneoSeleccionado();

        if(ent == null){
            vent.appendAConsola("Primero seleccione un entrenador del torneo.");
        }else{
            Torneo.getInstance().removeEntrenador(ent);
        }
        vent.actualizarTorneo(Torneo.getInstance().getParticipantes());
    }

    private void agregarEntrenador(){
        Ventana ventana = Ventana.getInstance();
        if(ventana.getNombreEntrenador().isEmpty())
            ventana.appendAConsola("Se necesita un nombre de entrenador.");
        else {
            String nombre = ventana.getNombreEntrenador();
            Entrenador nuevoEnt = new Entrenador(nombre);
            try {
                Lobby.getInstance().agregarEntrenador(nuevoEnt);
                ventana.appendAConsola("Se creó el entrenador: \""+nombre+"\"");
            } catch (YaExisteEntrenadorException e) {
                ventana.appendAConsola("Ya existe ese entrenador.");
            }
        }
    }

    private void eliminarEntrenador(){
        Ventana ventana = Ventana.getInstance();
        Entrenador selected = ventana.getEntrenadorSeleccionado();

        if(selected!=null){
            Lobby.getInstance().eliminarEntrenador(selected);
            Torneo.getInstance().removeEntrenador(selected);
            ventana.appendAConsola("Se eliminó el entrenador: "+selected.toString());
        }
    }

    private void crearPokemon(){
        Ventana vent = Ventana.getInstance();
        Entrenador ent = vent.getEntrenadorSeleccionado();
        if(ent == null){
            vent.appendAConsola("Primero seleccione un entrenador.");
        }else if(vent.getNombrePokemon().isEmpty()){
            vent.appendAConsola("Se necesita un nombre para el Pokémon.");
        }else{
            String nombre = vent.getNombrePokemon();
            String tipo = vent.getTipoPokemon();
            boolean granRecarga = vent.getGranRecarga();
            PokemonFactory pf = new PokemonFactory();
            Pokemon p = pf.getPokemon(tipo, nombre, granRecarga);
            ent.addPokemon(p);
            vent.actualizarPokemones(ent.getPokemones());
        }
    }

    private void eliminarPokemon(){
        Ventana vent = Ventana.getInstance();
        Pokemon pokeSelected = vent.getPokemonrSeleccionado();
        Entrenador entSelected = vent.getEntrenadorSeleccionado();



        if(entSelected != null) {
            if (pokeSelected != null) {
                entSelected.eliminarPokemon(pokeSelected);
            }
            vent.actualizarPokemones(entSelected.getPokemones());
        }
    }

    public static Controlador getInstance(){
        if(instance == null)
            instance = new Controlador();
        return instance;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        Entrenador ent = Ventana.getInstance().getEntrenadorSeleccionado();
        if(ent!=null) {
            Ventana.getInstance().actualizarPokemones(ent.getPokemones());
        }else{
            Ventana.getInstance().actualizarPokemones(new ArrayList<>());
        }
    }
}
