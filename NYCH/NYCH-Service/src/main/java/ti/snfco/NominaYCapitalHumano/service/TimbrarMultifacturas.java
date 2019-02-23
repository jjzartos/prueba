package ti.snfco.NominaYCapitalHumano.service;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.omg.IOP.ENCODING_CDR_ENCAPS;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class TimbrarMultifacturas {

	//SOLICITUD AL WS
	String strRequest = null;
	
	//RESPUESTA DEL WS
	HttpEntity entity;
	//RESPUESTA DEL WS A STRING
	String strResponse =null;
	
	//RESPUESTA DE LA CLASE MULTIFACTURAS
	HashMap<String, String> respuestaMultifacturas;
	
	public TimbrarMultifacturas(){}

	public String encode64(String cadena)
	{
		byte[] encoded = Base64.encodeBase64(cadena.getBytes()); 
		//System.out.println("Base64 Encoded String : " + new String(encoded));
		return new String(encoded);
	}
	public String decode64(String bytesEncoded)
	{
		// Decode data on other side, by processing encoded data
		byte[] valueDecoded= Base64.decodeBase64(bytesEncoded);
		//System.out.println("Decoded value is " + new String(valueDecoded));
		return new String(valueDecoded);
	}

	/*********************    TIMBRAR XML ***********************************************************
	/* timbrarXML(String url_webservice, String rfc, String clave,String ruta_xml, String produccion);
	 * 
	 * PARAMETROS:
	 * url_webservice : URL DEL WS MULTIFACTURAS
	 * rfc: RFC DEL CLIENTE QUE TIMBRARA EL XML
	 * clave: CLAVE DEL RFC EN EL PANEL DE MULTIFACTURAS
	 * ruta_xml: RUTA DONDE ESTA EL XML SELLADO DE LA FACTURA
	 * produccion: SI EL TIMBRADO ES DE PRUEBA O NO
	 * 
	 * RESPUESTA: HashMap <String,String> CON LA RESPUESTA DE MULTIFACTURAS
	 * ELEMENTOS HASHMAP:
	 * cfdi64: XML EN BASE64
	 * cfdi; xml timbrado en formato cadena
	 * png: PNG DE CODIGO QR EN BASE64
	 * idpac: PAC CON EL QUE SE TIMBRO EL XML
	 * produccion: SI FUE TIMBRADO EN MODO DE PRUEBAS O TIMBRADO REAL
	 * codigo_mf_numero: NUMERO DE CODIGO DE RESPUESTA MULTIFACTURAS
	 * codigo_mf_texto: TEXTO DE CODIGO DE RESPUESTA MULTIFACTURAS
	 * mensaje_original_pac_json: RESPUESTA DEL PAC EN JSON
	 * cancelada: SI FUE TIMBRADO DE CANCELACION
	 * saldo: SALDO DEL RFC
	 * uuid: UUID DEL LA FACTURA TIMBRADA
	 * servidor: SERVIDOR MULTIFACTURAS 
	 * ejecucion: TIEMPO DE EJECUCION DEL TIMBRADO
	 */
	public HashMap<String, String> timbrarXML(String url_ws, String rfc, String clave,String ruta_xml,String produccion)
	{
		System.out.println("Ejecucion timbrarXML: \n\n");
		
		String xml_tmp;
		String xml = null;
		
		try {
			//LEO EL XML Y LO CONVIERTO A CADENA
			xml_tmp = generar_Cadena(ruta_xml);
			//CONVIERTO EL XML A BASE64 PARA ENVIARL AL WS MULTIFACTURAS
			xml=encode64(xml_tmp);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//SOLICITUD QUE SE ENVIARA AL WS MULTIFACTURAS
		strRequest="<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:urn=\"urn:wservicewsdl\">"
				    	+"<soapenv:Header/>"
				    		+"<soapenv:Body>"
                        		+"<urn:timbrar64 soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">"
                        			+"<rfc xsi:type=\"xsd:string\">"+rfc+"</rfc>"
                        			+"<clave xsi:type=\"xsd:string\">"+clave+"</clave>"
                        			+"<xml xsi:type=\"xsd:string\">"+xml+"</xml>"
                        			+"<produccion xsi:type=\"xsd:string\">"+produccion+"</produccion>"
                        		+"</urn:timbrar64>"
                        	+"</soapenv:Body>"
                     +"</soapenv:Envelope>";
		//IMPRIMIR SOLICITUD ENVIADA
		//System.out.println(strRequest);		   

		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost postRequest = new HttpPost(url_ws);
		StringEntity input = null;
		
		try {
			input = new StringEntity(strRequest);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		input.setContentType("text/xml");
		//input.setContentEncoding("ISO-8859-1");
		
		postRequest.setEntity(input);
		
		//TRATAR LA RESPUESTA DEL WS
		HttpResponse response = null;
		
		try {
			response = httpClient.execute(postRequest);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (response.getStatusLine().getStatusCode() != 200) {
			throw new RuntimeException("Error : C�digo de error HTTP : " +response.getStatusLine().getStatusCode());
		}else{
			
			entity = response.getEntity();
			if (entity != null) {
				InputStream instream;
                try {
					instream = entity.getContent();
					strResponse = convertStreamToString(instream);
	                //System.out.println(strResponse);

					//CIERRA EL INPUT STREAM PARA LIBERAR LA CONEXION PARA CONVERTIR LA RESPUESTA A STRING
	                instream.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			//OBTENER LA INFORMACION DE LA RESPUESTA DEL WS
			String xml_respuesta = strResponse;

			InputSource source = new InputSource(new StringReader(xml_respuesta));

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db;
			try {
				db = dbf.newDocumentBuilder();
				Document document = db.parse(source);
				
				XPathFactory xpathFactory = XPathFactory.newInstance();
				XPath xpath = xpathFactory.newXPath();

				String cfdi64 = xpath.evaluate("//cfdi", document);
				String png  = xpath.evaluate("//png", document);
				String idpac  = xpath.evaluate("//idpac", document);
				String produccion_  = xpath.evaluate("//produccion", document);
				String codigo_mf_numero  = xpath.evaluate("//codigo_mf_numero", document);
				String codigo_mf_texto  = xpath.evaluate("//codigo_mf_texto", document);
				String mensaje_original_pac_json  = xpath.evaluate("//mensaje_original_pac_json", document);
				String cancelada  = xpath.evaluate("//cancelada", document);
				String saldo  = xpath.evaluate("//saldo", document);
				String uuid  = xpath.evaluate("//uuid", document);
				String servidor  = xpath.evaluate("//servidor", document);
				String ejecucion  = xpath.evaluate("//ejecucion", document);
				
				//convertir cfdi (base64) a xml normal
				String cfdi=decode64(cfdi64);
				
				respuestaMultifacturas= new HashMap<String, String>();
				
				respuestaMultifacturas.put("cfdi64",cfdi64);
				respuestaMultifacturas.put("cfdi",cfdi);
				respuestaMultifacturas.put("png",png);
				respuestaMultifacturas.put("idpac",idpac);
				respuestaMultifacturas.put("produccion",produccion_);
				respuestaMultifacturas.put("codigo_mf_numero",codigo_mf_numero);
				respuestaMultifacturas.put("codigo_mf_texto",codigo_mf_texto);
				respuestaMultifacturas.put("mensaje_original_pac_json",mensaje_original_pac_json);
				respuestaMultifacturas.put("cancelada",cancelada);
				respuestaMultifacturas.put("saldo",saldo);
				respuestaMultifacturas.put("uuid",uuid);
				respuestaMultifacturas.put("servidor",servidor);
				respuestaMultifacturas.put("ejecucion",ejecucion);
				
				/*
				System.out.println("cfdi=" + cfdi);
				System.out.println("xml_cfdi=" + xml_cfdi);
				System.out.println("png=" + png);
				System.out.println("idpac=" + idpac);
				System.out.println("produccion=" + produccion);
				System.out.println("codigo_mf_numero=" + codigo_mf_numero);
				System.out.println("codigo_mf_texto=" + codigo_mf_texto);
				System.out.println("mensaje_original_pac_json=" + mensaje_original_pac_json);
				System.out.println("cancelada=" + cancelada);
				System.out.println("saldo=" + saldo);
				System.out.println("uuid=" + uuid);
				System.out.println("servidor=" + servidor);
				System.out.println("ejecucion=" + ejecucion);
				*/
			} catch (Throwable e) {
				e.printStackTrace();
			}
		
			
		}
		
		//CIERRA LA CONEXION CON EL WS
		if (httpClient != null) httpClient.getConnectionManager().shutdown();
		
		return respuestaMultifacturas;
	}
	
	/*********************    CANCELAR XML ***************************************************************
	/* cancalarXml(String url_webservice,String rfc,String clave,String uuid,String ruta_cer,String ruta_key,String contrase�a_csd);
	 * PARAMETROS:
	 * url_webservice : URL DEL WS MULTIFACTURAS
	 * rfc: RFC DEL CLIENTE QUE TIMBRARA EL XML
	 * clave: CLAVE DEL RFC EN EL PANEL DE MULTIFACTURAS
	 * uuid: UUID DE LA FACTURA A CANCELAR
	 * ruta_cer: RUTA DONDE ESTA EL ARCHIVO.cer DEL RFC DEL CLIENTE
	 * ruta_key: RUTA DONDE ESTA EL ARCHIVO.key DEL RFC DEL CLIENTE
	 * contrase�a_csd: CONTRASE�A DE LOS ARCHIVOS CSD
	 * 
	 * RESPUESTA: HashMap <String,String> CON LA RESPUESTA DE MULTIFACTURAS
	 * ELEMENTOS HASHMAP:
	 * pac: NUMERO DE PAC
	 * idpac: PAC CON EL QUE SE TIMBRO EL XML
	 * produccion: SI FUE TIMBRADO EN MODO DE PRUEBAS O TIMBRADO REAL
	 * codigo_mf_numero: NUMERO DE CODIGO DE RESPUESTA MULTIFACTURAS
	 * codigo_mf_texto: TEXTO DE CODIGO DE RESPUESTA MULTIFACTURAS
	 * mensaje_original_pac_json: RESPUESTA DEL PAC EN JSON
	 * ejecucion: TIEMPO DE EJECUCION DEL TIMBRADO
	 */
	public HashMap<String, String> cancalarXml(String url_ws,String rfc,String clave, String uuid, String ruta_cer,String ruta_key, String contrasena_csd)
	{
		//CONVERTIR CER, KEY, CONTRASE�A_CSD A BASE64
		String cadena_cer=null;
		String cadena_key=null;
		String cadena_contrasena=null;
		try {
			//LEER EL CONTENIDO DE LOS ARCHIVOS
			String cadena_cer_tmp=generar_Cadena(ruta_cer);
			String cadena_key_tmp=generar_Cadena(ruta_key);
			//CONVERTIRLOS A BASE64
			cadena_cer=encode64(cadena_cer_tmp);
			cadena_key=encode64(cadena_key_tmp);
			cadena_contrasena=encode64(contrasena_csd);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//SOLICITUD ENVIADA AL WEB SERVICE
		strRequest="<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:urn=\"urn:wservicewsdl\">"
			    	+"<soapenv:Header/>"
			    		+"<soapenv:Body>"
			    		+"<urn:cancelar soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">"
					         +"<rfc xsi:type=\"xsd:string\">"+rfc+"</rfc>"
					         +"<clave xsi:type=\"xsd:string\">"+clave+"</clave>"
					         +"<uuid xsi:type=\"xsd:string\">"+uuid+"</uuid>"
					         +"<cer xsi:type=\"xsd:string\">"+cadena_cer+"</cer>"
					         +"<key xsi:type=\"xsd:string\">"+cadena_key+"</key>"
					         +"<pass_cer xsi:type=\"xsd:string\">"+cadena_contrasena+"</pass_cer>"
			         	+"</urn:cancelar>"
			      	+"</soapenv:Body>"
	             +"</soapenv:Envelope>";
		//System.out.println(strRequest);		   //IMPRIMIR SOLICITUD ENVIADA
		
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost postRequest = new HttpPost(url_ws);
		StringEntity input = null;
		
		try {
			input = new StringEntity(strRequest);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		input.setContentType("text/xml");
		//input.setContentEncoding("ISO-8859-1");
		
		postRequest.setEntity(input);
		
		//TRATAR LA RESPUESTA DEL WS
		HttpResponse response = null;
		
		try {
			response = httpClient.execute(postRequest);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (response.getStatusLine().getStatusCode() != 200) {
			throw new RuntimeException("Error : C�digo de error HTTP : " +response.getStatusLine().getStatusCode());
		}else{
			
			entity = response.getEntity();
			if (entity != null) {
				InputStream instream;
                try {
					instream = entity.getContent();
					strResponse = convertStreamToString(instream);
	                //System.out.println(strResponse);

					//CIERRA EL INPUT STREAM PARA LIBERAR LA CONEXION PARA CONVERTIR LA RESPUESTA A STRING
	                instream.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			//OBTENER LA INFORMACION DE LA RESPUESTA DEL WS
			String xml_respuesta = strResponse;

			InputSource source = new InputSource(new StringReader(xml_respuesta));

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db;
			try {
				db = dbf.newDocumentBuilder();
				Document document = db.parse(source);
				
				XPathFactory xpathFactory = XPathFactory.newInstance();
				XPath xpath = xpathFactory.newXPath();

				String idpac  = xpath.evaluate("//idpac", document);
				String pac  = xpath.evaluate("//pac", document);
				String produccion  = xpath.evaluate("//produccion", document);
				String codigo_mf_numero  = xpath.evaluate("//codigo_mf_numero", document);
				String codigo_mf_texto  = xpath.evaluate("//codigo_mf_texto", document);
				String mensaje_original_pac_json  = xpath.evaluate("//mensaje_original_pac_json", document);
				String ejecucion  = xpath.evaluate("//ejecucion", document);
				
				respuestaMultifacturas= new HashMap<String, String>();
				respuestaMultifacturas.put("idpac",idpac);
				respuestaMultifacturas.put("pac",pac);
				respuestaMultifacturas.put("produccion",produccion);
				respuestaMultifacturas.put("codigo_mf_numero",codigo_mf_numero);
				respuestaMultifacturas.put("codigo_mf_texto",codigo_mf_texto);
				respuestaMultifacturas.put("mensaje_original_pac_json",mensaje_original_pac_json);
				respuestaMultifacturas.put("ejecucion",ejecucion);
				/*
				System.out.println("idpac=" + idpac);
				System.out.println("pac=" + pac);
				System.out.println("produccion=" + produccion);
				System.out.println("codigo_mf_numero=" + codigo_mf_numero);
				System.out.println("codigo_mf_texto=" + codigo_mf_texto);
				System.out.println("mensaje_original_pac_json=" + mensaje_original_pac_json);
				System.out.println("ejecucion=" + ejecucion);
				*/
			} catch (Throwable e) {
				e.printStackTrace();
			}
		
			
		}
		
		//CIERRA LA CONEXION CON EL WS
		if (httpClient != null) httpClient.getConnectionManager().shutdown();
		
		return respuestaMultifacturas;
		
	}
	
	 /******************       CONSULTAR SALDO    ****************************************************
	/* saldo(String url_webservice, String rfc, String clave);
	 * PARAMETROS:
	 * url_webservice : URL DEL WS MULTIFACTURAS
	 * rfc: RFC DEL CLIENTE QUE TIMBRARA EL XML
	 * clave: CLAVE DEL RFC EN EL PANEL DE MULTIFACTURAS
	 * 
	 * RESPUESTA: HashMap <String,String> CON LA RESPUESTA DE MULTIFACTURAS
	 * ELEMENTOS HASHMAP:
	 * codigo_mf_numero: NUMERO DE CODIGO DE RESPUESTA MULTIFACTURAS
	 * codigo_mf_texto: TEXTO DE CODIGO DE RESPUESTA MULTIFACTURAS
	 * saldo: SALDO DISPONIBLE DEL RFC
	 */
	public HashMap<String, String> saldo(String url_ws,String rfc, String clave)
	{
		
		//SOLICITUD ENVIADA AL WEB SERVICE
		strRequest="<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:urn=\"urn:wservicewsdl\">"
			    	+"<soapenv:Header/>"
			    		+"<soapenv:Body>"
			    			+"<urn:saldo soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">"
		         				+"<rfc xsi:type=\"xsd:string\">"+rfc+"</rfc>"
		         				+"<clave xsi:type= \"xsd:string\">"+clave+"</clave>"
		         			+"</urn:saldo>"
	                	+"</soapenv:Body>"
	             +"</soapenv:Envelope>";
		//System.out.println(strRequest);		   //IMPRIMIR SOLICITUD ENVIADA
		
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost postRequest = new HttpPost(url_ws);
		StringEntity input = null;
		
		try {
			input = new StringEntity(strRequest);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		input.setContentType("text/xml");
		input.setContentEncoding("ISO-8859-1");
		
		postRequest.setEntity(input);
		// Tratar respuesta del servidor
		HttpResponse response = null;
		
		try {
			response = httpClient.execute(postRequest);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (response.getStatusLine().getStatusCode() != 200) {
			throw new RuntimeException("Error >: C�digo de error HTTP : " +response.getStatusLine().getStatusCode());
		}else{
			
			HttpEntity entity = response.getEntity();
			if (entity != null) {

                InputStream instream;
                try {
					instream = entity.getContent();
					strResponse = convertStreamToString(instream);
	                //System.out.println(strResponse);

	                // Closing the input stream will trigger connection release
	                instream.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			//OBTENER LA INFORMACION DE LA RESPUESTA DEL WS
			String xml = strResponse;

			InputSource source = new InputSource(new StringReader(xml));

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db;
			try {
				db = dbf.newDocumentBuilder();
				Document document = db.parse(source);
				
				XPathFactory xpathFactory = XPathFactory.newInstance();
				XPath xpath = xpathFactory.newXPath();

				String codigo_mf_numero  = xpath.evaluate("//codigo_mf_numero", document);
				String codigo_mf_texto  = xpath.evaluate("//codigo_mf_texto", document);
				String saldo  = xpath.evaluate("//saldo", document);
				/*
				System.out.println("codigo_mf_numero=" + codigo_mf_numero);
				System.out.println("codigo_mf_texto=" + codigo_mf_texto);
				System.out.println("saldo=" + saldo);
				*/
				respuestaMultifacturas= new HashMap<String, String>();
				
				respuestaMultifacturas.put("codigo_mf_numero",codigo_mf_numero);
				respuestaMultifacturas.put("codigo_mf_texto",codigo_mf_texto);
				respuestaMultifacturas.put("saldo",saldo);
				
			} catch (Throwable e) {
				e.printStackTrace();
			}
			
			//CIERRA LA CONEXION CON EL WS
			if (httpClient != null) httpClient.getConnectionManager().shutdown();
			
			return respuestaMultifacturas;
		}
	}
	
	//CONVIERTE EL OBJETO DE RESPUESTA EN STRING
	//CONVIERTE LA RESPUESTA DEL WS A UNA CADENA PARA PODER TRABAJAR CON ELLA
	private static String convertStreamToString(InputStream is) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
			while ((line = reader.readLine()) != null) {
			    sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
        try {
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return sb.toString();
     
    }
	
	//LEE LA RUTA DEL XML PARA CONVERTIRLO EN STRING
	//LEE LOS ARCHIVOS XML, CER Y KEY PARA CONVERTIRLOS EN STRING Y TRABAJAR CON ELLOS
	public static String generar_Cadena(String archivo) throws FileNotFoundException, IOException {
        String cadena = "";
        String linea = "";
        FileReader f = new FileReader(archivo);
        BufferedReader b = new BufferedReader(f);
        while((linea= b.readLine())!=null) {
            //System.out.println(linea);
            cadena+=linea;
        }
        b.close();
        
        return cadena;
    }
	
}
