package br.com.artur.dao;

import java.util.List;

import br.com.artur.exception.DaoException;
import br.com.artur.model.SurveyAnswer;
import br.com.artur.model.TransposedSurveyAnswer;

public interface SurveyAnswerDao extends GenericDao<SurveyAnswer, Long>{

	SurveyAnswer findByEmail(String email) throws DaoException;

	List<TransposedSurveyAnswer> transposeAnswers() throws DaoException;


}
