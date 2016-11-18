package com.chargemap_beta.android.preferences.library.types;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;

public abstract class Setting implements Serializable {

    public static String UNIQUE_KEY = "CHARGEMAP_PREFERENCES_";

    private String label;

    private String icon;

    private Boolean iconIsSVG = false;

    private Boolean iconIsDrawable = false;

    private String title;

    private String subtitle;

    private transient Context context;

    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

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

    public void saveValue(String value) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(UNIQUE_KEY + getTitle(), value);
        editor.apply();
    }

    public String findValue() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(UNIQUE_KEY + getTitle(), "null");
    }

    public Setting setIconDrawable(Drawable iconDrawable) {
        iconIsDrawable = true;

        setIcon(context.getCacheDir() + File.separator + UNIQUE_KEY + getTitle());

        Bitmap bitmap = drawableToBitmap(iconDrawable);

        FileOutputStream out = null;
        try {
            out = new FileOutputStream(icon);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out); // bmp is your Bitmap instance
            // PNG is a lossless format, the compression factor (100) is ignored
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return this;
    }

    public String getIcon() {
        return icon;
    }

    public Setting setIcon(String icon) {
        this.icon = icon;
        return this;
    }

    public Setting setContext(Context context) {
        this.context = context;
        return this;
    }

    public Boolean getIconIsSVG() {
        return iconIsSVG;
    }

    public Setting setIconIsSVG(Boolean iconIsSVG) {
        this.iconIsSVG = iconIsSVG;
        return this;
    }

    public Boolean getIconIsDrawable() {
        return iconIsDrawable;
    }

    public Setting setIconIsDrawable(Boolean iconIsDrawable) {
        this.iconIsDrawable = iconIsDrawable;
        return this;
    }
}
