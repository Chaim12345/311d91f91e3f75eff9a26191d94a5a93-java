package com.google.crypto.tink.subtle;

import android.support.v4.media.session.PlaybackStateCompat;
import com.google.common.base.Ascii;
import com.google.common.primitives.SignedBytes;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.Arrays;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class Ed25519 {
    public static final int PUBLIC_KEY_LEN = 32;
    public static final int SECRET_KEY_LEN = 32;
    public static final int SIGNATURE_LEN = 64;
    private static final CachedXYT CACHED_NEUTRAL = new CachedXYT(new long[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new long[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new long[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
    private static final PartialXYZT NEUTRAL = new PartialXYZT(new XYZ(new long[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new long[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new long[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0}), new long[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0});

    /* renamed from: a  reason: collision with root package name */
    static final byte[] f9838a = {-19, -45, -11, 92, Ascii.SUB, 99, Ascii.DC2, 88, -42, -100, -9, -94, -34, -7, -34, Ascii.DC4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 16};

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class CachedXYT {

        /* renamed from: a  reason: collision with root package name */
        final long[] f9839a;

        /* renamed from: b  reason: collision with root package name */
        final long[] f9840b;

        /* renamed from: c  reason: collision with root package name */
        final long[] f9841c;

        CachedXYT(CachedXYT cachedXYT) {
            this.f9839a = Arrays.copyOf(cachedXYT.f9839a, 10);
            this.f9840b = Arrays.copyOf(cachedXYT.f9840b, 10);
            this.f9841c = Arrays.copyOf(cachedXYT.f9841c, 10);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public CachedXYT(long[] jArr, long[] jArr2, long[] jArr3) {
            this.f9839a = jArr;
            this.f9840b = jArr2;
            this.f9841c = jArr3;
        }

        void a(CachedXYT cachedXYT, int i2) {
            Curve25519.a(this.f9839a, cachedXYT.f9839a, i2);
            Curve25519.a(this.f9840b, cachedXYT.f9840b, i2);
            Curve25519.a(this.f9841c, cachedXYT.f9841c, i2);
        }

        void multByZ(long[] jArr, long[] jArr2) {
            System.arraycopy(jArr2, 0, jArr, 0, 10);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class CachedXYZT extends CachedXYT {
        private final long[] z;

        CachedXYZT() {
            this(new long[10], new long[10], new long[10], new long[10]);
        }

        CachedXYZT(XYZT xyzt) {
            this();
            long[] jArr = this.f9839a;
            XYZ xyz = xyzt.f9847a;
            Field25519.n(jArr, xyz.f9845b, xyz.f9844a);
            long[] jArr2 = this.f9840b;
            XYZ xyz2 = xyzt.f9847a;
            Field25519.l(jArr2, xyz2.f9845b, xyz2.f9844a);
            System.arraycopy(xyzt.f9847a.f9846c, 0, this.z, 0, 10);
            Field25519.d(this.f9841c, xyzt.f9848b, Ed25519Constants.f9850b);
        }

        CachedXYZT(long[] jArr, long[] jArr2, long[] jArr3, long[] jArr4) {
            super(jArr, jArr2, jArr4);
            this.z = jArr3;
        }

        @Override // com.google.crypto.tink.subtle.Ed25519.CachedXYT
        public void multByZ(long[] jArr, long[] jArr2) {
            Field25519.d(jArr, jArr2, this.z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class PartialXYZT {

        /* renamed from: a  reason: collision with root package name */
        final XYZ f9842a;

        /* renamed from: b  reason: collision with root package name */
        final long[] f9843b;

        PartialXYZT() {
            this(new XYZ(), new long[10]);
        }

        PartialXYZT(PartialXYZT partialXYZT) {
            this.f9842a = new XYZ(partialXYZT.f9842a);
            this.f9843b = Arrays.copyOf(partialXYZT.f9843b, 10);
        }

        PartialXYZT(XYZ xyz, long[] jArr) {
            this.f9842a = xyz;
            this.f9843b = jArr;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class XYZ {

        /* renamed from: a  reason: collision with root package name */
        final long[] f9844a;

        /* renamed from: b  reason: collision with root package name */
        final long[] f9845b;

        /* renamed from: c  reason: collision with root package name */
        final long[] f9846c;

        XYZ() {
            this(new long[10], new long[10], new long[10]);
        }

        XYZ(PartialXYZT partialXYZT) {
            this();
            a(this, partialXYZT);
        }

        XYZ(XYZ xyz) {
            this.f9844a = Arrays.copyOf(xyz.f9844a, 10);
            this.f9845b = Arrays.copyOf(xyz.f9845b, 10);
            this.f9846c = Arrays.copyOf(xyz.f9846c, 10);
        }

        XYZ(long[] jArr, long[] jArr2, long[] jArr3) {
            this.f9844a = jArr;
            this.f9845b = jArr2;
            this.f9846c = jArr3;
        }

        static XYZ a(XYZ xyz, PartialXYZT partialXYZT) {
            Field25519.d(xyz.f9844a, partialXYZT.f9842a.f9844a, partialXYZT.f9843b);
            long[] jArr = xyz.f9845b;
            XYZ xyz2 = partialXYZT.f9842a;
            Field25519.d(jArr, xyz2.f9845b, xyz2.f9846c);
            Field25519.d(xyz.f9846c, partialXYZT.f9842a.f9846c, partialXYZT.f9843b);
            return xyz;
        }

        boolean b() {
            long[] jArr = new long[10];
            Field25519.j(jArr, this.f9844a);
            long[] jArr2 = new long[10];
            Field25519.j(jArr2, this.f9845b);
            long[] jArr3 = new long[10];
            Field25519.j(jArr3, this.f9846c);
            long[] jArr4 = new long[10];
            Field25519.j(jArr4, jArr3);
            long[] jArr5 = new long[10];
            Field25519.l(jArr5, jArr2, jArr);
            Field25519.d(jArr5, jArr5, jArr3);
            long[] jArr6 = new long[10];
            Field25519.d(jArr6, jArr, jArr2);
            Field25519.d(jArr6, jArr6, Ed25519Constants.f9849a);
            Field25519.m(jArr6, jArr4);
            Field25519.f(jArr6, jArr6);
            return Bytes.equal(Field25519.a(jArr5), Field25519.a(jArr6));
        }

        byte[] c() {
            long[] jArr = new long[10];
            long[] jArr2 = new long[10];
            long[] jArr3 = new long[10];
            Field25519.c(jArr, this.f9846c);
            Field25519.d(jArr2, this.f9844a, jArr);
            Field25519.d(jArr3, this.f9845b, jArr);
            byte[] a2 = Field25519.a(jArr3);
            a2[31] = (byte) ((Ed25519.getLsb(jArr2) << 7) ^ a2[31]);
            return a2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class XYZT {

        /* renamed from: a  reason: collision with root package name */
        final XYZ f9847a;

        /* renamed from: b  reason: collision with root package name */
        final long[] f9848b;

        XYZT() {
            this(new XYZ(), new long[10]);
        }

        XYZT(PartialXYZT partialXYZT) {
            this();
            fromPartialXYZT(this, partialXYZT);
        }

        XYZT(XYZ xyz, long[] jArr) {
            this.f9847a = xyz;
            this.f9848b = jArr;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static XYZT fromBytesNegateVarTime(byte[] bArr) {
            long[] jArr = new long[10];
            long[] b2 = Field25519.b(bArr);
            long[] jArr2 = new long[10];
            jArr2[0] = 1;
            long[] jArr3 = new long[10];
            long[] jArr4 = new long[10];
            long[] jArr5 = new long[10];
            long[] jArr6 = new long[10];
            long[] jArr7 = new long[10];
            Field25519.j(jArr4, b2);
            Field25519.d(jArr5, jArr4, Ed25519Constants.f9849a);
            Field25519.l(jArr4, jArr4, jArr2);
            Field25519.n(jArr5, jArr5, jArr2);
            long[] jArr8 = new long[10];
            Field25519.j(jArr8, jArr5);
            Field25519.d(jArr8, jArr8, jArr5);
            Field25519.j(jArr, jArr8);
            Field25519.d(jArr, jArr, jArr5);
            Field25519.d(jArr, jArr, jArr4);
            Ed25519.pow2252m3(jArr, jArr);
            Field25519.d(jArr, jArr, jArr8);
            Field25519.d(jArr, jArr, jArr4);
            Field25519.j(jArr6, jArr);
            Field25519.d(jArr6, jArr6, jArr5);
            Field25519.l(jArr7, jArr6, jArr4);
            if (Ed25519.isNonZeroVarTime(jArr7)) {
                Field25519.n(jArr7, jArr6, jArr4);
                if (Ed25519.isNonZeroVarTime(jArr7)) {
                    throw new GeneralSecurityException("Cannot convert given bytes to extended projective coordinates. No square root exists for modulo 2^255-19");
                }
                Field25519.d(jArr, jArr, Ed25519Constants.f9851c);
            }
            if (Ed25519.isNonZeroVarTime(jArr) || ((bArr[31] & 255) >> 7) == 0) {
                if (Ed25519.getLsb(jArr) == ((bArr[31] & 255) >> 7)) {
                    Ed25519.neg(jArr, jArr);
                }
                Field25519.d(jArr3, jArr, b2);
                return new XYZT(new XYZ(jArr, b2, jArr2), jArr3);
            }
            throw new GeneralSecurityException("Cannot convert given bytes to extended projective coordinates. Computed x is zero and encoded x's least significant bit is not zero");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static XYZT fromPartialXYZT(XYZT xyzt, PartialXYZT partialXYZT) {
            Field25519.d(xyzt.f9847a.f9844a, partialXYZT.f9842a.f9844a, partialXYZT.f9843b);
            long[] jArr = xyzt.f9847a.f9845b;
            XYZ xyz = partialXYZT.f9842a;
            Field25519.d(jArr, xyz.f9845b, xyz.f9846c);
            Field25519.d(xyzt.f9847a.f9846c, partialXYZT.f9842a.f9846c, partialXYZT.f9843b);
            long[] jArr2 = xyzt.f9848b;
            XYZ xyz2 = partialXYZT.f9842a;
            Field25519.d(jArr2, xyz2.f9844a, xyz2.f9845b);
            return xyzt;
        }
    }

    private static void add(PartialXYZT partialXYZT, XYZT xyzt, CachedXYT cachedXYT) {
        long[] jArr = new long[10];
        long[] jArr2 = partialXYZT.f9842a.f9844a;
        XYZ xyz = xyzt.f9847a;
        Field25519.n(jArr2, xyz.f9845b, xyz.f9844a);
        long[] jArr3 = partialXYZT.f9842a.f9845b;
        XYZ xyz2 = xyzt.f9847a;
        Field25519.l(jArr3, xyz2.f9845b, xyz2.f9844a);
        long[] jArr4 = partialXYZT.f9842a.f9845b;
        Field25519.d(jArr4, jArr4, cachedXYT.f9840b);
        XYZ xyz3 = partialXYZT.f9842a;
        Field25519.d(xyz3.f9846c, xyz3.f9844a, cachedXYT.f9839a);
        Field25519.d(partialXYZT.f9843b, xyzt.f9848b, cachedXYT.f9841c);
        cachedXYT.multByZ(partialXYZT.f9842a.f9844a, xyzt.f9847a.f9846c);
        long[] jArr5 = partialXYZT.f9842a.f9844a;
        Field25519.n(jArr, jArr5, jArr5);
        XYZ xyz4 = partialXYZT.f9842a;
        Field25519.l(xyz4.f9844a, xyz4.f9846c, xyz4.f9845b);
        XYZ xyz5 = partialXYZT.f9842a;
        long[] jArr6 = xyz5.f9845b;
        Field25519.n(jArr6, xyz5.f9846c, jArr6);
        Field25519.n(partialXYZT.f9842a.f9846c, jArr, partialXYZT.f9843b);
        long[] jArr7 = partialXYZT.f9843b;
        Field25519.l(jArr7, jArr, jArr7);
    }

    private static XYZ doubleScalarMultVarTime(byte[] bArr, XYZT xyzt, byte[] bArr2) {
        CachedXYZT[] cachedXYZTArr = new CachedXYZT[8];
        cachedXYZTArr[0] = new CachedXYZT(xyzt);
        PartialXYZT partialXYZT = new PartialXYZT();
        doubleXYZT(partialXYZT, xyzt);
        XYZT xyzt2 = new XYZT(partialXYZT);
        for (int i2 = 1; i2 < 8; i2++) {
            add(partialXYZT, xyzt2, cachedXYZTArr[i2 - 1]);
            cachedXYZTArr[i2] = new CachedXYZT(new XYZT(partialXYZT));
        }
        byte[] slide = slide(bArr);
        byte[] slide2 = slide(bArr2);
        PartialXYZT partialXYZT2 = new PartialXYZT(NEUTRAL);
        XYZT xyzt3 = new XYZT();
        int i3 = 255;
        while (i3 >= 0 && slide[i3] == 0 && slide2[i3] == 0) {
            i3--;
        }
        while (i3 >= 0) {
            doubleXYZ(partialXYZT2, new XYZ(partialXYZT2));
            if (slide[i3] > 0) {
                add(partialXYZT2, XYZT.fromPartialXYZT(xyzt3, partialXYZT2), cachedXYZTArr[slide[i3] / 2]);
            } else if (slide[i3] < 0) {
                sub(partialXYZT2, XYZT.fromPartialXYZT(xyzt3, partialXYZT2), cachedXYZTArr[(-slide[i3]) / 2]);
            }
            if (slide2[i3] > 0) {
                add(partialXYZT2, XYZT.fromPartialXYZT(xyzt3, partialXYZT2), Ed25519Constants.f9853e[slide2[i3] / 2]);
            } else if (slide2[i3] < 0) {
                sub(partialXYZT2, XYZT.fromPartialXYZT(xyzt3, partialXYZT2), Ed25519Constants.f9853e[(-slide2[i3]) / 2]);
            }
            i3--;
        }
        return new XYZ(partialXYZT2);
    }

    private static void doubleXYZ(PartialXYZT partialXYZT, XYZ xyz) {
        long[] jArr = new long[10];
        Field25519.j(partialXYZT.f9842a.f9844a, xyz.f9844a);
        Field25519.j(partialXYZT.f9842a.f9846c, xyz.f9845b);
        Field25519.j(partialXYZT.f9843b, xyz.f9846c);
        long[] jArr2 = partialXYZT.f9843b;
        Field25519.n(jArr2, jArr2, jArr2);
        Field25519.n(partialXYZT.f9842a.f9845b, xyz.f9844a, xyz.f9845b);
        Field25519.j(jArr, partialXYZT.f9842a.f9845b);
        XYZ xyz2 = partialXYZT.f9842a;
        Field25519.n(xyz2.f9845b, xyz2.f9846c, xyz2.f9844a);
        XYZ xyz3 = partialXYZT.f9842a;
        long[] jArr3 = xyz3.f9846c;
        Field25519.l(jArr3, jArr3, xyz3.f9844a);
        XYZ xyz4 = partialXYZT.f9842a;
        Field25519.l(xyz4.f9844a, jArr, xyz4.f9845b);
        long[] jArr4 = partialXYZT.f9843b;
        Field25519.l(jArr4, jArr4, partialXYZT.f9842a.f9846c);
    }

    private static void doubleXYZT(PartialXYZT partialXYZT, XYZT xyzt) {
        doubleXYZ(partialXYZT, xyzt.f9847a);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] e(byte[] bArr) {
        MessageDigest engineFactory = EngineFactory.MESSAGE_DIGEST.getInstance("SHA-512");
        engineFactory.update(bArr, 0, 32);
        byte[] digest = engineFactory.digest();
        digest[0] = (byte) (digest[0] & 248);
        digest[31] = (byte) (digest[31] & Byte.MAX_VALUE);
        digest[31] = (byte) (digest[31] | SignedBytes.MAX_POWER_OF_TWO);
        return digest;
    }

    private static int eq(int i2, int i3) {
        int i4 = (~(i2 ^ i3)) & 255;
        int i5 = i4 & (i4 << 4);
        int i6 = i5 & (i5 << 2);
        return ((i6 & (i6 << 1)) >> 7) & 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] f(byte[] bArr) {
        return scalarMultWithBase(bArr).c();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] g(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        byte[] copyOfRange = Arrays.copyOfRange(bArr, 0, bArr.length);
        MessageDigest engineFactory = EngineFactory.MESSAGE_DIGEST.getInstance("SHA-512");
        engineFactory.update(bArr3, 32, 32);
        engineFactory.update(copyOfRange);
        byte[] digest = engineFactory.digest();
        reduce(digest);
        byte[] copyOfRange2 = Arrays.copyOfRange(scalarMultWithBase(digest).c(), 0, 32);
        engineFactory.reset();
        engineFactory.update(copyOfRange2);
        engineFactory.update(bArr2);
        engineFactory.update(copyOfRange);
        byte[] digest2 = engineFactory.digest();
        reduce(digest2);
        byte[] bArr4 = new byte[32];
        mulAdd(bArr4, digest2, bArr3, digest);
        return Bytes.concat(copyOfRange2, bArr4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getLsb(long[] jArr) {
        return Field25519.a(jArr)[0] & 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean h(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        if (bArr2.length != 64) {
            return false;
        }
        byte[] copyOfRange = Arrays.copyOfRange(bArr2, 32, 64);
        if (isSmallerThanGroupOrder(copyOfRange)) {
            MessageDigest engineFactory = EngineFactory.MESSAGE_DIGEST.getInstance("SHA-512");
            engineFactory.update(bArr2, 0, 32);
            engineFactory.update(bArr3);
            engineFactory.update(bArr);
            byte[] digest = engineFactory.digest();
            reduce(digest);
            byte[] c2 = doubleScalarMultVarTime(digest, XYZT.fromBytesNegateVarTime(bArr3), copyOfRange).c();
            for (int i2 = 0; i2 < 32; i2++) {
                if (c2[i2] != bArr2[i2]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isNonZeroVarTime(long[] jArr) {
        long[] jArr2 = new long[jArr.length + 1];
        System.arraycopy(jArr, 0, jArr2, 0, jArr.length);
        Field25519.g(jArr2);
        for (byte b2 : Field25519.a(jArr2)) {
            if (b2 != 0) {
                return true;
            }
        }
        return false;
    }

    private static boolean isSmallerThanGroupOrder(byte[] bArr) {
        for (int i2 = 31; i2 >= 0; i2--) {
            int i3 = bArr[i2] & 255;
            int i4 = f9838a[i2] & 255;
            if (i3 != i4) {
                return i3 < i4;
            }
        }
        return false;
    }

    private static long load3(byte[] bArr, int i2) {
        return ((bArr[i2 + 2] & 255) << 16) | (bArr[i2] & 255) | ((bArr[i2 + 1] & 255) << 8);
    }

    private static long load4(byte[] bArr, int i2) {
        return ((bArr[i2 + 3] & 255) << 24) | load3(bArr, i2);
    }

    /*  JADX ERROR: Type inference failed with exception
        jadx.core.utils.exceptions.JadxOverflowException: Type update terminated with stack overflow, arg: (r34v5 ?? I:long)
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:56)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:30)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:18)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:114)
        */
    private static void mulAdd(byte[] r82, byte[] r83, byte[] r84, byte[] r85) {
        /*
            Method dump skipped, instructions count: 2096
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.subtle.Ed25519.mulAdd(byte[], byte[], byte[], byte[]):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void neg(long[] jArr, long[] jArr2) {
        for (int i2 = 0; i2 < jArr2.length; i2++) {
            jArr[i2] = -jArr2[i2];
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void pow2252m3(long[] jArr, long[] jArr2) {
        long[] jArr3 = new long[10];
        long[] jArr4 = new long[10];
        long[] jArr5 = new long[10];
        Field25519.j(jArr3, jArr2);
        Field25519.j(jArr4, jArr3);
        for (int i2 = 1; i2 < 2; i2++) {
            Field25519.j(jArr4, jArr4);
        }
        Field25519.d(jArr4, jArr2, jArr4);
        Field25519.d(jArr3, jArr3, jArr4);
        Field25519.j(jArr3, jArr3);
        Field25519.d(jArr3, jArr4, jArr3);
        Field25519.j(jArr4, jArr3);
        for (int i3 = 1; i3 < 5; i3++) {
            Field25519.j(jArr4, jArr4);
        }
        Field25519.d(jArr3, jArr4, jArr3);
        Field25519.j(jArr4, jArr3);
        for (int i4 = 1; i4 < 10; i4++) {
            Field25519.j(jArr4, jArr4);
        }
        Field25519.d(jArr4, jArr4, jArr3);
        Field25519.j(jArr5, jArr4);
        for (int i5 = 1; i5 < 20; i5++) {
            Field25519.j(jArr5, jArr5);
        }
        Field25519.d(jArr4, jArr5, jArr4);
        Field25519.j(jArr4, jArr4);
        for (int i6 = 1; i6 < 10; i6++) {
            Field25519.j(jArr4, jArr4);
        }
        Field25519.d(jArr3, jArr4, jArr3);
        Field25519.j(jArr4, jArr3);
        for (int i7 = 1; i7 < 50; i7++) {
            Field25519.j(jArr4, jArr4);
        }
        Field25519.d(jArr4, jArr4, jArr3);
        Field25519.j(jArr5, jArr4);
        for (int i8 = 1; i8 < 100; i8++) {
            Field25519.j(jArr5, jArr5);
        }
        Field25519.d(jArr4, jArr5, jArr4);
        Field25519.j(jArr4, jArr4);
        for (int i9 = 1; i9 < 50; i9++) {
            Field25519.j(jArr4, jArr4);
        }
        Field25519.d(jArr3, jArr4, jArr3);
        Field25519.j(jArr3, jArr3);
        for (int i10 = 1; i10 < 2; i10++) {
            Field25519.j(jArr3, jArr3);
        }
        Field25519.d(jArr, jArr3, jArr2);
    }

    private static void reduce(byte[] bArr) {
        long load3 = (load3(bArr, 47) >> 2) & 2097151;
        long load4 = (load4(bArr, 49) >> 7) & 2097151;
        long load42 = (load4(bArr, 52) >> 4) & 2097151;
        long load32 = (load3(bArr, 55) >> 1) & 2097151;
        long load43 = (load4(bArr, 57) >> 6) & 2097151;
        long load44 = load4(bArr, 60) >> 3;
        long load33 = (load3(bArr, 42) & 2097151) - (load44 * 683901);
        long load34 = ((load3(bArr, 26) >> 2) & 2097151) + (load43 * 666643);
        long load45 = ((load4(bArr, 28) >> 7) & 2097151) + (load44 * 666643) + (load43 * 470296);
        long load46 = ((load4(bArr, 31) >> 4) & 2097151) + (load44 * 470296) + (load43 * 654183);
        long load35 = (((load3(bArr, 34) >> 1) & 2097151) + (load44 * 654183)) - (load43 * 997805);
        long load36 = (((load3(bArr, 39) >> 3) & 2097151) + (load44 * 136657)) - (load43 * 683901);
        long load47 = ((load4(bArr, 23) >> 5) & 2097151) + (load32 * 666643);
        long load48 = ((((load4(bArr, 36) >> 6) & 2097151) - (load44 * 997805)) + (load43 * 136657)) - (load32 * 683901);
        long load37 = (load3(bArr, 21) & 2097151) + (load42 * 666643);
        long load38 = ((load3(bArr, 18) >> 3) & 2097151) + (load4 * 666643);
        long j2 = ((load46 - (load32 * 997805)) + (load42 * 136657)) - (load4 * 683901);
        long load49 = ((load4(bArr, 15) >> 6) & 2097151) + (load3 * 666643);
        long j3 = load38 + (load3 * 470296);
        long j4 = load37 + (load4 * 470296) + (load3 * 654183);
        long j5 = ((load47 + (load42 * 470296)) + (load4 * 654183)) - (load3 * 997805);
        long j6 = (((load34 + (load32 * 470296)) + (load42 * 654183)) - (load4 * 997805)) + (load3 * 136657);
        long j7 = (((load45 + (load32 * 654183)) - (load42 * 997805)) + (load4 * 136657)) - (load3 * 683901);
        long j8 = (load49 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j9 = j3 + j8;
        long j10 = load49 - (j8 << 21);
        long j11 = (j4 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j12 = j5 + j11;
        long j13 = j4 - (j11 << 21);
        long j14 = (j6 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j15 = j7 + j14;
        long j16 = j6 - (j14 << 21);
        long j17 = (j2 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j18 = ((load35 + (load32 * 136657)) - (load42 * 683901)) + j17;
        long j19 = j2 - (j17 << 21);
        long j20 = (load48 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j21 = load36 + j20;
        long j22 = load48 - (j20 << 21);
        long j23 = (load33 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long load410 = ((load4(bArr, 44) >> 5) & 2097151) + j23;
        long j24 = load33 - (j23 << 21);
        long j25 = (j9 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j26 = j13 + j25;
        long j27 = j9 - (j25 << 21);
        long j28 = (j12 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j29 = j16 + j28;
        long j30 = j12 - (j28 << 21);
        long j31 = (j15 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j32 = j19 + j31;
        long j33 = j15 - (j31 << 21);
        long j34 = (j18 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j35 = j22 + j34;
        long j36 = j18 - (j34 << 21);
        long j37 = (j21 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j38 = j24 + j37;
        long j39 = j21 - (j37 << 21);
        long load39 = ((load3(bArr, 13) >> 1) & 2097151) + (load410 * 666643);
        long j40 = j29 - (load410 * 683901);
        long load411 = ((load4(bArr, 10) >> 4) & 2097151) + (j38 * 666643);
        long load412 = ((load4(bArr, 7) >> 7) & 2097151) + (j39 * 666643);
        long j41 = ((j26 - (load410 * 997805)) + (j38 * 136657)) - (j39 * 683901);
        long load310 = ((load3(bArr, 5) >> 2) & 2097151) + (j35 * 666643);
        long load413 = ((load4(bArr, 2) >> 5) & 2097151) + (j36 * 666643);
        long j42 = ((((j10 + (load410 * 470296)) + (j38 * 654183)) - (j39 * 997805)) + (j35 * 136657)) - (j36 * 683901);
        long load311 = (load3(bArr, 0) & 2097151) + (j32 * 666643);
        long j43 = load413 + (j32 * 470296);
        long j44 = load310 + (j36 * 470296) + (j32 * 654183);
        long j45 = ((load412 + (j35 * 470296)) + (j36 * 654183)) - (j32 * 997805);
        long j46 = (((load411 + (j39 * 470296)) + (j35 * 654183)) - (j36 * 997805)) + (j32 * 136657);
        long j47 = ((((load39 + (j38 * 470296)) + (j39 * 654183)) - (j35 * 997805)) + (j36 * 136657)) - (j32 * 683901);
        long j48 = (load311 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j49 = j43 + j48;
        long j50 = load311 - (j48 << 21);
        long j51 = (j44 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j52 = j45 + j51;
        long j53 = j44 - (j51 << 21);
        long j54 = (j46 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j55 = j47 + j54;
        long j56 = j46 - (j54 << 21);
        long j57 = (j42 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j58 = ((((j27 + (load410 * 654183)) - (j38 * 997805)) + (j39 * 136657)) - (j35 * 683901)) + j57;
        long j59 = j42 - (j57 << 21);
        long j60 = (j41 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j61 = ((j30 + (load410 * 136657)) - (j38 * 683901)) + j60;
        long j62 = j41 - (j60 << 21);
        long j63 = (j40 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j64 = j33 + j63;
        long j65 = j40 - (j63 << 21);
        long j66 = (j49 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j67 = j53 + j66;
        long j68 = j49 - (j66 << 21);
        long j69 = (j52 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j70 = j56 + j69;
        long j71 = j52 - (j69 << 21);
        long j72 = (j55 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j73 = j59 + j72;
        long j74 = j55 - (j72 << 21);
        long j75 = (j58 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j76 = j62 + j75;
        long j77 = j58 - (j75 << 21);
        long j78 = (j61 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j79 = j65 + j78;
        long j80 = j61 - (j78 << 21);
        long j81 = (j64 + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) >> 21;
        long j82 = j81 + 0;
        long j83 = j50 + (j82 * 666643);
        long j84 = j68 + (j82 * 470296);
        long j85 = j67 + (j82 * 654183);
        long j86 = j71 - (j82 * 997805);
        long j87 = j74 - (j82 * 683901);
        long j88 = j83 >> 21;
        long j89 = j84 + j88;
        long j90 = j83 - (j88 << 21);
        long j91 = j89 >> 21;
        long j92 = j85 + j91;
        long j93 = j89 - (j91 << 21);
        long j94 = j92 >> 21;
        long j95 = j86 + j94;
        long j96 = j92 - (j94 << 21);
        long j97 = j95 >> 21;
        long j98 = j70 + (j82 * 136657) + j97;
        long j99 = j95 - (j97 << 21);
        long j100 = j98 >> 21;
        long j101 = j87 + j100;
        long j102 = j98 - (j100 << 21);
        long j103 = j101 >> 21;
        long j104 = j73 + j103;
        long j105 = j101 - (j103 << 21);
        long j106 = j104 >> 21;
        long j107 = j77 + j106;
        long j108 = j104 - (j106 << 21);
        long j109 = j107 >> 21;
        long j110 = j76 + j109;
        long j111 = j107 - (j109 << 21);
        long j112 = j110 >> 21;
        long j113 = j80 + j112;
        long j114 = j110 - (j112 << 21);
        long j115 = j113 >> 21;
        long j116 = j79 + j115;
        long j117 = j113 - (j115 << 21);
        long j118 = j116 >> 21;
        long j119 = (j64 - (j81 << 21)) + j118;
        long j120 = j116 - (j118 << 21);
        long j121 = j119 >> 21;
        long j122 = j121 + 0;
        long j123 = j119 - (j121 << 21);
        long j124 = j90 + (666643 * j122);
        long j125 = j124 >> 21;
        long j126 = j93 + (470296 * j122) + j125;
        long j127 = j124 - (j125 << 21);
        long j128 = j126 >> 21;
        long j129 = j96 + (654183 * j122) + j128;
        long j130 = j126 - (j128 << 21);
        long j131 = j129 >> 21;
        long j132 = (j99 - (997805 * j122)) + j131;
        long j133 = j129 - (j131 << 21);
        long j134 = j132 >> 21;
        long j135 = j102 + (136657 * j122) + j134;
        long j136 = j132 - (j134 << 21);
        long j137 = j135 >> 21;
        long j138 = (j105 - (j122 * 683901)) + j137;
        long j139 = j135 - (j137 << 21);
        long j140 = j138 >> 21;
        long j141 = j108 + j140;
        long j142 = j138 - (j140 << 21);
        long j143 = j141 >> 21;
        long j144 = j111 + j143;
        long j145 = j141 - (j143 << 21);
        long j146 = j144 >> 21;
        long j147 = j114 + j146;
        long j148 = j144 - (j146 << 21);
        long j149 = j147 >> 21;
        long j150 = j117 + j149;
        long j151 = j147 - (j149 << 21);
        long j152 = j150 >> 21;
        long j153 = j120 + j152;
        long j154 = j150 - (j152 << 21);
        long j155 = j153 >> 21;
        long j156 = j123 + j155;
        long j157 = j153 - (j155 << 21);
        bArr[0] = (byte) j127;
        bArr[1] = (byte) (j127 >> 8);
        bArr[2] = (byte) ((j127 >> 16) | (j130 << 5));
        bArr[3] = (byte) (j130 >> 3);
        bArr[4] = (byte) (j130 >> 11);
        bArr[5] = (byte) ((j130 >> 19) | (j133 << 2));
        bArr[6] = (byte) (j133 >> 6);
        bArr[7] = (byte) ((j133 >> 14) | (j136 << 7));
        bArr[8] = (byte) (j136 >> 1);
        bArr[9] = (byte) (j136 >> 9);
        bArr[10] = (byte) ((j136 >> 17) | (j139 << 4));
        bArr[11] = (byte) (j139 >> 4);
        bArr[12] = (byte) (j139 >> 12);
        bArr[13] = (byte) ((j139 >> 20) | (j142 << 1));
        bArr[14] = (byte) (j142 >> 7);
        bArr[15] = (byte) ((j142 >> 15) | (j145 << 6));
        bArr[16] = (byte) (j145 >> 2);
        bArr[17] = (byte) (j145 >> 10);
        bArr[18] = (byte) ((j145 >> 18) | (j148 << 3));
        bArr[19] = (byte) (j148 >> 5);
        bArr[20] = (byte) (j148 >> 13);
        bArr[21] = (byte) j151;
        bArr[22] = (byte) (j151 >> 8);
        bArr[23] = (byte) ((j151 >> 16) | (j154 << 5));
        bArr[24] = (byte) (j154 >> 3);
        bArr[25] = (byte) (j154 >> 11);
        bArr[26] = (byte) ((j154 >> 19) | (j157 << 2));
        bArr[27] = (byte) (j157 >> 6);
        bArr[28] = (byte) ((j157 >> 14) | (j156 << 7));
        bArr[29] = (byte) (j156 >> 1);
        bArr[30] = (byte) (j156 >> 9);
        bArr[31] = (byte) (j156 >> 17);
    }

    private static XYZ scalarMultWithBase(byte[] bArr) {
        int i2;
        byte[] bArr2 = new byte[64];
        int i3 = 0;
        while (true) {
            if (i3 >= 32) {
                break;
            }
            int i4 = i3 * 2;
            bArr2[i4 + 0] = (byte) (((bArr[i3] & 255) >> 0) & 15);
            bArr2[i4 + 1] = (byte) (((bArr[i3] & 255) >> 4) & 15);
            i3++;
        }
        int i5 = 0;
        for (int i6 = 0; i6 < 63; i6++) {
            bArr2[i6] = (byte) (bArr2[i6] + i5);
            i5 = (bArr2[i6] + 8) >> 4;
            bArr2[i6] = (byte) (bArr2[i6] - (i5 << 4));
        }
        bArr2[63] = (byte) (bArr2[63] + i5);
        PartialXYZT partialXYZT = new PartialXYZT(NEUTRAL);
        XYZT xyzt = new XYZT();
        for (i2 = 1; i2 < 64; i2 += 2) {
            CachedXYT cachedXYT = new CachedXYT(CACHED_NEUTRAL);
            select(cachedXYT, i2 / 2, bArr2[i2]);
            add(partialXYZT, XYZT.fromPartialXYZT(xyzt, partialXYZT), cachedXYT);
        }
        XYZ xyz = new XYZ();
        doubleXYZ(partialXYZT, XYZ.a(xyz, partialXYZT));
        doubleXYZ(partialXYZT, XYZ.a(xyz, partialXYZT));
        doubleXYZ(partialXYZT, XYZ.a(xyz, partialXYZT));
        doubleXYZ(partialXYZT, XYZ.a(xyz, partialXYZT));
        for (int i7 = 0; i7 < 64; i7 += 2) {
            CachedXYT cachedXYT2 = new CachedXYT(CACHED_NEUTRAL);
            select(cachedXYT2, i7 / 2, bArr2[i7]);
            add(partialXYZT, XYZT.fromPartialXYZT(xyzt, partialXYZT), cachedXYT2);
        }
        XYZ xyz2 = new XYZ(partialXYZT);
        if (xyz2.b()) {
            return xyz2;
        }
        throw new IllegalStateException("arithmetic error in scalar multiplication");
    }

    private static void select(CachedXYT cachedXYT, int i2, byte b2) {
        int i3 = (b2 & 255) >> 7;
        int i4 = b2 - (((-i3) & b2) << 1);
        CachedXYT[][] cachedXYTArr = Ed25519Constants.f9852d;
        cachedXYT.a(cachedXYTArr[i2][0], eq(i4, 1));
        cachedXYT.a(cachedXYTArr[i2][1], eq(i4, 2));
        cachedXYT.a(cachedXYTArr[i2][2], eq(i4, 3));
        cachedXYT.a(cachedXYTArr[i2][3], eq(i4, 4));
        cachedXYT.a(cachedXYTArr[i2][4], eq(i4, 5));
        cachedXYT.a(cachedXYTArr[i2][5], eq(i4, 6));
        cachedXYT.a(cachedXYTArr[i2][6], eq(i4, 7));
        cachedXYT.a(cachedXYTArr[i2][7], eq(i4, 8));
        long[] copyOf = Arrays.copyOf(cachedXYT.f9840b, 10);
        long[] copyOf2 = Arrays.copyOf(cachedXYT.f9839a, 10);
        long[] copyOf3 = Arrays.copyOf(cachedXYT.f9841c, 10);
        neg(copyOf3, copyOf3);
        cachedXYT.a(new CachedXYT(copyOf, copyOf2, copyOf3), i3);
    }

    private static byte[] slide(byte[] bArr) {
        int i2;
        byte[] bArr2 = new byte[256];
        for (int i3 = 0; i3 < 256; i3++) {
            bArr2[i3] = (byte) (1 & ((bArr[i3 >> 3] & 255) >> (i3 & 7)));
        }
        for (int i4 = 0; i4 < 256; i4++) {
            if (bArr2[i4] != 0) {
                for (int i5 = 1; i5 <= 6 && (i2 = i4 + i5) < 256; i5++) {
                    if (bArr2[i2] != 0) {
                        if (bArr2[i4] + (bArr2[i2] << i5) <= 15) {
                            bArr2[i4] = (byte) (bArr2[i4] + (bArr2[i2] << i5));
                            bArr2[i2] = 0;
                        } else if (bArr2[i4] - (bArr2[i2] << i5) >= -15) {
                            bArr2[i4] = (byte) (bArr2[i4] - (bArr2[i2] << i5));
                            while (true) {
                                if (i2 >= 256) {
                                    break;
                                } else if (bArr2[i2] == 0) {
                                    bArr2[i2] = 1;
                                    break;
                                } else {
                                    bArr2[i2] = 0;
                                    i2++;
                                }
                            }
                        }
                    }
                }
            }
        }
        return bArr2;
    }

    private static void sub(PartialXYZT partialXYZT, XYZT xyzt, CachedXYT cachedXYT) {
        long[] jArr = new long[10];
        long[] jArr2 = partialXYZT.f9842a.f9844a;
        XYZ xyz = xyzt.f9847a;
        Field25519.n(jArr2, xyz.f9845b, xyz.f9844a);
        long[] jArr3 = partialXYZT.f9842a.f9845b;
        XYZ xyz2 = xyzt.f9847a;
        Field25519.l(jArr3, xyz2.f9845b, xyz2.f9844a);
        long[] jArr4 = partialXYZT.f9842a.f9845b;
        Field25519.d(jArr4, jArr4, cachedXYT.f9839a);
        XYZ xyz3 = partialXYZT.f9842a;
        Field25519.d(xyz3.f9846c, xyz3.f9844a, cachedXYT.f9840b);
        Field25519.d(partialXYZT.f9843b, xyzt.f9848b, cachedXYT.f9841c);
        cachedXYT.multByZ(partialXYZT.f9842a.f9844a, xyzt.f9847a.f9846c);
        long[] jArr5 = partialXYZT.f9842a.f9844a;
        Field25519.n(jArr, jArr5, jArr5);
        XYZ xyz4 = partialXYZT.f9842a;
        Field25519.l(xyz4.f9844a, xyz4.f9846c, xyz4.f9845b);
        XYZ xyz5 = partialXYZT.f9842a;
        long[] jArr6 = xyz5.f9845b;
        Field25519.n(jArr6, xyz5.f9846c, jArr6);
        Field25519.l(partialXYZT.f9842a.f9846c, jArr, partialXYZT.f9843b);
        long[] jArr7 = partialXYZT.f9843b;
        Field25519.n(jArr7, jArr, jArr7);
    }
}
