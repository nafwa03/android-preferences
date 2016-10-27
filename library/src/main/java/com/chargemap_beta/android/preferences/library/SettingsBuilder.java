package com.chargemap_beta.android.preferences.library;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;

import com.chargemap_beta.android.preferences.library.types.Setting;

import java.io.Serializable;
import java.util.List;

public class SettingsBuilder implements Serializable {

    public List<Setting> settings;

    public String title;

    public int primaryColor;

    public int accentColor;

    public int toolbarTextColor;

    private transient Context context;

    public SettingsBuilder setPrimaryColor(int color) {
        this.primaryColor = ContextCompat.getColor(context, color);
        return this;
    }

    public SettingsBuilder setAccentColor(int textColor) {
        this.accentColor = ContextCompat.getColor(context, textColor);
        return this;
    }

    public SettingsBuilder setToolbarTextColor(int textColor) {
        this.toolbarTextColor = ContextCompat.getColor(context, textColor);
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

    public SettingsBuilder fromActivity(Context context) {
        this.context = context;
        return this;
    }

    public void start() {

        Intent intent = new Intent(context, SettingsActivity.class);

        intent.putExtra("data", this);

        context.startActivity(intent);
    }
}