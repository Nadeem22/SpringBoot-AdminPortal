package com.nadeem.bookstore.resource;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nadeem.bookstore.service.UserService;

@RestController
//@CrossOrigin(origins = {"http://localhost:4200", "http://anotherdomain:4300"})
public class LoginResource {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/token")
	public Map<String,String> token(HttpSession session, HttpServletRequest request){
		System.out.println("Remote Host================================== :" );
		System.out.println("Remote Host :" +request.getRemoteHost());
		String remoteHost =request.getRemoteHost();
		int remotePort=request.getRemotePort();
		System.out.println(remoteHost+":"+remotePort);
		System.out.println("Remote Address" +request.getRemoteAddr());
		return Collections.singletonMap("token", session.getId());
	}
	@GetMapping("/checkSession")
	public  ResponseEntity<Void> checkSession(){
		System.out.println("######################---In Check Session-------############");
			return ResponseEntity.noContent().build();
	}
	@PostMapping("/user/logout")
	public ResponseEntity<Void> logout(HttpServletRequest request,HttpServletResponse response) {
		
		HttpSession session= request.getSession(false);
	    SecurityContextHolder.clearContext();
	         session= request.getSession(false);
	        if(session != null) {
	            session.invalidate();
	        }
		/*
		 * for(Cookie cookie : request.getCookies()) { cookie.setMaxAge(0); }
		 */
		
		System.out.println("############------In Logout#############----------");
		//SecurityContextHolder.clearContext();
		return ResponseEntity.noContent().build();
}
}