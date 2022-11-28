package kotlin.comparisons;

import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class ComparisonsKt___ComparisonsJvmKt extends ComparisonsKt__ComparisonsKt {
    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final byte maxOf(byte b2, byte b3) {
        return (byte) Math.max((int) b2, (int) b3);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final byte maxOf(byte b2, byte b3, byte b4) {
        return (byte) Math.max((int) b2, Math.max((int) b3, (int) b4));
    }

    @SinceKotlin(version = "1.4")
    public static final byte maxOf(byte b2, @NotNull byte... other) {
        Intrinsics.checkNotNullParameter(other, "other");
        for (byte b3 : other) {
            b2 = (byte) Math.max((int) b2, (int) b3);
        }
        return b2;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final double maxOf(double d2, double d3) {
        return Math.max(d2, d3);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final double maxOf(double d2, double d3, double d4) {
        return Math.max(d2, Math.max(d3, d4));
    }

    @SinceKotlin(version = "1.4")
    public static final double maxOf(double d2, @NotNull double... other) {
        Intrinsics.checkNotNullParameter(other, "other");
        for (double d3 : other) {
            d2 = Math.max(d2, d3);
        }
        return d2;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final float maxOf(float f2, float f3) {
        return Math.max(f2, f3);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final float maxOf(float f2, float f3, float f4) {
        return Math.max(f2, Math.max(f3, f4));
    }

    @SinceKotlin(version = "1.4")
    public static final float maxOf(float f2, @NotNull float... other) {
        Intrinsics.checkNotNullParameter(other, "other");
        for (float f3 : other) {
            f2 = Math.max(f2, f3);
        }
        return f2;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final int maxOf(int i2, int i3) {
        return Math.max(i2, i3);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final int maxOf(int i2, int i3, int i4) {
        return Math.max(i2, Math.max(i3, i4));
    }

    @SinceKotlin(version = "1.4")
    public static final int maxOf(int i2, @NotNull int... other) {
        Intrinsics.checkNotNullParameter(other, "other");
        for (int i3 : other) {
            i2 = Math.max(i2, i3);
        }
        return i2;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final long maxOf(long j2, long j3) {
        return Math.max(j2, j3);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final long maxOf(long j2, long j3, long j4) {
        return Math.max(j2, Math.max(j3, j4));
    }

    @SinceKotlin(version = "1.4")
    public static final long maxOf(long j2, @NotNull long... other) {
        Intrinsics.checkNotNullParameter(other, "other");
        for (long j3 : other) {
            j2 = Math.max(j2, j3);
        }
        return j2;
    }

    @SinceKotlin(version = "1.1")
    @NotNull
    public static <T extends Comparable<? super T>> T maxOf(@NotNull T a2, @NotNull T b2) {
        Intrinsics.checkNotNullParameter(a2, "a");
        Intrinsics.checkNotNullParameter(b2, "b");
        return a2.compareTo(b2) >= 0 ? a2 : b2;
    }

    @SinceKotlin(version = "1.1")
    @NotNull
    public static final <T extends Comparable<? super T>> T maxOf(@NotNull T a2, @NotNull T b2, @NotNull T c2) {
        Intrinsics.checkNotNullParameter(a2, "a");
        Intrinsics.checkNotNullParameter(b2, "b");
        Intrinsics.checkNotNullParameter(c2, "c");
        return (T) ComparisonsKt.maxOf(a2, ComparisonsKt.maxOf(b2, c2));
    }

    @SinceKotlin(version = "1.4")
    @NotNull
    public static final <T extends Comparable<? super T>> T maxOf(@NotNull T a2, @NotNull T... other) {
        Intrinsics.checkNotNullParameter(a2, "a");
        Intrinsics.checkNotNullParameter(other, "other");
        for (T t2 : other) {
            a2 = (T) ComparisonsKt.maxOf(a2, t2);
        }
        return a2;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final short maxOf(short s2, short s3) {
        return (short) Math.max((int) s2, (int) s3);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final short maxOf(short s2, short s3, short s4) {
        return (short) Math.max((int) s2, Math.max((int) s3, (int) s4));
    }

    @SinceKotlin(version = "1.4")
    public static final short maxOf(short s2, @NotNull short... other) {
        Intrinsics.checkNotNullParameter(other, "other");
        for (short s3 : other) {
            s2 = (short) Math.max((int) s2, (int) s3);
        }
        return s2;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final byte minOf(byte b2, byte b3) {
        return (byte) Math.min((int) b2, (int) b3);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final byte minOf(byte b2, byte b3, byte b4) {
        return (byte) Math.min((int) b2, Math.min((int) b3, (int) b4));
    }

    @SinceKotlin(version = "1.4")
    public static final byte minOf(byte b2, @NotNull byte... other) {
        Intrinsics.checkNotNullParameter(other, "other");
        for (byte b3 : other) {
            b2 = (byte) Math.min((int) b2, (int) b3);
        }
        return b2;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final double minOf(double d2, double d3) {
        return Math.min(d2, d3);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final double minOf(double d2, double d3, double d4) {
        return Math.min(d2, Math.min(d3, d4));
    }

    @SinceKotlin(version = "1.4")
    public static final double minOf(double d2, @NotNull double... other) {
        Intrinsics.checkNotNullParameter(other, "other");
        for (double d3 : other) {
            d2 = Math.min(d2, d3);
        }
        return d2;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final float minOf(float f2, float f3) {
        return Math.min(f2, f3);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final float minOf(float f2, float f3, float f4) {
        return Math.min(f2, Math.min(f3, f4));
    }

    @SinceKotlin(version = "1.4")
    public static final float minOf(float f2, @NotNull float... other) {
        Intrinsics.checkNotNullParameter(other, "other");
        for (float f3 : other) {
            f2 = Math.min(f2, f3);
        }
        return f2;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final int minOf(int i2, int i3) {
        return Math.min(i2, i3);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final int minOf(int i2, int i3, int i4) {
        return Math.min(i2, Math.min(i3, i4));
    }

    @SinceKotlin(version = "1.4")
    public static final int minOf(int i2, @NotNull int... other) {
        Intrinsics.checkNotNullParameter(other, "other");
        for (int i3 : other) {
            i2 = Math.min(i2, i3);
        }
        return i2;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final long minOf(long j2, long j3) {
        return Math.min(j2, j3);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final long minOf(long j2, long j3, long j4) {
        return Math.min(j2, Math.min(j3, j4));
    }

    @SinceKotlin(version = "1.4")
    public static final long minOf(long j2, @NotNull long... other) {
        Intrinsics.checkNotNullParameter(other, "other");
        for (long j3 : other) {
            j2 = Math.min(j2, j3);
        }
        return j2;
    }

    @SinceKotlin(version = "1.1")
    @NotNull
    public static final <T extends Comparable<? super T>> T minOf(@NotNull T a2, @NotNull T b2) {
        Intrinsics.checkNotNullParameter(a2, "a");
        Intrinsics.checkNotNullParameter(b2, "b");
        return a2.compareTo(b2) <= 0 ? a2 : b2;
    }

    @SinceKotlin(version = "1.1")
    @NotNull
    public static final <T extends Comparable<? super T>> T minOf(@NotNull T a2, @NotNull T b2, @NotNull T c2) {
        Intrinsics.checkNotNullParameter(a2, "a");
        Intrinsics.checkNotNullParameter(b2, "b");
        Intrinsics.checkNotNullParameter(c2, "c");
        return (T) minOf(a2, minOf(b2, c2));
    }

    @SinceKotlin(version = "1.4")
    @NotNull
    public static final <T extends Comparable<? super T>> T minOf(@NotNull T a2, @NotNull T... other) {
        Intrinsics.checkNotNullParameter(a2, "a");
        Intrinsics.checkNotNullParameter(other, "other");
        for (T t2 : other) {
            a2 = (T) minOf(a2, t2);
        }
        return a2;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final short minOf(short s2, short s3) {
        return (short) Math.min((int) s2, (int) s3);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final short minOf(short s2, short s3, short s4) {
        return (short) Math.min((int) s2, Math.min((int) s3, (int) s4));
    }

    @SinceKotlin(version = "1.4")
    public static final short minOf(short s2, @NotNull short... other) {
        Intrinsics.checkNotNullParameter(other, "other");
        for (short s3 : other) {
            s2 = (short) Math.min((int) s2, (int) s3);
        }
        return s2;
    }
}
