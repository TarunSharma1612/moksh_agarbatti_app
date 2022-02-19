package com.website.mokshagarbatti.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotAuthenticatedUser extends RuntimeException {

	public NotAuthenticatedUser(String userEmail) {
		super(userEmail + "is not an authenticated user to perform action");
	}
}
