package com.mycompany.eventcalendar.repository.impl;

import java.util.ArrayList;
import java.util.List;

import com.mycompany.eventcalendar.model.AbstractDataObject;
import com.mycompany.eventcalendar.model.EventSearch;
import com.mycompany.eventcalendar.repository.GenericRepository;

/**
 * This class handles data operations for List with generic data type
 * 
 * @author Sami
 *
 */
public class ListRepositoryImpl<E extends AbstractDataObject> implements GenericRepository<E>{

	private List<E> objects;
	
	public ListRepositoryImpl(){
		objects = new ArrayList<E>();	
	}
	
	@Override
	public E getObjectById(int id) {
		E foundObject = null;
		
		System.out.println("getObjectById: "+id);
		
		for(E object: objects){
			if(object.getId() == id){
				foundObject = object;
				break;
			}
		}
		
		System.out.println("getObjectById, foundObject: "+foundObject);
		
		return foundObject;
	}

	@Override
	public List<E> getAllObjects() {	
		return objects;
	}

	@Override
	public int getObjectCount(){
		return objects.size();
	}
	
	@Override
	public E addObject(E newObject) {
		System.out.println("addObject: "+newObject);
		objects.add(newObject);	
		
		return objects.get(objects.size() - 1);
	}

	/**
	 * This method updates specified object or creates it, if it doesn't already exist.
	 */
	@Override
	public E updateObject(E updatedObject) {
		E object = this.getObjectById(updatedObject.getId());
		
		if(object != null){
			object.setName(updatedObject.getName());
		}
		else{
			object = updatedObject;
			this.addObject(object);
		}
		
		return object;
	}

	@Override
	public E deleteObject(int id) {
		E deletedObject = null;
		
		for(E object: objects){
			if(object.getId() == id){
				deletedObject = object;
				break;
			}
		}
		
		if(deletedObject != null){
			objects.remove(deletedObject);
		}
		
		return deletedObject;
	}

	
	@Override
	public List<E> findByNames(List<String> names) {
		List<E> foundObjects = new ArrayList<E>();
		
		for(E object: objects){
			for(String name: names){
				if(object.getName().equals(name)){
					foundObjects.add(object);
				}
			}
		}
		
		return foundObjects;
	}

	@Override
	public List<E> findByConstraint(EventSearch eventSearch) {
		List<E> foundObjects = new ArrayList<E>();
		
		for(E object: objects){
			for(String name: eventSearch.getNames()){
				if(object.getName().equals(name)){
					foundObjects.add(object);
				}
			}
		}
		
		return foundObjects;
	}

}
