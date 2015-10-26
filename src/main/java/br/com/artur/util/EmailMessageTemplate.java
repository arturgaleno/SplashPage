package br.com.artur.util;

import java.io.StringWriter;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import br.com.artur.exception.VelocityException;

abstract class EmailMessageTemplate {
    	
	private InternetAddress sender;
	
	private InternetAddress email;
	
	private String topic;

	private VelocityEngine ve = new VelocityEngine();
	
	private VelocityContext context = new VelocityContext();
	
	private Template t;
	
	public EmailMessageTemplate() {
		try {
			sender = new InternetAddress("arturgalenom@gmail.com");
		} catch (AddressException e) {
		}
	}

	public String getMessageBody() throws VelocityException {
		
		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        ve.setProperty("runtime.log.logsystem.class", "org.apache.velocity.runtime.log.NullLogSystem");
		
		try {
			this.ve.init();

			this.t = loadTemplate(ve);
			
			putAttributes(context);
			
			StringWriter writer = new StringWriter();
			
			this.t.merge(context, writer);
			
			return writer.toString();
		} catch (Exception e) {
			throw new VelocityException(e.getMessage(), e.getCause());
		}		
	}
	
	public InternetAddress[] getAddressArray() {
		InternetAddress[] addressArray = new InternetAddress[1];
		addressArray[0] = email;
		return addressArray;
	}
	
	public abstract Template loadTemplate(VelocityEngine velocityEngine) throws Exception;
	
	public abstract void putAttributes(VelocityContext context);

	public InternetAddress getEmail() {
		return email;
	}

	public void setEmail(InternetAddress address) {
		this.email = address;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public InternetAddress getSender() {
		return sender;
	}
}