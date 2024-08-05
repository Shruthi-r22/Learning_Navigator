package com.crio.learningnavigator.exchange.response;

import com.crio.learningnavigator.entity.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * The UpdateSubjectResponse class represents a response for updating a subject.
 *
 * <p>This class encapsulates the updated subject object and a list of IDs for students not found during the update process.</p>
 *
 * <p><b>Fields:</b></p>
 * <ul>
 *   <li>{@code Subject subject} - The updated Subject object.</li>
 *   <li>{@code List<Long> notFoundStudentIds} - The list of IDs for students not found during the update process.</li>
 * </ul>
 *
 * <p><b>Annotations:</b></p>
 * <ul>
 *   <li>{@code @Data} - Generates getters, setters, toString, equals, and hashCode methods.</li>
 *   <li>{@code @AllArgsConstructor} - Generates a constructor with arguments for all fields.</li>
 * </ul>
 *
 * <p><b>Usage:</b></p>
 * <p>This class should be used to provide a detailed response after updating a subject, including the updated subject object and information about students not found.</p>
 *
 * <pre>{@code
 * // Example usage:
 * UpdateSubjectResponse response = new UpdateSubjectResponse(updatedSubject, notFoundStudentIds);
 * }</pre>
 */
@Data
@AllArgsConstructor
public class UpdateSubjectResponse {
    /**
     * The updated Subject object.
     */
    private Subject subject;

    /**
     * The list of IDs for students not found during the update process.
     */
    private List<Long> notFoundStudentIds;
}
