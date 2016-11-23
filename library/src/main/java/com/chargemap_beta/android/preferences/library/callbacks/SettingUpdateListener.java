package com.chargemap_beta.android.preferences.library.callbacks;

import com.chargemap_beta.android.preferences.library.types.Setting;

import java.io.Serializable;

/**
 * Created by rbertin on 21/10/2016.
 */

public abstract class SettingUpdateListener implements Serializable {
    public abstract void onUpdate(Setting setting, String value, int position);
}