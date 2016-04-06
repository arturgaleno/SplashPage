package br.com.artur.controller;

import java.util.List;

import javax.inject.Inject;

import br.com.artur.dao.TransposedSurveyAnswerDao;
import br.com.artur.model.Serie;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;

@Controller
public class ReportController {

	private final Result result;
	
	@Inject
	TransposedSurveyAnswerDao transposedSurveyAnswerDao;
	
	/**
	 * @deprecated CDI eyes only
	 */
	protected ReportController() {
		this(null);
	}
	
	@Inject
	public ReportController(Result result) {
		this.result = result;
	}
	
	@Get("/report")
	public void report() {
	}
	
	@Get("/report/{question}")
	public void report(String question) {
		
		//this is hardcoded because the survey isn't dynamic
		
		if (question.equals("q1")) {
			
			List<Serie> serie = transposedSurveyAnswerDao.question1Report();
			
			result.use(Results.json()).withoutRoot()
										.from(serie)
										.include("color","id","name","data")
										.serialize();
			
		}else if (question.equals("q2")) {
			
			List<Serie> serie = transposedSurveyAnswerDao.question2Report();
			
			result.use(Results.json()).withoutRoot()
										.from(serie)
										.include("color","id","name","data")
										.serialize();
			
		} else {
			
			List<Serie> serie = transposedSurveyAnswerDao.question3Report();
			
			result.use(Results.json()).withoutRoot()
										.from(serie)
										.include("color","id","name","data")
										.serialize();
			
		}
	}
}
