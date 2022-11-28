package org.bouncycastle.openssl;

import java.io.IOException;
/* loaded from: classes4.dex */
public class PEMException extends IOException {

    /* renamed from: a  reason: collision with root package name */
    Exception f14397a;

    public PEMException(String str) {
        super(str);
    }

    public PEMException(String str, Exception exc) {
        super(str);
        this.f14397a = exc;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.f14397a;
    }

    public Exception getUnderlyingException() {
        return this.f14397a;
    }
}
