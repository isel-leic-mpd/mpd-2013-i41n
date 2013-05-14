package orm.northwind.test;

import java.sql.SQLException;

import junit.framework.Assert;
import model.Employee;

import org.junit.Before;
import org.junit.Test;

import orm.JdbcExecutor;
import orm.northwind.JdbcEmployeesMapper;
import orm.northwind.JdbcProductMapper;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

public class TestEmployees {


	private JdbcEmployeesMapper mapper; 

	@Before
	public void setUp(){
		SQLServerDataSource ds = new SQLServerDataSource();
		ds.setUser("myAppUser");
		ds.setPassword("fcp");
		JdbcExecutor exec = new JdbcExecutor(ds);
		mapper = new JdbcEmployeesMapper (exec);
	}
/*
	@Test
	public void test_update() throws Exception{
		Employee e = empMapper.loadById(7);
		Assert.assertEquals("King", e.getLastName());
		Assert.assertEquals("Robert", e.getFirstName());
		Assert.assertEquals("Sales Representative", e.getTitle());
		//
		// Update
		//
		e.setFirstName("Jose");
		e.setLastName("Manel");
		e.setTitle("Engenheiro");
		int res = empMapper.update(e);
		//
		// Assert
		//
		e = empMapper.loadById(7);
		Assert.assertEquals("Jose", e.getFirstName());
		Assert.assertEquals("Manel", e.getLastName());
		Assert.assertEquals("Engenheiro", e.getTitle());
	}
*/
	@Test
	public void test_load_all_employees() throws SQLException{		
		Iterable<Employee> res = mapper.getAll();
		int size = 0;
		for(Employee e:res){size++;}
		Assert.assertEquals(9, size);
	}
	@Test
	public void test_load_byid_employees() throws SQLException{
		Employee e = mapper.getById(7);
		Assert.assertEquals("King", e.getLastName());
		Assert.assertEquals("Robert", e.getFirstName());
		Assert.assertEquals("Sales Representative", e.getTitle());
	}
}
