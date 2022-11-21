package org.kanji.kanji.repository;

import org.kanji.kanji.entity.Kanji;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KanjiRepository extends JpaRepository<Kanji, Integer> {

}
