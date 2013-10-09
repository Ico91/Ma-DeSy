package madesy.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Report {
	private String id;
	private List<ReportType> report;
	private Map<String, Integer> courrierInfo;
	private Date fromDate;
	private Date toDate;
	
	public Report(String id, Date fromDate, Date toDate) {
		this.id = id;
		this.fromDate = fromDate;
		this.toDate = toDate;
		report = new ArrayList<ReportType>();
		courrierInfo = new HashMap<String, Integer>();
	}

	@Override
	public String toString() {
		return "Report [id=" + id + ", report=" + report + ", courrierInfo="
				+ courrierInfo + ", fromDate=" + fromDate + ", toDate="
				+ toDate + "]";
	}
	
	public void addReportElement(ReportType element) {
		this.report.add(element);
	}
	
	@Override
	public int hashCode() {
		return id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Report other = (Report) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<ReportType> getReport() {
		return report;
	}

	public Map<String, Integer> getCourrierInfo() {
		return courrierInfo;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

}
