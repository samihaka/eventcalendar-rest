package com.mycompany.eventcalendar.client;

import java.net.URI;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import com.mycompany.eventcalendar.model.Event;
import com.mycompany.eventcalendar.model.EventSearch;

public class EventSearchClient {
	private Client client;
	
	public EventSearchClient(){
		client = ClientBuilder.newClient();
	}
	
	/**
	 * Search using query params
	 * 
	 * @param param			Search parameter
	 * @param searchParams	Parameter values
	 * @return				Found events.
	 */
	public List<Event> searchEvents(String param, List<String> searchParams){
		URI uri = UriBuilder.fromUri("http://localhost:8080/eventcalendar/webapi/").
				path("search/events").
				queryParam(param,  searchParams).
				build();
		
		WebTarget target = client.target(uri);
		
		List<Event> response = target.request(MediaType.APPLICATION_XML).get(new GenericType<List<Event>>(){});
		return response;
	}

	/**
	 * Search using search object.
	 * 
	 * @param eventSearch	Search object.
	 * @return				Found events.
	 */
	public List<Event> searchEvents(EventSearch eventSearch) {
		URI uri = UriBuilder.fromUri("http://localhost:8080/eventcalendar/webapi/").
				path("search/events").
				build();	
		
		WebTarget target = client.target(uri);
		
		Response response = target.request(MediaType.APPLICATION_XML).post(Entity.entity(eventSearch, MediaType.APPLICATION_XML));

		if(response.getStatus() != 200){
			throw new RuntimeException(response.getStatus()+" error on server!");
		}

		return response.readEntity(new GenericType<List<Event>>() {});
	}
}
