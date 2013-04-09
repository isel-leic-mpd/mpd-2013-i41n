package app;

import java.lang.reflect.Member;
import java.lang.reflect.Method;

import util.Predicate;

public class PropertySetterWithValidationFinder extends AbstractSetterFinder{

	@Override
	protected Setter makeSetter(Member m) {
		Method prop = (Method) m;
		Validator v = prop .getAnnotation(Validator.class);
		if(v == null){
			return new PropertySetter(prop );
		}else{
			return new ValidatorSetter(v, new PropertySetter(prop ));
		}
	}

	@Override
	protected Predicate<? super Member> getPreciate(String memberName) {
		return Binder.methodEqualsTo("set" + memberName);
	}
}
