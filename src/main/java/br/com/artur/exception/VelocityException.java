package br.com.artur.exception;

public class VelocityException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7079226116393711309L;

	public VelocityException() {
	}

	public VelocityException(String message) {
		super(message);
	}

	public VelocityException(String message, Throwable cause) {
		super(message, cause);
	}

	public VelocityException(Throwable cause) {
		super(cause);
	}

	public VelocityException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
