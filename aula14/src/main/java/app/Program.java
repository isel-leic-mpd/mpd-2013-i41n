package app;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import tohtml.HtmlParser;
import tohtml.HtmlWriter;
import app.test.model.StudentDto;

public class Program {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		new HtmlWriter(new HtmlParser()).writeTo(System.out, new StudentDto("Ze Manel", "32451", 19));
		new HtmlWriter(new HtmlParser()).writeTo(new PrintStream(new FileOutputStream("dummy.html")), new StudentDto("Ze Manel", "32451", 19));
		
	}

}
