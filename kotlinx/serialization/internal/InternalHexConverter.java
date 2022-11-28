package kotlinx.serialization.internal;

import com.google.common.base.Ascii;
import java.util.Locale;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class InternalHexConverter {
    @NotNull
    public static final InternalHexConverter INSTANCE = new InternalHexConverter();
    @NotNull
    private static final String hexCode = "0123456789ABCDEF";

    private InternalHexConverter() {
    }

    private final int hexToInt(char c2) {
        boolean z = true;
        if ('0' <= c2 && c2 < ':') {
            return c2 - '0';
        }
        char c3 = 'A';
        if (!('A' <= c2 && c2 < 'G')) {
            c3 = 'a';
            if ('a' > c2 || c2 >= 'g') {
                z = false;
            }
            if (!z) {
                return -1;
            }
        }
        return (c2 - c3) + 10;
    }

    public static /* synthetic */ String printHexBinary$default(InternalHexConverter internalHexConverter, byte[] bArr, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        return internalHexConverter.printHexBinary(bArr, z);
    }

    @NotNull
    public final byte[] parseHexBinary(@NotNull String s2) {
        int i2;
        Intrinsics.checkNotNullParameter(s2, "s");
        int length = s2.length();
        if (length % 2 == 0) {
            byte[] bArr = new byte[length / 2];
            for (int i3 = 0; i3 < length; i3 += 2) {
                int hexToInt = hexToInt(s2.charAt(i3));
                int hexToInt2 = hexToInt(s2.charAt(i3 + 1));
                if (!((hexToInt == -1 || hexToInt2 == -1) ? false : true)) {
                    throw new IllegalArgumentException(("Invalid hex chars: " + s2.charAt(i3) + s2.charAt(i2)).toString());
                }
                bArr[i3 / 2] = (byte) ((hexToInt << 4) + hexToInt2);
            }
            return bArr;
        }
        throw new IllegalArgumentException("HexBinary string must be even length".toString());
    }

    @NotNull
    public final String printHexBinary(@NotNull byte[] data, boolean z) {
        Intrinsics.checkNotNullParameter(data, "data");
        StringBuilder sb = new StringBuilder(data.length * 2);
        int length = data.length;
        int i2 = 0;
        while (i2 < length) {
            byte b2 = data[i2];
            i2++;
            sb.append(hexCode.charAt((b2 >> 4) & 15));
            sb.append(hexCode.charAt(b2 & Ascii.SI));
        }
        if (!z) {
            String sb2 = sb.toString();
            Intrinsics.checkNotNullExpressionValue(sb2, "r.toString()");
            return sb2;
        }
        String sb3 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb3, "r.toString()");
        String lowerCase = sb3.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
        return lowerCase;
    }

    @NotNull
    public final String toHexString(int i2) {
        String trimStart;
        byte[] bArr = new byte[4];
        for (int i3 = 0; i3 < 4; i3++) {
            bArr[i3] = (byte) (i2 >> (24 - (i3 * 8)));
        }
        trimStart = StringsKt__StringsKt.trimStart(printHexBinary(bArr, true), '0');
        if (!(trimStart.length() > 0)) {
            trimStart = null;
        }
        return trimStart == null ? "0" : trimStart;
    }
}
