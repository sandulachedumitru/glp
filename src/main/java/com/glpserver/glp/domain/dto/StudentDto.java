package com.glpserver.glp.domain.dto;

import com.glpserver.glp.domain.entity.StudentEntity;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * A DTO for the {@link StudentEntity} entity
 * @author Dumitru Săndulache (sandulachedumitru@hotmail.com)
 */

@Data
public class StudentDto implements Serializable {
	private Long id;
	@NotNull private LocalDate createdAt = LocalDate.now();
	@NotNull private String firstName;
	private String lastName;
	private String email;
}
