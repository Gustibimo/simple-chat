package com.orainteractive.simplechat.web;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.orainteractive.simplechat.constant.SimpleChatConstant;
import com.orainteractive.simplechat.domain.ChatMessage;
import com.orainteractive.simplechat.exception.BaseException;
import com.orainteractive.simplechat.exception.ChatException;
import com.orainteractive.simplechat.helper.ResponseHelper;
import com.orainteractive.simplechat.po.CustomResponse;
import com.orainteractive.simplechat.service.ChatMessageService;

@RestController
public class ChatMessageController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(ChatMessageController.class);

	@Autowired
	private ChatMessageService chatMessageService;

	@RequestMapping(value = "/message", method = RequestMethod.GET)
	public ResponseEntity<CustomResponse<ChatMessage>> getMessages() {
		logger.info("ChatMessageController.clazz getMessages()");

		// Page request number start at 0 not 1
		Pageable pageable = new PageRequest(0, SimpleChatConstant.PER_PAGE);
		Page<ChatMessage> list = chatMessageService.findPaginated(pageable);
		CustomResponse<ChatMessage> result = ResponseHelper.convertFromPage(list, pageable.getPageNumber(),
				pageable.getPageSize());

		System.out.println("Result " + list.getContent().size());
		return new ResponseEntity<CustomResponse<ChatMessage>>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/messages/{page}", method = RequestMethod.GET)
	public @ResponseBody CustomResponse<ChatMessage> getPageChats(@PathVariable("page") int page) {
		logger.info("ChatMessageController.clazz getPageChats()");

		Page<ChatMessage> list = chatMessageService.findSortedPaginatedByDate(page, SimpleChatConstant.PER_PAGE);
		CustomResponse<ChatMessage> result = ResponseHelper.convertFromPage(list, page, SimpleChatConstant.PER_PAGE);
		System.out.println("Result ss " + list.getContent().size());
		return result;
	}

	@RequestMapping(value = "/message/{id}", method = RequestMethod.GET)
	public ResponseEntity<ChatMessage> getMessage(@PathVariable("id") long id) throws ChatException {
		logger.info("ChatMessageController.clazz getMessage() id " + id);

		if (id <= 0 || chatMessageService.getById(id) == null) {
			logger.info("ChatMessageController.clazz getMessage() can not be found with id::" + id);
			throw new ChatException("Chat id can not be found!");
		}
		return new ResponseEntity<ChatMessage>(chatMessageService.getById(id), HttpStatus.OK);
	}

	@RequestMapping(value = "/message", method = RequestMethod.POST)
	public ResponseEntity<ChatMessage> save(@Valid @RequestBody ChatMessage message) throws BaseException {
		logger.info("ChatMessageController.clazz save() chat" + message);
		return new ResponseEntity<ChatMessage>(chatMessageService.save(message), HttpStatus.OK);
	}

	@RequestMapping(value = "/message", method = RequestMethod.PATCH)
	public ResponseEntity<ChatMessage> update(@Valid @RequestBody ChatMessage message) throws BaseException {
		logger.info("UserController.clazz updateUser() User " + message);

		if (message.getId() <= 0 || chatMessageService.getById(message.getId()) == null) {
			logger.info(
					"ChatMessageController.clazz updateUser() can't update chat with message " + message.getMessage());
			throw new ChatException("Failed, chat doesn't exist");
		}
		return new ResponseEntity<ChatMessage>(chatMessageService.save(message), HttpStatus.OK);
	}
}