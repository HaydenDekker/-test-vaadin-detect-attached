package com.hdekker.test;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.springframework.stereotype.Component;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.spring.annotation.VaadinSessionScope;

@Component
@VaadinSessionScope
public class UIReferenceManager { 

	public Optional<BiConsumer<Runnable, Runnable>> isComponentAttachedConsumer = Optional.empty();
}
