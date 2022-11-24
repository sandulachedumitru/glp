package com.glpserver.glp.service.impl;

import com.glpserver.glp.domain.dto.StudentDto;
import com.glpserver.glp.domain.entity.StudentEntity;
import com.glpserver.glp.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Dumitru Săndulache (sandulachedumitru@hotmail.com)
 */

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
	@Override
	public Optional<StudentEntity> createNewStudent(StudentDto studentDto) {
		return Optional.empty();
	}

	@Override
	public Optional<List<StudentEntity>> findAllStudents() {
		return Optional.empty();
	}

	@Override
	public Optional<StudentEntity> findStudentById(Long studentId) {
		return Optional.empty();
	}

	@Override
	public Optional<StudentEntity> findStudentByEmail(String studentEmail) {
		return Optional.empty();
	}

	@Override
	public Optional<StudentEntity> findStudentByFirstNameAndLastName(String studentFirstName, String studentLastName) {
		return Optional.empty();
	}

	@Override
	public Optional<StudentEntity> deleteStudentById(Long studentId) {
		return Optional.empty();
	}

	@Override
	public Optional<StudentEntity> deleteStudentByEmail(String studentEmail) {
		return Optional.empty();
	}

	@Override
	public Optional<StudentEntity> deleteStudentByFirstNameAndLastName(String studentFirstName, String studentLastName) {
		return Optional.empty();
	}

	@Override
	public Optional<StudentEntity> updateStudentById(Long studentId) {
		return Optional.empty();
	}

	@Override
	public Optional<StudentEntity> updateStudentByEmail(String studentEmail) {
		return Optional.empty();
	}

	@Override
	public Optional<StudentEntity> updateStudentByFirstNameAndLastName(String studentFirstName, String studentLastName) {
		return Optional.empty();
	}
}
