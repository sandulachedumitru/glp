package com.glpserver.glp.service;

import com.glpserver.glp.domain.dto.LessonDto;
import com.glpserver.glp.domain.dto.StudentDto;
import com.glpserver.glp.domain.entity.LessonEntity;
import org.springframework.lang.NonNull;

import javax.validation.constraints.Email;
import java.util.List;
import java.util.Optional;

/**
 * @author Dumitru Săndulache (sandulachedumitru@hotmail.com)
 */

public interface LessonService {
	Optional<LessonEntity> createNewLesson(LessonDto lessonDto);

	Optional<List<LessonEntity>> findAllLessons();
	Optional<LessonEntity> findLessonById(@NonNull Long lessonId);
	Optional<List<LessonEntity>> findLessonsByStudentId(@NonNull Long studentId);
	Optional<LessonEntity> findLessonByStudentAndLessonNumber(@NonNull StudentDto studentDto, @NonNull Integer lessonNumber);
	Optional<LessonEntity> findLessonByStudentFirstNameAndStudentLastNameAndLessonNumber(@NonNull String studentFirstName, @NonNull String studentLastName, @NonNull Integer lessonNumber);
	Optional<LessonEntity> findLessonByStudentIdAndLessonNumber(@NonNull Long studentId, @NonNull Integer lessonNumber);
	Optional<LessonEntity> findLessonByStudentEmailAndLessonNumber(@NonNull @Email String studentEmail, @NonNull Integer lessonNumber);

	Optional<LessonEntity> deleteLessonById(@NonNull Long lessonId);

	Optional<LessonEntity> updateLesson(@NonNull LessonDto fromLessonDto, @NonNull LessonDto toLessonDto);
}
