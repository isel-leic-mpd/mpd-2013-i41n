package app.test;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Map;

import app.Binder;
import app.Program;
import app.test.model.PersonDto;

import junit.framework.Assert;
import junit.framework.TestCase;

public class ReflectionTets extends TestCase{
	
	PersonDto p1 = new PersonDto("Ze Manel", "8264763254", 23);
	PersonDto p2 = new PersonDto("Laurinda", "2343725", 47);

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
}
