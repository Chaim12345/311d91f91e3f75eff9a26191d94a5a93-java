package okio.internal;

import com.fasterxml.jackson.core.base.GeneratorBase;
import com.google.common.base.Ascii;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import okio.Utf8;
import org.bouncycastle.asn1.BERTags;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0010\u0012\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a\u001e\u0010\u0005\u001a\u00020\u0004*\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0003\u001a\u00020\u0001\u001a\n\u0010\u0006\u001a\u00020\u0000*\u00020\u0004¨\u0006\u0007"}, d2 = {"", "", "beginIndex", "endIndex", "", "commonToUtf8String", "commonAsUtf8ToByteArray", "okio"}, k = 2, mv = {1, 5, 1})
/* loaded from: classes3.dex */
public final class _Utf8Kt {
    @NotNull
    public static final byte[] commonAsUtf8ToByteArray(@NotNull String str) {
        int i2;
        int i3;
        Intrinsics.checkNotNullParameter(str, "<this>");
        byte[] bArr = new byte[str.length() * 4];
        int length = str.length();
        if (length > 0) {
            int i4 = 0;
            while (true) {
                int i5 = i4 + 1;
                char charAt = str.charAt(i4);
                if (Intrinsics.compare((int) charAt, 128) >= 0) {
                    int length2 = str.length();
                    int i6 = i4;
                    while (i4 < length2) {
                        char charAt2 = str.charAt(i4);
                        if (Intrinsics.compare((int) charAt2, 128) < 0) {
                            int i7 = i6 + 1;
                            bArr[i6] = (byte) charAt2;
                            i4++;
                            while (true) {
                                i6 = i7;
                                if (i4 < length2 && Intrinsics.compare((int) str.charAt(i4), 128) < 0) {
                                    i7 = i6 + 1;
                                    bArr[i6] = (byte) str.charAt(i4);
                                    i4++;
                                }
                            }
                        } else {
                            if (Intrinsics.compare((int) charAt2, 2048) < 0) {
                                int i8 = i6 + 1;
                                bArr[i6] = (byte) ((charAt2 >> 6) | 192);
                                i2 = i8 + 1;
                                bArr[i8] = (byte) ((charAt2 & '?') | 128);
                            } else {
                                boolean z = true;
                                if (55296 <= charAt2 && charAt2 <= 57343) {
                                    if (Intrinsics.compare((int) charAt2, (int) GeneratorBase.SURR1_LAST) <= 0 && length2 > (i3 = i4 + 1)) {
                                        char charAt3 = str.charAt(i3);
                                        if (56320 > charAt3 || charAt3 > 57343) {
                                            z = false;
                                        }
                                        if (z) {
                                            int charAt4 = ((charAt2 << '\n') + str.charAt(i3)) - 56613888;
                                            int i9 = i6 + 1;
                                            bArr[i6] = (byte) ((charAt4 >> 18) | 240);
                                            int i10 = i9 + 1;
                                            bArr[i9] = (byte) (((charAt4 >> 12) & 63) | 128);
                                            int i11 = i10 + 1;
                                            bArr[i10] = (byte) (((charAt4 >> 6) & 63) | 128);
                                            i2 = i11 + 1;
                                            bArr[i11] = (byte) ((charAt4 & 63) | 128);
                                            i4 += 2;
                                            i6 = i2;
                                        }
                                    }
                                    i2 = i6 + 1;
                                    bArr[i6] = Utf8.REPLACEMENT_BYTE;
                                } else {
                                    int i12 = i6 + 1;
                                    bArr[i6] = (byte) ((charAt2 >> '\f') | BERTags.FLAGS);
                                    int i13 = i12 + 1;
                                    bArr[i12] = (byte) (((charAt2 >> 6) & 63) | 128);
                                    i2 = i13 + 1;
                                    bArr[i13] = (byte) ((charAt2 & '?') | 128);
                                }
                            }
                            i4++;
                            i6 = i2;
                        }
                    }
                    byte[] copyOf = Arrays.copyOf(bArr, i6);
                    Intrinsics.checkNotNullExpressionValue(copyOf, "java.util.Arrays.copyOf(this, newSize)");
                    return copyOf;
                }
                bArr[i4] = (byte) charAt;
                if (i5 >= length) {
                    break;
                }
                i4 = i5;
            }
        }
        byte[] copyOf2 = Arrays.copyOf(bArr, str.length());
        Intrinsics.checkNotNullExpressionValue(copyOf2, "java.util.Arrays.copyOf(this, newSize)");
        return copyOf2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:49:0x009d, code lost:
        if (((r16[r5] & 192) == 128) == false) goto L86;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x0123, code lost:
        if (((r16[r5] & 192) == 128) == false) goto L41;
     */
    @NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final String commonToUtf8String(@NotNull byte[] bArr, int i2, int i3) {
        String concatToString;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9 = i2;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        if (i9 < 0 || i3 > bArr.length || i9 > i3) {
            throw new ArrayIndexOutOfBoundsException("size=" + bArr.length + " beginIndex=" + i9 + " endIndex=" + i3);
        }
        char[] cArr = new char[i3 - i9];
        int i10 = 0;
        while (i9 < i3) {
            byte b2 = bArr[i9];
            if (b2 >= 0) {
                int i11 = i10 + 1;
                cArr[i10] = (char) b2;
                i9++;
                while (true) {
                    i10 = i11;
                    if (i9 < i3 && bArr[i9] >= 0) {
                        i11 = i10 + 1;
                        cArr[i10] = (char) bArr[i9];
                        i9++;
                    }
                }
            } else {
                if ((b2 >> 5) == -2) {
                    int i12 = i9 + 1;
                    if (i3 <= i12) {
                        i4 = i10 + 1;
                        cArr[i10] = (char) Utf8.REPLACEMENT_CODE_POINT;
                    } else {
                        byte b3 = bArr[i9];
                        byte b4 = bArr[i12];
                        if ((b4 & 192) == 128) {
                            int i13 = (b4 ^ 3968) ^ (b3 << 6);
                            if (i13 < 128) {
                                i4 = i10 + 1;
                                cArr[i10] = (char) Utf8.REPLACEMENT_CODE_POINT;
                            } else {
                                i4 = i10 + 1;
                                cArr[i10] = (char) i13;
                            }
                            Unit unit = Unit.INSTANCE;
                            i10 = i4;
                            i5 = 2;
                        } else {
                            i4 = i10 + 1;
                            cArr[i10] = (char) Utf8.REPLACEMENT_CODE_POINT;
                        }
                    }
                    Unit unit2 = Unit.INSTANCE;
                    i10 = i4;
                    i5 = 1;
                } else {
                    if ((b2 >> 4) == -2) {
                        int i14 = i9 + 2;
                        if (i3 <= i14) {
                            i4 = i10 + 1;
                            cArr[i10] = (char) Utf8.REPLACEMENT_CODE_POINT;
                            Unit unit3 = Unit.INSTANCE;
                            int i15 = i9 + 1;
                            if (i3 > i15) {
                            }
                            i10 = i4;
                            i5 = 1;
                        } else {
                            byte b5 = bArr[i9];
                            byte b6 = bArr[i9 + 1];
                            if ((b6 & 192) == 128) {
                                byte b7 = bArr[i14];
                                if ((b7 & 192) == 128) {
                                    int i16 = ((b7 ^ (-123008)) ^ (b6 << 6)) ^ (b5 << Ascii.FF);
                                    if (i16 < 2048) {
                                        i6 = i10 + 1;
                                        cArr[i10] = (char) Utf8.REPLACEMENT_CODE_POINT;
                                    } else {
                                        if (55296 <= i16 && i16 <= 57343) {
                                            i6 = i10 + 1;
                                            cArr[i10] = (char) Utf8.REPLACEMENT_CODE_POINT;
                                        } else {
                                            i6 = i10 + 1;
                                            cArr[i10] = (char) i16;
                                        }
                                    }
                                    Unit unit4 = Unit.INSTANCE;
                                    i10 = i6;
                                } else {
                                    i4 = i10 + 1;
                                    cArr[i10] = (char) Utf8.REPLACEMENT_CODE_POINT;
                                    Unit unit5 = Unit.INSTANCE;
                                    i10 = i4;
                                    i5 = 2;
                                }
                            } else {
                                i4 = i10 + 1;
                                cArr[i10] = (char) Utf8.REPLACEMENT_CODE_POINT;
                                Unit unit6 = Unit.INSTANCE;
                                i10 = i4;
                                i5 = 1;
                            }
                        }
                    } else if ((b2 >> 3) == -2) {
                        int i17 = i9 + 3;
                        if (i3 <= i17) {
                            i8 = i10 + 1;
                            cArr[i10] = Utf8.REPLACEMENT_CHARACTER;
                            Unit unit7 = Unit.INSTANCE;
                            int i18 = i9 + 1;
                            if (i3 > i18) {
                                if ((bArr[i18] & 192) == 128) {
                                    int i19 = i9 + 2;
                                    if (i3 > i19) {
                                    }
                                    i10 = i8;
                                    i5 = 2;
                                }
                            }
                            i10 = i8;
                            i5 = 1;
                        } else {
                            byte b8 = bArr[i9];
                            byte b9 = bArr[i9 + 1];
                            if ((b9 & 192) == 128) {
                                byte b10 = bArr[i9 + 2];
                                if ((b10 & 192) == 128) {
                                    byte b11 = bArr[i17];
                                    if ((b11 & 192) == 128) {
                                        int i20 = (((b11 ^ 3678080) ^ (b10 << 6)) ^ (b9 << Ascii.FF)) ^ (b8 << Ascii.DC2);
                                        if (i20 > 1114111) {
                                            i7 = i10 + 1;
                                            cArr[i10] = Utf8.REPLACEMENT_CHARACTER;
                                        } else {
                                            if (55296 <= i20 && i20 <= 57343) {
                                                i7 = i10 + 1;
                                                cArr[i10] = Utf8.REPLACEMENT_CHARACTER;
                                            } else if (i20 < 65536) {
                                                i7 = i10 + 1;
                                                cArr[i10] = Utf8.REPLACEMENT_CHARACTER;
                                            } else if (i20 != 65533) {
                                                int i21 = i10 + 1;
                                                cArr[i10] = (char) ((i20 >>> 10) + Utf8.HIGH_SURROGATE_HEADER);
                                                i7 = i21 + 1;
                                                cArr[i21] = (char) ((i20 & 1023) + 56320);
                                            } else {
                                                i7 = i10 + 1;
                                                cArr[i10] = Utf8.REPLACEMENT_CHARACTER;
                                            }
                                        }
                                        Unit unit8 = Unit.INSTANCE;
                                        i5 = 4;
                                        i10 = i7;
                                    } else {
                                        i8 = i10 + 1;
                                        cArr[i10] = Utf8.REPLACEMENT_CHARACTER;
                                        Unit unit9 = Unit.INSTANCE;
                                        i10 = i8;
                                    }
                                } else {
                                    i8 = i10 + 1;
                                    cArr[i10] = Utf8.REPLACEMENT_CHARACTER;
                                    Unit unit10 = Unit.INSTANCE;
                                    i10 = i8;
                                    i5 = 2;
                                }
                            } else {
                                i8 = i10 + 1;
                                cArr[i10] = Utf8.REPLACEMENT_CHARACTER;
                                Unit unit11 = Unit.INSTANCE;
                                i10 = i8;
                                i5 = 1;
                            }
                        }
                    } else {
                        cArr[i10] = Utf8.REPLACEMENT_CHARACTER;
                        i9++;
                        i10++;
                    }
                    i5 = 3;
                }
                i9 += i5;
            }
        }
        concatToString = StringsKt__StringsJVMKt.concatToString(cArr, 0, i10);
        return concatToString;
    }

    public static /* synthetic */ String commonToUtf8String$default(byte[] bArr, int i2, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i2 = 0;
        }
        if ((i4 & 2) != 0) {
            i3 = bArr.length;
        }
        return commonToUtf8String(bArr, i2, i3);
    }
}
