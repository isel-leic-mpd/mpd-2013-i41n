package orm.northwind;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Employee;
import orm.JdbcAbstractMapper;
import orm.JdbcBinder;
import orm.JdbcCmd;
import orm.JdbcCmdTemplate;
import orm.JdbcConverter;
import orm.JdbcExecutor;
import orm.JdbcExecutorMultipleConnection;

public class JdbcEmployeesMapper extends JdbcAbstractMapper<Integer, Employee>{
	final static String sql = "SELECT EmployeeId, Title, FirstName, LastName, BirthDate FROM Employees";
	
	final static JdbcConverter<Employee> conv = new JdbcConverter<Employee>(){
		@Override
		public Employee convert(ResultSet rs) throws SQLException{
			return new Employee(
					rs.getInt(1),
					rs.getString(2), 
					rs.getString(3), 
					rs.getString(4), 
					rs.getDate(5));
		}		
	};
	final static JdbcCmd<Employee> cmdLoadAll = new JdbcCmdTemplate<Employee>(
			sql, 
			conv);
	
	final static JdbcCmd<Employee> cmdLoadById = new JdbcCmdTemplate<Employee>(
			sql + " WHERE EmployeeId = ?", 
			conv,
			JdbcBinder.BindInt);	


	public JdbcEmployeesMapper(JdbcExecutor exec) {
		super(exec);
	}

	@Override
	protected JdbcCmd<Employee> cmdGetAll() {
		return cmdLoadAll;
	}

	@Override
	protected JdbcCmd<Employee> cmdGetById() {
		return cmdLoadById;
	}

	@Override
	protected JdbcCmd<Employee> cmdUpdate() {
		// TODO Auto-generated method stub
		return null;
	}

}
