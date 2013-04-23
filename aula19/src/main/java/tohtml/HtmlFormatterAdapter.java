package tohtml;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HtmlFormatterAdapter implements HtmlFormatter{
	
	Method m;
	Object fieldVal;
	
	public HtmlFormatterAdapter(Class srcClass, String method, Object fieldVal) {
		this.fieldVal = fieldVal;
		try {
			m = srcClass.getDeclaredMethod(method, String.class, fieldVal.getClass());
		} catch (NoSuchMethodException | SecurityException e) {
			throw new RuntimeException(e);
		}
		m.setAccessible(true);

	}
	
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
		return klass.isAssignableFrom(fieldVal.getClass());
	}

}
