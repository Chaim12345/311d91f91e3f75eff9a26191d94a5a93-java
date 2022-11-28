package org.bouncycastle.tls;

import org.bouncycastle.util.Arrays;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public class TlsSessionImpl implements TlsSession {

    /* renamed from: a  reason: collision with root package name */
    final byte[] f14900a;

    /* renamed from: b  reason: collision with root package name */
    final SessionParameters f14901b;

    /* renamed from: c  reason: collision with root package name */
    boolean f14902c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TlsSessionImpl(byte[] bArr, SessionParameters sessionParameters) {
        if (bArr == null) {
            throw new IllegalArgumentException("'sessionID' cannot be null");
        }
        if (bArr.length > 32) {
            throw new IllegalArgumentException("'sessionID' cannot be longer than 32 bytes");
        }
        this.f14900a = Arrays.clone(bArr);
        this.f14901b = sessionParameters;
        this.f14902c = bArr.length > 0 && sessionParameters != null;
    }

    @Override // org.bouncycastle.tls.TlsSession
    public synchronized SessionParameters exportSessionParameters() {
        SessionParameters sessionParameters;
        sessionParameters = this.f14901b;
        return sessionParameters == null ? null : sessionParameters.copy();
    }

    @Override // org.bouncycastle.tls.TlsSession
    public synchronized byte[] getSessionID() {
        return this.f14900a;
    }

    @Override // org.bouncycastle.tls.TlsSession
    public synchronized void invalidate() {
        this.f14902c = false;
    }

    @Override // org.bouncycastle.tls.TlsSession
    public synchronized boolean isResumable() {
        return this.f14902c;
    }
}
