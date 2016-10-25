package com.chargemap_beta.android.preferences.library.callbacks;

import com.chargemap_beta.android.preferences.library.SettingAdapter;

import java.io.Serializable;

public abstract class SettingCallback implements Serializable {
    public abstract void onClick(SettingAdapter.VH vh);
}
