package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.DrawableCompat;
import com.google.android.gms.base.R;
import com.google.android.gms.common.util.DeviceProperties;
/* loaded from: classes.dex */
public final class zaaa extends Button {
    public zaaa(Context context, @Nullable AttributeSet attributeSet) {
        super(context, null, 16842824);
    }

    private static final int zab(int i2, int i3, int i4, int i5) {
        if (i2 != 0) {
            if (i2 != 1) {
                if (i2 == 2) {
                    return i5;
                }
                StringBuilder sb = new StringBuilder(33);
                sb.append("Unknown color scheme: ");
                sb.append(i2);
                throw new IllegalStateException(sb.toString());
            }
            return i4;
        }
        return i3;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:24:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void zaa(Resources resources, int i2, int i3) {
        int i4;
        setTypeface(Typeface.DEFAULT_BOLD);
        setTextSize(14.0f);
        int i5 = (int) ((resources.getDisplayMetrics().density * 48.0f) + 0.5f);
        setMinHeight(i5);
        setMinWidth(i5);
        int i6 = R.drawable.common_google_signin_btn_icon_dark;
        int i7 = R.drawable.common_google_signin_btn_icon_light;
        int zab = zab(i3, i6, i7, i7);
        int i8 = R.drawable.common_google_signin_btn_text_dark;
        int i9 = R.drawable.common_google_signin_btn_text_light;
        int zab2 = zab(i3, i8, i9, i9);
        if (i2 == 0 || i2 == 1) {
            zab = zab2;
        } else if (i2 != 2) {
            StringBuilder sb = new StringBuilder(32);
            sb.append("Unknown button size: ");
            sb.append(i2);
            throw new IllegalStateException(sb.toString());
        }
        Drawable wrap = DrawableCompat.wrap(resources.getDrawable(zab));
        DrawableCompat.setTintList(wrap, resources.getColorStateList(R.color.common_google_signin_btn_tint));
        DrawableCompat.setTintMode(wrap, PorterDuff.Mode.SRC_ATOP);
        setBackgroundDrawable(wrap);
        int i10 = R.color.common_google_signin_btn_text_dark;
        int i11 = R.color.common_google_signin_btn_text_light;
        setTextColor((ColorStateList) Preconditions.checkNotNull(resources.getColorStateList(zab(i3, i10, i11, i11))));
        if (i2 == 0) {
            i4 = R.string.common_signin_button_text;
        } else if (i2 != 1) {
            if (i2 != 2) {
                StringBuilder sb2 = new StringBuilder(32);
                sb2.append("Unknown button size: ");
                sb2.append(i2);
                throw new IllegalStateException(sb2.toString());
            }
            setText((CharSequence) null);
            setTransformationMethod(null);
            if (DeviceProperties.isWearable(getContext())) {
                return;
            }
            setGravity(19);
            return;
        } else {
            i4 = R.string.common_signin_button_text_long;
        }
        setText(resources.getString(i4));
        setTransformationMethod(null);
        if (DeviceProperties.isWearable(getContext())) {
        }
    }
}
