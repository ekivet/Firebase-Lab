package com.example.erickivet.chatroom;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by erickivet on 9/5/16.
 */
public class ChatroomApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Firebase.setAndroidContext(this);

    }
}
