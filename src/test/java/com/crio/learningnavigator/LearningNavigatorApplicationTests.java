package com.crio.learningnavigator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import com.crio.learningnavigator.entity.Exam;
import com.crio.learningnavigator.entity.Student;
import com.crio.learningnavigator.entity.Subject;
import com.crio.learningnavigator.exchange.request.StudentRequest;
import com.crio.learningnavigator.exchange.request.SubjectRequest;
import com.crio.learningnavigator.repository.ExamRepository;
import com.crio.learningnavigator.repository.StudentRepository;
import com.crio.learningnavigator.repository.SubjectRepository;
import com.crio.learningnavigator.service.implementation.StudentServiceImpl;
import com.crio.learningnavigator.service.implementation.SubjectServiceImpl;


@SpringBootTest
class LearningNavigatorApplicationTests {

    // Creating a subject with valid data should save and return the subject
    @Test
    public void test_create_subject_with_valid_data() {
        SubjectRepository subjectRepository = Mockito.mock(SubjectRepository.class);
        SubjectServiceImpl subjectService = new SubjectServiceImpl(subjectRepository);
        SubjectRequest request = new SubjectRequest("Mathematics");
        Subject subject = new Subject();
        subject.setName("Mathematics");
    
        Mockito.when(subjectRepository.save(Mockito.any(Subject.class))).thenReturn(subject);
    
        Subject result = subjectService.create(request);
    
        Assertions.assertNotNull(result);
        Assertions.assertEquals("Mathematics", result.getName());
        Mockito.verify(subjectRepository, Mockito.times(1)).save(Mockito.any(Subject.class));
    }

    // Creating a student with valid data should save the student in the repository
    @Test
    public void test_create_student_with_valid_data() {
        StudentRepository studentRepository = mock(StudentRepository.class);
        SubjectRepository subjectRepository = mock(SubjectRepository.class);
        ExamRepository examRepository = mock(ExamRepository.class);
        StudentServiceImpl studentService = new StudentServiceImpl(studentRepository, subjectRepository, examRepository);

        StudentRequest studentRequest = new StudentRequest();
        studentRequest.setName("John Doe");
        Set<Subject> subjects = new HashSet<>();
        subjects.add(new Subject(1L, "Math"));
        studentRequest.setEnrolledSubjects(subjects);
        Set<Exam> exams = new HashSet<>();
        exams.add(new Exam(1L, "Math Exam", new Subject(1L, "Math"), new HashSet<>()));
        studentRequest.setEnrolledExams(exams);

        Student student = new Student();
        student.setId(1L);
        student.setName("John Doe");
        student.setEnrolledSubjects(subjects);
        student.setEnrolledExams(exams);

        when(studentRepository.save(any(Student.class))).thenReturn(student);

        Student createdStudent = studentService.create(studentRequest);

        assertNotNull(createdStudent);
        assertEquals("John Doe", createdStudent.getName());
        assertEquals(1, createdStudent.getEnrolledSubjects().size());
        assertEquals(1, createdStudent.getEnrolledExams().size());
    }

    // Creating a student with null name should still save the student without a name
    @Test
    public void test_create_student_with_null_name() {
        StudentRepository studentRepository = mock(StudentRepository.class);
        SubjectRepository subjectRepository = mock(SubjectRepository.class);
        ExamRepository examRepository = mock(ExamRepository.class);
        StudentServiceImpl studentService = new StudentServiceImpl(studentRepository, subjectRepository, examRepository);

        StudentRequest studentRequest = new StudentRequest();
        studentRequest.setName(null);
        Set<Subject> subjects = new HashSet<>();
        subjects.add(new Subject(1L, "Math"));
        studentRequest.setEnrolledSubjects(subjects);
        Set<Exam> exams = new HashSet<>();
        exams.add(new Exam(1L, "Math Exam", new Subject(1L, "Math"), new HashSet<>()));
        studentRequest.setEnrolledExams(exams);

        Student student = new Student();
        student.setId(1L);
        student.setName(null);
        student.setEnrolledSubjects(subjects);
        student.setEnrolledExams(exams);

        when(studentRepository.save(any(Student.class))).thenReturn(student);

        Student createdStudent = studentService.create(studentRequest);

        assertNotNull(createdStudent);
        assertNull(createdStudent.getName());
        assertEquals(1, createdStudent.getEnrolledSubjects().size());
        assertEquals(1, createdStudent.getEnrolledExams().size());
    }
}
