package com.example.androidassignments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ChatWindow extends AppCompatActivity {

    ListView listview;
    EditText edittext;
    Button button;
    ArrayList<String> chatMessages = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);
        listview = findViewById(R.id.chatView);
        edittext = findViewById(R.id.editTextChatWindow);
        button = findViewById(R.id.sendButton);

        final ChatAdapter messageAdapter = new ChatAdapter(this);
        listview.setAdapter(messageAdapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String chat = edittext.getText().toString();
                chatMessages.add(chat);
                messageAdapter.notifyDataSetChanged();
                edittext.setText("");
            }
        });

    }

    private class ChatAdapter extends ArrayAdapter<String>{
        public ChatAdapter(Context ctx){
            super(ctx,0);
        }
        public int getCount(){ //return number of rows in listView
            return chatMessages.size();
        }
        public String getitem(int position){ //return item to show in the list at specified position
            return chatMessages.get(position);
        }
        public View getView(int position, View convertView, ViewGroup parent){ //return layout that will be position at specified row
            LayoutInflater inflater = ChatWindow.this.getLayoutInflater();
            View result = null;
            if(position%2 == 0){
                result = inflater.inflate(R.layout.chat_row_incoming,null);
            }
            else{
                result = inflater.inflate(R.layout.chat_row_outgoing,null);
            }
            TextView message = (TextView) result.findViewById(R.id.message_text);
            message.setText(getitem(position));
            return result;
        }
    }

}