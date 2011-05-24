package com.danielduner.quarterkeeper.client;

import com.danielduner.quarterkeeper.client.ginjection.QuarterGinjector;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Quarterkeeper implements EntryPoint {
	private final QuarterGinjector injector = GWT.create(QuarterGinjector.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		RootPanel rootPanel = RootPanel.get();
		QuarterKeeperWidget quarterKeeperWidget = injector.getQuarterKeeperWidget();
		rootPanel.add(quarterKeeperWidget);
	}
}
