package com.crio.learningnavigator.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Student {

    /**
     * The unique identifier of the student.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * The name of the student.
     */
    private String name;

    /**
     * The set of subjects in which the student is enrolled.
     * Relationship:
     * - Many students can be enrolled in many subjects.
     * - Defines the join table name and columns for the enrolled subjects.
     */
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Enrolled_Subjects",
            joinColumns = {
                    @JoinColumn(name = "student_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "subject_id", referencedColumnName = "id")
            })
    private Set<Subject> enrolledSubjects = new HashSet<>();

    /**
     * The set of exams in which the student is enrolled.
     * Relationship:
     * - Many students can be enrolled in many exams.
     * - Defines the join table name and columns for the enrolled exams.
     */
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Enrolled_Exams",
            joinColumns = {
                    @JoinColumn(name = "student_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "exam_id", referencedColumnName = "id")
            })
    private Set<Exam> enrolledExams = new HashSet<>();
}
