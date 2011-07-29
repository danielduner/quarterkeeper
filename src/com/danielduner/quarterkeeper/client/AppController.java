package com.danielduner.quarterkeeper.client;

import com.danielduner.quarterkeeper.client.ginjection.QuarterGinjector;
import com.danielduner.quarterkeeper.client.presenter.Presenter;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.inject.Inject;

public class AppController implements Presenter, ValueChangeHandler<String>{
	private final QuarterGinjector ginjector;
	
	private HasWidgets container;
	
	@Inject
	protected AppController(QuarterGinjector ginjector) {
		this.ginjector = ginjector;
		bind();
	}
	
	public void bind(){
		History.addValueChangeHandler(this);
	}
	
	public void go(final HasWidgets container){
	    this.container = container;
		if ("".equals(History.getToken())) {
			History.newItem("clock");
		} else {
			History.fireCurrentHistoryState();
		}
	}

	@Override
	public void onValueChange(ValueChangeEvent<String> event) {
		String token = event.getValue();
		if (token != null) {
			Presenter presenter = null;
			
			if (token.equals("clock")) {
				presenter = ginjector.getClockPresenter();
			}else{
				//fallback option
				presenter = ginjector.getClockPresenter();
			}
			
			if (presenter != null) {
				presenter.go(container);
			}
		}
	}
}
