package org.bouncycastle.tsp;
/* loaded from: classes4.dex */
public class TSPException extends Exception {

    /* renamed from: a  reason: collision with root package name */
    Throwable f15077a;

    public TSPException(String str) {
        super(str);
    }

    public TSPException(String str, Throwable th) {
        super(str);
        this.f15077a = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.f15077a;
    }

    public Exception getUnderlyingException() {
        return (Exception) this.f15077a;
    }
}
