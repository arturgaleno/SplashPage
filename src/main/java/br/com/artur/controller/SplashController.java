package br.com.artur.controller;

import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;

import br.com.artur.dto.EmailSubmitDTO;
import br.com.artur.exception.VelocityException;
import br.com.artur.util.EMailSender;
import br.com.artur.util.SurveyInviteEmail;
import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;

@Controller
public class SplashController {

	private final Result result;
	
	@Inject
	private EMailSender emailSender;
	
	@Inject 
    private HttpServletRequest request;  
	
	/**
	 * @deprecated CDI eyes only
	 */
	protected SplashController() {
		this(null);
	}
	
	@Inject
	public SplashController(Result result) {
		this.result = result;
	}

	@Get("/")
	public void splash() {
	}
	
	@Post("/emailSubmit")
	@Consumes(MediaType.APPLICATION_JSON_VALUE)
	public void postEmailSubmit(EmailSubmitDTO emailSubmit) {
		
		try {
			InternetAddress address = new InternetAddress(emailSubmit.getEmail());
		
			SurveyInviteEmail surveyInviteEmail = new SurveyInviteEmail();
			surveyInviteEmail.setEmail(address);
			surveyInviteEmail.setLink(getSurveyLink(address));
			emailSender.send(surveyInviteEmail);
			
			result.use(Results.status()).ok();
		} catch (AddressException e) {
			e.printStackTrace();
			result.use(Results.status()).badRequest("Invalid e-mail address!");
		} catch (MessagingException e) {
			e.printStackTrace();
			result.use(Results.http()).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		} catch (VelocityException e) {
			e.printStackTrace();
			result.use(Results.http()).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			result.use(Results.http()).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

	private String getSurveyLink(InternetAddress address) {
		return "http://" + request.getHeader("Host") + request.getContextPath() + "/survey/" + address.getAddress();
	}
}