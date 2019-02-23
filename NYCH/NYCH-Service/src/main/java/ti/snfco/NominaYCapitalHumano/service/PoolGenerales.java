package ti.snfco.NominaYCapitalHumano.service;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;


public class PoolGenerales {

	public DataSource datasource;
	//public String db= "dbGenerales";
//	public String url= "jdbc:sqlserver://WIN-9P8RVU1HG4H\\SNFCOSQLSERVER;databaseName=dbGenerales;";
	//jdbc:jtds:sqlserver://DB_Server_Name;databaseName=DB_Name;instance=;useLOBs=false

	
	//desarrollo
//	public String url= "jdbc:sqlserver://192.168.235.224\\SNFCOSQLSERVER;databaseName=dbGenerales;";
//	public String user= "usrSanFco";
//	public String pass= "MuyD1f1c1l";
	
	
	//produccion
	public String url= "jdbc:sqlserver://192.168.112.5\\SQL_SANFRANCISCO;databaseName=dbGenerales;";
	public String user= "sa";
	public String pass= "Mun1c|p105@nF|2@nc15c0";
	
	
	public PoolGenerales() {
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
