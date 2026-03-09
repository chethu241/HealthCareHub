package nimblix.in.HealthCareHub.serviceImpl;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import nimblix.in.HealthCareHub.model.Patient;
import nimblix.in.HealthCareHub.model.User;
import nimblix.in.HealthCareHub.repository.UserRepository;
import nimblix.in.HealthCareHub.request.PatientRegistrationRequest;
import nimblix.in.HealthCareHub.response.PatientRegistrationResponse;
import nimblix.in.HealthCareHub.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public PatientRegistrationResponse registerPatient(PatientRegistrationRequest request) {

        //  Checks if email already exists
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }

        // Checks password match
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new IllegalArgumentException("Password and Confirm Password do not match");
        }

        // Creates User
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(nimblix.in.HealthCareHub.model.Role.PATIENT);
        user.setEnabled(true);

        userRepository.save(user);

        //  Creates Patient linked to User
        Patient patient = new Patient();
        patient.setName(request.getFirstName() + " " + request.getLastName());
        patient.setGender(request.getGender());
        patient.setUser(user);

        entityManager.persist(patient);

        return new PatientRegistrationResponse(true, "Patient registered successfully");
    }
}