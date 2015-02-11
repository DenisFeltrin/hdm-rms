package de.hdm.rms.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
* Die Klasse Impressum ist notwendig, um nach TMG §5 den Betreiber der Website eindeutig identifizieren zu können.
* Dieses Impressum ist mithilfe eines Buttons von jeder Unterseite der Anwendung aus zu erreichen.
* <p>Im Footer der html-Seite ist ein eigens angelegter Bereich für den Button vorhanden.
* 
* @author Mario Theiler, Denis Feltrin, Björn Zimmermann
* @version 1.0
*/
public class Impressum extends VerticalPanel {
    
	public void onLoad(){
    
    	Button impressumBtn = new Button("Impressum");
    	impressumBtn.setStylePrimaryName("impressumBtn");
        
        final HTML html = new HTML("");
        html.setHTML("<h2>Impressum</h2>"+
        		"<h2>Angaben gemäß § 5 TMG:</h2>"+
        		"<p>Björn Zimmermann<br />"+
        		"Plochingerstr 46<br />"+
        		"73730 Esslingen"+
        		"</p>" +
        		"<h2>Kontakt:</h2>"+
        		"<table><tr>"+
        		"<td>Telefon:</td>"+
        		"<td></td></tr>"+
        		"<tr><td>E-Mail:</td>"+
        		"<td>bjconcepts.de@gmail.com</td>"+
        		"</tr></table>"+
        		"<h2>Verantwortlich für den Inhalt nach § 55 Abs. 2 RStV:</h2>"+
        		"<p>Denis Feltrin<br />"+
        		"Calwerstr 24<br />"+
        		"706556 Calw</p>"
        		);
        
        impressumBtn.addClickHandler(new ClickHandler() {
        	public void onClick(ClickEvent event) {     	
        		RootPanel.get("content_wrap").clear();
        		RootPanel.get("content_wrap").add(html);
        	}
        });
        
        RootPanel.get("Impressum").add(impressumBtn);
        
	}
    
}