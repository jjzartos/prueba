package ti.snfco.NominaYCapitalHumano.service;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class PruebaCombos {

	private JComboBox combo1;

	private JComboBox combo2;

	public static void main(String[] args) {
		new PruebaCombos();
	}

	public PruebaCombos() {
		JFrame v = new JFrame();
		v.getContentPane().setLayout(new FlowLayout());
		combo1 = new JComboBox();
		rellenaCombo1();
		combo1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				rellenaCombo2((String) combo1.getSelectedItem());
				
			}

		});

		combo2 = new JComboBox();
		rellenaCombo2((String) combo1.getSelectedItem());

		v.getContentPane().add(combo1);
		v.getContentPane().add(combo2);
		v.pack();
		v.setVisible(true);
		v.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	private void rellenaCombo1() {
		combo1.addItem("letras");
		combo1.addItem("numeros");
	}

	private void rellenaCombo2(String seleccionEnCombo1) {
		combo2.removeAllItems();
		if (seleccionEnCombo1.equals("letras")) {
			combo2.addItem("A");
			combo2.addItem("B");
			combo2.addItem("C");
		} else if (seleccionEnCombo1.equals("numeros")) {
			combo2.addItem("1");
			combo2.addItem("2");
			combo2.addItem("3");
		}

	}
}
