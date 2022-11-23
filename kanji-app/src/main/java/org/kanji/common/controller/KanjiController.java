package org.kanji.common.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.kanji.complete.entity.Complete;
import org.kanji.complete.service.CompleteServiceImpl;
import org.kanji.course.entity.Course;
import org.kanji.course.service.CourseServiceImpl;
import org.kanji.kanji.entity.Kanji;
import org.kanji.kanji.service.KanjiServiceImpl;
import org.springframework.data.repository.query.Param;
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
	private KanjiServiceImpl kService;
	
	@GetMapping("listSelect")
	public void listSelect() {
	
	}

	@GetMapping("/listSelect2")
	public void listSelect2(HttpServletRequest request, Model model) {
		
		HttpSession session = request.getSession();
		
		session.getAttribute("login_member");
				
		String login_member_id = (String)session.getAttribute("login_member_id");
					
		Optional<Course> current_course = cService.readCourse(login_member_id);
		List<Integer> course_period = new ArrayList<>();
		List<String> course_message = new ArrayList<>();
		
		
		if(cpService.selectCompleteAll(login_member_id).isPresent()) {
			
			Optional<List<Complete>> complete_list = cpService.selectCompleteAll(login_member_id);
			// 2가지 나눠서 보내야해 , 위에는 완료표시 미완료표시를 나누는 기준이 됡고 아래는 단순한 list 숫자
			//complete list에는 날짜 + passed 몇번째 + 복습 주기가 있어서 활용해야함
			
			//getpassed랑 저기 html숫자랑 같다면 ~ -> 표시 하는 div생성
			
			for (int i = 1; i<= current_course.get().getCoursePeriod(); i++) {
				
				
				for (int j = 0; j < complete_list.get().size(); j++) {
					
					if(complete_list.get().get(j).getCompletePassed() == i) {
		
						
						//현재날짜와 완료날짜 + 복습주기 비교해서 현재날짜보다 크다면 복습주기 도래 아니면 X 이런식으로
						course_message.add("완료");
						
					}else {
						
						course_message.add("미완료");
						
					}
					
				} 
				
				course_period.add(i);	
			}
				
		}else {
			
			for (int i = 1; i<= current_course.get().getCoursePeriod(); i++) {
				
				course_period.add(i);
				course_message.add("미완료");
				
			}
			
		}
		
		model.addAttribute("course_period", course_period);
		model.addAttribute("course_message", course_message);
	}
	
	@GetMapping("/list")
	public void list(@Param("course_index")int course_index,HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		session.getAttribute("login_member");
		String login_member_id = (String)session.getAttribute("login_member_id");
		int current_course = cService.readCourse(login_member_id).get().getCoursePeriod();
		
		List<Kanji> kanji_list = new ArrayList<>();
	
		int unit_kanji = 2136 / current_course;
		int lack_kanji = 2136%(unit_kanji*current_course);
		int remain_kanji = (lack_kanji - (course_index-1)) % (unit_kanji * current_course);
		int kanji_index;
		
		
		if (remain_kanji > 0) {
			kanji_index = unit_kanji * (course_index-1) + lack_kanji - remain_kanji;
		}else {
			kanji_index = unit_kanji * (course_index-1) + lack_kanji;
		}
		int sep_kanji;
		
		if(remain_kanji > 0 ) {
			sep_kanji = unit_kanji +1;
		}else {
			sep_kanji = unit_kanji;
		}

		kanji_list = kService.readListKanji(kanji_index, sep_kanji);
		
		model.addAttribute("kanji_list",kanji_list);
		model.addAttribute("course_index",course_index);
	}
	
	@GetMapping("/testSelect")
	public void testSelect(@Param("course_index")int course_index,Model model) {
	
		model.addAttribute("course_index",course_index);
		
	}
	
	@GetMapping("/test")
	public void test(@Param("type")String type,@Param("course_index")int course_index,HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		session.getAttribute("login_member");
		String login_member_id = (String)session.getAttribute("login_member_id");
		int current_course = cService.readCourse(login_member_id).get().getCoursePeriod();
		
		List<Kanji> kanji_list = new ArrayList<>();
	
		int unit_kanji = 2136 / current_course;
		int lack_kanji = 2136%(unit_kanji*current_course);
		int remain_kanji = (lack_kanji - (course_index-1)) % (unit_kanji * current_course);
		int kanji_index;
		
		
		if (remain_kanji > 0) {
			kanji_index = unit_kanji * (course_index-1) + lack_kanji - remain_kanji;
		}else {
			kanji_index = unit_kanji * (course_index-1) + lack_kanji;
		}
		int sep_kanji;
		
		if(remain_kanji > 0 ) {
			sep_kanji = unit_kanji +1;
		}else {
			sep_kanji = unit_kanji;
		}
		
		List<String> kokai1 = new ArrayList<>();
		List<String> kokai2 = new ArrayList<>();
		List<String> kokai3 = new ArrayList<>();
		
		
		if(type.equals("character")) {
			
			kokai1 = kService.readListCharacter(sep_kanji);
			kokai2 = kService.readListCharacter(sep_kanji);
			kokai3 = kService.readListCharacter(sep_kanji);
			
		}else{
			
			kokai1 = kService.readListSoundMean(sep_kanji);
			kokai2 = kService.readListSoundMean(sep_kanji);
			kokai3 = kService.readListSoundMean(sep_kanji);
		}
		
		
		
		kanji_list = kService.readListKanji(kanji_index, sep_kanji);
		Collections.shuffle(kanji_list);
	
		model.addAttribute("type",type);
		model.addAttribute("kanji_list",kanji_list);
		model.addAttribute("course_index",course_index);
		model.addAttribute("kokai1",kokai1);
		model.addAttribute("kokai2",kokai2);
		model.addAttribute("kokai3",kokai3);
	}
}
