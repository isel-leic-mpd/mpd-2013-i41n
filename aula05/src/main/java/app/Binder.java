package app;

import static util.Iters.find;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;

import util.Predicate;

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
	
	private static Predicate<Method> setterEqualsTo(final String name){
		return new Predicate<Method>() {
			public boolean invoke(Method m) {
				return m.getName().equalsIgnoreCase("set" + name);
			}
		};		
	}
	
	private static Predicate<Field> fieldEqualsTo(final String name){
		return new Predicate<Field>() {
			public boolean invoke(Field f) {
				return f.getName().equalsIgnoreCase(name);
			}
		};		
	}
}
