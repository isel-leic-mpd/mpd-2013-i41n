package orm.northwind;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Product;
import orm.DataMapper;
import orm.JdbcAbstractMapper;
import orm.JdbcBinder;
import orm.JdbcCmd;
import orm.JdbcCmdQuery;
import orm.JdbcConverter;
import orm.JdbcExecutor;

public class JdbcProductMapper extends JdbcAbstractMapper<Integer, Product>{

	/*=================================================================
	 * ----------------------- CONSTANTS ------------------------------
	 *=================================================================*/
	
	final static String sql = "SELECT ProductID, ProductName, UnitPrice, UnitsInStock FROM Products";
	
	final static JdbcConverter<Product> converter = new JdbcConverter<Product>() {
		@Override
		public Product convert(ResultSet rs) throws SQLException {
			return new Product(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getInt(4));
		}
	};
	
	final static JdbcCmd<Product> getById = new JdbcCmdQuery<>(sql + " WHERE ProductId = ?", converter, JdbcBinder.BindInt);
	final static JdbcCmd<Product> getAll = new JdbcCmdQuery<>(sql, converter);
	
	
	/*=================================================================
	 *=================================================================*/
	
	public JdbcProductMapper(JdbcExecutor exec) {
		super(exec);
	}

	@Override
	protected JdbcCmd<Product> cmdGetAll() {
		return getAll;
	}

	@Override
	protected JdbcCmd<Product> cmdGetById() {
		return getById;
	}

}
