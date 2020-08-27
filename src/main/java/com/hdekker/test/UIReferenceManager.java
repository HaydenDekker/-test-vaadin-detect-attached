package com.hdekker.test;

import java.util.Optional;
import java.util.function.Consumer;

import org.springframework.stereotype.Component;

import com.vaadin.flow.spring.annotation.VaadinSessionScope;

@Component
@VaadinSessionScope
public class UIReferenceManager { 

	public Optional<Consumer<Consumer<PolledCallbackEventType>>> isComponentAttachedConsumer = Optional.empty();
}
