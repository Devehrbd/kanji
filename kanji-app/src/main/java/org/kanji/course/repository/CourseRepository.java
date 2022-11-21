package org.kanji.course.repository;

import java.util.Optional;

import org.kanji.course.entity.Course;
import org.kanji.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CourseRepository extends JpaRepository<Course, Integer>{
	
	@Query(value = "select course_period from course where member_id = :member_id",nativeQuery = true)
	Integer existCourse(@Param("member_id")String member_id);
}
