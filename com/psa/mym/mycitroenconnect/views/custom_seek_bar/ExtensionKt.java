package com.psa.mym.mycitroenconnect.views.custom_seek_bar;

import android.view.View;
import android.widget.SeekBar;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt__MathJVMKt;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class ExtensionKt {
    public static final boolean isMaxProgress(@NotNull SeekBar seekBar) {
        Intrinsics.checkNotNullParameter(seekBar, "<this>");
        return seekBar.getProgress() >= seekBar.getMax();
    }

    public static final boolean isMinProgress(@NotNull SeekBar seekBar) {
        Intrinsics.checkNotNullParameter(seekBar, "<this>");
        return seekBar.getProgress() <= 0;
    }

    public static final boolean isShow(@NotNull View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        return view.getVisibility() == 0;
    }

    public static final void splitTrack(@NotNull SeekBar seekBar, boolean z) {
        Intrinsics.checkNotNullParameter(seekBar, "<this>");
        seekBar.setSplitTrack(z);
    }

    public static final int toInt(double d2, boolean z) {
        int roundToInt;
        if (z) {
            roundToInt = MathKt__MathJVMKt.roundToInt(d2);
            return roundToInt;
        }
        return (int) d2;
    }

    public static final void visibleFromBoolean(@NotNull View view, boolean z) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        view.setVisibility(z ? 0 : 8);
    }
}
