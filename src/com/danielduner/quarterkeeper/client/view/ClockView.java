package com.danielduner.quarterkeeper.client.view;

import com.google.gwt.user.client.ui.Widget;

public interface ClockView{
	
	public interface Presenter{
		/*
		 * add methods that the view can
		 * call on events, for example:
		 * 
		 * void onAddButtonClicked();
	     * void onDeleteButtonClicked();
		 */
	}
	
	void startWork();
	void startBreak();
	void setClock(String time);
	Widget asWidget();
}
