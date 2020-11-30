package internet_store.services.product;

import internet_store.core.domain.Product;
import internet_store.core.requests.product.DeleteProductRequest;
import internet_store.core.requests.product.FindByIdRequest;
import internet_store.core.response.CoreError;
import internet_store.core.response.product.DeleteProductResponse;
import internet_store.core.response.product.FindByIdResponse;
import internet_store.core.services.product.DeleteByIdService;
import internet_store.core.services.product.DeleteProductRequestValidator;
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
    public void noTitleAddedToProductRequestTest() {

        Product laptop = new Product("Laptop", "Samsung", 400);
        productDatabase.add(laptop);
        DeleteProductRequest request1 = new DeleteProductRequest(2L);

        List<CoreError> errors1 = new ArrayList<>();
        errors1.add(new CoreError("database", "There is no such ID!"));
        Mockito.when(deleteProductRequestValidator.validate(request1)).thenReturn(errors1);

        DeleteProductResponse response = deleteByIdService.execute(request1);
        assertEquals(response.hasErrors(), true);
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "database");
    }

}