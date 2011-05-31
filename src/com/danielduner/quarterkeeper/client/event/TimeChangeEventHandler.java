package com.danielduner.quarterkeeper.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.HandlerRegistration;

public interface TimeChangeEventHandler extends EventHandler {
	public void onTimeChangeEvent(TimeChangeEvent event);
}

interface HasTimeEvents{
	public HandlerRegistration addTimeHandler(TimeChangeEventHandler handler);
}