package org.bouncycastle.asn1;

import java.io.OutputStream;
/* loaded from: classes3.dex */
public abstract class ASN1Generator {

    /* renamed from: a  reason: collision with root package name */
    protected OutputStream f12691a;

    public ASN1Generator(OutputStream outputStream) {
        this.f12691a = outputStream;
    }

    public abstract OutputStream getRawOutputStream();
}
