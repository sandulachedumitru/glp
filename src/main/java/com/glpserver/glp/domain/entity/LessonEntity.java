package com.glpserver.glp.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Dumitru Săndulache (sandulachedumitru@hotmail.com)
 */

@Getter
@Setter

@Entity
@Table(name = "lesson")
public class LessonEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "LESSON_ID")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "student_student_id")
	private StudentEntity student;

	private int lessonNumber;
	private LocalDateTime createdAt = LocalDateTime.now();
	private String content;
	private String homework;
}
