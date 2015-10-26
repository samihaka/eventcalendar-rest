package com.mycompany.eventcalendar.client;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;

import com.mycompany.eventcalendar.model.Event;

public class EventClientTest {
	
	@Test
	public void testGet() {
		EventClient client = new EventClient();
		
		Event event = client.getEvent(1);
		
		assertEquals("Check, that correct event is found", event.getId(), 1);
	}
	
	@Test(expected = RuntimeException.class)
	public void testGetNotFound() {
		EventClient client = new EventClient();
		
		Event event = client.getEvent(6);
	}
	
	@Test
	public void testGetAll(){
		EventClient client = new EventClient();

		List<Event> events = client.getAllEvents();
		
		assertEquals("Check, that correct amount of events is found", events.size(), 5);
	}
	
	@Test
	public void testCreateNewEvent(){
		EventClient client = new EventClient();
		
		Event event = new Event();
		event.setId(6);
		event.setName("POST test");
		event.setDescription("New event");
		event.setStartDate(LocalDate.now().toString());
		event.setEndDate(LocalDate.now().toString());
		
		event = client.createNewEvent(event);
		
		assertEquals("Test that created event has correct id", event.getId(), 6);
	}
	
	@Test(expected = RuntimeException.class)
	public void testCreateNewEventWithNullEvent(){
		EventClient client = new EventClient();
		
		Event event = null;
		
		event = client.createNewEvent(event);
	}
	
	@Test
	public void testUpdateWithNewEvent(){
		EventClient client = new EventClient();
		
		Event event = new Event();
		event.setId(7);
		event.setName("PUT test");
		event.setStartDate(LocalDate.now().toString());
		event.setEndDate(LocalDate.now().toString());
		
		event = client.updateEvent(event);
		
		assertEquals("Test that created event has correct id", event.getId(), 7);
	}
	
	@Test
	public void testUpdateExistingEvent(){
		EventClient client = new EventClient();
		
		Event event = new Event();
		event.setId(3);
		event.setName("PUT test 3");
		event.setStartDate(LocalDate.now().toString());
		event.setEndDate(LocalDate.now().toString());
		
		event = client.updateEvent(event);
		
		assertEquals("Test that created event has correct name", event.getName(), "PUT test 3");
	}
	
	@Test
	public void testDelete(){
		EventClient client = new EventClient();
		
		Event event = client.deleteEvent(1);
		
		assertEquals("Test that deleted event has correct id", event.getId(), 1);
	}
}
