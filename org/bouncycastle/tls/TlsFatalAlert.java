package org.bouncycastle.tls;
/* loaded from: classes4.dex */
public class TlsFatalAlert extends TlsException {

    /* renamed from: b  reason: collision with root package name */
    protected short f14849b;

    public TlsFatalAlert(short s2) {
        this(s2, (String) null);
    }

    public TlsFatalAlert(short s2, String str) {
        this(s2, str, null);
    }

    public TlsFatalAlert(short s2, String str, Throwable th) {
        super(getMessage(s2, str), th);
        this.f14849b = s2;
    }

    public TlsFatalAlert(short s2, Throwable th) {
        this(s2, null, th);
    }

    private static String getMessage(short s2, String str) {
        String text = AlertDescription.getText(s2);
        if (str != null) {
            return text + "; " + str;
        }
        return text;
    }

    public short getAlertDescription() {
        return this.f14849b;
    }
}
