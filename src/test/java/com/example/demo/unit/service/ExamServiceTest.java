package com.example.demo.unit.service;

import com.example.demo.repository.ExamRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.ExamService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ExamServiceTest {
    @Mock
    private ExamRepository examRepository;
    @Mock
    private StudentRepository studentRepository;
    @InjectMocks
    private ExamService examService;

    @BeforeMethod
    public void init() throws Exception{
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void studentNotFoundExceptionTest(){

    }
}
