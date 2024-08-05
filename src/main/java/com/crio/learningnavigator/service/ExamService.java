package com.crio.learningnavigator.service;

import com.crio.learningnavigator.entity.Exam;
import com.crio.learningnavigator.exchange.request.ExamRequest;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * The ExamService interface defines methods for managing exams.
 *
 * <p>Implementations of this interface provide functionality to create, retrieve,
 * update, and delete exams.</p>
 *
 * <p><b>Methods:</b></p>
 * <ul>
 *   <li>{@code create(ExamRequest data)} - Creates a new exam based on the provided data.</li>
 *   <li>{@code get(Long id)} - Retrieves an exam by its ID.</li>
 *   <li>{@code get()} - Retrieves a list of all exams.</li>
 *   <li>{@code update(Long id, ExamRequest data)} - Updates an exam identified by its ID with the provided data.</li>
 *   <li>{@code delete(Long id)} - Deletes an exam by its ID.</li>
 * </ul>
 *
 * <p><b>Usage:</b></p>
 * <p>This interface should be implemented to provide functionality for managing exams,
 * including creation, retrieval, updating, and deletion operations.</p>
 *
 * <pre>{@code
 * // Example usage:
 * ExamService examService = new ExamServiceImpl(examRepository, subjectRepository);
 * Exam newExam = examService.create(new ExamRequest("Math Exam", subjectId));
 * }</pre>
 *
 * @see com.crio.learningnavigator.service.implementation.ExamServiceImpl
 */
public interface ExamService {

    /**
     * Creates a new exam based on the provided data.
     *
     * @param data the exam request data containing details of the exam to be created
     * @return the created exam
     */
    Exam create(ExamRequest data);

    /**
     * Retrieves an exam by its ID.
     *
     * @param id the ID of the exam to retrieve
     * @return the exam with the specified ID
     * @throws org.springframework.web.server.ResponseStatusException(404) if the exam with the specified ID is not found
     */
    Exam get(Long id);

    /**
     * Retrieves a list of all exams.
     *
     * @return a list containing all exams
     */
    List<Exam> get();

    /**
     * Updates an exam identified by its ID with the provided data.
     *
     * @param id   the ID of the exam to update
     * @param data the exam request data containing updated details
     * @return the updated exam
     * @throws ResponseStatusException if the subject associated with the exam is not found
     */
    Exam update(Long id, ExamRequest data);

    /**
     * Deletes an exam by its ID.
     *
     * @param id the ID of the exam to delete
     * @return {@code true} if the exam is successfully deleted, {@code false} otherwise
     * @throws org.springframework.web.server.ResponseStatusException(404) if the exam with the specified ID is not found
     */
    boolean delete(Long id);
}
