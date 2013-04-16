package tohtml;

import java.lang.reflect.Field;
import java.util.Date;

import tohtml.elements.HtmlComposedElement;
import tohtml.elements.HtmlTextElement;


public abstract class HtmlAbstractParser {
	
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
			Object val = field.get(src);
			Format f = field.getAnnotation(Format.class);
			if(f != null){
				HtmlFormatter formatter = f.value().newInstance();
				body.addElement(formatter.format(field.getName(), val));
			}
			else if(val instanceof Iterable){
				body.addElement(parseIterable(field.getName(), (Iterable) val));
			}
			else if(val instanceof Date){
				body.addElement(parseDate(field.getName(), (Date) val));
			}
			else{
				body.addElement(parseObject(field.getName(), val));
			}
		}
		return root;
	}

	protected abstract HtmlElement parseObject(String name, Object val);

	protected abstract HtmlElement parseDate(String name, Date val);
	
	protected abstract HtmlElement parseIterable(String name, Iterable val) ;
}
