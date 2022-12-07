package com.glpserver.glp.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.glpserver.glp.domain.entity.StudentEntity;
import javax.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * A DTO for the {@link StudentEntity} entity
 * @author Dumitru Săndulache (sandulachedumitru@hotmail.com)
 */

@Data

public class StudentDto implements Serializable {
	private Long id;
	@NotNull private LocalDateTime createdAt = LocalDateTime.now();
	@NotNull private String firstName;
	private String lastName;
	private String email;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Set<LessonDto> lessons;
}
