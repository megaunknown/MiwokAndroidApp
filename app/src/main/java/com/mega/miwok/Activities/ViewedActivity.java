package com.mega.miwok.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import com.mega.miwok.Adapter.ViewedAdapter;
import com.mega.miwok.Data.DataHandler;
import com.mega.miwok.R;

import java.util.ArrayList;

public class ViewedActivity extends AppCompatActivity {
    //for Debugging purpose
    final static String TAG = ViewedActivity.class.getName();
    private TextView textView;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learnt);

        textView = (TextView) findViewById(R.id.textViewNoLearnt);
        listView = (ListView) findViewById(R.id.listviewLearnt);


        if (DataHandler.fileCreated() && DataHandler.ReadFile().length() > 0) {
            textView.setVisibility(View.GONE);

            String string = DataHandler.ReadFile();

            //Read the content of our file
            ArrayList<Pair<Integer, Integer>> list = DataHandler.readStringToDS(DataHandler.ReadFile());
            //Initialize the list
            ViewedAdapter itemsAdapter = new ViewedAdapter(this, list);
            //Assign the layout
            ListView listView = (ListView) findViewById(R.id.listviewLearnt);
            //Set the Adapter for out list
            listView.setAdapter(itemsAdapter);
        } else {
            listView.setVisibility(View.GONE);
        }
    }
}
