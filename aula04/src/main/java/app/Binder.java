package app;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Binder<T> {
	/**
	 * Returns a map of pairs containing the fields names and values of the object o.
	 */
	public static Map<String, Object> getFields(Object o) throws IllegalArgumentException, IllegalAccessException{
		return null;
	}
	
	public T bindTo(Class<T> klass, Iterable<Entry<String, Object>> src) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		return null;
	}
}
