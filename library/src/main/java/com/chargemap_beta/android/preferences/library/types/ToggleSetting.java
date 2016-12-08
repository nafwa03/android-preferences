package com.chargemap_beta.android.preferences.library.types;

import java.util.List;

public class ToggleSetting extends Setting {

    private int defaultRadioPosition;
    private List<String> radioSettingItemList;

    private int radius;
    private int padding;
    private int borderWidth;

    private int activeBackgroundColor;
    private int inactiveBackgroundColor;
    private int activeTextColor;
    private int inactiveTextColor;

    private int borderColor;

    public ToggleSetting() {
        activeBackgroundColor = 0;
        inactiveBackgroundColor = 0;
        activeTextColor = 0;
        inactiveTextColor = 0;

        borderColor = 0;

        padding = 0;
        radius = 0;
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

    public int getRadius() {
        return radius;
    }

    public ToggleSetting setRadius(int radius) {
        this.radius = radius;
        return this;
    }

    public int getPadding() {
        return padding;
    }

    public ToggleSetting setPadding(int padding) {
        this.padding = padding;
        return this;
    }

    public int getActiveBackgroundColor() {
        return activeBackgroundColor;
    }

    public ToggleSetting setActiveBackgroundColor(int activeBackgroundColor) {
        this.activeBackgroundColor = activeBackgroundColor;
        return this;
    }

    public int getInactiveBackgroundColor() {
        return inactiveBackgroundColor;
    }

    public ToggleSetting setInactiveBackgroundColor(int inactiveBackgroundColor) {
        this.inactiveBackgroundColor = inactiveBackgroundColor;
        return this;
    }

    public int getActiveTextColor() {
        return activeTextColor;
    }

    public ToggleSetting setActiveTextColor(int activeTextColor) {
        this.activeTextColor = activeTextColor;
        return this;
    }

    public int getInactiveTextColor() {
        return inactiveTextColor;
    }

    public ToggleSetting setInactiveTextColor(int inactiveTextColor) {
        this.inactiveTextColor = inactiveTextColor;
        return this;
    }

    public int getBorderColor() {
        return borderColor;
    }

    public ToggleSetting setBorderColor(int borderColor) {
        this.borderColor = borderColor;
        return this;
    }

    public int getBorderWidth() {
        return borderWidth;
    }

    public ToggleSetting setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
        return this;
    }
}
