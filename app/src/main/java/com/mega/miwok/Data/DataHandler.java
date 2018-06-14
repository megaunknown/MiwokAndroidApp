package com.mega.miwok.Data;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.util.Pair;

import com.mega.miwok.Classes.Word;
import com.mega.miwok.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class DataHandler {
    //For Debugging purpose
    final static String TAG = DataHandler.class.getName();
    //File name
    final static String fileName = "data.txt";
    //Generate the file path
    final static String path = Environment.getExternalStorageDirectory().getPath() + "/Miwok";
    //ADT to store our file content
    public static ArrayList<Pair<Integer, Integer>> words = new ArrayList<>();
    //Resources
    public static ArrayList<Word> colorsArrayList;
    public static ArrayList<Word> nummberArrayList;
    public static ArrayList<Word> familyArrayList;
    public static ArrayList<Word> phraseArrayList;

    //init the app resources and parse file content.
    public static void init() {

        colorsArrayList = new ArrayList<>();
        colorsArrayList.add(new Word("Red", "weṭeṭṭi", R.drawable.color_red, R.raw.color_red));
        colorsArrayList.add(new Word("Green", "chokokki", R.drawable.color_green, R.raw.color_green));
        colorsArrayList.add(new Word("Brown", "ṭakaakki", R.drawable.color_brown, R.raw.color_brown));
        colorsArrayList.add(new Word("Gray", "ṭopoppi", R.drawable.color_gray, R.raw.color_gray));
        colorsArrayList.add(new Word("Black", "kululli", R.drawable.color_black, R.raw.color_black));
        colorsArrayList.add(new Word("White", " kelelli", R.drawable.color_white, R.raw.color_white));
        colorsArrayList.add(new Word("Dusty Yellow", "ṭopiisә", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
        colorsArrayList.add(new Word("Mustard Yellow", "chiwiiṭә", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));

        phraseArrayList = new ArrayList<>();
        phraseArrayList.add(new Word("Where are you going?", "minto wuksus", -1, R.raw.phrase_where_are_you_going));
        phraseArrayList.add(new Word("What is your name?", "tinnә oyaase'nә", -1, R.raw.phrase_what_is_your_name));
        phraseArrayList.add(new Word("My name is...", "oyaaset...", -1, R.raw.phrase_my_name_is));
        phraseArrayList.add(new Word("How are you feeling?", "michәksәs?", -1, R.raw.phrase_how_are_you_feeling));
        phraseArrayList.add(new Word("I’m feeling good.", " kuchi achit", -1, R.raw.phrase_im_feeling_good));
        phraseArrayList.add(new Word("Are you coming?", "әәnәs'aa?", -1, R.raw.phrase_are_you_coming));
        phraseArrayList.add(new Word("Yes, I’m coming.", "hәә’ әәnәm", -1, R.raw.phrase_yes_im_coming));
        phraseArrayList.add(new Word("I’m coming.", "әәnәm", -1, R.raw.phrase_im_coming));
        phraseArrayList.add(new Word("Let’s go.", "yoowutis", -1, R.raw.phrase_lets_go));
        phraseArrayList.add(new Word("Come here.", "әnni'nem", -1, R.raw.phrase_come_here));

        nummberArrayList = new ArrayList<>();
        nummberArrayList.add(new Word("One", "lutti", R.drawable.number_one, R.raw.number_one));
        nummberArrayList.add(new Word("Two", "oṭiiko", R.drawable.number_two, R.raw.number_two));
        nummberArrayList.add(new Word("Three", "tolookosu", R.drawable.number_three, R.raw.number_three));
        nummberArrayList.add(new Word("Four", "oyyiisa", R.drawable.number_four, R.raw.number_four));
        nummberArrayList.add(new Word("Five", "massokka", R.drawable.number_five, R.raw.number_five));
        nummberArrayList.add(new Word("Six", " ttemmokka", R.drawable.number_six, R.raw.number_six));
        nummberArrayList.add(new Word("Seven", "kenekaku", R.drawable.number_seven, R.raw.number_seven));
        nummberArrayList.add(new Word("Eight", "kawinṭa", R.drawable.number_eight, R.raw.number_eight));
        nummberArrayList.add(new Word("Nine", "wo'e", R.drawable.number_nine, R.raw.number_nine));
        nummberArrayList.add(new Word("Ten", "na'aacha", R.drawable.number_ten, R.raw.number_ten));

        familyArrayList = new ArrayList<>();
        familyArrayList.add(new Word("father", "әpә", R.drawable.family_father, R.raw.family_father));
        familyArrayList.add(new Word("mother", "әṭa", R.drawable.family_mother, R.raw.family_mother));
        familyArrayList.add(new Word("son", "angsi", R.drawable.family_son, R.raw.family_son));
        familyArrayList.add(new Word("daughter", "tune", R.drawable.family_daughter, R.raw.family_daughter));
        familyArrayList.add(new Word("older brother", "taachi", R.drawable.family_older_brother, R.raw.family_older_brother));
        familyArrayList.add(new Word("younger brother", "chalitti", R.drawable.family_younger_brother, R.raw.family_younger_brother));
        familyArrayList.add(new Word("older sister", "teṭe", R.drawable.family_older_sister, R.raw.family_older_sister));
        familyArrayList.add(new Word("younger sister", "kolliti", R.drawable.family_younger_sister, R.raw.family_younger_sister));
        familyArrayList.add(new Word("grandmother ", "ama", R.drawable.family_grandmother, R.raw.family_grandmother));
        familyArrayList.add(new Word("grandfather", "paapa", R.drawable.family_grandfather, R.raw.family_grandfather));

        //Check the file dir
        File f = new File(path);

        //not file dir created yet
        if (!f.exists()) {
            if (f.mkdirs())
                Log.e(TAG, "Dir created");
            else
                Log.e(TAG, "Dir not created");
        }

        //Check file creation.
        if (fileCreated()) {
            words = new ArrayList<>();
            words = readStringToDS(ReadFile());
        }

    }

    /*
    * Add new word to the dictionary
    * */
    public static void addWord(Context cnn, int iActivityId, int iWordId) {
        if (!words.contains(new Pair<Integer, Integer>(iActivityId, iWordId))) {
            //Add the word to the Data Structure.
            words.add(new Pair<Integer, Integer>(iActivityId, iWordId));
        }
    }

    /*
    * check if the file is created or not.
    * */
    public static Boolean fileCreated() {
        File file = new File(path + fileName);
        //file.delete();
        if (file.exists()) {
            return true;
        }
        return false;
    }

    /*
    * Remove a word for feature use.
    * */
    public static void removeWord(Context cnn, int iActivityId, int iWordId) {
        if (words.contains(new Pair<Integer, Integer>(iActivityId, iWordId))) {
            //remove the word to the Data Structure.
            words.remove(new Pair<Integer, Integer>(iActivityId, iWordId));
        }
    }

    /*
    * Read the file content and return it as string.
    * */
    public static String ReadFile() {
        String line = null;

        try {
            FileInputStream fileInputStream = new FileInputStream(new File(path + fileName));
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line + System.getProperty("line.separator"));
            }

            fileInputStream.close();

            line = stringBuilder.toString();

            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            Log.d(TAG, ex.getMessage());
        } catch (IOException ex) {
            Log.d(TAG, ex.getMessage());
        }
        return line;
    }

    /**
     * Write the current content to the file.
     */
    public static boolean WriteFile(String data) {
        if (data.length() == 0)
            return false;

        try {
            File file = new File(path + fileName);
            if (!file.exists())
                file.createNewFile();

            FileOutputStream fileOutputStream;
            fileOutputStream = new FileOutputStream(file, true);
            fileOutputStream.write(data.getBytes());
            Log.e("TAG", "Written Successfully");
            fileOutputStream.flush();
            fileOutputStream.close();
            return true;
        } catch (FileNotFoundException ex) {
            Log.e("TAG", "NOT Written Successfully");
            Log.e(TAG, ex.getMessage());
        } catch (IOException ex) {
            Log.e("TAG", "NOT Written Successfully");
            Log.e(TAG, ex.getMessage());
        }
        return false;
    }

    /*
    * Parse the file content and populate the OUR Dictionary
    * */
    public static ArrayList<Pair<Integer, Integer>> readStringToDS(String data) {
        if (data.length() == 0)
            return words = new ArrayList<>();

        String[] strLines = data.split(System.getProperty("line.separator"));
        for (String s : strLines) {
            Log.e("TAG", s);

            String sChunks[] = s.split(",");
            Log.e("TAG", sChunks[0] + "--" + sChunks[1]);
            Pair<Integer, Integer> p = new Pair<>(Integer.parseInt(sChunks[0]), Integer.parseInt(sChunks[1]));

            if (!words.contains(p))
                words.add(p);
        }

        return words;
    }

    /*
    * Convert our ADT Dictionary into String of pairs with Line separator.
    * */
    public static String writeDStoString() {
        if (words.size() == 0)
            return "";

        String stringData = "";
        for (Pair<Integer, Integer> p : words) {
            stringData += p.first + "," + p.second + System.getProperty("line.separator");
        }
        return stringData;
    }
}

