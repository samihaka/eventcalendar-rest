package com.mycompany.eventcalendar.resource;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.mycompany.eventcalendar.model.Event;
import com.mycompany.eventcalendar.model.EventSearch;
import com.mycompany.eventcalendar.repository.GenericRepository;
import com.mycompany.eventcalendar.repository.impl.ListRepositoryImpl;
import com.mycompany.eventcalendar.service.EventPopulationService;

/**
 * Search using query parameters.
 * 
 * @author Sami
 *
 */
@Path("search/events")
public class EventSearchResource {
	private GenericRepository<Event> eventRepository = new ListRepositoryImpl<Event>();
	private EventPopulationService populationService;
	
	public EventSearchResource(){
		populationService = new EventPopulationService();
		populationService.initializeEvents(eventRepository);
	}
	
	/**
	 * Search using query params.
	 * 
	 * @param names		List of names to search.
	 * @return			Found events.
	 */
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Response searchForEvents(@QueryParam(value = "name") List<String> names){
		List<Event> events = new ArrayList<Event>();
		
		System.out.println("Search, GET");
		
		events = eventRepository.findByNames(names);
		
		if(events ==  null && events.size() <= 0){
			return Response.status(Status.NOT_FOUND).build();
		}
		
		return Response.ok().entity(new GenericEntity<List<Event>>(events){}).build();
	}
	
	/**
	 * Search using search object.
	 * 
	 * @param names
	 * @return
	 */
	@POST
	@Produces(MediaType.APPLICATION_XML)
	public Response searchForEventsWithSearchObject(EventSearch eventSearch){
		System.out.println("Search, POST");
		
		List<Event> events = eventRepository.findByConstraint(eventSearch);
		
		if(events ==  null && events.size() <= 0){
			return Response.status(Status.NOT_FOUND).build();
		}
		
		return Response.ok().entity(new GenericEntity<List<Event>>(events){}).build();
	}
}
