package eu.retarded.internetstore.core.requests.product;

public class DeleteProductRequest {

    private final long productIdToDelete;

    public DeleteProductRequest(long productIdToDelete) {
        this.productIdToDelete = productIdToDelete;
    }

    public long getProductIdToDelete() {
        return productIdToDelete;
    }
}
