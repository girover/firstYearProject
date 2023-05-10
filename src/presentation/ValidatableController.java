package presentation;

import app.FormData;
import javaFxValidation.ValidationException;
import javaFxValidation.Validator;

/**
 * This abstract class is a part of Presentation Layer. This class provides a
 * set of methods that can be used in derived classes.
 * 
 * This class is useful for controllers that provide validation for user inputs.
 *
 * 
 * @version 1.0
 * 
 * @author Majed Hussein Farhan - <b style="color:red">girover.mhf@gmail.com</b>
 *         - <a href="https://github.com/girover">Github</a>
 */
public abstract class ValidatableController extends BaseController {

	protected Validator validator = null;
	

	/**
	 * Validate all FXML fields that are annotated with @Rules
	 * 
	 * @throws ValidationException
	 */
	protected void validate() throws ValidationException {
		validator = new Validator(this);
		validator.validate();
	}

	/**
	 * Validate only FXML fields that are annotated with @Rules and passed
	 * as parameters to this method.
	 * 
	 * @param fields
	 * @throws ValidationException
	 */
	protected void validate(String... fields) throws ValidationException {
		validator = new Validator(this);
		validator.validate(fields);
	}
	
}
