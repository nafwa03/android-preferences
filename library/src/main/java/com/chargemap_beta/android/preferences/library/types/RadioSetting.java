package com.chargemap_beta.android.preferences.library.types;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class RadioSetting extends Setting {

    /**
     * PARCELABLE
     */

    public static final Parcelable.Creator CREATOR = new Creator() {
        @Override
        public RadioSetting createFromParcel(Parcel parcel) {
            return new RadioSetting(parcel);
        }

        @Override
        public RadioSetting[] newArray(int size) {
            return new RadioSetting[size];
        }
    };
    private int defaultRadioPosition;
    private List<String> radioSettingItemList;

    public RadioSetting() {

    }

    public RadioSetting(Parcel in) {
        super.readFromParcel(in);

        setDefaultRadioPosition(in.readInt());
        setRadioSettingItemList(in.readArrayList(String.class.getClassLoader()));
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

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);

        parcel.writeInt(defaultRadioPosition);
        parcel.writeArray(radioSettingItemList.toArray());
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
