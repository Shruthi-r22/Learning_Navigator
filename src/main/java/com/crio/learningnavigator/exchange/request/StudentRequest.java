package com.crio.learningnavigator.exchange.request;

import java.util.Set;

import com.crio.learningnavigator.entity.Exam;
import com.crio.learningnavigator.entity.Subject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequest {
    /**
     * The name of the student.
     */
    private String name;

    /**
     * The set of subjects in which the student is enrolled.
     */
    private Set<Subject> enrolledSubjects;

    /**
     * The set of exams in which the student is enrolled.
     */
    private Set<Exam> enrolledExams;
}
