package ti.snfco.NominaYCapitalHumano.service;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class MyIcon implements Icon{

	public void paintIcon(Component c, Graphics g, int x, int y) {
		 Image image = new ImageIcon(getClass().getResource("/add.png")).getImage();
	        g.drawImage(image, x, y, c);
	        
	}

	public int getIconWidth() {
		return 0;
	}

	public int getIconHeight() {
		return 0;
	}

}
