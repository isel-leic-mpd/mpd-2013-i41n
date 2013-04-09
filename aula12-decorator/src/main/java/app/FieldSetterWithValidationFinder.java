package app;

import static util.Iters.find;

import java.lang.reflect.Field;
import java.lang.reflect.Member;

public class FieldSetterWithValidationFinder implements SetterFinder{

	@Override
	public Setter get(Iterable<? extends Member> members, final String memberName) {
		Field f = (Field) find(members, Binder.fieldEqualsTo(memberName));
		if(f != null){
			Validator v = f.getAnnotation(Validator.class);
			if(v == null)
				return new FieldSetter(f);
			else
				return new ValidatorSetter(v, new FieldSetter(f));
		}
		return null;
	}
}
