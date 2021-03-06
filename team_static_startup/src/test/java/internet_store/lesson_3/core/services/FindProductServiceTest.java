package internet_store.lesson_3.core.services;

import internet_store.lesson_3.core.domain.Product;
import internet_store.lesson_3.core.requests.FindByProductNameRequest;
import internet_store.lesson_3.core.responses.CoreError;
import internet_store.lesson_3.core.responses.FindByProductNameResponse;
import internet_store.lesson_3.core.services.FindByProductNameService;
import internet_store.lesson_3.core.services.validators.FindProductValidator;
import internet_store.lesson_3.database.Database;
import internet_store.lesson_3.database.InMemoryDatabase;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FindProductServiceTest {
    FindByProductNameService findByProductNameService;
    FindProductValidator validator;
    Database database;

    @Before
    public void setUp() {
        database = new InMemoryDatabase();
        validator = new FindProductValidator();
        findByProductNameService = new FindByProductNameService(database, validator);
    }

    @Test
    public void shouldFindProductByProductName() {
        database.add(new Product("tv", "good tv", new BigDecimal("499.99")));
        database.add(new Product("tv", "good tv, good", new BigDecimal("399.99")));
        FindByProductNameRequest request = new FindByProductNameRequest("tv");
        FindByProductNameResponse response = findByProductNameService.findByProductName(request);
        List<Product> testArray = response.getProductNameList();
        assertEquals(2, testArray.size());
    }

    @Test
    public void shouldThrowErrorFindProductByProductName() {
        FindByProductNameRequest request = new FindByProductNameRequest(null);
        FindByProductNameResponse response = findByProductNameService.findByProductName(request);
        List<CoreError> errorList = response.getCoreErrorList();
        assertEquals(1, errorList.size());
        assertEquals("Product Name", errorList.get(0).getField());
        assertEquals("Should be valid and not empty.", errorList.get(0).getMessage());
    }


/*    @Test
    public void findById() {
        Product productTV = new Product("tv", "good tv", new BigDecimal("499.99"));
        database.add(productTV);
        Product productToFind = findProductService.findById(1L).get();
        assertEquals(productTV, productToFind);
    }*/

}