package com.crio.learningnavigator.service;

import com.crio.learningnavigator.entity.Student;
import com.crio.learningnavigator.exchange.request.StudentRequest;
import com.crio.learningnavigator.exchange.response.UpdateStudentResponse;

import java.util.List;

/**
 * The StudentService interface defines methods for managing students.
 *
 * <p>Implementations of this interface provide functionality to create, retrieve,
 * update, and delete students.</p>
 *
 * <p><b>Methods:</b></p>
 * <ul>
 *   <li>{@code create(StudentRequest student)} - Creates a new student based on the provided data.</li>
 *   <li>{@code get(Long id)} - Retrieves a student by their ID.</li>
 *   <li>{@code get()} - Retrieves a list of all students.</li>
 *   <li>{@code update(Long id, StudentRequest student)} - Updates a student identified by their ID with the provided data.</li>
 *   <li>{@code delete(Long id)} - Deletes a student by their ID.</li>
 * </ul>
 *
 * <p><b>Usage:</b></p>
 * <p>This interface should be implemented to provide functionality for managing students,
 * including creation, retrieval, updating, and deletion operations.</p>
 *
 * <pre>{@code
 * // Example usage:
 * StudentService studentService = new StudentServiceImpl(studentRepository, subjectRepository, examRepository);
 * Student newStudent = studentService.create(new StudentRequest("John Doe", enrolledSubjects, enrolledExams));
 * }</pre>
 *
 * @see com.crio.learningnavigator.service.implementation.StudentServiceImpl
 */
public interface StudentService {

    /**
     * Creates a new student based on the provided data.
     *
     * @param student the student request containing details of the student to be created
     * @return the created student
     */
    Student create(StudentRequest student);

    /**
     * Retrieves a student by their ID.
     *
     * @param id the ID of the student to retrieve
     * @return the student with the specified ID
     * @throws org.springframework.web.server.ResponseStatusException(404) if the student with the specified ID is not found
     */
    Student get(Long id);

    /**
     * Retrieves a list of all students.
     *
     * @return a list containing all students
     */
    List<Student> get();

    /**
     * Updates a student identified by their ID with the provided data.
     *
     * @param id      the ID of the student to update
     * @param student the student request containing updated details
     * @return an {@link com.crio.learningnavigator.exchange.response.UpdateStudentResponse} object containing the updated student,
     *         lists of not found subject and exam IDs, and a message if there are invalid subjects for exams
     * @throws org.springframework.web.server.ResponseStatusException(404) if the student with the specified ID is not found
     */
    UpdateStudentResponse update(Long id, StudentRequest student);

    /**
     * Deletes a student by their ID.
     *
     * @param id the ID of the student to delete
     * @return {@code true} if the student is successfully deleted, {@code false} otherwise
     * @throws org.springframework.web.server.ResponseStatusException(404) if the student with the specified ID is not found
     */
    boolean delete(Long id);
}
