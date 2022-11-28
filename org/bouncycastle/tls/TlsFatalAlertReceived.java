package org.bouncycastle.tls;
/* loaded from: classes4.dex */
public class TlsFatalAlertReceived extends TlsException {

    /* renamed from: b  reason: collision with root package name */
    protected short f14850b;

    public TlsFatalAlertReceived(short s2) {
        super(AlertDescription.getText(s2));
        this.f14850b = s2;
    }

    public short getAlertDescription() {
        return this.f14850b;
    }
}
