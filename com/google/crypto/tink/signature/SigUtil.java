package com.google.crypto.tink.signature;

import com.google.crypto.tink.proto.EcdsaParams;
import com.google.crypto.tink.proto.EcdsaSignatureEncoding;
import com.google.crypto.tink.proto.EllipticCurveType;
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.proto.RsaSsaPkcs1Params;
import com.google.crypto.tink.proto.RsaSsaPssParams;
import com.google.crypto.tink.subtle.EllipticCurves;
import com.google.crypto.tink.subtle.Enums;
import java.security.GeneralSecurityException;
/* loaded from: classes2.dex */
final class SigUtil {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.crypto.tink.signature.SigUtil$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f9802a;

        /* renamed from: b  reason: collision with root package name */
        static final /* synthetic */ int[] f9803b;

        /* renamed from: c  reason: collision with root package name */
        static final /* synthetic */ int[] f9804c;

        static {
            int[] iArr = new int[HashType.values().length];
            f9804c = iArr;
            try {
                iArr[HashType.SHA256.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f9804c[HashType.SHA384.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f9804c[HashType.SHA512.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[EllipticCurveType.values().length];
            f9803b = iArr2;
            try {
                iArr2[EllipticCurveType.NIST_P256.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f9803b[EllipticCurveType.NIST_P384.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f9803b[EllipticCurveType.NIST_P521.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            int[] iArr3 = new int[EcdsaSignatureEncoding.values().length];
            f9802a = iArr3;
            try {
                iArr3[EcdsaSignatureEncoding.DER.ordinal()] = 1;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f9802a[EcdsaSignatureEncoding.IEEE_P1363.ordinal()] = 2;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    public static EllipticCurves.CurveType toCurveType(EllipticCurveType ellipticCurveType) {
        int i2 = AnonymousClass1.f9803b[ellipticCurveType.ordinal()];
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 == 3) {
                    return EllipticCurves.CurveType.NIST_P521;
                }
                throw new GeneralSecurityException("unknown curve type: " + ellipticCurveType.name());
            }
            return EllipticCurves.CurveType.NIST_P384;
        }
        return EllipticCurves.CurveType.NIST_P256;
    }

    public static EllipticCurves.EcdsaEncoding toEcdsaEncoding(EcdsaSignatureEncoding ecdsaSignatureEncoding) {
        int i2 = AnonymousClass1.f9802a[ecdsaSignatureEncoding.ordinal()];
        if (i2 != 1) {
            if (i2 == 2) {
                return EllipticCurves.EcdsaEncoding.IEEE_P1363;
            }
            throw new GeneralSecurityException("unknown ECDSA encoding: " + ecdsaSignatureEncoding.name());
        }
        return EllipticCurves.EcdsaEncoding.DER;
    }

    public static Enums.HashType toHashType(HashType hashType) {
        int i2 = AnonymousClass1.f9804c[hashType.ordinal()];
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 == 3) {
                    return Enums.HashType.SHA512;
                }
                throw new GeneralSecurityException("unsupported hash type: " + hashType.name());
            }
            return Enums.HashType.SHA384;
        }
        return Enums.HashType.SHA256;
    }

    public static void validateEcdsaParams(EcdsaParams ecdsaParams) {
        EcdsaSignatureEncoding encoding = ecdsaParams.getEncoding();
        HashType hashType = ecdsaParams.getHashType();
        EllipticCurveType curve = ecdsaParams.getCurve();
        int i2 = AnonymousClass1.f9802a[encoding.ordinal()];
        if (i2 != 1 && i2 != 2) {
            throw new GeneralSecurityException("unsupported signature encoding");
        }
        int i3 = AnonymousClass1.f9803b[curve.ordinal()];
        if (i3 == 1) {
            if (hashType != HashType.SHA256) {
                throw new GeneralSecurityException("Invalid ECDSA parameters");
            }
        } else if (i3 == 2) {
            if (hashType != HashType.SHA384 && hashType != HashType.SHA512) {
                throw new GeneralSecurityException("Invalid ECDSA parameters");
            }
        } else if (i3 != 3) {
            throw new GeneralSecurityException("Invalid ECDSA parameters");
        } else {
            if (hashType != HashType.SHA512) {
                throw new GeneralSecurityException("Invalid ECDSA parameters");
            }
        }
    }

    public static void validateRsaSsaPkcs1Params(RsaSsaPkcs1Params rsaSsaPkcs1Params) {
        toHashType(rsaSsaPkcs1Params.getHashType());
    }

    public static void validateRsaSsaPssParams(RsaSsaPssParams rsaSsaPssParams) {
        toHashType(rsaSsaPssParams.getSigHash());
        if (rsaSsaPssParams.getSigHash() != rsaSsaPssParams.getMgf1Hash()) {
            throw new GeneralSecurityException("MGF1 hash is different from signature hash");
        }
        if (rsaSsaPssParams.getSaltLength() < 0) {
            throw new GeneralSecurityException("salt length is negative");
        }
    }
}
