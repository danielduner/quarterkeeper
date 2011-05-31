package com.danielduner.quarterkeeper.client.event;

import com.google.web.bindery.event.shared.Event;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;

public class WorkChangeEvent extends Event<WorkChangeEventHandler>{
	public static final Type<WorkChangeEventHandler> TYPE = new Type<WorkChangeEventHandler>();
	private final boolean working;
	
	public WorkChangeEvent(boolean working) {
		this.working = working;
	}
	
	@Override
	public Event.Type<WorkChangeEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(WorkChangeEventHandler handler) {
		handler.onWorkChangeEvent(this);
	}

	public static HandlerRegistration register(EventBus eventBus, WorkChangeEventHandler eventHandler){
		return eventBus.addHandler(TYPE, eventHandler);
	}
	
	public boolean startWorking(){
		return working;
	}
}
