package com.danielduner.quarterkeeper.client.ginjection;

import com.danielduner.quarterkeeper.client.AppController;
import com.danielduner.quarterkeeper.client.model.TimeModel;
import com.danielduner.quarterkeeper.client.presenter.ClockPresenter.ClockDisplay;
import com.danielduner.quarterkeeper.client.view.ClockView;
import com.danielduner.quarterkeeper.client.view.ClockViewImpl;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

public class QuarterClientModule extends AbstractGinModule {
	protected void configure() {
		bind(AppController.class).in(Singleton.class);
		bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
		bind(ClockView.class).to(ClockViewImpl.class).in(Singleton.class);
		bind(ClockDisplay.class).to(ClockView.class);
		bind(TimeModel.class).in(Singleton.class);
	}
}