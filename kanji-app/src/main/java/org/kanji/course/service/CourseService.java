package org.kanji.course.service;

import java.util.Optional;

import org.kanji.course.entity.Course;
import org.kanji.member.entity.Member;

public interface CourseService {
	
	public void registCourse(Course course);
	public int readCourse(String member_id);
}
