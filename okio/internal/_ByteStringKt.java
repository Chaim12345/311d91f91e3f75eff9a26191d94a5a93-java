package okio.internal;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.common.base.Ascii;
import com.google.firebase.messaging.Constants;
import java.util.Arrays;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import okio.Buffer;
import okio.ByteString;
import okio._Base64Kt;
import okio._JvmPlatformKt;
import okio._UtilKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\u0000\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\f\n\u0002\b\u0006\n\u0002\u0010\u0019\n\u0002\b\u0007\u001a\r\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u0080\b\u001a\r\u0010\u0003\u001a\u00020\u0001*\u00020\u0000H\u0080\b\u001a\r\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\u0080\b\u001a\r\u0010\u0005\u001a\u00020\u0001*\u00020\u0000H\u0080\b\u001a\r\u0010\u0006\u001a\u00020\u0000*\u00020\u0000H\u0080\b\u001a\r\u0010\u0007\u001a\u00020\u0000*\u00020\u0000H\u0080\b\u001a\u001d\u0010\u000b\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\bH\u0080\b\u001a\u0015\u0010\u000e\u001a\u00020\r*\u00020\u00002\u0006\u0010\f\u001a\u00020\bH\u0080\b\u001a\r\u0010\u000f\u001a\u00020\b*\u00020\u0000H\u0080\b\u001a\r\u0010\u0011\u001a\u00020\u0010*\u00020\u0000H\u0080\b\u001a\r\u0010\u0012\u001a\u00020\u0010*\u00020\u0000H\u0080\b\u001a-\u0010\u0018\u001a\u00020\u0017*\u00020\u00002\u0006\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\u00002\u0006\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\bH\u0080\b\u001a-\u0010\u0018\u001a\u00020\u0017*\u00020\u00002\u0006\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\bH\u0080\b\u001a-\u0010\u001c\u001a\u00020\u001b*\u00020\u00002\u0006\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\bH\u0080\b\u001a\u0015\u0010\u001e\u001a\u00020\u0017*\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u0000H\u0080\b\u001a\u0015\u0010\u001e\u001a\u00020\u0017*\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u0010H\u0080\b\u001a\u0015\u0010 \u001a\u00020\u0017*\u00020\u00002\u0006\u0010\u001f\u001a\u00020\u0000H\u0080\b\u001a\u0015\u0010 \u001a\u00020\u0017*\u00020\u00002\u0006\u0010\u001f\u001a\u00020\u0010H\u0080\b\u001a\u001d\u0010\"\u001a\u00020\b*\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u00102\u0006\u0010!\u001a\u00020\bH\u0080\b\u001a\u001d\u0010#\u001a\u00020\b*\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u00002\u0006\u0010!\u001a\u00020\bH\u0080\b\u001a\u001d\u0010#\u001a\u00020\b*\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u00102\u0006\u0010!\u001a\u00020\bH\u0080\b\u001a\u0017\u0010%\u001a\u00020\u0017*\u00020\u00002\b\u0010\u0014\u001a\u0004\u0018\u00010$H\u0080\b\u001a\r\u0010&\u001a\u00020\b*\u00020\u0000H\u0080\b\u001a\u0015\u0010'\u001a\u00020\b*\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u0000H\u0080\b\u001a\u0011\u0010)\u001a\u00020\u00002\u0006\u0010(\u001a\u00020\u0010H\u0080\b\u001a\u001d\u0010*\u001a\u00020\u0000*\u00020\u00102\u0006\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\bH\u0080\b\u001a\r\u0010+\u001a\u00020\u0000*\u00020\u0001H\u0080\b\u001a\u000f\u0010,\u001a\u0004\u0018\u00010\u0000*\u00020\u0001H\u0080\b\u001a\r\u0010-\u001a\u00020\u0000*\u00020\u0001H\u0080\b\u001a$\u00100\u001a\u00020\u001b*\u00020\u00002\u0006\u0010/\u001a\u00020.2\u0006\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\bH\u0000\u001a\u0010\u00103\u001a\u00020\b2\u0006\u00102\u001a\u000201H\u0002\u001a\r\u00104\u001a\u00020\u0001*\u00020\u0000H\u0080\b\u001a\u0018\u00107\u001a\u00020\b2\u0006\u00105\u001a\u00020\u00102\u0006\u00106\u001a\u00020\bH\u0002\"\"\u00109\u001a\u0002088\u0000@\u0001X\u0081\u0004¢\u0006\u0012\n\u0004\b9\u0010:\u0012\u0004\b=\u0010>\u001a\u0004\b;\u0010<¨\u0006?"}, d2 = {"Lokio/ByteString;", "", "commonUtf8", "commonBase64", "commonBase64Url", "commonHex", "commonToAsciiLowercase", "commonToAsciiUppercase", "", "beginIndex", "endIndex", "commonSubstring", "pos", "", "commonGetByte", "commonGetSize", "", "commonToByteArray", "commonInternalArray", TypedValues.Cycle.S_WAVE_OFFSET, "other", "otherOffset", "byteCount", "", "commonRangeEquals", TypedValues.Attributes.S_TARGET, "targetOffset", "", "commonCopyInto", "prefix", "commonStartsWith", "suffix", "commonEndsWith", "fromIndex", "commonIndexOf", "commonLastIndexOf", "", "commonEquals", "commonHashCode", "commonCompareTo", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "commonOf", "commonToByteString", "commonEncodeUtf8", "commonDecodeBase64", "commonDecodeHex", "Lokio/Buffer;", "buffer", "commonWrite", "", "c", "decodeHexDigit", "commonToString", "s", "codePointCount", "codePointIndexToCharIndex", "", "HEX_DIGIT_CHARS", "[C", "getHEX_DIGIT_CHARS", "()[C", "getHEX_DIGIT_CHARS$annotations", "()V", "okio"}, k = 2, mv = {1, 5, 1})
/* loaded from: classes3.dex */
public final class _ByteStringKt {
    @NotNull
    private static final char[] HEX_DIGIT_CHARS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /* JADX WARN: Removed duplicated region for block: B:554:0x0047 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:557:0x0083 A[EDGE_INSN: B:557:0x0083->B:350:0x0083 ?: BREAK  , SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:559:0x0220 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:566:0x0173 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:578:0x00df A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final int codePointIndexToCharIndex(byte[] bArr, int i2) {
        boolean z;
        boolean z2;
        int i3;
        boolean z3;
        boolean z4;
        boolean z5;
        int length = bArr.length;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        loop0: while (i4 < length) {
            byte b2 = bArr[i4];
            if (b2 >= 0) {
                int i7 = i6 + 1;
                if (i6 == i2) {
                    return i5;
                }
                if (b2 != 10 && b2 != 13) {
                    if (!(b2 >= 0 && b2 <= 31)) {
                        if (!(Byte.MAX_VALUE <= b2 && b2 <= 159)) {
                            z2 = false;
                            if (z2) {
                                return -1;
                            }
                        }
                    }
                    z2 = true;
                    if (z2) {
                    }
                }
                if (b2 == 65533) {
                    return -1;
                }
                i5 += b2 < 65536 ? 1 : 2;
                i4++;
                while (true) {
                    i6 = i7;
                    if (i4 < length && bArr[i4] >= 0) {
                        int i8 = i4 + 1;
                        byte b3 = bArr[i4];
                        i7 = i6 + 1;
                        if (i6 == i2) {
                            return i5;
                        }
                        if (b3 != 10 && b3 != 13) {
                            if (!(b3 >= 0 && b3 <= 31)) {
                                if (!(Byte.MAX_VALUE <= b3 && b3 <= 159)) {
                                    z = false;
                                    if (z) {
                                        break loop0;
                                    }
                                }
                            }
                            z = true;
                            if (z) {
                            }
                        }
                        if (b3 == 65533) {
                            break loop0;
                        }
                        i5 += b3 < 65536 ? 1 : 2;
                        i4 = i8;
                    }
                }
                return -1;
            }
            if ((b2 >> 5) == -2) {
                int i9 = i4 + 1;
                if (length <= i9) {
                    if (i6 == i2) {
                        return i5;
                    }
                    return -1;
                }
                byte b4 = bArr[i4];
                byte b5 = bArr[i9];
                if (!((b5 & 192) == 128)) {
                    if (i6 == i2) {
                        return i5;
                    }
                    return -1;
                }
                int i10 = (b5 ^ 3968) ^ (b4 << 6);
                if (i10 < 128) {
                    if (i6 == i2) {
                        return i5;
                    }
                    return -1;
                }
                i3 = i6 + 1;
                if (i6 == i2) {
                    return i5;
                }
                if (i10 != 10 && i10 != 13) {
                    if (!(i10 >= 0 && i10 <= 31)) {
                        if (!(127 <= i10 && i10 <= 159)) {
                            z5 = false;
                            if (z5) {
                                return -1;
                            }
                        }
                    }
                    z5 = true;
                    if (z5) {
                    }
                }
                if (i10 == 65533) {
                    return -1;
                }
                i5 += i10 < 65536 ? 1 : 2;
                Unit unit = Unit.INSTANCE;
                i4 += 2;
            } else if ((b2 >> 4) == -2) {
                int i11 = i4 + 2;
                if (length <= i11) {
                    if (i6 == i2) {
                        return i5;
                    }
                    return -1;
                }
                byte b6 = bArr[i4];
                byte b7 = bArr[i4 + 1];
                if (!((b7 & 192) == 128)) {
                    if (i6 == i2) {
                        return i5;
                    }
                    return -1;
                }
                byte b8 = bArr[i11];
                if (!((b8 & 192) == 128)) {
                    if (i6 == i2) {
                        return i5;
                    }
                    return -1;
                }
                int i12 = ((b8 ^ (-123008)) ^ (b7 << 6)) ^ (b6 << Ascii.FF);
                if (i12 < 2048) {
                    if (i6 == i2) {
                        return i5;
                    }
                    return -1;
                }
                if (55296 <= i12 && i12 <= 57343) {
                    if (i6 == i2) {
                        return i5;
                    }
                    return -1;
                }
                i3 = i6 + 1;
                if (i6 == i2) {
                    return i5;
                }
                if (i12 != 10 && i12 != 13) {
                    if (!(i12 >= 0 && i12 <= 31)) {
                        if (!(127 <= i12 && i12 <= 159)) {
                            z4 = false;
                            if (z4) {
                                return -1;
                            }
                        }
                    }
                    z4 = true;
                    if (z4) {
                    }
                }
                if (i12 == 65533) {
                    return -1;
                }
                i5 += i12 < 65536 ? 1 : 2;
                Unit unit2 = Unit.INSTANCE;
                i4 += 3;
            } else if ((b2 >> 3) != -2) {
                if (i6 == i2) {
                    return i5;
                }
                return -1;
            } else {
                int i13 = i4 + 3;
                if (length <= i13) {
                    if (i6 == i2) {
                        return i5;
                    }
                    return -1;
                }
                byte b9 = bArr[i4];
                byte b10 = bArr[i4 + 1];
                if (!((b10 & 192) == 128)) {
                    if (i6 == i2) {
                        return i5;
                    }
                    return -1;
                }
                byte b11 = bArr[i4 + 2];
                if (!((b11 & 192) == 128)) {
                    if (i6 == i2) {
                        return i5;
                    }
                    return -1;
                }
                byte b12 = bArr[i13];
                if (!((b12 & 192) == 128)) {
                    if (i6 == i2) {
                        return i5;
                    }
                    return -1;
                }
                int i14 = (((b12 ^ 3678080) ^ (b11 << 6)) ^ (b10 << Ascii.FF)) ^ (b9 << Ascii.DC2);
                if (i14 > 1114111) {
                    if (i6 == i2) {
                        return i5;
                    }
                    return -1;
                }
                if (55296 <= i14 && i14 <= 57343) {
                    if (i6 == i2) {
                        return i5;
                    }
                    return -1;
                } else if (i14 < 65536) {
                    if (i6 == i2) {
                        return i5;
                    }
                    return -1;
                } else {
                    i3 = i6 + 1;
                    if (i6 == i2) {
                        return i5;
                    }
                    if (i14 != 10 && i14 != 13) {
                        if (!(i14 >= 0 && i14 <= 31)) {
                            if (!(127 <= i14 && i14 <= 159)) {
                                z3 = false;
                                if (z3) {
                                    return -1;
                                }
                            }
                        }
                        z3 = true;
                        if (z3) {
                        }
                    }
                    if (i14 == 65533) {
                        return -1;
                    }
                    i5 += i14 < 65536 ? 1 : 2;
                    Unit unit3 = Unit.INSTANCE;
                    i4 += 4;
                }
            }
            i6 = i3;
        }
        return i5;
    }

    @NotNull
    public static final String commonBase64(@NotNull ByteString byteString) {
        Intrinsics.checkNotNullParameter(byteString, "<this>");
        return _Base64Kt.encodeBase64$default(byteString.getData$okio(), null, 1, null);
    }

    @NotNull
    public static final String commonBase64Url(@NotNull ByteString byteString) {
        Intrinsics.checkNotNullParameter(byteString, "<this>");
        return _Base64Kt.encodeBase64(byteString.getData$okio(), _Base64Kt.getBASE64_URL_SAFE());
    }

    public static final int commonCompareTo(@NotNull ByteString byteString, @NotNull ByteString other) {
        Intrinsics.checkNotNullParameter(byteString, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        int size = byteString.size();
        int size2 = other.size();
        int min = Math.min(size, size2);
        for (int i2 = 0; i2 < min; i2++) {
            int i3 = byteString.getByte(i2) & 255;
            int i4 = other.getByte(i2) & 255;
            if (i3 != i4) {
                return i3 < i4 ? -1 : 1;
            }
        }
        if (size == size2) {
            return 0;
        }
        return size < size2 ? -1 : 1;
    }

    public static final void commonCopyInto(@NotNull ByteString byteString, int i2, @NotNull byte[] target, int i3, int i4) {
        Intrinsics.checkNotNullParameter(byteString, "<this>");
        Intrinsics.checkNotNullParameter(target, "target");
        ArraysKt___ArraysJvmKt.copyInto(byteString.getData$okio(), target, i3, i2, i4 + i2);
    }

    @Nullable
    public static final ByteString commonDecodeBase64(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        byte[] decodeBase64ToArray = _Base64Kt.decodeBase64ToArray(str);
        if (decodeBase64ToArray != null) {
            return new ByteString(decodeBase64ToArray);
        }
        return null;
    }

    @NotNull
    public static final ByteString commonDecodeHex(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        int i2 = 0;
        if (str.length() % 2 == 0) {
            int length = str.length() / 2;
            byte[] bArr = new byte[length];
            int i3 = length - 1;
            if (i3 >= 0) {
                while (true) {
                    int i4 = i2 + 1;
                    int i5 = i2 * 2;
                    bArr[i2] = (byte) ((decodeHexDigit(str.charAt(i5)) << 4) + decodeHexDigit(str.charAt(i5 + 1)));
                    if (i4 > i3) {
                        break;
                    }
                    i2 = i4;
                }
            }
            return new ByteString(bArr);
        }
        throw new IllegalArgumentException(Intrinsics.stringPlus("Unexpected hex string: ", str).toString());
    }

    @NotNull
    public static final ByteString commonEncodeUtf8(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        ByteString byteString = new ByteString(_JvmPlatformKt.asUtf8ToByteArray(str));
        byteString.setUtf8$okio(str);
        return byteString;
    }

    public static final boolean commonEndsWith(@NotNull ByteString byteString, @NotNull ByteString suffix) {
        Intrinsics.checkNotNullParameter(byteString, "<this>");
        Intrinsics.checkNotNullParameter(suffix, "suffix");
        return byteString.rangeEquals(byteString.size() - suffix.size(), suffix, 0, suffix.size());
    }

    public static final boolean commonEndsWith(@NotNull ByteString byteString, @NotNull byte[] suffix) {
        Intrinsics.checkNotNullParameter(byteString, "<this>");
        Intrinsics.checkNotNullParameter(suffix, "suffix");
        return byteString.rangeEquals(byteString.size() - suffix.length, suffix, 0, suffix.length);
    }

    public static final boolean commonEquals(@NotNull ByteString byteString, @Nullable Object obj) {
        Intrinsics.checkNotNullParameter(byteString, "<this>");
        if (obj == byteString) {
            return true;
        }
        if (obj instanceof ByteString) {
            ByteString byteString2 = (ByteString) obj;
            if (byteString2.size() == byteString.getData$okio().length && byteString2.rangeEquals(0, byteString.getData$okio(), 0, byteString.getData$okio().length)) {
                return true;
            }
        }
        return false;
    }

    public static final byte commonGetByte(@NotNull ByteString byteString, int i2) {
        Intrinsics.checkNotNullParameter(byteString, "<this>");
        return byteString.getData$okio()[i2];
    }

    public static final int commonGetSize(@NotNull ByteString byteString) {
        Intrinsics.checkNotNullParameter(byteString, "<this>");
        return byteString.getData$okio().length;
    }

    public static final int commonHashCode(@NotNull ByteString byteString) {
        Intrinsics.checkNotNullParameter(byteString, "<this>");
        int hashCode$okio = byteString.getHashCode$okio();
        if (hashCode$okio != 0) {
            return hashCode$okio;
        }
        int hashCode = Arrays.hashCode(byteString.getData$okio());
        byteString.setHashCode$okio(hashCode);
        return hashCode;
    }

    @NotNull
    public static final String commonHex(@NotNull ByteString byteString) {
        String concatToString;
        Intrinsics.checkNotNullParameter(byteString, "<this>");
        char[] cArr = new char[byteString.getData$okio().length * 2];
        byte[] data$okio = byteString.getData$okio();
        int length = data$okio.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            byte b2 = data$okio[i2];
            i2++;
            int i4 = i3 + 1;
            cArr[i3] = getHEX_DIGIT_CHARS()[(b2 >> 4) & 15];
            i3 = i4 + 1;
            cArr[i4] = getHEX_DIGIT_CHARS()[b2 & Ascii.SI];
        }
        concatToString = StringsKt__StringsJVMKt.concatToString(cArr);
        return concatToString;
    }

    public static final int commonIndexOf(@NotNull ByteString byteString, @NotNull byte[] other, int i2) {
        Intrinsics.checkNotNullParameter(byteString, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        int length = byteString.getData$okio().length - other.length;
        int max = Math.max(i2, 0);
        if (max > length) {
            return -1;
        }
        while (true) {
            int i3 = max + 1;
            if (_UtilKt.arrayRangeEquals(byteString.getData$okio(), max, other, 0, other.length)) {
                return max;
            }
            if (max == length) {
                return -1;
            }
            max = i3;
        }
    }

    @NotNull
    public static final byte[] commonInternalArray(@NotNull ByteString byteString) {
        Intrinsics.checkNotNullParameter(byteString, "<this>");
        return byteString.getData$okio();
    }

    public static final int commonLastIndexOf(@NotNull ByteString byteString, @NotNull ByteString other, int i2) {
        Intrinsics.checkNotNullParameter(byteString, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        return byteString.lastIndexOf(other.internalArray$okio(), i2);
    }

    public static final int commonLastIndexOf(@NotNull ByteString byteString, @NotNull byte[] other, int i2) {
        Intrinsics.checkNotNullParameter(byteString, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        int min = Math.min(_UtilKt.resolveDefaultParameter(byteString, i2), byteString.getData$okio().length - other.length);
        if (min < 0) {
            return -1;
        }
        while (true) {
            int i3 = min - 1;
            if (_UtilKt.arrayRangeEquals(byteString.getData$okio(), min, other, 0, other.length)) {
                return min;
            }
            if (i3 < 0) {
                return -1;
            }
            min = i3;
        }
    }

    @NotNull
    public static final ByteString commonOf(@NotNull byte[] data) {
        Intrinsics.checkNotNullParameter(data, "data");
        byte[] copyOf = Arrays.copyOf(data, data.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "java.util.Arrays.copyOf(this, size)");
        return new ByteString(copyOf);
    }

    public static final boolean commonRangeEquals(@NotNull ByteString byteString, int i2, @NotNull ByteString other, int i3, int i4) {
        Intrinsics.checkNotNullParameter(byteString, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        return other.rangeEquals(i3, byteString.getData$okio(), i2, i4);
    }

    public static final boolean commonRangeEquals(@NotNull ByteString byteString, int i2, @NotNull byte[] other, int i3, int i4) {
        Intrinsics.checkNotNullParameter(byteString, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        return i2 >= 0 && i2 <= byteString.getData$okio().length - i4 && i3 >= 0 && i3 <= other.length - i4 && _UtilKt.arrayRangeEquals(byteString.getData$okio(), i2, other, i3, i4);
    }

    public static final boolean commonStartsWith(@NotNull ByteString byteString, @NotNull ByteString prefix) {
        Intrinsics.checkNotNullParameter(byteString, "<this>");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        return byteString.rangeEquals(0, prefix, 0, prefix.size());
    }

    public static final boolean commonStartsWith(@NotNull ByteString byteString, @NotNull byte[] prefix) {
        Intrinsics.checkNotNullParameter(byteString, "<this>");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        return byteString.rangeEquals(0, prefix, 0, prefix.length);
    }

    @NotNull
    public static final ByteString commonSubstring(@NotNull ByteString byteString, int i2, int i3) {
        byte[] copyOfRange;
        Intrinsics.checkNotNullParameter(byteString, "<this>");
        int resolveDefaultParameter = _UtilKt.resolveDefaultParameter(byteString, i3);
        if (i2 >= 0) {
            if (!(resolveDefaultParameter <= byteString.getData$okio().length)) {
                throw new IllegalArgumentException(("endIndex > length(" + byteString.getData$okio().length + ')').toString());
            }
            if (resolveDefaultParameter - i2 >= 0) {
                if (i2 == 0 && resolveDefaultParameter == byteString.getData$okio().length) {
                    return byteString;
                }
                copyOfRange = ArraysKt___ArraysJvmKt.copyOfRange(byteString.getData$okio(), i2, resolveDefaultParameter);
                return new ByteString(copyOfRange);
            }
            throw new IllegalArgumentException("endIndex < beginIndex".toString());
        }
        throw new IllegalArgumentException("beginIndex < 0".toString());
    }

    @NotNull
    public static final ByteString commonToAsciiLowercase(@NotNull ByteString byteString) {
        byte b2;
        Intrinsics.checkNotNullParameter(byteString, "<this>");
        for (int i2 = 0; i2 < byteString.getData$okio().length; i2++) {
            byte b3 = byteString.getData$okio()[i2];
            byte b4 = (byte) 65;
            if (b3 >= b4 && b3 <= (b2 = (byte) 90)) {
                byte[] data$okio = byteString.getData$okio();
                byte[] copyOf = Arrays.copyOf(data$okio, data$okio.length);
                Intrinsics.checkNotNullExpressionValue(copyOf, "java.util.Arrays.copyOf(this, size)");
                copyOf[i2] = (byte) (b3 + 32);
                for (int i3 = i2 + 1; i3 < copyOf.length; i3++) {
                    byte b5 = copyOf[i3];
                    if (b5 >= b4 && b5 <= b2) {
                        copyOf[i3] = (byte) (b5 + 32);
                    }
                }
                return new ByteString(copyOf);
            }
        }
        return byteString;
    }

    @NotNull
    public static final ByteString commonToAsciiUppercase(@NotNull ByteString byteString) {
        byte b2;
        Intrinsics.checkNotNullParameter(byteString, "<this>");
        for (int i2 = 0; i2 < byteString.getData$okio().length; i2++) {
            byte b3 = byteString.getData$okio()[i2];
            byte b4 = (byte) 97;
            if (b3 >= b4 && b3 <= (b2 = (byte) 122)) {
                byte[] data$okio = byteString.getData$okio();
                byte[] copyOf = Arrays.copyOf(data$okio, data$okio.length);
                Intrinsics.checkNotNullExpressionValue(copyOf, "java.util.Arrays.copyOf(this, size)");
                copyOf[i2] = (byte) (b3 - 32);
                for (int i3 = i2 + 1; i3 < copyOf.length; i3++) {
                    byte b5 = copyOf[i3];
                    if (b5 >= b4 && b5 <= b2) {
                        copyOf[i3] = (byte) (b5 - 32);
                    }
                }
                return new ByteString(copyOf);
            }
        }
        return byteString;
    }

    @NotNull
    public static final byte[] commonToByteArray(@NotNull ByteString byteString) {
        Intrinsics.checkNotNullParameter(byteString, "<this>");
        byte[] data$okio = byteString.getData$okio();
        byte[] copyOf = Arrays.copyOf(data$okio, data$okio.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "java.util.Arrays.copyOf(this, size)");
        return copyOf;
    }

    @NotNull
    public static final ByteString commonToByteString(@NotNull byte[] bArr, int i2, int i3) {
        byte[] copyOfRange;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        _UtilKt.checkOffsetAndCount(bArr.length, i2, i3);
        copyOfRange = ArraysKt___ArraysJvmKt.copyOfRange(bArr, i2, i3 + i2);
        return new ByteString(copyOfRange);
    }

    @NotNull
    public static final String commonToString(@NotNull ByteString byteString) {
        String replace$default;
        String replace$default2;
        String replace$default3;
        StringBuilder sb;
        byte[] copyOfRange;
        ByteString byteString2 = byteString;
        Intrinsics.checkNotNullParameter(byteString2, "<this>");
        if (byteString.getData$okio().length == 0) {
            return "[size=0]";
        }
        int codePointIndexToCharIndex = codePointIndexToCharIndex(byteString.getData$okio(), 64);
        if (codePointIndexToCharIndex != -1) {
            String utf8 = byteString.utf8();
            Objects.requireNonNull(utf8, "null cannot be cast to non-null type java.lang.String");
            String substring = utf8.substring(0, codePointIndexToCharIndex);
            Intrinsics.checkNotNullExpressionValue(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            replace$default = StringsKt__StringsJVMKt.replace$default(substring, "\\", "\\\\", false, 4, (Object) null);
            replace$default2 = StringsKt__StringsJVMKt.replace$default(replace$default, "\n", "\\n", false, 4, (Object) null);
            replace$default3 = StringsKt__StringsJVMKt.replace$default(replace$default2, "\r", "\\r", false, 4, (Object) null);
            if (codePointIndexToCharIndex >= utf8.length()) {
                return "[text=" + replace$default3 + AbstractJsonLexerKt.END_LIST;
            }
            return "[size=" + byteString.getData$okio().length + " text=" + replace$default3 + "…]";
        }
        if (byteString.getData$okio().length <= 64) {
            sb = new StringBuilder();
            sb.append("[hex=");
            sb.append(byteString.hex());
            sb.append(AbstractJsonLexerKt.END_LIST);
        } else {
            sb = new StringBuilder();
            sb.append("[size=");
            sb.append(byteString.getData$okio().length);
            sb.append(" hex=");
            int resolveDefaultParameter = _UtilKt.resolveDefaultParameter(byteString2, 64);
            if (!(resolveDefaultParameter <= byteString.getData$okio().length)) {
                throw new IllegalArgumentException(("endIndex > length(" + byteString.getData$okio().length + ')').toString());
            }
            if (!(resolveDefaultParameter + 0 >= 0)) {
                throw new IllegalArgumentException("endIndex < beginIndex".toString());
            }
            if (resolveDefaultParameter != byteString.getData$okio().length) {
                copyOfRange = ArraysKt___ArraysJvmKt.copyOfRange(byteString.getData$okio(), 0, resolveDefaultParameter);
                byteString2 = new ByteString(copyOfRange);
            }
            sb.append(byteString2.hex());
            sb.append("…]");
        }
        return sb.toString();
    }

    @NotNull
    public static final String commonUtf8(@NotNull ByteString byteString) {
        Intrinsics.checkNotNullParameter(byteString, "<this>");
        String utf8$okio = byteString.getUtf8$okio();
        if (utf8$okio == null) {
            String utf8String = _JvmPlatformKt.toUtf8String(byteString.internalArray$okio());
            byteString.setUtf8$okio(utf8String);
            return utf8String;
        }
        return utf8$okio;
    }

    public static final void commonWrite(@NotNull ByteString byteString, @NotNull Buffer buffer, int i2, int i3) {
        Intrinsics.checkNotNullParameter(byteString, "<this>");
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        buffer.write(byteString.getData$okio(), i2, i3);
    }

    public static final int decodeHexDigit(char c2) {
        boolean z = true;
        if ('0' <= c2 && c2 <= '9') {
            return c2 - '0';
        }
        char c3 = 'a';
        if (!('a' <= c2 && c2 <= 'f')) {
            c3 = 'A';
            if ('A' > c2 || c2 > 'F') {
                z = false;
            }
            if (!z) {
                throw new IllegalArgumentException(Intrinsics.stringPlus("Unexpected hex digit: ", Character.valueOf(c2)));
            }
        }
        return (c2 - c3) + 10;
    }

    @NotNull
    public static final char[] getHEX_DIGIT_CHARS() {
        return HEX_DIGIT_CHARS;
    }

    public static /* synthetic */ void getHEX_DIGIT_CHARS$annotations() {
    }
}
