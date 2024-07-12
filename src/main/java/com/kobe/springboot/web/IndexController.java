package com.kobe.springboot.web;

import com.kobe.springboot.config.auth.dto.SessionUser;
import com.kobe.springboot.service.PostsService;
import com.kobe.springboot.web.dto.PostsResponseDto;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

	private final PostsService postsService;
	private final HttpSession httpSession;

	@GetMapping("/")
	// Model
		// 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장할 수 있습니다.
		// 여기서는 postsService.findAllDesc()로 가져온 결과를 posts로 index.mustache에 전달합니다.
	public String index(Model model) {
		model.addAttribute("posts", postsService.findAllDesc());
		SessionUser user = (SessionUser) httpSession.getAttribute("user");

		if (user != null) {
			model.addAttribute("userName", user.getName());
		}
		return "index";
	}

	@GetMapping("/posts/save")
	public String postsSave() {
		return "posts-save";
	}

	@GetMapping("/posts/update/{id}")
	public String postsUpdate(@PathVariable Long id, Model model) {
		PostsResponseDto dto = postsService.findById(id);
		model.addAttribute("post", dto);

		return "posts-update";
	}
}
