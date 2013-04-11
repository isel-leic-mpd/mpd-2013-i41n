package tohtml;

import java.io.PrintStream;

public class HtmlWriter {
	private HtmlParser parser;

	public HtmlWriter(HtmlParser parser) {
		this.parser = parser;
	}

	public void writeTo(PrintStream out, Object src){
		HtmlElement elem = parser.parse(src);
		elem.render(out);
	}
	
}
