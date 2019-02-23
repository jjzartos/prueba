package ti.snfco.NominaYCapitalHumano.service;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import javax.swing.JButton;
import javax.swing.JFrame;

public class BotonRedondo extends JButton{

	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Constructor del Botón Redondo
	  public BotonRedondo( String rotulo ) {
	    super( rotulo );

	    // Igualamos las dimensiones para que el botón sea un círculo en vez de
	    // una elipse
	    Dimension tamano = getPreferredSize();
	    tamano.width = tamano.height = Math.max( tamano.width,tamano.height );
	    setPreferredSize( tamano );

	    // Hacemos que el JButton no pinte su fondo, de este modo podremos
	    // nosotros hacer que el color de fondo que se salga de la figura sea
	    // del mismo color que el fondo de la ventana
	    setContentAreaFilled( false );
	    }

	  // Este es el método que pinta el botón en el color correspondiente al estado
	  // en que se encuentre, y también coloca el rótulo que se haya indicado en el
	  // centro del botón
	  protected void paintComponent( Graphics g ) {
	    if( getModel().isArmed() ) {
	      // Se puede hacer que la característica de Pulsado sea una propiedad de
	      // esta clase
	      g.setColor( Color.lightGray );
	      } 
	    else {
	      g.setColor( getBackground() );
	      }
	    g.fillOval( 0,0,getSize().width-1,getSize().height-1 );

	    // Llamando al método de la clase padre, haremos que aparezca el rótulo y
	    // hacemos que el restángulo correspondiente al botón sea el que controla
	    // el foco
	    super.paintComponent( g );
	    }

	  // Pintamos el borde del botón con una línea simple
	  protected void paintBorder( Graphics g ) {
	    g.setColor( getForeground() );
	    g.drawOval( 0,0,getSize().width-1,getSize().height-1 );
	    }

	  // Este es el método que controla la posición del ratón en el momento de
	  // pulsar su botón. Se sobreescribe para controlar los cambios de tamaño
	  // del botón
	  Shape figura;
	  public boolean contains( int x,int y ) {
	    // En caso de que el botón cambie de tamaño, hay que conseguir una nueva
	    // figura que se adapte a ese nuevo tamaño
	    if( figura == null || !figura.getBounds().equals(getBounds()) ) {
	      figura = new Ellipse2D.Float( 0,0,getWidth(),getHeight() );
	      }
	    return( figura.contains( x,y ) );
	    }

}
