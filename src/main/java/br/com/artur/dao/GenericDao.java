package br.com.artur.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.artur.exception.DaoException;

public interface GenericDao<T, K extends Serializable> {

	void delete(T obj) throws DaoException;

	T find(K id) throws DaoException;

	List<T> findAll() throws DaoException;

	void save(T obj) throws DaoException;
	
	T merge(T obj) throws DaoException;

	public EntityManager getEntityManager();



}