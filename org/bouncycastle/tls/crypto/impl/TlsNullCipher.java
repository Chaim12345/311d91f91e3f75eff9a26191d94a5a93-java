package org.bouncycastle.tls.crypto.impl;

import org.bouncycastle.tls.ProtocolVersion;
import org.bouncycastle.tls.TlsFatalAlert;
import org.bouncycastle.tls.TlsUtils;
import org.bouncycastle.tls.crypto.TlsCipher;
import org.bouncycastle.tls.crypto.TlsCryptoParameters;
import org.bouncycastle.tls.crypto.TlsDecodeResult;
import org.bouncycastle.tls.crypto.TlsEncodeResult;
import org.bouncycastle.tls.crypto.TlsHMAC;
/* loaded from: classes4.dex */
public class TlsNullCipher implements TlsCipher {

    /* renamed from: a  reason: collision with root package name */
    protected final TlsSuiteHMac f14946a;

    /* renamed from: b  reason: collision with root package name */
    protected final TlsSuiteHMac f14947b;

    public TlsNullCipher(TlsCryptoParameters tlsCryptoParameters, TlsHMAC tlsHMAC, TlsHMAC tlsHMAC2) {
        if (TlsImplUtils.isTLSv13(tlsCryptoParameters)) {
            throw new TlsFatalAlert((short) 80);
        }
        int macLength = tlsHMAC.getMacLength() + tlsHMAC2.getMacLength();
        byte[] calculateKeyBlock = TlsImplUtils.calculateKeyBlock(tlsCryptoParameters, macLength);
        tlsHMAC.setKey(calculateKeyBlock, 0, tlsHMAC.getMacLength());
        int macLength2 = tlsHMAC.getMacLength() + 0;
        tlsHMAC2.setKey(calculateKeyBlock, macLength2, tlsHMAC2.getMacLength());
        if (macLength2 + tlsHMAC2.getMacLength() != macLength) {
            throw new TlsFatalAlert((short) 80);
        }
        if (tlsCryptoParameters.isServer()) {
            this.f14947b = new TlsSuiteHMac(tlsCryptoParameters, tlsHMAC2);
            this.f14946a = new TlsSuiteHMac(tlsCryptoParameters, tlsHMAC);
            return;
        }
        this.f14947b = new TlsSuiteHMac(tlsCryptoParameters, tlsHMAC);
        this.f14946a = new TlsSuiteHMac(tlsCryptoParameters, tlsHMAC2);
    }

    @Override // org.bouncycastle.tls.crypto.TlsCipher
    public TlsDecodeResult decodeCiphertext(long j2, short s2, ProtocolVersion protocolVersion, byte[] bArr, int i2, int i3) {
        int size = this.f14946a.getSize();
        if (i3 >= size) {
            int i4 = i3 - size;
            if (!TlsUtils.constantTimeAreEqual(size, this.f14946a.calculateMac(j2, s2, bArr, i2, i4), 0, bArr, i2 + i4)) {
                throw new TlsFatalAlert((short) 20);
            }
            return new TlsDecodeResult(bArr, i2, i4, s2);
        }
        throw new TlsFatalAlert((short) 50);
    }

    @Override // org.bouncycastle.tls.crypto.TlsCipher
    public TlsEncodeResult encodePlaintext(long j2, short s2, ProtocolVersion protocolVersion, int i2, byte[] bArr, int i3, int i4) {
        byte[] calculateMac = this.f14947b.calculateMac(j2, s2, bArr, i3, i4);
        int i5 = i2 + i4;
        int length = calculateMac.length + i5;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, i3, bArr2, i2, i4);
        System.arraycopy(calculateMac, 0, bArr2, i5, calculateMac.length);
        return new TlsEncodeResult(bArr2, 0, length, s2);
    }

    @Override // org.bouncycastle.tls.crypto.TlsCipher
    public int getCiphertextDecodeLimit(int i2) {
        return i2 + this.f14947b.getSize();
    }

    @Override // org.bouncycastle.tls.crypto.TlsCipher
    public int getCiphertextEncodeLimit(int i2, int i3) {
        return i2 + this.f14947b.getSize();
    }

    @Override // org.bouncycastle.tls.crypto.TlsCipher
    public int getPlaintextLimit(int i2) {
        return i2 - this.f14947b.getSize();
    }

    @Override // org.bouncycastle.tls.crypto.TlsCipher
    public void rekeyDecoder() {
        throw new TlsFatalAlert((short) 80);
    }

    @Override // org.bouncycastle.tls.crypto.TlsCipher
    public void rekeyEncoder() {
        throw new TlsFatalAlert((short) 80);
    }

    @Override // org.bouncycastle.tls.crypto.TlsCipher
    public boolean usesOpaqueRecordType() {
        return false;
    }
}
