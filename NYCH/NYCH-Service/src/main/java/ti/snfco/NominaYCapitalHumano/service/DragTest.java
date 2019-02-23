package ti.snfco.NominaYCapitalHumano.service;

import javax.swing.*;

import java.awt.*;

import java.awt.event.*;


public class DragTest {

	
	InternalFrameMovimientos internalFrameMovimientos = new InternalFrameMovimientos();
	
//	public static void main(String[] args) {
//
//		MyWindow window = new MyWindow();
//
//		window.setVisible(true);
//
//		window.setPreferredSize(new Dimension(400,400));
//
//		window.pack();
//
//		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//	}

}

class MyWindow extends JFrame{

	public MyWindow(){


		Container container = getContentPane();

		container.setLayout(null);



		MyButton boton1 = new MyButton("Move me");

		container.add(boton1);

		boton1.setBounds(10, 10, 100, 30);

	}

}

class MyButton extends JButton implements MouseMotionListener{

	public MyButton(String text){

		super.setText(text);

		addMouseMotionListener(this);
	}



	public void mouseDragged(MouseEvent mme) {

		setLocation(

				this.getX() + mme.getX() - this.getWidth() / 2,

				this.getY() + mme.getY() - this.getHeight() / 2

				);

	}

	public void mouseMoved(MouseEvent mme) {
		
	}

}