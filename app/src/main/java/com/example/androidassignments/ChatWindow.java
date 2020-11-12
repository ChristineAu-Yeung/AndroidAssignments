package com.example.androidassignments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
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

import static com.example.androidassignments.ChatDatabaseHelper.TABLE_NAME;

public class ChatWindow extends AppCompatActivity {

    private static final String ACTIVITY_NAME = "ChatWindow";
    ListView listview;
    EditText edittext;
    Button button;
    ArrayList<String> chatMessages = new ArrayList<String>();
    static SQLiteDatabase db;
    static final String GET_MESSAGES = "SELECT KEY_M FROM MESSAGES";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);
        listview = findViewById(R.id.chatView);
        edittext = findViewById(R.id.editTextChatWindow);
        button = findViewById(R.id.sendButton);

        final ChatAdapter messageAdapter = new ChatAdapter(this);
        listview.setAdapter(messageAdapter);

        ChatDatabaseHelper dbHelper = new ChatDatabaseHelper(this);
        db = dbHelper.getReadableDatabase();
//        Cursor cursor = db.rawQuery(GET_MESSAGES,null);
        String[] cols = {ChatDatabaseHelper.KEY_MESSAGE};
        Cursor cursor = db.query(TABLE_NAME, cols,null,null,null,null,null);
        cursor.moveToFirst();

        chatMessages = new ArrayList<>();

        while(!cursor.isAfterLast()){
            Log.i(ACTIVITY_NAME, GET_MESSAGES + cursor.getString(cursor.getColumnIndex(ChatDatabaseHelper.KEY_MESSAGE)));
            chatMessages.add(cursor.getString(cursor.getColumnIndex(ChatDatabaseHelper.KEY_MESSAGE)));
            cursor.moveToNext();
        }
        Log.i(ACTIVITY_NAME, "Cursor’s  column count =" + cursor.getColumnCount());

        for(int i = 0; i < cursor.getColumnCount(); i++){
            Log.i(ACTIVITY_NAME, "Cursor's column name: " + cursor.getColumnName(i));
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String chat = edittext.getText().toString();
                chatMessages.add(chat);
                ContentValues messageToAdd = new ContentValues();
                messageToAdd.put(ChatDatabaseHelper.KEY_MESSAGE,chat);
                db.insert(TABLE_NAME, "Null",messageToAdd);
                messageAdapter.notifyDataSetChanged();
                edittext.setText("");
            }
        });
    }

    class ChatAdapter extends ArrayAdapter<String>{
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

    public void onDestroy(){
        super.onDestroy();
        db.close();
        Log.i(ACTIVITY_NAME, "In onDestroy()");
    }
}