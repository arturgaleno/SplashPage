package br.com.artur.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.CacheMode;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.artur.exception.DaoException;
import br.com.artur.model.Serie;
import br.com.artur.model.TransposedSurveyAnswer;

@Stateless
public class TransposedSurveyAnswerDaoImpl extends GenericDaoImpl<TransposedSurveyAnswer, Long> implements
		TransposedSurveyAnswerDao {

	@PersistenceContext(unitName = "pgresql")
	private EntityManager entityManager;

	@PostConstruct
	public void init() {
		super.instanciate(this.entityManager, TransposedSurveyAnswer.class);
	}
	
	@Override
	public void batchSave(List<TransposedSurveyAnswer> tranposeds) throws DaoException {
		
		try {
		
			Session session = super.getEntityManager().unwrap(Session.class);
			session.setCacheMode(CacheMode.IGNORE);
			Transaction tx = session.beginTransaction();
			
			int batchSize = 20;
			for (int i = 0; i < tranposeds.size(); i++) {
				TransposedSurveyAnswer tranposed = tranposeds.get(i);
			    if (tranposed != null) session.save(tranposed);
			    if(i % batchSize == 0) {
			        session.flush();
			        session.clear();
			    }
			}
			
			tx.commit();
			session.flush();
			session.clear();
		
		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e.getCause());
		}
	}
	
	@SuppressWarnings("unused")
	@Override
	public List<Serie> question1Report() {
		
		Query query = entityManager.createQuery("select sum(expected1), sum(expected2), sum(expected3) from TransposedSurveyAnswer");
		
		Serie serie = new Serie();
		
		Object[] values = (Object[]) query.getSingleResult();
		
		ArrayList<Serie> datas = mountData(values);
		datas.get(0).setName("Nothing");
		datas.get(1).setName("Not much");
		datas.get(2).setName("Awesome things");
		
		return datas;
	}
	
	@SuppressWarnings("unused")
	@Override
	public List<Serie> question2Report() {
		
		Query query = entityManager.createQuery("select sum(pay1), sum(pay2), sum(pay3) from TransposedSurveyAnswer");
		
		Serie serie = new Serie();
		
		Object[] values = (Object[]) query.getSingleResult();
		
		ArrayList<Serie> datas = mountData(values);
		datas.get(0).setName("I don't want give my money for you");
		datas.get(1).setName("I would like pay not so much");
		datas.get(2).setName("I would like pay my whole salary");
		
		return datas;
	}
	
	@SuppressWarnings("unused")
	@Override
	public List<Serie> question3Report() {
		
		Query query = entityManager.createQuery("select sum(needs1), sum(needs2), sum(needs3) from TransposedSurveyAnswer");
		
		Serie serie = new Serie();
		
		Object[] values = (Object[]) query.getSingleResult();
		
		ArrayList<Serie> datas = mountData(values);
		datas.get(0).setName("I need something that I want");
		datas.get(1).setName("I need food");
		datas.get(2).setName("Nothing, I already have everything");
		
		return datas;
	}

	private ArrayList<Serie> mountData(Object[] values) {
		Serie value1 = new Serie();
		Serie value2 = new Serie();
		Serie value3 = new Serie();
		
		if (values[0] != null)
			value1.setData(Arrays.asList(new BigDecimal((BigInteger) values[0])));
		
		if (values[1] != null)
			value2.setData(Arrays.asList(new BigDecimal((BigInteger) values[1])));
		
		if (values[2] != null)
			value3.setData(Arrays.asList(new BigDecimal((BigInteger) values[2])));
		
		ArrayList<Serie> datas = new ArrayList<Serie>();
		datas.add(value1);
		datas.add(value2);
		datas.add(value3);
		return datas;
	}
	
}
