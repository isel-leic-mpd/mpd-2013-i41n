package tohtml;

import tohtml.elements.HtmlComposedElement;
import tohtml.elements.HtmlTextElement;


public class HtmlFormatIterable implements HtmlFormatter<Iterable>{

	@Override
	public HtmlElement format(String name, Iterable val) {
		HtmlElement div = new HtmlComposedElement("div");
		div.addElement(new HtmlTextElement("p", name));
		HtmlElement ul = new HtmlComposedElement("ul");
		div.addElement(ul);
		for(Object item : val){
			ul.addElement(new HtmlTextElement("li", item.toString()));
		}
		return div;
	}

	@Override
	public boolean support(Class<?> klass) {
		return Iterable.class.isAssignableFrom(klass);
	}
}
