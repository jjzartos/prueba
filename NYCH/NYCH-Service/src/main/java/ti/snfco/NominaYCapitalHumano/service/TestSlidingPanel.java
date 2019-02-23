/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ti.snfco.NominaYCapitalHumano.service;

import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author root
 */
    public class TestSlidingPanel extends JPanel {

    public TestSlidingPanel() {
    	System.out.println("aqui");
        SlidePaneFactory factory = SlidePaneFactory.getInstance();
        BookForm bookForm = new BookForm();
        factory.add(bookForm,"Liquidación",new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("loan.png"))).getImage(), true);

        bookForm = new BookForm();
        factory.add(bookForm,"Calculo ISR",new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("loaan.png"))).getImage());

        bookForm = new BookForm();
        factory.add(bookForm,"Impuesto",new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("loan.png"))).getImage(), false);

        add(factory);

    }

    public static void main(String s[]) {
        JFrame frame = new JFrame();
        frame.setSize(330, 750);

        //frame.setLayout(new FlowLayout());
        JScrollPane pane = new JScrollPane();
        pane.setViewportView(new TestSlidingPanel());
        frame.add(pane);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
