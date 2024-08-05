package com.crio.learningnavigator.exchange.response;

import com.crio.learningnavigator.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * The UpdateStudentResponse class represents a response for updating a student.
 *
 * <p>This class encapsulates the updated student object, lists of IDs for subjects and exams not found during the update process,
 * and an optional message providing additional information about the update.</p>
 *
 * <p><b>Fields:</b></p>
 * <ul>
 *   <li>{@code Student student} - The updated Student object.</li>
 *   <li>{@code List<Long> notFoundSubjectIds} - The list of IDs for subjects not found during the update process.</li>
 *   <li>{@code List<Long> notFoundExamIds} - The list of IDs for exams not found during the update process.</li>
 *   <li>{@code String message} - An optional message providing additional information about the update.</li>
 * </ul>
 *
 * <p><b>Annotations:</b></p>
 * <ul>
 *   <li>{@code @Data} - Generates getters, setters, toString, equals, and hashCode methods.</li>
 *   <li>{@code @AllArgsConstructor} - Generates a constructor with arguments for all fields.</li>
 * </ul>
 *
 * <p><b>Usage:</b></p>
 * <p>This class should be used to provide a detailed response after updating a student, including the updated student object,
 * information about subjects and exams not found, and an optional message.</p>
 *
 * <pre>{@code
 * // Example usage:
 * UpdateStudentResponse response = new UpdateStudentResponse(updatedStudent, notFoundSubjectIds, notFoundExamIds, "Student updated successfully.");
 * }</pre>
 */
@Data
@AllArgsConstructor
public class UpdateStudentResponse {
    /**
     * The updated Student object.
     */
    private Student student;

    /**
     * The list of IDs for subjects not found during the update process.
     */
    private List<Long> notFoundSubjectIds;

    /**
     * The list of IDs for exams not found during the update process.
     */
    private List<Long> notFoundExamIds;

    /**
     * An optional message providing additional information about the update.
     */
    private String message;
}
