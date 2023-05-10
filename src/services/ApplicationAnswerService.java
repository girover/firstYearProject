package services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
	}

	@Override
	public ApplicationAnswer find(int id) {
		ApplicationAnswer applicationAnswer = (ApplicationAnswer) repository.find(id);
		return applicationAnswer;
	}

	@Override
	public ArrayList<ApplicationAnswer> getAll() {
		
		ArrayList<ApplicationAnswer> applicationAnswers = new ArrayList<>();
		
		ResultSet result = repository.getAll();
		
		try {
			while (result.next()) {
				ApplicationAnswer applicationAnswer = new ApplicationAnswer();
				if (applicationAnswer.makeFromResultSet(result))
					applicationAnswers.add(applicationAnswer);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return applicationAnswers;
	}

	@Override
	public ArrayList<ApplicationAnswer> getPage(int page) {

		
		ArrayList<ApplicationAnswer> applicationAnswers = new ArrayList<>();
		
		ResultSet result = repository.getPage(page);
		try {
			while (result.next()) {
				ApplicationAnswer applicationAnswer = new ApplicationAnswer();
				if (applicationAnswer.makeFromResultSet(result))
					applicationAnswers.add(applicationAnswer);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return applicationAnswers;
	}

	@Override
	public ApplicationAnswer create(FormData data) {
		
		ApplicationAnswer applicationAnswer = new ApplicationAnswer();
		
		applicationAnswer.setApplicationID((int)data.input("applicationId"));
		applicationAnswer.setEmployeeID((int)data.input("employeeId"));
		applicationAnswer.setAnswerDate((String)data.input("anserDate"));
		applicationAnswer.setAccepted((String)data.input("accepted"));
		applicationAnswer.setNote((String)data.input("note"));
		

		repository.add(applicationAnswer);

		return applicationAnswer;
	}

	@Override
	public ApplicationAnswer update(Entity entity) {
		if (repository.update((ApplicationAnswer) entity))
			return ((ApplicationAnswer) entity);
		
		return null;
	}

	@Override
	public boolean delete(Entity entity) {
		return repository.delete((ApplicationAnswer) entity);
	}

}