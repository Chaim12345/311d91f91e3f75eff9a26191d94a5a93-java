package org.slf4j.helpers;

import java.util.HashMap;
import java.util.Map;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
/* loaded from: classes4.dex */
public final class MessageFormatter {
    private static final char ESCAPE_CHAR = '\\';

    static final Throwable a(Object[] objArr) {
        if (objArr != null && objArr.length != 0) {
            Object obj = objArr[objArr.length - 1];
            if (obj instanceof Throwable) {
                return (Throwable) obj;
            }
        }
        return null;
    }

    public static final FormattingTuple arrayFormat(String str, Object[] objArr) {
        Throwable a2 = a(objArr);
        if (a2 != null) {
            objArr = trimmedCopy(objArr);
        }
        return arrayFormat(str, objArr, a2);
    }

    public static final FormattingTuple arrayFormat(String str, Object[] objArr, Throwable th) {
        Object obj;
        HashMap hashMap;
        int i2;
        if (str == null) {
            return new FormattingTuple(null, objArr, th);
        }
        if (objArr == null) {
            return new FormattingTuple(str);
        }
        StringBuilder sb = new StringBuilder(str.length() + 50);
        int i3 = 0;
        int i4 = 0;
        while (i3 < objArr.length) {
            int indexOf = str.indexOf("{}", i4);
            if (indexOf == -1) {
                if (i4 == 0) {
                    return new FormattingTuple(str, objArr, th);
                }
                sb.append((CharSequence) str, i4, str.length());
                return new FormattingTuple(sb.toString(), objArr, th);
            }
            if (!c(str, indexOf)) {
                sb.append((CharSequence) str, i4, indexOf);
                obj = objArr[i3];
                hashMap = new HashMap();
            } else if (b(str, indexOf)) {
                sb.append((CharSequence) str, i4, indexOf - 1);
                obj = objArr[i3];
                hashMap = new HashMap();
            } else {
                i3--;
                sb.append((CharSequence) str, i4, indexOf - 1);
                sb.append(AbstractJsonLexerKt.BEGIN_OBJ);
                i2 = indexOf + 1;
                i4 = i2;
                i3++;
            }
            deeplyAppendParameter(sb, obj, hashMap);
            i2 = indexOf + 2;
            i4 = i2;
            i3++;
        }
        sb.append((CharSequence) str, i4, str.length());
        return new FormattingTuple(sb.toString(), objArr, th);
    }

    static final boolean b(String str, int i2) {
        return i2 >= 2 && str.charAt(i2 - 2) == '\\';
    }

    private static void booleanArrayAppend(StringBuilder sb, boolean[] zArr) {
        sb.append(AbstractJsonLexerKt.BEGIN_LIST);
        int length = zArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            sb.append(zArr[i2]);
            if (i2 != length - 1) {
                sb.append(", ");
            }
        }
        sb.append(AbstractJsonLexerKt.END_LIST);
    }

    private static void byteArrayAppend(StringBuilder sb, byte[] bArr) {
        sb.append(AbstractJsonLexerKt.BEGIN_LIST);
        int length = bArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            sb.append((int) bArr[i2]);
            if (i2 != length - 1) {
                sb.append(", ");
            }
        }
        sb.append(AbstractJsonLexerKt.END_LIST);
    }

    static final boolean c(String str, int i2) {
        return i2 != 0 && str.charAt(i2 - 1) == '\\';
    }

    private static void charArrayAppend(StringBuilder sb, char[] cArr) {
        sb.append(AbstractJsonLexerKt.BEGIN_LIST);
        int length = cArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            sb.append(cArr[i2]);
            if (i2 != length - 1) {
                sb.append(", ");
            }
        }
        sb.append(AbstractJsonLexerKt.END_LIST);
    }

    private static void deeplyAppendParameter(StringBuilder sb, Object obj, Map<Object[], Object> map) {
        if (obj == null) {
            sb.append("null");
        } else if (!obj.getClass().isArray()) {
            safeObjectAppend(sb, obj);
        } else if (obj instanceof boolean[]) {
            booleanArrayAppend(sb, (boolean[]) obj);
        } else if (obj instanceof byte[]) {
            byteArrayAppend(sb, (byte[]) obj);
        } else if (obj instanceof char[]) {
            charArrayAppend(sb, (char[]) obj);
        } else if (obj instanceof short[]) {
            shortArrayAppend(sb, (short[]) obj);
        } else if (obj instanceof int[]) {
            intArrayAppend(sb, (int[]) obj);
        } else if (obj instanceof long[]) {
            longArrayAppend(sb, (long[]) obj);
        } else if (obj instanceof float[]) {
            floatArrayAppend(sb, (float[]) obj);
        } else if (obj instanceof double[]) {
            doubleArrayAppend(sb, (double[]) obj);
        } else {
            objectArrayAppend(sb, (Object[]) obj, map);
        }
    }

    private static void doubleArrayAppend(StringBuilder sb, double[] dArr) {
        sb.append(AbstractJsonLexerKt.BEGIN_LIST);
        int length = dArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            sb.append(dArr[i2]);
            if (i2 != length - 1) {
                sb.append(", ");
            }
        }
        sb.append(AbstractJsonLexerKt.END_LIST);
    }

    private static void floatArrayAppend(StringBuilder sb, float[] fArr) {
        sb.append(AbstractJsonLexerKt.BEGIN_LIST);
        int length = fArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            sb.append(fArr[i2]);
            if (i2 != length - 1) {
                sb.append(", ");
            }
        }
        sb.append(AbstractJsonLexerKt.END_LIST);
    }

    public static final FormattingTuple format(String str, Object obj) {
        return arrayFormat(str, new Object[]{obj});
    }

    public static final FormattingTuple format(String str, Object obj, Object obj2) {
        return arrayFormat(str, new Object[]{obj, obj2});
    }

    private static void intArrayAppend(StringBuilder sb, int[] iArr) {
        sb.append(AbstractJsonLexerKt.BEGIN_LIST);
        int length = iArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            sb.append(iArr[i2]);
            if (i2 != length - 1) {
                sb.append(", ");
            }
        }
        sb.append(AbstractJsonLexerKt.END_LIST);
    }

    private static void longArrayAppend(StringBuilder sb, long[] jArr) {
        sb.append(AbstractJsonLexerKt.BEGIN_LIST);
        int length = jArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            sb.append(jArr[i2]);
            if (i2 != length - 1) {
                sb.append(", ");
            }
        }
        sb.append(AbstractJsonLexerKt.END_LIST);
    }

    private static void objectArrayAppend(StringBuilder sb, Object[] objArr, Map<Object[], Object> map) {
        sb.append(AbstractJsonLexerKt.BEGIN_LIST);
        if (map.containsKey(objArr)) {
            sb.append("...");
        } else {
            map.put(objArr, null);
            int length = objArr.length;
            for (int i2 = 0; i2 < length; i2++) {
                deeplyAppendParameter(sb, objArr[i2], map);
                if (i2 != length - 1) {
                    sb.append(", ");
                }
            }
            map.remove(objArr);
        }
        sb.append(AbstractJsonLexerKt.END_LIST);
    }

    private static void safeObjectAppend(StringBuilder sb, Object obj) {
        try {
            sb.append(obj.toString());
        } catch (Throwable th) {
            Util.report("SLF4J: Failed toString() invocation on an object of type [" + obj.getClass().getName() + "]", th);
            sb.append("[FAILED toString()]");
        }
    }

    private static void shortArrayAppend(StringBuilder sb, short[] sArr) {
        sb.append(AbstractJsonLexerKt.BEGIN_LIST);
        int length = sArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            sb.append((int) sArr[i2]);
            if (i2 != length - 1) {
                sb.append(", ");
            }
        }
        sb.append(AbstractJsonLexerKt.END_LIST);
    }

    private static Object[] trimmedCopy(Object[] objArr) {
        if (objArr == null || objArr.length == 0) {
            throw new IllegalStateException("non-sensical empty or null argument array");
        }
        int length = objArr.length - 1;
        Object[] objArr2 = new Object[length];
        System.arraycopy(objArr, 0, objArr2, 0, length);
        return objArr2;
    }
}
