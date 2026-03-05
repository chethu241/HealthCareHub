package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
<<<<<<< HEAD
import nimblix.in.HealthCareHub.response.PatientSearchResponse;
import nimblix.in.HealthCareHub.service.PatientService;
import org.springframework.web.bind.annotation.GetMapping;
=======
import nimblix.in.HealthCareHub.request.PatientRegistrationRequest;
import nimblix.in.HealthCareHub.response.PatientRegistrationResponse;
import nimblix.in.HealthCareHub.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
>>>>>>> 85c721cae814bca568a4f4be22485f9c88a6f4f5
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/patient")
@RequiredArgsConstructor
public class PatientController {

<<<<<<< HEAD
    private final PatientService patientService;

    @GetMapping("/search")
    public List<PatientSearchResponse> searchPatients(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "phone", required = false) String phone,
            @RequestParam(value = "bloodGroup", required = false) String bloodGroup
    ) {
        return patientService.searchPatients(name, phone, bloodGroup);
    }
}
=======
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
>>>>>>> 85c721cae814bca568a4f4be22485f9c88a6f4f5
