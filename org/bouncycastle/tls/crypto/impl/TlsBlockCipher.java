package org.bouncycastle.tls.crypto.impl;

import org.bouncycastle.tls.ProtocolVersion;
import org.bouncycastle.tls.SecurityParameters;
import org.bouncycastle.tls.TlsFatalAlert;
import org.bouncycastle.tls.TlsUtils;
import org.bouncycastle.tls.crypto.TlsCipher;
import org.bouncycastle.tls.crypto.TlsCryptoParameters;
import org.bouncycastle.tls.crypto.TlsDecodeResult;
import org.bouncycastle.tls.crypto.TlsEncodeResult;
import org.bouncycastle.tls.crypto.TlsHMAC;
import org.bouncycastle.util.Pack;
/* loaded from: classes4.dex */
public class TlsBlockCipher implements TlsCipher {

    /* renamed from: a  reason: collision with root package name */
    protected final TlsCryptoParameters f14936a;

    /* renamed from: b  reason: collision with root package name */
    protected final byte[] f14937b;

    /* renamed from: c  reason: collision with root package name */
    protected final boolean f14938c;

    /* renamed from: d  reason: collision with root package name */
    protected final boolean f14939d;

    /* renamed from: e  reason: collision with root package name */
    protected final boolean f14940e;

    /* renamed from: f  reason: collision with root package name */
    protected final boolean f14941f;

    /* renamed from: g  reason: collision with root package name */
    protected final TlsBlockCipherImpl f14942g;

    /* renamed from: h  reason: collision with root package name */
    protected final TlsBlockCipherImpl f14943h;

    /* renamed from: i  reason: collision with root package name */
    protected final TlsSuiteMac f14944i;

    /* renamed from: j  reason: collision with root package name */
    protected final TlsSuiteMac f14945j;

    public TlsBlockCipher(TlsCryptoParameters tlsCryptoParameters, TlsBlockCipherImpl tlsBlockCipherImpl, TlsBlockCipherImpl tlsBlockCipherImpl2, TlsHMAC tlsHMAC, TlsHMAC tlsHMAC2, int i2) {
        TlsSuiteHMac tlsSuiteHMac;
        SecurityParameters securityParametersHandshake = tlsCryptoParameters.getSecurityParametersHandshake();
        ProtocolVersion negotiatedVersion = securityParametersHandshake.getNegotiatedVersion();
        if (TlsImplUtils.isTLSv13(negotiatedVersion)) {
            throw new TlsFatalAlert((short) 80);
        }
        this.f14936a = tlsCryptoParameters;
        this.f14937b = tlsCryptoParameters.getNonceGenerator().generateNonce(256);
        boolean isEncryptThenMAC = securityParametersHandshake.isEncryptThenMAC();
        this.f14938c = isEncryptThenMAC;
        boolean isTLSv11 = TlsImplUtils.isTLSv11(negotiatedVersion);
        this.f14939d = isTLSv11;
        boolean z = true;
        this.f14940e = !negotiatedVersion.isSSL();
        if (!securityParametersHandshake.isExtendedPadding() || !ProtocolVersion.TLSv10.isEqualOrEarlierVersionOf(negotiatedVersion) || (!isEncryptThenMAC && securityParametersHandshake.isTruncatedHMac())) {
            z = false;
        }
        this.f14941f = z;
        this.f14943h = tlsBlockCipherImpl;
        this.f14942g = tlsBlockCipherImpl2;
        if (tlsCryptoParameters.isServer()) {
            tlsBlockCipherImpl2 = tlsBlockCipherImpl;
            tlsBlockCipherImpl = tlsBlockCipherImpl2;
        }
        int macLength = (i2 * 2) + tlsHMAC.getMacLength() + tlsHMAC2.getMacLength();
        macLength = isTLSv11 ? macLength : macLength + tlsBlockCipherImpl.getBlockSize() + tlsBlockCipherImpl2.getBlockSize();
        byte[] calculateKeyBlock = TlsImplUtils.calculateKeyBlock(tlsCryptoParameters, macLength);
        tlsHMAC.setKey(calculateKeyBlock, 0, tlsHMAC.getMacLength());
        int macLength2 = tlsHMAC.getMacLength() + 0;
        tlsHMAC2.setKey(calculateKeyBlock, macLength2, tlsHMAC2.getMacLength());
        int macLength3 = macLength2 + tlsHMAC2.getMacLength();
        tlsBlockCipherImpl.setKey(calculateKeyBlock, macLength3, i2);
        int i3 = macLength3 + i2;
        tlsBlockCipherImpl2.setKey(calculateKeyBlock, i3, i2);
        int i4 = i3 + i2;
        int blockSize = tlsBlockCipherImpl.getBlockSize();
        int blockSize2 = tlsBlockCipherImpl2.getBlockSize();
        if (isTLSv11) {
            tlsBlockCipherImpl.init(new byte[blockSize], 0, blockSize);
            tlsBlockCipherImpl2.init(new byte[blockSize2], 0, blockSize2);
        } else {
            tlsBlockCipherImpl.init(calculateKeyBlock, i4, blockSize);
            int i5 = i4 + blockSize;
            tlsBlockCipherImpl2.init(calculateKeyBlock, i5, blockSize2);
            i4 = i5 + blockSize2;
        }
        if (i4 != macLength) {
            throw new TlsFatalAlert((short) 80);
        }
        if (tlsCryptoParameters.isServer()) {
            this.f14945j = new TlsSuiteHMac(tlsCryptoParameters, tlsHMAC2);
            tlsSuiteHMac = new TlsSuiteHMac(tlsCryptoParameters, tlsHMAC);
        } else {
            this.f14945j = new TlsSuiteHMac(tlsCryptoParameters, tlsHMAC);
            tlsSuiteHMac = new TlsSuiteHMac(tlsCryptoParameters, tlsHMAC2);
        }
        this.f14944i = tlsSuiteHMac;
    }

    protected int a(byte[] bArr, int i2, int i3, int i4, int i5) {
        byte b2;
        int i6;
        int i7 = i2 + i3;
        byte b3 = bArr[i7 - 1];
        int i8 = (b3 & 255) + 1;
        if (this.f14940e) {
            i4 = 256;
        }
        if (i8 > Math.min(i4, i3 - i5)) {
            i6 = 0;
            b2 = 0;
            i8 = 0;
        } else {
            int i9 = i7 - i8;
            b2 = 0;
            while (true) {
                int i10 = i9 + 1;
                b2 = (byte) ((bArr[i9] ^ b3) | b2);
                if (i10 >= i7) {
                    break;
                }
                i9 = i10;
            }
            i6 = i8;
            if (b2 != 0) {
                i8 = 0;
            }
        }
        byte[] bArr2 = this.f14937b;
        while (i6 < 256) {
            b2 = (byte) ((bArr2[i6] ^ b3) | b2);
            i6++;
        }
        bArr2[0] = (byte) (bArr2[0] ^ b2);
        return i8;
    }

    protected int b(int i2) {
        return Math.min(d(Pack.littleEndianToInt(this.f14936a.getNonceGenerator().generateNonce(4), 0)), i2);
    }

    protected int c(int i2, int i3, int i4, int i5) {
        if (this.f14939d) {
            i5 += i2;
        }
        int i6 = i5 + i4;
        if (this.f14938c) {
            return (i6 - (i6 % i2)) + i3;
        }
        int i7 = i6 + i3;
        return i7 - (i7 % i2);
    }

    protected int d(int i2) {
        if (i2 == 0) {
            return 32;
        }
        int i3 = 0;
        while ((i2 & 1) == 0) {
            i3++;
            i2 >>= 1;
        }
        return i3;
    }

    @Override // org.bouncycastle.tls.crypto.TlsCipher
    public TlsDecodeResult decodeCiphertext(long j2, short s2, ProtocolVersion protocolVersion, byte[] bArr, int i2, int i3) {
        int i4;
        int i5;
        byte[] bArr2;
        int blockSize = this.f14942g.getBlockSize();
        int size = this.f14944i.getSize();
        int max = this.f14938c ? blockSize + size : Math.max(blockSize, size + 1);
        if (this.f14939d) {
            max += blockSize;
        }
        if (i3 >= max) {
            boolean z = this.f14938c;
            int i6 = z ? i3 - size : i3;
            if (i6 % blockSize == 0) {
                if (z && (!TlsUtils.constantTimeAreEqual(size, this.f14944i.calculateMac(j2, s2, bArr, i2, i3 - size), 0, bArr, (i2 + i3) - size))) {
                    throw new TlsFatalAlert((short) 20);
                }
                this.f14942g.doFinal(bArr, i2, i6, bArr, i2);
                if (this.f14939d) {
                    i6 -= blockSize;
                    i4 = i2 + blockSize;
                } else {
                    i4 = i2;
                }
                int a2 = a(bArr, i4, i6, blockSize, this.f14938c ? 0 : size);
                boolean z2 = a2 == 0;
                int i7 = i6 - a2;
                if (this.f14938c) {
                    i5 = i4;
                    bArr2 = bArr;
                } else {
                    i7 -= size;
                    i5 = i4;
                    bArr2 = bArr;
                    z2 |= !TlsUtils.constantTimeAreEqual(size, this.f14944i.calculateMacConstantTime(j2, s2, bArr, i5, i7, i6 - size, this.f14937b), 0, bArr2, i5 + i7);
                }
                if (z2) {
                    throw new TlsFatalAlert((short) 20);
                }
                return new TlsDecodeResult(bArr2, i5, i7, s2);
            }
            throw new TlsFatalAlert((short) 21);
        }
        throw new TlsFatalAlert((short) 50);
    }

    @Override // org.bouncycastle.tls.crypto.TlsCipher
    public TlsEncodeResult encodePlaintext(long j2, short s2, ProtocolVersion protocolVersion, int i2, byte[] bArr, int i3, int i4) {
        byte[] bArr2;
        int i5;
        int i6;
        int blockSize = this.f14943h.getBlockSize();
        int size = this.f14945j.getSize();
        int i7 = blockSize - ((!this.f14938c ? i4 + size : i4) % blockSize);
        if (this.f14941f) {
            i7 += b((256 - i7) / blockSize) * blockSize;
        }
        int i8 = size + i4 + i7;
        boolean z = this.f14939d;
        if (z) {
            i8 += blockSize;
        }
        int i9 = i2 + i8;
        byte[] bArr3 = new byte[i9];
        if (z) {
            System.arraycopy(this.f14936a.getNonceGenerator().generateNonce(blockSize), 0, bArr3, i2, blockSize);
            i6 = blockSize + i2;
            bArr2 = bArr;
            i5 = i3;
        } else {
            bArr2 = bArr;
            i5 = i3;
            i6 = i2;
        }
        System.arraycopy(bArr2, i5, bArr3, i6, i4);
        int i10 = i6 + i4;
        if (!this.f14938c) {
            byte[] calculateMac = this.f14945j.calculateMac(j2, s2, bArr, i3, i4);
            System.arraycopy(calculateMac, 0, bArr3, i10, calculateMac.length);
            i10 += calculateMac.length;
        }
        byte b2 = (byte) (i7 - 1);
        int i11 = i10;
        int i12 = 0;
        while (i12 < i7) {
            bArr3[i11] = b2;
            i12++;
            i11++;
        }
        int i13 = i11 - i2;
        this.f14943h.doFinal(bArr3, i2, i13, bArr3, i2);
        if (this.f14938c) {
            byte[] calculateMac2 = this.f14945j.calculateMac(j2, s2, bArr3, i2, i13);
            System.arraycopy(calculateMac2, 0, bArr3, i11, calculateMac2.length);
            i11 += calculateMac2.length;
        }
        if (i11 == i9) {
            return new TlsEncodeResult(bArr3, 0, i9, s2);
        }
        throw new TlsFatalAlert((short) 80);
    }

    @Override // org.bouncycastle.tls.crypto.TlsCipher
    public int getCiphertextDecodeLimit(int i2) {
        return c(this.f14942g.getBlockSize(), this.f14944i.getSize(), 256, i2);
    }

    @Override // org.bouncycastle.tls.crypto.TlsCipher
    public int getCiphertextEncodeLimit(int i2, int i3) {
        int blockSize = this.f14943h.getBlockSize();
        return c(blockSize, this.f14945j.getSize(), this.f14941f ? 256 : blockSize, i2);
    }

    @Override // org.bouncycastle.tls.crypto.TlsCipher
    public int getPlaintextLimit(int i2) {
        int i3;
        int blockSize = this.f14943h.getBlockSize();
        int size = this.f14945j.getSize();
        if (this.f14938c) {
            i3 = i2 - size;
            size = i3 % blockSize;
        } else {
            i3 = i2 - (i2 % blockSize);
        }
        int i4 = (i3 - size) - 1;
        return this.f14939d ? i4 - blockSize : i4;
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
