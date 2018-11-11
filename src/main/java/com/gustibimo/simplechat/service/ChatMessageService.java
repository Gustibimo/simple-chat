package com.gustibimo.simplechat.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gustibimo.simplechat.entity.ChatMessage;

public interface ChatMessageService {
	ChatMessage getById(Long id);

	void remove(Long id);

	ChatMessage save(ChatMessage chatMessage);

	List<ChatMessage> getAll();

	Page<ChatMessage> findPaginated(Pageable pageable);	
	
	Page<ChatMessage> findSortedPaginatedByDate(int page, int size);
}
