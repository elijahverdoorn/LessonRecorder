package com.example.elijah.lessonrecorder;

import android.content.Intent;
import android.media.MediaRecorder;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.ImageView;

import java.io.IOException;

import static com.example.elijah.lessonrecorder.R.id.button2;

//TODO: Uncomment the start and stop recording methods when testing on a physical device
public class RecordingActivity extends ActionBarActivity {

    String fileName;
    MediaRecorder mRecorder;
    boolean nowRecording = false;
    ProgressBar spinner;
    ImageView image;

    private static final String LOG_TAG = "AudioRecordTest";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recording);
        Intent i = getIntent();
        fileName = i.getStringExtra("fileName");
        startSpinner();
        nowRecording = true; // for testing
        //startRecording();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_recording, menu);
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

    // method to start the recording spinner that is displayed when recording is taking place
    private void startSpinner()
    {
        spinner = (ProgressBar)findViewById(R.id.progressBar);
        spinner.setVisibility(View.VISIBLE);
        image = (ImageView)findViewById(R.id.imageView);
        image.setVisibility(View.GONE);
    }

    private void stopSpinner()
    {
        spinner.setVisibility(View.GONE);
        image = (ImageView)findViewById(R.id.imageView);
        image.setVisibility(View.VISIBLE);
    }

    private void startRecording()
    {
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(fileName);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mRecorder.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }

        mRecorder.start();
        nowRecording = true;
    }

    private void stopRecording()
    {
        mRecorder.stop();
        nowRecording = false;
    }

    private void resumeRecording()
    {
        // does this append or overwrite the file?
        mRecorder.start();
        nowRecording = false;
    }

    // stop the recording and change the button text
    public void buttonStopRecordClicked(View v)
    {
        Button mButton=(Button)findViewById(button2);
        if (nowRecording)
        {
            //stopRecording();
            stopSpinner();
            mButton.setText("@string/resume_recording");
        }else{
            //resumeRecording();
            startSpinner();
            mButton.setText("@string/pause_recording");
        }
    }

    // stop the recording, write the data, and exit the activity
    public void buttonSaveDataClicked(View v)
    {
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
        finish();
    }
}