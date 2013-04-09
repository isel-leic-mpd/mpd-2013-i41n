package app;

import static util.Iters.find;

import java.lang.reflect.Member;
import java.lang.reflect.Method;

import util.Predicate;

public class PropertySetterFinder implements SetterFinder{

	@Override
	public Setter get(Iterable<? extends Member> members, final String memberName) {
		Method m = (Method) find(members, Binder.methodEqualsTo("set" + memberName));
		if(m != null){ 
			return new PropertySetter(m);
		}
		return null;
	}
}
