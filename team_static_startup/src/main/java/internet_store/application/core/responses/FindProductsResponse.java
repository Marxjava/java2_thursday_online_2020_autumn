package internet_store.application.core.responses;

import internet_store.application.core.domain.Product;

import java.util.List;

public class FindProductsResponse extends CoreResponse {
    private final List<Product> products;

    public FindProductsResponse(List<Product> products, List<CoreError> errors) {
        super(errors);
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

}
