package dental_clinic_tests.acceptance_tests;

import dental_clinic.dependency_injection.ApplicationContext;
import dental_clinic.core.domain.PersonalData;
import dental_clinic.core.requests.AddPatientRequest;
import dental_clinic.core.requests.DeletePatientRequest;
import dental_clinic.core.requests.GetAllPatientsRequest;
import dental_clinic.core.responses.GetAllPatientsResponse;
import dental_clinic.core.services.AddPatientService;
import dental_clinic.core.services.DeletePatientService;
import dental_clinic.core.services.GetAllPatientsService;
import dental_clinic.dependency_injection.DIApplicationContextBuilder;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class AcceptanceTest2 {

    private ApplicationContext applicationContext =
            new DIApplicationContextBuilder().build("dental_clinic");

    @Test
    public void test(){
        PersonalData personalData1 = new PersonalData("Name", "Surname", "12345678", "25024512348");
        PersonalData personalData2 = new PersonalData("NameM", "SurnameM", "12345675", "25024512345");
        AddPatientRequest addPatientRequest1 = new AddPatientRequest(personalData1);
        AddPatientRequest addPatientRequest2 = new AddPatientRequest(personalData2);
        addPatientService().execute(addPatientRequest1);
        addPatientService().execute(addPatientRequest2);

        DeletePatientRequest deletePatientRequest = new DeletePatientRequest(8L);
        deletePatientService().execute(deletePatientRequest);

        GetAllPatientsRequest getAllPatientsRequest = new GetAllPatientsRequest();
        GetAllPatientsResponse getAllPatientsResponse = getAllPatientsService().execute(getAllPatientsRequest);

        assertTrue(getAllPatientsResponse.getPatients().size() == 2);
    }

    private AddPatientService addPatientService() {
        return applicationContext.getBean(AddPatientService.class);
    }

    private DeletePatientService deletePatientService(){
        return applicationContext.getBean(DeletePatientService.class);
    }

    private GetAllPatientsService getAllPatientsService() {
        return applicationContext.getBean(GetAllPatientsService.class);
    }
}
