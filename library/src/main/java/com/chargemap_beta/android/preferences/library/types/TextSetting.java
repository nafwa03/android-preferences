package com.chargemap_beta.android.preferences.library.types;

import android.os.Parcel;
import android.os.Parcelable;

public class TextSetting extends Setting {

    /**
     * PARCELABLE
     */

    public static final Parcelable.Creator CREATOR = new Creator() {
        @Override
        public TextSetting createFromParcel(Parcel parcel) {
            return new TextSetting(parcel);
        }

        @Override
        public TextSetting[] newArray(int size) {
            return new TextSetting[size];
        }
    };

    public TextSetting() {

    }

    public TextSetting(Parcel in) {
        super.readFromParcel(in);
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
