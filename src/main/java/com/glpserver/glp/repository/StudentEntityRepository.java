package com.glpserver.glp.repository;

import com.glpserver.glp.domain.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import javax.validation.constraints.Email;
import java.util.Optional;

/**
 * @author Dumitru Săndulache (sandulachedumitru@hotmail.com)
 */

public interface StudentEntityRepository extends JpaRepository<StudentEntity, Long> {
	Optional<StudentEntity> findStudentByEmail(@NonNull @Email String studentEmail);
	Optional<StudentEntity> findStudentByFirstNameAndLastName(@NonNull String studentFirstName, @NonNull String studentLastName);

	Optional<StudentEntity> deleteStudentByEmail(@NonNull String studentEmail);
	Optional<StudentEntity> deleteStudentByFirstNameAndLastName(@NonNull String studentFirstName, @NonNull String studentLastName);
}
