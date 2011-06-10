package com.danielduner.quarterkeeper.client.presenter;

import com.danielduner.quarterkeeper.client.event.TimeChangeEvent;
import com.danielduner.quarterkeeper.client.event.TimeChangeEventHandler;
import com.danielduner.quarterkeeper.client.event.WorkChangeEvent;
import com.danielduner.quarterkeeper.client.event.WorkChangeEventHandler;
import com.danielduner.quarterkeeper.client.model.TimeModel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;

public class ClockPresenter implements Presenter, TimeChangeEventHandler, WorkChangeEventHandler{
	
	private final EventBus eventBus;
	private final TimeModel model;
	private final ClockDisplay view;
	
	public interface ClockDisplay {
		void startWork();
		void startBreak();
		void setClock(String time);
		Widget asWidget();
	}
	
	@Inject
	protected ClockPresenter(EventBus eventBus, TimeModel model, ClockDisplay view) {
		this.eventBus = eventBus;
		this.model = model;
		this.view = view;
	}
	
	public void bind(){
		TimeChangeEvent.register(eventBus, this);
		WorkChangeEvent.register(eventBus, this);
	}
	 
	@Override
	public void go(final HasWidgets container) {
		bind();
	    container.clear();
	    container.add(view.asWidget());
	}

	@Override
	public void onWorkChangeEvent(WorkChangeEvent event) {
		if(model.isWorking()){
			view.startWork();
		}else{
			view.startBreak();
		}
	}

	@Override
	public void onTimeChangeEvent(TimeChangeEvent event) {
		view.setClock(model.getTimeLeftToChangeString());
	}

}
