package cl.inacap.opticaswingapp.frame;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cl.inacap.opticaswingappmodelo.dao.MarcaDAO;
import cl.inacap.opticaswingappmodelo.dto.Marca;
import cl.inacap.opticaswingapp.util.ImagenTablaUtil;

import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

public class MostrarMarcaFrame extends JInternalFrame {
	private JTable tableMarca;
	private DefaultTableModel mo = new DefaultTableModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MostrarMarcaFrame frame = new MostrarMarcaFrame();
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
	public MostrarMarcaFrame() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent arg0) {
				cargarTabla();
			}

		});
		setTitle("Mostrar Marca");
		setClosable(true);
		setBounds(100, 100, 700, 445);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPaneMarca = new JScrollPane();
		scrollPaneMarca.setBounds(10, 11, 664, 393);
		getContentPane().add(scrollPaneMarca);
		
		tableMarca = new JTable();
		tableMarca.setEnabled(false);
		scrollPaneMarca.setViewportView(tableMarca);

	}

	private void cargarTabla() {
		List<Marca> marcas = new MarcaDAO().getAll();
		
		tableMarca.setDefaultRenderer(Object.class, new ImagenTablaUtil());

		mo.addColumn("ID");
		mo.addColumn("Nombre");
		mo.addColumn("Pa�s");
		mo.addColumn("Logo");
		
		Object []fila = new Object[4];
		
		for (Marca m : marcas) {
			fila[0] = m.getId();
			fila[1] = m.getNombre();
			fila[2] = m.getPaisOrigen();
			fila[3] = new JLabel(new ImageIcon(getClass().getResource("/cl/inacap/opticaSwingApp/imagenes/" + m.getLogo())));
			mo.addRow(fila);
		}

		tableMarca.setRowHeight(50);
		tableMarca.setModel(mo);
	}
}
