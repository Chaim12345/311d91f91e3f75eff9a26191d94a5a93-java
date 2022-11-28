package org.bouncycastle.tls.crypto.impl;

import org.bouncycastle.tls.ProtocolVersion;
import org.bouncycastle.tls.TlsUtils;
import org.bouncycastle.tls.crypto.TlsCryptoParameters;
import org.bouncycastle.tls.crypto.TlsHMAC;
import org.bouncycastle.tls.crypto.TlsMAC;
import org.bouncycastle.util.Arrays;
/* loaded from: classes4.dex */
public class TlsSuiteHMac implements TlsSuiteMac {

    /* renamed from: a  reason: collision with root package name */
    protected final TlsCryptoParameters f14948a;

    /* renamed from: b  reason: collision with root package name */
    protected final TlsHMAC f14949b;

    /* renamed from: c  reason: collision with root package name */
    protected final int f14950c;

    /* renamed from: d  reason: collision with root package name */
    protected final int f14951d;

    /* renamed from: e  reason: collision with root package name */
    protected final int f14952e;

    public TlsSuiteHMac(TlsCryptoParameters tlsCryptoParameters, TlsHMAC tlsHMAC) {
        this.f14948a = tlsCryptoParameters;
        this.f14949b = tlsHMAC;
        this.f14952e = b(tlsCryptoParameters, tlsHMAC);
        int internalBlockSize = tlsHMAC.getInternalBlockSize();
        this.f14950c = internalBlockSize;
        if (TlsImplUtils.isSSL(tlsCryptoParameters) && tlsHMAC.getMacLength() == 20) {
            this.f14951d = 4;
        } else {
            this.f14951d = internalBlockSize / 8;
        }
    }

    protected static int b(TlsCryptoParameters tlsCryptoParameters, TlsMAC tlsMAC) {
        int macLength = tlsMAC.getMacLength();
        return tlsCryptoParameters.getSecurityParametersHandshake().isTruncatedHMac() ? Math.min(macLength, 10) : macLength;
    }

    protected int a(int i2) {
        return (i2 + this.f14951d) / this.f14950c;
    }

    protected byte[] c(byte[] bArr) {
        int length = bArr.length;
        int i2 = this.f14952e;
        return length <= i2 ? bArr : Arrays.copyOf(bArr, i2);
    }

    @Override // org.bouncycastle.tls.crypto.impl.TlsSuiteMac
    public byte[] calculateMac(long j2, short s2, byte[] bArr, int i2, int i3) {
        ProtocolVersion serverVersion = this.f14948a.getServerVersion();
        boolean isSSL = serverVersion.isSSL();
        int i4 = isSSL ? 11 : 13;
        byte[] bArr2 = new byte[i4];
        TlsUtils.writeUint64(j2, bArr2, 0);
        TlsUtils.writeUint8(s2, bArr2, 8);
        if (!isSSL) {
            TlsUtils.writeVersion(serverVersion, bArr2, 9);
        }
        TlsUtils.writeUint16(i3, bArr2, i4 - 2);
        this.f14949b.update(bArr2, 0, i4);
        this.f14949b.update(bArr, i2, i3);
        return c(this.f14949b.calculateMAC());
    }

    @Override // org.bouncycastle.tls.crypto.impl.TlsSuiteMac
    public byte[] calculateMacConstantTime(long j2, short s2, byte[] bArr, int i2, int i3, int i4, byte[] bArr2) {
        byte[] calculateMac = calculateMac(j2, s2, bArr, i2, i3);
        int i5 = TlsImplUtils.isSSL(this.f14948a) ? 11 : 13;
        int a2 = a(i4 + i5) - a(i5 + i3);
        while (true) {
            a2--;
            if (a2 < 0) {
                this.f14949b.update(bArr2, 0, 1);
                this.f14949b.reset();
                return calculateMac;
            }
            this.f14949b.update(bArr2, 0, this.f14950c);
        }
    }

    @Override // org.bouncycastle.tls.crypto.impl.TlsSuiteMac
    public int getSize() {
        return this.f14952e;
    }
}
