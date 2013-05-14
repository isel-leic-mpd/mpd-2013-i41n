package orm;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import model.Product;

public class JdbcExecutorSingleConnection implements JdbcExecutor, AutoCloseable{
	final private DataSource ds;
	final private boolean autocommit;
	private Connection con;
	
	public JdbcExecutorSingleConnection(DataSource ds, boolean autocommit) {
		this.ds = ds;
		this.autocommit = autocommit;
	}
	
	public JdbcExecutorSingleConnection(DataSource ds) {
		this.ds = ds;
		autocommit = true;
	}

	private Connection initConnection() throws SQLException{
		if(con == null){
			con = ds.getConnection(); // Establish the connection.
			con.setAutoCommit(autocommit);
		}
		return con;
	}
	
	public <T> Iterable<T> executeQuery(JdbcCmd<T> cmd, Object...args) throws SQLException{
		final String SQL = cmd.getSql();
		try (PreparedStatement stmt = initConnection().prepareStatement(SQL)) {
			con.setAutoCommit(autocommit);
			cmd.bind(stmt, args);
			ResultSet rs = stmt.executeQuery();
			List<T> res = new LinkedList<T>();
			/*
			 * Iterate through the data in the result set and display it.
			 */
			while (rs.next()) {
				T elem = cmd.convert(rs);
				res.add(elem);
			}
			return res;
		}		
	}
	
	public <T> void executeUpdate(JdbcCmd<T> cmd, Object...args) throws SQLException{
		final String SQL = cmd.getSql();
		try (PreparedStatement stmt = initConnection().prepareStatement(SQL)) {
			con.setAutoCommit(autocommit);
			cmd.bind(stmt, args);
			stmt.executeUpdate();
		}		
	}

	@Override
	public void close() throws Exception {
		if(con != null){
			if(!autocommit) con.rollback();
			con.close();
			con = null;
		}
	}
}






