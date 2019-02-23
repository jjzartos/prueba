package ti.snfco.NominaYCapitalHumano.service;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;
import javax.swing.JOptionPane;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.LoggerFactory;

public class PoolNYCH implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(PoolNYCH.class);
	public static  DataSource datasource;
	ConfigurarDataBase configurarDataBase = new ConfigurarDataBase();
	//public  String db= "NYCH";
	//public  String url= "jdbc:sqlserver://WIN-9P8RVU1HG4H\\SNFCOSQLSERVER;databaseName=NYCH;";
	//url para jasperestudio: jdbc:sqlserver://WIN-9P8RVU1HG4H\SNFCOSQLSERVER;databaseName=NYCH;
	//	public String url= "jdbc:sqlserver://TOSHIBA-ELI;databaseName=NOMINA;";
	//	public  String user= "sa";
	//	public  String pass= "12345";
//
		public String url= "jdbc:sqlserver://"+configurarDataBase.getTextFieldServidor().getText()+";databaseName="+ configurarDataBase.getTextFieldDB().getText() +";";
		public  String user= configurarDataBase.getTextFieldUsuario().getText();
		@SuppressWarnings("deprecation")
		public  String pass= configurarDataBase.getPasswordFieldDB().getText();

	
//	String url= "jdbc:sqlserver://192.168.202.98\\SNFCOSQLSERVER;databaseName=NYCH;";
//	String user= "usrSanFco";
//	String pass= "MuyD1f1c1l";

	//public String uri= "jdbc:sqlserver://"+ configurarDataBase.getTextFieldServidor().getText() +";databaseName="+ configurarDataBase.getTextFieldDB().getText() +";";



	public PoolNYCH() {
		inicializarDataSource();
	}

//	public void getParam(){
//		ConfigurarDataBase configurarDataBase = new ConfigurarDataBase();
//		ArrayList<String> listaParametros = new ArrayList<String>();
//		listaParametros = configurarDataBase.mostrarParametrosConexion();
//		for(int i = 0; i<listaParametros.size();i++){
//			System.out.println("indiceDatos: "+ i + " |valorDatos: " + listaParametros.get(i));
//		}
//		
//		String url= "jdbc:sqlserver://"+ listaParametros.get(0) +";databaseName="+ listaParametros.get(1) +";";
//		String user = listaParametros.get(3);
//		String pass = listaParametros.get(4);
//		
//		//inicializarDataSource(url,user,pass);
//		
//	}
	
	public  void inicializarDataSource() {
//		//ConfigurarDataBase configurarDataBase = new ConfigurarDataBase();
//		ArrayList<String> listaParametros = new ArrayList<String>();
//		listaParametros =selectParametros();
//		for(int i = 0; i<listaParametros.size();i++){
//			System.out.println("indiceDatos: "+ i + " |valorDatos: " + listaParametros.get(i));
//		}
		
//		String url= "jdbc:sqlserver://"+ listaParametros.get(0) +";databaseName="+ listaParametros.get(1) +";";
//		String user = listaParametros.get(3);
//		String pass = listaParametros.get(4);
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		basicDataSource.setUsername(user);
		basicDataSource.setPassword(pass);
		basicDataSource.setUrl(url);
//		System.out.println("url: "+url);
		basicDataSource.setMaxActive(50);
		datasource = basicDataSource;

//		System.out.println("uri: " + url);
//		System.out.println("usuario: " + user);
//		System.out.println("passw: "+  pass);

	}
	
	
//	public static ArrayList<String> selectParametros() {
//		//PoolNYCH nych = new PoolNYCH();
//		Connection connect = null;
//		Statement st;
//		ResultSet resultSet = null;
//		ArrayList<String> lista = new ArrayList<String>();
//		String sqlParam = "select SERVIDOR,BASE_DE_DATOS,PUERTO,USUARIO,PASSWORD from dbo.PARAMETROS_CONEXION";
//		try {
//			connect = datasource.getConnection();
//			st = connect.createStatement();
//			resultSet = st.executeQuery(sqlParam);
//		}catch(Exception exp) {
//			exp.printStackTrace();
//			StringWriter errors = new StringWriter();
//			exp.printStackTrace(new PrintWriter(errors));
//			LOG.info("Excepcion: "+ errors );
//			JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
//		}try {
//			while(resultSet.next()){
//				//lista.add(resultSet.getString("NOMBRE_PUESTO"));
//				lista.add(resultSet.getString(1));
//				lista.add(resultSet.getString(2));
//				lista.add(resultSet.getString(3));
//				lista.add(resultSet.getString(4));
//				lista.add(resultSet.getString(5));
//			}
//		}catch(Exception et) {
//			et.printStackTrace();
//			StringWriter errors = new StringWriter();
//			et.printStackTrace(new PrintWriter(errors));
//			LOG.info("Excepcion: "+ errors );
//			JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
//		}finally {
//			try {
//				connect.close();
//			} catch (SQLException ep) {
//				ep.printStackTrace();
//				StringWriter errors = new StringWriter();
//				ep.printStackTrace(new PrintWriter(errors));
//				LOG.info("Excepcion: "+ errors );
//				JOptionPane.showMessageDialog(null,"Error de conexión, vuelva a intentarlo");
//			}
//		}
//		return lista;
//	}

	
	
	
}
