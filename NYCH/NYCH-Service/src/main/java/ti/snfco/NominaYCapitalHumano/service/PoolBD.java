package ti.snfco.NominaYCapitalHumano.service;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

public class PoolBD {
	
	public DataSource datasource;
	public String db= "XE";
	public String url= "jdbc:oracle:thin:@localhost:1521:XE";
	public String user= "system";
	public String pass= "admin";
	
	
	public PoolBD() {
		inicializarDataSource();
	}
	
	
	private void inicializarDataSource() {
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		basicDataSource.setUsername(user);
		basicDataSource.setPassword(pass);
		basicDataSource.setUrl(url);
		basicDataSource.setMaxActive(50);
		datasource = basicDataSource;
	}
}
