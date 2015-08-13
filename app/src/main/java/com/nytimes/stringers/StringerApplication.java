package com.nytimes.stringers;

import android.app.Application;

import com.nytimes.stringers.models.Message;
import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParseObject;

/**
 * Created by alexio on 8/11/15.
 */
public class StringerApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // Register your parse models here
        ParseObject.registerSubclass(Message.class);
        Parse.initialize(this, "TQ4PO2mdMvHaO6kRdG6wTaRUpyXUwB9zbsweixBf", "ymd56771SPlaUYyVL1jNqNQYJ9RNaczmUpnN32ZU");
        ParseInstallation.getCurrentInstallation().saveInBackground();
    }
}
