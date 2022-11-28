package org.bouncycastle.math.ec.rfc8032;

import com.google.common.base.Ascii;
import java.security.SecureRandom;
import java.util.Objects;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
import org.bouncycastle.crypto.Xof;
import org.bouncycastle.crypto.digests.SHAKEDigest;
import org.bouncycastle.math.ec.rfc7748.X448;
import org.bouncycastle.math.ec.rfc7748.X448Field;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.tls.CipherSuite;
import org.bouncycastle.util.Arrays;
/* loaded from: classes4.dex */
public abstract class Ed448 {
    private static final int COORD_INTS = 14;
    private static final int C_d = -39081;
    private static final int L4_0 = 43969588;
    private static final int L4_1 = 30366549;
    private static final int L4_2 = 163752818;
    private static final int L4_3 = 258169998;
    private static final int L4_4 = 96434764;
    private static final int L4_5 = 227822194;
    private static final int L4_6 = 149865618;
    private static final int L4_7 = 550336261;
    private static final int L_0 = 78101261;
    private static final int L_1 = 141809365;
    private static final int L_2 = 175155932;
    private static final int L_3 = 64542499;
    private static final int L_4 = 158326419;
    private static final int L_5 = 191173276;
    private static final int L_6 = 104575268;
    private static final int L_7 = 137584065;
    private static final long M26L = 67108863;
    private static final long M28L = 268435455;
    private static final long M32L = 4294967295L;
    private static final int POINT_BYTES = 57;
    private static final int PRECOMP_BLOCKS = 5;
    private static final int PRECOMP_MASK = 15;
    private static final int PRECOMP_POINTS = 16;
    private static final int PRECOMP_SPACING = 18;
    private static final int PRECOMP_TEETH = 5;
    public static final int PREHASH_SIZE = 64;
    public static final int PUBLIC_KEY_SIZE = 57;
    private static final int SCALAR_BYTES = 57;
    private static final int SCALAR_INTS = 14;
    public static final int SECRET_KEY_SIZE = 57;
    public static final int SIGNATURE_SIZE = 114;
    private static final int WNAF_WIDTH_BASE = 7;
    private static final byte[] DOM4_PREFIX = {83, 105, 103, 69, 100, 52, 52, 56};
    private static final int[] P = {-1, -1, -1, -1, -1, -1, -1, -2, -1, -1, -1, -1, -1, -1};
    private static final int[] L = {-1420278541, 595116690, -1916432555, 560775794, -1361693040, -1001465015, 2093622249, -1, -1, -1, -1, -1, -1, LockFreeTaskQueueCore.MAX_CAPACITY_MASK};
    private static final int[] B_x = {118276190, 40534716, 9670182, 135141552, 85017403, 259173222, 68333082, 171784774, 174973732, 15824510, 73756743, 57518561, 94773951, 248652241, 107736333, 82941708};
    private static final int[] B_y = {36764180, 8885695, 130592152, 20104429, 163904957, 30304195, 121295871, 5901357, 125344798, 171541512, 175338348, 209069246, 3626697, 38307682, 24032956, 110359655};
    private static final Object precompLock = new Object();
    private static PointExt[] precompBaseTable = null;
    private static int[] precompBase = null;

    /* loaded from: classes4.dex */
    public static final class Algorithm {
        public static final int Ed448 = 0;
        public static final int Ed448ph = 1;
    }

    /* loaded from: classes4.dex */
    private static class F extends X448Field {
        private F() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class PointExt {

        /* renamed from: a  reason: collision with root package name */
        int[] f14346a;

        /* renamed from: b  reason: collision with root package name */
        int[] f14347b;

        /* renamed from: c  reason: collision with root package name */
        int[] f14348c;

        private PointExt() {
            this.f14346a = X448Field.create();
            this.f14347b = X448Field.create();
            this.f14348c = X448Field.create();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class PointPrecomp {

        /* renamed from: a  reason: collision with root package name */
        int[] f14349a;

        /* renamed from: b  reason: collision with root package name */
        int[] f14350b;

        private PointPrecomp() {
            this.f14349a = X448Field.create();
            this.f14350b = X448Field.create();
        }
    }

    private static byte[] calculateS(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        int[] iArr = new int[28];
        decodeScalar(bArr, 0, iArr);
        int[] iArr2 = new int[14];
        decodeScalar(bArr2, 0, iArr2);
        int[] iArr3 = new int[14];
        decodeScalar(bArr3, 0, iArr3);
        Nat.mulAddTo(14, iArr2, iArr3, iArr);
        byte[] bArr4 = new byte[114];
        for (int i2 = 0; i2 < 28; i2++) {
            encode32(iArr[i2], bArr4, i2 * 4);
        }
        return reduceScalar(bArr4);
    }

    private static boolean checkContextVar(byte[] bArr) {
        return bArr != null && bArr.length < 256;
    }

    private static int checkPoint(int[] iArr, int[] iArr2) {
        int[] create = X448Field.create();
        int[] create2 = X448Field.create();
        int[] create3 = X448Field.create();
        X448Field.sqr(iArr, create2);
        X448Field.sqr(iArr2, create3);
        X448Field.mul(create2, create3, create);
        X448Field.add(create2, create3, create2);
        X448Field.mul(create, 39081, create);
        X448Field.subOne(create);
        X448Field.add(create, create2, create);
        X448Field.normalize(create);
        return X448Field.isZero(create);
    }

    private static int checkPoint(int[] iArr, int[] iArr2, int[] iArr3) {
        int[] create = X448Field.create();
        int[] create2 = X448Field.create();
        int[] create3 = X448Field.create();
        int[] create4 = X448Field.create();
        X448Field.sqr(iArr, create2);
        X448Field.sqr(iArr2, create3);
        X448Field.sqr(iArr3, create4);
        X448Field.mul(create2, create3, create);
        X448Field.add(create2, create3, create2);
        X448Field.mul(create2, create4, create2);
        X448Field.sqr(create4, create4);
        X448Field.mul(create, 39081, create);
        X448Field.sub(create, create4, create);
        X448Field.add(create, create2, create);
        X448Field.normalize(create);
        return X448Field.isZero(create);
    }

    private static boolean checkPointVar(byte[] bArr) {
        if ((bArr[56] & Byte.MAX_VALUE) != 0) {
            return false;
        }
        int[] iArr = new int[14];
        decode32(bArr, 0, iArr, 0, 14);
        return !Nat.gte(14, iArr, P);
    }

    private static boolean checkScalarVar(byte[] bArr, int[] iArr) {
        if (bArr[56] != 0) {
            return false;
        }
        decodeScalar(bArr, 0, iArr);
        return !Nat.gte(14, iArr, L);
    }

    private static byte[] copy(byte[] bArr, int i2, int i3) {
        byte[] bArr2 = new byte[i3];
        System.arraycopy(bArr, i2, bArr2, 0, i3);
        return bArr2;
    }

    public static Xof createPrehash() {
        return createXof();
    }

    private static Xof createXof() {
        return new SHAKEDigest(256);
    }

    private static int decode16(byte[] bArr, int i2) {
        return ((bArr[i2 + 1] & 255) << 8) | (bArr[i2] & 255);
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

    private static boolean decodePointVar(byte[] bArr, int i2, boolean z, PointExt pointExt) {
        byte[] copy = copy(bArr, i2, 57);
        if (checkPointVar(copy)) {
            int i3 = (copy[56] & 128) >>> 7;
            copy[56] = (byte) (copy[56] & Byte.MAX_VALUE);
            X448Field.decode(copy, 0, pointExt.f14347b);
            int[] create = X448Field.create();
            int[] create2 = X448Field.create();
            X448Field.sqr(pointExt.f14347b, create);
            X448Field.mul(create, 39081, create2);
            X448Field.negate(create, create);
            X448Field.addOne(create);
            X448Field.addOne(create2);
            if (X448Field.sqrtRatioVar(create, create2, pointExt.f14346a)) {
                X448Field.normalize(pointExt.f14346a);
                if (i3 == 1 && X448Field.isZeroVar(pointExt.f14346a)) {
                    return false;
                }
                int[] iArr = pointExt.f14346a;
                if (z ^ (i3 != (iArr[0] & 1))) {
                    X448Field.negate(iArr, iArr);
                }
                pointExtendXY(pointExt);
                return true;
            }
            return false;
        }
        return false;
    }

    private static void decodeScalar(byte[] bArr, int i2, int[] iArr) {
        decode32(bArr, i2, iArr, 0, 14);
    }

    private static void dom4(Xof xof, byte b2, byte[] bArr) {
        byte[] bArr2 = DOM4_PREFIX;
        int length = bArr2.length;
        int i2 = length + 2;
        int length2 = bArr.length + i2;
        byte[] bArr3 = new byte[length2];
        System.arraycopy(bArr2, 0, bArr3, 0, length);
        bArr3[length] = b2;
        bArr3[length + 1] = (byte) bArr.length;
        System.arraycopy(bArr, 0, bArr3, i2, bArr.length);
        xof.update(bArr3, 0, length2);
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

    private static int encodePoint(PointExt pointExt, byte[] bArr, int i2) {
        int[] create = X448Field.create();
        int[] create2 = X448Field.create();
        X448Field.inv(pointExt.f14348c, create2);
        X448Field.mul(pointExt.f14346a, create2, create);
        X448Field.mul(pointExt.f14347b, create2, create2);
        X448Field.normalize(create);
        X448Field.normalize(create2);
        int checkPoint = checkPoint(create, create2);
        X448Field.encode(create2, bArr, i2);
        bArr[(i2 + 57) - 1] = (byte) ((create[0] & 1) << 7);
        return checkPoint;
    }

    public static void generatePrivateKey(SecureRandom secureRandom, byte[] bArr) {
        secureRandom.nextBytes(bArr);
    }

    public static void generatePublicKey(byte[] bArr, int i2, byte[] bArr2, int i3) {
        Xof createXof = createXof();
        byte[] bArr3 = new byte[114];
        createXof.update(bArr, i2, 57);
        createXof.doFinal(bArr3, 0, 114);
        byte[] bArr4 = new byte[57];
        pruneScalar(bArr3, 0, bArr4);
        scalarMultBaseEncoded(bArr4, bArr2, i3);
    }

    private static int getWindow4(int[] iArr, int i2) {
        return (iArr[i2 >>> 3] >>> ((i2 & 7) << 2)) & 15;
    }

    private static byte[] getWnafVar(int[] iArr, int i2) {
        int[] iArr2 = new int[28];
        int i3 = 0;
        int i4 = 14;
        int i5 = 28;
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
        byte[] bArr = new byte[447];
        int i9 = 32 - i2;
        int i10 = 0;
        int i11 = 0;
        while (i3 < 28) {
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

    private static void implSign(Xof xof, byte[] bArr, byte[] bArr2, byte[] bArr3, int i2, byte[] bArr4, byte b2, byte[] bArr5, int i3, int i4, byte[] bArr6, int i5) {
        dom4(xof, b2, bArr4);
        xof.update(bArr, 57, 57);
        xof.update(bArr5, i3, i4);
        xof.doFinal(bArr, 0, bArr.length);
        byte[] reduceScalar = reduceScalar(bArr);
        byte[] bArr7 = new byte[57];
        scalarMultBaseEncoded(reduceScalar, bArr7, 0);
        dom4(xof, b2, bArr4);
        xof.update(bArr7, 0, 57);
        xof.update(bArr3, i2, 57);
        xof.update(bArr5, i3, i4);
        xof.doFinal(bArr, 0, bArr.length);
        byte[] calculateS = calculateS(reduceScalar, reduceScalar(bArr), bArr2);
        System.arraycopy(bArr7, 0, bArr6, i5, 57);
        System.arraycopy(calculateS, 0, bArr6, i5 + 57, 57);
    }

    private static void implSign(byte[] bArr, int i2, byte[] bArr2, byte b2, byte[] bArr3, int i3, int i4, byte[] bArr4, int i5) {
        if (!checkContextVar(bArr2)) {
            throw new IllegalArgumentException("ctx");
        }
        Xof createXof = createXof();
        byte[] bArr5 = new byte[114];
        createXof.update(bArr, i2, 57);
        createXof.doFinal(bArr5, 0, 114);
        byte[] bArr6 = new byte[57];
        pruneScalar(bArr5, 0, bArr6);
        byte[] bArr7 = new byte[57];
        scalarMultBaseEncoded(bArr6, bArr7, 0);
        implSign(createXof, bArr5, bArr6, bArr7, 0, bArr2, b2, bArr3, i3, i4, bArr4, i5);
    }

    private static void implSign(byte[] bArr, int i2, byte[] bArr2, int i3, byte[] bArr3, byte b2, byte[] bArr4, int i4, int i5, byte[] bArr5, int i6) {
        if (!checkContextVar(bArr3)) {
            throw new IllegalArgumentException("ctx");
        }
        Xof createXof = createXof();
        byte[] bArr6 = new byte[114];
        createXof.update(bArr, i2, 57);
        createXof.doFinal(bArr6, 0, 114);
        byte[] bArr7 = new byte[57];
        pruneScalar(bArr6, 0, bArr7);
        implSign(createXof, bArr6, bArr7, bArr2, i3, bArr3, b2, bArr4, i4, i5, bArr5, i6);
    }

    private static boolean implVerify(byte[] bArr, int i2, byte[] bArr2, int i3, byte[] bArr3, byte b2, byte[] bArr4, int i4, int i5) {
        if (checkContextVar(bArr3)) {
            byte[] copy = copy(bArr, i2, 57);
            byte[] copy2 = copy(bArr, i2 + 57, 57);
            if (checkPointVar(copy)) {
                int[] iArr = new int[14];
                if (checkScalarVar(copy2, iArr)) {
                    PointExt pointExt = new PointExt();
                    if (decodePointVar(bArr2, i3, true, pointExt)) {
                        Xof createXof = createXof();
                        byte[] bArr5 = new byte[114];
                        dom4(createXof, b2, bArr3);
                        createXof.update(copy, 0, 57);
                        createXof.update(bArr2, i3, 57);
                        createXof.update(bArr4, i4, i5);
                        createXof.doFinal(bArr5, 0, 114);
                        int[] iArr2 = new int[14];
                        decodeScalar(reduceScalar(bArr5), 0, iArr2);
                        PointExt pointExt2 = new PointExt();
                        scalarMultStrausVar(iArr, iArr2, pointExt, pointExt2);
                        byte[] bArr6 = new byte[57];
                        return encodePoint(pointExt2, bArr6, 0) != 0 && Arrays.areEqual(bArr6, copy);
                    }
                    return false;
                }
                return false;
            }
            return false;
        }
        throw new IllegalArgumentException("ctx");
    }

    private static boolean isNeutralElementVar(int[] iArr, int[] iArr2, int[] iArr3) {
        return X448Field.isZeroVar(iArr) && X448Field.areEqualVar(iArr2, iArr3);
    }

    private static void pointAdd(PointExt pointExt, PointExt pointExt2) {
        int[] create = X448Field.create();
        int[] create2 = X448Field.create();
        int[] create3 = X448Field.create();
        int[] create4 = X448Field.create();
        int[] create5 = X448Field.create();
        int[] create6 = X448Field.create();
        int[] create7 = X448Field.create();
        int[] create8 = X448Field.create();
        X448Field.mul(pointExt.f14348c, pointExt2.f14348c, create);
        X448Field.sqr(create, create2);
        X448Field.mul(pointExt.f14346a, pointExt2.f14346a, create3);
        X448Field.mul(pointExt.f14347b, pointExt2.f14347b, create4);
        X448Field.mul(create3, create4, create5);
        X448Field.mul(create5, 39081, create5);
        X448Field.add(create2, create5, create6);
        X448Field.sub(create2, create5, create7);
        X448Field.add(pointExt.f14346a, pointExt.f14347b, create2);
        X448Field.add(pointExt2.f14346a, pointExt2.f14347b, create5);
        X448Field.mul(create2, create5, create8);
        X448Field.add(create4, create3, create2);
        X448Field.sub(create4, create3, create5);
        X448Field.carry(create2);
        X448Field.sub(create8, create2, create8);
        X448Field.mul(create8, create, create8);
        X448Field.mul(create5, create, create5);
        X448Field.mul(create6, create8, pointExt2.f14346a);
        X448Field.mul(create5, create7, pointExt2.f14347b);
        X448Field.mul(create6, create7, pointExt2.f14348c);
    }

    private static void pointAddPrecomp(PointPrecomp pointPrecomp, PointExt pointExt) {
        int[] create = X448Field.create();
        int[] create2 = X448Field.create();
        int[] create3 = X448Field.create();
        int[] create4 = X448Field.create();
        int[] create5 = X448Field.create();
        int[] create6 = X448Field.create();
        int[] create7 = X448Field.create();
        X448Field.sqr(pointExt.f14348c, create);
        X448Field.mul(pointPrecomp.f14349a, pointExt.f14346a, create2);
        X448Field.mul(pointPrecomp.f14350b, pointExt.f14347b, create3);
        X448Field.mul(create2, create3, create4);
        X448Field.mul(create4, 39081, create4);
        X448Field.add(create, create4, create5);
        X448Field.sub(create, create4, create6);
        X448Field.add(pointPrecomp.f14349a, pointPrecomp.f14350b, create);
        X448Field.add(pointExt.f14346a, pointExt.f14347b, create4);
        X448Field.mul(create, create4, create7);
        X448Field.add(create3, create2, create);
        X448Field.sub(create3, create2, create4);
        X448Field.carry(create);
        X448Field.sub(create7, create, create7);
        X448Field.mul(create7, pointExt.f14348c, create7);
        X448Field.mul(create4, pointExt.f14348c, create4);
        X448Field.mul(create5, create7, pointExt.f14346a);
        X448Field.mul(create4, create6, pointExt.f14347b);
        X448Field.mul(create5, create6, pointExt.f14348c);
    }

    private static void pointAddVar(boolean z, PointExt pointExt, PointExt pointExt2) {
        int[] iArr;
        int[] iArr2;
        int[] iArr3;
        int[] iArr4;
        int[] create = X448Field.create();
        int[] create2 = X448Field.create();
        int[] create3 = X448Field.create();
        int[] create4 = X448Field.create();
        int[] create5 = X448Field.create();
        int[] create6 = X448Field.create();
        int[] create7 = X448Field.create();
        int[] create8 = X448Field.create();
        if (z) {
            X448Field.sub(pointExt.f14347b, pointExt.f14346a, create8);
            iArr2 = create2;
            iArr = create5;
            iArr4 = create6;
            iArr3 = create7;
        } else {
            X448Field.add(pointExt.f14347b, pointExt.f14346a, create8);
            iArr = create2;
            iArr2 = create5;
            iArr3 = create6;
            iArr4 = create7;
        }
        X448Field.mul(pointExt.f14348c, pointExt2.f14348c, create);
        X448Field.sqr(create, create2);
        X448Field.mul(pointExt.f14346a, pointExt2.f14346a, create3);
        X448Field.mul(pointExt.f14347b, pointExt2.f14347b, create4);
        X448Field.mul(create3, create4, create5);
        X448Field.mul(create5, 39081, create5);
        X448Field.add(create2, create5, iArr3);
        X448Field.sub(create2, create5, iArr4);
        X448Field.add(pointExt2.f14346a, pointExt2.f14347b, create5);
        X448Field.mul(create8, create5, create8);
        X448Field.add(create4, create3, iArr);
        X448Field.sub(create4, create3, iArr2);
        X448Field.carry(iArr);
        X448Field.sub(create8, create2, create8);
        X448Field.mul(create8, create, create8);
        X448Field.mul(create5, create, create5);
        X448Field.mul(create6, create8, pointExt2.f14346a);
        X448Field.mul(create5, create7, pointExt2.f14347b);
        X448Field.mul(create6, create7, pointExt2.f14348c);
    }

    private static PointExt pointCopy(PointExt pointExt) {
        PointExt pointExt2 = new PointExt();
        pointCopy(pointExt, pointExt2);
        return pointExt2;
    }

    private static void pointCopy(PointExt pointExt, PointExt pointExt2) {
        X448Field.copy(pointExt.f14346a, 0, pointExt2.f14346a, 0);
        X448Field.copy(pointExt.f14347b, 0, pointExt2.f14347b, 0);
        X448Field.copy(pointExt.f14348c, 0, pointExt2.f14348c, 0);
    }

    private static void pointDouble(PointExt pointExt) {
        int[] create = X448Field.create();
        int[] create2 = X448Field.create();
        int[] create3 = X448Field.create();
        int[] create4 = X448Field.create();
        int[] create5 = X448Field.create();
        int[] create6 = X448Field.create();
        X448Field.add(pointExt.f14346a, pointExt.f14347b, create);
        X448Field.sqr(create, create);
        X448Field.sqr(pointExt.f14346a, create2);
        X448Field.sqr(pointExt.f14347b, create3);
        X448Field.add(create2, create3, create4);
        X448Field.carry(create4);
        X448Field.sqr(pointExt.f14348c, create5);
        X448Field.add(create5, create5, create5);
        X448Field.carry(create5);
        X448Field.sub(create4, create5, create6);
        X448Field.sub(create, create4, create);
        X448Field.sub(create2, create3, create2);
        X448Field.mul(create, create6, pointExt.f14346a);
        X448Field.mul(create4, create2, pointExt.f14347b);
        X448Field.mul(create4, create6, pointExt.f14348c);
    }

    private static void pointExtendXY(PointExt pointExt) {
        X448Field.one(pointExt.f14348c);
    }

    private static void pointLookup(int i2, int i3, PointPrecomp pointPrecomp) {
        int i4 = i2 * 16 * 2 * 16;
        for (int i5 = 0; i5 < 16; i5++) {
            int i6 = ((i5 ^ i3) - 1) >> 31;
            X448Field.cmov(i6, precompBase, i4, pointPrecomp.f14349a, 0);
            int i7 = i4 + 16;
            X448Field.cmov(i6, precompBase, i7, pointPrecomp.f14350b, 0);
            i4 = i7 + 16;
        }
    }

    private static void pointLookup(int[] iArr, int i2, int[] iArr2, PointExt pointExt) {
        int window4 = getWindow4(iArr, i2);
        int i3 = (window4 >>> 3) ^ 1;
        int i4 = (window4 ^ (-i3)) & 7;
        int i5 = 0;
        for (int i6 = 0; i6 < 8; i6++) {
            int i7 = ((i6 ^ i4) - 1) >> 31;
            X448Field.cmov(i7, iArr2, i5, pointExt.f14346a, 0);
            int i8 = i5 + 16;
            X448Field.cmov(i7, iArr2, i8, pointExt.f14347b, 0);
            int i9 = i8 + 16;
            X448Field.cmov(i7, iArr2, i9, pointExt.f14348c, 0);
            i5 = i9 + 16;
        }
        X448Field.cnegate(i3, pointExt.f14346a);
    }

    private static int[] pointPrecompute(PointExt pointExt, int i2) {
        PointExt pointCopy = pointCopy(pointExt);
        PointExt pointCopy2 = pointCopy(pointCopy);
        pointDouble(pointCopy2);
        int[] createTable = X448Field.createTable(i2 * 3);
        int i3 = 0;
        int i4 = 0;
        while (true) {
            X448Field.copy(pointCopy.f14346a, 0, createTable, i3);
            int i5 = i3 + 16;
            X448Field.copy(pointCopy.f14347b, 0, createTable, i5);
            int i6 = i5 + 16;
            X448Field.copy(pointCopy.f14348c, 0, createTable, i6);
            i3 = i6 + 16;
            i4++;
            if (i4 == i2) {
                return createTable;
            }
            pointAdd(pointCopy2, pointCopy);
        }
    }

    private static PointExt[] pointPrecomputeVar(PointExt pointExt, int i2) {
        PointExt pointCopy = pointCopy(pointExt);
        pointDouble(pointCopy);
        PointExt[] pointExtArr = new PointExt[i2];
        pointExtArr[0] = pointCopy(pointExt);
        for (int i3 = 1; i3 < i2; i3++) {
            pointExtArr[i3] = pointCopy(pointExtArr[i3 - 1]);
            pointAddVar(false, pointCopy, pointExtArr[i3]);
        }
        return pointExtArr;
    }

    private static void pointSetNeutral(PointExt pointExt) {
        X448Field.zero(pointExt.f14346a);
        X448Field.one(pointExt.f14347b);
        X448Field.one(pointExt.f14348c);
    }

    public static void precompute() {
        synchronized (precompLock) {
            if (precompBase != null) {
                return;
            }
            PointExt pointExt = new PointExt();
            X448Field.copy(B_x, 0, pointExt.f14346a, 0);
            X448Field.copy(B_y, 0, pointExt.f14347b, 0);
            pointExtendXY(pointExt);
            precompBaseTable = pointPrecomputeVar(pointExt, 32);
            precompBase = X448Field.createTable(CipherSuite.TLS_DH_RSA_WITH_AES_128_GCM_SHA256);
            int i2 = 0;
            for (int i3 = 0; i3 < 5; i3++) {
                PointExt[] pointExtArr = new PointExt[5];
                PointExt pointExt2 = new PointExt();
                pointSetNeutral(pointExt2);
                int i4 = 0;
                while (true) {
                    if (i4 >= 5) {
                        break;
                    }
                    pointAddVar(true, pointExt, pointExt2);
                    pointDouble(pointExt);
                    pointExtArr[i4] = pointCopy(pointExt);
                    if (i3 + i4 != 8) {
                        for (int i5 = 1; i5 < 18; i5++) {
                            pointDouble(pointExt);
                        }
                    }
                    i4++;
                }
                PointExt[] pointExtArr2 = new PointExt[16];
                pointExtArr2[0] = pointExt2;
                int i6 = 1;
                for (int i7 = 0; i7 < 4; i7++) {
                    int i8 = 1 << i7;
                    int i9 = 0;
                    while (i9 < i8) {
                        pointExtArr2[i6] = pointCopy(pointExtArr2[i6 - i8]);
                        pointAddVar(false, pointExtArr[i7], pointExtArr2[i6]);
                        i9++;
                        i6++;
                    }
                }
                int[] createTable = X448Field.createTable(16);
                int[] create = X448Field.create();
                X448Field.copy(pointExtArr2[0].f14348c, 0, create, 0);
                X448Field.copy(create, 0, createTable, 0);
                int i10 = 0;
                while (true) {
                    i10++;
                    if (i10 >= 16) {
                        break;
                    }
                    X448Field.mul(create, pointExtArr2[i10].f14348c, create);
                    X448Field.copy(create, 0, createTable, i10 * 16);
                }
                X448Field.invVar(create, create);
                int i11 = i10 - 1;
                int[] create2 = X448Field.create();
                while (i11 > 0) {
                    int i12 = i11 - 1;
                    X448Field.copy(createTable, i12 * 16, create2, 0);
                    X448Field.mul(create2, create, create2);
                    X448Field.copy(create2, 0, createTable, i11 * 16);
                    X448Field.mul(create, pointExtArr2[i11].f14348c, create);
                    i11 = i12;
                }
                X448Field.copy(create, 0, createTable, 0);
                for (int i13 = 0; i13 < 16; i13++) {
                    PointExt pointExt3 = pointExtArr2[i13];
                    X448Field.copy(createTable, i13 * 16, pointExt3.f14348c, 0);
                    int[] iArr = pointExt3.f14346a;
                    X448Field.mul(iArr, pointExt3.f14348c, iArr);
                    int[] iArr2 = pointExt3.f14347b;
                    X448Field.mul(iArr2, pointExt3.f14348c, iArr2);
                    X448Field.copy(pointExt3.f14346a, 0, precompBase, i2);
                    int i14 = i2 + 16;
                    X448Field.copy(pointExt3.f14347b, 0, precompBase, i14);
                    i2 = i14 + 16;
                }
            }
        }
    }

    private static void pruneScalar(byte[] bArr, int i2, byte[] bArr2) {
        System.arraycopy(bArr, i2, bArr2, 0, 56);
        bArr2[0] = (byte) (bArr2[0] & 252);
        bArr2[55] = (byte) (bArr2[55] | 128);
        bArr2[56] = 0;
    }

    private static byte[] reduceScalar(byte[] bArr) {
        long decode24 = (decode24(bArr, 4) << 4) & 4294967295L;
        long decode32 = decode32(bArr, 7) & 4294967295L;
        long decode242 = (decode24(bArr, 11) << 4) & 4294967295L;
        long decode322 = decode32(bArr, 14) & 4294967295L;
        long decode243 = (decode24(bArr, 18) << 4) & 4294967295L;
        long decode323 = decode32(bArr, 21) & 4294967295L;
        long decode324 = decode32(bArr, 28) & 4294967295L;
        long decode244 = (decode24(bArr, 32) << 4) & 4294967295L;
        long decode325 = decode32(bArr, 35) & 4294967295L;
        long decode245 = (decode24(bArr, 39) << 4) & 4294967295L;
        long decode326 = decode32(bArr, 42) & 4294967295L;
        long decode246 = (decode24(bArr, 46) << 4) & 4294967295L;
        long decode327 = decode32(bArr, 49) & 4294967295L;
        long decode247 = (decode24(bArr, 53) << 4) & 4294967295L;
        long decode248 = (decode24(bArr, 74) << 4) & 4294967295L;
        long decode328 = decode32(bArr, 77) & 4294967295L;
        long decode249 = (decode24(bArr, 81) << 4) & 4294967295L;
        long decode329 = decode32(bArr, 84) & 4294967295L;
        long decode2410 = (decode24(bArr, 88) << 4) & 4294967295L;
        long decode3210 = decode32(bArr, 91) & 4294967295L;
        long decode2411 = (decode24(bArr, 95) << 4) & 4294967295L;
        long decode3211 = decode32(bArr, 98) & 4294967295L;
        long decode2412 = (decode24(bArr, 102) << 4) & 4294967295L;
        long decode3212 = decode32(bArr, 105) & 4294967295L;
        long decode2413 = (decode24(bArr, 109) << 4) & 4294967295L;
        long decode16 = decode16(bArr, 112) & 4294967295L;
        long j2 = decode2413 + (decode3212 >>> 28);
        long j3 = decode3212 & M28L;
        long decode3213 = (decode32(bArr, 56) & 4294967295L) + (decode16 * 43969588) + (j2 * 30366549);
        long decode2414 = ((decode24(bArr, 60) << 4) & 4294967295L) + (decode16 * 30366549) + (j2 * 163752818);
        long decode3214 = (decode32(bArr, 63) & 4294967295L) + (decode16 * 163752818) + (j2 * 258169998);
        long decode2415 = ((decode24(bArr, 67) << 4) & 4294967295L) + (decode16 * 258169998) + (j2 * 96434764);
        long j4 = decode328 + (decode16 * 149865618) + (j2 * 550336261);
        long j5 = decode327 + (j3 * 43969588);
        long j6 = decode2412 + (decode3211 >>> 28);
        long j7 = decode3211 & M28L;
        long decode3215 = (decode32(bArr, 70) & 4294967295L) + (decode16 * 96434764) + (j2 * 227822194) + (j3 * 149865618) + (j6 * 550336261);
        long j8 = decode326 + (j7 * 43969588);
        long j9 = decode2411 + (decode3210 >>> 28);
        long j10 = decode3210 & M28L;
        long j11 = decode3214 + (j3 * 96434764) + (j6 * 227822194) + (j7 * 149865618) + (j9 * 550336261);
        long j12 = decode325 + (j10 * 43969588);
        long j13 = decode2414 + (j3 * 258169998) + (j6 * 96434764) + (j7 * 227822194) + (j9 * 149865618) + (j10 * 550336261);
        long j14 = decode2410 + (decode329 >>> 28);
        long j15 = decode329 & M28L;
        long j16 = decode248 + (decode16 * 227822194) + (j2 * 149865618) + (j3 * 550336261) + (decode3215 >>> 28);
        long j17 = decode3215 & M28L;
        long j18 = j4 + (j16 >>> 28);
        long j19 = j16 & M28L;
        long j20 = decode249 + (decode16 * 550336261) + (j18 >>> 28);
        long j21 = j18 & M28L;
        long j22 = j15 + (j20 >>> 28);
        long j23 = j20 & M28L;
        long decode2416 = ((decode24(bArr, 25) << 4) & 4294967295L) + (j23 * 43969588);
        long j24 = decode324 + (j22 * 43969588) + (j23 * 30366549);
        long j25 = decode244 + (j14 * 43969588) + (j22 * 30366549) + (j23 * 163752818);
        long j26 = j12 + (j14 * 30366549) + (j22 * 163752818) + (j23 * 258169998);
        long j27 = decode245 + (j9 * 43969588) + (j10 * 30366549) + (j14 * 163752818) + (j22 * 258169998) + (j23 * 96434764);
        long j28 = j8 + (j9 * 30366549) + (j10 * 163752818) + (j14 * 258169998) + (j22 * 96434764) + (j23 * 227822194);
        long j29 = j5 + (j6 * 30366549) + (j7 * 163752818) + (j9 * 258169998) + (j10 * 96434764) + (j14 * 227822194) + (j22 * 149865618) + (j23 * 550336261);
        long j30 = decode323 + (j21 * 43969588);
        long j31 = j11 + (j13 >>> 28);
        long j32 = j13 & M28L;
        long j33 = decode2415 + (j3 * 227822194) + (j6 * 149865618) + (j7 * 550336261) + (j31 >>> 28);
        long j34 = j31 & M28L;
        long j35 = j17 + (j33 >>> 28);
        long j36 = j33 & M28L;
        long j37 = j19 + (j35 >>> 28);
        long j38 = j35 & M28L;
        long j39 = j27 + (j21 * 227822194) + (j37 * 149865618) + (j38 * 550336261);
        long j40 = decode322 + (j38 * 43969588) + (j36 * 30366549);
        long j41 = decode243 + (j37 * 43969588) + (j38 * 30366549) + (j36 * 163752818);
        long j42 = j30 + (j37 * 30366549) + (j38 * 163752818) + (j36 * 258169998);
        long j43 = decode2416 + (j21 * 30366549) + (j37 * 163752818) + (j38 * 258169998) + (j36 * 96434764);
        long j44 = j24 + (j21 * 163752818) + (j37 * 258169998) + (j38 * 96434764) + (j36 * 227822194);
        long j45 = j25 + (j21 * 258169998) + (j37 * 96434764) + (j38 * 227822194) + (j36 * 149865618);
        long j46 = j26 + (j21 * 96434764) + (j37 * 227822194) + (j38 * 149865618) + (j36 * 550336261);
        long j47 = decode247 + (j2 * 43969588) + (j3 * 30366549) + (j6 * 163752818) + (j7 * 258169998) + (j9 * 96434764) + (j10 * 227822194) + (j14 * 149865618) + (j22 * 550336261) + (j29 >>> 28);
        long j48 = j29 & M28L;
        long j49 = decode3213 + (j3 * 163752818) + (j6 * 258169998) + (j7 * 96434764) + (j9 * 227822194) + (j10 * 149865618) + (j14 * 550336261) + (j47 >>> 28);
        long j50 = j47 & M28L;
        long j51 = j32 + (j49 >>> 28);
        long j52 = j49 & M28L;
        long j53 = j34 + (j51 >>> 28);
        long j54 = j51 & M28L;
        long j55 = decode32 + (j53 * 43969588);
        long j56 = decode242 + (j36 * 43969588) + (j53 * 30366549);
        long j57 = j40 + (j53 * 163752818);
        long j58 = j41 + (j53 * 258169998);
        long j59 = j42 + (j53 * 96434764);
        long j60 = j43 + (j53 * 227822194);
        long j61 = j45 + (j53 * 550336261);
        long j62 = j50 & M26L;
        long j63 = (j52 * 4) + (j50 >>> 26) + 1;
        long decode3216 = (decode32(bArr, 0) & 4294967295L) + (78101261 * j63);
        long j64 = j55 + (30366549 * j54) + (175155932 * j63);
        long j65 = j56 + (163752818 * j54) + (64542499 * j63);
        long j66 = j57 + (258169998 * j54) + (158326419 * j63);
        long j67 = j58 + (96434764 * j54) + (191173276 * j63);
        long j68 = j60 + (149865618 * j54) + (j63 * 137584065);
        long j69 = decode24 + (43969588 * j54) + (141809365 * j63) + (decode3216 >>> 28);
        long j70 = decode3216 & M28L;
        long j71 = j64 + (j69 >>> 28);
        long j72 = j69 & M28L;
        long j73 = j65 + (j71 >>> 28);
        long j74 = j71 & M28L;
        long j75 = j66 + (j73 >>> 28);
        long j76 = j73 & M28L;
        long j77 = j67 + (j75 >>> 28);
        long j78 = j75 & M28L;
        long j79 = j59 + (227822194 * j54) + (104575268 * j63) + (j77 >>> 28);
        long j80 = j77 & M28L;
        long j81 = j68 + (j79 >>> 28);
        long j82 = j79 & M28L;
        long j83 = j44 + (j53 * 149865618) + (j54 * 550336261) + (j81 >>> 28);
        long j84 = j81 & M28L;
        long j85 = j61 + (j83 >>> 28);
        long j86 = j83 & M28L;
        long j87 = j46 + (j85 >>> 28);
        long j88 = j85 & M28L;
        long j89 = j39 + (j87 >>> 28);
        long j90 = j87 & M28L;
        long j91 = j28 + (j21 * 149865618) + (j37 * 550336261) + (j89 >>> 28);
        long j92 = j89 & M28L;
        long j93 = decode246 + (j6 * 43969588) + (j7 * 30366549) + (j9 * 163752818) + (j10 * 258169998) + (j14 * 96434764) + (j22 * 227822194) + (j23 * 149865618) + (j21 * 550336261) + (j91 >>> 28);
        long j94 = j91 & M28L;
        long j95 = j48 + (j93 >>> 28);
        long j96 = j93 & M28L;
        long j97 = j62 + (j95 >>> 28);
        long j98 = j95 & M28L;
        long j99 = j97 & M26L;
        long j100 = (j97 >>> 26) - 1;
        long j101 = j70 - (j100 & 78101261);
        long j102 = (j72 - (j100 & 141809365)) + (j101 >> 28);
        long j103 = j101 & M28L;
        long j104 = (j74 - (j100 & 175155932)) + (j102 >> 28);
        long j105 = j102 & M28L;
        long j106 = (j76 - (j100 & 64542499)) + (j104 >> 28);
        long j107 = j104 & M28L;
        long j108 = (j78 - (j100 & 158326419)) + (j106 >> 28);
        long j109 = j106 & M28L;
        long j110 = (j80 - (j100 & 191173276)) + (j108 >> 28);
        long j111 = j108 & M28L;
        long j112 = (j82 - (j100 & 104575268)) + (j110 >> 28);
        long j113 = j110 & M28L;
        long j114 = (j84 - (j100 & 137584065)) + (j112 >> 28);
        long j115 = j112 & M28L;
        long j116 = j86 + (j114 >> 28);
        long j117 = j114 & M28L;
        long j118 = j88 + (j116 >> 28);
        long j119 = j116 & M28L;
        long j120 = j90 + (j118 >> 28);
        long j121 = j118 & M28L;
        long j122 = j92 + (j120 >> 28);
        long j123 = j120 & M28L;
        long j124 = j94 + (j122 >> 28);
        long j125 = j122 & M28L;
        long j126 = j96 + (j124 >> 28);
        long j127 = j124 & M28L;
        long j128 = j98 + (j126 >> 28);
        long j129 = j126 & M28L;
        long j130 = j128 & M28L;
        byte[] bArr2 = new byte[57];
        encode56((j105 << 28) | j103, bArr2, 0);
        encode56((j109 << 28) | j107, bArr2, 7);
        encode56(j111 | (j113 << 28), bArr2, 14);
        encode56(j115 | (j117 << 28), bArr2, 21);
        encode56(j119 | (j121 << 28), bArr2, 28);
        encode56(j123 | (j125 << 28), bArr2, 35);
        encode56(j127 | (j129 << 28), bArr2, 42);
        encode56(((j99 + (j128 >> 28)) << 28) | j130, bArr2, 49);
        return bArr2;
    }

    private static void scalarMult(byte[] bArr, PointExt pointExt, PointExt pointExt2) {
        int[] iArr = new int[14];
        decodeScalar(bArr, 0, iArr);
        Nat.shiftDownBits(14, iArr, 2, 0);
        Nat.cadd(14, (~iArr[0]) & 1, iArr, L, iArr);
        Nat.shiftDownBit(14, iArr, 1);
        int[] pointPrecompute = pointPrecompute(pointExt, 8);
        PointExt pointExt3 = new PointExt();
        pointLookup(iArr, 111, pointPrecompute, pointExt2);
        for (int i2 = 110; i2 >= 0; i2--) {
            for (int i3 = 0; i3 < 4; i3++) {
                pointDouble(pointExt2);
            }
            pointLookup(iArr, i2, pointPrecompute, pointExt3);
            pointAdd(pointExt3, pointExt2);
        }
        for (int i4 = 0; i4 < 2; i4++) {
            pointDouble(pointExt2);
        }
    }

    private static void scalarMultBase(byte[] bArr, PointExt pointExt) {
        precompute();
        int[] iArr = new int[15];
        decodeScalar(bArr, 0, iArr);
        iArr[14] = Nat.cadd(14, (~iArr[0]) & 1, iArr, L, iArr) + 4;
        Nat.shiftDownBit(15, iArr, 0);
        PointPrecomp pointPrecomp = new PointPrecomp();
        pointSetNeutral(pointExt);
        int i2 = 17;
        while (true) {
            int i3 = i2;
            for (int i4 = 0; i4 < 5; i4++) {
                int i5 = 0;
                for (int i6 = 0; i6 < 5; i6++) {
                    i5 = (i5 & (~(1 << i6))) ^ ((iArr[i3 >>> 5] >>> (i3 & 31)) << i6);
                    i3 += 18;
                }
                int i7 = (i5 >>> 4) & 1;
                pointLookup(i4, ((-i7) ^ i5) & 15, pointPrecomp);
                X448Field.cnegate(i7, pointPrecomp.f14349a);
                pointAddPrecomp(pointPrecomp, pointExt);
            }
            i2--;
            if (i2 < 0) {
                return;
            }
            pointDouble(pointExt);
        }
    }

    private static void scalarMultBaseEncoded(byte[] bArr, byte[] bArr2, int i2) {
        PointExt pointExt = new PointExt();
        scalarMultBase(bArr, pointExt);
        if (encodePoint(pointExt, bArr2, i2) == 0) {
            throw new IllegalStateException();
        }
    }

    public static void scalarMultBaseXY(X448.Friend friend, byte[] bArr, int i2, int[] iArr, int[] iArr2) {
        Objects.requireNonNull(friend, "This method is only for use by X448");
        byte[] bArr2 = new byte[57];
        pruneScalar(bArr, i2, bArr2);
        PointExt pointExt = new PointExt();
        scalarMultBase(bArr2, pointExt);
        if (checkPoint(pointExt.f14346a, pointExt.f14347b, pointExt.f14348c) == 0) {
            throw new IllegalStateException();
        }
        X448Field.copy(pointExt.f14346a, 0, iArr, 0);
        X448Field.copy(pointExt.f14347b, 0, iArr2, 0);
    }

    private static void scalarMultOrderVar(PointExt pointExt, PointExt pointExt2) {
        byte[] wnafVar = getWnafVar(L, 5);
        PointExt[] pointPrecomputeVar = pointPrecomputeVar(pointExt, 8);
        pointSetNeutral(pointExt2);
        int i2 = 446;
        while (true) {
            byte b2 = wnafVar[i2];
            if (b2 != 0) {
                int i3 = b2 >> Ascii.US;
                pointAddVar(i3 != 0, pointPrecomputeVar[(b2 ^ i3) >>> 1], pointExt2);
            }
            i2--;
            if (i2 < 0) {
                return;
            }
            pointDouble(pointExt2);
        }
    }

    private static void scalarMultStrausVar(int[] iArr, int[] iArr2, PointExt pointExt, PointExt pointExt2) {
        precompute();
        byte[] wnafVar = getWnafVar(iArr, 7);
        byte[] wnafVar2 = getWnafVar(iArr2, 5);
        PointExt[] pointPrecomputeVar = pointPrecomputeVar(pointExt, 8);
        pointSetNeutral(pointExt2);
        int i2 = 446;
        while (true) {
            byte b2 = wnafVar[i2];
            if (b2 != 0) {
                int i3 = b2 >> Ascii.US;
                pointAddVar(i3 != 0, precompBaseTable[(b2 ^ i3) >>> 1], pointExt2);
            }
            byte b3 = wnafVar2[i2];
            if (b3 != 0) {
                int i4 = b3 >> Ascii.US;
                pointAddVar(i4 != 0, pointPrecomputeVar[(b3 ^ i4) >>> 1], pointExt2);
            }
            i2--;
            if (i2 < 0) {
                return;
            }
            pointDouble(pointExt2);
        }
    }

    public static void sign(byte[] bArr, int i2, byte[] bArr2, int i3, byte[] bArr3, byte[] bArr4, int i4, int i5, byte[] bArr5, int i6) {
        implSign(bArr, i2, bArr2, i3, bArr3, (byte) 0, bArr4, i4, i5, bArr5, i6);
    }

    public static void sign(byte[] bArr, int i2, byte[] bArr2, byte[] bArr3, int i3, int i4, byte[] bArr4, int i5) {
        implSign(bArr, i2, bArr2, (byte) 0, bArr3, i3, i4, bArr4, i5);
    }

    public static void signPrehash(byte[] bArr, int i2, byte[] bArr2, int i3, byte[] bArr3, Xof xof, byte[] bArr4, int i4) {
        byte[] bArr5 = new byte[64];
        if (64 != xof.doFinal(bArr5, 0, 64)) {
            throw new IllegalArgumentException("ph");
        }
        implSign(bArr, i2, bArr2, i3, bArr3, (byte) 1, bArr5, 0, 64, bArr4, i4);
    }

    public static void signPrehash(byte[] bArr, int i2, byte[] bArr2, int i3, byte[] bArr3, byte[] bArr4, int i4, byte[] bArr5, int i5) {
        implSign(bArr, i2, bArr2, i3, bArr3, (byte) 1, bArr4, i4, 64, bArr5, i5);
    }

    public static void signPrehash(byte[] bArr, int i2, byte[] bArr2, Xof xof, byte[] bArr3, int i3) {
        byte[] bArr4 = new byte[64];
        if (64 != xof.doFinal(bArr4, 0, 64)) {
            throw new IllegalArgumentException("ph");
        }
        implSign(bArr, i2, bArr2, (byte) 1, bArr4, 0, 64, bArr3, i3);
    }

    public static void signPrehash(byte[] bArr, int i2, byte[] bArr2, byte[] bArr3, int i3, byte[] bArr4, int i4) {
        implSign(bArr, i2, bArr2, (byte) 1, bArr3, i3, 64, bArr4, i4);
    }

    public static boolean validatePublicKeyFull(byte[] bArr, int i2) {
        PointExt pointExt = new PointExt();
        if (decodePointVar(bArr, i2, false, pointExt)) {
            X448Field.normalize(pointExt.f14346a);
            X448Field.normalize(pointExt.f14347b);
            X448Field.normalize(pointExt.f14348c);
            if (isNeutralElementVar(pointExt.f14346a, pointExt.f14347b, pointExt.f14348c)) {
                return false;
            }
            PointExt pointExt2 = new PointExt();
            scalarMultOrderVar(pointExt, pointExt2);
            X448Field.normalize(pointExt2.f14346a);
            X448Field.normalize(pointExt2.f14347b);
            X448Field.normalize(pointExt2.f14348c);
            return isNeutralElementVar(pointExt2.f14346a, pointExt2.f14347b, pointExt2.f14348c);
        }
        return false;
    }

    public static boolean validatePublicKeyPartial(byte[] bArr, int i2) {
        return decodePointVar(bArr, i2, false, new PointExt());
    }

    public static boolean verify(byte[] bArr, int i2, byte[] bArr2, int i3, byte[] bArr3, byte[] bArr4, int i4, int i5) {
        return implVerify(bArr, i2, bArr2, i3, bArr3, (byte) 0, bArr4, i4, i5);
    }

    public static boolean verifyPrehash(byte[] bArr, int i2, byte[] bArr2, int i3, byte[] bArr3, Xof xof) {
        byte[] bArr4 = new byte[64];
        if (64 == xof.doFinal(bArr4, 0, 64)) {
            return implVerify(bArr, i2, bArr2, i3, bArr3, (byte) 1, bArr4, 0, 64);
        }
        throw new IllegalArgumentException("ph");
    }

    public static boolean verifyPrehash(byte[] bArr, int i2, byte[] bArr2, int i3, byte[] bArr3, byte[] bArr4, int i4) {
        return implVerify(bArr, i2, bArr2, i3, bArr3, (byte) 1, bArr4, i4, 64);
    }
}
