package com.google.common.math;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.android.gms.dynamite.descriptors.com.google.mlkit.dynamite.barcode.ModuleDescriptor;
import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Ints;
import java.math.RoundingMode;
import kotlin.time.DurationKt;
import org.bouncycastle.tls.CipherSuite;
@GwtCompatible(emulated = true)
/* loaded from: classes2.dex */
public final class IntMath {
    @VisibleForTesting

    /* renamed from: a  reason: collision with root package name */
    static final byte[] f9321a = {9, 9, 9, 8, 8, 8, 7, 7, 7, 6, 6, 6, 6, 5, 5, 5, 4, 4, 4, 3, 3, 3, 3, 2, 2, 2, 1, 1, 1, 0, 0, 0, 0};
    @VisibleForTesting

    /* renamed from: b  reason: collision with root package name */
    static final int[] f9322b = {1, 10, 100, 1000, ModuleDescriptor.MODULE_VERSION, 100000, DurationKt.NANOS_IN_MILLIS, 10000000, 100000000, 1000000000};
    @VisibleForTesting

    /* renamed from: c  reason: collision with root package name */
    static final int[] f9323c = {3, 31, TypedValues.Attributes.TYPE_PATH_ROTATE, 3162, 31622, 316227, 3162277, 31622776, 316227766, Integer.MAX_VALUE};
    private static final int[] factorials = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880, 3628800, 39916800, 479001600};
    @VisibleForTesting

    /* renamed from: d  reason: collision with root package name */
    static int[] f9324d = {Integer.MAX_VALUE, Integer.MAX_VALUE, 65536, 2345, 477, CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_CBC_SHA256, 110, 75, 58, 49, 43, 39, 37, 35, 34, 34, 33};

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.common.math.IntMath$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f9325a;

        static {
            int[] iArr = new int[RoundingMode.values().length];
            f9325a = iArr;
            try {
                iArr[RoundingMode.UNNECESSARY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f9325a[RoundingMode.DOWN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f9325a[RoundingMode.FLOOR.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f9325a[RoundingMode.UP.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f9325a[RoundingMode.CEILING.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f9325a[RoundingMode.HALF_DOWN.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f9325a[RoundingMode.HALF_UP.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f9325a[RoundingMode.HALF_EVEN.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    private IntMath() {
    }

    @VisibleForTesting
    static int a(int i2, int i3) {
        return (~(~(i2 - i3))) >>> 31;
    }

    public static int binomial(int i2, int i3) {
        MathPreconditions.e("n", i2);
        MathPreconditions.e("k", i3);
        int i4 = 0;
        Preconditions.checkArgument(i3 <= i2, "k (%s) > n (%s)", i3, i2);
        if (i3 > (i2 >> 1)) {
            i3 = i2 - i3;
        }
        int[] iArr = f9324d;
        if (i3 >= iArr.length || i2 > iArr[i3]) {
            return Integer.MAX_VALUE;
        }
        if (i3 != 0) {
            if (i3 != 1) {
                long j2 = 1;
                while (i4 < i3) {
                    i4++;
                    j2 = (j2 * (i2 - i4)) / i4;
                }
                return (int) j2;
            }
            return i2;
        }
        return 1;
    }

    @Beta
    public static int ceilingPowerOfTwo(int i2) {
        MathPreconditions.h("x", i2);
        if (i2 <= 1073741824) {
            return 1 << (-Integer.numberOfLeadingZeros(i2 - 1));
        }
        throw new ArithmeticException("ceilingPowerOfTwo(" + i2 + ") not representable as an int");
    }

    public static int checkedAdd(int i2, int i3) {
        long j2 = i2 + i3;
        int i4 = (int) j2;
        MathPreconditions.b(j2 == ((long) i4), "checkedAdd", i2, i3);
        return i4;
    }

    public static int checkedMultiply(int i2, int i3) {
        long j2 = i2 * i3;
        int i4 = (int) j2;
        MathPreconditions.b(j2 == ((long) i4), "checkedMultiply", i2, i3);
        return i4;
    }

    public static int checkedPow(int i2, int i3) {
        MathPreconditions.e("exponent", i3);
        if (i2 == -2) {
            MathPreconditions.b(i3 < 32, "checkedPow", i2, i3);
            return (i3 & 1) == 0 ? 1 << i3 : (-1) << i3;
        } else if (i2 == -1) {
            return (i3 & 1) == 0 ? 1 : -1;
        } else if (i2 == 0) {
            return i3 == 0 ? 1 : 0;
        } else if (i2 != 1) {
            if (i2 == 2) {
                MathPreconditions.b(i3 < 31, "checkedPow", i2, i3);
                return 1 << i3;
            }
            int i4 = 1;
            while (i3 != 0) {
                if (i3 == 1) {
                    return checkedMultiply(i4, i2);
                }
                if ((i3 & 1) != 0) {
                    i4 = checkedMultiply(i4, i2);
                }
                i3 >>= 1;
                if (i3 > 0) {
                    MathPreconditions.b((-46340 <= i2) & (i2 <= 46340), "checkedPow", i2, i3);
                    i2 *= i2;
                }
            }
            return i4;
        } else {
            return 1;
        }
    }

    public static int checkedSubtract(int i2, int i3) {
        long j2 = i2 - i3;
        int i4 = (int) j2;
        MathPreconditions.b(j2 == ((long) i4), "checkedSubtract", i2, i3);
        return i4;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0044, code lost:
        if (((r7 == java.math.RoundingMode.HALF_EVEN) & ((r0 & 1) != 0)) != false) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0047, code lost:
        if (r1 > 0) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x004a, code lost:
        if (r5 > 0) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x004d, code lost:
        if (r5 < 0) goto L32;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static int divide(int i2, int i3, RoundingMode roundingMode) {
        Preconditions.checkNotNull(roundingMode);
        if (i3 != 0) {
            int i4 = i2 / i3;
            int i5 = i2 - (i3 * i4);
            if (i5 == 0) {
                return i4;
            }
            int i6 = ((i2 ^ i3) >> 31) | 1;
            switch (AnonymousClass1.f9325a[roundingMode.ordinal()]) {
                case 1:
                    MathPreconditions.k(i5 == 0);
                    r2 = false;
                    break;
                case 2:
                    r2 = false;
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                case 7:
                case 8:
                    int abs = Math.abs(i5);
                    int abs2 = abs - (Math.abs(i3) - abs);
                    if (abs2 == 0) {
                        if (roundingMode != RoundingMode.HALF_UP) {
                            break;
                        }
                    }
                    break;
                default:
                    throw new AssertionError();
            }
            return r2 ? i4 + i6 : i4;
        }
        throw new ArithmeticException("/ by zero");
    }

    public static int factorial(int i2) {
        MathPreconditions.e("n", i2);
        int[] iArr = factorials;
        if (i2 < iArr.length) {
            return iArr[i2];
        }
        return Integer.MAX_VALUE;
    }

    @Beta
    public static int floorPowerOfTwo(int i2) {
        MathPreconditions.h("x", i2);
        return Integer.highestOneBit(i2);
    }

    public static int gcd(int i2, int i3) {
        MathPreconditions.e("a", i2);
        MathPreconditions.e("b", i3);
        if (i2 == 0) {
            return i3;
        }
        if (i3 == 0) {
            return i2;
        }
        int numberOfTrailingZeros = Integer.numberOfTrailingZeros(i2);
        int i4 = i2 >> numberOfTrailingZeros;
        int numberOfTrailingZeros2 = Integer.numberOfTrailingZeros(i3);
        int i5 = i3 >> numberOfTrailingZeros2;
        while (i4 != i5) {
            int i6 = i4 - i5;
            int i7 = (i6 >> 31) & i6;
            int i8 = (i6 - i7) - i7;
            i5 += i7;
            i4 = i8 >> Integer.numberOfTrailingZeros(i8);
        }
        return i4 << Math.min(numberOfTrailingZeros, numberOfTrailingZeros2);
    }

    public static boolean isPowerOfTwo(int i2) {
        return (i2 > 0) & ((i2 & (i2 + (-1))) == 0);
    }

    @Beta
    @GwtIncompatible
    public static boolean isPrime(int i2) {
        return LongMath.isPrime(i2);
    }

    @GwtIncompatible
    public static int log10(int i2, RoundingMode roundingMode) {
        int a2;
        MathPreconditions.h("x", i2);
        int log10Floor = log10Floor(i2);
        int i3 = f9322b[log10Floor];
        switch (AnonymousClass1.f9325a[roundingMode.ordinal()]) {
            case 1:
                MathPreconditions.k(i2 == i3);
                return log10Floor;
            case 2:
            case 3:
                return log10Floor;
            case 4:
            case 5:
                a2 = a(i3, i2);
                return log10Floor + a2;
            case 6:
            case 7:
            case 8:
                a2 = a(f9323c[log10Floor], i2);
                return log10Floor + a2;
            default:
                throw new AssertionError();
        }
    }

    private static int log10Floor(int i2) {
        byte b2 = f9321a[Integer.numberOfLeadingZeros(i2)];
        return b2 - a(i2, f9322b[b2]);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static int log2(int i2, RoundingMode roundingMode) {
        MathPreconditions.h("x", i2);
        switch (AnonymousClass1.f9325a[roundingMode.ordinal()]) {
            case 1:
                MathPreconditions.k(isPowerOfTwo(i2));
                break;
            case 2:
            case 3:
                break;
            case 4:
            case 5:
                return 32 - Integer.numberOfLeadingZeros(i2 - 1);
            case 6:
            case 7:
            case 8:
                int numberOfLeadingZeros = Integer.numberOfLeadingZeros(i2);
                return (31 - numberOfLeadingZeros) + a((-1257966797) >>> numberOfLeadingZeros, i2);
            default:
                throw new AssertionError();
        }
        return 31 - Integer.numberOfLeadingZeros(i2);
    }

    public static int mean(int i2, int i3) {
        return (i2 & i3) + ((i2 ^ i3) >> 1);
    }

    public static int mod(int i2, int i3) {
        if (i3 > 0) {
            int i4 = i2 % i3;
            return i4 >= 0 ? i4 : i4 + i3;
        }
        throw new ArithmeticException("Modulus " + i3 + " must be > 0");
    }

    @GwtIncompatible
    public static int pow(int i2, int i3) {
        MathPreconditions.e("exponent", i3);
        if (i2 == -2) {
            if (i3 < 32) {
                return (i3 & 1) == 0 ? 1 << i3 : -(1 << i3);
            }
            return 0;
        } else if (i2 == -1) {
            return (i3 & 1) == 0 ? 1 : -1;
        } else if (i2 == 0) {
            return i3 == 0 ? 1 : 0;
        } else if (i2 != 1) {
            if (i2 == 2) {
                if (i3 < 32) {
                    return 1 << i3;
                }
                return 0;
            }
            int i4 = 1;
            while (i3 != 0) {
                if (i3 == 1) {
                    return i2 * i4;
                }
                i4 *= (i3 & 1) == 0 ? 1 : i2;
                i2 *= i2;
                i3 >>= 1;
            }
            return i4;
        } else {
            return 1;
        }
    }

    @Beta
    public static int saturatedAdd(int i2, int i3) {
        return Ints.saturatedCast(i2 + i3);
    }

    @Beta
    public static int saturatedMultiply(int i2, int i3) {
        return Ints.saturatedCast(i2 * i3);
    }

    @Beta
    public static int saturatedPow(int i2, int i3) {
        MathPreconditions.e("exponent", i3);
        if (i2 == -2) {
            return i3 >= 32 ? (i3 & 1) + Integer.MAX_VALUE : (i3 & 1) == 0 ? 1 << i3 : (-1) << i3;
        } else if (i2 == -1) {
            return (i3 & 1) == 0 ? 1 : -1;
        } else if (i2 == 0) {
            return i3 == 0 ? 1 : 0;
        } else if (i2 != 1) {
            if (i2 == 2) {
                if (i3 >= 31) {
                    return Integer.MAX_VALUE;
                }
                return 1 << i3;
            }
            int i4 = ((i2 >>> 31) & i3 & 1) + Integer.MAX_VALUE;
            int i5 = 1;
            while (i3 != 0) {
                if (i3 == 1) {
                    return saturatedMultiply(i5, i2);
                }
                if ((i3 & 1) != 0) {
                    i5 = saturatedMultiply(i5, i2);
                }
                i3 >>= 1;
                if (i3 > 0) {
                    if ((-46340 > i2) || (i2 > 46340)) {
                        return i4;
                    }
                    i2 *= i2;
                }
            }
            return i5;
        } else {
            return 1;
        }
    }

    @Beta
    public static int saturatedSubtract(int i2, int i3) {
        return Ints.saturatedCast(i2 - i3);
    }

    @GwtIncompatible
    public static int sqrt(int i2, RoundingMode roundingMode) {
        int i3;
        MathPreconditions.e("x", i2);
        int sqrtFloor = sqrtFloor(i2);
        switch (AnonymousClass1.f9325a[roundingMode.ordinal()]) {
            case 1:
                MathPreconditions.k(sqrtFloor * sqrtFloor == i2);
                return sqrtFloor;
            case 2:
            case 3:
                return sqrtFloor;
            case 4:
            case 5:
                i3 = sqrtFloor * sqrtFloor;
                return sqrtFloor + a(i3, i2);
            case 6:
            case 7:
            case 8:
                i3 = (sqrtFloor * sqrtFloor) + sqrtFloor;
                return sqrtFloor + a(i3, i2);
            default:
                throw new AssertionError();
        }
    }

    private static int sqrtFloor(int i2) {
        return (int) Math.sqrt(i2);
    }
}
