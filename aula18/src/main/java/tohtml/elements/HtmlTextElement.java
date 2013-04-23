package tohtml.elements;

public class HtmlTextElement extends HtmlComposedElement{

	public HtmlTextElement(String tag, String text) {
		super(tag);
		addElement(new TextNode(text));
	}

}
