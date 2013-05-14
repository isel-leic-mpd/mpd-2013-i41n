package orm;

import java.sql.SQLException;

public abstract class JdbcAbstractMapper<K, T> implements DataMapper<K, T>{
	
	private final JdbcExecutor exec;
	
	public JdbcAbstractMapper(JdbcExecutor exec) {
		this.exec = exec;
	}

	@Override
	public final T getById(K key) {
		try {
			return exec.executeQuery(cmdGetById(), key).iterator().next();
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
	
	protected abstract JdbcCmd<T> cmdGetAll();
	
	protected abstract JdbcCmd<T> cmdGetById();
	
	protected abstract JdbcCmd<T> cmdUpdate();
}
