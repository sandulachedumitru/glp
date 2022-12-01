package com.glpserver.glp.service;

import com.glpserver.glp.domain.dto.StudentDto;
import com.glpserver.glp.domain.entity.StudentEntity;
import org.springframework.lang.NonNull;

import javax.validation.constraints.Email;
import java.util.List;
import java.util.Optional;

/**
 * @author Dumitru Săndulache (sandulachedumitru@hotmail.com)
 */

public interface StudentService {
	Optional<StudentEntity> createNewStudent(@NonNull StudentDto studentDto);

	Optional<List<StudentEntity>> findAllStudents();
	Optional<StudentEntity> findStudentById(@NonNull Long studentId);
	Optional<StudentEntity> findStudentByEmail(@NonNull @Email String studentEmail);
	Optional<StudentEntity> findStudentByFirstNameAndLastName(@NonNull String studentFirstName, @NonNull String studentLastName);

	Optional<StudentEntity> deleteStudentById(@NonNull Long studentId);
	Optional<StudentEntity> deleteStudentByEmail(@NonNull String studentEmail);
	Optional<StudentEntity> deleteStudentByFirstNameAndLastName(@NonNull String studentFirstName, @NonNull String studentLastName);

	Optional<StudentEntity> updateStudent(@NonNull  StudentDto studentDto);
}
