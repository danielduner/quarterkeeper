package com.danielduner.quarterkeeper.client.model;

import java.util.Date;

import com.danielduner.quarterkeeper.client.event.TimeChangeEvent;
import com.danielduner.quarterkeeper.client.event.WorkChangeEvent;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.RepeatingCommand;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;

public class TimeModelImpl implements TimeModel{
	private final EventBus eventBus;
	
	private static final int breakStartMinute = 45;
	
	private boolean wasWorking = isWorking();
	private boolean firstUpdate = true;
	
	@Inject
	protected TimeModelImpl(final EventBus eventBus) {
		this.eventBus = eventBus;
		Scheduler.get().scheduleFixedPeriod(new RepeatingCommand() {
			@Override public boolean execute() {
				eventBus.fireEventFromSource(new TimeChangeEvent(), TimeModelImpl.this);
				updateStatus();
				return true;
			}
		}, 1*1000);
	}
	
	private void updateStatus(){
		if(wasWorking!=isWorking() || firstUpdate){
			wasWorking = isWorking();
			eventBus.fireEventFromSource(new WorkChangeEvent(), TimeModelImpl.this);
			firstUpdate = false;
		}
	}
	
	private static DateTimeFormat minuteFormat = DateTimeFormat.getFormat("mm");
	private static int getMinutes(){
		return Integer.parseInt(minuteFormat.format(new Date()));
	}
	
	private static DateTimeFormat secondFormat = DateTimeFormat.getFormat("ss");
	private static int getSeconds(){
		return Integer.parseInt(secondFormat.format(new Date()));
	}
	
	@Override
	public String getTimeLeftToChangeString(){
		int minutesLeft;
		if(isWorking()){
			minutesLeft = breakStartMinute - getMinutes() - 1;
		}else{
			minutesLeft = 60 - getMinutes() - 1;
		}
		int secondsLeft;
		secondsLeft = 60 - getSeconds();
		if(secondsLeft==60){
			minutesLeft++;
			secondsLeft=0;
		}
		return (minutesLeft<10?"0":"")+minutesLeft+":"+(secondsLeft<10?"0":"")+secondsLeft;
	}
	
	@Override
	public boolean isWorking(){
		return 0<=getMinutes() && getMinutes()<breakStartMinute;
	}
}
