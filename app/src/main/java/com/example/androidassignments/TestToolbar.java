package com.example.androidassignments;

import android.content.DialogInterface;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TestToolbar extends AppCompatActivity {

    private String message = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_toolbar);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    public boolean onCreateOptionsMenu(Menu m){
        getMenuInflater().inflate(R.menu.toolbar_menu,m);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem mi) {
        final EditText messageEditText = new EditText(this);

        switch(mi.getItemId()){
            case R.id.action_one:
                Log.d("Toolbar","Option 1 selected");
                if(message != "") { //if user sent a new message display it in Snackbar
                    Snackbar.make(this.findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT).show();
                }
                else { //if no new message display original
                    Snackbar.make(this.findViewById(android.R.id.content), "You selected item 1", Snackbar.LENGTH_SHORT).show();
                }
                break;
            case R.id.action_two:
                Log.d("Toolbar","Option 2 selected");
//                Snackbar.make(this.findViewById(android.R.id.content), "You selected item 2",Snackbar.LENGTH_SHORT).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(TestToolbar.this);
                builder.setTitle(R.string.goback);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish(); //user clicked ok to go back
                    }
                });
                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //user clicked cancel
                    }
                });
                // create AlertDialog
                AlertDialog dialog = builder.create();
                dialog.show();
                break;
            case R.id.action_three:
                Log.d("Toolbar","Option 3 selected");
//                Snackbar.make(this.findViewById(android.R.id.content), "You selected item 3",Snackbar.LENGTH_SHORT).show();
                AlertDialog.Builder builder1 = new AlertDialog.Builder(TestToolbar.this);
                builder1.setTitle(R.string.sendmessage);
                LayoutInflater inflater = TestToolbar.this.getLayoutInflater();
                builder1.setView(inflater.inflate(R.layout.activity_test_toolbar,null));
                builder1.setView(messageEditText);
                builder1.setIcon(R.drawable.ic_macaron);

                builder1.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        message = messageEditText.getText().toString(); //user gave a new message
                    }
                });
                builder1.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // user clicked cancel
                    }
                });
                // create AlertDialog
                AlertDialog dialog1 = builder1.create();
                dialog1.show();
                break;
            case R.id.aboutMenuItem:
                Toast.makeText(getApplicationContext(), "Version 1.0, by Christine Au-yeung", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}