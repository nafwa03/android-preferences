package com.chargemap_beta.android.preferences.library.types;

public class SliderSetting extends Setting {

    private int defaultValue;
    private int minValue;
    private int maxValue;

    public int getMinValue() {
        return minValue;
    }

    public SliderSetting setMinValue(int minValue) {
        this.minValue = minValue;
        return this;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public SliderSetting setMaxValue(int maxValue) {
        this.maxValue = maxValue;
        return this;
    }

    public int getDefaultValue() {
        return defaultValue;
    }

    public Setting setDefaultValue(int defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }
}
