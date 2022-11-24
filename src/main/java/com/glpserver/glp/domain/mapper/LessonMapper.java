package com.glpserver.glp.domain.mapper;

import com.glpserver.glp.domain.dto.LessonDto;
import com.glpserver.glp.domain.entity.LessonEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author Dumitru Săndulache (sandulachedumitru@hotmail.com)
 */

@Mapper(componentModel = "spring")
public interface LessonMapper {
	LessonMapper INSTANCE = Mappers.getMapper(LessonMapper.class);

	LessonDto toDto(LessonEntity entity);
	LessonEntity toEntity(LessonDto dto);
}
