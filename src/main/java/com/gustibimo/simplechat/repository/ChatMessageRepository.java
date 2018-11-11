package com.gustibimo.simplechat.repository;

import com.gustibimo.simplechat.entity.ChatMessage;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ChatMessageRepository extends PagingAndSortingRepository<ChatMessage, Long> {

}
