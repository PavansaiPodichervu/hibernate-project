package edu.jsp.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import edu.jsp.model.User;

public class UserController {
	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("dev");
	private static EntityManager entityManager = entityManagerFactory.createEntityManager();

	public User findUser(String email, String password) {
		User user = null;
		try {
			String qry = "select u from User u where u.email=?1 and u.password=?2";
			Query query = entityManager.createQuery(qry);
			query.setParameter(1, email);
			query.setParameter(2, password);
			user = (User) query.getSingleResult();
			return user;
		} catch (Exception e) {
			return user;
		}
	}

	public User saveUser(User user) {
		try {
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();
			entityManager.persist(user);
			transaction.commit();
			return user;
		} catch (Exception e) {
			return null;
		}
	}

	public User updateUser(int id, String name) {
		try {
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();
			User user = findUserById(id);
			user.setName(name);
			entityManager.merge(user);
			transaction.commit();
			return user;
		} catch (Exception e) {
			return null;
		}
	}

	public User findUserById(int id) {
		try {
			return entityManager.find(User.class, id);
		} catch (Exception e) {
			return null;
		}
	}

	public List<User> findAllUsers() {
		String qry = "select u from User u";
		try {
			Query query = entityManager.createQuery(qry);
			return query.getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	public boolean deleteUser(int id) {
		try {
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();
			entityManager.remove(findUserById(id));
			transaction.commit();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
