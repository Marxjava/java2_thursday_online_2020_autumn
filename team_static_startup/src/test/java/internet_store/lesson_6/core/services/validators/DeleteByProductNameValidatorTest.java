package internet_store.lesson_6.core.services.validators;

import internet_store.lesson_6.core.requests.DeleteByProductNameRequest;
import internet_store.lesson_6.core.responses.CoreError;
import internet_store.lesson_6.core.services.validators.DeleteByProductNameValidator;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class DeleteByProductNameValidatorTest {

    private DeleteByProductNameValidator validator;

    @Before
    public void setUp(){
        validator = new DeleteByProductNameValidator();
    }

    @Test
    public void success() {
        DeleteByProductNameRequest request = new DeleteByProductNameRequest("Product");
        List<CoreError> errors = validator.validate(request);
        assertEquals(0, errors.size());
    }

    @Test
    public void shouldReturnErrorWhenProductNameIsNull() {
        DeleteByProductNameRequest request = new DeleteByProductNameRequest(null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Product name", errors.get(0).getField());
        assertEquals("must not be empty", errors.get(0).getMessage());
    }

}