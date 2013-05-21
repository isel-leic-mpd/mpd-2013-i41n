package orm.northwind;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Product;
import model.Supplier;
import orm.DataMapper;
import orm.JdbcAbstractMapper;
import orm.JdbcBinder;
import orm.JdbcCmd;
import orm.JdbcCmdTemplate;
import orm.JdbcConverter;
import orm.JdbcExecutor;

public class JdbcProductsMapper extends JdbcAbstractMapper<Integer, Product>{

	/*=================================================================
	 * ----------------------- CONSTANTS ------------------------------
	 *=================================================================*/
	
	final static String sqlQuery = "SELECT ProductID, ProductName, UnitPrice, UnitsInStock, SupplierId FROM Products";
	final static String sqlUpdate = "UPDATE Products SET ProductName = ?, UnitPrice = ?, UnitsInStock = ? WHERE ProductID = ?";
	final static String sqlInsert = "INSERT INTO Products (ProductName, UnitPrice, UnitsInStock) VALUES (?, ?, ?)";
	final static String sqlDelete = "DELETE FROM Products WHERE ProductID = ?";
	
	final JdbcConverter<Product> converter = new JdbcConverter<Product>() {
		@Override
		public Product convert(ResultSet rs) throws SQLException {
			return new Product(
					rs.getInt(1), 
					rs.getString(2), 
					rs.getDouble(3), 
					rs.getInt(4),
					supMapper.getById(rs.getInt(5)));
		}
	};

	final static JdbcBinder<Product> binderUpdate = new JdbcBinder<Product>() {
		@Override
		public void bind(PreparedStatement stmt, int idx, Product arg) throws SQLException {
			binderInsert.bind(stmt, idx, arg);
			stmt.setInt(4, arg.getId());
		}
	};
	
	final static JdbcBinder<Product> binderInsert= new JdbcBinder<Product>() {
		@Override
		public void bind(PreparedStatement stmt, int idx, Product arg) throws SQLException {
			stmt.setString(1, arg.getProductName());
			stmt.setDouble(2, arg.getUnitPrice());
			stmt.setInt(3, arg.getUnitsInStock());
		}
	};
	
	final static JdbcBinder<Product> binderDelete = new JdbcBinder<Product>() {
		@Override
		public void bind(PreparedStatement stmt, int idx, Product arg) throws SQLException {
			stmt.setInt(1, arg.getId());
		}
	};
	
	final static JdbcConverter<Integer> genKey = new JdbcConverter<Integer>() {		
		@Override
		public Integer convert(ResultSet rs) throws SQLException {
			return rs.getInt(1);
		}
	};
	
	final JdbcCmd<Product> getById = new JdbcCmdTemplate<>(sqlQuery + " WHERE ProductId = ?", converter, JdbcBinder.BindInt);
	final JdbcCmd<Product> getAll = new JdbcCmdTemplate<>(sqlQuery, converter);
	final JdbcCmd<Product> updateCmd = new JdbcCmdTemplate<>(sqlUpdate, null, binderUpdate);
	final JdbcCmd<Integer> insertCmd = new JdbcCmdTemplate<>(sqlInsert, genKey, binderInsert);
	final JdbcCmd<Product> deleteCmd = new JdbcCmdTemplate<>(sqlDelete, null, binderDelete);
	
	/*=================================================================
	 *=================================================================*/
	
	private DataMapper<Integer, Supplier> supMapper;
	
	public JdbcProductsMapper(JdbcExecutor exec) {
		super(exec);
	}

	public void setSupplier(DataMapper<Integer, Supplier> sup){
		this.supMapper = sup;
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

	@Override
	protected void updateKeyOnValue(Product value, Integer key) {
		value.setId(key);
	}

	@Override
	protected JdbcCmd<Integer> cmdInsert() {
		return insertCmd;
	}

	@Override
	protected JdbcCmd<Product> cmdDelete() {
		return deleteCmd;
	}

}
