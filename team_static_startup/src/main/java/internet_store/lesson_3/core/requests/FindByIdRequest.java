package internet_store.lesson_3.core.requests;

public class FindByIdRequest {

    private String productId;

    public FindByIdRequest(String productId) {
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }

}
