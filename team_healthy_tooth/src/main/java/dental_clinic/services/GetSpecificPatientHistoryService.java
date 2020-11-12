package dental_clinic.services;

import dental_clinic.database.PatientDatabase;
import dental_clinic.domain.Patient;

import java.util.Optional;

public class GetSpecificPatientHistoryService {

    private final PatientDatabase patientDatabase;

    public GetSpecificPatientHistoryService(PatientDatabase patientDatabase) {
        this.patientDatabase = patientDatabase;
    }

    public Optional<Patient> execute(long id){
        for (int i = 0; i < patientDatabase.getPatients().size(); i++){
            if (isSpecificPatient(i, id)){
                return Optional.of(patientDatabase.getPatients().get(i));
            }
        }
        return Optional.empty();
    }

    private boolean isSpecificPatient (int index, long id) {
        return patientDatabase.getPatients().get(index).getPersonalData().getId() == id;
    }
}
