package com.chargemap_beta.android.preferences.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.chargemap_beta.android.preferences.library.SettingsBuilder;
import com.chargemap_beta.android.preferences.library.types.CheckBoxSetting;
import com.chargemap_beta.android.preferences.library.types.RadioSetting;
import com.chargemap_beta.android.preferences.library.types.Setting;
import com.chargemap_beta.android.preferences.library.types.SliderSetting;
import com.chargemap_beta.android.preferences.library.types.TextSetting;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    SettingsBuilder settingsBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayList<Setting> settings = new ArrayList<>();

        settings.add(new TextSetting()
                .setContext(this)
                .setLabel("Section 1")
                .setTitle("Title")
                .setSubtitle("Subtitle")
                .setIconDrawable(ContextCompat.getDrawable(MainActivity.this, android.R.drawable.ic_media_pause))
        );

        List<String> stringList = new ArrayList<>();
        stringList.add("Meters");
        stringList.add("Miles");

        settings.add(((RadioSetting) new RadioSetting()
                        .setContext(this)
                        .setTitle("Distance unit")
                        .setIconDrawable(ContextCompat.getDrawable(getApplicationContext(), android.R.drawable.ic_media_pause))
                )
                        .setRadioSettingItemList(stringList)
                        .setDefaultRadioPosition(0)

        );

        settings.add(((SliderSetting) new SliderSetting()
                        .setContext(this)
                        .setLabel("Section 2")
                        .setTitle("Title2")
                        .setSubtitle("Subtitle")
                        .setIcon("http://www.myiconfinder.com/uploads/iconsets/79a6cc671eb7205ea4903436e08851c4-map.png")
                )
                        .setMinValue(1)
                        .setMaxValue(50)
                        .setValueNumber(6)
                        .setDefaultValue(12)

        );

        settings.add(((CheckBoxSetting) new CheckBoxSetting()
                        .setContext(this)
                        .setTitle("Title3")
                        .setSubtitle("Subtitle")
                        .setIcon("https://api-test.chargemap.com/medias/v2/charging_speeds/icon/original/1/normal.svg")
                        .setIconIsSVG(true)
                )
                        .setChecked(true)
        );

        settingsBuilder = new SettingsBuilder()
                .fromActivity(MainActivity.this)
                .setSettings(settings)
                .setPrimaryColor(R.color.colorPrimary)
                .setAccentColor(R.color.colorAccent)
                .setToolbarTextColor(R.color.md_white_1000)
                .setTitle("Settings custom");

        Button btn_activity = ((Button) findViewById(R.id.btn_activity));
        btn_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settingsBuilder.start();
            }
        });

        Button btn_recyclerview = (Button) findViewById(R.id.btn_recyclerview);
        btn_recyclerview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RecyclerActivity.class));
            }
        });

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content, settingsBuilder.getFragment(), "SETTINGFRAGMENT")
                .disallowAddToBackStack()
                .commit();
    }
}
