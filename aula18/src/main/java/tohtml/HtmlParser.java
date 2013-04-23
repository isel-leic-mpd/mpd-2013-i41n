package tohtml;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import tohtml.elements.HtmlComposedElement;
import tohtml.elements.HtmlTextElement;
import utils.Iters;
import utils.Predicate;


public class HtmlParser {

	final HtmlFormatter<?>[] formatters;
	
	public HtmlParser(HtmlFormatter<?>...formatters) {
		this.formatters = formatters;
	}

	public final HtmlElement parse(Object src) throws IllegalArgumentException, IllegalAccessException, InstantiationException, NoSuchMethodException, SecurityException{
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
			assert val.getClass() == field.getType();
			Format f = field.getAnnotation(Format.class);
			HtmlFormatter formatter;
			if(f != null){
				if(f.value() != Format.NullFormatter.class){
					formatter = f.value().newInstance();
				}
				else{
					formatter = extractHtmlFormatter(f.method(), field.getName(), val, srcClass);
				}
			}
			else{
				formatter = Iters.find(formatters, new Predicate<HtmlFormatter<?>>() {
					public boolean eval(HtmlFormatter e) {
						return e.support(val.getClass());
					}
				});
			}
			body.addElement(formatter.format(field.getName(), val));
			
		}
		return root;
	}

	private HtmlFormatter extractHtmlFormatter(final String method, String name, final Object val, final Class srcClass) throws NoSuchMethodException, SecurityException {
		final Method m = srcClass.getDeclaredMethod(method, String.class, val.getClass());
		m.setAccessible(true);
		return new HtmlFormatter() {
			@Override
			public HtmlElement format(String name, Object val) {
				try {
					return (HtmlElement) m.invoke(null, name, val);
				} catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					throw new RuntimeException(e);
				}
			}

			@Override
			public boolean support(Class klass) {
				return klass.isAssignableFrom(val.getClass());
			}
		};
		
	}
}
