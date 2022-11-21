package org.kanji.common.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.kanji.complete.entity.Complete;
import org.kanji.complete.service.CompleteServiceImpl;
import org.kanji.course.service.CourseServiceImpl;
import org.kanji.kanji.entity.Kanji;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/kanji")
@AllArgsConstructor
public class KanjiController {
	
	private CourseServiceImpl cService;
	private CompleteServiceImpl cpService;
	
	@GetMapping("listSelect")
	public void listSelect() {
	
	}

	@GetMapping("/listSelect2")
	public void list(HttpServletRequest request, Model model) {
		
		HttpSession session = request.getSession();
		
		session.getAttribute("login_member");
				
		String login_member_id = (String)session.getAttribute("login_member_id");
					
		int current_course = cService.readCourse(login_member_id);
		List<Integer> course_period = new ArrayList<>();
		List<String>course_message = new ArrayList<>();
		
		for(int i = 1; i <= current_course; i++) {
			course_period.add(i);
		}
		
		
		if(cpService.selectCompleteAll(login_member_id).isPresent()) {
			
			Optional<List<Complete>> complete_list = cpService.selectCompleteAll(login_member_id);
			// 2가지 나눠서 보내야해 , 위에는 완료표시 미완료표시를 나누는 기준이 됡고 아래는 단순한 list 숫자
			//complete list에는 날짜 + passed 몇번째 + 복습 주기가 있어서 활용해야함
			
			
		}
		
		
		model.addAttribute("course_period", course_period);

	}
	
}
