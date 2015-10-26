package com.mycompany.eventcalendar.service;

import java.time.LocalDate;

import com.mycompany.eventcalendar.model.Event;
import com.mycompany.eventcalendar.repository.GenericRepository;

/**
 * This class is used to initialize repository.
 * 
 * @author Sami
 *
 */
public class EventPopulationService {

	public void initializeEvents(GenericRepository<Event> repository){
		Event event1 = new Event(1, "Test 1", "Test 1 event", LocalDate.now(), LocalDate.now());
		Event event2 = new Event(2, "Test 2", "Test 2 event", LocalDate.now(), LocalDate.now());
		Event event3 = new Event(3, "Test 3", "Test 3 event", LocalDate.now(), LocalDate.now());
		Event event4 = new Event(4, "Test 4", "Test 4 event", LocalDate.now(), LocalDate.now());
		Event event5 = new Event(5, "Test 5", "Test 5 event", LocalDate.now(), LocalDate.now());
		
		repository.addObject(event1);
		repository.addObject(event2);
		repository.addObject(event3);
		repository.addObject(event4);
		repository.addObject(event5);
		
	}
}
