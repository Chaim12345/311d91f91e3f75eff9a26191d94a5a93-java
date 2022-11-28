package org.bouncycastle.cms;
/* loaded from: classes3.dex */
public class CMSAttributeTableGenerationException extends CMSRuntimeException {

    /* renamed from: b  reason: collision with root package name */
    Exception f13099b;

    public CMSAttributeTableGenerationException(String str) {
        super(str);
    }

    public CMSAttributeTableGenerationException(String str, Exception exc) {
        super(str);
        this.f13099b = exc;
    }

    @Override // org.bouncycastle.cms.CMSRuntimeException, java.lang.Throwable
    public Throwable getCause() {
        return this.f13099b;
    }

    @Override // org.bouncycastle.cms.CMSRuntimeException
    public Exception getUnderlyingException() {
        return this.f13099b;
    }
}
