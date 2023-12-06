package cl.inacap.opticaswingapp.frame;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import cl.inacap.opticaswingappmodelo.dao.MarcaDAO;
import cl.inacap.opticaswingappmodelo.dto.Marca;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class AgregarMarcaFrame extends JInternalFrame {
	private JTextField textFieldNombre;
	private JTextField textFieldPais;
	private JTextField textFieldLogoSeleccionado;
	private JLabel lblVisualizacionLogoSeleccionado;
	private File logoSeleccionado;
	private Path rutaLogoOrigen;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgregarMarcaFrame frame = new AgregarMarcaFrame();
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
	public AgregarMarcaFrame() {
		setTitle("Agregar Marca");
		setClosable(true);
		setBounds(100, 100, 700, 445);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(10, 33, 46, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Pa\u00EDs");
		lblNewLabel_1.setBounds(10, 58, 73, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Logo");
		lblNewLabel_2.setBounds(10, 83, 46, 14);
		getContentPane().add(lblNewLabel_2);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(60, 30, 86, 20);
		getContentPane().add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		textFieldPais = new JTextField();
		textFieldPais.setColumns(10);
		textFieldPais.setBounds(60, 55, 86, 20);
		getContentPane().add(textFieldPais);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(e -> agregarMarca(e));
		btnAgregar.setBounds(585, 381, 89, 23);
		getContentPane().add(btnAgregar);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JFileChooser archivo = new JFileChooser();
				FileNameExtensionFilter filtro = new FileNameExtensionFilter("Formatos de Imagen (*.PNG,*.JPG,*.JPEG)", "png", "jpg", "jpeg");
				archivo.addChoosableFileFilter(filtro); //Se le a�ade el filtro al JFileChooser (Para que al usuario se le sea mas f�cil buscar una imagen).
				archivo.setDialogTitle("Busque la imagen");
				int ventana = archivo.showOpenDialog(null); //Esta instrucci�n habre la ventana que permite seleccionar el archivo.
				if (ventana == JFileChooser.APPROVE_OPTION) { //Aca se esta consultando si el usuario le dio click al boton "Abrir" (En el caso de que lo haya hecho se ejecuta el c�digo).
					logoSeleccionado = archivo.getSelectedFile(); //Esta instrucci�n contiene la ruta del archivo.
					textFieldLogoSeleccionado.setText(String.valueOf(logoSeleccionado)); //Se modifica el texto con la ruta seleccionada (Tambi�n se puede utilizar el toString para refindir el dato).
					
					Image imagen = getToolkit().getImage(textFieldLogoSeleccionado.getText());
					imagen = imagen.getScaledInstance(170, 170, Image.SCALE_DEFAULT); //Se establece el tama�o de la imagen (Deben ser las mismas dimensiones que el label).
					lblVisualizacionLogoSeleccionado.setIcon(new ImageIcon(imagen)); //Se a�ade la imagen al label para que se muestre.
				}
			}
		});
		btnBuscar.setBounds(60, 79, 89, 23);
		getContentPane().add(btnBuscar);
		
		textFieldLogoSeleccionado = new JTextField();
		textFieldLogoSeleccionado.setColumns(10);
		textFieldLogoSeleccionado.setBounds(159, 80, 164, 20);
		getContentPane().add(textFieldLogoSeleccionado);
		
		JLabel lblVisualizacion = new JLabel("Visualizaci\u00F3n");
		lblVisualizacion.setBounds(60, 113, 128, 14);
		getContentPane().add(lblVisualizacion);
		
		lblVisualizacionLogoSeleccionado = new JLabel("");
		lblVisualizacionLogoSeleccionado.setBounds(60, 138, 170, 170);
		getContentPane().add(lblVisualizacionLogoSeleccionado);
	}

	private void agregarMarca(ActionEvent e) {
		List<String> errores = new ArrayList<String>();
		
		String nombre = this.textFieldNombre.getText().trim();
		if (nombre.isEmpty()) {
			errores.add("Falta colocar el nombre");
		}
		
		String pais = this.textFieldPais.getText().trim();
		if (pais.isEmpty()) {
			errores.add("Falta colocar el pa�s");
		}
		
		String logo = null;
		
		//Aca es donde comienza el proceso de copiar un archivo al servidor.
		rutaLogoOrigen = Paths.get(String.valueOf(logoSeleccionado)); //Aqu� se obtiene la ruta en donde se encuentra el archivo original (El m�todo "get" recibe por par�metro la ruta absoluta del archivo).
		Path rutaLogoDestino = Paths.get("src", "cl", "inacap", "opticaSwingApp", "imagenes"); //Aqu� se obtiene la ruta del paquete donde se va a guardar el archivo de destino.
		
		try {
			Path copiar = Files.copy(rutaLogoOrigen, rutaLogoDestino.resolve(rutaLogoOrigen.getFileName()), StandardCopyOption.REPLACE_EXISTING); //NOTA: Tambi�n se puede pasar como segundo par�metro, "StandardCopyOption.REPLACE_EXISTING", lo cual reemplaza el archivo cuando ya existe en la ruta de destino.
			logo = logoSeleccionado.getName(); //Aqu� se almacena el nombre de la imagen + su extensi�n (Imagen.png).
		} catch (IOException ex) {
			errores.add("El archivo ya existe en la ruta de destino");
		}
		
		if (errores.isEmpty() == true) {
			MarcaDAO daoMarca = new MarcaDAO();
			
			Marca m = new Marca();
			
			m.setNombre(nombre);
			m.setPaisOrigen(pais);
			m.setLogo(logo);
			
			if (daoMarca.save(m)) {
				JOptionPane.showMessageDialog(null, "Lente agregado correctamente");
			}
		}
		else {
			String mensaje = "";
			for (int i = 0 ; i < errores.size() ; i++) {
				mensaje+="\n" + "- " + errores.get(i);
			}
			JOptionPane.showMessageDialog(null, mensaje, "Error de validaci�n", JOptionPane.WARNING_MESSAGE);
		}
	}
}
