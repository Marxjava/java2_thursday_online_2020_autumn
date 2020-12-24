package team_VK.application.core.services.standart_validators;

import team_VK.application.core.responses.CoreError;
import team_VK.application.database.DIComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
@DIComponent
public class ClientLastNameFieldValidator {

    public List<CoreError> validate (String clientLastName){

        List<CoreError> errors = new ArrayList<>();

        if (clientLastName == null || clientLastName.equals("")) {
            CoreError error = new CoreError("clientLastName", "Field clientLastName must be not empty");
            errors.add(error);
        }

        if (clientLastName.trim().equals("") && !clientLastName.equals("")) {
            CoreError error = new CoreError("clientLastName", "Field clientLastName can't be Space");
            errors.add(error);
        }

        if (clientLastName.trim().length() <= 3 && !clientLastName.trim().equals("")) {
            CoreError error = new CoreError("clientLastName", "Field clientLastName is too short");
            errors.add(error);
        }

        IntStream streamClientLastName = clientLastName.chars();
        boolean clientLastNameContainsIllegalCharts = streamClientLastName
                .anyMatch(value -> (value <= 31) ||
                        ((value >= 33) && (value <= 44)) ||
                        ((value >= 46) && (value <= 64)) ||
                        ((value >= 91) && (value <= 96)) ||
                        ((value >= 123) && (value <= 159)) ||
                        ((value >= 174) && (value <= 180)) ||
                        ((value >= 185) && (value <= 207)) ||
                        ((value >= 217) && (value <= 220)) ||
                        (value == 223) ||
                        (value >= 253));
        if (clientLastNameContainsIllegalCharts) {
            CoreError error = new CoreError("clientLastName", "Field clientLastName contains illegal characters");
            errors.add(error);
        }
        return errors;
    }

}
