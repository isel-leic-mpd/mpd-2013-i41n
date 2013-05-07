package tohtml;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;



@Retention(RetentionPolicy.RUNTIME)
public @interface Format {
	
	class NullFormatter implements HtmlFormatter{
		@Override
		public HtmlElement format(String name, Object val) {
			return null;
		}

		@Override
		public boolean support(Class klass) {
			return false;
		}
	}
	
	Class<? extends HtmlFormatter> value() default NullFormatter.class;
	String method() default "";
}
