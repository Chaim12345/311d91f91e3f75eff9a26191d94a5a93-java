package androidx.camera.core.impl.utils;

import androidx.annotation.NonNull;
/* loaded from: classes.dex */
final class LongRational {
    private final long mDenominator;
    private final long mNumerator;

    /* JADX INFO: Access modifiers changed from: package-private */
    public LongRational(double d2) {
        this((long) (d2 * 10000.0d), 10000L);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LongRational(long j2, long j3) {
        this.mNumerator = j2;
        this.mDenominator = j3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long a() {
        return this.mDenominator;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long b() {
        return this.mNumerator;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public double c() {
        return this.mNumerator / this.mDenominator;
    }

    @NonNull
    public String toString() {
        return this.mNumerator + "/" + this.mDenominator;
    }
}
