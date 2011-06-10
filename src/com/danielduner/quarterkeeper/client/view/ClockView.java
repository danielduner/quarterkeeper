package com.danielduner.quarterkeeper.client.view;

import com.danielduner.quarterkeeper.client.presenter.ClockPresenter;
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

public class ClockView extends Composite implements ClockPresenter.ClockDisplay{
	
	private static ClockViewUiBinder uiBinder = GWT.create(ClockViewUiBinder.class);
	interface ClockViewUiBinder extends UiBinder<Widget, ClockView> {}
	interface BackgroundStyle extends CssResource {
		String northRed();
		String centerRed();
		String southRed();

		String northGreen();
		String centerGreen();
		String southGreen();
	}
	
	private Audio audioAlertBreak, audioAlertWork;
	
	@UiField Label header;
	@UiField Label centerLabel;
	@UiField Label footer;
	@UiField BackgroundStyle style;
	
	@Inject
	protected ClockView(EventBus eventBus) {
		initiateSounds();
		initWidget(uiBinder.createAndBindUi(this));
		setSize("100%", "100%");
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
	
	public void startWork(){
		if(Audio.isSupported()){
			audioAlertWork.play();
		}
		centerLabel.setText("YOU ARE WORKING!");
		setGreenBackground();
	}
	
	public void startBreak(){
		if(Audio.isSupported()){
			audioAlertBreak.play();
		}
		centerLabel.setText("YOU ARE HAVING A BREAK!");
		setRedBackground();
	}

	private void setGreenBackground(){
		header.getElement().removeClassName(style.northRed());
		header.getElement().addClassName(style.northGreen());
		centerLabel.getElement().removeClassName(style.centerRed());
		centerLabel.getElement().addClassName(style.centerGreen());
		footer.getElement().removeClassName(style.southRed());
		footer.getElement().addClassName(style.southGreen());
	}
	
	private void setRedBackground(){
		header.getElement().removeClassName(style.northGreen());
		header.getElement().addClassName(style.northRed());
		centerLabel.getElement().removeClassName(style.centerGreen());
		centerLabel.getElement().addClassName(style.centerRed());
		footer.getElement().removeClassName(style.southGreen());
		footer.getElement().addClassName(style.southRed());
	}
	
	public void setClock(String time) {
		footer.setText(time);
	}
	
	public Widget asWidget() {
		return this;
	}
}
