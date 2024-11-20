package com.example.demo.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


public class Student implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String firstName;

	private String lastName;

	private String identificationNumber;

	private Set<Exam> exams = new HashSet<Exam>();

	public Student() {
		super();
	}

	public Student(Long id, String firstName, String lastName, String identificationNumber) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.identificationNumber = identificationNumber;
	}

	public Student(String firstName, String lastName, Set<Exam> exams) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.exams = exams;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getIdentificationNumber() {
		return identificationNumber;
	}

	public void setIdentificationNumber(String identificationNumber) {
		this.identificationNumber = identificationNumber;
	}

	public Set<Exam> getExams() {
		return exams;
	}

	public void setExams(Set<Exam> exams) {
		this.exams = exams;
	}

}
