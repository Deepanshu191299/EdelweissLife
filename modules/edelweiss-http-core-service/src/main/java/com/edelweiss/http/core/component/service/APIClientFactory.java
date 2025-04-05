package com.edelweiss.http.core.component.service;

import com.edelweiss.http.core.component.api.APIClient;

/**
 * 
 * @author Ravi Shah
 *
 */
public class APIClientFactory {

	private APIClientFactory() {
		throw new IllegalStateException();
	}

	/** The api client. */
	private static APIClient apiClient = null;

	/**
	 * Gets the single instance of APIClientFactory.
	 *
	 * @return single instance of APIClientFactory
	 */
	public static APIClient getInstance() {

		if (apiClient == null) {
			apiClient = new HttpAPIClient();
		}
		return apiClient;

	}
}
