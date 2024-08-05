package com.crio.learningnavigator.service;

import com.crio.learningnavigator.entity.Subject;
import com.crio.learningnavigator.exchange.request.SubjectRequest;

import java.util.List;

/**
 * The SubjectService interface defines methods for managing subjects.
 *
 * <p>Implementations of this interface provide functionality to create, retrieve,
 * update, and delete subjects.</p>
 *
 * <p><b>Methods:</b></p>
 * <ul>
 *   <li>{@code create(SubjectRequest data)} - Creates a new subject based on the provided data.</li>
 *   <li>{@code create(List<SubjectRequest> data)} - Creates multiple subjects based on the provided list of data.</li>
 *   <li>{@code get(Long id)} - Retrieves a subject by its ID.</li>
 *   <li>{@code get()} - Retrieves a list of all subjects.</li>
 *   <li>{@code update(Long id, SubjectRequest data)} - Updates a subject identified by its ID with the provided data.</li>
 *   <li>{@code delete(Long id)} - Deletes a subject by its ID.</li>
 * </ul>
 *
 * <p><b>Usage:</b></p>
 * <p>This interface should be implemented to provide functionality for managing subjects,
 * including creation, retrieval, updating, and deletion operations.</p>
 *
 * <pre>{@code
 * // Example usage:
 * SubjectService subjectService = new SubjectServiceImpl(subjectRepository);
 * Subject newSubject = subjectService.create(new SubjectRequest("Mathematics"));
 * }</pre>
 *
 * @see com.crio.learningnavigator.service.implementation.SubjectServiceImpl
 */
public interface SubjectService {

    /**
     * Creates a new subject based on the provided data.
     *
     * @param data the subject request containing details of the subject to be created
     * @return the created subject
     */
    Subject create(SubjectRequest data);

    /**
     * Creates multiple subjects based on the provided list of data.
     *
     * @param data the list of subject requests containing details of the subjects to be created
     * @return a list containing all created subjects
     */
    List<Subject> create(List<SubjectRequest> data);

    /**
     * Retrieves a subject by its ID.
     *
     * @param id the ID of the subject to retrieve
     * @return the subject with the specified ID
     * @throws org.springframework.web.server.ResponseStatusException(404) if the subject with the specified ID is not found
     */
    Subject get(Long id);

    /**
     * Retrieves a list of all subjects.
     *
     * @return a list containing all subjects
     */
    List<Subject> get();

    /**
     * Updates a subject identified by its ID with the provided data.
     *
     * @param id   the ID of the subject to update
     * @param data the subject request containing updated details
     * @return the updated subject
     * @throws org.springframework.web.server.ResponseStatusException(404)if the subject with the specified ID is not found
     */
    Subject update(Long id, SubjectRequest data);

    /**
     * Deletes a subject by its ID.
     *
     * @param id the ID of the subject to delete
     * @return {@code true} if the subject is successfully deleted, {@code false} otherwise
     * @throws org.springframework.web.server.ResponseStatusException(404) if the subject with the specified ID is not found
     */
    boolean delete(Long id);
}
