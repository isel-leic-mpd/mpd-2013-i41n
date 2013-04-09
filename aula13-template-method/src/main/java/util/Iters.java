package util;

import java.util.Collection;
import java.util.LinkedList;

public class Iters {
	public static <T> T find(Iterable<T> elems, Predicate<? super T> p){
		for (T elem : elems) {
			if(p.invoke(elem)){
				return elem;
			}
		}
		return null;
	}
	// public static <T extends S, S> T find(T [] elems, Predicate<S> p){
	public static <T> T find(T [] elems, Predicate<? super T> p){
		for (T elem : elems) {
			if(p.invoke(elem)){
				return elem;
			}
		}
		return null;
	}
	

	public static <T> Iterable<T> merge(Class<T> base, Iterable<? extends T> elems1, Iterable<? extends T> elems2) {
		Collection<T> res = new LinkedList<T>();
		for (T t : elems1) {
			res.add(t);
		}
		for (T t : elems2) {
			res.add(t);
		}
		return res;
	}

}
