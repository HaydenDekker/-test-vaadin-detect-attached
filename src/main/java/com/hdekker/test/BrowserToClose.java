package com.hdekker.test;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

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
	
	Boolean clientHasResponding = false;
	
	@ClientCallable
    private void receivePresenceCheck() {
		clientHasResponding = true;
    }
	
	private void checkIfPresent() {
		
		clientHasResponding = false;
		UI ui = this.getUI().get();
		ui.access(()->{
			 getElement().executeJs("this.$server.receivePresenceCheck()", "");
			 ui.push();
		});
	   
	}
	
	public void isComponentReachable(Runnable canReach, Runnable unreachable) {
		
		checkIfPresent();
		CompletableFuture.runAsync(()->{
			
			int numOfAttempts = 10;
			int timemsBetweenAttempts = 1000;
			for(int i = 0; i < numOfAttempts; i++) {
				if(clientHasResponding) {
					canReach.run();
					return;
				} else {

				}
				try {
					Thread.sleep(timemsBetweenAttempts);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			unreachable.run();
		});
		
	}
	
	public BrowserToClose() {
		add(new Label("Open Browser to attempt connection first, then close"));
	}

	@Override
	public void afterNavigation(AfterNavigationEvent event) {
		
		uiRef.isComponentAttachedConsumer = Optional.of(this::isComponentReachable);
		
	}
	
	
}
