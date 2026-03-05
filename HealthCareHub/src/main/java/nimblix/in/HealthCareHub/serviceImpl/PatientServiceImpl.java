package nimblix.in.HealthCareHub.serviceImpl;

<<<<<<< HEAD
import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.repository.UserRepository;
import nimblix.in.HealthCareHub.response.PatientSearchResponse;
import nimblix.in.HealthCareHub.service.PatientService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final UserRepository userRepository;

    @Override
    public List<PatientSearchResponse> searchPatients(String name, String phone, String bloodGroup) {
        return userRepository.searchPatients(name, phone);
=======
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

        // 1️⃣ Check if email exists
        if(userRepository.findByEmail(request.getEmail()).isPresent()) {
            return new PatientRegistrationResponse(false, "Email already exists");
        }

        // 2️⃣ Check password match
        if(!request.getPassword().equals(request.getConfirmPassword())) {
            return new PatientRegistrationResponse(false, "Password and Confirm Password do not match");
        }

        // 3️⃣ Create User
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(nimblix.in.HealthCareHub.model.Role.PATIENT);
        user.setEnabled(true);  // required for login


        userRepository.save(user);

        // 4️⃣ Create Patient linked to User
        Patient patient = new Patient();
        patient.setName(request.getFirstName() + " " + request.getLastName());
        patient.setGender(request.getGender());
        patient.setUser(user);

        entityManager.persist(patient);

        return new PatientRegistrationResponse(true, "Registration successful");
>>>>>>> 85c721cae814bca568a4f4be22485f9c88a6f4f5
    }
}