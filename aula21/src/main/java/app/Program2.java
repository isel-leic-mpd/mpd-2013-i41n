package app;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;


public class Program2 {
	public static void main(String[] args) {
		
		JFrame frm = new JFrame();
		frm.setLayout(new FlowLayout());
		// frm.setLayout(new GridLayout(2,1)); // STRATEGY design pattern
		frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JLabel lb = new JLabel("Ola:");
		Border b = BorderFactory.createBevelBorder(1); 
		lb.setBorder(BorderFactory.createBevelBorder(1)); // STRATEGY !!!! Mas nao segue o PD Abstract Factory 
		frm.add(lb);
		final JTextArea txtArea = new JTextArea();
		JScrollPane txt = new JScrollPane(txtArea); // DECORATOR !!!
		
		ActionListener cmd1 = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				txtArea.append("Ole Madrid!!!");
				
			}	
		};
		ActionListener cmd2 = new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(new Date(System.currentTimeMillis()));
			}
		};

		Button bt1 =  new Button("Click");
		bt1.addActionListener(cmd1);
		bt1.addActionListener(cmd2);
		frm.add(bt1);
		Button bt2 = new Button("Backup");
		bt2.addActionListener(cmd1);
		frm.add(bt2);
		
		txt.setPreferredSize(new Dimension(300, 100));
		frm.add(txt);
		frm.add(new IconAdapter( new ImageIcon("wave.jpg"))); // ADAPTER ... patterm

		frm.pack();
		frm.setVisible(true);
	}

}
