package com.glpserver.glp.service.impl;

import com.glpserver.glp.domain.dto.StudentDto;
import com.glpserver.glp.domain.entity.StudentEntity;
import com.glpserver.glp.domain.mapper.StudentMapper;
import com.glpserver.glp.repository.LessonEntityRepository;
import com.glpserver.glp.repository.StudentEntityRepository;
import com.glpserver.glp.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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
	private final LessonEntityRepository lessonRepo;

	@Override
	public Optional<StudentEntity> createNewStudent(StudentDto studentDto) {
		log.info("Create new student ...");
		if (studentDto == null) {
			log.error("Creating new student: FAILED [accountDto:null]");
			return Optional.empty();
		}

		studentDto.setId(null); // it's a creation not an update
		studentDto.setCreatedAt(LocalDate.now());

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
			log.error("Deleting student: FAILED [studentId:null]");
			return Optional.empty();
		}

		var studentEntityOpt = studentRepo.findById(studentId);
		if (studentEntityOpt.isPresent()) {
			deleteStudentLessonList(studentEntityOpt.get().getId());
			studentRepo.deleteById(studentId);
			log.info("Deleting student: SUCCESSFUL [ID={}]", studentEntityOpt.get().getId());
		} else log.error("Deleting student: FAILED [ID={} not found ]", studentId);

		return studentEntityOpt;
	}

	private void deleteStudentLessonList(Long studentId) {
		var lessonEntitiesOpt = lessonRepo.findLessonsByStudentId(studentId);
		if (lessonEntitiesOpt.isPresent()) {
			log.info("Delete lessons for student with ID=[{}]", studentId);
			for (var lessonEntity : lessonEntitiesOpt.get()) {
				log.info("- delete lesson id=[{}]", lessonEntity.getId());
				lessonRepo.deleteById(lessonEntity.getId());
			}
		}
	}

	@Override
	public Optional<StudentEntity> deleteStudentByEmail(String studentEmail) {
		log.info("Delete student by email ...");

		if (studentEmail == null) {
			log.error("Deleting student: FAILED [studentEmail:null]");
			return Optional.empty();
		}

		var studentEntityOpt = findStudentByEmail(studentEmail);
		if (studentEntityOpt.isPresent()) {
			deleteStudentLessonList(studentEntityOpt.get().getId());
			studentRepo.deleteStudentByEmail(studentEmail);
			log.info("Deleting student: SUCCESSFUL [student email=[{}]", studentEntityOpt.get().getEmail());
		} else log.error("Deleting student: FAILED [student email=[{}] not found ]", studentEmail);

		return studentEntityOpt;
	}

	@Override
	public Optional<StudentEntity> deleteStudentByFirstNameAndLastName(String studentFirstName, String studentLastName) {
		log.info("Delete student by first name and last name ...");

		if (studentFirstName == null || studentLastName == null) {
			log.error("Deleting student: FAILED [studentFirstName:null OR studentLastName:null]");
			return Optional.empty();
		}

		var studentEntityOpt = findStudentByFirstNameAndLastName(studentFirstName, studentLastName);
		if (studentEntityOpt.isPresent()) {
			deleteStudentLessonList(studentEntityOpt.get().getId());
			studentRepo.deleteStudentByFirstNameAndLastName(studentFirstName, studentLastName);
			log.info("Deleting student: SUCCESSFUL [student name=[{} {}]", studentEntityOpt.get().getFirstName(), studentEntityOpt.get().getFirstName());
		}
		else
			log.error("Deleting student: FAILED [student name=[{} {}] not found ]", studentFirstName, studentLastName);

		return studentEntityOpt;
	}

	@Override
	public Optional<StudentEntity> updateStudent(StudentDto fromStudentDto, StudentDto toStudentDto) {
		log.info("Update student ...");

		if (fromStudentDto == null || toStudentDto == null) {
			log.error("Updating student: FAILED [fromStudentDto:null OR toStudentDto:null]");
			return Optional.empty();
		}
		if (toStudentDto.getFirstName() == null && toStudentDto.getLastName() == null) {
			// email could be null because a student might not necessarily have an email
			log.error("Updating student: FAILED [firstName and lastName cannot both be null]");
			return Optional.empty();
		}

		StudentEntity studentEntity;
		if (fromStudentDto.getId() != null) {
			var studentEntityOpt = findStudentById(fromStudentDto.getId());
			if (studentEntityOpt.isPresent()) {
				studentEntity = studentEntityOpt.get();
				log.info("Updating student: ID=[{}] was identified", studentEntity.getId());
			} else {
				log.error("Updating student: FAILED [Unable to identify object to update : fromStudentDto ID={}]", fromStudentDto.getId());
				return Optional.empty();
			}
		} else if (fromStudentDto.getEmail() != null) {
			var studentEntityOpt = findStudentByEmail(fromStudentDto.getEmail());
			if (studentEntityOpt.isPresent()) {
				studentEntity = studentEntityOpt.get();
				log.info("Updating student: email=[{}] was identified", studentEntity.getEmail());
			} else {
				log.error("Updating student: FAILED [Unable to identify object to update : fromStudentDto email={}]", fromStudentDto.getEmail());
				return Optional.empty();
			}
		} else if (fromStudentDto.getFirstName() != null && fromStudentDto.getLastName() != null) {
			var studentEntityOpt = findStudentByFirstNameAndLastName(fromStudentDto.getFirstName(), fromStudentDto.getLastName());
			if (studentEntityOpt.isPresent()) {
				studentEntity = studentEntityOpt.get();
				log.info("Updating student: name=[{} {}] was identified", studentEntity.getFirstName(), studentEntity.getLastName());
			} else {
				log.error("Updating student: FAILED [Unable to identify object to update : fromStudentDto name={} {}]", fromStudentDto.getFirstName(), fromStudentDto.getLastName());
				return Optional.empty();
			}
		} else {
			log.error("Updating student: FAILED [student not found in DB : {}", fromStudentDto);
			return Optional.empty();
		}

		// unchangeable fields: id, createdAt, lessons
		// changeable fields: firstName, lastName, email
		studentEntity.setFirstName(toStudentDto.getFirstName());
		studentEntity.setLastName(toStudentDto.getLastName());
		studentEntity.setEmail(toStudentDto.getEmail());

		var updatedStudentEntity = studentRepo.save(studentEntity);
		log.info("Updating student: SUCCESSFUL : [Found ID={} in DB]", updatedStudentEntity.getId());

		return Optional.of(studentEntity);
	}
}
