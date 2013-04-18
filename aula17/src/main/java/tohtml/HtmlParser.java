package tohtml;

import java.lang.reflect.Field;
import java.util.Date;

import tohtml.elements.HtmlComposedElement;
import tohtml.elements.HtmlTextElement;
import utils.Iters;
import utils.Predicate;


public class HtmlParser {

	final HtmlFormatter<?>[] formatters;
	
	public HtmlParser(HtmlFormatter<?>...formatters) {
		this.formatters = formatters;
	}

	public final HtmlElement parse(Object src) throws IllegalArgumentException, IllegalAccessException, InstantiationException{
		HtmlElement root = new HtmlComposedElement("html");
		/*
		 * Head
		 */
		Class srcClass = src.getClass();
		HtmlElement head = new HtmlComposedElement("head");
		head.addElement(new HtmlTextElement("title", srcClass.getSimpleName()));
		root.addElement(head);
		/*
		 * Body
		 */
		Field [] fs = srcClass.getDeclaredFields();
		HtmlElement body = new HtmlComposedElement("body");
		body.addElement(new HtmlTextElement("h1", srcClass.getSimpleName()));
		root.addElement(body);
		for (Field field : fs) {
			field.setAccessible(true);
			final Object val = field.get(src);
			Format f = field.getAnnotation(Format.class);
			HtmlFormatter formatter;
			if(f != null){
				formatter = f.value().newInstance();
			}
			else 
				formatter = Iters.find(formatters, new Predicate<HtmlFormatter<?>>() {
					public boolean eval(HtmlFormatter e) {
						return e.support(val.getClass());
					}
				});
			body.addElement(formatter.format(field.getName(), val));
		}
		return root;
	}
}
