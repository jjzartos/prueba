package ti.snfco.NominaYCapitalHumano.service;


import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.TextUI;

import org.pushingpixels.flamingo.api.common.JCommandButton;
import org.pushingpixels.flamingo.api.common.icon.ImageWrapperResizableIcon;
import org.pushingpixels.flamingo.api.common.icon.ResizableIcon;
import org.pushingpixels.flamingo.api.ribbon.JRibbonBand;
import org.pushingpixels.flamingo.api.ribbon.JRibbonFrame;
import org.pushingpixels.flamingo.api.ribbon.RibbonApplicationMenu;
import org.pushingpixels.flamingo.api.ribbon.RibbonElementPriority;
import org.pushingpixels.flamingo.api.ribbon.RibbonTask;
import org.pushingpixels.flamingo.api.ribbon.resize.CoreRibbonResizePolicies;
import org.pushingpixels.flamingo.api.ribbon.resize.IconRibbonBandResizePolicy;
import org.pushingpixels.substance.api.skin.SubstanceOfficeBlack2007LookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceOfficeBlue2007LookAndFeel;

import java.awt.Color;
import javax.swing.JButton;

public class FormularioPrincipal1 extends JRibbonFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static ResizableIcon getResizableIconFromResource(String resource) {
		return ImageWrapperResizableIcon.getIcon(FormularioPrincipal1.class
				.getClassLoader().getResource(resource), new Dimension(48, 48));
	}


	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//      UIManager.setLookAndFeel("org.pushingpixels.substance.api.skin.SubstanceOfficeBlue2007LookAndFeel");
//	
//   }
//   catch (Exception ex) {
//       Logger.getLogger(TextUI.class.getName()).log(Level.SEVERE, null, ex);
//   }
//		SwingUtilities.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					FormularioPrincipal1 frame = new FormularioPrincipal1();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//
//	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("unchecked")
	public FormularioPrincipal1() {
		getContentPane().setBackground(Color.GRAY);
		//setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("img/sfrpresidencia.png")));
		setTitle("Nomina Municipal y Capital Humano");
		setBounds(500, 100, 601, 335);
		setCursor(Cursor.HAND_CURSOR);
		//setExtendedState(MAXIMIZED_BOTH);

		JRibbonBand band1 = new JRibbonBand("",null);
		//JRibbonBand band2 = new JRibbonBand("world!", null);

		JCommandButton button1 = new JCommandButton("Nuevo Periodo",getResizableIconFromResource("48px-Crystal_Clear_app_kthememgr.png"));
//		JCommandButton button2 = new JCommandButton("Circle",getResizableIconFromResource("48px-Crystal_Clear_app_ksame.png"));
//		JCommandButton button3 = new JCommandButton("Triangle",getResizableIconFromResource("48px-Crystal_Clear_app_error.png"));
//		JCommandButton button4 = new JCommandButton("Star",getResizableIconFromResource("48px-Crystal_Clear_action_bookmark.png"));

		band1.addCommandButton(button1, RibbonElementPriority.TOP);
//		band1.addCommandButton(button2, RibbonElementPriority.MEDIUM);
//		band1.addCommandButton(button3, RibbonElementPriority.MEDIUM);
//		band1.addCommandButton(button4, RibbonElementPriority.MEDIUM);

		band1.setResizePolicies((List) Arrays.asList(
				new CoreRibbonResizePolicies.None(band1.getControlPanel()),
//				new CoreRibbonResizePolicies.Mirror(band1.getControlPanel()),
//				new CoreRibbonResizePolicies.Mid2Low(band1.getControlPanel()),
//				new CoreRibbonResizePolicies.High2Low(band1.getControlPanel()),
				new IconRibbonBandResizePolicy(band1.getControlPanel())));
		//band2.setResizePolicies((List) Arrays.asList(new IconRibbonBandResizePolicy(band2.getControlPanel())));

		RibbonTask task1 = new RibbonTask("Periodo", band1);
		//RibbonTask task2 = new RibbonTask("Two", band2);

		getRibbon().addTask(task1);
		//getRibbon().addTask(task2);

		getRibbon().setApplicationMenu(new RibbonApplicationMenu());

	}

}
