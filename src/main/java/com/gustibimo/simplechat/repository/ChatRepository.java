package com.gustibimo.simplechat.repository;

import com.gustibimo.simplechat.entity.Chat;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ChatRepository extends PagingAndSortingRepository<Chat, Long> {
}
