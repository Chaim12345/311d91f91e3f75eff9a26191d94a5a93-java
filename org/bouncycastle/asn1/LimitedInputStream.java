package org.bouncycastle.asn1;

import java.io.InputStream;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public abstract class LimitedInputStream extends InputStream {
    private int _limit;

    /* renamed from: a  reason: collision with root package name */
    protected final InputStream f12749a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public LimitedInputStream(InputStream inputStream, int i2) {
        this.f12749a = inputStream;
        this._limit = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int a() {
        return this._limit;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void b(boolean z) {
        InputStream inputStream = this.f12749a;
        if (inputStream instanceof IndefiniteLengthInputStream) {
            ((IndefiniteLengthInputStream) inputStream).c(z);
        }
    }
}
