package cl.inacap.opticaswingapp;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cl.inacap.opticaswingapp.frame.AgregarLenteFrame;
import cl.inacap.opticaswingapp.frame.AgregarMarcaFrame;
import cl.inacap.opticaswingapp.frame.MostrarLenteFrame;
import cl.inacap.opticaswingapp.frame.MostrarMarcaFrame;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JDesktopPane;
import java.awt.event.ActionListener;

public class StartFrame extends JFrame {
	private AgregarLenteFrame agregarLenteFrame = new AgregarLenteFrame();
	private AgregarMarcaFrame agregarMarcaFrame = new AgregarMarcaFrame();
	private MostrarLenteFrame mostrarLenteFrame = new MostrarLenteFrame();
	private MostrarMarcaFrame mostrarMarcaFrame = new MostrarMarcaFrame();
	private JPanel contentPane;
	private JDesktopPane desktopPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartFrame frame = new StartFrame();
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
	public StartFrame() {
		setTitle("OpticaSwingApp");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 652);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnOpciones = new JMenu("Opciones");
		menuBar.add(mnOpciones);
		
		JMenu mnLente = new JMenu("Lente");
		mnOpciones.add(mnLente);
		
		JMenuItem mntmAgregarLente = new JMenuItem("Agregar lente");
		mnLente.add(mntmAgregarLente);
		
		JMenuItem mntmMostrarLentes = new JMenuItem("Mostrar lentes");
		mntmMostrarLentes.addActionListener(e -> showFrameMostrarLentes(e));
		mnLente.add(mntmMostrarLentes);
		
		JMenuItem mntmBorrarLentes = new JMenuItem("Borrar lente");
		mnLente.add(mntmBorrarLentes);
		
		JMenu mnMarca = new JMenu("Marca");
		mnOpciones.add(mnMarca);
		
		JMenuItem mntmAgregarMarca = new JMenuItem("Agregar marca");
		mntmAgregarMarca.addActionListener(e -> showFrameAgregarMarca(e));
		mnMarca.add(mntmAgregarMarca);
		
		JMenuItem mntmMostrarMarcas = new JMenuItem("Mostrar marcas");
		mntmMostrarMarcas.addActionListener(e -> showFrameMostrarMarcas(e));
		mnMarca.add(mntmMostrarMarcas);
		mntmAgregarLente.addActionListener(e -> showFrameAgregarLente(e));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		desktopPane = new JDesktopPane();
		contentPane.add(desktopPane, BorderLayout.CENTER);
	}
	
	private void showFrameAgregarLente(ActionEvent e) {
		if (this.agregarLenteFrame != null) {
			desktopPane.remove(this.agregarLenteFrame);
		}
		this.agregarLenteFrame = new AgregarLenteFrame();
		desktopPane.add(this.agregarLenteFrame);
		this.agregarLenteFrame.setVisible(true);
	}
	
	private void showFrameAgregarMarca(ActionEvent e) {
		if (this.agregarMarcaFrame != null) {
			desktopPane.remove(this.agregarMarcaFrame);
		}
		this.agregarMarcaFrame = new AgregarMarcaFrame();
		desktopPane.add(this.agregarMarcaFrame);
		this.agregarMarcaFrame.setVisible(true);
	}
	
	private void showFrameMostrarLentes(ActionEvent e) {
		if (this.mostrarLenteFrame != null) {
			desktopPane.remove(this.mostrarLenteFrame);
		}
		this.mostrarLenteFrame = new MostrarLenteFrame();
		desktopPane.add(this.mostrarLenteFrame);
		this.mostrarLenteFrame.setVisible(true);
	}
	
	private void showFrameMostrarMarcas(ActionEvent e) {
		if (this.mostrarMarcaFrame != null) {
			desktopPane.remove(this.mostrarMarcaFrame);
		}
		this.mostrarMarcaFrame = new MostrarMarcaFrame();
		desktopPane.add(this.mostrarMarcaFrame);
		this.mostrarMarcaFrame.setVisible(true);
	}
}
