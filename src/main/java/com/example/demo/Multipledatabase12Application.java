package com.example.demo;

//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.book.repository.BookRepopsitory;
import com.example.demo.model.book.Book;
import com.example.demo.model.user.User;
import com.example.demo.user.repository.UserRepository;

@RestController

@SpringBootApplication
@RequestMapping("/employee")
public class Multipledatabase12Application {
	
	
	@Autowired
	private BookRepopsitory bookRepository;

	@Autowired
	private UserRepository userRepository;
	
	
	@PostConstruct()
	public void addData2DB() {
		userRepository.saveAll(Stream.of(new User()).collect(Collectors.toList()));
		bookRepository.saveAll(Stream.of(new Book()).collect(Collectors.toList()));		
	}
	
	@PostMapping
	public User saveEmp(@RequestBody User employee) {
		return userRepository.save(employee);
		
	}



	@GetMapping("/getBooks")
	public List<Book> getBooks() {
		return bookRepository.findAll();
	}
	
	@GetMapping("/getUsers")
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	

	public static void main(String[] args) {
		SpringApplication.run(Multipledatabase12Application.class, args);
	}

}
