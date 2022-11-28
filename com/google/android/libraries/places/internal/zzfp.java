package com.google.android.libraries.places.internal;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import androidx.annotation.ColorInt;
/* loaded from: classes2.dex */
public final class zzfp {
    @ColorInt
    public static int zza(@ColorInt int i2, @ColorInt int i3, @ColorInt int i4) {
        return zzc(i2, i3, i4) ? i4 : i3;
    }

    public static void zzb(ImageView imageView, @ColorInt int i2) {
        Drawable drawable = imageView.getDrawable();
        int rgb = Color.rgb(Color.red(i2), Color.green(i2), Color.blue(i2));
        Drawable mutate = drawable.mutate();
        mutate.setColorFilter(rgb, PorterDuff.Mode.SRC_ATOP);
        mutate.setAlpha(Color.alpha(i2));
    }

    public static boolean zzc(@ColorInt int i2, @ColorInt int i3, @ColorInt int i4) {
        double zzf = zzf(i2);
        double zze = zze(zzf(i3), zzf);
        return zze <= 3.0d && zze <= zze(zzf(i4), zzf);
    }

    private static double zzd(double d2) {
        return d2 <= 0.03928d ? d2 / 12.92d : Math.pow((d2 + 0.055d) / 1.055d, 2.4d);
    }

    private static double zze(double d2, double d3) {
        return Math.round(((Math.max(d2, d3) + 0.05d) / (Math.min(d2, d3) + 0.05d)) * 100.0d) / 100.0d;
    }

    private static double zzf(@ColorInt int i2) {
        return (zzd(Color.red(i2) / 255.0d) * 0.2126d) + (zzd(Color.green(i2) / 255.0d) * 0.7152d) + (zzd(Color.blue(i2) / 255.0d) * 0.0722d);
    }
}
