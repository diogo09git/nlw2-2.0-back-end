package com.br.nlw.domain.user;

@SuppressWarnings("serial")
public class DuplicateUserException extends Exception {

	public DuplicateUserException(String message) {
		super(message);
	
	}
	
}
