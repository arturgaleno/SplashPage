package br.com.artur.task;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.apache.deltaspike.scheduler.api.Scheduled;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import br.com.artur.dao.SubmitDao;
import br.com.artur.exception.DaoException;
import br.com.artur.exception.VelocityException;
import br.com.artur.model.Submit;
import br.com.artur.util.EMailSender;
import br.com.artur.util.SurveyInviteEmail;

@Scheduled(cronExpression = "0 0/5 * * * ?	", onStartup = false) //a cada 5 minutos
@Stateless
public class TaskSendPendingSubmits implements Job {

	@Inject
    SubmitDao submitDao;
	
	@Inject
	EMailSender emailSender;
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		try {
			
			List<Submit> submits = submitDao.findAllPendingSubmits();
			
			for (int i = 0; i < submits.size(); i++) {
				
				Submit submit = submits.get(i);
				InternetAddress address = new InternetAddress(submit.getEmail());
				
				SurveyInviteEmail surveyInviteEmail = new SurveyInviteEmail();
				surveyInviteEmail.setEmail(address);
				surveyInviteEmail.setLink(submit.getSurveyLink());
				
				try {
					emailSender.send(surveyInviteEmail);
				} catch (MessagingException e) {
					e.printStackTrace();
				} catch (VelocityException e) {
					e.printStackTrace();
				}
			}
			
		} catch (DaoException e) {
			e.printStackTrace();
		} catch (AddressException e) {
			e.printStackTrace();
		}
	}


}
