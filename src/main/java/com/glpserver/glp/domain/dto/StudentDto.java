package com.glpserver.glp.domain.dto;

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
	@NotNull private Long id;
	@NotNull private LocalDateTime createdAt = LocalDateTime.now();
	@NotNull private String firstName;
	@NotNull private String lastName;
	@NotNull private String email;
	@NotNull private Set<LessonDto> lessons;
}
