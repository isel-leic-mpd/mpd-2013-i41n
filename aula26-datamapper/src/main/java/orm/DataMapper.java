package orm;

public interface DataMapper<K, T>{
	
	T getById(K key);
	
	Iterable<T> getAll();
}
