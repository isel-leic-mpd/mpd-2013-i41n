package orm.northwind;

import model.Product;
import orm.JdbcBinder;
import orm.JdbcCmdQuery;

public class CmdGetProductById extends JdbcCmdQuery<Product>{

	final static String s = "SELECT ProductID, ProductName, UnitPrice, UnitsInStock FROM Products WHERE ProductId = ?";
	
	public CmdGetProductById() {
		super(s, ProductConverter.SINGLETON, JdbcBinder.BindInt);
	}

	
}
