package com.game.rshteam5.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin("*")	// CORS 오류(다른 도메인에서 요청했을 때 막아주는 것, 도메인은 'localhost:80'까지를 말함) 없애줌, 메소드마다 각각 쓰는 경우도 있음(이 경우는 해당 메소드만 crossorigin 적용됨), 백엔드에서 처리해줘야함
public class TestController {
	
	@RequestMapping(value="/test/**", method=RequestMethod.GET)
	public String test() {
		return "str";
	}
	
	@RequestMapping(value="/api/list", method=RequestMethod.GET)
	@ResponseBody	// 값 자체를 받아오려면 이걸 써야되고 페이지를 가져오려면 없어도됨, 한개의 컨트롤러에서 전체 메소드가 다 값 자체를 받으려면 이 어노테이션 없애고 11번줄 어노테이션을 @RestController로 바꾸면 됨
	public List<String> getList() {
		List<String> list = new ArrayList<>();
		list.add("abc1");
		list.add("abc2");
		list.add("abc3");
		return list;
	}
	
	@GetMapping("/api/users")	// @RequestMapping이랑 똑같은거임
	public @ResponseBody List<Map<String, String>> getUsers() {		// @ResponseBody는 메소드 위에 써도 되고 이렇게 써도 됨
		List<Map<String, String>> users = new ArrayList<>();
		for(int i=1; i<11; i++) {
			Map<String, String> user = new HashMap<>();
			user.put("name", "이름" + i);
			user.put("num", i+"");
			user.put("age", i+"");
			users.add(user);
		}
		return users;
	}
}
