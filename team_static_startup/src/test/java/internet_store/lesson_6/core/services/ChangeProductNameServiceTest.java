package internet_store.lesson_6.core.services;

import internet_store.lesson_6.core.database.Database;
import internet_store.lesson_6.core.requests.ChangeProductNameRequest;
import internet_store.lesson_6.core.responses.ChangeProductNameResponse;
import internet_store.lesson_6.core.responses.CoreError;
import internet_store.lesson_6.core.services.ChangeProductNameService;
import internet_store.lesson_6.core.services.validators.ChangeProductNameValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ChangeProductNameServiceTest {

    @Mock private Database database;
    @Mock private ChangeProductNameValidator validator;
    @InjectMocks private ChangeProductNameService service;

    @Test
    public void shouldReturnResponseWithoutErrors() {
        ChangeProductNameRequest request = new ChangeProductNameRequest(1L, "newName");
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        Mockito.when(database.changeProductName(1L, "newName")).thenReturn(true);

        ChangeProductNameResponse response = service.execute(request);

        assertTrue(response.isNameChanged());
    }

    @Test
    public void shouldReturnResponseWithError_whenValidationFails() {
        ChangeProductNameRequest request = new ChangeProductNameRequest(1L, "");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Product new name", "Should not be empty."));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        ChangeProductNameResponse response = service.execute(request);

        assertFalse(response.isNameChanged());
        assertEquals(1, response.getErrors().size());
        assertEquals("Product new name", response.getErrors().get(0).getField());
        assertEquals("Should not be empty.", response.getErrors().get(0).getMessage());
    }

}