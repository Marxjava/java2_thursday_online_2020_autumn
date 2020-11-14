package lesson_3_request_response_input_data_validation.code.after.core.services;

import java.util.List;

import lesson_3_request_response_input_data_validation.code.after.core.domain.Book;
import lesson_3_request_response_input_data_validation.code.after.database.Database;
import lesson_3_request_response_input_data_validation.code.after.core.requests.AddBookRequest;
import lesson_3_request_response_input_data_validation.code.after.core.responses.AddBookResponse;
import lesson_3_request_response_input_data_validation.code.after.core.responses.CoreError;

public class AddBookService {

	private Database database;
	private AddBookRequestValidator validator;

	public AddBookService(Database database,
						  AddBookRequestValidator validator) {
		this.database = database;
		this.validator = validator;
	}

	public AddBookResponse execute(AddBookRequest request) {
		List<CoreError> errors = validator.validate(request);
		if (!errors.isEmpty()) {
			return new AddBookResponse(errors);
		}

		Book book = new Book(request.getTitle(), request.getAuthor());
		database.save(book);

		return new AddBookResponse(book);
	}

}
