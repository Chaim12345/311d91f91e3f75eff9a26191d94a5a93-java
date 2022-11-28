package org.bouncycastle.cms;
/* loaded from: classes3.dex */
public class CMSRuntimeException extends RuntimeException {

    /* renamed from: a  reason: collision with root package name */
    Exception f13134a;

    public CMSRuntimeException(String str) {
        super(str);
    }

    public CMSRuntimeException(String str, Exception exc) {
        super(str);
        this.f13134a = exc;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.f13134a;
    }

    public Exception getUnderlyingException() {
        return this.f13134a;
    }
}
