package com.glpserver.glp.service.impl;

import com.glpserver.glp.domain.dto.LessonDto;
import com.glpserver.glp.domain.entity.LessonEntity;
import com.glpserver.glp.domain.entity.StudentEntity;
import com.glpserver.glp.service.LessonService;
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
public class LessonServiceImpl implements LessonService {
	@Override
	public Optional<LessonEntity> createNewLesson(LessonDto lessonDto) {
		return Optional.empty();
	}

	@Override
	public Optional<List<LessonEntity>> findAllLessons() {
		return Optional.empty();
	}

	@Override
	public Optional<List<LessonEntity>> findLessonsByStudentId(Long studentId) {
		return Optional.empty();
	}

	@Override
	public Optional<LessonEntity> findLessonById(Long lessonId) {
		return Optional.empty();
	}

	@Override
	public Optional<LessonEntity> findLessonByStudentAndLessonNumber(StudentEntity student, Integer lessonNumber) {
		return Optional.empty();
	}

	@Override
	public Optional<LessonEntity> findLessonByStudentFirstNameAndStudentLastNameAndLessonNumber(String studentFirstName, String studentLastName, Integer lessonNumber) {
		return Optional.empty();
	}

	@Override
	public Optional<LessonEntity> findLessonByStudentIdAndLessonNumber(Long studentId, Integer lessonNumber) {
		return Optional.empty();
	}

	@Override
	public Optional<LessonEntity> findLessonByStudentIEmailAndLessonNumber(Long studentId, Integer lessonNumber) {
		return Optional.empty();
	}

	@Override
	public Optional<LessonEntity> deleteLessonById(Long lessonId) {
		return Optional.empty();
	}

	@Override
	public Optional<LessonEntity> updateLessonById(Long lessonId) {
		return Optional.empty();
	}
}
