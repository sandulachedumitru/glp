package com.glpserver.glp.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link com.glpserver.glp.domain.entity.LessonEntity} entity
 * @author Dumitru Săndulache (sandulachedumitru@hotmail.com)
 */

@Data
public class LessonDto implements Serializable {
	@NotNull private Long id;
	@NotNull private StudentDto student;
	@NotNull private int lessonNumber;
	@NotNull private LocalDateTime createdAt = LocalDateTime.now();
	@NotNull private String content;
	@NotNull private String homework;
}
