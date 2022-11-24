package com.glpserver.glp.repository;

import com.glpserver.glp.domain.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;

/**
 * @author Dumitru Săndulache (sandulachedumitru@hotmail.com)
 */

public interface StudentEntityRepository extends JpaRepository<StudentEntity, Long> {
//	Optional<StudentEntity> findStudentEntityByFirstNameAndLastName(@NonNull String studentFirstName, @NonNull String studentLastName);
//	Optional<StudentEntity> findStudentEntityById(@NonNull Long studentId);
//	Optional<StudentEntity> findStudentEntityByEmail(@NonNull String email);
}
