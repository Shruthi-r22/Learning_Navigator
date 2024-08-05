package com.crio.learningnavigator.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Subject {

    /**
     * The unique identifier of the subject.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * The name of the subject.
     */
    private String name;

    /**
     * The set of students enrolled in the subject.
     * Relationship:
     * - Many subjects can be enrolled by many students.
     * - Prevents Jackson from infinitely serializing students.
     */
    @ManyToMany(mappedBy = "enrolledSubjects")
    @JsonBackReference
    private Set<Student> enrolledStudents = new HashSet<>();

    /**
     * Constructs a Subject with the given name.
     *
     * @param name the name of the subject
     */
    public Subject(String name) {
        this.name = name;
    }

    /**
     * Constructs a Subject with the given identifier.
     *
     * @param id the unique identifier of the subject
     */
    public Subject(Long id) {
        this.id = id;
    }

    /**
     * Constructs a Subject with the given identifier and name.
     *
     * @param id   the unique identifier of the subject
     * @param name the name of the subject
     */
    public Subject(long id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Returns a string representation of the Subject object.
     *
     * @return a string representation of the Subject object
     */
    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
