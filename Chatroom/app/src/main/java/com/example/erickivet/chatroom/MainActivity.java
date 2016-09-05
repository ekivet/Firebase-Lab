package com.example.erickivet.chatroom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.ui.FirebaseListAdapter;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ListView chatList;
    EditText chatText;
    Button sendButton;

    Firebase fbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chatList = (ListView)findViewById(R.id.chatroom_list);
        chatText = (EditText)findViewById(R.id.chat_text);
        sendButton = (Button)findViewById(R.id.send_button);

        Firebase.setAndroidContext(this);

        final String username = userNameGen();

        setTitle("Chatting as "+ username);

        fbRef = new Firebase("https://chatroom-5206b.firebaseio.com/ChatRoom");

        FirebaseListAdapter<Message> adapter = new FirebaseListAdapter<Message>(
                this, Message.class, android.R.layout.simple_list_item_2,fbRef
        ) {
            @Override
            protected void populateView(View view, Message message, int i) {

                TextView user = (TextView)view.findViewById(android.R.id.text1);
                TextView userMessage = (TextView)view.findViewById(android.R.id.text2);

                user.setText(message.getUser());
                userMessage.setText(message.getMessage());

            }
        };

        chatList.setAdapter(adapter);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fbRef.push().setValue(new Message(username,chatText.getText().toString()));
                chatText.setText("");
            }
        });
    }

    private String userNameGen(){
        Random name = new Random(System.currentTimeMillis());
        return "User"+name.nextInt(1000);
    }
}
