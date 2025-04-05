package com.edelweiss.http.core.exception;

public class ETIPSystemException extends Exception {
	
	/** The Constant serialVersionUID. **/
	private static final long serialVersionUID = 1854174299110193609L;

	/**
	 * Instantiates a new ETIP system exception.
	 */
	public ETIPSystemException() {
		super();
	}

	/**
	 * Instantiates a new ETIP system exception.
	 */
	public ETIPSystemException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * Instantiates a new ETIP system exception.
	 */
	public ETIPSystemException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new ETIP system exception.
	 */
	public ETIPSystemException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new ETIP system exception.
	 */
	public ETIPSystemException(Throwable cause) {
		super(cause);
	}

}
