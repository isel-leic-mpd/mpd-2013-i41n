package app;

import static util.Iters.find;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;

import com.sun.org.apache.xpath.internal.axes.PredicatedNodeTest;

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
	
	public static Predicate<Member> memberEqualsTo(final String name){
		return new Predicate<Member>() {
			public boolean invoke(Member m) {
				return m.getName().equalsIgnoreCase(name);
			}
		};		
	}	
}

class MemberEqualsTo implements Predicate<Member>{
	final String name;
	public MemberEqualsTo(String name) {
		this.name = name;
	}
	@Override
	public boolean invoke(Member elem) {
		return elem.getName().equalsIgnoreCase(name);
	}
}
