package ti.snfco.NominaYCapitalHumano.service;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.UIManager;
 
public class AbrirArchivoTexto extends JFrame implements ActionListener {
 
    public AbrirArchivoTexto(){
        //Para poder cerrar la ventana
        //setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
 
        //Se agrega un layout
        setLayout( new BorderLayout() );
 
        //Se crea el editor de texto y se agrega a un scroll
        txp = new JTextPane();
        JScrollPane jsp = new JScrollPane();
        jsp.setViewportView( txp );
 
        add( jsp, BorderLayout.CENTER );
 
        //Se crea un boton para abrir el archivo
        JButton btn = new JButton( "Abrir" );
        btn.addActionListener( this );
        //btn.setIcon( new ImageIcon( getClass().getResource( "Abrir.png" ) ) );
 
        add( btn, BorderLayout.NORTH );
 
        //Tamaño de la ventana
        setSize( 500, 500 );
 
        //Esto sirve para centrar la ventana
        setLocationRelativeTo( null );
   
        //Hacemos visible la ventana
        setVisible( true );
    }
 
    //------------------------------Action Performed-------------------------------//
    public void actionPerformed( ActionEvent e ){
       
    	JButton btn = (JButton)e.getSource();
        if( btn.getText().equals( "Abrir" )){
           
        	if( abrirArchivo == null ) {
            
            abrirArchivo = new JFileChooser();
            //Con esto solamente podamos abrir archivos
            abrirArchivo.setFileSelectionMode( JFileChooser.FILES_ONLY);
 
            int seleccion = abrirArchivo.showOpenDialog( this );
 
            if( seleccion == JFileChooser.APPROVE_OPTION ){
                File f = abrirArchivo.getSelectedFile();
                try
                {
                    String nombre = f.getName();
                    String path = f.getAbsolutePath();
                    String contenido = getArchivo( path );
                    //Colocamos en el titulo de la aplicacion el 
                    //nombre del archivo
                    this.setTitle( nombre );
 
                    //En el editor de texto colocamos su contenido
                    txp.setText( contenido );
                    
                    //imprimr(contenido);
                    
 
                }catch( Exception exp){}
            }
          } 
        }
    }
    //-----------------------------------------------------------------------------//
 
    //-------------------------Se obtiene el contenido del Archivo----------------//
    public String getArchivo( String ruta ){
        FileReader fr = null;
        BufferedReader br = null;
        //Cadena de texto donde se guardara el contenido del archivo
        String contenido = "";
        try
        {
            //ruta puede ser de tipo String o tipo File
            fr = new FileReader( ruta );
            br = new BufferedReader( fr );
 
            String linea;
            //Obtenemos el contenido del archivo linea por linea
            while( ( linea = br.readLine() ) != null ){ 
                contenido += linea + "\n";
            }
 
        }catch( Exception e ){  }
        //finally se utiliza para que si todo ocurre correctamente o si ocurre 
        //algun error se cierre el archivo que anteriormente abrimos
        finally
        {
            try{
                br.close();
            }catch( Exception ex ){}
        }
        return contenido;
    }
    //-----------------------------------------------------------------------------//
    
    
    public void AbrirArchivos(String pathFile) throws IOException{
    	try {
    	 
    	Runtime.getRuntime().exec (new String[]{pathFile});
    	 
    	} catch (Exception e) {
    	 
    	System.out.println("Error al Intentar Abrir el archivo");
    	}
    	}
    
	public void imprimr (String textoAImprimir) {


		String texto = textoAImprimir;
		PrintService printService = PrintServiceLookup.lookupDefaultPrintService();
		DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;

		DocPrintJob docPrintJob = printService.createPrintJob();

		Doc doc = new SimpleDoc(texto.getBytes(), flavor, null);

		try {

			docPrintJob.print(doc, null);

		} catch (PrintException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

	}
    
    //-------------------------//
    
 
//    public static void main( String[] arg ){
//        try
//        {
//            //Cambiamos el Look&Feel
//            JFrame.setDefaultLookAndFeelDecorated( true );
//            UIManager.setLookAndFeel( new com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel() );
//        }catch( Exception e ){}
//        new AbrirArchivoTexto(String algo);
//    }
 
    JTextPane txp;
    JFileChooser abrirArchivo;
}