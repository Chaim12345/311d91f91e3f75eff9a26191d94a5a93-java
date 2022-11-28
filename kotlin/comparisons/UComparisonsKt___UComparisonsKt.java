package kotlin.comparisons;

import kotlin.ExperimentalUnsignedTypes;
import kotlin.SinceKotlin;
import kotlin.UByteArray;
import kotlin.UIntArray;
import kotlin.ULongArray;
import kotlin.UShort;
import kotlin.UShortArray;
import kotlin.UnsignedKt;
import kotlin.WasExperimental;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class UComparisonsKt___UComparisonsKt {
    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    /* renamed from: maxOf-5PvTz6A  reason: not valid java name */
    public static final short m1358maxOf5PvTz6A(short s2, short s3) {
        return Intrinsics.compare(s2 & UShort.MAX_VALUE, 65535 & s3) >= 0 ? s2 : s3;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    /* renamed from: maxOf-J1ME1BU  reason: not valid java name */
    public static int m1359maxOfJ1ME1BU(int i2, int i3) {
        return UnsignedKt.uintCompare(i2, i3) >= 0 ? i2 : i3;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    /* renamed from: maxOf-Kr8caGY  reason: not valid java name */
    public static final byte m1360maxOfKr8caGY(byte b2, byte b3) {
        return Intrinsics.compare(b2 & 255, b3 & 255) >= 0 ? b2 : b3;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    /* renamed from: maxOf-Md2H83M  reason: not valid java name */
    public static final int m1361maxOfMd2H83M(int i2, @NotNull int... other) {
        Intrinsics.checkNotNullParameter(other, "other");
        int m341getSizeimpl = UIntArray.m341getSizeimpl(other);
        for (int i3 = 0; i3 < m341getSizeimpl; i3++) {
            i2 = m1359maxOfJ1ME1BU(i2, UIntArray.m340getpVg5ArA(other, i3));
        }
        return i2;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    /* renamed from: maxOf-R03FKyM  reason: not valid java name */
    public static final long m1362maxOfR03FKyM(long j2, @NotNull long... other) {
        Intrinsics.checkNotNullParameter(other, "other");
        int m419getSizeimpl = ULongArray.m419getSizeimpl(other);
        for (int i2 = 0; i2 < m419getSizeimpl; i2++) {
            j2 = m1367maxOfeb3DHEI(j2, ULongArray.m418getsVKNKU(other, i2));
        }
        return j2;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    @InlineOnly
    /* renamed from: maxOf-VKSA0NQ  reason: not valid java name */
    private static final short m1363maxOfVKSA0NQ(short s2, short s3, short s4) {
        return m1358maxOf5PvTz6A(s2, m1358maxOf5PvTz6A(s3, s4));
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    @InlineOnly
    /* renamed from: maxOf-WZ9TVnA  reason: not valid java name */
    private static final int m1364maxOfWZ9TVnA(int i2, int i3, int i4) {
        int m1359maxOfJ1ME1BU;
        int m1359maxOfJ1ME1BU2;
        m1359maxOfJ1ME1BU = m1359maxOfJ1ME1BU(i3, i4);
        m1359maxOfJ1ME1BU2 = m1359maxOfJ1ME1BU(i2, m1359maxOfJ1ME1BU);
        return m1359maxOfJ1ME1BU2;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    /* renamed from: maxOf-Wr6uiD8  reason: not valid java name */
    public static final byte m1365maxOfWr6uiD8(byte b2, @NotNull byte... other) {
        Intrinsics.checkNotNullParameter(other, "other");
        int m263getSizeimpl = UByteArray.m263getSizeimpl(other);
        for (int i2 = 0; i2 < m263getSizeimpl; i2++) {
            b2 = m1360maxOfKr8caGY(b2, UByteArray.m262getw2LRezQ(other, i2));
        }
        return b2;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    @InlineOnly
    /* renamed from: maxOf-b33U2AM  reason: not valid java name */
    private static final byte m1366maxOfb33U2AM(byte b2, byte b3, byte b4) {
        return m1360maxOfKr8caGY(b2, m1360maxOfKr8caGY(b3, b4));
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    /* renamed from: maxOf-eb3DHEI  reason: not valid java name */
    public static long m1367maxOfeb3DHEI(long j2, long j3) {
        return UnsignedKt.ulongCompare(j2, j3) >= 0 ? j2 : j3;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    @InlineOnly
    /* renamed from: maxOf-sambcqE  reason: not valid java name */
    private static final long m1368maxOfsambcqE(long j2, long j3, long j4) {
        long m1367maxOfeb3DHEI;
        long m1367maxOfeb3DHEI2;
        m1367maxOfeb3DHEI = m1367maxOfeb3DHEI(j3, j4);
        m1367maxOfeb3DHEI2 = m1367maxOfeb3DHEI(j2, m1367maxOfeb3DHEI);
        return m1367maxOfeb3DHEI2;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    /* renamed from: maxOf-t1qELG4  reason: not valid java name */
    public static final short m1369maxOft1qELG4(short s2, @NotNull short... other) {
        Intrinsics.checkNotNullParameter(other, "other");
        int m523getSizeimpl = UShortArray.m523getSizeimpl(other);
        for (int i2 = 0; i2 < m523getSizeimpl; i2++) {
            s2 = m1358maxOf5PvTz6A(s2, UShortArray.m522getMh2AYeg(other, i2));
        }
        return s2;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    /* renamed from: minOf-5PvTz6A  reason: not valid java name */
    public static final short m1370minOf5PvTz6A(short s2, short s3) {
        return Intrinsics.compare(s2 & UShort.MAX_VALUE, 65535 & s3) <= 0 ? s2 : s3;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    /* renamed from: minOf-J1ME1BU  reason: not valid java name */
    public static int m1371minOfJ1ME1BU(int i2, int i3) {
        return UnsignedKt.uintCompare(i2, i3) <= 0 ? i2 : i3;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    /* renamed from: minOf-Kr8caGY  reason: not valid java name */
    public static final byte m1372minOfKr8caGY(byte b2, byte b3) {
        return Intrinsics.compare(b2 & 255, b3 & 255) <= 0 ? b2 : b3;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    /* renamed from: minOf-Md2H83M  reason: not valid java name */
    public static final int m1373minOfMd2H83M(int i2, @NotNull int... other) {
        Intrinsics.checkNotNullParameter(other, "other");
        int m341getSizeimpl = UIntArray.m341getSizeimpl(other);
        for (int i3 = 0; i3 < m341getSizeimpl; i3++) {
            i2 = m1371minOfJ1ME1BU(i2, UIntArray.m340getpVg5ArA(other, i3));
        }
        return i2;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    /* renamed from: minOf-R03FKyM  reason: not valid java name */
    public static final long m1374minOfR03FKyM(long j2, @NotNull long... other) {
        Intrinsics.checkNotNullParameter(other, "other");
        int m419getSizeimpl = ULongArray.m419getSizeimpl(other);
        for (int i2 = 0; i2 < m419getSizeimpl; i2++) {
            j2 = m1379minOfeb3DHEI(j2, ULongArray.m418getsVKNKU(other, i2));
        }
        return j2;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    @InlineOnly
    /* renamed from: minOf-VKSA0NQ  reason: not valid java name */
    private static final short m1375minOfVKSA0NQ(short s2, short s3, short s4) {
        return m1370minOf5PvTz6A(s2, m1370minOf5PvTz6A(s3, s4));
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    @InlineOnly
    /* renamed from: minOf-WZ9TVnA  reason: not valid java name */
    private static final int m1376minOfWZ9TVnA(int i2, int i3, int i4) {
        int m1371minOfJ1ME1BU;
        int m1371minOfJ1ME1BU2;
        m1371minOfJ1ME1BU = m1371minOfJ1ME1BU(i3, i4);
        m1371minOfJ1ME1BU2 = m1371minOfJ1ME1BU(i2, m1371minOfJ1ME1BU);
        return m1371minOfJ1ME1BU2;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    /* renamed from: minOf-Wr6uiD8  reason: not valid java name */
    public static final byte m1377minOfWr6uiD8(byte b2, @NotNull byte... other) {
        Intrinsics.checkNotNullParameter(other, "other");
        int m263getSizeimpl = UByteArray.m263getSizeimpl(other);
        for (int i2 = 0; i2 < m263getSizeimpl; i2++) {
            b2 = m1372minOfKr8caGY(b2, UByteArray.m262getw2LRezQ(other, i2));
        }
        return b2;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    @InlineOnly
    /* renamed from: minOf-b33U2AM  reason: not valid java name */
    private static final byte m1378minOfb33U2AM(byte b2, byte b3, byte b4) {
        return m1372minOfKr8caGY(b2, m1372minOfKr8caGY(b3, b4));
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    /* renamed from: minOf-eb3DHEI  reason: not valid java name */
    public static long m1379minOfeb3DHEI(long j2, long j3) {
        return UnsignedKt.ulongCompare(j2, j3) <= 0 ? j2 : j3;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    @InlineOnly
    /* renamed from: minOf-sambcqE  reason: not valid java name */
    private static final long m1380minOfsambcqE(long j2, long j3, long j4) {
        long m1379minOfeb3DHEI;
        long m1379minOfeb3DHEI2;
        m1379minOfeb3DHEI = m1379minOfeb3DHEI(j3, j4);
        m1379minOfeb3DHEI2 = m1379minOfeb3DHEI(j2, m1379minOfeb3DHEI);
        return m1379minOfeb3DHEI2;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    /* renamed from: minOf-t1qELG4  reason: not valid java name */
    public static final short m1381minOft1qELG4(short s2, @NotNull short... other) {
        Intrinsics.checkNotNullParameter(other, "other");
        int m523getSizeimpl = UShortArray.m523getSizeimpl(other);
        for (int i2 = 0; i2 < m523getSizeimpl; i2++) {
            s2 = m1370minOf5PvTz6A(s2, UShortArray.m522getMh2AYeg(other, i2));
        }
        return s2;
    }
}
