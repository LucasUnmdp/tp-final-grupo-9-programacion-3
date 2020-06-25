package Pokemones.vista;

import Pokemones.control.ControladorGeneral;
import Pokemones.control.ControladorItem;
import Pokemones.modelo.Entrenador;
import Pokemones.modelo.Pokemon;

import java.awt.*;

import javax.naming.ldap.Control;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.UIManager;
import java.util.ArrayList;
import javax.swing.JTextArea;

public class Ventana extends JFrame {
	private static Ventana instance;
	private ControladorGeneral controladorGeneral;
	private JPanel contentPane;
	private JTextField TNombreEntrenador;
	private JTextField TNombrePokemon;
	private JTextArea taConsola;
	private JList listaDeEntrenadores;
	private JList listaDePokemones;
	private JList listaDeTorneo;
	private JCheckBox CBGranRecarga;
	private JComboBox ComboBoxTipo;
	private JComboBox CBParticipantes;

	public static Ventana getInstance(){
		if(instance == null)
			instance = new Ventana();
		return instance;
	}

	public void inicializar(){
		controladorGeneral = ControladorGeneral.getInstance();
	}

	public void crearVentana(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 811, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 4, 0, 0));

		JPanel PEntrenadores = new JPanel();
		contentPane.add(PEntrenadores);
		PEntrenadores.setLayout(new BorderLayout(0, 0));

		JPanel ConsolaEntrenadores = new JPanel();
		ConsolaEntrenadores.setBorder(new TitledBorder(null, "Lista de Entrenadores", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		PEntrenadores.add(ConsolaEntrenadores, BorderLayout.CENTER);
		ConsolaEntrenadores.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		ConsolaEntrenadores.add(scrollPane, BorderLayout.CENTER);

		listaDeEntrenadores = new JList();
		listaDeEntrenadores.addListSelectionListener(controladorGeneral);
		scrollPane.setViewportView(listaDeEntrenadores);

		JPanel CreacionEntrenadores = new JPanel();
		CreacionEntrenadores.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Crear Entrenador", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		PEntrenadores.add(CreacionEntrenadores, BorderLayout.SOUTH);
		CreacionEntrenadores.setPreferredSize(new Dimension(150,150));
		CreacionEntrenadores.setLayout(new GridLayout(4, 0, 0, 0));

		JPanel panel = new JPanel();
		CreacionEntrenadores.add(panel);

		JLabel LNombre = new JLabel("Nombre del Entrenador");
		panel.add(LNombre);

		JPanel panel_1 = new JPanel();
		CreacionEntrenadores.add(panel_1);

		TNombreEntrenador = new JTextField();
		TNombreEntrenador.setActionCommand("CREAR ENTRENADOR");
		TNombreEntrenador.addActionListener(controladorGeneral);
		panel_1.add(TNombreEntrenador);
		TNombreEntrenador.setColumns(10);

		JPanel panel_2 = new JPanel();
		CreacionEntrenadores.add(panel_2);

		JButton BCrearEntrenador = new JButton("Crear");
		BCrearEntrenador.setActionCommand("CREAR ENTRENADOR");
		BCrearEntrenador.addActionListener(controladorGeneral);
		panel_2.add(BCrearEntrenador);

		JPanel panel_12 = new JPanel();
		CreacionEntrenadores.add(panel_12);

		JButton BEliminarEntrenadores = new JButton("Eliminar");
		BEliminarEntrenadores.setActionCommand("ELIMINAR ENTRENADOR");
		BEliminarEntrenadores.addActionListener(controladorGeneral);
		panel_12.add(BEliminarEntrenadores);

		JPanel PPokemones = new JPanel();
		contentPane.add(PPokemones);
		PPokemones.setLayout(new BorderLayout(0, 0));

		JPanel ConsolaPokemones = new JPanel();
		ConsolaPokemones.setBorder(new TitledBorder(null, "Lista de Pokemones", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		PPokemones.add(ConsolaPokemones, BorderLayout.CENTER);
		ConsolaPokemones.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_1 = new JScrollPane();
		ConsolaPokemones.add(scrollPane_1, BorderLayout.CENTER);

		listaDePokemones = new JList();
		//listaDePokemones.addListSelectionListener(controladorGeneral);
		scrollPane_1.setViewportView(listaDePokemones);

		JPanel CreacionPokemones = new JPanel();
		CreacionPokemones.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Crear Pokemon", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		PPokemones.add(CreacionPokemones, BorderLayout.SOUTH);
		CreacionPokemones.setPreferredSize(new Dimension(150,150));
		CreacionPokemones.setLayout(new GridLayout(4, 2, 0, 0));

		JPanel panel_5 = new JPanel();
		CreacionPokemones.add(panel_5);

		JLabel LabelNombrePokemon = new JLabel("Nombre");
		panel_5.add(LabelNombrePokemon);

		JPanel panel_4 = new JPanel();
		CreacionPokemones.add(panel_4);

		TNombrePokemon = new JTextField();
		panel_4.add(TNombrePokemon);
		TNombrePokemon.setColumns(10);

		JPanel panel_6 = new JPanel();
		CreacionPokemones.add(panel_6);

		JLabel LTipoPokemon = new JLabel("Tipo");
		panel_6.add(LTipoPokemon);

		JPanel panel_3 = new JPanel();
		CreacionPokemones.add(panel_3);

		ComboBoxTipo = new JComboBox();
		panel_3.add(ComboBoxTipo);
		ComboBoxTipo.setModel(new DefaultComboBoxModel(new String[] {"Agua", "Fuego", "Tierra", "Hielo", "Fantasma", "Planta"}));
		ComboBoxTipo.setActionCommand("CAMBIADO TIPO POKEMON");
		ComboBoxTipo.addActionListener(controladorGeneral);
		ComboBoxTipo.setMaximumRowCount(6);

		JPanel panel_7 = new JPanel();
		CreacionPokemones.add(panel_7);

		CBGranRecarga = new JCheckBox("Gran Recarga");
		CBGranRecarga.setEnabled(false);
		CBGranRecarga.setActionCommand("CAMBIO GRAN RECARGA");
		CBGranRecarga.addActionListener(controladorGeneral);
		panel_7.add(CBGranRecarga);

		JPanel panel_8 = new JPanel();
		CreacionPokemones.add(panel_8);

		JButton BCrearPokemon = new JButton("Entrenar");
		BCrearPokemon.setActionCommand("CREAR POKEMON");
		BCrearPokemon.addActionListener(controladorGeneral);
		panel_8.add(BCrearPokemon);

		JLabel lblNewLabel = new JLabel("");
		CreacionPokemones.add(lblNewLabel);

		JPanel panel_13 = new JPanel();
		CreacionPokemones.add(panel_13);

		JButton BEliminarPokemon = new JButton("Eliminar");
		BEliminarPokemon.setActionCommand("ELIMINAR POKEMON");
		BEliminarPokemon.addActionListener(controladorGeneral);
		panel_13.add(BEliminarPokemon);
		JPanel PTorneo = new JPanel();
		contentPane.add(PTorneo);
		PTorneo.setLayout(new BorderLayout(0, 0));

		JPanel ConsolaTorneo = new JPanel();
		ConsolaTorneo.setBorder(new TitledBorder(null, "Torneo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		PTorneo.add(ConsolaTorneo, BorderLayout.CENTER);
		ConsolaTorneo.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_2 = new JScrollPane();
		ConsolaTorneo.add(scrollPane_2, BorderLayout.CENTER);

		listaDeTorneo = new JList();
		listaDeTorneo.addListSelectionListener(controladorGeneral);
		scrollPane_2.setViewportView(listaDeTorneo);

		JPanel CreaciondeTorneo = new JPanel();
		CreaciondeTorneo.setBorder(new TitledBorder(null, "Crear Torneo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		PTorneo.add(CreaciondeTorneo, BorderLayout.SOUTH);
		CreaciondeTorneo.setPreferredSize(new Dimension(150,150));
		CreaciondeTorneo.setLayout(new GridLayout(3, 2, 0, 0));

		JPanel panel_15 = new JPanel();
		CreaciondeTorneo.add(panel_15);

		JLabel labelParticipantes = new JLabel("Cantidad de Participantes:");
		panel_15.add(labelParticipantes);

		JPanel panel_14 = new JPanel();
		CreaciondeTorneo.add(panel_14);

		CBParticipantes = new JComboBox();

		CBParticipantes.addItemListener(ControladorItem.getInstance());
		panel_14.add(CBParticipantes);
		CBParticipantes.setModel(new DefaultComboBoxModel(new String[] {"2", "4", "8", "16", "32", "64"}));

		JPanel panel_9 = new JPanel();
		CreaciondeTorneo.add(panel_9);

		JButton BAgregarTorneo = new JButton("Agregar");
		BAgregarTorneo.setActionCommand("AGREGAR TORNEO");
		BAgregarTorneo.addActionListener(controladorGeneral);
		panel_9.add(BAgregarTorneo);

		JPanel panel_10 = new JPanel();
		CreaciondeTorneo.add(panel_10);

		JButton BEliminarTorneo = new JButton("Eliminar");
		BEliminarTorneo.setActionCommand("ELIMINAR TORNEO");
		BEliminarTorneo.addActionListener(controladorGeneral);
		panel_10.add(BEliminarTorneo);

		JLabel lblNewLabel_1 = new JLabel("");
		CreaciondeTorneo.add(lblNewLabel_1);

		JPanel panel_11 = new JPanel();
		CreaciondeTorneo.add(panel_11);

		JButton BComenzarTorneo = new JButton("Comenzar");
		BComenzarTorneo.setActionCommand("COMENZAR TORNEO");
		BComenzarTorneo.addActionListener(controladorGeneral);
		panel_11.add(BComenzarTorneo);
		JPanel PConsola = new JPanel();
		PConsola.setBorder(new TitledBorder(null, "Consola", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(PConsola);
		PConsola.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_3 = new JScrollPane();
		PConsola.add(scrollPane_3, BorderLayout.CENTER);

		taConsola = new JTextArea();
		taConsola.setEditable(false);
		scrollPane_3.setViewportView(taConsola);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

	public void appendAConsola(String linea){
		taConsola.append(linea+"\n");
		taConsola.updateUI();
	}

	public void actualizarEntrenadores(ArrayList<Entrenador> lista){
		listaDeEntrenadores.setListData(lista.toArray());
	}

	public void actualizarPokemones(ArrayList<Pokemon> lista){
		listaDePokemones.setListData(lista.toArray());
	}

	public void actualizarTorneo(ArrayList<Entrenador> lista){
		listaDeTorneo.setListData(lista.toArray());
	}

	public String getNombreEntrenador(){
		return TNombreEntrenador.getText();
	}

	public String getNombrePokemon(){
		return TNombrePokemon.getText();
	}

	public boolean getGranRecarga(){
		return CBGranRecarga.isSelected();
	}

	public JCheckBox getCBGranRecarga(){
		return CBGranRecarga;
	}

	public Entrenador getEntrenadorSeleccionado(){
		return (Entrenador) listaDeEntrenadores.getSelectedValue();
	}

	public Pokemon getPokemonrSeleccionado(){
		return (Pokemon) listaDePokemones.getSelectedValue();
	}

	public Entrenador getTorneoSeleccionado(){
		return (Entrenador) listaDeTorneo.getSelectedValue();
	}

	public String getTipoPokemon(){
		return (String) ComboBoxTipo.getSelectedItem();
	}

	public int getCantParticipantes(){
		assert CBParticipantes.getSelectedItem() != null;
		return Integer.parseInt((String) CBParticipantes.getSelectedItem());
	}

	public void setCantParticipantes(int num, boolean esInicializacion){
		if(esInicializacion){
			CBParticipantes.removeItemListener(ControladorItem.getInstance());
			CBParticipantes.setSelectedItem(String.valueOf(num));
			CBParticipantes.removeItemListener(ControladorItem.getInstance());
		}else{
			CBParticipantes.setSelectedItem(String.valueOf(num));
		}
	}
}
