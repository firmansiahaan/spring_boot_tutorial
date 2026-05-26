package com.tutorialspoint.spring;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.tutorialspoint.spring.entity.Book;
import com.tutorialspoint.spring.entity.BookCategory;
import com.tutorialspoint.spring.entity.Course;
import com.tutorialspoint.spring.entity.Student;
import com.tutorialspoint.spring.repository.BookCategoryRepository;
import com.tutorialspoint.spring.repository.BookRepository;
import com.tutorialspoint.spring.repository.CourseRepository;
import com.tutorialspoint.spring.repository.StudentRepository;

@SpringBootApplication
public class SpringDataJpaApplication implements CommandLineRunner {
	
	private  final Logger LOG =
			LoggerFactory.getLogger(SpringDataJpaApplication.class);
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private BookCategoryRepository bookCategoryRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		Book book1 = new Book();
		book1.setTitle("Belajar Spring Boot");
		book1.setWriter("Teten Nugraha");
		book1.setIsbn("IS-90908");
		
		Book book2 = new Book();
		book2.setTitle("Belajar Spring Boot 2");
		book2.setWriter("Teten Nugraha");
		book2.setIsbn("IS-9090890");
		
		bookRepository.save(book1);
		bookRepository.save(book2);
		
		LOG.info("Berhasil menyimpan "+book1);
		LOG.info("Berhasil menyimpan "+book2);
		
		List<Book> books = bookRepository.findAll();
		LOG.info("Books : "+ books);
		
		final String writer = "Teten Nugraha";
		books = bookRepository.findAllByWriter(writer);
		LOG.info("Books : "+ books);
		
		final String isbn = "IS-90908";
		Book book = bookRepository.findByIsbn(isbn);
		LOG.info("Book : "+ book);
		
		books = bookRepository.findAllQueryNative();
		LOG.info("Book : "+books);
		
		// create Book Category
		BookCategory bookCategory = bookCategoryRepository.save(new
		BookCategory("Programming", 
			new Book("Java 1","Teten N.","SEI92002"), 
			new Book("Java 2","Teten N.","UEOEI829")));
		
		LOG.info("BookCategory : "+ bookCategory);
		
		// create a student
		Student student = new Student("Bagoes Okta", 15);
		
		// save the student
		studentRepository.save(student);
		
		// create three courses
		Course course1 = new Course("Beginning Spring Boot", 12, 1500);
		Course course2 = new Course("Spring Reactive", 8, 800);
		Course course3 = new Course("Basic Microservices",9, 100);
		
		// save courses
		courseRepository.saveAll(Arrays.asList(course1, course2, course3));
		
		// add courses to the student
		student.getCourses().addAll(Arrays.asList(course1, course2, course3));
		
		// update the student
		studentRepository.save(student);
		
	}

}
