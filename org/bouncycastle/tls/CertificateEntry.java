package org.bouncycastle.tls;

import java.util.Hashtable;
import java.util.Objects;
import org.bouncycastle.tls.crypto.TlsCertificate;
/* loaded from: classes4.dex */
public class CertificateEntry {

    /* renamed from: a  reason: collision with root package name */
    protected final TlsCertificate f14697a;

    /* renamed from: b  reason: collision with root package name */
    protected final Hashtable f14698b;

    public CertificateEntry(TlsCertificate tlsCertificate, Hashtable hashtable) {
        Objects.requireNonNull(tlsCertificate, "'certificate' cannot be null");
        this.f14697a = tlsCertificate;
        this.f14698b = hashtable;
    }

    public TlsCertificate getCertificate() {
        return this.f14697a;
    }

    public Hashtable getExtensions() {
        return this.f14698b;
    }
}
