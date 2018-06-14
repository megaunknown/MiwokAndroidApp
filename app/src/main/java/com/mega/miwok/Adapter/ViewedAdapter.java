package com.mega.miwok.Adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mega.miwok.Data.DataHandler;
import com.mega.miwok.R;

import java.util.ArrayList;

public class ViewedAdapter extends ArrayAdapter<Pair<Integer, Integer>> {
    //for Debugging purpose.
    private static final String TAG = ViewedAdapter.class.getSimpleName();

    public ViewedAdapter(Activity context, ArrayList<Pair<Integer, Integer>> wordList) {
        super(context, 0, wordList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.learnt_item_layout, parent, false);
        }

        TextView txtActivity = (TextView) listItemView.findViewById(R.id.text_view_activity);
        TextView txtWord = (TextView) listItemView.findViewById(R.id.text_view_word);

        //Get the Element's Values
        Pair<Integer, Integer> pp = getItem(position);

        int iActivity = pp.first;
        int iSelectedPos = pp.second;

        //based of each code for the activity, the UI element TEXT will be changes
        if (iActivity == 1) {
            txtActivity.setText("Colors");
            Log.e("Tag", DataHandler.colorsArrayList.get(iSelectedPos).getMiwokTranslation());
            txtWord.setText(DataHandler.colorsArrayList.get(iSelectedPos).getMiwokTranslation());
        } else if (iActivity == 2) {
            txtActivity.setText("Family");
            Log.e("Tag", DataHandler.familyArrayList.get(iSelectedPos).getMiwokTranslation());
            txtWord.setText(DataHandler.familyArrayList.get(iSelectedPos).getMiwokTranslation());
        } else if (iActivity == 3) {
            txtActivity.setText("Numbers");
            Log.e("Tag", DataHandler.nummberArrayList.get(iSelectedPos).getMiwokTranslation());
            txtWord.setText(DataHandler.nummberArrayList.get(iSelectedPos).getMiwokTranslation());
        } else if (iActivity == 4) {
            txtActivity.setText("Phrases");
            Log.e("Tag", DataHandler.phraseArrayList.get(iSelectedPos).getMiwokTranslation());
            txtWord.setText(DataHandler.phraseArrayList.get(iSelectedPos).getMiwokTranslation());
        }
        return listItemView;
    }
}
