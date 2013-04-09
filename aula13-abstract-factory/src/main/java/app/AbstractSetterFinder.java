package app;

import static util.Iters.find;

import java.lang.reflect.Member;

import util.Predicate;

public abstract class AbstractSetterFinder implements SetterFinder{
	final SetterFactory factory;
	
	public AbstractSetterFinder(SetterFactory factory) {
		this.factory = factory;
	}

	@Override
	public final Setter get(Iterable<? extends Member> members, final String memberName) {
		Member m = find(members, getPreciate(memberName));
		if(m != null){
			return factory.makeSetter(m);
		}
		return null;
	}

	protected abstract Predicate<? super Member> getPreciate(String memberName);
	
}
