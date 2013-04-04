package app;

import static util.Iters.find;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;

public class PropertySetterWithValidationFinder implements SetterFinder{

	@Override
	public Setter get(Iterable<? extends Member> members, final String memberName) {
		Member m = find(members, Binder.memberEqualsTo("set" + memberName));
		if(m != null && (m instanceof Method)){
			final Method prop = (Method) m;
			final Validator v = prop.getAnnotation(Validator.class);
			if(v == null){
				return new PropertySetter(prop);
			}else{
				return new Setter() {
					@Override
					public void update(Object target, Object value) {
						IValidation valid;
						try {
							valid = v.value().newInstance();
						} catch (InstantiationException | IllegalAccessException e) {
							throw new RuntimeException(e);
						}
						if(valid.validate(value))
							try {
								prop.invoke(target, value);
							} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
								throw new RuntimeException(e);
							}
					}
				}; 
			}
		}
		return null;
	}

}
