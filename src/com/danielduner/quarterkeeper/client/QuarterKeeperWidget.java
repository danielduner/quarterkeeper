package com.danielduner.quarterkeeper.client;


import com.danielduner.quarterkeeper.client.event.TimeChangeEvent;
import com.danielduner.quarterkeeper.client.event.TimeChangeEventHandler;
import com.danielduner.quarterkeeper.client.event.WorkChangeEvent;
import com.danielduner.quarterkeeper.client.event.WorkChangeEventHandler;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.MediaElement;
import com.google.gwt.media.client.Audio;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;

public class QuarterKeeperWidget extends Composite implements WorkChangeEventHandler, TimeChangeEventHandler{
	
	private static QuarterKeeperWidgetUiBinder uiBinder = GWT.create(QuarterKeeperWidgetUiBinder.class);
	interface QuarterKeeperWidgetUiBinder extends UiBinder<Widget, QuarterKeeperWidget> {}
	interface BackgroundStyle extends CssResource {
		String northRed();
		String centerRed();
		String southRed();
		String westRed();
		String eastRed();

		String northGreen();
		String centerGreen();
		String southGreen();
		String westGreen();
		String eastGreen();
	}
	
	private Audio audioAlertBreak, audioAlertWork;
	//private final SimplePanel mainPanel = new SimplePanel();
	
	@UiField Label northLabel;
	@UiField Label centerLabel;
	@UiField Label southLabel;
	@UiField Label westLabel;
	@UiField Label eastLabel;
	@UiField BackgroundStyle style;
	
	@Inject
	public QuarterKeeperWidget(TimeModel timeModel, EventBus eventBus) {
		WorkChangeEvent.register(eventBus, this);
		TimeChangeEvent.register(eventBus, this);
		initiateSounds();
		initWidget(uiBinder.createAndBindUi(this));
		setSize("100%", "100%"); //have to set size or else everything will be hidden
		if(TimeModel.isWorking()){
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
		centerLabel.setText("YOU ARE WORKING!");
		setGreenBackground();
	}
	
	private void startBreak(){
		audioAlertBreak.play();
		centerLabel.setText("YOU ARE HAVING A BREAK!");
		setRedBackground();
		
	}

	private void setGreenBackground(){
		northLabel.getElement().removeClassName(style.northRed());
		northLabel.getElement().addClassName(style.northGreen());
		centerLabel.getElement().removeClassName(style.centerRed());
		centerLabel.getElement().addClassName(style.centerGreen());
		southLabel.getElement().removeClassName(style.southRed());
		southLabel.getElement().addClassName(style.southGreen());
		westLabel.getElement().removeClassName(style.westRed());
		westLabel.getElement().addClassName(style.westGreen());
		eastLabel.getElement().removeClassName(style.eastRed());
		eastLabel.getElement().addClassName(style.eastGreen());
	}
	
	private void setRedBackground(){
		northLabel.getElement().removeClassName(style.northGreen());
		northLabel.getElement().addClassName(style.northRed());
		centerLabel.getElement().removeClassName(style.centerGreen());
		centerLabel.getElement().addClassName(style.centerRed());
		southLabel.getElement().removeClassName(style.southGreen());
		southLabel.getElement().addClassName(style.southRed());
		westLabel.getElement().removeClassName(style.westGreen());
		westLabel.getElement().addClassName(style.westRed());
		eastLabel.getElement().removeClassName(style.eastGreen());
		eastLabel.getElement().addClassName(style.eastRed());
	}
	
	@Override
	public void onWorkChangeEvent(WorkChangeEvent event) {
		if(event.startWorking()){
			startWork();
		}else{
			startBreak();
		}
	}

	@Override
	public void onTimeChangeEvent(TimeChangeEvent event) {
		southLabel.setText("Time left until "+(TimeModel.isWorking()?"break":"work")+": " + event.getTimeLeft());
	}
}
