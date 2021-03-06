package eu.retarded.internetstore.core.responses.category;

import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.CoreResponse;

import java.util.List;

public class DeleteProductFromCategoryResponse extends CoreResponse {
    private long categoryId;
    private long productId;

    public DeleteProductFromCategoryResponse(List<CoreError> errors) {
        super(errors);
    }

    public DeleteProductFromCategoryResponse(long categoryId, long productId) {
        this.categoryId = categoryId;
        this.productId = productId;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public long getProductIdId() {
        return productId;
    }
}

