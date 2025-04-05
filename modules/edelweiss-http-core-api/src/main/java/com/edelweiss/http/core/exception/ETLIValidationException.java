package com.edelweiss.http.core.exception;

public class ETLIValidationException extends Exception {
	
	/** The Constant serialVersionUID. **/

	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new ETIP system exception.
	 */
	public ETLIValidationException() {
		super();
	}

	/**
	 * Instantiates a new ETIP system exception.
	 */
	public ETLIValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * Instantiates a new ETIP system exception.
	 */
	public ETLIValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new ETIP system exception.
	 */
	public ETLIValidationException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new ETIP system exception.
	 */
	public ETLIValidationException(Throwable cause) {
		super(cause);
	}

}

