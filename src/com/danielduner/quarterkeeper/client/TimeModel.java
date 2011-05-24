package com.danielduner.quarterkeeper.client;

import java.util.Date;

import com.danielduner.quarterkeeper.client.event.WorkChangeEvent;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.RepeatingCommand;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;

public class TimeModel{
	private final EventBus eventBus;
	
	private boolean wasWorking = isWorking();
	
	@Inject
	public TimeModel(final EventBus eventBus) {
		this.eventBus = eventBus;
		Scheduler.get().scheduleFixedPeriod(new RepeatingCommand() {
			@Override public boolean execute() {
				updateStatus();
				return true;
			}
		}, 10*1000);
		
	}
	
	private void updateStatus(){
		if(wasWorking!=isWorking()){
			wasWorking = isWorking();
			eventBus.fireEventFromSource(new WorkChangeEvent(isWorking()), TimeModel.this);
		}
	}
	
	private static final DateTimeFormat minuteFormat = DateTimeFormat.getFormat("mm");
	private static int getMinutes(){
		//No calendar in GWT
		//return new Date().getMinutes(); //deprecated
		return Integer.parseInt(minuteFormat.format(new Date()));
	}
	
	public static boolean isWorking(){
		return getMinutes()<45;
	}
}
