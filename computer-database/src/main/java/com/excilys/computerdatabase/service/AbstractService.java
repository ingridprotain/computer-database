package com.excilys.computerdatabase.service;

import java.util.List;

import com.excilys.computerdatabase.persistence.DataSource;

public abstract class AbstractService<T> {
	
	public final T find(int id) {
		T object = null;
		DataSource.INSTANCE.initTransaction();
		
		object = findAbstract(id);
		
		DataSource.INSTANCE.closeTransaction();
		return object;
	}
	
	public final T create(T object) {
		DataSource.INSTANCE.initTransaction();
		object = createAbstract(object);
		DataSource.INSTANCE.closeTransaction();
		return object;
	}
	
	public T update(T object) {
		DataSource.INSTANCE.initTransaction();
		object = updateAbstract(object);
		DataSource.INSTANCE.closeTransaction();
		return object;
	}
	
	public void delete(T object) {
		DataSource.INSTANCE.initTransaction();
		deleteAbstract(object);
		DataSource.INSTANCE.closeTransaction();
	}
	
	public List<T> getAll() {
		List<T> objects = null;
		DataSource.INSTANCE.initTransaction();
		objects = getAllAbstract();
		DataSource.INSTANCE.closeTransaction();
		return objects;
	}
	
	public abstract T findAbstract(int id);
	public abstract T createAbstract(T object);
	public abstract T updateAbstract(T object);
	public abstract void deleteAbstract(T object);
	public abstract List<T> getAllAbstract();
}
