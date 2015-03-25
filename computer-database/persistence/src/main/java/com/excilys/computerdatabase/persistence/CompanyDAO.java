package com.excilys.computerdatabase.persistence;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.QCompany;
import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.hibernate.HibernateDeleteClause;
import com.mysema.query.jpa.hibernate.HibernateQuery;

@Repository
public class CompanyDAO implements ICompanyDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public Company find(int id) {
		return (Company) sessionFactory.getCurrentSession().get(Company.class, id);
	}

	@Override
	@Transactional
	public List<Company> getAll() {
		List<Company> companies = new ArrayList<Company>();
		QCompany company = QCompany.company;
		JPQLQuery query = new HibernateQuery(sessionFactory.getCurrentSession());
		companies = query.from(company).list(company);
		return companies;
	}

	@Override
	@Transactional
	public void delete(Company company) {
		QCompany qcompany = QCompany.company;
		new HibernateDeleteClause(sessionFactory.getCurrentSession(), qcompany).where(qcompany.id.eq(company.getId()));
	}
}
