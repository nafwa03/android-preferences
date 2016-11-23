package com.chargemap_beta.android.preferences.library;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.chargemap_beta.android.multiToggle.library.MultiToggle;
import com.chargemap_beta.android.multiToggle.library.MultiToggleClickListener;
import com.chargemap_beta.android.pictureLoader.library.PictureView;
import com.chargemap_beta.android.preferences.library.callbacks.SettingClickListener;
import com.chargemap_beta.android.preferences.library.types.CheckBoxSetting;
import com.chargemap_beta.android.preferences.library.types.RadioSetting;
import com.chargemap_beta.android.preferences.library.types.Setting;
import com.chargemap_beta.android.preferences.library.types.SliderSetting;
import com.chargemap_beta.android.preferences.library.types.SwitchSetting;
import com.chargemap_beta.android.preferences.library.types.TextSetting;
import com.chargemap_beta.android.preferences.library.types.ToggleSetting;
import com.kyleduo.switchbutton.SwitchButton;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

import java.io.File;
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
            case 5:
                return new VH(inflater.inflate(R.layout.adapter_setting_switch_item, viewGroup, false));
            case 6:
                return new VH(inflater.inflate(R.layout.adapter_setting_toggle_item, viewGroup, false));
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
        } else if (settings.get(position) instanceof SwitchSetting) {
            return 5;
        } else if (settings.get(position) instanceof ToggleSetting) {
            return 6;
        }

        return 0;
    }

    public void setItems(List<Setting> settingList) {
        settings = settingList;
    }

    @Override
    public void onBindViewHolder(final VH vh, final int position) {

        final Setting setting = settings.get(position);

        setting.setContext(baseActivity);

        if (setting.getIcon() == null || setting.getIcon().equals("null")) {
            vh.icon.setVisibility(View.GONE);
        } else {
            if (setting.getIconIsSVG()) {
                vh.icon.loadSVG(setting.getIcon());
            } else if (setting.getIconIsDrawable()) {
                vh.icon.load(new File(setting.getIcon()));
            } else {
                vh.icon.load(setting.getIcon());
            }
        }

        if (vh.title != null) {
            if (setting.getLabel() == null) {
                vh.title.setVisibility(View.GONE);
            } else {
                vh.title.setText(setting.getLabel());
            }
        }

        if (vh.settingTitle != null) {
            if (setting.getTitle() == null) {
                vh.settingTitle.setVisibility(View.GONE);
            } else {
                vh.settingTitle.setText(setting.getTitle());
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

            if (checkBoxSetting.findValue() == null || checkBoxSetting.findValue().equals("null")) {
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
                    if (setting.getUpdateListener() != null) {
                        setting.getUpdateListener().onUpdate(position);
                    }
                }
            };

            vh.settingCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    setting.saveValue(b ? "true" : "false");
                }
            });

        } else if (settings.get(position) instanceof SwitchSetting) {

            final SwitchSetting switchSetting = (SwitchSetting) setting;

            if (switchSetting.findValue() == null || switchSetting.findValue().equals("null")) {
                // No preference found

                vh.switchButton.setCheckedImmediately(switchSetting.getChecked());
            } else {
                // we found a preference so we check the checkbox if needed

                vh.switchButton.setCheckedImmediately(Boolean.parseBoolean(switchSetting.findValue()));
            }

            vh.settingClickListener = new SettingClickListener() {
                @Override
                public void onClick(int position) {
                    vh.switchButton.toggleImmediately();
                    if (setting.getUpdateListener() != null) {
                        setting.getUpdateListener().onUpdate(position);
                    }
                }
            };

            vh.switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    setting.saveValue(b ? "true" : "false");
                }
            });

        } else if (settings.get(position) instanceof SliderSetting) {

            SliderSetting sliderSetting = (SliderSetting) setting;

            if (sliderSetting.findValue() == null || sliderSetting.findValue().equals("null")) {
                // No preference found
                vh.settingSlider.setProgress(sliderSetting.getDefaultValue());

            } else {
                // We found a preference so we adjust the slider
                vh.settingSlider.setProgress(Integer.parseInt(sliderSetting.findValue()));
            }

            vh.settingSlider.setTrackColor(accentColor);
            vh.settingSlider.setMin(sliderSetting.getMinValue());
            vh.settingSlider.setMax(sliderSetting.getMaxValue());

            vh.settingSliderValues.setWeightSum(6);

            LinearLayout.LayoutParams leftParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            leftParams.weight = 1;
            leftParams.gravity = Gravity.LEFT;

            LinearLayout.LayoutParams centerParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            centerParams.weight = 1;
            centerParams.gravity = Gravity.CENTER;

            LinearLayout.LayoutParams rightParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            rightParams.weight = 1;
            rightParams.gravity = Gravity.RIGHT;

            if (sliderSetting.getValueNumber() == 0) {
                vh.settingSliderValues.setVisibility(View.GONE);
            } else {

                vh.settingSliderValues.removeAllViews();

                int delta = sliderSetting.getMaxValue() / sliderSetting.getValueNumber();

                for (int i = 0; i <= sliderSetting.getValueNumber(); i++) {
                    TextView value = new TextView(vh.settingSliderValues.getContext());

                    if (i == 0) {
                        value.setText("" + sliderSetting.getMinValue());
                        value.setLayoutParams(leftParams);
                    } else if (i == sliderSetting.getValueNumber()) {
                        value.setText("" + sliderSetting.getMaxValue());
                        value.setLayoutParams(rightParams);
                    } else {
                        value.setText("" + (delta * i));
                        value.setLayoutParams(centerParams);
                    }

                    vh.settingSliderValues.addView(value);
                }
            }

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
                    if (setting.getUpdateListener() != null) {
                        setting.getUpdateListener().onUpdate(position);
                    }
                }
            });

        } else if (settings.get(position) instanceof ToggleSetting) {

            ToggleSetting toggleSetting = (ToggleSetting) setting;

            if (toggleSetting.getActiveBackgroundColor() != 0)
                vh.toggle.setActiveBackgroundColor(toggleSetting.getActiveBackgroundColor());
            if (toggleSetting.getInactiveBackgroundColor() != 0)
                vh.toggle.setInactiveBackgroundColor(toggleSetting.getInactiveBackgroundColor());
            if (toggleSetting.getActiveTextColor() != 0)
                vh.toggle.setActiveTextColor(toggleSetting.getActiveTextColor());
            if (toggleSetting.getInactiveTextColor() != 0)
                vh.toggle.setInactiveTextColor(toggleSetting.getInactiveTextColor());

            if (toggleSetting.getPadding() != 0) vh.toggle.setPadding(toggleSetting.getPadding());
            if (toggleSetting.getRadius() != 0) vh.toggle.setRadius(toggleSetting.getRadius());

            if (toggleSetting.getBorderColor() != 0)
                vh.toggle.setBorderColor(toggleSetting.getBorderColor());

            if (toggleSetting.findValue() == null || toggleSetting.findValue().equals("null")) {
                // No preference found
                vh.toggle.setSelectedToggle(toggleSetting.getDefaultRadioPosition());

            } else {

                // We found a preference so we adjust the slider
                vh.toggle.setSelectedToggle(Integer.parseInt(toggleSetting.findValue()));
            }

            vh.toggle.removeAllViews();
            vh.toggle.setItems(toggleSetting.getRadioSettingItemList());

            vh.toggle.setListener(new MultiToggleClickListener() {
                @Override
                public void onClick(int i) {
                    if (setting.getUpdateListener() != null) {
                        setting.getUpdateListener().onUpdate(position);
                    }
                }
            });

        } else if (settings.get(position) instanceof RadioSetting) {

            RadioSetting radioSetting = (RadioSetting) setting;

            int checkedRadioId;

            if (radioSetting.findValue() == null || radioSetting.findValue().equals("null")) {
                // No preference found
                checkedRadioId = radioSetting.getDefaultRadioPosition();

            } else {
                // We found a preference so we check the radio
                checkedRadioId = Integer.parseInt(radioSetting.findValue());
            }

            vh.radioGroup.removeAllViews();
            vh.radioRow.removeAllViews();

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
                    if (setting.getUpdateListener() != null) {
                        setting.getUpdateListener().onUpdate(position);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return settings == null ? 0 : settings.size();
    }

    public static class VH extends RecyclerView.ViewHolder implements View.OnClickListener {

        PictureView icon;

        TextView subtitle;

        CheckBox settingCheckbox;

        RadioGroup radioGroup;

        TextView settingTitle;

        DiscreteSeekBar settingSlider;

        MultiToggle toggle;

        SwitchButton switchButton;

        LinearLayout settingSliderValues;

        TextView title;

        LinearLayout radioRow;

        SettingClickListener settingClickListener;

        public VH(View v) {
            super(v);

            icon = (PictureView) v.findViewById(R.id.adapterSettingItem_imageView_icon);
            subtitle = (TextView) v.findViewById(R.id.adapterSettingItem_textView_subtitle);
            settingCheckbox = (CheckBox) v.findViewById(R.id.adapterSettingCheckboxItem_checkBox);
            radioGroup = (RadioGroup) v.findViewById(R.id.adapterSettingRadioItem_radioGroup);
            settingTitle = (TextView) v.findViewById(R.id.adapterSettingItem_textView_title);
            settingSlider = (DiscreteSeekBar) v.findViewById(R.id.adapterSettingSliderItem_rangeBar_slider);
            switchButton = (SwitchButton) v.findViewById(R.id.switchButton);
            toggle = (MultiToggle) v.findViewById(R.id.toggle);
            settingSliderValues = (LinearLayout) v.findViewById(R.id.adapterSettingSliderItem_linearLayout_values);
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