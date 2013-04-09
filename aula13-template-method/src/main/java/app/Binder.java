package app;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;

import util.Iters;
import util.Predicate;

public class Binder<T> {
	/**
	 * Returns a map of pairs containing the fields names and values of the object o.
	 */
	public static Map<String, Object> getFields(Object o) throws IllegalArgumentException, IllegalAccessException{
		return null;
	}
	
	public T bindTo(Class<T> klass, Iterable<Entry<String, Object>> pairs, SetterFinder...finders) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		T res = klass.newInstance();
		Method [] ms = klass.getMethods();
		Field[] fs = klass.getFields();
		Iterable<Member> members = Iters.merge(Member.class, Arrays.asList(fs), Arrays.asList(ms));
		for (Entry<String, Object> e : pairs) {
			for (SetterFinder f : finders) {
				Setter s = f.get(members, e.getKey());
				if(s != null){
					s.update(res, e.getValue());
					break;
				}
			}
		}		
		return res;
	}
	
	public static Predicate<Member> memberEqualsTo(final String name){
		return new Predicate<Member>() {
			public boolean invoke(Member m) {
				return m.getName().equalsIgnoreCase(name);
			}
		};		
	}
	
	public static Predicate<Member> methodEqualsTo(final String name){
		return new Predicate<Member>() {
			public boolean invoke(Member m) {
				return (m instanceof Method) && m.getName().equalsIgnoreCase(name);
			}
		};		
	}	

	public static Predicate<Member> fieldEqualsTo(final String name){
		return new Predicate<Member>() {
			public boolean invoke(Member m) {
				return (m instanceof Field) && m.getName().equalsIgnoreCase(name);
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
