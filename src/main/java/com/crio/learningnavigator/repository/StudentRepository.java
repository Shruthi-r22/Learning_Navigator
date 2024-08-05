package com.crio.learningnavigator.repository;

import com.crio.learningnavigator.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The StudentRepository interface provides methods to interact with the Student entity in the database.
 *
 * <p>It extends JpaRepository to inherit basic CRUD operations for Student entities.</p>
 *
 * <p><b>Usage:</b></p>
 * <p>This interface should be used to perform database operations related to students, including basic CRUD operations.</p>
 *
 * <pre>{@code
 * // Example usage:
 * Student student = studentRepository.findById(studentId).orElse(null);
 * }</pre>
 *
 * @see org.springframework.data.jpa.repository.JpaRepository
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // No additional methods are defined here because JpaRepository provides basic CRUD operations.
}
