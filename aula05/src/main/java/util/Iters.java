package util;

public class Iters {
	public static <T> T find(Iterable<T> elems, Predicate<T> p){
		for (T elem : elems) {
			if(p.invoke(elem)){
				return elem;
			}
		}
		return null;
	}
	public static <T> T find(T [] elems, Predicate<T> p){
		for (T elem : elems) {
			if(p.invoke(elem)){
				return elem;
			}
		}
		return null;
	}
}
