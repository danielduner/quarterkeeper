package com.danielduner.quarterkeeper.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.HandlerRegistration;

public interface WorkChangeEventHandler extends EventHandler {
	public void onWorkChangeEvent(WorkChangeEvent event);
}

interface HasWorkEvents{
	public HandlerRegistration addWorkHandler(WorkChangeEventHandler handler);
}