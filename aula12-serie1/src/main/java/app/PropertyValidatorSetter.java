package app;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PropertyValidatorSetter implements Setter{
	IValidation valid;
	Method prop;
	
	public PropertyValidatorSetter(Validator v, Method prop) {
		this.prop = prop;
		try {
			valid = v.value().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void update(Object target, Object value) {
		if(valid.validate(value)){
			try {
				prop.invoke(target, value);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				throw new RuntimeException(e);
			}
		}
	}

}
