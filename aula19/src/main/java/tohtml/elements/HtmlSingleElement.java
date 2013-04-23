package tohtml.elements;

import java.io.PrintStream;

import tohtml.HtmlElement;

public class HtmlSingleElement implements HtmlElement{
	
	private String tag;
	
	public HtmlSingleElement(String tag) {
		this.tag = tag;
	}

	@Override
	public void render(PrintStream out) {
		out.println(String.format("<%s/>", tag));
	}

	@Override
	public void addElement(HtmlElement elem) {
		throw new UnsupportedOperationException();
	}	
}
