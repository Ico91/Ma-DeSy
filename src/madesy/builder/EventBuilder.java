package madesy.builder;

import java.util.Date;
import java.util.UUID;

import madesy.model.Event;
import madesy.model.EventType;

public class EventBuilder {
	private EventType eventType;
	private String metaData;
	
	public EventBuilder() {
		
	}
	
	public EventBuilder addEvent(EventType eventType) {
		this.eventType = eventType;
		
		return this;
	}
	
	public EventBuilder addMetaData(String metaData) {
		this.metaData = metaData;
		
		return this;
	}
	
	public Event build() {
		Event event = new Event();
		
		event.setEventId(UUID.randomUUID().toString());
		event.setEventType(this.eventType);
		event.setDate( new Date());
		event.setMetaData(this.metaData);
		
		return event;
	}
}
