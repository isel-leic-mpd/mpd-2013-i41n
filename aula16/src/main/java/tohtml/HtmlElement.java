package tohtml;

import java.io.PrintStream;

public interface HtmlElement {
	
	public void addElement(HtmlElement elem);
	
	void render(PrintStream out);
	
}
