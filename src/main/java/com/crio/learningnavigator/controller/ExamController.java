
package com.crio.learningnavigator.controller;

import com.crio.learningnavigator.entity.Exam;
import com.crio.learningnavigator.exchange.request.ExamRequest;
import com.crio.learningnavigator.exchange.response.GenericResponse;
import com.crio.learningnavigator.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * The ExamController class handles HTTP requests related to exams.
 *
 * <p>This controller provides endpoints to retrieve, create, update, and delete exams.</p>
 */
@RestController
@RequestMapping("/exams")
@RequiredArgsConstructor
@Validated
public class ExamController {

    private final ExamService examService;

    /**
     * Retrieves all exams.
     *
     * @return ResponseEntity with a list of exams wrapped in GenericResponse
     */
    @GetMapping("")
    public ResponseEntity<?> getExams() {
        return ResponseEntity.ok().body(new GenericResponse<>(examService.get()));
    }

    /**
     * Retrieves a specific exam by its ID.
     *
     * @param id the ID of the exam to retrieve
     * @return ResponseEntity with the retrieved exam wrapped in GenericResponse
     */
    @GetMapping("{id}")
    public ResponseEntity<?> getExam(@PathVariable Long id) {
        return ResponseEntity.ok().body(new GenericResponse<>(examService.get(id)));
    }

    /**
     * Creates a new exam.
     *
     * @param data the request body containing details of the exam to create
     * @return ResponseEntity with the created exam wrapped in GenericResponse
     */
    @PostMapping("")
    public ResponseEntity<?> saveExam(@RequestBody ExamRequest data) {
        Exam exam = examService.create(data);
        return ResponseEntity.ok().body(new GenericResponse<>(exam));
    }

    /**
     * Updates an existing exam.
     *
     * @param id   the ID of the exam to update
     * @param data the request body containing updated details of the exam
     * @return ResponseEntity with the updated exam wrapped in GenericResponse
     */
    @PutMapping("{id}")
    public ResponseEntity<?> updateExam(@PathVariable Long id, @RequestBody ExamRequest data) {
        Exam exam = examService.update(id, data);
        return ResponseEntity.ok().body(new GenericResponse<>(exam));
    }

    /**
     * Deletes an exam.
     *
     * @param id the ID of the exam to delete
     * @return ResponseEntity indicating success or failure of the delete operation
     */
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteExam(@PathVariable Long id) {
        return ResponseEntity.ok().body(new GenericResponse<>(examService.delete(id)));
    }
}
