package com.crio.learningnavigator.controller;

import com.crio.learningnavigator.entity.Student;
import com.crio.learningnavigator.exchange.request.StudentRequest;
import com.crio.learningnavigator.exchange.response.GenericResponse;
import com.crio.learningnavigator.exchange.response.UpdateStudentResponse;
import com.crio.learningnavigator.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * The StudentController class handles HTTP requests related to students.
 *
 * <p>This controller provides endpoints to retrieve, create, update, and delete student records.</p>
 */
@RestController
@RequestMapping("/students")
@Validated
public class StudentController {

    private final StudentService studentService;

    /**
     * Constructs a new StudentController with the provided StudentService.
     *
     * @param studentService the service responsible for handling student-related operations
     */
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * Retrieves all students.
     *
     * @return ResponseEntity with a list of students wrapped in GenericResponse
     */
    @GetMapping("")
    public ResponseEntity<?> getStudents() {
        return ResponseEntity.ok().body(new GenericResponse<>(studentService.get()));
    }

    /**
     * Retrieves a specific student by ID.
     *
     * @param id the ID of the student to retrieve
     * @return ResponseEntity with the retrieved student wrapped in GenericResponse
     */
    @GetMapping("{id}")
    public ResponseEntity<?> getStudent(@PathVariable Long id) {
        return ResponseEntity.ok().body(new GenericResponse<>(studentService.get(id)));
    }

    /**
     * Creates a new student.
     *
     * @param data the request body containing details of the student to create
     * @return ResponseEntity with the created student wrapped in GenericResponse
     */
    @PostMapping("")
    public ResponseEntity<?> saveStudent(@RequestBody StudentRequest data) {
        Student student = studentService.create(data);
        return ResponseEntity.ok().body(new GenericResponse<>(student));
    }

    /**
     * Updates an existing student.
     *
     * @param id   the ID of the student to update
     * @param data the request body containing updated details of the student
     * @return ResponseEntity with the updated student wrapped in GenericResponse,
     * or a ResponseEntity with HTTP status 207 (Multi-Status) if there are issues with exam or subject IDs
     */
    @PutMapping("{id}")
    public ResponseEntity<?> updateStudent(@PathVariable Long id, @RequestBody StudentRequest data) {
        UpdateStudentResponse student = studentService.update(id, data);
        if (student.getNotFoundExamIds().isEmpty() && student.getNotFoundSubjectIds().isEmpty()) {
            return ResponseEntity.ok().body(new GenericResponse<>(student));
        } else {
            return ResponseEntity.status(HttpStatus.MULTI_STATUS).body(new GenericResponse<>(student));
        }
    }

    /**
     * Deletes a student.
     *
     * @param id the ID of the student to delete
     * @return ResponseEntity indicating success or failure of the delete operation
     */
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        return ResponseEntity.ok().body(new GenericResponse<>(studentService.delete(id)));
    }

}
