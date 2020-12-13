package internet_store.application.acceptancetests;

import internet_store.application.config.ProductListConfiguration;
import internet_store.application.core.requests.AddProductRequest;
import internet_store.application.core.requests.DeleteByProductRequest;
import internet_store.application.core.responses.DeleteByProductResponse;
import internet_store.application.core.services.AddProductService;
import internet_store.application.core.services.DeleteProductByProductService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class DeleteByProductAcceptanceTest {

    private ApplicationContext applicationContext;

    @Before
    public void setUp() {
        applicationContext =
                new AnnotationConfigApplicationContext(ProductListConfiguration.class);
    }

    @Test
    public void shouldDeleteProductWithNoErrors() {
        AddProductRequest request = new AddProductRequest("tv",
                "good tv", new BigDecimal("399.99"));
        getAddProductService().execute(request);

        DeleteByProductRequest requestToDelete = new DeleteByProductRequest("tv",
                "good tv", new BigDecimal("399.99"));
        DeleteByProductResponse response = getDeleteProductByProductService().execute(requestToDelete);

        assertEquals(request.getProductName(), response.getDeletedProduct().getName());
        assertEquals(request.getProductDescription(), response.getDeletedProduct().getDescription());
        assertEquals(request.getProductPrice(), response.getDeletedProduct().getPrice());
    }

    @Test
    public void shouldNotDeleteProductErrorInDescription() {
        AddProductRequest request = new AddProductRequest("tv",
                "good tv", new BigDecimal("399.99"));
        getAddProductService().execute(request);

        DeleteByProductRequest requestToDelete = new DeleteByProductRequest("tv",
                " ", new BigDecimal("399.99"));
        DeleteByProductResponse response = getDeleteProductByProductService().execute(requestToDelete);

        assertNull(response.getDeletedProduct());
        assertTrue(response.hasErrors());
        assertEquals("Description", response.getErrors().get(0).getField());
        assertEquals("must not be empty", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldNotDeleteProductErrorInName() {
        AddProductRequest request = new AddProductRequest("tv",
                "good tv", new BigDecimal("399.99"));
        getAddProductService().execute(request);

        DeleteByProductRequest requestToDelete = new DeleteByProductRequest(null,
                "good tv", new BigDecimal("399.99"));
        DeleteByProductResponse response = getDeleteProductByProductService().execute(requestToDelete);

        assertNull(response.getDeletedProduct());
        assertTrue(response.hasErrors());
        assertEquals("Name", response.getErrors().get(0).getField());
        assertEquals("must not be empty", response.getErrors().get(0).getMessage());
    }

    private DeleteProductByProductService getDeleteProductByProductService() {
        return applicationContext.getBean(DeleteProductByProductService.class);
    }

    private AddProductService getAddProductService() {
        return applicationContext.getBean(AddProductService.class);
    }
}
