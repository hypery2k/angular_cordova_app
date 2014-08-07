package de.mare.mobile.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.BeforeClass;

/**
 * Common base for all JPA related tests. This class inits the persistence
 * framework. JPAHelper based tests works only with pure DAO-Beans, for business
 * ejb, e.g. UserManager use integration tests
 * 
 * @author mreinhardt
 */
public class JPAHelper {

	protected static EntityManagerFactory emf;

	protected static EntityManager em;

	/**
	 * Set up entity maneger and factory
	 * 
	 * @throws Exception
	 */
	@BeforeClass
	public static void setup() throws Exception {
		// Get the entity manager for the tests.
		emf = Persistence.createEntityManagerFactory("chatTestPU");
		em = emf.createEntityManager();

	}

}
