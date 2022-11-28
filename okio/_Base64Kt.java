package okio;

import com.google.common.base.Ascii;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0010\u000e\n\u0002\u0010\u0012\n\u0002\b\r\u001a\u000e\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\u0000\u001a\u0016\u0010\u0004\u001a\u00020\u0000*\u00020\u00012\b\b\u0002\u0010\u0003\u001a\u00020\u0001H\u0000\"\"\u0010\u0005\u001a\u00020\u00018\u0000@\u0001X\u0081\u0004¢\u0006\u0012\n\u0004\b\u0005\u0010\u0006\u0012\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b\"\"\u0010\u000b\u001a\u00020\u00018\u0000@\u0001X\u0081\u0004¢\u0006\u0012\n\u0004\b\u000b\u0010\u0006\u0012\u0004\b\r\u0010\n\u001a\u0004\b\f\u0010\b¨\u0006\u000e"}, d2 = {"", "", "decodeBase64ToArray", "map", "encodeBase64", "BASE64", "[B", "getBASE64", "()[B", "getBASE64$annotations", "()V", "BASE64_URL_SAFE", "getBASE64_URL_SAFE", "getBASE64_URL_SAFE$annotations", "okio"}, k = 2, mv = {1, 5, 1})
/* loaded from: classes3.dex */
public final class _Base64Kt {
    @NotNull
    private static final byte[] BASE64;
    @NotNull
    private static final byte[] BASE64_URL_SAFE;

    static {
        ByteString.Companion companion = ByteString.Companion;
        BASE64 = companion.encodeUtf8("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/").getData$okio();
        BASE64_URL_SAFE = companion.encodeUtf8("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_").getData$okio();
    }

    /* JADX WARN: Removed duplicated region for block: B:63:0x00b7 A[LOOP:1: B:16:0x003d->B:63:0x00b7, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:87:0x00b3 A[SYNTHETIC] */
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final byte[] decodeBase64ToArray(@NotNull String str) {
        int i2;
        int i3;
        int i4;
        int i5;
        char charAt;
        Intrinsics.checkNotNullParameter(str, "<this>");
        int length = str.length();
        while (length > 0 && ((charAt = str.charAt(length - 1)) == '=' || charAt == '\n' || charAt == '\r' || charAt == ' ' || charAt == '\t')) {
            length--;
        }
        int i6 = (int) ((length * 6) / 8);
        byte[] bArr = new byte[i6];
        if (length > 0) {
            int i7 = 0;
            i3 = 0;
            int i8 = 0;
            int i9 = 0;
            while (true) {
                int i10 = i7 + 1;
                char charAt2 = str.charAt(i7);
                if ('A' <= charAt2 && charAt2 <= 'Z') {
                    i5 = charAt2 - 'A';
                } else {
                    if ('a' <= charAt2 && charAt2 <= 'z') {
                        i5 = charAt2 - 'G';
                    } else {
                        if ('0' <= charAt2 && charAt2 <= '9') {
                            i5 = charAt2 + 4;
                        } else if (charAt2 == '+' || charAt2 == '-') {
                            i5 = 62;
                        } else if (charAt2 == '/' || charAt2 == '_') {
                            i5 = 63;
                        } else {
                            if (charAt2 != '\n' && charAt2 != '\r' && charAt2 != ' ' && charAt2 != '\t') {
                                return null;
                            }
                            if (i10 < length) {
                                i2 = i8;
                                i4 = i9;
                                break;
                            }
                            i7 = i10;
                        }
                    }
                }
                i9 = (i9 << 6) | i5;
                i8++;
                if (i8 % 4 == 0) {
                    int i11 = i3 + 1;
                    bArr[i3] = (byte) (i9 >> 16);
                    int i12 = i11 + 1;
                    bArr[i11] = (byte) (i9 >> 8);
                    i3 = i12 + 1;
                    bArr[i12] = (byte) i9;
                }
                if (i10 < length) {
                }
            }
        } else {
            i2 = 0;
            i3 = 0;
            i4 = 0;
        }
        int i13 = i2 % 4;
        if (i13 != 1) {
            if (i13 == 2) {
                bArr[i3] = (byte) ((i4 << 12) >> 16);
                i3++;
            } else if (i13 == 3) {
                int i14 = i4 << 6;
                int i15 = i3 + 1;
                bArr[i3] = (byte) (i14 >> 16);
                i3 = i15 + 1;
                bArr[i15] = (byte) (i14 >> 8);
            }
            if (i3 == i6) {
                return bArr;
            }
            byte[] copyOf = Arrays.copyOf(bArr, i3);
            Intrinsics.checkNotNullExpressionValue(copyOf, "java.util.Arrays.copyOf(this, newSize)");
            return copyOf;
        }
        return null;
    }

    @NotNull
    public static final String encodeBase64(@NotNull byte[] bArr, @NotNull byte[] map) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(map, "map");
        byte[] bArr2 = new byte[((bArr.length + 2) / 3) * 4];
        int length = bArr.length - (bArr.length % 3);
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            int i4 = i2 + 1;
            byte b2 = bArr[i2];
            int i5 = i4 + 1;
            byte b3 = bArr[i4];
            int i6 = i5 + 1;
            byte b4 = bArr[i5];
            int i7 = i3 + 1;
            bArr2[i3] = map[(b2 & 255) >> 2];
            int i8 = i7 + 1;
            bArr2[i7] = map[((b2 & 3) << 4) | ((b3 & 255) >> 4)];
            int i9 = i8 + 1;
            bArr2[i8] = map[((b3 & Ascii.SI) << 2) | ((b4 & 255) >> 6)];
            i3 = i9 + 1;
            bArr2[i9] = map[b4 & Utf8.REPLACEMENT_BYTE];
            i2 = i6;
        }
        int length2 = bArr.length - length;
        if (length2 == 1) {
            byte b5 = bArr[i2];
            int i10 = i3 + 1;
            bArr2[i3] = map[(b5 & 255) >> 2];
            int i11 = i10 + 1;
            bArr2[i10] = map[(b5 & 3) << 4];
            byte b6 = (byte) 61;
            bArr2[i11] = b6;
            bArr2[i11 + 1] = b6;
        } else if (length2 == 2) {
            int i12 = i2 + 1;
            byte b7 = bArr[i2];
            byte b8 = bArr[i12];
            int i13 = i3 + 1;
            bArr2[i3] = map[(b7 & 255) >> 2];
            int i14 = i13 + 1;
            bArr2[i13] = map[((b7 & 3) << 4) | ((b8 & 255) >> 4)];
            bArr2[i14] = map[(b8 & Ascii.SI) << 2];
            bArr2[i14 + 1] = (byte) 61;
        }
        return _JvmPlatformKt.toUtf8String(bArr2);
    }

    public static /* synthetic */ String encodeBase64$default(byte[] bArr, byte[] bArr2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            bArr2 = BASE64;
        }
        return encodeBase64(bArr, bArr2);
    }

    @NotNull
    public static final byte[] getBASE64() {
        return BASE64;
    }

    public static /* synthetic */ void getBASE64$annotations() {
    }

    @NotNull
    public static final byte[] getBASE64_URL_SAFE() {
        return BASE64_URL_SAFE;
    }

    public static /* synthetic */ void getBASE64_URL_SAFE$annotations() {
    }
}
