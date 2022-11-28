package com.psa.mym.mycitroenconnect.views.custom_seek_bar;

import android.widget.SeekBar;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public interface CustomSeekBarListener {

    /* loaded from: classes3.dex */
    public static final class DefaultImpls {
        public static void onProgressChanged(@NotNull CustomSeekBarListener customSeekBarListener, @Nullable SeekBar seekBar, double d2, @NotNull String formattedValue, boolean z) {
            Intrinsics.checkNotNullParameter(formattedValue, "formattedValue");
        }

        public static void onStartTrackingTouch(@NotNull CustomSeekBarListener customSeekBarListener, @Nullable SeekBar seekBar, double d2, @NotNull String formattedValue) {
            Intrinsics.checkNotNullParameter(formattedValue, "formattedValue");
        }

        public static void onStopTrackingTouch(@NotNull CustomSeekBarListener customSeekBarListener, @Nullable SeekBar seekBar, double d2, @NotNull String formattedValue) {
            Intrinsics.checkNotNullParameter(formattedValue, "formattedValue");
        }
    }

    void onProgressChanged(@Nullable SeekBar seekBar, double d2, @NotNull String str, boolean z);

    void onStartTrackingTouch(@Nullable SeekBar seekBar, double d2, @NotNull String str);

    void onStopTrackingTouch(@Nullable SeekBar seekBar, double d2, @NotNull String str);
}
