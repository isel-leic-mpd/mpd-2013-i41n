package tohtml.elements;

import java.io.PrintStream;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import tohtml.HtmlElement;

public class HtmlComposedElement implements HtmlElement{
	private final List<HtmlElement> children;
	private final String  tag;
	
	public HtmlComposedElement(String tag) {
		children = new LinkedList<HtmlElement>();
		this.tag = tag;
	}

	public void addElement(HtmlElement elem){
		children.add(elem);
	}
	
	public final void render(PrintStream out) {
		out.println(String.format("<%s>", tag));
		for(HtmlElement c: this.children){
			c.render(out);
		}
		out.println(String.format("</%s>", tag));
	}

}
