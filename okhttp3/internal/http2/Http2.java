package okhttp3.internal.http2;

import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import okhttp3.internal.Util;
import okio.ByteString;
import org.apache.http.message.TokenParser;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class Http2 {
    @NotNull
    private static final String[] BINARY;
    public static final int FLAG_ACK = 1;
    public static final int FLAG_COMPRESSED = 32;
    public static final int FLAG_END_HEADERS = 4;
    public static final int FLAG_END_PUSH_PROMISE = 4;
    public static final int FLAG_END_STREAM = 1;
    public static final int FLAG_NONE = 0;
    public static final int FLAG_PADDED = 8;
    public static final int FLAG_PRIORITY = 32;
    public static final int INITIAL_MAX_FRAME_SIZE = 16384;
    public static final int TYPE_CONTINUATION = 9;
    public static final int TYPE_DATA = 0;
    public static final int TYPE_GOAWAY = 7;
    public static final int TYPE_HEADERS = 1;
    public static final int TYPE_PING = 6;
    public static final int TYPE_PRIORITY = 2;
    public static final int TYPE_PUSH_PROMISE = 5;
    public static final int TYPE_RST_STREAM = 3;
    public static final int TYPE_SETTINGS = 4;
    public static final int TYPE_WINDOW_UPDATE = 8;
    @NotNull
    public static final Http2 INSTANCE = new Http2();
    @JvmField
    @NotNull
    public static final ByteString CONNECTION_PREFACE = ByteString.Companion.encodeUtf8("PRI * HTTP/2.0\r\n\r\nSM\r\n\r\n");
    @NotNull
    private static final String[] FRAME_NAMES = {"DATA", "HEADERS", "PRIORITY", "RST_STREAM", "SETTINGS", "PUSH_PROMISE", "PING", "GOAWAY", "WINDOW_UPDATE", "CONTINUATION"};
    @NotNull
    private static final String[] FLAGS = new String[64];

    static {
        String replace$default;
        String[] strArr = new String[256];
        int i2 = 0;
        for (int i3 = 0; i3 < 256; i3++) {
            String binaryString = Integer.toBinaryString(i3);
            Intrinsics.checkNotNullExpressionValue(binaryString, "toBinaryString(it)");
            replace$default = StringsKt__StringsJVMKt.replace$default(Util.format("%8s", binaryString), (char) TokenParser.SP, '0', false, 4, (Object) null);
            strArr[i3] = replace$default;
        }
        BINARY = strArr;
        String[] strArr2 = FLAGS;
        strArr2[0] = "";
        strArr2[1] = "END_STREAM";
        int[] iArr = {1};
        strArr2[8] = "PADDED";
        int i4 = 0;
        while (i4 < 1) {
            int i5 = iArr[i4];
            i4++;
            String[] strArr3 = FLAGS;
            strArr3[i5 | 8] = Intrinsics.stringPlus(strArr3[i5], "|PADDED");
        }
        String[] strArr4 = FLAGS;
        strArr4[4] = "END_HEADERS";
        strArr4[32] = "PRIORITY";
        strArr4[36] = "END_HEADERS|PRIORITY";
        int[] iArr2 = {4, 32, 36};
        int i6 = 0;
        while (i6 < 3) {
            int i7 = iArr2[i6];
            i6++;
            int i8 = 0;
            while (i8 < 1) {
                int i9 = iArr[i8];
                i8++;
                String[] strArr5 = FLAGS;
                int i10 = i9 | i7;
                StringBuilder sb = new StringBuilder();
                sb.append((Object) strArr5[i9]);
                sb.append('|');
                sb.append((Object) strArr5[i7]);
                strArr5[i10] = sb.toString();
                strArr5[i10 | 8] = ((Object) strArr5[i9]) + '|' + ((Object) strArr5[i7]) + "|PADDED";
            }
        }
        int length = FLAGS.length;
        while (i2 < length) {
            int i11 = i2 + 1;
            String[] strArr6 = FLAGS;
            if (strArr6[i2] == null) {
                strArr6[i2] = BINARY[i2];
            }
            i2 = i11;
        }
    }

    private Http2() {
    }

    @NotNull
    public final String formatFlags(int i2, int i3) {
        String str;
        boolean z;
        int i4;
        Object obj;
        String str2;
        String str3;
        String replace$default;
        if (i3 == 0) {
            return "";
        }
        if (i2 != 2 && i2 != 3) {
            if (i2 == 4 || i2 == 6) {
                return i3 == 1 ? "ACK" : BINARY[i3];
            } else if (i2 != 7 && i2 != 8) {
                String[] strArr = FLAGS;
                if (i3 < strArr.length) {
                    str = strArr[i3];
                    Intrinsics.checkNotNull(str);
                } else {
                    str = BINARY[i3];
                }
                String str4 = str;
                if (i2 == 5 && (i3 & 4) != 0) {
                    z = false;
                    i4 = 4;
                    obj = null;
                    str2 = "HEADERS";
                    str3 = "PUSH_PROMISE";
                } else if (i2 != 0 || (i3 & 32) == 0) {
                    return str4;
                } else {
                    z = false;
                    i4 = 4;
                    obj = null;
                    str2 = "PRIORITY";
                    str3 = "COMPRESSED";
                }
                replace$default = StringsKt__StringsJVMKt.replace$default(str4, str2, str3, z, i4, obj);
                return replace$default;
            }
        }
        return BINARY[i3];
    }

    @NotNull
    public final String formattedType$okhttp(int i2) {
        String[] strArr = FRAME_NAMES;
        return i2 < strArr.length ? strArr[i2] : Util.format("0x%02x", Integer.valueOf(i2));
    }

    @NotNull
    public final String frameLog(boolean z, int i2, int i3, int i4, int i5) {
        return Util.format("%s 0x%08x %5d %-13s %s", z ? "<<" : ">>", Integer.valueOf(i2), Integer.valueOf(i3), formattedType$okhttp(i4), formatFlags(i4, i5));
    }
}
