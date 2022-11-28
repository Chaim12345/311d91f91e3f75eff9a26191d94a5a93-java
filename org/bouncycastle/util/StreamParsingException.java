package org.bouncycastle.util;
/* loaded from: classes4.dex */
public class StreamParsingException extends Exception {

    /* renamed from: a  reason: collision with root package name */
    Throwable f15101a;

    public StreamParsingException(String str, Throwable th) {
        super(str);
        this.f15101a = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.f15101a;
    }
}
