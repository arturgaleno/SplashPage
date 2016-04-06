package br.com.artur.exception;

public class DaoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7079226116393711309L;

	public DaoException() {
	}

	public DaoException(String message) {
		super(message);
	}

	public DaoException(String message, Throwable cause) {
		super(message, cause);
	}

	public DaoException(Throwable cause) {
		super(cause);
	}

	public DaoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
