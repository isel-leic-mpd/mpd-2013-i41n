package app.test.model;

import tohtml.HtmlElement;
import tohtml.HtmlFormatter;
import tohtml.elements.HtmlTextElement;

public class FormatName implements HtmlFormatter{

	@Override
	public HtmlElement format(String name, Object src) {
		return new HtmlTextElement(
				"h2", 
				String.format("%s: %s", name, src));
	}

}
