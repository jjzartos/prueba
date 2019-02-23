package ti.snfco.NominaYCapitalHumano.service;

//{$R Prestamo.JFM}
/*********************************************
*     Alfonso Bonillo Sierra                *
*                Curso Java, Web y Applets  *
* Práctica.   Prestamo.java                 *
* Cálculo de la amortización de un préstamo *
* siguiendo una renta fija según el sistema *
* francés.                                  * 
*********************************************/


import java.awt.*;
import java.applet.Applet;
import java.text.NumberFormat;  
import java.lang.String; 
import java.awt.event.*;
import java.applet.*;
import javax.swing.*;

//Class prestamo
public class Prestamo extends Applet
{
	public Prestamo() {
	}
  double c = 0;      // Capital total del préstamo 
  double i = 0;      // interés anual en % 
  double i1 = 0;     //    "      "   en tanto por uno
  double p = 0;      // número de años de amortización
  double cuota = 0;  // cuota a pagar para amortizar el préstamo
                     // en esas condiciones

  int    tipopago = 1;
  int    OTRO = 1;              

 final int MenuBarHeight = 0;
 
 private NumberFormat integerFormatter;  
 boolean isStandalone = false;    


 // Component Declaration
 public Label Label1;
 public Label Label2;
 public Label Label3;
 public Label E_cuantos;
 public Label Label5;
 
 public TextField capital;
 public TextField interes;
 public TextField anios;
 public TextField cuantos;
 public TextField pago;

 public CheckboxGroup CheckboxGrpInFrame;
 public Checkbox RadioButton1;
 public Checkbox RadioButton2;
 public Checkbox RadioButton3;
 public Checkbox RadioButton4;
 
 public Button calcula;        // botón que calcula la cuota
 public Button limpia;         //   " que limpia todo
 public Button ejemplo;        // botón que muestra un ejemplo
                                      // C = 1.000.000 ptas
                                      // i = 8 %
                                      // n = 4 años

 public List lista;
 public Panel Panel1;
 public Label E_msj;
 public Label E_valores;
 // End of Component Declaration

 // Obtiene el valor de un parámetro
 public String getParameter(String key, String def) {
     return isStandalone ? 
               System.getProperty(key, def) :
               (getParameter(key) != null ? 
                       getParameter(key) : def);
 }
 
 // init()
 public void init()
 {
     // Frame Initialization
     setForeground(Color.black);
     setBackground(Color.lightGray);
     setFont(new Font("Dialog",Font.BOLD,12));
     setLayout(null);
     // End of Frame Initialization

     // Component Initialization
     Label1 = new Label("Capital:",Label.LEFT);
     Label1.setFont(new Font("Dialog",Font.BOLD,12));
     Label2 = new Label("Interés:",Label.LEFT);
     Label2.setFont(new Font("Dialog",Font.BOLD,12));
     Label3 = new Label("Nº de años:",Label.LEFT);
     Label3.setFont(new Font("Dialog",Font.BOLD,12));
     E_cuantos = new Label("¿Cuantos pagos al año?",Label.LEFT);
     E_cuantos.setFont(new Font("Dialog",Font.BOLD,10));
     E_cuantos.show(false);
     Label5 = new Label("Cuota:",Label.LEFT);
     Label5.setFont(new Font("Dialog",Font.BOLD,12));
     E_msj = new Label("",Label.CENTER);
     E_msj.setFont(new Font("Dialog",Font.BOLD,12));
     E_valores = new Label("",Label.CENTER);
     E_valores.setFont(new Font("Dialog",Font.BOLD,10));
             
     capital = new TextField("");
     capital.setForeground(Color.black);
     capital.setBackground(Color.white);
     capital.setFont(new Font("Dialog",Font.BOLD,12));
     interes = new TextField("");
     interes.setForeground(Color.black);
     interes.setBackground(Color.white);
     interes.setFont(new Font("Dialog",Font.BOLD,12));
     anios = new TextField("");
     anios.setForeground(Color.black);
     anios.setBackground(Color.white);
     anios.setFont(new Font("Dialog",Font.BOLD,12));
     cuantos = new TextField("");
     cuantos.setForeground(Color.black);
     cuantos.setBackground(Color.white);
     cuantos.setFont(new Font("Dialog",Font.BOLD,12));
     cuantos.show(false);
     pago = new TextField("");
     pago.setForeground(Color.magenta);
     pago.setBackground(Color.green);
     pago.setFont(new Font("Dialog",Font.BOLD,12));
     pago.enable(false);
     
     CheckboxGrpInFrame = new CheckboxGroup(); // CheckboxGroup in Frame
     RadioButton1 = new Checkbox("Anual",CheckboxGrpInFrame,true);
     RadioButton1.setBackground(Color.lightGray);
     RadioButton1.setFont(new Font("Dialog",Font.BOLD,12));
     RadioButton2 = new Checkbox("Semestral",CheckboxGrpInFrame,false);
     RadioButton2.setBackground(Color.lightGray);
     RadioButton2.setFont(new Font("Dialog",Font.BOLD,12));
     RadioButton3 = new Checkbox("Mensual",CheckboxGrpInFrame,false);
     RadioButton3.setBackground(Color.lightGray);
     RadioButton3.setFont(new Font("Dialog",Font.BOLD,12));
     RadioButton4 = new Checkbox("Personal",CheckboxGrpInFrame,false);
     RadioButton4.setBackground(Color.lightGray);
     RadioButton4.setFont(new Font("Dialog",Font.BOLD,12));
     
     calcula = new Button("Calcular");
     calcula.setFont(new Font("Dialog",Font.BOLD,12));
//     calcula.setToolTipText("Calcula la cuota a pagar del préstamo.");
     limpia = new Button("Limpiar");
     limpia.setFont(new Font("Dialog",Font.BOLD,12));
//     limpia.setToolTipText("Limpia toda la pantalla.")
     ejemplo = new Button("Ejemplo");
     ejemplo.setFont(new Font("Dialog",Font.BOLD,10));
//     ejemplo.setToolTipText("Demostración para valores preestablecidos.");
     
     lista = new List();
     lista.setForeground(Color.black);
     lista.setBackground(Color.white);
     lista.setFont(new Font("Dialog",Font.BOLD,12));
    
     Panel1 = new Panel();
     Panel1.setLayout(null);
     Panel1.setForeground(Color.red);
     Panel1.setBackground(Color.lightGray);
     Panel1.setFont(new Font("Dialog",Font.BOLD,12));
     
     tipopago=1;
     // End of Component Initialization

     // Add()s
     add(E_valores);
     Panel1.add(E_msj);
     add(Panel1);
     add(pago);
     add(Label5);
     add(lista);
     add(ejemplo);
     add(limpia);
     add(calcula);
     add(cuantos);
     add(E_cuantos);
     add(RadioButton4);
     add(RadioButton3);
     add(RadioButton2);
     add(RadioButton1);
     add(anios);
     add(interes);
     add(capital);
     add(Label3);
     add(Label2);
     add(Label1);
     // End of Add()s

     InitialPositionSet();
 } // End of init()

 // start()
 public void start()
 {
 } // End of start()

 // stop()
 public void stop()
 {
 } // End of stop()

 // destroy()
 public void destroy()
 {
 } // End of destroy()

 public void paint(Graphics g)
 {
     // paint()
     // End of paint()
 }

 public void InitialPositionSet()
 {
     // InitialPositionSet()
     resize(483,399);
     Label1.reshape(106,19+MenuBarHeight,80,19);
     Label2.reshape(104,45+MenuBarHeight,74,19);
     Label3.reshape(74,70+MenuBarHeight,94,19);
     capital.reshape(172,14+MenuBarHeight,121,27);
     interes.reshape(172,42+MenuBarHeight,121,27);
     anios.reshape(173,70+MenuBarHeight,121,27);
     RadioButton1.reshape(319,19+MenuBarHeight,113,17);
     RadioButton2.reshape(318,39+MenuBarHeight,113,17);
     RadioButton3.reshape(318,58+MenuBarHeight,113,17);
     RadioButton4.reshape(317,78+MenuBarHeight,113,17);
     E_cuantos.reshape(318,92+MenuBarHeight,166,19);
     cuantos.reshape(434,105+MenuBarHeight,38,27);
     calcula.reshape(169,170+MenuBarHeight,75,25);
     limpia.reshape(254,169+MenuBarHeight,75,25);
     ejemplo.reshape(4,9+MenuBarHeight,75,25);
     lista.reshape(4,237+MenuBarHeight,465,110);
     Label5.reshape(110,213+MenuBarHeight,51,19);
     pago.reshape(168,208+MenuBarHeight,139,27);
     Panel1.reshape(4,350+MenuBarHeight,466,33);
     E_msj.reshape(0,6,457,19);
     E_valores.reshape(9,141+MenuBarHeight,456,19);
     // End of InitialPositionSet()
 }

 public boolean handleEvent(Event evt)
 {
     // handleEvent()
     if (evt.id == Event.WINDOW_DESTROY && evt.target == this) prestamo_WindowDestroy(evt.target);
     else if (evt.id == Event.ACTION_EVENT && evt.target == ejemplo) ejemplo_Action(evt.target);
     else if (evt.id == Event.ACTION_EVENT && evt.target == calcula) calcula_Action(evt.target);
     else if (evt.id == Event.ACTION_EVENT && evt.target == limpia) limpia_Action(evt.target);
     else if (evt.id == Event.ACTION_EVENT && evt.target == RadioButton1) RadioButton1_Action(evt.target);
     else if (evt.id == Event.ACTION_EVENT && evt.target == RadioButton2) RadioButton2_Action(evt.target);
     else if (evt.id == Event.ACTION_EVENT && evt.target == RadioButton3) RadioButton3_Action(evt.target);
     else if (evt.id == Event.ACTION_EVENT && evt.target == RadioButton4) RadioButton4_Action(evt.target);
     // End of handleEvent()

     return super.handleEvent(evt);
 }

 // Event Handling Routines
 public void prestamo_WindowDestroy(Object target)
 {
     System.exit(0);
 }

 public void ejemplo_Action(Object target)
 {
 capital.setText("1000000");
 interes.setText("8");
 anios.setText("4");
 }

 public void calcula_Action(Object target)
 {
 boolean correcto = true;
  double  E        = 0;
  double  inte     = 0;
  double  ani      = 0;
  double  ta, ca, ci, ra, tci;
  int     indice;
  
  double  exp=0.0;

  String n = capital.getText();
     try {
         c = Integer.parseInt(n);
     } catch(NumberFormatException nfe) {
         E_msj.setText("No es un valor correcto: '"+n+"'");
         correcto = false;
     } 
    n = interes.getText();
     try {
         i = Integer.parseInt(n);
     } catch(NumberFormatException nfe) {
         E_msj.setText("No es un valor correcto: '"+n+"'");
         correcto = false;
     } 
    n = anios.getText();
     try {
         p = Integer.parseInt(n);
     } catch(NumberFormatException nfe) {
         E_msj.setText("No es un valor correcto: '"+n+"'");
         correcto = false;
     } 

   if (tipopago == 4)  
   { 
     n = cuantos.getText();
     try {
         OTRO = Integer.parseInt(n);
     } catch(NumberFormatException nfe) {
         E_msj.setText("No es un valor correcto: '"+n+"'");
         correcto = false;
     }
   }  
   
   if (correcto == true)
   { 
     E_msj.setText("");
     E_valores.setText("Amortización de "+c+", a un interés del "+i+"%, en "+p+" años.");
     
     lista.addItem(" Tabla que detalla la evolución del préstamo para la ...");
     lista.addItem(" Amortización de "+c+", a un interés anual del "+i+"%, en "+p+" años.");

     i1=i/100;
     //inte=i1;
     
     switch(tipopago)
       {
         case 1:
          {// Cálculo de la cuota anual. 
           // Tipo de amortización anual
           lista.addItem("Cuotas Anuales");
           p = p;
           inte = i1;
           E = 1;
           for (indice=0;indice < p;indice++)
             E = E * (1+inte);
        } break;
        
        case 2:
          {// Cálculo de la cuota semestral. 
           // Tipo de amortización semestral
           lista.addItem("Cuotas Semestrales");
           p = p*2;
           //inte = (1+i1)^(1/2)-1;
           exp = 0.5;
           inte = Math.pow((1+i1),exp)-1;
           E = 1;
           for (indice=0;indice < p;indice++)
             E = E * (1+inte);
        } break;
        
        case 3:
          {// Cálculo de la cuota mensual.
           // Tipo de amortización mensual
           lista.addItem("Cuotas Mensuales");
           p = p*12;
           //inte = (1+i1)^(1/12)-1;
           exp = 0.083;
           inte = Math.pow((1+i1),exp)-1;
           E = 1;
           for (indice=0;indice < p;indice++)
             E = E * (1+inte);
        } break;
        
        case 4:
          {// Cálculo de la cuota personal. 
           // Tipo de amortización personal
           p = p*OTRO;
           switch (OTRO)  // ESTO ES NECESARIO PORQUE NO FUNCIONA EXP=1/OTRO.             
           {
             case 1:  exp=1; break;
             case 2:  exp=0.5; break;
             case 3:  exp=0.333; break;
             case 4:  exp=0.25; break;
             case 5:  exp=0.2; break;
             case 6:  exp=0.166; break;              
             case 7:  exp=0.142; break;
             case 8:  exp=0.125; break;
             case 9:  exp=0.111; break;
             case 10: exp=0.1; break;
             case 11: exp=0.09; break;
             case 12: exp=0.083; break;
             case 13: exp=0.076; break;
             case 14: exp=0.071; break;
             case 15: exp=0.066; break;
             case 16: exp=0.0625; break;
             case 17: exp=0.058; break;
             case 18: exp=0.055; break;
             case 19: exp=0.052; break;
             case 20: exp=0.05; break;
             case 21: exp=0.047; break;
             case 22: exp=0.045; break;
             case 23: exp=0.043; break;
             case 24: exp=0.041; break;
             case 48: exp=0.0208; break;
             case 365: exp=0.0027; break;
             default: 
                     {
                     exp=0.1; 
                     cuantos.setText("10");
                     }
                     break;
           }
           //exp = 1 / OTRO;   // esto es lo ideal pero no funciona
           inte = Math.pow((1+i1),exp)-1;
           lista.addItem("     "+OTRO+" Pagos al año, en un total de "+p+" cuotas.");              
           //inte = (1+i1)^(1/OTRO)-1;
           E = 1;
           for (indice=0;indice < p;indice++)
             E = E * (1+inte);
        }
       }
     
     ani = (E - 1)/(inte * E);  
     cuota = c / ani;
     pago.setText(""+presenta(cuota));
     
     // Construcción de la tabla de amortización que detalla
     // el estado del préstamo
     lista.addItem("Pago     Cuota     C.Interés     C.Amortiz.     Tot.Amortiz.     Resto");
     lista.addItem(" 0              ---                ---                  ---                    ---            "+presenta(c)); 
     ta=0;
     ra=c;
     tci=0;
     for (indice=0;indice < p;indice++)
         {
             ci=ra*inte;
             tci=tci+ci;
             ca=cuota-ci;
             ta=ta+ca;
             ra=c-ta;
                 
             lista.addItem(" "+(indice+1)+"      "+presenta(cuota)+"      "+presenta(ci)+"      "+presenta(ca)+"      "+presenta(ta)+"      "+presenta(ra));
         }
      lista.addItem("Total de intereses pagados = "+presenta(tci)); 
      lista.addItem("");
   }  

 }

 public void limpia_Action(Object target)
 {
 // primero limpia las variables
     c  = 0;
     i  = 0;
     i1 = 0;
     p  = 0;
     cuota = 0;
             
     limpia(); // segundo limpia los controles

 }

 public void RadioButton1_Action(Object target)
 {
         tipopago=1;
         cuantos.setVisible(false);
         E_cuantos.setVisible(false);
 }

 public void RadioButton2_Action(Object target)
 {
         tipopago=2;
         cuantos.setVisible(false);
         E_cuantos.setVisible(false);
 }

 public void RadioButton3_Action(Object target)
 {
         tipopago=3;
         cuantos.setVisible(false);
         E_cuantos.setVisible(false);
 }

 public void RadioButton4_Action(Object target)
 {
         tipopago=4;
         // Pide el número de pagos que desea hacer al año
         cuantos.setVisible(true);
         E_cuantos.setVisible(true);
 }

 // limpia los campos, la lista y los mensajes
 void limpia()
 {
     capital.setText("");
     interes.setText("");
     anios.setText("");      
     pago.setText("");
     E_valores.setText("");
     E_msj.setText("");
     lista.clear();	
     
     cuantos.setText("");
     cuantos.setVisible(false);
     E_cuantos.setVisible(false);
}

 // Esta función formatea la salida de los números reales
 // para mostrarlos sólo con dos decimales
   
 double presenta(double numero)
 {
   numero = numero*100;
   numero = (int) numero;
   numero = (numero/100);
   return numero;            
 }

 // End of Event Handling Routines

} // End of Class prestamo