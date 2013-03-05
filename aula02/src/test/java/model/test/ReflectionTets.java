package model.test;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Map;

import app.Program;

import model.Person;
import junit.framework.Assert;
import junit.framework.TestCase;

public class ReflectionTets extends TestCase{
	
	Person p1 = new Person("Ze Manel", "8264763254", 23);
	Person p2 = new Person("Laurinda", "2343725", 47);

	public void test_person_age_field() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{		
		Class cPerson = p1.getClass();
		Field fAge = cPerson.getField("age");
		Assert.assertEquals(23, ((Integer) fAge.get(p1)).intValue());
		Assert.assertEquals(47, ((Integer) fAge.get(p2)).intValue());	
	}
	

	public void test_person_name_field() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{		
		Class cPerson = p1.getClass();
		Field fName = cPerson.getField("name");
		Assert.assertEquals("Ze Manel", fName.get(p1));
		Assert.assertEquals("Laurinda", fName.get(p2));	
	}

	public void test_person_fields_values(){
		Map<String, Object> fieldsVals = Program.getFields(p1);
		Iterator<Map.Entry<String, Object>> iter = fieldsVals.entrySet().iterator();
		
		Map.Entry<String, Object> pair = iter.next();
		Assert.assertEquals("name", pair.getKey());
		Assert.assertEquals("Ze Manel", pair.getValue());
		
		pair = iter.next();
		Assert.assertEquals("id", pair.getKey());
		Assert.assertEquals("8264763254", pair.getValue());
		
		pair = iter.next();
		Assert.assertEquals("age", pair.getKey());
		Assert.assertEquals(23, pair.getValue());
	}
	
	public void test_account_fields_values(){
		
	}
	
}
