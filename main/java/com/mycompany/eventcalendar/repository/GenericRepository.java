package com.mycompany.eventcalendar.repository;

import java.util.List;

import com.mycompany.eventcalendar.model.EventSearch;

public interface GenericRepository<E> {

	E getObjectById(int id);
	List<E> getAllObjects();
	int getObjectCount();
	
	E addObject(E newObject);
	E updateObject(E updatedObject);
	E deleteObject(int id);
	
	List<E> findByNames(List<String> names);
	List<E> findByConstraint(EventSearch eventSearch);

}
