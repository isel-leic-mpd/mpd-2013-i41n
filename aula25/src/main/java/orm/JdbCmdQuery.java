package orm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbCmdQuery<T> implements JdbcCmd<T>{

	final String sql;
	final JdbcBinder [] binders;
	
	public JdbCmdQuery(String sql, JdbcBinder<?>...binder) {
		this.sql = sql;
		this.binders = binder;
	}

	@Override
	public String getSql() {
		return sql;
	}

	@Override
	public void bind(PreparedStatement stmt, Object[] args) throws SQLException {
		if(args.length != binders.length)
			throw new IllegalArgumentException("Illegal nnumber of arguments!");
		int i = 0;
		for (Object o : args) {
			binders[++i].bind(stmt, i, o);
		}
	}

	@Override
	public T convert(ResultSet rs) throws SQLException {
		// TPC: depender de um JdbcConverter e testar com um comando prodById que 
		// substitui a classe CmdGetProductById
		return null;
	}

}
