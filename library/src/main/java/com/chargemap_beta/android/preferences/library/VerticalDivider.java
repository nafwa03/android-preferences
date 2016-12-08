package com.chargemap_beta.android.preferences.library;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * Created by rbertin on 17/10/2016.
 */

public class VerticalDivider extends RecyclerView.ItemDecoration {

    private Drawable divider;
    private int height;
    private int columnNumber;
    private Boolean extBorders;

    public VerticalDivider(Context context, int color) {
        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        if (context != null) {
            shape.setColor(color);
        }

        divider = shape;

        height = (int) convertDpToPixel(1);

        extBorders = true;

        columnNumber = 1;
    }

    public VerticalDivider setBorders(Boolean borders) {
        extBorders = borders;
        return this;
    }

    public VerticalDivider setColumnNumber(int nb) {
        columnNumber = nb;
        return this;
    }

    public static float convertPixelsToDp(float px) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float dp = px / (metrics.densityDpi / 160f);
        return Math.round(dp);
    }

    public static float convertDpToPixel(float dp) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return Math.round(px);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        drawTopAndBottom(c, parent);
    }

    public void drawTopAndBottom(Canvas c, RecyclerView parent) {

        for (int i = 0; i < parent.getChildCount(); i++) {

            final View child = parent.getChildAt(i);

            if (i < columnNumber && extBorders) {
                drawTop(c, child);
            }

            if (i != parent.getChildCount() - 1 || (i == parent.getChildCount() - 1 && extBorders)) {
                drawBottom(c, child);
            }
        }
    }

    private void drawTop(Canvas c, View child) {
        final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

        int top = child.getTop() - params.topMargin;
        int bottom = top + height;

        divider.setBounds(child.getLeft() - params.leftMargin, top, child.getRight() + params.rightMargin, bottom);
        divider.draw(c);
    }

    private void drawBottom(Canvas c, View child) {
        final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

        int top = child.getBottom() + params.bottomMargin - height;
        int bottom = top + height;

        divider.setBounds(child.getLeft() - params.leftMargin, top, child.getRight() + params.rightMargin, bottom);
        divider.draw(c);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(0, 0, 0, 0);
    }
}