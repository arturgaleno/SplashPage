package br.com.artur.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;

import br.com.artur.dao.SubmitDao;
import br.com.artur.dao.SurveyAnswerDao;
import br.com.artur.dto.SurveyAnswerDTO;
import br.com.artur.exception.DaoException;
import br.com.artur.model.Submit;
import br.com.artur.model.SurveyAnswer;
import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;

@Controller
public class SurveyController {

	@Inject
	SurveyAnswerDao surveyAnswerDao;
	
	@Inject
	SubmitDao submitDao;
	
	private final Result result;

	/**
	 * @deprecated CDI eyes only
	 */
	protected SurveyController() {
		this(null);
	}
	
	@Inject
	public SurveyController(Result result) {
		this.result = result;
	}
	
	@Get("/survey/{email}")
	public void survey() {
		
	}
	
	@Get("/surveys/{email}")
	public void surveys(String email) {
		
		try {
			Submit submit = submitDao.findByEmail(email);
			if (submit == null) {
				result.use(Results.status()).notFound();
			} else {
				
				SurveyAnswer answer = surveyAnswerDao.findByEmail(submit.getEmail());
				
				SurveyAnswerDTO answerDTO = new SurveyAnswerDTO();
				if (answer != null) {
					answerDTO.setAnswer1(answer.getAnswer1());
					answerDTO.setAnswer2(answer.getAnswer2());
					answerDTO.setAnswer3(answer.getAnswer3());
				}
				answerDTO.setEmail(submit.getEmail());
				
				result.include(answerDTO).use(Results.status()).ok();
			}
		} catch (DaoException e) {
			e.printStackTrace();
			result.use(Results.status()).notFound();
		}
	}
	
	@Post("/surveys")
	@Consumes(MediaType.APPLICATION_JSON_VALUE)
	public void salveSurvey(SurveyAnswerDTO obj) {
		try {
			SurveyAnswer answer = surveyAnswerDao.findByEmail(obj.getEmail());
			Submit submit = submitDao.findByEmail(obj.getEmail());
		
			if (submit != null && answer == null) {
			
				answer = new SurveyAnswer();
				answer.setAnswer1(obj.getAnswer1());
				answer.setAnswer2(obj.getAnswer2());
				answer.setAnswer3(obj.getAnswer3());
				answer.setEmail(submit.getEmail());
				
				try {
					surveyAnswerDao.save(answer);
					
					result.use(Results.status()).ok();
				} catch (DaoException e) {
					e.printStackTrace();
					result.use(Results.http()).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				}
			} else if (submit != null && answer != null) {
				result.use(Results.status()).badRequest("Survey already answered!");
			} else {
				result.use(Results.status()).badRequest("Invalid request!");
			}
		} catch (DaoException e) {
			e.printStackTrace();
			result.use(Results.http()).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}
	
}
