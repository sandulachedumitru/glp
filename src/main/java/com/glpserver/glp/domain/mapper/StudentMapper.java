package com.glpserver.glp.domain.mapper;

import com.glpserver.glp.domain.dto.StudentDto;
import com.glpserver.glp.domain.entity.StudentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author Dumitru Săndulache (sandulachedumitru@hotmail.com)
 */

@Mapper(componentModel = "spring")
public interface StudentMapper {
	StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

	StudentDto toDto(StudentEntity entity);
	StudentEntity toEntity(StudentDto dto);
}
