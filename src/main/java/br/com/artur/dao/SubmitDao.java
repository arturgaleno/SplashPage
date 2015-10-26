package br.com.artur.dao;

import java.util.List;

import br.com.artur.exception.DaoException;
import br.com.artur.model.Submit;

public interface SubmitDao extends GenericDao<Submit, Long>{

	List<Submit> findAllPendingSubmits() throws DaoException;
	
	Submit findByEmail(String string) throws DaoException;
}
