package com.glpserver.glp.service;

import com.glpserver.glp.domain.dto.LessonDto;
import com.glpserver.glp.domain.entity.LessonEntity;
import com.glpserver.glp.domain.entity.StudentEntity;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

/**
 * @author Dumitru Săndulache (sandulachedumitru@hotmail.com)
 */

public interface LessonService {
	Optional<LessonEntity> createNewLesson(LessonDto lessonDto);

	Optional<List<LessonEntity>> findAllLessons();
	Optional<List<LessonEntity>> findLessonsByStudentId(@NonNull Long studentId);
	Optional<LessonEntity> findLessonById(@NonNull Long lessonId);
	Optional<LessonEntity> findLessonByStudentAndLessonNumber(@NonNull StudentEntity student, @NonNull Integer lessonNumber);
	Optional<LessonEntity> findLessonByStudentFirstNameAndStudentLastNameAndLessonNumber(@NonNull String studentFirstName, @NonNull String studentLastName, @NonNull Integer lessonNumber);
	Optional<LessonEntity> findLessonByStudentIdAndLessonNumber(@NonNull Long studentId, @NonNull Integer lessonNumber);
	Optional<LessonEntity> findLessonByStudentIEmailAndLessonNumber(@NonNull Long studentId, @NonNull Integer lessonNumber);

	Optional<LessonEntity> deleteLessonById(@NonNull Long lessonId);

	Optional<LessonEntity> updateLessonById(@NonNull Long lessonId);
}
