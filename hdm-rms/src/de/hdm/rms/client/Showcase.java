package de.hdm.rms.client;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;

public abstract class Showcase extends VerticalPanel {
	
	@Override
	public void onLoad()  {
		
		super.onLoad();
		this.run();
	}
	
	
	protected HTML createHeadline(String headlinetext){
		HTML headline = new HTML(headlinetext);
		headline.setStylePrimaryName("bankproject-headline");
		return headline;
	}
	
	
	public abstract String getHeadline();
	public abstract void run();
	
	

}
