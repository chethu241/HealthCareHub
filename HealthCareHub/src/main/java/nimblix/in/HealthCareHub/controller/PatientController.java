package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.constants.HealthCareConstants;
import nimblix.in.HealthCareHub.request.PatientRegistrationRequest;
import nimblix.in.HealthCareHub.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("api/patient")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    // This API is used to register a new patient in the system.
    // It accepts patient details such as name, email, gender, and password.
    // Creates a user account with PATIENT role and stores the patient information.
    // Returns 201 status with success response after successful registration.

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> registerPatient(
            @RequestBody PatientRegistrationRequest request) {

        Map<String, Object> response = new LinkedHashMap<>();

        try {

            patientService.registerPatient(request);

            Map<String, Object> data = new LinkedHashMap<>();
            data.put("success", true);

            response.put(HealthCareConstants.STATUS, HttpStatus.CREATED.value());
            response.put(HealthCareConstants.MESSAGE,
                    HealthCareConstants.PATIENT_REGISTERED_SUCCESSFULLY);
            response.put(HealthCareConstants.DATA, data);

            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (IllegalArgumentException e) {

            response.put("status", HttpStatus.BAD_REQUEST.value());
            response.put("message", e.getMessage());

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        catch (Exception e) {

            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("error", "Internal Server Error");
            response.put("message", "Unexpected error occurred");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}