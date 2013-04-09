package app;

import java.lang.reflect.Field;

public class FieldValidatorSetter implements Setter {
	IValidation valid;
	Field f;
	
	public FieldValidatorSetter(Validator v, Field f) {
		this.f = f;
		this.f.setAccessible(true);
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
				f.set(target, value);
			} catch (IllegalAccessException | IllegalArgumentException e) {
				throw new RuntimeException(e);
			}
		}
	}

}
