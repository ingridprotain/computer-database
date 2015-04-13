package com.excilys.computerdatabase.persistence.implementation;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.computerdatabase.model.QUser;
import com.excilys.computerdatabase.model.User;
import com.excilys.computerdatabase.persistence.IUserDAO;
import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.hibernate.HibernateQuery;

@Repository
public class UserDAO implements IUserDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public User findByUsername(String username) {
		QUser user = QUser.user;
		JPQLQuery query = new HibernateQuery(sessionFactory.getCurrentSession());
		
		return query.from(user)
				.where(user.username.eq(username))
				.uniqueResult(user);
	}
}
