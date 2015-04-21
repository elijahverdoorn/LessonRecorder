package com.example.elijah.lessonrecorder;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;


public class ListFiles extends ListActivity {

    Intent intent;
    TextView trackID;

    DBTools dbTools = new DBTools(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_files);
        ArrayList <HashMap <String, String>> trackList = dbTools.getAllTracks();
        dbTools.getAllTracks();

        if (trackList.size() != 0)
        {
            ListView list = getListView();
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    trackID = (TextView) view.findViewById(R.id.trackID);

                    String trackIDValue = trackID.getText().toString();

                    Intent theIntent = new Intent(getApplication(), EditTrack.class);

                    theIntent.putExtra("trackID", trackIDValue);
                    startActivity(theIntent);
                }
            });

            ListAdapter adapter = new SimpleAdapter(ListFiles.this, trackList, R.layout.track_entry, new String [] {"trackID", "trackName"}, new int[] {R.id.trackID, R.id.trackName});
            setListAdapter(adapter);
        }
    }
    public void onResume() // created so that the list updates after the EditTrack activity returns to this activity
    {
        super.onResume();
        setContentView(R.layout.activity_list_files);
        ArrayList <HashMap <String, String>> trackList = dbTools.getAllTracks();
        dbTools.getAllTracks();
        if (trackList.size() != 0)
        {
            ListView list = getListView();
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    trackID = (TextView) view.findViewById(R.id.trackID);

                    String trackIDValue = trackID.getText().toString();

                    Intent theIntent = new Intent(getApplication(), EditTrack.class);

                    theIntent.putExtra("trackID", trackIDValue);
                    startActivity(theIntent);
                }
            });

            ListAdapter adapter = new SimpleAdapter(ListFiles.this, trackList, R.layout.track_entry, new String [] {"trackID", "trackName"}, new int[] {R.id.trackID, R.id.trackName});
            setListAdapter(adapter);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_files, menu);
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