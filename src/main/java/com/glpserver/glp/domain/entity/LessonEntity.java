package com.glpserver.glp.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

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
	@JoinColumn(name = "STUDENT_ID")
	@NotNull private StudentEntity student;
	@NotNull private int lessonNumber;
	private LocalDate createdAt = LocalDate.now();
	private String content = "";
	private String homework = "";
}
