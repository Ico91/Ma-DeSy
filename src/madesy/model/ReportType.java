package madesy.model;

public enum ReportType {
	NOT_MANY_NEW_PICKINGS("The new pickings are dispatched properly."),
	TOO_MANY_NEW_PICKINGS("There are too many new pickings that the courriers have not dispatched them yet."),
	NOT_MANY_DISPATCHED_PICKINGS("The dispatched pickings are taken properly."),
	TOO_MANY_DISPATCHED_PICKINGS("Couriers are delayed in passing the pickings.");
	
	private String value;
	
	private ReportType(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
