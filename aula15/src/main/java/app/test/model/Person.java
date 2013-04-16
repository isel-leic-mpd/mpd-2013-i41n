package app.test.model;

/**
 * @author  mcarvalho
 */
public class Person {
	public final String id;
	/**
	 * @uml.property  name="name"
	 */
	private String name;
	/**
	 * @uml.property  name="age"
	 */
	private int age;
	
	public Person(){
		this.id = null;
	}
	
	public Person(String id, String name, int age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}

	/**
	 * @return
	 * @uml.property  name="name"
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 * @uml.property  name="name"
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return
	 * @uml.property  name="age"
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age
	 * @uml.property  name="age"
	 */
	public void setAge(int age) {
		this.age = age;
	}
}
