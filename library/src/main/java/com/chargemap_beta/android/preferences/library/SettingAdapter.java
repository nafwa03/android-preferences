package com.chargemap_beta.android.preferences.library;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.chargemap_beta.android.preferences.library.callbacks.ClickListener;
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

    public SettingAdapter(Activity baseActivity) {
        this.baseActivity = baseActivity;
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

    public void setItems(List<Setting> settingList){
        settings = settingList;
    }

    @Override
    public void onBindViewHolder(final VH vh, int position) {

        final Setting setting = settings.get(position);

        if(setting.getIcon() != null){
            vh.icon.setImageBitmap(setting.getIcon());
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
            }else{
                vh.subtitle.setText(setting.getSubtitle());
            }
        }

        if (settings.get(position) instanceof TextSetting) {

            vh.clickListener = new ClickListener() {
                @Override
                public void onClick(View v, int position, boolean isLongClick) {

                }
            };

        } else if (settings.get(position) instanceof CheckBoxSetting) {

            final CheckBoxSetting checkBoxSetting = (CheckBoxSetting) setting;

            if (checkBoxSetting.findValue(baseActivity) != null) {
                // No preference found

                vh.settingCheckbox.setChecked(checkBoxSetting.getChecked());
            } else {
                // we found a preference so we check the checkbox if needed

                vh.settingCheckbox.setChecked(Boolean.parseBoolean(checkBoxSetting.findValue(baseActivity)));
            }

            vh.clickListener = new ClickListener() {
                @Override
                public void onClick(View v, int position, boolean isLongClick) {
                    vh.settingCheckbox.setChecked(!vh.settingCheckbox.isChecked());
                }
            };

            vh.settingCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (checkBoxSetting.getCallback() != null) {
                        checkBoxSetting.getCallback().onClick(vh);
                    }

                    setting.saveValue(baseActivity, b ? "true" : "false");
                }
            });

        } else if (settings.get(position) instanceof SliderSetting) {

            SliderSetting sliderSetting = (SliderSetting) setting;

            if (sliderSetting.findValue(baseActivity) != null) {
                // No preference found
                vh.settingSlider.setProgress(sliderSetting.getDefaultValue());

            } else {
                // We found a preference so we adjust the slider
                vh.settingSlider.setProgress(Integer.parseInt(sliderSetting.findValue(baseActivity)));
            }

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
                    setting.getCallback().onClick(vh);
                    setting.saveValue(baseActivity, "" + seekBar.getProgress());
                }
            });

        } else if (settings.get(position) instanceof RadioSetting) {

            RadioSetting radioSetting = (RadioSetting) setting;

            int checkedRadioId;

            if (radioSetting.findValue(baseActivity) != null) {
                // No preference found
                checkedRadioId = radioSetting.getDefaultRadioPosition();

            } else {
                // We found a preference so we check the radio
                checkedRadioId = Integer.parseInt(radioSetting.findValue(baseActivity));
            }

            for (int i = 0; i < radioSetting.getRadioSettingItemList().size(); i++) {

                RadioButton radioButton = (RadioButton) LayoutInflater.from(baseActivity.getApplicationContext()).inflate(R.layout.adapter_setting_radiogroup_radio_radiobutton, null, false);
                radioButton.setId(i);
                radioButton.setChecked(i == checkedRadioId);
                vh.radioGroup.addView(radioButton);

                TextView textView = (TextView) LayoutInflater.from(baseActivity.getApplicationContext()).inflate(R.layout.adapter_setting_radiogroup_radio_label, null, false);
                textView.setText(((RadioSetting) setting).getRadioSettingItemList().get(i));
                vh.radioRow.addView(textView);
            }

            radioSetting.saveValue(baseActivity, "" + checkedRadioId);

            vh.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    setting.saveValue(baseActivity, "" + i);
                }
            });

            vh.clickListener = new ClickListener() {
                @Override
                public void onClick(View v, int position, boolean isLongClick) {
                    if (setting.getCallback() != null) {
                        setting.getCallback().onClick(vh);
                    }
                }
            };
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

        ClickListener clickListener;

        public VH(View v) {
            super(v);

            icon = (ImageView) v.findViewById(R.id.adapterSettingItem_imageView_icon) ;
            subtitle = (TextView) v.findViewById(R.id.adapterSettingItem_textView_subtitle) ;
            settingCheckbox = (CheckBox) v.findViewById(R.id.adapterSettingCheckboxItem_checkBox) ;
            radioGroup = (RadioGroup) v.findViewById(R.id.adapterSettingRadioItem_radioGroup) ;
            settingTitle = (TextView) v.findViewById(R.id.adapterSettingItem_textView_title) ;
            settingSlider = (DiscreteSeekBar) v.findViewById(R.id.adapterSettingSliderItem_rangeBar_slider) ;
            title = (TextView) v.findViewById(R.id.adapterSettingItem_textView_label) ;
            radioRow = (LinearLayout) v.findViewById(R.id.adapterSettingRadioItem_linearLayout_labelContainer) ;

            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                clickListener.onClick(v, getAdapterPosition(), false);
            }
        }
    }
}