package internet_store.core.service.product;

import internet_store.core.core_error.CoreError;
import internet_store.core.request.product.product_items.AddProductDescriptionRequest;
import internet_store.core.response.product.product_item.AddProductDescriptionResponse;
import internet_store.core.validate.StringTypeValidator;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddProductDescriptionService {
    public AddProductDescriptionResponse execute(AddProductDescriptionRequest addProductDescriptionRequest) {
        StringTypeValidator stringTypeValidator = new StringTypeValidator();
        List<CoreError> errors = stringTypeValidator.validate(addProductDescriptionRequest.getProductDescription());

        if (errors.isEmpty()) {
            return new AddProductDescriptionResponse(addProductDescriptionRequest.getProductDescription());
        }
        return new AddProductDescriptionResponse(errors);
    }
}