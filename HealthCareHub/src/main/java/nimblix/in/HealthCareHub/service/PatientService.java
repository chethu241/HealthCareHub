package nimblix.in.HealthCareHub.service;


import nimblix.in.HealthCareHub.request.PatientRegistrationRequest;
import nimblix.in.HealthCareHub.response.PatientRegistrationResponse;

public interface PatientService {
    // existing methods ...

    PatientRegistrationResponse registerPatient(PatientRegistrationRequest request);
}
