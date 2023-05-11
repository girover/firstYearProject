package services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import app.FormData;

import database.entities.Entity;
import database.entities.Payment;
import database.repositories.PaymentRepository;


/**
 * This class is a part of Service Layer (Business Logic Layer). 
 *
 * This class provides a set of methods that can be used to deal with payments.
 * such as retrieving all payments, retrieving a set of payments, 
 * retrieving a single payment by ID, updating a payment, 
 * and deleting a payment.
 * 
 * @version 1.0
 * 
 * @author Rasmus Lysgaard Villadsen
 * 		 - <b style="color:red"> mrmaklie@gmail.com</b>
 * 		 - <a href="https://github.com/MrMaklie">Github</a>
 */
public class PaymentService extends BaseResourceService {

	public PaymentService() {
		repository = new PaymentRepository();
	}

	@Override
	public Payment find(int id) {
		Payment payment = (Payment) repository.find(id);
		return payment;
	}

	@Override
	public ArrayList<Payment> getAll() {
		
		ArrayList<Payment> payments = new ArrayList<>();
		
		ResultSet result = repository.getAll();
		
		try {
			while (result.next()) {
				Payment payment = new Payment();
				if (payment.makeFromResultSet(result))
					payments.add(payment);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return payments;
	}

	@Override
	public ArrayList<Payment> getPage(int page) {

		
		ArrayList<Payment> payments = new ArrayList<>();
		
		ResultSet result = repository.getPage(page);
		try {
			while (result.next()) {
				Payment payment = new Payment();
				if (payment.makeFromResultSet(result))
					payments.add(payment);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return payments;
	}

	@Override
	public Payment create(FormData data) {
		
		Payment payment = new Payment();
		
		payment.setApplicationID((int)data.input("applicationId"));
		payment.setPaymentDate((String)data.input("paymentDate"));
		payment.setAmount((float)data.input("amount"));
		payment.setPrincipal((float)data.input("principal"));
		payment.setInterest((float)data.input("interest"));
		payment.setRemainingBalance((float)data.input("remainingBalance"));
		payment.setLateFee((float)data.input("lateFee"));
		payment.setPaymentMethod((String)data.input("paymentMethod"));
		payment.setNote((String)data.input("note"));
		payment.setCreatedAt((String)data.input("createdAt"));
		payment.setUpdatedAt((String)data.input("updatedAt"));

		repository.add(payment);

		return payment;
	}

	@Override
	public boolean update(Entity entity) {
		return repository.update((Payment) entity);
	}

	@Override
	public boolean delete(Entity entity) {
		return repository.delete((Payment) entity);
	}

}
