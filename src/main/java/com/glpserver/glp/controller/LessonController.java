package com.glpserver.glp.controller;

import com.glpserver.glp.domain.dto.LessonDto;
import com.glpserver.glp.domain.dto.StudentDto;
import com.glpserver.glp.domain.entity.LessonEntity;
import com.glpserver.glp.domain.mapper.LessonMapper;
import com.glpserver.glp.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Dumitru Săndulache (sandulachedumitru@hotmail.com)
 */

@RestController
@RequestMapping("/lesson")
@RequiredArgsConstructor
public class LessonController {
	private final LessonService lessonService;
	private final LessonMapper lessonMapper;

	@PostMapping
	public ResponseEntity<LessonDto> createNewLesson(@RequestBody LessonDto lessonDto) {
		var lessonEntityOpt = lessonService.createNewLesson(lessonDto);
//		return lessonEntityOpt.map(lessonEntity -> ResponseEntity.of(Optional.of(lessonMapper.toDto(lessonEntity)))).orElseGet(() -> ResponseEntity.of(Optional.empty()));
		return ResponseEntity.of(lessonEntityOpt.map(lessonMapper::toDto));
	}

	@GetMapping
	public ResponseEntity<List<LessonDto>> findAllLessons() {
		var lessonEntitiesOpt = lessonService.findAllLessons();
//		if (lessonEntitiesOpt.isPresent()) {
//			var lessonEntities = lessonEntitiesOpt.get();
//			var lessonDtos = lessonEntities.stream().map(lessonMapper::toDto).collect(Collectors.toList());
//			var lessonDtosOpt = Optional.of(lessonDtos);
//			return ResponseEntity.of(lessonDtosOpt);
//		}
//
//		return ResponseEntity.of(Optional.empty());


//		return lessonEntitiesOpt
//				.map(list -> ResponseEntity.of(
//						Optional.of(
//								list.stream().map(lessonMapper::toDto).collect(Collectors.toList())
//						))
//				)
//				.orElseGet(() -> ResponseEntity.of(Optional.empty()));

//		return lessonEntitiesOpt
//				.map(lessonEntities -> lessonEntities.stream().map(lessonMapper::toDto).collect(Collectors.toList()))
//				.map(Optional::of)
//				.map(ResponseEntity::of)
//				.orElseGet(() -> ResponseEntity.of(Optional.empty()));

		return ResponseEntity.of(lessonEntitiesOpt.map(lessonEntities -> lessonEntities.stream().map(lessonMapper::toDto).collect(Collectors.toList())));
	}

	@GetMapping("/student/{studentId}")
	public ResponseEntity<List<LessonDto>> findLessonsByStudentId(@PathVariable Long studentId) {
		var lessonEntitiesOpt = lessonService.findLessonsByStudentId(studentId);
		return ResponseEntity.of(lessonEntitiesOpt.map(lessonEntities -> lessonEntities.stream().map(lessonMapper::toDto).collect(Collectors.toList())));
	}

	@GetMapping("/{lessonId}")
	public ResponseEntity<LessonDto> findLessonById(@PathVariable Long lessonId) {
		var lessonEntityOpt = lessonService.findLessonById(lessonId);
		return ResponseEntity.of(lessonEntityOpt.map(lessonMapper::toDto));
	}

	@GetMapping("/student-and-lessonNumber")
	public ResponseEntity<LessonDto> findLessonByStudentAndLessonNumber(@RequestBody HelperStudentAndLessonNumber helper) {
		var lessonEntityOpt = lessonService.findLessonByStudentAndLessonNumber(helper.studentDto, helper.lessonNumber);
		return ResponseEntity.of(lessonEntityOpt.map(lessonMapper::toDto));
	}

	@GetMapping("/student-firstName-lastName-and-lessonNumber")
	public ResponseEntity<LessonDto> findLessonByStudentFirstNameAndStudentLastNameAndLessonNumber(@RequestParam String studentFirstName, @RequestParam String studentLastName, @RequestParam Integer lessonNumber) {
		var lessonEntityOpt = lessonService.findLessonByStudentFirstNameAndStudentLastNameAndLessonNumber(studentFirstName, studentLastName, lessonNumber);
		return ResponseEntity.of(lessonEntityOpt.map(lessonMapper::toDto));
	}

	@GetMapping("/student-id-and-lessonNumber")
	public ResponseEntity<LessonDto> findLessonByStudentIdAndLessonNumber(@RequestParam Long studentId, @RequestParam Integer lessonNumber) {
		var lessonEntityOpt = lessonService.findLessonByStudentIdAndLessonNumber(studentId, lessonNumber);
		return ResponseEntity.of(lessonEntityOpt.map(lessonMapper::toDto));
	}

	@GetMapping("/student-email-and-lessonNumber")
	public ResponseEntity<LessonDto> findLessonByStudentEmailAndLessonNumber(@RequestParam String studentEmail, @RequestParam Integer lessonNumber) {
		var lessonEntityOpt = lessonService.findLessonByStudentEmailAndLessonNumber(studentEmail, lessonNumber);
		return ResponseEntity.of(lessonEntityOpt.map(lessonMapper::toDto));
	}

	@DeleteMapping("/{lessonId}")
	public ResponseEntity<LessonDto> deleteLessonById(@PathVariable Long lessonId) {
		var lessonEntityOpt = lessonService.deleteLessonById(lessonId);
		return ResponseEntity.of(lessonEntityOpt.map(lessonMapper::toDto));
	}

	@PutMapping
	public ResponseEntity<LessonDto> updateLesson(@RequestBody LessonDto lessonDto) {
		var lessonEntityOpt = lessonService.updateLesson(lessonDto);
		return ResponseEntity.of(lessonEntityOpt.map(lessonMapper::toDto));
	}

	public static class HelperStudentAndLessonNumber {
		Integer lessonNumber;
		StudentDto studentDto;
	}
}
