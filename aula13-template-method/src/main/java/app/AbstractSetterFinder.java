package app;

import static util.Iters.find;

import java.lang.reflect.Member;

import util.Predicate;

public abstract class AbstractSetterFinder implements SetterFinder{
	@Override
	public final Setter get(Iterable<? extends Member> members, final String memberName) {
		Member m = find(members, getPreciate(memberName));
		if(m != null){
			return makeSetter(m);
		}
		return null;
	}

	protected abstract Setter makeSetter(Member m);

	protected abstract Predicate<? super Member> getPreciate(String memberName);
	
}
