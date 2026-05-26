package com.tutorialspoint.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tutorialspoint.spring.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
