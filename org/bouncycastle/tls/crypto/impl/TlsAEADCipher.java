package org.bouncycastle.tls.crypto.impl;

import org.bouncycastle.tls.ProtocolVersion;
import org.bouncycastle.tls.SecurityParameters;
import org.bouncycastle.tls.TlsFatalAlert;
import org.bouncycastle.tls.TlsUtils;
import org.bouncycastle.tls.crypto.TlsCipher;
import org.bouncycastle.tls.crypto.TlsCryptoParameters;
import org.bouncycastle.tls.crypto.TlsCryptoUtils;
import org.bouncycastle.tls.crypto.TlsDecodeResult;
import org.bouncycastle.tls.crypto.TlsEncodeResult;
import org.bouncycastle.tls.crypto.TlsSecret;
/* loaded from: classes4.dex */
public class TlsAEADCipher implements TlsCipher {
    public static final int AEAD_CCM = 1;
    public static final int AEAD_CHACHA20_POLY1305 = 2;
    public static final int AEAD_GCM = 3;
    private static final int NONCE_RFC5288 = 1;
    private static final int NONCE_RFC7905 = 2;

    /* renamed from: a  reason: collision with root package name */
    protected final TlsCryptoParameters f14925a;

    /* renamed from: b  reason: collision with root package name */
    protected final int f14926b;

    /* renamed from: c  reason: collision with root package name */
    protected final int f14927c;

    /* renamed from: d  reason: collision with root package name */
    protected final int f14928d;

    /* renamed from: e  reason: collision with root package name */
    protected final int f14929e;

    /* renamed from: f  reason: collision with root package name */
    protected final TlsAEADCipherImpl f14930f;

    /* renamed from: g  reason: collision with root package name */
    protected final TlsAEADCipherImpl f14931g;

    /* renamed from: h  reason: collision with root package name */
    protected final byte[] f14932h;

    /* renamed from: i  reason: collision with root package name */
    protected final byte[] f14933i;

    /* renamed from: j  reason: collision with root package name */
    protected final boolean f14934j;

    /* renamed from: k  reason: collision with root package name */
    protected final int f14935k;

    public TlsAEADCipher(TlsCryptoParameters tlsCryptoParameters, TlsAEADCipherImpl tlsAEADCipherImpl, TlsAEADCipherImpl tlsAEADCipherImpl2, int i2, int i3, int i4) {
        int i5;
        SecurityParameters securityParametersHandshake = tlsCryptoParameters.getSecurityParametersHandshake();
        ProtocolVersion negotiatedVersion = securityParametersHandshake.getNegotiatedVersion();
        if (!TlsImplUtils.isTLSv12(negotiatedVersion)) {
            throw new TlsFatalAlert((short) 80);
        }
        boolean isTLSv13 = TlsImplUtils.isTLSv13(negotiatedVersion);
        this.f14934j = isTLSv13;
        int nonceMode = getNonceMode(isTLSv13, i4);
        this.f14935k = nonceMode;
        if (nonceMode == 1) {
            this.f14928d = 4;
            this.f14929e = 8;
        } else if (nonceMode != 2) {
            throw new TlsFatalAlert((short) 80);
        } else {
            this.f14928d = 12;
            this.f14929e = 0;
        }
        this.f14925a = tlsCryptoParameters;
        this.f14926b = i2;
        this.f14927c = i3;
        this.f14930f = tlsAEADCipherImpl2;
        this.f14931g = tlsAEADCipherImpl;
        int i6 = this.f14928d;
        byte[] bArr = new byte[i6];
        this.f14932h = bArr;
        byte[] bArr2 = new byte[i6];
        this.f14933i = bArr2;
        boolean isServer = tlsCryptoParameters.isServer();
        if (isTLSv13) {
            b(securityParametersHandshake, tlsAEADCipherImpl2, bArr, !isServer);
            b(securityParametersHandshake, tlsAEADCipherImpl, bArr2, isServer);
            return;
        }
        int i7 = (i2 * 2) + (this.f14928d * 2);
        byte[] calculateKeyBlock = TlsImplUtils.calculateKeyBlock(tlsCryptoParameters, i7);
        if (isServer) {
            tlsAEADCipherImpl2.setKey(calculateKeyBlock, 0, i2);
            int i8 = i2 + 0;
            tlsAEADCipherImpl.setKey(calculateKeyBlock, i8, i2);
            int i9 = i8 + i2;
            System.arraycopy(calculateKeyBlock, i9, bArr, 0, this.f14928d);
            int i10 = this.f14928d;
            i5 = i9 + i10;
            System.arraycopy(calculateKeyBlock, i5, bArr2, 0, i10);
        } else {
            tlsAEADCipherImpl.setKey(calculateKeyBlock, 0, i2);
            int i11 = i2 + 0;
            tlsAEADCipherImpl2.setKey(calculateKeyBlock, i11, i2);
            int i12 = i11 + i2;
            System.arraycopy(calculateKeyBlock, i12, bArr2, 0, this.f14928d);
            int i13 = this.f14928d;
            i5 = i12 + i13;
            System.arraycopy(calculateKeyBlock, i5, bArr, 0, i13);
        }
        if (i7 != i5 + this.f14928d) {
            throw new TlsFatalAlert((short) 80);
        }
        byte[] bArr3 = new byte[this.f14928d + this.f14929e];
        bArr3[0] = (byte) (~bArr2[0]);
        bArr3[1] = (byte) (~bArr[1]);
        tlsAEADCipherImpl.init(bArr3, i3, null);
        tlsAEADCipherImpl2.init(bArr3, i3, null);
    }

    private static int getNonceMode(boolean z, int i2) {
        if (i2 != 1) {
            if (i2 == 2) {
                return 2;
            }
            if (i2 != 3) {
                throw new TlsFatalAlert((short) 80);
            }
        }
        return z ? 2 : 1;
    }

    protected byte[] a(long j2, short s2, ProtocolVersion protocolVersion, int i2, int i3) {
        if (this.f14934j) {
            byte[] bArr = new byte[5];
            TlsUtils.writeUint8(s2, bArr, 0);
            TlsUtils.writeVersion(protocolVersion, bArr, 1);
            TlsUtils.writeUint16(i2, bArr, 3);
            return bArr;
        }
        byte[] bArr2 = new byte[13];
        TlsUtils.writeUint64(j2, bArr2, 0);
        TlsUtils.writeUint8(s2, bArr2, 8);
        TlsUtils.writeVersion(protocolVersion, bArr2, 9);
        TlsUtils.writeUint16(i3, bArr2, 11);
        return bArr2;
    }

    protected void b(SecurityParameters securityParameters, TlsAEADCipherImpl tlsAEADCipherImpl, byte[] bArr, boolean z) {
        if (!this.f14934j) {
            throw new TlsFatalAlert((short) 80);
        }
        TlsSecret trafficSecretServer = z ? securityParameters.getTrafficSecretServer() : securityParameters.getTrafficSecretClient();
        if (trafficSecretServer == null) {
            throw new TlsFatalAlert((short) 80);
        }
        c(tlsAEADCipherImpl, bArr, trafficSecretServer, securityParameters.getPRFCryptoHashAlgorithm());
    }

    protected void c(TlsAEADCipherImpl tlsAEADCipherImpl, byte[] bArr, TlsSecret tlsSecret, int i2) {
        byte[] bArr2 = TlsUtils.EMPTY_BYTES;
        byte[] extract = TlsCryptoUtils.hkdfExpandLabel(tlsSecret, i2, "key", bArr2, this.f14926b).extract();
        byte[] extract2 = TlsCryptoUtils.hkdfExpandLabel(tlsSecret, i2, "iv", bArr2, this.f14928d).extract();
        tlsAEADCipherImpl.setKey(extract, 0, this.f14926b);
        System.arraycopy(extract2, 0, bArr, 0, this.f14928d);
        extract2[0] = (byte) (extract2[0] ^ 128);
        tlsAEADCipherImpl.init(extract2, this.f14927c, null);
    }

    @Override // org.bouncycastle.tls.crypto.TlsCipher
    public TlsDecodeResult decodeCiphertext(long j2, short s2, ProtocolVersion protocolVersion, byte[] bArr, int i2, int i3) {
        short s3;
        byte b2;
        if (getPlaintextLimit(i3) >= 0) {
            byte[] bArr2 = this.f14932h;
            int length = bArr2.length + this.f14929e;
            byte[] bArr3 = new byte[length];
            int i4 = this.f14935k;
            int i5 = 0;
            if (i4 == 1) {
                System.arraycopy(bArr2, 0, bArr3, 0, bArr2.length);
                int i6 = this.f14929e;
                System.arraycopy(bArr, i2, bArr3, length - i6, i6);
            } else if (i4 != 2) {
                throw new TlsFatalAlert((short) 80);
            } else {
                TlsUtils.writeUint64(j2, bArr3, length - 8);
                while (true) {
                    byte[] bArr4 = this.f14932h;
                    if (i5 >= bArr4.length) {
                        break;
                    }
                    bArr3[i5] = (byte) (bArr4[i5] ^ bArr3[i5]);
                    i5++;
                }
            }
            int i7 = this.f14929e;
            int i8 = i2 + i7;
            int i9 = i3 - i7;
            int outputSize = this.f14930f.getOutputSize(i9);
            try {
                this.f14930f.init(bArr3, this.f14927c, a(j2, s2, protocolVersion, i3, outputSize));
                if (this.f14930f.doFinal(bArr, i8, i9, bArr, i8) == outputSize) {
                    if (this.f14934j) {
                        do {
                            outputSize--;
                            if (outputSize < 0) {
                                throw new TlsFatalAlert((short) 10);
                            }
                            b2 = bArr[i8 + outputSize];
                        } while (b2 == 0);
                        s3 = (short) (b2 & 255);
                    } else {
                        s3 = s2;
                    }
                    return new TlsDecodeResult(bArr, i8, outputSize, s3);
                }
                throw new TlsFatalAlert((short) 80);
            } catch (RuntimeException e2) {
                throw new TlsFatalAlert((short) 20, (Throwable) e2);
            }
        }
        throw new TlsFatalAlert((short) 50);
    }

    @Override // org.bouncycastle.tls.crypto.TlsCipher
    public TlsEncodeResult encodePlaintext(long j2, short s2, ProtocolVersion protocolVersion, int i2, byte[] bArr, int i3, int i4) {
        int i5 = i2;
        byte[] bArr2 = this.f14933i;
        int length = bArr2.length + this.f14929e;
        byte[] bArr3 = new byte[length];
        int i6 = this.f14935k;
        if (i6 == 1) {
            System.arraycopy(bArr2, 0, bArr3, 0, bArr2.length);
            TlsUtils.writeUint64(j2, bArr3, this.f14933i.length);
        } else if (i6 != 2) {
            throw new TlsFatalAlert((short) 80);
        } else {
            TlsUtils.writeUint64(j2, bArr3, length - 8);
            int i7 = 0;
            while (true) {
                byte[] bArr4 = this.f14933i;
                if (i7 >= bArr4.length) {
                    break;
                }
                bArr3[i7] = (byte) (bArr4[i7] ^ bArr3[i7]);
                i7++;
            }
        }
        boolean z = this.f14934j;
        TlsAEADCipherImpl tlsAEADCipherImpl = this.f14931g;
        int i8 = i4 + (z ? 1 : 0);
        int outputSize = tlsAEADCipherImpl.getOutputSize(i8);
        int i9 = this.f14929e;
        int i10 = i9 + outputSize;
        int i11 = i5 + i10;
        byte[] bArr5 = new byte[i11];
        if (i9 != 0) {
            System.arraycopy(bArr3, length - i9, bArr5, i5, i9);
            i5 += this.f14929e;
        }
        short s3 = this.f14934j ? (short) 23 : s2;
        short s4 = s3;
        try {
            this.f14931g.init(bArr3, this.f14927c, a(j2, s3, protocolVersion, i10, i4));
            System.arraycopy(bArr, i3, bArr5, i5, i4);
            if (this.f14934j) {
                bArr5[i5 + i4] = (byte) s2;
            }
            if (i5 + this.f14931g.doFinal(bArr5, i5, i8, bArr5, i5) == i11) {
                return new TlsEncodeResult(bArr5, 0, i11, s4);
            }
            throw new TlsFatalAlert((short) 80);
        } catch (RuntimeException e2) {
            throw new TlsFatalAlert((short) 80, (Throwable) e2);
        }
    }

    @Override // org.bouncycastle.tls.crypto.TlsCipher
    public int getCiphertextDecodeLimit(int i2) {
        return i2 + this.f14927c + this.f14929e + (this.f14934j ? 1 : 0);
    }

    @Override // org.bouncycastle.tls.crypto.TlsCipher
    public int getCiphertextEncodeLimit(int i2, int i3) {
        if (this.f14934j) {
            i2 = Math.min(i3, i2 + 0) + 1;
        }
        return i2 + this.f14927c + this.f14929e;
    }

    @Override // org.bouncycastle.tls.crypto.TlsCipher
    public int getPlaintextLimit(int i2) {
        return ((i2 - this.f14927c) - this.f14929e) - (this.f14934j ? 1 : 0);
    }

    @Override // org.bouncycastle.tls.crypto.TlsCipher
    public void rekeyDecoder() {
        b(this.f14925a.getSecurityParametersConnection(), this.f14930f, this.f14932h, !this.f14925a.isServer());
    }

    @Override // org.bouncycastle.tls.crypto.TlsCipher
    public void rekeyEncoder() {
        b(this.f14925a.getSecurityParametersConnection(), this.f14931g, this.f14933i, this.f14925a.isServer());
    }

    @Override // org.bouncycastle.tls.crypto.TlsCipher
    public boolean usesOpaqueRecordType() {
        return this.f14934j;
    }
}
