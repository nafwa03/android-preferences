package com.chargemap_beta.android.preferences.library;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.chargemap_beta.android.preferences.library.types.Setting;

import java.util.List;

public class SettingsActivity extends AppCompatActivity {

    RecyclerView recyclerview;

    SettingAdapter settingAdapter;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settings);

        List<Setting> settings = getIntent().getParcelableArrayListExtra("settings");
        String title = getIntent().getStringExtra("title");

        int primaryColor = getIntent().getIntExtra("primaryColor", 0);

        int accentColor = getIntent().getIntExtra("accentColor", 0);

        int toolbarTextColor = getIntent().getIntExtra("toolbarTextColor", 0);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(primaryColor);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (toolbarTextColor == 0) {
            toolbarTextColor = Color.WHITE;
        }

        // Set toolbar title color
        toolbar.setTitleTextColor(toolbarTextColor);

        // Set toolbar icon color
        final PorterDuffColorFilter colorFilter = new PorterDuffColorFilter(toolbarTextColor, PorterDuff.Mode.SRC_ATOP);

        for (int i = 0; i < toolbar.getChildCount(); i++) {
            final View v = toolbar.getChildAt(i);

            if (v instanceof ImageButton) {
                ((ImageButton) v).setColorFilter(colorFilter);
            }
        }

        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);

        settingAdapter = new SettingAdapter(this, primaryColor, accentColor);
        settingAdapter.setItems(settings);
        recyclerview.setAdapter(settingAdapter);

        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.addItemDecoration(new DividerDecoration(this, DividerDecoration.VERTICAL_LIST));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {

            case android.R.id.home:
                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}