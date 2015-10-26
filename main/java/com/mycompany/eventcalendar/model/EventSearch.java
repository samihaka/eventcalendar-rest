package com.mycompany.eventcalendar.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class EventSearch {
	private List<String> names;
	private EventSearchType searchType;
	
	public EventSearchType getSearchType() {
		return searchType;
	}

	public void setSearchType(EventSearchType searchType) {
		this.searchType = searchType;
	}

	public List<String> getNames() {
		return names;
	}

	public void setNames(List<String> names) {
		this.names = names;
	}
	
	
}
