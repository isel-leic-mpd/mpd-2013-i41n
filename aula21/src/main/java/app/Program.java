package app;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


class MyButton extends Button{

	private Collection<ActionCommand> actions;
	
	public MyButton(String label, ActionCommand...actionCommand) {
		super(label);
		this.enableEvents(ActionEvent.ACTION_PERFORMED);
		this.actions = new ArrayList<>();
		for (ActionCommand a : actionCommand) {
			actions.add(a);
		}
	}
	
	public void addActionCommand(ActionCommand a){
		actions.add(a);
	}
	
	public void removeActionCommand(ActionCommand a){
		actions.remove(a);
	}
	
	@Override
	final protected void processActionEvent(ActionEvent arg0) {
		super.processActionEvent(arg0);
		JOptionPane.showMessageDialog(this, "Ola Mundo!!!!");
		for (ActionCommand a : actions) {
			a.doIt();
		}

		// additionalAction();
	}
/*
	protected void additionalAction() {
		// TODO Auto-generated method stub
		
	}
*/
	
}

public class Program {
	public static void main(String[] args) {
		
		JFrame frm = new JFrame();
		frm.setLayout(new FlowLayout());
		// frm.setLayout(new GridLayout(2,1)); // STRATEGY design pattern
		frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JLabel lb = new JLabel("Ola:");
		lb.setBorder(BorderFactory.createBevelBorder(1)); // STRATEGY !!!! Mas nao segue o PD Abstract Factory 
		frm.add(lb);
		final JTextArea txtArea = new JTextArea();
		JScrollPane txt = new JScrollPane(txtArea); // DECORATOR !!!
		ActionCommand cmd1 = new ActionCommand() {	
			@Override
			public void doIt() {
				txtArea.append("Ole Madrid!!!");
			}
		};
		ActionCommand cmd2 = new ActionCommand() {	
			@Override
			public void doIt() {
				System.out.println(new Date(System.currentTimeMillis()));
			}
		};

		MyButton bt1 =  new MyButton("Click");
		bt1.addActionCommand(cmd1);
		bt1.addActionCommand(cmd2);
		frm.add(bt1);
		MyButton bt2 = new MyButton("Backup");
		bt2.addActionCommand(cmd1);
		frm.add(bt2);
		
		txt.setPreferredSize(new Dimension(300, 100));
		frm.add(txt);
		frm.add(new IconAdapter( new ImageIcon("wave.jpg"))); // ADAPTER ... patterm

		frm.pack();
		frm.setVisible(true);
	}

}
