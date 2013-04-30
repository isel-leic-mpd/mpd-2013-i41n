package app;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.Icon;
import javax.swing.JComponent;

public class IconAdapter extends JComponent{
	private Icon _icon; 
	public IconAdapter(Icon i){ 
		_icon = i; 
	} 
	protected void paintComponent(Graphics g) { 
		_icon.paintIcon(this, g, 0, 0); 
	} 
	public int getWidth() { 
		return _icon.getIconWidth(); 
	} 
	public int getHeight() { 
		return _icon.getIconHeight(); 
	} 
	public Dimension getPreferredSize() { 
		return new Dimension(getWidth(), getHeight()); 
	} 
}
