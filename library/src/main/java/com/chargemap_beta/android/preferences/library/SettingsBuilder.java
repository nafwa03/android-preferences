package com.chargemap_beta.android.preferences.library;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chargemap_beta.android.preferences.library.types.Setting;

import java.io.Serializable;
import java.util.List;

public class SettingsBuilder implements Serializable {

    public List<Setting> settings;

    public String title;

    public int primaryColor;

    public int accentColor;

    public int toolbarTextColor;

    public int dividerColor = 0;

    private transient Activity activity;

    public SettingsBuilder setPrimaryColor(int color) {
        this.primaryColor = ContextCompat.getColor(activity, color);
        return this;
    }

    public SettingsBuilder setAccentColor(int textColor) {
        this.accentColor = ContextCompat.getColor(activity, textColor);
        return this;
    }

    public SettingsBuilder setToolbarTextColor(int textColor) {
        this.toolbarTextColor = ContextCompat.getColor(activity, textColor);
        return this;
    }

    public SettingsBuilder setDividerColor(int dividerColor) {
        this.dividerColor = ContextCompat.getColor(activity, dividerColor);
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

    public SettingsBuilder fromActivity(Activity activity) {
        this.activity = activity;
        return this;
    }

    public void start() {
        for (Setting setting : settings) {
            setting.writeIcon();
        }

        Intent intent = new Intent(activity, LibraryPreferenceActivity.class);

        intent.putExtra("data", this);

        activity.startActivity(intent);
    }

    public SettingAdapter setupRecyclerView(RecyclerView recyclerView) {
        SettingAdapter settingAdapter = new SettingAdapter(activity, primaryColor, accentColor);
        settingAdapter.setItems(settings);

        recyclerView.setAdapter(settingAdapter);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));

        if (dividerColor == 0) {
            dividerColor = ContextCompat.getColor(activity, R.color.borders);
        }

        recyclerView.addItemDecoration(new VerticalDivider(activity, dividerColor));

        return settingAdapter;
    }

    public Fragment getFragment() {
        return SettingFragment.newInstance(this);
    }
}