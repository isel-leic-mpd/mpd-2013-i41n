package orm.northwind;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Product;
import orm.JdbcCmd;

public class CmdGetProductById implements JdbcCmd<Product>{

	@Override
	public String getSql() {
		return "SELECT ProductID, ProductName, UnitPrice, UnitsInStock FROM Products WHERE ProductId = ?";
	}

	@Override
	public void bind(PreparedStatement stmt, Object[] args) throws SQLException {
		if(args.length != 1)
			throw new IllegalArgumentException("Expecting a product id as argument!");
		stmt.setInt(1, (Integer) args[0]);
	}

	@Override
	public Product convert(ResultSet rs) throws SQLException {
		return new Product(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getInt(4));
	}

}
