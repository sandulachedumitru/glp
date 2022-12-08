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
@Table(name = "student")
public class StudentEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "STUDENT_ID")
	private Long id;

	@NotNull private LocalDate createdAt = LocalDate.now();
	@NotNull private String firstName = "";
	private String lastName = "";

	@Column(unique = true)
	private String email = "";
}
