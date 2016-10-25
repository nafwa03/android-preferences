package com.chargemap_beta.android.preferences.sample;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.chargemap_beta.android.preferences.library.SettingAdapter;
import com.chargemap_beta.android.preferences.library.SettingsBuilder;
import com.chargemap_beta.android.preferences.library.callbacks.SettingCallback;
import com.chargemap_beta.android.preferences.library.types.Setting;
import com.chargemap_beta.android.preferences.library.types.TextSetting;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayList<Setting> settings = new ArrayList<>();

        settings.add(new TextSetting()
                .setLabel("Global preferences")
                .setTitle("Title")
                .setSubtitle("Subtitle")
                .setIconDrawable(ContextCompat.getDrawable(MainActivity.this, android.R.drawable.ic_media_pause))
                .setCallback(new SettingCallback() {
                    @Override
                    public void onClick(SettingAdapter.VH vh) {
                        Toast.makeText(getApplicationContext(), "Item clicked", Toast.LENGTH_LONG).show();
                    }
                })
        );

        Button btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new SettingsBuilder()
                        .fromActivity(MainActivity.this)
                        .setSettings(settings)
                        .setToolbarColor(R.color.colorPrimary)
                        .setToolbarTextColor(R.color.md_white_1000)
                        .setTitle("Settings custom")
                        .start();
            }
        });

        /*settings.add(((RadioSetting) new RadioSetting()
                .setLabel("Global preferences")
                .setTitle("Distance unit")
                .setIcon(ContextCompat.getDrawable(getApplicationContext(), android.R.drawable.ic_media_pause))
                .setCallback(new SettingCallback() {
                    @Override
                    public void onClick(SettingAdapter.VH vh) {
                        Toast.makeText(getApplicationContext(), "Item clicked", Toast.LENGTH_LONG).show();
                    }
                }))
                .setRadioSettingItemList(new ArrayList<RadioSettingItem>() {{
                    add(new RadioSettingItem("Meters"));
                    add(new RadioSettingItem("Miles"));
                }})
                .setDefaultRadioPosition(0)
        );*/


    }
}
