package org.kanji.complete.service;

import java.util.List;
import java.util.Optional;

import org.kanji.complete.entity.Complete;

public interface CompleteService {
	public Optional<List<Complete>> selectCompleteAll(String member_id); 
}
