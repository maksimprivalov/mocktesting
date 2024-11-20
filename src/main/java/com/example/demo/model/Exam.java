package com.example.demo.model;

import java.io.Serializable;
import java.util.Date;

public class Exam implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private boolean passed;

	private Integer grade;

	private Date examDate;

	private Date applicationDate;

	private Student student;

	public Exam() {
		super();
	}

	public Exam(Long id, Date examDate, Date applicationDate, Student student) {
		super();
		this.id = id;
		this.examDate = examDate;
		this.applicationDate = applicationDate;
		this.student = student;
	}

	public Exam(Long id, boolean passed, Date examDate, Date applicationDate, Student student) {
		super();
		this.id = id;
		this.passed = passed;
		this.examDate = examDate;
		this.applicationDate = applicationDate;
		this.student = student;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isPassed() {
		return passed;
	}

	public void setPassed(boolean passed) {
		this.passed = passed;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public Date getExamDate() {
		return examDate;
	}

	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}

	public Date getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(Date applicationDate) {
		this.applicationDate = applicationDate;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

}
