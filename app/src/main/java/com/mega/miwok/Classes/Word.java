package com.mega.miwok.Classes;

public class Word {
    private int mImageResId;
    private int mSoundTrack;
    private String mMiwokTranslation;
    private String mDefaultTranslation;

    public Word(String defaultTranslation, String miwokTranslation) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mImageResId = -1;
        mSoundTrack = -1;
    }

    public Word(String mDefaultTranslation, String mMiwokTranslation, int ImageResId) {
        this.mDefaultTranslation = mDefaultTranslation;
        this.mMiwokTranslation = mMiwokTranslation;
        this.mImageResId = ImageResId;
        mSoundTrack = -1;
    }

    public Word(String mDefaultTranslation, String mMiwokTranslation, int ImageResId, int SoundResId) {
        this.mDefaultTranslation = mDefaultTranslation;
        this.mMiwokTranslation = mMiwokTranslation;
        this.mImageResId = ImageResId;
        this.mSoundTrack = SoundResId;
    }

    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }

    public int getImageResourceID() {
        return mImageResId;
    }

    public int getSoundResourceID() {
        return mSoundTrack;
    }
}
