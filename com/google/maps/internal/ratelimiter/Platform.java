package com.google.maps.internal.ratelimiter;

import java.util.Locale;
/* loaded from: classes2.dex */
final class Platform {
    private Platform() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String a(double d2) {
        return String.format(Locale.ROOT, "%.4g", Double.valueOf(d2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long b() {
        return System.nanoTime();
    }
}
