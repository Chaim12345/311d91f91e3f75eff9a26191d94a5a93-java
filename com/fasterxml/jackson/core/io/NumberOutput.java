package com.fasterxml.jackson.core.io;

import androidx.exifinterface.media.ExifInterface;
import com.facebook.stetho.dumpapp.Framer;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import org.apache.commons.codec.language.Soundex;
/* loaded from: classes.dex */
public final class NumberOutput {
    private static int BILLION = 1000000000;
    private static long BILLION_L = 1000000000;
    private static long MAX_INT_AS_LONG = 2147483647L;
    private static int MILLION = 1000000;
    private static long MIN_INT_AS_LONG = -2147483648L;
    private static final String[] sSmallIntStrs;
    private static final String[] sSmallIntStrs2;

    /* renamed from: a  reason: collision with root package name */
    static final String f5163a = String.valueOf(Integer.MIN_VALUE);

    /* renamed from: b  reason: collision with root package name */
    static final String f5164b = String.valueOf(Long.MIN_VALUE);
    private static final int[] TRIPLET_TO_CHARS = new int[1000];

    static {
        int i2 = 0;
        for (int i3 = 0; i3 < 10; i3++) {
            for (int i4 = 0; i4 < 10; i4++) {
                int i5 = 0;
                while (i5 < 10) {
                    TRIPLET_TO_CHARS[i2] = ((i3 + 48) << 16) | ((i4 + 48) << 8) | (i5 + 48);
                    i5++;
                    i2++;
                }
            }
        }
        sSmallIntStrs = new String[]{"0", "1", ExifInterface.GPS_MEASUREMENT_2D, ExifInterface.GPS_MEASUREMENT_3D, "4", "5", "6", "7", "8", "9", "10"};
        sSmallIntStrs2 = new String[]{AppConstants.DEFAULT_VAL_SPEED_DLG, "-2", "-3", "-4", "-5", "-6", "-7", "-8", "-9", "-10"};
    }

    private static int _full3(int i2, byte[] bArr, int i3) {
        int i4 = TRIPLET_TO_CHARS[i2];
        int i5 = i3 + 1;
        bArr[i3] = (byte) (i4 >> 16);
        int i6 = i5 + 1;
        bArr[i5] = (byte) (i4 >> 8);
        int i7 = i6 + 1;
        bArr[i6] = (byte) i4;
        return i7;
    }

    private static int _full3(int i2, char[] cArr, int i3) {
        int i4 = TRIPLET_TO_CHARS[i2];
        int i5 = i3 + 1;
        cArr[i3] = (char) (i4 >> 16);
        int i6 = i5 + 1;
        cArr[i5] = (char) ((i4 >> 8) & 127);
        int i7 = i6 + 1;
        cArr[i6] = (char) (i4 & 127);
        return i7;
    }

    private static int _leading3(int i2, byte[] bArr, int i3) {
        int i4 = TRIPLET_TO_CHARS[i2];
        if (i2 > 9) {
            if (i2 > 99) {
                bArr[i3] = (byte) (i4 >> 16);
                i3++;
            }
            bArr[i3] = (byte) (i4 >> 8);
            i3++;
        }
        int i5 = i3 + 1;
        bArr[i3] = (byte) i4;
        return i5;
    }

    private static int _leading3(int i2, char[] cArr, int i3) {
        int i4 = TRIPLET_TO_CHARS[i2];
        if (i2 > 9) {
            if (i2 > 99) {
                cArr[i3] = (char) (i4 >> 16);
                i3++;
            }
            cArr[i3] = (char) ((i4 >> 8) & 127);
            i3++;
        }
        int i5 = i3 + 1;
        cArr[i3] = (char) (i4 & 127);
        return i5;
    }

    private static int _outputFullBillion(int i2, byte[] bArr, int i3) {
        int i4 = i2 / 1000;
        int i5 = i2 - (i4 * 1000);
        int i6 = i4 / 1000;
        int i7 = i4 - (i6 * 1000);
        int[] iArr = TRIPLET_TO_CHARS;
        int i8 = iArr[i6];
        int i9 = i3 + 1;
        bArr[i3] = (byte) (i8 >> 16);
        int i10 = i9 + 1;
        bArr[i9] = (byte) (i8 >> 8);
        int i11 = i10 + 1;
        bArr[i10] = (byte) i8;
        int i12 = iArr[i7];
        int i13 = i11 + 1;
        bArr[i11] = (byte) (i12 >> 16);
        int i14 = i13 + 1;
        bArr[i13] = (byte) (i12 >> 8);
        int i15 = i14 + 1;
        bArr[i14] = (byte) i12;
        int i16 = iArr[i5];
        int i17 = i15 + 1;
        bArr[i15] = (byte) (i16 >> 16);
        int i18 = i17 + 1;
        bArr[i17] = (byte) (i16 >> 8);
        int i19 = i18 + 1;
        bArr[i18] = (byte) i16;
        return i19;
    }

    private static int _outputFullBillion(int i2, char[] cArr, int i3) {
        int i4 = i2 / 1000;
        int i5 = i2 - (i4 * 1000);
        int i6 = i4 / 1000;
        int[] iArr = TRIPLET_TO_CHARS;
        int i7 = iArr[i6];
        int i8 = i3 + 1;
        cArr[i3] = (char) (i7 >> 16);
        int i9 = i8 + 1;
        cArr[i8] = (char) ((i7 >> 8) & 127);
        int i10 = i9 + 1;
        cArr[i9] = (char) (i7 & 127);
        int i11 = iArr[i4 - (i6 * 1000)];
        int i12 = i10 + 1;
        cArr[i10] = (char) (i11 >> 16);
        int i13 = i12 + 1;
        cArr[i12] = (char) ((i11 >> 8) & 127);
        int i14 = i13 + 1;
        cArr[i13] = (char) (i11 & 127);
        int i15 = iArr[i5];
        int i16 = i14 + 1;
        cArr[i14] = (char) (i15 >> 16);
        int i17 = i16 + 1;
        cArr[i16] = (char) ((i15 >> 8) & 127);
        int i18 = i17 + 1;
        cArr[i17] = (char) (i15 & 127);
        return i18;
    }

    private static int _outputSmallestI(byte[] bArr, int i2) {
        int length = f5163a.length();
        int i3 = 0;
        while (i3 < length) {
            bArr[i2] = (byte) f5163a.charAt(i3);
            i3++;
            i2++;
        }
        return i2;
    }

    private static int _outputSmallestI(char[] cArr, int i2) {
        String str = f5163a;
        int length = str.length();
        str.getChars(0, length, cArr, i2);
        return i2 + length;
    }

    private static int _outputSmallestL(byte[] bArr, int i2) {
        int length = f5164b.length();
        int i3 = 0;
        while (i3 < length) {
            bArr[i2] = (byte) f5164b.charAt(i3);
            i3++;
            i2++;
        }
        return i2;
    }

    private static int _outputSmallestL(char[] cArr, int i2) {
        String str = f5164b;
        int length = str.length();
        str.getChars(0, length, cArr, i2);
        return i2 + length;
    }

    private static int _outputUptoBillion(int i2, byte[] bArr, int i3) {
        if (i2 < MILLION) {
            if (i2 < 1000) {
                return _leading3(i2, bArr, i3);
            }
            int i4 = i2 / 1000;
            return _outputUptoMillion(bArr, i3, i4, i2 - (i4 * 1000));
        }
        int i5 = i2 / 1000;
        int i6 = i2 - (i5 * 1000);
        int i7 = i5 / 1000;
        int _leading3 = _leading3(i7, bArr, i3);
        int[] iArr = TRIPLET_TO_CHARS;
        int i8 = iArr[i5 - (i7 * 1000)];
        int i9 = _leading3 + 1;
        bArr[_leading3] = (byte) (i8 >> 16);
        int i10 = i9 + 1;
        bArr[i9] = (byte) (i8 >> 8);
        int i11 = i10 + 1;
        bArr[i10] = (byte) i8;
        int i12 = iArr[i6];
        int i13 = i11 + 1;
        bArr[i11] = (byte) (i12 >> 16);
        int i14 = i13 + 1;
        bArr[i13] = (byte) (i12 >> 8);
        int i15 = i14 + 1;
        bArr[i14] = (byte) i12;
        return i15;
    }

    private static int _outputUptoBillion(int i2, char[] cArr, int i3) {
        if (i2 < MILLION) {
            if (i2 < 1000) {
                return _leading3(i2, cArr, i3);
            }
            int i4 = i2 / 1000;
            return _outputUptoMillion(cArr, i3, i4, i2 - (i4 * 1000));
        }
        int i5 = i2 / 1000;
        int i6 = i2 - (i5 * 1000);
        int i7 = i5 / 1000;
        int _leading3 = _leading3(i7, cArr, i3);
        int[] iArr = TRIPLET_TO_CHARS;
        int i8 = iArr[i5 - (i7 * 1000)];
        int i9 = _leading3 + 1;
        cArr[_leading3] = (char) (i8 >> 16);
        int i10 = i9 + 1;
        cArr[i9] = (char) ((i8 >> 8) & 127);
        int i11 = i10 + 1;
        cArr[i10] = (char) (i8 & 127);
        int i12 = iArr[i6];
        int i13 = i11 + 1;
        cArr[i11] = (char) (i12 >> 16);
        int i14 = i13 + 1;
        cArr[i13] = (char) ((i12 >> 8) & 127);
        int i15 = i14 + 1;
        cArr[i14] = (char) (i12 & 127);
        return i15;
    }

    private static int _outputUptoMillion(byte[] bArr, int i2, int i3, int i4) {
        int[] iArr = TRIPLET_TO_CHARS;
        int i5 = iArr[i3];
        if (i3 > 9) {
            if (i3 > 99) {
                bArr[i2] = (byte) (i5 >> 16);
                i2++;
            }
            bArr[i2] = (byte) (i5 >> 8);
            i2++;
        }
        int i6 = i2 + 1;
        bArr[i2] = (byte) i5;
        int i7 = iArr[i4];
        int i8 = i6 + 1;
        bArr[i6] = (byte) (i7 >> 16);
        int i9 = i8 + 1;
        bArr[i8] = (byte) (i7 >> 8);
        int i10 = i9 + 1;
        bArr[i9] = (byte) i7;
        return i10;
    }

    private static int _outputUptoMillion(char[] cArr, int i2, int i3, int i4) {
        int[] iArr = TRIPLET_TO_CHARS;
        int i5 = iArr[i3];
        if (i3 > 9) {
            if (i3 > 99) {
                cArr[i2] = (char) (i5 >> 16);
                i2++;
            }
            cArr[i2] = (char) ((i5 >> 8) & 127);
            i2++;
        }
        int i6 = i2 + 1;
        cArr[i2] = (char) (i5 & 127);
        int i7 = iArr[i4];
        int i8 = i6 + 1;
        cArr[i6] = (char) (i7 >> 16);
        int i9 = i8 + 1;
        cArr[i8] = (char) ((i7 >> 8) & 127);
        int i10 = i9 + 1;
        cArr[i9] = (char) (i7 & 127);
        return i10;
    }

    public static boolean notFinite(double d2) {
        return Double.isNaN(d2) || Double.isInfinite(d2);
    }

    public static boolean notFinite(float f2) {
        return Float.isNaN(f2) || Float.isInfinite(f2);
    }

    public static int outputInt(int i2, byte[] bArr, int i3) {
        int i4;
        if (i2 < 0) {
            if (i2 == Integer.MIN_VALUE) {
                return _outputSmallestI(bArr, i3);
            }
            bArr[i3] = Framer.STDIN_FRAME_PREFIX;
            i2 = -i2;
            i3++;
        }
        if (i2 < MILLION) {
            if (i2 >= 1000) {
                int i5 = i2 / 1000;
                return _full3(i2 - (i5 * 1000), bArr, _leading3(i5, bArr, i3));
            } else if (i2 < 10) {
                int i6 = i3 + 1;
                bArr[i3] = (byte) (i2 + 48);
                return i6;
            } else {
                return _leading3(i2, bArr, i3);
            }
        }
        int i7 = BILLION;
        if (i2 < i7) {
            int i8 = i2 / 1000;
            int i9 = i8 / 1000;
            return _full3(i2 - (i8 * 1000), bArr, _full3(i8 - (i9 * 1000), bArr, _leading3(i9, bArr, i3)));
        }
        int i10 = i2 - i7;
        if (i10 >= i7) {
            i10 -= i7;
            i4 = i3 + 1;
            bArr[i3] = Framer.STDERR_FRAME_PREFIX;
        } else {
            i4 = i3 + 1;
            bArr[i3] = Framer.STDOUT_FRAME_PREFIX;
        }
        return _outputFullBillion(i10, bArr, i4);
    }

    public static int outputInt(int i2, char[] cArr, int i3) {
        int i4;
        int _full3;
        int i5;
        if (i2 < 0) {
            if (i2 == Integer.MIN_VALUE) {
                return _outputSmallestI(cArr, i3);
            }
            cArr[i3] = Soundex.SILENT_MARKER;
            i2 = -i2;
            i3++;
        }
        if (i2 >= MILLION) {
            int i6 = BILLION;
            if (i2 >= i6) {
                int i7 = i2 - i6;
                if (i7 >= i6) {
                    i7 -= i6;
                    i5 = i3 + 1;
                    cArr[i3] = '2';
                } else {
                    i5 = i3 + 1;
                    cArr[i3] = '1';
                }
                return _outputFullBillion(i7, cArr, i5);
            }
            int i8 = i2 / 1000;
            i4 = i2 - (i8 * 1000);
            int i9 = i8 / 1000;
            _full3 = _full3(i8 - (i9 * 1000), cArr, _leading3(i9, cArr, i3));
        } else if (i2 < 1000) {
            if (i2 < 10) {
                cArr[i3] = (char) (i2 + 48);
                return i3 + 1;
            }
            return _leading3(i2, cArr, i3);
        } else {
            int i10 = i2 / 1000;
            i4 = i2 - (i10 * 1000);
            _full3 = _leading3(i10, cArr, i3);
        }
        return _full3(i4, cArr, _full3);
    }

    public static int outputLong(long j2, byte[] bArr, int i2) {
        int _outputFullBillion;
        if (j2 < 0) {
            if (j2 > MIN_INT_AS_LONG) {
                return outputInt((int) j2, bArr, i2);
            }
            if (j2 == Long.MIN_VALUE) {
                return _outputSmallestL(bArr, i2);
            }
            bArr[i2] = Framer.STDIN_FRAME_PREFIX;
            j2 = -j2;
            i2++;
        } else if (j2 <= MAX_INT_AS_LONG) {
            return outputInt((int) j2, bArr, i2);
        }
        long j3 = BILLION_L;
        long j4 = j2 / j3;
        long j5 = j2 - (j4 * j3);
        if (j4 < j3) {
            _outputFullBillion = _outputUptoBillion((int) j4, bArr, i2);
        } else {
            long j6 = j4 / j3;
            int _leading3 = _leading3((int) j6, bArr, i2);
            _outputFullBillion = _outputFullBillion((int) (j4 - (j3 * j6)), bArr, _leading3);
        }
        return _outputFullBillion((int) j5, bArr, _outputFullBillion);
    }

    public static int outputLong(long j2, char[] cArr, int i2) {
        int _outputFullBillion;
        if (j2 < 0) {
            if (j2 > MIN_INT_AS_LONG) {
                return outputInt((int) j2, cArr, i2);
            }
            if (j2 == Long.MIN_VALUE) {
                return _outputSmallestL(cArr, i2);
            }
            cArr[i2] = Soundex.SILENT_MARKER;
            j2 = -j2;
            i2++;
        } else if (j2 <= MAX_INT_AS_LONG) {
            return outputInt((int) j2, cArr, i2);
        }
        long j3 = BILLION_L;
        long j4 = j2 / j3;
        long j5 = j2 - (j4 * j3);
        if (j4 < j3) {
            _outputFullBillion = _outputUptoBillion((int) j4, cArr, i2);
        } else {
            long j6 = j4 / j3;
            int _leading3 = _leading3((int) j6, cArr, i2);
            _outputFullBillion = _outputFullBillion((int) (j4 - (j3 * j6)), cArr, _leading3);
        }
        return _outputFullBillion((int) j5, cArr, _outputFullBillion);
    }

    public static String toString(double d2) {
        return Double.toString(d2);
    }

    public static String toString(float f2) {
        return Float.toString(f2);
    }

    public static String toString(int i2) {
        String[] strArr = sSmallIntStrs;
        if (i2 < strArr.length) {
            if (i2 >= 0) {
                return strArr[i2];
            }
            int i3 = (-i2) - 1;
            String[] strArr2 = sSmallIntStrs2;
            if (i3 < strArr2.length) {
                return strArr2[i3];
            }
        }
        return Integer.toString(i2);
    }

    public static String toString(long j2) {
        return (j2 > 2147483647L || j2 < -2147483648L) ? Long.toString(j2) : toString((int) j2);
    }
}
