package tohtml;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import tohtml.elements.HtmlTextElement;


public class HtmlFormatDate implements HtmlFormatter<Date>{
	final static DateFormat dtFormatter = new SimpleDateFormat("dd-MM-yyyy");

	@Override
	public HtmlElement format(String name, Date val) {
		return new HtmlTextElement(
				"p", 
				String.format("%s: %s", name, dtFormatter.format((Date) val)));	}

	@Override
	public boolean support(Class<?> klass) {
		return Date.class.isAssignableFrom(klass);
	}
}
