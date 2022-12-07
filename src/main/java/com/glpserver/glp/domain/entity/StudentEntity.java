package com.glpserver.glp.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Dumitru Săndulache (sandulachedumitru@hotmail.com)
 */

@Getter
@Setter

@Entity
@Table(name = "student")
public class StudentEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "STUDENT_ID")
	private Long id;

	private LocalDateTime createdAt = LocalDateTime.now();

	private String firstName = "";
	private String lastName = "";

	@Column(unique = true)
	private String email = "";

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<LessonEntity> lessons;
}
