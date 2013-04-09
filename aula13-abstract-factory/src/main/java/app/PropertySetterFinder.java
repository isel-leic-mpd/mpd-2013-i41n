package app;

import java.lang.reflect.Member;

import util.Predicate;

public class PropertySetterFinder extends AbstractSetterFinder{

	public PropertySetterFinder(SetterFactory factory){
		super(factory);
	}
	
	@Override
	protected Predicate<? super Member> getPreciate(String memberName) {
		return Binder.methodEqualsTo("set" + memberName);
	}
}
