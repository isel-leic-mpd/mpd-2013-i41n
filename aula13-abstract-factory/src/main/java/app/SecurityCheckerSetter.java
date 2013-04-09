package app;

/**
 * Follows the Decorator approach.
 */
public class SecurityCheckerSetter implements Setter{

	/** 
	 * @uml.property name="s"
	 * @uml.associationEnd aggregation="shared"
	 */
	private final Setter s;
	
	private SecurityCheckerSetter(Setter s) {
		this.s = s;
	}

	@Override
	public void update(Object target, Object value) {
		// TODO Auto-generated method stub
		// Check security settings...
		// if ok
		s.update(target, value);
		
	}

}
