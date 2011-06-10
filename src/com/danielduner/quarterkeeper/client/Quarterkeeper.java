package com.danielduner.quarterkeeper.client;

import com.danielduner.quarterkeeper.client.ginjection.QuarterGinjector;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;

public class Quarterkeeper implements EntryPoint {
	private final QuarterGinjector injector = GWT.create(QuarterGinjector.class);

	public void onModuleLoad() {
		AppController appController = injector.getAppController();
		appController.go(RootPanel.get());
	}
}
