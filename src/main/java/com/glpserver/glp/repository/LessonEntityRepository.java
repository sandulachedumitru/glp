package com.glpserver.glp.repository;

import com.glpserver.glp.domain.entity.LessonEntity;
import com.glpserver.glp.domain.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

/**
 * @author Dumitru Săndulache (sandulachedumitru@hotmail.com)
 */

public interface LessonEntityRepository extends JpaRepository<LessonEntity, Long> {
//	Optional<LessonEntity> findLessonEntityById(@NonNull Long id);
////	@Query("select l from LessonEntity l where l.student.firstName = ?1 and  l.student.lastName = ?2 and l.lessonNumber = ?3")
//	Optional<LessonEntity> findLessonByStudentFirstNameAndStudentLastNameAndLessonNumber(@NonNull String studentFirstName, @NonNull String studentLastName, @NonNull Integer lessonNumber);
//	Optional<LessonEntity> findLessonEntityByStudentIdAndLessonNumber(@NonNull Long studentId, @NonNull Integer lessonNumber);
//	Optional<LessonEntity> findLessonEntityByStudentAndLessonNumber(@NonNull StudentEntity student, @NonNull Integer lessonNumber);
//	Optional<List<LessonEntity>> findLessonEntitiesByStudentId(@NonNull Long studentId);
//	Optional<LessonEntity> findLessonEntitiesById(@NonNull Long lessonId);
}
