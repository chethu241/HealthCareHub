package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.constants.HealthCareConstants;
import nimblix.in.HealthCareHub.model.Patient;
import nimblix.in.HealthCareHub.request.PatientRegistrationRequest;
import nimblix.in.HealthCareHub.response.PatientRegistrationResponse;
import nimblix.in.HealthCareHub.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/patient")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService; // injected via constructor

//This API is used to register a new patient in the system.It accepts patient details such as name, email, gender, and password.
// creates a user account with the PATIENT role, and stores the patient information in the database.
    //On successful registration, it returns a 201 status with a success response.

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> registerPatient(
            @RequestBody PatientRegistrationRequest request) {

        Map<String, Object> response = new HashMap<>();

        try {

            patientService.registerPatient(request);

            Map<String, Object> data = new HashMap<>();
            data.put("success", true);

            response.put(HealthCareConstants.STATUS, HttpStatus.CREATED.value());
            response.put(HealthCareConstants.MESSAGE,
                    HealthCareConstants.PATIENT_REGISTERED_SUCCESSFULLY);
            response.put(HealthCareConstants.DATA, data);

            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (Exception e) {

            response.put(HealthCareConstants.STATUS, HttpStatus.BAD_REQUEST.value());
            response.put(HealthCareConstants.MESSAGE, "Patient registration failed");

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
