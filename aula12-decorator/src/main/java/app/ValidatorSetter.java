package app;


public class ValidatorSetter implements Setter {
	IValidation valid;
	/** 
	 * @uml.property name="s"
	 * @uml.associationEnd aggregation="shared"
	 */
	Setter s;

	public ValidatorSetter(Validator v, Setter s) {
		this.s = s;
		try {
			valid = v.value().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void update(Object target, Object value) {
		if(valid.validate(value)){
			s.update(target, value);
		}
	}

}
