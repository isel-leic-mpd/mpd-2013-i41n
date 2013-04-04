package app.test;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;
import junit.framework.TestCase;
import app.Binder;
import app.test.model.Person;
import app.test.model.PersonDto;

public class BinderTests extends TestCase{
	
	
	public void test_person_fields_values() throws IllegalArgumentException, IllegalAccessException{
		/*
		 * Arrange
		 */
		PersonDto p1 = new PersonDto("Ze Manel", "8264763254", 23);
		/*
		 * Act
		 */
		Map<String, Object> fieldsVals = Binder.getFields(p1);
		/*
		 * Assert
		 */
		Assert.assertEquals(p1.name, fieldsVals.get("name"));
		Assert.assertEquals(p1.age, fieldsVals.get("age"));
		Assert.assertEquals(p1.id, fieldsVals.get("id"));
	}
	
	public void test_account_fields_values(){
		
	}

	public void test_bindto_with_person() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		/*
		 * Arrange
		 */
		Map<String, Object> fields = new HashMap<String, Object>();
		fields.put("name", "Lolita");
		fields.put("id", "35846321");
		fields.put("age",  58);
		/*
		 * Act
		 */
		Person p = new Binder<Person>().bindTo(Person.class, fields.entrySet());
		/*
		 * Assert
		 */
		Assert.assertEquals(fields.get("name"), p.getName());
		Assert.assertEquals(fields.get("age"), p.getAge());
		Assert.assertEquals(fields.get("id"), p.id);
	}
}
