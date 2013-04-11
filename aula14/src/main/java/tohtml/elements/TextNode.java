package tohtml.elements;

import java.io.PrintStream;

import tohtml.HtmlElement;

public class TextNode implements HtmlElement{
	final String text;

	private TextNode(String text) {
		this.text = text;
	}

	@Override
	public void render(PrintStream out) {
		out.print(text);
	}
	
}
