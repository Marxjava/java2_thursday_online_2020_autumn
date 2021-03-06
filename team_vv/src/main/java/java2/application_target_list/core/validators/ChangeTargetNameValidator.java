package java2.application_target_list.core.validators;

import java2.application_target_list.core.database.Database;
import java2.application_target_list.core.requests.ChangeTargetNameRequest;
import org.springframework.stereotype.Component;

import java2.application_target_list.core.responses.CoreError;
import java.util.ArrayList;
import java.util.List;

@Component
public class ChangeTargetNameValidator {

    public List<CoreError> validate(ChangeTargetNameRequest request, Database database) {
        List<CoreError> errors = new ArrayList<>();

        if (!database.isIdInTargetList(request.getTargetIdToChange())){
            errors.add(new CoreError("Target ID;","no target with that ID"));
        }

        if (isTargetIdEmpty(request)){
            errors.add(new CoreError("Target ID","must not be empty!"));
        }
        if (isTargetIdNegative(request)){
            errors.add(new CoreError("Target ID","must not be negative!"));
        }

        if (isTargetNameEmpty(request)){
            errors.add(new CoreError("Target new name","must not be empty!"));
        }

        return errors;
    }

    private boolean isTargetNameEmpty(ChangeTargetNameRequest request) {
        return request.getNewTargetName() == null || request.getNewTargetName().isEmpty();
    }

    private boolean isTargetIdEmpty(ChangeTargetNameRequest request) {
        return request.getTargetIdToChange() == null;
    }

    private boolean isTargetIdNegative(ChangeTargetNameRequest request){
        return request.getTargetIdToChange() < 0;
    }


}
