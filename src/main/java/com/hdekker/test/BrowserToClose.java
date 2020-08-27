package com.hdekker.test;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.ClientCallable;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.PendingJavaScriptResult;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.Route;

@Route
@Push
public class BrowserToClose extends VerticalLayout implements AfterNavigationObserver{

	@Autowired
	UIReferenceManager uiRef;
	
	public class PolledReachable{
		
		UI ui;
		public PolledReachable(UI ui) {
			this.ui = ui;
		}
		
		public void isComponentReachable(Consumer<PolledCallbackEventType> callbacks) {
			
			ui.access(()->{
				
				//"this.$server.receivePresenceCheck()"
				PendingJavaScriptResult resp = 
							getElement()
							.executeJs("console.log('Browser checked to see this UI is still reachable')", "");
				ui.push();
				
				CompletableFuture.runAsync(()->{
			
					int numOfAttempts = 10;
					int timemsBetweenAttempts = 1000;
					for(int i = 0; i < numOfAttempts; i++) {
						// assumption is it will still be pending while
						// Vaadin cannot reach the server
						if(resp.isSentToBrowser()) {
							callbacks.accept(PolledCallbackEventType.CanReach);
							return;
						} else {
							callbacks.accept(PolledCallbackEventType.Degraded);
						}
						try {
							Thread.sleep(timemsBetweenAttempts);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					callbacks.accept(PolledCallbackEventType.Unreachable);
				});
			});
			
		}
		
	}
	
	public BrowserToClose() {
		add(new Label("Open Browser to attempt connection first, then close"));
	}

	@Override
	public void afterNavigation(AfterNavigationEvent event) {
		
		PolledReachable pr = new PolledReachable(this.getUI().get());
		uiRef.isComponentAttachedConsumer = Optional.of(pr::isComponentReachable);
		
	}
	
	
}
