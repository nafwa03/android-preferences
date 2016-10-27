package com.chargemap_beta.android.preferences.library.types;

public class CheckBoxSetting extends Setting {

    private Boolean isChecked;

    public Boolean getChecked() {
        return isChecked;
    }

    public CheckBoxSetting setChecked(Boolean checked) {
        isChecked = checked;
        return this;
    }
}
