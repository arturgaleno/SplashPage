package br.com.artur.dao;

import java.util.List;

import br.com.artur.exception.DaoException;
import br.com.artur.model.Serie;
import br.com.artur.model.TransposedSurveyAnswer;

public interface TransposedSurveyAnswerDao extends GenericDao<TransposedSurveyAnswer, Long> {

	List<Serie> question1Report();

	List<Serie> question2Report();

	List<Serie> question3Report();

	void batchSave(List<TransposedSurveyAnswer> tranposeds) throws DaoException;


}
