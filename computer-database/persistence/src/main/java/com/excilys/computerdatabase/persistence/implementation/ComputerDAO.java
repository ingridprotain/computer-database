package com.excilys.computerdatabase.persistence.implementation;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.model.QCompany;
import com.excilys.computerdatabase.model.QComputer;
import com.excilys.computerdatabase.persistence.IComputerDAO;
import com.excilys.computerdatabase.utils.Pages;
import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.hibernate.HibernateDeleteClause;
import com.mysema.query.jpa.hibernate.HibernateQuery;
import com.mysema.query.types.OrderSpecifier;

@Repository
public class ComputerDAO implements IComputerDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	//TODO Transactional dans service?
	@Override
	@Transactional
	public Computer find(int id) {
		QComputer computer = QComputer.computer;
		JPQLQuery query = new HibernateQuery(sessionFactory.getCurrentSession());

		return query.from(computer)
				.where(computer.id.eq(id))
				.uniqueResult(computer);
	}

	@Override
	@Transactional
	public void create(Computer computer) {
		sessionFactory.getCurrentSession().save(computer);
	}
	
	@Override
	@Transactional
	public void update(Computer computer) {
		sessionFactory.getCurrentSession().update(computer);
	}

	@Override
	@Transactional
	public void delete(Computer computer) {
		sessionFactory.getCurrentSession().delete(computer);
	}
	
	@Override
	@Transactional
	public void deleteByCompanyId(int company_id) {
		QComputer computer = QComputer.computer;
		new HibernateDeleteClause(sessionFactory.getCurrentSession(), computer).where(computer.company.id.eq(company_id)).execute();
	}

	@Override
	@Transactional
	public int count() {
		QComputer computer = QComputer.computer;
		JPQLQuery query = new HibernateQuery(sessionFactory.getCurrentSession());
		return query.from(computer).list(computer).size();
	}
	
	@Override
	@Transactional
	public int countSearch(String name) {
		QComputer computer = QComputer.computer;
		QCompany company = QCompany.company;
		JPQLQuery query = new HibernateQuery(sessionFactory.getCurrentSession());

		return query.from(computer)
				.leftJoin(computer.company, company)
				.where(computer.name.like("%" + name + "%").or(company.name.like("%" + name + "%")))
				.list(computer)
				.size();
	}
	
	@Override
	@Transactional
	public List<Computer> getAll() {
		QComputer computer = QComputer.computer;
		JPQLQuery query = new HibernateQuery(sessionFactory.getCurrentSession());

		return query.from(computer).list(computer);
	}
	
	@Override
	@Transactional
	public List<Computer> getAll(Pages pagination) {
		
		List<Computer> computers = new ArrayList<Computer>();
		QComputer computer = QComputer.computer;
		QCompany company = QCompany.company;
		JPQLQuery query = new HibernateQuery(sessionFactory.getCurrentSession());
		
		OrderSpecifier<?> order;
		switch (pagination.getOrderByColumn()) {
		case "name":
			order = (pagination.getOrderBy() == "ASC" ? computer.name.asc() : computer.name.desc());
			break;
		case "introduced":
			order = (pagination.getOrderBy() == "ASC" ? computer.introduced.asc() : computer.introduced.desc());
			break;
		case "discontinued":
			order = (pagination.getOrderBy() == "ASC" ? computer.discontinued.asc() : computer.discontinued.desc());
			break;
		case "company":
			order = (pagination.getOrderBy() == "ASC" ? computer.company.name.asc() : computer.company.name.desc());
			break;
		default:
			order = computer.name.asc();
			break;
		}
		
		
		if (pagination.getSearch() != null) {
			computers = query.from(computer)
				.leftJoin(computer.company, company)
				.where(computer.name.like("%" + pagination.getSearch() + "%").or(company.name.like("%" + pagination.getSearch() + "%")))
				.orderBy(order)
				.limit(pagination.getLimit())
				.offset(pagination.getOffset())
				.list(computer);
		} else {
			computers = query.from(computer)
				.orderBy(order)
				.limit(pagination.getLimit())
				.offset(pagination.getOffset())
				.list(computer);
		}

		return computers;
	}
}
