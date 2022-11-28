package org.bouncycastle.cms;
/* loaded from: classes3.dex */
public class CMSException extends Exception {

    /* renamed from: a  reason: collision with root package name */
    Exception f13133a;

    public CMSException(String str) {
        super(str);
    }

    public CMSException(String str, Exception exc) {
        super(str);
        this.f13133a = exc;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.f13133a;
    }

    public Exception getUnderlyingException() {
        return this.f13133a;
    }
}
