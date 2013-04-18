package tohtml;

import tohtml.elements.HtmlTextElement;


public class HtmlFormatObject implements HtmlFormatter<Object>{

	@Override
	public HtmlElement format(String name, Object val) {
		return new HtmlTextElement(
				"p", 
				String.format("%s: %s", name, val.toString()));

	}

	@Override
	public boolean support(Class<?> klass) {
		return true;
	}
}
