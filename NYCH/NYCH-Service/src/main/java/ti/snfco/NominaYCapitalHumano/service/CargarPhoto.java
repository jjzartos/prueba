package ti.snfco.NominaYCapitalHumano.service;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.BASE64Decoder; 
import sun.misc.BASE64Encoder;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import org.slf4j.LoggerFactory;


@SuppressWarnings("restriction")
public class CargarPhoto {

	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(CargarPhoto.class);

	int cont = 0;
	FileInputStream fileInputStream;

	public CargarPhoto() {
		super();
	}


	public static String encodeToString(BufferedImage image) {
		String imageString = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		try {
			ImageIO.write(image, "jpg", bos);
			byte[] imageBytes = bos.toByteArray();

			BASE64Encoder encoder = new BASE64Encoder();
			imageString = encoder.encode(imageBytes);

			bos.close();
		} catch (IOException e) {
			e.printStackTrace();
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: "+ errors );
		}
		return imageString;
	}

	public static BufferedImage decodeToImage(String imageString) {

		BufferedImage image = null;
		byte[] imageByte;
		try {
			BASE64Decoder decoder = new BASE64Decoder();
			imageByte = decoder.decodeBuffer(imageString);
			ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
			image = ImageIO.read(bis);
			bis.close();
		} catch (Exception e) {
			e.printStackTrace();
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: "+ errors );
		}
		return image;
	}


	void guardar_imagen(String FOTO, String NOMBRE_FOTO) {
		String sql = "";
		sql = "INSERT INTO dbo.EMPLEADOS_FOTOS (FOTO,NOMBRE_FOTO) values (?,?)";
		System.out.println(sql);
		Connection conn =null;
		PoolNYCH nych = new PoolNYCH();
		try {
			conn = nych.datasource.getConnection();
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, FOTO);
			pst.setString(2, NOMBRE_FOTO);
			int n = pst.executeUpdate();
			if (n > 0) {
				JOptionPane.showMessageDialog(null, "Registro guardado");
				contar();
			} else {
				JOptionPane.showMessageDialog(null, "Error al insertar el registro.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			Logger.getLogger(CargarPhoto.class.getName()).log(Level.SEVERE, null, e);
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: "+ errors );
		}
	}


	void cargar(int limite) {
		InternalFrameEmpleado ife = new InternalFrameEmpleado();
		BufferedImage img = null;
		String sql = "SELECT DBO.EMPLEADOS_FOTOS.FOTO FROM DBO.EMPLEADOS_FOTOS INNER JOIN DBO.EMPLEADOS ON DBO.EMPLEADOS_FOTOS.ID_FOTO = DBO.EMPLEADOS.ID_FOTO WHERE DBO.EMPLEADOS.NOMBRE='"+ ife.textFieldBuscarEmpExpediente.getText() +"'";
		//String sql = "SELECT * FROM DBO.EMPLEADOS_FOTOS limit " + limite + ",1";
		String imagen_string = null;
		Connection conn =null;
		PoolNYCH nych = new PoolNYCH();
		try {
			conn = nych.datasource.getConnection();
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				imagen_string = rs.getString("foto");
				ife.lblRutaFoto.setText(rs.getString("nombre_foto"));
			}
			if (imagen_string == null) {
				cont = cont - 1;
				contar();
			} else {
				img = decodeToImage(imagen_string);
				ImageIcon icon = new ImageIcon(img);
				Icon icono = new ImageIcon(icon.getImage().getScaledInstance(ife.lblfoto.getWidth(), ife.lblfoto.getHeight(), Image.SCALE_DEFAULT));
				ife.lblfoto.setText(null);
				ife.lblfoto.setIcon(icono);
			}

		} catch (Exception ex) {
			Logger.getLogger(CargarPhoto.class.getName()).log(Level.SEVERE, null, ex);
			StringWriter errors = new StringWriter();
			ex.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: "+ errors );
		}
	}


	void contar() {
		String sql2 = "SELECT count(*) as cont from dbo.empleados_fotos";
		Connection conn =null;
		PoolNYCH nych = new PoolNYCH();
		try {
			int con = 0;
			conn = nych.datasource.getConnection();
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sql2);
			while (rs.next()) {
				con = rs.getInt("cont");
			}
			if (con == 0) {
				JOptionPane.showMessageDialog(null, "No se encontraron imagenes");
			} else {
				cont = con - 1;
				cargar(cont);
			}
		} catch (Exception ex) {
			Logger.getLogger(CargarPhoto.class.getName()).log(Level.SEVERE, null, ex);
			StringWriter errors = new StringWriter();
			ex.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: "+ errors );
		}
	}

	public static ImageIcon getFoto(int id){
		InternalFrameEmpleado empleado = new InternalFrameEmpleado();
		String sql = "SELECT DBO.EMPLEADOS_FOTOS.FOTO from DBO.EMPLEADOS_FOTOS where ID_FOTO = "+ id ;
		System.out.println(sql);
		ImageIcon ii = null;
		InputStream is = null;
		Connection conn =null;
		//ImageIO io = null;
		// BufferedImage bi = null;
		PoolNYCH nych = new PoolNYCH();
		try{
			conn = nych.datasource.getConnection();
			Statement st = conn.createStatement();
			ResultSet  rs = st.executeQuery(sql); 
			if(rs.next()){
				is = rs.getBinaryStream(1);
				BufferedImage bi = ImageIO.read(is);
				System.out.println(bi);
				ii = new ImageIcon(bi);
				System.out.println(ii);

			}
		}catch(Exception ex){
			ex.printStackTrace();
			StringWriter errors = new StringWriter();
			ex.printStackTrace(new PrintWriter(errors));
			LOG.info("Excepcion: "+ errors );
			}

		return ii;
	}


}
