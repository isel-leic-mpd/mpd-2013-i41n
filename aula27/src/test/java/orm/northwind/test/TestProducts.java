package orm.northwind.test;

import java.sql.SQLException;

import junit.framework.Assert;
import model.Product;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import orm.JdbcExecutor;
import orm.JdbcExecutorMultipleConnection;
import orm.JdbcExecutorSingleConnection;
import orm.northwind.JdbcProductMapper;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

public class TestProducts {

	private JdbcProductMapper mapper; 
	private JdbcExecutor exec;
	@Before
	public void setUp(){
		SQLServerDataSource ds = new SQLServerDataSource();
		ds.setUser("myAppUser");
		ds.setPassword("fcp");
		exec = new JdbcExecutorSingleConnection(ds, false);
		mapper = new JdbcProductMapper(exec);
	}
	
	@After
	public void tearDown() throws Exception{
		exec.close();
	}
	
	@Test
	public void test_product_load_by_all() throws SQLException{
		Iterable<model.Product> res = mapper.getAll();
		int size = 0;
		for(Product e:res){size++;}
		Assert.assertEquals(77, size);
	}
	
	
	@Test
	public void test_product_load_by_id() throws SQLException{
		Product p = mapper.getById(9);
		
		Assert.assertEquals("Mishi Kobe Niku", p.getProductName());
		Assert.assertEquals(97.0, p.getUnitPrice());
		Assert.assertEquals(29, p.getUnitsInStock());
	}
	
	@Test
	public void test_update_product() throws SQLException{
		Product p = mapper.getById(9);
		Assert.assertEquals("Mishi Kobe Niku", p.getProductName());
		
		p.setProductName("Casa de Cafe Bastos");
		mapper.update(p);
		
		p = mapper.getById(9);
		Assert.assertEquals("Casa de Cafe Bastos", p.getProductName());		
	}
}
