package com.glpserver.glp.repository;

import com.glpserver.glp.domain.entity.LessonEntity;
import com.glpserver.glp.domain.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import javax.validation.constraints.Email;
import java.util.List;
import java.util.Optional;

/**
 * @author Dumitru Săndulache (sandulachedumitru@hotmail.com)
 */

public interface LessonEntityRepository extends JpaRepository<LessonEntity, Long> {
	Optional<LessonEntity> findTopByOrderByIdDesc();
	Optional<List<LessonEntity>> findLessonsByStudentId(@NonNull Long studentId);
	Optional<LessonEntity> findLessonByStudentAndLessonNumber(@NonNull StudentEntity student, @NonNull Integer lessonNumber);
	Optional<LessonEntity> findLessonByStudentFirstNameAndStudentLastNameAndLessonNumber(@NonNull String studentFirstName, @NonNull String studentLastName, @NonNull Integer lessonNumber);
	Optional<LessonEntity> findLessonByStudentIdAndLessonNumber(@NonNull Long studentId, @NonNull Integer lessonNumber);
	Optional<LessonEntity> findLessonByStudentEmailAndLessonNumber(@NonNull @Email String studentEmail, @NonNull Integer lessonNumber);
}
