package androidx.camera.core.impl.utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.core.Logger;
import com.fasterxml.jackson.core.JsonPointer;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class ExifAttribute {
    public static final long BYTES_OFFSET_UNKNOWN = -1;
    private static final String TAG = "ExifAttribute";

    /* renamed from: a  reason: collision with root package name */
    static final Charset f1182a = StandardCharsets.US_ASCII;

    /* renamed from: b  reason: collision with root package name */
    static final String[] f1183b = {"", "BYTE", "STRING", "USHORT", "ULONG", "URATIONAL", "SBYTE", "UNDEFINED", "SSHORT", "SLONG", "SRATIONAL", "SINGLE", "DOUBLE", "IFD"};

    /* renamed from: c  reason: collision with root package name */
    static final int[] f1184c = {0, 1, 1, 2, 4, 8, 1, 1, 2, 4, 8, 4, 8, 1};

    /* renamed from: d  reason: collision with root package name */
    static final byte[] f1185d = {65, 83, 67, 73, 73, 0, 0, 0};
    public final byte[] bytes;
    public final long bytesOffset;
    public final int format;
    public final int numberOfComponents;

    ExifAttribute(int i2, int i3, long j2, byte[] bArr) {
        this.format = i2;
        this.numberOfComponents = i3;
        this.bytesOffset = j2;
        this.bytes = bArr;
    }

    ExifAttribute(int i2, int i3, byte[] bArr) {
        this(i2, i3, -1L, bArr);
    }

    @NonNull
    public static ExifAttribute createByte(@NonNull String str) {
        if (str.length() != 1 || str.charAt(0) < '0' || str.charAt(0) > '1') {
            byte[] bytes = str.getBytes(f1182a);
            return new ExifAttribute(1, bytes.length, bytes);
        }
        return new ExifAttribute(1, 1, new byte[]{(byte) (str.charAt(0) - '0')});
    }

    @NonNull
    public static ExifAttribute createDouble(double d2, @NonNull ByteOrder byteOrder) {
        return createDouble(new double[]{d2}, byteOrder);
    }

    @NonNull
    public static ExifAttribute createDouble(@NonNull double[] dArr, @NonNull ByteOrder byteOrder) {
        ByteBuffer wrap = ByteBuffer.wrap(new byte[f1184c[12] * dArr.length]);
        wrap.order(byteOrder);
        for (double d2 : dArr) {
            wrap.putDouble(d2);
        }
        return new ExifAttribute(12, dArr.length, wrap.array());
    }

    @NonNull
    public static ExifAttribute createSLong(int i2, @NonNull ByteOrder byteOrder) {
        return createSLong(new int[]{i2}, byteOrder);
    }

    @NonNull
    public static ExifAttribute createSLong(@NonNull int[] iArr, @NonNull ByteOrder byteOrder) {
        ByteBuffer wrap = ByteBuffer.wrap(new byte[f1184c[9] * iArr.length]);
        wrap.order(byteOrder);
        for (int i2 : iArr) {
            wrap.putInt(i2);
        }
        return new ExifAttribute(9, iArr.length, wrap.array());
    }

    @NonNull
    public static ExifAttribute createSRational(@NonNull LongRational longRational, @NonNull ByteOrder byteOrder) {
        return createSRational(new LongRational[]{longRational}, byteOrder);
    }

    @NonNull
    public static ExifAttribute createSRational(@NonNull LongRational[] longRationalArr, @NonNull ByteOrder byteOrder) {
        ByteBuffer wrap = ByteBuffer.wrap(new byte[f1184c[10] * longRationalArr.length]);
        wrap.order(byteOrder);
        for (LongRational longRational : longRationalArr) {
            wrap.putInt((int) longRational.b());
            wrap.putInt((int) longRational.a());
        }
        return new ExifAttribute(10, longRationalArr.length, wrap.array());
    }

    @NonNull
    public static ExifAttribute createString(@NonNull String str) {
        byte[] bytes = (str + (char) 0).getBytes(f1182a);
        return new ExifAttribute(2, bytes.length, bytes);
    }

    @NonNull
    public static ExifAttribute createULong(long j2, @NonNull ByteOrder byteOrder) {
        return createULong(new long[]{j2}, byteOrder);
    }

    @NonNull
    public static ExifAttribute createULong(@NonNull long[] jArr, @NonNull ByteOrder byteOrder) {
        ByteBuffer wrap = ByteBuffer.wrap(new byte[f1184c[4] * jArr.length]);
        wrap.order(byteOrder);
        for (long j2 : jArr) {
            wrap.putInt((int) j2);
        }
        return new ExifAttribute(4, jArr.length, wrap.array());
    }

    @NonNull
    public static ExifAttribute createURational(@NonNull LongRational longRational, @NonNull ByteOrder byteOrder) {
        return createURational(new LongRational[]{longRational}, byteOrder);
    }

    @NonNull
    public static ExifAttribute createURational(@NonNull LongRational[] longRationalArr, @NonNull ByteOrder byteOrder) {
        ByteBuffer wrap = ByteBuffer.wrap(new byte[f1184c[5] * longRationalArr.length]);
        wrap.order(byteOrder);
        for (LongRational longRational : longRationalArr) {
            wrap.putInt((int) longRational.b());
            wrap.putInt((int) longRational.a());
        }
        return new ExifAttribute(5, longRationalArr.length, wrap.array());
    }

    @NonNull
    public static ExifAttribute createUShort(int i2, @NonNull ByteOrder byteOrder) {
        return createUShort(new int[]{i2}, byteOrder);
    }

    @NonNull
    public static ExifAttribute createUShort(@NonNull int[] iArr, @NonNull ByteOrder byteOrder) {
        ByteBuffer wrap = ByteBuffer.wrap(new byte[f1184c[3] * iArr.length]);
        wrap.order(byteOrder);
        for (int i2 : iArr) {
            wrap.putShort((short) i2);
        }
        return new ExifAttribute(3, iArr.length, wrap.array());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Not initialized variable reg: 3, insn: 0x019c: MOVE  (r2 I:??[OBJECT, ARRAY]) = (r3 I:??[OBJECT, ARRAY]), block:B:152:0x019c */
    /* JADX WARN: Removed duplicated region for block: B:176:0x019f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object a(ByteOrder byteOrder) {
        ByteOrderedDataInputStream byteOrderedDataInputStream;
        InputStream inputStream;
        byte b2;
        byte[] bArr;
        InputStream inputStream2 = null;
        try {
            try {
                byteOrderedDataInputStream = new ByteOrderedDataInputStream(this.bytes);
                try {
                    byteOrderedDataInputStream.setByteOrder(byteOrder);
                    boolean z = true;
                    int i2 = 0;
                    switch (this.format) {
                        case 1:
                        case 6:
                            byte[] bArr2 = this.bytes;
                            if (bArr2.length != 1 || bArr2[0] < 0 || bArr2[0] > 1) {
                                String str = new String(bArr2, f1182a);
                                try {
                                    byteOrderedDataInputStream.close();
                                } catch (IOException e2) {
                                    Logger.e(TAG, "IOException occurred while closing InputStream", e2);
                                }
                                return str;
                            }
                            String str2 = new String(new char[]{(char) (bArr2[0] + 48)});
                            try {
                                byteOrderedDataInputStream.close();
                            } catch (IOException e3) {
                                Logger.e(TAG, "IOException occurred while closing InputStream", e3);
                            }
                            return str2;
                        case 2:
                        case 7:
                            if (this.numberOfComponents >= f1185d.length) {
                                int i3 = 0;
                                while (true) {
                                    bArr = f1185d;
                                    if (i3 < bArr.length) {
                                        if (this.bytes[i3] != bArr[i3]) {
                                            z = false;
                                        } else {
                                            i3++;
                                        }
                                    }
                                }
                                if (z) {
                                    i2 = bArr.length;
                                }
                            }
                            StringBuilder sb = new StringBuilder();
                            while (i2 < this.numberOfComponents && (b2 = this.bytes[i2]) != 0) {
                                if (b2 >= 32) {
                                    sb.append((char) b2);
                                } else {
                                    sb.append('?');
                                }
                                i2++;
                            }
                            String sb2 = sb.toString();
                            try {
                                byteOrderedDataInputStream.close();
                            } catch (IOException e4) {
                                Logger.e(TAG, "IOException occurred while closing InputStream", e4);
                            }
                            return sb2;
                        case 3:
                            int[] iArr = new int[this.numberOfComponents];
                            while (i2 < this.numberOfComponents) {
                                iArr[i2] = byteOrderedDataInputStream.readUnsignedShort();
                                i2++;
                            }
                            try {
                                byteOrderedDataInputStream.close();
                            } catch (IOException e5) {
                                Logger.e(TAG, "IOException occurred while closing InputStream", e5);
                            }
                            return iArr;
                        case 4:
                            long[] jArr = new long[this.numberOfComponents];
                            while (i2 < this.numberOfComponents) {
                                jArr[i2] = byteOrderedDataInputStream.readUnsignedInt();
                                i2++;
                            }
                            try {
                                byteOrderedDataInputStream.close();
                            } catch (IOException e6) {
                                Logger.e(TAG, "IOException occurred while closing InputStream", e6);
                            }
                            return jArr;
                        case 5:
                            LongRational[] longRationalArr = new LongRational[this.numberOfComponents];
                            while (i2 < this.numberOfComponents) {
                                longRationalArr[i2] = new LongRational(byteOrderedDataInputStream.readUnsignedInt(), byteOrderedDataInputStream.readUnsignedInt());
                                i2++;
                            }
                            try {
                                byteOrderedDataInputStream.close();
                            } catch (IOException e7) {
                                Logger.e(TAG, "IOException occurred while closing InputStream", e7);
                            }
                            return longRationalArr;
                        case 8:
                            int[] iArr2 = new int[this.numberOfComponents];
                            while (i2 < this.numberOfComponents) {
                                iArr2[i2] = byteOrderedDataInputStream.readShort();
                                i2++;
                            }
                            try {
                                byteOrderedDataInputStream.close();
                            } catch (IOException e8) {
                                Logger.e(TAG, "IOException occurred while closing InputStream", e8);
                            }
                            return iArr2;
                        case 9:
                            int[] iArr3 = new int[this.numberOfComponents];
                            while (i2 < this.numberOfComponents) {
                                iArr3[i2] = byteOrderedDataInputStream.readInt();
                                i2++;
                            }
                            try {
                                byteOrderedDataInputStream.close();
                            } catch (IOException e9) {
                                Logger.e(TAG, "IOException occurred while closing InputStream", e9);
                            }
                            return iArr3;
                        case 10:
                            LongRational[] longRationalArr2 = new LongRational[this.numberOfComponents];
                            while (i2 < this.numberOfComponents) {
                                longRationalArr2[i2] = new LongRational(byteOrderedDataInputStream.readInt(), byteOrderedDataInputStream.readInt());
                                i2++;
                            }
                            try {
                                byteOrderedDataInputStream.close();
                            } catch (IOException e10) {
                                Logger.e(TAG, "IOException occurred while closing InputStream", e10);
                            }
                            return longRationalArr2;
                        case 11:
                            double[] dArr = new double[this.numberOfComponents];
                            while (i2 < this.numberOfComponents) {
                                dArr[i2] = byteOrderedDataInputStream.readFloat();
                                i2++;
                            }
                            try {
                                byteOrderedDataInputStream.close();
                            } catch (IOException e11) {
                                Logger.e(TAG, "IOException occurred while closing InputStream", e11);
                            }
                            return dArr;
                        case 12:
                            double[] dArr2 = new double[this.numberOfComponents];
                            while (i2 < this.numberOfComponents) {
                                dArr2[i2] = byteOrderedDataInputStream.readDouble();
                                i2++;
                            }
                            try {
                                byteOrderedDataInputStream.close();
                            } catch (IOException e12) {
                                Logger.e(TAG, "IOException occurred while closing InputStream", e12);
                            }
                            return dArr2;
                        default:
                            try {
                                byteOrderedDataInputStream.close();
                            } catch (IOException e13) {
                                Logger.e(TAG, "IOException occurred while closing InputStream", e13);
                            }
                            return null;
                    }
                } catch (IOException e14) {
                    e = e14;
                    Logger.w(TAG, "IOException occurred during reading a value", e);
                    if (byteOrderedDataInputStream != null) {
                        try {
                            byteOrderedDataInputStream.close();
                        } catch (IOException e15) {
                            Logger.e(TAG, "IOException occurred while closing InputStream", e15);
                        }
                    }
                    return null;
                }
            } catch (Throwable th) {
                th = th;
                inputStream2 = inputStream;
                if (inputStream2 != null) {
                    try {
                        inputStream2.close();
                    } catch (IOException e16) {
                        Logger.e(TAG, "IOException occurred while closing InputStream", e16);
                    }
                }
                throw th;
            }
        } catch (IOException e17) {
            e = e17;
            byteOrderedDataInputStream = null;
        } catch (Throwable th2) {
            th = th2;
            if (inputStream2 != null) {
            }
            throw th;
        }
    }

    public double getDoubleValue(@NonNull ByteOrder byteOrder) {
        Object a2 = a(byteOrder);
        if (a2 != null) {
            if (a2 instanceof String) {
                return Double.parseDouble((String) a2);
            }
            if (a2 instanceof long[]) {
                long[] jArr = (long[]) a2;
                if (jArr.length == 1) {
                    return jArr[0];
                }
                throw new NumberFormatException("There are more than one component");
            } else if (a2 instanceof int[]) {
                int[] iArr = (int[]) a2;
                if (iArr.length == 1) {
                    return iArr[0];
                }
                throw new NumberFormatException("There are more than one component");
            } else if (a2 instanceof double[]) {
                double[] dArr = (double[]) a2;
                if (dArr.length == 1) {
                    return dArr[0];
                }
                throw new NumberFormatException("There are more than one component");
            } else if (a2 instanceof LongRational[]) {
                LongRational[] longRationalArr = (LongRational[]) a2;
                if (longRationalArr.length == 1) {
                    return longRationalArr[0].c();
                }
                throw new NumberFormatException("There are more than one component");
            } else {
                throw new NumberFormatException("Couldn't find a double value");
            }
        }
        throw new NumberFormatException("NULL can't be converted to a double value");
    }

    public int getIntValue(@NonNull ByteOrder byteOrder) {
        Object a2 = a(byteOrder);
        if (a2 != null) {
            if (a2 instanceof String) {
                return Integer.parseInt((String) a2);
            }
            if (a2 instanceof long[]) {
                long[] jArr = (long[]) a2;
                if (jArr.length == 1) {
                    return (int) jArr[0];
                }
                throw new NumberFormatException("There are more than one component");
            } else if (a2 instanceof int[]) {
                int[] iArr = (int[]) a2;
                if (iArr.length == 1) {
                    return iArr[0];
                }
                throw new NumberFormatException("There are more than one component");
            } else {
                throw new NumberFormatException("Couldn't find a integer value");
            }
        }
        throw new NumberFormatException("NULL can't be converted to a integer value");
    }

    @Nullable
    public String getStringValue(@NonNull ByteOrder byteOrder) {
        Object a2 = a(byteOrder);
        if (a2 == null) {
            return null;
        }
        if (a2 instanceof String) {
            return (String) a2;
        }
        StringBuilder sb = new StringBuilder();
        int i2 = 0;
        if (a2 instanceof long[]) {
            long[] jArr = (long[]) a2;
            while (i2 < jArr.length) {
                sb.append(jArr[i2]);
                i2++;
                if (i2 != jArr.length) {
                    sb.append(",");
                }
            }
            return sb.toString();
        } else if (a2 instanceof int[]) {
            int[] iArr = (int[]) a2;
            while (i2 < iArr.length) {
                sb.append(iArr[i2]);
                i2++;
                if (i2 != iArr.length) {
                    sb.append(",");
                }
            }
            return sb.toString();
        } else if (a2 instanceof double[]) {
            double[] dArr = (double[]) a2;
            while (i2 < dArr.length) {
                sb.append(dArr[i2]);
                i2++;
                if (i2 != dArr.length) {
                    sb.append(",");
                }
            }
            return sb.toString();
        } else if (a2 instanceof LongRational[]) {
            LongRational[] longRationalArr = (LongRational[]) a2;
            while (i2 < longRationalArr.length) {
                sb.append(longRationalArr[i2].b());
                sb.append(JsonPointer.SEPARATOR);
                sb.append(longRationalArr[i2].a());
                i2++;
                if (i2 != longRationalArr.length) {
                    sb.append(",");
                }
            }
            return sb.toString();
        } else {
            return null;
        }
    }

    public int size() {
        return f1184c[this.format] * this.numberOfComponents;
    }

    public String toString() {
        return "(" + f1183b[this.format] + ", data length:" + this.bytes.length + ")";
    }
}
