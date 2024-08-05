package com.crio.learningnavigator.repository;

import com.crio.learningnavigator.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The SubjectRepository interface provides methods to interact with the Subject entity in the database.
 *
 * <p>It extends JpaRepository to inherit basic CRUD operations for Subject entities.</p>
 *
 * <p><b>Usage:</b></p>
 * <p>This interface should be used to perform database operations related to subjects, including basic CRUD operations.</p>
 *
 * <pre>{@code
 * // Example usage:
 * Subject subject = subjectRepository.findById(subjectId).orElse(null);
 * }</pre>
 *
 * @see org.springframework.data.jpa.repository.JpaRepository
 */
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    // No additional methods are defined here because JpaRepository provides basic CRUD operations.
}
