package com.example.demo.unit.service;

import com.example.demo.exception.StudentNotFoundException;
import com.example.demo.model.Exam;
import com.example.demo.model.Student;
import com.example.demo.repository.ExamRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.ExamService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.*;
import static org.testng.Assert.*;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.when;

public class ExamServiceTest {
    @Mock
    private ExamRepository examRepository;
    @Mock
    private StudentRepository studentRepository;
    @InjectMocks
    private ExamService examService;
    String nonExistentIdentificationNumber = "INVALID_ID";
    private Student student = new Student();
    private Exam exam = new Exam();

    {
        student.setId(11L);
        student.setLastName("Jovanovic");
        student.setFirstName("Jovan");
        student.setIdentificationNumber("SV18/2025");
        exam.setExamDate(new Date());
        exam.setGrade(9);
        exam.setPassed(false);
        exam.setStudent(student);
        exam.setId(90L);
        student.setExams(Set.of(exam));
    }
    @BeforeMethod
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void studentNotFoundExceptionTest() {
        when(studentRepository.findByIdentificationNumber(nonExistentIdentificationNumber)).thenReturn(Optional.empty());
        assertThrows(StudentNotFoundException.class, () -> {
            examService.examApplication(nonExistentIdentificationNumber, exam.getId());
        });
    }
}
