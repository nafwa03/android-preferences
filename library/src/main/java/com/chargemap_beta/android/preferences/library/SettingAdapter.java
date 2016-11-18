package com.chargemap_beta.android.preferences.library;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.chargemap_beta.android.preferences.library.callbacks.SettingClickListener;
import com.chargemap_beta.android.preferences.library.types.CheckBoxSetting;
import com.chargemap_beta.android.preferences.library.types.RadioSetting;
import com.chargemap_beta.android.preferences.library.types.Setting;
import com.chargemap_beta.android.preferences.library.types.SliderSetting;
import com.chargemap_beta.android.preferences.library.types.TextSetting;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

import java.util.ArrayList;
import java.util.List;


public class SettingAdapter extends RecyclerView.Adapter<SettingAdapter.VH> {

    private Activity baseActivity;
    private List<Setting> settings = new ArrayList<>();
    int primaryColor;

    int accentColor;

    public SettingAdapter(Activity baseActivity, int primaryColor, int accentColor) {
        this.baseActivity = baseActivity;
        this.primaryColor = primaryColor;
        this.accentColor = accentColor;
        this.settings = new ArrayList<>();
    }

    @Override
    public VH onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        switch (viewType) {
            case 1:
                return new VH(inflater.inflate(R.layout.adapter_setting_text_item, viewGroup, false));
            case 2:
                return new VH(inflater.inflate(R.layout.adapter_setting_checkbox_item, viewGroup, false));
            case 3:
                return new VH(inflater.inflate(R.layout.adapter_setting_slider_item, viewGroup, false));
            case 4:
                return new VH(inflater.inflate(R.layout.adapter_setting_radio_item, viewGroup, false));
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {

        if (settings.get(position) instanceof TextSetting) {
            return 1;
        } else if (settings.get(position) instanceof CheckBoxSetting) {
            return 2;
        } else if (settings.get(position) instanceof SliderSetting) {
            return 3;
        } else if (settings.get(position) instanceof RadioSetting) {
            return 4;
        }

        return 0;
    }

    public void setItems(List<Setting> settingList) {
        settings = settingList;
    }

    @Override
    public void onBindViewHolder(final VH vh, int position) {

        final Setting setting = settings.get(position);

        setting.setContext(baseActivity);

        if (setting.getIcon() != null) {
            Log.d("PARSE", setting.getIcon());
            Log.d("PARSE", Uri.parse("file://" + setting.getIcon()).toString());
            vh.icon.setImageURI(Uri.parse("file://" + setting.getIcon()));
        }

        /*if(setting.getIconFile() != null){
            vh.icon.setImageDrawable(ContextCompat.getDrawable(baseActivity, setting.getIconRes()));
        }

        if(setting.getIconURL() != null){
            vh.icon.setImageDrawable(ContextCompat.getDrawable(baseActivity, setting.getIconRes()));
        }*/

        if (vh.title != null) {
            vh.title.setText(setting.getLabel());
            if (setting.getLabel() == null) {
                vh.title.setVisibility(View.GONE);
            }
        }

        if (vh.settingTitle != null) {
            vh.settingTitle.setText(setting.getTitle());
            if (setting.getTitle() == null) {
                vh.settingTitle.setVisibility(View.GONE);
            }
        }
        if (vh.subtitle != null) {
            if (setting.getSubtitle() == null) {
                vh.subtitle.setVisibility(View.GONE);
            } else {
                vh.subtitle.setText(setting.getSubtitle());
            }
        }

        if (settings.get(position) instanceof TextSetting) {


        } else if (settings.get(position) instanceof CheckBoxSetting) {

            final CheckBoxSetting checkBoxSetting = (CheckBoxSetting) setting;

            if (checkBoxSetting.findValue() != null) {
                // No preference found

                vh.settingCheckbox.setChecked(checkBoxSetting.getChecked());
            } else {
                // we found a preference so we check the checkbox if needed

                vh.settingCheckbox.setChecked(Boolean.parseBoolean(checkBoxSetting.findValue()));
            }

            vh.settingClickListener = new SettingClickListener() {
                @Override
                public void onClick(int position) {
                    vh.settingCheckbox.setChecked(!vh.settingCheckbox.isChecked());
                }
            };

            vh.settingCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    setting.saveValue(b ? "true" : "false");
                }
            });

        } else if (settings.get(position) instanceof SliderSetting) {

            SliderSetting sliderSetting = (SliderSetting) setting;

            if (sliderSetting.findValue() != null) {
                // No preference found
                vh.settingSlider.setProgress(sliderSetting.getDefaultValue());

            } else {
                // We found a preference so we adjust the slider
                vh.settingSlider.setProgress(Integer.parseInt(sliderSetting.findValue()));
            }

            vh.settingSlider.setTrackColor(accentColor);
            vh.settingSlider.setMin(sliderSetting.getMinValue());
            vh.settingSlider.setMax(sliderSetting.getMaxValue());

            vh.settingSlider.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
                @Override
                public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {

                }

                @Override
                public void onStartTrackingTouch(DiscreteSeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(DiscreteSeekBar seekBar) {
                    setting.saveValue("" + seekBar.getProgress());
                }
            });

        } else if (settings.get(position) instanceof RadioSetting) {

            RadioSetting radioSetting = (RadioSetting) setting;

            int checkedRadioId;

            if (radioSetting.findValue() != null) {
                // No preference found
                checkedRadioId = radioSetting.getDefaultRadioPosition();

            } else {
                // We found a preference so we check the radio
                checkedRadioId = Integer.parseInt(radioSetting.findValue());
            }

            for (int i = 0; i < radioSetting.getRadioSettingItemList().size(); i++) {

                AppCompatRadioButton rb = new AppCompatRadioButton(baseActivity);
                rb.setId(i);

                ColorStateList colorStateList = new ColorStateList(
                        new int[][]{
                                new int[]{-android.R.attr.state_checked},
                                new int[]{android.R.attr.state_checked}
                        },
                        new int[]{Color.DKGRAY, accentColor}
                );
                rb.setSupportButtonTintList(colorStateList);

                rb.setChecked(i == checkedRadioId);
                vh.radioGroup.addView(rb);

                TextView textView = (TextView) LayoutInflater.from(baseActivity.getApplicationContext()).inflate(R.layout.adapter_setting_radiogroup_radio_label, null, false);
                textView.setText(((RadioSetting) setting).getRadioSettingItemList().get(i));
                vh.radioRow.addView(textView);
            }

            radioSetting.saveValue("" + checkedRadioId);

            vh.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    setting.saveValue("" + i);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return settings == null ? 0 : settings.size();
    }

    public static class VH extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView icon;

        TextView subtitle;

        CheckBox settingCheckbox;

        RadioGroup radioGroup;

        TextView settingTitle;

        DiscreteSeekBar settingSlider;

        TextView title;

        LinearLayout radioRow;

        SettingClickListener settingClickListener;

        public VH(View v) {
            super(v);

            icon = (ImageView) v.findViewById(R.id.adapterSettingItem_imageView_icon);
            subtitle = (TextView) v.findViewById(R.id.adapterSettingItem_textView_subtitle);
            settingCheckbox = (CheckBox) v.findViewById(R.id.adapterSettingCheckboxItem_checkBox);
            radioGroup = (RadioGroup) v.findViewById(R.id.adapterSettingRadioItem_radioGroup);
            settingTitle = (TextView) v.findViewById(R.id.adapterSettingItem_textView_title);
            settingSlider = (DiscreteSeekBar) v.findViewById(R.id.adapterSettingSliderItem_rangeBar_slider);
            title = (TextView) v.findViewById(R.id.adapterSettingItem_textView_label);
            radioRow = (LinearLayout) v.findViewById(R.id.adapterSettingRadioItem_linearLayout_labelContainer);

            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (settingClickListener != null) {
                settingClickListener.onClick(getAdapterPosition());
            }
        }
    }
}