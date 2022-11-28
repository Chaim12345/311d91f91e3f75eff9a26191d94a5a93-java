package com.google.crypto.tink.subtle;

import com.google.crypto.tink.PublicKeyVerify;
import com.google.crypto.tink.subtle.Enums;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;
/* loaded from: classes2.dex */
public final class RsaSsaPssVerifyJce implements PublicKeyVerify {
    private final Enums.HashType mgf1Hash;
    private final RSAPublicKey publicKey;
    private final int saltLength;
    private final Enums.HashType sigHash;

    public RsaSsaPssVerifyJce(RSAPublicKey rSAPublicKey, Enums.HashType hashType, Enums.HashType hashType2, int i2) {
        Validators.validateSignatureHash(hashType);
        Validators.validateRsaModulusSize(rSAPublicKey.getModulus().bitLength());
        Validators.validateRsaPublicExponent(rSAPublicKey.getPublicExponent());
        this.publicKey = rSAPublicKey;
        this.sigHash = hashType;
        this.mgf1Hash = hashType2;
        this.saltLength = i2;
    }

    private void emsaPssVerify(byte[] bArr, byte[] bArr2, int i2) {
        int i3;
        Validators.validateSignatureHash(this.sigHash);
        MessageDigest engineFactory = EngineFactory.MESSAGE_DIGEST.getInstance(SubtleUtil.toDigestAlgo(this.sigHash));
        byte[] digest = engineFactory.digest(bArr);
        int digestLength = engineFactory.getDigestLength();
        int length = bArr2.length;
        if (length < this.saltLength + digestLength + 2) {
            throw new GeneralSecurityException("inconsistent");
        }
        if (bArr2[bArr2.length - 1] != -68) {
            throw new GeneralSecurityException("inconsistent");
        }
        int i4 = (length - digestLength) - 1;
        byte[] copyOf = Arrays.copyOf(bArr2, i4);
        byte[] copyOfRange = Arrays.copyOfRange(bArr2, copyOf.length, copyOf.length + digestLength);
        int i5 = 0;
        while (true) {
            int i6 = i4;
            MessageDigest messageDigest = engineFactory;
            byte[] bArr3 = digest;
            long j2 = (length * 8) - i2;
            if (i5 < j2) {
                if (((copyOf[i5 / 8] >> (7 - (i5 % 8))) & 1) != 0) {
                    throw new GeneralSecurityException("inconsistent");
                }
                i5++;
                i4 = i6;
                engineFactory = messageDigest;
                digest = bArr3;
            } else {
                byte[] mgf1 = SubtleUtil.mgf1(copyOfRange, i6, this.mgf1Hash);
                int length2 = mgf1.length;
                byte[] bArr4 = new byte[length2];
                for (int i7 = 0; i7 < length2; i7++) {
                    bArr4[i7] = (byte) (mgf1[i7] ^ copyOf[i7]);
                }
                for (int i8 = 0; i8 <= j2; i8++) {
                    int i9 = i8 / 8;
                    bArr4[i9] = (byte) ((~(1 << (7 - (i8 % 8)))) & bArr4[i9]);
                }
                int i10 = 0;
                while (true) {
                    int i11 = this.saltLength;
                    if (i10 >= (i3 - i11) - 2) {
                        if (bArr4[(i3 - i11) - 2] != 1) {
                            throw new GeneralSecurityException("inconsistent");
                        }
                        byte[] copyOfRange2 = Arrays.copyOfRange(bArr4, length2 - i11, length2);
                        int i12 = digestLength + 8;
                        byte[] bArr5 = new byte[this.saltLength + i12];
                        System.arraycopy(bArr3, 0, bArr5, 8, bArr3.length);
                        System.arraycopy(copyOfRange2, 0, bArr5, i12, copyOfRange2.length);
                        if (!Bytes.equal(messageDigest.digest(bArr5), copyOfRange)) {
                            throw new GeneralSecurityException("inconsistent");
                        }
                        return;
                    } else if (bArr4[i10] != 0) {
                        throw new GeneralSecurityException("inconsistent");
                    } else {
                        i10++;
                    }
                }
            }
        }
    }

    @Override // com.google.crypto.tink.PublicKeyVerify
    public void verify(byte[] bArr, byte[] bArr2) {
        BigInteger publicExponent = this.publicKey.getPublicExponent();
        BigInteger modulus = this.publicKey.getModulus();
        int bitLength = (modulus.bitLength() + 7) / 8;
        int bitLength2 = ((modulus.bitLength() - 1) + 7) / 8;
        if (bitLength != bArr.length) {
            throw new GeneralSecurityException("invalid signature's length");
        }
        BigInteger bytes2Integer = SubtleUtil.bytes2Integer(bArr);
        if (bytes2Integer.compareTo(modulus) >= 0) {
            throw new GeneralSecurityException("signature out of range");
        }
        emsaPssVerify(bArr2, SubtleUtil.integer2Bytes(bytes2Integer.modPow(publicExponent, modulus), bitLength2), modulus.bitLength() - 1);
    }
}
