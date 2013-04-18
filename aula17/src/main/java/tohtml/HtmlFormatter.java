package tohtml;

public interface HtmlFormatter<T> {
	HtmlElement format(String name, T val);
	boolean support(Class<?> klass);
}
