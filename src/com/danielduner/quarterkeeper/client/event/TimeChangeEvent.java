package com.danielduner.quarterkeeper.client.event;

import com.google.web.bindery.event.shared.Event;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;

public class TimeChangeEvent extends Event<TimeChangeEventHandler>{
	public static final Type<TimeChangeEventHandler> TYPE = new Type<TimeChangeEventHandler>();

	public TimeChangeEvent() {
	}
	
	@Override
	public Event.Type<TimeChangeEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(TimeChangeEventHandler handler) {
		handler.onTimeChangeEvent(this);
	}

	public static HandlerRegistration register(EventBus eventBus, TimeChangeEventHandler eventHandler){
		return eventBus.addHandler(TYPE, eventHandler);
	}
}
