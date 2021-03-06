package internet_store.lesson_5.core.services.validators;

import internet_store.lesson_5.core.requests.DeleteByProductNameRequest;
import internet_store.lesson_5.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

public class DeleteByProductNameValidator {

    public List<CoreError> validate (DeleteByProductNameRequest request){
        List<CoreError> errors = new ArrayList<>();

        String productName = request.getProductName();
        if(productName == null || productName.isEmpty()){
            errors.add(new CoreError("Product name", "must not be empty"));
        }
        return errors;
    }

}
