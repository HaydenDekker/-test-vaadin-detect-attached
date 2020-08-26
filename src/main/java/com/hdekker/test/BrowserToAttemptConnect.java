package com.hdekker.test;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.Route;

@Push
@Route("test-attempt-connect")
public class BrowserToAttemptConnect extends VerticalLayout implements AfterNavigationObserver{

	Logger log = LoggerFactory.getLogger(BrowserToAttemptConnect.class);
	
	@Autowired
	UIReferenceManager refManager;

	Label uiNum = new Label("UI num");
	
	Button checkIfThere = new Button("Check if there");
	
	TextField tf = new TextField("Is attached");
	
	@Override
	public void afterNavigation(AfterNavigationEvent event) {
		
		checkIfThere.addClickListener((e)-> {
			
			refManager.isComponentAttachedConsumer.ifPresent((checker)->{
				
				checker.accept(()->{	
					tf.getUI().get().access(()->{
						
						tf.setValue("true");
						tf.getUI().get().push();
						
					});		
				}, ()->{			
					tf.getUI().get().access(()->{
						
						tf.setValue("false");
						tf.getUI().get().push();
						
					});		
				});
				
				
			});
			
			
		});
		
		add(uiNum, checkIfThere, tf);
	}
	
}
