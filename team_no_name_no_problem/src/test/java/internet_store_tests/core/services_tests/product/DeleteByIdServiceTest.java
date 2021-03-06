package internet_store_tests.core.services_tests.product;

import internet_store.core.domain.Product;
import internet_store.core.requests.product.DeleteProductRequest;
import internet_store.core.response.CoreError;
import internet_store.core.response.product.DeleteProductResponse;
import internet_store.core.services.product.DeleteByIdService;
import internet_store.core.services.product.validators.DeleteProductRequestValidator;
import internet_store.database.product.ProductDatabase;
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

public class DeleteByIdServiceTest {

    @Mock
    private ProductDatabase productDatabase;
    @Mock
    private DeleteProductRequestValidator deleteProductRequestValidator;
    @InjectMocks
    DeleteByIdService deleteByIdService;

    @Test
    public void deleteNotValidRequest() {

        DeleteProductRequest request1 = new DeleteProductRequest(-2L);

        List<CoreError> errors1 = new ArrayList<>();
        errors1.add(new CoreError("id", "Not valid input for id"));

        Mockito.when(deleteProductRequestValidator.validate(request1)).thenReturn(errors1);

        DeleteProductResponse response = deleteByIdService.execute(request1);
        assertEquals(response.hasErrors(), true);
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "id");
    }

    @Test
    public void testNoIdInDatabase() {

        DeleteProductRequest request1 = new DeleteProductRequest(2L);

        List<CoreError> errors1 = new ArrayList<>();
        CoreError expectedError = new CoreError("database", "database doesn't contain product with id 2");
        errors1.add(expectedError);

        Mockito.when(deleteProductRequestValidator.validate(request1)).thenReturn(new ArrayList<>());
        Mockito.when(productDatabase.containsId(2L)).thenReturn(false);

        DeleteProductResponse response = deleteByIdService.execute(request1);
        assertEquals(response.hasErrors(), true);
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().contains(expectedError), true);
    }

    @Test
    public void testDeletedSuccessfully() {

        DeleteProductRequest request1 = new DeleteProductRequest(2L);
        Product product = new Product("Title", "D", 5);
        product.setId(2L);
        List<Product>products = new ArrayList<>();
        products.add(product);

        Mockito.when(deleteProductRequestValidator.validate(request1)).thenReturn(new ArrayList<>());
        Mockito.when(productDatabase.containsId(2L)).thenReturn(true);
        Mockito.when(productDatabase.getProducts()).thenReturn(products);

        DeleteProductResponse response = deleteByIdService.execute(request1);
        assertFalse(response.hasErrors());
        assertTrue(response.getId().equals(2L));
    }

}