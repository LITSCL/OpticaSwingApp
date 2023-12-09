package cl.inacap.opticaswingapp.frame;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JRadioButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.filechooser.FileNameExtensionFilter;

import cl.inacap.opticaswingappmodelo.dao.LenteDAO;
import cl.inacap.opticaswingappmodelo.dao.MarcaDAO;
import cl.inacap.opticaswingappmodelo.dto.Lente;
import cl.inacap.opticaswingappmodelo.dto.Marca;

public class AgregarLenteFrame extends JInternalFrame {
	private JTextField textFieldPrecio;
	private JTextField textFieldCodigo;
	private JComboBox<String> comboBoxColorCristal;
	private JComboBox<String> comboBoxColorMarco;
	private JComboBox<String> comboBoxMaterialMarco;
	private JRadioButton rdbtnMujer;
	private JRadioButton rdbtnHombre;
	private JLabel lblVisualizacionImagenSeleccionada;
	private JTextField textFieldImagenSeleccionada;
	private Path rutaImagenOrigen;
	private File imagenSeleccionada;
	private JTextField textFieldModelo;
	private JComboBox<String> comboBoxMarca;
	private MarcaDAO daoMarca = new MarcaDAO();
	List<Marca> marcas = daoMarca.getAll();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgregarLenteFrame frame = new AgregarLenteFrame();
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
	public AgregarLenteFrame() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent arg0) {	
				rdbtnHombre.setSelected(true);
				
				comboBoxColorCristal.addItem("Amarillo");
				comboBoxColorCristal.addItem("Negro");
				comboBoxColorCristal.addItem("Rojo");
				
				comboBoxColorMarco.addItem("Negro");
				comboBoxColorMarco.addItem("Blanco");
				comboBoxColorMarco.addItem("Azul");
				
				comboBoxMaterialMarco.addItem("Plastico");
				comboBoxMaterialMarco.addItem("Metal");
				
				for (Marca m : marcas) {
					comboBoxMarca.addItem(m.getNombre());
				}
			}
		});
	setTitle("Agregar Lente");
	setClosable(true);
	setBounds(100, 100, 700, 445);
	getContentPane().setLayout(null);
	
	JLabel lblNewLabel = new JLabel("Codigo");
	lblNewLabel.setBounds(10, 33, 101, 14);
	getContentPane().add(lblNewLabel);
	
	JLabel lblPrecio = new JLabel("Precio");
	lblPrecio.setBounds(10, 58, 101, 14);
	getContentPane().add(lblPrecio);
	
	JLabel lblColorCristal = new JLabel("Color cristal");
	lblColorCristal.setBounds(10, 83, 101, 14);
	getContentPane().add(lblColorCristal);
	
	JLabel lblColorMarco = new JLabel("Color marco");
	lblColorMarco.setBounds(10, 108, 101, 14);
	getContentPane().add(lblColorMarco);
	
	JLabel lblMaterialMarco = new JLabel("Material marco");
	lblMaterialMarco.setBounds(10, 133, 101, 14);
	getContentPane().add(lblMaterialMarco);
	
	JLabel lblGenero = new JLabel("Genero");
	lblGenero.setBounds(201, 33, 101, 14);
	getContentPane().add(lblGenero);
	
	JLabel lblImagen = new JLabel("Imagen");
	lblImagen.setBounds(10, 183, 101, 14);
	getContentPane().add(lblImagen);
	
	JLabel lblMarca = new JLabel("Marca");
	lblMarca.setBounds(10, 158, 101, 14);
	getContentPane().add(lblMarca);
	
	comboBoxColorMarco = new JComboBox<String>();
	comboBoxColorMarco.setBounds(105, 105, 86, 20);
	getContentPane().add(comboBoxColorMarco);
	
	comboBoxColorCristal = new JComboBox<String>();
	comboBoxColorCristal.setBounds(105, 80, 86, 20);
	getContentPane().add(comboBoxColorCristal);
	
	textFieldPrecio = new JTextField();
	textFieldPrecio.setBounds(105, 55, 86, 20);
	getContentPane().add(textFieldPrecio);
	textFieldPrecio.setColumns(10);
	
	textFieldCodigo = new JTextField();
	textFieldCodigo.setBounds(105, 30, 86, 20);
	getContentPane().add(textFieldCodigo);
	textFieldCodigo.setColumns(10);
	
	comboBoxMaterialMarco = new JComboBox<String>();
	comboBoxMaterialMarco.setBounds(105, 130, 86, 20);
	getContentPane().add(comboBoxMaterialMarco);
	
	rdbtnHombre = new JRadioButton("Hombre");
	rdbtnHombre.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if (rdbtnHombre.isSelected()) {
				rdbtnMujer.setSelected(false);
			}
			else {
				rdbtnHombre.setSelected(true);
			}
		}
	});
	rdbtnHombre.setBounds(256, 29, 109, 23);
	getContentPane().add(rdbtnHombre);
	
	rdbtnMujer = new JRadioButton("Mujer");
	rdbtnMujer.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (rdbtnMujer.isSelected()) {
				rdbtnHombre.setSelected(false);
			}
			else {
				rdbtnMujer.setSelected(true);
			}
		}
	});
	rdbtnMujer.setBounds(256, 54, 109, 23);
	getContentPane().add(rdbtnMujer);
	
	JButton btnAgregar = new JButton("Agregar");
	btnAgregar.addActionListener(e -> agregarLente(e));
	btnAgregar.setBounds(585, 381, 89, 23);
	getContentPane().add(btnAgregar);
	
	JButton btnBuscarImagen = new JButton("Buscar");
	btnBuscarImagen.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			JFileChooser archivo = new JFileChooser();
			FileNameExtensionFilter filtro = new FileNameExtensionFilter("Formatos de Imagen (*.PNG,*.JPG,*.JPEG)", "png", "jpg", "jpeg");
			archivo.addChoosableFileFilter(filtro); //Se le añade el filtro al JFileChooser (Para que al usuario se le sea mas fácil buscar una imagen).
			archivo.setDialogTitle("Busque la imagen");
			int ventana = archivo.showOpenDialog(null); //Esta instrucción habre la ventana que permite seleccionar el archivo.
			if (ventana == JFileChooser.APPROVE_OPTION) { //Aca se esta consultando si el usuario le dio click al boton "Abrir" (En el caso de que lo haya hecho se ejecuta el código).
				imagenSeleccionada = archivo.getSelectedFile(); //Esta instrucción contiene la ruta del archivo.
				textFieldImagenSeleccionada.setText(String.valueOf(imagenSeleccionada)); //Se modifica el texto con la ruta seleccionada (También se puede utilizar el toString para refindir el dato).

				Image imagen = getToolkit().getImage(textFieldImagenSeleccionada.getText());
				imagen = imagen.getScaledInstance(170, 170, Image.SCALE_DEFAULT); //Se establece el tamaño de la imagen (Deben ser las mismas dimensiones que el label).
				lblVisualizacionImagenSeleccionada.setIcon(new ImageIcon(imagen)); //Se añade la imagen al label para que se muestre.
			}
		}
	});
	btnBuscarImagen.setBounds(105, 179, 89, 23);
	getContentPane().add(btnBuscarImagen);
	
	JLabel lblNewLabel_1 = new JLabel("Visualizaci\u00F3n");
	lblNewLabel_1.setBounds(105, 208, 128, 14);
	getContentPane().add(lblNewLabel_1);
	
	lblVisualizacionImagenSeleccionada = new JLabel("");
	lblVisualizacionImagenSeleccionada.setBounds(105, 233, 170, 170);
	getContentPane().add(lblVisualizacionImagenSeleccionada);
	
	textFieldImagenSeleccionada = new JTextField();
	textFieldImagenSeleccionada.setBounds(201, 180, 164, 20);
	getContentPane().add(textFieldImagenSeleccionada);
	textFieldImagenSeleccionada.setColumns(10);
	
	JLabel lblNewLabel_2 = new JLabel("Modelo");
	lblNewLabel_2.setBounds(201, 83, 58, 14);
	getContentPane().add(lblNewLabel_2);
	
	textFieldModelo = new JTextField();
	textFieldModelo.setBounds(256, 80, 86, 20);
	getContentPane().add(textFieldModelo);
	textFieldModelo.setColumns(10);
	
	comboBoxMarca = new JComboBox<String>();
	comboBoxMarca.setBounds(104, 155, 87, 20);
	getContentPane().add(comboBoxMarca);

	}

	private void agregarLente(ActionEvent e) {
		List<String> errores = new ArrayList<String>();
		
		String codigo = textFieldCodigo.getText().trim();
		if (codigo.equals("")) {
			errores.add("Falta colocar el código");
		}
		
		double precio = 0;
		try {
			precio = Double.parseDouble(textFieldPrecio.getText().trim());
		} catch (Exception ex) {
			errores.add("El precio no es válido");
		}
		
		String colorCristal = comboBoxColorCristal.getSelectedItem().toString();
		String colorMarco = comboBoxColorMarco.getSelectedItem().toString();
		String materialMarco = comboBoxMaterialMarco.getSelectedItem().toString();
		
		String marcaComboBox = comboBoxMarca.getSelectedItem().toString();
		int marca = 0;
		for (Marca m : marcas) {
			if (m.getNombre().equals(marcaComboBox)) {
				marca = m.getId(); 
			}
		}
		String genero = null;
		String imagen = null;
		
		//Aca es donde comienza el proceso de copiar un archivo al servidor.
		rutaImagenOrigen = Paths.get(String.valueOf(imagenSeleccionada)); //Aquí se obtiene la ruta en donde se encuentra el archivo original (El método "get" recibe por parámetro la ruta absoluta del archivo).
		Path rutaImagenDestino = Paths.get("src", "cl", "inacap", "opticaSwingApp", "imagenes"); //Aquí se obtiene la ruta del paquete donde se va a guardar el archivo de destino.
		
		try {
			Path copiar = Files.copy(rutaImagenOrigen, rutaImagenDestino.resolve(rutaImagenOrigen.getFileName()), StandardCopyOption.REPLACE_EXISTING); //NOTA: También se puede pasar como segundo parámetro, "StandardCopyOption.REPLACE_EXISTING", lo cual reemplaza el archivo cuando ya existe en la ruta de destino.
			imagen = imagenSeleccionada.getName(); //Aquí se almacena el nombre de la imagen + su extensión (Imagen.png).
		} catch (IOException ex) {
			errores.add("El archivo ya existe en la ruta de destino");
		}
		
		if (rdbtnHombre.isSelected()) {
			genero = rdbtnHombre.getText();
		}
		else {
			genero = rdbtnMujer.getText();
		}	
		
		String modelo = textFieldModelo.getText().trim();
		if (modelo.isEmpty() == true) {
			errores.add("Falta colocar el modelo");
		}
		
		if (errores.isEmpty()) {
			LenteDAO daoLente = new LenteDAO();
			
			Lente l = new Lente();
			
			l.setCodigo(codigo);
			l.setPrecio(precio);
			l.setColorCristal(colorCristal);
			l.setColorMarco(colorMarco);
			l.setMaterialMarco(materialMarco);
			l.setMarcaFK(marca);
			l.setGenero(genero);
			l.setModelo(modelo);
			l.setImagen(imagen);
			
			if (daoLente.save(l)) {
				JOptionPane.showMessageDialog(null, "Lente agregado correctamente");
			}
			else {
				JOptionPane.showMessageDialog(null, "El código que ingresaste ya existe en la BD", "Error en BD", JOptionPane.ERROR_MESSAGE);
			}
		}
		else {
			String mensaje = "";
			for (int i = 0 ; i < errores.size() ; i++) {
				mensaje+="\n" + "- " + errores.get(i);
			}
			JOptionPane.showMessageDialog(null, mensaje, "Error de validación", JOptionPane.WARNING_MESSAGE);
		}
	}
}
