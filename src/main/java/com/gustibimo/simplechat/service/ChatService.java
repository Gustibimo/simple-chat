package com.gustibimo.simplechat.service;

import java.util.List;

import com.gustibimo.simplechat.entity.Chat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ChatService {
	Chat getById(Long id);

	void remove(Long id);

	Chat save(Chat chat);

	List<Chat> getAll();

	Page<Chat> findPaginated(Pageable pageable);	
	
	Page<Chat> getPageSortByDate(int page, int size);
}
