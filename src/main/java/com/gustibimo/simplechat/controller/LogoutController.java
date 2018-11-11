package com.gustibimo.simplechat.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gustibimo.simplechat.constant.JwtConstant;
import com.gustibimo.simplechat.utils.Response;
import com.gustibimo.simplechat.security.CookieUtil;
import com.gustibimo.simplechat.security.TokenHelper;

@RestController
public class LogoutController {

	@RequestMapping("/logout")
    public Response logout(HttpServletRequest request, HttpServletResponse response) {
        TokenHelper.invalidateRelatedTokens(request);
        CookieUtil.clear(response, JwtConstant.TOKEN_COOKIE);
        return new Response(HttpStatus.OK.value(), "Successfully logout!");
    }
}
