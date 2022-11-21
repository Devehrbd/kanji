package com.kanji.course.repository;

import org.junit.jupiter.api.Test;
import org.kanji.course.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class CourseRepositoryTests {
	@Autowired
	private CourseRepository cRepo;
	
//	@Test
//	public void read() {
//		
//		log.info(cRepo.existCourse("kdhong"));
//		
//	}
	
}
