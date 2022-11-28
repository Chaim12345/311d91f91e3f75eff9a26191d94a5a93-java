package org.bouncycastle.tls;

import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Strings;
/* loaded from: classes4.dex */
public class BasicTlsSRPIdentity implements TlsSRPIdentity {

    /* renamed from: a  reason: collision with root package name */
    protected byte[] f14693a;

    /* renamed from: b  reason: collision with root package name */
    protected byte[] f14694b;

    public BasicTlsSRPIdentity(String str, String str2) {
        this.f14693a = Strings.toUTF8ByteArray(str);
        this.f14694b = Strings.toUTF8ByteArray(str2);
    }

    public BasicTlsSRPIdentity(byte[] bArr, byte[] bArr2) {
        this.f14693a = Arrays.clone(bArr);
        this.f14694b = Arrays.clone(bArr2);
    }

    @Override // org.bouncycastle.tls.TlsSRPIdentity
    public byte[] getSRPIdentity() {
        return this.f14693a;
    }

    @Override // org.bouncycastle.tls.TlsSRPIdentity
    public byte[] getSRPPassword() {
        return this.f14694b;
    }
}
