package app;

import java.lang.reflect.Member;

public interface SetterFactory {
	public Setter makeSetter(Member m);
}
