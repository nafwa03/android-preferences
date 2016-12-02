package com.chargemap_beta.android.preferences.library;

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

import java.io.File;

public class LibraryPreferenceActivity extends AppCompatActivity {

    RecyclerView recyclerview;

    SettingsBuilder builder;

    SettingAdapter settingAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_library_preference_settings);

        builder = (SettingsBuilder) getIntent().getSerializableExtra("data");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(builder.primaryColor);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(builder.title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (builder.toolbarTextColor == 0) {
            builder.toolbarTextColor = Color.WHITE;
        }

        // Set toolbar title color
        toolbar.setTitleTextColor(builder.toolbarTextColor);

        // Set toolbar icon color
        final PorterDuffColorFilter colorFilter = new PorterDuffColorFilter(builder.toolbarTextColor, PorterDuff.Mode.SRC_ATOP);

        for (int i = 0; i < toolbar.getChildCount(); i++) {
            final View v = toolbar.getChildAt(i);

            if (v instanceof ImageButton) {
                ((ImageButton) v).setColorFilter(colorFilter);
            }
        }

        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);

        settingAdapter = new SettingAdapter(this, builder.primaryColor, builder.accentColor);
        settingAdapter.setItems(builder.settings);

        recyclerview.setAdapter(settingAdapter);
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.addItemDecoration(new DividerDecoration(this, DividerDecoration.VERTICAL_LIST));

        /*for (Setting setting : builder.settings) {
            File icon = new File(setting.getIcon());
            if(icon.exists()){
                icon.delete();
            }
        }*/
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {

            case android.R.id.home:

                for (Setting setting : builder.settings) {
                    new File(setting.getIcon()).delete();
                }

                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        for (Setting setting : builder.settings) {
            new File(setting.getIcon()).delete();
        }

        finish();
    }
}