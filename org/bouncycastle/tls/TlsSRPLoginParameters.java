package org.bouncycastle.tls;

import java.math.BigInteger;
import org.bouncycastle.tls.crypto.TlsSRPConfig;
import org.bouncycastle.util.Arrays;
/* loaded from: classes4.dex */
public class TlsSRPLoginParameters {

    /* renamed from: a  reason: collision with root package name */
    protected byte[] f14894a;

    /* renamed from: b  reason: collision with root package name */
    protected TlsSRPConfig f14895b;

    /* renamed from: c  reason: collision with root package name */
    protected BigInteger f14896c;

    /* renamed from: d  reason: collision with root package name */
    protected byte[] f14897d;

    public TlsSRPLoginParameters(byte[] bArr, TlsSRPConfig tlsSRPConfig, BigInteger bigInteger, byte[] bArr2) {
        this.f14894a = Arrays.clone(bArr);
        this.f14895b = tlsSRPConfig;
        this.f14896c = bigInteger;
        this.f14897d = Arrays.clone(bArr2);
    }

    public TlsSRPConfig getConfig() {
        return this.f14895b;
    }

    public byte[] getIdentity() {
        return this.f14894a;
    }

    public byte[] getSalt() {
        return this.f14897d;
    }

    public BigInteger getVerifier() {
        return this.f14896c;
    }
}
