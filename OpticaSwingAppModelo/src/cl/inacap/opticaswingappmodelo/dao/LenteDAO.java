package cl.inacap.opticaswingappmodelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cl.inacap.opticaswingappmodelo.dto.Lente;
import cl.inacap.opticaswingappmodelo.util.BDUtil;


public class LenteDAO {
	private BDUtil bdUtil = new BDUtil();
	
	public boolean save(Lente l) {
		boolean resultado;
		try {
			System.out.println("Conexión a la DB: " + bdUtil.conectar());
			String sql = "INSERT INTO lente" + "(codigo, precio, color_cristal, color_marco, material_marco, genero,modelo, imagen, marca_id)" + " VALUES('" + l.getCodigo() + "','" + l.getPrecio() + "','" + l.getColorCristal() + "','"+ l.getColorMarco() + "','" + l.getMaterialMarco() + "','" + l.getGenero() + "','" + l.getModelo() + "','" + l.getImagen() + "','" + l.getMarcaFK() + "')"; 
			Statement st = bdUtil.getConexion().createStatement();
			st.executeUpdate(sql);
			resultado = true;
			System.out.println("Ejecución del SQL: " + resultado);
		} catch (Exception ex) {
			resultado = false;
			System.out.println("Ejecución del SQL: " + resultado);
			
		} finally { 
			bdUtil.desconectar(); 
		}
		return resultado;
	}
	
	public List<Lente> getAll() {
		List<Lente> lentes = new ArrayList<Lente>();
		
		boolean resultado;
		try {
			
			System.out.println("Conexión a la DB: " + bdUtil.conectar());
		
			String sql = "SELECT *" + " FROM lente";
			PreparedStatement st = bdUtil.getConexion().prepareStatement(sql); 
		
			ResultSet rs = st.executeQuery();
			resultado = true;
			System.out.println("Ejecución del SQL: " + resultado);
			while (rs.next()) { 
				Lente l = new Lente();
				l.setCodigo(rs.getString(1));
				l.setPrecio(rs.getInt(2));
				l.setColorCristal(rs.getString(3));
				l.setColorMarco(rs.getString(4));
				l.setMaterialMarco(rs.getString(5));
				l.setGenero(rs.getString(6));
				l.setModelo(rs.getString(7));
				l.setImagen(rs.getString(8));
				l.setMarcaFK(rs.getInt(9));
				
				lentes.add(l);
			}
			rs.close(); 
		} catch (Exception ex) {
			resultado = false;
			System.out.println("Ejecución del SQL: " + resultado);
			lentes = null;
			
		} finally { 
			bdUtil.desconectar(); 
		}

		return lentes;
	}
}
