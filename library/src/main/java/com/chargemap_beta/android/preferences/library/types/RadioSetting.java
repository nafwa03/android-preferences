package com.chargemap_beta.android.preferences.library.types;

import java.util.List;

public class RadioSetting extends Setting {

    private int defaultRadioPosition;
    private List<String> radioSettingItemList;

    public RadioSetting() {

    }

    public int getDefaultRadioPosition() {
        return defaultRadioPosition;
    }

    public RadioSetting setDefaultRadioPosition(int defaultRadioPosition) {
        this.defaultRadioPosition = defaultRadioPosition;
        return this;
    }

    public List<String> getRadioSettingItemList() {
        return radioSettingItemList;
    }

    public RadioSetting setRadioSettingItemList(List<String> radioSettingItemList) {
        this.radioSettingItemList = radioSettingItemList;
        return this;
    }
}
