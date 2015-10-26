package com.mycompany.eventcalendar.client;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.mycompany.eventcalendar.model.Event;
import com.mycompany.eventcalendar.model.EventSearch;
import com.mycompany.eventcalendar.model.EventSearchType;

public class EventSearchClientTest {

	/**
	 * Test search using query parameters.
	 */
	@Test
	public void testSearchForEvents(){
		EventSearchClient client = new EventSearchClient();
		
		String param = "name";
		List<String> searchParams = new ArrayList<String>();
		
		searchParams.add("test");
		
		List<Event> events = client.searchEvents(param, searchParams);
		
		assertNotNull(events);
	}
	
	@Test
	public void testSearchForEventsWithSearchObject(){
		EventSearchClient client = new EventSearchClient();	
		
		List<String> searchParams = new ArrayList<String>();
		
		searchParams.add("Test 2");
		searchParams.add("Test 4");
		
		EventSearch eventSearch = new EventSearch();
		eventSearch.setNames(searchParams);
		eventSearch.setSearchType(EventSearchType.SEARCH_BY_NAME);
		
		List<Event> events = client.searchEvents(eventSearch);
		
		assertEquals("Check, that correct amount of events is returned", events.size(), 2);
	}
}
