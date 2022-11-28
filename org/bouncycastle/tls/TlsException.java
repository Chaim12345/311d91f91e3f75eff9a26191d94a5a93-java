package org.bouncycastle.tls;

import java.io.IOException;
/* loaded from: classes4.dex */
public class TlsException extends IOException {

    /* renamed from: a  reason: collision with root package name */
    protected Throwable f14848a;

    public TlsException(String str) {
        this(str, null);
    }

    public TlsException(String str, Throwable th) {
        super(str);
        this.f14848a = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.f14848a;
    }
}
