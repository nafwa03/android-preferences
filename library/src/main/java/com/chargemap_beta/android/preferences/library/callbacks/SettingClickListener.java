package com.chargemap_beta.android.preferences.library.callbacks;

import java.io.Serializable;

/**
 * Created by rbertin on 21/10/2016.
 */

public abstract class SettingClickListener implements Serializable {
    public abstract void onClick(int position);
}