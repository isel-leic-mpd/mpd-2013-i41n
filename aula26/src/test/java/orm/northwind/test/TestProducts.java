package orm.northwind.test;

import java.sql.SQLException;

import junit.framework.Assert;
import model.Product;

import org.junit.Before;
import org.junit.Test;

import orm.JdbcExecutor;
import orm.northwind.CmdGetAllProducts;
import orm.northwind.CmdGetProductById;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

public class TestProducts {

	private JdbcExecutor exec; 
	
	@Before
	public void setUp(){
		SQLServerDataSource ds = new SQLServerDataSource();
		ds.setUser("myAppUser");
		ds.setPassword("fcp");
		exec = new JdbcExecutor(ds);
	}
	
	@Test
	public void test_product_load_by_all() throws SQLException{
		CmdGetAllProducts cmd = new CmdGetAllProducts(); 
		Iterable<model.Product> res = exec.executeQuery(cmd);
		int size = 0;
		for(Product e:res){size++;}
		Assert.assertEquals(77, size);
	}
	
	
	@Test
	public void test_product_load_by_id() throws SQLException{
		CmdGetProductById cmd = new CmdGetProductById();
		Iterable<model.Product> res = exec.executeQuery(cmd, 9);
		Product p = res.iterator().next();
		
		Assert.assertEquals("Mishi Kobe Niku", p.getProductName());
		Assert.assertEquals(97.0, p.getUnitPrice());
		Assert.assertEquals(29, p.getUnitsInStock());
	}
}
