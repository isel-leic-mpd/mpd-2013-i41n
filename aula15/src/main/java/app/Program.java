package app;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import tohtml.HtmlParser;
import app.test.model.Course;
import app.test.model.StudentDto;

public class Program {

	/**
	 * @param args
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws IOException 
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, IOException, ParseException {
		// new HtmlWriter(new HtmlParser()).writeTo(System.out, new StudentDto("Ze Manel", "32451", 19));

		new HtmlParser()
			.parse(new StudentDto("Ze Manel", "32451", dtFormatter.parse("4-6-1978")))
			.render(System.out);
		new HtmlParser()
			.parse(new StudentDto("Ze Manel", "32451", dtFormatter.parse("4-6-1978"), Course.MPD, Course.AVE))
			.render(new PrintStream(new FileOutputStream("dummy.html")));
		Runtime.getRuntime().exec("explorer dummy.html");
	}

	final static DateFormat dtFormatter = new SimpleDateFormat("dd-MM-yyyy");
}
