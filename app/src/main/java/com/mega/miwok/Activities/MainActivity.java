package com.mega.miwok.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.mega.miwok.Data.DataHandler;
import com.mega.miwok.R;
import com.mega.miwok.UI.AboutBox;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //for Debugging purpose
    final static String TAG = MainActivity.class.getName();
    public static int REQ_CODE_WRITE_FILE = 1;
    private TextView textViewNumbers;
    private TextView textViewFamily;
    private TextView textViewPhrases;
    private TextView textViewColors;
    private TextView textViewLearnt;
    private TextView textViewAbout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MainActivity.REQ_CODE_WRITE_FILE);
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MainActivity.REQ_CODE_WRITE_FILE);
        }
        DataHandler.init();

        textViewAbout= findViewById(R.id.textViewabout);
        textViewNumbers = findViewById(R.id.numbers);
        textViewFamily = findViewById(R.id.family);
        textViewPhrases = findViewById(R.id.phrases);
        textViewColors = findViewById(R.id.colors);
        textViewLearnt = findViewById(R.id.learnt);

        textViewNumbers.setOnClickListener(this);
        textViewFamily.setOnClickListener(this);
        textViewPhrases.setOnClickListener(this);
        textViewColors.setOnClickListener(this);
        textViewLearnt.setOnClickListener(this);
        textViewAbout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int iViewId = v.getId();
        if (iViewId == R.id.numbers) {
            startActivity(new Intent(MainActivity.this, NumbersActivity.class));
        } else if (iViewId == R.id.family) {
            startActivity(new Intent(MainActivity.this, FamilyActivity.class));
        } else if (iViewId == R.id.phrases) {
            startActivity(new Intent(MainActivity.this, PhrasesActivity.class));
        } else if (iViewId == R.id.colors) {
            startActivity(new Intent(MainActivity.this, ColorsActivity.class));
        } else if (iViewId == R.id.learnt) {
            startActivity(new Intent(MainActivity.this, ViewedActivity.class));
        } else if (iViewId == R.id.textViewabout) {
            com.mega.miwok.UI.AboutBox.Show(MainActivity.this);
        }
    }
}