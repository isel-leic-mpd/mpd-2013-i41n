package orm.northwind;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Product;
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
	
	final static String sqlQuery = "SELECT ProductID, ProductName, UnitPrice, UnitsInStock FROM Products";
	final static String sqlUpdate = "UPDATE Products SET ProductName = ?, UnitPrice = ?, UnitsInStock = ? WHERE ProductID = ?";
	
	final static JdbcConverter<Product> converter = new JdbcConverter<Product>() {
		@Override
		public Product convert(ResultSet rs) throws SQLException {
			return new Product(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getInt(4));
		}
	};

	final static JdbcBinder<Product> binder = new JdbcBinder<Product>() {
		@Override
		public void bind(PreparedStatement stmt, int idx, Product arg)
				throws SQLException {
			stmt.setString(1, arg.getProductName());
			stmt.setDouble(2, arg.getUnitPrice());
			stmt.setInt(3, arg.getUnitsInStock());
			stmt.setInt(4, arg.getId());
		}
	};
	
	final static JdbcCmd<Product> getById = new JdbcCmdQuery<>(sqlQuery + " WHERE ProductId = ?", converter, JdbcBinder.BindInt);
	final static JdbcCmd<Product> getAll = new JdbcCmdQuery<>(sqlQuery, converter);
	final static JdbcCmd<Product> updateCmd = new JdbcCmdQuery<>(sqlUpdate, null, binder);
	
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

	@Override
	protected JdbcCmd<Product> cmdUpdate() {
		return updateCmd;
	}

}
