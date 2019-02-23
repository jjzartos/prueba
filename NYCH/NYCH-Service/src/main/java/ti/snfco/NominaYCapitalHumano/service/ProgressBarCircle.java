package ti.snfco.NominaYCapitalHumano.service;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.border.LineBorder;

public class ProgressBarCircle extends JFrame {

	private JPanel contentPane;
	JButton btnCargar = new JButton("Cargar");
	//PanelProgreesBar panelProgreesBar;
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ProgressBarCircle frame = new ProgressBarCircle();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public ProgressBarCircle() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 348, 325);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

//		btnCargar.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				new Thread(new Runnable() {
//					PanelProgreesBar panelProgreesBar = new PanelProgreesBar();
//					public void run() {
//						for(int num= 1;num<=100;num++) {
//							try {
//								panelProgreesBar.updateProgress(num);
//								panelProgreesBar.repaint();
//								Thread.sleep(50);
//							} catch (InterruptedException e1) {
//								e1.printStackTrace();
//							}
//						}
//					}
//				}).start();
//
//			}});
//		btnCargar.setBounds(46, 44, 89, 23);
//		contentPane.add(btnCargar);

		PanelProgreesBar panelProgressBar = new PanelProgreesBar();
		panelProgressBar.setBorder(null);
		panelProgressBar.setBackground(new Color(240, 240, 240));
		panelProgressBar.setBounds(10, 11, 329, 286);
		contentPane.add(panelProgressBar);
		}
	}
