package app;

import static util.Iters.find;

import java.lang.reflect.Field;
import java.lang.reflect.Member;

public class FieldSetterFinder implements SetterFinder{

	@Override
	public Setter get(Iterable<? extends Member> members, final String memberName) {
		Field f = (Field) find(members, Binder.fieldEqualsTo(memberName));
		if(f != null){ 
			return new FieldSetter(f);
		}
		return null;
	}
}
