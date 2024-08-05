package com.crio.learningnavigator.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Exam {

    /**
     * The unique identifier of the exam.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * The name of the exam.
     * Constraints:
     * - Must not be empty
     */
    @NotEmpty
    private String name;

    /**
     * The subject associated with the exam.
     * Relationship:
     * - Many exams can belong to one subject.
     */
    @ManyToOne
    private Subject subject;

    /**
     * The set of students enrolled in the exam.
     * Relationship:
     * - Many exams can be enrolled by many students.
     * - Prevents Jackson from infinitely serializing students.
     */
    @ManyToMany(mappedBy = "enrolledExams")
    @JsonBackReference
    private Set<Student> enrolledStudents = new HashSet<>();

    /**
     * Returns a string representation of the Exam object.
     *
     * @return a string representation of the Exam object.
     */
    @Override
    public String toString() {
        return "Exam{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
