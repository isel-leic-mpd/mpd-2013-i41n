package app;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import tohtml.HtmlElement;
import tohtml.HtmlFormatDate;
import tohtml.HtmlFormatIterable;
import tohtml.HtmlFormatObject;
import tohtml.HtmlParser;
import tohtml.elements.HtmlTextElement;
import app.test.model.Course;
import app.test.model.StudentDto;

public class Program {

	/**
	 * @param args
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws IOException 
	 * @throws ParseException 
	 * @throws InstantiationException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 */
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, IOException, ParseException, InstantiationException, NoSuchMethodException, SecurityException {
		StudentDto sdt = new StudentDto("Ze Manel", "32451", dtFormatter.parse("4-6-1978"), Course.MPD, Course.AVE);
		
		HtmlParser parser = new HtmlParser(
				new HtmlFormatIterable(), 
				new HtmlFormatDate(), 
				new HtmlFormatObject());
		parser.parse(sdt).render(System.out);
		parser.parse(sdt).render(new PrintStream(new FileOutputStream("dummy.html")));
		
		Runtime.getRuntime().exec("explorer dummy.html");
	}

	final static DateFormat dtFormatter = new SimpleDateFormat("dd-MM-yyyy");
}
