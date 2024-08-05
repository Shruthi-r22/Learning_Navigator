package com.crio.learningnavigator.service.implementation;

import com.crio.learningnavigator.entity.Exam;
import com.crio.learningnavigator.entity.Student;
import com.crio.learningnavigator.entity.Subject;
import com.crio.learningnavigator.exchange.request.StudentRequest;
import com.crio.learningnavigator.exchange.response.UpdateStudentResponse;
import com.crio.learningnavigator.repository.ExamRepository;
import com.crio.learningnavigator.repository.StudentRepository;
import com.crio.learningnavigator.repository.SubjectRepository;
import com.crio.learningnavigator.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

/**
 * The StudentServiceImpl class implements the StudentService interface to provide CRUD operations for students.
 *
 * <p>It interacts with Student, Subject, and Exam entities using StudentRepository, SubjectRepository, and ExamRepository respectively.</p>
 *
 * <p><b>Dependencies:</b></p>
 * <ul>
 *   <li>{@code StudentRepository studentRepository} - Repository for Student entities.</li>
 *   <li>{@code SubjectRepository subjectRepository} - Repository for Subject entities.</li>
 *   <li>{@code ExamRepository examRepository} - Repository for Exam entities.</li>
 * </ul>
 *
 * <p><b>Constructor:</b></p>
 * <ul>
 *   <li>{@code StudentServiceImpl(StudentRepository studentRepository, SubjectRepository subjectRepository, ExamRepository examRepository)} - Constructor to initialize the service with repositories.</li>
 * </ul>
 *
 * <p><b>Methods:</b></p>
 * <ul>
 *   <li>{@code create(StudentRequest data)} - Creates a new student based on the provided data.</li>
 *   <li>{@code get(Long id)} - Retrieves a student by its ID.</li>
 *   <li>{@code get()} - Retrieves all students.</li>
 *   <li>{@code update(Long id, StudentRequest data)} - Updates an existing student based on the provided ID and data.</li>
 *   <li>{@code delete(Long id)} - Deletes a student by its ID.</li>
 * </ul>
 *
 * <p><b>Exceptions:</b></p>
 * <ul>
 *   <li>{@code ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found")} - Thrown when a student is not found during get, update, or delete operations.</li>
 * </ul>
 *
 * <p><b>Usage:</b></p>
 * <p>This class should be used to perform CRUD operations for students, including creation, retrieval, updating, and deletion.</p>
 *
 * <pre>{@code
 * // Example usage:
 * StudentService studentService = new StudentServiceImpl(studentRepository, subjectRepository, examRepository);
 * Student student = studentService.get(1L);
 * }</pre>
 *
 * @see com.crio.learningnavigator.service.StudentService
 */
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final ExamRepository examRepository;

    /**
     * Constructor to initialize StudentServiceImpl with repositories.
     *
     * @param studentRepository Repository for Student entities
     * @param subjectRepository Repository for Subject entities
     * @param examRepository Repository for Exam entities
     */
    public StudentServiceImpl(StudentRepository studentRepository, SubjectRepository subjectRepository, ExamRepository examRepository) {
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
        this.examRepository = examRepository;
    }

    /**
     * Creates a new student based on the provided data.
     *
     * @param data the StudentRequest containing data for the new student
     * @return the created Student object
     */
    @Override
    public Student create(StudentRequest data) {
        Student student = new Student();
        if (data.getName() != null) {
            student.setName(data.getName());
        }
        if (data.getEnrolledSubjects() != null) {
            student.setEnrolledSubjects(data.getEnrolledSubjects());
        }
        if (data.getEnrolledExams() != null) {
            student.setEnrolledExams(data.getEnrolledExams());
        }
        return studentRepository.save(student);
    }

    /**
     * Retrieves a student by its ID.
     *
     * @param id the ID of the student to retrieve
     * @return the Student object if found
     * @throws ResponseStatusException if the student is not found
     */
    @Override
    public Student get(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            return student.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found");

    }

    /**
     * Retrieves all students.
     *
     * @return a list of all Student objects
     */
    @Override
    public List<Student> get() {
        return studentRepository.findAll();
    }

    /**
     * Updates an existing student based on the provided ID and data.
     *
     * @param id   the ID of the student to update
     * @param data the StudentRequest containing updated data
     * @return an UpdateStudentResponse object containing the updated Student, not found subject IDs, not found exam IDs, and any error messages
     * @throws ResponseStatusException if the student is not found
     */
    @Override
    public UpdateStudentResponse update(Long id, StudentRequest data) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            student.setId(id);

            // Update Name
            if (data.getName() != null && !data.getName().isEmpty()) {
                student.setName(data.getName());
            }

            // Update Subjects
            List<Long> notFoundSubjectIds = new ArrayList<>();
            if (data.getEnrolledSubjects() != null && !data.getEnrolledSubjects().isEmpty()) {
                Set<Long> subjectIds = new HashSet<>();
                for (Subject subject : data.getEnrolledSubjects()) {
                    subjectIds.add(subject.getId());
                }
                List<Subject> subjects = subjectRepository.findAllById(subjectIds);

                // Check which subject IDs were not found
                for (Long subjectId : subjectIds) {
                    boolean found = subjects.stream().anyMatch(sub -> sub.getId().equals(subjectId));
                    if (!found) {
                        notFoundSubjectIds.add(subjectId);
                    }
                }

                // Merge existing and new subjects, ensuring uniqueness
                Set<Subject> mergedSubjects = new HashSet<>(student.getEnrolledSubjects());
                mergedSubjects.addAll(subjects);
                student.setEnrolledSubjects(mergedSubjects);
            }

            // Update Exams
            List<Long> notFoundExamIds = new ArrayList<>();
            Set<Subject> invalidSubjects = new HashSet<>();
            if (data.getEnrolledExams() != null && !data.getEnrolledExams().isEmpty()) {
                Set<Long> examIds = new HashSet<>();
                for (Exam exam : data.getEnrolledExams()) {
                    examIds.add(exam.getId());
                }

                List<Exam> exams = examRepository.findAllById(examIds);

                // Check which exam IDs were not found
                for (Long examId : examIds) {
                    boolean found = exams.stream().anyMatch(e -> e.getId().equals(examId));
                    if (!found) {
                        notFoundExamIds.add(examId);
                    }
                }

                // Filter exams to only include those for enrolled subjects
                StringBuilder stringBuilder = new StringBuilder();
                Set<Exam> validExams = new HashSet<>();
                for (Exam exam : exams) {
                    if (student.getEnrolledSubjects().contains(exam.getSubject())) {
                        validExams.add(exam);
                    } else {
                        invalidSubjects.add(exam.getSubject());
                        stringBuilder.append(exam).append(" ");
                    }
                }

                // Merge existing and new valid exams, ensuring uniqueness
                Set<Exam> mergedExams = new HashSet<>(student.getEnrolledExams());
                mergedExams.addAll(validExams);
                student.setEnrolledExams(mergedExams);

                // Create response with updated student, not found subject and exam IDs, and error message
                return new UpdateStudentResponse(studentRepository.save(student), notFoundSubjectIds, notFoundExamIds, invalidSubjects.isEmpty() ? null : stringBuilder.toString());
            }

            // Save updated student without exams
            return new UpdateStudentResponse(studentRepository.save(student), notFoundSubjectIds, notFoundExamIds, null);
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found");
    }

    /**
     * Deletes a student by its ID.
     *
     * @param id the ID of the student to delete
     * @return true if the student is successfully deleted
     * @throws ResponseStatusException if the student is not found
     */
    @Override
    public boolean delete(Long id) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        if (studentOptional.isPresent()) {
            studentRepository.deleteById(id);
            return true;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found");
    }
}
