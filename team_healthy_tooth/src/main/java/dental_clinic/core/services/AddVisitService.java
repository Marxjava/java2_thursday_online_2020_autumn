package dental_clinic.core.services;

import dental_clinic.core.requests.AddVisitRequest;
import dental_clinic.core.responses.AddVisitResponse;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.services.validators.AddVisitValidator;
import dental_clinic.database.PatientDatabase;
import dental_clinic.core.domain.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddVisitService {

    @Autowired
    private PatientDatabase patientDatabase;
    @Autowired private AddVisitValidator addVisitValidator;

    public AddVisitResponse execute(AddVisitRequest addVisitRequest){

        List<CoreError> errors = addVisitValidator.validate(addVisitRequest);

        if (!errors.isEmpty()){
            return new AddVisitResponse(errors);
        }

        Visit visit = new Visit(addVisitRequest.getToothNumber(), addVisitRequest.getComment(),
                addVisitRequest.getToothStatus(), addVisitRequest.getDoctor(), addVisitRequest.getDate());

        if (patientDatabase.containsPatientWithSpecificId(addVisitRequest.getId())){
            for (int i = 0; i < patientDatabase.getPatients().size(); i++) {
                if (isSpecificPatient(i, addVisitRequest.getId())) {
                    patientDatabase.getPatients().get(i).addVisit(visit);
                    patientDatabase.getPatients().get(i).updateJowl(addVisitRequest.getToothNumber(), addVisitRequest.getToothStatus());
                    return new AddVisitResponse();
                }
            }
        }

        errors.add(new CoreError("id", "Database doesnt't contain patient with id " + addVisitRequest.getId()));
        return new AddVisitResponse(errors);

    }

    private boolean isSpecificPatient (int index, long id) {
        return patientDatabase.getPatients().get(index).getPersonalData().getId().equals(id);
    }
}
