package com.chargemap_beta.android.preferences.library.types;

import android.os.Parcel;
import android.os.Parcelable;

public class SliderSetting extends Setting {

    /**
     * PARCELABLE
     */

    public static final Parcelable.Creator CREATOR = new Creator() {
        @Override
        public SliderSetting createFromParcel(Parcel parcel) {
            return new SliderSetting(parcel);
        }

        @Override
        public SliderSetting[] newArray(int size) {
            return new SliderSetting[size];
        }
    };
    private int defaultValue;
    private int minValue;
    private int maxValue;

    public SliderSetting() {

    }

    public SliderSetting(Parcel in) {
        super.readFromParcel(in);

        setDefaultValue(in.readInt());
        setMinValue(in.readInt());
        setMaxValue(in.readInt());
    }

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

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);

        parcel.writeInt(getDefaultValue());
        parcel.writeInt(getMinValue());
        parcel.writeInt(getMaxValue());
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
