package com.chargemap_beta.android.preferences.library;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;

import com.chargemap_beta.android.preferences.library.types.Setting;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SettingsBuilder{

    private List<Setting> settings;

    private String title;

    private int color;

    private Activity activity;

    public SettingsBuilder setToolbarColor(int color) {
        this.color = color;
        return this;
    }

    public SettingsBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public SettingsBuilder setSettings(List<Setting> settings) {
        this.settings = settings;
        return this;
    }

    public SettingsBuilder fromActivity(Activity activity){
        this.activity = activity;
        return this;
    }

    public void start() {
        Intent i = new Intent(activity, SettingsActivity.class);

        i.putParcelableArrayListExtra("settings", (ArrayList<? extends Parcelable>) settings);
        i.putExtra("title", title);
        i.putExtra("color", color);

        activity.startActivity(i);
    }
}