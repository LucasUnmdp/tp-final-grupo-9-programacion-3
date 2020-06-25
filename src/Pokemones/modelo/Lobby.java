package Pokemones.modelo;

import Pokemones.control.ControladorGeneral;
import Pokemones.modelo.excepciones.YaExisteEntrenadorException;
import Pokemones.vista.Ventana;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;

public class Lobby {
    private static Lobby instance;
    private Ventana ventana;
    private ArrayList<Entrenador> listaEntrenadores;
    public static String PATH_TORNEO_SERIALIZACION = "Torneo.xml";
    public static String PATH_ENTRENADORES_SERIALIZACION = "Entrenadores.xml";

	public static void main(String[] args) {
	    Lobby.getInstance();
	}

	private Lobby(){
        Ventana.getInstance().inicializar();
        Ventana.getInstance().crearVentana();
	    ControladorGeneral.getInstance();
        Torneo.getInstance().inicializar();
        ventana = Ventana.getInstance();
        ventana.setVisible(true);
        listaEntrenadores = new ArrayList<>();
        leerEntrenadores();
    }

    public void agregarEntrenador(Entrenador e) throws YaExisteEntrenadorException {
        for(Entrenador ent : listaEntrenadores){
            if(e.getNombre().equalsIgnoreCase(ent.getNombre())){
                throw new YaExisteEntrenadorException();
            }
        }
	    listaEntrenadores.add(e);
	    ventana.actualizarEntrenadores(listaEntrenadores);
	    guardarEntrenadores();
    }

    public void eliminarEntrenador(Entrenador e){
	    listaEntrenadores.remove(e);
	    ventana.actualizarEntrenadores(listaEntrenadores);
	    guardarEntrenadores();
    }

    public static Lobby getInstance(){
	    if(instance == null)
	        instance = new Lobby();
	    return instance;
    }

    public void guardarEntrenadores(){
        XMLEncoder encoder = null;
        try {
            encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(PATH_ENTRENADORES_SERIALIZACION)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        encoder.writeObject(listaEntrenadores);
        encoder.close();
    }

    public void leerEntrenadores(){
        XMLDecoder decoder = null;
        try{
            decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(PATH_ENTRENADORES_SERIALIZACION)));
            System.out.println("Se encontro el archivo de entrenadores, leyendo...");
            listaEntrenadores = (ArrayList<Entrenador>) decoder.readObject();
            decoder.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        Ventana.getInstance().actualizarEntrenadores(listaEntrenadores);
    }
}
