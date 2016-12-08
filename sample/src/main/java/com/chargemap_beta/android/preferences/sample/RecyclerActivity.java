package com.chargemap_beta.android.preferences.sample;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chargemap_beta.android.preferences.library.SettingAdapter;
import com.chargemap_beta.android.preferences.library.SettingsBuilder;
import com.chargemap_beta.android.preferences.library.types.CheckBoxSetting;
import com.chargemap_beta.android.preferences.library.types.RadioSetting;
import com.chargemap_beta.android.preferences.library.types.Setting;
import com.chargemap_beta.android.preferences.library.types.SliderSetting;
import com.chargemap_beta.android.preferences.library.types.TextSetting;

import java.util.ArrayList;
import java.util.List;

public class RecyclerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        final ArrayList<Setting> settings = new ArrayList<>();

        settings.add(new TextSetting()
                .setContext(this)
                .setLabel("Section 1")
                .setTitle("Title")
                .setSubtitle("Subtitle")
                .setIconDrawable(ContextCompat.getDrawable(RecyclerActivity.this, android.R.drawable.ic_media_pause))
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
                        .setTitle("Title")
                        .setSubtitle("Subtitle")
                        .setIcon("http://www.myiconfinder.com/uploads/iconsets/79a6cc671eb7205ea4903436e08851c4-map.png")
                )
                        .setMinValue(3)
                        .setMaxValue(18)
                        .setDefaultValue(12)

        );

        settings.add(((CheckBoxSetting) new CheckBoxSetting()
                        .setContext(this)
                        .setTitle("Title")
                        .setSubtitle("Subtitle")
                        .setIconDrawable(ContextCompat.getDrawable(RecyclerActivity.this, android.R.drawable.ic_delete))
                )
                        .setChecked(true)
        );

        new SettingsBuilder()
                .fromActivity(RecyclerActivity.this)
                .setSettings(settings)
                .setAccentColor(R.color.colorAccent)
                .setDividerColor(R.color.md_red_500)
                .setupRecyclerView((RecyclerView) findViewById(R.id.recyclerview));

        findViewById(R.id.refreshButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SettingAdapter settingAdapter = (SettingAdapter) ((RecyclerView) findViewById(R.id.recyclerview)).getAdapter();

                for (int i = 0; i < settings.size(); i++) {
                    settings.get(i).reset();
                }

                settingAdapter.setItems(settings);
                settingAdapter.notifyDataSetChanged();
            }
        });
    }
}
