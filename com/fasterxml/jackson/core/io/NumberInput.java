package com.fasterxml.jackson.core.io;

import com.google.android.gms.dynamite.descriptors.com.google.mlkit.dynamite.barcode.ModuleDescriptor;
import java.math.BigDecimal;
import kotlin.jvm.internal.LongCompanionObject;
import kotlin.time.DurationKt;
/* loaded from: classes.dex */
public final class NumberInput {
    public static final String NASTY_SMALL_DOUBLE = "2.2250738585072012e-308";

    /* renamed from: a  reason: collision with root package name */
    static final String f5161a = String.valueOf(Long.MIN_VALUE).substring(1);

    /* renamed from: b  reason: collision with root package name */
    static final String f5162b = String.valueOf((long) LongCompanionObject.MAX_VALUE);

    private static NumberFormatException _badBD(String str) {
        return new NumberFormatException("Value \"" + str + "\" can not be represented as BigDecimal");
    }

    public static boolean inLongRange(String str, boolean z) {
        String str2 = z ? f5161a : f5162b;
        int length = str2.length();
        int length2 = str.length();
        if (length2 < length) {
            return true;
        }
        if (length2 > length) {
            return false;
        }
        for (int i2 = 0; i2 < length; i2++) {
            int charAt = str.charAt(i2) - str2.charAt(i2);
            if (charAt != 0) {
                return charAt < 0;
            }
        }
        return true;
    }

    public static boolean inLongRange(char[] cArr, int i2, int i3, boolean z) {
        String str = z ? f5161a : f5162b;
        int length = str.length();
        if (i3 < length) {
            return true;
        }
        if (i3 > length) {
            return false;
        }
        for (int i4 = 0; i4 < length; i4++) {
            int charAt = cArr[i2 + i4] - str.charAt(i4);
            if (charAt != 0) {
                return charAt < 0;
            }
        }
        return true;
    }

    public static double parseAsDouble(String str, double d2) {
        if (str == null) {
            return d2;
        }
        String trim = str.trim();
        if (trim.length() == 0) {
            return d2;
        }
        try {
            return parseDouble(trim);
        } catch (NumberFormatException unused) {
            return d2;
        }
    }

    public static int parseAsInt(String str, int i2) {
        String trim;
        int length;
        if (str == null || (length = (trim = str.trim()).length()) == 0) {
            return i2;
        }
        int i3 = 0;
        if (length > 0) {
            char charAt = trim.charAt(0);
            if (charAt == '+') {
                trim = trim.substring(1);
                length = trim.length();
            } else if (charAt == '-') {
                i3 = 1;
            }
        }
        while (i3 < length) {
            char charAt2 = trim.charAt(i3);
            if (charAt2 > '9' || charAt2 < '0') {
                try {
                    return (int) parseDouble(trim);
                } catch (NumberFormatException unused) {
                    return i2;
                }
            }
            i3++;
        }
        try {
            return Integer.parseInt(trim);
        } catch (NumberFormatException unused2) {
            return i2;
        }
    }

    public static long parseAsLong(String str, long j2) {
        String trim;
        int length;
        if (str == null || (length = (trim = str.trim()).length()) == 0) {
            return j2;
        }
        int i2 = 0;
        if (length > 0) {
            char charAt = trim.charAt(0);
            if (charAt == '+') {
                trim = trim.substring(1);
                length = trim.length();
            } else if (charAt == '-') {
                i2 = 1;
            }
        }
        while (i2 < length) {
            char charAt2 = trim.charAt(i2);
            if (charAt2 > '9' || charAt2 < '0') {
                try {
                    return (long) parseDouble(trim);
                } catch (NumberFormatException unused) {
                    return j2;
                }
            }
            i2++;
        }
        try {
            return Long.parseLong(trim);
        } catch (NumberFormatException unused2) {
            return j2;
        }
    }

    public static BigDecimal parseBigDecimal(String str) {
        try {
            return new BigDecimal(str);
        } catch (NumberFormatException unused) {
            throw _badBD(str);
        }
    }

    public static BigDecimal parseBigDecimal(char[] cArr) {
        return parseBigDecimal(cArr, 0, cArr.length);
    }

    public static BigDecimal parseBigDecimal(char[] cArr, int i2, int i3) {
        try {
            return new BigDecimal(cArr, i2, i3);
        } catch (NumberFormatException unused) {
            throw _badBD(new String(cArr, i2, i3));
        }
    }

    public static double parseDouble(String str) {
        if (NASTY_SMALL_DOUBLE.equals(str)) {
            return Double.MIN_VALUE;
        }
        return Double.parseDouble(str);
    }

    /* JADX WARN: Code restructure failed: missing block: B:45:0x0075, code lost:
        return java.lang.Integer.parseInt(r9);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static int parseInt(String str) {
        char charAt = str.charAt(0);
        int length = str.length();
        int i2 = 1;
        boolean z = charAt == '-';
        if (z) {
            if (length == 1 || length > 10) {
                return Integer.parseInt(str);
            }
            i2 = 2;
            charAt = str.charAt(1);
        } else if (length > 9) {
            return Integer.parseInt(str);
        }
        if (charAt > '9' || charAt < '0') {
            return Integer.parseInt(str);
        }
        int i3 = charAt - '0';
        if (i2 < length) {
            int i4 = i2 + 1;
            char charAt2 = str.charAt(i2);
            if (charAt2 > '9' || charAt2 < '0') {
                return Integer.parseInt(str);
            }
            i3 = (i3 * 10) + (charAt2 - '0');
            if (i4 < length) {
                int i5 = i4 + 1;
                char charAt3 = str.charAt(i4);
                if (charAt3 > '9' || charAt3 < '0') {
                    return Integer.parseInt(str);
                }
                i3 = (i3 * 10) + (charAt3 - '0');
                if (i5 < length) {
                    while (true) {
                        int i6 = i5 + 1;
                        char charAt4 = str.charAt(i5);
                        if (charAt4 > '9' || charAt4 < '0') {
                            break;
                        }
                        i3 = (i3 * 10) + (charAt4 - '0');
                        if (i6 >= length) {
                            break;
                        }
                        i5 = i6;
                    }
                }
            }
        }
        return z ? -i3 : i3;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static int parseInt(char[] cArr, int i2, int i3) {
        int i4 = cArr[(i2 + i3) - 1] - '0';
        switch (i3) {
            case 2:
                break;
            case 3:
                i4 += (cArr[i2] - '0') * 100;
                i2++;
                break;
            case 4:
                i4 += (cArr[i2] - '0') * 1000;
                i2++;
                i4 += (cArr[i2] - '0') * 100;
                i2++;
                break;
            case 5:
                i4 += (cArr[i2] - '0') * ModuleDescriptor.MODULE_VERSION;
                i2++;
                i4 += (cArr[i2] - '0') * 1000;
                i2++;
                i4 += (cArr[i2] - '0') * 100;
                i2++;
                break;
            case 6:
                i4 += (cArr[i2] - '0') * 100000;
                i2++;
                i4 += (cArr[i2] - '0') * ModuleDescriptor.MODULE_VERSION;
                i2++;
                i4 += (cArr[i2] - '0') * 1000;
                i2++;
                i4 += (cArr[i2] - '0') * 100;
                i2++;
                break;
            case 7:
                i4 += (cArr[i2] - '0') * DurationKt.NANOS_IN_MILLIS;
                i2++;
                i4 += (cArr[i2] - '0') * 100000;
                i2++;
                i4 += (cArr[i2] - '0') * ModuleDescriptor.MODULE_VERSION;
                i2++;
                i4 += (cArr[i2] - '0') * 1000;
                i2++;
                i4 += (cArr[i2] - '0') * 100;
                i2++;
                break;
            case 8:
                i4 += (cArr[i2] - '0') * 10000000;
                i2++;
                i4 += (cArr[i2] - '0') * DurationKt.NANOS_IN_MILLIS;
                i2++;
                i4 += (cArr[i2] - '0') * 100000;
                i2++;
                i4 += (cArr[i2] - '0') * ModuleDescriptor.MODULE_VERSION;
                i2++;
                i4 += (cArr[i2] - '0') * 1000;
                i2++;
                i4 += (cArr[i2] - '0') * 100;
                i2++;
                break;
            case 9:
                i4 += (cArr[i2] - '0') * 100000000;
                i2++;
                i4 += (cArr[i2] - '0') * 10000000;
                i2++;
                i4 += (cArr[i2] - '0') * DurationKt.NANOS_IN_MILLIS;
                i2++;
                i4 += (cArr[i2] - '0') * 100000;
                i2++;
                i4 += (cArr[i2] - '0') * ModuleDescriptor.MODULE_VERSION;
                i2++;
                i4 += (cArr[i2] - '0') * 1000;
                i2++;
                i4 += (cArr[i2] - '0') * 100;
                i2++;
                break;
            default:
                return i4;
        }
        return i4 + ((cArr[i2] - '0') * 10);
    }

    public static long parseLong(String str) {
        return str.length() <= 9 ? parseInt(str) : Long.parseLong(str);
    }

    public static long parseLong(char[] cArr, int i2, int i3) {
        int i4 = i3 - 9;
        return (parseInt(cArr, i2, i4) * 1000000000) + parseInt(cArr, i2 + i4, 9);
    }
}
