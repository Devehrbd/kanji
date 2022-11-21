package org.kanji.complete.service;

import java.util.List;
import java.util.Optional;

import org.kanji.complete.entity.Complete;
import org.kanji.complete.repository.CompleteRepository;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CompleteServiceImpl implements CompleteService {
	
	private CompleteRepository cpRepo;
	
	
	@Override
	public Optional<List<Complete>> selectCompleteAll(String member_id) {
		
		return cpRepo.findComplete(member_id);
		
	}
}
