package kotlinx.serialization.json.internal;

import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class StringOpsKt {
    @NotNull
    private static final byte[] ESCAPE_MARKERS;
    @NotNull
    private static final String[] ESCAPE_STRINGS;

    static {
        String[] strArr = new String[93];
        for (int i2 = 0; i2 < 32; i2++) {
            strArr[i2] = "\\u" + toHexChar(i2 >> 12) + toHexChar(i2 >> 8) + toHexChar(i2 >> 4) + toHexChar(i2);
        }
        strArr[34] = "\\\"";
        strArr[92] = "\\\\";
        strArr[9] = "\\t";
        strArr[8] = "\\b";
        strArr[10] = "\\n";
        strArr[13] = "\\r";
        strArr[12] = "\\f";
        ESCAPE_STRINGS = strArr;
        byte[] bArr = new byte[93];
        for (int i3 = 0; i3 < 32; i3++) {
            bArr[i3] = 1;
        }
        bArr[34] = (byte) 34;
        bArr[92] = (byte) 92;
        bArr[9] = (byte) 116;
        bArr[8] = (byte) 98;
        bArr[10] = (byte) 110;
        bArr[13] = (byte) 114;
        bArr[12] = (byte) 102;
        ESCAPE_MARKERS = bArr;
    }

    @NotNull
    public static final byte[] getESCAPE_MARKERS() {
        return ESCAPE_MARKERS;
    }

    public static /* synthetic */ void getESCAPE_MARKERS$annotations() {
    }

    @NotNull
    public static final String[] getESCAPE_STRINGS() {
        return ESCAPE_STRINGS;
    }

    public static /* synthetic */ void getESCAPE_STRINGS$annotations() {
    }

    public static final void printQuoted(@NotNull StringBuilder sb, @NotNull String value) {
        int i2;
        int i3;
        char charAt;
        String[] strArr;
        Intrinsics.checkNotNullParameter(sb, "<this>");
        Intrinsics.checkNotNullParameter(value, "value");
        sb.append('\"');
        int length = value.length();
        int i4 = 0;
        loop0: while (true) {
            i2 = i4;
            while (i4 < length) {
                i3 = i4 + 1;
                charAt = value.charAt(i4);
                strArr = ESCAPE_STRINGS;
                if (charAt >= strArr.length || strArr[charAt] == null) {
                    i4 = i3;
                }
            }
            sb.append((CharSequence) value, i2, i4);
            sb.append(strArr[charAt]);
            i4 = i3;
        }
        if (i2 != 0) {
            sb.append((CharSequence) value, i2, value.length());
        } else {
            sb.append(value);
        }
        sb.append('\"');
    }

    @Nullable
    public static final Boolean toBooleanStrictOrNull(@NotNull String str) {
        boolean equals;
        boolean equals2;
        Intrinsics.checkNotNullParameter(str, "<this>");
        equals = StringsKt__StringsJVMKt.equals(str, "true", true);
        if (equals) {
            return Boolean.TRUE;
        }
        equals2 = StringsKt__StringsJVMKt.equals(str, "false", true);
        if (equals2) {
            return Boolean.FALSE;
        }
        return null;
    }

    private static final char toHexChar(int i2) {
        int i3 = i2 & 15;
        return (char) (i3 < 10 ? i3 + 48 : (i3 - 10) + 97);
    }
}
