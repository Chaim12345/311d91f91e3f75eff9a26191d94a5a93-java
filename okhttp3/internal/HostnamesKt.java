package okhttp3.internal;

import java.net.IDN;
import java.net.InetAddress;
import java.util.Locale;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import okio.Buffer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class HostnamesKt {
    private static final boolean containsInvalidHostnameAsciiCodes(String str) {
        int indexOf$default;
        int length = str.length();
        int i2 = 0;
        while (i2 < length) {
            int i3 = i2 + 1;
            char charAt = str.charAt(i2);
            if (Intrinsics.compare((int) charAt, 31) <= 0 || Intrinsics.compare((int) charAt, 127) >= 0) {
                return true;
            }
            indexOf$default = StringsKt__StringsKt.indexOf$default((CharSequence) " #%/:?@[\\]", charAt, 0, false, 6, (Object) null);
            if (indexOf$default != -1) {
                return true;
            }
            i2 = i3;
        }
        return false;
    }

    private static final boolean decodeIpv4Suffix(String str, int i2, int i3, byte[] bArr, int i4) {
        int i5 = i4;
        while (i2 < i3) {
            if (i5 == bArr.length) {
                return false;
            }
            if (i5 != i4) {
                if (str.charAt(i2) != '.') {
                    return false;
                }
                i2++;
            }
            int i6 = i2;
            int i7 = 0;
            while (i6 < i3) {
                char charAt = str.charAt(i6);
                if (Intrinsics.compare((int) charAt, 48) < 0 || Intrinsics.compare((int) charAt, 57) > 0) {
                    break;
                } else if ((i7 == 0 && i2 != i6) || (i7 = ((i7 * 10) + charAt) - 48) > 255) {
                    return false;
                } else {
                    i6++;
                }
            }
            if (i6 - i2 == 0) {
                return false;
            }
            bArr[i5] = (byte) i7;
            i5++;
            i2 = i6;
        }
        return i5 == i4 + 4;
    }

    /* JADX WARN: Code restructure failed: missing block: B:42:0x0097, code lost:
        if (r13 == 16) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0099, code lost:
        if (r14 != (-1)) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x009b, code lost:
        return null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x009c, code lost:
        r0 = r13 - r14;
        java.lang.System.arraycopy(r9, r14, r9, 16 - r0, r0);
        java.util.Arrays.fill(r9, r14, (16 - r13) + r14, (byte) 0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00ad, code lost:
        return java.net.InetAddress.getByAddress(r9);
     */
    /* JADX WARN: Removed duplicated region for block: B:31:0x006b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static final InetAddress decodeIpv6(String str, int i2, int i3) {
        boolean startsWith$default;
        boolean startsWith$default2;
        int i4;
        boolean startsWith$default3;
        byte[] bArr = new byte[16];
        int i5 = i2;
        int i6 = -1;
        int i7 = -1;
        int i8 = 0;
        while (true) {
            if (i5 < i3) {
                if (i8 != 16) {
                    int i9 = i5 + 2;
                    if (i9 <= i3) {
                        startsWith$default3 = StringsKt__StringsJVMKt.startsWith$default(str, "::", i5, false, 4, null);
                        if (startsWith$default3) {
                            if (i6 != -1) {
                                return null;
                            }
                            i8 += 2;
                            if (i9 == i3) {
                                i6 = i8;
                                break;
                            }
                            i7 = i9;
                            i6 = i8;
                            i5 = i7;
                            int i10 = 0;
                            while (i5 < i3) {
                                int parseHexDigit = Util.parseHexDigit(str.charAt(i5));
                                if (parseHexDigit == -1) {
                                    break;
                                }
                                i10 = (i10 << 4) + parseHexDigit;
                                i5++;
                            }
                            i4 = i5 - i7;
                            if (i4 == 0 || i4 > 4) {
                                break;
                            }
                            int i11 = i8 + 1;
                            bArr[i8] = (byte) ((i10 >>> 8) & 255);
                            i8 = i11 + 1;
                            bArr[i11] = (byte) (i10 & 255);
                        }
                    }
                    if (i8 != 0) {
                        startsWith$default = StringsKt__StringsJVMKt.startsWith$default(str, ":", i5, false, 4, null);
                        if (startsWith$default) {
                            i5++;
                        } else {
                            startsWith$default2 = StringsKt__StringsJVMKt.startsWith$default(str, ".", i5, false, 4, null);
                            if (!startsWith$default2 || !decodeIpv4Suffix(str, i7, i3, bArr, i8 - 2)) {
                                return null;
                            }
                            i8 += 2;
                        }
                    }
                    i7 = i5;
                    i5 = i7;
                    int i102 = 0;
                    while (i5 < i3) {
                    }
                    i4 = i5 - i7;
                    if (i4 == 0) {
                        break;
                    }
                    break;
                }
                return null;
            }
            break;
        }
        return null;
    }

    private static final String inet6AddressToAscii(byte[] bArr) {
        int i2 = 0;
        int i3 = -1;
        int i4 = 0;
        int i5 = 0;
        while (i4 < bArr.length) {
            int i6 = i4;
            while (i6 < 16 && bArr[i6] == 0 && bArr[i6 + 1] == 0) {
                i6 += 2;
            }
            int i7 = i6 - i4;
            if (i7 > i5 && i7 >= 4) {
                i3 = i4;
                i5 = i7;
            }
            i4 = i6 + 2;
        }
        Buffer buffer = new Buffer();
        while (i2 < bArr.length) {
            if (i2 == i3) {
                buffer.writeByte(58);
                i2 += i5;
                if (i2 == 16) {
                    buffer.writeByte(58);
                }
            } else {
                if (i2 > 0) {
                    buffer.writeByte(58);
                }
                buffer.writeHexadecimalUnsignedLong((Util.and(bArr[i2], 255) << 8) | Util.and(bArr[i2 + 1], 255));
                i2 += 2;
            }
        }
        return buffer.readUtf8();
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0035 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0036  */
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final String toCanonicalHost(@NotNull String str) {
        boolean contains$default;
        boolean startsWith$default;
        InetAddress decodeIpv6;
        boolean endsWith$default;
        Intrinsics.checkNotNullParameter(str, "<this>");
        contains$default = StringsKt__StringsKt.contains$default((CharSequence) str, (CharSequence) ":", false, 2, (Object) null);
        if (!contains$default) {
            try {
                String ascii = IDN.toASCII(str);
                Intrinsics.checkNotNullExpressionValue(ascii, "toASCII(host)");
                Locale US = Locale.US;
                Intrinsics.checkNotNullExpressionValue(US, "US");
                String lowerCase = ascii.toLowerCase(US);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
                if (lowerCase.length() == 0) {
                    return null;
                }
                if (containsInvalidHostnameAsciiCodes(lowerCase)) {
                    return null;
                }
                return lowerCase;
            } catch (IllegalArgumentException unused) {
                return null;
            }
        }
        startsWith$default = StringsKt__StringsJVMKt.startsWith$default(str, "[", false, 2, null);
        if (startsWith$default) {
            endsWith$default = StringsKt__StringsJVMKt.endsWith$default(str, "]", false, 2, null);
            if (endsWith$default) {
                decodeIpv6 = decodeIpv6(str, 1, str.length() - 1);
                if (decodeIpv6 != null) {
                    return null;
                }
                byte[] address = decodeIpv6.getAddress();
                if (address.length == 16) {
                    Intrinsics.checkNotNullExpressionValue(address, "address");
                    return inet6AddressToAscii(address);
                } else if (address.length == 4) {
                    return decodeIpv6.getHostAddress();
                } else {
                    throw new AssertionError("Invalid IPv6 address: '" + str + '\'');
                }
            }
        }
        decodeIpv6 = decodeIpv6(str, 0, str.length());
        if (decodeIpv6 != null) {
        }
    }
}
