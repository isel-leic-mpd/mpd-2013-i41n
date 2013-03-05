package app;

import java.lang.reflect.Field;
import java.util.Map;

import model.Person;

public class Program {

	public static void main(String[] args) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Person p1 = new Person("Ze Manel", "8264763254", 23);
		Person p2 = new Person("Laurinda", "2343725", 47);
		
		Class cPerson = p1.getClass();
		Field fAge = cPerson.getField("age");
		assert (Integer) fAge.get(p1) == 23;
		assert (Integer) fAge.get(p2) == 47;
		
		System.out.println("TEST succeeded!");
		
	}

	/**
	 * Retorna uma mapa com os pares nome - valor dos campos
	 * do objecto o recebido por parametro.
	 */
	public static Map<String, Object> getFields(Object o){
		return null;
	}
}
