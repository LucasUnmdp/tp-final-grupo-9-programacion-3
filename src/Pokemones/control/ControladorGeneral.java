package Pokemones.control;

import Pokemones.modelo.*;
import Pokemones.modelo.excepciones.TorneoParticipantesException;
import Pokemones.modelo.excepciones.YaExisteEntrenadorException;
import Pokemones.vista.Ventana;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
/**
 * @author Grupo 9
 *<br>
 *Clase controlador que se encarga de escuchar todos los ActionCommand, de verificar la informacion
 * introducida por el usuario, utilizar el modelo y actualizar la ventana.
 */
public class ControladorGeneral implements ActionListener, ListSelectionListener, KeyListener {
    private static ControladorGeneral instance;
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
        }else if(e.getActionCommand().equalsIgnoreCase("COMENZAR TORNEO")){
            new Thread(() -> {
                try {
                    Torneo.getInstance().siguienteEtapa();
                } catch (TorneoParticipantesException ex) {
                    Ventana.getInstance().appendAConsola("El torneo debe tener exactamente "+Torneo.getInstance().getCantidadDeEntrenadores()+" participantes.");
                }
            }).start();
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

    public static ControladorGeneral getInstance(){
        if(instance == null)
            instance = new ControladorGeneral();
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

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getExtendedKeyCode()==10){
            agregarTorneo();
        }
    }
}
