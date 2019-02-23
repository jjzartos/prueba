package ti.snfco.NominaYCapitalHumano.service;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import org.slf4j.LoggerFactory;

/*
 * Clase de conexion a la base de datos generales para acceso a usuarios
 * @autor: jesus zarate tostado
 * 20/09/2017
 */

public class Login implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(Login.class);
	//PoolNYCH poolNYCH = new PoolNYCH();
	PoolGenerales poolGenerales = new PoolGenerales();
	String usuario = MainFrame.textFieldUser.getText();
	String pass = String.valueOf(MainFrame.passwordField.getPassword());
	
	
	public int validarLogin() {
		
//		String usuario="jesus.zarate";
//		String pass="Tostado.17";
		
		int resultado = 0;
//		String sql= "SELECT * FROM acceso WHERE usuario='"+ usuario +"' and Password='"+ pass +"'";
		
		String sql="SELECT acc.IdUsuario, usr.Usuario, usr.IdDependencia, usr.IdSubDependencia, dep.Dependencia, usr.Paterno, usr.Materno, usr.Nombres, usr.Foto, usr.Estatus, acc.TipoAcceso,"
				+ " (SELECT CASE acc.TipoAcceso WHEN 0 THEN 'Administrador' WHEN 1 THEN 'Usuario' WHEN 2 THEN 'Consultas' END) AS TipoUsuario, 'Si' AS Acc"
				+ " FROM Accesos acc"
				+ " INNER JOIN Usuarios usr ON acc.IdUsuario = usr.IdUsuario AND acc.IdSistema = 4"
				+ " INNER JOIN Dependencias dep ON usr.IdDependencia = dep.IdDependencia AND usr.IdSubDependencia = 0"
				+ " WHERE usr.Usuario = '"+ usuario +"' AND usr.Password = '"+ Utilidades.get_md5(pass) +"' AND usr.Estatus = 'Activo'";
		Connection connect = null;
		try {
			connect = poolGenerales.datasource.getConnection();
			//connect = poolNYCH.datasource.getConnection();
			Statement st = connect.createStatement();
			ResultSet resultSet = st.executeQuery(sql);
			//System.out.println("sql: " + sql);
			if(resultSet.next()) {
				resultado=1;
				//System.out.println("resultado:" + resultado);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: "+ errors );
			JOptionPane.showMessageDialog(null, e, "Error de conexion", JOptionPane.ERROR_MESSAGE);
		}finally {
			try {
				connect.close();
			} catch (SQLException e) {
				e.printStackTrace();
				StringWriter errors = new StringWriter();
				e.printStackTrace(new PrintWriter(errors));
				LOG.info("Excepcion: "+ errors );
				JOptionPane.showMessageDialog(null, e, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
			}
		}
	return resultado;
	}

	
	
	
	
	
	
	
	/************************************setter y getter************************************/
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario= usuario;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	
	
	
	
	

}
