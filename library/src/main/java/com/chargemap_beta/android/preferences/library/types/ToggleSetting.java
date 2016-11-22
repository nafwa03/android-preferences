package com.chargemap_beta.android.preferences.library.types;

import java.util.List;

public class ToggleSetting extends Setting {

    private int defaultRadioPosition;
    private List<String> radioSettingItemList;

    public ToggleSetting() {

    }

    public int getDefaultRadioPosition() {
        return defaultRadioPosition;
    }

    public ToggleSetting setDefaultRadioPosition(int defaultRadioPosition) {
        this.defaultRadioPosition = defaultRadioPosition;
        return this;
    }

    public List<String> getRadioSettingItemList() {
        return radioSettingItemList;
    }

    public ToggleSetting setRadioSettingItemList(List<String> radioSettingItemList) {
        this.radioSettingItemList = radioSettingItemList;
        return this;
    }
}
