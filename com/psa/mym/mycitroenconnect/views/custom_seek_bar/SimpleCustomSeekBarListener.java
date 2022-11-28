package com.psa.mym.mycitroenconnect.views.custom_seek_bar;

import android.widget.SeekBar;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public class SimpleCustomSeekBarListener implements CustomSeekBarListener {
    @Override // com.psa.mym.mycitroenconnect.views.custom_seek_bar.CustomSeekBarListener
    public void onProgressChanged(@Nullable SeekBar seekBar, double d2, @NotNull String formattedValue, boolean z) {
        Intrinsics.checkNotNullParameter(formattedValue, "formattedValue");
    }

    @Override // com.psa.mym.mycitroenconnect.views.custom_seek_bar.CustomSeekBarListener
    public void onStartTrackingTouch(@Nullable SeekBar seekBar, double d2, @NotNull String formattedValue) {
        Intrinsics.checkNotNullParameter(formattedValue, "formattedValue");
    }

    @Override // com.psa.mym.mycitroenconnect.views.custom_seek_bar.CustomSeekBarListener
    public void onStopTrackingTouch(@Nullable SeekBar seekBar, double d2, @NotNull String formattedValue) {
        Intrinsics.checkNotNullParameter(formattedValue, "formattedValue");
    }
}
