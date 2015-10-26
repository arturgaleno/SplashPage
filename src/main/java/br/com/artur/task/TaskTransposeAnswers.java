package br.com.artur.task;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.deltaspike.scheduler.api.Scheduled;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import br.com.artur.dao.SurveyAnswerDao;
import br.com.artur.dao.TransposedSurveyAnswerDao;
import br.com.artur.exception.DaoException;
import br.com.artur.model.TransposedSurveyAnswer;

//@Scheduled(cronExpression = "0 1 0 1/1 * ? *", onStartup = false)
@Scheduled(cronExpression = "0 0/1 * * * ?	", onStartup = false) 
@Stateless
public class TaskTransposeAnswers implements Job {

	@Inject
    SurveyAnswerDao surveyAnswerDao;
	
	@Inject
	TransposedSurveyAnswerDao transposedSurveyAnswerDao;
	
	/**
	 * Only an example of transformation, do not use in production environments
	 */
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {		
		try {
			List<TransposedSurveyAnswer> transposeAnswers = surveyAnswerDao.transposeAnswers();
			transposedSurveyAnswerDao.batchSave(transposeAnswers);
		} catch (DaoException e) {
			e.printStackTrace();
		}
	}
}
