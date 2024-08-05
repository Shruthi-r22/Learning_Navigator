package com.crio.learningnavigator.service.implementation;

import com.crio.learningnavigator.entity.Exam;
import com.crio.learningnavigator.entity.Subject;
import com.crio.learningnavigator.exchange.request.ExamRequest;
import com.crio.learningnavigator.repository.ExamRepository;
import com.crio.learningnavigator.repository.SubjectRepository;
import com.crio.learningnavigator.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

/**
 * The ExamServiceImpl class implements the ExamService interface to provide CRUD operations for exams.
 *
 * <p>It interacts with Exam and Subject entities using ExamRepository and SubjectRepository respectively.</p>
 *
 * <p><b>Dependencies:</b></p>
 * <ul>
 *   <li>{@code ExamRepository examRepository} - Repository for Exam entities.</li>
 *   <li>{@code SubjectRepository subjectRepository} - Repository for Subject entities.</li>
 * </ul>
 *
 * <p><b>Constructor:</b></p>
 * <ul>
 *   <li>{@code ExamServiceImpl(ExamRepository examRepository, SubjectRepository subjectRepository)} - Constructor to initialize the service with repositories.</li>
 * </ul>
 *
 * <p><b>Methods:</b></p>
 * <ul>
 *   <li>{@code create(ExamRequest data)} - Creates a new exam based on the provided data.</li>
 *   <li>{@code get(Long id)} - Retrieves an exam by its ID.</li>
 *   <li>{@code get()} - Retrieves all exams.</li>
 *   <li>{@code update(Long id, ExamRequest data)} - Updates an existing exam based on the provided ID and data.</li>
 *   <li>{@code delete(Long id)} - Deletes an exam by its ID.</li>
 * </ul>
 *
 * <p><b>Exceptions:</b></p>
 * <ul>
 *   <li>{@code ResponseStatusException(HttpStatus.CONFLICT, "Exam already exist for this subject")} - Thrown when trying to create or update an exam with an existing exam for the same subject.</li>
 *   <li>{@code ResponseStatusException(HttpStatus.NOT_FOUND, "Subject not found")} - Thrown when the subject is not found during create or update operations.</li>
 *   <li>{@code ResponseStatusException(HttpStatus.NOT_FOUND, "Exam not found")} - Thrown when an exam is not found during get, update, or delete operations.</li>
 * </ul>
 *
 * <p><b>Usage:</b></p>
 * <p>This class should be used to perform CRUD operations for exams, including creation, retrieval, updating, and deletion.</p>
 *
 * <pre>{@code
 * // Example usage:
 * ExamService examService = new ExamServiceImpl(examRepository, subjectRepository);
 * Exam exam = examService.get(1L);
 * }</pre>
 *
 * @see com.crio.learningnavigator.service.ExamService
 */
@Service
@RequiredArgsConstructor
public class ExamServiceImpl implements ExamService {

    private final ExamRepository examRepository;
    private final SubjectRepository subjectRepository;

    /**
     * Creates a new exam based on the provided data.
     *
     * @param data the ExamRequest containing data for the new exam
     * @return the created Exam object
     * @throws ResponseStatusException if the subject is not found or if an exam already exists for the subject
     */
    @Override
    public Exam create(ExamRequest data) throws ResponseStatusException {
        Exam exam = new Exam();
        Optional<Subject> subject = subjectRepository.findById(data.getSubjectId());
        if (subject.isPresent()) {
            Exam existingExam = examRepository.findBySubject_Id(data.getSubjectId());
            if (existingExam != null) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Exam already exist for this subject");
            }
            exam.setSubject(subject.get());
            exam.setName(data.getName());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Subject not found");
        }
        return examRepository.save(exam);
    }

    /**
     * Retrieves an exam by its ID.
     *
     * @param id the ID of the exam to retrieve
     * @return the Exam object if found
     * @throws ResponseStatusException if the exam is not found
     */
    @Override
    public Exam get(Long id) throws ResponseStatusException {
        return examRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Exam not found"));
    }

    /**
     * Retrieves all exams.
     *
     * @return a list of all Exam objects
     */
    @Override
    public List<Exam> get() {
        return examRepository.findAll();
    }

    /**
     * Updates an existing exam based on the provided ID and data.
     *
     * @param id   the ID of the exam to update
     * @param data the ExamRequest containing updated data
     * @return the updated Exam object
     * @throws ResponseStatusException if the exam or subject is not found, or if an exam already exists for the subject
     */
    @Override
    public Exam update(Long id, ExamRequest data) throws ResponseStatusException {
        Optional<Exam> examOptional = examRepository.findById(id);
        if (examOptional.isPresent()) {
            Exam exam = examOptional.get();
            if (data.getSubjectId() != null) {
                Optional<Subject> subjectOptional = subjectRepository.findById(data.getSubjectId());
                if (subjectOptional.isPresent()) {
                    Exam existingExam = examRepository.findBySubject_Id(data.getSubjectId());
                    if (existingExam != null && !existingExam.getId().equals(exam.getId())) {
                        throw new ResponseStatusException(HttpStatus.CONFLICT, "Exam already exist for this subject");
                    }
                    exam.setSubject(subjectOptional.get());
                } else {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Subject not found");
                }
            }
            if (data.getName() != null && !data.getName().isEmpty()) {
                exam.setName(data.getName());
            }
            return examRepository.save(exam);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Exam not found");
    }

    /**
     * Deletes an exam by its ID.
     *
     * @param id the ID of the exam to delete
     * @return true if the exam is successfully deleted
     * @throws ResponseStatusException if the exam is not found
     */
    @Override
    public boolean delete(Long id) throws ResponseStatusException {
        if (examRepository.existsById(id)) {
            examRepository.deleteById(id);
            return true;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Exam not found");
        }
    }
}
