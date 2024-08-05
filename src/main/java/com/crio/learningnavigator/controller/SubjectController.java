package com.crio.learningnavigator.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crio.learningnavigator.entity.Subject;
import com.crio.learningnavigator.exchange.request.SubjectRequest;
import com.crio.learningnavigator.exchange.response.GenericResponse;
import com.crio.learningnavigator.service.SubjectService;

import jakarta.validation.Valid;

/**
 * The SubjectController class handles HTTP requests related to subjects.
 *
 * <p>This controller provides endpoints to retrieve, create, update, and delete subject records.</p>
 */
@RestController
@RequestMapping("/subjects")
@Validated
public class SubjectController {

    private final SubjectService subjectService;

    /**
     * Constructs a new SubjectController with the provided SubjectService.
     *
     * @param subjectService the service responsible for handling subject-related operations
     */
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    /**
     * Retrieves all subjects.
     *
     * @return ResponseEntity with a list of subjects wrapped in GenericResponse
     */
    @GetMapping("")
    public ResponseEntity<?> getSubjects() {
        return ResponseEntity.ok().body(new GenericResponse<>(subjectService.get()));
    }

    /**
     * Retrieves a specific subject by ID.
     *
     * @param id the ID of the subject to retrieve
     * @return ResponseEntity with the retrieved subject wrapped in GenericResponse
     */
    @GetMapping("{id}")
    public ResponseEntity<?> getSubject(@PathVariable Long id) {
        return ResponseEntity.ok().body(new GenericResponse<>(subjectService.get(id)));
    }

    /**
     * Creates a new subject.
     *
     * @param data the request body containing details of the subject to create
     * @return ResponseEntity with the created subject wrapped in GenericResponse
     */
    @PostMapping("")
    public ResponseEntity<?> saveSubject(@Valid @RequestBody SubjectRequest data) {
        Subject subject = subjectService.create(data);
        return ResponseEntity.ok().body(new GenericResponse<>(subject));
    }

    /**
     * Updates an existing subject.
     *
     * @param id   the ID of the subject to update
     * @param data the request body containing updated details of the subject
     * @return ResponseEntity with the updated subject wrapped in GenericResponse
     */
    @PutMapping("{id}")
    public ResponseEntity<?> updateSubject(@PathVariable Long id, @Valid @RequestBody SubjectRequest data) {
        Subject subject = subjectService.update(id, data);
        return ResponseEntity.ok().body(new GenericResponse<>(subject));
    }

    /**
     * Deletes a subject.
     *
     * @param id the ID of the subject to delete
     * @return ResponseEntity indicating success or failure of the delete operation
     */
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteSubject(@PathVariable Long id) {
        return ResponseEntity.ok().body(new GenericResponse<>(subjectService.delete(id)));
    }

}
