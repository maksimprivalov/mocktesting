package com.example.demo.service;

import com.example.demo.exception.ExamNotFoundException;
import com.example.demo.exception.NonExistingExamApplicationException;
import com.example.demo.exception.StudentNotFoundException;
import com.example.demo.model.Exam;
import com.example.demo.model.Student;
import com.example.demo.repository.ExamRepository;
import com.example.demo.repository.StudentRepository;
import java.util.Date;

public class ExamService {

	private ExamRepository examRepository;

	private StudentRepository studentRepository;

	/**
	 * A GRUPA
	 * 
	 * Metoda vrsi prijavu ispita od strane studenta, pri cemu se prosledjuje index
	 * studenta, kao i id ispita na koji se prijavljuje
	 * 
	 * Ukoliko bilo koji od entiteta ne postoji, metoda baca odgovarajuce izuzetke
	 * 
	 * Metoda vraca false u slucaju kada je datum odrzavanja ispita pre danasnjeg
	 * ili ukoliko je student vec polozio ispit
	 * 
	 * @param identificationNumber
	 * @param examId
	 * @return
	 * @throws Exception
	 */
	public boolean examApplication(String identificationNumber, long examId) throws Exception {

		Student student = studentRepository.findByIdentificationNumber(identificationNumber)
				.orElseThrow(() -> new StudentNotFoundException("Student with provided ID does not exist"));

		Exam exam = examRepository.findById(examId)
				.orElseThrow(() -> new ExamNotFoundException("Exam with provided ID does not exist"));

		if (exam.getExamDate().before(new Date()) || exam.isPassed())
			return false;

		exam.setStudent(student);
		exam.setApplicationDate(new Date());
		examRepository.save(exam);

		return true;
	}

	/***
	 * B GRUPA
	 * 
	 * Metoda vrsi prosledjivanje ocene za odredjeni ispit
	 * 
	 * Ukoliko ispit sa prosledjenim ID ne postoji, metoda baca izuzetak
	 * 
	 * Metoda baca izuzetak ukoliko ne postoji vezana instanca studenta za ispit,
	 * cime predpostavljamo da ispit nije predhodno prijavljen
	 * 
	 * False se vraca ukoliko prosledjena ocena nije u intervalu izmedju 5 i 10
	 * 
	 * @param examId
	 * @param grade
	 * @return
	 * @throws Exception
	 */
	public boolean gradeExam(long examId, int grade) throws Exception {

		Exam exam = examRepository.findById(examId)
				.orElseThrow(() -> new ExamNotFoundException("Exam with provided ID does not exist"));

		if (exam.getStudent() == null)
			throw new NonExistingExamApplicationException();

		if (grade < 5 || grade > 10)
			return false;

		boolean passed = grade == 5 ? false : true;

		exam.setGrade(grade);
		exam.setPassed(passed);
		examRepository.save(exam);

		return true;
	}
}
