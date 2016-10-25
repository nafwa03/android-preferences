package com.chargemap_beta.android.preferences.library.types;

import android.os.Parcel;
import android.os.Parcelable;

public class CheckBoxSetting extends Setting {

    /**
     * PARCELABLE
     */

    public static final Parcelable.Creator CREATOR = new Creator() {
        @Override
        public CheckBoxSetting createFromParcel(Parcel parcel) {
            return new CheckBoxSetting(parcel);
        }

        @Override
        public CheckBoxSetting[] newArray(int size) {
            return new CheckBoxSetting[size];
        }
    };
    private Boolean isChecked;

    public CheckBoxSetting() {

    }

    public CheckBoxSetting(Parcel in) {
        super.readFromParcel(in);

        setChecked(in.readInt() == 1);
    }

    public Boolean getChecked() {
        return isChecked;
    }

    public CheckBoxSetting setChecked(Boolean checked) {
        isChecked = checked;
        return this;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);

        parcel.writeInt(isChecked ? 1 : 0);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
