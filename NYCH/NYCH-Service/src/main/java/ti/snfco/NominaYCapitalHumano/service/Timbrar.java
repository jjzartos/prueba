package ti.snfco.NominaYCapitalHumano.service;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Timbrar {

	public void exec(){

		/*//este snnipet buscar los archivos desde una ruta que le de como paraemtro y busca el archivo con las palabras que les des en la variable "txt"
		  String dir = "C:\\Users\\DeveloperTI\\Documents\\GitHub\\Nomina\\NYCH\\NYCH-Service\\src\\main\\java\\ti\\snfco\\NominaYCapitalHumano\\service\\prueba.bat";
		    String txt = "";

		    try {

		        final Pattern pattern = Pattern.compile("\\A(?=.*" +  txt.replace(";", ")(?=.*") + ").*\\z", 
		                Pattern.CASE_INSENSITIVE | Pattern.DOTALL);

		        Files.walkFileTree(Paths.get(dir), new SimpleFileVisitor<Path>() {

		            @Override
		            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
		                    throws IOException {
		                String str = new String(Files.readAllBytes(file), StandardCharsets.UTF_8);
		                if (pattern.matcher(str).matches()) {
		                    System.out.println("ruta: "+file);
		                    InternalFrameTimbrado.execute();
		                }
		                return FileVisitResult.CONTINUE;
		            }
		        });
		    } catch (IOException e) {
		        //LOG.log(Level.SEVERE, "IO Error", e);
		    	e.printStackTrace();
		    }
		 */

		//se manda ejecutar el .bat del timbrado, con el archivo a timbrar.
		InternalFrameTimbrado.execute("C:\\SDK2\\timbrar32.bat C:\\SDK2\\ejemplos\\cfdi33\\timbre_nomina12.ini");
	}

	/*public static void main(String[] args) throws FileNotFoundException {    
	    File file=new File("C:\\Users\\DeveloperTI\\Desktop\\prueba.bat");
	    System.out.println(file.exists());
	    Scanner scan=new Scanner(file);
	    System.out.println(scan.nextLine());

	}
	public static void main(String[] args) throws IOException {
		muestraContenido("/home/mario/archivo.txt");
	}*/

	public String  muestraContenido(String archivo){

		String cadena = "";
				String temp="";
		try {
			FileReader f = new FileReader(archivo);
			BufferedReader b = new BufferedReader(f);
			while((cadena = b.readLine())!=null) {
				//System.out.println(cadena);

				temp = temp + cadena;
				
			}
			cadena = temp;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return temp;
	}




	public String leerTxt(String direccion){
		String texto = "";
		try {
			BufferedReader bf = new BufferedReader(new FileReader(direccion));
			String temp="";
			String bfRead;
			while((bfRead = bf.readLine()) != null) {
				temp = temp + bfRead;
			}
			texto = temp;
			System.out.println("texto: "+texto);
		}catch(Exception e) {
			e.printStackTrace();
		}

		return texto;
	}


	public void abrirArchivo(String archivo) {
		try {
			File objFile = new File(archivo);
			Desktop.getDesktop().open(objFile);
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}

}
