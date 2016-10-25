package com.chargemap_beta.android.preferences.library.types;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.preference.PreferenceManager;

import com.chargemap_beta.android.preferences.library.callbacks.SettingCallback;


public abstract class Setting implements Parcelable {

    private String label;

    private Bitmap icon;

    private String title;

    private String subtitle;

    private transient SettingCallback callback;

    public String getLabel() {
        return label;
    }

    public Setting setLabel(String label) {
        this.label = label;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Setting setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public Setting setSubtitle(String subtitle) {
        this.subtitle = subtitle;
        return this;
    }

    public SettingCallback getCallback() {
        return callback;
    }

    public Setting setCallback(SettingCallback callback) {
        this.callback = callback;
        return this;
    }

    public void saveValue(Activity activity, String value) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(getTitle(), value);
        editor.apply();
    }

    public String findValue(Activity activity) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(activity);
        return preferences.getString(getTitle(), "null");
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(getLabel());
        parcel.writeString(getTitle());
        parcel.writeString(getSubtitle());

        parcel.writeParcelable(getIcon(), flags);
    }

    public void readFromParcel(Parcel in) {
        setLabel(in.readString());
        setTitle(in.readString());
        setSubtitle(in.readString());

        setIcon((Bitmap) in.readParcelable(Bitmap.class.getClassLoader()));
    }

    public void setIcon(Bitmap icon) {
        this.icon = icon;
    }

    public Setting setIconDrawable(Drawable iconDrawable) {
        this.icon = drawableToBitmap(iconDrawable);
        return this;
    }

    public static Bitmap drawableToBitmap (Drawable drawable) {
        Bitmap bitmap;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if(bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if(drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    public Bitmap getIcon() {
        return icon;
    }
}
