package estore.core.service;

import estore.database.ProductDataBase;
import estore.domain.Product;
import estore.core.requests.SearchProductByNameRequest;
import estore.core.responses.SearchProductByNameResponse;

import java.util.List;

public class SearchProductByNameService {

    private ProductDataBase database;

    public SearchProductByNameService(ProductDataBase database) {
        this.database = database;
    }

    public SearchProductByNameResponse execute(SearchProductByNameRequest request) {
        List<Product> foundProducts = database.searchProductByName(request.getProductName());
        return new SearchProductByNameResponse(foundProducts);
    }

}
