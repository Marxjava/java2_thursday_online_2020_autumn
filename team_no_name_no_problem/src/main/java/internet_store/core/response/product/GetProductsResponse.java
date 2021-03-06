package internet_store.core.response.product;

import internet_store.core.domain.Product;
import internet_store.core.response.CoreError;
import internet_store.core.response.CoreResponse;

import java.util.List;

public class GetProductsResponse extends CoreResponse {

    private List<Product> products;

    public GetProductsResponse(List<Product>products){
        this.products = products;
    }

    public GetProductsResponse(List<CoreError> errors, List<Product>products){
        super(errors);
    }

    public List<Product> getProducts(){
        return products;
    }

}
