package madesy.model;

import java.util.Date;

import madesy.model.types.EventType;

public class Event {
	private String eventId;
	private EventType eventType;
	private Date date;
	private String metaData;
	
	public Event() {
		
	}
	
	public Event(String eventId, EventType eventType, Date date, String metaData) {
		this.eventId = eventId;
		this.eventType = eventType;
		this.date = date;
		this.metaData = metaData;
	}

	@Override
	public String toString() {
		return "Event [eventId=" + eventId + ", eventType=" + eventType + ", date="
				+ date + ", metaData=" + metaData + "]";
	}

	@Override
	public int hashCode() {
		return eventId.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		if (eventId != other.eventId)
			return false;
		return true;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getMetaData() {
		return metaData;
	}

	public void setMetaData(String metaData) {
		this.metaData = metaData;
	}	
	
}
