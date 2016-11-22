package com.chargemap_beta.android.preferences.library.types;

public class SwitchSetting extends Setting {

    private Boolean isChecked;

    public Boolean getChecked() {
        return isChecked;
    }

    public SwitchSetting setChecked(Boolean checked) {
        isChecked = checked;
        return this;
    }
}
