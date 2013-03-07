package model;

public class Person {

	public final String id;
	private String name;
	private int age;
	
	public Person(){
		id = null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	
}
