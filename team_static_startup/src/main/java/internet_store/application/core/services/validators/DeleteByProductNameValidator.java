package internet_store.application.core.services.validators;

import internet_store.application.core.requests.DeleteByProductNameRequest;
import internet_store.application.core.responses.CoreError;
import internet_store.application.dependency_injection.DIComponent;

import java.util.ArrayList;
import java.util.List;

@DIComponent
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
