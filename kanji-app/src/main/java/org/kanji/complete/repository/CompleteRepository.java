package org.kanji.complete.repository;

import java.util.List;
import java.util.Optional;

import org.kanji.complete.entity.Complete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CompleteRepository extends JpaRepository<Complete, Integer> {
	@Query(value = "select * from complete where member_id = :member_id",nativeQuery = true)
	Optional<List<Complete>> findComplete(@Param("member_id")String member_id);
}
