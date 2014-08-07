package de.mare.mobile.services;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.mare.mobile.domain.User;

/**
 * 
 * @author mreinhardt
 * 
 */
@Stateless
@PermitAll
public class UserRepository {

	private Logger LOG = LoggerFactory.getLogger(UserRepository.class);

	@Inject
	private EntityManager entityManager;

	/**
	 * 
	 * @param pUserToSave
	 * @return
	 */
	public User addUser(final User pUserToSave) {
		entityManager.persist(pUserToSave);
		return pUserToSave;
	}

	/**
	 * Get all registered users, ordered by lastname
	 * 
	 * @return a list of users
	 */
	public List<User> getAllUsers() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> criteria = cb.createQuery(User.class);
		Root<User> member = criteria.from(User.class);
		criteria.select(member).orderBy(cb.asc(member.get("lastname")));
		List<User> result = null;
		try {
			result = entityManager.createQuery(criteria).getResultList();
		} catch (Exception e) {
			LOG.info("Found no result.");
		}
		return result;
	}

}
