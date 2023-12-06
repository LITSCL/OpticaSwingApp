package cl.inacap.opticaswingapp.frame;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cl.inacap.opticaswingappmodelo.dao.LenteDAO;
import cl.inacap.opticaswingappmodelo.dto.Lente;
import cl.inacap.opticaswingapp.util.ImagenTablaUtil;

import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

public class MostrarLenteFrame extends JInternalFrame {
	private JTable tableLente;
	private DefaultTableModel mo = new DefaultTableModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MostrarLenteFrame frame = new MostrarLenteFrame();
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
	public MostrarLenteFrame() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent e) {
				cargarTabla();
			}
		});
		setTitle("Mostrar Lente");
		setClosable(true);
		setBounds(100, 100, 700, 445);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPaneLente = new JScrollPane();
		scrollPaneLente.setBounds(10, 11, 664, 393);
		getContentPane().add(scrollPaneLente);
		
		tableLente = new JTable();
		tableLente.setEnabled(false);
		scrollPaneLente.setViewportView(tableLente);

	}
	
	private void cargarTabla() {
		List<Lente> lentes = new LenteDAO().getAll();
		
		tableLente.setDefaultRenderer(Object.class, new ImagenTablaUtil());
		
		mo.addColumn("Código");
		mo.addColumn("Precio");
		mo.addColumn("Color Cristal");
		mo.addColumn("Color Marco");
		mo.addColumn("Material Marco");
		mo.addColumn("Genero");
		mo.addColumn("Modelo");
		mo.addColumn("Imagen");
		mo.addColumn("Marca");
		
		Object []fila = new Object[9];
		
		for (Lente l : lentes) {
			fila[0] = l.getCodigo();
			fila[1] = l.getPrecio();
			fila[2] = l.getColorCristal();
			fila[3] = l.getColorMarco();
			fila[4] = l.getMaterialMarco();
			fila[5] = l.getGenero();
			fila[6] = l.getModelo();
			fila[7] = new JLabel(new ImageIcon(getClass().getResource("/cl/inacap/opticaSwingApp/imagenes/" + l.getImagen())));
			fila[8] = l.getMarcaFK();
			mo.addRow(fila);
		}
		
		tableLente.setRowHeight(50);
		tableLente.setModel(mo);
	}
}
