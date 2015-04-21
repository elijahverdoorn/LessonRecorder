package com.example.elijah.lessonrecorder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.HashMap;


public class EditTrack extends Activity {

    EditText trackName;

    DBTools dbTools = new DBTools(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_track);

        trackName = (EditText) findViewById(R.id.trackNameEditText);
    }

    public void editTheTrack(View view)
    {
        HashMap<String, String> queryValuesMap = new HashMap<String, String>();

        queryValuesMap.put("trackName", trackName.getText().toString());

        dbTools.updateTrack(queryValuesMap);

        finish(); // end the edit activity
    }

    public void deleteTheTrack(View view)
    {
        Intent i = getIntent();
        String fileName = i.getStringExtra("trackID");
        dbTools.deleteTrack(fileName);
        finish(); //end the edit activity after removing the record from the table
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_track, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}