package com.glpserver.glp.controller;

import com.glpserver.glp.domain.dto.LessonDto;
import com.glpserver.glp.domain.dto.StudentDto;
import com.glpserver.glp.domain.mapper.LessonMapper;
import com.glpserver.glp.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
		return ResponseEntity.of(lessonEntityOpt.map(lessonMapper::toDto));
	}

	@GetMapping
	public ResponseEntity<List<LessonDto>> findAllLessons() {
		var lessonEntitiesOpt = lessonService.findAllLessons();
		return ResponseEntity.of(lessonEntitiesOpt.map(lessonEntities -> lessonEntities.stream().map(lessonMapper::toDto).collect(Collectors.toList())));
	}

	@GetMapping("/student/{studentId}")
	public ResponseEntity<List<LessonDto>> findLessonsByStudentId(@PathVariable Long studentId) {
		var lessonEntitiesOpt = lessonService.findLessonsByStudentId(studentId);
		return ResponseEntity.of(lessonEntitiesOpt.map(lessonEntities -> lessonEntities.stream().map(lessonMapper::toDto).collect(Collectors.toList())));
	}

	@GetMapping("/id/{lessonId}")
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

	@DeleteMapping("/id/{lessonId}")
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
