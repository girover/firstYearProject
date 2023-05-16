package services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import app.FormData;

import database.entities.ApplicationAnswer;
import database.entities.Entity;
import database.repositories.ApplicationAnswerRepository;


/**
 * This class is a part of Service Layer (Business Logic Layer). 
 *
 * This class provides a set of methods that can be used to deal with applicationAnswers.
 * such as retrieving all applicationAnswers, retrieving a set of applicationAnswers, 
 * retrieving a single applicationAnswer by ID, updating a applicationAnswer, 
 * and deleting a applicationAnswer.
 * 
 * @version 1.0
 * 
 * @author Rasmus Lysgaard Villadsen
 * 		 - <b style="color:red"> mrmaklie@gmail.com</b>
 * 		 - <a href="https://github.com/MrMaklie">Github</a>
 */
public class ApplicationAnswerService extends BaseResourceService {

	public ApplicationAnswerService() {
		repository = new ApplicationAnswerRepository();
//		entityClass = ApplicationAnswer.class;
	}

	@Override
	public ApplicationAnswer find(int id) {
		ApplicationAnswer applicationAnswer = (ApplicationAnswer) repository.find(id);
		return applicationAnswer;
	}

	@Override
	public ArrayList<ApplicationAnswer> getAll() {
		
		return (ArrayList<ApplicationAnswer>) repository.getAll();
	}

	@Override
	public ApplicationAnswer create(FormData data) {
		
		ApplicationAnswer applicationAnswer = new ApplicationAnswer();
		
		applicationAnswer.setApplicationID((int)data.input("applicationId"));
		applicationAnswer.setEmployeeID((int)data.input("userId"));
		applicationAnswer.setAnswerDate((String)data.input("answerDate"));
		applicationAnswer.setAccepted((String)data.input("accepted"));
		applicationAnswer.setNote((String)data.input("note"));
		

		repository.add(applicationAnswer);

		return applicationAnswer;
	}

	@Override
	public boolean update(Entity entity) {
		return repository.update((ApplicationAnswer) entity);
	}

	@Override
	public boolean delete(Entity entity) {
		return repository.delete((ApplicationAnswer) entity);
	}

}