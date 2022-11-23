package com.kanji.kanji.repository;


import org.junit.jupiter.api.Test;
import org.kanji.kanji.repository.KanjiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class KanjiRepositoryTests {
	@Autowired
	private KanjiRepository kRepo;
	
	@Test
	public void insert() {
		
		
		
	}
	
}
