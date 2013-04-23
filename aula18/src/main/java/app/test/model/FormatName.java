package app.test.model;

import tohtml.HtmlElement;
import tohtml.HtmlFormatter;
import tohtml.elements.HtmlTextElement;

public class FormatName implements HtmlFormatter<String>{

	@Override
	public HtmlElement format(String name, String src) {
		return new HtmlTextElement(
				"h2", 
				String.format("%s: %s", name, src));
	}

	@Override
	public boolean support(Class klass) {
		return klass == String.class;
	}

}
