package internet_store.lesson_4.core.database.services;

import internet_store.lesson_4.core.database.Database;
import internet_store.lesson_4.core.database.InMemoryDatabase;
import internet_store.lesson_4.core.domain.Product;
import internet_store.lesson_4.core.responses.PrintProductsToConsoleResponse;
import internet_store.lesson_4.core.services.GetProductListService;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class GetProductListServiceTest {

    Database database;

    @Before
    public void setUp() {
        database = new InMemoryDatabase();
    }

    @Test
    public void shouldReturnProductList() {
        database.add(new Product("iphone12", "mobile phone", new BigDecimal("900.00")));
        database.add(new Product("imac", "pc", new BigDecimal("4000.00")));
        GetProductListService service = new GetProductListService(database);
        PrintProductsToConsoleResponse response = service.getProductList();

        assertEquals(database.getProductList(), response.getProductList());
    }

    @Test
    public void shouldReturnProductList_whenItIsEmpty() {
        GetProductListService service = new GetProductListService(database);
        PrintProductsToConsoleResponse response = service.getProductList();
        assertEquals(database.getProductList(), response.getProductList());
    }
}