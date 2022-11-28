package org.bouncycastle.math.ec.rfc8032;

import com.facebook.stetho.dumpapp.Framer;
import com.google.common.base.Ascii;
import com.google.common.primitives.SignedBytes;
import java.security.SecureRandom;
import java.util.Objects;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA512Digest;
import org.bouncycastle.math.ec.rfc7748.X25519;
import org.bouncycastle.math.ec.rfc7748.X25519Field;
import org.bouncycastle.math.raw.Interleave;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.math.raw.Nat256;
import org.bouncycastle.util.Arrays;
/* loaded from: classes4.dex */
public abstract class Ed25519 {
    private static final int COORD_INTS = 8;
    private static final int L0 = -50998291;
    private static final int L1 = 19280294;
    private static final int L2 = 127719000;
    private static final int L3 = -6428113;
    private static final int L4 = 5343;
    private static final long M08L = 255;
    private static final long M28L = 268435455;
    private static final long M32L = 4294967295L;
    private static final int POINT_BYTES = 32;
    private static final int PRECOMP_BLOCKS = 8;
    private static final int PRECOMP_MASK = 7;
    private static final int PRECOMP_POINTS = 8;
    private static final int PRECOMP_SPACING = 8;
    private static final int PRECOMP_TEETH = 4;
    public static final int PREHASH_SIZE = 64;
    public static final int PUBLIC_KEY_SIZE = 32;
    private static final int SCALAR_BYTES = 32;
    private static final int SCALAR_INTS = 8;
    public static final int SECRET_KEY_SIZE = 32;
    public static final int SIGNATURE_SIZE = 64;
    private static final int WNAF_WIDTH_BASE = 7;
    private static final byte[] DOM2_PREFIX = {83, 105, 103, 69, 100, Framer.STDERR_FRAME_PREFIX, 53, 53, Framer.STDOUT_FRAME_PREFIX, 57, 32, 110, 111, 32, 69, 100, Framer.STDERR_FRAME_PREFIX, 53, 53, Framer.STDOUT_FRAME_PREFIX, 57, 32, 99, 111, 108, 108, 105, 115, 105, 111, 110, 115};
    private static final int[] P = {-19, -1, -1, -1, -1, -1, -1, Integer.MAX_VALUE};
    private static final int[] L = {1559614445, 1477600026, -1560830762, 350157278, 0, 0, 0, 268435456};
    private static final int[] B_x = {52811034, 25909283, 8072341, 50637101, 13785486, 30858332, 20483199, 20966410, 43936626, 4379245};
    private static final int[] B_y = {40265304, 26843545, 6710886, 53687091, 13421772, 40265318, 26843545, 6710886, 53687091, 13421772};
    private static final int[] C_d = {56195235, 47411844, 25868126, 40503822, 57364, 58321048, 30416477, 31930572, 57760639, 10749657};
    private static final int[] C_d2 = {45281625, 27714825, 18181821, 13898781, 114729, 49533232, 60832955, 30306712, 48412415, 4722099};
    private static final int[] C_d4 = {23454386, 55429651, 2809210, 27797563, 229458, 31957600, 54557047, 27058993, 29715967, 9444199};
    private static final Object precompLock = new Object();
    private static PointExt[] precompBaseTable = null;
    private static int[] precompBase = null;

    /* loaded from: classes4.dex */
    public static final class Algorithm {
        public static final int Ed25519 = 0;
        public static final int Ed25519ctx = 1;
        public static final int Ed25519ph = 2;
    }

    /* loaded from: classes4.dex */
    private static class F extends X25519Field {
        private F() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class PointAccum {

        /* renamed from: a  reason: collision with root package name */
        int[] f14332a;

        /* renamed from: b  reason: collision with root package name */
        int[] f14333b;

        /* renamed from: c  reason: collision with root package name */
        int[] f14334c;

        /* renamed from: d  reason: collision with root package name */
        int[] f14335d;

        /* renamed from: e  reason: collision with root package name */
        int[] f14336e;

        private PointAccum() {
            this.f14332a = X25519Field.create();
            this.f14333b = X25519Field.create();
            this.f14334c = X25519Field.create();
            this.f14335d = X25519Field.create();
            this.f14336e = X25519Field.create();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class PointAffine {

        /* renamed from: a  reason: collision with root package name */
        int[] f14337a;

        /* renamed from: b  reason: collision with root package name */
        int[] f14338b;

        private PointAffine() {
            this.f14337a = X25519Field.create();
            this.f14338b = X25519Field.create();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class PointExt {

        /* renamed from: a  reason: collision with root package name */
        int[] f14339a;

        /* renamed from: b  reason: collision with root package name */
        int[] f14340b;

        /* renamed from: c  reason: collision with root package name */
        int[] f14341c;

        /* renamed from: d  reason: collision with root package name */
        int[] f14342d;

        private PointExt() {
            this.f14339a = X25519Field.create();
            this.f14340b = X25519Field.create();
            this.f14341c = X25519Field.create();
            this.f14342d = X25519Field.create();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class PointPrecomp {

        /* renamed from: a  reason: collision with root package name */
        int[] f14343a;

        /* renamed from: b  reason: collision with root package name */
        int[] f14344b;

        /* renamed from: c  reason: collision with root package name */
        int[] f14345c;

        private PointPrecomp() {
            this.f14343a = X25519Field.create();
            this.f14344b = X25519Field.create();
            this.f14345c = X25519Field.create();
        }
    }

    private static byte[] calculateS(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        int[] iArr = new int[16];
        decodeScalar(bArr, 0, iArr);
        int[] iArr2 = new int[8];
        decodeScalar(bArr2, 0, iArr2);
        int[] iArr3 = new int[8];
        decodeScalar(bArr3, 0, iArr3);
        Nat256.mulAddTo(iArr2, iArr3, iArr);
        byte[] bArr4 = new byte[64];
        for (int i2 = 0; i2 < 16; i2++) {
            encode32(iArr[i2], bArr4, i2 * 4);
        }
        return reduceScalar(bArr4);
    }

    private static boolean checkContextVar(byte[] bArr, byte b2) {
        return (bArr == null && b2 == 0) || (bArr != null && bArr.length < 256);
    }

    private static int checkPoint(int[] iArr, int[] iArr2) {
        int[] create = X25519Field.create();
        int[] create2 = X25519Field.create();
        int[] create3 = X25519Field.create();
        X25519Field.sqr(iArr, create2);
        X25519Field.sqr(iArr2, create3);
        X25519Field.mul(create2, create3, create);
        X25519Field.sub(create3, create2, create3);
        X25519Field.mul(create, C_d, create);
        X25519Field.addOne(create);
        X25519Field.sub(create, create3, create);
        X25519Field.normalize(create);
        return X25519Field.isZero(create);
    }

    private static int checkPoint(int[] iArr, int[] iArr2, int[] iArr3) {
        int[] create = X25519Field.create();
        int[] create2 = X25519Field.create();
        int[] create3 = X25519Field.create();
        int[] create4 = X25519Field.create();
        X25519Field.sqr(iArr, create2);
        X25519Field.sqr(iArr2, create3);
        X25519Field.sqr(iArr3, create4);
        X25519Field.mul(create2, create3, create);
        X25519Field.sub(create3, create2, create3);
        X25519Field.mul(create3, create4, create3);
        X25519Field.sqr(create4, create4);
        X25519Field.mul(create, C_d, create);
        X25519Field.add(create, create4, create);
        X25519Field.sub(create, create3, create);
        X25519Field.normalize(create);
        return X25519Field.isZero(create);
    }

    private static boolean checkPointVar(byte[] bArr) {
        int[] iArr = new int[8];
        decode32(bArr, 0, iArr, 0, 8);
        iArr[7] = iArr[7] & Integer.MAX_VALUE;
        return !Nat256.gte(iArr, P);
    }

    private static boolean checkScalarVar(byte[] bArr, int[] iArr) {
        decodeScalar(bArr, 0, iArr);
        return !Nat256.gte(iArr, L);
    }

    private static byte[] copy(byte[] bArr, int i2, int i3) {
        byte[] bArr2 = new byte[i3];
        System.arraycopy(bArr, i2, bArr2, 0, i3);
        return bArr2;
    }

    private static Digest createDigest() {
        return new SHA512Digest();
    }

    public static Digest createPrehash() {
        return createDigest();
    }

    private static int decode24(byte[] bArr, int i2) {
        int i3 = i2 + 1;
        return ((bArr[i3 + 1] & 255) << 16) | (bArr[i2] & 255) | ((bArr[i3] & 255) << 8);
    }

    private static int decode32(byte[] bArr, int i2) {
        int i3 = i2 + 1;
        int i4 = i3 + 1;
        return (bArr[i4 + 1] << Ascii.CAN) | (bArr[i2] & 255) | ((bArr[i3] & 255) << 8) | ((bArr[i4] & 255) << 16);
    }

    private static void decode32(byte[] bArr, int i2, int[] iArr, int i3, int i4) {
        for (int i5 = 0; i5 < i4; i5++) {
            iArr[i3 + i5] = decode32(bArr, (i5 * 4) + i2);
        }
    }

    private static boolean decodePointVar(byte[] bArr, int i2, boolean z, PointAffine pointAffine) {
        byte[] copy = copy(bArr, i2, 32);
        if (checkPointVar(copy)) {
            int i3 = (copy[31] & 128) >>> 7;
            copy[31] = (byte) (copy[31] & Byte.MAX_VALUE);
            X25519Field.decode(copy, 0, pointAffine.f14338b);
            int[] create = X25519Field.create();
            int[] create2 = X25519Field.create();
            X25519Field.sqr(pointAffine.f14338b, create);
            X25519Field.mul(C_d, create, create2);
            X25519Field.subOne(create);
            X25519Field.addOne(create2);
            if (X25519Field.sqrtRatioVar(create, create2, pointAffine.f14337a)) {
                X25519Field.normalize(pointAffine.f14337a);
                if (i3 == 1 && X25519Field.isZeroVar(pointAffine.f14337a)) {
                    return false;
                }
                int[] iArr = pointAffine.f14337a;
                if (z ^ (i3 != (iArr[0] & 1))) {
                    X25519Field.negate(iArr, iArr);
                }
                return true;
            }
            return false;
        }
        return false;
    }

    private static void decodeScalar(byte[] bArr, int i2, int[] iArr) {
        decode32(bArr, i2, iArr, 0, 8);
    }

    private static void dom2(Digest digest, byte b2, byte[] bArr) {
        if (bArr != null) {
            byte[] bArr2 = DOM2_PREFIX;
            int length = bArr2.length;
            int i2 = length + 2;
            int length2 = bArr.length + i2;
            byte[] bArr3 = new byte[length2];
            System.arraycopy(bArr2, 0, bArr3, 0, length);
            bArr3[length] = b2;
            bArr3[length + 1] = (byte) bArr.length;
            System.arraycopy(bArr, 0, bArr3, i2, bArr.length);
            digest.update(bArr3, 0, length2);
        }
    }

    private static void encode24(int i2, byte[] bArr, int i3) {
        bArr[i3] = (byte) i2;
        int i4 = i3 + 1;
        bArr[i4] = (byte) (i2 >>> 8);
        bArr[i4 + 1] = (byte) (i2 >>> 16);
    }

    private static void encode32(int i2, byte[] bArr, int i3) {
        bArr[i3] = (byte) i2;
        int i4 = i3 + 1;
        bArr[i4] = (byte) (i2 >>> 8);
        int i5 = i4 + 1;
        bArr[i5] = (byte) (i2 >>> 16);
        bArr[i5 + 1] = (byte) (i2 >>> 24);
    }

    private static void encode56(long j2, byte[] bArr, int i2) {
        encode32((int) j2, bArr, i2);
        encode24((int) (j2 >>> 32), bArr, i2 + 4);
    }

    private static int encodePoint(PointAccum pointAccum, byte[] bArr, int i2) {
        int[] create = X25519Field.create();
        int[] create2 = X25519Field.create();
        X25519Field.inv(pointAccum.f14334c, create2);
        X25519Field.mul(pointAccum.f14332a, create2, create);
        X25519Field.mul(pointAccum.f14333b, create2, create2);
        X25519Field.normalize(create);
        X25519Field.normalize(create2);
        int checkPoint = checkPoint(create, create2);
        X25519Field.encode(create2, bArr, i2);
        int i3 = (i2 + 32) - 1;
        bArr[i3] = (byte) (((create[0] & 1) << 7) | bArr[i3]);
        return checkPoint;
    }

    public static void generatePrivateKey(SecureRandom secureRandom, byte[] bArr) {
        secureRandom.nextBytes(bArr);
    }

    public static void generatePublicKey(byte[] bArr, int i2, byte[] bArr2, int i3) {
        Digest createDigest = createDigest();
        byte[] bArr3 = new byte[createDigest.getDigestSize()];
        createDigest.update(bArr, i2, 32);
        createDigest.doFinal(bArr3, 0);
        byte[] bArr4 = new byte[32];
        pruneScalar(bArr3, 0, bArr4);
        scalarMultBaseEncoded(bArr4, bArr2, i3);
    }

    private static int getWindow4(int[] iArr, int i2) {
        return (iArr[i2 >>> 3] >>> ((i2 & 7) << 2)) & 15;
    }

    private static byte[] getWnafVar(int[] iArr, int i2) {
        int[] iArr2 = new int[16];
        int i3 = 0;
        int i4 = 8;
        int i5 = 16;
        int i6 = 0;
        while (true) {
            i4--;
            if (i4 < 0) {
                break;
            }
            int i7 = iArr[i4];
            int i8 = i5 - 1;
            iArr2[i8] = (i6 << 16) | (i7 >>> 16);
            i5 = i8 - 1;
            iArr2[i5] = i7;
            i6 = i7;
        }
        byte[] bArr = new byte[253];
        int i9 = 32 - i2;
        int i10 = 0;
        int i11 = 0;
        while (i3 < 16) {
            int i12 = iArr2[i3];
            while (i10 < 16) {
                int i13 = i12 >>> i10;
                if ((i13 & 1) == i11) {
                    i10++;
                } else {
                    int i14 = (i13 | 1) << i9;
                    bArr[(i3 << 4) + i10] = (byte) (i14 >> i9);
                    i10 += i2;
                    i11 = i14 >>> 31;
                }
            }
            i3++;
            i10 -= 16;
        }
        return bArr;
    }

    private static void implSign(Digest digest, byte[] bArr, byte[] bArr2, byte[] bArr3, int i2, byte[] bArr4, byte b2, byte[] bArr5, int i3, int i4, byte[] bArr6, int i5) {
        dom2(digest, b2, bArr4);
        digest.update(bArr, 32, 32);
        digest.update(bArr5, i3, i4);
        digest.doFinal(bArr, 0);
        byte[] reduceScalar = reduceScalar(bArr);
        byte[] bArr7 = new byte[32];
        scalarMultBaseEncoded(reduceScalar, bArr7, 0);
        dom2(digest, b2, bArr4);
        digest.update(bArr7, 0, 32);
        digest.update(bArr3, i2, 32);
        digest.update(bArr5, i3, i4);
        digest.doFinal(bArr, 0);
        byte[] calculateS = calculateS(reduceScalar, reduceScalar(bArr), bArr2);
        System.arraycopy(bArr7, 0, bArr6, i5, 32);
        System.arraycopy(calculateS, 0, bArr6, i5 + 32, 32);
    }

    private static void implSign(byte[] bArr, int i2, byte[] bArr2, byte b2, byte[] bArr3, int i3, int i4, byte[] bArr4, int i5) {
        if (!checkContextVar(bArr2, b2)) {
            throw new IllegalArgumentException("ctx");
        }
        Digest createDigest = createDigest();
        byte[] bArr5 = new byte[createDigest.getDigestSize()];
        createDigest.update(bArr, i2, 32);
        createDigest.doFinal(bArr5, 0);
        byte[] bArr6 = new byte[32];
        pruneScalar(bArr5, 0, bArr6);
        byte[] bArr7 = new byte[32];
        scalarMultBaseEncoded(bArr6, bArr7, 0);
        implSign(createDigest, bArr5, bArr6, bArr7, 0, bArr2, b2, bArr3, i3, i4, bArr4, i5);
    }

    private static void implSign(byte[] bArr, int i2, byte[] bArr2, int i3, byte[] bArr3, byte b2, byte[] bArr4, int i4, int i5, byte[] bArr5, int i6) {
        if (!checkContextVar(bArr3, b2)) {
            throw new IllegalArgumentException("ctx");
        }
        Digest createDigest = createDigest();
        byte[] bArr6 = new byte[createDigest.getDigestSize()];
        createDigest.update(bArr, i2, 32);
        createDigest.doFinal(bArr6, 0);
        byte[] bArr7 = new byte[32];
        pruneScalar(bArr6, 0, bArr7);
        implSign(createDigest, bArr6, bArr7, bArr2, i3, bArr3, b2, bArr4, i4, i5, bArr5, i6);
    }

    private static boolean implVerify(byte[] bArr, int i2, byte[] bArr2, int i3, byte[] bArr3, byte b2, byte[] bArr4, int i4, int i5) {
        if (checkContextVar(bArr3, b2)) {
            byte[] copy = copy(bArr, i2, 32);
            byte[] copy2 = copy(bArr, i2 + 32, 32);
            if (checkPointVar(copy)) {
                int[] iArr = new int[8];
                if (checkScalarVar(copy2, iArr)) {
                    PointAffine pointAffine = new PointAffine();
                    if (decodePointVar(bArr2, i3, true, pointAffine)) {
                        Digest createDigest = createDigest();
                        byte[] bArr5 = new byte[createDigest.getDigestSize()];
                        dom2(createDigest, b2, bArr3);
                        createDigest.update(copy, 0, 32);
                        createDigest.update(bArr2, i3, 32);
                        createDigest.update(bArr4, i4, i5);
                        createDigest.doFinal(bArr5, 0);
                        int[] iArr2 = new int[8];
                        decodeScalar(reduceScalar(bArr5), 0, iArr2);
                        PointAccum pointAccum = new PointAccum();
                        scalarMultStrausVar(iArr, iArr2, pointAffine, pointAccum);
                        byte[] bArr6 = new byte[32];
                        return encodePoint(pointAccum, bArr6, 0) != 0 && Arrays.areEqual(bArr6, copy);
                    }
                    return false;
                }
                return false;
            }
            return false;
        }
        throw new IllegalArgumentException("ctx");
    }

    private static boolean isNeutralElementVar(int[] iArr, int[] iArr2) {
        return X25519Field.isZeroVar(iArr) && X25519Field.isOneVar(iArr2);
    }

    private static boolean isNeutralElementVar(int[] iArr, int[] iArr2, int[] iArr3) {
        return X25519Field.isZeroVar(iArr) && X25519Field.areEqualVar(iArr2, iArr3);
    }

    private static void pointAdd(PointExt pointExt, PointAccum pointAccum) {
        int[] create = X25519Field.create();
        int[] create2 = X25519Field.create();
        int[] create3 = X25519Field.create();
        int[] create4 = X25519Field.create();
        int[] iArr = pointAccum.f14335d;
        int[] create5 = X25519Field.create();
        int[] create6 = X25519Field.create();
        int[] iArr2 = pointAccum.f14336e;
        X25519Field.apm(pointAccum.f14333b, pointAccum.f14332a, create2, create);
        X25519Field.apm(pointExt.f14340b, pointExt.f14339a, create4, create3);
        X25519Field.mul(create, create3, create);
        X25519Field.mul(create2, create4, create2);
        X25519Field.mul(pointAccum.f14335d, pointAccum.f14336e, create3);
        X25519Field.mul(create3, pointExt.f14342d, create3);
        X25519Field.mul(create3, C_d2, create3);
        X25519Field.mul(pointAccum.f14334c, pointExt.f14341c, create4);
        X25519Field.add(create4, create4, create4);
        X25519Field.apm(create2, create, iArr2, iArr);
        X25519Field.apm(create4, create3, create6, create5);
        X25519Field.carry(create6);
        X25519Field.mul(iArr, create5, pointAccum.f14332a);
        X25519Field.mul(create6, iArr2, pointAccum.f14333b);
        X25519Field.mul(create5, create6, pointAccum.f14334c);
    }

    private static void pointAdd(PointExt pointExt, PointExt pointExt2) {
        int[] create = X25519Field.create();
        int[] create2 = X25519Field.create();
        int[] create3 = X25519Field.create();
        int[] create4 = X25519Field.create();
        int[] create5 = X25519Field.create();
        int[] create6 = X25519Field.create();
        int[] create7 = X25519Field.create();
        int[] create8 = X25519Field.create();
        X25519Field.apm(pointExt.f14340b, pointExt.f14339a, create2, create);
        X25519Field.apm(pointExt2.f14340b, pointExt2.f14339a, create4, create3);
        X25519Field.mul(create, create3, create);
        X25519Field.mul(create2, create4, create2);
        X25519Field.mul(pointExt.f14342d, pointExt2.f14342d, create3);
        X25519Field.mul(create3, C_d2, create3);
        X25519Field.mul(pointExt.f14341c, pointExt2.f14341c, create4);
        X25519Field.add(create4, create4, create4);
        X25519Field.apm(create2, create, create8, create5);
        X25519Field.apm(create4, create3, create7, create6);
        X25519Field.carry(create7);
        X25519Field.mul(create5, create6, pointExt2.f14339a);
        X25519Field.mul(create7, create8, pointExt2.f14340b);
        X25519Field.mul(create6, create7, pointExt2.f14341c);
        X25519Field.mul(create5, create8, pointExt2.f14342d);
    }

    private static void pointAddPrecomp(PointPrecomp pointPrecomp, PointAccum pointAccum) {
        int[] create = X25519Field.create();
        int[] create2 = X25519Field.create();
        int[] create3 = X25519Field.create();
        int[] iArr = pointAccum.f14335d;
        int[] create4 = X25519Field.create();
        int[] create5 = X25519Field.create();
        int[] iArr2 = pointAccum.f14336e;
        X25519Field.apm(pointAccum.f14333b, pointAccum.f14332a, create2, create);
        X25519Field.mul(create, pointPrecomp.f14344b, create);
        X25519Field.mul(create2, pointPrecomp.f14343a, create2);
        X25519Field.mul(pointAccum.f14335d, pointAccum.f14336e, create3);
        X25519Field.mul(create3, pointPrecomp.f14345c, create3);
        X25519Field.apm(create2, create, iArr2, iArr);
        X25519Field.apm(pointAccum.f14334c, create3, create5, create4);
        X25519Field.carry(create5);
        X25519Field.mul(iArr, create4, pointAccum.f14332a);
        X25519Field.mul(create5, iArr2, pointAccum.f14333b);
        X25519Field.mul(create4, create5, pointAccum.f14334c);
    }

    private static void pointAddVar(boolean z, PointExt pointExt, PointAccum pointAccum) {
        int[] iArr;
        int[] iArr2;
        int[] iArr3;
        int[] iArr4;
        int[] create = X25519Field.create();
        int[] create2 = X25519Field.create();
        int[] create3 = X25519Field.create();
        int[] create4 = X25519Field.create();
        int[] iArr5 = pointAccum.f14335d;
        int[] create5 = X25519Field.create();
        int[] create6 = X25519Field.create();
        int[] iArr6 = pointAccum.f14336e;
        if (z) {
            iArr2 = create3;
            iArr = create4;
            iArr4 = create5;
            iArr3 = create6;
        } else {
            iArr = create3;
            iArr2 = create4;
            iArr3 = create5;
            iArr4 = create6;
        }
        X25519Field.apm(pointAccum.f14333b, pointAccum.f14332a, create2, create);
        X25519Field.apm(pointExt.f14340b, pointExt.f14339a, iArr2, iArr);
        X25519Field.mul(create, create3, create);
        X25519Field.mul(create2, create4, create2);
        X25519Field.mul(pointAccum.f14335d, pointAccum.f14336e, create3);
        X25519Field.mul(create3, pointExt.f14342d, create3);
        X25519Field.mul(create3, C_d2, create3);
        X25519Field.mul(pointAccum.f14334c, pointExt.f14341c, create4);
        X25519Field.add(create4, create4, create4);
        X25519Field.apm(create2, create, iArr6, iArr5);
        X25519Field.apm(create4, create3, iArr4, iArr3);
        X25519Field.carry(iArr4);
        X25519Field.mul(iArr5, create5, pointAccum.f14332a);
        X25519Field.mul(create6, iArr6, pointAccum.f14333b);
        X25519Field.mul(create5, create6, pointAccum.f14334c);
    }

    private static void pointAddVar(boolean z, PointExt pointExt, PointExt pointExt2, PointExt pointExt3) {
        int[] iArr;
        int[] iArr2;
        int[] iArr3;
        int[] iArr4;
        int[] create = X25519Field.create();
        int[] create2 = X25519Field.create();
        int[] create3 = X25519Field.create();
        int[] create4 = X25519Field.create();
        int[] create5 = X25519Field.create();
        int[] create6 = X25519Field.create();
        int[] create7 = X25519Field.create();
        int[] create8 = X25519Field.create();
        if (z) {
            iArr2 = create3;
            iArr = create4;
            iArr4 = create6;
            iArr3 = create7;
        } else {
            iArr = create3;
            iArr2 = create4;
            iArr3 = create6;
            iArr4 = create7;
        }
        X25519Field.apm(pointExt.f14340b, pointExt.f14339a, create2, create);
        X25519Field.apm(pointExt2.f14340b, pointExt2.f14339a, iArr2, iArr);
        X25519Field.mul(create, create3, create);
        X25519Field.mul(create2, create4, create2);
        X25519Field.mul(pointExt.f14342d, pointExt2.f14342d, create3);
        X25519Field.mul(create3, C_d2, create3);
        X25519Field.mul(pointExt.f14341c, pointExt2.f14341c, create4);
        X25519Field.add(create4, create4, create4);
        X25519Field.apm(create2, create, create8, create5);
        X25519Field.apm(create4, create3, iArr4, iArr3);
        X25519Field.carry(iArr4);
        X25519Field.mul(create5, create6, pointExt3.f14339a);
        X25519Field.mul(create7, create8, pointExt3.f14340b);
        X25519Field.mul(create6, create7, pointExt3.f14341c);
        X25519Field.mul(create5, create8, pointExt3.f14342d);
    }

    private static PointExt pointCopy(PointAccum pointAccum) {
        PointExt pointExt = new PointExt();
        X25519Field.copy(pointAccum.f14332a, 0, pointExt.f14339a, 0);
        X25519Field.copy(pointAccum.f14333b, 0, pointExt.f14340b, 0);
        X25519Field.copy(pointAccum.f14334c, 0, pointExt.f14341c, 0);
        X25519Field.mul(pointAccum.f14335d, pointAccum.f14336e, pointExt.f14342d);
        return pointExt;
    }

    private static PointExt pointCopy(PointAffine pointAffine) {
        PointExt pointExt = new PointExt();
        X25519Field.copy(pointAffine.f14337a, 0, pointExt.f14339a, 0);
        X25519Field.copy(pointAffine.f14338b, 0, pointExt.f14340b, 0);
        pointExtendXY(pointExt);
        return pointExt;
    }

    private static PointExt pointCopy(PointExt pointExt) {
        PointExt pointExt2 = new PointExt();
        pointCopy(pointExt, pointExt2);
        return pointExt2;
    }

    private static void pointCopy(PointAffine pointAffine, PointAccum pointAccum) {
        X25519Field.copy(pointAffine.f14337a, 0, pointAccum.f14332a, 0);
        X25519Field.copy(pointAffine.f14338b, 0, pointAccum.f14333b, 0);
        pointExtendXY(pointAccum);
    }

    private static void pointCopy(PointExt pointExt, PointExt pointExt2) {
        X25519Field.copy(pointExt.f14339a, 0, pointExt2.f14339a, 0);
        X25519Field.copy(pointExt.f14340b, 0, pointExt2.f14340b, 0);
        X25519Field.copy(pointExt.f14341c, 0, pointExt2.f14341c, 0);
        X25519Field.copy(pointExt.f14342d, 0, pointExt2.f14342d, 0);
    }

    private static void pointDouble(PointAccum pointAccum) {
        int[] create = X25519Field.create();
        int[] create2 = X25519Field.create();
        int[] create3 = X25519Field.create();
        int[] iArr = pointAccum.f14335d;
        int[] create4 = X25519Field.create();
        int[] create5 = X25519Field.create();
        int[] iArr2 = pointAccum.f14336e;
        X25519Field.sqr(pointAccum.f14332a, create);
        X25519Field.sqr(pointAccum.f14333b, create2);
        X25519Field.sqr(pointAccum.f14334c, create3);
        X25519Field.add(create3, create3, create3);
        X25519Field.apm(create, create2, iArr2, create5);
        X25519Field.add(pointAccum.f14332a, pointAccum.f14333b, iArr);
        X25519Field.sqr(iArr, iArr);
        X25519Field.sub(iArr2, iArr, iArr);
        X25519Field.add(create3, create5, create4);
        X25519Field.carry(create4);
        X25519Field.mul(iArr, create4, pointAccum.f14332a);
        X25519Field.mul(create5, iArr2, pointAccum.f14333b);
        X25519Field.mul(create4, create5, pointAccum.f14334c);
    }

    private static void pointExtendXY(PointAccum pointAccum) {
        X25519Field.one(pointAccum.f14334c);
        X25519Field.copy(pointAccum.f14332a, 0, pointAccum.f14335d, 0);
        X25519Field.copy(pointAccum.f14333b, 0, pointAccum.f14336e, 0);
    }

    private static void pointExtendXY(PointExt pointExt) {
        X25519Field.one(pointExt.f14341c);
        X25519Field.mul(pointExt.f14339a, pointExt.f14340b, pointExt.f14342d);
    }

    private static void pointLookup(int i2, int i3, PointPrecomp pointPrecomp) {
        int i4 = i2 * 8 * 3 * 10;
        for (int i5 = 0; i5 < 8; i5++) {
            int i6 = ((i5 ^ i3) - 1) >> 31;
            X25519Field.cmov(i6, precompBase, i4, pointPrecomp.f14343a, 0);
            int i7 = i4 + 10;
            X25519Field.cmov(i6, precompBase, i7, pointPrecomp.f14344b, 0);
            int i8 = i7 + 10;
            X25519Field.cmov(i6, precompBase, i8, pointPrecomp.f14345c, 0);
            i4 = i8 + 10;
        }
    }

    private static void pointLookup(int[] iArr, int i2, PointExt pointExt) {
        int i3 = i2 * 40;
        X25519Field.copy(iArr, i3, pointExt.f14339a, 0);
        int i4 = i3 + 10;
        X25519Field.copy(iArr, i4, pointExt.f14340b, 0);
        int i5 = i4 + 10;
        X25519Field.copy(iArr, i5, pointExt.f14341c, 0);
        X25519Field.copy(iArr, i5 + 10, pointExt.f14342d, 0);
    }

    private static void pointLookup(int[] iArr, int i2, int[] iArr2, PointExt pointExt) {
        int window4 = getWindow4(iArr, i2);
        int i3 = (window4 >>> 3) ^ 1;
        int i4 = (window4 ^ (-i3)) & 7;
        int i5 = 0;
        for (int i6 = 0; i6 < 8; i6++) {
            int i7 = ((i6 ^ i4) - 1) >> 31;
            X25519Field.cmov(i7, iArr2, i5, pointExt.f14339a, 0);
            int i8 = i5 + 10;
            X25519Field.cmov(i7, iArr2, i8, pointExt.f14340b, 0);
            int i9 = i8 + 10;
            X25519Field.cmov(i7, iArr2, i9, pointExt.f14341c, 0);
            int i10 = i9 + 10;
            X25519Field.cmov(i7, iArr2, i10, pointExt.f14342d, 0);
            i5 = i10 + 10;
        }
        X25519Field.cnegate(i3, pointExt.f14339a);
        X25519Field.cnegate(i3, pointExt.f14342d);
    }

    private static int[] pointPrecompute(PointAffine pointAffine, int i2) {
        PointExt pointCopy = pointCopy(pointAffine);
        PointExt pointCopy2 = pointCopy(pointCopy);
        pointAdd(pointCopy, pointCopy2);
        int[] createTable = X25519Field.createTable(i2 * 4);
        int i3 = 0;
        int i4 = 0;
        while (true) {
            X25519Field.copy(pointCopy.f14339a, 0, createTable, i3);
            int i5 = i3 + 10;
            X25519Field.copy(pointCopy.f14340b, 0, createTable, i5);
            int i6 = i5 + 10;
            X25519Field.copy(pointCopy.f14341c, 0, createTable, i6);
            int i7 = i6 + 10;
            X25519Field.copy(pointCopy.f14342d, 0, createTable, i7);
            i3 = i7 + 10;
            i4++;
            if (i4 == i2) {
                return createTable;
            }
            pointAdd(pointCopy2, pointCopy);
        }
    }

    private static PointExt[] pointPrecomputeVar(PointExt pointExt, int i2) {
        PointExt pointExt2 = new PointExt();
        pointAddVar(false, pointExt, pointExt, pointExt2);
        PointExt[] pointExtArr = new PointExt[i2];
        pointExtArr[0] = pointCopy(pointExt);
        for (int i3 = 1; i3 < i2; i3++) {
            PointExt pointExt3 = pointExtArr[i3 - 1];
            PointExt pointExt4 = new PointExt();
            pointExtArr[i3] = pointExt4;
            pointAddVar(false, pointExt3, pointExt2, pointExt4);
        }
        return pointExtArr;
    }

    private static void pointSetNeutral(PointAccum pointAccum) {
        X25519Field.zero(pointAccum.f14332a);
        X25519Field.one(pointAccum.f14333b);
        X25519Field.one(pointAccum.f14334c);
        X25519Field.zero(pointAccum.f14335d);
        X25519Field.one(pointAccum.f14336e);
    }

    private static void pointSetNeutral(PointExt pointExt) {
        X25519Field.zero(pointExt.f14339a);
        X25519Field.one(pointExt.f14340b);
        X25519Field.one(pointExt.f14341c);
        X25519Field.zero(pointExt.f14342d);
    }

    public static void precompute() {
        int i2;
        synchronized (precompLock) {
            if (precompBase != null) {
                return;
            }
            PointExt pointExt = new PointExt();
            int[] iArr = B_x;
            X25519Field.copy(iArr, 0, pointExt.f14339a, 0);
            int[] iArr2 = B_y;
            X25519Field.copy(iArr2, 0, pointExt.f14340b, 0);
            pointExtendXY(pointExt);
            precompBaseTable = pointPrecomputeVar(pointExt, 32);
            PointAccum pointAccum = new PointAccum();
            X25519Field.copy(iArr, 0, pointAccum.f14332a, 0);
            X25519Field.copy(iArr2, 0, pointAccum.f14333b, 0);
            pointExtendXY(pointAccum);
            precompBase = X25519Field.createTable(192);
            int i3 = 0;
            for (int i4 = 0; i4 < 8; i4++) {
                PointExt[] pointExtArr = new PointExt[4];
                PointExt pointExt2 = new PointExt();
                pointSetNeutral(pointExt2);
                int i5 = 0;
                while (true) {
                    i2 = 1;
                    if (i5 >= 4) {
                        break;
                    }
                    pointAddVar(true, pointExt2, pointCopy(pointAccum), pointExt2);
                    pointDouble(pointAccum);
                    pointExtArr[i5] = pointCopy(pointAccum);
                    if (i4 + i5 != 10) {
                        while (i2 < 8) {
                            pointDouble(pointAccum);
                            i2++;
                        }
                    }
                    i5++;
                }
                PointExt[] pointExtArr2 = new PointExt[8];
                pointExtArr2[0] = pointExt2;
                int i6 = 0;
                int i7 = 1;
                while (i6 < 3) {
                    int i8 = i2 << i6;
                    int i9 = 0;
                    while (i9 < i8) {
                        PointExt pointExt3 = pointExtArr2[i7 - i8];
                        PointExt pointExt4 = pointExtArr[i6];
                        PointExt pointExt5 = new PointExt();
                        pointExtArr2[i7] = pointExt5;
                        pointAddVar(false, pointExt3, pointExt4, pointExt5);
                        i9++;
                        i7++;
                    }
                    i6++;
                    i2 = 1;
                }
                int[] createTable = X25519Field.createTable(8);
                int[] create = X25519Field.create();
                X25519Field.copy(pointExtArr2[0].f14341c, 0, create, 0);
                X25519Field.copy(create, 0, createTable, 0);
                int i10 = 0;
                while (true) {
                    i10++;
                    if (i10 >= 8) {
                        break;
                    }
                    X25519Field.mul(create, pointExtArr2[i10].f14341c, create);
                    X25519Field.copy(create, 0, createTable, i10 * 10);
                }
                X25519Field.add(create, create, create);
                X25519Field.invVar(create, create);
                int i11 = i10 - 1;
                int[] create2 = X25519Field.create();
                while (i11 > 0) {
                    int i12 = i11 - 1;
                    X25519Field.copy(createTable, i12 * 10, create2, 0);
                    X25519Field.mul(create2, create, create2);
                    X25519Field.copy(create2, 0, createTable, i11 * 10);
                    X25519Field.mul(create, pointExtArr2[i11].f14341c, create);
                    i11 = i12;
                }
                X25519Field.copy(create, 0, createTable, 0);
                for (int i13 = 0; i13 < 8; i13++) {
                    PointExt pointExt6 = pointExtArr2[i13];
                    int[] create3 = X25519Field.create();
                    int[] create4 = X25519Field.create();
                    X25519Field.copy(createTable, i13 * 10, create4, 0);
                    X25519Field.mul(pointExt6.f14339a, create4, create3);
                    X25519Field.mul(pointExt6.f14340b, create4, create4);
                    PointPrecomp pointPrecomp = new PointPrecomp();
                    X25519Field.apm(create4, create3, pointPrecomp.f14343a, pointPrecomp.f14344b);
                    X25519Field.mul(create3, create4, pointPrecomp.f14345c);
                    int[] iArr3 = pointPrecomp.f14345c;
                    X25519Field.mul(iArr3, C_d4, iArr3);
                    X25519Field.normalize(pointPrecomp.f14343a);
                    X25519Field.normalize(pointPrecomp.f14344b);
                    X25519Field.copy(pointPrecomp.f14343a, 0, precompBase, i3);
                    int i14 = i3 + 10;
                    X25519Field.copy(pointPrecomp.f14344b, 0, precompBase, i14);
                    int i15 = i14 + 10;
                    X25519Field.copy(pointPrecomp.f14345c, 0, precompBase, i15);
                    i3 = i15 + 10;
                }
            }
        }
    }

    private static void pruneScalar(byte[] bArr, int i2, byte[] bArr2) {
        System.arraycopy(bArr, i2, bArr2, 0, 32);
        bArr2[0] = (byte) (bArr2[0] & 248);
        bArr2[31] = (byte) (bArr2[31] & Byte.MAX_VALUE);
        bArr2[31] = (byte) (bArr2[31] | SignedBytes.MAX_POWER_OF_TWO);
    }

    private static byte[] reduceScalar(byte[] bArr) {
        long decode24 = (decode24(bArr, 4) << 4) & 4294967295L;
        long decode32 = decode32(bArr, 7) & 4294967295L;
        long decode242 = (decode24(bArr, 11) << 4) & 4294967295L;
        long decode322 = decode32(bArr, 14) & 4294967295L;
        long decode243 = (decode24(bArr, 18) << 4) & 4294967295L;
        long decode323 = decode32(bArr, 21) & 4294967295L;
        long decode324 = decode32(bArr, 28) & 4294967295L;
        long decode325 = decode32(bArr, 49) & 4294967295L;
        long decode244 = (decode24(bArr, 53) << 4) & 4294967295L;
        long decode326 = decode32(bArr, 56) & 4294967295L;
        long decode245 = (decode24(bArr, 60) << 4) & 4294967295L;
        long j2 = bArr[63] & M08L;
        long decode246 = ((decode24(bArr, 46) << 4) & 4294967295L) - (j2 * 5343);
        long j3 = decode245 + (decode326 >> 28);
        long j4 = decode326 & M28L;
        long decode247 = (((decode24(bArr, 32) << 4) & 4294967295L) - (j2 * (-50998291))) - (j3 * 19280294);
        long decode327 = ((decode32(bArr, 35) & 4294967295L) - (j2 * 19280294)) - (j3 * 127719000);
        long decode328 = ((decode32(bArr, 42) & 4294967295L) - (j2 * (-6428113))) - (j3 * 5343);
        long decode248 = ((((decode24(bArr, 39) << 4) & 4294967295L) - (j2 * 127719000)) - (j3 * (-6428113))) - (j4 * 5343);
        long j5 = decode244 + (decode325 >> 28);
        long j6 = decode325 & M28L;
        long j7 = (decode327 - (j4 * (-6428113))) - (j5 * 5343);
        long j8 = ((decode247 - (j4 * 127719000)) - (j5 * (-6428113))) - (j6 * 5343);
        long j9 = decode246 + (decode328 >> 28);
        long j10 = (decode328 & M28L) + (decode248 >> 28);
        long j11 = (decode322 - (j9 * (-50998291))) - (j10 * 19280294);
        long j12 = ((decode243 - (j6 * (-50998291))) - (j9 * 19280294)) - (j10 * 127719000);
        long decode249 = ((((((decode24(bArr, 25) << 4) & 4294967295L) - (j4 * (-50998291))) - (j5 * 19280294)) - (j6 * 127719000)) - (j9 * (-6428113))) - (j10 * 5343);
        long j13 = (decode248 & M28L) + (j7 >> 28);
        long j14 = (decode242 - (j10 * (-50998291))) - (j13 * 19280294);
        long j15 = j11 - (j13 * 127719000);
        long j16 = ((((decode323 - (j5 * (-50998291))) - (j6 * 19280294)) - (j9 * 127719000)) - (j10 * (-6428113))) - (j13 * 5343);
        long j17 = (j7 & M28L) + (j8 >> 28);
        long j18 = j8 & M28L;
        long j19 = decode24 - (j17 * (-50998291));
        long j20 = (decode32 - (j13 * (-50998291))) - (j17 * 19280294);
        long j21 = j14 - (j17 * 127719000);
        long j22 = j15 - (j17 * (-6428113));
        long j23 = (j12 - (j13 * (-6428113))) - (j17 * 5343);
        long j24 = (((((decode324 - (j3 * (-50998291))) - (j4 * 19280294)) - (j5 * 127719000)) - (j6 * (-6428113))) - (j9 * 5343)) + (decode249 >> 28);
        long j25 = decode249 & M28L;
        long j26 = j24 & M28L;
        long j27 = j26 >>> 27;
        long j28 = j18 + (j24 >> 28) + j27;
        long decode329 = (decode32(bArr, 0) & 4294967295L) - (j28 * (-50998291));
        long j29 = (j19 - (j28 * 19280294)) + (decode329 >> 28);
        long j30 = decode329 & M28L;
        long j31 = (j20 - (j28 * 127719000)) + (j29 >> 28);
        long j32 = j29 & M28L;
        long j33 = (j21 - (j28 * (-6428113))) + (j31 >> 28);
        long j34 = j31 & M28L;
        long j35 = (j22 - (j28 * 5343)) + (j33 >> 28);
        long j36 = j33 & M28L;
        long j37 = j23 + (j35 >> 28);
        long j38 = j35 & M28L;
        long j39 = j16 + (j37 >> 28);
        long j40 = j37 & M28L;
        long j41 = j25 + (j39 >> 28);
        long j42 = j39 & M28L;
        long j43 = j26 + (j41 >> 28);
        long j44 = j41 & M28L;
        long j45 = j43 >> 28;
        long j46 = j43 & M28L;
        long j47 = j45 - j27;
        long j48 = j30 + (j47 & (-50998291));
        long j49 = j32 + (j47 & 19280294) + (j48 >> 28);
        long j50 = j48 & M28L;
        long j51 = j34 + (j47 & 127719000) + (j49 >> 28);
        long j52 = j49 & M28L;
        long j53 = j36 + (j47 & (-6428113)) + (j51 >> 28);
        long j54 = j51 & M28L;
        long j55 = j38 + (j47 & 5343) + (j53 >> 28);
        long j56 = j53 & M28L;
        long j57 = j40 + (j55 >> 28);
        long j58 = j55 & M28L;
        long j59 = j42 + (j57 >> 28);
        long j60 = j57 & M28L;
        long j61 = j44 + (j59 >> 28);
        long j62 = j59 & M28L;
        long j63 = j61 & M28L;
        byte[] bArr2 = new byte[32];
        encode56(j50 | (j52 << 28), bArr2, 0);
        encode56((j56 << 28) | j54, bArr2, 7);
        encode56(j58 | (j60 << 28), bArr2, 14);
        encode56(j62 | (j63 << 28), bArr2, 21);
        encode32((int) (j46 + (j61 >> 28)), bArr2, 28);
        return bArr2;
    }

    private static void scalarMult(byte[] bArr, PointAffine pointAffine, PointAccum pointAccum) {
        int[] iArr = new int[8];
        decodeScalar(bArr, 0, iArr);
        Nat.shiftDownBits(8, iArr, 3, 1);
        Nat.cadd(8, (~iArr[0]) & 1, iArr, L, iArr);
        Nat.shiftDownBit(8, iArr, 0);
        int[] pointPrecompute = pointPrecompute(pointAffine, 8);
        PointExt pointExt = new PointExt();
        pointCopy(pointAffine, pointAccum);
        pointLookup(pointPrecompute, 7, pointExt);
        pointAdd(pointExt, pointAccum);
        int i2 = 62;
        while (true) {
            pointLookup(iArr, i2, pointPrecompute, pointExt);
            pointAdd(pointExt, pointAccum);
            pointDouble(pointAccum);
            pointDouble(pointAccum);
            pointDouble(pointAccum);
            i2--;
            if (i2 < 0) {
                return;
            }
            pointDouble(pointAccum);
        }
    }

    private static void scalarMultBase(byte[] bArr, PointAccum pointAccum) {
        precompute();
        int[] iArr = new int[8];
        decodeScalar(bArr, 0, iArr);
        Nat.cadd(8, (~iArr[0]) & 1, iArr, L, iArr);
        Nat.shiftDownBit(8, iArr, 1);
        for (int i2 = 0; i2 < 8; i2++) {
            iArr[i2] = Interleave.shuffle2(iArr[i2]);
        }
        PointPrecomp pointPrecomp = new PointPrecomp();
        pointSetNeutral(pointAccum);
        int i3 = 28;
        while (true) {
            for (int i4 = 0; i4 < 8; i4++) {
                int i5 = iArr[i4] >>> i3;
                int i6 = (i5 >>> 3) & 1;
                pointLookup(i4, (i5 ^ (-i6)) & 7, pointPrecomp);
                X25519Field.cswap(i6, pointPrecomp.f14343a, pointPrecomp.f14344b);
                X25519Field.cnegate(i6, pointPrecomp.f14345c);
                pointAddPrecomp(pointPrecomp, pointAccum);
            }
            i3 -= 4;
            if (i3 < 0) {
                return;
            }
            pointDouble(pointAccum);
        }
    }

    private static void scalarMultBaseEncoded(byte[] bArr, byte[] bArr2, int i2) {
        PointAccum pointAccum = new PointAccum();
        scalarMultBase(bArr, pointAccum);
        if (encodePoint(pointAccum, bArr2, i2) == 0) {
            throw new IllegalStateException();
        }
    }

    public static void scalarMultBaseYZ(X25519.Friend friend, byte[] bArr, int i2, int[] iArr, int[] iArr2) {
        Objects.requireNonNull(friend, "This method is only for use by X25519");
        byte[] bArr2 = new byte[32];
        pruneScalar(bArr, i2, bArr2);
        PointAccum pointAccum = new PointAccum();
        scalarMultBase(bArr2, pointAccum);
        if (checkPoint(pointAccum.f14332a, pointAccum.f14333b, pointAccum.f14334c) == 0) {
            throw new IllegalStateException();
        }
        X25519Field.copy(pointAccum.f14333b, 0, iArr, 0);
        X25519Field.copy(pointAccum.f14334c, 0, iArr2, 0);
    }

    private static void scalarMultOrderVar(PointAffine pointAffine, PointAccum pointAccum) {
        byte[] wnafVar = getWnafVar(L, 5);
        PointExt[] pointPrecomputeVar = pointPrecomputeVar(pointCopy(pointAffine), 8);
        pointSetNeutral(pointAccum);
        int i2 = 252;
        while (true) {
            byte b2 = wnafVar[i2];
            if (b2 != 0) {
                int i3 = b2 >> Ascii.US;
                pointAddVar(i3 != 0, pointPrecomputeVar[(b2 ^ i3) >>> 1], pointAccum);
            }
            i2--;
            if (i2 < 0) {
                return;
            }
            pointDouble(pointAccum);
        }
    }

    private static void scalarMultStrausVar(int[] iArr, int[] iArr2, PointAffine pointAffine, PointAccum pointAccum) {
        precompute();
        byte[] wnafVar = getWnafVar(iArr, 7);
        byte[] wnafVar2 = getWnafVar(iArr2, 5);
        PointExt[] pointPrecomputeVar = pointPrecomputeVar(pointCopy(pointAffine), 8);
        pointSetNeutral(pointAccum);
        int i2 = 252;
        while (true) {
            byte b2 = wnafVar[i2];
            if (b2 != 0) {
                int i3 = b2 >> Ascii.US;
                pointAddVar(i3 != 0, precompBaseTable[(b2 ^ i3) >>> 1], pointAccum);
            }
            byte b3 = wnafVar2[i2];
            if (b3 != 0) {
                int i4 = b3 >> Ascii.US;
                pointAddVar(i4 != 0, pointPrecomputeVar[(b3 ^ i4) >>> 1], pointAccum);
            }
            i2--;
            if (i2 < 0) {
                return;
            }
            pointDouble(pointAccum);
        }
    }

    public static void sign(byte[] bArr, int i2, byte[] bArr2, int i3, int i4, byte[] bArr3, int i5) {
        implSign(bArr, i2, null, (byte) 0, bArr2, i3, i4, bArr3, i5);
    }

    public static void sign(byte[] bArr, int i2, byte[] bArr2, int i3, byte[] bArr3, int i4, int i5, byte[] bArr4, int i6) {
        implSign(bArr, i2, bArr2, i3, null, (byte) 0, bArr3, i4, i5, bArr4, i6);
    }

    public static void sign(byte[] bArr, int i2, byte[] bArr2, int i3, byte[] bArr3, byte[] bArr4, int i4, int i5, byte[] bArr5, int i6) {
        implSign(bArr, i2, bArr2, i3, bArr3, (byte) 0, bArr4, i4, i5, bArr5, i6);
    }

    public static void sign(byte[] bArr, int i2, byte[] bArr2, byte[] bArr3, int i3, int i4, byte[] bArr4, int i5) {
        implSign(bArr, i2, bArr2, (byte) 0, bArr3, i3, i4, bArr4, i5);
    }

    public static void signPrehash(byte[] bArr, int i2, byte[] bArr2, int i3, byte[] bArr3, Digest digest, byte[] bArr4, int i4) {
        byte[] bArr5 = new byte[64];
        if (64 != digest.doFinal(bArr5, 0)) {
            throw new IllegalArgumentException("ph");
        }
        implSign(bArr, i2, bArr2, i3, bArr3, (byte) 1, bArr5, 0, 64, bArr4, i4);
    }

    public static void signPrehash(byte[] bArr, int i2, byte[] bArr2, int i3, byte[] bArr3, byte[] bArr4, int i4, byte[] bArr5, int i5) {
        implSign(bArr, i2, bArr2, i3, bArr3, (byte) 1, bArr4, i4, 64, bArr5, i5);
    }

    public static void signPrehash(byte[] bArr, int i2, byte[] bArr2, Digest digest, byte[] bArr3, int i3) {
        byte[] bArr4 = new byte[64];
        if (64 != digest.doFinal(bArr4, 0)) {
            throw new IllegalArgumentException("ph");
        }
        implSign(bArr, i2, bArr2, (byte) 1, bArr4, 0, 64, bArr3, i3);
    }

    public static void signPrehash(byte[] bArr, int i2, byte[] bArr2, byte[] bArr3, int i3, byte[] bArr4, int i4) {
        implSign(bArr, i2, bArr2, (byte) 1, bArr3, i3, 64, bArr4, i4);
    }

    public static boolean validatePublicKeyFull(byte[] bArr, int i2) {
        PointAffine pointAffine = new PointAffine();
        if (decodePointVar(bArr, i2, false, pointAffine)) {
            X25519Field.normalize(pointAffine.f14337a);
            X25519Field.normalize(pointAffine.f14338b);
            if (isNeutralElementVar(pointAffine.f14337a, pointAffine.f14338b)) {
                return false;
            }
            PointAccum pointAccum = new PointAccum();
            scalarMultOrderVar(pointAffine, pointAccum);
            X25519Field.normalize(pointAccum.f14332a);
            X25519Field.normalize(pointAccum.f14333b);
            X25519Field.normalize(pointAccum.f14334c);
            return isNeutralElementVar(pointAccum.f14332a, pointAccum.f14333b, pointAccum.f14334c);
        }
        return false;
    }

    public static boolean validatePublicKeyPartial(byte[] bArr, int i2) {
        return decodePointVar(bArr, i2, false, new PointAffine());
    }

    public static boolean verify(byte[] bArr, int i2, byte[] bArr2, int i3, byte[] bArr3, int i4, int i5) {
        return implVerify(bArr, i2, bArr2, i3, null, (byte) 0, bArr3, i4, i5);
    }

    public static boolean verify(byte[] bArr, int i2, byte[] bArr2, int i3, byte[] bArr3, byte[] bArr4, int i4, int i5) {
        return implVerify(bArr, i2, bArr2, i3, bArr3, (byte) 0, bArr4, i4, i5);
    }

    public static boolean verifyPrehash(byte[] bArr, int i2, byte[] bArr2, int i3, byte[] bArr3, Digest digest) {
        byte[] bArr4 = new byte[64];
        if (64 == digest.doFinal(bArr4, 0)) {
            return implVerify(bArr, i2, bArr2, i3, bArr3, (byte) 1, bArr4, 0, 64);
        }
        throw new IllegalArgumentException("ph");
    }

    public static boolean verifyPrehash(byte[] bArr, int i2, byte[] bArr2, int i3, byte[] bArr3, byte[] bArr4, int i4) {
        return implVerify(bArr, i2, bArr2, i3, bArr3, (byte) 1, bArr4, i4, 64);
    }
}
