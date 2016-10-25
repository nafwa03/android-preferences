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
import com.chargemap_beta.android.preferences.library.types.CheckBoxSetting;
import com.chargemap_beta.android.preferences.library.types.RadioSetting;
import com.chargemap_beta.android.preferences.library.types.Setting;
import com.chargemap_beta.android.preferences.library.types.SliderSetting;
import com.chargemap_beta.android.preferences.library.types.TextSetting;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayList<Setting> settings = new ArrayList<>();

        settings.add(new TextSetting()
                .setLabel("Section 1")
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

        settings.add(((RadioSetting) new RadioSetting()
                .setTitle("Distance unit")
                .setIconDrawable(ContextCompat.getDrawable(getApplicationContext(), android.R.drawable.ic_media_pause))
                .setCallback(new SettingCallback() {
                    @Override
                    public void onClick(SettingAdapter.VH vh) {
                        Toast.makeText(getApplicationContext(), "Item clicked", Toast.LENGTH_LONG).show();
                    }
                }))
                .setRadioSettingItemList(new ArrayList<String>() {{
                    add("Meters");
                    add("Miles");
                }})
                .setDefaultRadioPosition(0)
        );

        settings.add(((SliderSetting) new SliderSetting()
                .setLabel("Section 2")
                .setTitle("Title")
                .setSubtitle("Subtitle")
                .setIconDrawable(ContextCompat.getDrawable(MainActivity.this, android.R.drawable.ic_media_pause))
                .setCallback(new SettingCallback() {
                    @Override
                    public void onClick(SettingAdapter.VH vh) {
                        Toast.makeText(getApplicationContext(), "Item clicked", Toast.LENGTH_LONG).show();
                    }
                }))
                .setMinValue(3)
                .setMaxValue(18)
                .setDefaultValue(12)
        );

        settings.add(((CheckBoxSetting) new CheckBoxSetting()
                .setTitle("Title")
                .setSubtitle("Subtitle")
                .setIconDrawable(ContextCompat.getDrawable(MainActivity.this, android.R.drawable.ic_media_pause))
                .setCallback(new SettingCallback() {
                    @Override
                    public void onClick(SettingAdapter.VH vh) {
                        Toast.makeText(getApplicationContext(), "Item clicked", Toast.LENGTH_LONG).show();
                    }
                }))
                .setChecked(true)
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

    }
}
