package com.mycompany.eventcalendar.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.mycompany.eventcalendar.model.Event;
import com.mycompany.eventcalendar.repository.GenericRepository;
import com.mycompany.eventcalendar.repository.impl.ListRepositoryImpl;
import com.mycompany.eventcalendar.service.EventPopulationService;

/**
 * This class REST API for Events
 * 
 * @author Sami
 *
 */
@Path("/events")
public class EventResource {

	private GenericRepository<Event> eventRepository = new ListRepositoryImpl<Event>();
	private EventPopulationService populationService;

	public EventResource(){
		populationService = new EventPopulationService();
		populationService.initializeEvents(eventRepository);
	}
	
	/**
	 * Get list of events.
	 * 
	 * @return	List of events
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Event> getAllEvents(){
		List<Event> events = eventRepository.getAllObjects();

		System.out.println("GET all events: "+events);

		return events;
	}
	
	/**
	 * Get specific event.
	 * 
	 * @param eventId	Id of an event.
	 * @return			Requested event.
	 */
	@GET
	@Path("{eventId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEvent(@PathParam("eventId") String eventId){
		Event event;
		
		System.out.println("POST, eventRepository: "+eventRepository.getObjectCount());
		System.out.println("GET with Id: "+eventId);
		
		if(eventId == null){
			return Response.status(Status.BAD_REQUEST).build();
		}
		
		event = eventRepository.getObjectById(Integer.parseInt(eventId));
		
		System.out.println("GET with Id: "+event);
		
		if(event == null){
			return Response.status(Status.NOT_FOUND).build();
		}
		
		return Response.ok().entity(event).build(); 
	}
	
	/**
	 * Get start date for specific event.
	 * 
	 * @param eventId	Id of an event.
	 * @return			Start date for requested event.
	 */
	@GET
	@Path("{eventId}/startDate")
	@Produces(MediaType.APPLICATION_XML)
	public String getEventStartDate(@PathParam("eventId") int eventId){
		Event event = eventRepository.getObjectById(eventId);
		
		System.out.println("GET with startDate: "+event);
		
		return event.getStartDate(); 
	}
	
	/**
	 * POST method using object binding.
	 * 
	 * @param event		Event to add.
	 * @return			Created event.
	 */
	@POST
	@Path("event")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public Response createEvent(Event event){
		System.out.println("POST: "+event);
	
		if(event == null){
			return Response.status(Status.BAD_REQUEST).build();
		}
		else{
			Event newEvent = eventRepository.addObject(event);

			return Response.ok().entity(newEvent).build(); 
		}
	}
	
	/**
	 * Method for updating existing event or creating new one, if event doesn't exist.
	 * 
	 * @param event		Updated event.
	 * @return			Updated event.
	 */
	@PUT
	@Path("{eventId}")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public Response updateEvent(Event event){
		eventRepository.updateObject(event);
		
		System.out.println("PUT: "+event);
		return Response.ok().entity(event).build();
	}
	
	@DELETE
	@Path("{eventId}")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public Response deleteEvent(@PathParam("eventId") int eventId){
		Event event = eventRepository.deleteObject(eventId);
		
		System.out.println("DELETE with Id: "+eventId);
		return Response.ok().entity(event).build();
	}

}
