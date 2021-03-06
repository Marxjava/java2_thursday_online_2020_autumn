package internet_store.core.service.cart;

import internet_store.core.core_error.CoreError;
import internet_store.core.domain.Product;
import internet_store.core.request.cart.DeleteProductFromCartRequest;
import internet_store.core.response.cart.DeleteProductFromCartResponse;
import internet_store.core.validate.NegativeNumberValidator;
import internet_store.database.cart_database.InnerCartDatabase;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Component
public class DeleteProductFromCartService {
    @Autowired
    InnerCartDatabase cartDatabase;

    public DeleteProductFromCartService(InnerCartDatabase cartDatabase) {
        this.cartDatabase = cartDatabase;
    }

    public DeleteProductFromCartResponse execute(DeleteProductFromCartRequest deleteProductFromCartRequest) {
        NegativeNumberValidator<?> negativeNumberValidator = new NegativeNumberValidator<>
                (deleteProductFromCartRequest.getId());

        List<CoreError> errors = negativeNumberValidator.validate();

        if (isIdExist(deleteProductFromCartRequest.getId())) {
            Product deletedProduct = findProductById(deleteProductFromCartRequest.getId());
            cartDatabase.deleteProductFromCart(deletedProduct);
        } else {
            errors.add(new CoreError("Id error ", "wrong ID"));
        }

        if (errors.isEmpty()) {
            return new DeleteProductFromCartResponse(deleteProductFromCartRequest.getId());
        }
        return new DeleteProductFromCartResponse(errors);
    }

    private boolean isIdExist(long id) {
        return cartDatabase.isIdExist(id);
    }

    private Product findProductById(long id) {
        return cartDatabase.findById(id);
    }
}