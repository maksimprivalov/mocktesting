package com.example.demo.unit.service;

import com.example.demo.exception.ExamNotFoundException;
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
    Long nonExistentId = 1L;
    private Student student = new Student();
    private Exam exam = new Exam();
    private Exam examWithBadDate = new Exam(1000L, new Date(0, 1, 1), new Date(0, 1, 1), student);
    private Exam examWithGoodDateButPassedTrue = new Exam(100L, new Date(126, 1, 1), new Date(), student);

    {
        examWithGoodDateButPassedTrue.setPassed(true);
        student.setId(11L);
        student.setLastName("Jovanovic");
        student.setFirstName("Jovan");
        student.setIdentificationNumber("SV18/2025");
        exam.setExamDate(new Date(126, 1, 1));
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
    @Test
    public void studentIsFoundTest() {
        when(studentRepository.findByIdentificationNumber(student.getIdentificationNumber())).thenReturn(Optional.of(student));
        assertEquals(studentRepository.findByIdentificationNumber(student.getIdentificationNumber()), Optional.of(student));
    }

    @Test
    public void examNotFoundExceptionTest() {
        when(studentRepository.findByIdentificationNumber(student.getIdentificationNumber())).thenReturn(Optional.of(student));

        when(examRepository.findById(nonExistentId)).thenReturn(Optional.empty());
        assertThrows(ExamNotFoundException.class, () -> {
            examService.examApplication(student.getIdentificationNumber(), nonExistentId);
        });
    }

    @Test
    public void examFoundTest() throws Exception {
        //when(studentRepository.findByIdentificationNumber(student.getIdentificationNumber())).thenReturn(Optional.of(student));

        when(examRepository.findById(exam.getId())).thenReturn(Optional.of(exam));

        assertEquals(examRepository.findById(exam.getId()), Optional.of(exam));
    }

    @Test
    public void examWasNotYetTest() throws Exception {
        when(studentRepository.findByIdentificationNumber(student.getIdentificationNumber())).thenReturn(Optional.of(student));
        when(examRepository.findById(examWithBadDate.getId())).thenReturn(Optional.of(examWithBadDate));
        assertFalse(examService.examApplication(student.getIdentificationNumber(), examWithBadDate.getId()));
    }

    @Test
    public void examWasAlreadyPassed() throws Exception {
        when(studentRepository.findByIdentificationNumber(student.getIdentificationNumber())).thenReturn(Optional.of(student));
        when(examRepository.findById(examWithGoodDateButPassedTrue.getId())).thenReturn(Optional.of(examWithGoodDateButPassedTrue));
        assertFalse(examService.examApplication(student.getIdentificationNumber(), examWithGoodDateButPassedTrue.getId()));
    }
    @Test
    public void goodVariationTest() throws Exception {
        when(studentRepository.findByIdentificationNumber(student.getIdentificationNumber())).thenReturn(Optional.of(student));
        when(examRepository.findById(exam.getId())).thenReturn(Optional.of(exam));
        assertTrue(examService.examApplication(student.getIdentificationNumber(), exam.getId()));
    }
}
