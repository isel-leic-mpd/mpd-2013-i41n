package tohtml;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import tohtml.elements.HtmlComposedElement;
import tohtml.elements.HtmlSingleElement;
import tohtml.elements.HtmlTextElement;
import tohtml.elements.TextNode;


public class HtmlParser {
	
	public HtmlElement parse(Object src) throws IllegalArgumentException, IllegalAccessException{
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
			Object val = field.get(src);
			if(val instanceof Iterable){
				body.addElement(new HtmlTextElement("p", field.getName()));
				HtmlElement ul = new HtmlComposedElement("ol");
				body.addElement(ul);
				for(Object item : (Iterable) val){
					ul.addElement(new HtmlTextElement("li", item.toString()));
				}
			}
			else if(val instanceof Date){
				body.addElement(new HtmlTextElement(
						"p", 
						String.format("%s: %s", field.getName(), dtFormatter.format((Date) val))));
			}
			else{
				body.addElement(new TextNode(
						String.format("%s: %s", field.getName(), field.get(src).toString())));
				body.addElement(new HtmlSingleElement("br"));
			}

			/*
			Object val = field.get(src);
			if(val instanceof Iterable){
				body.addElement(new HtmlTextElement("p", field.getName()));
				HtmlElement ul = new HtmlComposedElement("ul");
				body.addElement(ul);
				for(Object item : (Iterable) val){
					ul.addElement(new HtmlTextElement("li", item.toString()));
				}
			}
			else if(val instanceof Date){
				body.addElement(new HtmlTextElement(
						"p", 
						String.format("%s: %s", field.getName(), dtFormatter.format((Date) val))));
			}
			else{
				body.addElement(new HtmlTextElement(
						"p", 
						String.format("%s: %s", field.getName(), field.get(src).toString())));
			}
			*/
		}
		return root;
	}
	
	final static DateFormat dtFormatter = new SimpleDateFormat("dd-MM-yyyy");
}
