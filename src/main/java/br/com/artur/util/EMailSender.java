package br.com.artur.util;

import java.util.Calendar;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.mail.*;
import javax.mail.internet.MimeMessage;

import org.jboss.logging.Logger;
import org.jboss.logging.Logger.Level;

import br.com.artur.dao.SubmitDao;
import br.com.artur.exception.DaoException;
import br.com.artur.exception.VelocityException;
import br.com.artur.model.Submit;

import com.google.common.base.Charsets;

@Stateless
public class EMailSender {

	private static final Logger LOGGER = Logger.getLogger(EMailSender.class.getName());

	private Session session;

	@Inject
	SubmitDao submitDao;

	@PostConstruct
	public void init() {
		final String username = "";//"username@gmail.com";
		final String password = "";//"password";

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});
	}

	@Asynchronous
	public void send(EmailMessageTemplate emailMessage) throws MessagingException, VelocityException, DaoException {

		try {

			Submit submit = submitDao.findByEmail(emailMessage.getEmail().getAddress());

			if (submit != null && submit.isEnviado()) {
				return;
			} else if (submit != null && !submit.isEnviado()) {
				performSend(emailMessage);
				updateSubmitAsSent(submit);
			} else {
				performSend(emailMessage);
				saveSubmitAsSent(emailMessage);
			}

		} catch (MessagingException e) {
			LOGGER.log(Level.ERROR, "Cannot send e-mail", e);
			savePendingSubmit(emailMessage);
			throw e;
		} catch (DaoException e) {
			LOGGER.log(Level.ERROR, "Cannot initialize send e-mail process", e);
			savePendingSubmit(emailMessage);
			throw e;
		}
	}


	private void performSend(EmailMessageTemplate emailMessage) throws MessagingException, VelocityException {
		MimeMessage message = new MimeMessage(session);
		message.setFrom(emailMessage.getSender());
		message.setRecipients(Message.RecipientType.BCC, emailMessage.getAddressArray());
		message.setSubject(emailMessage.getTopic());
		message.setSentDate(Calendar.getInstance().getTime());
		message.setText(emailMessage.getMessageBody(), Charsets.UTF_8.name(), "html");

		Transport.send(message);
	}

	@Asynchronous
	private void savePendingSubmit(EmailMessageTemplate emailMessage) {
		try {
			Submit submit = submitDao.findByEmail(emailMessage.getEmail().getAddress());

			if (submit == null) {
				submit = new Submit();
				submit.setEmail(emailMessage.getEmail().getAddress());
				submit.setEnviado(false);
				submit.setSurveyLink(((SurveyInviteEmail) emailMessage).getLink());

				submitDao.save(submit);
			}
		} catch (DaoException e) {
			e.printStackTrace();
		}
	}

	@Asynchronous
	private void updateSubmitAsSent(Submit submit) throws DaoException {
		submit.setEnviado(true);
		submitDao.merge(submit);
	}

	@Asynchronous
	private void saveSubmitAsSent(EmailMessageTemplate emailMessage) throws DaoException {
		Submit submit = new Submit();
		submit.setEmail(emailMessage.getEmail().getAddress());
		submit.setEnviado(true);
		submit.setSurveyLink(((SurveyInviteEmail) emailMessage).getLink());

		submitDao.save(submit);
	}
}