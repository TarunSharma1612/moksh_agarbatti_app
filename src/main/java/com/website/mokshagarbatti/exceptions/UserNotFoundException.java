package com.website.mokshagarbatti.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException  {
		public UserNotFoundException(String userEmail) {
			super("User not found with the email:"+userEmail);
		}
}
