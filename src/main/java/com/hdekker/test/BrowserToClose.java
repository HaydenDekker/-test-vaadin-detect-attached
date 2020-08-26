package com.hdekker.test;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.ClientCallable;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.Route;

@Route
@Push
public class BrowserToClose extends VerticalLayout implements AfterNavigationObserver{

	@Autowired
	UIReferenceManager uiRef;
	
	Boolean clientIsResponding = false;
	
	@ClientCallable
    public void receivePresenceCheck() {
		clientIsResponding = true;
    }
	
	public void checkIfPresent() {
		
		clientIsResponding = false;
		UI ui = this.getUI().get();
		ui.access(()->{
			 getElement().executeJs("this.$server.receivePresenceCheck()", "");
			 ui.push();
		});
	   
	}
	
	public BrowserToClose() {
		add(new Label("Open Browser to attempt connection first, then close"));
	}

	@Override
	public void afterNavigation(AfterNavigationEvent event) {
		uiRef.uiToDetectPresence = Optional.of(() -> clientIsResponding);
		uiRef.checkForPresence = Optional.of(() -> {
			checkIfPresent();
		});
		
	}
	
	
}
