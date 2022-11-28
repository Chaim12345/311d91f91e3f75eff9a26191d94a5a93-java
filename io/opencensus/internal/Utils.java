package io.opencensus.internal;

import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
/* loaded from: classes3.dex */
public final class Utils {
    private Utils() {
    }

    public static void checkArgument(boolean z, @Nullable Object obj) {
        if (!z) {
            throw new IllegalArgumentException(String.valueOf(obj));
        }
    }

    public static void checkArgument(boolean z, String str, @Nullable Object... objArr) {
        if (!z) {
            throw new IllegalArgumentException(format(str, objArr));
        }
    }

    public static void checkIndex(int i2, int i3) {
        if (i3 < 0) {
            throw new IllegalArgumentException("Negative size: " + i3);
        } else if (i2 < 0 || i2 >= i3) {
            throw new IndexOutOfBoundsException("Index out of bounds: size=" + i3 + ", index=" + i2);
        }
    }

    public static <T> void checkListElementNotNull(List<T> list, @Nullable Object obj) {
        for (T t2 : list) {
            if (t2 == null) {
                throw new NullPointerException(String.valueOf(obj));
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:5:0x000e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static <K, V> void checkMapElementNotNull(Map<K, V> map, @Nullable Object obj) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (entry.getKey() == null || entry.getValue() == null) {
                throw new NullPointerException(String.valueOf(obj));
            }
            while (r2.hasNext()) {
            }
        }
    }

    public static <T> T checkNotNull(T t2, @Nullable Object obj) {
        if (t2 != null) {
            return t2;
        }
        throw new NullPointerException(String.valueOf(obj));
    }

    public static void checkState(boolean z, @Nullable Object obj) {
        if (!z) {
            throw new IllegalStateException(String.valueOf(obj));
        }
    }

    public static boolean equalsObjects(@Nullable Object obj, @Nullable Object obj2) {
        return obj == null ? obj2 == null : obj.equals(obj2);
    }

    private static String format(String str, @Nullable Object... objArr) {
        int indexOf;
        if (objArr == null) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str.length() + (objArr.length * 16));
        int i2 = 0;
        int i3 = 0;
        while (i2 < objArr.length && (indexOf = str.indexOf("%s", i3)) != -1) {
            sb.append((CharSequence) str, i3, indexOf);
            sb.append(objArr[i2]);
            i3 = indexOf + 2;
            i2++;
        }
        sb.append((CharSequence) str, i3, str.length());
        if (i2 < objArr.length) {
            sb.append(" [");
            sb.append(objArr[i2]);
            for (int i4 = i2 + 1; i4 < objArr.length; i4++) {
                sb.append(", ");
                sb.append(objArr[i4]);
            }
            sb.append(AbstractJsonLexerKt.END_LIST);
        }
        return sb.toString();
    }
}
