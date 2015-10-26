package br.com.artur.util;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

public class SurveyInviteEmail extends EmailMessageTemplate {

	private static final String TOPIC = "Hi! Email from SplashPage team!";
	
	private String link;
	
	public SurveyInviteEmail() {
		super.setTopic(TOPIC);
	}

	@Override
	public Template loadTemplate(VelocityEngine velocityEngine) throws Exception {
		return velocityEngine.getTemplate("velocity/surveyInvite.vm", "UTF-8");
	}
	
	@Override
	public void putAttributes(VelocityContext context) {
		context.put("surveyLink", link);
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
}
