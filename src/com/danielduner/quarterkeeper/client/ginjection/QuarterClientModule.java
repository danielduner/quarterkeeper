package com.danielduner.quarterkeeper.client.ginjection;

import com.danielduner.quarterkeeper.client.TimeModel;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

public class QuarterClientModule extends AbstractGinModule {
	protected void configure() {
		bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
		bind(TimeModel.class).in(Singleton.class);
	}
}