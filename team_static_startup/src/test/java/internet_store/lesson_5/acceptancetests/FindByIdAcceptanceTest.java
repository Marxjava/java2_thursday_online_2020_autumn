package internet_store.lesson_5.acceptancetests;

import internet_store.lesson_5.ApplicationContext;
import internet_store.lesson_5.core.database.Database;
import internet_store.lesson_5.core.domain.Product;
import internet_store.lesson_5.core.requests.FindByIdRequest;
import internet_store.lesson_5.core.responses.FindByIdResponse;
import internet_store.lesson_5.core.services.FindByIdService;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.*;

public class FindByIdAcceptanceTest {

    private ApplicationContext appContext = new ApplicationContext();
    private Database database;

    @Before
    public void setUp() {
        database = getDatabase();
        database.add(new Product("iPhone", "phone", new BigDecimal("900")));
        database.add(new Product("iMac", "pc", new BigDecimal("4000")));
    }

    @Test
    public void shouldFindById() {
        FindByIdRequest request = new FindByIdRequest("2");
        FindByIdResponse response = getFindByIdService().execute(request);

        assertFalse(response.hasErrors());
        assertFalse(response.getProductFoundById().isEmpty());
        assertEquals(Optional.of(
                newProduct(2L, "iMac", "pc", new BigDecimal("4000"))),
                response.getProductFoundById());
    }

    @Test
    public void shouldNotFindWhenIdIsNotExist() {
        FindByIdRequest request = new FindByIdRequest("5");
        FindByIdResponse response = getFindByIdService().execute(request);

        assertFalse(response.hasErrors());
        assertEquals(Optional.empty(),
                response.getProductFoundById());
    }

    @Test
    public void shouldReturnErrorWhenIdIsEmpty() {
        FindByIdRequest request = new FindByIdRequest("");
        FindByIdResponse response = getFindByIdService().execute(request);

        assertTrue(response.hasErrors());
        assertEquals(1, response.getErrors().size());
        assertEquals("Product ID", response.getErrors().get(0).getField());
        assertEquals("Should not be empty.", response.getErrors().get(0).getMessage());
    }

    private Product newProduct(Long id, String name, String description, BigDecimal price) {
        Product product = new Product(name, description, price);
        product.setId(id);
        return product;
    }

    private Database getDatabase() {
        return appContext.getBean(Database.class);
    }

    private FindByIdService getFindByIdService() {
        return appContext.getBean(FindByIdService.class);
    }
}