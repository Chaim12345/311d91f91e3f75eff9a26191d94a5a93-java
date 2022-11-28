package org.bouncycastle.x509.util;
/* loaded from: classes4.dex */
public class StreamParsingException extends Exception {

    /* renamed from: a  reason: collision with root package name */
    Throwable f15151a;

    public StreamParsingException(String str, Throwable th) {
        super(str);
        this.f15151a = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.f15151a;
    }
}
