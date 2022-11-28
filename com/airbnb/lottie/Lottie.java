package com.airbnb.lottie;

import androidx.annotation.NonNull;
/* loaded from: classes.dex */
public class Lottie {
    private Lottie() {
    }

    public static void initialize(@NonNull LottieConfig lottieConfig) {
        L.setFetcher(lottieConfig.f4374a);
        L.setCacheProvider(lottieConfig.f4375b);
        L.setTraceEnabled(lottieConfig.f4376c);
    }
}
