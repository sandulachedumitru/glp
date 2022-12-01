package com.glpserver.glp.controller;

import com.glpserver.glp.domain.dto.StudentDto;
import com.glpserver.glp.domain.mapper.StudentMapper;
import com.glpserver.glp.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Dumitru Săndulache (sandulachedumitru@hotmail.com)
 */

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {
	private final StudentService studentService;
	private final StudentMapper studentMapper;

	@PostMapping
	public ResponseEntity<StudentDto> createNewStudent(@RequestBody StudentDto studentDto) {
		var studentEntityOpt = studentService.createNewStudent(studentDto);
		return ResponseEntity.of(studentEntityOpt.map(studentMapper::toDto));
	}

	@GetMapping
	public ResponseEntity<List<StudentDto>> findAllStudents() {
		var studentEntitiesOpt = studentService.findAllStudents();
		return ResponseEntity.of(studentEntitiesOpt.map(studentEntities -> studentEntities.stream().map(studentMapper::toDto).collect(Collectors.toList())));
	}

	@GetMapping("/id/{studentId}")
	public ResponseEntity<StudentDto> findStudentById(@PathVariable Long studentId) {
		var studentEntityOpt = studentService.findStudentById(studentId);
		return ResponseEntity.of(studentEntityOpt.map(studentMapper::toDto));
	}

	@GetMapping("/email/{studentEmail}")
	public ResponseEntity<StudentDto> findStudentByEmail(@PathVariable String studentEmail) {
		var studentEntityOpt = studentService.findStudentByEmail(studentEmail);
		return ResponseEntity.of(studentEntityOpt.map(studentMapper::toDto));
	}

	@GetMapping("/name")
	public ResponseEntity<StudentDto> findStudentByFirstNameAndLastName(@RequestParam String studentFirstName, @RequestParam String studentLastName) {
		var studentEntityOpt = studentService.findStudentByFirstNameAndLastName(studentFirstName, studentLastName);
		return ResponseEntity.of(studentEntityOpt.map(studentMapper::toDto));
	}

	@DeleteMapping("/id/{studentId}")
	public ResponseEntity<StudentDto> deleteStudentById(@PathVariable Long studentId) {
		var studentEntityOpt = studentService.deleteStudentById(studentId);
		return ResponseEntity.of(studentEntityOpt.map(studentMapper::toDto));
	}

	@DeleteMapping("/email/{studentEmail}")
	public ResponseEntity<StudentDto> deleteStudentByEmail(@PathVariable String studentEmail) {
		var studentEntityOpt = studentService.deleteStudentByEmail(studentEmail);
		return ResponseEntity.of(studentEntityOpt.map(studentMapper::toDto));
	}

	@DeleteMapping("/name")
	public ResponseEntity<StudentDto> deleteStudentByFirstNameAndLastName(@RequestParam String studentFirstName, @RequestParam String studentLastName) {
		var studentEntityOpt = studentService.deleteStudentByFirstNameAndLastName(studentFirstName, studentLastName);
		return ResponseEntity.of(studentEntityOpt.map(studentMapper::toDto));
	}

	@PutMapping
	public ResponseEntity<StudentDto> updateStudent(@RequestBody StudentDto studentDto) {
		var studentEntityOpt = studentService.updateStudent(studentDto);
		return ResponseEntity.of(studentEntityOpt.map(studentMapper::toDto));
	}
}
