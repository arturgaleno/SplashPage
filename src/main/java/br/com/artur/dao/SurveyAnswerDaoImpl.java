package br.com.artur.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.artur.exception.DaoException;
import br.com.artur.model.SurveyAnswer;
import br.com.artur.model.TransposedSurveyAnswer;

@Stateless
public class SurveyAnswerDaoImpl extends GenericDaoImpl<SurveyAnswer, Long> implements
		SurveyAnswerDao {

	@PersistenceContext(unitName = "pgresql")
	private EntityManager entityManager;

	@PostConstruct
	public void init() {
		super.instanciate(this.entityManager, SurveyAnswer.class);
	}
	
	@Override
	public SurveyAnswer findByEmail(String email) throws DaoException {
		
		Query query = entityManager.createQuery("from SurveyAnswer where email = :email");
		query.setParameter("email", email);
		
		try {
			return (SurveyAnswer) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (NonUniqueResultException e) {
			throw new DaoException(e.getMessage(), e.getCause());
		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e.getCause());
		}
	}
	
	/**
	 * Only an example of transformation, do not use in production environments
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<TransposedSurveyAnswer> transposeAnswers() throws DaoException {
	
		try {
		
			StringBuilder strBuilder = new StringBuilder();
			
			strBuilder.append(" SELECT 	case when answer1 = 1 then count(answer1) end expected1, ");
			strBuilder.append(" case when answer2 = 1 then count(answer2) end pay1, ");
			strBuilder.append(" case when answer3 = 1 then count(answer3) end needs1, ");
			strBuilder.append(" case when answer1 = 2 then count(answer1) end expected2, ");
			strBuilder.append(" case when answer2 = 2 then count(answer2) end pay2, ");
			strBuilder.append(" case when answer3 = 2 then count(answer3) end needs2, ");
			strBuilder.append(" case when answer1 = 3 then count(answer1) end expected3, ");
			strBuilder.append(" case when answer2 = 3 then count(answer2) end pay3, ");
			strBuilder.append(" case when answer3 = 3 then count(answer3) end needs3 ");
			strBuilder.append(" FROM surveyanswer ");
			strBuilder.append(" GROUP BY answer1, answer2, answer3 ");
	
			
			Query query = entityManager.createNativeQuery(strBuilder.toString());
			
			List<Object[]> objects = query.getResultList();
			List<TransposedSurveyAnswer> transposedSurveyAnswers = new ArrayList<>();
			
			for (int i = 0; i < objects.size(); i++) {
				TransposedSurveyAnswer transposedSurveyAnswer = new TransposedSurveyAnswer();
				transposedSurveyAnswer.setExpected1((BigInteger) objects.get(i)[0]);
				transposedSurveyAnswer.setPay1((BigInteger) objects.get(i)[1]);
				transposedSurveyAnswer.setNeeds1((BigInteger) objects.get(i)[2]);
				
				transposedSurveyAnswer.setExpected2((BigInteger) objects.get(i)[3]);
				transposedSurveyAnswer.setPay2((BigInteger) objects.get(i)[4]);
				transposedSurveyAnswer.setNeeds2((BigInteger) objects.get(i)[5]);
				
				transposedSurveyAnswer.setExpected3((BigInteger) objects.get(i)[6]);
				transposedSurveyAnswer.setPay3((BigInteger) objects.get(i)[7]);
				transposedSurveyAnswer.setNeeds3((BigInteger) objects.get(i)[8]);
				
				transposedSurveyAnswers.add(transposedSurveyAnswer);
			}
			
			return transposedSurveyAnswers;
			
		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e.getCause());
		}
	}
	
}
