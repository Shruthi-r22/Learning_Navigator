package com.crio.learningnavigator.service.implementation;

import com.crio.learningnavigator.entity.Subject;
import com.crio.learningnavigator.exchange.request.SubjectRequest;
import com.crio.learningnavigator.repository.SubjectRepository;
import com.crio.learningnavigator.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The SubjectServiceImpl class implements the SubjectService interface to provide CRUD operations for subjects.
 *
 * <p>It interacts with Subject entities using SubjectRepository.</p>
 *
 * <p><b>Dependencies:</b></p>
 * <ul>
 *   <li>{@code SubjectRepository subjectRepository} - Repository for Subject entities.</li>
 * </ul>
 *
 * <p><b>Constructor:</b></p>
 * <ul>
 *   <li>{@code SubjectServiceImpl(SubjectRepository subjectRepository)} - Constructor to initialize the service with the repository.</li>
 * </ul>
 *
 * <p><b>Methods:</b></p>
 * <ul>
 *   <li>{@code create(SubjectRequest data)} - Creates a new subject based on the provided data.</li>
 *   <li>{@code create(List<SubjectRequest> data)} - Creates multiple subjects based on the provided list of data.</li>
 *   <li>{@code get(Long id)} - Retrieves a subject by its ID.</li>
 *   <li>{@code get()} - Retrieves all subjects.</li>
 *   <li>{@code update(Long id, SubjectRequest data)} - Updates an existing subject based on the provided ID and data.</li>
 *   <li>{@code delete(Long id)} - Deletes a subject by its ID.</li>
 * </ul>
 *
 * <p><b>Exceptions:</b></p>
 * <ul>
 *   <li>{@code ResponseStatusException(HttpStatus.NOT_FOUND, "Subject Not Found")} - Thrown when a subject is not found during get, update, or delete operations.</li>
 * </ul>
 *
 * <p><b>Usage:</b></p>
 * <p>This class should be used to perform CRUD operations for subjects, including creation, retrieval, updating, and deletion.</p>
 *
 * <pre>{@code
 * // Example usage:
 * SubjectService subjectService = new SubjectServiceImpl(subjectRepository);
 * Subject subject = subjectService.get(1L);
 * }</pre>
 *
 * @see com.crio.learningnavigator.service.SubjectService
 */
@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;

    /**
     * Creates a new subject based on the provided data.
     *
     * @param data the SubjectRequest containing data for the new subject
     * @return the created Subject object
     */
    @Override
    public Subject create(SubjectRequest data) {
        Subject subject = new Subject();
        subject.setName(data.getName());
        return subjectRepository.save(subject);
    }

    /**
     * Creates multiple subjects based on the provided list of data.
     *
     * @param data the list of SubjectRequest containing data for the new subjects
     * @return the list of created Subject objects
     */
    @Override
    public List<Subject> create(List<SubjectRequest> data) {
        List<Subject> subjects = new ArrayList<>();
        data.forEach((s) -> subjects.add(new Subject(s.getName())));
        return subjectRepository.saveAll(subjects);
    }

    /**
     * Retrieves a subject by its ID.
     *
     * @param id the ID of the subject to retrieve
     * @return the Subject object if found
     * @throws ResponseStatusException if the subject is not found
     */
    @Override
    public Subject get(Long id) {
        Optional<Subject> subject = subjectRepository.findById(id);
        if (subject.isPresent())
            return subject.get();
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Subject Not Found");
    }

    /**
     * Retrieves all subjects.
     *
     * @return a list of all Subject objects
     */
    @Override
    public List<Subject> get() {
        return subjectRepository.findAll();
    }

    /**
     * Updates an existing subject based on the provided ID and data.
     *
     * @param id   the ID of the subject to update
     * @param data the SubjectRequest containing updated data
     * @return the updated Subject object
     * @throws ResponseStatusException if the subject is not found
     */
    @Override
    public Subject update(Long id, SubjectRequest data) {
        Optional<Subject> s = subjectRepository.findById(id);
        if (s.isPresent()) {
            Subject subject = s.get();
            subject.setName(data.getName());
            return subjectRepository.save(subject);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Subject Not Found");
    }

    /**
     * Deletes a subject by its ID.
     *
     * @param id the ID of the subject to delete
     * @return true if the subject is successfully deleted
     * @throws ResponseStatusException if the subject is not found
     */
    @Override
    public boolean delete(Long id) {
        Optional<Subject> subject = subjectRepository.findById(id);
        if (subject.isPresent()) {
            subjectRepository.deleteById(id);
            return true;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Subject Not Found");
    }
}
