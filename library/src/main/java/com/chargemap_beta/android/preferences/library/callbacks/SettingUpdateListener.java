package com.chargemap_beta.android.preferences.library.callbacks;

import java.io.Serializable;

/**
 * Created by rbertin on 21/10/2016.
 */

public abstract class SettingUpdateListener implements Serializable {
    public abstract void onUpdate(int position);
}