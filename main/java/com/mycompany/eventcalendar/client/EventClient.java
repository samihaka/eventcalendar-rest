package com.mycompany.eventcalendar.client;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mycompany.eventcalendar.model.Event;

public class EventClient {

	private Client client;
	private WebTarget baseUrl;
	
	public EventClient(){
		client = ClientBuilder.newClient();
		baseUrl = client.target("http://localhost:8080/eventcalendar/webapi/");
	}
	
	/**
	 * Get specific event.
	 * 
	 * @param id	Id of retrieved event.
	 * @return		Found event.
	 */
	public Event getEvent(int id){
		Response response = baseUrl.path("events/"+id).request(MediaType.APPLICATION_JSON).get(Response.class);
		
		if(response.getStatus() != 200){
			throw new RuntimeException(response.getStatus()+" error on server!");
		}
		
		return response.readEntity(Event.class);
	}
	
	/**
	 * Get all events.
	 * 
	 * @return	List of events.
	 */
	public List<Event> getAllEvents(){
		List<Event> response = baseUrl.path("events").request(MediaType.APPLICATION_JSON).get(new GenericType<List<Event>>(){});
		
		return response;
	}

	/**
	 * Create new event (POST).
	 * 
	 * @param event		Event to create.
	 * @return			Created event.
	 */
	public Event createNewEvent(Event event) {
		Response response = baseUrl.path("events/event").
				request(MediaType.APPLICATION_XML).
				post(Entity.entity(event, MediaType.APPLICATION_XML));
		
		if(response.getStatus() != 200){
			throw new RuntimeException(response.getStatus()+" error on server!");
		}

		return response.readEntity(Event.class);
	}

	/**
	 * Update existing event (PUT).
	 * 
	 * @param event		Event to update.
	 * @return			Updated event.
	 */
	public Event updateEvent(Event event) {
		Response response = baseUrl.path("events/"+event.getId()).
				request(MediaType.APPLICATION_XML).
				put(Entity.entity(event, MediaType.APPLICATION_XML));
		
		if(response.getStatus() != 200){
			throw new RuntimeException(response.getStatus()+" error on server!");
		}

		return response.readEntity(Event.class);
		
	}

	/**
	 * Delete event with specified id (DELETE).
	 * 
	 * @param 	Id of event to be deleted.
	 * @return	Deleted event.
	 */
	public Event deleteEvent(int eventId) {
		Response response = baseUrl.path("events/"+eventId).
				request(MediaType.APPLICATION_XML).delete();
		
		if(response.getStatus() != 200){
			throw new RuntimeException(response.getStatus()+" error on server!");
		}

		return response.readEntity(Event.class);
	}
}
