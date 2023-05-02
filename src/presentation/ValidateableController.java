package presentation;

import javaFxValidation.ValidationException;
import javaFxValidation.Validator;

public abstract class ValidateableController extends BaseController {

	protected Validator validator = null;
	
	
	protected void validate() throws ValidationException {
		validator = new Validator(this);
		this.validator.validate();
	}
	
	protected void validate(String...fields) throws ValidationException {
		this.validator.validate(fields);
	}
}
