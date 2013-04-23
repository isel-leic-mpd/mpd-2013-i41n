package app.test.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import tohtml.Format;
import tohtml.HtmlElement;
import tohtml.elements.HtmlTextElement;

public class StudentDto {
	
	@Format(FormatName.class)
	public final String name;
	public final String id;
	@Format(method="formatDate")
	public final Date birthDate;
	public final Iterable<Course> courses; 
	
	
	public StudentDto(String name, String id, Date birthDate, Course...courses) {
		super();
		this.name = name;
		this.id = id;
		this.birthDate = birthDate;
		this.courses = Arrays.asList(courses);
	}
	
	final static DateFormat dtFormatter = new SimpleDateFormat("YYYY-'W'ww-u");
	
	private static HtmlElement formatDate(String label, Date val){
		return new HtmlTextElement(
				"h2", 
				String.format("%s: %s", label, dtFormatter.format((Date) val)));	
	}	
	
}
