package com.mega.miwok.Activities;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mega.miwok.Adapter.WordAdapter;
import com.mega.miwok.Data.DataHandler;
import com.mega.miwok.R;

public class FamilyActivity extends AppCompatActivity implements
        AdapterView.OnItemClickListener,
        MediaPlayer.OnCompletionListener,
        AudioManager.OnAudioFocusChangeListener {
    //for Debugging purpose
    final static String TAG = FamilyActivity.class.getName();
    private MediaPlayer mMediaPlayer;
    private AudioManager myAudioManager;
    private int result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        myAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        WordAdapter itemsAdapter = new WordAdapter(this, DataHandler.familyArrayList, "Family");
        ListView listView = (ListView) findViewById(R.id.listViewWord);
        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        releaseMediaPlayer();
        mMediaPlayer = MediaPlayer.create(FamilyActivity.this, DataHandler.familyArrayList.get(position).getSoundResourceID());
        mMediaPlayer.start();
        mMediaPlayer.setOnCompletionListener(this);

        DataHandler.addWord(this, 2, position);
        if (DataHandler.WriteFile(DataHandler.writeDStoString())) {
            Log.i(TAG, "Successful");
        } else {
            Log.i(TAG, "xxxxxxxxxx");
        }
    }

    @Override
    public void onAudioFocusChange(int focusChange) {
        if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
            Log.i("Test", "AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK --   mMediaPlayer.pause();");
            if(mMediaPlayer!= null)
            {
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            }
        } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
            Log.i("Test", "AUDIOFOCUS_GAIN --   mMediaPlayer.start();");
            if(mMediaPlayer!= null) mMediaPlayer.start();
        } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
            // The AUDIOFOCUS_LOSS case means we've lost audio focus and Stop playback and clean up resources
            Log.i("Test", "AUDIOFOCUS_LOSS --   releaseMediaPlayer");
            releaseMediaPlayer();

        }
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    public void releaseMediaPlayer() {
        if (mMediaPlayer != null) {
            Log.i("Test", "releaseMediaPlayer");
            mMediaPlayer.release();
            mMediaPlayer = null;
            abandonAudioFocus();
        }
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        releaseMediaPlayer();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releaseMediaPlayer();
    }

    @Override
    protected void onResume() {
        super.onResume();
        result = myAudioManager.requestAudioFocus(this, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
    }

    private void abandonAudioFocus() {
        myAudioManager.abandonAudioFocus(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}
