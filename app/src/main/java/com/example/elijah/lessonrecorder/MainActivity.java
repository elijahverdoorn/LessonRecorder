package com.example.elijah.lessonrecorder;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;

import static com.example.elijah.lessonrecorder.R.menu.menu_main;

public class MainActivity extends ActionBarActivity {

    EditText fileNameField;
    DBTools dbTools = new DBTools(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button recordButton = (Button) findViewById(R.id.button);
        fileNameField   = (EditText) findViewById(R.id.editText);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id)
        {
            case R.id.action_view:
                listAllFiles();
                return true;
            case R.id.action_settings:
                showSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showSettings() {
        Intent theIntent = new Intent(getApplication(), SettingsActivity.class);
        startActivity(theIntent);
    }

    private void listAllFiles() {
        Intent theIntent = new Intent(getApplication(), ListFiles.class);
        startActivity(theIntent);
    }

    public void buttonRecordClicked(View v)
    {
        fileNameField = (EditText) findViewById(R.id.editText);
        startRecording(String.valueOf(fileNameField.getText()));
    }

    // start the recording by passing in the filename
    public void startRecording(String fileName)
    {
        Intent i = new Intent(getApplicationContext(), RecordingActivity.class);
        i.putExtra("fileName", fileName);
        HashMap<String, String> trackMap = new HashMap<String, String>();
        trackMap.put("fileName", fileName);
        dbTools.insertTrack(trackMap);
        //TODO: uncomment next line
        startActivity(i);
    }
}