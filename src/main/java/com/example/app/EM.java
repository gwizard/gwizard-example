package com.example.app;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.persistence.EntityManager;

/**
 */
public class EM {
	@Inject
	private static Provider<EntityManager> entityManagerProvider;

	public static EntityManager em() {
		return entityManagerProvider.get();
	}
}
