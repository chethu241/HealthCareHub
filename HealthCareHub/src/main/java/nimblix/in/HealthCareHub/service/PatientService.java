package nimblix.in.HealthCareHub.service;

<<<<<<< HEAD
import nimblix.in.HealthCareHub.response.PatientSearchResponse;
import java.util.List;

public interface PatientService {

    List<PatientSearchResponse> searchPatients(String name, String phone, String bloodGroup);

}
=======

import nimblix.in.HealthCareHub.request.PatientRegistrationRequest;
import nimblix.in.HealthCareHub.response.PatientRegistrationResponse;

public interface PatientService {
    // existing methods ...

    PatientRegistrationResponse registerPatient(PatientRegistrationRequest request);
}
>>>>>>> 85c721cae814bca568a4f4be22485f9c88a6f4f5
