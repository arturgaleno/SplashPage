package br.com.artur.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.artur.exception.DaoException;
import br.com.artur.model.Submit;

@Stateless
public class SubmitDaoImpl extends GenericDaoImpl<Submit, Long> implements
		SubmitDao {

	@PersistenceContext(unitName = "pgresql")
	private EntityManager entityManager;

	@PostConstruct
	public void init() {
		super.instanciate(this.entityManager, Submit.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Submit> findAllPendingSubmits() throws DaoException {
		
		Query query = entityManager.createQuery("from Submit where enviado = false");
		
		try {
			return query.getResultList();			
		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public Submit findByEmail(String email) throws DaoException {
		
		Query query = entityManager.createQuery("from Submit where email = :email");
		query.setParameter("email", email);
		
		try {
			return (Submit) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (NonUniqueResultException e) {
			throw new DaoException(e.getMessage(), e.getCause());
		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e.getCause());
		}
	}

	
}
