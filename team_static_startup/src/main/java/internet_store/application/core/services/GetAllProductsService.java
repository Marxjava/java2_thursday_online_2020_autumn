package internet_store.application.core.services;

import internet_store.application.core.domain.Product;
import internet_store.application.core.requests.GetAllProductsRequest;
import internet_store.application.core.responses.GetAllProductsResponse;
import internet_store.application.core.database.Database;
import internet_store.application.dependency_injection.DIComponent;
import internet_store.application.dependency_injection.DIDependency;

import java.util.List;

@DIComponent
public class GetAllProductsService {

    @DIDependency private Database database;

 public GetAllProductsResponse execute(GetAllProductsRequest request) {
        List<Product> productList = database.getProductList();
        return new GetAllProductsResponse(productList);
    }

}
