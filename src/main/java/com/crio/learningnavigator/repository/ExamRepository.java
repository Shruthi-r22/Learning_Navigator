package com.crio.learningnavigator.repository;

import com.crio.learningnavigator.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The ExamRepository interface provides methods to interact with the Exam entity in the database.
 *
 * <p>It extends JpaRepository to inherit basic CRUD operations for Exam entities and adds a custom method to find an exam by subject ID.</p>
 *
 * <p><b>Methods:</b></p>
 * <ul>
 *   <li>{@code Exam findBySubject_Id(Long subject_id)} - Retrieves an exam by the ID of its associated subject.</li>
 * </ul>
 *
 * <p><b>Usage:</b></p>
 * <p>This interface should be used to perform database operations related to exams, including basic CRUD operations and custom queries.</p>
 *
 * <pre>{@code
 * // Example usage:
 * Exam exam = examRepository.findBySubject_Id(subjectId);
 * }</pre>
 *
 * @see org.springframework.data.jpa.repository.JpaRepository
 */
public interface ExamRepository extends JpaRepository<Exam, Long> {
    /**
     * Retrieves an exam by the ID of its associated subject.
     *
     * @param subject_id the ID of the subject
     * @return the exam associated with the given subject ID, or null if not found
     */
    Exam findBySubject_Id(Long subject_id);
}
