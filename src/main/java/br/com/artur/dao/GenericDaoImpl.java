package br.com.artur.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import br.com.artur.exception.DaoException;

@Stateless
public  class GenericDaoImpl<T, K extends Serializable> implements GenericDao<T, K> {
	
	private EntityManager entityManager;
	private Class<T> claz;
	
	public GenericDaoImpl() {
	}
	
	public void instanciate(EntityManager entityManager, Class<T> claz) {
		this.entityManager = entityManager;
		this.claz = claz;
	}

	@Override
	public void delete(T obj) throws DaoException {
		try {
			entityManager.remove(obj);
		} catch (Exception e) {
			throw new DaoException(e);
		} finally {
			entityManager.flush();
		}
	}

	@Override
	public T find(K id) throws DaoException {
		try {
			return entityManager.find(claz, id);
		} catch (NoResultException ex) {
			return null;
		}catch (Exception e) {
            String erro = String.format("Exception on retrieve %s with id  %s", claz.getName(), id == null ? "" : id.toString());
            throw new DaoException(erro,e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() throws DaoException {
		try {
			return entityManager.createQuery(("FROM " + claz.getName())).getResultList();
		} catch (Exception e) {
            String erro = String.format("Exception on retrieve all objects of type %s", claz.getName());
			throw new DaoException(erro, e);
		}
	}
	
	@Override
	public void save(T obj) throws DaoException {
		try {
			entityManager.persist(obj);
		} catch (Exception e) {
			throw new DaoException(e);
		} finally {
			entityManager.flush();
		}
	}
	
	@Override
	public T merge(T obj) throws DaoException {
		try {
			return entityManager.merge(obj);
		} catch (Exception e) {
			throw new DaoException(e);
		} finally {
			entityManager.flush();
		}
	}
	
	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}



}
