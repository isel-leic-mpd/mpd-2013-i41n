package orm;

import java.sql.SQLException;
import java.util.Iterator;

public abstract class JdbcAbstractMapper<K, T> implements DataMapper<K, T>{
	
	private final JdbcExecutor exec;
	
	public JdbcAbstractMapper(JdbcExecutor exec) {
		this.exec = exec;
	}

	@Override
	public final T getById(K key) {
		try {
			Iterator<T> iter = exec.executeQuery(cmdGetById(), key).iterator();
			return iter.hasNext()? iter.next(): null;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	
	@Override
	public final Iterable<T> getAll() {
		try {
			return exec.executeQuery(cmdGetAll());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public final void update(T value) {
		try {
			exec.executeUpdate(cmdUpdate(), value);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public final void insert(T value) {
		try {
			K key = exec.executeInsert(cmdInsert(), value);
			updateKeyOnValue(value, key);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public void delete(T value) {
		try {
			exec.executeUpdate(cmdDelete(), value);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	protected abstract void updateKeyOnValue(T value, K key);

	protected abstract JdbcCmd<K> cmdInsert();

	protected abstract JdbcCmd<T> cmdGetAll();
	
	protected abstract JdbcCmd<T> cmdGetById();
	
	protected abstract JdbcCmd<T> cmdUpdate();
	
	protected abstract JdbcCmd<T> cmdDelete();


}
