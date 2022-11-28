package org.bouncycastle.tsp;

import java.io.IOException;
/* loaded from: classes4.dex */
public class TSPIOException extends IOException {

    /* renamed from: a  reason: collision with root package name */
    Throwable f15078a;

    public TSPIOException(String str) {
        super(str);
    }

    public TSPIOException(String str, Throwable th) {
        super(str);
        this.f15078a = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.f15078a;
    }

    public Exception getUnderlyingException() {
        return (Exception) this.f15078a;
    }
}
