package orm.northwind;

import model.Product;
import orm.JdbcCmdQuery;

public class CmdGetAllProducts extends JdbcCmdQuery<Product>{

	final static String s = "SELECT ProductID, ProductName, UnitPrice, UnitsInStock FROM Products";
	
	public CmdGetAllProducts() {
		super(s, ProductConverter.SINGLETON);
	}

}
