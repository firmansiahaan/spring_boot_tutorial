package com.tutorialspoint.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tutorialspoint.spring.entity.BookCategory;

public interface BookCategoryRepository extends JpaRepository<BookCategory, Long> {

}
