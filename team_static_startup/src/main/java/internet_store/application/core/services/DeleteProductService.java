package internet_store.application.core.services;

import internet_store.application.core.domain.Product;
import internet_store.application.core.requests.DeleteByProductNameRequest;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.DeleteByProductNameResponse;
import internet_store.application.database.Database;

import java.util.List;

public class DeleteProductService {

    private final Database database;
    private DeleteByProductNameValidator validator;

    public DeleteProductService(Database database, DeleteByProductNameValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public DeleteByProductNameResponse deleteByProductName(DeleteByProductNameRequest productNameRequest) {
        List<CoreError> errors = validator.validate(productNameRequest);
        if (!errors.isEmpty()){
            return new DeleteByProductNameResponse(errors);
        }

        boolean isRemoved = database.deleteByProductName(productNameRequest.getProductName());

        return new DeleteByProductNameResponse(isRemoved);
    }

    public boolean delete(Product product) {
        return database.delete(product);
    }

    public boolean delete(Long productIdLong) {
        return database.delete(productIdLong);
    }


}
