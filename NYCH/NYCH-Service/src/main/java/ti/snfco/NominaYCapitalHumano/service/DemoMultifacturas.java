package ti.snfco.NominaYCapitalHumano.service;

import java.util.HashMap;
import java.util.Random;

public class DemoMultifacturas {

public static void main(String[] args) {
		
		
		Random rn = new Random();
		int pac = rn.nextInt(10) + 1;
		System.out.println("SERVIDOR MULTIFACTURAS:"+ pac);
		
		//TOMA UN SERVIDOR MULTIFACTURAS AL AZAR
		//CONTAMON CON 10 SERVIDORES PARA BALANCEAR LA CARGA DE TRABAJO Y ASEGURAR DE TIMBRADO DE NUESTROS CLIENTES
		String url_webservice ="http://pac"+pac+".multifacturas.com/pac/index.php?wsdl";  
		System.out.println("url servidor multifacturas:"+ url_webservice+"\n\n");
		
		//RFC DE PRUEBA 
		String rfc="DEMO700101XXX";
		String clave="DEMO700101XXX";
		//RUTA DEL XML SELLADO 
		String ruta_xml="C:\\multifacturas_sdk\\timbrados\\sin_timbrar_ejemplo_factura.xml";
		String produccion="NO";
		
		//CANCELAR UN XML
		String uuid="E353BE75-756D-46B8-82C2-91D8A3F1A7A8";
		String ruta_cer="C:\\multifacturas_sdk\\pruebas\\aaa010101aaa.cer";
		String ruta_key="C:\\multifacturas_sdk\\pruebas\\aaa010101aaa.key";
		String contrasena_csd="12345678a";
		
		//RESPUESTA CLASE MULTIFACTURAS
	    HashMap<String, String> respuesta_timbrado;
	    HashMap<String, String> respuesta_saldo;
	    HashMap<String, String> respuesta_cancelar;
		
	    //OBJETO TimbrarMultifacturas CON 3 METODOS DE EJEMPLO
	    //METODO timbrarXML PARA TIMBRAR EL XML DE UNA FACTURA 
	    //METODO saldo PARA VER EL SALDO DE UN RFC
	    //METODO cancelar PARA CANCELAR UNA FACTURA XML
		TimbrarMultifacturas timbrar = new TimbrarMultifacturas();
		
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
		
		/*
		respuesta_timbrado = timbrar.timbrarXML(url_webservice, rfc, clave, ruta_xml, produccion);
		System.out.println("Demo cfdi64=" + respuesta_timbrado.get("cfdi64"));
		System.out.println("Demo cfdi=" + respuesta_timbrado.get("cfdi"));
		System.out.println("Demo png=" + respuesta_timbrado.get("png"));
		System.out.println("Demo idpac=" + respuesta_timbrado.get("idpac"));
		System.out.println("Demo produccion=" + respuesta_timbrado.get("produccion"));
		System.out.println("Demo codigo_mf_numero=" + respuesta_timbrado.get("codigo_mf_numero"));
		System.out.println("Demo codigo_mf_texto=" + respuesta_timbrado.get("codigo_mf_texto"));
		System.out.println("Demo mensaje_original_pac_json=" + respuesta_timbrado.get("mensaje_original_pac_json"));
		System.out.println("Demo cancelada=" + respuesta_timbrado.get("cancelada"));
		System.out.println("Demo saldo=" + respuesta_timbrado.get("saldo"));
		System.out.println("Demo uuid=" + respuesta_timbrado.get("uuid"));
		System.out.println("Demo servidor=" + respuesta_timbrado.get("servidor"));
		System.out.println("Demo ejecucion=" + respuesta_timbrado.get("ejecucion"));
		*/
		
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
	
		/*
		respuesta_saldo = timbrar.saldo(url_webservice, rfc, clave);
		System.out.println("Demo saldo codigo_mf_numero=" + respuesta_saldo.get("codigo_mf_numero"));
		System.out.println("Demo saldo codigo_mf_texto=" + respuesta_saldo.get("codigo_mf_texto"));
		System.out.println("Demo saldo saldo=" + respuesta_saldo.get("saldo"));
		*/
		
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
		
		
		respuesta_cancelar=timbrar.cancalarXml(url_webservice, rfc, clave, uuid, ruta_cer, ruta_key, contrasena_csd);
		
		System.out.println("Demo cancelar idpac=" + respuesta_cancelar.get("idpac"));
		System.out.println("Demo cancelar pac=" + respuesta_cancelar.get("pac"));
		System.out.println("Demo cancelar produccion=" + respuesta_cancelar.get("produccion"));
		System.out.println("Demo cancelar codigo_mf_numero=" + respuesta_cancelar.get("codigo_mf_numero"));
		System.out.println("Demo cancelar codigo_mf_texto=" + respuesta_cancelar.get("codigo_mf_texto"));
		System.out.println("Demo cancelar mensaje_original_pac_json=" + respuesta_cancelar.get("mensaje_original_pac_json"));
		System.out.println("Demo cancelar ejecucion=" + respuesta_cancelar.get("ejecucion"));
		
	}
	
	

}
