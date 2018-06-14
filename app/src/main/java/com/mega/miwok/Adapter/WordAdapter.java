package com.mega.miwok.Adapter;

import android.app.Activity;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mega.miwok.Classes.Word;
import com.mega.miwok.R;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {
    private static final String TAG = WordAdapter.class.getSimpleName();
    private String ActivityName = "";

    public WordAdapter(Activity context, ArrayList<Word> wordList) {
        super(context, 0, wordList);
    }

    public WordAdapter(Activity context, ArrayList<Word> wordList, String ActivityName) {
        super(context, 0, wordList);
        this.ActivityName = ActivityName;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.item_layout, parent, false);

            if (ActivityName.equals("Numbers")) {
                listItemView.setBackgroundColor(Color.parseColor("#FD8E09"));
            } else if (ActivityName.equals("Colors")) {
                listItemView.setBackgroundColor(Color.parseColor("#8800A0"));
            } else if (ActivityName.equals("Family")) {
                listItemView.setBackgroundColor(Color.parseColor("#379237"));
            } else if (ActivityName.equals("Phrases")) {
                listItemView.setBackgroundColor(Color.parseColor("#16AFCA"));
            }
        }

        Word currentWord = getItem(position);

        TextView txtMawi = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        TextView txtdefault = (TextView) listItemView.findViewById(R.id.default_text_view);
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);

        txtMawi.setText(currentWord.getMiwokTranslation());
        txtdefault.setText(currentWord.getDefaultTranslation());
        if (currentWord.getImageResourceID() > 0 && currentWord.getSoundResourceID() > 0)
            imageView.setImageResource(currentWord.getImageResourceID());
        else
            imageView.setVisibility(View.GONE);

        return listItemView;
    }
}
