package com.glpserver.glp.domain.dto;

import lombok.Data;


import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * A DTO for the {@link com.glpserver.glp.domain.entity.LessonEntity} entity
 * @author Dumitru Săndulache (sandulachedumitru@hotmail.com)
 */

@Data
public class LessonDto implements Serializable {
	private Long id;
	@NotNull private StudentDto student;
	@NotNull private int lessonNumber;
	private LocalDate createdAt = LocalDate.now();
	private String content = "";
	private String homework = "";
}
