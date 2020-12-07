package internet_store.core.service.product;

import internet_store.core.core_error.CoreError;
import internet_store.core.domain.Product;
import internet_store.core.request.product.DeleteProductRequest;
import internet_store.core.response.product.DeleteProductResponse;
import internet_store.core.validate.NegativeNumberValidator;
import internet_store.database.product_database.InnerProductDatabase;
import dependency.annotation.DIComponent;
import dependency.annotation.DIDependency;

import java.util.List;

@DIComponent
public class DeleteProductService {
    @DIDependency
    InnerProductDatabase productDatabase;

    public DeleteProductService() {
    }

    public DeleteProductService(InnerProductDatabase productDatabase) {
        this.productDatabase = productDatabase;
    }

    public DeleteProductResponse execute(DeleteProductRequest deleteProductRequest) {
        NegativeNumberValidator<?> negativeNumberValidator = new NegativeNumberValidator<>(deleteProductRequest.getId());

        List<CoreError> errors = negativeNumberValidator.validate();

        if (isIdExist(deleteProductRequest.getId())) {
            Product deletedProduct = findProductById(deleteProductRequest.getId());
            productDatabase.deleteProduct(deletedProduct);
        } else {
            errors.add(new CoreError("Id error ", "wrong ID"));
        }

        if (errors.isEmpty()) {
            return new DeleteProductResponse(deleteProductRequest.getId());
        }
        return new DeleteProductResponse(errors);
    }

    private boolean isIdExist(long id) {
        return productDatabase.isIdExist(id);
    }

    private Product findProductById(long id) {
        return productDatabase.findById(id);
    }
}