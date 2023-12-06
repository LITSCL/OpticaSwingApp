package cl.inacap.opticaswingappmodelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cl.inacap.opticaswingappmodelo.dto.Marca;
import cl.inacap.opticaswingappmodelo.util.BDUtil;

public class MarcaDAO {
	private BDUtil bdUtil = new BDUtil();
	
	public boolean save(Marca m) {
		boolean resultado;
		try {
			System.out.println("Conexi�n a la DB: " + bdUtil.conectar());
			String sql = "INSERT INTO marca" + "(nombre, pais_origen, logo)" + " VALUES('" + m.getNombre() + "','" + m.getPaisOrigen() + "','" + m.getLogo() + "')"; 
			Statement st = bdUtil.getConexion().createStatement();
			st.executeUpdate(sql);
			resultado = true;
			System.out.println("Ejecuci�n del SQL: " + resultado);
		} catch (Exception ex) {
			resultado = false;
			System.out.println("Ejecuci�n del SQL: " + resultado);
			
		} finally { 
			bdUtil.desconectar(); 
		}
		return resultado;
	}
	
	public List<Marca> getAll(){
		List<Marca> marcas = new ArrayList<Marca>();
		
		boolean resultado;
		try {
			
			System.out.println("Conexi�n a la DB: " + bdUtil.conectar());
		
			String sql = "SELECT *" + " FROM marca";
			PreparedStatement st = bdUtil.getConexion().prepareStatement(sql); 
		
			ResultSet rs = st.executeQuery();
			resultado = true;
			System.out.println("Ejecuci�n del SQL: " + resultado);
			while (rs.next()) { 
				Marca m = new Marca();
				m.setId(rs.getInt(1));
				m.setNombre(rs.getString(2));
				m.setPaisOrigen(rs.getString(3));
				m.setLogo(rs.getString(4));
				
				marcas.add(m);
			}
			rs.close(); 
		} catch (Exception ex) {
			resultado = false;
			System.out.println("Ejecuci�n del SQL: " + resultado);
			marcas = null;
			
		} finally { 
			bdUtil.desconectar(); 
		}

		return marcas;
	}
}
