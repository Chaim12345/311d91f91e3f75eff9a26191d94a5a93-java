package org.bouncycastle.jce.provider;

import java.security.cert.CRLException;
/* loaded from: classes3.dex */
class ExtCRLException extends CRLException {

    /* renamed from: a  reason: collision with root package name */
    Throwable f13814a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ExtCRLException(String str, Throwable th) {
        super(str);
        this.f13814a = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.f13814a;
    }
}
