package com.ll.site.domain.post.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PostDto {
	private long id;
	private String title;
	private String content;
}