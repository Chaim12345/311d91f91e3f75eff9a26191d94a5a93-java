package com.google.android.gms.common.api.internal;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zabn implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ int f5678a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zabq f5679b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zabn(zabq zabqVar, int i2) {
        this.f5679b = zabqVar;
        this.f5678a = i2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f5679b.zaH(this.f5678a);
    }
}
