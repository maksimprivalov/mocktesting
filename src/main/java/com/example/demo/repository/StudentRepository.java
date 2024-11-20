package com.example.demo.repository;
import com.example.demo.model.Student;

import java.util.Optional;

public interface StudentRepository {

	Optional<Student> findByIdentificationNumber(String identificationNumber);
	
}
