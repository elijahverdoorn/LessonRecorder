package com.example.elijah.lessonrecorder;

import android.media.MediaRecorder;

/**
 * Created by Elijah on 3/30/2015.
 */
public class AudioRecorder {

    // class variables
    private String fileLocation;
    private MediaRecorder recorder;

    // constructor
    private AudioRecorder()
    {
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        recorder.setOutputFile(fileLocation);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
    }

    // set the file to be outputted to
    public void setFile(String par)
    {
        fileLocation = par;
    }

    // get the file to be written to
    public String getFile()
    {
        return fileLocation;
    }
}
