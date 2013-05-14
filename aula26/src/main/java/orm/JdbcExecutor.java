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

public class JdbcExecutor{
	final private DataSource ds;
	
	public JdbcExecutor(DataSource ds) {
		this.ds = ds;
	}

	public <T> Iterable<T> executeQuery(JdbcCmd<T> cmd, Object...args) throws SQLException{
		final String SQL = cmd.getSql();
		try (Connection con = ds.getConnection(); // Establish the connection.
			 PreparedStatement stmt = con.prepareStatement(SQL)	
		) {
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
	}






