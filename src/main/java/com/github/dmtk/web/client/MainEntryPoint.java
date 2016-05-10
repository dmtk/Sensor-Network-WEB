package com.github.dmtk.web.client;



import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.TextArea;
import com.github.dmtk.web.client.sampleservice.GWTService;
import com.github.dmtk.web.client.sampleservice.GWTServiceAsync;

public class MainEntryPoint implements EntryPoint {

    
    public MainEntryPoint() {
    }

    public static GWTServiceAsync getService() {
       

        return GWT.create(GWTService.class);
    }

    Integer i=0;
    
    public void onModuleLoad() {
        final Label quoteText = new Label();
        final TextArea t= new TextArea();
        CheckBox ch = new CheckBox("536 ");
        ch.setText("Text 1");
        ch.addClickHandler(new ClickHandler(){

            public void onClick(ClickEvent event) {
                i++;
                t.setText("Text click "+i);
            }
        });

        Timer timer = new Timer() {

            public void run() {
                //create an async callback to handle the result:
                AsyncCallback callback = new AsyncCallback() {

                    public void onFailure(Throwable arg0) {
                        //display error text if we can't get the quote:
                        quoteText.setText("Failed to get a quote");
                    }

            public void onSuccess(Object result) {
                //display the retrieved quote in the label:
                quoteText.setText((String) result);
                quoteText.setStyleName("quoteLabel");
            }
        };
        getService().myMethod(callback);
            }
        };

        timer.scheduleRepeating(1000);
        RootPanel.get().add(quoteText);
        RootPanel.get().add(ch);
        RootPanel.get().add(t);

    }
}
