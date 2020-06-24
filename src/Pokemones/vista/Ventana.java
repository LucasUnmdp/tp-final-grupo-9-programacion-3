package Pokemones.vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
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
import java.awt.Color;
import javax.swing.JTextArea;

public class Ventana extends JFrame {

	private JPanel contentPane;
	private JTextField TNombreEntrenador;
	private JTextField TNombrePokemon;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana frame = new Ventana();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Ventana() {
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
		
		JList ListaDeEntrenadores = new JList();
		scrollPane.setViewportView(ListaDeEntrenadores);
		
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
		panel_1.add(TNombreEntrenador);
		TNombreEntrenador.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		CreacionEntrenadores.add(panel_2);
		
		JButton BCrearEntrenador = new JButton("Crear");
		panel_2.add(BCrearEntrenador);
		
		JButton btnNewButton = new JButton("entrenar");
		panel_2.add(btnNewButton);
		
		JPanel panel_12 = new JPanel();
		CreacionEntrenadores.add(panel_12);
		
		JButton BEliminarEntrenadores = new JButton("Eliminar");
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
		
		JList ListaPokemones = new JList();
		scrollPane_1.setViewportView(ListaPokemones);
		
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
		
		JComboBox ComboBoxTipo = new JComboBox();
		panel_3.add(ComboBoxTipo);
		ComboBoxTipo.setModel(new DefaultComboBoxModel(new String[] {"Agua", "Fuego", "Tierra", "Hielo", "Fantasma", "Planta"}));
		ComboBoxTipo.setMaximumRowCount(6);
		
		JPanel panel_7 = new JPanel();
		CreacionPokemones.add(panel_7);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Gran Recarga");
		panel_7.add(chckbxNewCheckBox);
		
		JPanel panel_8 = new JPanel();
		CreacionPokemones.add(panel_8);
		
		JButton BCrearPokemon = new JButton("Crear");
		panel_8.add(BCrearPokemon);
		
		JLabel lblNewLabel = new JLabel("");
		CreacionPokemones.add(lblNewLabel);
		
		JPanel panel_13 = new JPanel();
		CreacionPokemones.add(panel_13);
		
		JButton btnNewButton_1 = new JButton("Eliminar");
		panel_13.add(btnNewButton_1);
		JPanel PTorneo = new JPanel();
		contentPane.add(PTorneo);
		PTorneo.setLayout(new BorderLayout(0, 0));
		
		JPanel ConsolaTorneo = new JPanel();
		ConsolaTorneo.setBorder(new TitledBorder(null, "Torneo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		PTorneo.add(ConsolaTorneo, BorderLayout.CENTER);
		ConsolaTorneo.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_2 = new JScrollPane();
		ConsolaTorneo.add(scrollPane_2, BorderLayout.CENTER);
		
		JList list = new JList();
		scrollPane_2.setViewportView(list);
		
		JPanel CreaciondeTorneo = new JPanel();
		CreaciondeTorneo.setBorder(new TitledBorder(null, "Crear Torneo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		PTorneo.add(CreaciondeTorneo, BorderLayout.SOUTH);
		CreaciondeTorneo.setPreferredSize(new Dimension(150,150));
		CreaciondeTorneo.setLayout(new GridLayout(3, 1, 0, 0));
		
		JPanel panel_9 = new JPanel();
		CreaciondeTorneo.add(panel_9);
		
		JButton BAgregarTorneo = new JButton("Agregar");
		panel_9.add(BAgregarTorneo);
		
		JPanel panel_10 = new JPanel();
		CreaciondeTorneo.add(panel_10);
		
		JButton BEliminarTorneo = new JButton("Eliminar");
		panel_10.add(BEliminarTorneo);
		
		JPanel panel_11 = new JPanel();
		CreaciondeTorneo.add(panel_11);
		
		JButton BComenzarTorneo = new JButton("Comenzar");
		panel_11.add(BComenzarTorneo);
		JPanel PConsola = new JPanel();
		PConsola.setBorder(new TitledBorder(null, "Consola", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(PConsola);
		PConsola.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_3 = new JScrollPane();
		PConsola.add(scrollPane_3, BorderLayout.CENTER);
		
		JTextArea textArea = new JTextArea();
		scrollPane_3.setViewportView(textArea);
	}

}
