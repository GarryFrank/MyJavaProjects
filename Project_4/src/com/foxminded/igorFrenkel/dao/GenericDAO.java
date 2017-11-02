package com.foxminded.igorFrenkel.dao;

import java.util.List;

public interface GenericDAO<T> {
	
	public T save(T object) throws DAOException;

	List<T> retrieve() throws DAOException;

	T retrieveById(long id) throws DAOException;

	void update(T object) throws DAOException;

    void remove(T object) throws DAOException;

}
