package org.bouncycastle.crypto.macs;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.Xof;
import org.bouncycastle.crypto.digests.CSHAKEDigest;
import org.bouncycastle.crypto.digests.XofUtils;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.tls.CipherSuite;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Strings;
/* loaded from: classes3.dex */
public class KMAC implements Mac, Xof {
    private static final byte[] padding = new byte[100];
    private final int bitLength;
    private final CSHAKEDigest cshake;
    private boolean firstOutput;
    private boolean initialised;
    private byte[] key;
    private final int outputLength;

    public KMAC(int i2, byte[] bArr) {
        this.cshake = new CSHAKEDigest(i2, Strings.toByteArray("KMAC"), bArr);
        this.bitLength = i2;
        this.outputLength = (i2 * 2) / 8;
    }

    private void bytePad(byte[] bArr, int i2) {
        byte[] leftEncode = XofUtils.leftEncode(i2);
        update(leftEncode, 0, leftEncode.length);
        byte[] encode = encode(bArr);
        update(encode, 0, encode.length);
        int length = i2 - ((leftEncode.length + encode.length) % i2);
        if (length <= 0 || length == i2) {
            return;
        }
        while (true) {
            byte[] bArr2 = padding;
            if (length <= bArr2.length) {
                update(bArr2, 0, length);
                return;
            } else {
                update(bArr2, 0, bArr2.length);
                length -= bArr2.length;
            }
        }
    }

    private static byte[] encode(byte[] bArr) {
        return Arrays.concatenate(XofUtils.leftEncode(bArr.length * 8), bArr);
    }

    @Override // org.bouncycastle.crypto.Mac
    public int doFinal(byte[] bArr, int i2) {
        if (this.firstOutput) {
            if (!this.initialised) {
                throw new IllegalStateException("KMAC not initialized");
            }
            byte[] rightEncode = XofUtils.rightEncode(getMacSize() * 8);
            this.cshake.update(rightEncode, 0, rightEncode.length);
        }
        int doFinal = this.cshake.doFinal(bArr, i2, getMacSize());
        reset();
        return doFinal;
    }

    @Override // org.bouncycastle.crypto.Xof
    public int doFinal(byte[] bArr, int i2, int i3) {
        if (this.firstOutput) {
            if (!this.initialised) {
                throw new IllegalStateException("KMAC not initialized");
            }
            byte[] rightEncode = XofUtils.rightEncode(i3 * 8);
            this.cshake.update(rightEncode, 0, rightEncode.length);
        }
        int doFinal = this.cshake.doFinal(bArr, i2, i3);
        reset();
        return doFinal;
    }

    @Override // org.bouncycastle.crypto.Xof
    public int doOutput(byte[] bArr, int i2, int i3) {
        if (this.firstOutput) {
            if (!this.initialised) {
                throw new IllegalStateException("KMAC not initialized");
            }
            byte[] rightEncode = XofUtils.rightEncode(0L);
            this.cshake.update(rightEncode, 0, rightEncode.length);
            this.firstOutput = false;
        }
        return this.cshake.doOutput(bArr, i2, i3);
    }

    @Override // org.bouncycastle.crypto.Mac
    public String getAlgorithmName() {
        return "KMAC" + this.cshake.getAlgorithmName().substring(6);
    }

    @Override // org.bouncycastle.crypto.ExtendedDigest
    public int getByteLength() {
        return this.cshake.getByteLength();
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return this.outputLength;
    }

    @Override // org.bouncycastle.crypto.Mac
    public int getMacSize() {
        return this.outputLength;
    }

    @Override // org.bouncycastle.crypto.Mac
    public void init(CipherParameters cipherParameters) {
        this.key = Arrays.clone(((KeyParameter) cipherParameters).getKey());
        this.initialised = true;
        reset();
    }

    @Override // org.bouncycastle.crypto.Mac
    public void reset() {
        this.cshake.reset();
        byte[] bArr = this.key;
        if (bArr != null) {
            bytePad(bArr, this.bitLength == 128 ? CipherSuite.TLS_PSK_WITH_AES_128_GCM_SHA256 : CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA);
        }
        this.firstOutput = true;
    }

    @Override // org.bouncycastle.crypto.Mac
    public void update(byte b2) {
        if (!this.initialised) {
            throw new IllegalStateException("KMAC not initialized");
        }
        this.cshake.update(b2);
    }

    @Override // org.bouncycastle.crypto.Mac
    public void update(byte[] bArr, int i2, int i3) {
        if (!this.initialised) {
            throw new IllegalStateException("KMAC not initialized");
        }
        this.cshake.update(bArr, i2, i3);
    }
}
