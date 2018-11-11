package com.gustibimo.simplechat.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.gustibimo.simplechat.constant.SimpleChatConstant;
import com.gustibimo.simplechat.exception.PermissionDeniedException;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import io.jsonwebtoken.Claims;

@RequestMapping(value = "/${api.path}")
public class BaseController {

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(SimpleChatConstant.DATE_PATTERN);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
	protected boolean isOwner(Claims claims, String userid) {
		return claims.getId().equals(userid);
	}
	
	/** This method is to check whether the current login user has permission to perform request
	 * Specific request like update chat, profile only the owner can perform the request
	 * otherwise it will throw PermissionDeniedException
	 * 
	 * @param req HttpServletRequest
	 * @param userId
	 * @throws PermissionDeniedException
	 */
	protected void verifyPermission(HttpServletRequest req, String userId) throws PermissionDeniedException {
		Claims claims = (Claims) req.getAttribute("claims");
		if (!isOwner(claims, userId.toString())) {
			throw new PermissionDeniedException(SimpleChatConstant.PERMISSION_DENIED);
		}
	}
}
