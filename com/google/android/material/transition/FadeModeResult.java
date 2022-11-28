package com.google.android.material.transition;
/* loaded from: classes2.dex */
class FadeModeResult {

    /* renamed from: a  reason: collision with root package name */
    final int f7646a;

    /* renamed from: b  reason: collision with root package name */
    final int f7647b;

    /* renamed from: c  reason: collision with root package name */
    final boolean f7648c;

    private FadeModeResult(int i2, int i3, boolean z) {
        this.f7646a = i2;
        this.f7647b = i3;
        this.f7648c = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static FadeModeResult a(int i2, int i3) {
        return new FadeModeResult(i2, i3, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static FadeModeResult b(int i2, int i3) {
        return new FadeModeResult(i2, i3, false);
    }
}
