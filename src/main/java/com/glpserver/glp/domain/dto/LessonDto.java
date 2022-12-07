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
	private Long id;
	private Long studentId;
	private int lessonNumber;
	private LocalDateTime createdAt = LocalDateTime.now();
	private String content = "";
	private String homework = "";
}
