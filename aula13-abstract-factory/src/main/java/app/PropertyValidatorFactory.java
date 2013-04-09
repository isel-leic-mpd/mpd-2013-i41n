package app;

import java.lang.reflect.Member;
import java.lang.reflect.Method;


public class PropertyValidatorFactory implements SetterFactory {

	@Override
	public Setter makeSetter(Member m) {
		Method prop = (Method) m;
		Validator v = prop.getAnnotation(Validator.class);
		if(v == null)
			return new PropertySetter(prop);
		else
			return new ValidatorSetter(v, new PropertySetter(prop));
	}

}
