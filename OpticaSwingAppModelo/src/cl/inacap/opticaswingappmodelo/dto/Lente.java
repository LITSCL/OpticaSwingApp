package cl.inacap.opticaswingappmodelo.dto;

public class Lente {
	String codigo;
	double precio;
	String colorCristal;
	String colorMarco;
	String materialMarco;
	String genero;
	String modelo;
	String imagen;
	int marcaFK;
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getColorCristal() {
		return colorCristal;
	}

	public void setColorCristal(String colorCristal) {
		this.colorCristal = colorCristal;
	}

	public String getColorMarco() {
		return colorMarco;
	}

	public void setColorMarco(String colorMarco) {
		this.colorMarco = colorMarco;
	}

	public String getMaterialMarco() {
		return materialMarco;
	}

	public void setMaterialMarco(String materialMarco) {
		this.materialMarco = materialMarco;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public int getMarcaFK() {
		return marcaFK;
	}
	
	public void setMarcaFK(int marcaFK) {
		this.marcaFK = marcaFK;
	}
}
