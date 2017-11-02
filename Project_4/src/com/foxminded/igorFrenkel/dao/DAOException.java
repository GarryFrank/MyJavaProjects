package com.foxminded.igorFrenkel.dao;

public class DAOException extends Exception {

	private static final long serialVersionUID = -3436185210955255955L;

	public DAOException(){
		super();
	}
	
	public DAOException(String message, Throwable cause){
		super(message, cause);
	}
	
	public DAOException(String message){
		super(message);
	}
}

