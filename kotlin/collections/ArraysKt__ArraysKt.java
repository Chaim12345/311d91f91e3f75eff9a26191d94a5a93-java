package kotlin.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Pair;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.TuplesKt;
import kotlin.UByteArray;
import kotlin.UIntArray;
import kotlin.ULongArray;
import kotlin.UShortArray;
import kotlin.collections.unsigned.UArraysKt___UArraysKt;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class ArraysKt__ArraysKt extends ArraysKt__ArraysJVMKt {
    @SinceKotlin(version = "1.3")
    @PublishedApi
    @JvmName(name = "contentDeepEquals")
    public static final <T> boolean contentDeepEquals(@Nullable T[] tArr, @Nullable T[] tArr2) {
        boolean m739contentEqualslec5QzE;
        boolean m735contentEqualsKJPZfPQ;
        boolean m734contentEqualsFGO6Aew;
        boolean m737contentEqualskV0jMPg;
        if (tArr == tArr2) {
            return true;
        }
        if (tArr == null || tArr2 == null || tArr.length != tArr2.length) {
            return false;
        }
        int length = tArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            T t2 = tArr[i2];
            T t3 = tArr2[i2];
            if (t2 != t3) {
                if (t2 == null || t3 == null) {
                    return false;
                }
                if ((t2 instanceof Object[]) && (t3 instanceof Object[])) {
                    if (!contentDeepEquals((Object[]) t2, (Object[]) t3)) {
                        return false;
                    }
                } else if ((t2 instanceof byte[]) && (t3 instanceof byte[])) {
                    if (!Arrays.equals((byte[]) t2, (byte[]) t3)) {
                        return false;
                    }
                } else if ((t2 instanceof short[]) && (t3 instanceof short[])) {
                    if (!Arrays.equals((short[]) t2, (short[]) t3)) {
                        return false;
                    }
                } else if ((t2 instanceof int[]) && (t3 instanceof int[])) {
                    if (!Arrays.equals((int[]) t2, (int[]) t3)) {
                        return false;
                    }
                } else if ((t2 instanceof long[]) && (t3 instanceof long[])) {
                    if (!Arrays.equals((long[]) t2, (long[]) t3)) {
                        return false;
                    }
                } else if ((t2 instanceof float[]) && (t3 instanceof float[])) {
                    if (!Arrays.equals((float[]) t2, (float[]) t3)) {
                        return false;
                    }
                } else if ((t2 instanceof double[]) && (t3 instanceof double[])) {
                    if (!Arrays.equals((double[]) t2, (double[]) t3)) {
                        return false;
                    }
                } else if ((t2 instanceof char[]) && (t3 instanceof char[])) {
                    if (!Arrays.equals((char[]) t2, (char[]) t3)) {
                        return false;
                    }
                } else if ((t2 instanceof boolean[]) && (t3 instanceof boolean[])) {
                    if (!Arrays.equals((boolean[]) t2, (boolean[]) t3)) {
                        return false;
                    }
                } else if ((t2 instanceof UByteArray) && (t3 instanceof UByteArray)) {
                    m737contentEqualskV0jMPg = UArraysKt___UArraysKt.m737contentEqualskV0jMPg(((UByteArray) t2).m271unboximpl(), ((UByteArray) t3).m271unboximpl());
                    if (!m737contentEqualskV0jMPg) {
                        return false;
                    }
                } else if ((t2 instanceof UShortArray) && (t3 instanceof UShortArray)) {
                    m734contentEqualsFGO6Aew = UArraysKt___UArraysKt.m734contentEqualsFGO6Aew(((UShortArray) t2).m531unboximpl(), ((UShortArray) t3).m531unboximpl());
                    if (!m734contentEqualsFGO6Aew) {
                        return false;
                    }
                } else if ((t2 instanceof UIntArray) && (t3 instanceof UIntArray)) {
                    m735contentEqualsKJPZfPQ = UArraysKt___UArraysKt.m735contentEqualsKJPZfPQ(((UIntArray) t2).m349unboximpl(), ((UIntArray) t3).m349unboximpl());
                    if (!m735contentEqualsKJPZfPQ) {
                        return false;
                    }
                } else if ((t2 instanceof ULongArray) && (t3 instanceof ULongArray)) {
                    m739contentEqualslec5QzE = UArraysKt___UArraysKt.m739contentEqualslec5QzE(((ULongArray) t2).m427unboximpl(), ((ULongArray) t3).m427unboximpl());
                    if (!m739contentEqualslec5QzE) {
                        return false;
                    }
                } else if (!Intrinsics.areEqual(t2, t3)) {
                    return false;
                }
            }
        }
        return true;
    }

    @SinceKotlin(version = "1.3")
    @JvmName(name = "contentDeepToString")
    @NotNull
    @PublishedApi
    public static final <T> String contentDeepToString(@Nullable T[] tArr) {
        int coerceAtMost;
        if (tArr == null) {
            return "null";
        }
        coerceAtMost = RangesKt___RangesKt.coerceAtMost(tArr.length, 429496729);
        StringBuilder sb = new StringBuilder((coerceAtMost * 5) + 2);
        contentDeepToStringInternal$ArraysKt__ArraysKt(tArr, sb, new ArrayList());
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "StringBuilder(capacity).…builderAction).toString()");
        return sb2;
    }

    private static final <T> void contentDeepToStringInternal$ArraysKt__ArraysKt(T[] tArr, StringBuilder sb, List<Object[]> list) {
        int lastIndex;
        String m751contentToString2csIQuQ;
        if (list.contains(tArr)) {
            sb.append("[...]");
            return;
        }
        list.add(tArr);
        sb.append(AbstractJsonLexerKt.BEGIN_LIST);
        int length = tArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (i2 != 0) {
                sb.append(", ");
            }
            T t2 = tArr[i2];
            if (t2 == null) {
                m751contentToString2csIQuQ = "null";
            } else if (t2 instanceof Object[]) {
                contentDeepToStringInternal$ArraysKt__ArraysKt((Object[]) t2, sb, list);
            } else {
                if (t2 instanceof byte[]) {
                    m751contentToString2csIQuQ = Arrays.toString((byte[]) t2);
                } else if (t2 instanceof short[]) {
                    m751contentToString2csIQuQ = Arrays.toString((short[]) t2);
                } else if (t2 instanceof int[]) {
                    m751contentToString2csIQuQ = Arrays.toString((int[]) t2);
                } else if (t2 instanceof long[]) {
                    m751contentToString2csIQuQ = Arrays.toString((long[]) t2);
                } else if (t2 instanceof float[]) {
                    m751contentToString2csIQuQ = Arrays.toString((float[]) t2);
                } else if (t2 instanceof double[]) {
                    m751contentToString2csIQuQ = Arrays.toString((double[]) t2);
                } else if (t2 instanceof char[]) {
                    m751contentToString2csIQuQ = Arrays.toString((char[]) t2);
                } else if (t2 instanceof boolean[]) {
                    m751contentToString2csIQuQ = Arrays.toString((boolean[]) t2);
                } else {
                    m751contentToString2csIQuQ = t2 instanceof UByteArray ? UArraysKt___UArraysKt.m751contentToString2csIQuQ(((UByteArray) t2).m271unboximpl()) : t2 instanceof UShortArray ? UArraysKt___UArraysKt.m755contentToStringd6D3K8(((UShortArray) t2).m531unboximpl()) : t2 instanceof UIntArray ? UArraysKt___UArraysKt.m754contentToStringXUkPCBk(((UIntArray) t2).m349unboximpl()) : t2 instanceof ULongArray ? UArraysKt___UArraysKt.m757contentToStringuLth9ew(((ULongArray) t2).m427unboximpl()) : t2.toString();
                }
                Intrinsics.checkNotNullExpressionValue(m751contentToString2csIQuQ, "toString(this)");
            }
            sb.append(m751contentToString2csIQuQ);
        }
        sb.append(AbstractJsonLexerKt.END_LIST);
        lastIndex = CollectionsKt__CollectionsKt.getLastIndex(list);
        list.remove(lastIndex);
    }

    @NotNull
    public static final <T> List<T> flatten(@NotNull T[][] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        int i2 = 0;
        for (T[] tArr2 : tArr) {
            i2 += tArr2.length;
        }
        ArrayList arrayList = new ArrayList(i2);
        for (T[] tArr3 : tArr) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, tArr3);
        }
        return arrayList;
    }

    /* JADX WARN: Incorrect types in method signature: <C:[Ljava/lang/Object;:TR;R:Ljava/lang/Object;>(TC;Lkotlin/jvm/functions/Function0<+TR;>;)TR; */
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final Object ifEmpty(Object[] objArr, Function0 defaultValue) {
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        return objArr.length == 0 ? defaultValue.invoke() : objArr;
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final boolean isNullOrEmpty(Object[] objArr) {
        if (objArr != null) {
            if (!(objArr.length == 0)) {
                return false;
            }
        }
        return true;
    }

    @NotNull
    public static final <T, R> Pair<List<T>, List<R>> unzip(@NotNull Pair<? extends T, ? extends R>[] pairArr) {
        Intrinsics.checkNotNullParameter(pairArr, "<this>");
        ArrayList arrayList = new ArrayList(pairArr.length);
        ArrayList arrayList2 = new ArrayList(pairArr.length);
        for (Pair<? extends T, ? extends R> pair : pairArr) {
            arrayList.add(pair.getFirst());
            arrayList2.add(pair.getSecond());
        }
        return TuplesKt.to(arrayList, arrayList2);
    }
}
