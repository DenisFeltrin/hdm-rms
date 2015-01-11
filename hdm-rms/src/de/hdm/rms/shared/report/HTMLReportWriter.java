package de.hdm.rms.shared.report;

public class HTMLReportWriter {
	
	  public String getHeader() {
		    StringBuffer result = new StringBuffer();

		    result.append("<html><head><title></title></head><body>");
		    return result.toString();
		  }
	  
	  public String getReportText() {
		    return this.getHeader() + this.reportText;
		  }
	  
	  public void process(ReportOne r) {
		  
	  }
	  
	  public void process(ReportTwo r) {
		  
	  }

}
