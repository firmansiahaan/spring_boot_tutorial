package com.tutorialspoint.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tutorialspoint.spring.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
