package org.bouncycastle.tls;

import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Strings;
/* loaded from: classes4.dex */
public class BasicTlsPSKIdentity implements TlsPSKIdentity {

    /* renamed from: a  reason: collision with root package name */
    protected byte[] f14691a;

    /* renamed from: b  reason: collision with root package name */
    protected byte[] f14692b;

    public BasicTlsPSKIdentity(String str, byte[] bArr) {
        this.f14691a = Strings.toUTF8ByteArray(str);
        this.f14692b = Arrays.clone(bArr);
    }

    public BasicTlsPSKIdentity(byte[] bArr, byte[] bArr2) {
        this.f14691a = Arrays.clone(bArr);
        this.f14692b = Arrays.clone(bArr2);
    }

    @Override // org.bouncycastle.tls.TlsPSKIdentity
    public byte[] getPSK() {
        return Arrays.clone(this.f14692b);
    }

    @Override // org.bouncycastle.tls.TlsPSKIdentity
    public byte[] getPSKIdentity() {
        return this.f14691a;
    }

    @Override // org.bouncycastle.tls.TlsPSKIdentity
    public void notifyIdentityHint(byte[] bArr) {
    }

    @Override // org.bouncycastle.tls.TlsPSKIdentity
    public void skipIdentityHint() {
    }
}
