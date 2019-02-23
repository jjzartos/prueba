package ti.snfco.NominaYCapitalHumano.service;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

public class PoolChecador {

	
	public DataSource datasource;
	//public String db= "checador";
	//public String url= "jdbc:sqlserver://WIN-9P8RVU1HG4H\\SNFCOSQLSERVER;databaseName=dbGenerales;";
//	public String url= "jdbc:sqlserver://192.168.112.5\\SQL_SANFRANCISCO;databaseName=checador;";
//	public String user= "usrSanFco";
//	public String pass= "MuyD1f1c1l";
	
	public String url= "jdbc:sqlserver://192.168.112.5\\SQL_SANFRANCISCO;databaseName=checador45pro2;";
	public String user= "sa";
	public String pass= "Mun1c|p105@nF|2@nc15c0";//
	
	
	public PoolChecador() {
		inicializarDataSource();
	}
	
	
	private void inicializarDataSource() {
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		//basicDataSource.setDriverClassName("net.sourceforge.jtds.jdbc.Driver");
		basicDataSource.setUsername(user);
		basicDataSource.setPassword(pass);
		basicDataSource.setUrl(url);
		basicDataSource.setMaxActive(50);
		datasource = basicDataSource;
		
		
	}
	
}
