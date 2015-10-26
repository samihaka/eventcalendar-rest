package com.mycompany.eventcalendar.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class represents event.
 * 
 * @author Sami
 *
 */
@XmlRootElement
public class Event extends AbstractDataObject implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String description;
	private String startDate;
	private String endDate;
	
	public Event(){	
	}
	
	public Event(int id, String name, String description, LocalDate startDate, LocalDate endDate){
		super.setId(id);
		super.setName(name);
		this.description = description;
		this.startDate = startDate.toString();
		this.endDate = endDate.toString();
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStartDate() {
		return startDate;
	}
	
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	public String getEndDate() {
		return endDate;
	}
	
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	public String toString(){
		return "Id: "+super.getId()+" Name: "+super.getName()+" Description: "+this.description+ " Start: "+this.startDate+" End: "+this.getEndDate();
	}
}
