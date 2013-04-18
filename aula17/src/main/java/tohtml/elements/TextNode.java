package tohtml.elements;

import java.io.PrintStream;

import tohtml.HtmlElement;

public class TextNode implements HtmlElement{
	final String text;

	public TextNode(String text) {
		this.text = text;
	}

	@Override
	public void render(PrintStream out) {
		out.print(text);
	}

	@Override
	public void addElement(HtmlElement elem) {
		throw new UnsupportedOperationException();
	}
	
}
