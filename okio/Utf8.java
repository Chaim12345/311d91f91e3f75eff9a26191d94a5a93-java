package okio;

import com.fasterxml.jackson.core.base.GeneratorBase;
import com.google.common.base.Ascii;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.asn1.BERTags;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\f\n\u0002\b\u0010\u001a'\u0010\u0007\u001a\u00020\u0004*\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0003\u001a\u00020\u0001H\u0007¢\u0006\u0004\b\u0005\u0010\u0006\u001a\u0011\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0001H\u0080\b\u001a\u0011\u0010\r\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000bH\u0080\b\u001a4\u0010\u0011\u001a\u00020\u000f*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0080\bø\u0001\u0000\u001a4\u0010\u0013\u001a\u00020\u000f*\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u000f0\u000eH\u0080\bø\u0001\u0000\u001a4\u0010\u0015\u001a\u00020\u000f*\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u000f0\u000eH\u0080\bø\u0001\u0000\u001a4\u0010\u0016\u001a\u00020\u0001*\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u000f0\u000eH\u0080\bø\u0001\u0000\u001a4\u0010\u0017\u001a\u00020\u0001*\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u000f0\u000eH\u0080\bø\u0001\u0000\u001a4\u0010\u0018\u001a\u00020\u0001*\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u000f0\u000eH\u0080\bø\u0001\u0000\"\u0016\u0010\u0019\u001a\u00020\u000b8\u0000@\u0000X\u0080T¢\u0006\u0006\n\u0004\b\u0019\u0010\u001a\"\u0016\u0010\u001b\u001a\u00020\u00148\u0000@\u0000X\u0080T¢\u0006\u0006\n\u0004\b\u001b\u0010\u001c\"\u0016\u0010\u001d\u001a\u00020\u00018\u0000@\u0000X\u0080T¢\u0006\u0006\n\u0004\b\u001d\u0010\u001e\"\u0016\u0010\u001f\u001a\u00020\u00018\u0000@\u0000X\u0080T¢\u0006\u0006\n\u0004\b\u001f\u0010\u001e\"\u0016\u0010 \u001a\u00020\u00018\u0000@\u0000X\u0080T¢\u0006\u0006\n\u0004\b \u0010\u001e\"\u0016\u0010!\u001a\u00020\u00018\u0000@\u0000X\u0080T¢\u0006\u0006\n\u0004\b!\u0010\u001e\"\u0016\u0010\"\u001a\u00020\u00018\u0000@\u0000X\u0080T¢\u0006\u0006\n\u0004\b\"\u0010\u001e\"\u0016\u0010#\u001a\u00020\u00018\u0000@\u0000X\u0080T¢\u0006\u0006\n\u0004\b#\u0010\u001e\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006$"}, d2 = {"", "", "beginIndex", "endIndex", "", "size", "(Ljava/lang/String;II)J", "utf8Size", "codePoint", "", "isIsoControl", "", "byte", "isUtf8Continuation", "Lkotlin/Function1;", "", "yield", "processUtf8Bytes", "", "processUtf8CodePoints", "", "processUtf16Chars", "process2Utf8Bytes", "process3Utf8Bytes", "process4Utf8Bytes", "REPLACEMENT_BYTE", "B", "REPLACEMENT_CHARACTER", "C", "REPLACEMENT_CODE_POINT", "I", "HIGH_SURROGATE_HEADER", "LOG_SURROGATE_HEADER", "MASK_2BYTES", "MASK_3BYTES", "MASK_4BYTES", "okio"}, k = 2, mv = {1, 5, 1})
@JvmName(name = "Utf8")
/* loaded from: classes3.dex */
public final class Utf8 {
    public static final int HIGH_SURROGATE_HEADER = 55232;
    public static final int LOG_SURROGATE_HEADER = 56320;
    public static final int MASK_2BYTES = 3968;
    public static final int MASK_3BYTES = -123008;
    public static final int MASK_4BYTES = 3678080;
    public static final byte REPLACEMENT_BYTE = 63;
    public static final char REPLACEMENT_CHARACTER = 65533;
    public static final int REPLACEMENT_CODE_POINT = 65533;

    public static final boolean isIsoControl(int i2) {
        if (i2 >= 0 && i2 <= 31) {
            return true;
        }
        return 127 <= i2 && i2 <= 159;
    }

    public static final boolean isUtf8Continuation(byte b2) {
        return (b2 & 192) == 128;
    }

    public static final int process2Utf8Bytes(@NotNull byte[] bArr, int i2, int i3, @NotNull Function1<? super Integer, Unit> yield) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(yield, "yield");
        int i4 = i2 + 1;
        Integer valueOf = Integer.valueOf((int) REPLACEMENT_CODE_POINT);
        if (i3 <= i4) {
            yield.invoke(valueOf);
            return 1;
        }
        byte b2 = bArr[i2];
        byte b3 = bArr[i4];
        if (!((b3 & 192) == 128)) {
            yield.invoke(valueOf);
            return 1;
        }
        int i5 = (b3 ^ 3968) ^ (b2 << 6);
        if (i5 < 128) {
            yield.invoke(valueOf);
            return 2;
        }
        yield.invoke(Integer.valueOf(i5));
        return 2;
    }

    public static final int process3Utf8Bytes(@NotNull byte[] bArr, int i2, int i3, @NotNull Function1<? super Integer, Unit> yield) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(yield, "yield");
        int i4 = i2 + 2;
        Integer valueOf = Integer.valueOf((int) REPLACEMENT_CODE_POINT);
        if (i3 <= i4) {
            yield.invoke(valueOf);
            int i5 = i2 + 1;
            if (i3 > i5) {
                if ((bArr[i5] & 192) == 128) {
                    return 2;
                }
            }
            return 1;
        }
        byte b2 = bArr[i2];
        byte b3 = bArr[i2 + 1];
        if (!((b3 & 192) == 128)) {
            yield.invoke(valueOf);
            return 1;
        }
        byte b4 = bArr[i4];
        if (!((b4 & 192) == 128)) {
            yield.invoke(valueOf);
            return 2;
        }
        int i6 = ((b4 ^ (-123008)) ^ (b3 << 6)) ^ (b2 << Ascii.FF);
        if (i6 >= 2048) {
            if (55296 <= i6 && i6 <= 57343) {
                r3 = true;
            }
            if (!r3) {
                yield.invoke(Integer.valueOf(i6));
                return 3;
            }
        }
        yield.invoke(valueOf);
        return 3;
    }

    public static final int process4Utf8Bytes(@NotNull byte[] bArr, int i2, int i3, @NotNull Function1<? super Integer, Unit> yield) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(yield, "yield");
        int i4 = i2 + 3;
        Integer valueOf = Integer.valueOf((int) REPLACEMENT_CODE_POINT);
        if (i3 <= i4) {
            yield.invoke(valueOf);
            int i5 = i2 + 1;
            if (i3 > i5) {
                if ((bArr[i5] & 192) == 128) {
                    int i6 = i2 + 2;
                    if (i3 > i6) {
                        if ((bArr[i6] & 192) == 128) {
                            return 3;
                        }
                    }
                    return 2;
                }
            }
            return 1;
        }
        byte b2 = bArr[i2];
        byte b3 = bArr[i2 + 1];
        if (!((b3 & 192) == 128)) {
            yield.invoke(valueOf);
            return 1;
        }
        byte b4 = bArr[i2 + 2];
        if (!((b4 & 192) == 128)) {
            yield.invoke(valueOf);
            return 2;
        }
        byte b5 = bArr[i4];
        if (!((b5 & 192) == 128)) {
            yield.invoke(valueOf);
            return 3;
        }
        int i7 = (((b5 ^ 3678080) ^ (b4 << 6)) ^ (b3 << Ascii.FF)) ^ (b2 << Ascii.DC2);
        if (i7 <= 1114111) {
            if (55296 <= i7 && i7 <= 57343) {
                r4 = true;
            }
            if (!r4 && i7 >= 65536) {
                yield.invoke(Integer.valueOf(i7));
                return 4;
            }
        }
        yield.invoke(valueOf);
        return 4;
    }

    /* JADX WARN: Code restructure failed: missing block: B:38:0x0098, code lost:
        if (((r16[r4] & 192) == 128) == false) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x011d, code lost:
        if (((r16[r4] & 192) == 128) == false) goto L30;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void processUtf16Chars(@NotNull byte[] bArr, int i2, int i3, @NotNull Function1<? super Character, Unit> yield) {
        int i4;
        char c2;
        Character valueOf;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(yield, "yield");
        int i5 = i2;
        while (i5 < i3) {
            byte b2 = bArr[i5];
            if (b2 >= 0) {
                yield.invoke(Character.valueOf((char) b2));
                i5++;
                while (i5 < i3 && bArr[i5] >= 0) {
                    yield.invoke(Character.valueOf((char) bArr[i5]));
                    i5++;
                }
            } else {
                if ((b2 >> 5) == -2) {
                    int i6 = i5 + 1;
                    if (i3 > i6) {
                        byte b3 = bArr[i5];
                        byte b4 = bArr[i6];
                        if ((b4 & 192) == 128) {
                            int i7 = (b4 ^ 3968) ^ (b3 << 6);
                            yield.invoke(Character.valueOf(i7 < 128 ? (char) REPLACEMENT_CODE_POINT : (char) i7));
                            Unit unit = Unit.INSTANCE;
                            i4 = 2;
                        }
                    }
                    yield.invoke(Character.valueOf((char) REPLACEMENT_CODE_POINT));
                    Unit unit2 = Unit.INSTANCE;
                    i4 = 1;
                } else if ((b2 >> 4) == -2) {
                    int i8 = i5 + 2;
                    if (i3 <= i8) {
                        yield.invoke(Character.valueOf((char) REPLACEMENT_CODE_POINT));
                        Unit unit3 = Unit.INSTANCE;
                        int i9 = i5 + 1;
                        if (i3 > i9) {
                        }
                        i4 = 1;
                    } else {
                        byte b5 = bArr[i5];
                        byte b6 = bArr[i5 + 1];
                        if ((b6 & 192) == 128) {
                            byte b7 = bArr[i8];
                            if ((b7 & 192) == 128) {
                                int i10 = ((b7 ^ (-123008)) ^ (b6 << 6)) ^ (b5 << Ascii.FF);
                                if (i10 >= 2048) {
                                    if (55296 <= i10 && i10 <= 57343) {
                                        r9 = true;
                                    }
                                    if (!r9) {
                                        c2 = (char) i10;
                                        yield.invoke(Character.valueOf(c2));
                                        Unit unit4 = Unit.INSTANCE;
                                        i4 = 3;
                                    }
                                }
                                c2 = (char) REPLACEMENT_CODE_POINT;
                                yield.invoke(Character.valueOf(c2));
                                Unit unit42 = Unit.INSTANCE;
                                i4 = 3;
                            } else {
                                yield.invoke(Character.valueOf((char) REPLACEMENT_CODE_POINT));
                                Unit unit5 = Unit.INSTANCE;
                                i4 = 2;
                            }
                        } else {
                            yield.invoke(Character.valueOf((char) REPLACEMENT_CODE_POINT));
                            Unit unit6 = Unit.INSTANCE;
                            i4 = 1;
                        }
                    }
                } else if ((b2 >> 3) == -2) {
                    int i11 = i5 + 3;
                    if (i3 <= i11) {
                        yield.invoke(Character.valueOf(REPLACEMENT_CHARACTER));
                        Unit unit7 = Unit.INSTANCE;
                        int i12 = i5 + 1;
                        if (i3 > i12) {
                            if ((bArr[i12] & 192) == 128) {
                                int i13 = i5 + 2;
                                if (i3 > i13) {
                                }
                                i4 = 2;
                            }
                        }
                        i4 = 1;
                    } else {
                        byte b8 = bArr[i5];
                        byte b9 = bArr[i5 + 1];
                        if ((b9 & 192) == 128) {
                            byte b10 = bArr[i5 + 2];
                            if ((b10 & 192) == 128) {
                                byte b11 = bArr[i11];
                                if ((b11 & 192) == 128) {
                                    int i14 = (((b11 ^ 3678080) ^ (b10 << 6)) ^ (b9 << Ascii.FF)) ^ (b8 << Ascii.DC2);
                                    if (i14 <= 1114111) {
                                        if (55296 <= i14 && i14 <= 57343) {
                                            r9 = true;
                                        }
                                        if (!r9 && i14 >= 65536) {
                                            if (i14 != 65533) {
                                                yield.invoke(Character.valueOf((char) ((i14 >>> 10) + HIGH_SURROGATE_HEADER)));
                                                valueOf = Character.valueOf((char) ((i14 & 1023) + 56320));
                                            } else {
                                                valueOf = Character.valueOf(REPLACEMENT_CHARACTER);
                                            }
                                            yield.invoke(valueOf);
                                            Unit unit8 = Unit.INSTANCE;
                                            i4 = 4;
                                        }
                                    }
                                    valueOf = Character.valueOf(REPLACEMENT_CHARACTER);
                                    yield.invoke(valueOf);
                                    Unit unit82 = Unit.INSTANCE;
                                    i4 = 4;
                                } else {
                                    yield.invoke(Character.valueOf(REPLACEMENT_CHARACTER));
                                    Unit unit9 = Unit.INSTANCE;
                                    i4 = 3;
                                }
                            } else {
                                yield.invoke(Character.valueOf(REPLACEMENT_CHARACTER));
                                Unit unit10 = Unit.INSTANCE;
                                i4 = 2;
                            }
                        } else {
                            yield.invoke(Character.valueOf(REPLACEMENT_CHARACTER));
                            Unit unit11 = Unit.INSTANCE;
                            i4 = 1;
                        }
                    }
                } else {
                    yield.invoke(Character.valueOf(REPLACEMENT_CHARACTER));
                    i5++;
                }
                i5 += i4;
            }
        }
    }

    public static final void processUtf8Bytes(@NotNull String str, int i2, int i3, @NotNull Function1<? super Byte, Unit> yield) {
        int i4;
        Byte valueOf;
        int i5;
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(yield, "yield");
        while (i2 < i3) {
            char charAt = str.charAt(i2);
            if (Intrinsics.compare((int) charAt, 128) < 0) {
                yield.invoke(Byte.valueOf((byte) charAt));
                i2++;
                while (i2 < i3 && Intrinsics.compare((int) str.charAt(i2), 128) < 0) {
                    yield.invoke(Byte.valueOf((byte) str.charAt(i2)));
                    i2++;
                }
            } else {
                if (Intrinsics.compare((int) charAt, 2048) < 0) {
                    i4 = (charAt >> 6) | 192;
                } else {
                    boolean z = false;
                    if (55296 <= charAt && charAt <= 57343) {
                        if (Intrinsics.compare((int) charAt, (int) GeneratorBase.SURR1_LAST) <= 0 && i3 > (i5 = i2 + 1)) {
                            char charAt2 = str.charAt(i5);
                            if (56320 <= charAt2 && charAt2 <= 57343) {
                                z = true;
                            }
                            if (z) {
                                int charAt3 = ((charAt << '\n') + str.charAt(i5)) - 56613888;
                                yield.invoke(Byte.valueOf((byte) ((charAt3 >> 18) | 240)));
                                yield.invoke(Byte.valueOf((byte) (((charAt3 >> 12) & 63) | 128)));
                                yield.invoke(Byte.valueOf((byte) (((charAt3 >> 6) & 63) | 128)));
                                yield.invoke(Byte.valueOf((byte) ((charAt3 & 63) | 128)));
                                i2 += 2;
                            }
                        }
                        valueOf = Byte.valueOf((byte) REPLACEMENT_BYTE);
                        yield.invoke(valueOf);
                        i2++;
                    } else {
                        yield.invoke(Byte.valueOf((byte) ((charAt >> '\f') | BERTags.FLAGS)));
                        i4 = ((charAt >> 6) & 63) | 128;
                    }
                }
                yield.invoke(Byte.valueOf((byte) i4));
                valueOf = Byte.valueOf((byte) ((charAt & '?') | 128));
                yield.invoke(valueOf);
                i2++;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:38:0x0096, code lost:
        if (((r16[r4] & 192) == 128) == false) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x011b, code lost:
        if (((r16[r4] & 192) == 128) == false) goto L30;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void processUtf8CodePoints(@NotNull byte[] bArr, int i2, int i3, @NotNull Function1<? super Integer, Unit> yield) {
        int i4;
        Integer valueOf;
        Integer valueOf2;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(yield, "yield");
        int i5 = i2;
        while (i5 < i3) {
            byte b2 = bArr[i5];
            if (b2 >= 0) {
                yield.invoke(Integer.valueOf(b2));
                i5++;
                while (i5 < i3 && bArr[i5] >= 0) {
                    yield.invoke(Integer.valueOf(bArr[i5]));
                    i5++;
                }
            } else {
                if ((b2 >> 5) == -2) {
                    int i6 = i5 + 1;
                    if (i3 > i6) {
                        byte b3 = bArr[i5];
                        byte b4 = bArr[i6];
                        if ((b4 & 192) == 128) {
                            int i7 = (b4 ^ 3968) ^ (b3 << 6);
                            yield.invoke(i7 < 128 ? Integer.valueOf((int) REPLACEMENT_CODE_POINT) : Integer.valueOf(i7));
                            Unit unit = Unit.INSTANCE;
                            i4 = 2;
                        }
                    }
                    yield.invoke(Integer.valueOf((int) REPLACEMENT_CODE_POINT));
                    Unit unit2 = Unit.INSTANCE;
                    i4 = 1;
                } else if ((b2 >> 4) == -2) {
                    int i8 = i5 + 2;
                    if (i3 <= i8) {
                        yield.invoke(Integer.valueOf((int) REPLACEMENT_CODE_POINT));
                        Unit unit3 = Unit.INSTANCE;
                        int i9 = i5 + 1;
                        if (i3 > i9) {
                        }
                        i4 = 1;
                    } else {
                        byte b5 = bArr[i5];
                        byte b6 = bArr[i5 + 1];
                        if ((b6 & 192) == 128) {
                            byte b7 = bArr[i8];
                            if ((b7 & 192) == 128) {
                                int i10 = ((b7 ^ (-123008)) ^ (b6 << 6)) ^ (b5 << Ascii.FF);
                                if (i10 >= 2048) {
                                    if (55296 <= i10 && i10 <= 57343) {
                                        r9 = true;
                                    }
                                    if (!r9) {
                                        valueOf = Integer.valueOf(i10);
                                        yield.invoke(valueOf);
                                        Unit unit4 = Unit.INSTANCE;
                                        i4 = 3;
                                    }
                                }
                                valueOf = Integer.valueOf((int) REPLACEMENT_CODE_POINT);
                                yield.invoke(valueOf);
                                Unit unit42 = Unit.INSTANCE;
                                i4 = 3;
                            } else {
                                yield.invoke(Integer.valueOf((int) REPLACEMENT_CODE_POINT));
                                Unit unit5 = Unit.INSTANCE;
                                i4 = 2;
                            }
                        } else {
                            yield.invoke(Integer.valueOf((int) REPLACEMENT_CODE_POINT));
                            Unit unit6 = Unit.INSTANCE;
                            i4 = 1;
                        }
                    }
                } else if ((b2 >> 3) == -2) {
                    int i11 = i5 + 3;
                    if (i3 <= i11) {
                        yield.invoke(Integer.valueOf((int) REPLACEMENT_CODE_POINT));
                        Unit unit7 = Unit.INSTANCE;
                        int i12 = i5 + 1;
                        if (i3 > i12) {
                            if ((bArr[i12] & 192) == 128) {
                                int i13 = i5 + 2;
                                if (i3 > i13) {
                                }
                                i4 = 2;
                            }
                        }
                        i4 = 1;
                    } else {
                        byte b8 = bArr[i5];
                        byte b9 = bArr[i5 + 1];
                        if ((b9 & 192) == 128) {
                            byte b10 = bArr[i5 + 2];
                            if ((b10 & 192) == 128) {
                                byte b11 = bArr[i11];
                                if ((b11 & 192) == 128) {
                                    int i14 = (((b11 ^ 3678080) ^ (b10 << 6)) ^ (b9 << Ascii.FF)) ^ (b8 << Ascii.DC2);
                                    if (i14 <= 1114111) {
                                        if (55296 <= i14 && i14 <= 57343) {
                                            r9 = true;
                                        }
                                        if (!r9 && i14 >= 65536) {
                                            valueOf2 = Integer.valueOf(i14);
                                            yield.invoke(valueOf2);
                                            Unit unit8 = Unit.INSTANCE;
                                            i4 = 4;
                                        }
                                    }
                                    valueOf2 = Integer.valueOf((int) REPLACEMENT_CODE_POINT);
                                    yield.invoke(valueOf2);
                                    Unit unit82 = Unit.INSTANCE;
                                    i4 = 4;
                                } else {
                                    yield.invoke(Integer.valueOf((int) REPLACEMENT_CODE_POINT));
                                    Unit unit9 = Unit.INSTANCE;
                                    i4 = 3;
                                }
                            } else {
                                yield.invoke(Integer.valueOf((int) REPLACEMENT_CODE_POINT));
                                Unit unit10 = Unit.INSTANCE;
                                i4 = 2;
                            }
                        } else {
                            yield.invoke(Integer.valueOf((int) REPLACEMENT_CODE_POINT));
                            Unit unit11 = Unit.INSTANCE;
                            i4 = 1;
                        }
                    }
                } else {
                    yield.invoke(Integer.valueOf((int) REPLACEMENT_CODE_POINT));
                    i5++;
                }
                i5 += i4;
            }
        }
    }

    @JvmOverloads
    @JvmName(name = "size")
    public static final long size(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return size$default(str, 0, 0, 3, null);
    }

    @JvmOverloads
    @JvmName(name = "size")
    public static final long size(@NotNull String str, int i2) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return size$default(str, i2, 0, 2, null);
    }

    @JvmOverloads
    @JvmName(name = "size")
    public static final long size(@NotNull String str, int i2, int i3) {
        int i4;
        Intrinsics.checkNotNullParameter(str, "<this>");
        if (i2 >= 0) {
            if (!(i3 >= i2)) {
                throw new IllegalArgumentException(("endIndex < beginIndex: " + i3 + " < " + i2).toString());
            }
            if (!(i3 <= str.length())) {
                throw new IllegalArgumentException(("endIndex > string.length: " + i3 + " > " + str.length()).toString());
            }
            long j2 = 0;
            while (i2 < i3) {
                char charAt = str.charAt(i2);
                if (charAt < 128) {
                    j2++;
                } else {
                    if (charAt < 2048) {
                        i4 = 2;
                    } else if (charAt < 55296 || charAt > 57343) {
                        i4 = 3;
                    } else {
                        int i5 = i2 + 1;
                        char charAt2 = i5 < i3 ? str.charAt(i5) : (char) 0;
                        if (charAt > 56319 || charAt2 < 56320 || charAt2 > 57343) {
                            j2++;
                            i2 = i5;
                        } else {
                            j2 += 4;
                            i2 += 2;
                        }
                    }
                    j2 += i4;
                }
                i2++;
            }
            return j2;
        }
        throw new IllegalArgumentException(Intrinsics.stringPlus("beginIndex < 0: ", Integer.valueOf(i2)).toString());
    }

    public static /* synthetic */ long size$default(String str, int i2, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i2 = 0;
        }
        if ((i4 & 2) != 0) {
            i3 = str.length();
        }
        return size(str, i2, i3);
    }
}
