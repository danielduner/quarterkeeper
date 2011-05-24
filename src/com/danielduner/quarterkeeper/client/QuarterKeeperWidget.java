package com.danielduner.quarterkeeper.client;

import com.danielduner.quarterkeeper.client.event.WorkChangeEvent;
import com.danielduner.quarterkeeper.client.event.WorkChangeEventHandler;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.MediaElement;
import com.google.gwt.media.client.Audio;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;

public class QuarterKeeperWidget extends Composite implements WorkChangeEventHandler{
	
	private static QuarterKeeperWidgetUiBinder uiBinder = GWT.create(QuarterKeeperWidgetUiBinder.class);
	interface QuarterKeeperWidgetUiBinder extends UiBinder<Widget, QuarterKeeperWidget> {}
	
	private Audio audioAlertBreak, audioAlertWork;
	//private final SimplePanel mainPanel = new SimplePanel();
	
	@Inject
	public QuarterKeeperWidget(TimeModel timeModel, EventBus eventBus) {
		WorkChangeEvent.register(eventBus, this);
		initiateSounds();
		initWidget(uiBinder.createAndBindUi(this));
		setSize("100%", "100%"); //have to set size or else everything will be hidden
		if(TimeModel.isWorking()){
			startWork();
		}else{
			startBreak();
		}
	}

	@Override
	public void onTimeEvent(WorkChangeEvent event) {
		if(event.startWorking()){
			startWork();
		}else{
			startBreak();
		}
	}
	
	private void initiateSounds(){
		if(Audio.isSupported()){
			audioAlertBreak = Audio.createIfSupported();
			audioAlertBreak.setSrc("http://upload.wikimedia.org/wikipedia/commons/1/10/En-us-pause.ogg");
			audioAlertBreak.setPreload(MediaElement.PRELOAD_AUTO);
			
			audioAlertWork = Audio.createIfSupported();
			audioAlertWork.setSrc("http://upload.wikimedia.org/wikipedia/commons/7/76/En-us-working.ogg");
			audioAlertWork.setPreload(MediaElement.PRELOAD_AUTO);
		}else{
			//TODO display some warning
		}
	}
	
	private void startWork(){
		audioAlertWork.play();
		//mainPanel.setWidget(new Label("YOU ARE WORKING!"));
	}
	
	private void startBreak(){
		audioAlertBreak.play();
		//mainPanel.setWidget(new Label("YOU ARE HAVING A BREAK!"));
	}
}
