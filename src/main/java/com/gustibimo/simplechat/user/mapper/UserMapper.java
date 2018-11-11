package com.gustibimo.simplechat.user.mapper;

import com.gustibimo.simplechat.entity.User;
import com.gustibimo.simplechat.exception.DuplicateUsernameException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserMapper {
	User getById(Long id);

	User save(User user) throws DuplicateUsernameException;

	void remove(Long id);

	void remove(User user);

	User getByUsername(String username);

	List<User> getAll();

	Page<User> listAllByPage(Pageable pageable);
}
