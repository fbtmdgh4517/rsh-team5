package com.game.rshteam5.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ViewsController {
	@RequestMapping(value="/views/**", method=RequestMethod.GET)
	public void page() {
		
	}
	
	// void, string, modelandview
	// void는 url이 파일 경로?(views/???)랑 무조건 같아야되고
	// string, modelandview는 url은 알아서 하고 return 하는게 파일 경로?(views/???)랑 같아야됨 
	// url이 views/???이면 application.properties 에 의해 /WEB-INF/views/???.jsp로 바뀌어서 저 파일을 보여줌
}
