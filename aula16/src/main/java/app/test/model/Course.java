package app.test.model;

public class Course {
	public final String name;
	public final float ects;
	
	private Course(String name, float ects) {
		this.name = name;
		this.ects = ects;
	}
	
	
	
	@Override
	public String toString() {
		return "[name=" + name + ", ects=" + ects + "]";
	}

	public static final Course MPD = new Course("MPD", 6);
	public static final Course AVE = new Course("AVE", 6);
	public static final Course PC = new Course("PC", 6);
}
