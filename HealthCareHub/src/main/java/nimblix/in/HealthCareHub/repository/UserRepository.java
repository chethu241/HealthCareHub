package nimblix.in.HealthCareHub.repository;

import nimblix.in.HealthCareHub.model.User;
import nimblix.in.HealthCareHub.response.PatientSearchResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
<<<<<<< HEAD

    @Query("""
            SELECT new nimblix.in.HealthCareHub.response.PatientSearchResponse(
                p.id,
                p.name,
                p.age,
                p.gender,
                p.phone,
                p.disease
            )
            FROM Patient p
            WHERE
            (:name IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%')))
            AND (:phone IS NULL OR p.phone = :phone)
            """)
    List<PatientSearchResponse> searchPatients(
            @Param("name") String name,
            @Param("phone") String phone
    );
}
=======
    boolean existsByEmail(String email);
}
>>>>>>> 85c721cae814bca568a4f4be22485f9c88a6f4f5
