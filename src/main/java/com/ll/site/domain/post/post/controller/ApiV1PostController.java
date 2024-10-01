package com.ll.site.domain.post.post.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ll.site.domain.post.post.dto.PostDto;

@RestController
@RequestMapping ("/api/v1/posts")
public class ApiV1PostController {
	@GetMapping
	public List<PostDto> getItems() {
		return List.of(
			new PostDto(3, "title3", "content3"),
			new PostDto(2, "title2", "content2"),
			new PostDto(1, "title1", "content1")
		);
	}
}
