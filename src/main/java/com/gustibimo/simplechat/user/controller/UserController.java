package com.gustibimo.simplechat.user.controller;

import com.gustibimo.simplechat.constant.SimpleChatConstant;
import com.gustibimo.simplechat.controller.BaseController;
import com.gustibimo.simplechat.entity.ChatMessage;
import com.gustibimo.simplechat.entity.User;
import com.gustibimo.simplechat.exception.BaseException;
import com.gustibimo.simplechat.exception.ChatMessageException;
import com.gustibimo.simplechat.exception.UserException;
import com.gustibimo.simplechat.helper.ResponseHelper;
import com.gustibimo.simplechat.utils.CustomResponse;
import com.gustibimo.simplechat.utils.Response;
import com.gustibimo.simplechat.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(com.gustibimo.simplechat.user.controller.UserController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ResponseEntity<List<User>> getUsers() {
		logger.debug("UserController.clazz getUsers()");
		return new ResponseEntity<List<User>>(userService.getAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/show", params = {"page"}, method = RequestMethod.GET)
	public @ResponseBody CustomResponse<User> getPageUsers(@RequestParam("page") int page) {
		logger.debug("UserController.clazz getUsers()");

		Pageable pageable = new PageRequest(page, SimpleChatConstant.PER_PAGE);
		Page<User> list = userService.listAllByPage(pageable);
		CustomResponse<User> result = ResponseHelper.convertFromPage(list, pageable.getPageNumber(),
				pageable.getPageSize());
		return result;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> getUser(@PathVariable("id") long id) throws UserException {
		logger.debug("UserController.clazz getUser() id " + id);

		if (invalidUser(id)) {
			logger.info("UserController.clazz getUser() can not be found with id::" + id);
			throw new UserException("User can not be found!");
		}
		return new ResponseEntity<User>(userService.getById(id), HttpStatus.OK);
	}

//	@RequestMapping(value = "/{id}/messages", method = RequestMethod.GET)
//	@ResponseWrapper(rootName = "user")
//	public ResponseEntity<ChatMessage> getMessage(@PathVariable("id") long id) throws ChatMessageException {
//
//	    return new ResponseEntity<ChatMessage>(message.getMessage(id), HttpStatus.OK);
//
//    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Response> delete(@PathVariable("id") long id) throws UserException {
		logger.debug("UserController.clazz delete() id" + id);

		if (invalidUser(id)) {
			logger.info("User to delete can not be found::id::" + id);
			throw new UserException("User to delete can not be found");
		}
		userService.remove(id);
		return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(), "User has been deleted successfully"),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/show", method = RequestMethod.POST)
	public ResponseEntity<User> saveUser(@Valid @RequestBody User user) throws BaseException {
		logger.debug("UserController.clazz saveUser() user" + user);
		return new ResponseEntity<User>(userService.save(user), HttpStatus.OK);
	}

	@RequestMapping(value = "/show", method = RequestMethod.PATCH)
	public ResponseEntity<User> updateUser(@Valid @RequestBody User user, HttpServletRequest req) throws BaseException {
		logger.info("UserController.clazz updateUser() User " + user);

		verifyPermission(req, user.getId().toString());

		if (invalidUser(user.getId())) {
			logger.debug("UserController.clazz updateUser() can't update User " + user.getUsername());
			throw new UserException("Failed, user doesn't exist");
		}
		return new ResponseEntity<User>(userService.save(user), HttpStatus.OK);
	}

	@RequestMapping(value = "/show", method = RequestMethod.PUT)
	public ResponseEntity<User> updateEntity(@Valid @RequestBody User user, HttpServletRequest req) throws BaseException {
		logger.info("UserController.clazz updateUser() User " + user);

		verifyPermission(req, user.getId().toString());

		if (invalidUser(user.getId())) {
			logger.debug("UserController.clazz updateUser() can't update User " + user.getUsername());
			throw new UserException("Failed, user doesn't exist");
		}
		return new ResponseEntity<User>(userService.save(user), HttpStatus.OK);
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<User> regUser(@Valid @RequestBody User user) throws BaseException {
		return new ResponseEntity<User>(userService.save(user), HttpStatus.OK);
	}

	private boolean invalidUser(Long id) {
		return id > 0 && userService.getById(id) == null;
	}
}
