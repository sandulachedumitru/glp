package com.glpserver.glp.service.impl;

import com.glpserver.glp.domain.dto.StudentDto;
import com.glpserver.glp.domain.entity.StudentEntity;
import com.glpserver.glp.domain.mapper.StudentMapper;
import com.glpserver.glp.repository.StudentEntityRepository;
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
	private final StudentEntityRepository studentRepo;
	private final StudentMapper studentMapper;

	@Override
	public Optional<StudentEntity> createNewStudent(StudentDto studentDto) {
		log.info("Create new student ...");
		if (studentDto == null) {
			log.error("Creating new student: FAILED [accountDto:null]");
			return Optional.empty();
		}

		studentDto.setId(null); // it's a creation not an update

		var studentEntity = studentMapper.toEntity(studentDto);
		if (studentEntity != null) {
			studentRepo.save(studentEntity);
			log.info("Creating student: SUCCESSFUL : {}", studentMapper.toDto(studentEntity));
			return Optional.of(studentEntity);
		}

		log.info("Creating student: FAILED [DTO -> ENTITY : failed]");
		return Optional.empty();
	}

	@Override
	public Optional<List<StudentEntity>> findAllStudents() {
		log.info("Find all student ...");

		var studentEntities = studentRepo.findAll();
		log.info("Finding all students: SUCCESSFUL : {}", studentEntities);

		return Optional.of(studentEntities);
	}

	@Override
	public Optional<StudentEntity> findStudentById(Long studentId) {
		log.info("Find student by ID ...");

		if (studentId == null) {
			log.error("Finding student: FAILED [studentId:null]");
			return Optional.empty();
		}

		var studentEntityOpt = studentRepo.findById(studentId);
		if (studentEntityOpt.isPresent())
			log.info("Finding student: SUCCESSFUL [ID=[{}] found in DB]", studentEntityOpt.get().getId());
		else log.error("Finding student: FAILED [ID=[{}] not found in DB]", studentId);

		return studentEntityOpt;
	}

	@Override
	public Optional<StudentEntity> findStudentByEmail(String studentEmail) {
		log.info("Find student by email ...");

		if (studentEmail == null) {
			log.error("Finding student: FAILED [studentEmail:null]");
			return Optional.empty();
		}

		var studentEntityOpt = studentRepo.findStudentByEmail(studentEmail);
		if (studentEntityOpt.isPresent())
			log.info("Finding student: SUCCESSFUL [email=[{}] found in DB]", studentEntityOpt.get().getEmail());
		else log.error("Finding student: FAILED [email=[{}] not found in DB]", studentEmail);

		return studentEntityOpt;
	}

	@Override
	public Optional<StudentEntity> findStudentByFirstNameAndLastName(String studentFirstName, String studentLastName) {
		log.info("Find student by first name and last name ...");

		if (studentFirstName == null || studentLastName == null) {
			log.error("Finding student: FAILED [studentFirstName:null OR studentLastName:null]");
			return Optional.empty();
		}

		var studentEntityOpt = studentRepo.findStudentByFirstNameAndLastName(studentFirstName, studentLastName);
		if (studentEntityOpt.isPresent())
			log.info("Finding student: SUCCESSFUL [student name=[{} {}] found in DB]", studentEntityOpt.get().getFirstName(), studentEntityOpt.get().getLastName());
		else log.error("Finding student: FAILED [student name=[{} {}] not found in DB]", studentFirstName, studentLastName);

		return studentEntityOpt;
	}

	@Override
	public Optional<StudentEntity> deleteStudentById(Long studentId) {
		log.info("Delete student by ID ...");

		if (studentId == null) {
			log.info("Deleting student: FAILED [studentId:null]");
			return Optional.empty();
		}

		var studentEntityOpt = studentRepo.findById(studentId);
		if (studentEntityOpt.isPresent()) {
			studentRepo.deleteById(studentId);
			log.info("Deleting student: SUCCESSFUL [ID={}]", studentEntityOpt.get().getId());
		} else log.info("Deleting student: FAILED [ID={} not found ]", studentId);

		return studentEntityOpt;
	}

//	@Override
//	public Optional<StudentEntity> deleteStudentByEmail(String studentEmail) {
//		log.info("Delete student by email ...");
//
//		if (studentEmail == null) {
//			log.info("Deleting student: FAILED [studentEmail:null]");
//			return Optional.empty();
//		}
//
//		Optional<StudentEntity> studentEntityOpt = studentRepo.findStudentByEmail(studentEmail);
//		if (studentEntityOpt.isPresent()) {
//			studentRepo.deleteById(studentEntityOpt.get().getId());
//			log.info("Deleting student: SUCCESSFUL [student email=[{}]", studentEntityOpt.get().getId());
//		} else log.info("Deleting student: FAILED [student email=[{}] not found ]", studentEmail);
//
//		return studentEntityOpt;
//	}

//	@Override
//	public Optional<StudentEntity> deleteStudentByFirstNameAndLastName(String studentFirstName, String studentLastName) {
//		log.info("Delete student by first name and last name ...");
//
//		if (studentFirstName == null || studentLastName == null) {
//			log.info("Deleting student: FAILED [studentFirstName:null OR studentLastName:null]");
//			return Optional.empty();
//		}
//
//		Optional<StudentEntity> studentEntityOpt = studentRepo.findStudentByFirstNameAndLastName(studentFirstName, studentLastName);
//		if (studentEntityOpt.isPresent()) {
//			studentRepo.deleteById(studentEntityOpt.get().getId());
//			log.info("Deleting student: SUCCESSFUL [student name=[{} {}]", studentEntityOpt.get().getFirstName(), studentEntityOpt.get().getFirstName());
//		} else log.info("Deleting student: FAILED [student name=[{} {}] not found ]", studentFirstName, studentLastName);
//
//		return studentEntityOpt;
//	}

	@Override
	public Optional<StudentEntity> deleteStudentByEmail(String studentEmail) {
		log.info("Delete student by email ...");

		if (studentEmail == null) {
			log.info("Deleting student: FAILED [studentEmail:null]");
			return Optional.empty();
		}

		var studentEntityOpt = studentRepo.deleteStudentByEmail(studentEmail);
		if (studentEntityOpt.isPresent())
			log.info("Deleting student: SUCCESSFUL [student email=[{}]", studentEntityOpt.get().getId());
		else log.info("Deleting student: FAILED [student email=[{}] not found ]", studentEmail);

		return studentEntityOpt;
	}

	@Override
	public Optional<StudentEntity> deleteStudentByFirstNameAndLastName(String studentFirstName, String studentLastName) {
		log.info("Delete student by first name and last name ...");

		if (studentFirstName == null || studentLastName == null) {
			log.info("Deleting student: FAILED [studentFirstName:null OR studentLastName:null]");
			return Optional.empty();
		}

		var studentEntityOpt = studentRepo.deleteStudentByFirstNameAndLastName(studentFirstName, studentLastName);
		if (studentEntityOpt.isPresent())
			log.info("Deleting student: SUCCESSFUL [student name=[{} {}]", studentEntityOpt.get().getFirstName(), studentEntityOpt.get().getFirstName());
		else
			log.info("Deleting student: FAILED [student name=[{} {}] not found ]", studentFirstName, studentLastName);

		return studentEntityOpt;
	}

	@Override
	public Optional<StudentEntity> updateStudent(StudentDto studentDto) {
		log.info("Update student ...");

		if (studentDto == null) {
			log.info("Updating student: FAILED [studentDto:null]");
			return Optional.empty();
		}

		StudentEntity studentEntity;
		if (studentDto.getId() != null) {
			var studentEntityOpt = findStudentById(studentDto.getId());
			if (studentEntityOpt.isPresent()) {
				studentEntity = studentEntityOpt.get();
				log.info("Updating student: ID=[{}] was identified", studentEntity.getId());
			} else {
				log.info("Updating student: FAILED [Unable to identify object to update : studentDto ID={}]", studentDto.getId());
				return Optional.empty();
			}
		} else if (studentDto.getEmail() != null) {
			var studentEntityOpt = findStudentByEmail(studentDto.getEmail());
			if (studentEntityOpt.isPresent()) {
				studentEntity = studentEntityOpt.get();
				log.info("Updating student: email=[{}] was identified", studentEntity.getEmail());
			} else {
				log.info("Updating student: FAILED [Unable to identify object to update : studentDto email={}]", studentDto.getEmail());
				return Optional.empty();
			}
		} else if (studentDto.getFirstName() != null && studentDto.getLastName() != null) {
			var studentEntityOpt = findStudentByFirstNameAndLastName(studentDto.getFirstName(), studentDto.getLastName());
			if (studentEntityOpt.isPresent()) {
				studentEntity = studentEntityOpt.get();
				log.info("Updating student: name=[{} {}] was identified", studentEntity.getFirstName(), studentEntity.getLastName());
			} else {
				log.info("Updating student: FAILED [Unable to identify object to update : studentDto name={} {}]", studentDto.getFirstName(), studentDto.getLastName());
				return Optional.empty();
			}
		} else {
			log.info("Updating student: FAILED [student not found in DB : {}", studentDto);
			return Optional.empty();
		}

		var studentEntityToUpdate = studentMapper.toEntity(studentDto);
		studentEntityToUpdate.setId(studentEntity.getId());
		studentEntityToUpdate.setCreatedAt(studentEntity.getCreatedAt());
		studentEntityToUpdate.setLessons(studentEntity.getLessons());

		var updatedStudent = studentRepo.save(studentEntityToUpdate);
		log.info("Updating student: SUCCESSFUL : [Found ID={} in DB]", updatedStudent.getId());

		return Optional.of(studentEntityToUpdate);
	}
}
