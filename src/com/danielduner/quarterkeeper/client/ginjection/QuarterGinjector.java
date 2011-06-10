package com.danielduner.quarterkeeper.client.ginjection;

import com.danielduner.quarterkeeper.client.AppController;
import com.danielduner.quarterkeeper.client.presenter.ClockPresenter;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

@GinModules(QuarterClientModule.class)
public interface QuarterGinjector extends Ginjector{
	AppController getAppController();
	ClockPresenter getClockPresenter();
}
