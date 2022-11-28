package com.google.crypto.tink.subtle;

import com.google.crypto.tink.PublicKeyVerify;
import com.google.crypto.tink.subtle.Enums;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.interfaces.RSAPublicKey;
/* loaded from: classes2.dex */
public final class RsaSsaPkcs1VerifyJce implements PublicKeyVerify {
    private static final String ASN_PREFIX_SHA256 = "3031300d060960864801650304020105000420";
    private static final String ASN_PREFIX_SHA512 = "3051300d060960864801650304020305000440";
    private final Enums.HashType hash;
    private final RSAPublicKey publicKey;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.crypto.tink.subtle.RsaSsaPkcs1VerifyJce$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f9862a;

        static {
            int[] iArr = new int[Enums.HashType.values().length];
            f9862a = iArr;
            try {
                iArr[Enums.HashType.SHA256.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f9862a[Enums.HashType.SHA512.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public RsaSsaPkcs1VerifyJce(RSAPublicKey rSAPublicKey, Enums.HashType hashType) {
        Validators.validateSignatureHash(hashType);
        Validators.validateRsaModulusSize(rSAPublicKey.getModulus().bitLength());
        Validators.validateRsaPublicExponent(rSAPublicKey.getPublicExponent());
        this.publicKey = rSAPublicKey;
        this.hash = hashType;
    }

    private byte[] emsaPkcs1(byte[] bArr, int i2, Enums.HashType hashType) {
        int length;
        Validators.validateSignatureHash(hashType);
        MessageDigest engineFactory = EngineFactory.MESSAGE_DIGEST.getInstance(SubtleUtil.toDigestAlgo(this.hash));
        engineFactory.update(bArr);
        byte[] digest = engineFactory.digest();
        byte[] asnPrefix = toAsnPrefix(hashType);
        if (i2 >= asnPrefix.length + digest.length + 11) {
            byte[] bArr2 = new byte[i2];
            bArr2[0] = 0;
            int i3 = 2;
            bArr2[1] = 1;
            int i4 = 0;
            while (i4 < (i2 - length) - 3) {
                bArr2[i3] = -1;
                i4++;
                i3++;
            }
            int i5 = i3 + 1;
            bArr2[i3] = 0;
            System.arraycopy(asnPrefix, 0, bArr2, i5, asnPrefix.length);
            System.arraycopy(digest, 0, bArr2, i5 + asnPrefix.length, digest.length);
            return bArr2;
        }
        throw new GeneralSecurityException("intended encoded message length too short");
    }

    private byte[] toAsnPrefix(Enums.HashType hashType) {
        String str;
        int i2 = AnonymousClass1.f9862a[hashType.ordinal()];
        if (i2 == 1) {
            str = ASN_PREFIX_SHA256;
        } else if (i2 != 2) {
            throw new GeneralSecurityException("Unsupported hash " + hashType);
        } else {
            str = ASN_PREFIX_SHA512;
        }
        return Hex.decode(str);
    }

    @Override // com.google.crypto.tink.PublicKeyVerify
    public void verify(byte[] bArr, byte[] bArr2) {
        BigInteger publicExponent = this.publicKey.getPublicExponent();
        BigInteger modulus = this.publicKey.getModulus();
        int bitLength = (modulus.bitLength() + 7) / 8;
        if (bitLength != bArr.length) {
            throw new GeneralSecurityException("invalid signature's length");
        }
        BigInteger bytes2Integer = SubtleUtil.bytes2Integer(bArr);
        if (bytes2Integer.compareTo(modulus) >= 0) {
            throw new GeneralSecurityException("signature out of range");
        }
        if (!Bytes.equal(SubtleUtil.integer2Bytes(bytes2Integer.modPow(publicExponent, modulus), bitLength), emsaPkcs1(bArr2, bitLength, this.hash))) {
            throw new GeneralSecurityException("invalid signature");
        }
    }
}
