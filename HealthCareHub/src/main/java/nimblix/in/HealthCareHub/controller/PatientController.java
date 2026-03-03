package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.request.PatientRegistrationRequest;
import nimblix.in.HealthCareHub.response.PatientRegistrationResponse;
import nimblix.in.HealthCareHub.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/patient")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService; // injected via constructor

    @PostMapping("/register")
    public ResponseEntity<PatientRegistrationResponse> register(@RequestBody PatientRegistrationRequest request) {
        PatientRegistrationResponse response = patientService.registerPatient(request);

        if (!response.isSuccess()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        return ResponseEntity.ok(response);
    }

}
