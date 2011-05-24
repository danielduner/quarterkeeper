package com.danielduner.quarterkeeper.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.HandlerRegistration;

public interface WorkChangeEventHandler extends EventHandler {
	public void onTimeEvent(WorkChangeEvent event);
}

interface HasTimeEvents{
	public HandlerRegistration addTimeHandler(WorkChangeEventHandler handler);
}