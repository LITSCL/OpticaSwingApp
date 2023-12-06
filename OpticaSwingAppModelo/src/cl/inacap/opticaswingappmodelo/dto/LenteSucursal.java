package cl.inacap.opticaswingappmodelo.dto;

public class LenteSucursal {
	int id;
	String lenteFK;
	String sucursalFK;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLenteFK() {
		return lenteFK;
	}
	public void setLenteFK(String lenteFK) {
		this.lenteFK = lenteFK;
	}
	public String getSucursalFK() {
		return sucursalFK;
	}
	public void setSucursalFK(String sucursalFK) {
		this.sucursalFK = sucursalFK;
	}
}
