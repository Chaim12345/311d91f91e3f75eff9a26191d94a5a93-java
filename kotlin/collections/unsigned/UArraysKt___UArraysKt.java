package kotlin.collections.unsigned;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import kotlin.Deprecated;
import kotlin.DeprecatedSinceKotlin;
import kotlin.ExperimentalStdlibApi;
import kotlin.ExperimentalUnsignedTypes;
import kotlin.OverloadResolutionByLambdaReturnType;
import kotlin.Pair;
import kotlin.ReplaceWith;
import kotlin.SinceKotlin;
import kotlin.TuplesKt;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.UInt;
import kotlin.UIntArray;
import kotlin.ULong;
import kotlin.ULongArray;
import kotlin.UShort;
import kotlin.UShortArray;
import kotlin.Unit;
import kotlin.UnsignedKt;
import kotlin.WasExperimental;
import kotlin.collections.AbstractList;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsJvmKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.collections.IndexingIterable;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.collections.UArraySortingKt;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt___RangesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class UArraysKt___UArraysKt extends UArraysKt___UArraysJvmKt {
    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: all-JOV_ifY  reason: not valid java name */
    private static final boolean m690allJOV_ifY(byte[] all, Function1<? super UByte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(all, "$this$all");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int m263getSizeimpl = UByteArray.m263getSizeimpl(all);
        for (int i2 = 0; i2 < m263getSizeimpl; i2++) {
            if (!predicate.invoke(UByte.m199boximpl(UByteArray.m262getw2LRezQ(all, i2))).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: all-MShoTSo  reason: not valid java name */
    private static final boolean m691allMShoTSo(long[] all, Function1<? super ULong, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(all, "$this$all");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int m419getSizeimpl = ULongArray.m419getSizeimpl(all);
        for (int i2 = 0; i2 < m419getSizeimpl; i2++) {
            if (!predicate.invoke(ULong.m353boximpl(ULongArray.m418getsVKNKU(all, i2))).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: all-jgv0xPQ  reason: not valid java name */
    private static final boolean m692alljgv0xPQ(int[] all, Function1<? super UInt, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(all, "$this$all");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int m341getSizeimpl = UIntArray.m341getSizeimpl(all);
        for (int i2 = 0; i2 < m341getSizeimpl; i2++) {
            if (!predicate.invoke(UInt.m275boximpl(UIntArray.m340getpVg5ArA(all, i2))).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: all-xTcfx_M  reason: not valid java name */
    private static final boolean m693allxTcfx_M(short[] all, Function1<? super UShort, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(all, "$this$all");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int m523getSizeimpl = UShortArray.m523getSizeimpl(all);
        for (int i2 = 0; i2 < m523getSizeimpl; i2++) {
            if (!predicate.invoke(UShort.m459boximpl(UShortArray.m522getMh2AYeg(all, i2))).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: any--ajY-9A  reason: not valid java name */
    private static final boolean m694anyajY9A(int[] any) {
        boolean any2;
        Intrinsics.checkNotNullParameter(any, "$this$any");
        any2 = ArraysKt___ArraysKt.any(any);
        return any2;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: any-GBYM_sE  reason: not valid java name */
    private static final boolean m695anyGBYM_sE(byte[] any) {
        boolean any2;
        Intrinsics.checkNotNullParameter(any, "$this$any");
        any2 = ArraysKt___ArraysKt.any(any);
        return any2;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: any-JOV_ifY  reason: not valid java name */
    private static final boolean m696anyJOV_ifY(byte[] any, Function1<? super UByte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(any, "$this$any");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int m263getSizeimpl = UByteArray.m263getSizeimpl(any);
        for (int i2 = 0; i2 < m263getSizeimpl; i2++) {
            if (predicate.invoke(UByte.m199boximpl(UByteArray.m262getw2LRezQ(any, i2))).booleanValue()) {
                return true;
            }
        }
        return false;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: any-MShoTSo  reason: not valid java name */
    private static final boolean m697anyMShoTSo(long[] any, Function1<? super ULong, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(any, "$this$any");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int m419getSizeimpl = ULongArray.m419getSizeimpl(any);
        for (int i2 = 0; i2 < m419getSizeimpl; i2++) {
            if (predicate.invoke(ULong.m353boximpl(ULongArray.m418getsVKNKU(any, i2))).booleanValue()) {
                return true;
            }
        }
        return false;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: any-QwZRm1k  reason: not valid java name */
    private static final boolean m698anyQwZRm1k(long[] any) {
        boolean any2;
        Intrinsics.checkNotNullParameter(any, "$this$any");
        any2 = ArraysKt___ArraysKt.any(any);
        return any2;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: any-jgv0xPQ  reason: not valid java name */
    private static final boolean m699anyjgv0xPQ(int[] any, Function1<? super UInt, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(any, "$this$any");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int m341getSizeimpl = UIntArray.m341getSizeimpl(any);
        for (int i2 = 0; i2 < m341getSizeimpl; i2++) {
            if (predicate.invoke(UInt.m275boximpl(UIntArray.m340getpVg5ArA(any, i2))).booleanValue()) {
                return true;
            }
        }
        return false;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: any-rL5Bavg  reason: not valid java name */
    private static final boolean m700anyrL5Bavg(short[] any) {
        boolean any2;
        Intrinsics.checkNotNullParameter(any, "$this$any");
        any2 = ArraysKt___ArraysKt.any(any);
        return any2;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: any-xTcfx_M  reason: not valid java name */
    private static final boolean m701anyxTcfx_M(short[] any, Function1<? super UShort, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(any, "$this$any");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int m523getSizeimpl = UShortArray.m523getSizeimpl(any);
        for (int i2 = 0; i2 < m523getSizeimpl; i2++) {
            if (predicate.invoke(UShort.m459boximpl(UShortArray.m522getMh2AYeg(any, i2))).booleanValue()) {
                return true;
            }
        }
        return false;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: asByteArray-GBYM_sE  reason: not valid java name */
    private static final byte[] m702asByteArrayGBYM_sE(byte[] asByteArray) {
        Intrinsics.checkNotNullParameter(asByteArray, "$this$asByteArray");
        return asByteArray;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: asIntArray--ajY-9A  reason: not valid java name */
    private static final int[] m703asIntArrayajY9A(int[] asIntArray) {
        Intrinsics.checkNotNullParameter(asIntArray, "$this$asIntArray");
        return asIntArray;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: asLongArray-QwZRm1k  reason: not valid java name */
    private static final long[] m704asLongArrayQwZRm1k(long[] asLongArray) {
        Intrinsics.checkNotNullParameter(asLongArray, "$this$asLongArray");
        return asLongArray;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: asShortArray-rL5Bavg  reason: not valid java name */
    private static final short[] m705asShortArrayrL5Bavg(short[] asShortArray) {
        Intrinsics.checkNotNullParameter(asShortArray, "$this$asShortArray");
        return asShortArray;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final byte[] asUByteArray(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        return UByteArray.m257constructorimpl(bArr);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final int[] asUIntArray(int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        return UIntArray.m335constructorimpl(iArr);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final long[] asULongArray(long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        return ULongArray.m413constructorimpl(jArr);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final short[] asUShortArray(short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        return UShortArray.m517constructorimpl(sArr);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: associateWith-JOV_ifY  reason: not valid java name */
    private static final <V> Map<UByte, V> m706associateWithJOV_ifY(byte[] associateWith, Function1<? super UByte, ? extends V> valueSelector) {
        int mapCapacity;
        int coerceAtLeast;
        Intrinsics.checkNotNullParameter(associateWith, "$this$associateWith");
        Intrinsics.checkNotNullParameter(valueSelector, "valueSelector");
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(UByteArray.m263getSizeimpl(associateWith));
        coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(mapCapacity, 16);
        LinkedHashMap linkedHashMap = new LinkedHashMap(coerceAtLeast);
        int m263getSizeimpl = UByteArray.m263getSizeimpl(associateWith);
        for (int i2 = 0; i2 < m263getSizeimpl; i2++) {
            byte m262getw2LRezQ = UByteArray.m262getw2LRezQ(associateWith, i2);
            linkedHashMap.put(UByte.m199boximpl(m262getw2LRezQ), valueSelector.invoke(UByte.m199boximpl(m262getw2LRezQ)));
        }
        return linkedHashMap;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: associateWith-MShoTSo  reason: not valid java name */
    private static final <V> Map<ULong, V> m707associateWithMShoTSo(long[] associateWith, Function1<? super ULong, ? extends V> valueSelector) {
        int mapCapacity;
        int coerceAtLeast;
        Intrinsics.checkNotNullParameter(associateWith, "$this$associateWith");
        Intrinsics.checkNotNullParameter(valueSelector, "valueSelector");
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(ULongArray.m419getSizeimpl(associateWith));
        coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(mapCapacity, 16);
        LinkedHashMap linkedHashMap = new LinkedHashMap(coerceAtLeast);
        int m419getSizeimpl = ULongArray.m419getSizeimpl(associateWith);
        for (int i2 = 0; i2 < m419getSizeimpl; i2++) {
            long m418getsVKNKU = ULongArray.m418getsVKNKU(associateWith, i2);
            linkedHashMap.put(ULong.m353boximpl(m418getsVKNKU), valueSelector.invoke(ULong.m353boximpl(m418getsVKNKU)));
        }
        return linkedHashMap;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: associateWith-jgv0xPQ  reason: not valid java name */
    private static final <V> Map<UInt, V> m708associateWithjgv0xPQ(int[] associateWith, Function1<? super UInt, ? extends V> valueSelector) {
        int mapCapacity;
        int coerceAtLeast;
        Intrinsics.checkNotNullParameter(associateWith, "$this$associateWith");
        Intrinsics.checkNotNullParameter(valueSelector, "valueSelector");
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(UIntArray.m341getSizeimpl(associateWith));
        coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(mapCapacity, 16);
        LinkedHashMap linkedHashMap = new LinkedHashMap(coerceAtLeast);
        int m341getSizeimpl = UIntArray.m341getSizeimpl(associateWith);
        for (int i2 = 0; i2 < m341getSizeimpl; i2++) {
            int m340getpVg5ArA = UIntArray.m340getpVg5ArA(associateWith, i2);
            linkedHashMap.put(UInt.m275boximpl(m340getpVg5ArA), valueSelector.invoke(UInt.m275boximpl(m340getpVg5ArA)));
        }
        return linkedHashMap;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: associateWith-xTcfx_M  reason: not valid java name */
    private static final <V> Map<UShort, V> m709associateWithxTcfx_M(short[] associateWith, Function1<? super UShort, ? extends V> valueSelector) {
        int mapCapacity;
        int coerceAtLeast;
        Intrinsics.checkNotNullParameter(associateWith, "$this$associateWith");
        Intrinsics.checkNotNullParameter(valueSelector, "valueSelector");
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(UShortArray.m523getSizeimpl(associateWith));
        coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(mapCapacity, 16);
        LinkedHashMap linkedHashMap = new LinkedHashMap(coerceAtLeast);
        int m523getSizeimpl = UShortArray.m523getSizeimpl(associateWith);
        for (int i2 = 0; i2 < m523getSizeimpl; i2++) {
            short m522getMh2AYeg = UShortArray.m522getMh2AYeg(associateWith, i2);
            linkedHashMap.put(UShort.m459boximpl(m522getMh2AYeg), valueSelector.invoke(UShort.m459boximpl(m522getMh2AYeg)));
        }
        return linkedHashMap;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: associateWithTo-4D70W2E  reason: not valid java name */
    private static final <V, M extends Map<? super UInt, ? super V>> M m710associateWithTo4D70W2E(int[] associateWithTo, M destination, Function1<? super UInt, ? extends V> valueSelector) {
        Intrinsics.checkNotNullParameter(associateWithTo, "$this$associateWithTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(valueSelector, "valueSelector");
        int m341getSizeimpl = UIntArray.m341getSizeimpl(associateWithTo);
        for (int i2 = 0; i2 < m341getSizeimpl; i2++) {
            int m340getpVg5ArA = UIntArray.m340getpVg5ArA(associateWithTo, i2);
            destination.put(UInt.m275boximpl(m340getpVg5ArA), valueSelector.invoke(UInt.m275boximpl(m340getpVg5ArA)));
        }
        return destination;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: associateWithTo-H21X9dk  reason: not valid java name */
    private static final <V, M extends Map<? super UByte, ? super V>> M m711associateWithToH21X9dk(byte[] associateWithTo, M destination, Function1<? super UByte, ? extends V> valueSelector) {
        Intrinsics.checkNotNullParameter(associateWithTo, "$this$associateWithTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(valueSelector, "valueSelector");
        int m263getSizeimpl = UByteArray.m263getSizeimpl(associateWithTo);
        for (int i2 = 0; i2 < m263getSizeimpl; i2++) {
            byte m262getw2LRezQ = UByteArray.m262getw2LRezQ(associateWithTo, i2);
            destination.put(UByte.m199boximpl(m262getw2LRezQ), valueSelector.invoke(UByte.m199boximpl(m262getw2LRezQ)));
        }
        return destination;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: associateWithTo-X6OPwNk  reason: not valid java name */
    private static final <V, M extends Map<? super ULong, ? super V>> M m712associateWithToX6OPwNk(long[] associateWithTo, M destination, Function1<? super ULong, ? extends V> valueSelector) {
        Intrinsics.checkNotNullParameter(associateWithTo, "$this$associateWithTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(valueSelector, "valueSelector");
        int m419getSizeimpl = ULongArray.m419getSizeimpl(associateWithTo);
        for (int i2 = 0; i2 < m419getSizeimpl; i2++) {
            long m418getsVKNKU = ULongArray.m418getsVKNKU(associateWithTo, i2);
            destination.put(ULong.m353boximpl(m418getsVKNKU), valueSelector.invoke(ULong.m353boximpl(m418getsVKNKU)));
        }
        return destination;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: associateWithTo-ciTST-8  reason: not valid java name */
    private static final <V, M extends Map<? super UShort, ? super V>> M m713associateWithTociTST8(short[] associateWithTo, M destination, Function1<? super UShort, ? extends V> valueSelector) {
        Intrinsics.checkNotNullParameter(associateWithTo, "$this$associateWithTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(valueSelector, "valueSelector");
        int m523getSizeimpl = UShortArray.m523getSizeimpl(associateWithTo);
        for (int i2 = 0; i2 < m523getSizeimpl; i2++) {
            short m522getMh2AYeg = UShortArray.m522getMh2AYeg(associateWithTo, i2);
            destination.put(UShort.m459boximpl(m522getMh2AYeg), valueSelector.invoke(UShort.m459boximpl(m522getMh2AYeg)));
        }
        return destination;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: component1--ajY-9A  reason: not valid java name */
    private static final int m714component1ajY9A(int[] component1) {
        Intrinsics.checkNotNullParameter(component1, "$this$component1");
        return UIntArray.m340getpVg5ArA(component1, 0);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: component1-GBYM_sE  reason: not valid java name */
    private static final byte m715component1GBYM_sE(byte[] component1) {
        Intrinsics.checkNotNullParameter(component1, "$this$component1");
        return UByteArray.m262getw2LRezQ(component1, 0);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: component1-QwZRm1k  reason: not valid java name */
    private static final long m716component1QwZRm1k(long[] component1) {
        Intrinsics.checkNotNullParameter(component1, "$this$component1");
        return ULongArray.m418getsVKNKU(component1, 0);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: component1-rL5Bavg  reason: not valid java name */
    private static final short m717component1rL5Bavg(short[] component1) {
        Intrinsics.checkNotNullParameter(component1, "$this$component1");
        return UShortArray.m522getMh2AYeg(component1, 0);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: component2--ajY-9A  reason: not valid java name */
    private static final int m718component2ajY9A(int[] component2) {
        Intrinsics.checkNotNullParameter(component2, "$this$component2");
        return UIntArray.m340getpVg5ArA(component2, 1);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: component2-GBYM_sE  reason: not valid java name */
    private static final byte m719component2GBYM_sE(byte[] component2) {
        Intrinsics.checkNotNullParameter(component2, "$this$component2");
        return UByteArray.m262getw2LRezQ(component2, 1);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: component2-QwZRm1k  reason: not valid java name */
    private static final long m720component2QwZRm1k(long[] component2) {
        Intrinsics.checkNotNullParameter(component2, "$this$component2");
        return ULongArray.m418getsVKNKU(component2, 1);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: component2-rL5Bavg  reason: not valid java name */
    private static final short m721component2rL5Bavg(short[] component2) {
        Intrinsics.checkNotNullParameter(component2, "$this$component2");
        return UShortArray.m522getMh2AYeg(component2, 1);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: component3--ajY-9A  reason: not valid java name */
    private static final int m722component3ajY9A(int[] component3) {
        Intrinsics.checkNotNullParameter(component3, "$this$component3");
        return UIntArray.m340getpVg5ArA(component3, 2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: component3-GBYM_sE  reason: not valid java name */
    private static final byte m723component3GBYM_sE(byte[] component3) {
        Intrinsics.checkNotNullParameter(component3, "$this$component3");
        return UByteArray.m262getw2LRezQ(component3, 2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: component3-QwZRm1k  reason: not valid java name */
    private static final long m724component3QwZRm1k(long[] component3) {
        Intrinsics.checkNotNullParameter(component3, "$this$component3");
        return ULongArray.m418getsVKNKU(component3, 2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: component3-rL5Bavg  reason: not valid java name */
    private static final short m725component3rL5Bavg(short[] component3) {
        Intrinsics.checkNotNullParameter(component3, "$this$component3");
        return UShortArray.m522getMh2AYeg(component3, 2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: component4--ajY-9A  reason: not valid java name */
    private static final int m726component4ajY9A(int[] component4) {
        Intrinsics.checkNotNullParameter(component4, "$this$component4");
        return UIntArray.m340getpVg5ArA(component4, 3);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: component4-GBYM_sE  reason: not valid java name */
    private static final byte m727component4GBYM_sE(byte[] component4) {
        Intrinsics.checkNotNullParameter(component4, "$this$component4");
        return UByteArray.m262getw2LRezQ(component4, 3);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: component4-QwZRm1k  reason: not valid java name */
    private static final long m728component4QwZRm1k(long[] component4) {
        Intrinsics.checkNotNullParameter(component4, "$this$component4");
        return ULongArray.m418getsVKNKU(component4, 3);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: component4-rL5Bavg  reason: not valid java name */
    private static final short m729component4rL5Bavg(short[] component4) {
        Intrinsics.checkNotNullParameter(component4, "$this$component4");
        return UShortArray.m522getMh2AYeg(component4, 3);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: component5--ajY-9A  reason: not valid java name */
    private static final int m730component5ajY9A(int[] component5) {
        Intrinsics.checkNotNullParameter(component5, "$this$component5");
        return UIntArray.m340getpVg5ArA(component5, 4);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: component5-GBYM_sE  reason: not valid java name */
    private static final byte m731component5GBYM_sE(byte[] component5) {
        Intrinsics.checkNotNullParameter(component5, "$this$component5");
        return UByteArray.m262getw2LRezQ(component5, 4);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: component5-QwZRm1k  reason: not valid java name */
    private static final long m732component5QwZRm1k(long[] component5) {
        Intrinsics.checkNotNullParameter(component5, "$this$component5");
        return ULongArray.m418getsVKNKU(component5, 4);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: component5-rL5Bavg  reason: not valid java name */
    private static final short m733component5rL5Bavg(short[] component5) {
        Intrinsics.checkNotNullParameter(component5, "$this$component5");
        return UShortArray.m522getMh2AYeg(component5, 4);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    /* renamed from: contentEquals-FGO6Aew  reason: not valid java name */
    public static boolean m734contentEqualsFGO6Aew(@Nullable short[] sArr, @Nullable short[] sArr2) {
        if (sArr == null) {
            sArr = null;
        }
        if (sArr2 == null) {
            sArr2 = null;
        }
        return Arrays.equals(sArr, sArr2);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    /* renamed from: contentEquals-KJPZfPQ  reason: not valid java name */
    public static boolean m735contentEqualsKJPZfPQ(@Nullable int[] iArr, @Nullable int[] iArr2) {
        if (iArr == null) {
            iArr = null;
        }
        if (iArr2 == null) {
            iArr2 = null;
        }
        return Arrays.equals(iArr, iArr2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Deprecated(message = "Use Kotlin compiler 1.4 to avoid deprecation warning.")
    @DeprecatedSinceKotlin(hiddenSince = "1.4")
    /* renamed from: contentEquals-ctEhBpI  reason: not valid java name */
    public static final /* synthetic */ boolean m736contentEqualsctEhBpI(int[] contentEquals, int[] other) {
        boolean m735contentEqualsKJPZfPQ;
        Intrinsics.checkNotNullParameter(contentEquals, "$this$contentEquals");
        Intrinsics.checkNotNullParameter(other, "other");
        m735contentEqualsKJPZfPQ = m735contentEqualsKJPZfPQ(contentEquals, other);
        return m735contentEqualsKJPZfPQ;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    /* renamed from: contentEquals-kV0jMPg  reason: not valid java name */
    public static boolean m737contentEqualskV0jMPg(@Nullable byte[] bArr, @Nullable byte[] bArr2) {
        if (bArr == null) {
            bArr = null;
        }
        if (bArr2 == null) {
            bArr2 = null;
        }
        return Arrays.equals(bArr, bArr2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Deprecated(message = "Use Kotlin compiler 1.4 to avoid deprecation warning.")
    @DeprecatedSinceKotlin(hiddenSince = "1.4")
    /* renamed from: contentEquals-kdPth3s  reason: not valid java name */
    public static final /* synthetic */ boolean m738contentEqualskdPth3s(byte[] contentEquals, byte[] other) {
        boolean m737contentEqualskV0jMPg;
        Intrinsics.checkNotNullParameter(contentEquals, "$this$contentEquals");
        Intrinsics.checkNotNullParameter(other, "other");
        m737contentEqualskV0jMPg = m737contentEqualskV0jMPg(contentEquals, other);
        return m737contentEqualskV0jMPg;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    /* renamed from: contentEquals-lec5QzE  reason: not valid java name */
    public static boolean m739contentEqualslec5QzE(@Nullable long[] jArr, @Nullable long[] jArr2) {
        if (jArr == null) {
            jArr = null;
        }
        if (jArr2 == null) {
            jArr2 = null;
        }
        return Arrays.equals(jArr, jArr2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Deprecated(message = "Use Kotlin compiler 1.4 to avoid deprecation warning.")
    @DeprecatedSinceKotlin(hiddenSince = "1.4")
    /* renamed from: contentEquals-mazbYpA  reason: not valid java name */
    public static final /* synthetic */ boolean m740contentEqualsmazbYpA(short[] contentEquals, short[] other) {
        boolean m734contentEqualsFGO6Aew;
        Intrinsics.checkNotNullParameter(contentEquals, "$this$contentEquals");
        Intrinsics.checkNotNullParameter(other, "other");
        m734contentEqualsFGO6Aew = m734contentEqualsFGO6Aew(contentEquals, other);
        return m734contentEqualsFGO6Aew;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Deprecated(message = "Use Kotlin compiler 1.4 to avoid deprecation warning.")
    @DeprecatedSinceKotlin(hiddenSince = "1.4")
    /* renamed from: contentEquals-us8wMrg  reason: not valid java name */
    public static final /* synthetic */ boolean m741contentEqualsus8wMrg(long[] contentEquals, long[] other) {
        boolean m739contentEqualslec5QzE;
        Intrinsics.checkNotNullParameter(contentEquals, "$this$contentEquals");
        Intrinsics.checkNotNullParameter(other, "other");
        m739contentEqualslec5QzE = m739contentEqualslec5QzE(contentEquals, other);
        return m739contentEqualslec5QzE;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    /* renamed from: contentHashCode-2csIQuQ  reason: not valid java name */
    public static final int m743contentHashCode2csIQuQ(@Nullable byte[] bArr) {
        if (bArr == null) {
            bArr = null;
        }
        return Arrays.hashCode(bArr);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    /* renamed from: contentHashCode-XUkPCBk  reason: not valid java name */
    public static final int m746contentHashCodeXUkPCBk(@Nullable int[] iArr) {
        if (iArr == null) {
            iArr = null;
        }
        return Arrays.hashCode(iArr);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    /* renamed from: contentHashCode-d-6D3K8  reason: not valid java name */
    public static final int m747contentHashCoded6D3K8(@Nullable short[] sArr) {
        if (sArr == null) {
            sArr = null;
        }
        return Arrays.hashCode(sArr);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    /* renamed from: contentHashCode-uLth9ew  reason: not valid java name */
    public static final int m749contentHashCodeuLth9ew(@Nullable long[] jArr) {
        if (jArr == null) {
            jArr = null;
        }
        return Arrays.hashCode(jArr);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Deprecated(message = "Use Kotlin compiler 1.4 to avoid deprecation warning.")
    @DeprecatedSinceKotlin(hiddenSince = "1.4")
    /* renamed from: contentToString--ajY-9A  reason: not valid java name */
    public static final /* synthetic */ String m750contentToStringajY9A(int[] contentToString) {
        String m754contentToStringXUkPCBk;
        Intrinsics.checkNotNullParameter(contentToString, "$this$contentToString");
        m754contentToStringXUkPCBk = m754contentToStringXUkPCBk(contentToString);
        return m754contentToStringXUkPCBk;
    }

    /* JADX WARN: Code restructure failed: missing block: B:3:0x0002, code lost:
        r9 = kotlin.collections.CollectionsKt___CollectionsKt.joinToString$default(kotlin.UByteArray.m255boximpl(r9), ", ", "[", "]", 0, null, null, 56, null);
     */
    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: contentToString-2csIQuQ  reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String m751contentToString2csIQuQ(@Nullable byte[] bArr) {
        String joinToString$default;
        return (bArr == null || joinToString$default == null) ? "null" : joinToString$default;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Deprecated(message = "Use Kotlin compiler 1.4 to avoid deprecation warning.")
    @DeprecatedSinceKotlin(hiddenSince = "1.4")
    /* renamed from: contentToString-GBYM_sE  reason: not valid java name */
    public static final /* synthetic */ String m752contentToStringGBYM_sE(byte[] contentToString) {
        String m751contentToString2csIQuQ;
        Intrinsics.checkNotNullParameter(contentToString, "$this$contentToString");
        m751contentToString2csIQuQ = m751contentToString2csIQuQ(contentToString);
        return m751contentToString2csIQuQ;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Deprecated(message = "Use Kotlin compiler 1.4 to avoid deprecation warning.")
    @DeprecatedSinceKotlin(hiddenSince = "1.4")
    /* renamed from: contentToString-QwZRm1k  reason: not valid java name */
    public static final /* synthetic */ String m753contentToStringQwZRm1k(long[] contentToString) {
        String m757contentToStringuLth9ew;
        Intrinsics.checkNotNullParameter(contentToString, "$this$contentToString");
        m757contentToStringuLth9ew = m757contentToStringuLth9ew(contentToString);
        return m757contentToStringuLth9ew;
    }

    /* JADX WARN: Code restructure failed: missing block: B:3:0x0002, code lost:
        r9 = kotlin.collections.CollectionsKt___CollectionsKt.joinToString$default(kotlin.UIntArray.m333boximpl(r9), ", ", "[", "]", 0, null, null, 56, null);
     */
    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: contentToString-XUkPCBk  reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String m754contentToStringXUkPCBk(@Nullable int[] iArr) {
        String joinToString$default;
        return (iArr == null || joinToString$default == null) ? "null" : joinToString$default;
    }

    /* JADX WARN: Code restructure failed: missing block: B:3:0x0002, code lost:
        r9 = kotlin.collections.CollectionsKt___CollectionsKt.joinToString$default(kotlin.UShortArray.m515boximpl(r9), ", ", "[", "]", 0, null, null, 56, null);
     */
    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: contentToString-d-6D3K8  reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String m755contentToStringd6D3K8(@Nullable short[] sArr) {
        String joinToString$default;
        return (sArr == null || joinToString$default == null) ? "null" : joinToString$default;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Deprecated(message = "Use Kotlin compiler 1.4 to avoid deprecation warning.")
    @DeprecatedSinceKotlin(hiddenSince = "1.4")
    /* renamed from: contentToString-rL5Bavg  reason: not valid java name */
    public static final /* synthetic */ String m756contentToStringrL5Bavg(short[] contentToString) {
        String m755contentToStringd6D3K8;
        Intrinsics.checkNotNullParameter(contentToString, "$this$contentToString");
        m755contentToStringd6D3K8 = m755contentToStringd6D3K8(contentToString);
        return m755contentToStringd6D3K8;
    }

    /* JADX WARN: Code restructure failed: missing block: B:3:0x0002, code lost:
        r9 = kotlin.collections.CollectionsKt___CollectionsKt.joinToString$default(kotlin.ULongArray.m411boximpl(r9), ", ", "[", "]", 0, null, null, 56, null);
     */
    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: contentToString-uLth9ew  reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String m757contentToStringuLth9ew(@Nullable long[] jArr) {
        String joinToString$default;
        return (jArr == null || joinToString$default == null) ? "null" : joinToString$default;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: copyInto--B0-L2c  reason: not valid java name */
    private static final long[] m758copyIntoB0L2c(long[] copyInto, long[] destination, int i2, int i3, int i4) {
        Intrinsics.checkNotNullParameter(copyInto, "$this$copyInto");
        Intrinsics.checkNotNullParameter(destination, "destination");
        ArraysKt___ArraysJvmKt.copyInto(copyInto, destination, i2, i3, i4);
        return destination;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: copyInto-9-ak10g  reason: not valid java name */
    private static final short[] m759copyInto9ak10g(short[] copyInto, short[] destination, int i2, int i3, int i4) {
        Intrinsics.checkNotNullParameter(copyInto, "$this$copyInto");
        Intrinsics.checkNotNullParameter(destination, "destination");
        ArraysKt___ArraysJvmKt.copyInto(copyInto, destination, i2, i3, i4);
        return destination;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: copyInto-FUQE5sA  reason: not valid java name */
    private static final byte[] m760copyIntoFUQE5sA(byte[] copyInto, byte[] destination, int i2, int i3, int i4) {
        Intrinsics.checkNotNullParameter(copyInto, "$this$copyInto");
        Intrinsics.checkNotNullParameter(destination, "destination");
        ArraysKt___ArraysJvmKt.copyInto(copyInto, destination, i2, i3, i4);
        return destination;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: copyInto-sIZ3KeM  reason: not valid java name */
    private static final int[] m761copyIntosIZ3KeM(int[] copyInto, int[] destination, int i2, int i3, int i4) {
        Intrinsics.checkNotNullParameter(copyInto, "$this$copyInto");
        Intrinsics.checkNotNullParameter(destination, "destination");
        ArraysKt___ArraysJvmKt.copyInto(copyInto, destination, i2, i3, i4);
        return destination;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: copyOf--ajY-9A  reason: not valid java name */
    private static final int[] m762copyOfajY9A(int[] copyOf) {
        Intrinsics.checkNotNullParameter(copyOf, "$this$copyOf");
        int[] copyOf2 = Arrays.copyOf(copyOf, copyOf.length);
        Intrinsics.checkNotNullExpressionValue(copyOf2, "copyOf(this, size)");
        return UIntArray.m335constructorimpl(copyOf2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: copyOf-GBYM_sE  reason: not valid java name */
    private static final byte[] m763copyOfGBYM_sE(byte[] copyOf) {
        Intrinsics.checkNotNullParameter(copyOf, "$this$copyOf");
        byte[] copyOf2 = Arrays.copyOf(copyOf, copyOf.length);
        Intrinsics.checkNotNullExpressionValue(copyOf2, "copyOf(this, size)");
        return UByteArray.m257constructorimpl(copyOf2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: copyOf-PpDY95g  reason: not valid java name */
    private static final byte[] m764copyOfPpDY95g(byte[] copyOf, int i2) {
        Intrinsics.checkNotNullParameter(copyOf, "$this$copyOf");
        byte[] copyOf2 = Arrays.copyOf(copyOf, i2);
        Intrinsics.checkNotNullExpressionValue(copyOf2, "copyOf(this, newSize)");
        return UByteArray.m257constructorimpl(copyOf2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: copyOf-QwZRm1k  reason: not valid java name */
    private static final long[] m765copyOfQwZRm1k(long[] copyOf) {
        Intrinsics.checkNotNullParameter(copyOf, "$this$copyOf");
        long[] copyOf2 = Arrays.copyOf(copyOf, copyOf.length);
        Intrinsics.checkNotNullExpressionValue(copyOf2, "copyOf(this, size)");
        return ULongArray.m413constructorimpl(copyOf2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: copyOf-nggk6HY  reason: not valid java name */
    private static final short[] m766copyOfnggk6HY(short[] copyOf, int i2) {
        Intrinsics.checkNotNullParameter(copyOf, "$this$copyOf");
        short[] copyOf2 = Arrays.copyOf(copyOf, i2);
        Intrinsics.checkNotNullExpressionValue(copyOf2, "copyOf(this, newSize)");
        return UShortArray.m517constructorimpl(copyOf2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: copyOf-qFRl0hI  reason: not valid java name */
    private static final int[] m767copyOfqFRl0hI(int[] copyOf, int i2) {
        Intrinsics.checkNotNullParameter(copyOf, "$this$copyOf");
        int[] copyOf2 = Arrays.copyOf(copyOf, i2);
        Intrinsics.checkNotNullExpressionValue(copyOf2, "copyOf(this, newSize)");
        return UIntArray.m335constructorimpl(copyOf2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: copyOf-r7IrZao  reason: not valid java name */
    private static final long[] m768copyOfr7IrZao(long[] copyOf, int i2) {
        Intrinsics.checkNotNullParameter(copyOf, "$this$copyOf");
        long[] copyOf2 = Arrays.copyOf(copyOf, i2);
        Intrinsics.checkNotNullExpressionValue(copyOf2, "copyOf(this, newSize)");
        return ULongArray.m413constructorimpl(copyOf2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: copyOf-rL5Bavg  reason: not valid java name */
    private static final short[] m769copyOfrL5Bavg(short[] copyOf) {
        Intrinsics.checkNotNullParameter(copyOf, "$this$copyOf");
        short[] copyOf2 = Arrays.copyOf(copyOf, copyOf.length);
        Intrinsics.checkNotNullExpressionValue(copyOf2, "copyOf(this, size)");
        return UShortArray.m517constructorimpl(copyOf2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: copyOfRange--nroSd4  reason: not valid java name */
    private static final long[] m770copyOfRangenroSd4(long[] copyOfRange, int i2, int i3) {
        long[] copyOfRange2;
        Intrinsics.checkNotNullParameter(copyOfRange, "$this$copyOfRange");
        copyOfRange2 = ArraysKt___ArraysJvmKt.copyOfRange(copyOfRange, i2, i3);
        return ULongArray.m413constructorimpl(copyOfRange2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: copyOfRange-4UcCI2c  reason: not valid java name */
    private static final byte[] m771copyOfRange4UcCI2c(byte[] copyOfRange, int i2, int i3) {
        byte[] copyOfRange2;
        Intrinsics.checkNotNullParameter(copyOfRange, "$this$copyOfRange");
        copyOfRange2 = ArraysKt___ArraysJvmKt.copyOfRange(copyOfRange, i2, i3);
        return UByteArray.m257constructorimpl(copyOfRange2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: copyOfRange-Aa5vz7o  reason: not valid java name */
    private static final short[] m772copyOfRangeAa5vz7o(short[] copyOfRange, int i2, int i3) {
        short[] copyOfRange2;
        Intrinsics.checkNotNullParameter(copyOfRange, "$this$copyOfRange");
        copyOfRange2 = ArraysKt___ArraysJvmKt.copyOfRange(copyOfRange, i2, i3);
        return UShortArray.m517constructorimpl(copyOfRange2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: copyOfRange-oBK06Vg  reason: not valid java name */
    private static final int[] m773copyOfRangeoBK06Vg(int[] copyOfRange, int i2, int i3) {
        int[] copyOfRange2;
        Intrinsics.checkNotNullParameter(copyOfRange, "$this$copyOfRange");
        copyOfRange2 = ArraysKt___ArraysJvmKt.copyOfRange(copyOfRange, i2, i3);
        return UIntArray.m335constructorimpl(copyOfRange2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: count-JOV_ifY  reason: not valid java name */
    private static final int m774countJOV_ifY(byte[] count, Function1<? super UByte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(count, "$this$count");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int m263getSizeimpl = UByteArray.m263getSizeimpl(count);
        int i2 = 0;
        for (int i3 = 0; i3 < m263getSizeimpl; i3++) {
            if (predicate.invoke(UByte.m199boximpl(UByteArray.m262getw2LRezQ(count, i3))).booleanValue()) {
                i2++;
            }
        }
        return i2;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: count-MShoTSo  reason: not valid java name */
    private static final int m775countMShoTSo(long[] count, Function1<? super ULong, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(count, "$this$count");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int m419getSizeimpl = ULongArray.m419getSizeimpl(count);
        int i2 = 0;
        for (int i3 = 0; i3 < m419getSizeimpl; i3++) {
            if (predicate.invoke(ULong.m353boximpl(ULongArray.m418getsVKNKU(count, i3))).booleanValue()) {
                i2++;
            }
        }
        return i2;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: count-jgv0xPQ  reason: not valid java name */
    private static final int m776countjgv0xPQ(int[] count, Function1<? super UInt, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(count, "$this$count");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int m341getSizeimpl = UIntArray.m341getSizeimpl(count);
        int i2 = 0;
        for (int i3 = 0; i3 < m341getSizeimpl; i3++) {
            if (predicate.invoke(UInt.m275boximpl(UIntArray.m340getpVg5ArA(count, i3))).booleanValue()) {
                i2++;
            }
        }
        return i2;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: count-xTcfx_M  reason: not valid java name */
    private static final int m777countxTcfx_M(short[] count, Function1<? super UShort, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(count, "$this$count");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int m523getSizeimpl = UShortArray.m523getSizeimpl(count);
        int i2 = 0;
        for (int i3 = 0; i3 < m523getSizeimpl; i3++) {
            if (predicate.invoke(UShort.m459boximpl(UShortArray.m522getMh2AYeg(count, i3))).booleanValue()) {
                i2++;
            }
        }
        return i2;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: drop-PpDY95g  reason: not valid java name */
    public static final List<UByte> m778dropPpDY95g(@NotNull byte[] drop, int i2) {
        int coerceAtLeast;
        Intrinsics.checkNotNullParameter(drop, "$this$drop");
        if (i2 >= 0) {
            coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(UByteArray.m263getSizeimpl(drop) - i2, 0);
            return m1306takeLastPpDY95g(drop, coerceAtLeast);
        }
        throw new IllegalArgumentException(("Requested element count " + i2 + " is less than zero.").toString());
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: drop-nggk6HY  reason: not valid java name */
    public static final List<UShort> m779dropnggk6HY(@NotNull short[] drop, int i2) {
        int coerceAtLeast;
        Intrinsics.checkNotNullParameter(drop, "$this$drop");
        if (i2 >= 0) {
            coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(UShortArray.m523getSizeimpl(drop) - i2, 0);
            return m1307takeLastnggk6HY(drop, coerceAtLeast);
        }
        throw new IllegalArgumentException(("Requested element count " + i2 + " is less than zero.").toString());
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: drop-qFRl0hI  reason: not valid java name */
    public static final List<UInt> m780dropqFRl0hI(@NotNull int[] drop, int i2) {
        int coerceAtLeast;
        Intrinsics.checkNotNullParameter(drop, "$this$drop");
        if (i2 >= 0) {
            coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(UIntArray.m341getSizeimpl(drop) - i2, 0);
            return m1308takeLastqFRl0hI(drop, coerceAtLeast);
        }
        throw new IllegalArgumentException(("Requested element count " + i2 + " is less than zero.").toString());
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: drop-r7IrZao  reason: not valid java name */
    public static final List<ULong> m781dropr7IrZao(@NotNull long[] drop, int i2) {
        int coerceAtLeast;
        Intrinsics.checkNotNullParameter(drop, "$this$drop");
        if (i2 >= 0) {
            coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(ULongArray.m419getSizeimpl(drop) - i2, 0);
            return m1309takeLastr7IrZao(drop, coerceAtLeast);
        }
        throw new IllegalArgumentException(("Requested element count " + i2 + " is less than zero.").toString());
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: dropLast-PpDY95g  reason: not valid java name */
    public static final List<UByte> m782dropLastPpDY95g(@NotNull byte[] dropLast, int i2) {
        int coerceAtLeast;
        Intrinsics.checkNotNullParameter(dropLast, "$this$dropLast");
        if (i2 >= 0) {
            coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(UByteArray.m263getSizeimpl(dropLast) - i2, 0);
            return m1302takePpDY95g(dropLast, coerceAtLeast);
        }
        throw new IllegalArgumentException(("Requested element count " + i2 + " is less than zero.").toString());
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: dropLast-nggk6HY  reason: not valid java name */
    public static final List<UShort> m783dropLastnggk6HY(@NotNull short[] dropLast, int i2) {
        int coerceAtLeast;
        Intrinsics.checkNotNullParameter(dropLast, "$this$dropLast");
        if (i2 >= 0) {
            coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(UShortArray.m523getSizeimpl(dropLast) - i2, 0);
            return m1303takenggk6HY(dropLast, coerceAtLeast);
        }
        throw new IllegalArgumentException(("Requested element count " + i2 + " is less than zero.").toString());
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: dropLast-qFRl0hI  reason: not valid java name */
    public static final List<UInt> m784dropLastqFRl0hI(@NotNull int[] dropLast, int i2) {
        int coerceAtLeast;
        Intrinsics.checkNotNullParameter(dropLast, "$this$dropLast");
        if (i2 >= 0) {
            coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(UIntArray.m341getSizeimpl(dropLast) - i2, 0);
            return m1304takeqFRl0hI(dropLast, coerceAtLeast);
        }
        throw new IllegalArgumentException(("Requested element count " + i2 + " is less than zero.").toString());
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: dropLast-r7IrZao  reason: not valid java name */
    public static final List<ULong> m785dropLastr7IrZao(@NotNull long[] dropLast, int i2) {
        int coerceAtLeast;
        Intrinsics.checkNotNullParameter(dropLast, "$this$dropLast");
        if (i2 >= 0) {
            coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(ULongArray.m419getSizeimpl(dropLast) - i2, 0);
            return m1305taker7IrZao(dropLast, coerceAtLeast);
        }
        throw new IllegalArgumentException(("Requested element count " + i2 + " is less than zero.").toString());
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: dropLastWhile-JOV_ifY  reason: not valid java name */
    private static final List<UByte> m786dropLastWhileJOV_ifY(byte[] dropLastWhile, Function1<? super UByte, Boolean> predicate) {
        int lastIndex;
        List<UByte> emptyList;
        Intrinsics.checkNotNullParameter(dropLastWhile, "$this$dropLastWhile");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (lastIndex = ArraysKt___ArraysKt.getLastIndex(dropLastWhile); -1 < lastIndex; lastIndex--) {
            if (!predicate.invoke(UByte.m199boximpl(UByteArray.m262getw2LRezQ(dropLastWhile, lastIndex))).booleanValue()) {
                return m1302takePpDY95g(dropLastWhile, lastIndex + 1);
            }
        }
        emptyList = CollectionsKt__CollectionsKt.emptyList();
        return emptyList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: dropLastWhile-MShoTSo  reason: not valid java name */
    private static final List<ULong> m787dropLastWhileMShoTSo(long[] dropLastWhile, Function1<? super ULong, Boolean> predicate) {
        int lastIndex;
        List<ULong> emptyList;
        Intrinsics.checkNotNullParameter(dropLastWhile, "$this$dropLastWhile");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (lastIndex = ArraysKt___ArraysKt.getLastIndex(dropLastWhile); -1 < lastIndex; lastIndex--) {
            if (!predicate.invoke(ULong.m353boximpl(ULongArray.m418getsVKNKU(dropLastWhile, lastIndex))).booleanValue()) {
                return m1305taker7IrZao(dropLastWhile, lastIndex + 1);
            }
        }
        emptyList = CollectionsKt__CollectionsKt.emptyList();
        return emptyList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: dropLastWhile-jgv0xPQ  reason: not valid java name */
    private static final List<UInt> m788dropLastWhilejgv0xPQ(int[] dropLastWhile, Function1<? super UInt, Boolean> predicate) {
        int lastIndex;
        List<UInt> emptyList;
        Intrinsics.checkNotNullParameter(dropLastWhile, "$this$dropLastWhile");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (lastIndex = ArraysKt___ArraysKt.getLastIndex(dropLastWhile); -1 < lastIndex; lastIndex--) {
            if (!predicate.invoke(UInt.m275boximpl(UIntArray.m340getpVg5ArA(dropLastWhile, lastIndex))).booleanValue()) {
                return m1304takeqFRl0hI(dropLastWhile, lastIndex + 1);
            }
        }
        emptyList = CollectionsKt__CollectionsKt.emptyList();
        return emptyList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: dropLastWhile-xTcfx_M  reason: not valid java name */
    private static final List<UShort> m789dropLastWhilexTcfx_M(short[] dropLastWhile, Function1<? super UShort, Boolean> predicate) {
        int lastIndex;
        List<UShort> emptyList;
        Intrinsics.checkNotNullParameter(dropLastWhile, "$this$dropLastWhile");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (lastIndex = ArraysKt___ArraysKt.getLastIndex(dropLastWhile); -1 < lastIndex; lastIndex--) {
            if (!predicate.invoke(UShort.m459boximpl(UShortArray.m522getMh2AYeg(dropLastWhile, lastIndex))).booleanValue()) {
                return m1303takenggk6HY(dropLastWhile, lastIndex + 1);
            }
        }
        emptyList = CollectionsKt__CollectionsKt.emptyList();
        return emptyList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: dropWhile-JOV_ifY  reason: not valid java name */
    private static final List<UByte> m790dropWhileJOV_ifY(byte[] dropWhile, Function1<? super UByte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(dropWhile, "$this$dropWhile");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        int m263getSizeimpl = UByteArray.m263getSizeimpl(dropWhile);
        boolean z = false;
        for (int i2 = 0; i2 < m263getSizeimpl; i2++) {
            byte m262getw2LRezQ = UByteArray.m262getw2LRezQ(dropWhile, i2);
            if (z) {
                arrayList.add(UByte.m199boximpl(m262getw2LRezQ));
            } else if (!predicate.invoke(UByte.m199boximpl(m262getw2LRezQ)).booleanValue()) {
                arrayList.add(UByte.m199boximpl(m262getw2LRezQ));
                z = true;
            }
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: dropWhile-MShoTSo  reason: not valid java name */
    private static final List<ULong> m791dropWhileMShoTSo(long[] dropWhile, Function1<? super ULong, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(dropWhile, "$this$dropWhile");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        int m419getSizeimpl = ULongArray.m419getSizeimpl(dropWhile);
        boolean z = false;
        for (int i2 = 0; i2 < m419getSizeimpl; i2++) {
            long m418getsVKNKU = ULongArray.m418getsVKNKU(dropWhile, i2);
            if (z) {
                arrayList.add(ULong.m353boximpl(m418getsVKNKU));
            } else if (!predicate.invoke(ULong.m353boximpl(m418getsVKNKU)).booleanValue()) {
                arrayList.add(ULong.m353boximpl(m418getsVKNKU));
                z = true;
            }
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: dropWhile-jgv0xPQ  reason: not valid java name */
    private static final List<UInt> m792dropWhilejgv0xPQ(int[] dropWhile, Function1<? super UInt, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(dropWhile, "$this$dropWhile");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        int m341getSizeimpl = UIntArray.m341getSizeimpl(dropWhile);
        boolean z = false;
        for (int i2 = 0; i2 < m341getSizeimpl; i2++) {
            int m340getpVg5ArA = UIntArray.m340getpVg5ArA(dropWhile, i2);
            if (z) {
                arrayList.add(UInt.m275boximpl(m340getpVg5ArA));
            } else if (!predicate.invoke(UInt.m275boximpl(m340getpVg5ArA)).booleanValue()) {
                arrayList.add(UInt.m275boximpl(m340getpVg5ArA));
                z = true;
            }
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: dropWhile-xTcfx_M  reason: not valid java name */
    private static final List<UShort> m793dropWhilexTcfx_M(short[] dropWhile, Function1<? super UShort, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(dropWhile, "$this$dropWhile");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        int m523getSizeimpl = UShortArray.m523getSizeimpl(dropWhile);
        boolean z = false;
        for (int i2 = 0; i2 < m523getSizeimpl; i2++) {
            short m522getMh2AYeg = UShortArray.m522getMh2AYeg(dropWhile, i2);
            if (z) {
                arrayList.add(UShort.m459boximpl(m522getMh2AYeg));
            } else if (!predicate.invoke(UShort.m459boximpl(m522getMh2AYeg)).booleanValue()) {
                arrayList.add(UShort.m459boximpl(m522getMh2AYeg));
                z = true;
            }
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: elementAtOrElse-CVVdw08  reason: not valid java name */
    private static final short m794elementAtOrElseCVVdw08(short[] elementAtOrElse, int i2, Function1<? super Integer, UShort> defaultValue) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(elementAtOrElse, "$this$elementAtOrElse");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        if (i2 >= 0) {
            lastIndex = ArraysKt___ArraysKt.getLastIndex(elementAtOrElse);
            if (i2 <= lastIndex) {
                return UShortArray.m522getMh2AYeg(elementAtOrElse, i2);
            }
        }
        return defaultValue.invoke(Integer.valueOf(i2)).m514unboximpl();
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: elementAtOrElse-QxvSvLU  reason: not valid java name */
    private static final int m795elementAtOrElseQxvSvLU(int[] elementAtOrElse, int i2, Function1<? super Integer, UInt> defaultValue) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(elementAtOrElse, "$this$elementAtOrElse");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        if (i2 >= 0) {
            lastIndex = ArraysKt___ArraysKt.getLastIndex(elementAtOrElse);
            if (i2 <= lastIndex) {
                return UIntArray.m340getpVg5ArA(elementAtOrElse, i2);
            }
        }
        return defaultValue.invoke(Integer.valueOf(i2)).m332unboximpl();
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: elementAtOrElse-Xw8i6dc  reason: not valid java name */
    private static final long m796elementAtOrElseXw8i6dc(long[] elementAtOrElse, int i2, Function1<? super Integer, ULong> defaultValue) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(elementAtOrElse, "$this$elementAtOrElse");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        if (i2 >= 0) {
            lastIndex = ArraysKt___ArraysKt.getLastIndex(elementAtOrElse);
            if (i2 <= lastIndex) {
                return ULongArray.m418getsVKNKU(elementAtOrElse, i2);
            }
        }
        return defaultValue.invoke(Integer.valueOf(i2)).m410unboximpl();
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: elementAtOrElse-cO-VybQ  reason: not valid java name */
    private static final byte m797elementAtOrElsecOVybQ(byte[] elementAtOrElse, int i2, Function1<? super Integer, UByte> defaultValue) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(elementAtOrElse, "$this$elementAtOrElse");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        if (i2 >= 0) {
            lastIndex = ArraysKt___ArraysKt.getLastIndex(elementAtOrElse);
            if (i2 <= lastIndex) {
                return UByteArray.m262getw2LRezQ(elementAtOrElse, i2);
            }
        }
        return defaultValue.invoke(Integer.valueOf(i2)).m254unboximpl();
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: elementAtOrNull-PpDY95g  reason: not valid java name */
    private static final UByte m798elementAtOrNullPpDY95g(byte[] elementAtOrNull, int i2) {
        Intrinsics.checkNotNullParameter(elementAtOrNull, "$this$elementAtOrNull");
        return m918getOrNullPpDY95g(elementAtOrNull, i2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: elementAtOrNull-nggk6HY  reason: not valid java name */
    private static final UShort m799elementAtOrNullnggk6HY(short[] elementAtOrNull, int i2) {
        Intrinsics.checkNotNullParameter(elementAtOrNull, "$this$elementAtOrNull");
        return m919getOrNullnggk6HY(elementAtOrNull, i2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: elementAtOrNull-qFRl0hI  reason: not valid java name */
    private static final UInt m800elementAtOrNullqFRl0hI(int[] elementAtOrNull, int i2) {
        Intrinsics.checkNotNullParameter(elementAtOrNull, "$this$elementAtOrNull");
        return m920getOrNullqFRl0hI(elementAtOrNull, i2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: elementAtOrNull-r7IrZao  reason: not valid java name */
    private static final ULong m801elementAtOrNullr7IrZao(long[] elementAtOrNull, int i2) {
        Intrinsics.checkNotNullParameter(elementAtOrNull, "$this$elementAtOrNull");
        return m921getOrNullr7IrZao(elementAtOrNull, i2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: fill-2fe2U9s  reason: not valid java name */
    public static final void m802fill2fe2U9s(@NotNull int[] fill, int i2, int i3, int i4) {
        Intrinsics.checkNotNullParameter(fill, "$this$fill");
        ArraysKt___ArraysJvmKt.fill(fill, i2, i3, i4);
    }

    /* renamed from: fill-2fe2U9s$default  reason: not valid java name */
    public static /* synthetic */ void m803fill2fe2U9s$default(int[] iArr, int i2, int i3, int i4, int i5, Object obj) {
        if ((i5 & 2) != 0) {
            i3 = 0;
        }
        if ((i5 & 4) != 0) {
            i4 = UIntArray.m341getSizeimpl(iArr);
        }
        m802fill2fe2U9s(iArr, i2, i3, i4);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: fill-EtDCXyQ  reason: not valid java name */
    public static final void m804fillEtDCXyQ(@NotNull short[] fill, short s2, int i2, int i3) {
        Intrinsics.checkNotNullParameter(fill, "$this$fill");
        ArraysKt___ArraysJvmKt.fill(fill, s2, i2, i3);
    }

    /* renamed from: fill-EtDCXyQ$default  reason: not valid java name */
    public static /* synthetic */ void m805fillEtDCXyQ$default(short[] sArr, short s2, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i2 = 0;
        }
        if ((i4 & 4) != 0) {
            i3 = UShortArray.m523getSizeimpl(sArr);
        }
        m804fillEtDCXyQ(sArr, s2, i2, i3);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: fill-K6DWlUc  reason: not valid java name */
    public static final void m806fillK6DWlUc(@NotNull long[] fill, long j2, int i2, int i3) {
        Intrinsics.checkNotNullParameter(fill, "$this$fill");
        ArraysKt___ArraysJvmKt.fill(fill, j2, i2, i3);
    }

    /* renamed from: fill-K6DWlUc$default  reason: not valid java name */
    public static /* synthetic */ void m807fillK6DWlUc$default(long[] jArr, long j2, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i2 = 0;
        }
        if ((i4 & 4) != 0) {
            i3 = ULongArray.m419getSizeimpl(jArr);
        }
        m806fillK6DWlUc(jArr, j2, i2, i3);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: fill-WpHrYlw  reason: not valid java name */
    public static final void m808fillWpHrYlw(@NotNull byte[] fill, byte b2, int i2, int i3) {
        Intrinsics.checkNotNullParameter(fill, "$this$fill");
        ArraysKt___ArraysJvmKt.fill(fill, b2, i2, i3);
    }

    /* renamed from: fill-WpHrYlw$default  reason: not valid java name */
    public static /* synthetic */ void m809fillWpHrYlw$default(byte[] bArr, byte b2, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i2 = 0;
        }
        if ((i4 & 4) != 0) {
            i3 = UByteArray.m263getSizeimpl(bArr);
        }
        m808fillWpHrYlw(bArr, b2, i2, i3);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: filter-JOV_ifY  reason: not valid java name */
    private static final List<UByte> m810filterJOV_ifY(byte[] filter, Function1<? super UByte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(filter, "$this$filter");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        int m263getSizeimpl = UByteArray.m263getSizeimpl(filter);
        for (int i2 = 0; i2 < m263getSizeimpl; i2++) {
            byte m262getw2LRezQ = UByteArray.m262getw2LRezQ(filter, i2);
            if (predicate.invoke(UByte.m199boximpl(m262getw2LRezQ)).booleanValue()) {
                arrayList.add(UByte.m199boximpl(m262getw2LRezQ));
            }
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: filter-MShoTSo  reason: not valid java name */
    private static final List<ULong> m811filterMShoTSo(long[] filter, Function1<? super ULong, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(filter, "$this$filter");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        int m419getSizeimpl = ULongArray.m419getSizeimpl(filter);
        for (int i2 = 0; i2 < m419getSizeimpl; i2++) {
            long m418getsVKNKU = ULongArray.m418getsVKNKU(filter, i2);
            if (predicate.invoke(ULong.m353boximpl(m418getsVKNKU)).booleanValue()) {
                arrayList.add(ULong.m353boximpl(m418getsVKNKU));
            }
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: filter-jgv0xPQ  reason: not valid java name */
    private static final List<UInt> m812filterjgv0xPQ(int[] filter, Function1<? super UInt, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(filter, "$this$filter");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        int m341getSizeimpl = UIntArray.m341getSizeimpl(filter);
        for (int i2 = 0; i2 < m341getSizeimpl; i2++) {
            int m340getpVg5ArA = UIntArray.m340getpVg5ArA(filter, i2);
            if (predicate.invoke(UInt.m275boximpl(m340getpVg5ArA)).booleanValue()) {
                arrayList.add(UInt.m275boximpl(m340getpVg5ArA));
            }
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: filter-xTcfx_M  reason: not valid java name */
    private static final List<UShort> m813filterxTcfx_M(short[] filter, Function1<? super UShort, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(filter, "$this$filter");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        int m523getSizeimpl = UShortArray.m523getSizeimpl(filter);
        for (int i2 = 0; i2 < m523getSizeimpl; i2++) {
            short m522getMh2AYeg = UShortArray.m522getMh2AYeg(filter, i2);
            if (predicate.invoke(UShort.m459boximpl(m522getMh2AYeg)).booleanValue()) {
                arrayList.add(UShort.m459boximpl(m522getMh2AYeg));
            }
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: filterIndexed-ELGow60  reason: not valid java name */
    private static final List<UByte> m814filterIndexedELGow60(byte[] filterIndexed, Function2<? super Integer, ? super UByte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(filterIndexed, "$this$filterIndexed");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        int m263getSizeimpl = UByteArray.m263getSizeimpl(filterIndexed);
        int i2 = 0;
        int i3 = 0;
        while (i2 < m263getSizeimpl) {
            byte m262getw2LRezQ = UByteArray.m262getw2LRezQ(filterIndexed, i2);
            int i4 = i3 + 1;
            if (predicate.invoke(Integer.valueOf(i3), UByte.m199boximpl(m262getw2LRezQ)).booleanValue()) {
                arrayList.add(UByte.m199boximpl(m262getw2LRezQ));
            }
            i2++;
            i3 = i4;
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: filterIndexed-WyvcNBI  reason: not valid java name */
    private static final List<UInt> m815filterIndexedWyvcNBI(int[] filterIndexed, Function2<? super Integer, ? super UInt, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(filterIndexed, "$this$filterIndexed");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        int m341getSizeimpl = UIntArray.m341getSizeimpl(filterIndexed);
        int i2 = 0;
        int i3 = 0;
        while (i2 < m341getSizeimpl) {
            int m340getpVg5ArA = UIntArray.m340getpVg5ArA(filterIndexed, i2);
            int i4 = i3 + 1;
            if (predicate.invoke(Integer.valueOf(i3), UInt.m275boximpl(m340getpVg5ArA)).booleanValue()) {
                arrayList.add(UInt.m275boximpl(m340getpVg5ArA));
            }
            i2++;
            i3 = i4;
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: filterIndexed-s8dVfGU  reason: not valid java name */
    private static final List<ULong> m816filterIndexeds8dVfGU(long[] filterIndexed, Function2<? super Integer, ? super ULong, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(filterIndexed, "$this$filterIndexed");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        int m419getSizeimpl = ULongArray.m419getSizeimpl(filterIndexed);
        int i2 = 0;
        int i3 = 0;
        while (i2 < m419getSizeimpl) {
            long m418getsVKNKU = ULongArray.m418getsVKNKU(filterIndexed, i2);
            int i4 = i3 + 1;
            if (predicate.invoke(Integer.valueOf(i3), ULong.m353boximpl(m418getsVKNKU)).booleanValue()) {
                arrayList.add(ULong.m353boximpl(m418getsVKNKU));
            }
            i2++;
            i3 = i4;
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: filterIndexed-xzaTVY8  reason: not valid java name */
    private static final List<UShort> m817filterIndexedxzaTVY8(short[] filterIndexed, Function2<? super Integer, ? super UShort, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(filterIndexed, "$this$filterIndexed");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        int m523getSizeimpl = UShortArray.m523getSizeimpl(filterIndexed);
        int i2 = 0;
        int i3 = 0;
        while (i2 < m523getSizeimpl) {
            short m522getMh2AYeg = UShortArray.m522getMh2AYeg(filterIndexed, i2);
            int i4 = i3 + 1;
            if (predicate.invoke(Integer.valueOf(i3), UShort.m459boximpl(m522getMh2AYeg)).booleanValue()) {
                arrayList.add(UShort.m459boximpl(m522getMh2AYeg));
            }
            i2++;
            i3 = i4;
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: filterIndexedTo--6EtJGI  reason: not valid java name */
    private static final <C extends Collection<? super UInt>> C m818filterIndexedTo6EtJGI(int[] filterIndexedTo, C destination, Function2<? super Integer, ? super UInt, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(filterIndexedTo, "$this$filterIndexedTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int m341getSizeimpl = UIntArray.m341getSizeimpl(filterIndexedTo);
        int i2 = 0;
        int i3 = 0;
        while (i2 < m341getSizeimpl) {
            int m340getpVg5ArA = UIntArray.m340getpVg5ArA(filterIndexedTo, i2);
            int i4 = i3 + 1;
            if (predicate.invoke(Integer.valueOf(i3), UInt.m275boximpl(m340getpVg5ArA)).booleanValue()) {
                destination.add(UInt.m275boximpl(m340getpVg5ArA));
            }
            i2++;
            i3 = i4;
        }
        return destination;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: filterIndexedTo-QqktQ3k  reason: not valid java name */
    private static final <C extends Collection<? super UShort>> C m819filterIndexedToQqktQ3k(short[] filterIndexedTo, C destination, Function2<? super Integer, ? super UShort, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(filterIndexedTo, "$this$filterIndexedTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int m523getSizeimpl = UShortArray.m523getSizeimpl(filterIndexedTo);
        int i2 = 0;
        int i3 = 0;
        while (i2 < m523getSizeimpl) {
            short m522getMh2AYeg = UShortArray.m522getMh2AYeg(filterIndexedTo, i2);
            int i4 = i3 + 1;
            if (predicate.invoke(Integer.valueOf(i3), UShort.m459boximpl(m522getMh2AYeg)).booleanValue()) {
                destination.add(UShort.m459boximpl(m522getMh2AYeg));
            }
            i2++;
            i3 = i4;
        }
        return destination;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: filterIndexedTo-eNpIKz8  reason: not valid java name */
    private static final <C extends Collection<? super UByte>> C m820filterIndexedToeNpIKz8(byte[] filterIndexedTo, C destination, Function2<? super Integer, ? super UByte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(filterIndexedTo, "$this$filterIndexedTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int m263getSizeimpl = UByteArray.m263getSizeimpl(filterIndexedTo);
        int i2 = 0;
        int i3 = 0;
        while (i2 < m263getSizeimpl) {
            byte m262getw2LRezQ = UByteArray.m262getw2LRezQ(filterIndexedTo, i2);
            int i4 = i3 + 1;
            if (predicate.invoke(Integer.valueOf(i3), UByte.m199boximpl(m262getw2LRezQ)).booleanValue()) {
                destination.add(UByte.m199boximpl(m262getw2LRezQ));
            }
            i2++;
            i3 = i4;
        }
        return destination;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: filterIndexedTo-pe2Q0Dw  reason: not valid java name */
    private static final <C extends Collection<? super ULong>> C m821filterIndexedTope2Q0Dw(long[] filterIndexedTo, C destination, Function2<? super Integer, ? super ULong, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(filterIndexedTo, "$this$filterIndexedTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int m419getSizeimpl = ULongArray.m419getSizeimpl(filterIndexedTo);
        int i2 = 0;
        int i3 = 0;
        while (i2 < m419getSizeimpl) {
            long m418getsVKNKU = ULongArray.m418getsVKNKU(filterIndexedTo, i2);
            int i4 = i3 + 1;
            if (predicate.invoke(Integer.valueOf(i3), ULong.m353boximpl(m418getsVKNKU)).booleanValue()) {
                destination.add(ULong.m353boximpl(m418getsVKNKU));
            }
            i2++;
            i3 = i4;
        }
        return destination;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: filterNot-JOV_ifY  reason: not valid java name */
    private static final List<UByte> m822filterNotJOV_ifY(byte[] filterNot, Function1<? super UByte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(filterNot, "$this$filterNot");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        int m263getSizeimpl = UByteArray.m263getSizeimpl(filterNot);
        for (int i2 = 0; i2 < m263getSizeimpl; i2++) {
            byte m262getw2LRezQ = UByteArray.m262getw2LRezQ(filterNot, i2);
            if (!predicate.invoke(UByte.m199boximpl(m262getw2LRezQ)).booleanValue()) {
                arrayList.add(UByte.m199boximpl(m262getw2LRezQ));
            }
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: filterNot-MShoTSo  reason: not valid java name */
    private static final List<ULong> m823filterNotMShoTSo(long[] filterNot, Function1<? super ULong, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(filterNot, "$this$filterNot");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        int m419getSizeimpl = ULongArray.m419getSizeimpl(filterNot);
        for (int i2 = 0; i2 < m419getSizeimpl; i2++) {
            long m418getsVKNKU = ULongArray.m418getsVKNKU(filterNot, i2);
            if (!predicate.invoke(ULong.m353boximpl(m418getsVKNKU)).booleanValue()) {
                arrayList.add(ULong.m353boximpl(m418getsVKNKU));
            }
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: filterNot-jgv0xPQ  reason: not valid java name */
    private static final List<UInt> m824filterNotjgv0xPQ(int[] filterNot, Function1<? super UInt, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(filterNot, "$this$filterNot");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        int m341getSizeimpl = UIntArray.m341getSizeimpl(filterNot);
        for (int i2 = 0; i2 < m341getSizeimpl; i2++) {
            int m340getpVg5ArA = UIntArray.m340getpVg5ArA(filterNot, i2);
            if (!predicate.invoke(UInt.m275boximpl(m340getpVg5ArA)).booleanValue()) {
                arrayList.add(UInt.m275boximpl(m340getpVg5ArA));
            }
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: filterNot-xTcfx_M  reason: not valid java name */
    private static final List<UShort> m825filterNotxTcfx_M(short[] filterNot, Function1<? super UShort, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(filterNot, "$this$filterNot");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        int m523getSizeimpl = UShortArray.m523getSizeimpl(filterNot);
        for (int i2 = 0; i2 < m523getSizeimpl; i2++) {
            short m522getMh2AYeg = UShortArray.m522getMh2AYeg(filterNot, i2);
            if (!predicate.invoke(UShort.m459boximpl(m522getMh2AYeg)).booleanValue()) {
                arrayList.add(UShort.m459boximpl(m522getMh2AYeg));
            }
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: filterNotTo-HqK1JgA  reason: not valid java name */
    private static final <C extends Collection<? super ULong>> C m826filterNotToHqK1JgA(long[] filterNotTo, C destination, Function1<? super ULong, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(filterNotTo, "$this$filterNotTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int m419getSizeimpl = ULongArray.m419getSizeimpl(filterNotTo);
        for (int i2 = 0; i2 < m419getSizeimpl; i2++) {
            long m418getsVKNKU = ULongArray.m418getsVKNKU(filterNotTo, i2);
            if (!predicate.invoke(ULong.m353boximpl(m418getsVKNKU)).booleanValue()) {
                destination.add(ULong.m353boximpl(m418getsVKNKU));
            }
        }
        return destination;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: filterNotTo-oEOeDjA  reason: not valid java name */
    private static final <C extends Collection<? super UShort>> C m827filterNotTooEOeDjA(short[] filterNotTo, C destination, Function1<? super UShort, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(filterNotTo, "$this$filterNotTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int m523getSizeimpl = UShortArray.m523getSizeimpl(filterNotTo);
        for (int i2 = 0; i2 < m523getSizeimpl; i2++) {
            short m522getMh2AYeg = UShortArray.m522getMh2AYeg(filterNotTo, i2);
            if (!predicate.invoke(UShort.m459boximpl(m522getMh2AYeg)).booleanValue()) {
                destination.add(UShort.m459boximpl(m522getMh2AYeg));
            }
        }
        return destination;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: filterNotTo-wU5IKMo  reason: not valid java name */
    private static final <C extends Collection<? super UInt>> C m828filterNotTowU5IKMo(int[] filterNotTo, C destination, Function1<? super UInt, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(filterNotTo, "$this$filterNotTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int m341getSizeimpl = UIntArray.m341getSizeimpl(filterNotTo);
        for (int i2 = 0; i2 < m341getSizeimpl; i2++) {
            int m340getpVg5ArA = UIntArray.m340getpVg5ArA(filterNotTo, i2);
            if (!predicate.invoke(UInt.m275boximpl(m340getpVg5ArA)).booleanValue()) {
                destination.add(UInt.m275boximpl(m340getpVg5ArA));
            }
        }
        return destination;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: filterNotTo-wzUQCXU  reason: not valid java name */
    private static final <C extends Collection<? super UByte>> C m829filterNotTowzUQCXU(byte[] filterNotTo, C destination, Function1<? super UByte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(filterNotTo, "$this$filterNotTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int m263getSizeimpl = UByteArray.m263getSizeimpl(filterNotTo);
        for (int i2 = 0; i2 < m263getSizeimpl; i2++) {
            byte m262getw2LRezQ = UByteArray.m262getw2LRezQ(filterNotTo, i2);
            if (!predicate.invoke(UByte.m199boximpl(m262getw2LRezQ)).booleanValue()) {
                destination.add(UByte.m199boximpl(m262getw2LRezQ));
            }
        }
        return destination;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: filterTo-HqK1JgA  reason: not valid java name */
    private static final <C extends Collection<? super ULong>> C m830filterToHqK1JgA(long[] filterTo, C destination, Function1<? super ULong, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(filterTo, "$this$filterTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int m419getSizeimpl = ULongArray.m419getSizeimpl(filterTo);
        for (int i2 = 0; i2 < m419getSizeimpl; i2++) {
            long m418getsVKNKU = ULongArray.m418getsVKNKU(filterTo, i2);
            if (predicate.invoke(ULong.m353boximpl(m418getsVKNKU)).booleanValue()) {
                destination.add(ULong.m353boximpl(m418getsVKNKU));
            }
        }
        return destination;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: filterTo-oEOeDjA  reason: not valid java name */
    private static final <C extends Collection<? super UShort>> C m831filterTooEOeDjA(short[] filterTo, C destination, Function1<? super UShort, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(filterTo, "$this$filterTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int m523getSizeimpl = UShortArray.m523getSizeimpl(filterTo);
        for (int i2 = 0; i2 < m523getSizeimpl; i2++) {
            short m522getMh2AYeg = UShortArray.m522getMh2AYeg(filterTo, i2);
            if (predicate.invoke(UShort.m459boximpl(m522getMh2AYeg)).booleanValue()) {
                destination.add(UShort.m459boximpl(m522getMh2AYeg));
            }
        }
        return destination;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: filterTo-wU5IKMo  reason: not valid java name */
    private static final <C extends Collection<? super UInt>> C m832filterTowU5IKMo(int[] filterTo, C destination, Function1<? super UInt, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(filterTo, "$this$filterTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int m341getSizeimpl = UIntArray.m341getSizeimpl(filterTo);
        for (int i2 = 0; i2 < m341getSizeimpl; i2++) {
            int m340getpVg5ArA = UIntArray.m340getpVg5ArA(filterTo, i2);
            if (predicate.invoke(UInt.m275boximpl(m340getpVg5ArA)).booleanValue()) {
                destination.add(UInt.m275boximpl(m340getpVg5ArA));
            }
        }
        return destination;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: filterTo-wzUQCXU  reason: not valid java name */
    private static final <C extends Collection<? super UByte>> C m833filterTowzUQCXU(byte[] filterTo, C destination, Function1<? super UByte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(filterTo, "$this$filterTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int m263getSizeimpl = UByteArray.m263getSizeimpl(filterTo);
        for (int i2 = 0; i2 < m263getSizeimpl; i2++) {
            byte m262getw2LRezQ = UByteArray.m262getw2LRezQ(filterTo, i2);
            if (predicate.invoke(UByte.m199boximpl(m262getw2LRezQ)).booleanValue()) {
                destination.add(UByte.m199boximpl(m262getw2LRezQ));
            }
        }
        return destination;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: find-JOV_ifY  reason: not valid java name */
    private static final UByte m834findJOV_ifY(byte[] find, Function1<? super UByte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(find, "$this$find");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int m263getSizeimpl = UByteArray.m263getSizeimpl(find);
        for (int i2 = 0; i2 < m263getSizeimpl; i2++) {
            byte m262getw2LRezQ = UByteArray.m262getw2LRezQ(find, i2);
            if (predicate.invoke(UByte.m199boximpl(m262getw2LRezQ)).booleanValue()) {
                return UByte.m199boximpl(m262getw2LRezQ);
            }
        }
        return null;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: find-MShoTSo  reason: not valid java name */
    private static final ULong m835findMShoTSo(long[] find, Function1<? super ULong, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(find, "$this$find");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int m419getSizeimpl = ULongArray.m419getSizeimpl(find);
        for (int i2 = 0; i2 < m419getSizeimpl; i2++) {
            long m418getsVKNKU = ULongArray.m418getsVKNKU(find, i2);
            if (predicate.invoke(ULong.m353boximpl(m418getsVKNKU)).booleanValue()) {
                return ULong.m353boximpl(m418getsVKNKU);
            }
        }
        return null;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: find-jgv0xPQ  reason: not valid java name */
    private static final UInt m836findjgv0xPQ(int[] find, Function1<? super UInt, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(find, "$this$find");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int m341getSizeimpl = UIntArray.m341getSizeimpl(find);
        for (int i2 = 0; i2 < m341getSizeimpl; i2++) {
            int m340getpVg5ArA = UIntArray.m340getpVg5ArA(find, i2);
            if (predicate.invoke(UInt.m275boximpl(m340getpVg5ArA)).booleanValue()) {
                return UInt.m275boximpl(m340getpVg5ArA);
            }
        }
        return null;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: find-xTcfx_M  reason: not valid java name */
    private static final UShort m837findxTcfx_M(short[] find, Function1<? super UShort, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(find, "$this$find");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int m523getSizeimpl = UShortArray.m523getSizeimpl(find);
        for (int i2 = 0; i2 < m523getSizeimpl; i2++) {
            short m522getMh2AYeg = UShortArray.m522getMh2AYeg(find, i2);
            if (predicate.invoke(UShort.m459boximpl(m522getMh2AYeg)).booleanValue()) {
                return UShort.m459boximpl(m522getMh2AYeg);
            }
        }
        return null;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: findLast-JOV_ifY  reason: not valid java name */
    private static final UByte m838findLastJOV_ifY(byte[] findLast, Function1<? super UByte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(findLast, "$this$findLast");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int m263getSizeimpl = UByteArray.m263getSizeimpl(findLast) - 1;
        if (m263getSizeimpl >= 0) {
            while (true) {
                int i2 = m263getSizeimpl - 1;
                byte m262getw2LRezQ = UByteArray.m262getw2LRezQ(findLast, m263getSizeimpl);
                if (predicate.invoke(UByte.m199boximpl(m262getw2LRezQ)).booleanValue()) {
                    return UByte.m199boximpl(m262getw2LRezQ);
                }
                if (i2 < 0) {
                    break;
                }
                m263getSizeimpl = i2;
            }
        }
        return null;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: findLast-MShoTSo  reason: not valid java name */
    private static final ULong m839findLastMShoTSo(long[] findLast, Function1<? super ULong, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(findLast, "$this$findLast");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int m419getSizeimpl = ULongArray.m419getSizeimpl(findLast) - 1;
        if (m419getSizeimpl >= 0) {
            while (true) {
                int i2 = m419getSizeimpl - 1;
                long m418getsVKNKU = ULongArray.m418getsVKNKU(findLast, m419getSizeimpl);
                if (predicate.invoke(ULong.m353boximpl(m418getsVKNKU)).booleanValue()) {
                    return ULong.m353boximpl(m418getsVKNKU);
                }
                if (i2 < 0) {
                    break;
                }
                m419getSizeimpl = i2;
            }
        }
        return null;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: findLast-jgv0xPQ  reason: not valid java name */
    private static final UInt m840findLastjgv0xPQ(int[] findLast, Function1<? super UInt, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(findLast, "$this$findLast");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int m341getSizeimpl = UIntArray.m341getSizeimpl(findLast) - 1;
        if (m341getSizeimpl >= 0) {
            while (true) {
                int i2 = m341getSizeimpl - 1;
                int m340getpVg5ArA = UIntArray.m340getpVg5ArA(findLast, m341getSizeimpl);
                if (predicate.invoke(UInt.m275boximpl(m340getpVg5ArA)).booleanValue()) {
                    return UInt.m275boximpl(m340getpVg5ArA);
                }
                if (i2 < 0) {
                    break;
                }
                m341getSizeimpl = i2;
            }
        }
        return null;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: findLast-xTcfx_M  reason: not valid java name */
    private static final UShort m841findLastxTcfx_M(short[] findLast, Function1<? super UShort, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(findLast, "$this$findLast");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int m523getSizeimpl = UShortArray.m523getSizeimpl(findLast) - 1;
        if (m523getSizeimpl >= 0) {
            while (true) {
                int i2 = m523getSizeimpl - 1;
                short m522getMh2AYeg = UShortArray.m522getMh2AYeg(findLast, m523getSizeimpl);
                if (predicate.invoke(UShort.m459boximpl(m522getMh2AYeg)).booleanValue()) {
                    return UShort.m459boximpl(m522getMh2AYeg);
                }
                if (i2 < 0) {
                    break;
                }
                m523getSizeimpl = i2;
            }
        }
        return null;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: first--ajY-9A  reason: not valid java name */
    private static final int m842firstajY9A(int[] first) {
        int first2;
        Intrinsics.checkNotNullParameter(first, "$this$first");
        first2 = ArraysKt___ArraysKt.first(first);
        return UInt.m281constructorimpl(first2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: first-GBYM_sE  reason: not valid java name */
    private static final byte m843firstGBYM_sE(byte[] first) {
        byte first2;
        Intrinsics.checkNotNullParameter(first, "$this$first");
        first2 = ArraysKt___ArraysKt.first(first);
        return UByte.m205constructorimpl(first2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: first-JOV_ifY  reason: not valid java name */
    private static final byte m844firstJOV_ifY(byte[] first, Function1<? super UByte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(first, "$this$first");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int m263getSizeimpl = UByteArray.m263getSizeimpl(first);
        for (int i2 = 0; i2 < m263getSizeimpl; i2++) {
            byte m262getw2LRezQ = UByteArray.m262getw2LRezQ(first, i2);
            if (predicate.invoke(UByte.m199boximpl(m262getw2LRezQ)).booleanValue()) {
                return m262getw2LRezQ;
            }
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: first-MShoTSo  reason: not valid java name */
    private static final long m845firstMShoTSo(long[] first, Function1<? super ULong, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(first, "$this$first");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int m419getSizeimpl = ULongArray.m419getSizeimpl(first);
        for (int i2 = 0; i2 < m419getSizeimpl; i2++) {
            long m418getsVKNKU = ULongArray.m418getsVKNKU(first, i2);
            if (predicate.invoke(ULong.m353boximpl(m418getsVKNKU)).booleanValue()) {
                return m418getsVKNKU;
            }
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: first-QwZRm1k  reason: not valid java name */
    private static final long m846firstQwZRm1k(long[] first) {
        long first2;
        Intrinsics.checkNotNullParameter(first, "$this$first");
        first2 = ArraysKt___ArraysKt.first(first);
        return ULong.m359constructorimpl(first2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: first-jgv0xPQ  reason: not valid java name */
    private static final int m847firstjgv0xPQ(int[] first, Function1<? super UInt, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(first, "$this$first");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int m341getSizeimpl = UIntArray.m341getSizeimpl(first);
        for (int i2 = 0; i2 < m341getSizeimpl; i2++) {
            int m340getpVg5ArA = UIntArray.m340getpVg5ArA(first, i2);
            if (predicate.invoke(UInt.m275boximpl(m340getpVg5ArA)).booleanValue()) {
                return m340getpVg5ArA;
            }
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: first-rL5Bavg  reason: not valid java name */
    private static final short m848firstrL5Bavg(short[] first) {
        short first2;
        Intrinsics.checkNotNullParameter(first, "$this$first");
        first2 = ArraysKt___ArraysKt.first(first);
        return UShort.m465constructorimpl(first2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: first-xTcfx_M  reason: not valid java name */
    private static final short m849firstxTcfx_M(short[] first, Function1<? super UShort, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(first, "$this$first");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int m523getSizeimpl = UShortArray.m523getSizeimpl(first);
        for (int i2 = 0; i2 < m523getSizeimpl; i2++) {
            short m522getMh2AYeg = UShortArray.m522getMh2AYeg(first, i2);
            if (predicate.invoke(UShort.m459boximpl(m522getMh2AYeg)).booleanValue()) {
                return m522getMh2AYeg;
            }
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Nullable
    /* renamed from: firstOrNull--ajY-9A  reason: not valid java name */
    public static final UInt m850firstOrNullajY9A(@NotNull int[] firstOrNull) {
        Intrinsics.checkNotNullParameter(firstOrNull, "$this$firstOrNull");
        if (UIntArray.m343isEmptyimpl(firstOrNull)) {
            return null;
        }
        return UInt.m275boximpl(UIntArray.m340getpVg5ArA(firstOrNull, 0));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Nullable
    /* renamed from: firstOrNull-GBYM_sE  reason: not valid java name */
    public static final UByte m851firstOrNullGBYM_sE(@NotNull byte[] firstOrNull) {
        Intrinsics.checkNotNullParameter(firstOrNull, "$this$firstOrNull");
        if (UByteArray.m265isEmptyimpl(firstOrNull)) {
            return null;
        }
        return UByte.m199boximpl(UByteArray.m262getw2LRezQ(firstOrNull, 0));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: firstOrNull-JOV_ifY  reason: not valid java name */
    private static final UByte m852firstOrNullJOV_ifY(byte[] firstOrNull, Function1<? super UByte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(firstOrNull, "$this$firstOrNull");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int m263getSizeimpl = UByteArray.m263getSizeimpl(firstOrNull);
        for (int i2 = 0; i2 < m263getSizeimpl; i2++) {
            byte m262getw2LRezQ = UByteArray.m262getw2LRezQ(firstOrNull, i2);
            if (predicate.invoke(UByte.m199boximpl(m262getw2LRezQ)).booleanValue()) {
                return UByte.m199boximpl(m262getw2LRezQ);
            }
        }
        return null;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: firstOrNull-MShoTSo  reason: not valid java name */
    private static final ULong m853firstOrNullMShoTSo(long[] firstOrNull, Function1<? super ULong, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(firstOrNull, "$this$firstOrNull");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int m419getSizeimpl = ULongArray.m419getSizeimpl(firstOrNull);
        for (int i2 = 0; i2 < m419getSizeimpl; i2++) {
            long m418getsVKNKU = ULongArray.m418getsVKNKU(firstOrNull, i2);
            if (predicate.invoke(ULong.m353boximpl(m418getsVKNKU)).booleanValue()) {
                return ULong.m353boximpl(m418getsVKNKU);
            }
        }
        return null;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Nullable
    /* renamed from: firstOrNull-QwZRm1k  reason: not valid java name */
    public static final ULong m854firstOrNullQwZRm1k(@NotNull long[] firstOrNull) {
        Intrinsics.checkNotNullParameter(firstOrNull, "$this$firstOrNull");
        if (ULongArray.m421isEmptyimpl(firstOrNull)) {
            return null;
        }
        return ULong.m353boximpl(ULongArray.m418getsVKNKU(firstOrNull, 0));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: firstOrNull-jgv0xPQ  reason: not valid java name */
    private static final UInt m855firstOrNulljgv0xPQ(int[] firstOrNull, Function1<? super UInt, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(firstOrNull, "$this$firstOrNull");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int m341getSizeimpl = UIntArray.m341getSizeimpl(firstOrNull);
        for (int i2 = 0; i2 < m341getSizeimpl; i2++) {
            int m340getpVg5ArA = UIntArray.m340getpVg5ArA(firstOrNull, i2);
            if (predicate.invoke(UInt.m275boximpl(m340getpVg5ArA)).booleanValue()) {
                return UInt.m275boximpl(m340getpVg5ArA);
            }
        }
        return null;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Nullable
    /* renamed from: firstOrNull-rL5Bavg  reason: not valid java name */
    public static final UShort m856firstOrNullrL5Bavg(@NotNull short[] firstOrNull) {
        Intrinsics.checkNotNullParameter(firstOrNull, "$this$firstOrNull");
        if (UShortArray.m525isEmptyimpl(firstOrNull)) {
            return null;
        }
        return UShort.m459boximpl(UShortArray.m522getMh2AYeg(firstOrNull, 0));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: firstOrNull-xTcfx_M  reason: not valid java name */
    private static final UShort m857firstOrNullxTcfx_M(short[] firstOrNull, Function1<? super UShort, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(firstOrNull, "$this$firstOrNull");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int m523getSizeimpl = UShortArray.m523getSizeimpl(firstOrNull);
        for (int i2 = 0; i2 < m523getSizeimpl; i2++) {
            short m522getMh2AYeg = UShortArray.m522getMh2AYeg(firstOrNull, i2);
            if (predicate.invoke(UShort.m459boximpl(m522getMh2AYeg)).booleanValue()) {
                return UShort.m459boximpl(m522getMh2AYeg);
            }
        }
        return null;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: flatMap-JOV_ifY  reason: not valid java name */
    private static final <R> List<R> m858flatMapJOV_ifY(byte[] flatMap, Function1<? super UByte, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(flatMap, "$this$flatMap");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList();
        int m263getSizeimpl = UByteArray.m263getSizeimpl(flatMap);
        for (int i2 = 0; i2 < m263getSizeimpl; i2++) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, transform.invoke(UByte.m199boximpl(UByteArray.m262getw2LRezQ(flatMap, i2))));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: flatMap-MShoTSo  reason: not valid java name */
    private static final <R> List<R> m859flatMapMShoTSo(long[] flatMap, Function1<? super ULong, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(flatMap, "$this$flatMap");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList();
        int m419getSizeimpl = ULongArray.m419getSizeimpl(flatMap);
        for (int i2 = 0; i2 < m419getSizeimpl; i2++) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, transform.invoke(ULong.m353boximpl(ULongArray.m418getsVKNKU(flatMap, i2))));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: flatMap-jgv0xPQ  reason: not valid java name */
    private static final <R> List<R> m860flatMapjgv0xPQ(int[] flatMap, Function1<? super UInt, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(flatMap, "$this$flatMap");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList();
        int m341getSizeimpl = UIntArray.m341getSizeimpl(flatMap);
        for (int i2 = 0; i2 < m341getSizeimpl; i2++) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, transform.invoke(UInt.m275boximpl(UIntArray.m340getpVg5ArA(flatMap, i2))));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: flatMap-xTcfx_M  reason: not valid java name */
    private static final <R> List<R> m861flatMapxTcfx_M(short[] flatMap, Function1<? super UShort, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(flatMap, "$this$flatMap");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList();
        int m523getSizeimpl = UShortArray.m523getSizeimpl(flatMap);
        for (int i2 = 0; i2 < m523getSizeimpl; i2++) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, transform.invoke(UShort.m459boximpl(UShortArray.m522getMh2AYeg(flatMap, i2))));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* renamed from: flatMapIndexed-ELGow60  reason: not valid java name */
    private static final <R> List<R> m862flatMapIndexedELGow60(byte[] flatMapIndexed, Function2<? super Integer, ? super UByte, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(flatMapIndexed, "$this$flatMapIndexed");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList();
        int m263getSizeimpl = UByteArray.m263getSizeimpl(flatMapIndexed);
        int i2 = 0;
        int i3 = 0;
        while (i2 < m263getSizeimpl) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, transform.invoke(Integer.valueOf(i3), UByte.m199boximpl(UByteArray.m262getw2LRezQ(flatMapIndexed, i2))));
            i2++;
            i3++;
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* renamed from: flatMapIndexed-WyvcNBI  reason: not valid java name */
    private static final <R> List<R> m863flatMapIndexedWyvcNBI(int[] flatMapIndexed, Function2<? super Integer, ? super UInt, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(flatMapIndexed, "$this$flatMapIndexed");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList();
        int m341getSizeimpl = UIntArray.m341getSizeimpl(flatMapIndexed);
        int i2 = 0;
        int i3 = 0;
        while (i2 < m341getSizeimpl) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, transform.invoke(Integer.valueOf(i3), UInt.m275boximpl(UIntArray.m340getpVg5ArA(flatMapIndexed, i2))));
            i2++;
            i3++;
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* renamed from: flatMapIndexed-s8dVfGU  reason: not valid java name */
    private static final <R> List<R> m864flatMapIndexeds8dVfGU(long[] flatMapIndexed, Function2<? super Integer, ? super ULong, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(flatMapIndexed, "$this$flatMapIndexed");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList();
        int m419getSizeimpl = ULongArray.m419getSizeimpl(flatMapIndexed);
        int i2 = 0;
        int i3 = 0;
        while (i2 < m419getSizeimpl) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, transform.invoke(Integer.valueOf(i3), ULong.m353boximpl(ULongArray.m418getsVKNKU(flatMapIndexed, i2))));
            i2++;
            i3++;
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* renamed from: flatMapIndexed-xzaTVY8  reason: not valid java name */
    private static final <R> List<R> m865flatMapIndexedxzaTVY8(short[] flatMapIndexed, Function2<? super Integer, ? super UShort, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(flatMapIndexed, "$this$flatMapIndexed");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList();
        int m523getSizeimpl = UShortArray.m523getSizeimpl(flatMapIndexed);
        int i2 = 0;
        int i3 = 0;
        while (i2 < m523getSizeimpl) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, transform.invoke(Integer.valueOf(i3), UShort.m459boximpl(UShortArray.m522getMh2AYeg(flatMapIndexed, i2))));
            i2++;
            i3++;
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* renamed from: flatMapIndexedTo--6EtJGI  reason: not valid java name */
    private static final <R, C extends Collection<? super R>> C m866flatMapIndexedTo6EtJGI(int[] flatMapIndexedTo, C destination, Function2<? super Integer, ? super UInt, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(flatMapIndexedTo, "$this$flatMapIndexedTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int m341getSizeimpl = UIntArray.m341getSizeimpl(flatMapIndexedTo);
        int i2 = 0;
        int i3 = 0;
        while (i2 < m341getSizeimpl) {
            CollectionsKt__MutableCollectionsKt.addAll(destination, transform.invoke(Integer.valueOf(i3), UInt.m275boximpl(UIntArray.m340getpVg5ArA(flatMapIndexedTo, i2))));
            i2++;
            i3++;
        }
        return destination;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* renamed from: flatMapIndexedTo-QqktQ3k  reason: not valid java name */
    private static final <R, C extends Collection<? super R>> C m867flatMapIndexedToQqktQ3k(short[] flatMapIndexedTo, C destination, Function2<? super Integer, ? super UShort, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(flatMapIndexedTo, "$this$flatMapIndexedTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int m523getSizeimpl = UShortArray.m523getSizeimpl(flatMapIndexedTo);
        int i2 = 0;
        int i3 = 0;
        while (i2 < m523getSizeimpl) {
            CollectionsKt__MutableCollectionsKt.addAll(destination, transform.invoke(Integer.valueOf(i3), UShort.m459boximpl(UShortArray.m522getMh2AYeg(flatMapIndexedTo, i2))));
            i2++;
            i3++;
        }
        return destination;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* renamed from: flatMapIndexedTo-eNpIKz8  reason: not valid java name */
    private static final <R, C extends Collection<? super R>> C m868flatMapIndexedToeNpIKz8(byte[] flatMapIndexedTo, C destination, Function2<? super Integer, ? super UByte, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(flatMapIndexedTo, "$this$flatMapIndexedTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int m263getSizeimpl = UByteArray.m263getSizeimpl(flatMapIndexedTo);
        int i2 = 0;
        int i3 = 0;
        while (i2 < m263getSizeimpl) {
            CollectionsKt__MutableCollectionsKt.addAll(destination, transform.invoke(Integer.valueOf(i3), UByte.m199boximpl(UByteArray.m262getw2LRezQ(flatMapIndexedTo, i2))));
            i2++;
            i3++;
        }
        return destination;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* renamed from: flatMapIndexedTo-pe2Q0Dw  reason: not valid java name */
    private static final <R, C extends Collection<? super R>> C m869flatMapIndexedTope2Q0Dw(long[] flatMapIndexedTo, C destination, Function2<? super Integer, ? super ULong, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(flatMapIndexedTo, "$this$flatMapIndexedTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int m419getSizeimpl = ULongArray.m419getSizeimpl(flatMapIndexedTo);
        int i2 = 0;
        int i3 = 0;
        while (i2 < m419getSizeimpl) {
            CollectionsKt__MutableCollectionsKt.addAll(destination, transform.invoke(Integer.valueOf(i3), ULong.m353boximpl(ULongArray.m418getsVKNKU(flatMapIndexedTo, i2))));
            i2++;
            i3++;
        }
        return destination;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: flatMapTo-HqK1JgA  reason: not valid java name */
    private static final <R, C extends Collection<? super R>> C m870flatMapToHqK1JgA(long[] flatMapTo, C destination, Function1<? super ULong, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(flatMapTo, "$this$flatMapTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int m419getSizeimpl = ULongArray.m419getSizeimpl(flatMapTo);
        for (int i2 = 0; i2 < m419getSizeimpl; i2++) {
            CollectionsKt__MutableCollectionsKt.addAll(destination, transform.invoke(ULong.m353boximpl(ULongArray.m418getsVKNKU(flatMapTo, i2))));
        }
        return destination;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: flatMapTo-oEOeDjA  reason: not valid java name */
    private static final <R, C extends Collection<? super R>> C m871flatMapTooEOeDjA(short[] flatMapTo, C destination, Function1<? super UShort, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(flatMapTo, "$this$flatMapTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int m523getSizeimpl = UShortArray.m523getSizeimpl(flatMapTo);
        for (int i2 = 0; i2 < m523getSizeimpl; i2++) {
            CollectionsKt__MutableCollectionsKt.addAll(destination, transform.invoke(UShort.m459boximpl(UShortArray.m522getMh2AYeg(flatMapTo, i2))));
        }
        return destination;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: flatMapTo-wU5IKMo  reason: not valid java name */
    private static final <R, C extends Collection<? super R>> C m872flatMapTowU5IKMo(int[] flatMapTo, C destination, Function1<? super UInt, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(flatMapTo, "$this$flatMapTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int m341getSizeimpl = UIntArray.m341getSizeimpl(flatMapTo);
        for (int i2 = 0; i2 < m341getSizeimpl; i2++) {
            CollectionsKt__MutableCollectionsKt.addAll(destination, transform.invoke(UInt.m275boximpl(UIntArray.m340getpVg5ArA(flatMapTo, i2))));
        }
        return destination;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: flatMapTo-wzUQCXU  reason: not valid java name */
    private static final <R, C extends Collection<? super R>> C m873flatMapTowzUQCXU(byte[] flatMapTo, C destination, Function1<? super UByte, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(flatMapTo, "$this$flatMapTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int m263getSizeimpl = UByteArray.m263getSizeimpl(flatMapTo);
        for (int i2 = 0; i2 < m263getSizeimpl; i2++) {
            CollectionsKt__MutableCollectionsKt.addAll(destination, transform.invoke(UByte.m199boximpl(UByteArray.m262getw2LRezQ(flatMapTo, i2))));
        }
        return destination;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: fold-A8wKCXQ  reason: not valid java name */
    private static final <R> R m874foldA8wKCXQ(long[] fold, R r2, Function2<? super R, ? super ULong, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(fold, "$this$fold");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int m419getSizeimpl = ULongArray.m419getSizeimpl(fold);
        for (int i2 = 0; i2 < m419getSizeimpl; i2++) {
            r2 = operation.invoke(r2, ULong.m353boximpl(ULongArray.m418getsVKNKU(fold, i2)));
        }
        return r2;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: fold-yXmHNn8  reason: not valid java name */
    private static final <R> R m875foldyXmHNn8(byte[] fold, R r2, Function2<? super R, ? super UByte, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(fold, "$this$fold");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int m263getSizeimpl = UByteArray.m263getSizeimpl(fold);
        for (int i2 = 0; i2 < m263getSizeimpl; i2++) {
            r2 = operation.invoke(r2, UByte.m199boximpl(UByteArray.m262getw2LRezQ(fold, i2)));
        }
        return r2;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: fold-zi1B2BA  reason: not valid java name */
    private static final <R> R m876foldzi1B2BA(int[] fold, R r2, Function2<? super R, ? super UInt, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(fold, "$this$fold");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int m341getSizeimpl = UIntArray.m341getSizeimpl(fold);
        for (int i2 = 0; i2 < m341getSizeimpl; i2++) {
            r2 = operation.invoke(r2, UInt.m275boximpl(UIntArray.m340getpVg5ArA(fold, i2)));
        }
        return r2;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: fold-zww5nb8  reason: not valid java name */
    private static final <R> R m877foldzww5nb8(short[] fold, R r2, Function2<? super R, ? super UShort, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(fold, "$this$fold");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int m523getSizeimpl = UShortArray.m523getSizeimpl(fold);
        for (int i2 = 0; i2 < m523getSizeimpl; i2++) {
            r2 = operation.invoke(r2, UShort.m459boximpl(UShortArray.m522getMh2AYeg(fold, i2)));
        }
        return r2;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: foldIndexed-3iWJZGE  reason: not valid java name */
    private static final <R> R m878foldIndexed3iWJZGE(byte[] foldIndexed, R r2, Function3<? super Integer, ? super R, ? super UByte, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(foldIndexed, "$this$foldIndexed");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int m263getSizeimpl = UByteArray.m263getSizeimpl(foldIndexed);
        int i2 = 0;
        int i3 = 0;
        while (i2 < m263getSizeimpl) {
            r2 = operation.invoke(Integer.valueOf(i3), r2, UByte.m199boximpl(UByteArray.m262getw2LRezQ(foldIndexed, i2)));
            i2++;
            i3++;
        }
        return r2;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: foldIndexed-bzxtMww  reason: not valid java name */
    private static final <R> R m879foldIndexedbzxtMww(short[] foldIndexed, R r2, Function3<? super Integer, ? super R, ? super UShort, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(foldIndexed, "$this$foldIndexed");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int m523getSizeimpl = UShortArray.m523getSizeimpl(foldIndexed);
        int i2 = 0;
        int i3 = 0;
        while (i2 < m523getSizeimpl) {
            r2 = operation.invoke(Integer.valueOf(i3), r2, UShort.m459boximpl(UShortArray.m522getMh2AYeg(foldIndexed, i2)));
            i2++;
            i3++;
        }
        return r2;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: foldIndexed-mwnnOCs  reason: not valid java name */
    private static final <R> R m880foldIndexedmwnnOCs(long[] foldIndexed, R r2, Function3<? super Integer, ? super R, ? super ULong, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(foldIndexed, "$this$foldIndexed");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int m419getSizeimpl = ULongArray.m419getSizeimpl(foldIndexed);
        int i2 = 0;
        int i3 = 0;
        while (i2 < m419getSizeimpl) {
            r2 = operation.invoke(Integer.valueOf(i3), r2, ULong.m353boximpl(ULongArray.m418getsVKNKU(foldIndexed, i2)));
            i2++;
            i3++;
        }
        return r2;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: foldIndexed-yVwIW0Q  reason: not valid java name */
    private static final <R> R m881foldIndexedyVwIW0Q(int[] foldIndexed, R r2, Function3<? super Integer, ? super R, ? super UInt, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(foldIndexed, "$this$foldIndexed");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int m341getSizeimpl = UIntArray.m341getSizeimpl(foldIndexed);
        int i2 = 0;
        int i3 = 0;
        while (i2 < m341getSizeimpl) {
            r2 = operation.invoke(Integer.valueOf(i3), r2, UInt.m275boximpl(UIntArray.m340getpVg5ArA(foldIndexed, i2)));
            i2++;
            i3++;
        }
        return r2;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: foldRight-A8wKCXQ  reason: not valid java name */
    private static final <R> R m882foldRightA8wKCXQ(long[] foldRight, R r2, Function2<? super ULong, ? super R, ? extends R> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(foldRight, "$this$foldRight");
        Intrinsics.checkNotNullParameter(operation, "operation");
        for (lastIndex = ArraysKt___ArraysKt.getLastIndex(foldRight); lastIndex >= 0; lastIndex--) {
            r2 = operation.invoke(ULong.m353boximpl(ULongArray.m418getsVKNKU(foldRight, lastIndex)), r2);
        }
        return r2;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: foldRight-yXmHNn8  reason: not valid java name */
    private static final <R> R m883foldRightyXmHNn8(byte[] foldRight, R r2, Function2<? super UByte, ? super R, ? extends R> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(foldRight, "$this$foldRight");
        Intrinsics.checkNotNullParameter(operation, "operation");
        for (lastIndex = ArraysKt___ArraysKt.getLastIndex(foldRight); lastIndex >= 0; lastIndex--) {
            r2 = operation.invoke(UByte.m199boximpl(UByteArray.m262getw2LRezQ(foldRight, lastIndex)), r2);
        }
        return r2;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: foldRight-zi1B2BA  reason: not valid java name */
    private static final <R> R m884foldRightzi1B2BA(int[] foldRight, R r2, Function2<? super UInt, ? super R, ? extends R> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(foldRight, "$this$foldRight");
        Intrinsics.checkNotNullParameter(operation, "operation");
        for (lastIndex = ArraysKt___ArraysKt.getLastIndex(foldRight); lastIndex >= 0; lastIndex--) {
            r2 = operation.invoke(UInt.m275boximpl(UIntArray.m340getpVg5ArA(foldRight, lastIndex)), r2);
        }
        return r2;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: foldRight-zww5nb8  reason: not valid java name */
    private static final <R> R m885foldRightzww5nb8(short[] foldRight, R r2, Function2<? super UShort, ? super R, ? extends R> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(foldRight, "$this$foldRight");
        Intrinsics.checkNotNullParameter(operation, "operation");
        for (lastIndex = ArraysKt___ArraysKt.getLastIndex(foldRight); lastIndex >= 0; lastIndex--) {
            r2 = operation.invoke(UShort.m459boximpl(UShortArray.m522getMh2AYeg(foldRight, lastIndex)), r2);
        }
        return r2;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: foldRightIndexed-3iWJZGE  reason: not valid java name */
    private static final <R> R m886foldRightIndexed3iWJZGE(byte[] foldRightIndexed, R r2, Function3<? super Integer, ? super UByte, ? super R, ? extends R> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(foldRightIndexed, "$this$foldRightIndexed");
        Intrinsics.checkNotNullParameter(operation, "operation");
        for (lastIndex = ArraysKt___ArraysKt.getLastIndex(foldRightIndexed); lastIndex >= 0; lastIndex--) {
            r2 = operation.invoke(Integer.valueOf(lastIndex), UByte.m199boximpl(UByteArray.m262getw2LRezQ(foldRightIndexed, lastIndex)), r2);
        }
        return r2;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: foldRightIndexed-bzxtMww  reason: not valid java name */
    private static final <R> R m887foldRightIndexedbzxtMww(short[] foldRightIndexed, R r2, Function3<? super Integer, ? super UShort, ? super R, ? extends R> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(foldRightIndexed, "$this$foldRightIndexed");
        Intrinsics.checkNotNullParameter(operation, "operation");
        for (lastIndex = ArraysKt___ArraysKt.getLastIndex(foldRightIndexed); lastIndex >= 0; lastIndex--) {
            r2 = operation.invoke(Integer.valueOf(lastIndex), UShort.m459boximpl(UShortArray.m522getMh2AYeg(foldRightIndexed, lastIndex)), r2);
        }
        return r2;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: foldRightIndexed-mwnnOCs  reason: not valid java name */
    private static final <R> R m888foldRightIndexedmwnnOCs(long[] foldRightIndexed, R r2, Function3<? super Integer, ? super ULong, ? super R, ? extends R> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(foldRightIndexed, "$this$foldRightIndexed");
        Intrinsics.checkNotNullParameter(operation, "operation");
        for (lastIndex = ArraysKt___ArraysKt.getLastIndex(foldRightIndexed); lastIndex >= 0; lastIndex--) {
            r2 = operation.invoke(Integer.valueOf(lastIndex), ULong.m353boximpl(ULongArray.m418getsVKNKU(foldRightIndexed, lastIndex)), r2);
        }
        return r2;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: foldRightIndexed-yVwIW0Q  reason: not valid java name */
    private static final <R> R m889foldRightIndexedyVwIW0Q(int[] foldRightIndexed, R r2, Function3<? super Integer, ? super UInt, ? super R, ? extends R> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(foldRightIndexed, "$this$foldRightIndexed");
        Intrinsics.checkNotNullParameter(operation, "operation");
        for (lastIndex = ArraysKt___ArraysKt.getLastIndex(foldRightIndexed); lastIndex >= 0; lastIndex--) {
            r2 = operation.invoke(Integer.valueOf(lastIndex), UInt.m275boximpl(UIntArray.m340getpVg5ArA(foldRightIndexed, lastIndex)), r2);
        }
        return r2;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: forEach-JOV_ifY  reason: not valid java name */
    private static final void m890forEachJOV_ifY(byte[] forEach, Function1<? super UByte, Unit> action) {
        Intrinsics.checkNotNullParameter(forEach, "$this$forEach");
        Intrinsics.checkNotNullParameter(action, "action");
        int m263getSizeimpl = UByteArray.m263getSizeimpl(forEach);
        for (int i2 = 0; i2 < m263getSizeimpl; i2++) {
            action.invoke(UByte.m199boximpl(UByteArray.m262getw2LRezQ(forEach, i2)));
        }
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: forEach-MShoTSo  reason: not valid java name */
    private static final void m891forEachMShoTSo(long[] forEach, Function1<? super ULong, Unit> action) {
        Intrinsics.checkNotNullParameter(forEach, "$this$forEach");
        Intrinsics.checkNotNullParameter(action, "action");
        int m419getSizeimpl = ULongArray.m419getSizeimpl(forEach);
        for (int i2 = 0; i2 < m419getSizeimpl; i2++) {
            action.invoke(ULong.m353boximpl(ULongArray.m418getsVKNKU(forEach, i2)));
        }
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: forEach-jgv0xPQ  reason: not valid java name */
    private static final void m892forEachjgv0xPQ(int[] forEach, Function1<? super UInt, Unit> action) {
        Intrinsics.checkNotNullParameter(forEach, "$this$forEach");
        Intrinsics.checkNotNullParameter(action, "action");
        int m341getSizeimpl = UIntArray.m341getSizeimpl(forEach);
        for (int i2 = 0; i2 < m341getSizeimpl; i2++) {
            action.invoke(UInt.m275boximpl(UIntArray.m340getpVg5ArA(forEach, i2)));
        }
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: forEach-xTcfx_M  reason: not valid java name */
    private static final void m893forEachxTcfx_M(short[] forEach, Function1<? super UShort, Unit> action) {
        Intrinsics.checkNotNullParameter(forEach, "$this$forEach");
        Intrinsics.checkNotNullParameter(action, "action");
        int m523getSizeimpl = UShortArray.m523getSizeimpl(forEach);
        for (int i2 = 0; i2 < m523getSizeimpl; i2++) {
            action.invoke(UShort.m459boximpl(UShortArray.m522getMh2AYeg(forEach, i2)));
        }
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: forEachIndexed-ELGow60  reason: not valid java name */
    private static final void m894forEachIndexedELGow60(byte[] forEachIndexed, Function2<? super Integer, ? super UByte, Unit> action) {
        Intrinsics.checkNotNullParameter(forEachIndexed, "$this$forEachIndexed");
        Intrinsics.checkNotNullParameter(action, "action");
        int m263getSizeimpl = UByteArray.m263getSizeimpl(forEachIndexed);
        int i2 = 0;
        int i3 = 0;
        while (i2 < m263getSizeimpl) {
            action.invoke(Integer.valueOf(i3), UByte.m199boximpl(UByteArray.m262getw2LRezQ(forEachIndexed, i2)));
            i2++;
            i3++;
        }
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: forEachIndexed-WyvcNBI  reason: not valid java name */
    private static final void m895forEachIndexedWyvcNBI(int[] forEachIndexed, Function2<? super Integer, ? super UInt, Unit> action) {
        Intrinsics.checkNotNullParameter(forEachIndexed, "$this$forEachIndexed");
        Intrinsics.checkNotNullParameter(action, "action");
        int m341getSizeimpl = UIntArray.m341getSizeimpl(forEachIndexed);
        int i2 = 0;
        int i3 = 0;
        while (i2 < m341getSizeimpl) {
            action.invoke(Integer.valueOf(i3), UInt.m275boximpl(UIntArray.m340getpVg5ArA(forEachIndexed, i2)));
            i2++;
            i3++;
        }
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: forEachIndexed-s8dVfGU  reason: not valid java name */
    private static final void m896forEachIndexeds8dVfGU(long[] forEachIndexed, Function2<? super Integer, ? super ULong, Unit> action) {
        Intrinsics.checkNotNullParameter(forEachIndexed, "$this$forEachIndexed");
        Intrinsics.checkNotNullParameter(action, "action");
        int m419getSizeimpl = ULongArray.m419getSizeimpl(forEachIndexed);
        int i2 = 0;
        int i3 = 0;
        while (i2 < m419getSizeimpl) {
            action.invoke(Integer.valueOf(i3), ULong.m353boximpl(ULongArray.m418getsVKNKU(forEachIndexed, i2)));
            i2++;
            i3++;
        }
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: forEachIndexed-xzaTVY8  reason: not valid java name */
    private static final void m897forEachIndexedxzaTVY8(short[] forEachIndexed, Function2<? super Integer, ? super UShort, Unit> action) {
        Intrinsics.checkNotNullParameter(forEachIndexed, "$this$forEachIndexed");
        Intrinsics.checkNotNullParameter(action, "action");
        int m523getSizeimpl = UShortArray.m523getSizeimpl(forEachIndexed);
        int i2 = 0;
        int i3 = 0;
        while (i2 < m523getSizeimpl) {
            action.invoke(Integer.valueOf(i3), UShort.m459boximpl(UShortArray.m522getMh2AYeg(forEachIndexed, i2)));
            i2++;
            i3++;
        }
    }

    @NotNull
    /* renamed from: getIndices--ajY-9A  reason: not valid java name */
    public static final IntRange m898getIndicesajY9A(@NotNull int[] indices) {
        IntRange indices2;
        Intrinsics.checkNotNullParameter(indices, "$this$indices");
        indices2 = ArraysKt___ArraysKt.getIndices(indices);
        return indices2;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: getIndices--ajY-9A$annotations  reason: not valid java name */
    public static /* synthetic */ void m899getIndicesajY9A$annotations(int[] iArr) {
    }

    @NotNull
    /* renamed from: getIndices-GBYM_sE  reason: not valid java name */
    public static final IntRange m900getIndicesGBYM_sE(@NotNull byte[] indices) {
        IntRange indices2;
        Intrinsics.checkNotNullParameter(indices, "$this$indices");
        indices2 = ArraysKt___ArraysKt.getIndices(indices);
        return indices2;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: getIndices-GBYM_sE$annotations  reason: not valid java name */
    public static /* synthetic */ void m901getIndicesGBYM_sE$annotations(byte[] bArr) {
    }

    @NotNull
    /* renamed from: getIndices-QwZRm1k  reason: not valid java name */
    public static final IntRange m902getIndicesQwZRm1k(@NotNull long[] indices) {
        IntRange indices2;
        Intrinsics.checkNotNullParameter(indices, "$this$indices");
        indices2 = ArraysKt___ArraysKt.getIndices(indices);
        return indices2;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: getIndices-QwZRm1k$annotations  reason: not valid java name */
    public static /* synthetic */ void m903getIndicesQwZRm1k$annotations(long[] jArr) {
    }

    @NotNull
    /* renamed from: getIndices-rL5Bavg  reason: not valid java name */
    public static final IntRange m904getIndicesrL5Bavg(@NotNull short[] indices) {
        IntRange indices2;
        Intrinsics.checkNotNullParameter(indices, "$this$indices");
        indices2 = ArraysKt___ArraysKt.getIndices(indices);
        return indices2;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: getIndices-rL5Bavg$annotations  reason: not valid java name */
    public static /* synthetic */ void m905getIndicesrL5Bavg$annotations(short[] sArr) {
    }

    /* renamed from: getLastIndex--ajY-9A  reason: not valid java name */
    public static final int m906getLastIndexajY9A(@NotNull int[] lastIndex) {
        int lastIndex2;
        Intrinsics.checkNotNullParameter(lastIndex, "$this$lastIndex");
        lastIndex2 = ArraysKt___ArraysKt.getLastIndex(lastIndex);
        return lastIndex2;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: getLastIndex--ajY-9A$annotations  reason: not valid java name */
    public static /* synthetic */ void m907getLastIndexajY9A$annotations(int[] iArr) {
    }

    /* renamed from: getLastIndex-GBYM_sE  reason: not valid java name */
    public static final int m908getLastIndexGBYM_sE(@NotNull byte[] lastIndex) {
        int lastIndex2;
        Intrinsics.checkNotNullParameter(lastIndex, "$this$lastIndex");
        lastIndex2 = ArraysKt___ArraysKt.getLastIndex(lastIndex);
        return lastIndex2;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: getLastIndex-GBYM_sE$annotations  reason: not valid java name */
    public static /* synthetic */ void m909getLastIndexGBYM_sE$annotations(byte[] bArr) {
    }

    /* renamed from: getLastIndex-QwZRm1k  reason: not valid java name */
    public static final int m910getLastIndexQwZRm1k(@NotNull long[] lastIndex) {
        int lastIndex2;
        Intrinsics.checkNotNullParameter(lastIndex, "$this$lastIndex");
        lastIndex2 = ArraysKt___ArraysKt.getLastIndex(lastIndex);
        return lastIndex2;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: getLastIndex-QwZRm1k$annotations  reason: not valid java name */
    public static /* synthetic */ void m911getLastIndexQwZRm1k$annotations(long[] jArr) {
    }

    /* renamed from: getLastIndex-rL5Bavg  reason: not valid java name */
    public static final int m912getLastIndexrL5Bavg(@NotNull short[] lastIndex) {
        int lastIndex2;
        Intrinsics.checkNotNullParameter(lastIndex, "$this$lastIndex");
        lastIndex2 = ArraysKt___ArraysKt.getLastIndex(lastIndex);
        return lastIndex2;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: getLastIndex-rL5Bavg$annotations  reason: not valid java name */
    public static /* synthetic */ void m913getLastIndexrL5Bavg$annotations(short[] sArr) {
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: getOrElse-CVVdw08  reason: not valid java name */
    private static final short m914getOrElseCVVdw08(short[] getOrElse, int i2, Function1<? super Integer, UShort> defaultValue) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(getOrElse, "$this$getOrElse");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        if (i2 >= 0) {
            lastIndex = ArraysKt___ArraysKt.getLastIndex(getOrElse);
            if (i2 <= lastIndex) {
                return UShortArray.m522getMh2AYeg(getOrElse, i2);
            }
        }
        return defaultValue.invoke(Integer.valueOf(i2)).m514unboximpl();
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: getOrElse-QxvSvLU  reason: not valid java name */
    private static final int m915getOrElseQxvSvLU(int[] getOrElse, int i2, Function1<? super Integer, UInt> defaultValue) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(getOrElse, "$this$getOrElse");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        if (i2 >= 0) {
            lastIndex = ArraysKt___ArraysKt.getLastIndex(getOrElse);
            if (i2 <= lastIndex) {
                return UIntArray.m340getpVg5ArA(getOrElse, i2);
            }
        }
        return defaultValue.invoke(Integer.valueOf(i2)).m332unboximpl();
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: getOrElse-Xw8i6dc  reason: not valid java name */
    private static final long m916getOrElseXw8i6dc(long[] getOrElse, int i2, Function1<? super Integer, ULong> defaultValue) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(getOrElse, "$this$getOrElse");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        if (i2 >= 0) {
            lastIndex = ArraysKt___ArraysKt.getLastIndex(getOrElse);
            if (i2 <= lastIndex) {
                return ULongArray.m418getsVKNKU(getOrElse, i2);
            }
        }
        return defaultValue.invoke(Integer.valueOf(i2)).m410unboximpl();
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: getOrElse-cO-VybQ  reason: not valid java name */
    private static final byte m917getOrElsecOVybQ(byte[] getOrElse, int i2, Function1<? super Integer, UByte> defaultValue) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(getOrElse, "$this$getOrElse");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        if (i2 >= 0) {
            lastIndex = ArraysKt___ArraysKt.getLastIndex(getOrElse);
            if (i2 <= lastIndex) {
                return UByteArray.m262getw2LRezQ(getOrElse, i2);
            }
        }
        return defaultValue.invoke(Integer.valueOf(i2)).m254unboximpl();
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Nullable
    /* renamed from: getOrNull-PpDY95g  reason: not valid java name */
    public static final UByte m918getOrNullPpDY95g(@NotNull byte[] getOrNull, int i2) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(getOrNull, "$this$getOrNull");
        if (i2 >= 0) {
            lastIndex = ArraysKt___ArraysKt.getLastIndex(getOrNull);
            if (i2 <= lastIndex) {
                return UByte.m199boximpl(UByteArray.m262getw2LRezQ(getOrNull, i2));
            }
        }
        return null;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Nullable
    /* renamed from: getOrNull-nggk6HY  reason: not valid java name */
    public static final UShort m919getOrNullnggk6HY(@NotNull short[] getOrNull, int i2) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(getOrNull, "$this$getOrNull");
        if (i2 >= 0) {
            lastIndex = ArraysKt___ArraysKt.getLastIndex(getOrNull);
            if (i2 <= lastIndex) {
                return UShort.m459boximpl(UShortArray.m522getMh2AYeg(getOrNull, i2));
            }
        }
        return null;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Nullable
    /* renamed from: getOrNull-qFRl0hI  reason: not valid java name */
    public static final UInt m920getOrNullqFRl0hI(@NotNull int[] getOrNull, int i2) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(getOrNull, "$this$getOrNull");
        if (i2 >= 0) {
            lastIndex = ArraysKt___ArraysKt.getLastIndex(getOrNull);
            if (i2 <= lastIndex) {
                return UInt.m275boximpl(UIntArray.m340getpVg5ArA(getOrNull, i2));
            }
        }
        return null;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Nullable
    /* renamed from: getOrNull-r7IrZao  reason: not valid java name */
    public static final ULong m921getOrNullr7IrZao(@NotNull long[] getOrNull, int i2) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(getOrNull, "$this$getOrNull");
        if (i2 >= 0) {
            lastIndex = ArraysKt___ArraysKt.getLastIndex(getOrNull);
            if (i2 <= lastIndex) {
                return ULong.m353boximpl(ULongArray.m418getsVKNKU(getOrNull, i2));
            }
        }
        return null;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: groupBy--_j2Y-Q  reason: not valid java name */
    private static final <K, V> Map<K, List<V>> m922groupBy_j2YQ(long[] groupBy, Function1<? super ULong, ? extends K> keySelector, Function1<? super ULong, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(groupBy, "$this$groupBy");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int m419getSizeimpl = ULongArray.m419getSizeimpl(groupBy);
        for (int i2 = 0; i2 < m419getSizeimpl; i2++) {
            long m418getsVKNKU = ULongArray.m418getsVKNKU(groupBy, i2);
            K invoke = keySelector.invoke(ULong.m353boximpl(m418getsVKNKU));
            List<V> list = linkedHashMap.get(invoke);
            if (list == null) {
                list = new ArrayList<>();
                linkedHashMap.put(invoke, list);
            }
            list.add(valueTransform.invoke(ULong.m353boximpl(m418getsVKNKU)));
        }
        return linkedHashMap;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: groupBy-3bBvP4M  reason: not valid java name */
    private static final <K, V> Map<K, List<V>> m923groupBy3bBvP4M(short[] groupBy, Function1<? super UShort, ? extends K> keySelector, Function1<? super UShort, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(groupBy, "$this$groupBy");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int m523getSizeimpl = UShortArray.m523getSizeimpl(groupBy);
        for (int i2 = 0; i2 < m523getSizeimpl; i2++) {
            short m522getMh2AYeg = UShortArray.m522getMh2AYeg(groupBy, i2);
            K invoke = keySelector.invoke(UShort.m459boximpl(m522getMh2AYeg));
            List<V> list = linkedHashMap.get(invoke);
            if (list == null) {
                list = new ArrayList<>();
                linkedHashMap.put(invoke, list);
            }
            list.add(valueTransform.invoke(UShort.m459boximpl(m522getMh2AYeg)));
        }
        return linkedHashMap;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: groupBy-JOV_ifY  reason: not valid java name */
    private static final <K> Map<K, List<UByte>> m924groupByJOV_ifY(byte[] groupBy, Function1<? super UByte, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(groupBy, "$this$groupBy");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int m263getSizeimpl = UByteArray.m263getSizeimpl(groupBy);
        for (int i2 = 0; i2 < m263getSizeimpl; i2++) {
            byte m262getw2LRezQ = UByteArray.m262getw2LRezQ(groupBy, i2);
            K invoke = keySelector.invoke(UByte.m199boximpl(m262getw2LRezQ));
            Object obj = linkedHashMap.get(invoke);
            if (obj == null) {
                obj = new ArrayList();
                linkedHashMap.put(invoke, obj);
            }
            ((List) obj).add(UByte.m199boximpl(m262getw2LRezQ));
        }
        return linkedHashMap;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: groupBy-L4rlFek  reason: not valid java name */
    private static final <K, V> Map<K, List<V>> m925groupByL4rlFek(int[] groupBy, Function1<? super UInt, ? extends K> keySelector, Function1<? super UInt, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(groupBy, "$this$groupBy");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int m341getSizeimpl = UIntArray.m341getSizeimpl(groupBy);
        for (int i2 = 0; i2 < m341getSizeimpl; i2++) {
            int m340getpVg5ArA = UIntArray.m340getpVg5ArA(groupBy, i2);
            K invoke = keySelector.invoke(UInt.m275boximpl(m340getpVg5ArA));
            List<V> list = linkedHashMap.get(invoke);
            if (list == null) {
                list = new ArrayList<>();
                linkedHashMap.put(invoke, list);
            }
            list.add(valueTransform.invoke(UInt.m275boximpl(m340getpVg5ArA)));
        }
        return linkedHashMap;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: groupBy-MShoTSo  reason: not valid java name */
    private static final <K> Map<K, List<ULong>> m926groupByMShoTSo(long[] groupBy, Function1<? super ULong, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(groupBy, "$this$groupBy");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int m419getSizeimpl = ULongArray.m419getSizeimpl(groupBy);
        for (int i2 = 0; i2 < m419getSizeimpl; i2++) {
            long m418getsVKNKU = ULongArray.m418getsVKNKU(groupBy, i2);
            K invoke = keySelector.invoke(ULong.m353boximpl(m418getsVKNKU));
            Object obj = linkedHashMap.get(invoke);
            if (obj == null) {
                obj = new ArrayList();
                linkedHashMap.put(invoke, obj);
            }
            ((List) obj).add(ULong.m353boximpl(m418getsVKNKU));
        }
        return linkedHashMap;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: groupBy-bBsjw1Y  reason: not valid java name */
    private static final <K, V> Map<K, List<V>> m927groupBybBsjw1Y(byte[] groupBy, Function1<? super UByte, ? extends K> keySelector, Function1<? super UByte, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(groupBy, "$this$groupBy");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int m263getSizeimpl = UByteArray.m263getSizeimpl(groupBy);
        for (int i2 = 0; i2 < m263getSizeimpl; i2++) {
            byte m262getw2LRezQ = UByteArray.m262getw2LRezQ(groupBy, i2);
            K invoke = keySelector.invoke(UByte.m199boximpl(m262getw2LRezQ));
            List<V> list = linkedHashMap.get(invoke);
            if (list == null) {
                list = new ArrayList<>();
                linkedHashMap.put(invoke, list);
            }
            list.add(valueTransform.invoke(UByte.m199boximpl(m262getw2LRezQ)));
        }
        return linkedHashMap;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: groupBy-jgv0xPQ  reason: not valid java name */
    private static final <K> Map<K, List<UInt>> m928groupByjgv0xPQ(int[] groupBy, Function1<? super UInt, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(groupBy, "$this$groupBy");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int m341getSizeimpl = UIntArray.m341getSizeimpl(groupBy);
        for (int i2 = 0; i2 < m341getSizeimpl; i2++) {
            int m340getpVg5ArA = UIntArray.m340getpVg5ArA(groupBy, i2);
            K invoke = keySelector.invoke(UInt.m275boximpl(m340getpVg5ArA));
            Object obj = linkedHashMap.get(invoke);
            if (obj == null) {
                obj = new ArrayList();
                linkedHashMap.put(invoke, obj);
            }
            ((List) obj).add(UInt.m275boximpl(m340getpVg5ArA));
        }
        return linkedHashMap;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: groupBy-xTcfx_M  reason: not valid java name */
    private static final <K> Map<K, List<UShort>> m929groupByxTcfx_M(short[] groupBy, Function1<? super UShort, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(groupBy, "$this$groupBy");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int m523getSizeimpl = UShortArray.m523getSizeimpl(groupBy);
        for (int i2 = 0; i2 < m523getSizeimpl; i2++) {
            short m522getMh2AYeg = UShortArray.m522getMh2AYeg(groupBy, i2);
            K invoke = keySelector.invoke(UShort.m459boximpl(m522getMh2AYeg));
            Object obj = linkedHashMap.get(invoke);
            if (obj == null) {
                obj = new ArrayList();
                linkedHashMap.put(invoke, obj);
            }
            ((List) obj).add(UShort.m459boximpl(m522getMh2AYeg));
        }
        return linkedHashMap;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: groupByTo-4D70W2E  reason: not valid java name */
    private static final <K, M extends Map<? super K, List<UInt>>> M m930groupByTo4D70W2E(int[] groupByTo, M destination, Function1<? super UInt, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(groupByTo, "$this$groupByTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        int m341getSizeimpl = UIntArray.m341getSizeimpl(groupByTo);
        for (int i2 = 0; i2 < m341getSizeimpl; i2++) {
            int m340getpVg5ArA = UIntArray.m340getpVg5ArA(groupByTo, i2);
            K invoke = keySelector.invoke(UInt.m275boximpl(m340getpVg5ArA));
            Object obj = destination.get(invoke);
            if (obj == null) {
                obj = new ArrayList();
                destination.put(invoke, obj);
            }
            ((List) obj).add(UInt.m275boximpl(m340getpVg5ArA));
        }
        return destination;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: groupByTo-H21X9dk  reason: not valid java name */
    private static final <K, M extends Map<? super K, List<UByte>>> M m931groupByToH21X9dk(byte[] groupByTo, M destination, Function1<? super UByte, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(groupByTo, "$this$groupByTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        int m263getSizeimpl = UByteArray.m263getSizeimpl(groupByTo);
        for (int i2 = 0; i2 < m263getSizeimpl; i2++) {
            byte m262getw2LRezQ = UByteArray.m262getw2LRezQ(groupByTo, i2);
            K invoke = keySelector.invoke(UByte.m199boximpl(m262getw2LRezQ));
            Object obj = destination.get(invoke);
            if (obj == null) {
                obj = new ArrayList();
                destination.put(invoke, obj);
            }
            ((List) obj).add(UByte.m199boximpl(m262getw2LRezQ));
        }
        return destination;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: groupByTo-JM6gNCM  reason: not valid java name */
    private static final <K, V, M extends Map<? super K, List<V>>> M m932groupByToJM6gNCM(int[] groupByTo, M destination, Function1<? super UInt, ? extends K> keySelector, Function1<? super UInt, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(groupByTo, "$this$groupByTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        int m341getSizeimpl = UIntArray.m341getSizeimpl(groupByTo);
        for (int i2 = 0; i2 < m341getSizeimpl; i2++) {
            int m340getpVg5ArA = UIntArray.m340getpVg5ArA(groupByTo, i2);
            K invoke = keySelector.invoke(UInt.m275boximpl(m340getpVg5ArA));
            Object obj = destination.get(invoke);
            if (obj == null) {
                obj = new ArrayList();
                destination.put(invoke, obj);
            }
            ((List) obj).add(valueTransform.invoke(UInt.m275boximpl(m340getpVg5ArA)));
        }
        return destination;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: groupByTo-QxgOkWg  reason: not valid java name */
    private static final <K, V, M extends Map<? super K, List<V>>> M m933groupByToQxgOkWg(long[] groupByTo, M destination, Function1<? super ULong, ? extends K> keySelector, Function1<? super ULong, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(groupByTo, "$this$groupByTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        int m419getSizeimpl = ULongArray.m419getSizeimpl(groupByTo);
        for (int i2 = 0; i2 < m419getSizeimpl; i2++) {
            long m418getsVKNKU = ULongArray.m418getsVKNKU(groupByTo, i2);
            K invoke = keySelector.invoke(ULong.m353boximpl(m418getsVKNKU));
            Object obj = destination.get(invoke);
            if (obj == null) {
                obj = new ArrayList();
                destination.put(invoke, obj);
            }
            ((List) obj).add(valueTransform.invoke(ULong.m353boximpl(m418getsVKNKU)));
        }
        return destination;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: groupByTo-X6OPwNk  reason: not valid java name */
    private static final <K, M extends Map<? super K, List<ULong>>> M m934groupByToX6OPwNk(long[] groupByTo, M destination, Function1<? super ULong, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(groupByTo, "$this$groupByTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        int m419getSizeimpl = ULongArray.m419getSizeimpl(groupByTo);
        for (int i2 = 0; i2 < m419getSizeimpl; i2++) {
            long m418getsVKNKU = ULongArray.m418getsVKNKU(groupByTo, i2);
            K invoke = keySelector.invoke(ULong.m353boximpl(m418getsVKNKU));
            Object obj = destination.get(invoke);
            if (obj == null) {
                obj = new ArrayList();
                destination.put(invoke, obj);
            }
            ((List) obj).add(ULong.m353boximpl(m418getsVKNKU));
        }
        return destination;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: groupByTo-ciTST-8  reason: not valid java name */
    private static final <K, M extends Map<? super K, List<UShort>>> M m935groupByTociTST8(short[] groupByTo, M destination, Function1<? super UShort, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(groupByTo, "$this$groupByTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        int m523getSizeimpl = UShortArray.m523getSizeimpl(groupByTo);
        for (int i2 = 0; i2 < m523getSizeimpl; i2++) {
            short m522getMh2AYeg = UShortArray.m522getMh2AYeg(groupByTo, i2);
            K invoke = keySelector.invoke(UShort.m459boximpl(m522getMh2AYeg));
            Object obj = destination.get(invoke);
            if (obj == null) {
                obj = new ArrayList();
                destination.put(invoke, obj);
            }
            ((List) obj).add(UShort.m459boximpl(m522getMh2AYeg));
        }
        return destination;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: groupByTo-q8RuPII  reason: not valid java name */
    private static final <K, V, M extends Map<? super K, List<V>>> M m936groupByToq8RuPII(short[] groupByTo, M destination, Function1<? super UShort, ? extends K> keySelector, Function1<? super UShort, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(groupByTo, "$this$groupByTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        int m523getSizeimpl = UShortArray.m523getSizeimpl(groupByTo);
        for (int i2 = 0; i2 < m523getSizeimpl; i2++) {
            short m522getMh2AYeg = UShortArray.m522getMh2AYeg(groupByTo, i2);
            K invoke = keySelector.invoke(UShort.m459boximpl(m522getMh2AYeg));
            Object obj = destination.get(invoke);
            if (obj == null) {
                obj = new ArrayList();
                destination.put(invoke, obj);
            }
            ((List) obj).add(valueTransform.invoke(UShort.m459boximpl(m522getMh2AYeg)));
        }
        return destination;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: groupByTo-qOZmbk8  reason: not valid java name */
    private static final <K, V, M extends Map<? super K, List<V>>> M m937groupByToqOZmbk8(byte[] groupByTo, M destination, Function1<? super UByte, ? extends K> keySelector, Function1<? super UByte, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(groupByTo, "$this$groupByTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        int m263getSizeimpl = UByteArray.m263getSizeimpl(groupByTo);
        for (int i2 = 0; i2 < m263getSizeimpl; i2++) {
            byte m262getw2LRezQ = UByteArray.m262getw2LRezQ(groupByTo, i2);
            K invoke = keySelector.invoke(UByte.m199boximpl(m262getw2LRezQ));
            Object obj = destination.get(invoke);
            if (obj == null) {
                obj = new ArrayList();
                destination.put(invoke, obj);
            }
            ((List) obj).add(valueTransform.invoke(UByte.m199boximpl(m262getw2LRezQ)));
        }
        return destination;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: indexOf-3uqUaXg  reason: not valid java name */
    private static final int m938indexOf3uqUaXg(long[] indexOf, long j2) {
        int indexOf2;
        Intrinsics.checkNotNullParameter(indexOf, "$this$indexOf");
        indexOf2 = ArraysKt___ArraysKt.indexOf(indexOf, j2);
        return indexOf2;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: indexOf-XzdR7RA  reason: not valid java name */
    private static final int m939indexOfXzdR7RA(short[] indexOf, short s2) {
        int indexOf2;
        Intrinsics.checkNotNullParameter(indexOf, "$this$indexOf");
        indexOf2 = ArraysKt___ArraysKt.indexOf(indexOf, s2);
        return indexOf2;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: indexOf-gMuBH34  reason: not valid java name */
    private static final int m940indexOfgMuBH34(byte[] indexOf, byte b2) {
        int indexOf2;
        Intrinsics.checkNotNullParameter(indexOf, "$this$indexOf");
        indexOf2 = ArraysKt___ArraysKt.indexOf(indexOf, b2);
        return indexOf2;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: indexOf-uWY9BYg  reason: not valid java name */
    private static final int m941indexOfuWY9BYg(int[] indexOf, int i2) {
        int indexOf2;
        Intrinsics.checkNotNullParameter(indexOf, "$this$indexOf");
        indexOf2 = ArraysKt___ArraysKt.indexOf(indexOf, i2);
        return indexOf2;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: indexOfFirst-JOV_ifY  reason: not valid java name */
    private static final int m942indexOfFirstJOV_ifY(byte[] indexOfFirst, Function1<? super UByte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(indexOfFirst, "$this$indexOfFirst");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = indexOfFirst.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (predicate.invoke(UByte.m199boximpl(UByte.m205constructorimpl(indexOfFirst[i2]))).booleanValue()) {
                return i2;
            }
        }
        return -1;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: indexOfFirst-MShoTSo  reason: not valid java name */
    private static final int m943indexOfFirstMShoTSo(long[] indexOfFirst, Function1<? super ULong, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(indexOfFirst, "$this$indexOfFirst");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = indexOfFirst.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (predicate.invoke(ULong.m353boximpl(ULong.m359constructorimpl(indexOfFirst[i2]))).booleanValue()) {
                return i2;
            }
        }
        return -1;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: indexOfFirst-jgv0xPQ  reason: not valid java name */
    private static final int m944indexOfFirstjgv0xPQ(int[] indexOfFirst, Function1<? super UInt, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(indexOfFirst, "$this$indexOfFirst");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = indexOfFirst.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (predicate.invoke(UInt.m275boximpl(UInt.m281constructorimpl(indexOfFirst[i2]))).booleanValue()) {
                return i2;
            }
        }
        return -1;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: indexOfFirst-xTcfx_M  reason: not valid java name */
    private static final int m945indexOfFirstxTcfx_M(short[] indexOfFirst, Function1<? super UShort, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(indexOfFirst, "$this$indexOfFirst");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = indexOfFirst.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (predicate.invoke(UShort.m459boximpl(UShort.m465constructorimpl(indexOfFirst[i2]))).booleanValue()) {
                return i2;
            }
        }
        return -1;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: indexOfLast-JOV_ifY  reason: not valid java name */
    private static final int m946indexOfLastJOV_ifY(byte[] indexOfLast, Function1<? super UByte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(indexOfLast, "$this$indexOfLast");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = indexOfLast.length - 1;
        if (length < 0) {
            return -1;
        }
        while (true) {
            int i2 = length - 1;
            if (predicate.invoke(UByte.m199boximpl(UByte.m205constructorimpl(indexOfLast[length]))).booleanValue()) {
                return length;
            }
            if (i2 < 0) {
                return -1;
            }
            length = i2;
        }
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: indexOfLast-MShoTSo  reason: not valid java name */
    private static final int m947indexOfLastMShoTSo(long[] indexOfLast, Function1<? super ULong, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(indexOfLast, "$this$indexOfLast");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = indexOfLast.length - 1;
        if (length < 0) {
            return -1;
        }
        while (true) {
            int i2 = length - 1;
            if (predicate.invoke(ULong.m353boximpl(ULong.m359constructorimpl(indexOfLast[length]))).booleanValue()) {
                return length;
            }
            if (i2 < 0) {
                return -1;
            }
            length = i2;
        }
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: indexOfLast-jgv0xPQ  reason: not valid java name */
    private static final int m948indexOfLastjgv0xPQ(int[] indexOfLast, Function1<? super UInt, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(indexOfLast, "$this$indexOfLast");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = indexOfLast.length - 1;
        if (length < 0) {
            return -1;
        }
        while (true) {
            int i2 = length - 1;
            if (predicate.invoke(UInt.m275boximpl(UInt.m281constructorimpl(indexOfLast[length]))).booleanValue()) {
                return length;
            }
            if (i2 < 0) {
                return -1;
            }
            length = i2;
        }
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: indexOfLast-xTcfx_M  reason: not valid java name */
    private static final int m949indexOfLastxTcfx_M(short[] indexOfLast, Function1<? super UShort, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(indexOfLast, "$this$indexOfLast");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = indexOfLast.length - 1;
        if (length < 0) {
            return -1;
        }
        while (true) {
            int i2 = length - 1;
            if (predicate.invoke(UShort.m459boximpl(UShort.m465constructorimpl(indexOfLast[length]))).booleanValue()) {
                return length;
            }
            if (i2 < 0) {
                return -1;
            }
            length = i2;
        }
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: last--ajY-9A  reason: not valid java name */
    private static final int m950lastajY9A(int[] last) {
        int last2;
        Intrinsics.checkNotNullParameter(last, "$this$last");
        last2 = ArraysKt___ArraysKt.last(last);
        return UInt.m281constructorimpl(last2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: last-GBYM_sE  reason: not valid java name */
    private static final byte m951lastGBYM_sE(byte[] last) {
        byte last2;
        Intrinsics.checkNotNullParameter(last, "$this$last");
        last2 = ArraysKt___ArraysKt.last(last);
        return UByte.m205constructorimpl(last2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: last-JOV_ifY  reason: not valid java name */
    private static final byte m952lastJOV_ifY(byte[] last, Function1<? super UByte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(last, "$this$last");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int m263getSizeimpl = UByteArray.m263getSizeimpl(last) - 1;
        if (m263getSizeimpl >= 0) {
            while (true) {
                int i2 = m263getSizeimpl - 1;
                byte m262getw2LRezQ = UByteArray.m262getw2LRezQ(last, m263getSizeimpl);
                if (!predicate.invoke(UByte.m199boximpl(m262getw2LRezQ)).booleanValue()) {
                    if (i2 < 0) {
                        break;
                    }
                    m263getSizeimpl = i2;
                } else {
                    return m262getw2LRezQ;
                }
            }
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: last-MShoTSo  reason: not valid java name */
    private static final long m953lastMShoTSo(long[] last, Function1<? super ULong, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(last, "$this$last");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int m419getSizeimpl = ULongArray.m419getSizeimpl(last) - 1;
        if (m419getSizeimpl >= 0) {
            while (true) {
                int i2 = m419getSizeimpl - 1;
                long m418getsVKNKU = ULongArray.m418getsVKNKU(last, m419getSizeimpl);
                if (!predicate.invoke(ULong.m353boximpl(m418getsVKNKU)).booleanValue()) {
                    if (i2 < 0) {
                        break;
                    }
                    m419getSizeimpl = i2;
                } else {
                    return m418getsVKNKU;
                }
            }
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: last-QwZRm1k  reason: not valid java name */
    private static final long m954lastQwZRm1k(long[] last) {
        long last2;
        Intrinsics.checkNotNullParameter(last, "$this$last");
        last2 = ArraysKt___ArraysKt.last(last);
        return ULong.m359constructorimpl(last2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: last-jgv0xPQ  reason: not valid java name */
    private static final int m955lastjgv0xPQ(int[] last, Function1<? super UInt, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(last, "$this$last");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int m341getSizeimpl = UIntArray.m341getSizeimpl(last) - 1;
        if (m341getSizeimpl >= 0) {
            while (true) {
                int i2 = m341getSizeimpl - 1;
                int m340getpVg5ArA = UIntArray.m340getpVg5ArA(last, m341getSizeimpl);
                if (!predicate.invoke(UInt.m275boximpl(m340getpVg5ArA)).booleanValue()) {
                    if (i2 < 0) {
                        break;
                    }
                    m341getSizeimpl = i2;
                } else {
                    return m340getpVg5ArA;
                }
            }
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: last-rL5Bavg  reason: not valid java name */
    private static final short m956lastrL5Bavg(short[] last) {
        short last2;
        Intrinsics.checkNotNullParameter(last, "$this$last");
        last2 = ArraysKt___ArraysKt.last(last);
        return UShort.m465constructorimpl(last2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: last-xTcfx_M  reason: not valid java name */
    private static final short m957lastxTcfx_M(short[] last, Function1<? super UShort, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(last, "$this$last");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int m523getSizeimpl = UShortArray.m523getSizeimpl(last) - 1;
        if (m523getSizeimpl >= 0) {
            while (true) {
                int i2 = m523getSizeimpl - 1;
                short m522getMh2AYeg = UShortArray.m522getMh2AYeg(last, m523getSizeimpl);
                if (!predicate.invoke(UShort.m459boximpl(m522getMh2AYeg)).booleanValue()) {
                    if (i2 < 0) {
                        break;
                    }
                    m523getSizeimpl = i2;
                } else {
                    return m522getMh2AYeg;
                }
            }
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: lastIndexOf-3uqUaXg  reason: not valid java name */
    private static final int m958lastIndexOf3uqUaXg(long[] lastIndexOf, long j2) {
        int lastIndexOf2;
        Intrinsics.checkNotNullParameter(lastIndexOf, "$this$lastIndexOf");
        lastIndexOf2 = ArraysKt___ArraysKt.lastIndexOf(lastIndexOf, j2);
        return lastIndexOf2;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: lastIndexOf-XzdR7RA  reason: not valid java name */
    private static final int m959lastIndexOfXzdR7RA(short[] lastIndexOf, short s2) {
        int lastIndexOf2;
        Intrinsics.checkNotNullParameter(lastIndexOf, "$this$lastIndexOf");
        lastIndexOf2 = ArraysKt___ArraysKt.lastIndexOf(lastIndexOf, s2);
        return lastIndexOf2;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: lastIndexOf-gMuBH34  reason: not valid java name */
    private static final int m960lastIndexOfgMuBH34(byte[] lastIndexOf, byte b2) {
        int lastIndexOf2;
        Intrinsics.checkNotNullParameter(lastIndexOf, "$this$lastIndexOf");
        lastIndexOf2 = ArraysKt___ArraysKt.lastIndexOf(lastIndexOf, b2);
        return lastIndexOf2;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: lastIndexOf-uWY9BYg  reason: not valid java name */
    private static final int m961lastIndexOfuWY9BYg(int[] lastIndexOf, int i2) {
        int lastIndexOf2;
        Intrinsics.checkNotNullParameter(lastIndexOf, "$this$lastIndexOf");
        lastIndexOf2 = ArraysKt___ArraysKt.lastIndexOf(lastIndexOf, i2);
        return lastIndexOf2;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Nullable
    /* renamed from: lastOrNull--ajY-9A  reason: not valid java name */
    public static final UInt m962lastOrNullajY9A(@NotNull int[] lastOrNull) {
        Intrinsics.checkNotNullParameter(lastOrNull, "$this$lastOrNull");
        if (UIntArray.m343isEmptyimpl(lastOrNull)) {
            return null;
        }
        return UInt.m275boximpl(UIntArray.m340getpVg5ArA(lastOrNull, UIntArray.m341getSizeimpl(lastOrNull) - 1));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Nullable
    /* renamed from: lastOrNull-GBYM_sE  reason: not valid java name */
    public static final UByte m963lastOrNullGBYM_sE(@NotNull byte[] lastOrNull) {
        Intrinsics.checkNotNullParameter(lastOrNull, "$this$lastOrNull");
        if (UByteArray.m265isEmptyimpl(lastOrNull)) {
            return null;
        }
        return UByte.m199boximpl(UByteArray.m262getw2LRezQ(lastOrNull, UByteArray.m263getSizeimpl(lastOrNull) - 1));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: lastOrNull-JOV_ifY  reason: not valid java name */
    private static final UByte m964lastOrNullJOV_ifY(byte[] lastOrNull, Function1<? super UByte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(lastOrNull, "$this$lastOrNull");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int m263getSizeimpl = UByteArray.m263getSizeimpl(lastOrNull) - 1;
        if (m263getSizeimpl < 0) {
            return null;
        }
        while (true) {
            int i2 = m263getSizeimpl - 1;
            byte m262getw2LRezQ = UByteArray.m262getw2LRezQ(lastOrNull, m263getSizeimpl);
            if (predicate.invoke(UByte.m199boximpl(m262getw2LRezQ)).booleanValue()) {
                return UByte.m199boximpl(m262getw2LRezQ);
            }
            if (i2 < 0) {
                return null;
            }
            m263getSizeimpl = i2;
        }
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: lastOrNull-MShoTSo  reason: not valid java name */
    private static final ULong m965lastOrNullMShoTSo(long[] lastOrNull, Function1<? super ULong, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(lastOrNull, "$this$lastOrNull");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int m419getSizeimpl = ULongArray.m419getSizeimpl(lastOrNull) - 1;
        if (m419getSizeimpl < 0) {
            return null;
        }
        while (true) {
            int i2 = m419getSizeimpl - 1;
            long m418getsVKNKU = ULongArray.m418getsVKNKU(lastOrNull, m419getSizeimpl);
            if (predicate.invoke(ULong.m353boximpl(m418getsVKNKU)).booleanValue()) {
                return ULong.m353boximpl(m418getsVKNKU);
            }
            if (i2 < 0) {
                return null;
            }
            m419getSizeimpl = i2;
        }
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Nullable
    /* renamed from: lastOrNull-QwZRm1k  reason: not valid java name */
    public static final ULong m966lastOrNullQwZRm1k(@NotNull long[] lastOrNull) {
        Intrinsics.checkNotNullParameter(lastOrNull, "$this$lastOrNull");
        if (ULongArray.m421isEmptyimpl(lastOrNull)) {
            return null;
        }
        return ULong.m353boximpl(ULongArray.m418getsVKNKU(lastOrNull, ULongArray.m419getSizeimpl(lastOrNull) - 1));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: lastOrNull-jgv0xPQ  reason: not valid java name */
    private static final UInt m967lastOrNulljgv0xPQ(int[] lastOrNull, Function1<? super UInt, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(lastOrNull, "$this$lastOrNull");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int m341getSizeimpl = UIntArray.m341getSizeimpl(lastOrNull) - 1;
        if (m341getSizeimpl < 0) {
            return null;
        }
        while (true) {
            int i2 = m341getSizeimpl - 1;
            int m340getpVg5ArA = UIntArray.m340getpVg5ArA(lastOrNull, m341getSizeimpl);
            if (predicate.invoke(UInt.m275boximpl(m340getpVg5ArA)).booleanValue()) {
                return UInt.m275boximpl(m340getpVg5ArA);
            }
            if (i2 < 0) {
                return null;
            }
            m341getSizeimpl = i2;
        }
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Nullable
    /* renamed from: lastOrNull-rL5Bavg  reason: not valid java name */
    public static final UShort m968lastOrNullrL5Bavg(@NotNull short[] lastOrNull) {
        Intrinsics.checkNotNullParameter(lastOrNull, "$this$lastOrNull");
        if (UShortArray.m525isEmptyimpl(lastOrNull)) {
            return null;
        }
        return UShort.m459boximpl(UShortArray.m522getMh2AYeg(lastOrNull, UShortArray.m523getSizeimpl(lastOrNull) - 1));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: lastOrNull-xTcfx_M  reason: not valid java name */
    private static final UShort m969lastOrNullxTcfx_M(short[] lastOrNull, Function1<? super UShort, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(lastOrNull, "$this$lastOrNull");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int m523getSizeimpl = UShortArray.m523getSizeimpl(lastOrNull) - 1;
        if (m523getSizeimpl < 0) {
            return null;
        }
        while (true) {
            int i2 = m523getSizeimpl - 1;
            short m522getMh2AYeg = UShortArray.m522getMh2AYeg(lastOrNull, m523getSizeimpl);
            if (predicate.invoke(UShort.m459boximpl(m522getMh2AYeg)).booleanValue()) {
                return UShort.m459boximpl(m522getMh2AYeg);
            }
            if (i2 < 0) {
                return null;
            }
            m523getSizeimpl = i2;
        }
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: map-JOV_ifY  reason: not valid java name */
    private static final <R> List<R> m970mapJOV_ifY(byte[] map, Function1<? super UByte, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(map, "$this$map");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList(UByteArray.m263getSizeimpl(map));
        int m263getSizeimpl = UByteArray.m263getSizeimpl(map);
        for (int i2 = 0; i2 < m263getSizeimpl; i2++) {
            arrayList.add(transform.invoke(UByte.m199boximpl(UByteArray.m262getw2LRezQ(map, i2))));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: map-MShoTSo  reason: not valid java name */
    private static final <R> List<R> m971mapMShoTSo(long[] map, Function1<? super ULong, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(map, "$this$map");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList(ULongArray.m419getSizeimpl(map));
        int m419getSizeimpl = ULongArray.m419getSizeimpl(map);
        for (int i2 = 0; i2 < m419getSizeimpl; i2++) {
            arrayList.add(transform.invoke(ULong.m353boximpl(ULongArray.m418getsVKNKU(map, i2))));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: map-jgv0xPQ  reason: not valid java name */
    private static final <R> List<R> m972mapjgv0xPQ(int[] map, Function1<? super UInt, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(map, "$this$map");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList(UIntArray.m341getSizeimpl(map));
        int m341getSizeimpl = UIntArray.m341getSizeimpl(map);
        for (int i2 = 0; i2 < m341getSizeimpl; i2++) {
            arrayList.add(transform.invoke(UInt.m275boximpl(UIntArray.m340getpVg5ArA(map, i2))));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: map-xTcfx_M  reason: not valid java name */
    private static final <R> List<R> m973mapxTcfx_M(short[] map, Function1<? super UShort, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(map, "$this$map");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList(UShortArray.m523getSizeimpl(map));
        int m523getSizeimpl = UShortArray.m523getSizeimpl(map);
        for (int i2 = 0; i2 < m523getSizeimpl; i2++) {
            arrayList.add(transform.invoke(UShort.m459boximpl(UShortArray.m522getMh2AYeg(map, i2))));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: mapIndexed-ELGow60  reason: not valid java name */
    private static final <R> List<R> m974mapIndexedELGow60(byte[] mapIndexed, Function2<? super Integer, ? super UByte, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(mapIndexed, "$this$mapIndexed");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList(UByteArray.m263getSizeimpl(mapIndexed));
        int m263getSizeimpl = UByteArray.m263getSizeimpl(mapIndexed);
        int i2 = 0;
        int i3 = 0;
        while (i2 < m263getSizeimpl) {
            arrayList.add(transform.invoke(Integer.valueOf(i3), UByte.m199boximpl(UByteArray.m262getw2LRezQ(mapIndexed, i2))));
            i2++;
            i3++;
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: mapIndexed-WyvcNBI  reason: not valid java name */
    private static final <R> List<R> m975mapIndexedWyvcNBI(int[] mapIndexed, Function2<? super Integer, ? super UInt, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(mapIndexed, "$this$mapIndexed");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList(UIntArray.m341getSizeimpl(mapIndexed));
        int m341getSizeimpl = UIntArray.m341getSizeimpl(mapIndexed);
        int i2 = 0;
        int i3 = 0;
        while (i2 < m341getSizeimpl) {
            arrayList.add(transform.invoke(Integer.valueOf(i3), UInt.m275boximpl(UIntArray.m340getpVg5ArA(mapIndexed, i2))));
            i2++;
            i3++;
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: mapIndexed-s8dVfGU  reason: not valid java name */
    private static final <R> List<R> m976mapIndexeds8dVfGU(long[] mapIndexed, Function2<? super Integer, ? super ULong, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(mapIndexed, "$this$mapIndexed");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList(ULongArray.m419getSizeimpl(mapIndexed));
        int m419getSizeimpl = ULongArray.m419getSizeimpl(mapIndexed);
        int i2 = 0;
        int i3 = 0;
        while (i2 < m419getSizeimpl) {
            arrayList.add(transform.invoke(Integer.valueOf(i3), ULong.m353boximpl(ULongArray.m418getsVKNKU(mapIndexed, i2))));
            i2++;
            i3++;
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: mapIndexed-xzaTVY8  reason: not valid java name */
    private static final <R> List<R> m977mapIndexedxzaTVY8(short[] mapIndexed, Function2<? super Integer, ? super UShort, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(mapIndexed, "$this$mapIndexed");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList(UShortArray.m523getSizeimpl(mapIndexed));
        int m523getSizeimpl = UShortArray.m523getSizeimpl(mapIndexed);
        int i2 = 0;
        int i3 = 0;
        while (i2 < m523getSizeimpl) {
            arrayList.add(transform.invoke(Integer.valueOf(i3), UShort.m459boximpl(UShortArray.m522getMh2AYeg(mapIndexed, i2))));
            i2++;
            i3++;
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: mapIndexedTo--6EtJGI  reason: not valid java name */
    private static final <R, C extends Collection<? super R>> C m978mapIndexedTo6EtJGI(int[] mapIndexedTo, C destination, Function2<? super Integer, ? super UInt, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(mapIndexedTo, "$this$mapIndexedTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int m341getSizeimpl = UIntArray.m341getSizeimpl(mapIndexedTo);
        int i2 = 0;
        int i3 = 0;
        while (i2 < m341getSizeimpl) {
            destination.add(transform.invoke(Integer.valueOf(i3), UInt.m275boximpl(UIntArray.m340getpVg5ArA(mapIndexedTo, i2))));
            i2++;
            i3++;
        }
        return destination;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: mapIndexedTo-QqktQ3k  reason: not valid java name */
    private static final <R, C extends Collection<? super R>> C m979mapIndexedToQqktQ3k(short[] mapIndexedTo, C destination, Function2<? super Integer, ? super UShort, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(mapIndexedTo, "$this$mapIndexedTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int m523getSizeimpl = UShortArray.m523getSizeimpl(mapIndexedTo);
        int i2 = 0;
        int i3 = 0;
        while (i2 < m523getSizeimpl) {
            destination.add(transform.invoke(Integer.valueOf(i3), UShort.m459boximpl(UShortArray.m522getMh2AYeg(mapIndexedTo, i2))));
            i2++;
            i3++;
        }
        return destination;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: mapIndexedTo-eNpIKz8  reason: not valid java name */
    private static final <R, C extends Collection<? super R>> C m980mapIndexedToeNpIKz8(byte[] mapIndexedTo, C destination, Function2<? super Integer, ? super UByte, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(mapIndexedTo, "$this$mapIndexedTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int m263getSizeimpl = UByteArray.m263getSizeimpl(mapIndexedTo);
        int i2 = 0;
        int i3 = 0;
        while (i2 < m263getSizeimpl) {
            destination.add(transform.invoke(Integer.valueOf(i3), UByte.m199boximpl(UByteArray.m262getw2LRezQ(mapIndexedTo, i2))));
            i2++;
            i3++;
        }
        return destination;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: mapIndexedTo-pe2Q0Dw  reason: not valid java name */
    private static final <R, C extends Collection<? super R>> C m981mapIndexedTope2Q0Dw(long[] mapIndexedTo, C destination, Function2<? super Integer, ? super ULong, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(mapIndexedTo, "$this$mapIndexedTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int m419getSizeimpl = ULongArray.m419getSizeimpl(mapIndexedTo);
        int i2 = 0;
        int i3 = 0;
        while (i2 < m419getSizeimpl) {
            destination.add(transform.invoke(Integer.valueOf(i3), ULong.m353boximpl(ULongArray.m418getsVKNKU(mapIndexedTo, i2))));
            i2++;
            i3++;
        }
        return destination;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: mapTo-HqK1JgA  reason: not valid java name */
    private static final <R, C extends Collection<? super R>> C m982mapToHqK1JgA(long[] mapTo, C destination, Function1<? super ULong, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(mapTo, "$this$mapTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int m419getSizeimpl = ULongArray.m419getSizeimpl(mapTo);
        for (int i2 = 0; i2 < m419getSizeimpl; i2++) {
            destination.add(transform.invoke(ULong.m353boximpl(ULongArray.m418getsVKNKU(mapTo, i2))));
        }
        return destination;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: mapTo-oEOeDjA  reason: not valid java name */
    private static final <R, C extends Collection<? super R>> C m983mapTooEOeDjA(short[] mapTo, C destination, Function1<? super UShort, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(mapTo, "$this$mapTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int m523getSizeimpl = UShortArray.m523getSizeimpl(mapTo);
        for (int i2 = 0; i2 < m523getSizeimpl; i2++) {
            destination.add(transform.invoke(UShort.m459boximpl(UShortArray.m522getMh2AYeg(mapTo, i2))));
        }
        return destination;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: mapTo-wU5IKMo  reason: not valid java name */
    private static final <R, C extends Collection<? super R>> C m984mapTowU5IKMo(int[] mapTo, C destination, Function1<? super UInt, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(mapTo, "$this$mapTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int m341getSizeimpl = UIntArray.m341getSizeimpl(mapTo);
        for (int i2 = 0; i2 < m341getSizeimpl; i2++) {
            destination.add(transform.invoke(UInt.m275boximpl(UIntArray.m340getpVg5ArA(mapTo, i2))));
        }
        return destination;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: mapTo-wzUQCXU  reason: not valid java name */
    private static final <R, C extends Collection<? super R>> C m985mapTowzUQCXU(byte[] mapTo, C destination, Function1<? super UByte, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(mapTo, "$this$mapTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int m263getSizeimpl = UByteArray.m263getSizeimpl(mapTo);
        for (int i2 = 0; i2 < m263getSizeimpl; i2++) {
            destination.add(transform.invoke(UByte.m199boximpl(UByteArray.m262getw2LRezQ(mapTo, i2))));
        }
        return destination;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @Deprecated(message = "Use maxByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    /* renamed from: maxBy-JOV_ifY  reason: not valid java name */
    private static final /* synthetic */ <R extends Comparable<? super R>> UByte m990maxByJOV_ifY(byte[] maxBy, Function1<? super UByte, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(maxBy, "$this$maxBy");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (UByteArray.m265isEmptyimpl(maxBy)) {
            return null;
        }
        byte m262getw2LRezQ = UByteArray.m262getw2LRezQ(maxBy, 0);
        lastIndex = ArraysKt___ArraysKt.getLastIndex(maxBy);
        if (lastIndex != 0) {
            R invoke = selector.invoke(UByte.m199boximpl(m262getw2LRezQ));
            int i2 = 1;
            if (1 <= lastIndex) {
                while (true) {
                    byte m262getw2LRezQ2 = UByteArray.m262getw2LRezQ(maxBy, i2);
                    R invoke2 = selector.invoke(UByte.m199boximpl(m262getw2LRezQ2));
                    if (invoke.compareTo(invoke2) < 0) {
                        m262getw2LRezQ = m262getw2LRezQ2;
                        invoke = invoke2;
                    }
                    if (i2 == lastIndex) {
                        break;
                    }
                    i2++;
                }
            }
        }
        return UByte.m199boximpl(m262getw2LRezQ);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @Deprecated(message = "Use maxByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    /* renamed from: maxBy-MShoTSo  reason: not valid java name */
    private static final /* synthetic */ <R extends Comparable<? super R>> ULong m991maxByMShoTSo(long[] maxBy, Function1<? super ULong, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(maxBy, "$this$maxBy");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (ULongArray.m421isEmptyimpl(maxBy)) {
            return null;
        }
        long m418getsVKNKU = ULongArray.m418getsVKNKU(maxBy, 0);
        lastIndex = ArraysKt___ArraysKt.getLastIndex(maxBy);
        if (lastIndex != 0) {
            R invoke = selector.invoke(ULong.m353boximpl(m418getsVKNKU));
            int i2 = 1;
            if (1 <= lastIndex) {
                while (true) {
                    long m418getsVKNKU2 = ULongArray.m418getsVKNKU(maxBy, i2);
                    R invoke2 = selector.invoke(ULong.m353boximpl(m418getsVKNKU2));
                    if (invoke.compareTo(invoke2) < 0) {
                        m418getsVKNKU = m418getsVKNKU2;
                        invoke = invoke2;
                    }
                    if (i2 == lastIndex) {
                        break;
                    }
                    i2++;
                }
            }
        }
        return ULong.m353boximpl(m418getsVKNKU);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @Deprecated(message = "Use maxByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    /* renamed from: maxBy-jgv0xPQ  reason: not valid java name */
    private static final /* synthetic */ <R extends Comparable<? super R>> UInt m992maxByjgv0xPQ(int[] maxBy, Function1<? super UInt, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(maxBy, "$this$maxBy");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (UIntArray.m343isEmptyimpl(maxBy)) {
            return null;
        }
        int m340getpVg5ArA = UIntArray.m340getpVg5ArA(maxBy, 0);
        lastIndex = ArraysKt___ArraysKt.getLastIndex(maxBy);
        if (lastIndex != 0) {
            R invoke = selector.invoke(UInt.m275boximpl(m340getpVg5ArA));
            int i2 = 1;
            if (1 <= lastIndex) {
                while (true) {
                    int m340getpVg5ArA2 = UIntArray.m340getpVg5ArA(maxBy, i2);
                    R invoke2 = selector.invoke(UInt.m275boximpl(m340getpVg5ArA2));
                    if (invoke.compareTo(invoke2) < 0) {
                        m340getpVg5ArA = m340getpVg5ArA2;
                        invoke = invoke2;
                    }
                    if (i2 == lastIndex) {
                        break;
                    }
                    i2++;
                }
            }
        }
        return UInt.m275boximpl(m340getpVg5ArA);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @Deprecated(message = "Use maxByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    /* renamed from: maxBy-xTcfx_M  reason: not valid java name */
    private static final /* synthetic */ <R extends Comparable<? super R>> UShort m993maxByxTcfx_M(short[] maxBy, Function1<? super UShort, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(maxBy, "$this$maxBy");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (UShortArray.m525isEmptyimpl(maxBy)) {
            return null;
        }
        short m522getMh2AYeg = UShortArray.m522getMh2AYeg(maxBy, 0);
        lastIndex = ArraysKt___ArraysKt.getLastIndex(maxBy);
        if (lastIndex != 0) {
            R invoke = selector.invoke(UShort.m459boximpl(m522getMh2AYeg));
            int i2 = 1;
            if (1 <= lastIndex) {
                while (true) {
                    short m522getMh2AYeg2 = UShortArray.m522getMh2AYeg(maxBy, i2);
                    R invoke2 = selector.invoke(UShort.m459boximpl(m522getMh2AYeg2));
                    if (invoke.compareTo(invoke2) < 0) {
                        m522getMh2AYeg = m522getMh2AYeg2;
                        invoke = invoke2;
                    }
                    if (i2 == lastIndex) {
                        break;
                    }
                    i2++;
                }
            }
        }
        return UShort.m459boximpl(m522getMh2AYeg);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: maxByOrNull-JOV_ifY  reason: not valid java name */
    private static final <R extends Comparable<? super R>> UByte m994maxByOrNullJOV_ifY(byte[] maxByOrNull, Function1<? super UByte, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(maxByOrNull, "$this$maxByOrNull");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (UByteArray.m265isEmptyimpl(maxByOrNull)) {
            return null;
        }
        byte m262getw2LRezQ = UByteArray.m262getw2LRezQ(maxByOrNull, 0);
        lastIndex = ArraysKt___ArraysKt.getLastIndex(maxByOrNull);
        if (lastIndex == 0) {
            return UByte.m199boximpl(m262getw2LRezQ);
        }
        R invoke = selector.invoke(UByte.m199boximpl(m262getw2LRezQ));
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                byte m262getw2LRezQ2 = UByteArray.m262getw2LRezQ(maxByOrNull, i2);
                R invoke2 = selector.invoke(UByte.m199boximpl(m262getw2LRezQ2));
                if (invoke.compareTo(invoke2) < 0) {
                    m262getw2LRezQ = m262getw2LRezQ2;
                    invoke = invoke2;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return UByte.m199boximpl(m262getw2LRezQ);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: maxByOrNull-MShoTSo  reason: not valid java name */
    private static final <R extends Comparable<? super R>> ULong m995maxByOrNullMShoTSo(long[] maxByOrNull, Function1<? super ULong, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(maxByOrNull, "$this$maxByOrNull");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (ULongArray.m421isEmptyimpl(maxByOrNull)) {
            return null;
        }
        long m418getsVKNKU = ULongArray.m418getsVKNKU(maxByOrNull, 0);
        lastIndex = ArraysKt___ArraysKt.getLastIndex(maxByOrNull);
        if (lastIndex == 0) {
            return ULong.m353boximpl(m418getsVKNKU);
        }
        R invoke = selector.invoke(ULong.m353boximpl(m418getsVKNKU));
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                long m418getsVKNKU2 = ULongArray.m418getsVKNKU(maxByOrNull, i2);
                R invoke2 = selector.invoke(ULong.m353boximpl(m418getsVKNKU2));
                if (invoke.compareTo(invoke2) < 0) {
                    m418getsVKNKU = m418getsVKNKU2;
                    invoke = invoke2;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return ULong.m353boximpl(m418getsVKNKU);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: maxByOrNull-jgv0xPQ  reason: not valid java name */
    private static final <R extends Comparable<? super R>> UInt m996maxByOrNulljgv0xPQ(int[] maxByOrNull, Function1<? super UInt, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(maxByOrNull, "$this$maxByOrNull");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (UIntArray.m343isEmptyimpl(maxByOrNull)) {
            return null;
        }
        int m340getpVg5ArA = UIntArray.m340getpVg5ArA(maxByOrNull, 0);
        lastIndex = ArraysKt___ArraysKt.getLastIndex(maxByOrNull);
        if (lastIndex == 0) {
            return UInt.m275boximpl(m340getpVg5ArA);
        }
        R invoke = selector.invoke(UInt.m275boximpl(m340getpVg5ArA));
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                int m340getpVg5ArA2 = UIntArray.m340getpVg5ArA(maxByOrNull, i2);
                R invoke2 = selector.invoke(UInt.m275boximpl(m340getpVg5ArA2));
                if (invoke.compareTo(invoke2) < 0) {
                    m340getpVg5ArA = m340getpVg5ArA2;
                    invoke = invoke2;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return UInt.m275boximpl(m340getpVg5ArA);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: maxByOrNull-xTcfx_M  reason: not valid java name */
    private static final <R extends Comparable<? super R>> UShort m997maxByOrNullxTcfx_M(short[] maxByOrNull, Function1<? super UShort, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(maxByOrNull, "$this$maxByOrNull");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (UShortArray.m525isEmptyimpl(maxByOrNull)) {
            return null;
        }
        short m522getMh2AYeg = UShortArray.m522getMh2AYeg(maxByOrNull, 0);
        lastIndex = ArraysKt___ArraysKt.getLastIndex(maxByOrNull);
        if (lastIndex == 0) {
            return UShort.m459boximpl(m522getMh2AYeg);
        }
        R invoke = selector.invoke(UShort.m459boximpl(m522getMh2AYeg));
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                short m522getMh2AYeg2 = UShortArray.m522getMh2AYeg(maxByOrNull, i2);
                R invoke2 = selector.invoke(UShort.m459boximpl(m522getMh2AYeg2));
                if (invoke.compareTo(invoke2) < 0) {
                    m522getMh2AYeg = m522getMh2AYeg2;
                    invoke = invoke2;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return UShort.m459boximpl(m522getMh2AYeg);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* renamed from: maxOf-JOV_ifY  reason: not valid java name */
    private static final double m998maxOfJOV_ifY(byte[] maxOf, Function1<? super UByte, Double> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(maxOf, "$this$maxOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (UByteArray.m265isEmptyimpl(maxOf)) {
            throw new NoSuchElementException();
        }
        double doubleValue = selector.invoke(UByte.m199boximpl(UByteArray.m262getw2LRezQ(maxOf, 0))).doubleValue();
        lastIndex = ArraysKt___ArraysKt.getLastIndex(maxOf);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                doubleValue = Math.max(doubleValue, selector.invoke(UByte.m199boximpl(UByteArray.m262getw2LRezQ(maxOf, i2))).doubleValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return doubleValue;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* renamed from: maxOf-JOV_ifY  reason: not valid java name */
    private static final float m999maxOfJOV_ifY(byte[] maxOf, Function1<? super UByte, Float> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(maxOf, "$this$maxOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (UByteArray.m265isEmptyimpl(maxOf)) {
            throw new NoSuchElementException();
        }
        float floatValue = selector.invoke(UByte.m199boximpl(UByteArray.m262getw2LRezQ(maxOf, 0))).floatValue();
        lastIndex = ArraysKt___ArraysKt.getLastIndex(maxOf);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                floatValue = Math.max(floatValue, selector.invoke(UByte.m199boximpl(UByteArray.m262getw2LRezQ(maxOf, i2))).floatValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return floatValue;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* renamed from: maxOf-JOV_ifY  reason: not valid java name */
    private static final <R extends Comparable<? super R>> R m1000maxOfJOV_ifY(byte[] maxOf, Function1<? super UByte, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(maxOf, "$this$maxOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (UByteArray.m265isEmptyimpl(maxOf)) {
            throw new NoSuchElementException();
        }
        R invoke = selector.invoke(UByte.m199boximpl(UByteArray.m262getw2LRezQ(maxOf, 0)));
        lastIndex = ArraysKt___ArraysKt.getLastIndex(maxOf);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                R invoke2 = selector.invoke(UByte.m199boximpl(UByteArray.m262getw2LRezQ(maxOf, i2)));
                if (invoke.compareTo(invoke2) < 0) {
                    invoke = invoke2;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return invoke;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* renamed from: maxOf-MShoTSo  reason: not valid java name */
    private static final double m1001maxOfMShoTSo(long[] maxOf, Function1<? super ULong, Double> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(maxOf, "$this$maxOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (ULongArray.m421isEmptyimpl(maxOf)) {
            throw new NoSuchElementException();
        }
        double doubleValue = selector.invoke(ULong.m353boximpl(ULongArray.m418getsVKNKU(maxOf, 0))).doubleValue();
        lastIndex = ArraysKt___ArraysKt.getLastIndex(maxOf);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                doubleValue = Math.max(doubleValue, selector.invoke(ULong.m353boximpl(ULongArray.m418getsVKNKU(maxOf, i2))).doubleValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return doubleValue;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* renamed from: maxOf-MShoTSo  reason: not valid java name */
    private static final float m1002maxOfMShoTSo(long[] maxOf, Function1<? super ULong, Float> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(maxOf, "$this$maxOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (ULongArray.m421isEmptyimpl(maxOf)) {
            throw new NoSuchElementException();
        }
        float floatValue = selector.invoke(ULong.m353boximpl(ULongArray.m418getsVKNKU(maxOf, 0))).floatValue();
        lastIndex = ArraysKt___ArraysKt.getLastIndex(maxOf);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                floatValue = Math.max(floatValue, selector.invoke(ULong.m353boximpl(ULongArray.m418getsVKNKU(maxOf, i2))).floatValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return floatValue;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* renamed from: maxOf-MShoTSo  reason: not valid java name */
    private static final <R extends Comparable<? super R>> R m1003maxOfMShoTSo(long[] maxOf, Function1<? super ULong, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(maxOf, "$this$maxOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (ULongArray.m421isEmptyimpl(maxOf)) {
            throw new NoSuchElementException();
        }
        R invoke = selector.invoke(ULong.m353boximpl(ULongArray.m418getsVKNKU(maxOf, 0)));
        lastIndex = ArraysKt___ArraysKt.getLastIndex(maxOf);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                R invoke2 = selector.invoke(ULong.m353boximpl(ULongArray.m418getsVKNKU(maxOf, i2)));
                if (invoke.compareTo(invoke2) < 0) {
                    invoke = invoke2;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return invoke;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* renamed from: maxOf-jgv0xPQ  reason: not valid java name */
    private static final double m1004maxOfjgv0xPQ(int[] maxOf, Function1<? super UInt, Double> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(maxOf, "$this$maxOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (UIntArray.m343isEmptyimpl(maxOf)) {
            throw new NoSuchElementException();
        }
        double doubleValue = selector.invoke(UInt.m275boximpl(UIntArray.m340getpVg5ArA(maxOf, 0))).doubleValue();
        lastIndex = ArraysKt___ArraysKt.getLastIndex(maxOf);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                doubleValue = Math.max(doubleValue, selector.invoke(UInt.m275boximpl(UIntArray.m340getpVg5ArA(maxOf, i2))).doubleValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return doubleValue;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* renamed from: maxOf-jgv0xPQ  reason: not valid java name */
    private static final float m1005maxOfjgv0xPQ(int[] maxOf, Function1<? super UInt, Float> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(maxOf, "$this$maxOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (UIntArray.m343isEmptyimpl(maxOf)) {
            throw new NoSuchElementException();
        }
        float floatValue = selector.invoke(UInt.m275boximpl(UIntArray.m340getpVg5ArA(maxOf, 0))).floatValue();
        lastIndex = ArraysKt___ArraysKt.getLastIndex(maxOf);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                floatValue = Math.max(floatValue, selector.invoke(UInt.m275boximpl(UIntArray.m340getpVg5ArA(maxOf, i2))).floatValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return floatValue;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* renamed from: maxOf-jgv0xPQ  reason: not valid java name */
    private static final <R extends Comparable<? super R>> R m1006maxOfjgv0xPQ(int[] maxOf, Function1<? super UInt, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(maxOf, "$this$maxOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (UIntArray.m343isEmptyimpl(maxOf)) {
            throw new NoSuchElementException();
        }
        R invoke = selector.invoke(UInt.m275boximpl(UIntArray.m340getpVg5ArA(maxOf, 0)));
        lastIndex = ArraysKt___ArraysKt.getLastIndex(maxOf);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                R invoke2 = selector.invoke(UInt.m275boximpl(UIntArray.m340getpVg5ArA(maxOf, i2)));
                if (invoke.compareTo(invoke2) < 0) {
                    invoke = invoke2;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return invoke;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* renamed from: maxOf-xTcfx_M  reason: not valid java name */
    private static final double m1007maxOfxTcfx_M(short[] maxOf, Function1<? super UShort, Double> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(maxOf, "$this$maxOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (UShortArray.m525isEmptyimpl(maxOf)) {
            throw new NoSuchElementException();
        }
        double doubleValue = selector.invoke(UShort.m459boximpl(UShortArray.m522getMh2AYeg(maxOf, 0))).doubleValue();
        lastIndex = ArraysKt___ArraysKt.getLastIndex(maxOf);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                doubleValue = Math.max(doubleValue, selector.invoke(UShort.m459boximpl(UShortArray.m522getMh2AYeg(maxOf, i2))).doubleValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return doubleValue;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* renamed from: maxOf-xTcfx_M  reason: not valid java name */
    private static final float m1008maxOfxTcfx_M(short[] maxOf, Function1<? super UShort, Float> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(maxOf, "$this$maxOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (UShortArray.m525isEmptyimpl(maxOf)) {
            throw new NoSuchElementException();
        }
        float floatValue = selector.invoke(UShort.m459boximpl(UShortArray.m522getMh2AYeg(maxOf, 0))).floatValue();
        lastIndex = ArraysKt___ArraysKt.getLastIndex(maxOf);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                floatValue = Math.max(floatValue, selector.invoke(UShort.m459boximpl(UShortArray.m522getMh2AYeg(maxOf, i2))).floatValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return floatValue;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* renamed from: maxOf-xTcfx_M  reason: not valid java name */
    private static final <R extends Comparable<? super R>> R m1009maxOfxTcfx_M(short[] maxOf, Function1<? super UShort, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(maxOf, "$this$maxOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (UShortArray.m525isEmptyimpl(maxOf)) {
            throw new NoSuchElementException();
        }
        R invoke = selector.invoke(UShort.m459boximpl(UShortArray.m522getMh2AYeg(maxOf, 0)));
        lastIndex = ArraysKt___ArraysKt.getLastIndex(maxOf);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                R invoke2 = selector.invoke(UShort.m459boximpl(UShortArray.m522getMh2AYeg(maxOf, i2)));
                if (invoke.compareTo(invoke2) < 0) {
                    invoke = invoke2;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return invoke;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* renamed from: maxOfOrNull-JOV_ifY  reason: not valid java name */
    private static final <R extends Comparable<? super R>> R m1010maxOfOrNullJOV_ifY(byte[] maxOfOrNull, Function1<? super UByte, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(maxOfOrNull, "$this$maxOfOrNull");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (UByteArray.m265isEmptyimpl(maxOfOrNull)) {
            return null;
        }
        R invoke = selector.invoke(UByte.m199boximpl(UByteArray.m262getw2LRezQ(maxOfOrNull, 0)));
        lastIndex = ArraysKt___ArraysKt.getLastIndex(maxOfOrNull);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                R invoke2 = selector.invoke(UByte.m199boximpl(UByteArray.m262getw2LRezQ(maxOfOrNull, i2)));
                if (invoke.compareTo(invoke2) < 0) {
                    invoke = invoke2;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return invoke;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* renamed from: maxOfOrNull-JOV_ifY  reason: not valid java name */
    private static final Double m1011maxOfOrNullJOV_ifY(byte[] maxOfOrNull, Function1<? super UByte, Double> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(maxOfOrNull, "$this$maxOfOrNull");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (UByteArray.m265isEmptyimpl(maxOfOrNull)) {
            return null;
        }
        double doubleValue = selector.invoke(UByte.m199boximpl(UByteArray.m262getw2LRezQ(maxOfOrNull, 0))).doubleValue();
        lastIndex = ArraysKt___ArraysKt.getLastIndex(maxOfOrNull);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                doubleValue = Math.max(doubleValue, selector.invoke(UByte.m199boximpl(UByteArray.m262getw2LRezQ(maxOfOrNull, i2))).doubleValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Double.valueOf(doubleValue);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* renamed from: maxOfOrNull-JOV_ifY  reason: not valid java name */
    private static final Float m1012maxOfOrNullJOV_ifY(byte[] maxOfOrNull, Function1<? super UByte, Float> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(maxOfOrNull, "$this$maxOfOrNull");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (UByteArray.m265isEmptyimpl(maxOfOrNull)) {
            return null;
        }
        float floatValue = selector.invoke(UByte.m199boximpl(UByteArray.m262getw2LRezQ(maxOfOrNull, 0))).floatValue();
        lastIndex = ArraysKt___ArraysKt.getLastIndex(maxOfOrNull);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                floatValue = Math.max(floatValue, selector.invoke(UByte.m199boximpl(UByteArray.m262getw2LRezQ(maxOfOrNull, i2))).floatValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Float.valueOf(floatValue);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* renamed from: maxOfOrNull-MShoTSo  reason: not valid java name */
    private static final <R extends Comparable<? super R>> R m1013maxOfOrNullMShoTSo(long[] maxOfOrNull, Function1<? super ULong, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(maxOfOrNull, "$this$maxOfOrNull");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (ULongArray.m421isEmptyimpl(maxOfOrNull)) {
            return null;
        }
        R invoke = selector.invoke(ULong.m353boximpl(ULongArray.m418getsVKNKU(maxOfOrNull, 0)));
        lastIndex = ArraysKt___ArraysKt.getLastIndex(maxOfOrNull);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                R invoke2 = selector.invoke(ULong.m353boximpl(ULongArray.m418getsVKNKU(maxOfOrNull, i2)));
                if (invoke.compareTo(invoke2) < 0) {
                    invoke = invoke2;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return invoke;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* renamed from: maxOfOrNull-MShoTSo  reason: not valid java name */
    private static final Double m1014maxOfOrNullMShoTSo(long[] maxOfOrNull, Function1<? super ULong, Double> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(maxOfOrNull, "$this$maxOfOrNull");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (ULongArray.m421isEmptyimpl(maxOfOrNull)) {
            return null;
        }
        double doubleValue = selector.invoke(ULong.m353boximpl(ULongArray.m418getsVKNKU(maxOfOrNull, 0))).doubleValue();
        lastIndex = ArraysKt___ArraysKt.getLastIndex(maxOfOrNull);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                doubleValue = Math.max(doubleValue, selector.invoke(ULong.m353boximpl(ULongArray.m418getsVKNKU(maxOfOrNull, i2))).doubleValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Double.valueOf(doubleValue);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* renamed from: maxOfOrNull-MShoTSo  reason: not valid java name */
    private static final Float m1015maxOfOrNullMShoTSo(long[] maxOfOrNull, Function1<? super ULong, Float> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(maxOfOrNull, "$this$maxOfOrNull");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (ULongArray.m421isEmptyimpl(maxOfOrNull)) {
            return null;
        }
        float floatValue = selector.invoke(ULong.m353boximpl(ULongArray.m418getsVKNKU(maxOfOrNull, 0))).floatValue();
        lastIndex = ArraysKt___ArraysKt.getLastIndex(maxOfOrNull);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                floatValue = Math.max(floatValue, selector.invoke(ULong.m353boximpl(ULongArray.m418getsVKNKU(maxOfOrNull, i2))).floatValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Float.valueOf(floatValue);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* renamed from: maxOfOrNull-jgv0xPQ  reason: not valid java name */
    private static final <R extends Comparable<? super R>> R m1016maxOfOrNulljgv0xPQ(int[] maxOfOrNull, Function1<? super UInt, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(maxOfOrNull, "$this$maxOfOrNull");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (UIntArray.m343isEmptyimpl(maxOfOrNull)) {
            return null;
        }
        R invoke = selector.invoke(UInt.m275boximpl(UIntArray.m340getpVg5ArA(maxOfOrNull, 0)));
        lastIndex = ArraysKt___ArraysKt.getLastIndex(maxOfOrNull);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                R invoke2 = selector.invoke(UInt.m275boximpl(UIntArray.m340getpVg5ArA(maxOfOrNull, i2)));
                if (invoke.compareTo(invoke2) < 0) {
                    invoke = invoke2;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return invoke;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* renamed from: maxOfOrNull-jgv0xPQ  reason: not valid java name */
    private static final Double m1017maxOfOrNulljgv0xPQ(int[] maxOfOrNull, Function1<? super UInt, Double> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(maxOfOrNull, "$this$maxOfOrNull");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (UIntArray.m343isEmptyimpl(maxOfOrNull)) {
            return null;
        }
        double doubleValue = selector.invoke(UInt.m275boximpl(UIntArray.m340getpVg5ArA(maxOfOrNull, 0))).doubleValue();
        lastIndex = ArraysKt___ArraysKt.getLastIndex(maxOfOrNull);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                doubleValue = Math.max(doubleValue, selector.invoke(UInt.m275boximpl(UIntArray.m340getpVg5ArA(maxOfOrNull, i2))).doubleValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Double.valueOf(doubleValue);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* renamed from: maxOfOrNull-jgv0xPQ  reason: not valid java name */
    private static final Float m1018maxOfOrNulljgv0xPQ(int[] maxOfOrNull, Function1<? super UInt, Float> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(maxOfOrNull, "$this$maxOfOrNull");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (UIntArray.m343isEmptyimpl(maxOfOrNull)) {
            return null;
        }
        float floatValue = selector.invoke(UInt.m275boximpl(UIntArray.m340getpVg5ArA(maxOfOrNull, 0))).floatValue();
        lastIndex = ArraysKt___ArraysKt.getLastIndex(maxOfOrNull);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                floatValue = Math.max(floatValue, selector.invoke(UInt.m275boximpl(UIntArray.m340getpVg5ArA(maxOfOrNull, i2))).floatValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Float.valueOf(floatValue);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* renamed from: maxOfOrNull-xTcfx_M  reason: not valid java name */
    private static final <R extends Comparable<? super R>> R m1019maxOfOrNullxTcfx_M(short[] maxOfOrNull, Function1<? super UShort, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(maxOfOrNull, "$this$maxOfOrNull");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (UShortArray.m525isEmptyimpl(maxOfOrNull)) {
            return null;
        }
        R invoke = selector.invoke(UShort.m459boximpl(UShortArray.m522getMh2AYeg(maxOfOrNull, 0)));
        lastIndex = ArraysKt___ArraysKt.getLastIndex(maxOfOrNull);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                R invoke2 = selector.invoke(UShort.m459boximpl(UShortArray.m522getMh2AYeg(maxOfOrNull, i2)));
                if (invoke.compareTo(invoke2) < 0) {
                    invoke = invoke2;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return invoke;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* renamed from: maxOfOrNull-xTcfx_M  reason: not valid java name */
    private static final Double m1020maxOfOrNullxTcfx_M(short[] maxOfOrNull, Function1<? super UShort, Double> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(maxOfOrNull, "$this$maxOfOrNull");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (UShortArray.m525isEmptyimpl(maxOfOrNull)) {
            return null;
        }
        double doubleValue = selector.invoke(UShort.m459boximpl(UShortArray.m522getMh2AYeg(maxOfOrNull, 0))).doubleValue();
        lastIndex = ArraysKt___ArraysKt.getLastIndex(maxOfOrNull);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                doubleValue = Math.max(doubleValue, selector.invoke(UShort.m459boximpl(UShortArray.m522getMh2AYeg(maxOfOrNull, i2))).doubleValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Double.valueOf(doubleValue);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* renamed from: maxOfOrNull-xTcfx_M  reason: not valid java name */
    private static final Float m1021maxOfOrNullxTcfx_M(short[] maxOfOrNull, Function1<? super UShort, Float> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(maxOfOrNull, "$this$maxOfOrNull");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (UShortArray.m525isEmptyimpl(maxOfOrNull)) {
            return null;
        }
        float floatValue = selector.invoke(UShort.m459boximpl(UShortArray.m522getMh2AYeg(maxOfOrNull, 0))).floatValue();
        lastIndex = ArraysKt___ArraysKt.getLastIndex(maxOfOrNull);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                floatValue = Math.max(floatValue, selector.invoke(UShort.m459boximpl(UShortArray.m522getMh2AYeg(maxOfOrNull, i2))).floatValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Float.valueOf(floatValue);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* renamed from: maxOfWith-5NtCtWE  reason: not valid java name */
    private static final <R> R m1022maxOfWith5NtCtWE(long[] maxOfWith, Comparator<? super R> comparator, Function1<? super ULong, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(maxOfWith, "$this$maxOfWith");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (ULongArray.m421isEmptyimpl(maxOfWith)) {
            throw new NoSuchElementException();
        }
        Object obj = (R) selector.invoke(ULong.m353boximpl(ULongArray.m418getsVKNKU(maxOfWith, 0)));
        lastIndex = ArraysKt___ArraysKt.getLastIndex(maxOfWith);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                R invoke = selector.invoke(ULong.m353boximpl(ULongArray.m418getsVKNKU(maxOfWith, i2)));
                if (comparator.compare(obj, invoke) < 0) {
                    obj = invoke;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return (R) obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* renamed from: maxOfWith-LTi4i_s  reason: not valid java name */
    private static final <R> R m1023maxOfWithLTi4i_s(byte[] maxOfWith, Comparator<? super R> comparator, Function1<? super UByte, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(maxOfWith, "$this$maxOfWith");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (UByteArray.m265isEmptyimpl(maxOfWith)) {
            throw new NoSuchElementException();
        }
        Object obj = (R) selector.invoke(UByte.m199boximpl(UByteArray.m262getw2LRezQ(maxOfWith, 0)));
        lastIndex = ArraysKt___ArraysKt.getLastIndex(maxOfWith);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                R invoke = selector.invoke(UByte.m199boximpl(UByteArray.m262getw2LRezQ(maxOfWith, i2)));
                if (comparator.compare(obj, invoke) < 0) {
                    obj = invoke;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return (R) obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* renamed from: maxOfWith-l8EHGbQ  reason: not valid java name */
    private static final <R> R m1024maxOfWithl8EHGbQ(short[] maxOfWith, Comparator<? super R> comparator, Function1<? super UShort, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(maxOfWith, "$this$maxOfWith");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (UShortArray.m525isEmptyimpl(maxOfWith)) {
            throw new NoSuchElementException();
        }
        Object obj = (R) selector.invoke(UShort.m459boximpl(UShortArray.m522getMh2AYeg(maxOfWith, 0)));
        lastIndex = ArraysKt___ArraysKt.getLastIndex(maxOfWith);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                R invoke = selector.invoke(UShort.m459boximpl(UShortArray.m522getMh2AYeg(maxOfWith, i2)));
                if (comparator.compare(obj, invoke) < 0) {
                    obj = invoke;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return (R) obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* renamed from: maxOfWith-myNOsp4  reason: not valid java name */
    private static final <R> R m1025maxOfWithmyNOsp4(int[] maxOfWith, Comparator<? super R> comparator, Function1<? super UInt, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(maxOfWith, "$this$maxOfWith");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (UIntArray.m343isEmptyimpl(maxOfWith)) {
            throw new NoSuchElementException();
        }
        Object obj = (R) selector.invoke(UInt.m275boximpl(UIntArray.m340getpVg5ArA(maxOfWith, 0)));
        lastIndex = ArraysKt___ArraysKt.getLastIndex(maxOfWith);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                R invoke = selector.invoke(UInt.m275boximpl(UIntArray.m340getpVg5ArA(maxOfWith, i2)));
                if (comparator.compare(obj, invoke) < 0) {
                    obj = invoke;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return (R) obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* renamed from: maxOfWithOrNull-5NtCtWE  reason: not valid java name */
    private static final <R> R m1026maxOfWithOrNull5NtCtWE(long[] maxOfWithOrNull, Comparator<? super R> comparator, Function1<? super ULong, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(maxOfWithOrNull, "$this$maxOfWithOrNull");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (ULongArray.m421isEmptyimpl(maxOfWithOrNull)) {
            return null;
        }
        Object obj = (R) selector.invoke(ULong.m353boximpl(ULongArray.m418getsVKNKU(maxOfWithOrNull, 0)));
        lastIndex = ArraysKt___ArraysKt.getLastIndex(maxOfWithOrNull);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                R invoke = selector.invoke(ULong.m353boximpl(ULongArray.m418getsVKNKU(maxOfWithOrNull, i2)));
                if (comparator.compare(obj, invoke) < 0) {
                    obj = invoke;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return (R) obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* renamed from: maxOfWithOrNull-LTi4i_s  reason: not valid java name */
    private static final <R> R m1027maxOfWithOrNullLTi4i_s(byte[] maxOfWithOrNull, Comparator<? super R> comparator, Function1<? super UByte, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(maxOfWithOrNull, "$this$maxOfWithOrNull");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (UByteArray.m265isEmptyimpl(maxOfWithOrNull)) {
            return null;
        }
        Object obj = (R) selector.invoke(UByte.m199boximpl(UByteArray.m262getw2LRezQ(maxOfWithOrNull, 0)));
        lastIndex = ArraysKt___ArraysKt.getLastIndex(maxOfWithOrNull);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                R invoke = selector.invoke(UByte.m199boximpl(UByteArray.m262getw2LRezQ(maxOfWithOrNull, i2)));
                if (comparator.compare(obj, invoke) < 0) {
                    obj = invoke;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return (R) obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* renamed from: maxOfWithOrNull-l8EHGbQ  reason: not valid java name */
    private static final <R> R m1028maxOfWithOrNulll8EHGbQ(short[] maxOfWithOrNull, Comparator<? super R> comparator, Function1<? super UShort, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(maxOfWithOrNull, "$this$maxOfWithOrNull");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (UShortArray.m525isEmptyimpl(maxOfWithOrNull)) {
            return null;
        }
        Object obj = (R) selector.invoke(UShort.m459boximpl(UShortArray.m522getMh2AYeg(maxOfWithOrNull, 0)));
        lastIndex = ArraysKt___ArraysKt.getLastIndex(maxOfWithOrNull);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                R invoke = selector.invoke(UShort.m459boximpl(UShortArray.m522getMh2AYeg(maxOfWithOrNull, i2)));
                if (comparator.compare(obj, invoke) < 0) {
                    obj = invoke;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return (R) obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* renamed from: maxOfWithOrNull-myNOsp4  reason: not valid java name */
    private static final <R> R m1029maxOfWithOrNullmyNOsp4(int[] maxOfWithOrNull, Comparator<? super R> comparator, Function1<? super UInt, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(maxOfWithOrNull, "$this$maxOfWithOrNull");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (UIntArray.m343isEmptyimpl(maxOfWithOrNull)) {
            return null;
        }
        Object obj = (R) selector.invoke(UInt.m275boximpl(UIntArray.m340getpVg5ArA(maxOfWithOrNull, 0)));
        lastIndex = ArraysKt___ArraysKt.getLastIndex(maxOfWithOrNull);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                R invoke = selector.invoke(UInt.m275boximpl(UIntArray.m340getpVg5ArA(maxOfWithOrNull, i2)));
                if (comparator.compare(obj, invoke) < 0) {
                    obj = invoke;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return (R) obj;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @Nullable
    /* renamed from: maxOrNull--ajY-9A  reason: not valid java name */
    public static final UInt m1030maxOrNullajY9A(@NotNull int[] maxOrNull) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(maxOrNull, "$this$maxOrNull");
        if (UIntArray.m343isEmptyimpl(maxOrNull)) {
            return null;
        }
        int m340getpVg5ArA = UIntArray.m340getpVg5ArA(maxOrNull, 0);
        lastIndex = ArraysKt___ArraysKt.getLastIndex(maxOrNull);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                int m340getpVg5ArA2 = UIntArray.m340getpVg5ArA(maxOrNull, i2);
                if (UnsignedKt.uintCompare(m340getpVg5ArA, m340getpVg5ArA2) < 0) {
                    m340getpVg5ArA = m340getpVg5ArA2;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return UInt.m275boximpl(m340getpVg5ArA);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @Nullable
    /* renamed from: maxOrNull-GBYM_sE  reason: not valid java name */
    public static final UByte m1031maxOrNullGBYM_sE(@NotNull byte[] maxOrNull) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(maxOrNull, "$this$maxOrNull");
        if (UByteArray.m265isEmptyimpl(maxOrNull)) {
            return null;
        }
        byte m262getw2LRezQ = UByteArray.m262getw2LRezQ(maxOrNull, 0);
        lastIndex = ArraysKt___ArraysKt.getLastIndex(maxOrNull);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                byte m262getw2LRezQ2 = UByteArray.m262getw2LRezQ(maxOrNull, i2);
                if (Intrinsics.compare(m262getw2LRezQ & 255, m262getw2LRezQ2 & 255) < 0) {
                    m262getw2LRezQ = m262getw2LRezQ2;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return UByte.m199boximpl(m262getw2LRezQ);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @Nullable
    /* renamed from: maxOrNull-QwZRm1k  reason: not valid java name */
    public static final ULong m1032maxOrNullQwZRm1k(@NotNull long[] maxOrNull) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(maxOrNull, "$this$maxOrNull");
        if (ULongArray.m421isEmptyimpl(maxOrNull)) {
            return null;
        }
        long m418getsVKNKU = ULongArray.m418getsVKNKU(maxOrNull, 0);
        lastIndex = ArraysKt___ArraysKt.getLastIndex(maxOrNull);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                long m418getsVKNKU2 = ULongArray.m418getsVKNKU(maxOrNull, i2);
                if (UnsignedKt.ulongCompare(m418getsVKNKU, m418getsVKNKU2) < 0) {
                    m418getsVKNKU = m418getsVKNKU2;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return ULong.m353boximpl(m418getsVKNKU);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @Nullable
    /* renamed from: maxOrNull-rL5Bavg  reason: not valid java name */
    public static final UShort m1033maxOrNullrL5Bavg(@NotNull short[] maxOrNull) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(maxOrNull, "$this$maxOrNull");
        if (UShortArray.m525isEmptyimpl(maxOrNull)) {
            return null;
        }
        short m522getMh2AYeg = UShortArray.m522getMh2AYeg(maxOrNull, 0);
        lastIndex = ArraysKt___ArraysKt.getLastIndex(maxOrNull);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                short m522getMh2AYeg2 = UShortArray.m522getMh2AYeg(maxOrNull, i2);
                if (Intrinsics.compare(m522getMh2AYeg & UShort.MAX_VALUE, 65535 & m522getMh2AYeg2) < 0) {
                    m522getMh2AYeg = m522getMh2AYeg2;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return UShort.m459boximpl(m522getMh2AYeg);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Deprecated(message = "Use maxWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    /* renamed from: maxWith-XMRcp5o  reason: not valid java name */
    public static final /* synthetic */ UByte m1034maxWithXMRcp5o(byte[] maxWith, Comparator comparator) {
        Intrinsics.checkNotNullParameter(maxWith, "$this$maxWith");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        return m1038maxWithOrNullXMRcp5o(maxWith, comparator);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Deprecated(message = "Use maxWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    /* renamed from: maxWith-YmdZ_VM  reason: not valid java name */
    public static final /* synthetic */ UInt m1035maxWithYmdZ_VM(int[] maxWith, Comparator comparator) {
        Intrinsics.checkNotNullParameter(maxWith, "$this$maxWith");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        return m1039maxWithOrNullYmdZ_VM(maxWith, comparator);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Deprecated(message = "Use maxWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    /* renamed from: maxWith-eOHTfZs  reason: not valid java name */
    public static final /* synthetic */ UShort m1036maxWitheOHTfZs(short[] maxWith, Comparator comparator) {
        Intrinsics.checkNotNullParameter(maxWith, "$this$maxWith");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        return m1040maxWithOrNulleOHTfZs(maxWith, comparator);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Deprecated(message = "Use maxWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    /* renamed from: maxWith-zrEWJaI  reason: not valid java name */
    public static final /* synthetic */ ULong m1037maxWithzrEWJaI(long[] maxWith, Comparator comparator) {
        Intrinsics.checkNotNullParameter(maxWith, "$this$maxWith");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        return m1041maxWithOrNullzrEWJaI(maxWith, comparator);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @Nullable
    /* renamed from: maxWithOrNull-XMRcp5o  reason: not valid java name */
    public static final UByte m1038maxWithOrNullXMRcp5o(@NotNull byte[] maxWithOrNull, @NotNull Comparator<? super UByte> comparator) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(maxWithOrNull, "$this$maxWithOrNull");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (UByteArray.m265isEmptyimpl(maxWithOrNull)) {
            return null;
        }
        byte m262getw2LRezQ = UByteArray.m262getw2LRezQ(maxWithOrNull, 0);
        lastIndex = ArraysKt___ArraysKt.getLastIndex(maxWithOrNull);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                byte m262getw2LRezQ2 = UByteArray.m262getw2LRezQ(maxWithOrNull, i2);
                if (comparator.compare(UByte.m199boximpl(m262getw2LRezQ), UByte.m199boximpl(m262getw2LRezQ2)) < 0) {
                    m262getw2LRezQ = m262getw2LRezQ2;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return UByte.m199boximpl(m262getw2LRezQ);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @Nullable
    /* renamed from: maxWithOrNull-YmdZ_VM  reason: not valid java name */
    public static final UInt m1039maxWithOrNullYmdZ_VM(@NotNull int[] maxWithOrNull, @NotNull Comparator<? super UInt> comparator) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(maxWithOrNull, "$this$maxWithOrNull");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (UIntArray.m343isEmptyimpl(maxWithOrNull)) {
            return null;
        }
        int m340getpVg5ArA = UIntArray.m340getpVg5ArA(maxWithOrNull, 0);
        lastIndex = ArraysKt___ArraysKt.getLastIndex(maxWithOrNull);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                int m340getpVg5ArA2 = UIntArray.m340getpVg5ArA(maxWithOrNull, i2);
                if (comparator.compare(UInt.m275boximpl(m340getpVg5ArA), UInt.m275boximpl(m340getpVg5ArA2)) < 0) {
                    m340getpVg5ArA = m340getpVg5ArA2;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return UInt.m275boximpl(m340getpVg5ArA);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @Nullable
    /* renamed from: maxWithOrNull-eOHTfZs  reason: not valid java name */
    public static final UShort m1040maxWithOrNulleOHTfZs(@NotNull short[] maxWithOrNull, @NotNull Comparator<? super UShort> comparator) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(maxWithOrNull, "$this$maxWithOrNull");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (UShortArray.m525isEmptyimpl(maxWithOrNull)) {
            return null;
        }
        short m522getMh2AYeg = UShortArray.m522getMh2AYeg(maxWithOrNull, 0);
        lastIndex = ArraysKt___ArraysKt.getLastIndex(maxWithOrNull);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                short m522getMh2AYeg2 = UShortArray.m522getMh2AYeg(maxWithOrNull, i2);
                if (comparator.compare(UShort.m459boximpl(m522getMh2AYeg), UShort.m459boximpl(m522getMh2AYeg2)) < 0) {
                    m522getMh2AYeg = m522getMh2AYeg2;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return UShort.m459boximpl(m522getMh2AYeg);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @Nullable
    /* renamed from: maxWithOrNull-zrEWJaI  reason: not valid java name */
    public static final ULong m1041maxWithOrNullzrEWJaI(@NotNull long[] maxWithOrNull, @NotNull Comparator<? super ULong> comparator) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(maxWithOrNull, "$this$maxWithOrNull");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (ULongArray.m421isEmptyimpl(maxWithOrNull)) {
            return null;
        }
        long m418getsVKNKU = ULongArray.m418getsVKNKU(maxWithOrNull, 0);
        lastIndex = ArraysKt___ArraysKt.getLastIndex(maxWithOrNull);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                long m418getsVKNKU2 = ULongArray.m418getsVKNKU(maxWithOrNull, i2);
                if (comparator.compare(ULong.m353boximpl(m418getsVKNKU), ULong.m353boximpl(m418getsVKNKU2)) < 0) {
                    m418getsVKNKU = m418getsVKNKU2;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return ULong.m353boximpl(m418getsVKNKU);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @Deprecated(message = "Use minByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    /* renamed from: minBy-JOV_ifY  reason: not valid java name */
    private static final /* synthetic */ <R extends Comparable<? super R>> UByte m1046minByJOV_ifY(byte[] minBy, Function1<? super UByte, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(minBy, "$this$minBy");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (UByteArray.m265isEmptyimpl(minBy)) {
            return null;
        }
        byte m262getw2LRezQ = UByteArray.m262getw2LRezQ(minBy, 0);
        lastIndex = ArraysKt___ArraysKt.getLastIndex(minBy);
        if (lastIndex != 0) {
            R invoke = selector.invoke(UByte.m199boximpl(m262getw2LRezQ));
            int i2 = 1;
            if (1 <= lastIndex) {
                while (true) {
                    byte m262getw2LRezQ2 = UByteArray.m262getw2LRezQ(minBy, i2);
                    R invoke2 = selector.invoke(UByte.m199boximpl(m262getw2LRezQ2));
                    if (invoke.compareTo(invoke2) > 0) {
                        m262getw2LRezQ = m262getw2LRezQ2;
                        invoke = invoke2;
                    }
                    if (i2 == lastIndex) {
                        break;
                    }
                    i2++;
                }
            }
        }
        return UByte.m199boximpl(m262getw2LRezQ);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @Deprecated(message = "Use minByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    /* renamed from: minBy-MShoTSo  reason: not valid java name */
    private static final /* synthetic */ <R extends Comparable<? super R>> ULong m1047minByMShoTSo(long[] minBy, Function1<? super ULong, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(minBy, "$this$minBy");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (ULongArray.m421isEmptyimpl(minBy)) {
            return null;
        }
        long m418getsVKNKU = ULongArray.m418getsVKNKU(minBy, 0);
        lastIndex = ArraysKt___ArraysKt.getLastIndex(minBy);
        if (lastIndex != 0) {
            R invoke = selector.invoke(ULong.m353boximpl(m418getsVKNKU));
            int i2 = 1;
            if (1 <= lastIndex) {
                while (true) {
                    long m418getsVKNKU2 = ULongArray.m418getsVKNKU(minBy, i2);
                    R invoke2 = selector.invoke(ULong.m353boximpl(m418getsVKNKU2));
                    if (invoke.compareTo(invoke2) > 0) {
                        m418getsVKNKU = m418getsVKNKU2;
                        invoke = invoke2;
                    }
                    if (i2 == lastIndex) {
                        break;
                    }
                    i2++;
                }
            }
        }
        return ULong.m353boximpl(m418getsVKNKU);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @Deprecated(message = "Use minByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    /* renamed from: minBy-jgv0xPQ  reason: not valid java name */
    private static final /* synthetic */ <R extends Comparable<? super R>> UInt m1048minByjgv0xPQ(int[] minBy, Function1<? super UInt, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(minBy, "$this$minBy");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (UIntArray.m343isEmptyimpl(minBy)) {
            return null;
        }
        int m340getpVg5ArA = UIntArray.m340getpVg5ArA(minBy, 0);
        lastIndex = ArraysKt___ArraysKt.getLastIndex(minBy);
        if (lastIndex != 0) {
            R invoke = selector.invoke(UInt.m275boximpl(m340getpVg5ArA));
            int i2 = 1;
            if (1 <= lastIndex) {
                while (true) {
                    int m340getpVg5ArA2 = UIntArray.m340getpVg5ArA(minBy, i2);
                    R invoke2 = selector.invoke(UInt.m275boximpl(m340getpVg5ArA2));
                    if (invoke.compareTo(invoke2) > 0) {
                        m340getpVg5ArA = m340getpVg5ArA2;
                        invoke = invoke2;
                    }
                    if (i2 == lastIndex) {
                        break;
                    }
                    i2++;
                }
            }
        }
        return UInt.m275boximpl(m340getpVg5ArA);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @Deprecated(message = "Use minByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    /* renamed from: minBy-xTcfx_M  reason: not valid java name */
    private static final /* synthetic */ <R extends Comparable<? super R>> UShort m1049minByxTcfx_M(short[] minBy, Function1<? super UShort, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(minBy, "$this$minBy");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (UShortArray.m525isEmptyimpl(minBy)) {
            return null;
        }
        short m522getMh2AYeg = UShortArray.m522getMh2AYeg(minBy, 0);
        lastIndex = ArraysKt___ArraysKt.getLastIndex(minBy);
        if (lastIndex != 0) {
            R invoke = selector.invoke(UShort.m459boximpl(m522getMh2AYeg));
            int i2 = 1;
            if (1 <= lastIndex) {
                while (true) {
                    short m522getMh2AYeg2 = UShortArray.m522getMh2AYeg(minBy, i2);
                    R invoke2 = selector.invoke(UShort.m459boximpl(m522getMh2AYeg2));
                    if (invoke.compareTo(invoke2) > 0) {
                        m522getMh2AYeg = m522getMh2AYeg2;
                        invoke = invoke2;
                    }
                    if (i2 == lastIndex) {
                        break;
                    }
                    i2++;
                }
            }
        }
        return UShort.m459boximpl(m522getMh2AYeg);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: minByOrNull-JOV_ifY  reason: not valid java name */
    private static final <R extends Comparable<? super R>> UByte m1050minByOrNullJOV_ifY(byte[] minByOrNull, Function1<? super UByte, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(minByOrNull, "$this$minByOrNull");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (UByteArray.m265isEmptyimpl(minByOrNull)) {
            return null;
        }
        byte m262getw2LRezQ = UByteArray.m262getw2LRezQ(minByOrNull, 0);
        lastIndex = ArraysKt___ArraysKt.getLastIndex(minByOrNull);
        if (lastIndex == 0) {
            return UByte.m199boximpl(m262getw2LRezQ);
        }
        R invoke = selector.invoke(UByte.m199boximpl(m262getw2LRezQ));
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                byte m262getw2LRezQ2 = UByteArray.m262getw2LRezQ(minByOrNull, i2);
                R invoke2 = selector.invoke(UByte.m199boximpl(m262getw2LRezQ2));
                if (invoke.compareTo(invoke2) > 0) {
                    m262getw2LRezQ = m262getw2LRezQ2;
                    invoke = invoke2;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return UByte.m199boximpl(m262getw2LRezQ);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: minByOrNull-MShoTSo  reason: not valid java name */
    private static final <R extends Comparable<? super R>> ULong m1051minByOrNullMShoTSo(long[] minByOrNull, Function1<? super ULong, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(minByOrNull, "$this$minByOrNull");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (ULongArray.m421isEmptyimpl(minByOrNull)) {
            return null;
        }
        long m418getsVKNKU = ULongArray.m418getsVKNKU(minByOrNull, 0);
        lastIndex = ArraysKt___ArraysKt.getLastIndex(minByOrNull);
        if (lastIndex == 0) {
            return ULong.m353boximpl(m418getsVKNKU);
        }
        R invoke = selector.invoke(ULong.m353boximpl(m418getsVKNKU));
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                long m418getsVKNKU2 = ULongArray.m418getsVKNKU(minByOrNull, i2);
                R invoke2 = selector.invoke(ULong.m353boximpl(m418getsVKNKU2));
                if (invoke.compareTo(invoke2) > 0) {
                    m418getsVKNKU = m418getsVKNKU2;
                    invoke = invoke2;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return ULong.m353boximpl(m418getsVKNKU);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: minByOrNull-jgv0xPQ  reason: not valid java name */
    private static final <R extends Comparable<? super R>> UInt m1052minByOrNulljgv0xPQ(int[] minByOrNull, Function1<? super UInt, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(minByOrNull, "$this$minByOrNull");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (UIntArray.m343isEmptyimpl(minByOrNull)) {
            return null;
        }
        int m340getpVg5ArA = UIntArray.m340getpVg5ArA(minByOrNull, 0);
        lastIndex = ArraysKt___ArraysKt.getLastIndex(minByOrNull);
        if (lastIndex == 0) {
            return UInt.m275boximpl(m340getpVg5ArA);
        }
        R invoke = selector.invoke(UInt.m275boximpl(m340getpVg5ArA));
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                int m340getpVg5ArA2 = UIntArray.m340getpVg5ArA(minByOrNull, i2);
                R invoke2 = selector.invoke(UInt.m275boximpl(m340getpVg5ArA2));
                if (invoke.compareTo(invoke2) > 0) {
                    m340getpVg5ArA = m340getpVg5ArA2;
                    invoke = invoke2;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return UInt.m275boximpl(m340getpVg5ArA);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: minByOrNull-xTcfx_M  reason: not valid java name */
    private static final <R extends Comparable<? super R>> UShort m1053minByOrNullxTcfx_M(short[] minByOrNull, Function1<? super UShort, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(minByOrNull, "$this$minByOrNull");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (UShortArray.m525isEmptyimpl(minByOrNull)) {
            return null;
        }
        short m522getMh2AYeg = UShortArray.m522getMh2AYeg(minByOrNull, 0);
        lastIndex = ArraysKt___ArraysKt.getLastIndex(minByOrNull);
        if (lastIndex == 0) {
            return UShort.m459boximpl(m522getMh2AYeg);
        }
        R invoke = selector.invoke(UShort.m459boximpl(m522getMh2AYeg));
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                short m522getMh2AYeg2 = UShortArray.m522getMh2AYeg(minByOrNull, i2);
                R invoke2 = selector.invoke(UShort.m459boximpl(m522getMh2AYeg2));
                if (invoke.compareTo(invoke2) > 0) {
                    m522getMh2AYeg = m522getMh2AYeg2;
                    invoke = invoke2;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return UShort.m459boximpl(m522getMh2AYeg);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* renamed from: minOf-JOV_ifY  reason: not valid java name */
    private static final double m1054minOfJOV_ifY(byte[] minOf, Function1<? super UByte, Double> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(minOf, "$this$minOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (UByteArray.m265isEmptyimpl(minOf)) {
            throw new NoSuchElementException();
        }
        double doubleValue = selector.invoke(UByte.m199boximpl(UByteArray.m262getw2LRezQ(minOf, 0))).doubleValue();
        lastIndex = ArraysKt___ArraysKt.getLastIndex(minOf);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                doubleValue = Math.min(doubleValue, selector.invoke(UByte.m199boximpl(UByteArray.m262getw2LRezQ(minOf, i2))).doubleValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return doubleValue;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* renamed from: minOf-JOV_ifY  reason: not valid java name */
    private static final float m1055minOfJOV_ifY(byte[] minOf, Function1<? super UByte, Float> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(minOf, "$this$minOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (UByteArray.m265isEmptyimpl(minOf)) {
            throw new NoSuchElementException();
        }
        float floatValue = selector.invoke(UByte.m199boximpl(UByteArray.m262getw2LRezQ(minOf, 0))).floatValue();
        lastIndex = ArraysKt___ArraysKt.getLastIndex(minOf);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                floatValue = Math.min(floatValue, selector.invoke(UByte.m199boximpl(UByteArray.m262getw2LRezQ(minOf, i2))).floatValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return floatValue;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* renamed from: minOf-JOV_ifY  reason: not valid java name */
    private static final <R extends Comparable<? super R>> R m1056minOfJOV_ifY(byte[] minOf, Function1<? super UByte, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(minOf, "$this$minOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (UByteArray.m265isEmptyimpl(minOf)) {
            throw new NoSuchElementException();
        }
        R invoke = selector.invoke(UByte.m199boximpl(UByteArray.m262getw2LRezQ(minOf, 0)));
        lastIndex = ArraysKt___ArraysKt.getLastIndex(minOf);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                R invoke2 = selector.invoke(UByte.m199boximpl(UByteArray.m262getw2LRezQ(minOf, i2)));
                if (invoke.compareTo(invoke2) > 0) {
                    invoke = invoke2;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return invoke;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* renamed from: minOf-MShoTSo  reason: not valid java name */
    private static final double m1057minOfMShoTSo(long[] minOf, Function1<? super ULong, Double> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(minOf, "$this$minOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (ULongArray.m421isEmptyimpl(minOf)) {
            throw new NoSuchElementException();
        }
        double doubleValue = selector.invoke(ULong.m353boximpl(ULongArray.m418getsVKNKU(minOf, 0))).doubleValue();
        lastIndex = ArraysKt___ArraysKt.getLastIndex(minOf);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                doubleValue = Math.min(doubleValue, selector.invoke(ULong.m353boximpl(ULongArray.m418getsVKNKU(minOf, i2))).doubleValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return doubleValue;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* renamed from: minOf-MShoTSo  reason: not valid java name */
    private static final float m1058minOfMShoTSo(long[] minOf, Function1<? super ULong, Float> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(minOf, "$this$minOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (ULongArray.m421isEmptyimpl(minOf)) {
            throw new NoSuchElementException();
        }
        float floatValue = selector.invoke(ULong.m353boximpl(ULongArray.m418getsVKNKU(minOf, 0))).floatValue();
        lastIndex = ArraysKt___ArraysKt.getLastIndex(minOf);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                floatValue = Math.min(floatValue, selector.invoke(ULong.m353boximpl(ULongArray.m418getsVKNKU(minOf, i2))).floatValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return floatValue;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* renamed from: minOf-MShoTSo  reason: not valid java name */
    private static final <R extends Comparable<? super R>> R m1059minOfMShoTSo(long[] minOf, Function1<? super ULong, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(minOf, "$this$minOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (ULongArray.m421isEmptyimpl(minOf)) {
            throw new NoSuchElementException();
        }
        R invoke = selector.invoke(ULong.m353boximpl(ULongArray.m418getsVKNKU(minOf, 0)));
        lastIndex = ArraysKt___ArraysKt.getLastIndex(minOf);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                R invoke2 = selector.invoke(ULong.m353boximpl(ULongArray.m418getsVKNKU(minOf, i2)));
                if (invoke.compareTo(invoke2) > 0) {
                    invoke = invoke2;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return invoke;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* renamed from: minOf-jgv0xPQ  reason: not valid java name */
    private static final double m1060minOfjgv0xPQ(int[] minOf, Function1<? super UInt, Double> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(minOf, "$this$minOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (UIntArray.m343isEmptyimpl(minOf)) {
            throw new NoSuchElementException();
        }
        double doubleValue = selector.invoke(UInt.m275boximpl(UIntArray.m340getpVg5ArA(minOf, 0))).doubleValue();
        lastIndex = ArraysKt___ArraysKt.getLastIndex(minOf);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                doubleValue = Math.min(doubleValue, selector.invoke(UInt.m275boximpl(UIntArray.m340getpVg5ArA(minOf, i2))).doubleValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return doubleValue;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* renamed from: minOf-jgv0xPQ  reason: not valid java name */
    private static final float m1061minOfjgv0xPQ(int[] minOf, Function1<? super UInt, Float> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(minOf, "$this$minOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (UIntArray.m343isEmptyimpl(minOf)) {
            throw new NoSuchElementException();
        }
        float floatValue = selector.invoke(UInt.m275boximpl(UIntArray.m340getpVg5ArA(minOf, 0))).floatValue();
        lastIndex = ArraysKt___ArraysKt.getLastIndex(minOf);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                floatValue = Math.min(floatValue, selector.invoke(UInt.m275boximpl(UIntArray.m340getpVg5ArA(minOf, i2))).floatValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return floatValue;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* renamed from: minOf-jgv0xPQ  reason: not valid java name */
    private static final <R extends Comparable<? super R>> R m1062minOfjgv0xPQ(int[] minOf, Function1<? super UInt, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(minOf, "$this$minOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (UIntArray.m343isEmptyimpl(minOf)) {
            throw new NoSuchElementException();
        }
        R invoke = selector.invoke(UInt.m275boximpl(UIntArray.m340getpVg5ArA(minOf, 0)));
        lastIndex = ArraysKt___ArraysKt.getLastIndex(minOf);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                R invoke2 = selector.invoke(UInt.m275boximpl(UIntArray.m340getpVg5ArA(minOf, i2)));
                if (invoke.compareTo(invoke2) > 0) {
                    invoke = invoke2;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return invoke;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* renamed from: minOf-xTcfx_M  reason: not valid java name */
    private static final double m1063minOfxTcfx_M(short[] minOf, Function1<? super UShort, Double> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(minOf, "$this$minOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (UShortArray.m525isEmptyimpl(minOf)) {
            throw new NoSuchElementException();
        }
        double doubleValue = selector.invoke(UShort.m459boximpl(UShortArray.m522getMh2AYeg(minOf, 0))).doubleValue();
        lastIndex = ArraysKt___ArraysKt.getLastIndex(minOf);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                doubleValue = Math.min(doubleValue, selector.invoke(UShort.m459boximpl(UShortArray.m522getMh2AYeg(minOf, i2))).doubleValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return doubleValue;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* renamed from: minOf-xTcfx_M  reason: not valid java name */
    private static final float m1064minOfxTcfx_M(short[] minOf, Function1<? super UShort, Float> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(minOf, "$this$minOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (UShortArray.m525isEmptyimpl(minOf)) {
            throw new NoSuchElementException();
        }
        float floatValue = selector.invoke(UShort.m459boximpl(UShortArray.m522getMh2AYeg(minOf, 0))).floatValue();
        lastIndex = ArraysKt___ArraysKt.getLastIndex(minOf);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                floatValue = Math.min(floatValue, selector.invoke(UShort.m459boximpl(UShortArray.m522getMh2AYeg(minOf, i2))).floatValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return floatValue;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* renamed from: minOf-xTcfx_M  reason: not valid java name */
    private static final <R extends Comparable<? super R>> R m1065minOfxTcfx_M(short[] minOf, Function1<? super UShort, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(minOf, "$this$minOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (UShortArray.m525isEmptyimpl(minOf)) {
            throw new NoSuchElementException();
        }
        R invoke = selector.invoke(UShort.m459boximpl(UShortArray.m522getMh2AYeg(minOf, 0)));
        lastIndex = ArraysKt___ArraysKt.getLastIndex(minOf);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                R invoke2 = selector.invoke(UShort.m459boximpl(UShortArray.m522getMh2AYeg(minOf, i2)));
                if (invoke.compareTo(invoke2) > 0) {
                    invoke = invoke2;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return invoke;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* renamed from: minOfOrNull-JOV_ifY  reason: not valid java name */
    private static final <R extends Comparable<? super R>> R m1066minOfOrNullJOV_ifY(byte[] minOfOrNull, Function1<? super UByte, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(minOfOrNull, "$this$minOfOrNull");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (UByteArray.m265isEmptyimpl(minOfOrNull)) {
            return null;
        }
        R invoke = selector.invoke(UByte.m199boximpl(UByteArray.m262getw2LRezQ(minOfOrNull, 0)));
        lastIndex = ArraysKt___ArraysKt.getLastIndex(minOfOrNull);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                R invoke2 = selector.invoke(UByte.m199boximpl(UByteArray.m262getw2LRezQ(minOfOrNull, i2)));
                if (invoke.compareTo(invoke2) > 0) {
                    invoke = invoke2;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return invoke;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* renamed from: minOfOrNull-JOV_ifY  reason: not valid java name */
    private static final Double m1067minOfOrNullJOV_ifY(byte[] minOfOrNull, Function1<? super UByte, Double> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(minOfOrNull, "$this$minOfOrNull");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (UByteArray.m265isEmptyimpl(minOfOrNull)) {
            return null;
        }
        double doubleValue = selector.invoke(UByte.m199boximpl(UByteArray.m262getw2LRezQ(minOfOrNull, 0))).doubleValue();
        lastIndex = ArraysKt___ArraysKt.getLastIndex(minOfOrNull);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                doubleValue = Math.min(doubleValue, selector.invoke(UByte.m199boximpl(UByteArray.m262getw2LRezQ(minOfOrNull, i2))).doubleValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Double.valueOf(doubleValue);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* renamed from: minOfOrNull-JOV_ifY  reason: not valid java name */
    private static final Float m1068minOfOrNullJOV_ifY(byte[] minOfOrNull, Function1<? super UByte, Float> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(minOfOrNull, "$this$minOfOrNull");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (UByteArray.m265isEmptyimpl(minOfOrNull)) {
            return null;
        }
        float floatValue = selector.invoke(UByte.m199boximpl(UByteArray.m262getw2LRezQ(minOfOrNull, 0))).floatValue();
        lastIndex = ArraysKt___ArraysKt.getLastIndex(minOfOrNull);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                floatValue = Math.min(floatValue, selector.invoke(UByte.m199boximpl(UByteArray.m262getw2LRezQ(minOfOrNull, i2))).floatValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Float.valueOf(floatValue);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* renamed from: minOfOrNull-MShoTSo  reason: not valid java name */
    private static final <R extends Comparable<? super R>> R m1069minOfOrNullMShoTSo(long[] minOfOrNull, Function1<? super ULong, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(minOfOrNull, "$this$minOfOrNull");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (ULongArray.m421isEmptyimpl(minOfOrNull)) {
            return null;
        }
        R invoke = selector.invoke(ULong.m353boximpl(ULongArray.m418getsVKNKU(minOfOrNull, 0)));
        lastIndex = ArraysKt___ArraysKt.getLastIndex(minOfOrNull);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                R invoke2 = selector.invoke(ULong.m353boximpl(ULongArray.m418getsVKNKU(minOfOrNull, i2)));
                if (invoke.compareTo(invoke2) > 0) {
                    invoke = invoke2;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return invoke;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* renamed from: minOfOrNull-MShoTSo  reason: not valid java name */
    private static final Double m1070minOfOrNullMShoTSo(long[] minOfOrNull, Function1<? super ULong, Double> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(minOfOrNull, "$this$minOfOrNull");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (ULongArray.m421isEmptyimpl(minOfOrNull)) {
            return null;
        }
        double doubleValue = selector.invoke(ULong.m353boximpl(ULongArray.m418getsVKNKU(minOfOrNull, 0))).doubleValue();
        lastIndex = ArraysKt___ArraysKt.getLastIndex(minOfOrNull);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                doubleValue = Math.min(doubleValue, selector.invoke(ULong.m353boximpl(ULongArray.m418getsVKNKU(minOfOrNull, i2))).doubleValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Double.valueOf(doubleValue);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* renamed from: minOfOrNull-MShoTSo  reason: not valid java name */
    private static final Float m1071minOfOrNullMShoTSo(long[] minOfOrNull, Function1<? super ULong, Float> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(minOfOrNull, "$this$minOfOrNull");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (ULongArray.m421isEmptyimpl(minOfOrNull)) {
            return null;
        }
        float floatValue = selector.invoke(ULong.m353boximpl(ULongArray.m418getsVKNKU(minOfOrNull, 0))).floatValue();
        lastIndex = ArraysKt___ArraysKt.getLastIndex(minOfOrNull);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                floatValue = Math.min(floatValue, selector.invoke(ULong.m353boximpl(ULongArray.m418getsVKNKU(minOfOrNull, i2))).floatValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Float.valueOf(floatValue);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* renamed from: minOfOrNull-jgv0xPQ  reason: not valid java name */
    private static final <R extends Comparable<? super R>> R m1072minOfOrNulljgv0xPQ(int[] minOfOrNull, Function1<? super UInt, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(minOfOrNull, "$this$minOfOrNull");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (UIntArray.m343isEmptyimpl(minOfOrNull)) {
            return null;
        }
        R invoke = selector.invoke(UInt.m275boximpl(UIntArray.m340getpVg5ArA(minOfOrNull, 0)));
        lastIndex = ArraysKt___ArraysKt.getLastIndex(minOfOrNull);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                R invoke2 = selector.invoke(UInt.m275boximpl(UIntArray.m340getpVg5ArA(minOfOrNull, i2)));
                if (invoke.compareTo(invoke2) > 0) {
                    invoke = invoke2;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return invoke;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* renamed from: minOfOrNull-jgv0xPQ  reason: not valid java name */
    private static final Double m1073minOfOrNulljgv0xPQ(int[] minOfOrNull, Function1<? super UInt, Double> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(minOfOrNull, "$this$minOfOrNull");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (UIntArray.m343isEmptyimpl(minOfOrNull)) {
            return null;
        }
        double doubleValue = selector.invoke(UInt.m275boximpl(UIntArray.m340getpVg5ArA(minOfOrNull, 0))).doubleValue();
        lastIndex = ArraysKt___ArraysKt.getLastIndex(minOfOrNull);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                doubleValue = Math.min(doubleValue, selector.invoke(UInt.m275boximpl(UIntArray.m340getpVg5ArA(minOfOrNull, i2))).doubleValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Double.valueOf(doubleValue);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* renamed from: minOfOrNull-jgv0xPQ  reason: not valid java name */
    private static final Float m1074minOfOrNulljgv0xPQ(int[] minOfOrNull, Function1<? super UInt, Float> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(minOfOrNull, "$this$minOfOrNull");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (UIntArray.m343isEmptyimpl(minOfOrNull)) {
            return null;
        }
        float floatValue = selector.invoke(UInt.m275boximpl(UIntArray.m340getpVg5ArA(minOfOrNull, 0))).floatValue();
        lastIndex = ArraysKt___ArraysKt.getLastIndex(minOfOrNull);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                floatValue = Math.min(floatValue, selector.invoke(UInt.m275boximpl(UIntArray.m340getpVg5ArA(minOfOrNull, i2))).floatValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Float.valueOf(floatValue);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* renamed from: minOfOrNull-xTcfx_M  reason: not valid java name */
    private static final <R extends Comparable<? super R>> R m1075minOfOrNullxTcfx_M(short[] minOfOrNull, Function1<? super UShort, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(minOfOrNull, "$this$minOfOrNull");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (UShortArray.m525isEmptyimpl(minOfOrNull)) {
            return null;
        }
        R invoke = selector.invoke(UShort.m459boximpl(UShortArray.m522getMh2AYeg(minOfOrNull, 0)));
        lastIndex = ArraysKt___ArraysKt.getLastIndex(minOfOrNull);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                R invoke2 = selector.invoke(UShort.m459boximpl(UShortArray.m522getMh2AYeg(minOfOrNull, i2)));
                if (invoke.compareTo(invoke2) > 0) {
                    invoke = invoke2;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return invoke;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* renamed from: minOfOrNull-xTcfx_M  reason: not valid java name */
    private static final Double m1076minOfOrNullxTcfx_M(short[] minOfOrNull, Function1<? super UShort, Double> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(minOfOrNull, "$this$minOfOrNull");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (UShortArray.m525isEmptyimpl(minOfOrNull)) {
            return null;
        }
        double doubleValue = selector.invoke(UShort.m459boximpl(UShortArray.m522getMh2AYeg(minOfOrNull, 0))).doubleValue();
        lastIndex = ArraysKt___ArraysKt.getLastIndex(minOfOrNull);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                doubleValue = Math.min(doubleValue, selector.invoke(UShort.m459boximpl(UShortArray.m522getMh2AYeg(minOfOrNull, i2))).doubleValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Double.valueOf(doubleValue);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* renamed from: minOfOrNull-xTcfx_M  reason: not valid java name */
    private static final Float m1077minOfOrNullxTcfx_M(short[] minOfOrNull, Function1<? super UShort, Float> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(minOfOrNull, "$this$minOfOrNull");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (UShortArray.m525isEmptyimpl(minOfOrNull)) {
            return null;
        }
        float floatValue = selector.invoke(UShort.m459boximpl(UShortArray.m522getMh2AYeg(minOfOrNull, 0))).floatValue();
        lastIndex = ArraysKt___ArraysKt.getLastIndex(minOfOrNull);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                floatValue = Math.min(floatValue, selector.invoke(UShort.m459boximpl(UShortArray.m522getMh2AYeg(minOfOrNull, i2))).floatValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Float.valueOf(floatValue);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* renamed from: minOfWith-5NtCtWE  reason: not valid java name */
    private static final <R> R m1078minOfWith5NtCtWE(long[] minOfWith, Comparator<? super R> comparator, Function1<? super ULong, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(minOfWith, "$this$minOfWith");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (ULongArray.m421isEmptyimpl(minOfWith)) {
            throw new NoSuchElementException();
        }
        Object obj = (R) selector.invoke(ULong.m353boximpl(ULongArray.m418getsVKNKU(minOfWith, 0)));
        lastIndex = ArraysKt___ArraysKt.getLastIndex(minOfWith);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                R invoke = selector.invoke(ULong.m353boximpl(ULongArray.m418getsVKNKU(minOfWith, i2)));
                if (comparator.compare(obj, invoke) > 0) {
                    obj = invoke;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return (R) obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* renamed from: minOfWith-LTi4i_s  reason: not valid java name */
    private static final <R> R m1079minOfWithLTi4i_s(byte[] minOfWith, Comparator<? super R> comparator, Function1<? super UByte, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(minOfWith, "$this$minOfWith");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (UByteArray.m265isEmptyimpl(minOfWith)) {
            throw new NoSuchElementException();
        }
        Object obj = (R) selector.invoke(UByte.m199boximpl(UByteArray.m262getw2LRezQ(minOfWith, 0)));
        lastIndex = ArraysKt___ArraysKt.getLastIndex(minOfWith);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                R invoke = selector.invoke(UByte.m199boximpl(UByteArray.m262getw2LRezQ(minOfWith, i2)));
                if (comparator.compare(obj, invoke) > 0) {
                    obj = invoke;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return (R) obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* renamed from: minOfWith-l8EHGbQ  reason: not valid java name */
    private static final <R> R m1080minOfWithl8EHGbQ(short[] minOfWith, Comparator<? super R> comparator, Function1<? super UShort, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(minOfWith, "$this$minOfWith");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (UShortArray.m525isEmptyimpl(minOfWith)) {
            throw new NoSuchElementException();
        }
        Object obj = (R) selector.invoke(UShort.m459boximpl(UShortArray.m522getMh2AYeg(minOfWith, 0)));
        lastIndex = ArraysKt___ArraysKt.getLastIndex(minOfWith);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                R invoke = selector.invoke(UShort.m459boximpl(UShortArray.m522getMh2AYeg(minOfWith, i2)));
                if (comparator.compare(obj, invoke) > 0) {
                    obj = invoke;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return (R) obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* renamed from: minOfWith-myNOsp4  reason: not valid java name */
    private static final <R> R m1081minOfWithmyNOsp4(int[] minOfWith, Comparator<? super R> comparator, Function1<? super UInt, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(minOfWith, "$this$minOfWith");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (UIntArray.m343isEmptyimpl(minOfWith)) {
            throw new NoSuchElementException();
        }
        Object obj = (R) selector.invoke(UInt.m275boximpl(UIntArray.m340getpVg5ArA(minOfWith, 0)));
        lastIndex = ArraysKt___ArraysKt.getLastIndex(minOfWith);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                R invoke = selector.invoke(UInt.m275boximpl(UIntArray.m340getpVg5ArA(minOfWith, i2)));
                if (comparator.compare(obj, invoke) > 0) {
                    obj = invoke;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return (R) obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* renamed from: minOfWithOrNull-5NtCtWE  reason: not valid java name */
    private static final <R> R m1082minOfWithOrNull5NtCtWE(long[] minOfWithOrNull, Comparator<? super R> comparator, Function1<? super ULong, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(minOfWithOrNull, "$this$minOfWithOrNull");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (ULongArray.m421isEmptyimpl(minOfWithOrNull)) {
            return null;
        }
        Object obj = (R) selector.invoke(ULong.m353boximpl(ULongArray.m418getsVKNKU(minOfWithOrNull, 0)));
        lastIndex = ArraysKt___ArraysKt.getLastIndex(minOfWithOrNull);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                R invoke = selector.invoke(ULong.m353boximpl(ULongArray.m418getsVKNKU(minOfWithOrNull, i2)));
                if (comparator.compare(obj, invoke) > 0) {
                    obj = invoke;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return (R) obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* renamed from: minOfWithOrNull-LTi4i_s  reason: not valid java name */
    private static final <R> R m1083minOfWithOrNullLTi4i_s(byte[] minOfWithOrNull, Comparator<? super R> comparator, Function1<? super UByte, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(minOfWithOrNull, "$this$minOfWithOrNull");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (UByteArray.m265isEmptyimpl(minOfWithOrNull)) {
            return null;
        }
        Object obj = (R) selector.invoke(UByte.m199boximpl(UByteArray.m262getw2LRezQ(minOfWithOrNull, 0)));
        lastIndex = ArraysKt___ArraysKt.getLastIndex(minOfWithOrNull);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                R invoke = selector.invoke(UByte.m199boximpl(UByteArray.m262getw2LRezQ(minOfWithOrNull, i2)));
                if (comparator.compare(obj, invoke) > 0) {
                    obj = invoke;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return (R) obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* renamed from: minOfWithOrNull-l8EHGbQ  reason: not valid java name */
    private static final <R> R m1084minOfWithOrNulll8EHGbQ(short[] minOfWithOrNull, Comparator<? super R> comparator, Function1<? super UShort, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(minOfWithOrNull, "$this$minOfWithOrNull");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (UShortArray.m525isEmptyimpl(minOfWithOrNull)) {
            return null;
        }
        Object obj = (R) selector.invoke(UShort.m459boximpl(UShortArray.m522getMh2AYeg(minOfWithOrNull, 0)));
        lastIndex = ArraysKt___ArraysKt.getLastIndex(minOfWithOrNull);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                R invoke = selector.invoke(UShort.m459boximpl(UShortArray.m522getMh2AYeg(minOfWithOrNull, i2)));
                if (comparator.compare(obj, invoke) > 0) {
                    obj = invoke;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return (R) obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    /* renamed from: minOfWithOrNull-myNOsp4  reason: not valid java name */
    private static final <R> R m1085minOfWithOrNullmyNOsp4(int[] minOfWithOrNull, Comparator<? super R> comparator, Function1<? super UInt, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(minOfWithOrNull, "$this$minOfWithOrNull");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (UIntArray.m343isEmptyimpl(minOfWithOrNull)) {
            return null;
        }
        Object obj = (R) selector.invoke(UInt.m275boximpl(UIntArray.m340getpVg5ArA(minOfWithOrNull, 0)));
        lastIndex = ArraysKt___ArraysKt.getLastIndex(minOfWithOrNull);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                R invoke = selector.invoke(UInt.m275boximpl(UIntArray.m340getpVg5ArA(minOfWithOrNull, i2)));
                if (comparator.compare(obj, invoke) > 0) {
                    obj = invoke;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return (R) obj;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @Nullable
    /* renamed from: minOrNull--ajY-9A  reason: not valid java name */
    public static final UInt m1086minOrNullajY9A(@NotNull int[] minOrNull) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(minOrNull, "$this$minOrNull");
        if (UIntArray.m343isEmptyimpl(minOrNull)) {
            return null;
        }
        int m340getpVg5ArA = UIntArray.m340getpVg5ArA(minOrNull, 0);
        lastIndex = ArraysKt___ArraysKt.getLastIndex(minOrNull);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                int m340getpVg5ArA2 = UIntArray.m340getpVg5ArA(minOrNull, i2);
                if (UnsignedKt.uintCompare(m340getpVg5ArA, m340getpVg5ArA2) > 0) {
                    m340getpVg5ArA = m340getpVg5ArA2;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return UInt.m275boximpl(m340getpVg5ArA);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @Nullable
    /* renamed from: minOrNull-GBYM_sE  reason: not valid java name */
    public static final UByte m1087minOrNullGBYM_sE(@NotNull byte[] minOrNull) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(minOrNull, "$this$minOrNull");
        if (UByteArray.m265isEmptyimpl(minOrNull)) {
            return null;
        }
        byte m262getw2LRezQ = UByteArray.m262getw2LRezQ(minOrNull, 0);
        lastIndex = ArraysKt___ArraysKt.getLastIndex(minOrNull);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                byte m262getw2LRezQ2 = UByteArray.m262getw2LRezQ(minOrNull, i2);
                if (Intrinsics.compare(m262getw2LRezQ & 255, m262getw2LRezQ2 & 255) > 0) {
                    m262getw2LRezQ = m262getw2LRezQ2;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return UByte.m199boximpl(m262getw2LRezQ);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @Nullable
    /* renamed from: minOrNull-QwZRm1k  reason: not valid java name */
    public static final ULong m1088minOrNullQwZRm1k(@NotNull long[] minOrNull) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(minOrNull, "$this$minOrNull");
        if (ULongArray.m421isEmptyimpl(minOrNull)) {
            return null;
        }
        long m418getsVKNKU = ULongArray.m418getsVKNKU(minOrNull, 0);
        lastIndex = ArraysKt___ArraysKt.getLastIndex(minOrNull);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                long m418getsVKNKU2 = ULongArray.m418getsVKNKU(minOrNull, i2);
                if (UnsignedKt.ulongCompare(m418getsVKNKU, m418getsVKNKU2) > 0) {
                    m418getsVKNKU = m418getsVKNKU2;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return ULong.m353boximpl(m418getsVKNKU);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @Nullable
    /* renamed from: minOrNull-rL5Bavg  reason: not valid java name */
    public static final UShort m1089minOrNullrL5Bavg(@NotNull short[] minOrNull) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(minOrNull, "$this$minOrNull");
        if (UShortArray.m525isEmptyimpl(minOrNull)) {
            return null;
        }
        short m522getMh2AYeg = UShortArray.m522getMh2AYeg(minOrNull, 0);
        lastIndex = ArraysKt___ArraysKt.getLastIndex(minOrNull);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                short m522getMh2AYeg2 = UShortArray.m522getMh2AYeg(minOrNull, i2);
                if (Intrinsics.compare(m522getMh2AYeg & UShort.MAX_VALUE, 65535 & m522getMh2AYeg2) > 0) {
                    m522getMh2AYeg = m522getMh2AYeg2;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return UShort.m459boximpl(m522getMh2AYeg);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Deprecated(message = "Use minWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    /* renamed from: minWith-XMRcp5o  reason: not valid java name */
    public static final /* synthetic */ UByte m1090minWithXMRcp5o(byte[] minWith, Comparator comparator) {
        Intrinsics.checkNotNullParameter(minWith, "$this$minWith");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        return m1094minWithOrNullXMRcp5o(minWith, comparator);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Deprecated(message = "Use minWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    /* renamed from: minWith-YmdZ_VM  reason: not valid java name */
    public static final /* synthetic */ UInt m1091minWithYmdZ_VM(int[] minWith, Comparator comparator) {
        Intrinsics.checkNotNullParameter(minWith, "$this$minWith");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        return m1095minWithOrNullYmdZ_VM(minWith, comparator);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Deprecated(message = "Use minWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    /* renamed from: minWith-eOHTfZs  reason: not valid java name */
    public static final /* synthetic */ UShort m1092minWitheOHTfZs(short[] minWith, Comparator comparator) {
        Intrinsics.checkNotNullParameter(minWith, "$this$minWith");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        return m1096minWithOrNulleOHTfZs(minWith, comparator);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Deprecated(message = "Use minWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    /* renamed from: minWith-zrEWJaI  reason: not valid java name */
    public static final /* synthetic */ ULong m1093minWithzrEWJaI(long[] minWith, Comparator comparator) {
        Intrinsics.checkNotNullParameter(minWith, "$this$minWith");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        return m1097minWithOrNullzrEWJaI(minWith, comparator);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @Nullable
    /* renamed from: minWithOrNull-XMRcp5o  reason: not valid java name */
    public static final UByte m1094minWithOrNullXMRcp5o(@NotNull byte[] minWithOrNull, @NotNull Comparator<? super UByte> comparator) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(minWithOrNull, "$this$minWithOrNull");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (UByteArray.m265isEmptyimpl(minWithOrNull)) {
            return null;
        }
        byte m262getw2LRezQ = UByteArray.m262getw2LRezQ(minWithOrNull, 0);
        lastIndex = ArraysKt___ArraysKt.getLastIndex(minWithOrNull);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                byte m262getw2LRezQ2 = UByteArray.m262getw2LRezQ(minWithOrNull, i2);
                if (comparator.compare(UByte.m199boximpl(m262getw2LRezQ), UByte.m199boximpl(m262getw2LRezQ2)) > 0) {
                    m262getw2LRezQ = m262getw2LRezQ2;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return UByte.m199boximpl(m262getw2LRezQ);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @Nullable
    /* renamed from: minWithOrNull-YmdZ_VM  reason: not valid java name */
    public static final UInt m1095minWithOrNullYmdZ_VM(@NotNull int[] minWithOrNull, @NotNull Comparator<? super UInt> comparator) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(minWithOrNull, "$this$minWithOrNull");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (UIntArray.m343isEmptyimpl(minWithOrNull)) {
            return null;
        }
        int m340getpVg5ArA = UIntArray.m340getpVg5ArA(minWithOrNull, 0);
        lastIndex = ArraysKt___ArraysKt.getLastIndex(minWithOrNull);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                int m340getpVg5ArA2 = UIntArray.m340getpVg5ArA(minWithOrNull, i2);
                if (comparator.compare(UInt.m275boximpl(m340getpVg5ArA), UInt.m275boximpl(m340getpVg5ArA2)) > 0) {
                    m340getpVg5ArA = m340getpVg5ArA2;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return UInt.m275boximpl(m340getpVg5ArA);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @Nullable
    /* renamed from: minWithOrNull-eOHTfZs  reason: not valid java name */
    public static final UShort m1096minWithOrNulleOHTfZs(@NotNull short[] minWithOrNull, @NotNull Comparator<? super UShort> comparator) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(minWithOrNull, "$this$minWithOrNull");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (UShortArray.m525isEmptyimpl(minWithOrNull)) {
            return null;
        }
        short m522getMh2AYeg = UShortArray.m522getMh2AYeg(minWithOrNull, 0);
        lastIndex = ArraysKt___ArraysKt.getLastIndex(minWithOrNull);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                short m522getMh2AYeg2 = UShortArray.m522getMh2AYeg(minWithOrNull, i2);
                if (comparator.compare(UShort.m459boximpl(m522getMh2AYeg), UShort.m459boximpl(m522getMh2AYeg2)) > 0) {
                    m522getMh2AYeg = m522getMh2AYeg2;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return UShort.m459boximpl(m522getMh2AYeg);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @Nullable
    /* renamed from: minWithOrNull-zrEWJaI  reason: not valid java name */
    public static final ULong m1097minWithOrNullzrEWJaI(@NotNull long[] minWithOrNull, @NotNull Comparator<? super ULong> comparator) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(minWithOrNull, "$this$minWithOrNull");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (ULongArray.m421isEmptyimpl(minWithOrNull)) {
            return null;
        }
        long m418getsVKNKU = ULongArray.m418getsVKNKU(minWithOrNull, 0);
        lastIndex = ArraysKt___ArraysKt.getLastIndex(minWithOrNull);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                long m418getsVKNKU2 = ULongArray.m418getsVKNKU(minWithOrNull, i2);
                if (comparator.compare(ULong.m353boximpl(m418getsVKNKU), ULong.m353boximpl(m418getsVKNKU2)) > 0) {
                    m418getsVKNKU = m418getsVKNKU2;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return ULong.m353boximpl(m418getsVKNKU);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: none--ajY-9A  reason: not valid java name */
    private static final boolean m1098noneajY9A(int[] none) {
        Intrinsics.checkNotNullParameter(none, "$this$none");
        return UIntArray.m343isEmptyimpl(none);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: none-GBYM_sE  reason: not valid java name */
    private static final boolean m1099noneGBYM_sE(byte[] none) {
        Intrinsics.checkNotNullParameter(none, "$this$none");
        return UByteArray.m265isEmptyimpl(none);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: none-JOV_ifY  reason: not valid java name */
    private static final boolean m1100noneJOV_ifY(byte[] none, Function1<? super UByte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(none, "$this$none");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int m263getSizeimpl = UByteArray.m263getSizeimpl(none);
        for (int i2 = 0; i2 < m263getSizeimpl; i2++) {
            if (predicate.invoke(UByte.m199boximpl(UByteArray.m262getw2LRezQ(none, i2))).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: none-MShoTSo  reason: not valid java name */
    private static final boolean m1101noneMShoTSo(long[] none, Function1<? super ULong, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(none, "$this$none");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int m419getSizeimpl = ULongArray.m419getSizeimpl(none);
        for (int i2 = 0; i2 < m419getSizeimpl; i2++) {
            if (predicate.invoke(ULong.m353boximpl(ULongArray.m418getsVKNKU(none, i2))).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: none-QwZRm1k  reason: not valid java name */
    private static final boolean m1102noneQwZRm1k(long[] none) {
        Intrinsics.checkNotNullParameter(none, "$this$none");
        return ULongArray.m421isEmptyimpl(none);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: none-jgv0xPQ  reason: not valid java name */
    private static final boolean m1103nonejgv0xPQ(int[] none, Function1<? super UInt, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(none, "$this$none");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int m341getSizeimpl = UIntArray.m341getSizeimpl(none);
        for (int i2 = 0; i2 < m341getSizeimpl; i2++) {
            if (predicate.invoke(UInt.m275boximpl(UIntArray.m340getpVg5ArA(none, i2))).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: none-rL5Bavg  reason: not valid java name */
    private static final boolean m1104nonerL5Bavg(short[] none) {
        Intrinsics.checkNotNullParameter(none, "$this$none");
        return UShortArray.m525isEmptyimpl(none);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: none-xTcfx_M  reason: not valid java name */
    private static final boolean m1105nonexTcfx_M(short[] none, Function1<? super UShort, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(none, "$this$none");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int m523getSizeimpl = UShortArray.m523getSizeimpl(none);
        for (int i2 = 0; i2 < m523getSizeimpl; i2++) {
            if (predicate.invoke(UShort.m459boximpl(UShortArray.m522getMh2AYeg(none, i2))).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: onEach-JOV_ifY  reason: not valid java name */
    private static final byte[] m1106onEachJOV_ifY(byte[] onEach, Function1<? super UByte, Unit> action) {
        Intrinsics.checkNotNullParameter(onEach, "$this$onEach");
        Intrinsics.checkNotNullParameter(action, "action");
        int m263getSizeimpl = UByteArray.m263getSizeimpl(onEach);
        for (int i2 = 0; i2 < m263getSizeimpl; i2++) {
            action.invoke(UByte.m199boximpl(UByteArray.m262getw2LRezQ(onEach, i2)));
        }
        return onEach;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: onEach-MShoTSo  reason: not valid java name */
    private static final long[] m1107onEachMShoTSo(long[] onEach, Function1<? super ULong, Unit> action) {
        Intrinsics.checkNotNullParameter(onEach, "$this$onEach");
        Intrinsics.checkNotNullParameter(action, "action");
        int m419getSizeimpl = ULongArray.m419getSizeimpl(onEach);
        for (int i2 = 0; i2 < m419getSizeimpl; i2++) {
            action.invoke(ULong.m353boximpl(ULongArray.m418getsVKNKU(onEach, i2)));
        }
        return onEach;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: onEach-jgv0xPQ  reason: not valid java name */
    private static final int[] m1108onEachjgv0xPQ(int[] onEach, Function1<? super UInt, Unit> action) {
        Intrinsics.checkNotNullParameter(onEach, "$this$onEach");
        Intrinsics.checkNotNullParameter(action, "action");
        int m341getSizeimpl = UIntArray.m341getSizeimpl(onEach);
        for (int i2 = 0; i2 < m341getSizeimpl; i2++) {
            action.invoke(UInt.m275boximpl(UIntArray.m340getpVg5ArA(onEach, i2)));
        }
        return onEach;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: onEach-xTcfx_M  reason: not valid java name */
    private static final short[] m1109onEachxTcfx_M(short[] onEach, Function1<? super UShort, Unit> action) {
        Intrinsics.checkNotNullParameter(onEach, "$this$onEach");
        Intrinsics.checkNotNullParameter(action, "action");
        int m523getSizeimpl = UShortArray.m523getSizeimpl(onEach);
        for (int i2 = 0; i2 < m523getSizeimpl; i2++) {
            action.invoke(UShort.m459boximpl(UShortArray.m522getMh2AYeg(onEach, i2)));
        }
        return onEach;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: onEachIndexed-ELGow60  reason: not valid java name */
    private static final byte[] m1110onEachIndexedELGow60(byte[] onEachIndexed, Function2<? super Integer, ? super UByte, Unit> action) {
        Intrinsics.checkNotNullParameter(onEachIndexed, "$this$onEachIndexed");
        Intrinsics.checkNotNullParameter(action, "action");
        int m263getSizeimpl = UByteArray.m263getSizeimpl(onEachIndexed);
        int i2 = 0;
        int i3 = 0;
        while (i2 < m263getSizeimpl) {
            action.invoke(Integer.valueOf(i3), UByte.m199boximpl(UByteArray.m262getw2LRezQ(onEachIndexed, i2)));
            i2++;
            i3++;
        }
        return onEachIndexed;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: onEachIndexed-WyvcNBI  reason: not valid java name */
    private static final int[] m1111onEachIndexedWyvcNBI(int[] onEachIndexed, Function2<? super Integer, ? super UInt, Unit> action) {
        Intrinsics.checkNotNullParameter(onEachIndexed, "$this$onEachIndexed");
        Intrinsics.checkNotNullParameter(action, "action");
        int m341getSizeimpl = UIntArray.m341getSizeimpl(onEachIndexed);
        int i2 = 0;
        int i3 = 0;
        while (i2 < m341getSizeimpl) {
            action.invoke(Integer.valueOf(i3), UInt.m275boximpl(UIntArray.m340getpVg5ArA(onEachIndexed, i2)));
            i2++;
            i3++;
        }
        return onEachIndexed;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: onEachIndexed-s8dVfGU  reason: not valid java name */
    private static final long[] m1112onEachIndexeds8dVfGU(long[] onEachIndexed, Function2<? super Integer, ? super ULong, Unit> action) {
        Intrinsics.checkNotNullParameter(onEachIndexed, "$this$onEachIndexed");
        Intrinsics.checkNotNullParameter(action, "action");
        int m419getSizeimpl = ULongArray.m419getSizeimpl(onEachIndexed);
        int i2 = 0;
        int i3 = 0;
        while (i2 < m419getSizeimpl) {
            action.invoke(Integer.valueOf(i3), ULong.m353boximpl(ULongArray.m418getsVKNKU(onEachIndexed, i2)));
            i2++;
            i3++;
        }
        return onEachIndexed;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: onEachIndexed-xzaTVY8  reason: not valid java name */
    private static final short[] m1113onEachIndexedxzaTVY8(short[] onEachIndexed, Function2<? super Integer, ? super UShort, Unit> action) {
        Intrinsics.checkNotNullParameter(onEachIndexed, "$this$onEachIndexed");
        Intrinsics.checkNotNullParameter(action, "action");
        int m523getSizeimpl = UShortArray.m523getSizeimpl(onEachIndexed);
        int i2 = 0;
        int i3 = 0;
        while (i2 < m523getSizeimpl) {
            action.invoke(Integer.valueOf(i3), UShort.m459boximpl(UShortArray.m522getMh2AYeg(onEachIndexed, i2)));
            i2++;
            i3++;
        }
        return onEachIndexed;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: plus-3uqUaXg  reason: not valid java name */
    private static final long[] m1114plus3uqUaXg(long[] plus, long j2) {
        long[] plus2;
        Intrinsics.checkNotNullParameter(plus, "$this$plus");
        plus2 = ArraysKt___ArraysJvmKt.plus(plus, j2);
        return ULongArray.m413constructorimpl(plus2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: plus-CFIt9YE  reason: not valid java name */
    public static final int[] m1115plusCFIt9YE(@NotNull int[] plus, @NotNull Collection<UInt> elements) {
        Intrinsics.checkNotNullParameter(plus, "$this$plus");
        Intrinsics.checkNotNullParameter(elements, "elements");
        int m341getSizeimpl = UIntArray.m341getSizeimpl(plus);
        int[] copyOf = Arrays.copyOf(plus, UIntArray.m341getSizeimpl(plus) + elements.size());
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
        for (UInt uInt : elements) {
            copyOf[m341getSizeimpl] = uInt.m332unboximpl();
            m341getSizeimpl++;
        }
        return UIntArray.m335constructorimpl(copyOf);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: plus-XzdR7RA  reason: not valid java name */
    private static final short[] m1116plusXzdR7RA(short[] plus, short s2) {
        short[] plus2;
        Intrinsics.checkNotNullParameter(plus, "$this$plus");
        plus2 = ArraysKt___ArraysJvmKt.plus(plus, s2);
        return UShortArray.m517constructorimpl(plus2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: plus-ctEhBpI  reason: not valid java name */
    private static final int[] m1117plusctEhBpI(int[] plus, int[] elements) {
        int[] plus2;
        Intrinsics.checkNotNullParameter(plus, "$this$plus");
        Intrinsics.checkNotNullParameter(elements, "elements");
        plus2 = ArraysKt___ArraysJvmKt.plus(plus, elements);
        return UIntArray.m335constructorimpl(plus2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: plus-gMuBH34  reason: not valid java name */
    private static final byte[] m1118plusgMuBH34(byte[] plus, byte b2) {
        byte[] plus2;
        Intrinsics.checkNotNullParameter(plus, "$this$plus");
        plus2 = ArraysKt___ArraysJvmKt.plus(plus, b2);
        return UByteArray.m257constructorimpl(plus2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: plus-kdPth3s  reason: not valid java name */
    private static final byte[] m1119pluskdPth3s(byte[] plus, byte[] elements) {
        byte[] plus2;
        Intrinsics.checkNotNullParameter(plus, "$this$plus");
        Intrinsics.checkNotNullParameter(elements, "elements");
        plus2 = ArraysKt___ArraysJvmKt.plus(plus, elements);
        return UByteArray.m257constructorimpl(plus2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: plus-kzHmqpY  reason: not valid java name */
    public static final long[] m1120pluskzHmqpY(@NotNull long[] plus, @NotNull Collection<ULong> elements) {
        Intrinsics.checkNotNullParameter(plus, "$this$plus");
        Intrinsics.checkNotNullParameter(elements, "elements");
        int m419getSizeimpl = ULongArray.m419getSizeimpl(plus);
        long[] copyOf = Arrays.copyOf(plus, ULongArray.m419getSizeimpl(plus) + elements.size());
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
        for (ULong uLong : elements) {
            copyOf[m419getSizeimpl] = uLong.m410unboximpl();
            m419getSizeimpl++;
        }
        return ULongArray.m413constructorimpl(copyOf);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: plus-mazbYpA  reason: not valid java name */
    private static final short[] m1121plusmazbYpA(short[] plus, short[] elements) {
        short[] plus2;
        Intrinsics.checkNotNullParameter(plus, "$this$plus");
        Intrinsics.checkNotNullParameter(elements, "elements");
        plus2 = ArraysKt___ArraysJvmKt.plus(plus, elements);
        return UShortArray.m517constructorimpl(plus2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: plus-ojwP5H8  reason: not valid java name */
    public static final short[] m1122plusojwP5H8(@NotNull short[] plus, @NotNull Collection<UShort> elements) {
        Intrinsics.checkNotNullParameter(plus, "$this$plus");
        Intrinsics.checkNotNullParameter(elements, "elements");
        int m523getSizeimpl = UShortArray.m523getSizeimpl(plus);
        short[] copyOf = Arrays.copyOf(plus, UShortArray.m523getSizeimpl(plus) + elements.size());
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
        for (UShort uShort : elements) {
            copyOf[m523getSizeimpl] = uShort.m514unboximpl();
            m523getSizeimpl++;
        }
        return UShortArray.m517constructorimpl(copyOf);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: plus-uWY9BYg  reason: not valid java name */
    private static final int[] m1123plusuWY9BYg(int[] plus, int i2) {
        int[] plus2;
        Intrinsics.checkNotNullParameter(plus, "$this$plus");
        plus2 = ArraysKt___ArraysJvmKt.plus(plus, i2);
        return UIntArray.m335constructorimpl(plus2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: plus-us8wMrg  reason: not valid java name */
    private static final long[] m1124plusus8wMrg(long[] plus, long[] elements) {
        long[] plus2;
        Intrinsics.checkNotNullParameter(plus, "$this$plus");
        Intrinsics.checkNotNullParameter(elements, "elements");
        plus2 = ArraysKt___ArraysJvmKt.plus(plus, elements);
        return ULongArray.m413constructorimpl(plus2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: plus-xo_DsdI  reason: not valid java name */
    public static final byte[] m1125plusxo_DsdI(@NotNull byte[] plus, @NotNull Collection<UByte> elements) {
        Intrinsics.checkNotNullParameter(plus, "$this$plus");
        Intrinsics.checkNotNullParameter(elements, "elements");
        int m263getSizeimpl = UByteArray.m263getSizeimpl(plus);
        byte[] copyOf = Arrays.copyOf(plus, UByteArray.m263getSizeimpl(plus) + elements.size());
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
        for (UByte uByte : elements) {
            copyOf[m263getSizeimpl] = uByte.m254unboximpl();
            m263getSizeimpl++;
        }
        return UByteArray.m257constructorimpl(copyOf);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: random--ajY-9A  reason: not valid java name */
    private static final int m1126randomajY9A(int[] random) {
        Intrinsics.checkNotNullParameter(random, "$this$random");
        return m1127random2D5oskM(random, Random.Default);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: random-2D5oskM  reason: not valid java name */
    public static final int m1127random2D5oskM(@NotNull int[] random, @NotNull Random random2) {
        Intrinsics.checkNotNullParameter(random, "$this$random");
        Intrinsics.checkNotNullParameter(random2, "random");
        if (UIntArray.m343isEmptyimpl(random)) {
            throw new NoSuchElementException("Array is empty.");
        }
        return UIntArray.m340getpVg5ArA(random, random2.nextInt(UIntArray.m341getSizeimpl(random)));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: random-GBYM_sE  reason: not valid java name */
    private static final byte m1128randomGBYM_sE(byte[] random) {
        Intrinsics.checkNotNullParameter(random, "$this$random");
        return m1131randomoSF2wD8(random, Random.Default);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: random-JzugnMA  reason: not valid java name */
    public static final long m1129randomJzugnMA(@NotNull long[] random, @NotNull Random random2) {
        Intrinsics.checkNotNullParameter(random, "$this$random");
        Intrinsics.checkNotNullParameter(random2, "random");
        if (ULongArray.m421isEmptyimpl(random)) {
            throw new NoSuchElementException("Array is empty.");
        }
        return ULongArray.m418getsVKNKU(random, random2.nextInt(ULongArray.m419getSizeimpl(random)));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: random-QwZRm1k  reason: not valid java name */
    private static final long m1130randomQwZRm1k(long[] random) {
        Intrinsics.checkNotNullParameter(random, "$this$random");
        return m1129randomJzugnMA(random, Random.Default);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: random-oSF2wD8  reason: not valid java name */
    public static final byte m1131randomoSF2wD8(@NotNull byte[] random, @NotNull Random random2) {
        Intrinsics.checkNotNullParameter(random, "$this$random");
        Intrinsics.checkNotNullParameter(random2, "random");
        if (UByteArray.m265isEmptyimpl(random)) {
            throw new NoSuchElementException("Array is empty.");
        }
        return UByteArray.m262getw2LRezQ(random, random2.nextInt(UByteArray.m263getSizeimpl(random)));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: random-rL5Bavg  reason: not valid java name */
    private static final short m1132randomrL5Bavg(short[] random) {
        Intrinsics.checkNotNullParameter(random, "$this$random");
        return m1133randoms5X_as8(random, Random.Default);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: random-s5X_as8  reason: not valid java name */
    public static final short m1133randoms5X_as8(@NotNull short[] random, @NotNull Random random2) {
        Intrinsics.checkNotNullParameter(random, "$this$random");
        Intrinsics.checkNotNullParameter(random2, "random");
        if (UShortArray.m525isEmptyimpl(random)) {
            throw new NoSuchElementException("Array is empty.");
        }
        return UShortArray.m522getMh2AYeg(random, random2.nextInt(UShortArray.m523getSizeimpl(random)));
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    /* renamed from: randomOrNull--ajY-9A  reason: not valid java name */
    private static final UInt m1134randomOrNullajY9A(int[] randomOrNull) {
        Intrinsics.checkNotNullParameter(randomOrNull, "$this$randomOrNull");
        return m1135randomOrNull2D5oskM(randomOrNull, Random.Default);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @Nullable
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    /* renamed from: randomOrNull-2D5oskM  reason: not valid java name */
    public static final UInt m1135randomOrNull2D5oskM(@NotNull int[] randomOrNull, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(randomOrNull, "$this$randomOrNull");
        Intrinsics.checkNotNullParameter(random, "random");
        if (UIntArray.m343isEmptyimpl(randomOrNull)) {
            return null;
        }
        return UInt.m275boximpl(UIntArray.m340getpVg5ArA(randomOrNull, random.nextInt(UIntArray.m341getSizeimpl(randomOrNull))));
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    /* renamed from: randomOrNull-GBYM_sE  reason: not valid java name */
    private static final UByte m1136randomOrNullGBYM_sE(byte[] randomOrNull) {
        Intrinsics.checkNotNullParameter(randomOrNull, "$this$randomOrNull");
        return m1139randomOrNulloSF2wD8(randomOrNull, Random.Default);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @Nullable
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    /* renamed from: randomOrNull-JzugnMA  reason: not valid java name */
    public static final ULong m1137randomOrNullJzugnMA(@NotNull long[] randomOrNull, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(randomOrNull, "$this$randomOrNull");
        Intrinsics.checkNotNullParameter(random, "random");
        if (ULongArray.m421isEmptyimpl(randomOrNull)) {
            return null;
        }
        return ULong.m353boximpl(ULongArray.m418getsVKNKU(randomOrNull, random.nextInt(ULongArray.m419getSizeimpl(randomOrNull))));
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    /* renamed from: randomOrNull-QwZRm1k  reason: not valid java name */
    private static final ULong m1138randomOrNullQwZRm1k(long[] randomOrNull) {
        Intrinsics.checkNotNullParameter(randomOrNull, "$this$randomOrNull");
        return m1137randomOrNullJzugnMA(randomOrNull, Random.Default);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @Nullable
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    /* renamed from: randomOrNull-oSF2wD8  reason: not valid java name */
    public static final UByte m1139randomOrNulloSF2wD8(@NotNull byte[] randomOrNull, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(randomOrNull, "$this$randomOrNull");
        Intrinsics.checkNotNullParameter(random, "random");
        if (UByteArray.m265isEmptyimpl(randomOrNull)) {
            return null;
        }
        return UByte.m199boximpl(UByteArray.m262getw2LRezQ(randomOrNull, random.nextInt(UByteArray.m263getSizeimpl(randomOrNull))));
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    /* renamed from: randomOrNull-rL5Bavg  reason: not valid java name */
    private static final UShort m1140randomOrNullrL5Bavg(short[] randomOrNull) {
        Intrinsics.checkNotNullParameter(randomOrNull, "$this$randomOrNull");
        return m1141randomOrNulls5X_as8(randomOrNull, Random.Default);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @Nullable
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    /* renamed from: randomOrNull-s5X_as8  reason: not valid java name */
    public static final UShort m1141randomOrNulls5X_as8(@NotNull short[] randomOrNull, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(randomOrNull, "$this$randomOrNull");
        Intrinsics.checkNotNullParameter(random, "random");
        if (UShortArray.m525isEmptyimpl(randomOrNull)) {
            return null;
        }
        return UShort.m459boximpl(UShortArray.m522getMh2AYeg(randomOrNull, random.nextInt(UShortArray.m523getSizeimpl(randomOrNull))));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: reduce-ELGow60  reason: not valid java name */
    private static final byte m1142reduceELGow60(byte[] reduce, Function2<? super UByte, ? super UByte, UByte> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(reduce, "$this$reduce");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (UByteArray.m265isEmptyimpl(reduce)) {
            throw new UnsupportedOperationException("Empty array can't be reduced.");
        }
        byte m262getw2LRezQ = UByteArray.m262getw2LRezQ(reduce, 0);
        lastIndex = ArraysKt___ArraysKt.getLastIndex(reduce);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                m262getw2LRezQ = operation.invoke(UByte.m199boximpl(m262getw2LRezQ), UByte.m199boximpl(UByteArray.m262getw2LRezQ(reduce, i2))).m254unboximpl();
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return m262getw2LRezQ;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: reduce-WyvcNBI  reason: not valid java name */
    private static final int m1143reduceWyvcNBI(int[] reduce, Function2<? super UInt, ? super UInt, UInt> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(reduce, "$this$reduce");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (UIntArray.m343isEmptyimpl(reduce)) {
            throw new UnsupportedOperationException("Empty array can't be reduced.");
        }
        int m340getpVg5ArA = UIntArray.m340getpVg5ArA(reduce, 0);
        lastIndex = ArraysKt___ArraysKt.getLastIndex(reduce);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                m340getpVg5ArA = operation.invoke(UInt.m275boximpl(m340getpVg5ArA), UInt.m275boximpl(UIntArray.m340getpVg5ArA(reduce, i2))).m332unboximpl();
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return m340getpVg5ArA;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: reduce-s8dVfGU  reason: not valid java name */
    private static final long m1144reduces8dVfGU(long[] reduce, Function2<? super ULong, ? super ULong, ULong> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(reduce, "$this$reduce");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (ULongArray.m421isEmptyimpl(reduce)) {
            throw new UnsupportedOperationException("Empty array can't be reduced.");
        }
        long m418getsVKNKU = ULongArray.m418getsVKNKU(reduce, 0);
        lastIndex = ArraysKt___ArraysKt.getLastIndex(reduce);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                m418getsVKNKU = operation.invoke(ULong.m353boximpl(m418getsVKNKU), ULong.m353boximpl(ULongArray.m418getsVKNKU(reduce, i2))).m410unboximpl();
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return m418getsVKNKU;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: reduce-xzaTVY8  reason: not valid java name */
    private static final short m1145reducexzaTVY8(short[] reduce, Function2<? super UShort, ? super UShort, UShort> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(reduce, "$this$reduce");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (UShortArray.m525isEmptyimpl(reduce)) {
            throw new UnsupportedOperationException("Empty array can't be reduced.");
        }
        short m522getMh2AYeg = UShortArray.m522getMh2AYeg(reduce, 0);
        lastIndex = ArraysKt___ArraysKt.getLastIndex(reduce);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                m522getMh2AYeg = operation.invoke(UShort.m459boximpl(m522getMh2AYeg), UShort.m459boximpl(UShortArray.m522getMh2AYeg(reduce, i2))).m514unboximpl();
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return m522getMh2AYeg;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: reduceIndexed-D40WMg8  reason: not valid java name */
    private static final int m1146reduceIndexedD40WMg8(int[] reduceIndexed, Function3<? super Integer, ? super UInt, ? super UInt, UInt> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(reduceIndexed, "$this$reduceIndexed");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (UIntArray.m343isEmptyimpl(reduceIndexed)) {
            throw new UnsupportedOperationException("Empty array can't be reduced.");
        }
        int m340getpVg5ArA = UIntArray.m340getpVg5ArA(reduceIndexed, 0);
        lastIndex = ArraysKt___ArraysKt.getLastIndex(reduceIndexed);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                m340getpVg5ArA = operation.invoke(Integer.valueOf(i2), UInt.m275boximpl(m340getpVg5ArA), UInt.m275boximpl(UIntArray.m340getpVg5ArA(reduceIndexed, i2))).m332unboximpl();
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return m340getpVg5ArA;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: reduceIndexed-EOyYB1Y  reason: not valid java name */
    private static final byte m1147reduceIndexedEOyYB1Y(byte[] reduceIndexed, Function3<? super Integer, ? super UByte, ? super UByte, UByte> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(reduceIndexed, "$this$reduceIndexed");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (UByteArray.m265isEmptyimpl(reduceIndexed)) {
            throw new UnsupportedOperationException("Empty array can't be reduced.");
        }
        byte m262getw2LRezQ = UByteArray.m262getw2LRezQ(reduceIndexed, 0);
        lastIndex = ArraysKt___ArraysKt.getLastIndex(reduceIndexed);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                m262getw2LRezQ = operation.invoke(Integer.valueOf(i2), UByte.m199boximpl(m262getw2LRezQ), UByte.m199boximpl(UByteArray.m262getw2LRezQ(reduceIndexed, i2))).m254unboximpl();
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return m262getw2LRezQ;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: reduceIndexed-aLgx1Fo  reason: not valid java name */
    private static final short m1148reduceIndexedaLgx1Fo(short[] reduceIndexed, Function3<? super Integer, ? super UShort, ? super UShort, UShort> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(reduceIndexed, "$this$reduceIndexed");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (UShortArray.m525isEmptyimpl(reduceIndexed)) {
            throw new UnsupportedOperationException("Empty array can't be reduced.");
        }
        short m522getMh2AYeg = UShortArray.m522getMh2AYeg(reduceIndexed, 0);
        lastIndex = ArraysKt___ArraysKt.getLastIndex(reduceIndexed);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                m522getMh2AYeg = operation.invoke(Integer.valueOf(i2), UShort.m459boximpl(m522getMh2AYeg), UShort.m459boximpl(UShortArray.m522getMh2AYeg(reduceIndexed, i2))).m514unboximpl();
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return m522getMh2AYeg;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: reduceIndexed-z1zDJgo  reason: not valid java name */
    private static final long m1149reduceIndexedz1zDJgo(long[] reduceIndexed, Function3<? super Integer, ? super ULong, ? super ULong, ULong> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(reduceIndexed, "$this$reduceIndexed");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (ULongArray.m421isEmptyimpl(reduceIndexed)) {
            throw new UnsupportedOperationException("Empty array can't be reduced.");
        }
        long m418getsVKNKU = ULongArray.m418getsVKNKU(reduceIndexed, 0);
        lastIndex = ArraysKt___ArraysKt.getLastIndex(reduceIndexed);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                m418getsVKNKU = operation.invoke(Integer.valueOf(i2), ULong.m353boximpl(m418getsVKNKU), ULong.m353boximpl(ULongArray.m418getsVKNKU(reduceIndexed, i2))).m410unboximpl();
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return m418getsVKNKU;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: reduceIndexedOrNull-D40WMg8  reason: not valid java name */
    private static final UInt m1150reduceIndexedOrNullD40WMg8(int[] reduceIndexedOrNull, Function3<? super Integer, ? super UInt, ? super UInt, UInt> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(reduceIndexedOrNull, "$this$reduceIndexedOrNull");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (UIntArray.m343isEmptyimpl(reduceIndexedOrNull)) {
            return null;
        }
        int m340getpVg5ArA = UIntArray.m340getpVg5ArA(reduceIndexedOrNull, 0);
        lastIndex = ArraysKt___ArraysKt.getLastIndex(reduceIndexedOrNull);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                m340getpVg5ArA = operation.invoke(Integer.valueOf(i2), UInt.m275boximpl(m340getpVg5ArA), UInt.m275boximpl(UIntArray.m340getpVg5ArA(reduceIndexedOrNull, i2))).m332unboximpl();
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return UInt.m275boximpl(m340getpVg5ArA);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: reduceIndexedOrNull-EOyYB1Y  reason: not valid java name */
    private static final UByte m1151reduceIndexedOrNullEOyYB1Y(byte[] reduceIndexedOrNull, Function3<? super Integer, ? super UByte, ? super UByte, UByte> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(reduceIndexedOrNull, "$this$reduceIndexedOrNull");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (UByteArray.m265isEmptyimpl(reduceIndexedOrNull)) {
            return null;
        }
        byte m262getw2LRezQ = UByteArray.m262getw2LRezQ(reduceIndexedOrNull, 0);
        lastIndex = ArraysKt___ArraysKt.getLastIndex(reduceIndexedOrNull);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                m262getw2LRezQ = operation.invoke(Integer.valueOf(i2), UByte.m199boximpl(m262getw2LRezQ), UByte.m199boximpl(UByteArray.m262getw2LRezQ(reduceIndexedOrNull, i2))).m254unboximpl();
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return UByte.m199boximpl(m262getw2LRezQ);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: reduceIndexedOrNull-aLgx1Fo  reason: not valid java name */
    private static final UShort m1152reduceIndexedOrNullaLgx1Fo(short[] reduceIndexedOrNull, Function3<? super Integer, ? super UShort, ? super UShort, UShort> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(reduceIndexedOrNull, "$this$reduceIndexedOrNull");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (UShortArray.m525isEmptyimpl(reduceIndexedOrNull)) {
            return null;
        }
        short m522getMh2AYeg = UShortArray.m522getMh2AYeg(reduceIndexedOrNull, 0);
        lastIndex = ArraysKt___ArraysKt.getLastIndex(reduceIndexedOrNull);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                m522getMh2AYeg = operation.invoke(Integer.valueOf(i2), UShort.m459boximpl(m522getMh2AYeg), UShort.m459boximpl(UShortArray.m522getMh2AYeg(reduceIndexedOrNull, i2))).m514unboximpl();
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return UShort.m459boximpl(m522getMh2AYeg);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: reduceIndexedOrNull-z1zDJgo  reason: not valid java name */
    private static final ULong m1153reduceIndexedOrNullz1zDJgo(long[] reduceIndexedOrNull, Function3<? super Integer, ? super ULong, ? super ULong, ULong> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(reduceIndexedOrNull, "$this$reduceIndexedOrNull");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (ULongArray.m421isEmptyimpl(reduceIndexedOrNull)) {
            return null;
        }
        long m418getsVKNKU = ULongArray.m418getsVKNKU(reduceIndexedOrNull, 0);
        lastIndex = ArraysKt___ArraysKt.getLastIndex(reduceIndexedOrNull);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                m418getsVKNKU = operation.invoke(Integer.valueOf(i2), ULong.m353boximpl(m418getsVKNKU), ULong.m353boximpl(ULongArray.m418getsVKNKU(reduceIndexedOrNull, i2))).m410unboximpl();
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return ULong.m353boximpl(m418getsVKNKU);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    /* renamed from: reduceOrNull-ELGow60  reason: not valid java name */
    private static final UByte m1154reduceOrNullELGow60(byte[] reduceOrNull, Function2<? super UByte, ? super UByte, UByte> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(reduceOrNull, "$this$reduceOrNull");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (UByteArray.m265isEmptyimpl(reduceOrNull)) {
            return null;
        }
        byte m262getw2LRezQ = UByteArray.m262getw2LRezQ(reduceOrNull, 0);
        lastIndex = ArraysKt___ArraysKt.getLastIndex(reduceOrNull);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                m262getw2LRezQ = operation.invoke(UByte.m199boximpl(m262getw2LRezQ), UByte.m199boximpl(UByteArray.m262getw2LRezQ(reduceOrNull, i2))).m254unboximpl();
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return UByte.m199boximpl(m262getw2LRezQ);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    /* renamed from: reduceOrNull-WyvcNBI  reason: not valid java name */
    private static final UInt m1155reduceOrNullWyvcNBI(int[] reduceOrNull, Function2<? super UInt, ? super UInt, UInt> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(reduceOrNull, "$this$reduceOrNull");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (UIntArray.m343isEmptyimpl(reduceOrNull)) {
            return null;
        }
        int m340getpVg5ArA = UIntArray.m340getpVg5ArA(reduceOrNull, 0);
        lastIndex = ArraysKt___ArraysKt.getLastIndex(reduceOrNull);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                m340getpVg5ArA = operation.invoke(UInt.m275boximpl(m340getpVg5ArA), UInt.m275boximpl(UIntArray.m340getpVg5ArA(reduceOrNull, i2))).m332unboximpl();
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return UInt.m275boximpl(m340getpVg5ArA);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    /* renamed from: reduceOrNull-s8dVfGU  reason: not valid java name */
    private static final ULong m1156reduceOrNulls8dVfGU(long[] reduceOrNull, Function2<? super ULong, ? super ULong, ULong> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(reduceOrNull, "$this$reduceOrNull");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (ULongArray.m421isEmptyimpl(reduceOrNull)) {
            return null;
        }
        long m418getsVKNKU = ULongArray.m418getsVKNKU(reduceOrNull, 0);
        lastIndex = ArraysKt___ArraysKt.getLastIndex(reduceOrNull);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                m418getsVKNKU = operation.invoke(ULong.m353boximpl(m418getsVKNKU), ULong.m353boximpl(ULongArray.m418getsVKNKU(reduceOrNull, i2))).m410unboximpl();
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return ULong.m353boximpl(m418getsVKNKU);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    /* renamed from: reduceOrNull-xzaTVY8  reason: not valid java name */
    private static final UShort m1157reduceOrNullxzaTVY8(short[] reduceOrNull, Function2<? super UShort, ? super UShort, UShort> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(reduceOrNull, "$this$reduceOrNull");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (UShortArray.m525isEmptyimpl(reduceOrNull)) {
            return null;
        }
        short m522getMh2AYeg = UShortArray.m522getMh2AYeg(reduceOrNull, 0);
        lastIndex = ArraysKt___ArraysKt.getLastIndex(reduceOrNull);
        int i2 = 1;
        if (1 <= lastIndex) {
            while (true) {
                m522getMh2AYeg = operation.invoke(UShort.m459boximpl(m522getMh2AYeg), UShort.m459boximpl(UShortArray.m522getMh2AYeg(reduceOrNull, i2))).m514unboximpl();
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return UShort.m459boximpl(m522getMh2AYeg);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: reduceRight-ELGow60  reason: not valid java name */
    private static final byte m1158reduceRightELGow60(byte[] reduceRight, Function2<? super UByte, ? super UByte, UByte> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(reduceRight, "$this$reduceRight");
        Intrinsics.checkNotNullParameter(operation, "operation");
        lastIndex = ArraysKt___ArraysKt.getLastIndex(reduceRight);
        if (lastIndex >= 0) {
            byte m262getw2LRezQ = UByteArray.m262getw2LRezQ(reduceRight, lastIndex);
            for (int i2 = lastIndex - 1; i2 >= 0; i2--) {
                m262getw2LRezQ = operation.invoke(UByte.m199boximpl(UByteArray.m262getw2LRezQ(reduceRight, i2)), UByte.m199boximpl(m262getw2LRezQ)).m254unboximpl();
            }
            return m262getw2LRezQ;
        }
        throw new UnsupportedOperationException("Empty array can't be reduced.");
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: reduceRight-WyvcNBI  reason: not valid java name */
    private static final int m1159reduceRightWyvcNBI(int[] reduceRight, Function2<? super UInt, ? super UInt, UInt> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(reduceRight, "$this$reduceRight");
        Intrinsics.checkNotNullParameter(operation, "operation");
        lastIndex = ArraysKt___ArraysKt.getLastIndex(reduceRight);
        if (lastIndex >= 0) {
            int m340getpVg5ArA = UIntArray.m340getpVg5ArA(reduceRight, lastIndex);
            for (int i2 = lastIndex - 1; i2 >= 0; i2--) {
                m340getpVg5ArA = operation.invoke(UInt.m275boximpl(UIntArray.m340getpVg5ArA(reduceRight, i2)), UInt.m275boximpl(m340getpVg5ArA)).m332unboximpl();
            }
            return m340getpVg5ArA;
        }
        throw new UnsupportedOperationException("Empty array can't be reduced.");
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: reduceRight-s8dVfGU  reason: not valid java name */
    private static final long m1160reduceRights8dVfGU(long[] reduceRight, Function2<? super ULong, ? super ULong, ULong> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(reduceRight, "$this$reduceRight");
        Intrinsics.checkNotNullParameter(operation, "operation");
        lastIndex = ArraysKt___ArraysKt.getLastIndex(reduceRight);
        if (lastIndex >= 0) {
            long m418getsVKNKU = ULongArray.m418getsVKNKU(reduceRight, lastIndex);
            for (int i2 = lastIndex - 1; i2 >= 0; i2--) {
                m418getsVKNKU = operation.invoke(ULong.m353boximpl(ULongArray.m418getsVKNKU(reduceRight, i2)), ULong.m353boximpl(m418getsVKNKU)).m410unboximpl();
            }
            return m418getsVKNKU;
        }
        throw new UnsupportedOperationException("Empty array can't be reduced.");
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: reduceRight-xzaTVY8  reason: not valid java name */
    private static final short m1161reduceRightxzaTVY8(short[] reduceRight, Function2<? super UShort, ? super UShort, UShort> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(reduceRight, "$this$reduceRight");
        Intrinsics.checkNotNullParameter(operation, "operation");
        lastIndex = ArraysKt___ArraysKt.getLastIndex(reduceRight);
        if (lastIndex >= 0) {
            short m522getMh2AYeg = UShortArray.m522getMh2AYeg(reduceRight, lastIndex);
            for (int i2 = lastIndex - 1; i2 >= 0; i2--) {
                m522getMh2AYeg = operation.invoke(UShort.m459boximpl(UShortArray.m522getMh2AYeg(reduceRight, i2)), UShort.m459boximpl(m522getMh2AYeg)).m514unboximpl();
            }
            return m522getMh2AYeg;
        }
        throw new UnsupportedOperationException("Empty array can't be reduced.");
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: reduceRightIndexed-D40WMg8  reason: not valid java name */
    private static final int m1162reduceRightIndexedD40WMg8(int[] reduceRightIndexed, Function3<? super Integer, ? super UInt, ? super UInt, UInt> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(reduceRightIndexed, "$this$reduceRightIndexed");
        Intrinsics.checkNotNullParameter(operation, "operation");
        lastIndex = ArraysKt___ArraysKt.getLastIndex(reduceRightIndexed);
        if (lastIndex >= 0) {
            int m340getpVg5ArA = UIntArray.m340getpVg5ArA(reduceRightIndexed, lastIndex);
            for (int i2 = lastIndex - 1; i2 >= 0; i2--) {
                m340getpVg5ArA = operation.invoke(Integer.valueOf(i2), UInt.m275boximpl(UIntArray.m340getpVg5ArA(reduceRightIndexed, i2)), UInt.m275boximpl(m340getpVg5ArA)).m332unboximpl();
            }
            return m340getpVg5ArA;
        }
        throw new UnsupportedOperationException("Empty array can't be reduced.");
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: reduceRightIndexed-EOyYB1Y  reason: not valid java name */
    private static final byte m1163reduceRightIndexedEOyYB1Y(byte[] reduceRightIndexed, Function3<? super Integer, ? super UByte, ? super UByte, UByte> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(reduceRightIndexed, "$this$reduceRightIndexed");
        Intrinsics.checkNotNullParameter(operation, "operation");
        lastIndex = ArraysKt___ArraysKt.getLastIndex(reduceRightIndexed);
        if (lastIndex >= 0) {
            byte m262getw2LRezQ = UByteArray.m262getw2LRezQ(reduceRightIndexed, lastIndex);
            for (int i2 = lastIndex - 1; i2 >= 0; i2--) {
                m262getw2LRezQ = operation.invoke(Integer.valueOf(i2), UByte.m199boximpl(UByteArray.m262getw2LRezQ(reduceRightIndexed, i2)), UByte.m199boximpl(m262getw2LRezQ)).m254unboximpl();
            }
            return m262getw2LRezQ;
        }
        throw new UnsupportedOperationException("Empty array can't be reduced.");
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: reduceRightIndexed-aLgx1Fo  reason: not valid java name */
    private static final short m1164reduceRightIndexedaLgx1Fo(short[] reduceRightIndexed, Function3<? super Integer, ? super UShort, ? super UShort, UShort> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(reduceRightIndexed, "$this$reduceRightIndexed");
        Intrinsics.checkNotNullParameter(operation, "operation");
        lastIndex = ArraysKt___ArraysKt.getLastIndex(reduceRightIndexed);
        if (lastIndex >= 0) {
            short m522getMh2AYeg = UShortArray.m522getMh2AYeg(reduceRightIndexed, lastIndex);
            for (int i2 = lastIndex - 1; i2 >= 0; i2--) {
                m522getMh2AYeg = operation.invoke(Integer.valueOf(i2), UShort.m459boximpl(UShortArray.m522getMh2AYeg(reduceRightIndexed, i2)), UShort.m459boximpl(m522getMh2AYeg)).m514unboximpl();
            }
            return m522getMh2AYeg;
        }
        throw new UnsupportedOperationException("Empty array can't be reduced.");
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: reduceRightIndexed-z1zDJgo  reason: not valid java name */
    private static final long m1165reduceRightIndexedz1zDJgo(long[] reduceRightIndexed, Function3<? super Integer, ? super ULong, ? super ULong, ULong> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(reduceRightIndexed, "$this$reduceRightIndexed");
        Intrinsics.checkNotNullParameter(operation, "operation");
        lastIndex = ArraysKt___ArraysKt.getLastIndex(reduceRightIndexed);
        if (lastIndex >= 0) {
            long m418getsVKNKU = ULongArray.m418getsVKNKU(reduceRightIndexed, lastIndex);
            for (int i2 = lastIndex - 1; i2 >= 0; i2--) {
                m418getsVKNKU = operation.invoke(Integer.valueOf(i2), ULong.m353boximpl(ULongArray.m418getsVKNKU(reduceRightIndexed, i2)), ULong.m353boximpl(m418getsVKNKU)).m410unboximpl();
            }
            return m418getsVKNKU;
        }
        throw new UnsupportedOperationException("Empty array can't be reduced.");
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: reduceRightIndexedOrNull-D40WMg8  reason: not valid java name */
    private static final UInt m1166reduceRightIndexedOrNullD40WMg8(int[] reduceRightIndexedOrNull, Function3<? super Integer, ? super UInt, ? super UInt, UInt> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(reduceRightIndexedOrNull, "$this$reduceRightIndexedOrNull");
        Intrinsics.checkNotNullParameter(operation, "operation");
        lastIndex = ArraysKt___ArraysKt.getLastIndex(reduceRightIndexedOrNull);
        if (lastIndex < 0) {
            return null;
        }
        int m340getpVg5ArA = UIntArray.m340getpVg5ArA(reduceRightIndexedOrNull, lastIndex);
        for (int i2 = lastIndex - 1; i2 >= 0; i2--) {
            m340getpVg5ArA = operation.invoke(Integer.valueOf(i2), UInt.m275boximpl(UIntArray.m340getpVg5ArA(reduceRightIndexedOrNull, i2)), UInt.m275boximpl(m340getpVg5ArA)).m332unboximpl();
        }
        return UInt.m275boximpl(m340getpVg5ArA);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: reduceRightIndexedOrNull-EOyYB1Y  reason: not valid java name */
    private static final UByte m1167reduceRightIndexedOrNullEOyYB1Y(byte[] reduceRightIndexedOrNull, Function3<? super Integer, ? super UByte, ? super UByte, UByte> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(reduceRightIndexedOrNull, "$this$reduceRightIndexedOrNull");
        Intrinsics.checkNotNullParameter(operation, "operation");
        lastIndex = ArraysKt___ArraysKt.getLastIndex(reduceRightIndexedOrNull);
        if (lastIndex < 0) {
            return null;
        }
        byte m262getw2LRezQ = UByteArray.m262getw2LRezQ(reduceRightIndexedOrNull, lastIndex);
        for (int i2 = lastIndex - 1; i2 >= 0; i2--) {
            m262getw2LRezQ = operation.invoke(Integer.valueOf(i2), UByte.m199boximpl(UByteArray.m262getw2LRezQ(reduceRightIndexedOrNull, i2)), UByte.m199boximpl(m262getw2LRezQ)).m254unboximpl();
        }
        return UByte.m199boximpl(m262getw2LRezQ);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: reduceRightIndexedOrNull-aLgx1Fo  reason: not valid java name */
    private static final UShort m1168reduceRightIndexedOrNullaLgx1Fo(short[] reduceRightIndexedOrNull, Function3<? super Integer, ? super UShort, ? super UShort, UShort> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(reduceRightIndexedOrNull, "$this$reduceRightIndexedOrNull");
        Intrinsics.checkNotNullParameter(operation, "operation");
        lastIndex = ArraysKt___ArraysKt.getLastIndex(reduceRightIndexedOrNull);
        if (lastIndex < 0) {
            return null;
        }
        short m522getMh2AYeg = UShortArray.m522getMh2AYeg(reduceRightIndexedOrNull, lastIndex);
        for (int i2 = lastIndex - 1; i2 >= 0; i2--) {
            m522getMh2AYeg = operation.invoke(Integer.valueOf(i2), UShort.m459boximpl(UShortArray.m522getMh2AYeg(reduceRightIndexedOrNull, i2)), UShort.m459boximpl(m522getMh2AYeg)).m514unboximpl();
        }
        return UShort.m459boximpl(m522getMh2AYeg);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: reduceRightIndexedOrNull-z1zDJgo  reason: not valid java name */
    private static final ULong m1169reduceRightIndexedOrNullz1zDJgo(long[] reduceRightIndexedOrNull, Function3<? super Integer, ? super ULong, ? super ULong, ULong> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(reduceRightIndexedOrNull, "$this$reduceRightIndexedOrNull");
        Intrinsics.checkNotNullParameter(operation, "operation");
        lastIndex = ArraysKt___ArraysKt.getLastIndex(reduceRightIndexedOrNull);
        if (lastIndex < 0) {
            return null;
        }
        long m418getsVKNKU = ULongArray.m418getsVKNKU(reduceRightIndexedOrNull, lastIndex);
        for (int i2 = lastIndex - 1; i2 >= 0; i2--) {
            m418getsVKNKU = operation.invoke(Integer.valueOf(i2), ULong.m353boximpl(ULongArray.m418getsVKNKU(reduceRightIndexedOrNull, i2)), ULong.m353boximpl(m418getsVKNKU)).m410unboximpl();
        }
        return ULong.m353boximpl(m418getsVKNKU);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    /* renamed from: reduceRightOrNull-ELGow60  reason: not valid java name */
    private static final UByte m1170reduceRightOrNullELGow60(byte[] reduceRightOrNull, Function2<? super UByte, ? super UByte, UByte> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(reduceRightOrNull, "$this$reduceRightOrNull");
        Intrinsics.checkNotNullParameter(operation, "operation");
        lastIndex = ArraysKt___ArraysKt.getLastIndex(reduceRightOrNull);
        if (lastIndex < 0) {
            return null;
        }
        byte m262getw2LRezQ = UByteArray.m262getw2LRezQ(reduceRightOrNull, lastIndex);
        for (int i2 = lastIndex - 1; i2 >= 0; i2--) {
            m262getw2LRezQ = operation.invoke(UByte.m199boximpl(UByteArray.m262getw2LRezQ(reduceRightOrNull, i2)), UByte.m199boximpl(m262getw2LRezQ)).m254unboximpl();
        }
        return UByte.m199boximpl(m262getw2LRezQ);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    /* renamed from: reduceRightOrNull-WyvcNBI  reason: not valid java name */
    private static final UInt m1171reduceRightOrNullWyvcNBI(int[] reduceRightOrNull, Function2<? super UInt, ? super UInt, UInt> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(reduceRightOrNull, "$this$reduceRightOrNull");
        Intrinsics.checkNotNullParameter(operation, "operation");
        lastIndex = ArraysKt___ArraysKt.getLastIndex(reduceRightOrNull);
        if (lastIndex < 0) {
            return null;
        }
        int m340getpVg5ArA = UIntArray.m340getpVg5ArA(reduceRightOrNull, lastIndex);
        for (int i2 = lastIndex - 1; i2 >= 0; i2--) {
            m340getpVg5ArA = operation.invoke(UInt.m275boximpl(UIntArray.m340getpVg5ArA(reduceRightOrNull, i2)), UInt.m275boximpl(m340getpVg5ArA)).m332unboximpl();
        }
        return UInt.m275boximpl(m340getpVg5ArA);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    /* renamed from: reduceRightOrNull-s8dVfGU  reason: not valid java name */
    private static final ULong m1172reduceRightOrNulls8dVfGU(long[] reduceRightOrNull, Function2<? super ULong, ? super ULong, ULong> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(reduceRightOrNull, "$this$reduceRightOrNull");
        Intrinsics.checkNotNullParameter(operation, "operation");
        lastIndex = ArraysKt___ArraysKt.getLastIndex(reduceRightOrNull);
        if (lastIndex < 0) {
            return null;
        }
        long m418getsVKNKU = ULongArray.m418getsVKNKU(reduceRightOrNull, lastIndex);
        for (int i2 = lastIndex - 1; i2 >= 0; i2--) {
            m418getsVKNKU = operation.invoke(ULong.m353boximpl(ULongArray.m418getsVKNKU(reduceRightOrNull, i2)), ULong.m353boximpl(m418getsVKNKU)).m410unboximpl();
        }
        return ULong.m353boximpl(m418getsVKNKU);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    /* renamed from: reduceRightOrNull-xzaTVY8  reason: not valid java name */
    private static final UShort m1173reduceRightOrNullxzaTVY8(short[] reduceRightOrNull, Function2<? super UShort, ? super UShort, UShort> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(reduceRightOrNull, "$this$reduceRightOrNull");
        Intrinsics.checkNotNullParameter(operation, "operation");
        lastIndex = ArraysKt___ArraysKt.getLastIndex(reduceRightOrNull);
        if (lastIndex < 0) {
            return null;
        }
        short m522getMh2AYeg = UShortArray.m522getMh2AYeg(reduceRightOrNull, lastIndex);
        for (int i2 = lastIndex - 1; i2 >= 0; i2--) {
            m522getMh2AYeg = operation.invoke(UShort.m459boximpl(UShortArray.m522getMh2AYeg(reduceRightOrNull, i2)), UShort.m459boximpl(m522getMh2AYeg)).m514unboximpl();
        }
        return UShort.m459boximpl(m522getMh2AYeg);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: reverse--ajY-9A  reason: not valid java name */
    private static final void m1174reverseajY9A(int[] reverse) {
        Intrinsics.checkNotNullParameter(reverse, "$this$reverse");
        ArraysKt___ArraysKt.reverse(reverse);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: reverse--nroSd4  reason: not valid java name */
    private static final void m1175reversenroSd4(long[] reverse, int i2, int i3) {
        Intrinsics.checkNotNullParameter(reverse, "$this$reverse");
        ArraysKt___ArraysKt.reverse(reverse, i2, i3);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: reverse-4UcCI2c  reason: not valid java name */
    private static final void m1176reverse4UcCI2c(byte[] reverse, int i2, int i3) {
        Intrinsics.checkNotNullParameter(reverse, "$this$reverse");
        ArraysKt___ArraysKt.reverse(reverse, i2, i3);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: reverse-Aa5vz7o  reason: not valid java name */
    private static final void m1177reverseAa5vz7o(short[] reverse, int i2, int i3) {
        Intrinsics.checkNotNullParameter(reverse, "$this$reverse");
        ArraysKt___ArraysKt.reverse(reverse, i2, i3);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: reverse-GBYM_sE  reason: not valid java name */
    private static final void m1178reverseGBYM_sE(byte[] reverse) {
        Intrinsics.checkNotNullParameter(reverse, "$this$reverse");
        ArraysKt___ArraysKt.reverse(reverse);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: reverse-QwZRm1k  reason: not valid java name */
    private static final void m1179reverseQwZRm1k(long[] reverse) {
        Intrinsics.checkNotNullParameter(reverse, "$this$reverse");
        ArraysKt___ArraysKt.reverse(reverse);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: reverse-oBK06Vg  reason: not valid java name */
    private static final void m1180reverseoBK06Vg(int[] reverse, int i2, int i3) {
        Intrinsics.checkNotNullParameter(reverse, "$this$reverse");
        ArraysKt___ArraysKt.reverse(reverse, i2, i3);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: reverse-rL5Bavg  reason: not valid java name */
    private static final void m1181reverserL5Bavg(short[] reverse) {
        Intrinsics.checkNotNullParameter(reverse, "$this$reverse");
        ArraysKt___ArraysKt.reverse(reverse);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: reversed--ajY-9A  reason: not valid java name */
    public static final List<UInt> m1182reversedajY9A(@NotNull int[] reversed) {
        List<UInt> mutableList;
        List<UInt> emptyList;
        Intrinsics.checkNotNullParameter(reversed, "$this$reversed");
        if (UIntArray.m343isEmptyimpl(reversed)) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) UIntArray.m333boximpl(reversed));
        CollectionsKt___CollectionsJvmKt.reverse(mutableList);
        return mutableList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: reversed-GBYM_sE  reason: not valid java name */
    public static final List<UByte> m1183reversedGBYM_sE(@NotNull byte[] reversed) {
        List<UByte> mutableList;
        List<UByte> emptyList;
        Intrinsics.checkNotNullParameter(reversed, "$this$reversed");
        if (UByteArray.m265isEmptyimpl(reversed)) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) UByteArray.m255boximpl(reversed));
        CollectionsKt___CollectionsJvmKt.reverse(mutableList);
        return mutableList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: reversed-QwZRm1k  reason: not valid java name */
    public static final List<ULong> m1184reversedQwZRm1k(@NotNull long[] reversed) {
        List<ULong> mutableList;
        List<ULong> emptyList;
        Intrinsics.checkNotNullParameter(reversed, "$this$reversed");
        if (ULongArray.m421isEmptyimpl(reversed)) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) ULongArray.m411boximpl(reversed));
        CollectionsKt___CollectionsJvmKt.reverse(mutableList);
        return mutableList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: reversed-rL5Bavg  reason: not valid java name */
    public static final List<UShort> m1185reversedrL5Bavg(@NotNull short[] reversed) {
        List<UShort> mutableList;
        List<UShort> emptyList;
        Intrinsics.checkNotNullParameter(reversed, "$this$reversed");
        if (UShortArray.m525isEmptyimpl(reversed)) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) UShortArray.m515boximpl(reversed));
        CollectionsKt___CollectionsJvmKt.reverse(mutableList);
        return mutableList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: reversedArray--ajY-9A  reason: not valid java name */
    private static final int[] m1186reversedArrayajY9A(int[] reversedArray) {
        int[] reversedArray2;
        Intrinsics.checkNotNullParameter(reversedArray, "$this$reversedArray");
        reversedArray2 = ArraysKt___ArraysKt.reversedArray(reversedArray);
        return UIntArray.m335constructorimpl(reversedArray2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: reversedArray-GBYM_sE  reason: not valid java name */
    private static final byte[] m1187reversedArrayGBYM_sE(byte[] reversedArray) {
        byte[] reversedArray2;
        Intrinsics.checkNotNullParameter(reversedArray, "$this$reversedArray");
        reversedArray2 = ArraysKt___ArraysKt.reversedArray(reversedArray);
        return UByteArray.m257constructorimpl(reversedArray2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: reversedArray-QwZRm1k  reason: not valid java name */
    private static final long[] m1188reversedArrayQwZRm1k(long[] reversedArray) {
        long[] reversedArray2;
        Intrinsics.checkNotNullParameter(reversedArray, "$this$reversedArray");
        reversedArray2 = ArraysKt___ArraysKt.reversedArray(reversedArray);
        return ULongArray.m413constructorimpl(reversedArray2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: reversedArray-rL5Bavg  reason: not valid java name */
    private static final short[] m1189reversedArrayrL5Bavg(short[] reversedArray) {
        short[] reversedArray2;
        Intrinsics.checkNotNullParameter(reversedArray, "$this$reversedArray");
        reversedArray2 = ArraysKt___ArraysKt.reversedArray(reversedArray);
        return UShortArray.m517constructorimpl(reversedArray2);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: runningFold-A8wKCXQ  reason: not valid java name */
    private static final <R> List<R> m1190runningFoldA8wKCXQ(long[] runningFold, R r2, Function2<? super R, ? super ULong, ? extends R> operation) {
        List<R> listOf;
        Intrinsics.checkNotNullParameter(runningFold, "$this$runningFold");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (ULongArray.m421isEmptyimpl(runningFold)) {
            listOf = CollectionsKt__CollectionsJVMKt.listOf(r2);
            return listOf;
        }
        ArrayList arrayList = new ArrayList(ULongArray.m419getSizeimpl(runningFold) + 1);
        arrayList.add(r2);
        int m419getSizeimpl = ULongArray.m419getSizeimpl(runningFold);
        for (int i2 = 0; i2 < m419getSizeimpl; i2++) {
            r2 = operation.invoke(r2, ULong.m353boximpl(ULongArray.m418getsVKNKU(runningFold, i2)));
            arrayList.add(r2);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: runningFold-yXmHNn8  reason: not valid java name */
    private static final <R> List<R> m1191runningFoldyXmHNn8(byte[] runningFold, R r2, Function2<? super R, ? super UByte, ? extends R> operation) {
        List<R> listOf;
        Intrinsics.checkNotNullParameter(runningFold, "$this$runningFold");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (UByteArray.m265isEmptyimpl(runningFold)) {
            listOf = CollectionsKt__CollectionsJVMKt.listOf(r2);
            return listOf;
        }
        ArrayList arrayList = new ArrayList(UByteArray.m263getSizeimpl(runningFold) + 1);
        arrayList.add(r2);
        int m263getSizeimpl = UByteArray.m263getSizeimpl(runningFold);
        for (int i2 = 0; i2 < m263getSizeimpl; i2++) {
            r2 = operation.invoke(r2, UByte.m199boximpl(UByteArray.m262getw2LRezQ(runningFold, i2)));
            arrayList.add(r2);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: runningFold-zi1B2BA  reason: not valid java name */
    private static final <R> List<R> m1192runningFoldzi1B2BA(int[] runningFold, R r2, Function2<? super R, ? super UInt, ? extends R> operation) {
        List<R> listOf;
        Intrinsics.checkNotNullParameter(runningFold, "$this$runningFold");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (UIntArray.m343isEmptyimpl(runningFold)) {
            listOf = CollectionsKt__CollectionsJVMKt.listOf(r2);
            return listOf;
        }
        ArrayList arrayList = new ArrayList(UIntArray.m341getSizeimpl(runningFold) + 1);
        arrayList.add(r2);
        int m341getSizeimpl = UIntArray.m341getSizeimpl(runningFold);
        for (int i2 = 0; i2 < m341getSizeimpl; i2++) {
            r2 = operation.invoke(r2, UInt.m275boximpl(UIntArray.m340getpVg5ArA(runningFold, i2)));
            arrayList.add(r2);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: runningFold-zww5nb8  reason: not valid java name */
    private static final <R> List<R> m1193runningFoldzww5nb8(short[] runningFold, R r2, Function2<? super R, ? super UShort, ? extends R> operation) {
        List<R> listOf;
        Intrinsics.checkNotNullParameter(runningFold, "$this$runningFold");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (UShortArray.m525isEmptyimpl(runningFold)) {
            listOf = CollectionsKt__CollectionsJVMKt.listOf(r2);
            return listOf;
        }
        ArrayList arrayList = new ArrayList(UShortArray.m523getSizeimpl(runningFold) + 1);
        arrayList.add(r2);
        int m523getSizeimpl = UShortArray.m523getSizeimpl(runningFold);
        for (int i2 = 0; i2 < m523getSizeimpl; i2++) {
            r2 = operation.invoke(r2, UShort.m459boximpl(UShortArray.m522getMh2AYeg(runningFold, i2)));
            arrayList.add(r2);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: runningFoldIndexed-3iWJZGE  reason: not valid java name */
    private static final <R> List<R> m1194runningFoldIndexed3iWJZGE(byte[] runningFoldIndexed, R r2, Function3<? super Integer, ? super R, ? super UByte, ? extends R> operation) {
        List<R> listOf;
        Intrinsics.checkNotNullParameter(runningFoldIndexed, "$this$runningFoldIndexed");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (UByteArray.m265isEmptyimpl(runningFoldIndexed)) {
            listOf = CollectionsKt__CollectionsJVMKt.listOf(r2);
            return listOf;
        }
        ArrayList arrayList = new ArrayList(UByteArray.m263getSizeimpl(runningFoldIndexed) + 1);
        arrayList.add(r2);
        int m263getSizeimpl = UByteArray.m263getSizeimpl(runningFoldIndexed);
        for (int i2 = 0; i2 < m263getSizeimpl; i2++) {
            r2 = operation.invoke(Integer.valueOf(i2), r2, UByte.m199boximpl(UByteArray.m262getw2LRezQ(runningFoldIndexed, i2)));
            arrayList.add(r2);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: runningFoldIndexed-bzxtMww  reason: not valid java name */
    private static final <R> List<R> m1195runningFoldIndexedbzxtMww(short[] runningFoldIndexed, R r2, Function3<? super Integer, ? super R, ? super UShort, ? extends R> operation) {
        List<R> listOf;
        Intrinsics.checkNotNullParameter(runningFoldIndexed, "$this$runningFoldIndexed");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (UShortArray.m525isEmptyimpl(runningFoldIndexed)) {
            listOf = CollectionsKt__CollectionsJVMKt.listOf(r2);
            return listOf;
        }
        ArrayList arrayList = new ArrayList(UShortArray.m523getSizeimpl(runningFoldIndexed) + 1);
        arrayList.add(r2);
        int m523getSizeimpl = UShortArray.m523getSizeimpl(runningFoldIndexed);
        for (int i2 = 0; i2 < m523getSizeimpl; i2++) {
            r2 = operation.invoke(Integer.valueOf(i2), r2, UShort.m459boximpl(UShortArray.m522getMh2AYeg(runningFoldIndexed, i2)));
            arrayList.add(r2);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: runningFoldIndexed-mwnnOCs  reason: not valid java name */
    private static final <R> List<R> m1196runningFoldIndexedmwnnOCs(long[] runningFoldIndexed, R r2, Function3<? super Integer, ? super R, ? super ULong, ? extends R> operation) {
        List<R> listOf;
        Intrinsics.checkNotNullParameter(runningFoldIndexed, "$this$runningFoldIndexed");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (ULongArray.m421isEmptyimpl(runningFoldIndexed)) {
            listOf = CollectionsKt__CollectionsJVMKt.listOf(r2);
            return listOf;
        }
        ArrayList arrayList = new ArrayList(ULongArray.m419getSizeimpl(runningFoldIndexed) + 1);
        arrayList.add(r2);
        int m419getSizeimpl = ULongArray.m419getSizeimpl(runningFoldIndexed);
        for (int i2 = 0; i2 < m419getSizeimpl; i2++) {
            r2 = operation.invoke(Integer.valueOf(i2), r2, ULong.m353boximpl(ULongArray.m418getsVKNKU(runningFoldIndexed, i2)));
            arrayList.add(r2);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: runningFoldIndexed-yVwIW0Q  reason: not valid java name */
    private static final <R> List<R> m1197runningFoldIndexedyVwIW0Q(int[] runningFoldIndexed, R r2, Function3<? super Integer, ? super R, ? super UInt, ? extends R> operation) {
        List<R> listOf;
        Intrinsics.checkNotNullParameter(runningFoldIndexed, "$this$runningFoldIndexed");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (UIntArray.m343isEmptyimpl(runningFoldIndexed)) {
            listOf = CollectionsKt__CollectionsJVMKt.listOf(r2);
            return listOf;
        }
        ArrayList arrayList = new ArrayList(UIntArray.m341getSizeimpl(runningFoldIndexed) + 1);
        arrayList.add(r2);
        int m341getSizeimpl = UIntArray.m341getSizeimpl(runningFoldIndexed);
        for (int i2 = 0; i2 < m341getSizeimpl; i2++) {
            r2 = operation.invoke(Integer.valueOf(i2), r2, UInt.m275boximpl(UIntArray.m340getpVg5ArA(runningFoldIndexed, i2)));
            arrayList.add(r2);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: runningReduce-ELGow60  reason: not valid java name */
    private static final List<UByte> m1198runningReduceELGow60(byte[] runningReduce, Function2<? super UByte, ? super UByte, UByte> operation) {
        List<UByte> emptyList;
        Intrinsics.checkNotNullParameter(runningReduce, "$this$runningReduce");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (UByteArray.m265isEmptyimpl(runningReduce)) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        byte m262getw2LRezQ = UByteArray.m262getw2LRezQ(runningReduce, 0);
        ArrayList arrayList = new ArrayList(UByteArray.m263getSizeimpl(runningReduce));
        arrayList.add(UByte.m199boximpl(m262getw2LRezQ));
        int m263getSizeimpl = UByteArray.m263getSizeimpl(runningReduce);
        for (int i2 = 1; i2 < m263getSizeimpl; i2++) {
            m262getw2LRezQ = operation.invoke(UByte.m199boximpl(m262getw2LRezQ), UByte.m199boximpl(UByteArray.m262getw2LRezQ(runningReduce, i2))).m254unboximpl();
            arrayList.add(UByte.m199boximpl(m262getw2LRezQ));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: runningReduce-WyvcNBI  reason: not valid java name */
    private static final List<UInt> m1199runningReduceWyvcNBI(int[] runningReduce, Function2<? super UInt, ? super UInt, UInt> operation) {
        List<UInt> emptyList;
        Intrinsics.checkNotNullParameter(runningReduce, "$this$runningReduce");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (UIntArray.m343isEmptyimpl(runningReduce)) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        int m340getpVg5ArA = UIntArray.m340getpVg5ArA(runningReduce, 0);
        ArrayList arrayList = new ArrayList(UIntArray.m341getSizeimpl(runningReduce));
        arrayList.add(UInt.m275boximpl(m340getpVg5ArA));
        int m341getSizeimpl = UIntArray.m341getSizeimpl(runningReduce);
        for (int i2 = 1; i2 < m341getSizeimpl; i2++) {
            m340getpVg5ArA = operation.invoke(UInt.m275boximpl(m340getpVg5ArA), UInt.m275boximpl(UIntArray.m340getpVg5ArA(runningReduce, i2))).m332unboximpl();
            arrayList.add(UInt.m275boximpl(m340getpVg5ArA));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: runningReduce-s8dVfGU  reason: not valid java name */
    private static final List<ULong> m1200runningReduces8dVfGU(long[] runningReduce, Function2<? super ULong, ? super ULong, ULong> operation) {
        List<ULong> emptyList;
        Intrinsics.checkNotNullParameter(runningReduce, "$this$runningReduce");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (ULongArray.m421isEmptyimpl(runningReduce)) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        long m418getsVKNKU = ULongArray.m418getsVKNKU(runningReduce, 0);
        ArrayList arrayList = new ArrayList(ULongArray.m419getSizeimpl(runningReduce));
        arrayList.add(ULong.m353boximpl(m418getsVKNKU));
        int m419getSizeimpl = ULongArray.m419getSizeimpl(runningReduce);
        for (int i2 = 1; i2 < m419getSizeimpl; i2++) {
            m418getsVKNKU = operation.invoke(ULong.m353boximpl(m418getsVKNKU), ULong.m353boximpl(ULongArray.m418getsVKNKU(runningReduce, i2))).m410unboximpl();
            arrayList.add(ULong.m353boximpl(m418getsVKNKU));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: runningReduce-xzaTVY8  reason: not valid java name */
    private static final List<UShort> m1201runningReducexzaTVY8(short[] runningReduce, Function2<? super UShort, ? super UShort, UShort> operation) {
        List<UShort> emptyList;
        Intrinsics.checkNotNullParameter(runningReduce, "$this$runningReduce");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (UShortArray.m525isEmptyimpl(runningReduce)) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        short m522getMh2AYeg = UShortArray.m522getMh2AYeg(runningReduce, 0);
        ArrayList arrayList = new ArrayList(UShortArray.m523getSizeimpl(runningReduce));
        arrayList.add(UShort.m459boximpl(m522getMh2AYeg));
        int m523getSizeimpl = UShortArray.m523getSizeimpl(runningReduce);
        for (int i2 = 1; i2 < m523getSizeimpl; i2++) {
            m522getMh2AYeg = operation.invoke(UShort.m459boximpl(m522getMh2AYeg), UShort.m459boximpl(UShortArray.m522getMh2AYeg(runningReduce, i2))).m514unboximpl();
            arrayList.add(UShort.m459boximpl(m522getMh2AYeg));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: runningReduceIndexed-D40WMg8  reason: not valid java name */
    private static final List<UInt> m1202runningReduceIndexedD40WMg8(int[] runningReduceIndexed, Function3<? super Integer, ? super UInt, ? super UInt, UInt> operation) {
        List<UInt> emptyList;
        Intrinsics.checkNotNullParameter(runningReduceIndexed, "$this$runningReduceIndexed");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (UIntArray.m343isEmptyimpl(runningReduceIndexed)) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        int m340getpVg5ArA = UIntArray.m340getpVg5ArA(runningReduceIndexed, 0);
        ArrayList arrayList = new ArrayList(UIntArray.m341getSizeimpl(runningReduceIndexed));
        arrayList.add(UInt.m275boximpl(m340getpVg5ArA));
        int m341getSizeimpl = UIntArray.m341getSizeimpl(runningReduceIndexed);
        for (int i2 = 1; i2 < m341getSizeimpl; i2++) {
            m340getpVg5ArA = operation.invoke(Integer.valueOf(i2), UInt.m275boximpl(m340getpVg5ArA), UInt.m275boximpl(UIntArray.m340getpVg5ArA(runningReduceIndexed, i2))).m332unboximpl();
            arrayList.add(UInt.m275boximpl(m340getpVg5ArA));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: runningReduceIndexed-EOyYB1Y  reason: not valid java name */
    private static final List<UByte> m1203runningReduceIndexedEOyYB1Y(byte[] runningReduceIndexed, Function3<? super Integer, ? super UByte, ? super UByte, UByte> operation) {
        List<UByte> emptyList;
        Intrinsics.checkNotNullParameter(runningReduceIndexed, "$this$runningReduceIndexed");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (UByteArray.m265isEmptyimpl(runningReduceIndexed)) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        byte m262getw2LRezQ = UByteArray.m262getw2LRezQ(runningReduceIndexed, 0);
        ArrayList arrayList = new ArrayList(UByteArray.m263getSizeimpl(runningReduceIndexed));
        arrayList.add(UByte.m199boximpl(m262getw2LRezQ));
        int m263getSizeimpl = UByteArray.m263getSizeimpl(runningReduceIndexed);
        for (int i2 = 1; i2 < m263getSizeimpl; i2++) {
            m262getw2LRezQ = operation.invoke(Integer.valueOf(i2), UByte.m199boximpl(m262getw2LRezQ), UByte.m199boximpl(UByteArray.m262getw2LRezQ(runningReduceIndexed, i2))).m254unboximpl();
            arrayList.add(UByte.m199boximpl(m262getw2LRezQ));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: runningReduceIndexed-aLgx1Fo  reason: not valid java name */
    private static final List<UShort> m1204runningReduceIndexedaLgx1Fo(short[] runningReduceIndexed, Function3<? super Integer, ? super UShort, ? super UShort, UShort> operation) {
        List<UShort> emptyList;
        Intrinsics.checkNotNullParameter(runningReduceIndexed, "$this$runningReduceIndexed");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (UShortArray.m525isEmptyimpl(runningReduceIndexed)) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        short m522getMh2AYeg = UShortArray.m522getMh2AYeg(runningReduceIndexed, 0);
        ArrayList arrayList = new ArrayList(UShortArray.m523getSizeimpl(runningReduceIndexed));
        arrayList.add(UShort.m459boximpl(m522getMh2AYeg));
        int m523getSizeimpl = UShortArray.m523getSizeimpl(runningReduceIndexed);
        for (int i2 = 1; i2 < m523getSizeimpl; i2++) {
            m522getMh2AYeg = operation.invoke(Integer.valueOf(i2), UShort.m459boximpl(m522getMh2AYeg), UShort.m459boximpl(UShortArray.m522getMh2AYeg(runningReduceIndexed, i2))).m514unboximpl();
            arrayList.add(UShort.m459boximpl(m522getMh2AYeg));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: runningReduceIndexed-z1zDJgo  reason: not valid java name */
    private static final List<ULong> m1205runningReduceIndexedz1zDJgo(long[] runningReduceIndexed, Function3<? super Integer, ? super ULong, ? super ULong, ULong> operation) {
        List<ULong> emptyList;
        Intrinsics.checkNotNullParameter(runningReduceIndexed, "$this$runningReduceIndexed");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (ULongArray.m421isEmptyimpl(runningReduceIndexed)) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        long m418getsVKNKU = ULongArray.m418getsVKNKU(runningReduceIndexed, 0);
        ArrayList arrayList = new ArrayList(ULongArray.m419getSizeimpl(runningReduceIndexed));
        arrayList.add(ULong.m353boximpl(m418getsVKNKU));
        int m419getSizeimpl = ULongArray.m419getSizeimpl(runningReduceIndexed);
        for (int i2 = 1; i2 < m419getSizeimpl; i2++) {
            m418getsVKNKU = operation.invoke(Integer.valueOf(i2), ULong.m353boximpl(m418getsVKNKU), ULong.m353boximpl(ULongArray.m418getsVKNKU(runningReduceIndexed, i2))).m410unboximpl();
            arrayList.add(ULong.m353boximpl(m418getsVKNKU));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    /* renamed from: scan-A8wKCXQ  reason: not valid java name */
    private static final <R> List<R> m1206scanA8wKCXQ(long[] scan, R r2, Function2<? super R, ? super ULong, ? extends R> operation) {
        List<R> listOf;
        Intrinsics.checkNotNullParameter(scan, "$this$scan");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (ULongArray.m421isEmptyimpl(scan)) {
            listOf = CollectionsKt__CollectionsJVMKt.listOf(r2);
            return listOf;
        }
        ArrayList arrayList = new ArrayList(ULongArray.m419getSizeimpl(scan) + 1);
        arrayList.add(r2);
        int m419getSizeimpl = ULongArray.m419getSizeimpl(scan);
        for (int i2 = 0; i2 < m419getSizeimpl; i2++) {
            r2 = operation.invoke(r2, ULong.m353boximpl(ULongArray.m418getsVKNKU(scan, i2)));
            arrayList.add(r2);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    /* renamed from: scan-yXmHNn8  reason: not valid java name */
    private static final <R> List<R> m1207scanyXmHNn8(byte[] scan, R r2, Function2<? super R, ? super UByte, ? extends R> operation) {
        List<R> listOf;
        Intrinsics.checkNotNullParameter(scan, "$this$scan");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (UByteArray.m265isEmptyimpl(scan)) {
            listOf = CollectionsKt__CollectionsJVMKt.listOf(r2);
            return listOf;
        }
        ArrayList arrayList = new ArrayList(UByteArray.m263getSizeimpl(scan) + 1);
        arrayList.add(r2);
        int m263getSizeimpl = UByteArray.m263getSizeimpl(scan);
        for (int i2 = 0; i2 < m263getSizeimpl; i2++) {
            r2 = operation.invoke(r2, UByte.m199boximpl(UByteArray.m262getw2LRezQ(scan, i2)));
            arrayList.add(r2);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    /* renamed from: scan-zi1B2BA  reason: not valid java name */
    private static final <R> List<R> m1208scanzi1B2BA(int[] scan, R r2, Function2<? super R, ? super UInt, ? extends R> operation) {
        List<R> listOf;
        Intrinsics.checkNotNullParameter(scan, "$this$scan");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (UIntArray.m343isEmptyimpl(scan)) {
            listOf = CollectionsKt__CollectionsJVMKt.listOf(r2);
            return listOf;
        }
        ArrayList arrayList = new ArrayList(UIntArray.m341getSizeimpl(scan) + 1);
        arrayList.add(r2);
        int m341getSizeimpl = UIntArray.m341getSizeimpl(scan);
        for (int i2 = 0; i2 < m341getSizeimpl; i2++) {
            r2 = operation.invoke(r2, UInt.m275boximpl(UIntArray.m340getpVg5ArA(scan, i2)));
            arrayList.add(r2);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    /* renamed from: scan-zww5nb8  reason: not valid java name */
    private static final <R> List<R> m1209scanzww5nb8(short[] scan, R r2, Function2<? super R, ? super UShort, ? extends R> operation) {
        List<R> listOf;
        Intrinsics.checkNotNullParameter(scan, "$this$scan");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (UShortArray.m525isEmptyimpl(scan)) {
            listOf = CollectionsKt__CollectionsJVMKt.listOf(r2);
            return listOf;
        }
        ArrayList arrayList = new ArrayList(UShortArray.m523getSizeimpl(scan) + 1);
        arrayList.add(r2);
        int m523getSizeimpl = UShortArray.m523getSizeimpl(scan);
        for (int i2 = 0; i2 < m523getSizeimpl; i2++) {
            r2 = operation.invoke(r2, UShort.m459boximpl(UShortArray.m522getMh2AYeg(scan, i2)));
            arrayList.add(r2);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    /* renamed from: scanIndexed-3iWJZGE  reason: not valid java name */
    private static final <R> List<R> m1210scanIndexed3iWJZGE(byte[] scanIndexed, R r2, Function3<? super Integer, ? super R, ? super UByte, ? extends R> operation) {
        List<R> listOf;
        Intrinsics.checkNotNullParameter(scanIndexed, "$this$scanIndexed");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (UByteArray.m265isEmptyimpl(scanIndexed)) {
            listOf = CollectionsKt__CollectionsJVMKt.listOf(r2);
            return listOf;
        }
        ArrayList arrayList = new ArrayList(UByteArray.m263getSizeimpl(scanIndexed) + 1);
        arrayList.add(r2);
        int m263getSizeimpl = UByteArray.m263getSizeimpl(scanIndexed);
        for (int i2 = 0; i2 < m263getSizeimpl; i2++) {
            r2 = operation.invoke(Integer.valueOf(i2), r2, UByte.m199boximpl(UByteArray.m262getw2LRezQ(scanIndexed, i2)));
            arrayList.add(r2);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    /* renamed from: scanIndexed-bzxtMww  reason: not valid java name */
    private static final <R> List<R> m1211scanIndexedbzxtMww(short[] scanIndexed, R r2, Function3<? super Integer, ? super R, ? super UShort, ? extends R> operation) {
        List<R> listOf;
        Intrinsics.checkNotNullParameter(scanIndexed, "$this$scanIndexed");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (UShortArray.m525isEmptyimpl(scanIndexed)) {
            listOf = CollectionsKt__CollectionsJVMKt.listOf(r2);
            return listOf;
        }
        ArrayList arrayList = new ArrayList(UShortArray.m523getSizeimpl(scanIndexed) + 1);
        arrayList.add(r2);
        int m523getSizeimpl = UShortArray.m523getSizeimpl(scanIndexed);
        for (int i2 = 0; i2 < m523getSizeimpl; i2++) {
            r2 = operation.invoke(Integer.valueOf(i2), r2, UShort.m459boximpl(UShortArray.m522getMh2AYeg(scanIndexed, i2)));
            arrayList.add(r2);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    /* renamed from: scanIndexed-mwnnOCs  reason: not valid java name */
    private static final <R> List<R> m1212scanIndexedmwnnOCs(long[] scanIndexed, R r2, Function3<? super Integer, ? super R, ? super ULong, ? extends R> operation) {
        List<R> listOf;
        Intrinsics.checkNotNullParameter(scanIndexed, "$this$scanIndexed");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (ULongArray.m421isEmptyimpl(scanIndexed)) {
            listOf = CollectionsKt__CollectionsJVMKt.listOf(r2);
            return listOf;
        }
        ArrayList arrayList = new ArrayList(ULongArray.m419getSizeimpl(scanIndexed) + 1);
        arrayList.add(r2);
        int m419getSizeimpl = ULongArray.m419getSizeimpl(scanIndexed);
        for (int i2 = 0; i2 < m419getSizeimpl; i2++) {
            r2 = operation.invoke(Integer.valueOf(i2), r2, ULong.m353boximpl(ULongArray.m418getsVKNKU(scanIndexed, i2)));
            arrayList.add(r2);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    /* renamed from: scanIndexed-yVwIW0Q  reason: not valid java name */
    private static final <R> List<R> m1213scanIndexedyVwIW0Q(int[] scanIndexed, R r2, Function3<? super Integer, ? super R, ? super UInt, ? extends R> operation) {
        List<R> listOf;
        Intrinsics.checkNotNullParameter(scanIndexed, "$this$scanIndexed");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (UIntArray.m343isEmptyimpl(scanIndexed)) {
            listOf = CollectionsKt__CollectionsJVMKt.listOf(r2);
            return listOf;
        }
        ArrayList arrayList = new ArrayList(UIntArray.m341getSizeimpl(scanIndexed) + 1);
        arrayList.add(r2);
        int m341getSizeimpl = UIntArray.m341getSizeimpl(scanIndexed);
        for (int i2 = 0; i2 < m341getSizeimpl; i2++) {
            r2 = operation.invoke(Integer.valueOf(i2), r2, UInt.m275boximpl(UIntArray.m340getpVg5ArA(scanIndexed, i2)));
            arrayList.add(r2);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    /* renamed from: shuffle--ajY-9A  reason: not valid java name */
    public static final void m1214shuffleajY9A(@NotNull int[] shuffle) {
        Intrinsics.checkNotNullParameter(shuffle, "$this$shuffle");
        m1215shuffle2D5oskM(shuffle, Random.Default);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    /* renamed from: shuffle-2D5oskM  reason: not valid java name */
    public static final void m1215shuffle2D5oskM(@NotNull int[] shuffle, @NotNull Random random) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(shuffle, "$this$shuffle");
        Intrinsics.checkNotNullParameter(random, "random");
        for (lastIndex = ArraysKt___ArraysKt.getLastIndex(shuffle); lastIndex > 0; lastIndex--) {
            int nextInt = random.nextInt(lastIndex + 1);
            int m340getpVg5ArA = UIntArray.m340getpVg5ArA(shuffle, lastIndex);
            UIntArray.m345setVXSXFK8(shuffle, lastIndex, UIntArray.m340getpVg5ArA(shuffle, nextInt));
            UIntArray.m345setVXSXFK8(shuffle, nextInt, m340getpVg5ArA);
        }
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    /* renamed from: shuffle-GBYM_sE  reason: not valid java name */
    public static final void m1216shuffleGBYM_sE(@NotNull byte[] shuffle) {
        Intrinsics.checkNotNullParameter(shuffle, "$this$shuffle");
        m1219shuffleoSF2wD8(shuffle, Random.Default);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    /* renamed from: shuffle-JzugnMA  reason: not valid java name */
    public static final void m1217shuffleJzugnMA(@NotNull long[] shuffle, @NotNull Random random) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(shuffle, "$this$shuffle");
        Intrinsics.checkNotNullParameter(random, "random");
        for (lastIndex = ArraysKt___ArraysKt.getLastIndex(shuffle); lastIndex > 0; lastIndex--) {
            int nextInt = random.nextInt(lastIndex + 1);
            long m418getsVKNKU = ULongArray.m418getsVKNKU(shuffle, lastIndex);
            ULongArray.m423setk8EXiF4(shuffle, lastIndex, ULongArray.m418getsVKNKU(shuffle, nextInt));
            ULongArray.m423setk8EXiF4(shuffle, nextInt, m418getsVKNKU);
        }
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    /* renamed from: shuffle-QwZRm1k  reason: not valid java name */
    public static final void m1218shuffleQwZRm1k(@NotNull long[] shuffle) {
        Intrinsics.checkNotNullParameter(shuffle, "$this$shuffle");
        m1217shuffleJzugnMA(shuffle, Random.Default);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    /* renamed from: shuffle-oSF2wD8  reason: not valid java name */
    public static final void m1219shuffleoSF2wD8(@NotNull byte[] shuffle, @NotNull Random random) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(shuffle, "$this$shuffle");
        Intrinsics.checkNotNullParameter(random, "random");
        for (lastIndex = ArraysKt___ArraysKt.getLastIndex(shuffle); lastIndex > 0; lastIndex--) {
            int nextInt = random.nextInt(lastIndex + 1);
            byte m262getw2LRezQ = UByteArray.m262getw2LRezQ(shuffle, lastIndex);
            UByteArray.m267setVurrAj0(shuffle, lastIndex, UByteArray.m262getw2LRezQ(shuffle, nextInt));
            UByteArray.m267setVurrAj0(shuffle, nextInt, m262getw2LRezQ);
        }
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    /* renamed from: shuffle-rL5Bavg  reason: not valid java name */
    public static final void m1220shufflerL5Bavg(@NotNull short[] shuffle) {
        Intrinsics.checkNotNullParameter(shuffle, "$this$shuffle");
        m1221shuffles5X_as8(shuffle, Random.Default);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    /* renamed from: shuffle-s5X_as8  reason: not valid java name */
    public static final void m1221shuffles5X_as8(@NotNull short[] shuffle, @NotNull Random random) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(shuffle, "$this$shuffle");
        Intrinsics.checkNotNullParameter(random, "random");
        for (lastIndex = ArraysKt___ArraysKt.getLastIndex(shuffle); lastIndex > 0; lastIndex--) {
            int nextInt = random.nextInt(lastIndex + 1);
            short m522getMh2AYeg = UShortArray.m522getMh2AYeg(shuffle, lastIndex);
            UShortArray.m527set01HTLdE(shuffle, lastIndex, UShortArray.m522getMh2AYeg(shuffle, nextInt));
            UShortArray.m527set01HTLdE(shuffle, nextInt, m522getMh2AYeg);
        }
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: single--ajY-9A  reason: not valid java name */
    private static final int m1222singleajY9A(int[] single) {
        int single2;
        Intrinsics.checkNotNullParameter(single, "$this$single");
        single2 = ArraysKt___ArraysKt.single(single);
        return UInt.m281constructorimpl(single2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: single-GBYM_sE  reason: not valid java name */
    private static final byte m1223singleGBYM_sE(byte[] single) {
        byte single2;
        Intrinsics.checkNotNullParameter(single, "$this$single");
        single2 = ArraysKt___ArraysKt.single(single);
        return UByte.m205constructorimpl(single2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: single-JOV_ifY  reason: not valid java name */
    private static final byte m1224singleJOV_ifY(byte[] single, Function1<? super UByte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(single, "$this$single");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int m263getSizeimpl = UByteArray.m263getSizeimpl(single);
        UByte uByte = null;
        boolean z = false;
        for (int i2 = 0; i2 < m263getSizeimpl; i2++) {
            byte m262getw2LRezQ = UByteArray.m262getw2LRezQ(single, i2);
            if (predicate.invoke(UByte.m199boximpl(m262getw2LRezQ)).booleanValue()) {
                if (z) {
                    throw new IllegalArgumentException("Array contains more than one matching element.");
                }
                uByte = UByte.m199boximpl(m262getw2LRezQ);
                z = true;
            }
        }
        if (z) {
            Objects.requireNonNull(uByte, "null cannot be cast to non-null type kotlin.UByte");
            return uByte.m254unboximpl();
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: single-MShoTSo  reason: not valid java name */
    private static final long m1225singleMShoTSo(long[] single, Function1<? super ULong, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(single, "$this$single");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int m419getSizeimpl = ULongArray.m419getSizeimpl(single);
        ULong uLong = null;
        boolean z = false;
        for (int i2 = 0; i2 < m419getSizeimpl; i2++) {
            long m418getsVKNKU = ULongArray.m418getsVKNKU(single, i2);
            if (predicate.invoke(ULong.m353boximpl(m418getsVKNKU)).booleanValue()) {
                if (z) {
                    throw new IllegalArgumentException("Array contains more than one matching element.");
                }
                uLong = ULong.m353boximpl(m418getsVKNKU);
                z = true;
            }
        }
        if (z) {
            Objects.requireNonNull(uLong, "null cannot be cast to non-null type kotlin.ULong");
            return uLong.m410unboximpl();
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: single-QwZRm1k  reason: not valid java name */
    private static final long m1226singleQwZRm1k(long[] single) {
        long single2;
        Intrinsics.checkNotNullParameter(single, "$this$single");
        single2 = ArraysKt___ArraysKt.single(single);
        return ULong.m359constructorimpl(single2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: single-jgv0xPQ  reason: not valid java name */
    private static final int m1227singlejgv0xPQ(int[] single, Function1<? super UInt, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(single, "$this$single");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int m341getSizeimpl = UIntArray.m341getSizeimpl(single);
        UInt uInt = null;
        boolean z = false;
        for (int i2 = 0; i2 < m341getSizeimpl; i2++) {
            int m340getpVg5ArA = UIntArray.m340getpVg5ArA(single, i2);
            if (predicate.invoke(UInt.m275boximpl(m340getpVg5ArA)).booleanValue()) {
                if (z) {
                    throw new IllegalArgumentException("Array contains more than one matching element.");
                }
                uInt = UInt.m275boximpl(m340getpVg5ArA);
                z = true;
            }
        }
        if (z) {
            Objects.requireNonNull(uInt, "null cannot be cast to non-null type kotlin.UInt");
            return uInt.m332unboximpl();
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: single-rL5Bavg  reason: not valid java name */
    private static final short m1228singlerL5Bavg(short[] single) {
        short single2;
        Intrinsics.checkNotNullParameter(single, "$this$single");
        single2 = ArraysKt___ArraysKt.single(single);
        return UShort.m465constructorimpl(single2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: single-xTcfx_M  reason: not valid java name */
    private static final short m1229singlexTcfx_M(short[] single, Function1<? super UShort, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(single, "$this$single");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int m523getSizeimpl = UShortArray.m523getSizeimpl(single);
        UShort uShort = null;
        boolean z = false;
        for (int i2 = 0; i2 < m523getSizeimpl; i2++) {
            short m522getMh2AYeg = UShortArray.m522getMh2AYeg(single, i2);
            if (predicate.invoke(UShort.m459boximpl(m522getMh2AYeg)).booleanValue()) {
                if (z) {
                    throw new IllegalArgumentException("Array contains more than one matching element.");
                }
                uShort = UShort.m459boximpl(m522getMh2AYeg);
                z = true;
            }
        }
        if (z) {
            Objects.requireNonNull(uShort, "null cannot be cast to non-null type kotlin.UShort");
            return uShort.m514unboximpl();
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Nullable
    /* renamed from: singleOrNull--ajY-9A  reason: not valid java name */
    public static final UInt m1230singleOrNullajY9A(@NotNull int[] singleOrNull) {
        Intrinsics.checkNotNullParameter(singleOrNull, "$this$singleOrNull");
        if (UIntArray.m341getSizeimpl(singleOrNull) == 1) {
            return UInt.m275boximpl(UIntArray.m340getpVg5ArA(singleOrNull, 0));
        }
        return null;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Nullable
    /* renamed from: singleOrNull-GBYM_sE  reason: not valid java name */
    public static final UByte m1231singleOrNullGBYM_sE(@NotNull byte[] singleOrNull) {
        Intrinsics.checkNotNullParameter(singleOrNull, "$this$singleOrNull");
        if (UByteArray.m263getSizeimpl(singleOrNull) == 1) {
            return UByte.m199boximpl(UByteArray.m262getw2LRezQ(singleOrNull, 0));
        }
        return null;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: singleOrNull-JOV_ifY  reason: not valid java name */
    private static final UByte m1232singleOrNullJOV_ifY(byte[] singleOrNull, Function1<? super UByte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(singleOrNull, "$this$singleOrNull");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int m263getSizeimpl = UByteArray.m263getSizeimpl(singleOrNull);
        boolean z = false;
        UByte uByte = null;
        for (int i2 = 0; i2 < m263getSizeimpl; i2++) {
            byte m262getw2LRezQ = UByteArray.m262getw2LRezQ(singleOrNull, i2);
            if (predicate.invoke(UByte.m199boximpl(m262getw2LRezQ)).booleanValue()) {
                if (z) {
                    return null;
                }
                uByte = UByte.m199boximpl(m262getw2LRezQ);
                z = true;
            }
        }
        if (z) {
            return uByte;
        }
        return null;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: singleOrNull-MShoTSo  reason: not valid java name */
    private static final ULong m1233singleOrNullMShoTSo(long[] singleOrNull, Function1<? super ULong, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(singleOrNull, "$this$singleOrNull");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int m419getSizeimpl = ULongArray.m419getSizeimpl(singleOrNull);
        boolean z = false;
        ULong uLong = null;
        for (int i2 = 0; i2 < m419getSizeimpl; i2++) {
            long m418getsVKNKU = ULongArray.m418getsVKNKU(singleOrNull, i2);
            if (predicate.invoke(ULong.m353boximpl(m418getsVKNKU)).booleanValue()) {
                if (z) {
                    return null;
                }
                uLong = ULong.m353boximpl(m418getsVKNKU);
                z = true;
            }
        }
        if (z) {
            return uLong;
        }
        return null;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Nullable
    /* renamed from: singleOrNull-QwZRm1k  reason: not valid java name */
    public static final ULong m1234singleOrNullQwZRm1k(@NotNull long[] singleOrNull) {
        Intrinsics.checkNotNullParameter(singleOrNull, "$this$singleOrNull");
        if (ULongArray.m419getSizeimpl(singleOrNull) == 1) {
            return ULong.m353boximpl(ULongArray.m418getsVKNKU(singleOrNull, 0));
        }
        return null;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: singleOrNull-jgv0xPQ  reason: not valid java name */
    private static final UInt m1235singleOrNulljgv0xPQ(int[] singleOrNull, Function1<? super UInt, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(singleOrNull, "$this$singleOrNull");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int m341getSizeimpl = UIntArray.m341getSizeimpl(singleOrNull);
        boolean z = false;
        UInt uInt = null;
        for (int i2 = 0; i2 < m341getSizeimpl; i2++) {
            int m340getpVg5ArA = UIntArray.m340getpVg5ArA(singleOrNull, i2);
            if (predicate.invoke(UInt.m275boximpl(m340getpVg5ArA)).booleanValue()) {
                if (z) {
                    return null;
                }
                uInt = UInt.m275boximpl(m340getpVg5ArA);
                z = true;
            }
        }
        if (z) {
            return uInt;
        }
        return null;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Nullable
    /* renamed from: singleOrNull-rL5Bavg  reason: not valid java name */
    public static final UShort m1236singleOrNullrL5Bavg(@NotNull short[] singleOrNull) {
        Intrinsics.checkNotNullParameter(singleOrNull, "$this$singleOrNull");
        if (UShortArray.m523getSizeimpl(singleOrNull) == 1) {
            return UShort.m459boximpl(UShortArray.m522getMh2AYeg(singleOrNull, 0));
        }
        return null;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: singleOrNull-xTcfx_M  reason: not valid java name */
    private static final UShort m1237singleOrNullxTcfx_M(short[] singleOrNull, Function1<? super UShort, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(singleOrNull, "$this$singleOrNull");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int m523getSizeimpl = UShortArray.m523getSizeimpl(singleOrNull);
        boolean z = false;
        UShort uShort = null;
        for (int i2 = 0; i2 < m523getSizeimpl; i2++) {
            short m522getMh2AYeg = UShortArray.m522getMh2AYeg(singleOrNull, i2);
            if (predicate.invoke(UShort.m459boximpl(m522getMh2AYeg)).booleanValue()) {
                if (z) {
                    return null;
                }
                uShort = UShort.m459boximpl(m522getMh2AYeg);
                z = true;
            }
        }
        if (z) {
            return uShort;
        }
        return null;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: slice-F7u83W8  reason: not valid java name */
    public static final List<ULong> m1238sliceF7u83W8(@NotNull long[] slice, @NotNull Iterable<Integer> indices) {
        int collectionSizeOrDefault;
        List<ULong> emptyList;
        Intrinsics.checkNotNullParameter(slice, "$this$slice");
        Intrinsics.checkNotNullParameter(indices, "indices");
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(indices, 10);
        if (collectionSizeOrDefault == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (Integer num : indices) {
            arrayList.add(ULong.m353boximpl(ULongArray.m418getsVKNKU(slice, num.intValue())));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: slice-HwE9HBo  reason: not valid java name */
    public static final List<UInt> m1239sliceHwE9HBo(@NotNull int[] slice, @NotNull Iterable<Integer> indices) {
        int collectionSizeOrDefault;
        List<UInt> emptyList;
        Intrinsics.checkNotNullParameter(slice, "$this$slice");
        Intrinsics.checkNotNullParameter(indices, "indices");
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(indices, 10);
        if (collectionSizeOrDefault == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (Integer num : indices) {
            arrayList.add(UInt.m275boximpl(UIntArray.m340getpVg5ArA(slice, num.intValue())));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: slice-JGPC0-M  reason: not valid java name */
    public static final List<UShort> m1240sliceJGPC0M(@NotNull short[] slice, @NotNull Iterable<Integer> indices) {
        int collectionSizeOrDefault;
        List<UShort> emptyList;
        Intrinsics.checkNotNullParameter(slice, "$this$slice");
        Intrinsics.checkNotNullParameter(indices, "indices");
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(indices, 10);
        if (collectionSizeOrDefault == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (Integer num : indices) {
            arrayList.add(UShort.m459boximpl(UShortArray.m522getMh2AYeg(slice, num.intValue())));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: slice-JQknh5Q  reason: not valid java name */
    public static final List<UByte> m1241sliceJQknh5Q(@NotNull byte[] slice, @NotNull Iterable<Integer> indices) {
        int collectionSizeOrDefault;
        List<UByte> emptyList;
        Intrinsics.checkNotNullParameter(slice, "$this$slice");
        Intrinsics.checkNotNullParameter(indices, "indices");
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(indices, 10);
        if (collectionSizeOrDefault == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (Integer num : indices) {
            arrayList.add(UByte.m199boximpl(UByteArray.m262getw2LRezQ(slice, num.intValue())));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: slice-Q6IL4kU  reason: not valid java name */
    public static final List<UShort> m1242sliceQ6IL4kU(@NotNull short[] slice, @NotNull IntRange indices) {
        short[] copyOfRange;
        List<UShort> emptyList;
        Intrinsics.checkNotNullParameter(slice, "$this$slice");
        Intrinsics.checkNotNullParameter(indices, "indices");
        if (indices.isEmpty()) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        copyOfRange = ArraysKt___ArraysJvmKt.copyOfRange(slice, indices.getStart().intValue(), indices.getEndInclusive().intValue() + 1);
        return UArraysKt___UArraysJvmKt.m661asListrL5Bavg(UShortArray.m517constructorimpl(copyOfRange));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: slice-ZRhS8yI  reason: not valid java name */
    public static final List<ULong> m1243sliceZRhS8yI(@NotNull long[] slice, @NotNull IntRange indices) {
        long[] copyOfRange;
        List<ULong> emptyList;
        Intrinsics.checkNotNullParameter(slice, "$this$slice");
        Intrinsics.checkNotNullParameter(indices, "indices");
        if (indices.isEmpty()) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        copyOfRange = ArraysKt___ArraysJvmKt.copyOfRange(slice, indices.getStart().intValue(), indices.getEndInclusive().intValue() + 1);
        return UArraysKt___UArraysJvmKt.m660asListQwZRm1k(ULongArray.m413constructorimpl(copyOfRange));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: slice-c0bezYM  reason: not valid java name */
    public static final List<UByte> m1244slicec0bezYM(@NotNull byte[] slice, @NotNull IntRange indices) {
        byte[] copyOfRange;
        List<UByte> emptyList;
        Intrinsics.checkNotNullParameter(slice, "$this$slice");
        Intrinsics.checkNotNullParameter(indices, "indices");
        if (indices.isEmpty()) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        copyOfRange = ArraysKt___ArraysJvmKt.copyOfRange(slice, indices.getStart().intValue(), indices.getEndInclusive().intValue() + 1);
        return UArraysKt___UArraysJvmKt.m659asListGBYM_sE(UByteArray.m257constructorimpl(copyOfRange));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: slice-tAntMlw  reason: not valid java name */
    public static final List<UInt> m1245slicetAntMlw(@NotNull int[] slice, @NotNull IntRange indices) {
        int[] copyOfRange;
        List<UInt> emptyList;
        Intrinsics.checkNotNullParameter(slice, "$this$slice");
        Intrinsics.checkNotNullParameter(indices, "indices");
        if (indices.isEmpty()) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        copyOfRange = ArraysKt___ArraysJvmKt.copyOfRange(slice, indices.getStart().intValue(), indices.getEndInclusive().intValue() + 1);
        return UArraysKt___UArraysJvmKt.m658asListajY9A(UIntArray.m335constructorimpl(copyOfRange));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: sliceArray-CFIt9YE  reason: not valid java name */
    public static final int[] m1246sliceArrayCFIt9YE(@NotNull int[] sliceArray, @NotNull Collection<Integer> indices) {
        int[] sliceArray2;
        Intrinsics.checkNotNullParameter(sliceArray, "$this$sliceArray");
        Intrinsics.checkNotNullParameter(indices, "indices");
        sliceArray2 = ArraysKt___ArraysKt.sliceArray(sliceArray, (Collection<Integer>) indices);
        return UIntArray.m335constructorimpl(sliceArray2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: sliceArray-Q6IL4kU  reason: not valid java name */
    public static final short[] m1247sliceArrayQ6IL4kU(@NotNull short[] sliceArray, @NotNull IntRange indices) {
        short[] sliceArray2;
        Intrinsics.checkNotNullParameter(sliceArray, "$this$sliceArray");
        Intrinsics.checkNotNullParameter(indices, "indices");
        sliceArray2 = ArraysKt___ArraysKt.sliceArray(sliceArray, indices);
        return UShortArray.m517constructorimpl(sliceArray2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: sliceArray-ZRhS8yI  reason: not valid java name */
    public static final long[] m1248sliceArrayZRhS8yI(@NotNull long[] sliceArray, @NotNull IntRange indices) {
        long[] sliceArray2;
        Intrinsics.checkNotNullParameter(sliceArray, "$this$sliceArray");
        Intrinsics.checkNotNullParameter(indices, "indices");
        sliceArray2 = ArraysKt___ArraysKt.sliceArray(sliceArray, indices);
        return ULongArray.m413constructorimpl(sliceArray2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: sliceArray-c0bezYM  reason: not valid java name */
    public static final byte[] m1249sliceArrayc0bezYM(@NotNull byte[] sliceArray, @NotNull IntRange indices) {
        byte[] sliceArray2;
        Intrinsics.checkNotNullParameter(sliceArray, "$this$sliceArray");
        Intrinsics.checkNotNullParameter(indices, "indices");
        sliceArray2 = ArraysKt___ArraysKt.sliceArray(sliceArray, indices);
        return UByteArray.m257constructorimpl(sliceArray2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: sliceArray-kzHmqpY  reason: not valid java name */
    public static final long[] m1250sliceArraykzHmqpY(@NotNull long[] sliceArray, @NotNull Collection<Integer> indices) {
        long[] sliceArray2;
        Intrinsics.checkNotNullParameter(sliceArray, "$this$sliceArray");
        Intrinsics.checkNotNullParameter(indices, "indices");
        sliceArray2 = ArraysKt___ArraysKt.sliceArray(sliceArray, (Collection<Integer>) indices);
        return ULongArray.m413constructorimpl(sliceArray2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: sliceArray-ojwP5H8  reason: not valid java name */
    public static final short[] m1251sliceArrayojwP5H8(@NotNull short[] sliceArray, @NotNull Collection<Integer> indices) {
        short[] sliceArray2;
        Intrinsics.checkNotNullParameter(sliceArray, "$this$sliceArray");
        Intrinsics.checkNotNullParameter(indices, "indices");
        sliceArray2 = ArraysKt___ArraysKt.sliceArray(sliceArray, (Collection<Integer>) indices);
        return UShortArray.m517constructorimpl(sliceArray2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: sliceArray-tAntMlw  reason: not valid java name */
    public static final int[] m1252sliceArraytAntMlw(@NotNull int[] sliceArray, @NotNull IntRange indices) {
        int[] sliceArray2;
        Intrinsics.checkNotNullParameter(sliceArray, "$this$sliceArray");
        Intrinsics.checkNotNullParameter(indices, "indices");
        sliceArray2 = ArraysKt___ArraysKt.sliceArray(sliceArray, indices);
        return UIntArray.m335constructorimpl(sliceArray2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: sliceArray-xo_DsdI  reason: not valid java name */
    public static final byte[] m1253sliceArrayxo_DsdI(@NotNull byte[] sliceArray, @NotNull Collection<Integer> indices) {
        byte[] sliceArray2;
        Intrinsics.checkNotNullParameter(sliceArray, "$this$sliceArray");
        Intrinsics.checkNotNullParameter(indices, "indices");
        sliceArray2 = ArraysKt___ArraysKt.sliceArray(sliceArray, (Collection<Integer>) indices);
        return UByteArray.m257constructorimpl(sliceArray2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: sort--ajY-9A  reason: not valid java name */
    public static final void m1254sortajY9A(@NotNull int[] sort) {
        Intrinsics.checkNotNullParameter(sort, "$this$sort");
        if (UIntArray.m341getSizeimpl(sort) > 1) {
            UArraySortingKt.m645sortArrayoBK06Vg(sort, 0, UIntArray.m341getSizeimpl(sort));
        }
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    /* renamed from: sort--nroSd4  reason: not valid java name */
    public static final void m1255sortnroSd4(@NotNull long[] sort, int i2, int i3) {
        Intrinsics.checkNotNullParameter(sort, "$this$sort");
        AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(i2, i3, ULongArray.m419getSizeimpl(sort));
        UArraySortingKt.m642sortArraynroSd4(sort, i2, i3);
    }

    /* renamed from: sort--nroSd4$default  reason: not valid java name */
    public static /* synthetic */ void m1256sortnroSd4$default(long[] jArr, int i2, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i2 = 0;
        }
        if ((i4 & 2) != 0) {
            i3 = ULongArray.m419getSizeimpl(jArr);
        }
        m1255sortnroSd4(jArr, i2, i3);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    /* renamed from: sort-4UcCI2c  reason: not valid java name */
    public static final void m1257sort4UcCI2c(@NotNull byte[] sort, int i2, int i3) {
        Intrinsics.checkNotNullParameter(sort, "$this$sort");
        AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(i2, i3, UByteArray.m263getSizeimpl(sort));
        UArraySortingKt.m643sortArray4UcCI2c(sort, i2, i3);
    }

    /* renamed from: sort-4UcCI2c$default  reason: not valid java name */
    public static /* synthetic */ void m1258sort4UcCI2c$default(byte[] bArr, int i2, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i2 = 0;
        }
        if ((i4 & 2) != 0) {
            i3 = UByteArray.m263getSizeimpl(bArr);
        }
        m1257sort4UcCI2c(bArr, i2, i3);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    /* renamed from: sort-Aa5vz7o  reason: not valid java name */
    public static final void m1259sortAa5vz7o(@NotNull short[] sort, int i2, int i3) {
        Intrinsics.checkNotNullParameter(sort, "$this$sort");
        AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(i2, i3, UShortArray.m523getSizeimpl(sort));
        UArraySortingKt.m644sortArrayAa5vz7o(sort, i2, i3);
    }

    /* renamed from: sort-Aa5vz7o$default  reason: not valid java name */
    public static /* synthetic */ void m1260sortAa5vz7o$default(short[] sArr, int i2, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i2 = 0;
        }
        if ((i4 & 2) != 0) {
            i3 = UShortArray.m523getSizeimpl(sArr);
        }
        m1259sortAa5vz7o(sArr, i2, i3);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: sort-GBYM_sE  reason: not valid java name */
    public static final void m1261sortGBYM_sE(@NotNull byte[] sort) {
        Intrinsics.checkNotNullParameter(sort, "$this$sort");
        if (UByteArray.m263getSizeimpl(sort) > 1) {
            UArraySortingKt.m643sortArray4UcCI2c(sort, 0, UByteArray.m263getSizeimpl(sort));
        }
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: sort-QwZRm1k  reason: not valid java name */
    public static final void m1262sortQwZRm1k(@NotNull long[] sort) {
        Intrinsics.checkNotNullParameter(sort, "$this$sort");
        if (ULongArray.m419getSizeimpl(sort) > 1) {
            UArraySortingKt.m642sortArraynroSd4(sort, 0, ULongArray.m419getSizeimpl(sort));
        }
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    /* renamed from: sort-oBK06Vg  reason: not valid java name */
    public static final void m1263sortoBK06Vg(@NotNull int[] sort, int i2, int i3) {
        Intrinsics.checkNotNullParameter(sort, "$this$sort");
        AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(i2, i3, UIntArray.m341getSizeimpl(sort));
        UArraySortingKt.m645sortArrayoBK06Vg(sort, i2, i3);
    }

    /* renamed from: sort-oBK06Vg$default  reason: not valid java name */
    public static /* synthetic */ void m1264sortoBK06Vg$default(int[] iArr, int i2, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i2 = 0;
        }
        if ((i4 & 2) != 0) {
            i3 = UIntArray.m341getSizeimpl(iArr);
        }
        m1263sortoBK06Vg(iArr, i2, i3);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: sort-rL5Bavg  reason: not valid java name */
    public static final void m1265sortrL5Bavg(@NotNull short[] sort) {
        Intrinsics.checkNotNullParameter(sort, "$this$sort");
        if (UShortArray.m523getSizeimpl(sort) > 1) {
            UArraySortingKt.m644sortArrayAa5vz7o(sort, 0, UShortArray.m523getSizeimpl(sort));
        }
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: sortDescending--ajY-9A  reason: not valid java name */
    public static final void m1266sortDescendingajY9A(@NotNull int[] sortDescending) {
        Intrinsics.checkNotNullParameter(sortDescending, "$this$sortDescending");
        if (UIntArray.m341getSizeimpl(sortDescending) > 1) {
            m1254sortajY9A(sortDescending);
            ArraysKt___ArraysKt.reverse(sortDescending);
        }
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    /* renamed from: sortDescending--nroSd4  reason: not valid java name */
    public static final void m1267sortDescendingnroSd4(@NotNull long[] sortDescending, int i2, int i3) {
        Intrinsics.checkNotNullParameter(sortDescending, "$this$sortDescending");
        m1255sortnroSd4(sortDescending, i2, i3);
        ArraysKt___ArraysKt.reverse(sortDescending, i2, i3);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    /* renamed from: sortDescending-4UcCI2c  reason: not valid java name */
    public static final void m1268sortDescending4UcCI2c(@NotNull byte[] sortDescending, int i2, int i3) {
        Intrinsics.checkNotNullParameter(sortDescending, "$this$sortDescending");
        m1257sort4UcCI2c(sortDescending, i2, i3);
        ArraysKt___ArraysKt.reverse(sortDescending, i2, i3);
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    /* renamed from: sortDescending-Aa5vz7o  reason: not valid java name */
    public static final void m1269sortDescendingAa5vz7o(@NotNull short[] sortDescending, int i2, int i3) {
        Intrinsics.checkNotNullParameter(sortDescending, "$this$sortDescending");
        m1259sortAa5vz7o(sortDescending, i2, i3);
        ArraysKt___ArraysKt.reverse(sortDescending, i2, i3);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: sortDescending-GBYM_sE  reason: not valid java name */
    public static final void m1270sortDescendingGBYM_sE(@NotNull byte[] sortDescending) {
        Intrinsics.checkNotNullParameter(sortDescending, "$this$sortDescending");
        if (UByteArray.m263getSizeimpl(sortDescending) > 1) {
            m1261sortGBYM_sE(sortDescending);
            ArraysKt___ArraysKt.reverse(sortDescending);
        }
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: sortDescending-QwZRm1k  reason: not valid java name */
    public static final void m1271sortDescendingQwZRm1k(@NotNull long[] sortDescending) {
        Intrinsics.checkNotNullParameter(sortDescending, "$this$sortDescending");
        if (ULongArray.m419getSizeimpl(sortDescending) > 1) {
            m1262sortQwZRm1k(sortDescending);
            ArraysKt___ArraysKt.reverse(sortDescending);
        }
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    /* renamed from: sortDescending-oBK06Vg  reason: not valid java name */
    public static final void m1272sortDescendingoBK06Vg(@NotNull int[] sortDescending, int i2, int i3) {
        Intrinsics.checkNotNullParameter(sortDescending, "$this$sortDescending");
        m1263sortoBK06Vg(sortDescending, i2, i3);
        ArraysKt___ArraysKt.reverse(sortDescending, i2, i3);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: sortDescending-rL5Bavg  reason: not valid java name */
    public static final void m1273sortDescendingrL5Bavg(@NotNull short[] sortDescending) {
        Intrinsics.checkNotNullParameter(sortDescending, "$this$sortDescending");
        if (UShortArray.m523getSizeimpl(sortDescending) > 1) {
            m1265sortrL5Bavg(sortDescending);
            ArraysKt___ArraysKt.reverse(sortDescending);
        }
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: sorted--ajY-9A  reason: not valid java name */
    public static final List<UInt> m1274sortedajY9A(@NotNull int[] sorted) {
        Intrinsics.checkNotNullParameter(sorted, "$this$sorted");
        int[] copyOf = Arrays.copyOf(sorted, sorted.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
        int[] m335constructorimpl = UIntArray.m335constructorimpl(copyOf);
        m1254sortajY9A(m335constructorimpl);
        return UArraysKt___UArraysJvmKt.m658asListajY9A(m335constructorimpl);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: sorted-GBYM_sE  reason: not valid java name */
    public static final List<UByte> m1275sortedGBYM_sE(@NotNull byte[] sorted) {
        Intrinsics.checkNotNullParameter(sorted, "$this$sorted");
        byte[] copyOf = Arrays.copyOf(sorted, sorted.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
        byte[] m257constructorimpl = UByteArray.m257constructorimpl(copyOf);
        m1261sortGBYM_sE(m257constructorimpl);
        return UArraysKt___UArraysJvmKt.m659asListGBYM_sE(m257constructorimpl);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: sorted-QwZRm1k  reason: not valid java name */
    public static final List<ULong> m1276sortedQwZRm1k(@NotNull long[] sorted) {
        Intrinsics.checkNotNullParameter(sorted, "$this$sorted");
        long[] copyOf = Arrays.copyOf(sorted, sorted.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
        long[] m413constructorimpl = ULongArray.m413constructorimpl(copyOf);
        m1262sortQwZRm1k(m413constructorimpl);
        return UArraysKt___UArraysJvmKt.m660asListQwZRm1k(m413constructorimpl);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: sorted-rL5Bavg  reason: not valid java name */
    public static final List<UShort> m1277sortedrL5Bavg(@NotNull short[] sorted) {
        Intrinsics.checkNotNullParameter(sorted, "$this$sorted");
        short[] copyOf = Arrays.copyOf(sorted, sorted.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
        short[] m517constructorimpl = UShortArray.m517constructorimpl(copyOf);
        m1265sortrL5Bavg(m517constructorimpl);
        return UArraysKt___UArraysJvmKt.m661asListrL5Bavg(m517constructorimpl);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: sortedArray--ajY-9A  reason: not valid java name */
    public static final int[] m1278sortedArrayajY9A(@NotNull int[] sortedArray) {
        Intrinsics.checkNotNullParameter(sortedArray, "$this$sortedArray");
        if (UIntArray.m343isEmptyimpl(sortedArray)) {
            return sortedArray;
        }
        int[] copyOf = Arrays.copyOf(sortedArray, sortedArray.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
        int[] m335constructorimpl = UIntArray.m335constructorimpl(copyOf);
        m1254sortajY9A(m335constructorimpl);
        return m335constructorimpl;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: sortedArray-GBYM_sE  reason: not valid java name */
    public static final byte[] m1279sortedArrayGBYM_sE(@NotNull byte[] sortedArray) {
        Intrinsics.checkNotNullParameter(sortedArray, "$this$sortedArray");
        if (UByteArray.m265isEmptyimpl(sortedArray)) {
            return sortedArray;
        }
        byte[] copyOf = Arrays.copyOf(sortedArray, sortedArray.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
        byte[] m257constructorimpl = UByteArray.m257constructorimpl(copyOf);
        m1261sortGBYM_sE(m257constructorimpl);
        return m257constructorimpl;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: sortedArray-QwZRm1k  reason: not valid java name */
    public static final long[] m1280sortedArrayQwZRm1k(@NotNull long[] sortedArray) {
        Intrinsics.checkNotNullParameter(sortedArray, "$this$sortedArray");
        if (ULongArray.m421isEmptyimpl(sortedArray)) {
            return sortedArray;
        }
        long[] copyOf = Arrays.copyOf(sortedArray, sortedArray.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
        long[] m413constructorimpl = ULongArray.m413constructorimpl(copyOf);
        m1262sortQwZRm1k(m413constructorimpl);
        return m413constructorimpl;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: sortedArray-rL5Bavg  reason: not valid java name */
    public static final short[] m1281sortedArrayrL5Bavg(@NotNull short[] sortedArray) {
        Intrinsics.checkNotNullParameter(sortedArray, "$this$sortedArray");
        if (UShortArray.m525isEmptyimpl(sortedArray)) {
            return sortedArray;
        }
        short[] copyOf = Arrays.copyOf(sortedArray, sortedArray.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
        short[] m517constructorimpl = UShortArray.m517constructorimpl(copyOf);
        m1265sortrL5Bavg(m517constructorimpl);
        return m517constructorimpl;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: sortedArrayDescending--ajY-9A  reason: not valid java name */
    public static final int[] m1282sortedArrayDescendingajY9A(@NotNull int[] sortedArrayDescending) {
        Intrinsics.checkNotNullParameter(sortedArrayDescending, "$this$sortedArrayDescending");
        if (UIntArray.m343isEmptyimpl(sortedArrayDescending)) {
            return sortedArrayDescending;
        }
        int[] copyOf = Arrays.copyOf(sortedArrayDescending, sortedArrayDescending.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
        int[] m335constructorimpl = UIntArray.m335constructorimpl(copyOf);
        m1266sortDescendingajY9A(m335constructorimpl);
        return m335constructorimpl;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: sortedArrayDescending-GBYM_sE  reason: not valid java name */
    public static final byte[] m1283sortedArrayDescendingGBYM_sE(@NotNull byte[] sortedArrayDescending) {
        Intrinsics.checkNotNullParameter(sortedArrayDescending, "$this$sortedArrayDescending");
        if (UByteArray.m265isEmptyimpl(sortedArrayDescending)) {
            return sortedArrayDescending;
        }
        byte[] copyOf = Arrays.copyOf(sortedArrayDescending, sortedArrayDescending.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
        byte[] m257constructorimpl = UByteArray.m257constructorimpl(copyOf);
        m1270sortDescendingGBYM_sE(m257constructorimpl);
        return m257constructorimpl;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: sortedArrayDescending-QwZRm1k  reason: not valid java name */
    public static final long[] m1284sortedArrayDescendingQwZRm1k(@NotNull long[] sortedArrayDescending) {
        Intrinsics.checkNotNullParameter(sortedArrayDescending, "$this$sortedArrayDescending");
        if (ULongArray.m421isEmptyimpl(sortedArrayDescending)) {
            return sortedArrayDescending;
        }
        long[] copyOf = Arrays.copyOf(sortedArrayDescending, sortedArrayDescending.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
        long[] m413constructorimpl = ULongArray.m413constructorimpl(copyOf);
        m1271sortDescendingQwZRm1k(m413constructorimpl);
        return m413constructorimpl;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: sortedArrayDescending-rL5Bavg  reason: not valid java name */
    public static final short[] m1285sortedArrayDescendingrL5Bavg(@NotNull short[] sortedArrayDescending) {
        Intrinsics.checkNotNullParameter(sortedArrayDescending, "$this$sortedArrayDescending");
        if (UShortArray.m525isEmptyimpl(sortedArrayDescending)) {
            return sortedArrayDescending;
        }
        short[] copyOf = Arrays.copyOf(sortedArrayDescending, sortedArrayDescending.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
        short[] m517constructorimpl = UShortArray.m517constructorimpl(copyOf);
        m1273sortDescendingrL5Bavg(m517constructorimpl);
        return m517constructorimpl;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: sortedDescending--ajY-9A  reason: not valid java name */
    public static final List<UInt> m1286sortedDescendingajY9A(@NotNull int[] sortedDescending) {
        Intrinsics.checkNotNullParameter(sortedDescending, "$this$sortedDescending");
        int[] copyOf = Arrays.copyOf(sortedDescending, sortedDescending.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
        int[] m335constructorimpl = UIntArray.m335constructorimpl(copyOf);
        m1254sortajY9A(m335constructorimpl);
        return m1182reversedajY9A(m335constructorimpl);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: sortedDescending-GBYM_sE  reason: not valid java name */
    public static final List<UByte> m1287sortedDescendingGBYM_sE(@NotNull byte[] sortedDescending) {
        Intrinsics.checkNotNullParameter(sortedDescending, "$this$sortedDescending");
        byte[] copyOf = Arrays.copyOf(sortedDescending, sortedDescending.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
        byte[] m257constructorimpl = UByteArray.m257constructorimpl(copyOf);
        m1261sortGBYM_sE(m257constructorimpl);
        return m1183reversedGBYM_sE(m257constructorimpl);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: sortedDescending-QwZRm1k  reason: not valid java name */
    public static final List<ULong> m1288sortedDescendingQwZRm1k(@NotNull long[] sortedDescending) {
        Intrinsics.checkNotNullParameter(sortedDescending, "$this$sortedDescending");
        long[] copyOf = Arrays.copyOf(sortedDescending, sortedDescending.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
        long[] m413constructorimpl = ULongArray.m413constructorimpl(copyOf);
        m1262sortQwZRm1k(m413constructorimpl);
        return m1184reversedQwZRm1k(m413constructorimpl);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: sortedDescending-rL5Bavg  reason: not valid java name */
    public static final List<UShort> m1289sortedDescendingrL5Bavg(@NotNull short[] sortedDescending) {
        Intrinsics.checkNotNullParameter(sortedDescending, "$this$sortedDescending");
        short[] copyOf = Arrays.copyOf(sortedDescending, sortedDescending.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
        short[] m517constructorimpl = UShortArray.m517constructorimpl(copyOf);
        m1265sortrL5Bavg(m517constructorimpl);
        return m1185reversedrL5Bavg(m517constructorimpl);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: sum--ajY-9A  reason: not valid java name */
    private static final int m1290sumajY9A(int[] sum) {
        int sum2;
        Intrinsics.checkNotNullParameter(sum, "$this$sum");
        sum2 = ArraysKt___ArraysKt.sum(sum);
        return UInt.m281constructorimpl(sum2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: sum-GBYM_sE  reason: not valid java name */
    private static final int m1291sumGBYM_sE(byte[] sum) {
        Intrinsics.checkNotNullParameter(sum, "$this$sum");
        int m281constructorimpl = UInt.m281constructorimpl(0);
        int m263getSizeimpl = UByteArray.m263getSizeimpl(sum);
        for (int i2 = 0; i2 < m263getSizeimpl; i2++) {
            m281constructorimpl = UInt.m281constructorimpl(m281constructorimpl + UInt.m281constructorimpl(UByteArray.m262getw2LRezQ(sum, i2) & 255));
        }
        return m281constructorimpl;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: sum-QwZRm1k  reason: not valid java name */
    private static final long m1292sumQwZRm1k(long[] sum) {
        long sum2;
        Intrinsics.checkNotNullParameter(sum, "$this$sum");
        sum2 = ArraysKt___ArraysKt.sum(sum);
        return ULong.m359constructorimpl(sum2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: sum-rL5Bavg  reason: not valid java name */
    private static final int m1293sumrL5Bavg(short[] sum) {
        Intrinsics.checkNotNullParameter(sum, "$this$sum");
        int m281constructorimpl = UInt.m281constructorimpl(0);
        int m523getSizeimpl = UShortArray.m523getSizeimpl(sum);
        for (int i2 = 0; i2 < m523getSizeimpl; i2++) {
            m281constructorimpl = UInt.m281constructorimpl(m281constructorimpl + UInt.m281constructorimpl(UShortArray.m522getMh2AYeg(sum, i2) & UShort.MAX_VALUE));
        }
        return m281constructorimpl;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = {}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    /* renamed from: sumBy-JOV_ifY  reason: not valid java name */
    private static final int m1294sumByJOV_ifY(byte[] sumBy, Function1<? super UByte, UInt> selector) {
        Intrinsics.checkNotNullParameter(sumBy, "$this$sumBy");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int m263getSizeimpl = UByteArray.m263getSizeimpl(sumBy);
        int i2 = 0;
        for (int i3 = 0; i3 < m263getSizeimpl; i3++) {
            i2 = UInt.m281constructorimpl(i2 + selector.invoke(UByte.m199boximpl(UByteArray.m262getw2LRezQ(sumBy, i3))).m332unboximpl());
        }
        return i2;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = {}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    /* renamed from: sumBy-MShoTSo  reason: not valid java name */
    private static final int m1295sumByMShoTSo(long[] sumBy, Function1<? super ULong, UInt> selector) {
        Intrinsics.checkNotNullParameter(sumBy, "$this$sumBy");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int m419getSizeimpl = ULongArray.m419getSizeimpl(sumBy);
        int i2 = 0;
        for (int i3 = 0; i3 < m419getSizeimpl; i3++) {
            i2 = UInt.m281constructorimpl(i2 + selector.invoke(ULong.m353boximpl(ULongArray.m418getsVKNKU(sumBy, i3))).m332unboximpl());
        }
        return i2;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = {}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    /* renamed from: sumBy-jgv0xPQ  reason: not valid java name */
    private static final int m1296sumByjgv0xPQ(int[] sumBy, Function1<? super UInt, UInt> selector) {
        Intrinsics.checkNotNullParameter(sumBy, "$this$sumBy");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int m341getSizeimpl = UIntArray.m341getSizeimpl(sumBy);
        int i2 = 0;
        for (int i3 = 0; i3 < m341getSizeimpl; i3++) {
            i2 = UInt.m281constructorimpl(i2 + selector.invoke(UInt.m275boximpl(UIntArray.m340getpVg5ArA(sumBy, i3))).m332unboximpl());
        }
        return i2;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = {}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    /* renamed from: sumBy-xTcfx_M  reason: not valid java name */
    private static final int m1297sumByxTcfx_M(short[] sumBy, Function1<? super UShort, UInt> selector) {
        Intrinsics.checkNotNullParameter(sumBy, "$this$sumBy");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int m523getSizeimpl = UShortArray.m523getSizeimpl(sumBy);
        int i2 = 0;
        for (int i3 = 0; i3 < m523getSizeimpl; i3++) {
            i2 = UInt.m281constructorimpl(i2 + selector.invoke(UShort.m459boximpl(UShortArray.m522getMh2AYeg(sumBy, i3))).m332unboximpl());
        }
        return i2;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = {}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    /* renamed from: sumByDouble-JOV_ifY  reason: not valid java name */
    private static final double m1298sumByDoubleJOV_ifY(byte[] sumByDouble, Function1<? super UByte, Double> selector) {
        Intrinsics.checkNotNullParameter(sumByDouble, "$this$sumByDouble");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int m263getSizeimpl = UByteArray.m263getSizeimpl(sumByDouble);
        double d2 = 0.0d;
        for (int i2 = 0; i2 < m263getSizeimpl; i2++) {
            d2 += selector.invoke(UByte.m199boximpl(UByteArray.m262getw2LRezQ(sumByDouble, i2))).doubleValue();
        }
        return d2;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = {}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    /* renamed from: sumByDouble-MShoTSo  reason: not valid java name */
    private static final double m1299sumByDoubleMShoTSo(long[] sumByDouble, Function1<? super ULong, Double> selector) {
        Intrinsics.checkNotNullParameter(sumByDouble, "$this$sumByDouble");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int m419getSizeimpl = ULongArray.m419getSizeimpl(sumByDouble);
        double d2 = 0.0d;
        for (int i2 = 0; i2 < m419getSizeimpl; i2++) {
            d2 += selector.invoke(ULong.m353boximpl(ULongArray.m418getsVKNKU(sumByDouble, i2))).doubleValue();
        }
        return d2;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = {}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    /* renamed from: sumByDouble-jgv0xPQ  reason: not valid java name */
    private static final double m1300sumByDoublejgv0xPQ(int[] sumByDouble, Function1<? super UInt, Double> selector) {
        Intrinsics.checkNotNullParameter(sumByDouble, "$this$sumByDouble");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int m341getSizeimpl = UIntArray.m341getSizeimpl(sumByDouble);
        double d2 = 0.0d;
        for (int i2 = 0; i2 < m341getSizeimpl; i2++) {
            d2 += selector.invoke(UInt.m275boximpl(UIntArray.m340getpVg5ArA(sumByDouble, i2))).doubleValue();
        }
        return d2;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = {}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    /* renamed from: sumByDouble-xTcfx_M  reason: not valid java name */
    private static final double m1301sumByDoublexTcfx_M(short[] sumByDouble, Function1<? super UShort, Double> selector) {
        Intrinsics.checkNotNullParameter(sumByDouble, "$this$sumByDouble");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int m523getSizeimpl = UShortArray.m523getSizeimpl(sumByDouble);
        double d2 = 0.0d;
        for (int i2 = 0; i2 < m523getSizeimpl; i2++) {
            d2 += selector.invoke(UShort.m459boximpl(UShortArray.m522getMh2AYeg(sumByDouble, i2))).doubleValue();
        }
        return d2;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @JvmName(name = "sumOfDouble")
    @OverloadResolutionByLambdaReturnType
    private static final double sumOfDouble(byte[] sumOf, Function1<? super UByte, Double> selector) {
        Intrinsics.checkNotNullParameter(sumOf, "$this$sumOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int m263getSizeimpl = UByteArray.m263getSizeimpl(sumOf);
        double d2 = 0.0d;
        for (int i2 = 0; i2 < m263getSizeimpl; i2++) {
            d2 += selector.invoke(UByte.m199boximpl(UByteArray.m262getw2LRezQ(sumOf, i2))).doubleValue();
        }
        return d2;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @JvmName(name = "sumOfDouble")
    @OverloadResolutionByLambdaReturnType
    private static final double sumOfDouble(int[] sumOf, Function1<? super UInt, Double> selector) {
        Intrinsics.checkNotNullParameter(sumOf, "$this$sumOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int m341getSizeimpl = UIntArray.m341getSizeimpl(sumOf);
        double d2 = 0.0d;
        for (int i2 = 0; i2 < m341getSizeimpl; i2++) {
            d2 += selector.invoke(UInt.m275boximpl(UIntArray.m340getpVg5ArA(sumOf, i2))).doubleValue();
        }
        return d2;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @JvmName(name = "sumOfDouble")
    @OverloadResolutionByLambdaReturnType
    private static final double sumOfDouble(long[] sumOf, Function1<? super ULong, Double> selector) {
        Intrinsics.checkNotNullParameter(sumOf, "$this$sumOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int m419getSizeimpl = ULongArray.m419getSizeimpl(sumOf);
        double d2 = 0.0d;
        for (int i2 = 0; i2 < m419getSizeimpl; i2++) {
            d2 += selector.invoke(ULong.m353boximpl(ULongArray.m418getsVKNKU(sumOf, i2))).doubleValue();
        }
        return d2;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @JvmName(name = "sumOfDouble")
    @OverloadResolutionByLambdaReturnType
    private static final double sumOfDouble(short[] sumOf, Function1<? super UShort, Double> selector) {
        Intrinsics.checkNotNullParameter(sumOf, "$this$sumOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int m523getSizeimpl = UShortArray.m523getSizeimpl(sumOf);
        double d2 = 0.0d;
        for (int i2 = 0; i2 < m523getSizeimpl; i2++) {
            d2 += selector.invoke(UShort.m459boximpl(UShortArray.m522getMh2AYeg(sumOf, i2))).doubleValue();
        }
        return d2;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @JvmName(name = "sumOfInt")
    @OverloadResolutionByLambdaReturnType
    private static final int sumOfInt(byte[] sumOf, Function1<? super UByte, Integer> selector) {
        Intrinsics.checkNotNullParameter(sumOf, "$this$sumOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int m263getSizeimpl = UByteArray.m263getSizeimpl(sumOf);
        int i2 = 0;
        for (int i3 = 0; i3 < m263getSizeimpl; i3++) {
            i2 += selector.invoke(UByte.m199boximpl(UByteArray.m262getw2LRezQ(sumOf, i3))).intValue();
        }
        return i2;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @JvmName(name = "sumOfInt")
    @OverloadResolutionByLambdaReturnType
    private static final int sumOfInt(int[] sumOf, Function1<? super UInt, Integer> selector) {
        Intrinsics.checkNotNullParameter(sumOf, "$this$sumOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int m341getSizeimpl = UIntArray.m341getSizeimpl(sumOf);
        int i2 = 0;
        for (int i3 = 0; i3 < m341getSizeimpl; i3++) {
            i2 += selector.invoke(UInt.m275boximpl(UIntArray.m340getpVg5ArA(sumOf, i3))).intValue();
        }
        return i2;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @JvmName(name = "sumOfInt")
    @OverloadResolutionByLambdaReturnType
    private static final int sumOfInt(long[] sumOf, Function1<? super ULong, Integer> selector) {
        Intrinsics.checkNotNullParameter(sumOf, "$this$sumOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int m419getSizeimpl = ULongArray.m419getSizeimpl(sumOf);
        int i2 = 0;
        for (int i3 = 0; i3 < m419getSizeimpl; i3++) {
            i2 += selector.invoke(ULong.m353boximpl(ULongArray.m418getsVKNKU(sumOf, i3))).intValue();
        }
        return i2;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @JvmName(name = "sumOfInt")
    @OverloadResolutionByLambdaReturnType
    private static final int sumOfInt(short[] sumOf, Function1<? super UShort, Integer> selector) {
        Intrinsics.checkNotNullParameter(sumOf, "$this$sumOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int m523getSizeimpl = UShortArray.m523getSizeimpl(sumOf);
        int i2 = 0;
        for (int i3 = 0; i3 < m523getSizeimpl; i3++) {
            i2 += selector.invoke(UShort.m459boximpl(UShortArray.m522getMh2AYeg(sumOf, i3))).intValue();
        }
        return i2;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @JvmName(name = "sumOfLong")
    @OverloadResolutionByLambdaReturnType
    private static final long sumOfLong(byte[] sumOf, Function1<? super UByte, Long> selector) {
        Intrinsics.checkNotNullParameter(sumOf, "$this$sumOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int m263getSizeimpl = UByteArray.m263getSizeimpl(sumOf);
        long j2 = 0;
        for (int i2 = 0; i2 < m263getSizeimpl; i2++) {
            j2 += selector.invoke(UByte.m199boximpl(UByteArray.m262getw2LRezQ(sumOf, i2))).longValue();
        }
        return j2;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @JvmName(name = "sumOfLong")
    @OverloadResolutionByLambdaReturnType
    private static final long sumOfLong(int[] sumOf, Function1<? super UInt, Long> selector) {
        Intrinsics.checkNotNullParameter(sumOf, "$this$sumOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int m341getSizeimpl = UIntArray.m341getSizeimpl(sumOf);
        long j2 = 0;
        for (int i2 = 0; i2 < m341getSizeimpl; i2++) {
            j2 += selector.invoke(UInt.m275boximpl(UIntArray.m340getpVg5ArA(sumOf, i2))).longValue();
        }
        return j2;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @JvmName(name = "sumOfLong")
    @OverloadResolutionByLambdaReturnType
    private static final long sumOfLong(long[] sumOf, Function1<? super ULong, Long> selector) {
        Intrinsics.checkNotNullParameter(sumOf, "$this$sumOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int m419getSizeimpl = ULongArray.m419getSizeimpl(sumOf);
        long j2 = 0;
        for (int i2 = 0; i2 < m419getSizeimpl; i2++) {
            j2 += selector.invoke(ULong.m353boximpl(ULongArray.m418getsVKNKU(sumOf, i2))).longValue();
        }
        return j2;
    }

    @SinceKotlin(version = "1.4")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @JvmName(name = "sumOfLong")
    @OverloadResolutionByLambdaReturnType
    private static final long sumOfLong(short[] sumOf, Function1<? super UShort, Long> selector) {
        Intrinsics.checkNotNullParameter(sumOf, "$this$sumOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int m523getSizeimpl = UShortArray.m523getSizeimpl(sumOf);
        long j2 = 0;
        for (int i2 = 0; i2 < m523getSizeimpl; i2++) {
            j2 += selector.invoke(UShort.m459boximpl(UShortArray.m522getMh2AYeg(sumOf, i2))).longValue();
        }
        return j2;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    @JvmName(name = "sumOfUByte")
    public static final int sumOfUByte(@NotNull UByte[] uByteArr) {
        Intrinsics.checkNotNullParameter(uByteArr, "<this>");
        int i2 = 0;
        for (UByte uByte : uByteArr) {
            i2 = UInt.m281constructorimpl(i2 + UInt.m281constructorimpl(uByte.m254unboximpl() & 255));
        }
        return i2;
    }

    @SinceKotlin(version = "1.5")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @JvmName(name = "sumOfUInt")
    @OverloadResolutionByLambdaReturnType
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    private static final int sumOfUInt(byte[] sumOf, Function1<? super UByte, UInt> selector) {
        Intrinsics.checkNotNullParameter(sumOf, "$this$sumOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int m281constructorimpl = UInt.m281constructorimpl(0);
        int m263getSizeimpl = UByteArray.m263getSizeimpl(sumOf);
        for (int i2 = 0; i2 < m263getSizeimpl; i2++) {
            m281constructorimpl = UInt.m281constructorimpl(m281constructorimpl + selector.invoke(UByte.m199boximpl(UByteArray.m262getw2LRezQ(sumOf, i2))).m332unboximpl());
        }
        return m281constructorimpl;
    }

    @SinceKotlin(version = "1.5")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @JvmName(name = "sumOfUInt")
    @OverloadResolutionByLambdaReturnType
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    private static final int sumOfUInt(int[] sumOf, Function1<? super UInt, UInt> selector) {
        Intrinsics.checkNotNullParameter(sumOf, "$this$sumOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int m281constructorimpl = UInt.m281constructorimpl(0);
        int m341getSizeimpl = UIntArray.m341getSizeimpl(sumOf);
        for (int i2 = 0; i2 < m341getSizeimpl; i2++) {
            m281constructorimpl = UInt.m281constructorimpl(m281constructorimpl + selector.invoke(UInt.m275boximpl(UIntArray.m340getpVg5ArA(sumOf, i2))).m332unboximpl());
        }
        return m281constructorimpl;
    }

    @SinceKotlin(version = "1.5")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @JvmName(name = "sumOfUInt")
    @OverloadResolutionByLambdaReturnType
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    private static final int sumOfUInt(long[] sumOf, Function1<? super ULong, UInt> selector) {
        Intrinsics.checkNotNullParameter(sumOf, "$this$sumOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int m281constructorimpl = UInt.m281constructorimpl(0);
        int m419getSizeimpl = ULongArray.m419getSizeimpl(sumOf);
        for (int i2 = 0; i2 < m419getSizeimpl; i2++) {
            m281constructorimpl = UInt.m281constructorimpl(m281constructorimpl + selector.invoke(ULong.m353boximpl(ULongArray.m418getsVKNKU(sumOf, i2))).m332unboximpl());
        }
        return m281constructorimpl;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    @JvmName(name = "sumOfUInt")
    public static final int sumOfUInt(@NotNull UInt[] uIntArr) {
        Intrinsics.checkNotNullParameter(uIntArr, "<this>");
        int i2 = 0;
        for (UInt uInt : uIntArr) {
            i2 = UInt.m281constructorimpl(i2 + uInt.m332unboximpl());
        }
        return i2;
    }

    @SinceKotlin(version = "1.5")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @JvmName(name = "sumOfUInt")
    @OverloadResolutionByLambdaReturnType
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    private static final int sumOfUInt(short[] sumOf, Function1<? super UShort, UInt> selector) {
        Intrinsics.checkNotNullParameter(sumOf, "$this$sumOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int m281constructorimpl = UInt.m281constructorimpl(0);
        int m523getSizeimpl = UShortArray.m523getSizeimpl(sumOf);
        for (int i2 = 0; i2 < m523getSizeimpl; i2++) {
            m281constructorimpl = UInt.m281constructorimpl(m281constructorimpl + selector.invoke(UShort.m459boximpl(UShortArray.m522getMh2AYeg(sumOf, i2))).m332unboximpl());
        }
        return m281constructorimpl;
    }

    @SinceKotlin(version = "1.5")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @JvmName(name = "sumOfULong")
    @OverloadResolutionByLambdaReturnType
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    private static final long sumOfULong(byte[] sumOf, Function1<? super UByte, ULong> selector) {
        Intrinsics.checkNotNullParameter(sumOf, "$this$sumOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        long m359constructorimpl = ULong.m359constructorimpl(0L);
        int m263getSizeimpl = UByteArray.m263getSizeimpl(sumOf);
        for (int i2 = 0; i2 < m263getSizeimpl; i2++) {
            m359constructorimpl = ULong.m359constructorimpl(m359constructorimpl + selector.invoke(UByte.m199boximpl(UByteArray.m262getw2LRezQ(sumOf, i2))).m410unboximpl());
        }
        return m359constructorimpl;
    }

    @SinceKotlin(version = "1.5")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @JvmName(name = "sumOfULong")
    @OverloadResolutionByLambdaReturnType
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    private static final long sumOfULong(int[] sumOf, Function1<? super UInt, ULong> selector) {
        Intrinsics.checkNotNullParameter(sumOf, "$this$sumOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        long m359constructorimpl = ULong.m359constructorimpl(0L);
        int m341getSizeimpl = UIntArray.m341getSizeimpl(sumOf);
        for (int i2 = 0; i2 < m341getSizeimpl; i2++) {
            m359constructorimpl = ULong.m359constructorimpl(m359constructorimpl + selector.invoke(UInt.m275boximpl(UIntArray.m340getpVg5ArA(sumOf, i2))).m410unboximpl());
        }
        return m359constructorimpl;
    }

    @SinceKotlin(version = "1.5")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @JvmName(name = "sumOfULong")
    @OverloadResolutionByLambdaReturnType
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    private static final long sumOfULong(long[] sumOf, Function1<? super ULong, ULong> selector) {
        Intrinsics.checkNotNullParameter(sumOf, "$this$sumOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        long m359constructorimpl = ULong.m359constructorimpl(0L);
        int m419getSizeimpl = ULongArray.m419getSizeimpl(sumOf);
        for (int i2 = 0; i2 < m419getSizeimpl; i2++) {
            m359constructorimpl = ULong.m359constructorimpl(m359constructorimpl + selector.invoke(ULong.m353boximpl(ULongArray.m418getsVKNKU(sumOf, i2))).m410unboximpl());
        }
        return m359constructorimpl;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    @JvmName(name = "sumOfULong")
    public static final long sumOfULong(@NotNull ULong[] uLongArr) {
        Intrinsics.checkNotNullParameter(uLongArr, "<this>");
        long j2 = 0;
        for (ULong uLong : uLongArr) {
            j2 = ULong.m359constructorimpl(j2 + uLong.m410unboximpl());
        }
        return j2;
    }

    @SinceKotlin(version = "1.5")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @JvmName(name = "sumOfULong")
    @OverloadResolutionByLambdaReturnType
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    private static final long sumOfULong(short[] sumOf, Function1<? super UShort, ULong> selector) {
        Intrinsics.checkNotNullParameter(sumOf, "$this$sumOf");
        Intrinsics.checkNotNullParameter(selector, "selector");
        long m359constructorimpl = ULong.m359constructorimpl(0L);
        int m523getSizeimpl = UShortArray.m523getSizeimpl(sumOf);
        for (int i2 = 0; i2 < m523getSizeimpl; i2++) {
            m359constructorimpl = ULong.m359constructorimpl(m359constructorimpl + selector.invoke(UShort.m459boximpl(UShortArray.m522getMh2AYeg(sumOf, i2))).m410unboximpl());
        }
        return m359constructorimpl;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    @JvmName(name = "sumOfUShort")
    public static final int sumOfUShort(@NotNull UShort[] uShortArr) {
        Intrinsics.checkNotNullParameter(uShortArr, "<this>");
        int i2 = 0;
        for (UShort uShort : uShortArr) {
            i2 = UInt.m281constructorimpl(i2 + UInt.m281constructorimpl(uShort.m514unboximpl() & UShort.MAX_VALUE));
        }
        return i2;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: take-PpDY95g  reason: not valid java name */
    public static final List<UByte> m1302takePpDY95g(@NotNull byte[] take, int i2) {
        List<UByte> listOf;
        List<UByte> list;
        List<UByte> emptyList;
        Intrinsics.checkNotNullParameter(take, "$this$take");
        if (!(i2 >= 0)) {
            throw new IllegalArgumentException(("Requested element count " + i2 + " is less than zero.").toString());
        } else if (i2 == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        } else if (i2 >= UByteArray.m263getSizeimpl(take)) {
            list = CollectionsKt___CollectionsKt.toList(UByteArray.m255boximpl(take));
            return list;
        } else if (i2 == 1) {
            listOf = CollectionsKt__CollectionsJVMKt.listOf(UByte.m199boximpl(UByteArray.m262getw2LRezQ(take, 0)));
            return listOf;
        } else {
            ArrayList arrayList = new ArrayList(i2);
            int m263getSizeimpl = UByteArray.m263getSizeimpl(take);
            int i3 = 0;
            for (int i4 = 0; i4 < m263getSizeimpl; i4++) {
                arrayList.add(UByte.m199boximpl(UByteArray.m262getw2LRezQ(take, i4)));
                i3++;
                if (i3 == i2) {
                    break;
                }
            }
            return arrayList;
        }
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: take-nggk6HY  reason: not valid java name */
    public static final List<UShort> m1303takenggk6HY(@NotNull short[] take, int i2) {
        List<UShort> listOf;
        List<UShort> list;
        List<UShort> emptyList;
        Intrinsics.checkNotNullParameter(take, "$this$take");
        if (!(i2 >= 0)) {
            throw new IllegalArgumentException(("Requested element count " + i2 + " is less than zero.").toString());
        } else if (i2 == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        } else if (i2 >= UShortArray.m523getSizeimpl(take)) {
            list = CollectionsKt___CollectionsKt.toList(UShortArray.m515boximpl(take));
            return list;
        } else if (i2 == 1) {
            listOf = CollectionsKt__CollectionsJVMKt.listOf(UShort.m459boximpl(UShortArray.m522getMh2AYeg(take, 0)));
            return listOf;
        } else {
            ArrayList arrayList = new ArrayList(i2);
            int m523getSizeimpl = UShortArray.m523getSizeimpl(take);
            int i3 = 0;
            for (int i4 = 0; i4 < m523getSizeimpl; i4++) {
                arrayList.add(UShort.m459boximpl(UShortArray.m522getMh2AYeg(take, i4)));
                i3++;
                if (i3 == i2) {
                    break;
                }
            }
            return arrayList;
        }
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: take-qFRl0hI  reason: not valid java name */
    public static final List<UInt> m1304takeqFRl0hI(@NotNull int[] take, int i2) {
        List<UInt> listOf;
        List<UInt> list;
        List<UInt> emptyList;
        Intrinsics.checkNotNullParameter(take, "$this$take");
        if (!(i2 >= 0)) {
            throw new IllegalArgumentException(("Requested element count " + i2 + " is less than zero.").toString());
        } else if (i2 == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        } else if (i2 >= UIntArray.m341getSizeimpl(take)) {
            list = CollectionsKt___CollectionsKt.toList(UIntArray.m333boximpl(take));
            return list;
        } else if (i2 == 1) {
            listOf = CollectionsKt__CollectionsJVMKt.listOf(UInt.m275boximpl(UIntArray.m340getpVg5ArA(take, 0)));
            return listOf;
        } else {
            ArrayList arrayList = new ArrayList(i2);
            int m341getSizeimpl = UIntArray.m341getSizeimpl(take);
            int i3 = 0;
            for (int i4 = 0; i4 < m341getSizeimpl; i4++) {
                arrayList.add(UInt.m275boximpl(UIntArray.m340getpVg5ArA(take, i4)));
                i3++;
                if (i3 == i2) {
                    break;
                }
            }
            return arrayList;
        }
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: take-r7IrZao  reason: not valid java name */
    public static final List<ULong> m1305taker7IrZao(@NotNull long[] take, int i2) {
        List<ULong> listOf;
        List<ULong> list;
        List<ULong> emptyList;
        Intrinsics.checkNotNullParameter(take, "$this$take");
        if (!(i2 >= 0)) {
            throw new IllegalArgumentException(("Requested element count " + i2 + " is less than zero.").toString());
        } else if (i2 == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        } else if (i2 >= ULongArray.m419getSizeimpl(take)) {
            list = CollectionsKt___CollectionsKt.toList(ULongArray.m411boximpl(take));
            return list;
        } else if (i2 == 1) {
            listOf = CollectionsKt__CollectionsJVMKt.listOf(ULong.m353boximpl(ULongArray.m418getsVKNKU(take, 0)));
            return listOf;
        } else {
            ArrayList arrayList = new ArrayList(i2);
            int m419getSizeimpl = ULongArray.m419getSizeimpl(take);
            int i3 = 0;
            for (int i4 = 0; i4 < m419getSizeimpl; i4++) {
                arrayList.add(ULong.m353boximpl(ULongArray.m418getsVKNKU(take, i4)));
                i3++;
                if (i3 == i2) {
                    break;
                }
            }
            return arrayList;
        }
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: takeLast-PpDY95g  reason: not valid java name */
    public static final List<UByte> m1306takeLastPpDY95g(@NotNull byte[] takeLast, int i2) {
        List<UByte> listOf;
        List<UByte> list;
        List<UByte> emptyList;
        Intrinsics.checkNotNullParameter(takeLast, "$this$takeLast");
        if (!(i2 >= 0)) {
            throw new IllegalArgumentException(("Requested element count " + i2 + " is less than zero.").toString());
        } else if (i2 == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        } else {
            int m263getSizeimpl = UByteArray.m263getSizeimpl(takeLast);
            if (i2 >= m263getSizeimpl) {
                list = CollectionsKt___CollectionsKt.toList(UByteArray.m255boximpl(takeLast));
                return list;
            } else if (i2 == 1) {
                listOf = CollectionsKt__CollectionsJVMKt.listOf(UByte.m199boximpl(UByteArray.m262getw2LRezQ(takeLast, m263getSizeimpl - 1)));
                return listOf;
            } else {
                ArrayList arrayList = new ArrayList(i2);
                for (int i3 = m263getSizeimpl - i2; i3 < m263getSizeimpl; i3++) {
                    arrayList.add(UByte.m199boximpl(UByteArray.m262getw2LRezQ(takeLast, i3)));
                }
                return arrayList;
            }
        }
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: takeLast-nggk6HY  reason: not valid java name */
    public static final List<UShort> m1307takeLastnggk6HY(@NotNull short[] takeLast, int i2) {
        List<UShort> listOf;
        List<UShort> list;
        List<UShort> emptyList;
        Intrinsics.checkNotNullParameter(takeLast, "$this$takeLast");
        if (!(i2 >= 0)) {
            throw new IllegalArgumentException(("Requested element count " + i2 + " is less than zero.").toString());
        } else if (i2 == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        } else {
            int m523getSizeimpl = UShortArray.m523getSizeimpl(takeLast);
            if (i2 >= m523getSizeimpl) {
                list = CollectionsKt___CollectionsKt.toList(UShortArray.m515boximpl(takeLast));
                return list;
            } else if (i2 == 1) {
                listOf = CollectionsKt__CollectionsJVMKt.listOf(UShort.m459boximpl(UShortArray.m522getMh2AYeg(takeLast, m523getSizeimpl - 1)));
                return listOf;
            } else {
                ArrayList arrayList = new ArrayList(i2);
                for (int i3 = m523getSizeimpl - i2; i3 < m523getSizeimpl; i3++) {
                    arrayList.add(UShort.m459boximpl(UShortArray.m522getMh2AYeg(takeLast, i3)));
                }
                return arrayList;
            }
        }
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: takeLast-qFRl0hI  reason: not valid java name */
    public static final List<UInt> m1308takeLastqFRl0hI(@NotNull int[] takeLast, int i2) {
        List<UInt> listOf;
        List<UInt> list;
        List<UInt> emptyList;
        Intrinsics.checkNotNullParameter(takeLast, "$this$takeLast");
        if (!(i2 >= 0)) {
            throw new IllegalArgumentException(("Requested element count " + i2 + " is less than zero.").toString());
        } else if (i2 == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        } else {
            int m341getSizeimpl = UIntArray.m341getSizeimpl(takeLast);
            if (i2 >= m341getSizeimpl) {
                list = CollectionsKt___CollectionsKt.toList(UIntArray.m333boximpl(takeLast));
                return list;
            } else if (i2 == 1) {
                listOf = CollectionsKt__CollectionsJVMKt.listOf(UInt.m275boximpl(UIntArray.m340getpVg5ArA(takeLast, m341getSizeimpl - 1)));
                return listOf;
            } else {
                ArrayList arrayList = new ArrayList(i2);
                for (int i3 = m341getSizeimpl - i2; i3 < m341getSizeimpl; i3++) {
                    arrayList.add(UInt.m275boximpl(UIntArray.m340getpVg5ArA(takeLast, i3)));
                }
                return arrayList;
            }
        }
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: takeLast-r7IrZao  reason: not valid java name */
    public static final List<ULong> m1309takeLastr7IrZao(@NotNull long[] takeLast, int i2) {
        List<ULong> listOf;
        List<ULong> list;
        List<ULong> emptyList;
        Intrinsics.checkNotNullParameter(takeLast, "$this$takeLast");
        if (!(i2 >= 0)) {
            throw new IllegalArgumentException(("Requested element count " + i2 + " is less than zero.").toString());
        } else if (i2 == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        } else {
            int m419getSizeimpl = ULongArray.m419getSizeimpl(takeLast);
            if (i2 >= m419getSizeimpl) {
                list = CollectionsKt___CollectionsKt.toList(ULongArray.m411boximpl(takeLast));
                return list;
            } else if (i2 == 1) {
                listOf = CollectionsKt__CollectionsJVMKt.listOf(ULong.m353boximpl(ULongArray.m418getsVKNKU(takeLast, m419getSizeimpl - 1)));
                return listOf;
            } else {
                ArrayList arrayList = new ArrayList(i2);
                for (int i3 = m419getSizeimpl - i2; i3 < m419getSizeimpl; i3++) {
                    arrayList.add(ULong.m353boximpl(ULongArray.m418getsVKNKU(takeLast, i3)));
                }
                return arrayList;
            }
        }
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: takeLastWhile-JOV_ifY  reason: not valid java name */
    private static final List<UByte> m1310takeLastWhileJOV_ifY(byte[] takeLastWhile, Function1<? super UByte, Boolean> predicate) {
        int lastIndex;
        List<UByte> list;
        Intrinsics.checkNotNullParameter(takeLastWhile, "$this$takeLastWhile");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (lastIndex = ArraysKt___ArraysKt.getLastIndex(takeLastWhile); -1 < lastIndex; lastIndex--) {
            if (!predicate.invoke(UByte.m199boximpl(UByteArray.m262getw2LRezQ(takeLastWhile, lastIndex))).booleanValue()) {
                return m778dropPpDY95g(takeLastWhile, lastIndex + 1);
            }
        }
        list = CollectionsKt___CollectionsKt.toList(UByteArray.m255boximpl(takeLastWhile));
        return list;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: takeLastWhile-MShoTSo  reason: not valid java name */
    private static final List<ULong> m1311takeLastWhileMShoTSo(long[] takeLastWhile, Function1<? super ULong, Boolean> predicate) {
        int lastIndex;
        List<ULong> list;
        Intrinsics.checkNotNullParameter(takeLastWhile, "$this$takeLastWhile");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (lastIndex = ArraysKt___ArraysKt.getLastIndex(takeLastWhile); -1 < lastIndex; lastIndex--) {
            if (!predicate.invoke(ULong.m353boximpl(ULongArray.m418getsVKNKU(takeLastWhile, lastIndex))).booleanValue()) {
                return m781dropr7IrZao(takeLastWhile, lastIndex + 1);
            }
        }
        list = CollectionsKt___CollectionsKt.toList(ULongArray.m411boximpl(takeLastWhile));
        return list;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: takeLastWhile-jgv0xPQ  reason: not valid java name */
    private static final List<UInt> m1312takeLastWhilejgv0xPQ(int[] takeLastWhile, Function1<? super UInt, Boolean> predicate) {
        int lastIndex;
        List<UInt> list;
        Intrinsics.checkNotNullParameter(takeLastWhile, "$this$takeLastWhile");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (lastIndex = ArraysKt___ArraysKt.getLastIndex(takeLastWhile); -1 < lastIndex; lastIndex--) {
            if (!predicate.invoke(UInt.m275boximpl(UIntArray.m340getpVg5ArA(takeLastWhile, lastIndex))).booleanValue()) {
                return m780dropqFRl0hI(takeLastWhile, lastIndex + 1);
            }
        }
        list = CollectionsKt___CollectionsKt.toList(UIntArray.m333boximpl(takeLastWhile));
        return list;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: takeLastWhile-xTcfx_M  reason: not valid java name */
    private static final List<UShort> m1313takeLastWhilexTcfx_M(short[] takeLastWhile, Function1<? super UShort, Boolean> predicate) {
        int lastIndex;
        List<UShort> list;
        Intrinsics.checkNotNullParameter(takeLastWhile, "$this$takeLastWhile");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (lastIndex = ArraysKt___ArraysKt.getLastIndex(takeLastWhile); -1 < lastIndex; lastIndex--) {
            if (!predicate.invoke(UShort.m459boximpl(UShortArray.m522getMh2AYeg(takeLastWhile, lastIndex))).booleanValue()) {
                return m779dropnggk6HY(takeLastWhile, lastIndex + 1);
            }
        }
        list = CollectionsKt___CollectionsKt.toList(UShortArray.m515boximpl(takeLastWhile));
        return list;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: takeWhile-JOV_ifY  reason: not valid java name */
    private static final List<UByte> m1314takeWhileJOV_ifY(byte[] takeWhile, Function1<? super UByte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(takeWhile, "$this$takeWhile");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        int m263getSizeimpl = UByteArray.m263getSizeimpl(takeWhile);
        for (int i2 = 0; i2 < m263getSizeimpl; i2++) {
            byte m262getw2LRezQ = UByteArray.m262getw2LRezQ(takeWhile, i2);
            if (!predicate.invoke(UByte.m199boximpl(m262getw2LRezQ)).booleanValue()) {
                break;
            }
            arrayList.add(UByte.m199boximpl(m262getw2LRezQ));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: takeWhile-MShoTSo  reason: not valid java name */
    private static final List<ULong> m1315takeWhileMShoTSo(long[] takeWhile, Function1<? super ULong, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(takeWhile, "$this$takeWhile");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        int m419getSizeimpl = ULongArray.m419getSizeimpl(takeWhile);
        for (int i2 = 0; i2 < m419getSizeimpl; i2++) {
            long m418getsVKNKU = ULongArray.m418getsVKNKU(takeWhile, i2);
            if (!predicate.invoke(ULong.m353boximpl(m418getsVKNKU)).booleanValue()) {
                break;
            }
            arrayList.add(ULong.m353boximpl(m418getsVKNKU));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: takeWhile-jgv0xPQ  reason: not valid java name */
    private static final List<UInt> m1316takeWhilejgv0xPQ(int[] takeWhile, Function1<? super UInt, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(takeWhile, "$this$takeWhile");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        int m341getSizeimpl = UIntArray.m341getSizeimpl(takeWhile);
        for (int i2 = 0; i2 < m341getSizeimpl; i2++) {
            int m340getpVg5ArA = UIntArray.m340getpVg5ArA(takeWhile, i2);
            if (!predicate.invoke(UInt.m275boximpl(m340getpVg5ArA)).booleanValue()) {
                break;
            }
            arrayList.add(UInt.m275boximpl(m340getpVg5ArA));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: takeWhile-xTcfx_M  reason: not valid java name */
    private static final List<UShort> m1317takeWhilexTcfx_M(short[] takeWhile, Function1<? super UShort, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(takeWhile, "$this$takeWhile");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        int m523getSizeimpl = UShortArray.m523getSizeimpl(takeWhile);
        for (int i2 = 0; i2 < m523getSizeimpl; i2++) {
            short m522getMh2AYeg = UShortArray.m522getMh2AYeg(takeWhile, i2);
            if (!predicate.invoke(UShort.m459boximpl(m522getMh2AYeg)).booleanValue()) {
                break;
            }
            arrayList.add(UShort.m459boximpl(m522getMh2AYeg));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: toByteArray-GBYM_sE  reason: not valid java name */
    private static final byte[] m1318toByteArrayGBYM_sE(byte[] toByteArray) {
        Intrinsics.checkNotNullParameter(toByteArray, "$this$toByteArray");
        byte[] copyOf = Arrays.copyOf(toByteArray, toByteArray.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
        return copyOf;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: toIntArray--ajY-9A  reason: not valid java name */
    private static final int[] m1319toIntArrayajY9A(int[] toIntArray) {
        Intrinsics.checkNotNullParameter(toIntArray, "$this$toIntArray");
        int[] copyOf = Arrays.copyOf(toIntArray, toIntArray.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
        return copyOf;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: toLongArray-QwZRm1k  reason: not valid java name */
    private static final long[] m1320toLongArrayQwZRm1k(long[] toLongArray) {
        Intrinsics.checkNotNullParameter(toLongArray, "$this$toLongArray");
        long[] copyOf = Arrays.copyOf(toLongArray, toLongArray.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
        return copyOf;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: toShortArray-rL5Bavg  reason: not valid java name */
    private static final short[] m1321toShortArrayrL5Bavg(short[] toShortArray) {
        Intrinsics.checkNotNullParameter(toShortArray, "$this$toShortArray");
        short[] copyOf = Arrays.copyOf(toShortArray, toShortArray.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
        return copyOf;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: toTypedArray--ajY-9A  reason: not valid java name */
    public static final UInt[] m1322toTypedArrayajY9A(@NotNull int[] toTypedArray) {
        Intrinsics.checkNotNullParameter(toTypedArray, "$this$toTypedArray");
        int m341getSizeimpl = UIntArray.m341getSizeimpl(toTypedArray);
        UInt[] uIntArr = new UInt[m341getSizeimpl];
        for (int i2 = 0; i2 < m341getSizeimpl; i2++) {
            uIntArr[i2] = UInt.m275boximpl(UIntArray.m340getpVg5ArA(toTypedArray, i2));
        }
        return uIntArr;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: toTypedArray-GBYM_sE  reason: not valid java name */
    public static final UByte[] m1323toTypedArrayGBYM_sE(@NotNull byte[] toTypedArray) {
        Intrinsics.checkNotNullParameter(toTypedArray, "$this$toTypedArray");
        int m263getSizeimpl = UByteArray.m263getSizeimpl(toTypedArray);
        UByte[] uByteArr = new UByte[m263getSizeimpl];
        for (int i2 = 0; i2 < m263getSizeimpl; i2++) {
            uByteArr[i2] = UByte.m199boximpl(UByteArray.m262getw2LRezQ(toTypedArray, i2));
        }
        return uByteArr;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: toTypedArray-QwZRm1k  reason: not valid java name */
    public static final ULong[] m1324toTypedArrayQwZRm1k(@NotNull long[] toTypedArray) {
        Intrinsics.checkNotNullParameter(toTypedArray, "$this$toTypedArray");
        int m419getSizeimpl = ULongArray.m419getSizeimpl(toTypedArray);
        ULong[] uLongArr = new ULong[m419getSizeimpl];
        for (int i2 = 0; i2 < m419getSizeimpl; i2++) {
            uLongArr[i2] = ULong.m353boximpl(ULongArray.m418getsVKNKU(toTypedArray, i2));
        }
        return uLongArr;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: toTypedArray-rL5Bavg  reason: not valid java name */
    public static final UShort[] m1325toTypedArrayrL5Bavg(@NotNull short[] toTypedArray) {
        Intrinsics.checkNotNullParameter(toTypedArray, "$this$toTypedArray");
        int m523getSizeimpl = UShortArray.m523getSizeimpl(toTypedArray);
        UShort[] uShortArr = new UShort[m523getSizeimpl];
        for (int i2 = 0; i2 < m523getSizeimpl; i2++) {
            uShortArr[i2] = UShort.m459boximpl(UShortArray.m522getMh2AYeg(toTypedArray, i2));
        }
        return uShortArr;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final byte[] toUByteArray(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        byte[] copyOf = Arrays.copyOf(bArr, bArr.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
        return UByteArray.m257constructorimpl(copyOf);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    public static final byte[] toUByteArray(@NotNull UByte[] uByteArr) {
        Intrinsics.checkNotNullParameter(uByteArr, "<this>");
        int length = uByteArr.length;
        byte[] bArr = new byte[length];
        for (int i2 = 0; i2 < length; i2++) {
            bArr[i2] = uByteArr[i2].m254unboximpl();
        }
        return UByteArray.m257constructorimpl(bArr);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final int[] toUIntArray(int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        int[] copyOf = Arrays.copyOf(iArr, iArr.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
        return UIntArray.m335constructorimpl(copyOf);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    public static final int[] toUIntArray(@NotNull UInt[] uIntArr) {
        Intrinsics.checkNotNullParameter(uIntArr, "<this>");
        int length = uIntArr.length;
        int[] iArr = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = uIntArr[i2].m332unboximpl();
        }
        return UIntArray.m335constructorimpl(iArr);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final long[] toULongArray(long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        long[] copyOf = Arrays.copyOf(jArr, jArr.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
        return ULongArray.m413constructorimpl(copyOf);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    public static final long[] toULongArray(@NotNull ULong[] uLongArr) {
        Intrinsics.checkNotNullParameter(uLongArr, "<this>");
        int length = uLongArr.length;
        long[] jArr = new long[length];
        for (int i2 = 0; i2 < length; i2++) {
            jArr[i2] = uLongArr[i2].m410unboximpl();
        }
        return ULongArray.m413constructorimpl(jArr);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    public static final short[] toUShortArray(@NotNull UShort[] uShortArr) {
        Intrinsics.checkNotNullParameter(uShortArr, "<this>");
        int length = uShortArr.length;
        short[] sArr = new short[length];
        for (int i2 = 0; i2 < length; i2++) {
            sArr[i2] = uShortArr[i2].m514unboximpl();
        }
        return UShortArray.m517constructorimpl(sArr);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final short[] toUShortArray(short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        short[] copyOf = Arrays.copyOf(sArr, sArr.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
        return UShortArray.m517constructorimpl(copyOf);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: withIndex--ajY-9A  reason: not valid java name */
    public static final Iterable<IndexedValue<UInt>> m1326withIndexajY9A(@NotNull int[] withIndex) {
        Intrinsics.checkNotNullParameter(withIndex, "$this$withIndex");
        return new IndexingIterable(new UArraysKt___UArraysKt$withIndex$1(withIndex));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: withIndex-GBYM_sE  reason: not valid java name */
    public static final Iterable<IndexedValue<UByte>> m1327withIndexGBYM_sE(@NotNull byte[] withIndex) {
        Intrinsics.checkNotNullParameter(withIndex, "$this$withIndex");
        return new IndexingIterable(new UArraysKt___UArraysKt$withIndex$3(withIndex));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: withIndex-QwZRm1k  reason: not valid java name */
    public static final Iterable<IndexedValue<ULong>> m1328withIndexQwZRm1k(@NotNull long[] withIndex) {
        Intrinsics.checkNotNullParameter(withIndex, "$this$withIndex");
        return new IndexingIterable(new UArraysKt___UArraysKt$withIndex$2(withIndex));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: withIndex-rL5Bavg  reason: not valid java name */
    public static final Iterable<IndexedValue<UShort>> m1329withIndexrL5Bavg(@NotNull short[] withIndex) {
        Intrinsics.checkNotNullParameter(withIndex, "$this$withIndex");
        return new IndexingIterable(new UArraysKt___UArraysKt$withIndex$4(withIndex));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: zip-7znnbtw  reason: not valid java name */
    private static final <R, V> List<V> m1330zip7znnbtw(int[] zip, Iterable<? extends R> other, Function2<? super UInt, ? super R, ? extends V> transform) {
        int collectionSizeOrDefault;
        Intrinsics.checkNotNullParameter(zip, "$this$zip");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int m341getSizeimpl = UIntArray.m341getSizeimpl(zip);
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(other, 10);
        ArrayList arrayList = new ArrayList(Math.min(collectionSizeOrDefault, m341getSizeimpl));
        Iterator<? extends R> it = other.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            Object obj = (R) it.next();
            if (i2 >= m341getSizeimpl) {
                break;
            }
            arrayList.add(transform.invoke(UInt.m275boximpl(UIntArray.m340getpVg5ArA(zip, i2)), obj));
            i2++;
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: zip-8LME4QE  reason: not valid java name */
    private static final <R, V> List<V> m1331zip8LME4QE(long[] zip, R[] other, Function2<? super ULong, ? super R, ? extends V> transform) {
        Intrinsics.checkNotNullParameter(zip, "$this$zip");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int min = Math.min(ULongArray.m419getSizeimpl(zip), other.length);
        ArrayList arrayList = new ArrayList(min);
        for (int i2 = 0; i2 < min; i2++) {
            arrayList.add(transform.invoke(ULong.m353boximpl(ULongArray.m418getsVKNKU(zip, i2)), other[i2]));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: zip-C-E_24M  reason: not valid java name */
    public static final <R> List<Pair<UInt, R>> m1332zipCE_24M(@NotNull int[] zip, @NotNull R[] other) {
        Intrinsics.checkNotNullParameter(zip, "$this$zip");
        Intrinsics.checkNotNullParameter(other, "other");
        int min = Math.min(UIntArray.m341getSizeimpl(zip), other.length);
        ArrayList arrayList = new ArrayList(min);
        for (int i2 = 0; i2 < min; i2++) {
            int m340getpVg5ArA = UIntArray.m340getpVg5ArA(zip, i2);
            arrayList.add(TuplesKt.to(UInt.m275boximpl(m340getpVg5ArA), other[i2]));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: zip-F7u83W8  reason: not valid java name */
    public static final <R> List<Pair<ULong, R>> m1333zipF7u83W8(@NotNull long[] zip, @NotNull Iterable<? extends R> other) {
        int collectionSizeOrDefault;
        Intrinsics.checkNotNullParameter(zip, "$this$zip");
        Intrinsics.checkNotNullParameter(other, "other");
        int m419getSizeimpl = ULongArray.m419getSizeimpl(zip);
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(other, 10);
        ArrayList arrayList = new ArrayList(Math.min(collectionSizeOrDefault, m419getSizeimpl));
        int i2 = 0;
        for (R r2 : other) {
            if (i2 >= m419getSizeimpl) {
                break;
            }
            arrayList.add(TuplesKt.to(ULong.m353boximpl(ULongArray.m418getsVKNKU(zip, i2)), r2));
            i2++;
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: zip-HwE9HBo  reason: not valid java name */
    public static final <R> List<Pair<UInt, R>> m1334zipHwE9HBo(@NotNull int[] zip, @NotNull Iterable<? extends R> other) {
        int collectionSizeOrDefault;
        Intrinsics.checkNotNullParameter(zip, "$this$zip");
        Intrinsics.checkNotNullParameter(other, "other");
        int m341getSizeimpl = UIntArray.m341getSizeimpl(zip);
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(other, 10);
        ArrayList arrayList = new ArrayList(Math.min(collectionSizeOrDefault, m341getSizeimpl));
        int i2 = 0;
        for (R r2 : other) {
            if (i2 >= m341getSizeimpl) {
                break;
            }
            arrayList.add(TuplesKt.to(UInt.m275boximpl(UIntArray.m340getpVg5ArA(zip, i2)), r2));
            i2++;
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: zip-JAKpvQM  reason: not valid java name */
    private static final <V> List<V> m1335zipJAKpvQM(byte[] zip, byte[] other, Function2<? super UByte, ? super UByte, ? extends V> transform) {
        Intrinsics.checkNotNullParameter(zip, "$this$zip");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int min = Math.min(UByteArray.m263getSizeimpl(zip), UByteArray.m263getSizeimpl(other));
        ArrayList arrayList = new ArrayList(min);
        for (int i2 = 0; i2 < min; i2++) {
            arrayList.add(transform.invoke(UByte.m199boximpl(UByteArray.m262getw2LRezQ(zip, i2)), UByte.m199boximpl(UByteArray.m262getw2LRezQ(other, i2))));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: zip-JGPC0-M  reason: not valid java name */
    public static final <R> List<Pair<UShort, R>> m1336zipJGPC0M(@NotNull short[] zip, @NotNull Iterable<? extends R> other) {
        int collectionSizeOrDefault;
        Intrinsics.checkNotNullParameter(zip, "$this$zip");
        Intrinsics.checkNotNullParameter(other, "other");
        int m523getSizeimpl = UShortArray.m523getSizeimpl(zip);
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(other, 10);
        ArrayList arrayList = new ArrayList(Math.min(collectionSizeOrDefault, m523getSizeimpl));
        int i2 = 0;
        for (R r2 : other) {
            if (i2 >= m523getSizeimpl) {
                break;
            }
            arrayList.add(TuplesKt.to(UShort.m459boximpl(UShortArray.m522getMh2AYeg(zip, i2)), r2));
            i2++;
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: zip-JQknh5Q  reason: not valid java name */
    public static final <R> List<Pair<UByte, R>> m1337zipJQknh5Q(@NotNull byte[] zip, @NotNull Iterable<? extends R> other) {
        int collectionSizeOrDefault;
        Intrinsics.checkNotNullParameter(zip, "$this$zip");
        Intrinsics.checkNotNullParameter(other, "other");
        int m263getSizeimpl = UByteArray.m263getSizeimpl(zip);
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(other, 10);
        ArrayList arrayList = new ArrayList(Math.min(collectionSizeOrDefault, m263getSizeimpl));
        int i2 = 0;
        for (R r2 : other) {
            if (i2 >= m263getSizeimpl) {
                break;
            }
            arrayList.add(TuplesKt.to(UByte.m199boximpl(UByteArray.m262getw2LRezQ(zip, i2)), r2));
            i2++;
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: zip-L83TJbI  reason: not valid java name */
    private static final <V> List<V> m1338zipL83TJbI(int[] zip, int[] other, Function2<? super UInt, ? super UInt, ? extends V> transform) {
        Intrinsics.checkNotNullParameter(zip, "$this$zip");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int min = Math.min(UIntArray.m341getSizeimpl(zip), UIntArray.m341getSizeimpl(other));
        ArrayList arrayList = new ArrayList(min);
        for (int i2 = 0; i2 < min; i2++) {
            arrayList.add(transform.invoke(UInt.m275boximpl(UIntArray.m340getpVg5ArA(zip, i2)), UInt.m275boximpl(UIntArray.m340getpVg5ArA(other, i2))));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: zip-LuipOMY  reason: not valid java name */
    private static final <R, V> List<V> m1339zipLuipOMY(byte[] zip, R[] other, Function2<? super UByte, ? super R, ? extends V> transform) {
        Intrinsics.checkNotNullParameter(zip, "$this$zip");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int min = Math.min(UByteArray.m263getSizeimpl(zip), other.length);
        ArrayList arrayList = new ArrayList(min);
        for (int i2 = 0; i2 < min; i2++) {
            arrayList.add(transform.invoke(UByte.m199boximpl(UByteArray.m262getw2LRezQ(zip, i2)), other[i2]));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: zip-PabeH-Q  reason: not valid java name */
    private static final <V> List<V> m1340zipPabeHQ(long[] zip, long[] other, Function2<? super ULong, ? super ULong, ? extends V> transform) {
        Intrinsics.checkNotNullParameter(zip, "$this$zip");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int min = Math.min(ULongArray.m419getSizeimpl(zip), ULongArray.m419getSizeimpl(other));
        ArrayList arrayList = new ArrayList(min);
        for (int i2 = 0; i2 < min; i2++) {
            arrayList.add(transform.invoke(ULong.m353boximpl(ULongArray.m418getsVKNKU(zip, i2)), ULong.m353boximpl(ULongArray.m418getsVKNKU(other, i2))));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: zip-TUPTUsU  reason: not valid java name */
    private static final <R, V> List<V> m1341zipTUPTUsU(long[] zip, Iterable<? extends R> other, Function2<? super ULong, ? super R, ? extends V> transform) {
        int collectionSizeOrDefault;
        Intrinsics.checkNotNullParameter(zip, "$this$zip");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int m419getSizeimpl = ULongArray.m419getSizeimpl(zip);
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(other, 10);
        ArrayList arrayList = new ArrayList(Math.min(collectionSizeOrDefault, m419getSizeimpl));
        Iterator<? extends R> it = other.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            Object obj = (R) it.next();
            if (i2 >= m419getSizeimpl) {
                break;
            }
            arrayList.add(transform.invoke(ULong.m353boximpl(ULongArray.m418getsVKNKU(zip, i2)), obj));
            i2++;
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: zip-UCnP4_w  reason: not valid java name */
    private static final <R, V> List<V> m1342zipUCnP4_w(byte[] zip, Iterable<? extends R> other, Function2<? super UByte, ? super R, ? extends V> transform) {
        int collectionSizeOrDefault;
        Intrinsics.checkNotNullParameter(zip, "$this$zip");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int m263getSizeimpl = UByteArray.m263getSizeimpl(zip);
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(other, 10);
        ArrayList arrayList = new ArrayList(Math.min(collectionSizeOrDefault, m263getSizeimpl));
        Iterator<? extends R> it = other.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            Object obj = (R) it.next();
            if (i2 >= m263getSizeimpl) {
                break;
            }
            arrayList.add(transform.invoke(UByte.m199boximpl(UByteArray.m262getw2LRezQ(zip, i2)), obj));
            i2++;
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: zip-ZjwqOic  reason: not valid java name */
    private static final <R, V> List<V> m1343zipZjwqOic(int[] zip, R[] other, Function2<? super UInt, ? super R, ? extends V> transform) {
        Intrinsics.checkNotNullParameter(zip, "$this$zip");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int min = Math.min(UIntArray.m341getSizeimpl(zip), other.length);
        ArrayList arrayList = new ArrayList(min);
        for (int i2 = 0; i2 < min; i2++) {
            arrayList.add(transform.invoke(UInt.m275boximpl(UIntArray.m340getpVg5ArA(zip, i2)), other[i2]));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: zip-ctEhBpI  reason: not valid java name */
    public static final List<Pair<UInt, UInt>> m1344zipctEhBpI(@NotNull int[] zip, @NotNull int[] other) {
        Intrinsics.checkNotNullParameter(zip, "$this$zip");
        Intrinsics.checkNotNullParameter(other, "other");
        int min = Math.min(UIntArray.m341getSizeimpl(zip), UIntArray.m341getSizeimpl(other));
        ArrayList arrayList = new ArrayList(min);
        for (int i2 = 0; i2 < min; i2++) {
            arrayList.add(TuplesKt.to(UInt.m275boximpl(UIntArray.m340getpVg5ArA(zip, i2)), UInt.m275boximpl(UIntArray.m340getpVg5ArA(other, i2))));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: zip-ePBmRWY  reason: not valid java name */
    private static final <R, V> List<V> m1345zipePBmRWY(short[] zip, R[] other, Function2<? super UShort, ? super R, ? extends V> transform) {
        Intrinsics.checkNotNullParameter(zip, "$this$zip");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int min = Math.min(UShortArray.m523getSizeimpl(zip), other.length);
        ArrayList arrayList = new ArrayList(min);
        for (int i2 = 0; i2 < min; i2++) {
            arrayList.add(transform.invoke(UShort.m459boximpl(UShortArray.m522getMh2AYeg(zip, i2)), other[i2]));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: zip-f7H3mmw  reason: not valid java name */
    public static final <R> List<Pair<ULong, R>> m1346zipf7H3mmw(@NotNull long[] zip, @NotNull R[] other) {
        Intrinsics.checkNotNullParameter(zip, "$this$zip");
        Intrinsics.checkNotNullParameter(other, "other");
        int min = Math.min(ULongArray.m419getSizeimpl(zip), other.length);
        ArrayList arrayList = new ArrayList(min);
        for (int i2 = 0; i2 < min; i2++) {
            long m418getsVKNKU = ULongArray.m418getsVKNKU(zip, i2);
            arrayList.add(TuplesKt.to(ULong.m353boximpl(m418getsVKNKU), other[i2]));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: zip-gVVukQo  reason: not valid java name */
    private static final <V> List<V> m1347zipgVVukQo(short[] zip, short[] other, Function2<? super UShort, ? super UShort, ? extends V> transform) {
        Intrinsics.checkNotNullParameter(zip, "$this$zip");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int min = Math.min(UShortArray.m523getSizeimpl(zip), UShortArray.m523getSizeimpl(other));
        ArrayList arrayList = new ArrayList(min);
        for (int i2 = 0; i2 < min; i2++) {
            arrayList.add(transform.invoke(UShort.m459boximpl(UShortArray.m522getMh2AYeg(zip, i2)), UShort.m459boximpl(UShortArray.m522getMh2AYeg(other, i2))));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: zip-kBb4a-s  reason: not valid java name */
    private static final <R, V> List<V> m1348zipkBb4as(short[] zip, Iterable<? extends R> other, Function2<? super UShort, ? super R, ? extends V> transform) {
        int collectionSizeOrDefault;
        Intrinsics.checkNotNullParameter(zip, "$this$zip");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int m523getSizeimpl = UShortArray.m523getSizeimpl(zip);
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(other, 10);
        ArrayList arrayList = new ArrayList(Math.min(collectionSizeOrDefault, m523getSizeimpl));
        Iterator<? extends R> it = other.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            Object obj = (R) it.next();
            if (i2 >= m523getSizeimpl) {
                break;
            }
            arrayList.add(transform.invoke(UShort.m459boximpl(UShortArray.m522getMh2AYeg(zip, i2)), obj));
            i2++;
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: zip-kdPth3s  reason: not valid java name */
    public static final List<Pair<UByte, UByte>> m1349zipkdPth3s(@NotNull byte[] zip, @NotNull byte[] other) {
        Intrinsics.checkNotNullParameter(zip, "$this$zip");
        Intrinsics.checkNotNullParameter(other, "other");
        int min = Math.min(UByteArray.m263getSizeimpl(zip), UByteArray.m263getSizeimpl(other));
        ArrayList arrayList = new ArrayList(min);
        for (int i2 = 0; i2 < min; i2++) {
            arrayList.add(TuplesKt.to(UByte.m199boximpl(UByteArray.m262getw2LRezQ(zip, i2)), UByte.m199boximpl(UByteArray.m262getw2LRezQ(other, i2))));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: zip-mazbYpA  reason: not valid java name */
    public static final List<Pair<UShort, UShort>> m1350zipmazbYpA(@NotNull short[] zip, @NotNull short[] other) {
        Intrinsics.checkNotNullParameter(zip, "$this$zip");
        Intrinsics.checkNotNullParameter(other, "other");
        int min = Math.min(UShortArray.m523getSizeimpl(zip), UShortArray.m523getSizeimpl(other));
        ArrayList arrayList = new ArrayList(min);
        for (int i2 = 0; i2 < min; i2++) {
            arrayList.add(TuplesKt.to(UShort.m459boximpl(UShortArray.m522getMh2AYeg(zip, i2)), UShort.m459boximpl(UShortArray.m522getMh2AYeg(other, i2))));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: zip-nl983wc  reason: not valid java name */
    public static final <R> List<Pair<UByte, R>> m1351zipnl983wc(@NotNull byte[] zip, @NotNull R[] other) {
        Intrinsics.checkNotNullParameter(zip, "$this$zip");
        Intrinsics.checkNotNullParameter(other, "other");
        int min = Math.min(UByteArray.m263getSizeimpl(zip), other.length);
        ArrayList arrayList = new ArrayList(min);
        for (int i2 = 0; i2 < min; i2++) {
            byte m262getw2LRezQ = UByteArray.m262getw2LRezQ(zip, i2);
            arrayList.add(TuplesKt.to(UByte.m199boximpl(m262getw2LRezQ), other[i2]));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: zip-uaTIQ5s  reason: not valid java name */
    public static final <R> List<Pair<UShort, R>> m1352zipuaTIQ5s(@NotNull short[] zip, @NotNull R[] other) {
        Intrinsics.checkNotNullParameter(zip, "$this$zip");
        Intrinsics.checkNotNullParameter(other, "other");
        int min = Math.min(UShortArray.m523getSizeimpl(zip), other.length);
        ArrayList arrayList = new ArrayList(min);
        for (int i2 = 0; i2 < min; i2++) {
            short m522getMh2AYeg = UShortArray.m522getMh2AYeg(zip, i2);
            arrayList.add(TuplesKt.to(UShort.m459boximpl(m522getMh2AYeg), other[i2]));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: zip-us8wMrg  reason: not valid java name */
    public static final List<Pair<ULong, ULong>> m1353zipus8wMrg(@NotNull long[] zip, @NotNull long[] other) {
        Intrinsics.checkNotNullParameter(zip, "$this$zip");
        Intrinsics.checkNotNullParameter(other, "other");
        int min = Math.min(ULongArray.m419getSizeimpl(zip), ULongArray.m419getSizeimpl(other));
        ArrayList arrayList = new ArrayList(min);
        for (int i2 = 0; i2 < min; i2++) {
            arrayList.add(TuplesKt.to(ULong.m353boximpl(ULongArray.m418getsVKNKU(zip, i2)), ULong.m353boximpl(ULongArray.m418getsVKNKU(other, i2))));
        }
        return arrayList;
    }
}
