package com.glpserver.glp.service.impl;

import com.glpserver.glp.domain.dto.LessonDto;
import com.glpserver.glp.domain.dto.StudentDto;
import com.glpserver.glp.domain.entity.LessonEntity;
import com.glpserver.glp.domain.entity.StudentEntity;
import com.glpserver.glp.domain.mapper.LessonMapper;
import com.glpserver.glp.domain.mapper.StudentMapper;
import com.glpserver.glp.repository.LessonEntityRepository;
import com.glpserver.glp.service.LessonService;
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
public class LessonServiceImpl implements LessonService {
	private final LessonEntityRepository lessonRepo;
	private final LessonMapper lessonMapper;
	private final StudentService studentService;
	private final StudentMapper studentMapper;

	@Override
	public Optional<LessonEntity> createNewLesson(LessonDto lessonDto) {
		log.info("Create new lesson ...");
		if (lessonDto == null) {
			log.error("Creating new lesson: FAILED [lessonDto:null]");
			return Optional.empty();
		}

		var studentEntity = checkStudentDto(lessonDto.getStudent());
		if (studentEntity == null) {
			log.error("Creating new lesson: FAILED [studentDto is null/invalid]");
			return Optional.empty();
		}

		var lessonsEntitiesOpt = findLessonsByStudentId(studentEntity.getId());
		lessonsEntitiesOpt.ifPresent(lessonEntities -> lessonDto.setLessonNumber(lessonEntities.size() + 1));
		lessonDto.setId(null); // it's a creation not an update
		lessonDto.setCreatedAt(LocalDate.now());
		lessonDto.setStudent(studentMapper.toDto(studentEntity));

		var lessonEntity = lessonMapper.toEntity(lessonDto);
		if (lessonEntity != null) {
			lessonRepo.save(lessonEntity);
			log.info("Creating lesson: SUCCESSFUL : {}", lessonMapper.toDto(lessonEntity));
			return Optional.of(lessonEntity);
		}

		log.info("Creating lesson: FAILED [DTO -> ENTITY : failed]");
		return Optional.empty();
	}

	private StudentEntity checkStudentDto(StudentDto studentDto) {
		if (studentDto == null) return null;

		Optional<StudentEntity> studentEntityOpt;
		if (studentDto.getId() != null) {
			studentEntityOpt = studentService.findStudentById(studentDto.getId());
		} else if (studentDto.getEmail() != null) {
			studentEntityOpt = studentService.findStudentByEmail(studentDto.getEmail());
		} else if (studentDto.getFirstName() != null && studentDto.getLastName() != null) {
			studentEntityOpt = studentService.findStudentByFirstNameAndLastName(studentDto.getFirstName(), studentDto.getLastName());
		} else return null;

		return studentEntityOpt.orElse(null);
	}

	@Override
	public Optional<List<LessonEntity>> findAllLessons() {
		log.info("Find all lesson ...");

		List<LessonEntity> lessonEntities = lessonRepo.findAll();
		log.info("Finding all lessons: SUCCESSFUL : {}", lessonEntities);

		return Optional.of(lessonEntities);
	}

	@Override
	public Optional<List<LessonEntity>> findLessonsByStudentId(Long studentId) {
		log.info("Find all lesson by student ID ...");

		if (studentId == null) {
			log.error("Finding all lesson: FAILED [studentId:null]");
			return Optional.empty();
		}

		var studentEntity = studentService.findStudentById(studentId).orElse(null);
		if (studentEntity == null) {
			log.error("Finding all lesson:: FAILED [student id=[{}] not found]", studentId);
			return Optional.empty();
		}

		var lessonEntitiesOpt = lessonRepo.findLessonsByStudentId(studentId);
		if (lessonEntitiesOpt.isPresent())
			log.info("Finding all lesson for student id=[{}]: SUCCESSFUL : {}", studentId, lessonEntitiesOpt.get());
		else log.error("Finding all lesson for student id=[{}]: FAILED", studentId);

		return lessonEntitiesOpt;
	}

	@Override
	public Optional<LessonEntity> findLessonById(Long lessonId) {
		log.info("Find lesson by ID ...");

		if (lessonId == null) {
			log.error("Finding lesson: FAILED [studentId:null]");
			return Optional.empty();
		}

		var lessonEntityOpt = lessonRepo.findById(lessonId);
		if (lessonEntityOpt.isPresent())
			log.info("Finding lesson: SUCCESSFUL [ID=[{}] found in DB]", lessonEntityOpt.get().getId());
		else log.error("Finding lesson: FAILED [ID=[{}] not found in DB]", lessonId);

		return lessonEntityOpt;
	}

	@Override
	public Optional<LessonEntity> findLessonByStudentAndLessonNumber(StudentDto studentDto, Integer lessonNumber) {
		log.info("Find lesson by student and lesson number ...");

		if (studentDto == null || lessonNumber == null) {
			log.error("Finding lesson: FAILED [studentDto:null OR lessonNumber:null]");
			return Optional.empty();
		}

		StudentEntity studentEntity;
		if (studentDto.getId() != null) {
			var studentEntityOpt = studentService.findStudentById(studentDto.getId());
			if (studentEntityOpt.isPresent()) {
				studentEntity = studentEntityOpt.get();
				log.info("Finding student: ID=[{}] was identified", studentEntity.getId());
			} else {
				log.error("Finding student: FAILED [Unable to identify object : studentDto ID={}]", studentDto.getId());
				return Optional.empty();
			}
		} else if (studentDto.getEmail() != null) {
			var studentEntityOpt = studentService.findStudentByEmail(studentDto.getEmail());
			if (studentEntityOpt.isPresent()) {
				studentEntity = studentEntityOpt.get();
				log.info("Finding student: email=[{}] was identified", studentEntity.getEmail());
			} else {
				log.error("Finding student: FAILED [Unable to identify object : studentDto email={}]", studentDto.getEmail());
				return Optional.empty();
			}
		} else if (studentDto.getFirstName() != null && studentDto.getLastName() != null) {
			var studentEntityOpt = studentService.findStudentByFirstNameAndLastName(studentDto.getFirstName(), studentDto.getLastName());
			if (studentEntityOpt.isPresent()) {
				studentEntity = studentEntityOpt.get();
				log.info("Finding student: name=[{} {}] was identified", studentEntity.getFirstName(), studentEntity.getLastName());
			} else {
				log.error("Finding student: FAILED [Unable to identify object : studentDto name={} {}]", studentDto.getFirstName(), studentDto.getLastName());
				return Optional.empty();
			}
		} else {
			log.error("Finding student: FAILED [student not found in DB : {}", studentDto);
			return Optional.empty();
		}

		var lessonEntityOpt = lessonRepo.findLessonByStudentAndLessonNumber(studentEntity, lessonNumber);
		if (lessonEntityOpt.isPresent())
			log.info("Finding lesson with number {}: SUCCESSFUL [ID=[{}] found in DB]", lessonNumber, lessonEntityOpt.get().getId());
		else log.error("Finding lesson: FAILED [lesson number=[{}] not found in DB]", lessonNumber);

		return lessonEntityOpt;
	}

	@Override
	public Optional<LessonEntity> findLessonByStudentFirstNameAndStudentLastNameAndLessonNumber(String studentFirstName, String studentLastName, Integer lessonNumber) {
		log.info("Find lesson by student name and lesson number ...");

		if (studentFirstName == null || studentLastName == null || lessonNumber == null) {
			log.error("Finding lesson: FAILED [studentFirstName:null OR studentLastName:null OR lessonNumber:null]");
			return Optional.empty();
		}

		var lessonEntityOpt = lessonRepo.findLessonByStudentFirstNameAndStudentLastNameAndLessonNumber(studentFirstName, studentLastName, lessonNumber);
		if (lessonEntityOpt.isPresent())
			log.info("Finding lesson with number {} for student [{} {}]: SUCCESSFUL [ID=[{}] found in DB]", lessonNumber, studentFirstName, studentLastName, lessonEntityOpt.get().getId());
		else log.error("Finding lesson: FAILED [student [{} {}], lesson number=[{}] not found in DB]", studentFirstName, studentLastName, lessonNumber);

		return lessonEntityOpt;
	}

	@Override
	public Optional<LessonEntity> findLessonByStudentIdAndLessonNumber(Long studentId, Integer lessonNumber) {
		log.info("Find lesson by student ID and lesson number ...");

		if (studentId == null || lessonNumber == null) {
			log.error("Finding lesson: FAILED [studentId:null OR lessonNumber:null]");
			return Optional.empty();
		}

		var lessonEntityOpt = lessonRepo.findLessonByStudentIdAndLessonNumber(studentId, lessonNumber);
		if (lessonEntityOpt.isPresent())
			log.info("Finding lesson with number [{}] for student [{} {}]: SUCCESSFUL [ID=[{}] found in DB]", lessonNumber, lessonEntityOpt.get().getStudent().getFirstName(), lessonEntityOpt.get().getStudent().getLastName(), lessonEntityOpt.get().getId());
		else log.error("Finding lesson: FAILED [student id=[{}] lesson number=[{}] not found in DB]", studentId, lessonNumber);

		return lessonEntityOpt;
	}

	@Override
	public Optional<LessonEntity> findLessonByStudentEmailAndLessonNumber(String studentEmail, Integer lessonNumber) {
		log.info("Find lesson by student email and lesson number ...");

		if (studentEmail == null || lessonNumber == null) {
			log.error("Finding lesson: FAILED [studentEmail:null OR lessonNumber:null]");
			return Optional.empty();
		}

		var lessonEntityOpt = lessonRepo.findLessonByStudentEmailAndLessonNumber(studentEmail, lessonNumber);
		if (lessonEntityOpt.isPresent())
			log.info("Finding lesson with number [{}] for [{} {}]: SUCCESSFUL [ID=[{}] found in DB]", lessonNumber, lessonEntityOpt.get().getStudent().getFirstName(), lessonEntityOpt.get().getStudent().getLastName(), lessonEntityOpt.get().getId());
		else log.error("Finding lesson: FAILED [student email=[{}] lesson number=[{}] not found in DB]", studentEmail, lessonNumber);

		return lessonEntityOpt;
	}

	@Override
	public Optional<LessonEntity> deleteLessonById(Long lessonId) {
		log.info("Delete lesson by ID ...");

		if (lessonId == null) {
			log.error("Deleting lesson: FAILED [lessonId:null]");
			return Optional.empty();
		}

		var lessonEntityOpt = lessonRepo.findById(lessonId);
		if (lessonEntityOpt.isPresent()) {
			lessonRepo.deleteById(lessonId);
			log.info("Deleting lesson: SUCCESSFUL [ID={}]", lessonEntityOpt.get().getId());
		} else log.error("Deleting lesson: FAILED [ID={} not found ]", lessonId);

		return lessonEntityOpt;
	}

	@Override
	public Optional<LessonEntity> updateLesson(LessonDto fromLessonDto, LessonDto toLessonDto) {
		log.info("Update lesson ...");

		if (fromLessonDto == null || toLessonDto == null) {
			log.error("Updating lesson: FAILED [fromLessonDto:null OR toLessonDto:null]");
			return Optional.empty();
		}

		LessonEntity lessonEntity;
		if (fromLessonDto.getId() != null) {
			var lessonEntityOpt = findLessonById(fromLessonDto.getId());
			if (lessonEntityOpt.isPresent()) {
				lessonEntity = lessonEntityOpt.get();
				log.info("Updating lesson: ID=[{}] was identified", lessonEntity.getId());
			} else {
				log.error("Updating lesson: FAILED [Unable to identify object to update : fromLessonDto ID={}]", fromLessonDto.getId());
				return Optional.empty();
			}
		} else if (fromLessonDto.getStudent() != null && fromLessonDto.getLessonNumber() > 0) {
			var studentEntityOpt = findLessonByStudentAndLessonNumber(fromLessonDto.getStudent(), fromLessonDto.getLessonNumber());
			if (studentEntityOpt.isPresent()) {
				lessonEntity = studentEntityOpt.get();
				log.info("Updating lesson: student=[{}] and lessonNumber=[{}] was identified", lessonEntity.getStudent(), lessonEntity.getLessonNumber());
			} else {
				var studentFirstName = fromLessonDto.getStudent().getFirstName();
				var studentLastName = fromLessonDto.getStudent().getLastName();
				var studentId = fromLessonDto.getStudent().getId();
				var lessonNumber = fromLessonDto.getLessonNumber();
				log.error("Updating lesson: FAILED [Unable to identify object to update : student=[{} {}] with id=[{}] and lessonNumber=[{}]]", studentFirstName, studentLastName, studentId, lessonNumber);
				return Optional.empty();
			}
		} else {
			log.error("Updating lesson: FAILED [lesson to update not found in DB : {}", fromLessonDto);
			return Optional.empty();
		}

		// unchangeable fields: id, createdAt
		// changeable fields: student, lessonNumber, content, homework
		lessonEntity.setStudent         (studentMapper.toEntity(toLessonDto.getStudent()));
		lessonEntity.setLessonNumber    (toLessonDto.getLessonNumber());
		lessonEntity.setContent         (toLessonDto.getContent());
		lessonEntity.setHomework        (toLessonDto.getHomework());

		var updatedLessonEntity = lessonRepo.save(lessonEntity);
		log.info("Updating lesson: SUCCESSFUL : [Found ID={} in DB]", updatedLessonEntity.getId());

		return Optional.of(updatedLessonEntity);
	}
}
