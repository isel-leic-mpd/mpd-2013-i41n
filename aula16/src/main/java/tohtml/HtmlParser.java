package tohtml;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import tohtml.elements.HtmlComposedElement;
import tohtml.elements.HtmlTextElement;


public class HtmlParser extends HtmlAbstractParser{
	final static DateFormat dtFormatter = new SimpleDateFormat("dd-MM-yyyy");

	@Override
	protected HtmlElement parseObject(String name, Object val) {
		return new HtmlTextElement(
				"p", 
				String.format("%s: %s", name, val.toString()));
	}

	@Override
	protected HtmlElement parseDate(String name, Date val) {
		return new HtmlTextElement(
				"p", 
				String.format("%s: %s", name, dtFormatter.format((Date) val)));
	}

	@Override
	protected HtmlElement parseIterable(String name, Iterable val) {
		HtmlElement div = new HtmlComposedElement("div");
		div.addElement(new HtmlTextElement("p", name));
		HtmlElement ul = new HtmlComposedElement("ul");
		div.addElement(ul);
		for(Object item : (Iterable) val){
			ul.addElement(new HtmlTextElement("li", item.toString()));
		}
		return div;
	}
}
