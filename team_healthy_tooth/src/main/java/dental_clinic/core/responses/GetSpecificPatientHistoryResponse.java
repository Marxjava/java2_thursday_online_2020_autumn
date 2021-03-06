package dental_clinic.core.responses;

import dental_clinic.core.domain.Patient;

import java.util.List;
import java.util.Optional;

public class GetSpecificPatientHistoryResponse extends CoreResponse{

    private Optional<Patient> specificPatient;

    public GetSpecificPatientHistoryResponse (Optional<Patient> specificPatient){
        this.specificPatient = specificPatient;
    }

    public GetSpecificPatientHistoryResponse (List<CoreError> errors){
        super(errors);
    }

    public Optional<Patient>getSpecificPatient(){
        return specificPatient;
    }

}
