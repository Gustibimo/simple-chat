package com.gustibimo.simplechat.repository;

import com.gustibimo.simplechat.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
	User findFirstByUsername(String username);
}
