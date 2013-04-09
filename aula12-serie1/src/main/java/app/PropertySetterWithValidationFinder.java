package app;

import static util.Iters.find;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;

public class PropertySetterWithValidationFinder implements SetterFinder{

	@Override
	public Setter get(Iterable<? extends Member> members, final String memberName) {
		Method m = (Method) find(members, Binder.methodEqualsTo("set" + memberName));
		if(m != null){
			Validator v = m.getAnnotation(Validator.class);
			if(v == null){
				return new PropertySetter(m);
			}else{
				return new PropertyValidatorSetter(v, m);
			}
		}
		return null;
	}

}
