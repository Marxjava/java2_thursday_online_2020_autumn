package dental_clinic.acceptance_tests;

import dental_clinic.ApplicationContext;
import dental_clinic.core.domain.PersonalData;
import dental_clinic.core.requests.AddPatientRequest;
import dental_clinic.core.requests.ChangePersonalDataRequest;
import dental_clinic.core.requests.GetAllPatientsRequest;
import dental_clinic.core.requests.GetPatientCardRequest;
import dental_clinic.core.responses.GetAllPatientsResponse;
import dental_clinic.core.responses.GetPatientCardResponse;
import dental_clinic.core.services.AddPatientService;
import dental_clinic.core.services.ChangePersonalDataService;
import dental_clinic.core.services.GetAllPatientsService;
import dental_clinic.core.services.GetPatientCardService;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AcceptanceTest6 {

    private ApplicationContext applicationContext = new  ApplicationContext();

    @Test
    public void test(){
        PersonalData personalData1 = new PersonalData("Name", "Surname", "12345678", "25038910523");
        PersonalData personalData2 = new PersonalData("NameC", "SurnameC", "12345679", "25038910525");

        AddPatientRequest addPatientRequest1 = new AddPatientRequest(personalData1);
        addPatientService().execute(addPatientRequest1);

        AddPatientRequest addPatientRequest2 = new AddPatientRequest(personalData2);
        addPatientService().execute(addPatientRequest2);

        ChangePersonalDataRequest changePersonalDataRequest = new ChangePersonalDataRequest(1L, "SurnameB", "");
        changePersonalDataService().execute(changePersonalDataRequest);

        GetPatientCardRequest getPatientCardRequest = new GetPatientCardRequest(1L);
        GetPatientCardResponse getPatientCardResponse = getPatientCardService().execute(getPatientCardRequest);

        assertFalse(getPatientCardResponse.hasErrors());
        assertTrue(getPatientCardResponse.getPatient().getPersonalData().getSurname().equals("SurnameB"));
    }

    private AddPatientService addPatientService() {
        return applicationContext.getBean(AddPatientService.class);
    }

    private ChangePersonalDataService changePersonalDataService(){
        return applicationContext.getBean(ChangePersonalDataService.class);
    }

    private GetPatientCardService getPatientCardService() {
        return applicationContext.getBean(GetPatientCardService.class);
    }

}
