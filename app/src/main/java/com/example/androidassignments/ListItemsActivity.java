package com.example.androidassignments;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ImageButton;

import java.text.BreakIterator;
import java.util.List;

public class ListItemsActivity extends AppCompatActivity {

    protected static final String ACTIVITY_NAME = "ListItemsActivity";
    private ImageButton imagebutton;
    private Switch s;
    private CheckBox checkBox;
    private final static int REQUEST_IMAGE_CAPTURE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_items);
        Log.i(ACTIVITY_NAME, "In onCreate()");

        imagebutton = (ImageButton) findViewById(R.id.imageButton);
        s = (Switch) findViewById(R.id.switch1);
        checkBox = (CheckBox) findViewById(R.id.checkBox);

        imagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                try {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                } catch (ActivityNotFoundException e) {
                    Log.i(ACTIVITY_NAME, "Error");
                }
            }
        });

        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Context context = getApplicationContext();
                if(b){
                    int text = R.string.switchon;
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context,text,duration);
                    toast.show();
                } else {
                    int text = R.string.switchoff;
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context,text,duration);
                    toast.show();
                }
            }
        });

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                AlertDialog.Builder builder = new AlertDialog.Builder(ListItemsActivity.this);
                builder.setMessage(R.string.dialog)
                .setTitle(R.string.dialog)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // User clicked OK button
                        Intent resultIntent = new Intent(  );
                        resultIntent.putExtra("Response", "ListItemsActivity passed: My information to share");
                        setResult(Activity.RESULT_OK, resultIntent);
                        finish();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // User cancelled the dialog
                    }
                })
                .show();
                }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imagebutton = (ImageButton) findViewById(R.id.imageButton);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imagebutton.setImageBitmap(imageBitmap);
        }
    }

    public void onResume(){
        super.onResume();
        Log.i(ACTIVITY_NAME, "In onResume()");
    }
    public void onStart(){
        super.onStart();
        Log.i(ACTIVITY_NAME, "In onStart()");
    }
    public void onPause(){
        super.onPause();
        Log.i(ACTIVITY_NAME, "In onPause()");
    }
    public void onStop(){
        super.onStop();
        Log.i(ACTIVITY_NAME, "In onStop()");
    }
    public void onDestroy(){
        super.onDestroy();
        Log.i(ACTIVITY_NAME, "In onDestroy()");
    }
}