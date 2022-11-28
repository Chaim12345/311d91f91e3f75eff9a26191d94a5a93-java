package kotlin.collections;

import android.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.DeprecatedSinceKotlin;
import kotlin.ExperimentalStdlibApi;
import kotlin.ExperimentalUnsignedTypes;
import kotlin.OverloadResolutionByLambdaReturnType;
import kotlin.Pair;
import kotlin.ReplaceWith;
import kotlin.SinceKotlin;
import kotlin.TuplesKt;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.Unit;
import kotlin.WasExperimental;
import kotlin.comparisons.ComparisonsKt__ComparisonsKt;
import kotlin.comparisons.ComparisonsKt__ComparisonsKt$compareBy$2;
import kotlin.comparisons.ComparisonsKt__ComparisonsKt$compareByDescending$1;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.ArrayIteratorsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt___RangesKt;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt__SequencesKt;
import kotlin.text.StringsKt__AppendableKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public class ArraysKt___ArraysKt extends ArraysKt___ArraysJvmKt {
    public static final boolean all(@NotNull byte[] bArr, @NotNull Function1<? super Byte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (byte b2 : bArr) {
            if (!predicate.invoke(Byte.valueOf(b2)).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    public static final boolean all(@NotNull char[] cArr, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (char c2 : cArr) {
            if (!predicate.invoke(Character.valueOf(c2)).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    public static final boolean all(@NotNull double[] dArr, @NotNull Function1<? super Double, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (double d2 : dArr) {
            if (!predicate.invoke(Double.valueOf(d2)).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    public static final boolean all(@NotNull float[] fArr, @NotNull Function1<? super Float, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (float f2 : fArr) {
            if (!predicate.invoke(Float.valueOf(f2)).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    public static final boolean all(@NotNull int[] iArr, @NotNull Function1<? super Integer, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (int i2 : iArr) {
            if (!predicate.invoke(Integer.valueOf(i2)).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    public static final boolean all(@NotNull long[] jArr, @NotNull Function1<? super Long, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (long j2 : jArr) {
            if (!predicate.invoke(Long.valueOf(j2)).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    public static final <T> boolean all(@NotNull T[] tArr, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (T t2 : tArr) {
            if (!predicate.invoke(t2).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    public static final boolean all(@NotNull short[] sArr, @NotNull Function1<? super Short, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (short s2 : sArr) {
            if (!predicate.invoke(Short.valueOf(s2)).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    public static final boolean all(@NotNull boolean[] zArr, @NotNull Function1<? super Boolean, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (boolean z : zArr) {
            if (!predicate.invoke(Boolean.valueOf(z)).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    public static boolean any(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        return !(bArr.length == 0);
    }

    public static final boolean any(@NotNull byte[] bArr, @NotNull Function1<? super Byte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (byte b2 : bArr) {
            if (predicate.invoke(Byte.valueOf(b2)).booleanValue()) {
                return true;
            }
        }
        return false;
    }

    public static final boolean any(@NotNull char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        return !(cArr.length == 0);
    }

    public static final boolean any(@NotNull char[] cArr, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (char c2 : cArr) {
            if (predicate.invoke(Character.valueOf(c2)).booleanValue()) {
                return true;
            }
        }
        return false;
    }

    public static final boolean any(@NotNull double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        return !(dArr.length == 0);
    }

    public static final boolean any(@NotNull double[] dArr, @NotNull Function1<? super Double, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (double d2 : dArr) {
            if (predicate.invoke(Double.valueOf(d2)).booleanValue()) {
                return true;
            }
        }
        return false;
    }

    public static final boolean any(@NotNull float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        return !(fArr.length == 0);
    }

    public static final boolean any(@NotNull float[] fArr, @NotNull Function1<? super Float, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (float f2 : fArr) {
            if (predicate.invoke(Float.valueOf(f2)).booleanValue()) {
                return true;
            }
        }
        return false;
    }

    public static boolean any(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        return !(iArr.length == 0);
    }

    public static final boolean any(@NotNull int[] iArr, @NotNull Function1<? super Integer, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (int i2 : iArr) {
            if (predicate.invoke(Integer.valueOf(i2)).booleanValue()) {
                return true;
            }
        }
        return false;
    }

    public static boolean any(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        return !(jArr.length == 0);
    }

    public static final boolean any(@NotNull long[] jArr, @NotNull Function1<? super Long, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (long j2 : jArr) {
            if (predicate.invoke(Long.valueOf(j2)).booleanValue()) {
                return true;
            }
        }
        return false;
    }

    public static final <T> boolean any(@NotNull T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        return !(tArr.length == 0);
    }

    public static final <T> boolean any(@NotNull T[] tArr, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (T t2 : tArr) {
            if (predicate.invoke(t2).booleanValue()) {
                return true;
            }
        }
        return false;
    }

    public static boolean any(@NotNull short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        return !(sArr.length == 0);
    }

    public static final boolean any(@NotNull short[] sArr, @NotNull Function1<? super Short, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (short s2 : sArr) {
            if (predicate.invoke(Short.valueOf(s2)).booleanValue()) {
                return true;
            }
        }
        return false;
    }

    public static final boolean any(@NotNull boolean[] zArr) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        return !(zArr.length == 0);
    }

    public static final boolean any(@NotNull boolean[] zArr, @NotNull Function1<? super Boolean, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (boolean z : zArr) {
            if (predicate.invoke(Boolean.valueOf(z)).booleanValue()) {
                return true;
            }
        }
        return false;
    }

    @NotNull
    public static final Iterable<Byte> asIterable(@NotNull byte[] bArr) {
        List emptyList;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        if (bArr.length == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        return new ArraysKt___ArraysKt$asIterable$$inlined$Iterable$2(bArr);
    }

    @NotNull
    public static final Iterable<Character> asIterable(@NotNull char[] cArr) {
        List emptyList;
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        if (cArr.length == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        return new ArraysKt___ArraysKt$asIterable$$inlined$Iterable$9(cArr);
    }

    @NotNull
    public static final Iterable<Double> asIterable(@NotNull double[] dArr) {
        List emptyList;
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        if (dArr.length == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        return new ArraysKt___ArraysKt$asIterable$$inlined$Iterable$7(dArr);
    }

    @NotNull
    public static final Iterable<Float> asIterable(@NotNull float[] fArr) {
        List emptyList;
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        if (fArr.length == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        return new ArraysKt___ArraysKt$asIterable$$inlined$Iterable$6(fArr);
    }

    @NotNull
    public static final Iterable<Integer> asIterable(@NotNull int[] iArr) {
        List emptyList;
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        if (iArr.length == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        return new ArraysKt___ArraysKt$asIterable$$inlined$Iterable$4(iArr);
    }

    @NotNull
    public static final Iterable<Long> asIterable(@NotNull long[] jArr) {
        List emptyList;
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        if (jArr.length == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        return new ArraysKt___ArraysKt$asIterable$$inlined$Iterable$5(jArr);
    }

    @NotNull
    public static <T> Iterable<T> asIterable(@NotNull T[] tArr) {
        List emptyList;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        if (tArr.length == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        return new ArraysKt___ArraysKt$asIterable$$inlined$Iterable$1(tArr);
    }

    @NotNull
    public static final Iterable<Short> asIterable(@NotNull short[] sArr) {
        List emptyList;
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        if (sArr.length == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        return new ArraysKt___ArraysKt$asIterable$$inlined$Iterable$3(sArr);
    }

    @NotNull
    public static final Iterable<Boolean> asIterable(@NotNull boolean[] zArr) {
        List emptyList;
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        if (zArr.length == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        return new ArraysKt___ArraysKt$asIterable$$inlined$Iterable$8(zArr);
    }

    @NotNull
    public static final Sequence<Byte> asSequence(@NotNull final byte[] bArr) {
        Sequence<Byte> emptySequence;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        if (bArr.length == 0) {
            emptySequence = SequencesKt__SequencesKt.emptySequence();
            return emptySequence;
        }
        return new Sequence<Byte>() { // from class: kotlin.collections.ArraysKt___ArraysKt$asSequence$$inlined$Sequence$2
            @Override // kotlin.sequences.Sequence
            @NotNull
            public Iterator<Byte> iterator() {
                return ArrayIteratorsKt.iterator(bArr);
            }
        };
    }

    @NotNull
    public static final Sequence<Character> asSequence(@NotNull final char[] cArr) {
        Sequence<Character> emptySequence;
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        if (cArr.length == 0) {
            emptySequence = SequencesKt__SequencesKt.emptySequence();
            return emptySequence;
        }
        return new Sequence<Character>() { // from class: kotlin.collections.ArraysKt___ArraysKt$asSequence$$inlined$Sequence$9
            @Override // kotlin.sequences.Sequence
            @NotNull
            public Iterator<Character> iterator() {
                return ArrayIteratorsKt.iterator(cArr);
            }
        };
    }

    @NotNull
    public static final Sequence<Double> asSequence(@NotNull final double[] dArr) {
        Sequence<Double> emptySequence;
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        if (dArr.length == 0) {
            emptySequence = SequencesKt__SequencesKt.emptySequence();
            return emptySequence;
        }
        return new Sequence<Double>() { // from class: kotlin.collections.ArraysKt___ArraysKt$asSequence$$inlined$Sequence$7
            @Override // kotlin.sequences.Sequence
            @NotNull
            public Iterator<Double> iterator() {
                return ArrayIteratorsKt.iterator(dArr);
            }
        };
    }

    @NotNull
    public static final Sequence<Float> asSequence(@NotNull final float[] fArr) {
        Sequence<Float> emptySequence;
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        if (fArr.length == 0) {
            emptySequence = SequencesKt__SequencesKt.emptySequence();
            return emptySequence;
        }
        return new Sequence<Float>() { // from class: kotlin.collections.ArraysKt___ArraysKt$asSequence$$inlined$Sequence$6
            @Override // kotlin.sequences.Sequence
            @NotNull
            public Iterator<Float> iterator() {
                return ArrayIteratorsKt.iterator(fArr);
            }
        };
    }

    @NotNull
    public static final Sequence<Integer> asSequence(@NotNull final int[] iArr) {
        Sequence<Integer> emptySequence;
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        if (iArr.length == 0) {
            emptySequence = SequencesKt__SequencesKt.emptySequence();
            return emptySequence;
        }
        return new Sequence<Integer>() { // from class: kotlin.collections.ArraysKt___ArraysKt$asSequence$$inlined$Sequence$4
            @Override // kotlin.sequences.Sequence
            @NotNull
            public Iterator<Integer> iterator() {
                return ArrayIteratorsKt.iterator(iArr);
            }
        };
    }

    @NotNull
    public static final Sequence<Long> asSequence(@NotNull final long[] jArr) {
        Sequence<Long> emptySequence;
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        if (jArr.length == 0) {
            emptySequence = SequencesKt__SequencesKt.emptySequence();
            return emptySequence;
        }
        return new Sequence<Long>() { // from class: kotlin.collections.ArraysKt___ArraysKt$asSequence$$inlined$Sequence$5
            @Override // kotlin.sequences.Sequence
            @NotNull
            public Iterator<Long> iterator() {
                return ArrayIteratorsKt.iterator(jArr);
            }
        };
    }

    @NotNull
    public static <T> Sequence<T> asSequence(@NotNull final T[] tArr) {
        Sequence<T> emptySequence;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        if (tArr.length == 0) {
            emptySequence = SequencesKt__SequencesKt.emptySequence();
            return emptySequence;
        }
        return new Sequence<T>() { // from class: kotlin.collections.ArraysKt___ArraysKt$asSequence$$inlined$Sequence$1
            @Override // kotlin.sequences.Sequence
            @NotNull
            public Iterator<T> iterator() {
                return ArrayIteratorKt.iterator(tArr);
            }
        };
    }

    @NotNull
    public static final Sequence<Short> asSequence(@NotNull final short[] sArr) {
        Sequence<Short> emptySequence;
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        if (sArr.length == 0) {
            emptySequence = SequencesKt__SequencesKt.emptySequence();
            return emptySequence;
        }
        return new Sequence<Short>() { // from class: kotlin.collections.ArraysKt___ArraysKt$asSequence$$inlined$Sequence$3
            @Override // kotlin.sequences.Sequence
            @NotNull
            public Iterator<Short> iterator() {
                return ArrayIteratorsKt.iterator(sArr);
            }
        };
    }

    @NotNull
    public static final Sequence<Boolean> asSequence(@NotNull final boolean[] zArr) {
        Sequence<Boolean> emptySequence;
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        if (zArr.length == 0) {
            emptySequence = SequencesKt__SequencesKt.emptySequence();
            return emptySequence;
        }
        return new Sequence<Boolean>() { // from class: kotlin.collections.ArraysKt___ArraysKt$asSequence$$inlined$Sequence$8
            @Override // kotlin.sequences.Sequence
            @NotNull
            public Iterator<Boolean> iterator() {
                return ArrayIteratorsKt.iterator(zArr);
            }
        };
    }

    @NotNull
    public static final <K, V> Map<K, V> associate(@NotNull byte[] bArr, @NotNull Function1<? super Byte, ? extends Pair<? extends K, ? extends V>> transform) {
        int mapCapacity;
        int coerceAtLeast;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(bArr.length);
        coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(mapCapacity, 16);
        LinkedHashMap linkedHashMap = new LinkedHashMap(coerceAtLeast);
        for (byte b2 : bArr) {
            Pair<? extends K, ? extends V> invoke = transform.invoke(Byte.valueOf(b2));
            linkedHashMap.put(invoke.getFirst(), invoke.getSecond());
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K, V> Map<K, V> associate(@NotNull char[] cArr, @NotNull Function1<? super Character, ? extends Pair<? extends K, ? extends V>> transform) {
        int mapCapacity;
        int coerceAtLeast;
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(cArr.length);
        coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(mapCapacity, 16);
        LinkedHashMap linkedHashMap = new LinkedHashMap(coerceAtLeast);
        for (char c2 : cArr) {
            Pair<? extends K, ? extends V> invoke = transform.invoke(Character.valueOf(c2));
            linkedHashMap.put(invoke.getFirst(), invoke.getSecond());
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K, V> Map<K, V> associate(@NotNull double[] dArr, @NotNull Function1<? super Double, ? extends Pair<? extends K, ? extends V>> transform) {
        int mapCapacity;
        int coerceAtLeast;
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(dArr.length);
        coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(mapCapacity, 16);
        LinkedHashMap linkedHashMap = new LinkedHashMap(coerceAtLeast);
        for (double d2 : dArr) {
            Pair<? extends K, ? extends V> invoke = transform.invoke(Double.valueOf(d2));
            linkedHashMap.put(invoke.getFirst(), invoke.getSecond());
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K, V> Map<K, V> associate(@NotNull float[] fArr, @NotNull Function1<? super Float, ? extends Pair<? extends K, ? extends V>> transform) {
        int mapCapacity;
        int coerceAtLeast;
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(fArr.length);
        coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(mapCapacity, 16);
        LinkedHashMap linkedHashMap = new LinkedHashMap(coerceAtLeast);
        for (float f2 : fArr) {
            Pair<? extends K, ? extends V> invoke = transform.invoke(Float.valueOf(f2));
            linkedHashMap.put(invoke.getFirst(), invoke.getSecond());
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K, V> Map<K, V> associate(@NotNull int[] iArr, @NotNull Function1<? super Integer, ? extends Pair<? extends K, ? extends V>> transform) {
        int mapCapacity;
        int coerceAtLeast;
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(iArr.length);
        coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(mapCapacity, 16);
        LinkedHashMap linkedHashMap = new LinkedHashMap(coerceAtLeast);
        for (int i2 : iArr) {
            Pair<? extends K, ? extends V> invoke = transform.invoke(Integer.valueOf(i2));
            linkedHashMap.put(invoke.getFirst(), invoke.getSecond());
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K, V> Map<K, V> associate(@NotNull long[] jArr, @NotNull Function1<? super Long, ? extends Pair<? extends K, ? extends V>> transform) {
        int mapCapacity;
        int coerceAtLeast;
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(jArr.length);
        coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(mapCapacity, 16);
        LinkedHashMap linkedHashMap = new LinkedHashMap(coerceAtLeast);
        for (long j2 : jArr) {
            Pair<? extends K, ? extends V> invoke = transform.invoke(Long.valueOf(j2));
            linkedHashMap.put(invoke.getFirst(), invoke.getSecond());
        }
        return linkedHashMap;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public static final <T, K, V> Map<K, V> associate(@NotNull T[] tArr, @NotNull Function1<? super T, ? extends Pair<? extends K, ? extends V>> transform) {
        int mapCapacity;
        int coerceAtLeast;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(tArr.length);
        coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(mapCapacity, 16);
        LinkedHashMap linkedHashMap = new LinkedHashMap(coerceAtLeast);
        for (R.animator animatorVar : tArr) {
            Pair<? extends K, ? extends V> invoke = transform.invoke(animatorVar);
            linkedHashMap.put(invoke.getFirst(), invoke.getSecond());
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K, V> Map<K, V> associate(@NotNull short[] sArr, @NotNull Function1<? super Short, ? extends Pair<? extends K, ? extends V>> transform) {
        int mapCapacity;
        int coerceAtLeast;
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(sArr.length);
        coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(mapCapacity, 16);
        LinkedHashMap linkedHashMap = new LinkedHashMap(coerceAtLeast);
        for (short s2 : sArr) {
            Pair<? extends K, ? extends V> invoke = transform.invoke(Short.valueOf(s2));
            linkedHashMap.put(invoke.getFirst(), invoke.getSecond());
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K, V> Map<K, V> associate(@NotNull boolean[] zArr, @NotNull Function1<? super Boolean, ? extends Pair<? extends K, ? extends V>> transform) {
        int mapCapacity;
        int coerceAtLeast;
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(zArr.length);
        coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(mapCapacity, 16);
        LinkedHashMap linkedHashMap = new LinkedHashMap(coerceAtLeast);
        for (boolean z : zArr) {
            Pair<? extends K, ? extends V> invoke = transform.invoke(Boolean.valueOf(z));
            linkedHashMap.put(invoke.getFirst(), invoke.getSecond());
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K> Map<K, Byte> associateBy(@NotNull byte[] bArr, @NotNull Function1<? super Byte, ? extends K> keySelector) {
        int mapCapacity;
        int coerceAtLeast;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(bArr.length);
        coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(mapCapacity, 16);
        LinkedHashMap linkedHashMap = new LinkedHashMap(coerceAtLeast);
        for (byte b2 : bArr) {
            linkedHashMap.put(keySelector.invoke(Byte.valueOf(b2)), Byte.valueOf(b2));
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K, V> Map<K, V> associateBy(@NotNull byte[] bArr, @NotNull Function1<? super Byte, ? extends K> keySelector, @NotNull Function1<? super Byte, ? extends V> valueTransform) {
        int mapCapacity;
        int coerceAtLeast;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(bArr.length);
        coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(mapCapacity, 16);
        LinkedHashMap linkedHashMap = new LinkedHashMap(coerceAtLeast);
        for (byte b2 : bArr) {
            linkedHashMap.put(keySelector.invoke(Byte.valueOf(b2)), valueTransform.invoke(Byte.valueOf(b2)));
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K> Map<K, Character> associateBy(@NotNull char[] cArr, @NotNull Function1<? super Character, ? extends K> keySelector) {
        int mapCapacity;
        int coerceAtLeast;
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(cArr.length);
        coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(mapCapacity, 16);
        LinkedHashMap linkedHashMap = new LinkedHashMap(coerceAtLeast);
        for (char c2 : cArr) {
            linkedHashMap.put(keySelector.invoke(Character.valueOf(c2)), Character.valueOf(c2));
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K, V> Map<K, V> associateBy(@NotNull char[] cArr, @NotNull Function1<? super Character, ? extends K> keySelector, @NotNull Function1<? super Character, ? extends V> valueTransform) {
        int mapCapacity;
        int coerceAtLeast;
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(cArr.length);
        coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(mapCapacity, 16);
        LinkedHashMap linkedHashMap = new LinkedHashMap(coerceAtLeast);
        for (char c2 : cArr) {
            linkedHashMap.put(keySelector.invoke(Character.valueOf(c2)), valueTransform.invoke(Character.valueOf(c2)));
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K> Map<K, Double> associateBy(@NotNull double[] dArr, @NotNull Function1<? super Double, ? extends K> keySelector) {
        int mapCapacity;
        int coerceAtLeast;
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(dArr.length);
        coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(mapCapacity, 16);
        LinkedHashMap linkedHashMap = new LinkedHashMap(coerceAtLeast);
        for (double d2 : dArr) {
            linkedHashMap.put(keySelector.invoke(Double.valueOf(d2)), Double.valueOf(d2));
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K, V> Map<K, V> associateBy(@NotNull double[] dArr, @NotNull Function1<? super Double, ? extends K> keySelector, @NotNull Function1<? super Double, ? extends V> valueTransform) {
        int mapCapacity;
        int coerceAtLeast;
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(dArr.length);
        coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(mapCapacity, 16);
        LinkedHashMap linkedHashMap = new LinkedHashMap(coerceAtLeast);
        for (double d2 : dArr) {
            linkedHashMap.put(keySelector.invoke(Double.valueOf(d2)), valueTransform.invoke(Double.valueOf(d2)));
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K> Map<K, Float> associateBy(@NotNull float[] fArr, @NotNull Function1<? super Float, ? extends K> keySelector) {
        int mapCapacity;
        int coerceAtLeast;
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(fArr.length);
        coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(mapCapacity, 16);
        LinkedHashMap linkedHashMap = new LinkedHashMap(coerceAtLeast);
        for (float f2 : fArr) {
            linkedHashMap.put(keySelector.invoke(Float.valueOf(f2)), Float.valueOf(f2));
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K, V> Map<K, V> associateBy(@NotNull float[] fArr, @NotNull Function1<? super Float, ? extends K> keySelector, @NotNull Function1<? super Float, ? extends V> valueTransform) {
        int mapCapacity;
        int coerceAtLeast;
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(fArr.length);
        coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(mapCapacity, 16);
        LinkedHashMap linkedHashMap = new LinkedHashMap(coerceAtLeast);
        for (float f2 : fArr) {
            linkedHashMap.put(keySelector.invoke(Float.valueOf(f2)), valueTransform.invoke(Float.valueOf(f2)));
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K> Map<K, Integer> associateBy(@NotNull int[] iArr, @NotNull Function1<? super Integer, ? extends K> keySelector) {
        int mapCapacity;
        int coerceAtLeast;
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(iArr.length);
        coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(mapCapacity, 16);
        LinkedHashMap linkedHashMap = new LinkedHashMap(coerceAtLeast);
        for (int i2 : iArr) {
            linkedHashMap.put(keySelector.invoke(Integer.valueOf(i2)), Integer.valueOf(i2));
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K, V> Map<K, V> associateBy(@NotNull int[] iArr, @NotNull Function1<? super Integer, ? extends K> keySelector, @NotNull Function1<? super Integer, ? extends V> valueTransform) {
        int mapCapacity;
        int coerceAtLeast;
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(iArr.length);
        coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(mapCapacity, 16);
        LinkedHashMap linkedHashMap = new LinkedHashMap(coerceAtLeast);
        for (int i2 : iArr) {
            linkedHashMap.put(keySelector.invoke(Integer.valueOf(i2)), valueTransform.invoke(Integer.valueOf(i2)));
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K> Map<K, Long> associateBy(@NotNull long[] jArr, @NotNull Function1<? super Long, ? extends K> keySelector) {
        int mapCapacity;
        int coerceAtLeast;
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(jArr.length);
        coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(mapCapacity, 16);
        LinkedHashMap linkedHashMap = new LinkedHashMap(coerceAtLeast);
        for (long j2 : jArr) {
            linkedHashMap.put(keySelector.invoke(Long.valueOf(j2)), Long.valueOf(j2));
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K, V> Map<K, V> associateBy(@NotNull long[] jArr, @NotNull Function1<? super Long, ? extends K> keySelector, @NotNull Function1<? super Long, ? extends V> valueTransform) {
        int mapCapacity;
        int coerceAtLeast;
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(jArr.length);
        coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(mapCapacity, 16);
        LinkedHashMap linkedHashMap = new LinkedHashMap(coerceAtLeast);
        for (long j2 : jArr) {
            linkedHashMap.put(keySelector.invoke(Long.valueOf(j2)), valueTransform.invoke(Long.valueOf(j2)));
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <T, K> Map<K, T> associateBy(@NotNull T[] tArr, @NotNull Function1<? super T, ? extends K> keySelector) {
        int mapCapacity;
        int coerceAtLeast;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(tArr.length);
        coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(mapCapacity, 16);
        LinkedHashMap linkedHashMap = new LinkedHashMap(coerceAtLeast);
        for (T t2 : tArr) {
            linkedHashMap.put(keySelector.invoke(t2), t2);
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <T, K, V> Map<K, V> associateBy(@NotNull T[] tArr, @NotNull Function1<? super T, ? extends K> keySelector, @NotNull Function1<? super T, ? extends V> valueTransform) {
        int mapCapacity;
        int coerceAtLeast;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(tArr.length);
        coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(mapCapacity, 16);
        LinkedHashMap linkedHashMap = new LinkedHashMap(coerceAtLeast);
        for (T t2 : tArr) {
            linkedHashMap.put(keySelector.invoke(t2), valueTransform.invoke(t2));
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K> Map<K, Short> associateBy(@NotNull short[] sArr, @NotNull Function1<? super Short, ? extends K> keySelector) {
        int mapCapacity;
        int coerceAtLeast;
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(sArr.length);
        coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(mapCapacity, 16);
        LinkedHashMap linkedHashMap = new LinkedHashMap(coerceAtLeast);
        for (short s2 : sArr) {
            linkedHashMap.put(keySelector.invoke(Short.valueOf(s2)), Short.valueOf(s2));
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K, V> Map<K, V> associateBy(@NotNull short[] sArr, @NotNull Function1<? super Short, ? extends K> keySelector, @NotNull Function1<? super Short, ? extends V> valueTransform) {
        int mapCapacity;
        int coerceAtLeast;
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(sArr.length);
        coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(mapCapacity, 16);
        LinkedHashMap linkedHashMap = new LinkedHashMap(coerceAtLeast);
        for (short s2 : sArr) {
            linkedHashMap.put(keySelector.invoke(Short.valueOf(s2)), valueTransform.invoke(Short.valueOf(s2)));
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K> Map<K, Boolean> associateBy(@NotNull boolean[] zArr, @NotNull Function1<? super Boolean, ? extends K> keySelector) {
        int mapCapacity;
        int coerceAtLeast;
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(zArr.length);
        coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(mapCapacity, 16);
        LinkedHashMap linkedHashMap = new LinkedHashMap(coerceAtLeast);
        for (boolean z : zArr) {
            linkedHashMap.put(keySelector.invoke(Boolean.valueOf(z)), Boolean.valueOf(z));
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K, V> Map<K, V> associateBy(@NotNull boolean[] zArr, @NotNull Function1<? super Boolean, ? extends K> keySelector, @NotNull Function1<? super Boolean, ? extends V> valueTransform) {
        int mapCapacity;
        int coerceAtLeast;
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(zArr.length);
        coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(mapCapacity, 16);
        LinkedHashMap linkedHashMap = new LinkedHashMap(coerceAtLeast);
        for (boolean z : zArr) {
            linkedHashMap.put(keySelector.invoke(Boolean.valueOf(z)), valueTransform.invoke(Boolean.valueOf(z)));
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K, M extends Map<? super K, ? super Byte>> M associateByTo(@NotNull byte[] bArr, @NotNull M destination, @NotNull Function1<? super Byte, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        for (byte b2 : bArr) {
            destination.put(keySelector.invoke(Byte.valueOf(b2)), Byte.valueOf(b2));
        }
        return destination;
    }

    @NotNull
    public static final <K, V, M extends Map<? super K, ? super V>> M associateByTo(@NotNull byte[] bArr, @NotNull M destination, @NotNull Function1<? super Byte, ? extends K> keySelector, @NotNull Function1<? super Byte, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        for (byte b2 : bArr) {
            destination.put(keySelector.invoke(Byte.valueOf(b2)), valueTransform.invoke(Byte.valueOf(b2)));
        }
        return destination;
    }

    @NotNull
    public static final <K, M extends Map<? super K, ? super Character>> M associateByTo(@NotNull char[] cArr, @NotNull M destination, @NotNull Function1<? super Character, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        for (char c2 : cArr) {
            destination.put(keySelector.invoke(Character.valueOf(c2)), Character.valueOf(c2));
        }
        return destination;
    }

    @NotNull
    public static final <K, V, M extends Map<? super K, ? super V>> M associateByTo(@NotNull char[] cArr, @NotNull M destination, @NotNull Function1<? super Character, ? extends K> keySelector, @NotNull Function1<? super Character, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        for (char c2 : cArr) {
            destination.put(keySelector.invoke(Character.valueOf(c2)), valueTransform.invoke(Character.valueOf(c2)));
        }
        return destination;
    }

    @NotNull
    public static final <K, M extends Map<? super K, ? super Double>> M associateByTo(@NotNull double[] dArr, @NotNull M destination, @NotNull Function1<? super Double, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        for (double d2 : dArr) {
            destination.put(keySelector.invoke(Double.valueOf(d2)), Double.valueOf(d2));
        }
        return destination;
    }

    @NotNull
    public static final <K, V, M extends Map<? super K, ? super V>> M associateByTo(@NotNull double[] dArr, @NotNull M destination, @NotNull Function1<? super Double, ? extends K> keySelector, @NotNull Function1<? super Double, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        for (double d2 : dArr) {
            destination.put(keySelector.invoke(Double.valueOf(d2)), valueTransform.invoke(Double.valueOf(d2)));
        }
        return destination;
    }

    @NotNull
    public static final <K, M extends Map<? super K, ? super Float>> M associateByTo(@NotNull float[] fArr, @NotNull M destination, @NotNull Function1<? super Float, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        for (float f2 : fArr) {
            destination.put(keySelector.invoke(Float.valueOf(f2)), Float.valueOf(f2));
        }
        return destination;
    }

    @NotNull
    public static final <K, V, M extends Map<? super K, ? super V>> M associateByTo(@NotNull float[] fArr, @NotNull M destination, @NotNull Function1<? super Float, ? extends K> keySelector, @NotNull Function1<? super Float, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        for (float f2 : fArr) {
            destination.put(keySelector.invoke(Float.valueOf(f2)), valueTransform.invoke(Float.valueOf(f2)));
        }
        return destination;
    }

    @NotNull
    public static final <K, M extends Map<? super K, ? super Integer>> M associateByTo(@NotNull int[] iArr, @NotNull M destination, @NotNull Function1<? super Integer, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        for (int i2 : iArr) {
            destination.put(keySelector.invoke(Integer.valueOf(i2)), Integer.valueOf(i2));
        }
        return destination;
    }

    @NotNull
    public static final <K, V, M extends Map<? super K, ? super V>> M associateByTo(@NotNull int[] iArr, @NotNull M destination, @NotNull Function1<? super Integer, ? extends K> keySelector, @NotNull Function1<? super Integer, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        for (int i2 : iArr) {
            destination.put(keySelector.invoke(Integer.valueOf(i2)), valueTransform.invoke(Integer.valueOf(i2)));
        }
        return destination;
    }

    @NotNull
    public static final <K, M extends Map<? super K, ? super Long>> M associateByTo(@NotNull long[] jArr, @NotNull M destination, @NotNull Function1<? super Long, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        for (long j2 : jArr) {
            destination.put(keySelector.invoke(Long.valueOf(j2)), Long.valueOf(j2));
        }
        return destination;
    }

    @NotNull
    public static final <K, V, M extends Map<? super K, ? super V>> M associateByTo(@NotNull long[] jArr, @NotNull M destination, @NotNull Function1<? super Long, ? extends K> keySelector, @NotNull Function1<? super Long, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        for (long j2 : jArr) {
            destination.put(keySelector.invoke(Long.valueOf(j2)), valueTransform.invoke(Long.valueOf(j2)));
        }
        return destination;
    }

    @NotNull
    public static final <T, K, M extends Map<? super K, ? super T>> M associateByTo(@NotNull T[] tArr, @NotNull M destination, @NotNull Function1<? super T, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        for (T t2 : tArr) {
            destination.put(keySelector.invoke(t2), t2);
        }
        return destination;
    }

    @NotNull
    public static final <T, K, V, M extends Map<? super K, ? super V>> M associateByTo(@NotNull T[] tArr, @NotNull M destination, @NotNull Function1<? super T, ? extends K> keySelector, @NotNull Function1<? super T, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        for (T t2 : tArr) {
            destination.put(keySelector.invoke(t2), valueTransform.invoke(t2));
        }
        return destination;
    }

    @NotNull
    public static final <K, M extends Map<? super K, ? super Short>> M associateByTo(@NotNull short[] sArr, @NotNull M destination, @NotNull Function1<? super Short, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        for (short s2 : sArr) {
            destination.put(keySelector.invoke(Short.valueOf(s2)), Short.valueOf(s2));
        }
        return destination;
    }

    @NotNull
    public static final <K, V, M extends Map<? super K, ? super V>> M associateByTo(@NotNull short[] sArr, @NotNull M destination, @NotNull Function1<? super Short, ? extends K> keySelector, @NotNull Function1<? super Short, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        for (short s2 : sArr) {
            destination.put(keySelector.invoke(Short.valueOf(s2)), valueTransform.invoke(Short.valueOf(s2)));
        }
        return destination;
    }

    @NotNull
    public static final <K, M extends Map<? super K, ? super Boolean>> M associateByTo(@NotNull boolean[] zArr, @NotNull M destination, @NotNull Function1<? super Boolean, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        for (boolean z : zArr) {
            destination.put(keySelector.invoke(Boolean.valueOf(z)), Boolean.valueOf(z));
        }
        return destination;
    }

    @NotNull
    public static final <K, V, M extends Map<? super K, ? super V>> M associateByTo(@NotNull boolean[] zArr, @NotNull M destination, @NotNull Function1<? super Boolean, ? extends K> keySelector, @NotNull Function1<? super Boolean, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        for (boolean z : zArr) {
            destination.put(keySelector.invoke(Boolean.valueOf(z)), valueTransform.invoke(Boolean.valueOf(z)));
        }
        return destination;
    }

    @NotNull
    public static final <K, V, M extends Map<? super K, ? super V>> M associateTo(@NotNull byte[] bArr, @NotNull M destination, @NotNull Function1<? super Byte, ? extends Pair<? extends K, ? extends V>> transform) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        for (byte b2 : bArr) {
            Pair<? extends K, ? extends V> invoke = transform.invoke(Byte.valueOf(b2));
            destination.put(invoke.getFirst(), invoke.getSecond());
        }
        return destination;
    }

    @NotNull
    public static final <K, V, M extends Map<? super K, ? super V>> M associateTo(@NotNull char[] cArr, @NotNull M destination, @NotNull Function1<? super Character, ? extends Pair<? extends K, ? extends V>> transform) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        for (char c2 : cArr) {
            Pair<? extends K, ? extends V> invoke = transform.invoke(Character.valueOf(c2));
            destination.put(invoke.getFirst(), invoke.getSecond());
        }
        return destination;
    }

    @NotNull
    public static final <K, V, M extends Map<? super K, ? super V>> M associateTo(@NotNull double[] dArr, @NotNull M destination, @NotNull Function1<? super Double, ? extends Pair<? extends K, ? extends V>> transform) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        for (double d2 : dArr) {
            Pair<? extends K, ? extends V> invoke = transform.invoke(Double.valueOf(d2));
            destination.put(invoke.getFirst(), invoke.getSecond());
        }
        return destination;
    }

    @NotNull
    public static final <K, V, M extends Map<? super K, ? super V>> M associateTo(@NotNull float[] fArr, @NotNull M destination, @NotNull Function1<? super Float, ? extends Pair<? extends K, ? extends V>> transform) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        for (float f2 : fArr) {
            Pair<? extends K, ? extends V> invoke = transform.invoke(Float.valueOf(f2));
            destination.put(invoke.getFirst(), invoke.getSecond());
        }
        return destination;
    }

    @NotNull
    public static final <K, V, M extends Map<? super K, ? super V>> M associateTo(@NotNull int[] iArr, @NotNull M destination, @NotNull Function1<? super Integer, ? extends Pair<? extends K, ? extends V>> transform) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        for (int i2 : iArr) {
            Pair<? extends K, ? extends V> invoke = transform.invoke(Integer.valueOf(i2));
            destination.put(invoke.getFirst(), invoke.getSecond());
        }
        return destination;
    }

    @NotNull
    public static final <K, V, M extends Map<? super K, ? super V>> M associateTo(@NotNull long[] jArr, @NotNull M destination, @NotNull Function1<? super Long, ? extends Pair<? extends K, ? extends V>> transform) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        for (long j2 : jArr) {
            Pair<? extends K, ? extends V> invoke = transform.invoke(Long.valueOf(j2));
            destination.put(invoke.getFirst(), invoke.getSecond());
        }
        return destination;
    }

    @NotNull
    public static final <T, K, V, M extends Map<? super K, ? super V>> M associateTo(@NotNull T[] tArr, @NotNull M destination, @NotNull Function1<? super T, ? extends Pair<? extends K, ? extends V>> transform) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        for (T t2 : tArr) {
            Pair<? extends K, ? extends V> invoke = transform.invoke(t2);
            destination.put(invoke.getFirst(), invoke.getSecond());
        }
        return destination;
    }

    @NotNull
    public static final <K, V, M extends Map<? super K, ? super V>> M associateTo(@NotNull short[] sArr, @NotNull M destination, @NotNull Function1<? super Short, ? extends Pair<? extends K, ? extends V>> transform) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        for (short s2 : sArr) {
            Pair<? extends K, ? extends V> invoke = transform.invoke(Short.valueOf(s2));
            destination.put(invoke.getFirst(), invoke.getSecond());
        }
        return destination;
    }

    @NotNull
    public static final <K, V, M extends Map<? super K, ? super V>> M associateTo(@NotNull boolean[] zArr, @NotNull M destination, @NotNull Function1<? super Boolean, ? extends Pair<? extends K, ? extends V>> transform) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        for (boolean z : zArr) {
            Pair<? extends K, ? extends V> invoke = transform.invoke(Boolean.valueOf(z));
            destination.put(invoke.getFirst(), invoke.getSecond());
        }
        return destination;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final <V> Map<Byte, V> associateWith(byte[] bArr, Function1<? super Byte, ? extends V> valueSelector) {
        int mapCapacity;
        int coerceAtLeast;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(valueSelector, "valueSelector");
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(bArr.length);
        coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(mapCapacity, 16);
        LinkedHashMap linkedHashMap = new LinkedHashMap(coerceAtLeast);
        for (byte b2 : bArr) {
            linkedHashMap.put(Byte.valueOf(b2), valueSelector.invoke(Byte.valueOf(b2)));
        }
        return linkedHashMap;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final <V> Map<Character, V> associateWith(char[] cArr, Function1<? super Character, ? extends V> valueSelector) {
        int coerceAtMost;
        int mapCapacity;
        int coerceAtLeast;
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(valueSelector, "valueSelector");
        coerceAtMost = RangesKt___RangesKt.coerceAtMost(cArr.length, 128);
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(coerceAtMost);
        coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(mapCapacity, 16);
        LinkedHashMap linkedHashMap = new LinkedHashMap(coerceAtLeast);
        for (char c2 : cArr) {
            linkedHashMap.put(Character.valueOf(c2), valueSelector.invoke(Character.valueOf(c2)));
        }
        return linkedHashMap;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final <V> Map<Double, V> associateWith(double[] dArr, Function1<? super Double, ? extends V> valueSelector) {
        int mapCapacity;
        int coerceAtLeast;
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(valueSelector, "valueSelector");
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(dArr.length);
        coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(mapCapacity, 16);
        LinkedHashMap linkedHashMap = new LinkedHashMap(coerceAtLeast);
        for (double d2 : dArr) {
            linkedHashMap.put(Double.valueOf(d2), valueSelector.invoke(Double.valueOf(d2)));
        }
        return linkedHashMap;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final <V> Map<Float, V> associateWith(float[] fArr, Function1<? super Float, ? extends V> valueSelector) {
        int mapCapacity;
        int coerceAtLeast;
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(valueSelector, "valueSelector");
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(fArr.length);
        coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(mapCapacity, 16);
        LinkedHashMap linkedHashMap = new LinkedHashMap(coerceAtLeast);
        for (float f2 : fArr) {
            linkedHashMap.put(Float.valueOf(f2), valueSelector.invoke(Float.valueOf(f2)));
        }
        return linkedHashMap;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final <V> Map<Integer, V> associateWith(int[] iArr, Function1<? super Integer, ? extends V> valueSelector) {
        int mapCapacity;
        int coerceAtLeast;
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(valueSelector, "valueSelector");
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(iArr.length);
        coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(mapCapacity, 16);
        LinkedHashMap linkedHashMap = new LinkedHashMap(coerceAtLeast);
        for (int i2 : iArr) {
            linkedHashMap.put(Integer.valueOf(i2), valueSelector.invoke(Integer.valueOf(i2)));
        }
        return linkedHashMap;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final <V> Map<Long, V> associateWith(long[] jArr, Function1<? super Long, ? extends V> valueSelector) {
        int mapCapacity;
        int coerceAtLeast;
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(valueSelector, "valueSelector");
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(jArr.length);
        coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(mapCapacity, 16);
        LinkedHashMap linkedHashMap = new LinkedHashMap(coerceAtLeast);
        for (long j2 : jArr) {
            linkedHashMap.put(Long.valueOf(j2), valueSelector.invoke(Long.valueOf(j2)));
        }
        return linkedHashMap;
    }

    @SinceKotlin(version = "1.4")
    @NotNull
    public static final <K, V> Map<K, V> associateWith(@NotNull K[] kArr, @NotNull Function1<? super K, ? extends V> valueSelector) {
        int mapCapacity;
        int coerceAtLeast;
        Intrinsics.checkNotNullParameter(kArr, "<this>");
        Intrinsics.checkNotNullParameter(valueSelector, "valueSelector");
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(kArr.length);
        coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(mapCapacity, 16);
        LinkedHashMap linkedHashMap = new LinkedHashMap(coerceAtLeast);
        for (K k2 : kArr) {
            linkedHashMap.put(k2, valueSelector.invoke(k2));
        }
        return linkedHashMap;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final <V> Map<Short, V> associateWith(short[] sArr, Function1<? super Short, ? extends V> valueSelector) {
        int mapCapacity;
        int coerceAtLeast;
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(valueSelector, "valueSelector");
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(sArr.length);
        coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(mapCapacity, 16);
        LinkedHashMap linkedHashMap = new LinkedHashMap(coerceAtLeast);
        for (short s2 : sArr) {
            linkedHashMap.put(Short.valueOf(s2), valueSelector.invoke(Short.valueOf(s2)));
        }
        return linkedHashMap;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final <V> Map<Boolean, V> associateWith(boolean[] zArr, Function1<? super Boolean, ? extends V> valueSelector) {
        int mapCapacity;
        int coerceAtLeast;
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(valueSelector, "valueSelector");
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(zArr.length);
        coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(mapCapacity, 16);
        LinkedHashMap linkedHashMap = new LinkedHashMap(coerceAtLeast);
        for (boolean z : zArr) {
            linkedHashMap.put(Boolean.valueOf(z), valueSelector.invoke(Boolean.valueOf(z)));
        }
        return linkedHashMap;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final <V, M extends Map<? super Byte, ? super V>> M associateWithTo(byte[] bArr, M destination, Function1<? super Byte, ? extends V> valueSelector) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(valueSelector, "valueSelector");
        for (byte b2 : bArr) {
            destination.put(Byte.valueOf(b2), valueSelector.invoke(Byte.valueOf(b2)));
        }
        return destination;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final <V, M extends Map<? super Character, ? super V>> M associateWithTo(char[] cArr, M destination, Function1<? super Character, ? extends V> valueSelector) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(valueSelector, "valueSelector");
        for (char c2 : cArr) {
            destination.put(Character.valueOf(c2), valueSelector.invoke(Character.valueOf(c2)));
        }
        return destination;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final <V, M extends Map<? super Double, ? super V>> M associateWithTo(double[] dArr, M destination, Function1<? super Double, ? extends V> valueSelector) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(valueSelector, "valueSelector");
        for (double d2 : dArr) {
            destination.put(Double.valueOf(d2), valueSelector.invoke(Double.valueOf(d2)));
        }
        return destination;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final <V, M extends Map<? super Float, ? super V>> M associateWithTo(float[] fArr, M destination, Function1<? super Float, ? extends V> valueSelector) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(valueSelector, "valueSelector");
        for (float f2 : fArr) {
            destination.put(Float.valueOf(f2), valueSelector.invoke(Float.valueOf(f2)));
        }
        return destination;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final <V, M extends Map<? super Integer, ? super V>> M associateWithTo(int[] iArr, M destination, Function1<? super Integer, ? extends V> valueSelector) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(valueSelector, "valueSelector");
        for (int i2 : iArr) {
            destination.put(Integer.valueOf(i2), valueSelector.invoke(Integer.valueOf(i2)));
        }
        return destination;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final <V, M extends Map<? super Long, ? super V>> M associateWithTo(long[] jArr, M destination, Function1<? super Long, ? extends V> valueSelector) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(valueSelector, "valueSelector");
        for (long j2 : jArr) {
            destination.put(Long.valueOf(j2), valueSelector.invoke(Long.valueOf(j2)));
        }
        return destination;
    }

    @SinceKotlin(version = "1.4")
    @NotNull
    public static final <K, V, M extends Map<? super K, ? super V>> M associateWithTo(@NotNull K[] kArr, @NotNull M destination, @NotNull Function1<? super K, ? extends V> valueSelector) {
        Intrinsics.checkNotNullParameter(kArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(valueSelector, "valueSelector");
        for (K k2 : kArr) {
            destination.put(k2, valueSelector.invoke(k2));
        }
        return destination;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final <V, M extends Map<? super Short, ? super V>> M associateWithTo(short[] sArr, M destination, Function1<? super Short, ? extends V> valueSelector) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(valueSelector, "valueSelector");
        for (short s2 : sArr) {
            destination.put(Short.valueOf(s2), valueSelector.invoke(Short.valueOf(s2)));
        }
        return destination;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final <V, M extends Map<? super Boolean, ? super V>> M associateWithTo(boolean[] zArr, M destination, Function1<? super Boolean, ? extends V> valueSelector) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(valueSelector, "valueSelector");
        for (boolean z : zArr) {
            destination.put(Boolean.valueOf(z), valueSelector.invoke(Boolean.valueOf(z)));
        }
        return destination;
    }

    public static final double average(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        double d2 = 0.0d;
        int i2 = 0;
        for (byte b2 : bArr) {
            d2 += b2;
            i2++;
        }
        if (i2 == 0) {
            return Double.NaN;
        }
        return d2 / i2;
    }

    public static final double average(@NotNull double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        double d2 = 0.0d;
        int i2 = 0;
        for (double d3 : dArr) {
            d2 += d3;
            i2++;
        }
        if (i2 == 0) {
            return Double.NaN;
        }
        return d2 / i2;
    }

    public static final double average(@NotNull float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        double d2 = 0.0d;
        int i2 = 0;
        for (float f2 : fArr) {
            d2 += f2;
            i2++;
        }
        if (i2 == 0) {
            return Double.NaN;
        }
        return d2 / i2;
    }

    public static final double average(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        double d2 = 0.0d;
        int i2 = 0;
        for (int i3 : iArr) {
            d2 += i3;
            i2++;
        }
        if (i2 == 0) {
            return Double.NaN;
        }
        return d2 / i2;
    }

    public static final double average(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        double d2 = 0.0d;
        int i2 = 0;
        for (long j2 : jArr) {
            d2 += j2;
            i2++;
        }
        if (i2 == 0) {
            return Double.NaN;
        }
        return d2 / i2;
    }

    public static final double average(@NotNull short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        double d2 = 0.0d;
        int i2 = 0;
        for (short s2 : sArr) {
            d2 += s2;
            i2++;
        }
        if (i2 == 0) {
            return Double.NaN;
        }
        return d2 / i2;
    }

    @JvmName(name = "averageOfByte")
    public static final double averageOfByte(@NotNull Byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        double d2 = 0.0d;
        int i2 = 0;
        for (Byte b2 : bArr) {
            d2 += b2.byteValue();
            i2++;
        }
        if (i2 == 0) {
            return Double.NaN;
        }
        return d2 / i2;
    }

    @JvmName(name = "averageOfDouble")
    public static final double averageOfDouble(@NotNull Double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        double d2 = 0.0d;
        int i2 = 0;
        for (Double d3 : dArr) {
            d2 += d3.doubleValue();
            i2++;
        }
        if (i2 == 0) {
            return Double.NaN;
        }
        return d2 / i2;
    }

    @JvmName(name = "averageOfFloat")
    public static final double averageOfFloat(@NotNull Float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        double d2 = 0.0d;
        int i2 = 0;
        for (Float f2 : fArr) {
            d2 += f2.floatValue();
            i2++;
        }
        if (i2 == 0) {
            return Double.NaN;
        }
        return d2 / i2;
    }

    @JvmName(name = "averageOfInt")
    public static final double averageOfInt(@NotNull Integer[] numArr) {
        Intrinsics.checkNotNullParameter(numArr, "<this>");
        double d2 = 0.0d;
        int i2 = 0;
        for (Integer num : numArr) {
            d2 += num.intValue();
            i2++;
        }
        if (i2 == 0) {
            return Double.NaN;
        }
        return d2 / i2;
    }

    @JvmName(name = "averageOfLong")
    public static final double averageOfLong(@NotNull Long[] lArr) {
        Intrinsics.checkNotNullParameter(lArr, "<this>");
        double d2 = 0.0d;
        int i2 = 0;
        for (Long l2 : lArr) {
            d2 += l2.longValue();
            i2++;
        }
        if (i2 == 0) {
            return Double.NaN;
        }
        return d2 / i2;
    }

    @JvmName(name = "averageOfShort")
    public static final double averageOfShort(@NotNull Short[] shArr) {
        Intrinsics.checkNotNullParameter(shArr, "<this>");
        double d2 = 0.0d;
        int i2 = 0;
        for (Short sh : shArr) {
            d2 += sh.shortValue();
            i2++;
        }
        if (i2 == 0) {
            return Double.NaN;
        }
        return d2 / i2;
    }

    @InlineOnly
    private static final byte component1(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        return bArr[0];
    }

    @InlineOnly
    private static final char component1(char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        return cArr[0];
    }

    @InlineOnly
    private static final double component1(double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        return dArr[0];
    }

    @InlineOnly
    private static final float component1(float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        return fArr[0];
    }

    @InlineOnly
    private static final int component1(int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        return iArr[0];
    }

    @InlineOnly
    private static final long component1(long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        return jArr[0];
    }

    @InlineOnly
    private static final <T> T component1(T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        return tArr[0];
    }

    @InlineOnly
    private static final short component1(short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        return sArr[0];
    }

    @InlineOnly
    private static final boolean component1(boolean[] zArr) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        return zArr[0];
    }

    @InlineOnly
    private static final byte component2(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        return bArr[1];
    }

    @InlineOnly
    private static final char component2(char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        return cArr[1];
    }

    @InlineOnly
    private static final double component2(double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        return dArr[1];
    }

    @InlineOnly
    private static final float component2(float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        return fArr[1];
    }

    @InlineOnly
    private static final int component2(int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        return iArr[1];
    }

    @InlineOnly
    private static final long component2(long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        return jArr[1];
    }

    @InlineOnly
    private static final <T> T component2(T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        return tArr[1];
    }

    @InlineOnly
    private static final short component2(short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        return sArr[1];
    }

    @InlineOnly
    private static final boolean component2(boolean[] zArr) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        return zArr[1];
    }

    @InlineOnly
    private static final byte component3(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        return bArr[2];
    }

    @InlineOnly
    private static final char component3(char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        return cArr[2];
    }

    @InlineOnly
    private static final double component3(double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        return dArr[2];
    }

    @InlineOnly
    private static final float component3(float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        return fArr[2];
    }

    @InlineOnly
    private static final int component3(int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        return iArr[2];
    }

    @InlineOnly
    private static final long component3(long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        return jArr[2];
    }

    @InlineOnly
    private static final <T> T component3(T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        return tArr[2];
    }

    @InlineOnly
    private static final short component3(short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        return sArr[2];
    }

    @InlineOnly
    private static final boolean component3(boolean[] zArr) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        return zArr[2];
    }

    @InlineOnly
    private static final byte component4(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        return bArr[3];
    }

    @InlineOnly
    private static final char component4(char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        return cArr[3];
    }

    @InlineOnly
    private static final double component4(double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        return dArr[3];
    }

    @InlineOnly
    private static final float component4(float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        return fArr[3];
    }

    @InlineOnly
    private static final int component4(int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        return iArr[3];
    }

    @InlineOnly
    private static final long component4(long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        return jArr[3];
    }

    @InlineOnly
    private static final <T> T component4(T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        return tArr[3];
    }

    @InlineOnly
    private static final short component4(short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        return sArr[3];
    }

    @InlineOnly
    private static final boolean component4(boolean[] zArr) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        return zArr[3];
    }

    @InlineOnly
    private static final byte component5(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        return bArr[4];
    }

    @InlineOnly
    private static final char component5(char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        return cArr[4];
    }

    @InlineOnly
    private static final double component5(double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        return dArr[4];
    }

    @InlineOnly
    private static final float component5(float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        return fArr[4];
    }

    @InlineOnly
    private static final int component5(int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        return iArr[4];
    }

    @InlineOnly
    private static final long component5(long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        return jArr[4];
    }

    @InlineOnly
    private static final <T> T component5(T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        return tArr[4];
    }

    @InlineOnly
    private static final short component5(short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        return sArr[4];
    }

    @InlineOnly
    private static final boolean component5(boolean[] zArr) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        return zArr[4];
    }

    public static boolean contains(@NotNull byte[] bArr, byte b2) {
        int indexOf;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        indexOf = indexOf(bArr, b2);
        return indexOf >= 0;
    }

    public static boolean contains(@NotNull char[] cArr, char c2) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        return indexOf(cArr, c2) >= 0;
    }

    @Deprecated(message = "The function has unclear behavior when searching for NaN or zero values and will be removed soon. Use 'any { it == element }' instead to continue using this behavior, or '.asList().contains(element: T)' to get the same search behavior as in a list.", replaceWith = @ReplaceWith(expression = "any { it == element }", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.6", warningSince = "1.4")
    public static final boolean contains(@NotNull double[] dArr, double d2) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        return indexOf(dArr, d2) >= 0;
    }

    @Deprecated(message = "The function has unclear behavior when searching for NaN or zero values and will be removed soon. Use 'any { it == element }' instead to continue using this behavior, or '.asList().contains(element: T)' to get the same search behavior as in a list.", replaceWith = @ReplaceWith(expression = "any { it == element }", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.6", warningSince = "1.4")
    public static final boolean contains(@NotNull float[] fArr, float f2) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        return indexOf(fArr, f2) >= 0;
    }

    public static boolean contains(@NotNull int[] iArr, int i2) {
        int indexOf;
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        indexOf = indexOf(iArr, i2);
        return indexOf >= 0;
    }

    public static boolean contains(@NotNull long[] jArr, long j2) {
        int indexOf;
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        indexOf = indexOf(jArr, j2);
        return indexOf >= 0;
    }

    public static final <T> boolean contains(@NotNull T[] tArr, T t2) {
        int indexOf;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        indexOf = indexOf(tArr, t2);
        return indexOf >= 0;
    }

    public static boolean contains(@NotNull short[] sArr, short s2) {
        int indexOf;
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        indexOf = indexOf(sArr, s2);
        return indexOf >= 0;
    }

    public static final boolean contains(@NotNull boolean[] zArr, boolean z) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        return indexOf(zArr, z) >= 0;
    }

    @InlineOnly
    private static final int count(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        return bArr.length;
    }

    public static final int count(@NotNull byte[] bArr, @NotNull Function1<? super Byte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int i2 = 0;
        for (byte b2 : bArr) {
            if (predicate.invoke(Byte.valueOf(b2)).booleanValue()) {
                i2++;
            }
        }
        return i2;
    }

    @InlineOnly
    private static final int count(char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        return cArr.length;
    }

    public static final int count(@NotNull char[] cArr, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int i2 = 0;
        for (char c2 : cArr) {
            if (predicate.invoke(Character.valueOf(c2)).booleanValue()) {
                i2++;
            }
        }
        return i2;
    }

    @InlineOnly
    private static final int count(double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        return dArr.length;
    }

    public static final int count(@NotNull double[] dArr, @NotNull Function1<? super Double, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int i2 = 0;
        for (double d2 : dArr) {
            if (predicate.invoke(Double.valueOf(d2)).booleanValue()) {
                i2++;
            }
        }
        return i2;
    }

    @InlineOnly
    private static final int count(float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        return fArr.length;
    }

    public static final int count(@NotNull float[] fArr, @NotNull Function1<? super Float, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int i2 = 0;
        for (float f2 : fArr) {
            if (predicate.invoke(Float.valueOf(f2)).booleanValue()) {
                i2++;
            }
        }
        return i2;
    }

    @InlineOnly
    private static final int count(int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        return iArr.length;
    }

    public static final int count(@NotNull int[] iArr, @NotNull Function1<? super Integer, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int i2 = 0;
        for (int i3 : iArr) {
            if (predicate.invoke(Integer.valueOf(i3)).booleanValue()) {
                i2++;
            }
        }
        return i2;
    }

    @InlineOnly
    private static final int count(long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        return jArr.length;
    }

    public static final int count(@NotNull long[] jArr, @NotNull Function1<? super Long, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int i2 = 0;
        for (long j2 : jArr) {
            if (predicate.invoke(Long.valueOf(j2)).booleanValue()) {
                i2++;
            }
        }
        return i2;
    }

    @InlineOnly
    private static final <T> int count(T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        return tArr.length;
    }

    public static final <T> int count(@NotNull T[] tArr, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int i2 = 0;
        for (T t2 : tArr) {
            if (predicate.invoke(t2).booleanValue()) {
                i2++;
            }
        }
        return i2;
    }

    @InlineOnly
    private static final int count(short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        return sArr.length;
    }

    public static final int count(@NotNull short[] sArr, @NotNull Function1<? super Short, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int i2 = 0;
        for (short s2 : sArr) {
            if (predicate.invoke(Short.valueOf(s2)).booleanValue()) {
                i2++;
            }
        }
        return i2;
    }

    @InlineOnly
    private static final int count(boolean[] zArr) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        return zArr.length;
    }

    public static final int count(@NotNull boolean[] zArr, @NotNull Function1<? super Boolean, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int i2 = 0;
        for (boolean z : zArr) {
            if (predicate.invoke(Boolean.valueOf(z)).booleanValue()) {
                i2++;
            }
        }
        return i2;
    }

    @NotNull
    public static final List<Byte> distinct(@NotNull byte[] bArr) {
        List<Byte> list;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        list = CollectionsKt___CollectionsKt.toList(toMutableSet(bArr));
        return list;
    }

    @NotNull
    public static final List<Character> distinct(@NotNull char[] cArr) {
        List<Character> list;
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        list = CollectionsKt___CollectionsKt.toList(toMutableSet(cArr));
        return list;
    }

    @NotNull
    public static final List<Double> distinct(@NotNull double[] dArr) {
        List<Double> list;
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        list = CollectionsKt___CollectionsKt.toList(toMutableSet(dArr));
        return list;
    }

    @NotNull
    public static final List<Float> distinct(@NotNull float[] fArr) {
        List<Float> list;
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        list = CollectionsKt___CollectionsKt.toList(toMutableSet(fArr));
        return list;
    }

    @NotNull
    public static final List<Integer> distinct(@NotNull int[] iArr) {
        List<Integer> list;
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        list = CollectionsKt___CollectionsKt.toList(toMutableSet(iArr));
        return list;
    }

    @NotNull
    public static final List<Long> distinct(@NotNull long[] jArr) {
        List<Long> list;
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        list = CollectionsKt___CollectionsKt.toList(toMutableSet(jArr));
        return list;
    }

    @NotNull
    public static final <T> List<T> distinct(@NotNull T[] tArr) {
        List<T> list;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        list = CollectionsKt___CollectionsKt.toList(toMutableSet(tArr));
        return list;
    }

    @NotNull
    public static final List<Short> distinct(@NotNull short[] sArr) {
        List<Short> list;
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        list = CollectionsKt___CollectionsKt.toList(toMutableSet(sArr));
        return list;
    }

    @NotNull
    public static final List<Boolean> distinct(@NotNull boolean[] zArr) {
        List<Boolean> list;
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        list = CollectionsKt___CollectionsKt.toList(toMutableSet(zArr));
        return list;
    }

    @NotNull
    public static final <K> List<Byte> distinctBy(@NotNull byte[] bArr, @NotNull Function1<? super Byte, ? extends K> selector) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        HashSet hashSet = new HashSet();
        ArrayList arrayList = new ArrayList();
        for (byte b2 : bArr) {
            if (hashSet.add(selector.invoke(Byte.valueOf(b2)))) {
                arrayList.add(Byte.valueOf(b2));
            }
        }
        return arrayList;
    }

    @NotNull
    public static final <K> List<Character> distinctBy(@NotNull char[] cArr, @NotNull Function1<? super Character, ? extends K> selector) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        HashSet hashSet = new HashSet();
        ArrayList arrayList = new ArrayList();
        for (char c2 : cArr) {
            if (hashSet.add(selector.invoke(Character.valueOf(c2)))) {
                arrayList.add(Character.valueOf(c2));
            }
        }
        return arrayList;
    }

    @NotNull
    public static final <K> List<Double> distinctBy(@NotNull double[] dArr, @NotNull Function1<? super Double, ? extends K> selector) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        HashSet hashSet = new HashSet();
        ArrayList arrayList = new ArrayList();
        for (double d2 : dArr) {
            if (hashSet.add(selector.invoke(Double.valueOf(d2)))) {
                arrayList.add(Double.valueOf(d2));
            }
        }
        return arrayList;
    }

    @NotNull
    public static final <K> List<Float> distinctBy(@NotNull float[] fArr, @NotNull Function1<? super Float, ? extends K> selector) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        HashSet hashSet = new HashSet();
        ArrayList arrayList = new ArrayList();
        for (float f2 : fArr) {
            if (hashSet.add(selector.invoke(Float.valueOf(f2)))) {
                arrayList.add(Float.valueOf(f2));
            }
        }
        return arrayList;
    }

    @NotNull
    public static final <K> List<Integer> distinctBy(@NotNull int[] iArr, @NotNull Function1<? super Integer, ? extends K> selector) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        HashSet hashSet = new HashSet();
        ArrayList arrayList = new ArrayList();
        for (int i2 : iArr) {
            if (hashSet.add(selector.invoke(Integer.valueOf(i2)))) {
                arrayList.add(Integer.valueOf(i2));
            }
        }
        return arrayList;
    }

    @NotNull
    public static final <K> List<Long> distinctBy(@NotNull long[] jArr, @NotNull Function1<? super Long, ? extends K> selector) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        HashSet hashSet = new HashSet();
        ArrayList arrayList = new ArrayList();
        for (long j2 : jArr) {
            if (hashSet.add(selector.invoke(Long.valueOf(j2)))) {
                arrayList.add(Long.valueOf(j2));
            }
        }
        return arrayList;
    }

    @NotNull
    public static final <T, K> List<T> distinctBy(@NotNull T[] tArr, @NotNull Function1<? super T, ? extends K> selector) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        HashSet hashSet = new HashSet();
        ArrayList arrayList = new ArrayList();
        for (T t2 : tArr) {
            if (hashSet.add(selector.invoke(t2))) {
                arrayList.add(t2);
            }
        }
        return arrayList;
    }

    @NotNull
    public static final <K> List<Short> distinctBy(@NotNull short[] sArr, @NotNull Function1<? super Short, ? extends K> selector) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        HashSet hashSet = new HashSet();
        ArrayList arrayList = new ArrayList();
        for (short s2 : sArr) {
            if (hashSet.add(selector.invoke(Short.valueOf(s2)))) {
                arrayList.add(Short.valueOf(s2));
            }
        }
        return arrayList;
    }

    @NotNull
    public static final <K> List<Boolean> distinctBy(@NotNull boolean[] zArr, @NotNull Function1<? super Boolean, ? extends K> selector) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        HashSet hashSet = new HashSet();
        ArrayList arrayList = new ArrayList();
        for (boolean z : zArr) {
            if (hashSet.add(selector.invoke(Boolean.valueOf(z)))) {
                arrayList.add(Boolean.valueOf(z));
            }
        }
        return arrayList;
    }

    @NotNull
    public static final List<Byte> drop(@NotNull byte[] bArr, int i2) {
        int coerceAtLeast;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        if (i2 >= 0) {
            coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(bArr.length - i2, 0);
            return takeLast(bArr, coerceAtLeast);
        }
        throw new IllegalArgumentException(("Requested element count " + i2 + " is less than zero.").toString());
    }

    @NotNull
    public static final List<Character> drop(@NotNull char[] cArr, int i2) {
        int coerceAtLeast;
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        if (i2 >= 0) {
            coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(cArr.length - i2, 0);
            return takeLast(cArr, coerceAtLeast);
        }
        throw new IllegalArgumentException(("Requested element count " + i2 + " is less than zero.").toString());
    }

    @NotNull
    public static final List<Double> drop(@NotNull double[] dArr, int i2) {
        int coerceAtLeast;
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        if (i2 >= 0) {
            coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(dArr.length - i2, 0);
            return takeLast(dArr, coerceAtLeast);
        }
        throw new IllegalArgumentException(("Requested element count " + i2 + " is less than zero.").toString());
    }

    @NotNull
    public static final List<Float> drop(@NotNull float[] fArr, int i2) {
        int coerceAtLeast;
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        if (i2 >= 0) {
            coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(fArr.length - i2, 0);
            return takeLast(fArr, coerceAtLeast);
        }
        throw new IllegalArgumentException(("Requested element count " + i2 + " is less than zero.").toString());
    }

    @NotNull
    public static final List<Integer> drop(@NotNull int[] iArr, int i2) {
        int coerceAtLeast;
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        if (i2 >= 0) {
            coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(iArr.length - i2, 0);
            return takeLast(iArr, coerceAtLeast);
        }
        throw new IllegalArgumentException(("Requested element count " + i2 + " is less than zero.").toString());
    }

    @NotNull
    public static final List<Long> drop(@NotNull long[] jArr, int i2) {
        int coerceAtLeast;
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        if (i2 >= 0) {
            coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(jArr.length - i2, 0);
            return takeLast(jArr, coerceAtLeast);
        }
        throw new IllegalArgumentException(("Requested element count " + i2 + " is less than zero.").toString());
    }

    @NotNull
    public static final <T> List<T> drop(@NotNull T[] tArr, int i2) {
        int coerceAtLeast;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        if (i2 >= 0) {
            coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(tArr.length - i2, 0);
            return takeLast(tArr, coerceAtLeast);
        }
        throw new IllegalArgumentException(("Requested element count " + i2 + " is less than zero.").toString());
    }

    @NotNull
    public static final List<Short> drop(@NotNull short[] sArr, int i2) {
        int coerceAtLeast;
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        if (i2 >= 0) {
            coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(sArr.length - i2, 0);
            return takeLast(sArr, coerceAtLeast);
        }
        throw new IllegalArgumentException(("Requested element count " + i2 + " is less than zero.").toString());
    }

    @NotNull
    public static final List<Boolean> drop(@NotNull boolean[] zArr, int i2) {
        int coerceAtLeast;
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        if (i2 >= 0) {
            coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(zArr.length - i2, 0);
            return takeLast(zArr, coerceAtLeast);
        }
        throw new IllegalArgumentException(("Requested element count " + i2 + " is less than zero.").toString());
    }

    @NotNull
    public static final List<Byte> dropLast(@NotNull byte[] bArr, int i2) {
        int coerceAtLeast;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        if (i2 >= 0) {
            coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(bArr.length - i2, 0);
            return take(bArr, coerceAtLeast);
        }
        throw new IllegalArgumentException(("Requested element count " + i2 + " is less than zero.").toString());
    }

    @NotNull
    public static final List<Character> dropLast(@NotNull char[] cArr, int i2) {
        int coerceAtLeast;
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        if (i2 >= 0) {
            coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(cArr.length - i2, 0);
            return take(cArr, coerceAtLeast);
        }
        throw new IllegalArgumentException(("Requested element count " + i2 + " is less than zero.").toString());
    }

    @NotNull
    public static final List<Double> dropLast(@NotNull double[] dArr, int i2) {
        int coerceAtLeast;
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        if (i2 >= 0) {
            coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(dArr.length - i2, 0);
            return take(dArr, coerceAtLeast);
        }
        throw new IllegalArgumentException(("Requested element count " + i2 + " is less than zero.").toString());
    }

    @NotNull
    public static final List<Float> dropLast(@NotNull float[] fArr, int i2) {
        int coerceAtLeast;
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        if (i2 >= 0) {
            coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(fArr.length - i2, 0);
            return take(fArr, coerceAtLeast);
        }
        throw new IllegalArgumentException(("Requested element count " + i2 + " is less than zero.").toString());
    }

    @NotNull
    public static final List<Integer> dropLast(@NotNull int[] iArr, int i2) {
        int coerceAtLeast;
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        if (i2 >= 0) {
            coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(iArr.length - i2, 0);
            return take(iArr, coerceAtLeast);
        }
        throw new IllegalArgumentException(("Requested element count " + i2 + " is less than zero.").toString());
    }

    @NotNull
    public static final List<Long> dropLast(@NotNull long[] jArr, int i2) {
        int coerceAtLeast;
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        if (i2 >= 0) {
            coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(jArr.length - i2, 0);
            return take(jArr, coerceAtLeast);
        }
        throw new IllegalArgumentException(("Requested element count " + i2 + " is less than zero.").toString());
    }

    @NotNull
    public static final <T> List<T> dropLast(@NotNull T[] tArr, int i2) {
        int coerceAtLeast;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        if (i2 >= 0) {
            coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(tArr.length - i2, 0);
            return take(tArr, coerceAtLeast);
        }
        throw new IllegalArgumentException(("Requested element count " + i2 + " is less than zero.").toString());
    }

    @NotNull
    public static final List<Short> dropLast(@NotNull short[] sArr, int i2) {
        int coerceAtLeast;
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        if (i2 >= 0) {
            coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(sArr.length - i2, 0);
            return take(sArr, coerceAtLeast);
        }
        throw new IllegalArgumentException(("Requested element count " + i2 + " is less than zero.").toString());
    }

    @NotNull
    public static final List<Boolean> dropLast(@NotNull boolean[] zArr, int i2) {
        int coerceAtLeast;
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        if (i2 >= 0) {
            coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(zArr.length - i2, 0);
            return take(zArr, coerceAtLeast);
        }
        throw new IllegalArgumentException(("Requested element count " + i2 + " is less than zero.").toString());
    }

    @NotNull
    public static final List<Byte> dropLastWhile(@NotNull byte[] bArr, @NotNull Function1<? super Byte, Boolean> predicate) {
        int lastIndex;
        List<Byte> emptyList;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (lastIndex = getLastIndex(bArr); -1 < lastIndex; lastIndex--) {
            if (!predicate.invoke(Byte.valueOf(bArr[lastIndex])).booleanValue()) {
                return take(bArr, lastIndex + 1);
            }
        }
        emptyList = CollectionsKt__CollectionsKt.emptyList();
        return emptyList;
    }

    @NotNull
    public static final List<Character> dropLastWhile(@NotNull char[] cArr, @NotNull Function1<? super Character, Boolean> predicate) {
        List<Character> emptyList;
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (int lastIndex = getLastIndex(cArr); -1 < lastIndex; lastIndex--) {
            if (!predicate.invoke(Character.valueOf(cArr[lastIndex])).booleanValue()) {
                return take(cArr, lastIndex + 1);
            }
        }
        emptyList = CollectionsKt__CollectionsKt.emptyList();
        return emptyList;
    }

    @NotNull
    public static final List<Double> dropLastWhile(@NotNull double[] dArr, @NotNull Function1<? super Double, Boolean> predicate) {
        List<Double> emptyList;
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (int lastIndex = getLastIndex(dArr); -1 < lastIndex; lastIndex--) {
            if (!predicate.invoke(Double.valueOf(dArr[lastIndex])).booleanValue()) {
                return take(dArr, lastIndex + 1);
            }
        }
        emptyList = CollectionsKt__CollectionsKt.emptyList();
        return emptyList;
    }

    @NotNull
    public static final List<Float> dropLastWhile(@NotNull float[] fArr, @NotNull Function1<? super Float, Boolean> predicate) {
        List<Float> emptyList;
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (int lastIndex = getLastIndex(fArr); -1 < lastIndex; lastIndex--) {
            if (!predicate.invoke(Float.valueOf(fArr[lastIndex])).booleanValue()) {
                return take(fArr, lastIndex + 1);
            }
        }
        emptyList = CollectionsKt__CollectionsKt.emptyList();
        return emptyList;
    }

    @NotNull
    public static final List<Integer> dropLastWhile(@NotNull int[] iArr, @NotNull Function1<? super Integer, Boolean> predicate) {
        int lastIndex;
        List<Integer> emptyList;
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (lastIndex = getLastIndex(iArr); -1 < lastIndex; lastIndex--) {
            if (!predicate.invoke(Integer.valueOf(iArr[lastIndex])).booleanValue()) {
                return take(iArr, lastIndex + 1);
            }
        }
        emptyList = CollectionsKt__CollectionsKt.emptyList();
        return emptyList;
    }

    @NotNull
    public static final List<Long> dropLastWhile(@NotNull long[] jArr, @NotNull Function1<? super Long, Boolean> predicate) {
        int lastIndex;
        List<Long> emptyList;
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (lastIndex = getLastIndex(jArr); -1 < lastIndex; lastIndex--) {
            if (!predicate.invoke(Long.valueOf(jArr[lastIndex])).booleanValue()) {
                return take(jArr, lastIndex + 1);
            }
        }
        emptyList = CollectionsKt__CollectionsKt.emptyList();
        return emptyList;
    }

    @NotNull
    public static final <T> List<T> dropLastWhile(@NotNull T[] tArr, @NotNull Function1<? super T, Boolean> predicate) {
        int lastIndex;
        List<T> emptyList;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (lastIndex = getLastIndex(tArr); -1 < lastIndex; lastIndex--) {
            if (!predicate.invoke(tArr[lastIndex]).booleanValue()) {
                return take(tArr, lastIndex + 1);
            }
        }
        emptyList = CollectionsKt__CollectionsKt.emptyList();
        return emptyList;
    }

    @NotNull
    public static final List<Short> dropLastWhile(@NotNull short[] sArr, @NotNull Function1<? super Short, Boolean> predicate) {
        int lastIndex;
        List<Short> emptyList;
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (lastIndex = getLastIndex(sArr); -1 < lastIndex; lastIndex--) {
            if (!predicate.invoke(Short.valueOf(sArr[lastIndex])).booleanValue()) {
                return take(sArr, lastIndex + 1);
            }
        }
        emptyList = CollectionsKt__CollectionsKt.emptyList();
        return emptyList;
    }

    @NotNull
    public static final List<Boolean> dropLastWhile(@NotNull boolean[] zArr, @NotNull Function1<? super Boolean, Boolean> predicate) {
        List<Boolean> emptyList;
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (int lastIndex = getLastIndex(zArr); -1 < lastIndex; lastIndex--) {
            if (!predicate.invoke(Boolean.valueOf(zArr[lastIndex])).booleanValue()) {
                return take(zArr, lastIndex + 1);
            }
        }
        emptyList = CollectionsKt__CollectionsKt.emptyList();
        return emptyList;
    }

    @NotNull
    public static final List<Byte> dropWhile(@NotNull byte[] bArr, @NotNull Function1<? super Byte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        for (byte b2 : bArr) {
            if (z) {
                arrayList.add(Byte.valueOf(b2));
            } else if (!predicate.invoke(Byte.valueOf(b2)).booleanValue()) {
                arrayList.add(Byte.valueOf(b2));
                z = true;
            }
        }
        return arrayList;
    }

    @NotNull
    public static final List<Character> dropWhile(@NotNull char[] cArr, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        for (char c2 : cArr) {
            if (z) {
                arrayList.add(Character.valueOf(c2));
            } else if (!predicate.invoke(Character.valueOf(c2)).booleanValue()) {
                arrayList.add(Character.valueOf(c2));
                z = true;
            }
        }
        return arrayList;
    }

    @NotNull
    public static final List<Double> dropWhile(@NotNull double[] dArr, @NotNull Function1<? super Double, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        for (double d2 : dArr) {
            if (z) {
                arrayList.add(Double.valueOf(d2));
            } else if (!predicate.invoke(Double.valueOf(d2)).booleanValue()) {
                arrayList.add(Double.valueOf(d2));
                z = true;
            }
        }
        return arrayList;
    }

    @NotNull
    public static final List<Float> dropWhile(@NotNull float[] fArr, @NotNull Function1<? super Float, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        for (float f2 : fArr) {
            if (z) {
                arrayList.add(Float.valueOf(f2));
            } else if (!predicate.invoke(Float.valueOf(f2)).booleanValue()) {
                arrayList.add(Float.valueOf(f2));
                z = true;
            }
        }
        return arrayList;
    }

    @NotNull
    public static final List<Integer> dropWhile(@NotNull int[] iArr, @NotNull Function1<? super Integer, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        for (int i2 : iArr) {
            if (z) {
                arrayList.add(Integer.valueOf(i2));
            } else if (!predicate.invoke(Integer.valueOf(i2)).booleanValue()) {
                arrayList.add(Integer.valueOf(i2));
                z = true;
            }
        }
        return arrayList;
    }

    @NotNull
    public static final List<Long> dropWhile(@NotNull long[] jArr, @NotNull Function1<? super Long, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        for (long j2 : jArr) {
            if (z) {
                arrayList.add(Long.valueOf(j2));
            } else if (!predicate.invoke(Long.valueOf(j2)).booleanValue()) {
                arrayList.add(Long.valueOf(j2));
                z = true;
            }
        }
        return arrayList;
    }

    @NotNull
    public static final <T> List<T> dropWhile(@NotNull T[] tArr, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        for (T t2 : tArr) {
            if (z) {
                arrayList.add(t2);
            } else if (!predicate.invoke(t2).booleanValue()) {
                arrayList.add(t2);
                z = true;
            }
        }
        return arrayList;
    }

    @NotNull
    public static final List<Short> dropWhile(@NotNull short[] sArr, @NotNull Function1<? super Short, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        for (short s2 : sArr) {
            if (z) {
                arrayList.add(Short.valueOf(s2));
            } else if (!predicate.invoke(Short.valueOf(s2)).booleanValue()) {
                arrayList.add(Short.valueOf(s2));
                z = true;
            }
        }
        return arrayList;
    }

    @NotNull
    public static final List<Boolean> dropWhile(@NotNull boolean[] zArr, @NotNull Function1<? super Boolean, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        for (boolean z2 : zArr) {
            if (z) {
                arrayList.add(Boolean.valueOf(z2));
            } else if (!predicate.invoke(Boolean.valueOf(z2)).booleanValue()) {
                arrayList.add(Boolean.valueOf(z2));
                z = true;
            }
        }
        return arrayList;
    }

    @InlineOnly
    private static final byte elementAtOrElse(byte[] bArr, int i2, Function1<? super Integer, Byte> defaultValue) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        if (i2 >= 0) {
            lastIndex = getLastIndex(bArr);
            if (i2 <= lastIndex) {
                return bArr[i2];
            }
        }
        return defaultValue.invoke(Integer.valueOf(i2)).byteValue();
    }

    @InlineOnly
    private static final char elementAtOrElse(char[] cArr, int i2, Function1<? super Integer, Character> defaultValue) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        return (i2 < 0 || i2 > getLastIndex(cArr)) ? defaultValue.invoke(Integer.valueOf(i2)).charValue() : cArr[i2];
    }

    @InlineOnly
    private static final double elementAtOrElse(double[] dArr, int i2, Function1<? super Integer, Double> defaultValue) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        return (i2 < 0 || i2 > getLastIndex(dArr)) ? defaultValue.invoke(Integer.valueOf(i2)).doubleValue() : dArr[i2];
    }

    @InlineOnly
    private static final float elementAtOrElse(float[] fArr, int i2, Function1<? super Integer, Float> defaultValue) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        return (i2 < 0 || i2 > getLastIndex(fArr)) ? defaultValue.invoke(Integer.valueOf(i2)).floatValue() : fArr[i2];
    }

    @InlineOnly
    private static final int elementAtOrElse(int[] iArr, int i2, Function1<? super Integer, Integer> defaultValue) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        if (i2 >= 0) {
            lastIndex = getLastIndex(iArr);
            if (i2 <= lastIndex) {
                return iArr[i2];
            }
        }
        return defaultValue.invoke(Integer.valueOf(i2)).intValue();
    }

    @InlineOnly
    private static final long elementAtOrElse(long[] jArr, int i2, Function1<? super Integer, Long> defaultValue) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        if (i2 >= 0) {
            lastIndex = getLastIndex(jArr);
            if (i2 <= lastIndex) {
                return jArr[i2];
            }
        }
        return defaultValue.invoke(Integer.valueOf(i2)).longValue();
    }

    @InlineOnly
    private static final <T> T elementAtOrElse(T[] tArr, int i2, Function1<? super Integer, ? extends T> defaultValue) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        if (i2 >= 0) {
            lastIndex = getLastIndex(tArr);
            if (i2 <= lastIndex) {
                return tArr[i2];
            }
        }
        return defaultValue.invoke(Integer.valueOf(i2));
    }

    @InlineOnly
    private static final short elementAtOrElse(short[] sArr, int i2, Function1<? super Integer, Short> defaultValue) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        if (i2 >= 0) {
            lastIndex = getLastIndex(sArr);
            if (i2 <= lastIndex) {
                return sArr[i2];
            }
        }
        return defaultValue.invoke(Integer.valueOf(i2)).shortValue();
    }

    @InlineOnly
    private static final boolean elementAtOrElse(boolean[] zArr, int i2, Function1<? super Integer, Boolean> defaultValue) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        return (i2 < 0 || i2 > getLastIndex(zArr)) ? defaultValue.invoke(Integer.valueOf(i2)).booleanValue() : zArr[i2];
    }

    @InlineOnly
    private static final Boolean elementAtOrNull(boolean[] zArr, int i2) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        return getOrNull(zArr, i2);
    }

    @InlineOnly
    private static final Byte elementAtOrNull(byte[] bArr, int i2) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        return getOrNull(bArr, i2);
    }

    @InlineOnly
    private static final Character elementAtOrNull(char[] cArr, int i2) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        return getOrNull(cArr, i2);
    }

    @InlineOnly
    private static final Double elementAtOrNull(double[] dArr, int i2) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        return getOrNull(dArr, i2);
    }

    @InlineOnly
    private static final Float elementAtOrNull(float[] fArr, int i2) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        return getOrNull(fArr, i2);
    }

    @InlineOnly
    private static final Integer elementAtOrNull(int[] iArr, int i2) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        return getOrNull(iArr, i2);
    }

    @InlineOnly
    private static final Long elementAtOrNull(long[] jArr, int i2) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        return getOrNull(jArr, i2);
    }

    @InlineOnly
    private static final <T> T elementAtOrNull(T[] tArr, int i2) {
        Object orNull;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        orNull = getOrNull(tArr, i2);
        return (T) orNull;
    }

    @InlineOnly
    private static final Short elementAtOrNull(short[] sArr, int i2) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        return getOrNull(sArr, i2);
    }

    @NotNull
    public static final List<Byte> filter(@NotNull byte[] bArr, @NotNull Function1<? super Byte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        for (byte b2 : bArr) {
            if (predicate.invoke(Byte.valueOf(b2)).booleanValue()) {
                arrayList.add(Byte.valueOf(b2));
            }
        }
        return arrayList;
    }

    @NotNull
    public static final List<Character> filter(@NotNull char[] cArr, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        for (char c2 : cArr) {
            if (predicate.invoke(Character.valueOf(c2)).booleanValue()) {
                arrayList.add(Character.valueOf(c2));
            }
        }
        return arrayList;
    }

    @NotNull
    public static final List<Double> filter(@NotNull double[] dArr, @NotNull Function1<? super Double, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        for (double d2 : dArr) {
            if (predicate.invoke(Double.valueOf(d2)).booleanValue()) {
                arrayList.add(Double.valueOf(d2));
            }
        }
        return arrayList;
    }

    @NotNull
    public static final List<Float> filter(@NotNull float[] fArr, @NotNull Function1<? super Float, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        for (float f2 : fArr) {
            if (predicate.invoke(Float.valueOf(f2)).booleanValue()) {
                arrayList.add(Float.valueOf(f2));
            }
        }
        return arrayList;
    }

    @NotNull
    public static final List<Integer> filter(@NotNull int[] iArr, @NotNull Function1<? super Integer, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        for (int i2 : iArr) {
            if (predicate.invoke(Integer.valueOf(i2)).booleanValue()) {
                arrayList.add(Integer.valueOf(i2));
            }
        }
        return arrayList;
    }

    @NotNull
    public static final List<Long> filter(@NotNull long[] jArr, @NotNull Function1<? super Long, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        for (long j2 : jArr) {
            if (predicate.invoke(Long.valueOf(j2)).booleanValue()) {
                arrayList.add(Long.valueOf(j2));
            }
        }
        return arrayList;
    }

    @NotNull
    public static final <T> List<T> filter(@NotNull T[] tArr, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        for (T t2 : tArr) {
            if (predicate.invoke(t2).booleanValue()) {
                arrayList.add(t2);
            }
        }
        return arrayList;
    }

    @NotNull
    public static final List<Short> filter(@NotNull short[] sArr, @NotNull Function1<? super Short, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        for (short s2 : sArr) {
            if (predicate.invoke(Short.valueOf(s2)).booleanValue()) {
                arrayList.add(Short.valueOf(s2));
            }
        }
        return arrayList;
    }

    @NotNull
    public static final List<Boolean> filter(@NotNull boolean[] zArr, @NotNull Function1<? super Boolean, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        for (boolean z : zArr) {
            if (predicate.invoke(Boolean.valueOf(z)).booleanValue()) {
                arrayList.add(Boolean.valueOf(z));
            }
        }
        return arrayList;
    }

    @NotNull
    public static final List<Byte> filterIndexed(@NotNull byte[] bArr, @NotNull Function2<? super Integer, ? super Byte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        int length = bArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            byte b2 = bArr[i2];
            int i4 = i3 + 1;
            if (predicate.invoke(Integer.valueOf(i3), Byte.valueOf(b2)).booleanValue()) {
                arrayList.add(Byte.valueOf(b2));
            }
            i2++;
            i3 = i4;
        }
        return arrayList;
    }

    @NotNull
    public static final List<Character> filterIndexed(@NotNull char[] cArr, @NotNull Function2<? super Integer, ? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        int length = cArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            char c2 = cArr[i2];
            int i4 = i3 + 1;
            if (predicate.invoke(Integer.valueOf(i3), Character.valueOf(c2)).booleanValue()) {
                arrayList.add(Character.valueOf(c2));
            }
            i2++;
            i3 = i4;
        }
        return arrayList;
    }

    @NotNull
    public static final List<Double> filterIndexed(@NotNull double[] dArr, @NotNull Function2<? super Integer, ? super Double, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        int length = dArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            double d2 = dArr[i2];
            int i4 = i3 + 1;
            if (predicate.invoke(Integer.valueOf(i3), Double.valueOf(d2)).booleanValue()) {
                arrayList.add(Double.valueOf(d2));
            }
            i2++;
            i3 = i4;
        }
        return arrayList;
    }

    @NotNull
    public static final List<Float> filterIndexed(@NotNull float[] fArr, @NotNull Function2<? super Integer, ? super Float, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        int length = fArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            float f2 = fArr[i2];
            int i4 = i3 + 1;
            if (predicate.invoke(Integer.valueOf(i3), Float.valueOf(f2)).booleanValue()) {
                arrayList.add(Float.valueOf(f2));
            }
            i2++;
            i3 = i4;
        }
        return arrayList;
    }

    @NotNull
    public static final List<Integer> filterIndexed(@NotNull int[] iArr, @NotNull Function2<? super Integer, ? super Integer, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        int length = iArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            int i4 = iArr[i2];
            int i5 = i3 + 1;
            if (predicate.invoke(Integer.valueOf(i3), Integer.valueOf(i4)).booleanValue()) {
                arrayList.add(Integer.valueOf(i4));
            }
            i2++;
            i3 = i5;
        }
        return arrayList;
    }

    @NotNull
    public static final List<Long> filterIndexed(@NotNull long[] jArr, @NotNull Function2<? super Integer, ? super Long, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        int length = jArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            long j2 = jArr[i2];
            int i4 = i3 + 1;
            if (predicate.invoke(Integer.valueOf(i3), Long.valueOf(j2)).booleanValue()) {
                arrayList.add(Long.valueOf(j2));
            }
            i2++;
            i3 = i4;
        }
        return arrayList;
    }

    @NotNull
    public static final <T> List<T> filterIndexed(@NotNull T[] tArr, @NotNull Function2<? super Integer, ? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        int length = tArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            T t2 = tArr[i2];
            int i4 = i3 + 1;
            if (predicate.invoke(Integer.valueOf(i3), t2).booleanValue()) {
                arrayList.add(t2);
            }
            i2++;
            i3 = i4;
        }
        return arrayList;
    }

    @NotNull
    public static final List<Short> filterIndexed(@NotNull short[] sArr, @NotNull Function2<? super Integer, ? super Short, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        int length = sArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            short s2 = sArr[i2];
            int i4 = i3 + 1;
            if (predicate.invoke(Integer.valueOf(i3), Short.valueOf(s2)).booleanValue()) {
                arrayList.add(Short.valueOf(s2));
            }
            i2++;
            i3 = i4;
        }
        return arrayList;
    }

    @NotNull
    public static final List<Boolean> filterIndexed(@NotNull boolean[] zArr, @NotNull Function2<? super Integer, ? super Boolean, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        int length = zArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            boolean z = zArr[i2];
            int i4 = i3 + 1;
            if (predicate.invoke(Integer.valueOf(i3), Boolean.valueOf(z)).booleanValue()) {
                arrayList.add(Boolean.valueOf(z));
            }
            i2++;
            i3 = i4;
        }
        return arrayList;
    }

    @NotNull
    public static final <C extends Collection<? super Byte>> C filterIndexedTo(@NotNull byte[] bArr, @NotNull C destination, @NotNull Function2<? super Integer, ? super Byte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = bArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            byte b2 = bArr[i2];
            int i4 = i3 + 1;
            if (predicate.invoke(Integer.valueOf(i3), Byte.valueOf(b2)).booleanValue()) {
                destination.add(Byte.valueOf(b2));
            }
            i2++;
            i3 = i4;
        }
        return destination;
    }

    @NotNull
    public static final <C extends Collection<? super Character>> C filterIndexedTo(@NotNull char[] cArr, @NotNull C destination, @NotNull Function2<? super Integer, ? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = cArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            char c2 = cArr[i2];
            int i4 = i3 + 1;
            if (predicate.invoke(Integer.valueOf(i3), Character.valueOf(c2)).booleanValue()) {
                destination.add(Character.valueOf(c2));
            }
            i2++;
            i3 = i4;
        }
        return destination;
    }

    @NotNull
    public static final <C extends Collection<? super Double>> C filterIndexedTo(@NotNull double[] dArr, @NotNull C destination, @NotNull Function2<? super Integer, ? super Double, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = dArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            double d2 = dArr[i2];
            int i4 = i3 + 1;
            if (predicate.invoke(Integer.valueOf(i3), Double.valueOf(d2)).booleanValue()) {
                destination.add(Double.valueOf(d2));
            }
            i2++;
            i3 = i4;
        }
        return destination;
    }

    @NotNull
    public static final <C extends Collection<? super Float>> C filterIndexedTo(@NotNull float[] fArr, @NotNull C destination, @NotNull Function2<? super Integer, ? super Float, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = fArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            float f2 = fArr[i2];
            int i4 = i3 + 1;
            if (predicate.invoke(Integer.valueOf(i3), Float.valueOf(f2)).booleanValue()) {
                destination.add(Float.valueOf(f2));
            }
            i2++;
            i3 = i4;
        }
        return destination;
    }

    @NotNull
    public static final <C extends Collection<? super Integer>> C filterIndexedTo(@NotNull int[] iArr, @NotNull C destination, @NotNull Function2<? super Integer, ? super Integer, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = iArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            int i4 = iArr[i2];
            int i5 = i3 + 1;
            if (predicate.invoke(Integer.valueOf(i3), Integer.valueOf(i4)).booleanValue()) {
                destination.add(Integer.valueOf(i4));
            }
            i2++;
            i3 = i5;
        }
        return destination;
    }

    @NotNull
    public static final <C extends Collection<? super Long>> C filterIndexedTo(@NotNull long[] jArr, @NotNull C destination, @NotNull Function2<? super Integer, ? super Long, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = jArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            long j2 = jArr[i2];
            int i4 = i3 + 1;
            if (predicate.invoke(Integer.valueOf(i3), Long.valueOf(j2)).booleanValue()) {
                destination.add(Long.valueOf(j2));
            }
            i2++;
            i3 = i4;
        }
        return destination;
    }

    @NotNull
    public static final <T, C extends Collection<? super T>> C filterIndexedTo(@NotNull T[] tArr, @NotNull C destination, @NotNull Function2<? super Integer, ? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = tArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            T t2 = tArr[i2];
            int i4 = i3 + 1;
            if (predicate.invoke(Integer.valueOf(i3), t2).booleanValue()) {
                destination.add(t2);
            }
            i2++;
            i3 = i4;
        }
        return destination;
    }

    @NotNull
    public static final <C extends Collection<? super Short>> C filterIndexedTo(@NotNull short[] sArr, @NotNull C destination, @NotNull Function2<? super Integer, ? super Short, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = sArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            short s2 = sArr[i2];
            int i4 = i3 + 1;
            if (predicate.invoke(Integer.valueOf(i3), Short.valueOf(s2)).booleanValue()) {
                destination.add(Short.valueOf(s2));
            }
            i2++;
            i3 = i4;
        }
        return destination;
    }

    @NotNull
    public static final <C extends Collection<? super Boolean>> C filterIndexedTo(@NotNull boolean[] zArr, @NotNull C destination, @NotNull Function2<? super Integer, ? super Boolean, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = zArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            boolean z = zArr[i2];
            int i4 = i3 + 1;
            if (predicate.invoke(Integer.valueOf(i3), Boolean.valueOf(z)).booleanValue()) {
                destination.add(Boolean.valueOf(z));
            }
            i2++;
            i3 = i4;
        }
        return destination;
    }

    public static final /* synthetic */ <R> List<R> filterIsInstance(Object[] objArr) {
        Intrinsics.checkNotNullParameter(objArr, "<this>");
        ArrayList arrayList = new ArrayList();
        for (Object obj : objArr) {
            Intrinsics.reifiedOperationMarker(3, "R");
            if (obj instanceof Object) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static final /* synthetic */ <R, C extends Collection<? super R>> C filterIsInstanceTo(Object[] objArr, C destination) {
        Intrinsics.checkNotNullParameter(objArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        for (Object obj : objArr) {
            Intrinsics.reifiedOperationMarker(3, "R");
            if (obj instanceof Object) {
                destination.add(obj);
            }
        }
        return destination;
    }

    @NotNull
    public static final List<Byte> filterNot(@NotNull byte[] bArr, @NotNull Function1<? super Byte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        for (byte b2 : bArr) {
            if (!predicate.invoke(Byte.valueOf(b2)).booleanValue()) {
                arrayList.add(Byte.valueOf(b2));
            }
        }
        return arrayList;
    }

    @NotNull
    public static final List<Character> filterNot(@NotNull char[] cArr, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        for (char c2 : cArr) {
            if (!predicate.invoke(Character.valueOf(c2)).booleanValue()) {
                arrayList.add(Character.valueOf(c2));
            }
        }
        return arrayList;
    }

    @NotNull
    public static final List<Double> filterNot(@NotNull double[] dArr, @NotNull Function1<? super Double, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        for (double d2 : dArr) {
            if (!predicate.invoke(Double.valueOf(d2)).booleanValue()) {
                arrayList.add(Double.valueOf(d2));
            }
        }
        return arrayList;
    }

    @NotNull
    public static final List<Float> filterNot(@NotNull float[] fArr, @NotNull Function1<? super Float, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        for (float f2 : fArr) {
            if (!predicate.invoke(Float.valueOf(f2)).booleanValue()) {
                arrayList.add(Float.valueOf(f2));
            }
        }
        return arrayList;
    }

    @NotNull
    public static final List<Integer> filterNot(@NotNull int[] iArr, @NotNull Function1<? super Integer, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        for (int i2 : iArr) {
            if (!predicate.invoke(Integer.valueOf(i2)).booleanValue()) {
                arrayList.add(Integer.valueOf(i2));
            }
        }
        return arrayList;
    }

    @NotNull
    public static final List<Long> filterNot(@NotNull long[] jArr, @NotNull Function1<? super Long, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        for (long j2 : jArr) {
            if (!predicate.invoke(Long.valueOf(j2)).booleanValue()) {
                arrayList.add(Long.valueOf(j2));
            }
        }
        return arrayList;
    }

    @NotNull
    public static final <T> List<T> filterNot(@NotNull T[] tArr, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        for (T t2 : tArr) {
            if (!predicate.invoke(t2).booleanValue()) {
                arrayList.add(t2);
            }
        }
        return arrayList;
    }

    @NotNull
    public static final List<Short> filterNot(@NotNull short[] sArr, @NotNull Function1<? super Short, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        for (short s2 : sArr) {
            if (!predicate.invoke(Short.valueOf(s2)).booleanValue()) {
                arrayList.add(Short.valueOf(s2));
            }
        }
        return arrayList;
    }

    @NotNull
    public static final List<Boolean> filterNot(@NotNull boolean[] zArr, @NotNull Function1<? super Boolean, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        for (boolean z : zArr) {
            if (!predicate.invoke(Boolean.valueOf(z)).booleanValue()) {
                arrayList.add(Boolean.valueOf(z));
            }
        }
        return arrayList;
    }

    @NotNull
    public static final <T> List<T> filterNotNull(@NotNull T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        return (List) filterNotNullTo(tArr, new ArrayList());
    }

    @NotNull
    public static final <C extends Collection<? super T>, T> C filterNotNullTo(@NotNull T[] tArr, @NotNull C destination) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        for (T t2 : tArr) {
            if (t2 != null) {
                destination.add(t2);
            }
        }
        return destination;
    }

    @NotNull
    public static final <C extends Collection<? super Byte>> C filterNotTo(@NotNull byte[] bArr, @NotNull C destination, @NotNull Function1<? super Byte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (byte b2 : bArr) {
            if (!predicate.invoke(Byte.valueOf(b2)).booleanValue()) {
                destination.add(Byte.valueOf(b2));
            }
        }
        return destination;
    }

    @NotNull
    public static final <C extends Collection<? super Character>> C filterNotTo(@NotNull char[] cArr, @NotNull C destination, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (char c2 : cArr) {
            if (!predicate.invoke(Character.valueOf(c2)).booleanValue()) {
                destination.add(Character.valueOf(c2));
            }
        }
        return destination;
    }

    @NotNull
    public static final <C extends Collection<? super Double>> C filterNotTo(@NotNull double[] dArr, @NotNull C destination, @NotNull Function1<? super Double, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (double d2 : dArr) {
            if (!predicate.invoke(Double.valueOf(d2)).booleanValue()) {
                destination.add(Double.valueOf(d2));
            }
        }
        return destination;
    }

    @NotNull
    public static final <C extends Collection<? super Float>> C filterNotTo(@NotNull float[] fArr, @NotNull C destination, @NotNull Function1<? super Float, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (float f2 : fArr) {
            if (!predicate.invoke(Float.valueOf(f2)).booleanValue()) {
                destination.add(Float.valueOf(f2));
            }
        }
        return destination;
    }

    @NotNull
    public static final <C extends Collection<? super Integer>> C filterNotTo(@NotNull int[] iArr, @NotNull C destination, @NotNull Function1<? super Integer, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (int i2 : iArr) {
            if (!predicate.invoke(Integer.valueOf(i2)).booleanValue()) {
                destination.add(Integer.valueOf(i2));
            }
        }
        return destination;
    }

    @NotNull
    public static final <C extends Collection<? super Long>> C filterNotTo(@NotNull long[] jArr, @NotNull C destination, @NotNull Function1<? super Long, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (long j2 : jArr) {
            if (!predicate.invoke(Long.valueOf(j2)).booleanValue()) {
                destination.add(Long.valueOf(j2));
            }
        }
        return destination;
    }

    @NotNull
    public static final <T, C extends Collection<? super T>> C filterNotTo(@NotNull T[] tArr, @NotNull C destination, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (T t2 : tArr) {
            if (!predicate.invoke(t2).booleanValue()) {
                destination.add(t2);
            }
        }
        return destination;
    }

    @NotNull
    public static final <C extends Collection<? super Short>> C filterNotTo(@NotNull short[] sArr, @NotNull C destination, @NotNull Function1<? super Short, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (short s2 : sArr) {
            if (!predicate.invoke(Short.valueOf(s2)).booleanValue()) {
                destination.add(Short.valueOf(s2));
            }
        }
        return destination;
    }

    @NotNull
    public static final <C extends Collection<? super Boolean>> C filterNotTo(@NotNull boolean[] zArr, @NotNull C destination, @NotNull Function1<? super Boolean, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (boolean z : zArr) {
            if (!predicate.invoke(Boolean.valueOf(z)).booleanValue()) {
                destination.add(Boolean.valueOf(z));
            }
        }
        return destination;
    }

    @NotNull
    public static final <C extends Collection<? super Byte>> C filterTo(@NotNull byte[] bArr, @NotNull C destination, @NotNull Function1<? super Byte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (byte b2 : bArr) {
            if (predicate.invoke(Byte.valueOf(b2)).booleanValue()) {
                destination.add(Byte.valueOf(b2));
            }
        }
        return destination;
    }

    @NotNull
    public static final <C extends Collection<? super Character>> C filterTo(@NotNull char[] cArr, @NotNull C destination, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (char c2 : cArr) {
            if (predicate.invoke(Character.valueOf(c2)).booleanValue()) {
                destination.add(Character.valueOf(c2));
            }
        }
        return destination;
    }

    @NotNull
    public static final <C extends Collection<? super Double>> C filterTo(@NotNull double[] dArr, @NotNull C destination, @NotNull Function1<? super Double, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (double d2 : dArr) {
            if (predicate.invoke(Double.valueOf(d2)).booleanValue()) {
                destination.add(Double.valueOf(d2));
            }
        }
        return destination;
    }

    @NotNull
    public static final <C extends Collection<? super Float>> C filterTo(@NotNull float[] fArr, @NotNull C destination, @NotNull Function1<? super Float, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (float f2 : fArr) {
            if (predicate.invoke(Float.valueOf(f2)).booleanValue()) {
                destination.add(Float.valueOf(f2));
            }
        }
        return destination;
    }

    @NotNull
    public static final <C extends Collection<? super Integer>> C filterTo(@NotNull int[] iArr, @NotNull C destination, @NotNull Function1<? super Integer, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (int i2 : iArr) {
            if (predicate.invoke(Integer.valueOf(i2)).booleanValue()) {
                destination.add(Integer.valueOf(i2));
            }
        }
        return destination;
    }

    @NotNull
    public static final <C extends Collection<? super Long>> C filterTo(@NotNull long[] jArr, @NotNull C destination, @NotNull Function1<? super Long, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (long j2 : jArr) {
            if (predicate.invoke(Long.valueOf(j2)).booleanValue()) {
                destination.add(Long.valueOf(j2));
            }
        }
        return destination;
    }

    @NotNull
    public static final <T, C extends Collection<? super T>> C filterTo(@NotNull T[] tArr, @NotNull C destination, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (T t2 : tArr) {
            if (predicate.invoke(t2).booleanValue()) {
                destination.add(t2);
            }
        }
        return destination;
    }

    @NotNull
    public static final <C extends Collection<? super Short>> C filterTo(@NotNull short[] sArr, @NotNull C destination, @NotNull Function1<? super Short, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (short s2 : sArr) {
            if (predicate.invoke(Short.valueOf(s2)).booleanValue()) {
                destination.add(Short.valueOf(s2));
            }
        }
        return destination;
    }

    @NotNull
    public static final <C extends Collection<? super Boolean>> C filterTo(@NotNull boolean[] zArr, @NotNull C destination, @NotNull Function1<? super Boolean, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (boolean z : zArr) {
            if (predicate.invoke(Boolean.valueOf(z)).booleanValue()) {
                destination.add(Boolean.valueOf(z));
            }
        }
        return destination;
    }

    @InlineOnly
    private static final Boolean find(boolean[] zArr, Function1<? super Boolean, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (boolean z : zArr) {
            if (predicate.invoke(Boolean.valueOf(z)).booleanValue()) {
                return Boolean.valueOf(z);
            }
        }
        return null;
    }

    @InlineOnly
    private static final Byte find(byte[] bArr, Function1<? super Byte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (byte b2 : bArr) {
            if (predicate.invoke(Byte.valueOf(b2)).booleanValue()) {
                return Byte.valueOf(b2);
            }
        }
        return null;
    }

    @InlineOnly
    private static final Character find(char[] cArr, Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (char c2 : cArr) {
            if (predicate.invoke(Character.valueOf(c2)).booleanValue()) {
                return Character.valueOf(c2);
            }
        }
        return null;
    }

    @InlineOnly
    private static final Double find(double[] dArr, Function1<? super Double, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (double d2 : dArr) {
            if (predicate.invoke(Double.valueOf(d2)).booleanValue()) {
                return Double.valueOf(d2);
            }
        }
        return null;
    }

    @InlineOnly
    private static final Float find(float[] fArr, Function1<? super Float, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (float f2 : fArr) {
            if (predicate.invoke(Float.valueOf(f2)).booleanValue()) {
                return Float.valueOf(f2);
            }
        }
        return null;
    }

    @InlineOnly
    private static final Integer find(int[] iArr, Function1<? super Integer, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (int i2 : iArr) {
            if (predicate.invoke(Integer.valueOf(i2)).booleanValue()) {
                return Integer.valueOf(i2);
            }
        }
        return null;
    }

    @InlineOnly
    private static final Long find(long[] jArr, Function1<? super Long, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (long j2 : jArr) {
            if (predicate.invoke(Long.valueOf(j2)).booleanValue()) {
                return Long.valueOf(j2);
            }
        }
        return null;
    }

    @InlineOnly
    private static final <T> T find(T[] tArr, Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (T t2 : tArr) {
            if (predicate.invoke(t2).booleanValue()) {
                return t2;
            }
        }
        return null;
    }

    @InlineOnly
    private static final Short find(short[] sArr, Function1<? super Short, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (short s2 : sArr) {
            if (predicate.invoke(Short.valueOf(s2)).booleanValue()) {
                return Short.valueOf(s2);
            }
        }
        return null;
    }

    @InlineOnly
    private static final Boolean findLast(boolean[] zArr, Function1<? super Boolean, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = zArr.length - 1;
        if (length >= 0) {
            while (true) {
                int i2 = length - 1;
                boolean z = zArr[length];
                if (predicate.invoke(Boolean.valueOf(z)).booleanValue()) {
                    return Boolean.valueOf(z);
                }
                if (i2 < 0) {
                    break;
                }
                length = i2;
            }
        }
        return null;
    }

    @InlineOnly
    private static final Byte findLast(byte[] bArr, Function1<? super Byte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = bArr.length - 1;
        if (length >= 0) {
            while (true) {
                int i2 = length - 1;
                byte b2 = bArr[length];
                if (predicate.invoke(Byte.valueOf(b2)).booleanValue()) {
                    return Byte.valueOf(b2);
                }
                if (i2 < 0) {
                    break;
                }
                length = i2;
            }
        }
        return null;
    }

    @InlineOnly
    private static final Character findLast(char[] cArr, Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = cArr.length - 1;
        if (length >= 0) {
            while (true) {
                int i2 = length - 1;
                char c2 = cArr[length];
                if (predicate.invoke(Character.valueOf(c2)).booleanValue()) {
                    return Character.valueOf(c2);
                }
                if (i2 < 0) {
                    break;
                }
                length = i2;
            }
        }
        return null;
    }

    @InlineOnly
    private static final Double findLast(double[] dArr, Function1<? super Double, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = dArr.length - 1;
        if (length >= 0) {
            while (true) {
                int i2 = length - 1;
                double d2 = dArr[length];
                if (predicate.invoke(Double.valueOf(d2)).booleanValue()) {
                    return Double.valueOf(d2);
                }
                if (i2 < 0) {
                    break;
                }
                length = i2;
            }
        }
        return null;
    }

    @InlineOnly
    private static final Float findLast(float[] fArr, Function1<? super Float, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = fArr.length - 1;
        if (length >= 0) {
            while (true) {
                int i2 = length - 1;
                float f2 = fArr[length];
                if (predicate.invoke(Float.valueOf(f2)).booleanValue()) {
                    return Float.valueOf(f2);
                }
                if (i2 < 0) {
                    break;
                }
                length = i2;
            }
        }
        return null;
    }

    @InlineOnly
    private static final Integer findLast(int[] iArr, Function1<? super Integer, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = iArr.length - 1;
        if (length >= 0) {
            while (true) {
                int i2 = length - 1;
                int i3 = iArr[length];
                if (predicate.invoke(Integer.valueOf(i3)).booleanValue()) {
                    return Integer.valueOf(i3);
                }
                if (i2 < 0) {
                    break;
                }
                length = i2;
            }
        }
        return null;
    }

    @InlineOnly
    private static final Long findLast(long[] jArr, Function1<? super Long, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = jArr.length - 1;
        if (length >= 0) {
            while (true) {
                int i2 = length - 1;
                long j2 = jArr[length];
                if (predicate.invoke(Long.valueOf(j2)).booleanValue()) {
                    return Long.valueOf(j2);
                }
                if (i2 < 0) {
                    break;
                }
                length = i2;
            }
        }
        return null;
    }

    @InlineOnly
    private static final <T> T findLast(T[] tArr, Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = tArr.length - 1;
        if (length >= 0) {
            while (true) {
                int i2 = length - 1;
                T t2 = tArr[length];
                if (predicate.invoke(t2).booleanValue()) {
                    return t2;
                }
                if (i2 < 0) {
                    break;
                }
                length = i2;
            }
        }
        return null;
    }

    @InlineOnly
    private static final Short findLast(short[] sArr, Function1<? super Short, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = sArr.length - 1;
        if (length >= 0) {
            while (true) {
                int i2 = length - 1;
                short s2 = sArr[length];
                if (predicate.invoke(Short.valueOf(s2)).booleanValue()) {
                    return Short.valueOf(s2);
                }
                if (i2 < 0) {
                    break;
                }
                length = i2;
            }
        }
        return null;
    }

    public static byte first(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        if (bArr.length == 0) {
            throw new NoSuchElementException("Array is empty.");
        }
        return bArr[0];
    }

    public static final byte first(@NotNull byte[] bArr, @NotNull Function1<? super Byte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (byte b2 : bArr) {
            if (predicate.invoke(Byte.valueOf(b2)).booleanValue()) {
                return b2;
            }
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    public static final char first(@NotNull char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        if (cArr.length == 0) {
            throw new NoSuchElementException("Array is empty.");
        }
        return cArr[0];
    }

    public static final char first(@NotNull char[] cArr, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (char c2 : cArr) {
            if (predicate.invoke(Character.valueOf(c2)).booleanValue()) {
                return c2;
            }
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    public static final double first(@NotNull double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        if (dArr.length == 0) {
            throw new NoSuchElementException("Array is empty.");
        }
        return dArr[0];
    }

    public static final double first(@NotNull double[] dArr, @NotNull Function1<? super Double, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (double d2 : dArr) {
            if (predicate.invoke(Double.valueOf(d2)).booleanValue()) {
                return d2;
            }
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    public static final float first(@NotNull float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        if (fArr.length == 0) {
            throw new NoSuchElementException("Array is empty.");
        }
        return fArr[0];
    }

    public static final float first(@NotNull float[] fArr, @NotNull Function1<? super Float, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (float f2 : fArr) {
            if (predicate.invoke(Float.valueOf(f2)).booleanValue()) {
                return f2;
            }
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    public static int first(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        if (iArr.length == 0) {
            throw new NoSuchElementException("Array is empty.");
        }
        return iArr[0];
    }

    public static final int first(@NotNull int[] iArr, @NotNull Function1<? super Integer, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (int i2 : iArr) {
            if (predicate.invoke(Integer.valueOf(i2)).booleanValue()) {
                return i2;
            }
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    public static long first(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        if (jArr.length == 0) {
            throw new NoSuchElementException("Array is empty.");
        }
        return jArr[0];
    }

    public static final long first(@NotNull long[] jArr, @NotNull Function1<? super Long, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (long j2 : jArr) {
            if (predicate.invoke(Long.valueOf(j2)).booleanValue()) {
                return j2;
            }
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    public static <T> T first(@NotNull T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        if (tArr.length == 0) {
            throw new NoSuchElementException("Array is empty.");
        }
        return tArr[0];
    }

    public static final <T> T first(@NotNull T[] tArr, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (T t2 : tArr) {
            if (predicate.invoke(t2).booleanValue()) {
                return t2;
            }
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    public static short first(@NotNull short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        if (sArr.length == 0) {
            throw new NoSuchElementException("Array is empty.");
        }
        return sArr[0];
    }

    public static final short first(@NotNull short[] sArr, @NotNull Function1<? super Short, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (short s2 : sArr) {
            if (predicate.invoke(Short.valueOf(s2)).booleanValue()) {
                return s2;
            }
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    public static final boolean first(@NotNull boolean[] zArr) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        if (zArr.length == 0) {
            throw new NoSuchElementException("Array is empty.");
        }
        return zArr[0];
    }

    public static final boolean first(@NotNull boolean[] zArr, @NotNull Function1<? super Boolean, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (boolean z : zArr) {
            if (predicate.invoke(Boolean.valueOf(z)).booleanValue()) {
                return z;
            }
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    private static final <T, R> R firstNotNullOf(T[] tArr, Function1<? super T, ? extends R> transform) {
        R r2;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int length = tArr.length;
        int i2 = 0;
        while (true) {
            if (i2 < length) {
                r2 = transform.invoke(tArr[i2]);
                if (r2 != null) {
                    break;
                }
                i2++;
            } else {
                r2 = null;
                break;
            }
        }
        if (r2 != null) {
            return r2;
        }
        throw new NoSuchElementException("No element of the array was transformed to a non-null value.");
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    private static final <T, R> R firstNotNullOfOrNull(T[] tArr, Function1<? super T, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        for (T t2 : tArr) {
            R invoke = transform.invoke(t2);
            if (invoke != null) {
                return invoke;
            }
        }
        return null;
    }

    @Nullable
    public static final Boolean firstOrNull(@NotNull boolean[] zArr) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        if (zArr.length == 0) {
            return null;
        }
        return Boolean.valueOf(zArr[0]);
    }

    @Nullable
    public static final Boolean firstOrNull(@NotNull boolean[] zArr, @NotNull Function1<? super Boolean, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (boolean z : zArr) {
            if (predicate.invoke(Boolean.valueOf(z)).booleanValue()) {
                return Boolean.valueOf(z);
            }
        }
        return null;
    }

    @Nullable
    public static final Byte firstOrNull(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        if (bArr.length == 0) {
            return null;
        }
        return Byte.valueOf(bArr[0]);
    }

    @Nullable
    public static final Byte firstOrNull(@NotNull byte[] bArr, @NotNull Function1<? super Byte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (byte b2 : bArr) {
            if (predicate.invoke(Byte.valueOf(b2)).booleanValue()) {
                return Byte.valueOf(b2);
            }
        }
        return null;
    }

    @Nullable
    public static final Character firstOrNull(@NotNull char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        if (cArr.length == 0) {
            return null;
        }
        return Character.valueOf(cArr[0]);
    }

    @Nullable
    public static final Character firstOrNull(@NotNull char[] cArr, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (char c2 : cArr) {
            if (predicate.invoke(Character.valueOf(c2)).booleanValue()) {
                return Character.valueOf(c2);
            }
        }
        return null;
    }

    @Nullable
    public static final Double firstOrNull(@NotNull double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        if (dArr.length == 0) {
            return null;
        }
        return Double.valueOf(dArr[0]);
    }

    @Nullable
    public static final Double firstOrNull(@NotNull double[] dArr, @NotNull Function1<? super Double, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (double d2 : dArr) {
            if (predicate.invoke(Double.valueOf(d2)).booleanValue()) {
                return Double.valueOf(d2);
            }
        }
        return null;
    }

    @Nullable
    public static final Float firstOrNull(@NotNull float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        if (fArr.length == 0) {
            return null;
        }
        return Float.valueOf(fArr[0]);
    }

    @Nullable
    public static final Float firstOrNull(@NotNull float[] fArr, @NotNull Function1<? super Float, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (float f2 : fArr) {
            if (predicate.invoke(Float.valueOf(f2)).booleanValue()) {
                return Float.valueOf(f2);
            }
        }
        return null;
    }

    @Nullable
    public static final Integer firstOrNull(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        if (iArr.length == 0) {
            return null;
        }
        return Integer.valueOf(iArr[0]);
    }

    @Nullable
    public static final Integer firstOrNull(@NotNull int[] iArr, @NotNull Function1<? super Integer, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (int i2 : iArr) {
            if (predicate.invoke(Integer.valueOf(i2)).booleanValue()) {
                return Integer.valueOf(i2);
            }
        }
        return null;
    }

    @Nullable
    public static final Long firstOrNull(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        if (jArr.length == 0) {
            return null;
        }
        return Long.valueOf(jArr[0]);
    }

    @Nullable
    public static final Long firstOrNull(@NotNull long[] jArr, @NotNull Function1<? super Long, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (long j2 : jArr) {
            if (predicate.invoke(Long.valueOf(j2)).booleanValue()) {
                return Long.valueOf(j2);
            }
        }
        return null;
    }

    @Nullable
    public static final <T> T firstOrNull(@NotNull T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        if (tArr.length == 0) {
            return null;
        }
        return tArr[0];
    }

    @Nullable
    public static final <T> T firstOrNull(@NotNull T[] tArr, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (T t2 : tArr) {
            if (predicate.invoke(t2).booleanValue()) {
                return t2;
            }
        }
        return null;
    }

    @Nullable
    public static final Short firstOrNull(@NotNull short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        if (sArr.length == 0) {
            return null;
        }
        return Short.valueOf(sArr[0]);
    }

    @Nullable
    public static final Short firstOrNull(@NotNull short[] sArr, @NotNull Function1<? super Short, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (short s2 : sArr) {
            if (predicate.invoke(Short.valueOf(s2)).booleanValue()) {
                return Short.valueOf(s2);
            }
        }
        return null;
    }

    @NotNull
    public static final <R> List<R> flatMap(@NotNull byte[] bArr, @NotNull Function1<? super Byte, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList();
        for (byte b2 : bArr) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, transform.invoke(Byte.valueOf(b2)));
        }
        return arrayList;
    }

    @NotNull
    public static final <R> List<R> flatMap(@NotNull char[] cArr, @NotNull Function1<? super Character, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList();
        for (char c2 : cArr) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, transform.invoke(Character.valueOf(c2)));
        }
        return arrayList;
    }

    @NotNull
    public static final <R> List<R> flatMap(@NotNull double[] dArr, @NotNull Function1<? super Double, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList();
        for (double d2 : dArr) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, transform.invoke(Double.valueOf(d2)));
        }
        return arrayList;
    }

    @NotNull
    public static final <R> List<R> flatMap(@NotNull float[] fArr, @NotNull Function1<? super Float, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList();
        for (float f2 : fArr) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, transform.invoke(Float.valueOf(f2)));
        }
        return arrayList;
    }

    @NotNull
    public static final <R> List<R> flatMap(@NotNull int[] iArr, @NotNull Function1<? super Integer, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList();
        for (int i2 : iArr) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, transform.invoke(Integer.valueOf(i2)));
        }
        return arrayList;
    }

    @NotNull
    public static final <R> List<R> flatMap(@NotNull long[] jArr, @NotNull Function1<? super Long, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList();
        for (long j2 : jArr) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, transform.invoke(Long.valueOf(j2)));
        }
        return arrayList;
    }

    @NotNull
    public static final <T, R> List<R> flatMap(@NotNull T[] tArr, @NotNull Function1<? super T, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList();
        for (T t2 : tArr) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, transform.invoke(t2));
        }
        return arrayList;
    }

    @NotNull
    public static final <R> List<R> flatMap(@NotNull short[] sArr, @NotNull Function1<? super Short, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList();
        for (short s2 : sArr) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, transform.invoke(Short.valueOf(s2)));
        }
        return arrayList;
    }

    @NotNull
    public static final <R> List<R> flatMap(@NotNull boolean[] zArr, @NotNull Function1<? super Boolean, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList();
        for (boolean z : zArr) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, transform.invoke(Boolean.valueOf(z)));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "flatMapIndexedIterable")
    @OverloadResolutionByLambdaReturnType
    private static final <R> List<R> flatMapIndexedIterable(byte[] bArr, Function2<? super Integer, ? super Byte, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList();
        int length = bArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, transform.invoke(Integer.valueOf(i3), Byte.valueOf(bArr[i2])));
            i2++;
            i3++;
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "flatMapIndexedIterable")
    @OverloadResolutionByLambdaReturnType
    private static final <R> List<R> flatMapIndexedIterable(char[] cArr, Function2<? super Integer, ? super Character, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList();
        int length = cArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, transform.invoke(Integer.valueOf(i3), Character.valueOf(cArr[i2])));
            i2++;
            i3++;
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "flatMapIndexedIterable")
    @OverloadResolutionByLambdaReturnType
    private static final <R> List<R> flatMapIndexedIterable(double[] dArr, Function2<? super Integer, ? super Double, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList();
        int length = dArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, transform.invoke(Integer.valueOf(i3), Double.valueOf(dArr[i2])));
            i2++;
            i3++;
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "flatMapIndexedIterable")
    @OverloadResolutionByLambdaReturnType
    private static final <R> List<R> flatMapIndexedIterable(float[] fArr, Function2<? super Integer, ? super Float, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList();
        int length = fArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, transform.invoke(Integer.valueOf(i3), Float.valueOf(fArr[i2])));
            i2++;
            i3++;
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "flatMapIndexedIterable")
    @OverloadResolutionByLambdaReturnType
    private static final <R> List<R> flatMapIndexedIterable(int[] iArr, Function2<? super Integer, ? super Integer, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList();
        int length = iArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, transform.invoke(Integer.valueOf(i3), Integer.valueOf(iArr[i2])));
            i2++;
            i3++;
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "flatMapIndexedIterable")
    @OverloadResolutionByLambdaReturnType
    private static final <R> List<R> flatMapIndexedIterable(long[] jArr, Function2<? super Integer, ? super Long, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList();
        int length = jArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, transform.invoke(Integer.valueOf(i3), Long.valueOf(jArr[i2])));
            i2++;
            i3++;
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "flatMapIndexedIterable")
    @OverloadResolutionByLambdaReturnType
    private static final <T, R> List<R> flatMapIndexedIterable(T[] tArr, Function2<? super Integer, ? super T, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList();
        int length = tArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, transform.invoke(Integer.valueOf(i3), tArr[i2]));
            i2++;
            i3++;
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "flatMapIndexedIterable")
    @OverloadResolutionByLambdaReturnType
    private static final <R> List<R> flatMapIndexedIterable(short[] sArr, Function2<? super Integer, ? super Short, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList();
        int length = sArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, transform.invoke(Integer.valueOf(i3), Short.valueOf(sArr[i2])));
            i2++;
            i3++;
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "flatMapIndexedIterable")
    @OverloadResolutionByLambdaReturnType
    private static final <R> List<R> flatMapIndexedIterable(boolean[] zArr, Function2<? super Integer, ? super Boolean, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList();
        int length = zArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, transform.invoke(Integer.valueOf(i3), Boolean.valueOf(zArr[i2])));
            i2++;
            i3++;
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "flatMapIndexedIterableTo")
    @OverloadResolutionByLambdaReturnType
    private static final <R, C extends Collection<? super R>> C flatMapIndexedIterableTo(byte[] bArr, C destination, Function2<? super Integer, ? super Byte, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int length = bArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            CollectionsKt__MutableCollectionsKt.addAll(destination, transform.invoke(Integer.valueOf(i3), Byte.valueOf(bArr[i2])));
            i2++;
            i3++;
        }
        return destination;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "flatMapIndexedIterableTo")
    @OverloadResolutionByLambdaReturnType
    private static final <R, C extends Collection<? super R>> C flatMapIndexedIterableTo(char[] cArr, C destination, Function2<? super Integer, ? super Character, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int length = cArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            CollectionsKt__MutableCollectionsKt.addAll(destination, transform.invoke(Integer.valueOf(i3), Character.valueOf(cArr[i2])));
            i2++;
            i3++;
        }
        return destination;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "flatMapIndexedIterableTo")
    @OverloadResolutionByLambdaReturnType
    private static final <R, C extends Collection<? super R>> C flatMapIndexedIterableTo(double[] dArr, C destination, Function2<? super Integer, ? super Double, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int length = dArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            CollectionsKt__MutableCollectionsKt.addAll(destination, transform.invoke(Integer.valueOf(i3), Double.valueOf(dArr[i2])));
            i2++;
            i3++;
        }
        return destination;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "flatMapIndexedIterableTo")
    @OverloadResolutionByLambdaReturnType
    private static final <R, C extends Collection<? super R>> C flatMapIndexedIterableTo(float[] fArr, C destination, Function2<? super Integer, ? super Float, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int length = fArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            CollectionsKt__MutableCollectionsKt.addAll(destination, transform.invoke(Integer.valueOf(i3), Float.valueOf(fArr[i2])));
            i2++;
            i3++;
        }
        return destination;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "flatMapIndexedIterableTo")
    @OverloadResolutionByLambdaReturnType
    private static final <R, C extends Collection<? super R>> C flatMapIndexedIterableTo(int[] iArr, C destination, Function2<? super Integer, ? super Integer, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int length = iArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            CollectionsKt__MutableCollectionsKt.addAll(destination, transform.invoke(Integer.valueOf(i3), Integer.valueOf(iArr[i2])));
            i2++;
            i3++;
        }
        return destination;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "flatMapIndexedIterableTo")
    @OverloadResolutionByLambdaReturnType
    private static final <R, C extends Collection<? super R>> C flatMapIndexedIterableTo(long[] jArr, C destination, Function2<? super Integer, ? super Long, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int length = jArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            CollectionsKt__MutableCollectionsKt.addAll(destination, transform.invoke(Integer.valueOf(i3), Long.valueOf(jArr[i2])));
            i2++;
            i3++;
        }
        return destination;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "flatMapIndexedIterableTo")
    @OverloadResolutionByLambdaReturnType
    private static final <T, R, C extends Collection<? super R>> C flatMapIndexedIterableTo(T[] tArr, C destination, Function2<? super Integer, ? super T, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int length = tArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            CollectionsKt__MutableCollectionsKt.addAll(destination, transform.invoke(Integer.valueOf(i3), tArr[i2]));
            i2++;
            i3++;
        }
        return destination;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "flatMapIndexedIterableTo")
    @OverloadResolutionByLambdaReturnType
    private static final <R, C extends Collection<? super R>> C flatMapIndexedIterableTo(short[] sArr, C destination, Function2<? super Integer, ? super Short, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int length = sArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            CollectionsKt__MutableCollectionsKt.addAll(destination, transform.invoke(Integer.valueOf(i3), Short.valueOf(sArr[i2])));
            i2++;
            i3++;
        }
        return destination;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "flatMapIndexedIterableTo")
    @OverloadResolutionByLambdaReturnType
    private static final <R, C extends Collection<? super R>> C flatMapIndexedIterableTo(boolean[] zArr, C destination, Function2<? super Integer, ? super Boolean, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int length = zArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            CollectionsKt__MutableCollectionsKt.addAll(destination, transform.invoke(Integer.valueOf(i3), Boolean.valueOf(zArr[i2])));
            i2++;
            i3++;
        }
        return destination;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "flatMapIndexedSequence")
    @OverloadResolutionByLambdaReturnType
    private static final <T, R> List<R> flatMapIndexedSequence(T[] tArr, Function2<? super Integer, ? super T, ? extends Sequence<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList();
        int length = tArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, transform.invoke(Integer.valueOf(i3), tArr[i2]));
            i2++;
            i3++;
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "flatMapIndexedSequenceTo")
    @OverloadResolutionByLambdaReturnType
    private static final <T, R, C extends Collection<? super R>> C flatMapIndexedSequenceTo(T[] tArr, C destination, Function2<? super Integer, ? super T, ? extends Sequence<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int length = tArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            CollectionsKt__MutableCollectionsKt.addAll(destination, transform.invoke(Integer.valueOf(i3), tArr[i2]));
            i2++;
            i3++;
        }
        return destination;
    }

    @SinceKotlin(version = "1.4")
    @JvmName(name = "flatMapSequence")
    @NotNull
    @OverloadResolutionByLambdaReturnType
    public static final <T, R> List<R> flatMapSequence(@NotNull T[] tArr, @NotNull Function1<? super T, ? extends Sequence<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList();
        for (T t2 : tArr) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, transform.invoke(t2));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @JvmName(name = "flatMapSequenceTo")
    @NotNull
    @OverloadResolutionByLambdaReturnType
    public static final <T, R, C extends Collection<? super R>> C flatMapSequenceTo(@NotNull T[] tArr, @NotNull C destination, @NotNull Function1<? super T, ? extends Sequence<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        for (T t2 : tArr) {
            CollectionsKt__MutableCollectionsKt.addAll(destination, transform.invoke(t2));
        }
        return destination;
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C flatMapTo(@NotNull byte[] bArr, @NotNull C destination, @NotNull Function1<? super Byte, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        for (byte b2 : bArr) {
            CollectionsKt__MutableCollectionsKt.addAll(destination, transform.invoke(Byte.valueOf(b2)));
        }
        return destination;
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C flatMapTo(@NotNull char[] cArr, @NotNull C destination, @NotNull Function1<? super Character, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        for (char c2 : cArr) {
            CollectionsKt__MutableCollectionsKt.addAll(destination, transform.invoke(Character.valueOf(c2)));
        }
        return destination;
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C flatMapTo(@NotNull double[] dArr, @NotNull C destination, @NotNull Function1<? super Double, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        for (double d2 : dArr) {
            CollectionsKt__MutableCollectionsKt.addAll(destination, transform.invoke(Double.valueOf(d2)));
        }
        return destination;
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C flatMapTo(@NotNull float[] fArr, @NotNull C destination, @NotNull Function1<? super Float, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        for (float f2 : fArr) {
            CollectionsKt__MutableCollectionsKt.addAll(destination, transform.invoke(Float.valueOf(f2)));
        }
        return destination;
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C flatMapTo(@NotNull int[] iArr, @NotNull C destination, @NotNull Function1<? super Integer, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        for (int i2 : iArr) {
            CollectionsKt__MutableCollectionsKt.addAll(destination, transform.invoke(Integer.valueOf(i2)));
        }
        return destination;
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C flatMapTo(@NotNull long[] jArr, @NotNull C destination, @NotNull Function1<? super Long, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        for (long j2 : jArr) {
            CollectionsKt__MutableCollectionsKt.addAll(destination, transform.invoke(Long.valueOf(j2)));
        }
        return destination;
    }

    @NotNull
    public static final <T, R, C extends Collection<? super R>> C flatMapTo(@NotNull T[] tArr, @NotNull C destination, @NotNull Function1<? super T, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        for (T t2 : tArr) {
            CollectionsKt__MutableCollectionsKt.addAll(destination, transform.invoke(t2));
        }
        return destination;
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C flatMapTo(@NotNull short[] sArr, @NotNull C destination, @NotNull Function1<? super Short, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        for (short s2 : sArr) {
            CollectionsKt__MutableCollectionsKt.addAll(destination, transform.invoke(Short.valueOf(s2)));
        }
        return destination;
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C flatMapTo(@NotNull boolean[] zArr, @NotNull C destination, @NotNull Function1<? super Boolean, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        for (boolean z : zArr) {
            CollectionsKt__MutableCollectionsKt.addAll(destination, transform.invoke(Boolean.valueOf(z)));
        }
        return destination;
    }

    public static final <R> R fold(@NotNull byte[] bArr, R r2, @NotNull Function2<? super R, ? super Byte, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        for (byte b2 : bArr) {
            r2 = operation.invoke(r2, Byte.valueOf(b2));
        }
        return r2;
    }

    public static final <R> R fold(@NotNull char[] cArr, R r2, @NotNull Function2<? super R, ? super Character, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        for (char c2 : cArr) {
            r2 = operation.invoke(r2, Character.valueOf(c2));
        }
        return r2;
    }

    public static final <R> R fold(@NotNull double[] dArr, R r2, @NotNull Function2<? super R, ? super Double, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        for (double d2 : dArr) {
            r2 = operation.invoke(r2, Double.valueOf(d2));
        }
        return r2;
    }

    public static final <R> R fold(@NotNull float[] fArr, R r2, @NotNull Function2<? super R, ? super Float, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        for (float f2 : fArr) {
            r2 = operation.invoke(r2, Float.valueOf(f2));
        }
        return r2;
    }

    public static final <R> R fold(@NotNull int[] iArr, R r2, @NotNull Function2<? super R, ? super Integer, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        for (int i2 : iArr) {
            r2 = operation.invoke(r2, Integer.valueOf(i2));
        }
        return r2;
    }

    public static final <R> R fold(@NotNull long[] jArr, R r2, @NotNull Function2<? super R, ? super Long, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        for (long j2 : jArr) {
            r2 = operation.invoke(r2, Long.valueOf(j2));
        }
        return r2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <T, R> R fold(@NotNull T[] tArr, R r2, @NotNull Function2<? super R, ? super T, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        for (R.animator animatorVar : tArr) {
            r2 = operation.invoke(r2, animatorVar);
        }
        return r2;
    }

    public static final <R> R fold(@NotNull short[] sArr, R r2, @NotNull Function2<? super R, ? super Short, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        for (short s2 : sArr) {
            r2 = operation.invoke(r2, Short.valueOf(s2));
        }
        return r2;
    }

    public static final <R> R fold(@NotNull boolean[] zArr, R r2, @NotNull Function2<? super R, ? super Boolean, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        for (boolean z : zArr) {
            r2 = operation.invoke(r2, Boolean.valueOf(z));
        }
        return r2;
    }

    public static final <R> R foldIndexed(@NotNull byte[] bArr, R r2, @NotNull Function3<? super Integer, ? super R, ? super Byte, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int length = bArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            r2 = operation.invoke(Integer.valueOf(i3), r2, Byte.valueOf(bArr[i2]));
            i2++;
            i3++;
        }
        return r2;
    }

    public static final <R> R foldIndexed(@NotNull char[] cArr, R r2, @NotNull Function3<? super Integer, ? super R, ? super Character, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int length = cArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            r2 = operation.invoke(Integer.valueOf(i3), r2, Character.valueOf(cArr[i2]));
            i2++;
            i3++;
        }
        return r2;
    }

    public static final <R> R foldIndexed(@NotNull double[] dArr, R r2, @NotNull Function3<? super Integer, ? super R, ? super Double, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int length = dArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            r2 = operation.invoke(Integer.valueOf(i3), r2, Double.valueOf(dArr[i2]));
            i2++;
            i3++;
        }
        return r2;
    }

    public static final <R> R foldIndexed(@NotNull float[] fArr, R r2, @NotNull Function3<? super Integer, ? super R, ? super Float, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int length = fArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            r2 = operation.invoke(Integer.valueOf(i3), r2, Float.valueOf(fArr[i2]));
            i2++;
            i3++;
        }
        return r2;
    }

    public static final <R> R foldIndexed(@NotNull int[] iArr, R r2, @NotNull Function3<? super Integer, ? super R, ? super Integer, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int length = iArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            r2 = operation.invoke(Integer.valueOf(i3), r2, Integer.valueOf(iArr[i2]));
            i2++;
            i3++;
        }
        return r2;
    }

    public static final <R> R foldIndexed(@NotNull long[] jArr, R r2, @NotNull Function3<? super Integer, ? super R, ? super Long, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int length = jArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            r2 = operation.invoke(Integer.valueOf(i3), r2, Long.valueOf(jArr[i2]));
            i2++;
            i3++;
        }
        return r2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <T, R> R foldIndexed(@NotNull T[] tArr, R r2, @NotNull Function3<? super Integer, ? super R, ? super T, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int length = tArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            r2 = operation.invoke(Integer.valueOf(i3), r2, tArr[i2]);
            i2++;
            i3++;
        }
        return r2;
    }

    public static final <R> R foldIndexed(@NotNull short[] sArr, R r2, @NotNull Function3<? super Integer, ? super R, ? super Short, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int length = sArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            r2 = operation.invoke(Integer.valueOf(i3), r2, Short.valueOf(sArr[i2]));
            i2++;
            i3++;
        }
        return r2;
    }

    public static final <R> R foldIndexed(@NotNull boolean[] zArr, R r2, @NotNull Function3<? super Integer, ? super R, ? super Boolean, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int length = zArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            r2 = operation.invoke(Integer.valueOf(i3), r2, Boolean.valueOf(zArr[i2]));
            i2++;
            i3++;
        }
        return r2;
    }

    public static final <R> R foldRight(@NotNull byte[] bArr, R r2, @NotNull Function2<? super Byte, ? super R, ? extends R> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        for (lastIndex = getLastIndex(bArr); lastIndex >= 0; lastIndex--) {
            r2 = operation.invoke(Byte.valueOf(bArr[lastIndex]), r2);
        }
        return r2;
    }

    public static final <R> R foldRight(@NotNull char[] cArr, R r2, @NotNull Function2<? super Character, ? super R, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        for (int lastIndex = getLastIndex(cArr); lastIndex >= 0; lastIndex--) {
            r2 = operation.invoke(Character.valueOf(cArr[lastIndex]), r2);
        }
        return r2;
    }

    public static final <R> R foldRight(@NotNull double[] dArr, R r2, @NotNull Function2<? super Double, ? super R, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        for (int lastIndex = getLastIndex(dArr); lastIndex >= 0; lastIndex--) {
            r2 = operation.invoke(Double.valueOf(dArr[lastIndex]), r2);
        }
        return r2;
    }

    public static final <R> R foldRight(@NotNull float[] fArr, R r2, @NotNull Function2<? super Float, ? super R, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        for (int lastIndex = getLastIndex(fArr); lastIndex >= 0; lastIndex--) {
            r2 = operation.invoke(Float.valueOf(fArr[lastIndex]), r2);
        }
        return r2;
    }

    public static final <R> R foldRight(@NotNull int[] iArr, R r2, @NotNull Function2<? super Integer, ? super R, ? extends R> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        for (lastIndex = getLastIndex(iArr); lastIndex >= 0; lastIndex--) {
            r2 = operation.invoke(Integer.valueOf(iArr[lastIndex]), r2);
        }
        return r2;
    }

    public static final <R> R foldRight(@NotNull long[] jArr, R r2, @NotNull Function2<? super Long, ? super R, ? extends R> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        for (lastIndex = getLastIndex(jArr); lastIndex >= 0; lastIndex--) {
            r2 = operation.invoke(Long.valueOf(jArr[lastIndex]), r2);
        }
        return r2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <T, R> R foldRight(@NotNull T[] tArr, R r2, @NotNull Function2<? super T, ? super R, ? extends R> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        for (lastIndex = getLastIndex(tArr); lastIndex >= 0; lastIndex--) {
            r2 = operation.invoke(tArr[lastIndex], r2);
        }
        return r2;
    }

    public static final <R> R foldRight(@NotNull short[] sArr, R r2, @NotNull Function2<? super Short, ? super R, ? extends R> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        for (lastIndex = getLastIndex(sArr); lastIndex >= 0; lastIndex--) {
            r2 = operation.invoke(Short.valueOf(sArr[lastIndex]), r2);
        }
        return r2;
    }

    public static final <R> R foldRight(@NotNull boolean[] zArr, R r2, @NotNull Function2<? super Boolean, ? super R, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        for (int lastIndex = getLastIndex(zArr); lastIndex >= 0; lastIndex--) {
            r2 = operation.invoke(Boolean.valueOf(zArr[lastIndex]), r2);
        }
        return r2;
    }

    public static final <R> R foldRightIndexed(@NotNull byte[] bArr, R r2, @NotNull Function3<? super Integer, ? super Byte, ? super R, ? extends R> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        for (lastIndex = getLastIndex(bArr); lastIndex >= 0; lastIndex--) {
            r2 = operation.invoke(Integer.valueOf(lastIndex), Byte.valueOf(bArr[lastIndex]), r2);
        }
        return r2;
    }

    public static final <R> R foldRightIndexed(@NotNull char[] cArr, R r2, @NotNull Function3<? super Integer, ? super Character, ? super R, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        for (int lastIndex = getLastIndex(cArr); lastIndex >= 0; lastIndex--) {
            r2 = operation.invoke(Integer.valueOf(lastIndex), Character.valueOf(cArr[lastIndex]), r2);
        }
        return r2;
    }

    public static final <R> R foldRightIndexed(@NotNull double[] dArr, R r2, @NotNull Function3<? super Integer, ? super Double, ? super R, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        for (int lastIndex = getLastIndex(dArr); lastIndex >= 0; lastIndex--) {
            r2 = operation.invoke(Integer.valueOf(lastIndex), Double.valueOf(dArr[lastIndex]), r2);
        }
        return r2;
    }

    public static final <R> R foldRightIndexed(@NotNull float[] fArr, R r2, @NotNull Function3<? super Integer, ? super Float, ? super R, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        for (int lastIndex = getLastIndex(fArr); lastIndex >= 0; lastIndex--) {
            r2 = operation.invoke(Integer.valueOf(lastIndex), Float.valueOf(fArr[lastIndex]), r2);
        }
        return r2;
    }

    public static final <R> R foldRightIndexed(@NotNull int[] iArr, R r2, @NotNull Function3<? super Integer, ? super Integer, ? super R, ? extends R> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        for (lastIndex = getLastIndex(iArr); lastIndex >= 0; lastIndex--) {
            r2 = operation.invoke(Integer.valueOf(lastIndex), Integer.valueOf(iArr[lastIndex]), r2);
        }
        return r2;
    }

    public static final <R> R foldRightIndexed(@NotNull long[] jArr, R r2, @NotNull Function3<? super Integer, ? super Long, ? super R, ? extends R> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        for (lastIndex = getLastIndex(jArr); lastIndex >= 0; lastIndex--) {
            r2 = operation.invoke(Integer.valueOf(lastIndex), Long.valueOf(jArr[lastIndex]), r2);
        }
        return r2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <T, R> R foldRightIndexed(@NotNull T[] tArr, R r2, @NotNull Function3<? super Integer, ? super T, ? super R, ? extends R> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        for (lastIndex = getLastIndex(tArr); lastIndex >= 0; lastIndex--) {
            r2 = operation.invoke(Integer.valueOf(lastIndex), tArr[lastIndex], r2);
        }
        return r2;
    }

    public static final <R> R foldRightIndexed(@NotNull short[] sArr, R r2, @NotNull Function3<? super Integer, ? super Short, ? super R, ? extends R> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        for (lastIndex = getLastIndex(sArr); lastIndex >= 0; lastIndex--) {
            r2 = operation.invoke(Integer.valueOf(lastIndex), Short.valueOf(sArr[lastIndex]), r2);
        }
        return r2;
    }

    public static final <R> R foldRightIndexed(@NotNull boolean[] zArr, R r2, @NotNull Function3<? super Integer, ? super Boolean, ? super R, ? extends R> operation) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        for (int lastIndex = getLastIndex(zArr); lastIndex >= 0; lastIndex--) {
            r2 = operation.invoke(Integer.valueOf(lastIndex), Boolean.valueOf(zArr[lastIndex]), r2);
        }
        return r2;
    }

    public static final void forEach(@NotNull byte[] bArr, @NotNull Function1<? super Byte, Unit> action) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        for (byte b2 : bArr) {
            action.invoke(Byte.valueOf(b2));
        }
    }

    public static final void forEach(@NotNull char[] cArr, @NotNull Function1<? super Character, Unit> action) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        for (char c2 : cArr) {
            action.invoke(Character.valueOf(c2));
        }
    }

    public static final void forEach(@NotNull double[] dArr, @NotNull Function1<? super Double, Unit> action) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        for (double d2 : dArr) {
            action.invoke(Double.valueOf(d2));
        }
    }

    public static final void forEach(@NotNull float[] fArr, @NotNull Function1<? super Float, Unit> action) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        for (float f2 : fArr) {
            action.invoke(Float.valueOf(f2));
        }
    }

    public static final void forEach(@NotNull int[] iArr, @NotNull Function1<? super Integer, Unit> action) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        for (int i2 : iArr) {
            action.invoke(Integer.valueOf(i2));
        }
    }

    public static final void forEach(@NotNull long[] jArr, @NotNull Function1<? super Long, Unit> action) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        for (long j2 : jArr) {
            action.invoke(Long.valueOf(j2));
        }
    }

    public static final <T> void forEach(@NotNull T[] tArr, @NotNull Function1<? super T, Unit> action) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        for (T t2 : tArr) {
            action.invoke(t2);
        }
    }

    public static final void forEach(@NotNull short[] sArr, @NotNull Function1<? super Short, Unit> action) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        for (short s2 : sArr) {
            action.invoke(Short.valueOf(s2));
        }
    }

    public static final void forEach(@NotNull boolean[] zArr, @NotNull Function1<? super Boolean, Unit> action) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        for (boolean z : zArr) {
            action.invoke(Boolean.valueOf(z));
        }
    }

    public static final void forEachIndexed(@NotNull byte[] bArr, @NotNull Function2<? super Integer, ? super Byte, Unit> action) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        int length = bArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            action.invoke(Integer.valueOf(i3), Byte.valueOf(bArr[i2]));
            i2++;
            i3++;
        }
    }

    public static final void forEachIndexed(@NotNull char[] cArr, @NotNull Function2<? super Integer, ? super Character, Unit> action) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        int length = cArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            action.invoke(Integer.valueOf(i3), Character.valueOf(cArr[i2]));
            i2++;
            i3++;
        }
    }

    public static final void forEachIndexed(@NotNull double[] dArr, @NotNull Function2<? super Integer, ? super Double, Unit> action) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        int length = dArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            action.invoke(Integer.valueOf(i3), Double.valueOf(dArr[i2]));
            i2++;
            i3++;
        }
    }

    public static final void forEachIndexed(@NotNull float[] fArr, @NotNull Function2<? super Integer, ? super Float, Unit> action) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        int length = fArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            action.invoke(Integer.valueOf(i3), Float.valueOf(fArr[i2]));
            i2++;
            i3++;
        }
    }

    public static final void forEachIndexed(@NotNull int[] iArr, @NotNull Function2<? super Integer, ? super Integer, Unit> action) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        int length = iArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            action.invoke(Integer.valueOf(i3), Integer.valueOf(iArr[i2]));
            i2++;
            i3++;
        }
    }

    public static final void forEachIndexed(@NotNull long[] jArr, @NotNull Function2<? super Integer, ? super Long, Unit> action) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        int length = jArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            action.invoke(Integer.valueOf(i3), Long.valueOf(jArr[i2]));
            i2++;
            i3++;
        }
    }

    public static final <T> void forEachIndexed(@NotNull T[] tArr, @NotNull Function2<? super Integer, ? super T, Unit> action) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        int length = tArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            action.invoke(Integer.valueOf(i3), tArr[i2]);
            i2++;
            i3++;
        }
    }

    public static final void forEachIndexed(@NotNull short[] sArr, @NotNull Function2<? super Integer, ? super Short, Unit> action) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        int length = sArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            action.invoke(Integer.valueOf(i3), Short.valueOf(sArr[i2]));
            i2++;
            i3++;
        }
    }

    public static final void forEachIndexed(@NotNull boolean[] zArr, @NotNull Function2<? super Integer, ? super Boolean, Unit> action) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        int length = zArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            action.invoke(Integer.valueOf(i3), Boolean.valueOf(zArr[i2]));
            i2++;
            i3++;
        }
    }

    @NotNull
    public static IntRange getIndices(@NotNull byte[] bArr) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        lastIndex = getLastIndex(bArr);
        return new IntRange(0, lastIndex);
    }

    @NotNull
    public static final IntRange getIndices(@NotNull char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        return new IntRange(0, getLastIndex(cArr));
    }

    @NotNull
    public static final IntRange getIndices(@NotNull double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        return new IntRange(0, getLastIndex(dArr));
    }

    @NotNull
    public static final IntRange getIndices(@NotNull float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        return new IntRange(0, getLastIndex(fArr));
    }

    @NotNull
    public static IntRange getIndices(@NotNull int[] iArr) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        lastIndex = getLastIndex(iArr);
        return new IntRange(0, lastIndex);
    }

    @NotNull
    public static IntRange getIndices(@NotNull long[] jArr) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        lastIndex = getLastIndex(jArr);
        return new IntRange(0, lastIndex);
    }

    @NotNull
    public static final <T> IntRange getIndices(@NotNull T[] tArr) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        lastIndex = getLastIndex(tArr);
        return new IntRange(0, lastIndex);
    }

    @NotNull
    public static IntRange getIndices(@NotNull short[] sArr) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        lastIndex = getLastIndex(sArr);
        return new IntRange(0, lastIndex);
    }

    @NotNull
    public static final IntRange getIndices(@NotNull boolean[] zArr) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        return new IntRange(0, getLastIndex(zArr));
    }

    public static int getLastIndex(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        return bArr.length - 1;
    }

    public static final int getLastIndex(@NotNull char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        return cArr.length - 1;
    }

    public static final int getLastIndex(@NotNull double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        return dArr.length - 1;
    }

    public static final int getLastIndex(@NotNull float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        return fArr.length - 1;
    }

    public static int getLastIndex(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        return iArr.length - 1;
    }

    public static int getLastIndex(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        return jArr.length - 1;
    }

    public static <T> int getLastIndex(@NotNull T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        return tArr.length - 1;
    }

    public static int getLastIndex(@NotNull short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        return sArr.length - 1;
    }

    public static final int getLastIndex(@NotNull boolean[] zArr) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        return zArr.length - 1;
    }

    @InlineOnly
    private static final byte getOrElse(byte[] bArr, int i2, Function1<? super Integer, Byte> defaultValue) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        if (i2 >= 0) {
            lastIndex = getLastIndex(bArr);
            if (i2 <= lastIndex) {
                return bArr[i2];
            }
        }
        return defaultValue.invoke(Integer.valueOf(i2)).byteValue();
    }

    @InlineOnly
    private static final char getOrElse(char[] cArr, int i2, Function1<? super Integer, Character> defaultValue) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        return (i2 < 0 || i2 > getLastIndex(cArr)) ? defaultValue.invoke(Integer.valueOf(i2)).charValue() : cArr[i2];
    }

    @InlineOnly
    private static final double getOrElse(double[] dArr, int i2, Function1<? super Integer, Double> defaultValue) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        return (i2 < 0 || i2 > getLastIndex(dArr)) ? defaultValue.invoke(Integer.valueOf(i2)).doubleValue() : dArr[i2];
    }

    @InlineOnly
    private static final float getOrElse(float[] fArr, int i2, Function1<? super Integer, Float> defaultValue) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        return (i2 < 0 || i2 > getLastIndex(fArr)) ? defaultValue.invoke(Integer.valueOf(i2)).floatValue() : fArr[i2];
    }

    @InlineOnly
    private static final int getOrElse(int[] iArr, int i2, Function1<? super Integer, Integer> defaultValue) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        if (i2 >= 0) {
            lastIndex = getLastIndex(iArr);
            if (i2 <= lastIndex) {
                return iArr[i2];
            }
        }
        return defaultValue.invoke(Integer.valueOf(i2)).intValue();
    }

    @InlineOnly
    private static final long getOrElse(long[] jArr, int i2, Function1<? super Integer, Long> defaultValue) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        if (i2 >= 0) {
            lastIndex = getLastIndex(jArr);
            if (i2 <= lastIndex) {
                return jArr[i2];
            }
        }
        return defaultValue.invoke(Integer.valueOf(i2)).longValue();
    }

    @InlineOnly
    private static final <T> T getOrElse(T[] tArr, int i2, Function1<? super Integer, ? extends T> defaultValue) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        if (i2 >= 0) {
            lastIndex = getLastIndex(tArr);
            if (i2 <= lastIndex) {
                return tArr[i2];
            }
        }
        return defaultValue.invoke(Integer.valueOf(i2));
    }

    @InlineOnly
    private static final short getOrElse(short[] sArr, int i2, Function1<? super Integer, Short> defaultValue) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        if (i2 >= 0) {
            lastIndex = getLastIndex(sArr);
            if (i2 <= lastIndex) {
                return sArr[i2];
            }
        }
        return defaultValue.invoke(Integer.valueOf(i2)).shortValue();
    }

    @InlineOnly
    private static final boolean getOrElse(boolean[] zArr, int i2, Function1<? super Integer, Boolean> defaultValue) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        return (i2 < 0 || i2 > getLastIndex(zArr)) ? defaultValue.invoke(Integer.valueOf(i2)).booleanValue() : zArr[i2];
    }

    @Nullable
    public static final Boolean getOrNull(@NotNull boolean[] zArr, int i2) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        if (i2 < 0 || i2 > getLastIndex(zArr)) {
            return null;
        }
        return Boolean.valueOf(zArr[i2]);
    }

    @Nullable
    public static final Byte getOrNull(@NotNull byte[] bArr, int i2) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        if (i2 >= 0) {
            lastIndex = getLastIndex(bArr);
            if (i2 <= lastIndex) {
                return Byte.valueOf(bArr[i2]);
            }
        }
        return null;
    }

    @Nullable
    public static final Character getOrNull(@NotNull char[] cArr, int i2) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        if (i2 < 0 || i2 > getLastIndex(cArr)) {
            return null;
        }
        return Character.valueOf(cArr[i2]);
    }

    @Nullable
    public static final Double getOrNull(@NotNull double[] dArr, int i2) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        if (i2 < 0 || i2 > getLastIndex(dArr)) {
            return null;
        }
        return Double.valueOf(dArr[i2]);
    }

    @Nullable
    public static final Float getOrNull(@NotNull float[] fArr, int i2) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        if (i2 < 0 || i2 > getLastIndex(fArr)) {
            return null;
        }
        return Float.valueOf(fArr[i2]);
    }

    @Nullable
    public static final Integer getOrNull(@NotNull int[] iArr, int i2) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        if (i2 >= 0) {
            lastIndex = getLastIndex(iArr);
            if (i2 <= lastIndex) {
                return Integer.valueOf(iArr[i2]);
            }
        }
        return null;
    }

    @Nullable
    public static final Long getOrNull(@NotNull long[] jArr, int i2) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        if (i2 >= 0) {
            lastIndex = getLastIndex(jArr);
            if (i2 <= lastIndex) {
                return Long.valueOf(jArr[i2]);
            }
        }
        return null;
    }

    @Nullable
    public static <T> T getOrNull(@NotNull T[] tArr, int i2) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        if (i2 >= 0) {
            lastIndex = getLastIndex(tArr);
            if (i2 <= lastIndex) {
                return tArr[i2];
            }
        }
        return null;
    }

    @Nullable
    public static final Short getOrNull(@NotNull short[] sArr, int i2) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        if (i2 >= 0) {
            lastIndex = getLastIndex(sArr);
            if (i2 <= lastIndex) {
                return Short.valueOf(sArr[i2]);
            }
        }
        return null;
    }

    @NotNull
    public static final <K> Map<K, List<Byte>> groupBy(@NotNull byte[] bArr, @NotNull Function1<? super Byte, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (byte b2 : bArr) {
            K invoke = keySelector.invoke(Byte.valueOf(b2));
            Object obj = linkedHashMap.get(invoke);
            if (obj == null) {
                obj = new ArrayList();
                linkedHashMap.put(invoke, obj);
            }
            ((List) obj).add(Byte.valueOf(b2));
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K, V> Map<K, List<V>> groupBy(@NotNull byte[] bArr, @NotNull Function1<? super Byte, ? extends K> keySelector, @NotNull Function1<? super Byte, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (byte b2 : bArr) {
            K invoke = keySelector.invoke(Byte.valueOf(b2));
            List<V> list = linkedHashMap.get(invoke);
            if (list == null) {
                list = new ArrayList<>();
                linkedHashMap.put(invoke, list);
            }
            list.add(valueTransform.invoke(Byte.valueOf(b2)));
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K> Map<K, List<Character>> groupBy(@NotNull char[] cArr, @NotNull Function1<? super Character, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (char c2 : cArr) {
            K invoke = keySelector.invoke(Character.valueOf(c2));
            Object obj = linkedHashMap.get(invoke);
            if (obj == null) {
                obj = new ArrayList();
                linkedHashMap.put(invoke, obj);
            }
            ((List) obj).add(Character.valueOf(c2));
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K, V> Map<K, List<V>> groupBy(@NotNull char[] cArr, @NotNull Function1<? super Character, ? extends K> keySelector, @NotNull Function1<? super Character, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (char c2 : cArr) {
            K invoke = keySelector.invoke(Character.valueOf(c2));
            List<V> list = linkedHashMap.get(invoke);
            if (list == null) {
                list = new ArrayList<>();
                linkedHashMap.put(invoke, list);
            }
            list.add(valueTransform.invoke(Character.valueOf(c2)));
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K> Map<K, List<Double>> groupBy(@NotNull double[] dArr, @NotNull Function1<? super Double, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (double d2 : dArr) {
            K invoke = keySelector.invoke(Double.valueOf(d2));
            Object obj = linkedHashMap.get(invoke);
            if (obj == null) {
                obj = new ArrayList();
                linkedHashMap.put(invoke, obj);
            }
            ((List) obj).add(Double.valueOf(d2));
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K, V> Map<K, List<V>> groupBy(@NotNull double[] dArr, @NotNull Function1<? super Double, ? extends K> keySelector, @NotNull Function1<? super Double, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (double d2 : dArr) {
            K invoke = keySelector.invoke(Double.valueOf(d2));
            List<V> list = linkedHashMap.get(invoke);
            if (list == null) {
                list = new ArrayList<>();
                linkedHashMap.put(invoke, list);
            }
            list.add(valueTransform.invoke(Double.valueOf(d2)));
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K> Map<K, List<Float>> groupBy(@NotNull float[] fArr, @NotNull Function1<? super Float, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (float f2 : fArr) {
            K invoke = keySelector.invoke(Float.valueOf(f2));
            Object obj = linkedHashMap.get(invoke);
            if (obj == null) {
                obj = new ArrayList();
                linkedHashMap.put(invoke, obj);
            }
            ((List) obj).add(Float.valueOf(f2));
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K, V> Map<K, List<V>> groupBy(@NotNull float[] fArr, @NotNull Function1<? super Float, ? extends K> keySelector, @NotNull Function1<? super Float, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (float f2 : fArr) {
            K invoke = keySelector.invoke(Float.valueOf(f2));
            List<V> list = linkedHashMap.get(invoke);
            if (list == null) {
                list = new ArrayList<>();
                linkedHashMap.put(invoke, list);
            }
            list.add(valueTransform.invoke(Float.valueOf(f2)));
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K> Map<K, List<Integer>> groupBy(@NotNull int[] iArr, @NotNull Function1<? super Integer, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (int i2 : iArr) {
            K invoke = keySelector.invoke(Integer.valueOf(i2));
            Object obj = linkedHashMap.get(invoke);
            if (obj == null) {
                obj = new ArrayList();
                linkedHashMap.put(invoke, obj);
            }
            ((List) obj).add(Integer.valueOf(i2));
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K, V> Map<K, List<V>> groupBy(@NotNull int[] iArr, @NotNull Function1<? super Integer, ? extends K> keySelector, @NotNull Function1<? super Integer, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (int i2 : iArr) {
            K invoke = keySelector.invoke(Integer.valueOf(i2));
            List<V> list = linkedHashMap.get(invoke);
            if (list == null) {
                list = new ArrayList<>();
                linkedHashMap.put(invoke, list);
            }
            list.add(valueTransform.invoke(Integer.valueOf(i2)));
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K> Map<K, List<Long>> groupBy(@NotNull long[] jArr, @NotNull Function1<? super Long, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (long j2 : jArr) {
            K invoke = keySelector.invoke(Long.valueOf(j2));
            Object obj = linkedHashMap.get(invoke);
            if (obj == null) {
                obj = new ArrayList();
                linkedHashMap.put(invoke, obj);
            }
            ((List) obj).add(Long.valueOf(j2));
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K, V> Map<K, List<V>> groupBy(@NotNull long[] jArr, @NotNull Function1<? super Long, ? extends K> keySelector, @NotNull Function1<? super Long, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (long j2 : jArr) {
            K invoke = keySelector.invoke(Long.valueOf(j2));
            List<V> list = linkedHashMap.get(invoke);
            if (list == null) {
                list = new ArrayList<>();
                linkedHashMap.put(invoke, list);
            }
            list.add(valueTransform.invoke(Long.valueOf(j2)));
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <T, K> Map<K, List<T>> groupBy(@NotNull T[] tArr, @NotNull Function1<? super T, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (T t2 : tArr) {
            K invoke = keySelector.invoke(t2);
            Object obj = linkedHashMap.get(invoke);
            if (obj == null) {
                obj = new ArrayList();
                linkedHashMap.put(invoke, obj);
            }
            ((List) obj).add(t2);
        }
        return linkedHashMap;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public static final <T, K, V> Map<K, List<V>> groupBy(@NotNull T[] tArr, @NotNull Function1<? super T, ? extends K> keySelector, @NotNull Function1<? super T, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (R.animator animatorVar : tArr) {
            K invoke = keySelector.invoke(animatorVar);
            List<V> list = linkedHashMap.get(invoke);
            if (list == null) {
                list = new ArrayList<>();
                linkedHashMap.put(invoke, list);
            }
            list.add(valueTransform.invoke(animatorVar));
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K> Map<K, List<Short>> groupBy(@NotNull short[] sArr, @NotNull Function1<? super Short, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (short s2 : sArr) {
            K invoke = keySelector.invoke(Short.valueOf(s2));
            Object obj = linkedHashMap.get(invoke);
            if (obj == null) {
                obj = new ArrayList();
                linkedHashMap.put(invoke, obj);
            }
            ((List) obj).add(Short.valueOf(s2));
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K, V> Map<K, List<V>> groupBy(@NotNull short[] sArr, @NotNull Function1<? super Short, ? extends K> keySelector, @NotNull Function1<? super Short, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (short s2 : sArr) {
            K invoke = keySelector.invoke(Short.valueOf(s2));
            List<V> list = linkedHashMap.get(invoke);
            if (list == null) {
                list = new ArrayList<>();
                linkedHashMap.put(invoke, list);
            }
            list.add(valueTransform.invoke(Short.valueOf(s2)));
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K> Map<K, List<Boolean>> groupBy(@NotNull boolean[] zArr, @NotNull Function1<? super Boolean, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (boolean z : zArr) {
            K invoke = keySelector.invoke(Boolean.valueOf(z));
            Object obj = linkedHashMap.get(invoke);
            if (obj == null) {
                obj = new ArrayList();
                linkedHashMap.put(invoke, obj);
            }
            ((List) obj).add(Boolean.valueOf(z));
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K, V> Map<K, List<V>> groupBy(@NotNull boolean[] zArr, @NotNull Function1<? super Boolean, ? extends K> keySelector, @NotNull Function1<? super Boolean, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (boolean z : zArr) {
            K invoke = keySelector.invoke(Boolean.valueOf(z));
            List<V> list = linkedHashMap.get(invoke);
            if (list == null) {
                list = new ArrayList<>();
                linkedHashMap.put(invoke, list);
            }
            list.add(valueTransform.invoke(Boolean.valueOf(z)));
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K, M extends Map<? super K, List<Byte>>> M groupByTo(@NotNull byte[] bArr, @NotNull M destination, @NotNull Function1<? super Byte, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        for (byte b2 : bArr) {
            K invoke = keySelector.invoke(Byte.valueOf(b2));
            Object obj = destination.get(invoke);
            if (obj == null) {
                obj = new ArrayList();
                destination.put(invoke, obj);
            }
            ((List) obj).add(Byte.valueOf(b2));
        }
        return destination;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public static final <K, V, M extends Map<? super K, List<V>>> M groupByTo(@NotNull byte[] bArr, @NotNull M destination, @NotNull Function1<? super Byte, ? extends K> keySelector, @NotNull Function1<? super Byte, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        for (byte b2 : bArr) {
            K invoke = keySelector.invoke(Byte.valueOf(b2));
            Object obj = destination.get(invoke);
            if (obj == null) {
                obj = new ArrayList();
                destination.put(invoke, obj);
            }
            ((List) obj).add(valueTransform.invoke(Byte.valueOf(b2)));
        }
        return destination;
    }

    @NotNull
    public static final <K, M extends Map<? super K, List<Character>>> M groupByTo(@NotNull char[] cArr, @NotNull M destination, @NotNull Function1<? super Character, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        for (char c2 : cArr) {
            K invoke = keySelector.invoke(Character.valueOf(c2));
            Object obj = destination.get(invoke);
            if (obj == null) {
                obj = new ArrayList();
                destination.put(invoke, obj);
            }
            ((List) obj).add(Character.valueOf(c2));
        }
        return destination;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public static final <K, V, M extends Map<? super K, List<V>>> M groupByTo(@NotNull char[] cArr, @NotNull M destination, @NotNull Function1<? super Character, ? extends K> keySelector, @NotNull Function1<? super Character, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        for (char c2 : cArr) {
            K invoke = keySelector.invoke(Character.valueOf(c2));
            Object obj = destination.get(invoke);
            if (obj == null) {
                obj = new ArrayList();
                destination.put(invoke, obj);
            }
            ((List) obj).add(valueTransform.invoke(Character.valueOf(c2)));
        }
        return destination;
    }

    @NotNull
    public static final <K, M extends Map<? super K, List<Double>>> M groupByTo(@NotNull double[] dArr, @NotNull M destination, @NotNull Function1<? super Double, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        for (double d2 : dArr) {
            K invoke = keySelector.invoke(Double.valueOf(d2));
            Object obj = destination.get(invoke);
            if (obj == null) {
                obj = new ArrayList();
                destination.put(invoke, obj);
            }
            ((List) obj).add(Double.valueOf(d2));
        }
        return destination;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public static final <K, V, M extends Map<? super K, List<V>>> M groupByTo(@NotNull double[] dArr, @NotNull M destination, @NotNull Function1<? super Double, ? extends K> keySelector, @NotNull Function1<? super Double, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        for (double d2 : dArr) {
            K invoke = keySelector.invoke(Double.valueOf(d2));
            Object obj = destination.get(invoke);
            if (obj == null) {
                obj = new ArrayList();
                destination.put(invoke, obj);
            }
            ((List) obj).add(valueTransform.invoke(Double.valueOf(d2)));
        }
        return destination;
    }

    @NotNull
    public static final <K, M extends Map<? super K, List<Float>>> M groupByTo(@NotNull float[] fArr, @NotNull M destination, @NotNull Function1<? super Float, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        for (float f2 : fArr) {
            K invoke = keySelector.invoke(Float.valueOf(f2));
            Object obj = destination.get(invoke);
            if (obj == null) {
                obj = new ArrayList();
                destination.put(invoke, obj);
            }
            ((List) obj).add(Float.valueOf(f2));
        }
        return destination;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public static final <K, V, M extends Map<? super K, List<V>>> M groupByTo(@NotNull float[] fArr, @NotNull M destination, @NotNull Function1<? super Float, ? extends K> keySelector, @NotNull Function1<? super Float, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        for (float f2 : fArr) {
            K invoke = keySelector.invoke(Float.valueOf(f2));
            Object obj = destination.get(invoke);
            if (obj == null) {
                obj = new ArrayList();
                destination.put(invoke, obj);
            }
            ((List) obj).add(valueTransform.invoke(Float.valueOf(f2)));
        }
        return destination;
    }

    @NotNull
    public static final <K, M extends Map<? super K, List<Integer>>> M groupByTo(@NotNull int[] iArr, @NotNull M destination, @NotNull Function1<? super Integer, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        for (int i2 : iArr) {
            K invoke = keySelector.invoke(Integer.valueOf(i2));
            Object obj = destination.get(invoke);
            if (obj == null) {
                obj = new ArrayList();
                destination.put(invoke, obj);
            }
            ((List) obj).add(Integer.valueOf(i2));
        }
        return destination;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public static final <K, V, M extends Map<? super K, List<V>>> M groupByTo(@NotNull int[] iArr, @NotNull M destination, @NotNull Function1<? super Integer, ? extends K> keySelector, @NotNull Function1<? super Integer, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        for (int i2 : iArr) {
            K invoke = keySelector.invoke(Integer.valueOf(i2));
            Object obj = destination.get(invoke);
            if (obj == null) {
                obj = new ArrayList();
                destination.put(invoke, obj);
            }
            ((List) obj).add(valueTransform.invoke(Integer.valueOf(i2)));
        }
        return destination;
    }

    @NotNull
    public static final <K, M extends Map<? super K, List<Long>>> M groupByTo(@NotNull long[] jArr, @NotNull M destination, @NotNull Function1<? super Long, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        for (long j2 : jArr) {
            K invoke = keySelector.invoke(Long.valueOf(j2));
            Object obj = destination.get(invoke);
            if (obj == null) {
                obj = new ArrayList();
                destination.put(invoke, obj);
            }
            ((List) obj).add(Long.valueOf(j2));
        }
        return destination;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public static final <K, V, M extends Map<? super K, List<V>>> M groupByTo(@NotNull long[] jArr, @NotNull M destination, @NotNull Function1<? super Long, ? extends K> keySelector, @NotNull Function1<? super Long, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        for (long j2 : jArr) {
            K invoke = keySelector.invoke(Long.valueOf(j2));
            Object obj = destination.get(invoke);
            if (obj == null) {
                obj = new ArrayList();
                destination.put(invoke, obj);
            }
            ((List) obj).add(valueTransform.invoke(Long.valueOf(j2)));
        }
        return destination;
    }

    @NotNull
    public static final <T, K, M extends Map<? super K, List<T>>> M groupByTo(@NotNull T[] tArr, @NotNull M destination, @NotNull Function1<? super T, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        for (T t2 : tArr) {
            K invoke = keySelector.invoke(t2);
            Object obj = destination.get(invoke);
            if (obj == null) {
                obj = new ArrayList();
                destination.put(invoke, obj);
            }
            ((List) obj).add(t2);
        }
        return destination;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public static final <T, K, V, M extends Map<? super K, List<V>>> M groupByTo(@NotNull T[] tArr, @NotNull M destination, @NotNull Function1<? super T, ? extends K> keySelector, @NotNull Function1<? super T, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        for (T t2 : tArr) {
            K invoke = keySelector.invoke(t2);
            Object obj = destination.get(invoke);
            if (obj == null) {
                obj = new ArrayList();
                destination.put(invoke, obj);
            }
            ((List) obj).add(valueTransform.invoke(t2));
        }
        return destination;
    }

    @NotNull
    public static final <K, M extends Map<? super K, List<Short>>> M groupByTo(@NotNull short[] sArr, @NotNull M destination, @NotNull Function1<? super Short, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        for (short s2 : sArr) {
            K invoke = keySelector.invoke(Short.valueOf(s2));
            Object obj = destination.get(invoke);
            if (obj == null) {
                obj = new ArrayList();
                destination.put(invoke, obj);
            }
            ((List) obj).add(Short.valueOf(s2));
        }
        return destination;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public static final <K, V, M extends Map<? super K, List<V>>> M groupByTo(@NotNull short[] sArr, @NotNull M destination, @NotNull Function1<? super Short, ? extends K> keySelector, @NotNull Function1<? super Short, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        for (short s2 : sArr) {
            K invoke = keySelector.invoke(Short.valueOf(s2));
            Object obj = destination.get(invoke);
            if (obj == null) {
                obj = new ArrayList();
                destination.put(invoke, obj);
            }
            ((List) obj).add(valueTransform.invoke(Short.valueOf(s2)));
        }
        return destination;
    }

    @NotNull
    public static final <K, M extends Map<? super K, List<Boolean>>> M groupByTo(@NotNull boolean[] zArr, @NotNull M destination, @NotNull Function1<? super Boolean, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        for (boolean z : zArr) {
            K invoke = keySelector.invoke(Boolean.valueOf(z));
            Object obj = destination.get(invoke);
            if (obj == null) {
                obj = new ArrayList();
                destination.put(invoke, obj);
            }
            ((List) obj).add(Boolean.valueOf(z));
        }
        return destination;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public static final <K, V, M extends Map<? super K, List<V>>> M groupByTo(@NotNull boolean[] zArr, @NotNull M destination, @NotNull Function1<? super Boolean, ? extends K> keySelector, @NotNull Function1<? super Boolean, ? extends V> valueTransform) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        Intrinsics.checkNotNullParameter(valueTransform, "valueTransform");
        for (boolean z : zArr) {
            K invoke = keySelector.invoke(Boolean.valueOf(z));
            Object obj = destination.get(invoke);
            if (obj == null) {
                obj = new ArrayList();
                destination.put(invoke, obj);
            }
            ((List) obj).add(valueTransform.invoke(Boolean.valueOf(z)));
        }
        return destination;
    }

    @SinceKotlin(version = "1.1")
    @NotNull
    public static final <T, K> Grouping<T, K> groupingBy(@NotNull final T[] tArr, @NotNull final Function1<? super T, ? extends K> keySelector) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(keySelector, "keySelector");
        return new Grouping<T, K>() { // from class: kotlin.collections.ArraysKt___ArraysKt$groupingBy$1
            /* JADX WARN: Type inference failed for: r2v1, types: [java.lang.Object, K] */
            @Override // kotlin.collections.Grouping
            public K keyOf(T t2) {
                return keySelector.invoke(t2);
            }

            @Override // kotlin.collections.Grouping
            @NotNull
            public Iterator<T> sourceIterator() {
                return ArrayIteratorKt.iterator(tArr);
            }
        };
    }

    public static int indexOf(@NotNull byte[] bArr, byte b2) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        int length = bArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (b2 == bArr[i2]) {
                return i2;
            }
        }
        return -1;
    }

    public static final int indexOf(@NotNull char[] cArr, char c2) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        int length = cArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (c2 == cArr[i2]) {
                return i2;
            }
        }
        return -1;
    }

    @Deprecated(message = "The function has unclear behavior when searching for NaN or zero values and will be removed soon. Use 'indexOfFirst { it == element }' instead to continue using this behavior, or '.asList().indexOf(element: T)' to get the same search behavior as in a list.", replaceWith = @ReplaceWith(expression = "indexOfFirst { it == element }", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.6", warningSince = "1.4")
    public static final int indexOf(@NotNull double[] dArr, double d2) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        int length = dArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (d2 == dArr[i2]) {
                return i2;
            }
        }
        return -1;
    }

    @Deprecated(message = "The function has unclear behavior when searching for NaN or zero values and will be removed soon. Use 'indexOfFirst { it == element }' instead to continue using this behavior, or '.asList().indexOf(element: T)' to get the same search behavior as in a list.", replaceWith = @ReplaceWith(expression = "indexOfFirst { it == element }", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.6", warningSince = "1.4")
    public static final int indexOf(@NotNull float[] fArr, float f2) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        int length = fArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (f2 == fArr[i2]) {
                return i2;
            }
        }
        return -1;
    }

    public static int indexOf(@NotNull int[] iArr, int i2) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        int length = iArr.length;
        for (int i3 = 0; i3 < length; i3++) {
            if (i2 == iArr[i3]) {
                return i3;
            }
        }
        return -1;
    }

    public static int indexOf(@NotNull long[] jArr, long j2) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        int length = jArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (j2 == jArr[i2]) {
                return i2;
            }
        }
        return -1;
    }

    public static <T> int indexOf(@NotNull T[] tArr, T t2) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        int i2 = 0;
        if (t2 == null) {
            int length = tArr.length;
            while (i2 < length) {
                if (tArr[i2] == null) {
                    return i2;
                }
                i2++;
            }
            return -1;
        }
        int length2 = tArr.length;
        while (i2 < length2) {
            if (Intrinsics.areEqual(t2, tArr[i2])) {
                return i2;
            }
            i2++;
        }
        return -1;
    }

    public static int indexOf(@NotNull short[] sArr, short s2) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        int length = sArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (s2 == sArr[i2]) {
                return i2;
            }
        }
        return -1;
    }

    public static final int indexOf(@NotNull boolean[] zArr, boolean z) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        int length = zArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (z == zArr[i2]) {
                return i2;
            }
        }
        return -1;
    }

    public static final int indexOfFirst(@NotNull byte[] bArr, @NotNull Function1<? super Byte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = bArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (predicate.invoke(Byte.valueOf(bArr[i2])).booleanValue()) {
                return i2;
            }
        }
        return -1;
    }

    public static final int indexOfFirst(@NotNull char[] cArr, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = cArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (predicate.invoke(Character.valueOf(cArr[i2])).booleanValue()) {
                return i2;
            }
        }
        return -1;
    }

    public static final int indexOfFirst(@NotNull double[] dArr, @NotNull Function1<? super Double, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = dArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (predicate.invoke(Double.valueOf(dArr[i2])).booleanValue()) {
                return i2;
            }
        }
        return -1;
    }

    public static final int indexOfFirst(@NotNull float[] fArr, @NotNull Function1<? super Float, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = fArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (predicate.invoke(Float.valueOf(fArr[i2])).booleanValue()) {
                return i2;
            }
        }
        return -1;
    }

    public static final int indexOfFirst(@NotNull int[] iArr, @NotNull Function1<? super Integer, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = iArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (predicate.invoke(Integer.valueOf(iArr[i2])).booleanValue()) {
                return i2;
            }
        }
        return -1;
    }

    public static final int indexOfFirst(@NotNull long[] jArr, @NotNull Function1<? super Long, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = jArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (predicate.invoke(Long.valueOf(jArr[i2])).booleanValue()) {
                return i2;
            }
        }
        return -1;
    }

    public static final <T> int indexOfFirst(@NotNull T[] tArr, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = tArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (predicate.invoke(tArr[i2]).booleanValue()) {
                return i2;
            }
        }
        return -1;
    }

    public static final int indexOfFirst(@NotNull short[] sArr, @NotNull Function1<? super Short, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = sArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (predicate.invoke(Short.valueOf(sArr[i2])).booleanValue()) {
                return i2;
            }
        }
        return -1;
    }

    public static final int indexOfFirst(@NotNull boolean[] zArr, @NotNull Function1<? super Boolean, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = zArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (predicate.invoke(Boolean.valueOf(zArr[i2])).booleanValue()) {
                return i2;
            }
        }
        return -1;
    }

    public static final int indexOfLast(@NotNull byte[] bArr, @NotNull Function1<? super Byte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = bArr.length - 1;
        if (length >= 0) {
            while (true) {
                int i2 = length - 1;
                if (predicate.invoke(Byte.valueOf(bArr[length])).booleanValue()) {
                    return length;
                }
                if (i2 < 0) {
                    break;
                }
                length = i2;
            }
        }
        return -1;
    }

    public static final int indexOfLast(@NotNull char[] cArr, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = cArr.length - 1;
        if (length >= 0) {
            while (true) {
                int i2 = length - 1;
                if (predicate.invoke(Character.valueOf(cArr[length])).booleanValue()) {
                    return length;
                }
                if (i2 < 0) {
                    break;
                }
                length = i2;
            }
        }
        return -1;
    }

    public static final int indexOfLast(@NotNull double[] dArr, @NotNull Function1<? super Double, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = dArr.length - 1;
        if (length >= 0) {
            while (true) {
                int i2 = length - 1;
                if (predicate.invoke(Double.valueOf(dArr[length])).booleanValue()) {
                    return length;
                }
                if (i2 < 0) {
                    break;
                }
                length = i2;
            }
        }
        return -1;
    }

    public static final int indexOfLast(@NotNull float[] fArr, @NotNull Function1<? super Float, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = fArr.length - 1;
        if (length >= 0) {
            while (true) {
                int i2 = length - 1;
                if (predicate.invoke(Float.valueOf(fArr[length])).booleanValue()) {
                    return length;
                }
                if (i2 < 0) {
                    break;
                }
                length = i2;
            }
        }
        return -1;
    }

    public static final int indexOfLast(@NotNull int[] iArr, @NotNull Function1<? super Integer, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = iArr.length - 1;
        if (length >= 0) {
            while (true) {
                int i2 = length - 1;
                if (predicate.invoke(Integer.valueOf(iArr[length])).booleanValue()) {
                    return length;
                }
                if (i2 < 0) {
                    break;
                }
                length = i2;
            }
        }
        return -1;
    }

    public static final int indexOfLast(@NotNull long[] jArr, @NotNull Function1<? super Long, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = jArr.length - 1;
        if (length >= 0) {
            while (true) {
                int i2 = length - 1;
                if (predicate.invoke(Long.valueOf(jArr[length])).booleanValue()) {
                    return length;
                }
                if (i2 < 0) {
                    break;
                }
                length = i2;
            }
        }
        return -1;
    }

    public static final <T> int indexOfLast(@NotNull T[] tArr, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = tArr.length - 1;
        if (length >= 0) {
            while (true) {
                int i2 = length - 1;
                if (predicate.invoke(tArr[length]).booleanValue()) {
                    return length;
                }
                if (i2 < 0) {
                    break;
                }
                length = i2;
            }
        }
        return -1;
    }

    public static final int indexOfLast(@NotNull short[] sArr, @NotNull Function1<? super Short, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = sArr.length - 1;
        if (length >= 0) {
            while (true) {
                int i2 = length - 1;
                if (predicate.invoke(Short.valueOf(sArr[length])).booleanValue()) {
                    return length;
                }
                if (i2 < 0) {
                    break;
                }
                length = i2;
            }
        }
        return -1;
    }

    public static final int indexOfLast(@NotNull boolean[] zArr, @NotNull Function1<? super Boolean, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = zArr.length - 1;
        if (length >= 0) {
            while (true) {
                int i2 = length - 1;
                if (predicate.invoke(Boolean.valueOf(zArr[length])).booleanValue()) {
                    return length;
                }
                if (i2 < 0) {
                    break;
                }
                length = i2;
            }
        }
        return -1;
    }

    @NotNull
    public static final Set<Byte> intersect(@NotNull byte[] bArr, @NotNull Iterable<Byte> other) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Set<Byte> mutableSet = toMutableSet(bArr);
        CollectionsKt__MutableCollectionsKt.retainAll(mutableSet, other);
        return mutableSet;
    }

    @NotNull
    public static final Set<Character> intersect(@NotNull char[] cArr, @NotNull Iterable<Character> other) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Set<Character> mutableSet = toMutableSet(cArr);
        CollectionsKt__MutableCollectionsKt.retainAll(mutableSet, other);
        return mutableSet;
    }

    @NotNull
    public static final Set<Double> intersect(@NotNull double[] dArr, @NotNull Iterable<Double> other) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Set<Double> mutableSet = toMutableSet(dArr);
        CollectionsKt__MutableCollectionsKt.retainAll(mutableSet, other);
        return mutableSet;
    }

    @NotNull
    public static final Set<Float> intersect(@NotNull float[] fArr, @NotNull Iterable<Float> other) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Set<Float> mutableSet = toMutableSet(fArr);
        CollectionsKt__MutableCollectionsKt.retainAll(mutableSet, other);
        return mutableSet;
    }

    @NotNull
    public static final Set<Integer> intersect(@NotNull int[] iArr, @NotNull Iterable<Integer> other) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Set<Integer> mutableSet = toMutableSet(iArr);
        CollectionsKt__MutableCollectionsKt.retainAll(mutableSet, other);
        return mutableSet;
    }

    @NotNull
    public static final Set<Long> intersect(@NotNull long[] jArr, @NotNull Iterable<Long> other) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Set<Long> mutableSet = toMutableSet(jArr);
        CollectionsKt__MutableCollectionsKt.retainAll(mutableSet, other);
        return mutableSet;
    }

    @NotNull
    public static final <T> Set<T> intersect(@NotNull T[] tArr, @NotNull Iterable<? extends T> other) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Set<T> mutableSet = toMutableSet(tArr);
        CollectionsKt__MutableCollectionsKt.retainAll(mutableSet, other);
        return mutableSet;
    }

    @NotNull
    public static final Set<Short> intersect(@NotNull short[] sArr, @NotNull Iterable<Short> other) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Set<Short> mutableSet = toMutableSet(sArr);
        CollectionsKt__MutableCollectionsKt.retainAll(mutableSet, other);
        return mutableSet;
    }

    @NotNull
    public static final Set<Boolean> intersect(@NotNull boolean[] zArr, @NotNull Iterable<Boolean> other) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Set<Boolean> mutableSet = toMutableSet(zArr);
        CollectionsKt__MutableCollectionsKt.retainAll(mutableSet, other);
        return mutableSet;
    }

    @InlineOnly
    private static final boolean isEmpty(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        return bArr.length == 0;
    }

    @InlineOnly
    private static final boolean isEmpty(char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        return cArr.length == 0;
    }

    @InlineOnly
    private static final boolean isEmpty(double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        return dArr.length == 0;
    }

    @InlineOnly
    private static final boolean isEmpty(float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        return fArr.length == 0;
    }

    @InlineOnly
    private static final boolean isEmpty(int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        return iArr.length == 0;
    }

    @InlineOnly
    private static final boolean isEmpty(long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        return jArr.length == 0;
    }

    @InlineOnly
    private static final <T> boolean isEmpty(T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        return tArr.length == 0;
    }

    @InlineOnly
    private static final boolean isEmpty(short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        return sArr.length == 0;
    }

    @InlineOnly
    private static final boolean isEmpty(boolean[] zArr) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        return zArr.length == 0;
    }

    @InlineOnly
    private static final boolean isNotEmpty(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        return !(bArr.length == 0);
    }

    @InlineOnly
    private static final boolean isNotEmpty(char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        return !(cArr.length == 0);
    }

    @InlineOnly
    private static final boolean isNotEmpty(double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        return !(dArr.length == 0);
    }

    @InlineOnly
    private static final boolean isNotEmpty(float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        return !(fArr.length == 0);
    }

    @InlineOnly
    private static final boolean isNotEmpty(int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        return !(iArr.length == 0);
    }

    @InlineOnly
    private static final boolean isNotEmpty(long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        return !(jArr.length == 0);
    }

    @InlineOnly
    private static final <T> boolean isNotEmpty(T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        return !(tArr.length == 0);
    }

    @InlineOnly
    private static final boolean isNotEmpty(short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        return !(sArr.length == 0);
    }

    @InlineOnly
    private static final boolean isNotEmpty(boolean[] zArr) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        return !(zArr.length == 0);
    }

    @NotNull
    public static final <A extends Appendable> A joinTo(@NotNull byte[] bArr, @NotNull A buffer, @NotNull CharSequence separator, @NotNull CharSequence prefix, @NotNull CharSequence postfix, int i2, @NotNull CharSequence truncated, @Nullable Function1<? super Byte, ? extends CharSequence> function1) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        Intrinsics.checkNotNullParameter(separator, "separator");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        Intrinsics.checkNotNullParameter(postfix, "postfix");
        Intrinsics.checkNotNullParameter(truncated, "truncated");
        buffer.append(prefix);
        int i3 = 0;
        for (byte b2 : bArr) {
            i3++;
            if (i3 > 1) {
                buffer.append(separator);
            }
            if (i2 >= 0 && i3 > i2) {
                break;
            }
            buffer.append(function1 != null ? function1.invoke(Byte.valueOf(b2)) : String.valueOf((int) b2));
        }
        if (i2 >= 0 && i3 > i2) {
            buffer.append(truncated);
        }
        buffer.append(postfix);
        return buffer;
    }

    @NotNull
    public static final <A extends Appendable> A joinTo(@NotNull char[] cArr, @NotNull A buffer, @NotNull CharSequence separator, @NotNull CharSequence prefix, @NotNull CharSequence postfix, int i2, @NotNull CharSequence truncated, @Nullable Function1<? super Character, ? extends CharSequence> function1) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        Intrinsics.checkNotNullParameter(separator, "separator");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        Intrinsics.checkNotNullParameter(postfix, "postfix");
        Intrinsics.checkNotNullParameter(truncated, "truncated");
        buffer.append(prefix);
        int i3 = 0;
        for (char c2 : cArr) {
            i3++;
            if (i3 > 1) {
                buffer.append(separator);
            }
            if (i2 >= 0 && i3 > i2) {
                break;
            }
            if (function1 != null) {
                buffer.append(function1.invoke(Character.valueOf(c2)));
            } else {
                buffer.append(c2);
            }
        }
        if (i2 >= 0 && i3 > i2) {
            buffer.append(truncated);
        }
        buffer.append(postfix);
        return buffer;
    }

    @NotNull
    public static final <A extends Appendable> A joinTo(@NotNull double[] dArr, @NotNull A buffer, @NotNull CharSequence separator, @NotNull CharSequence prefix, @NotNull CharSequence postfix, int i2, @NotNull CharSequence truncated, @Nullable Function1<? super Double, ? extends CharSequence> function1) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        Intrinsics.checkNotNullParameter(separator, "separator");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        Intrinsics.checkNotNullParameter(postfix, "postfix");
        Intrinsics.checkNotNullParameter(truncated, "truncated");
        buffer.append(prefix);
        int i3 = 0;
        for (double d2 : dArr) {
            i3++;
            if (i3 > 1) {
                buffer.append(separator);
            }
            if (i2 >= 0 && i3 > i2) {
                break;
            }
            buffer.append(function1 != null ? function1.invoke(Double.valueOf(d2)) : String.valueOf(d2));
        }
        if (i2 >= 0 && i3 > i2) {
            buffer.append(truncated);
        }
        buffer.append(postfix);
        return buffer;
    }

    @NotNull
    public static final <A extends Appendable> A joinTo(@NotNull float[] fArr, @NotNull A buffer, @NotNull CharSequence separator, @NotNull CharSequence prefix, @NotNull CharSequence postfix, int i2, @NotNull CharSequence truncated, @Nullable Function1<? super Float, ? extends CharSequence> function1) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        Intrinsics.checkNotNullParameter(separator, "separator");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        Intrinsics.checkNotNullParameter(postfix, "postfix");
        Intrinsics.checkNotNullParameter(truncated, "truncated");
        buffer.append(prefix);
        int i3 = 0;
        for (float f2 : fArr) {
            i3++;
            if (i3 > 1) {
                buffer.append(separator);
            }
            if (i2 >= 0 && i3 > i2) {
                break;
            }
            buffer.append(function1 != null ? function1.invoke(Float.valueOf(f2)) : String.valueOf(f2));
        }
        if (i2 >= 0 && i3 > i2) {
            buffer.append(truncated);
        }
        buffer.append(postfix);
        return buffer;
    }

    @NotNull
    public static final <A extends Appendable> A joinTo(@NotNull int[] iArr, @NotNull A buffer, @NotNull CharSequence separator, @NotNull CharSequence prefix, @NotNull CharSequence postfix, int i2, @NotNull CharSequence truncated, @Nullable Function1<? super Integer, ? extends CharSequence> function1) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        Intrinsics.checkNotNullParameter(separator, "separator");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        Intrinsics.checkNotNullParameter(postfix, "postfix");
        Intrinsics.checkNotNullParameter(truncated, "truncated");
        buffer.append(prefix);
        int i3 = 0;
        for (int i4 : iArr) {
            i3++;
            if (i3 > 1) {
                buffer.append(separator);
            }
            if (i2 >= 0 && i3 > i2) {
                break;
            }
            buffer.append(function1 != null ? function1.invoke(Integer.valueOf(i4)) : String.valueOf(i4));
        }
        if (i2 >= 0 && i3 > i2) {
            buffer.append(truncated);
        }
        buffer.append(postfix);
        return buffer;
    }

    @NotNull
    public static final <A extends Appendable> A joinTo(@NotNull long[] jArr, @NotNull A buffer, @NotNull CharSequence separator, @NotNull CharSequence prefix, @NotNull CharSequence postfix, int i2, @NotNull CharSequence truncated, @Nullable Function1<? super Long, ? extends CharSequence> function1) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        Intrinsics.checkNotNullParameter(separator, "separator");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        Intrinsics.checkNotNullParameter(postfix, "postfix");
        Intrinsics.checkNotNullParameter(truncated, "truncated");
        buffer.append(prefix);
        int i3 = 0;
        for (long j2 : jArr) {
            i3++;
            if (i3 > 1) {
                buffer.append(separator);
            }
            if (i2 >= 0 && i3 > i2) {
                break;
            }
            buffer.append(function1 != null ? function1.invoke(Long.valueOf(j2)) : String.valueOf(j2));
        }
        if (i2 >= 0 && i3 > i2) {
            buffer.append(truncated);
        }
        buffer.append(postfix);
        return buffer;
    }

    @NotNull
    public static final <T, A extends Appendable> A joinTo(@NotNull T[] tArr, @NotNull A buffer, @NotNull CharSequence separator, @NotNull CharSequence prefix, @NotNull CharSequence postfix, int i2, @NotNull CharSequence truncated, @Nullable Function1<? super T, ? extends CharSequence> function1) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        Intrinsics.checkNotNullParameter(separator, "separator");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        Intrinsics.checkNotNullParameter(postfix, "postfix");
        Intrinsics.checkNotNullParameter(truncated, "truncated");
        buffer.append(prefix);
        int i3 = 0;
        for (T t2 : tArr) {
            i3++;
            if (i3 > 1) {
                buffer.append(separator);
            }
            if (i2 >= 0 && i3 > i2) {
                break;
            }
            StringsKt__AppendableKt.appendElement(buffer, t2, function1);
        }
        if (i2 >= 0 && i3 > i2) {
            buffer.append(truncated);
        }
        buffer.append(postfix);
        return buffer;
    }

    @NotNull
    public static final <A extends Appendable> A joinTo(@NotNull short[] sArr, @NotNull A buffer, @NotNull CharSequence separator, @NotNull CharSequence prefix, @NotNull CharSequence postfix, int i2, @NotNull CharSequence truncated, @Nullable Function1<? super Short, ? extends CharSequence> function1) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        Intrinsics.checkNotNullParameter(separator, "separator");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        Intrinsics.checkNotNullParameter(postfix, "postfix");
        Intrinsics.checkNotNullParameter(truncated, "truncated");
        buffer.append(prefix);
        int i3 = 0;
        for (short s2 : sArr) {
            i3++;
            if (i3 > 1) {
                buffer.append(separator);
            }
            if (i2 >= 0 && i3 > i2) {
                break;
            }
            buffer.append(function1 != null ? function1.invoke(Short.valueOf(s2)) : String.valueOf((int) s2));
        }
        if (i2 >= 0 && i3 > i2) {
            buffer.append(truncated);
        }
        buffer.append(postfix);
        return buffer;
    }

    @NotNull
    public static final <A extends Appendable> A joinTo(@NotNull boolean[] zArr, @NotNull A buffer, @NotNull CharSequence separator, @NotNull CharSequence prefix, @NotNull CharSequence postfix, int i2, @NotNull CharSequence truncated, @Nullable Function1<? super Boolean, ? extends CharSequence> function1) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        Intrinsics.checkNotNullParameter(separator, "separator");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        Intrinsics.checkNotNullParameter(postfix, "postfix");
        Intrinsics.checkNotNullParameter(truncated, "truncated");
        buffer.append(prefix);
        int i3 = 0;
        for (boolean z : zArr) {
            i3++;
            if (i3 > 1) {
                buffer.append(separator);
            }
            if (i2 >= 0 && i3 > i2) {
                break;
            }
            buffer.append(function1 != null ? function1.invoke(Boolean.valueOf(z)) : String.valueOf(z));
        }
        if (i2 >= 0 && i3 > i2) {
            buffer.append(truncated);
        }
        buffer.append(postfix);
        return buffer;
    }

    public static /* synthetic */ Appendable joinTo$default(Object[] objArr, Appendable appendable, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i2, CharSequence charSequence4, Function1 function1, int i3, Object obj) {
        return joinTo(objArr, appendable, (i3 & 2) != 0 ? ", " : charSequence, (i3 & 4) != 0 ? "" : charSequence2, (i3 & 8) == 0 ? charSequence3 : "", (i3 & 16) != 0 ? -1 : i2, (i3 & 32) != 0 ? "..." : charSequence4, (i3 & 64) != 0 ? null : function1);
    }

    @NotNull
    public static final String joinToString(@NotNull byte[] bArr, @NotNull CharSequence separator, @NotNull CharSequence prefix, @NotNull CharSequence postfix, int i2, @NotNull CharSequence truncated, @Nullable Function1<? super Byte, ? extends CharSequence> function1) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(separator, "separator");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        Intrinsics.checkNotNullParameter(postfix, "postfix");
        Intrinsics.checkNotNullParameter(truncated, "truncated");
        String sb = ((StringBuilder) joinTo(bArr, new StringBuilder(), separator, prefix, postfix, i2, truncated, function1)).toString();
        Intrinsics.checkNotNullExpressionValue(sb, "joinTo(StringBuilder(), …ed, transform).toString()");
        return sb;
    }

    @NotNull
    public static final String joinToString(@NotNull char[] cArr, @NotNull CharSequence separator, @NotNull CharSequence prefix, @NotNull CharSequence postfix, int i2, @NotNull CharSequence truncated, @Nullable Function1<? super Character, ? extends CharSequence> function1) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(separator, "separator");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        Intrinsics.checkNotNullParameter(postfix, "postfix");
        Intrinsics.checkNotNullParameter(truncated, "truncated");
        String sb = ((StringBuilder) joinTo(cArr, new StringBuilder(), separator, prefix, postfix, i2, truncated, function1)).toString();
        Intrinsics.checkNotNullExpressionValue(sb, "joinTo(StringBuilder(), …ed, transform).toString()");
        return sb;
    }

    @NotNull
    public static final String joinToString(@NotNull double[] dArr, @NotNull CharSequence separator, @NotNull CharSequence prefix, @NotNull CharSequence postfix, int i2, @NotNull CharSequence truncated, @Nullable Function1<? super Double, ? extends CharSequence> function1) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(separator, "separator");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        Intrinsics.checkNotNullParameter(postfix, "postfix");
        Intrinsics.checkNotNullParameter(truncated, "truncated");
        String sb = ((StringBuilder) joinTo(dArr, new StringBuilder(), separator, prefix, postfix, i2, truncated, function1)).toString();
        Intrinsics.checkNotNullExpressionValue(sb, "joinTo(StringBuilder(), …ed, transform).toString()");
        return sb;
    }

    @NotNull
    public static final String joinToString(@NotNull float[] fArr, @NotNull CharSequence separator, @NotNull CharSequence prefix, @NotNull CharSequence postfix, int i2, @NotNull CharSequence truncated, @Nullable Function1<? super Float, ? extends CharSequence> function1) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(separator, "separator");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        Intrinsics.checkNotNullParameter(postfix, "postfix");
        Intrinsics.checkNotNullParameter(truncated, "truncated");
        String sb = ((StringBuilder) joinTo(fArr, new StringBuilder(), separator, prefix, postfix, i2, truncated, function1)).toString();
        Intrinsics.checkNotNullExpressionValue(sb, "joinTo(StringBuilder(), …ed, transform).toString()");
        return sb;
    }

    @NotNull
    public static final String joinToString(@NotNull int[] iArr, @NotNull CharSequence separator, @NotNull CharSequence prefix, @NotNull CharSequence postfix, int i2, @NotNull CharSequence truncated, @Nullable Function1<? super Integer, ? extends CharSequence> function1) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(separator, "separator");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        Intrinsics.checkNotNullParameter(postfix, "postfix");
        Intrinsics.checkNotNullParameter(truncated, "truncated");
        String sb = ((StringBuilder) joinTo(iArr, new StringBuilder(), separator, prefix, postfix, i2, truncated, function1)).toString();
        Intrinsics.checkNotNullExpressionValue(sb, "joinTo(StringBuilder(), …ed, transform).toString()");
        return sb;
    }

    @NotNull
    public static final String joinToString(@NotNull long[] jArr, @NotNull CharSequence separator, @NotNull CharSequence prefix, @NotNull CharSequence postfix, int i2, @NotNull CharSequence truncated, @Nullable Function1<? super Long, ? extends CharSequence> function1) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(separator, "separator");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        Intrinsics.checkNotNullParameter(postfix, "postfix");
        Intrinsics.checkNotNullParameter(truncated, "truncated");
        String sb = ((StringBuilder) joinTo(jArr, new StringBuilder(), separator, prefix, postfix, i2, truncated, function1)).toString();
        Intrinsics.checkNotNullExpressionValue(sb, "joinTo(StringBuilder(), …ed, transform).toString()");
        return sb;
    }

    @NotNull
    public static final <T> String joinToString(@NotNull T[] tArr, @NotNull CharSequence separator, @NotNull CharSequence prefix, @NotNull CharSequence postfix, int i2, @NotNull CharSequence truncated, @Nullable Function1<? super T, ? extends CharSequence> function1) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(separator, "separator");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        Intrinsics.checkNotNullParameter(postfix, "postfix");
        Intrinsics.checkNotNullParameter(truncated, "truncated");
        String sb = ((StringBuilder) joinTo(tArr, new StringBuilder(), separator, prefix, postfix, i2, truncated, function1)).toString();
        Intrinsics.checkNotNullExpressionValue(sb, "joinTo(StringBuilder(), …ed, transform).toString()");
        return sb;
    }

    @NotNull
    public static final String joinToString(@NotNull short[] sArr, @NotNull CharSequence separator, @NotNull CharSequence prefix, @NotNull CharSequence postfix, int i2, @NotNull CharSequence truncated, @Nullable Function1<? super Short, ? extends CharSequence> function1) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(separator, "separator");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        Intrinsics.checkNotNullParameter(postfix, "postfix");
        Intrinsics.checkNotNullParameter(truncated, "truncated");
        String sb = ((StringBuilder) joinTo(sArr, new StringBuilder(), separator, prefix, postfix, i2, truncated, function1)).toString();
        Intrinsics.checkNotNullExpressionValue(sb, "joinTo(StringBuilder(), …ed, transform).toString()");
        return sb;
    }

    @NotNull
    public static final String joinToString(@NotNull boolean[] zArr, @NotNull CharSequence separator, @NotNull CharSequence prefix, @NotNull CharSequence postfix, int i2, @NotNull CharSequence truncated, @Nullable Function1<? super Boolean, ? extends CharSequence> function1) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(separator, "separator");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        Intrinsics.checkNotNullParameter(postfix, "postfix");
        Intrinsics.checkNotNullParameter(truncated, "truncated");
        String sb = ((StringBuilder) joinTo(zArr, new StringBuilder(), separator, prefix, postfix, i2, truncated, function1)).toString();
        Intrinsics.checkNotNullExpressionValue(sb, "joinTo(StringBuilder(), …ed, transform).toString()");
        return sb;
    }

    public static /* synthetic */ String joinToString$default(byte[] bArr, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i2, CharSequence charSequence4, Function1 function1, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            charSequence = ", ";
        }
        String str = (i3 & 2) != 0 ? "" : charSequence2;
        String str2 = (i3 & 4) == 0 ? charSequence3 : "";
        if ((i3 & 8) != 0) {
            i2 = -1;
        }
        int i4 = i2;
        if ((i3 & 16) != 0) {
            charSequence4 = "...";
        }
        CharSequence charSequence5 = charSequence4;
        if ((i3 & 32) != 0) {
            function1 = null;
        }
        return joinToString(bArr, charSequence, str, str2, i4, charSequence5, (Function1<? super Byte, ? extends CharSequence>) function1);
    }

    public static /* synthetic */ String joinToString$default(char[] cArr, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i2, CharSequence charSequence4, Function1 function1, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            charSequence = ", ";
        }
        String str = (i3 & 2) != 0 ? "" : charSequence2;
        String str2 = (i3 & 4) == 0 ? charSequence3 : "";
        if ((i3 & 8) != 0) {
            i2 = -1;
        }
        int i4 = i2;
        if ((i3 & 16) != 0) {
            charSequence4 = "...";
        }
        CharSequence charSequence5 = charSequence4;
        if ((i3 & 32) != 0) {
            function1 = null;
        }
        return joinToString(cArr, charSequence, str, str2, i4, charSequence5, (Function1<? super Character, ? extends CharSequence>) function1);
    }

    public static /* synthetic */ String joinToString$default(double[] dArr, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i2, CharSequence charSequence4, Function1 function1, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            charSequence = ", ";
        }
        String str = (i3 & 2) != 0 ? "" : charSequence2;
        String str2 = (i3 & 4) == 0 ? charSequence3 : "";
        if ((i3 & 8) != 0) {
            i2 = -1;
        }
        int i4 = i2;
        if ((i3 & 16) != 0) {
            charSequence4 = "...";
        }
        CharSequence charSequence5 = charSequence4;
        if ((i3 & 32) != 0) {
            function1 = null;
        }
        return joinToString(dArr, charSequence, str, str2, i4, charSequence5, function1);
    }

    public static /* synthetic */ String joinToString$default(float[] fArr, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i2, CharSequence charSequence4, Function1 function1, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            charSequence = ", ";
        }
        String str = (i3 & 2) != 0 ? "" : charSequence2;
        String str2 = (i3 & 4) == 0 ? charSequence3 : "";
        if ((i3 & 8) != 0) {
            i2 = -1;
        }
        int i4 = i2;
        if ((i3 & 16) != 0) {
            charSequence4 = "...";
        }
        CharSequence charSequence5 = charSequence4;
        if ((i3 & 32) != 0) {
            function1 = null;
        }
        return joinToString(fArr, charSequence, str, str2, i4, charSequence5, (Function1<? super Float, ? extends CharSequence>) function1);
    }

    public static /* synthetic */ String joinToString$default(int[] iArr, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i2, CharSequence charSequence4, Function1 function1, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            charSequence = ", ";
        }
        String str = (i3 & 2) != 0 ? "" : charSequence2;
        String str2 = (i3 & 4) == 0 ? charSequence3 : "";
        if ((i3 & 8) != 0) {
            i2 = -1;
        }
        int i4 = i2;
        if ((i3 & 16) != 0) {
            charSequence4 = "...";
        }
        CharSequence charSequence5 = charSequence4;
        if ((i3 & 32) != 0) {
            function1 = null;
        }
        return joinToString(iArr, charSequence, str, str2, i4, charSequence5, (Function1<? super Integer, ? extends CharSequence>) function1);
    }

    public static /* synthetic */ String joinToString$default(long[] jArr, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i2, CharSequence charSequence4, Function1 function1, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            charSequence = ", ";
        }
        String str = (i3 & 2) != 0 ? "" : charSequence2;
        String str2 = (i3 & 4) == 0 ? charSequence3 : "";
        if ((i3 & 8) != 0) {
            i2 = -1;
        }
        int i4 = i2;
        if ((i3 & 16) != 0) {
            charSequence4 = "...";
        }
        CharSequence charSequence5 = charSequence4;
        if ((i3 & 32) != 0) {
            function1 = null;
        }
        return joinToString(jArr, charSequence, str, str2, i4, charSequence5, (Function1<? super Long, ? extends CharSequence>) function1);
    }

    public static /* synthetic */ String joinToString$default(Object[] objArr, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i2, CharSequence charSequence4, Function1 function1, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            charSequence = ", ";
        }
        String str = (i3 & 2) != 0 ? "" : charSequence2;
        String str2 = (i3 & 4) == 0 ? charSequence3 : "";
        if ((i3 & 8) != 0) {
            i2 = -1;
        }
        int i4 = i2;
        if ((i3 & 16) != 0) {
            charSequence4 = "...";
        }
        CharSequence charSequence5 = charSequence4;
        if ((i3 & 32) != 0) {
            function1 = null;
        }
        return joinToString(objArr, charSequence, str, str2, i4, charSequence5, function1);
    }

    public static /* synthetic */ String joinToString$default(short[] sArr, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i2, CharSequence charSequence4, Function1 function1, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            charSequence = ", ";
        }
        String str = (i3 & 2) != 0 ? "" : charSequence2;
        String str2 = (i3 & 4) == 0 ? charSequence3 : "";
        if ((i3 & 8) != 0) {
            i2 = -1;
        }
        int i4 = i2;
        if ((i3 & 16) != 0) {
            charSequence4 = "...";
        }
        CharSequence charSequence5 = charSequence4;
        if ((i3 & 32) != 0) {
            function1 = null;
        }
        return joinToString(sArr, charSequence, str, str2, i4, charSequence5, (Function1<? super Short, ? extends CharSequence>) function1);
    }

    public static /* synthetic */ String joinToString$default(boolean[] zArr, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i2, CharSequence charSequence4, Function1 function1, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            charSequence = ", ";
        }
        String str = (i3 & 2) != 0 ? "" : charSequence2;
        String str2 = (i3 & 4) == 0 ? charSequence3 : "";
        if ((i3 & 8) != 0) {
            i2 = -1;
        }
        int i4 = i2;
        if ((i3 & 16) != 0) {
            charSequence4 = "...";
        }
        CharSequence charSequence5 = charSequence4;
        if ((i3 & 32) != 0) {
            function1 = null;
        }
        return joinToString(zArr, charSequence, str, str2, i4, charSequence5, function1);
    }

    public static byte last(@NotNull byte[] bArr) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        if (bArr.length == 0) {
            throw new NoSuchElementException("Array is empty.");
        }
        lastIndex = getLastIndex(bArr);
        return bArr[lastIndex];
    }

    public static final byte last(@NotNull byte[] bArr, @NotNull Function1<? super Byte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = bArr.length - 1;
        if (length >= 0) {
            while (true) {
                int i2 = length - 1;
                byte b2 = bArr[length];
                if (!predicate.invoke(Byte.valueOf(b2)).booleanValue()) {
                    if (i2 < 0) {
                        break;
                    }
                    length = i2;
                } else {
                    return b2;
                }
            }
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    public static final char last(@NotNull char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        if (cArr.length == 0) {
            throw new NoSuchElementException("Array is empty.");
        }
        return cArr[getLastIndex(cArr)];
    }

    public static final char last(@NotNull char[] cArr, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = cArr.length - 1;
        if (length >= 0) {
            while (true) {
                int i2 = length - 1;
                char c2 = cArr[length];
                if (!predicate.invoke(Character.valueOf(c2)).booleanValue()) {
                    if (i2 < 0) {
                        break;
                    }
                    length = i2;
                } else {
                    return c2;
                }
            }
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    public static final double last(@NotNull double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        if (dArr.length == 0) {
            throw new NoSuchElementException("Array is empty.");
        }
        return dArr[getLastIndex(dArr)];
    }

    public static final double last(@NotNull double[] dArr, @NotNull Function1<? super Double, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = dArr.length - 1;
        if (length >= 0) {
            while (true) {
                int i2 = length - 1;
                double d2 = dArr[length];
                if (!predicate.invoke(Double.valueOf(d2)).booleanValue()) {
                    if (i2 < 0) {
                        break;
                    }
                    length = i2;
                } else {
                    return d2;
                }
            }
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    public static final float last(@NotNull float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        if (fArr.length == 0) {
            throw new NoSuchElementException("Array is empty.");
        }
        return fArr[getLastIndex(fArr)];
    }

    public static final float last(@NotNull float[] fArr, @NotNull Function1<? super Float, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = fArr.length - 1;
        if (length >= 0) {
            while (true) {
                int i2 = length - 1;
                float f2 = fArr[length];
                if (!predicate.invoke(Float.valueOf(f2)).booleanValue()) {
                    if (i2 < 0) {
                        break;
                    }
                    length = i2;
                } else {
                    return f2;
                }
            }
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    public static int last(@NotNull int[] iArr) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        if (iArr.length == 0) {
            throw new NoSuchElementException("Array is empty.");
        }
        lastIndex = getLastIndex(iArr);
        return iArr[lastIndex];
    }

    public static final int last(@NotNull int[] iArr, @NotNull Function1<? super Integer, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = iArr.length - 1;
        if (length >= 0) {
            while (true) {
                int i2 = length - 1;
                int i3 = iArr[length];
                if (!predicate.invoke(Integer.valueOf(i3)).booleanValue()) {
                    if (i2 < 0) {
                        break;
                    }
                    length = i2;
                } else {
                    return i3;
                }
            }
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    public static long last(@NotNull long[] jArr) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        if (jArr.length == 0) {
            throw new NoSuchElementException("Array is empty.");
        }
        lastIndex = getLastIndex(jArr);
        return jArr[lastIndex];
    }

    public static final long last(@NotNull long[] jArr, @NotNull Function1<? super Long, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = jArr.length - 1;
        if (length >= 0) {
            while (true) {
                int i2 = length - 1;
                long j2 = jArr[length];
                if (!predicate.invoke(Long.valueOf(j2)).booleanValue()) {
                    if (i2 < 0) {
                        break;
                    }
                    length = i2;
                } else {
                    return j2;
                }
            }
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    public static final <T> T last(@NotNull T[] tArr) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        if (tArr.length == 0) {
            throw new NoSuchElementException("Array is empty.");
        }
        lastIndex = getLastIndex(tArr);
        return tArr[lastIndex];
    }

    public static final <T> T last(@NotNull T[] tArr, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = tArr.length - 1;
        if (length >= 0) {
            while (true) {
                int i2 = length - 1;
                T t2 = tArr[length];
                if (!predicate.invoke(t2).booleanValue()) {
                    if (i2 < 0) {
                        break;
                    }
                    length = i2;
                } else {
                    return t2;
                }
            }
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    public static short last(@NotNull short[] sArr) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        if (sArr.length == 0) {
            throw new NoSuchElementException("Array is empty.");
        }
        lastIndex = getLastIndex(sArr);
        return sArr[lastIndex];
    }

    public static final short last(@NotNull short[] sArr, @NotNull Function1<? super Short, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = sArr.length - 1;
        if (length >= 0) {
            while (true) {
                int i2 = length - 1;
                short s2 = sArr[length];
                if (!predicate.invoke(Short.valueOf(s2)).booleanValue()) {
                    if (i2 < 0) {
                        break;
                    }
                    length = i2;
                } else {
                    return s2;
                }
            }
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    public static final boolean last(@NotNull boolean[] zArr) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        if (zArr.length == 0) {
            throw new NoSuchElementException("Array is empty.");
        }
        return zArr[getLastIndex(zArr)];
    }

    public static final boolean last(@NotNull boolean[] zArr, @NotNull Function1<? super Boolean, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = zArr.length - 1;
        if (length >= 0) {
            while (true) {
                int i2 = length - 1;
                boolean z = zArr[length];
                if (!predicate.invoke(Boolean.valueOf(z)).booleanValue()) {
                    if (i2 < 0) {
                        break;
                    }
                    length = i2;
                } else {
                    return z;
                }
            }
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    public static int lastIndexOf(@NotNull byte[] bArr, byte b2) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        int length = bArr.length - 1;
        if (length >= 0) {
            while (true) {
                int i2 = length - 1;
                if (b2 == bArr[length]) {
                    return length;
                }
                if (i2 < 0) {
                    break;
                }
                length = i2;
            }
        }
        return -1;
    }

    public static final int lastIndexOf(@NotNull char[] cArr, char c2) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        int length = cArr.length - 1;
        if (length >= 0) {
            while (true) {
                int i2 = length - 1;
                if (c2 == cArr[length]) {
                    return length;
                }
                if (i2 < 0) {
                    break;
                }
                length = i2;
            }
        }
        return -1;
    }

    @Deprecated(message = "The function has unclear behavior when searching for NaN or zero values and will be removed soon. Use 'indexOfLast { it == element }' instead to continue using this behavior, or '.asList().lastIndexOf(element: T)' to get the same search behavior as in a list.", replaceWith = @ReplaceWith(expression = "indexOfLast { it == element }", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.6", warningSince = "1.4")
    public static final int lastIndexOf(@NotNull double[] dArr, double d2) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        int length = dArr.length - 1;
        if (length >= 0) {
            while (true) {
                int i2 = length - 1;
                if (d2 == dArr[length]) {
                    return length;
                }
                if (i2 < 0) {
                    break;
                }
                length = i2;
            }
        }
        return -1;
    }

    @Deprecated(message = "The function has unclear behavior when searching for NaN or zero values and will be removed soon. Use 'indexOfLast { it == element }' instead to continue using this behavior, or '.asList().lastIndexOf(element: T)' to get the same search behavior as in a list.", replaceWith = @ReplaceWith(expression = "indexOfLast { it == element }", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.6", warningSince = "1.4")
    public static final int lastIndexOf(@NotNull float[] fArr, float f2) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        int length = fArr.length - 1;
        if (length >= 0) {
            while (true) {
                int i2 = length - 1;
                if (f2 == fArr[length]) {
                    return length;
                }
                if (i2 < 0) {
                    break;
                }
                length = i2;
            }
        }
        return -1;
    }

    public static int lastIndexOf(@NotNull int[] iArr, int i2) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        int length = iArr.length - 1;
        if (length >= 0) {
            while (true) {
                int i3 = length - 1;
                if (i2 == iArr[length]) {
                    return length;
                }
                if (i3 < 0) {
                    break;
                }
                length = i3;
            }
        }
        return -1;
    }

    public static int lastIndexOf(@NotNull long[] jArr, long j2) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        int length = jArr.length - 1;
        if (length >= 0) {
            while (true) {
                int i2 = length - 1;
                if (j2 == jArr[length]) {
                    return length;
                }
                if (i2 < 0) {
                    break;
                }
                length = i2;
            }
        }
        return -1;
    }

    public static final <T> int lastIndexOf(@NotNull T[] tArr, T t2) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        if (t2 == null) {
            int length = tArr.length - 1;
            if (length >= 0) {
                while (true) {
                    int i2 = length - 1;
                    if (tArr[length] == null) {
                        return length;
                    }
                    if (i2 < 0) {
                        break;
                    }
                    length = i2;
                }
            }
        } else {
            int length2 = tArr.length - 1;
            if (length2 >= 0) {
                while (true) {
                    int i3 = length2 - 1;
                    if (Intrinsics.areEqual(t2, tArr[length2])) {
                        return length2;
                    }
                    if (i3 < 0) {
                        break;
                    }
                    length2 = i3;
                }
            }
        }
        return -1;
    }

    public static int lastIndexOf(@NotNull short[] sArr, short s2) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        int length = sArr.length - 1;
        if (length >= 0) {
            while (true) {
                int i2 = length - 1;
                if (s2 == sArr[length]) {
                    return length;
                }
                if (i2 < 0) {
                    break;
                }
                length = i2;
            }
        }
        return -1;
    }

    public static final int lastIndexOf(@NotNull boolean[] zArr, boolean z) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        int length = zArr.length - 1;
        if (length >= 0) {
            while (true) {
                int i2 = length - 1;
                if (z == zArr[length]) {
                    return length;
                }
                if (i2 < 0) {
                    break;
                }
                length = i2;
            }
        }
        return -1;
    }

    @Nullable
    public static final Boolean lastOrNull(@NotNull boolean[] zArr) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        if (zArr.length == 0) {
            return null;
        }
        return Boolean.valueOf(zArr[zArr.length - 1]);
    }

    @Nullable
    public static final Boolean lastOrNull(@NotNull boolean[] zArr, @NotNull Function1<? super Boolean, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = zArr.length - 1;
        if (length < 0) {
            return null;
        }
        while (true) {
            int i2 = length - 1;
            boolean z = zArr[length];
            if (predicate.invoke(Boolean.valueOf(z)).booleanValue()) {
                return Boolean.valueOf(z);
            }
            if (i2 < 0) {
                return null;
            }
            length = i2;
        }
    }

    @Nullable
    public static final Byte lastOrNull(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        if (bArr.length == 0) {
            return null;
        }
        return Byte.valueOf(bArr[bArr.length - 1]);
    }

    @Nullable
    public static final Byte lastOrNull(@NotNull byte[] bArr, @NotNull Function1<? super Byte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = bArr.length - 1;
        if (length < 0) {
            return null;
        }
        while (true) {
            int i2 = length - 1;
            byte b2 = bArr[length];
            if (predicate.invoke(Byte.valueOf(b2)).booleanValue()) {
                return Byte.valueOf(b2);
            }
            if (i2 < 0) {
                return null;
            }
            length = i2;
        }
    }

    @Nullable
    public static final Character lastOrNull(@NotNull char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        if (cArr.length == 0) {
            return null;
        }
        return Character.valueOf(cArr[cArr.length - 1]);
    }

    @Nullable
    public static final Character lastOrNull(@NotNull char[] cArr, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = cArr.length - 1;
        if (length < 0) {
            return null;
        }
        while (true) {
            int i2 = length - 1;
            char c2 = cArr[length];
            if (predicate.invoke(Character.valueOf(c2)).booleanValue()) {
                return Character.valueOf(c2);
            }
            if (i2 < 0) {
                return null;
            }
            length = i2;
        }
    }

    @Nullable
    public static final Double lastOrNull(@NotNull double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        if (dArr.length == 0) {
            return null;
        }
        return Double.valueOf(dArr[dArr.length - 1]);
    }

    @Nullable
    public static final Double lastOrNull(@NotNull double[] dArr, @NotNull Function1<? super Double, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = dArr.length - 1;
        if (length < 0) {
            return null;
        }
        while (true) {
            int i2 = length - 1;
            double d2 = dArr[length];
            if (predicate.invoke(Double.valueOf(d2)).booleanValue()) {
                return Double.valueOf(d2);
            }
            if (i2 < 0) {
                return null;
            }
            length = i2;
        }
    }

    @Nullable
    public static final Float lastOrNull(@NotNull float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        if (fArr.length == 0) {
            return null;
        }
        return Float.valueOf(fArr[fArr.length - 1]);
    }

    @Nullable
    public static final Float lastOrNull(@NotNull float[] fArr, @NotNull Function1<? super Float, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = fArr.length - 1;
        if (length < 0) {
            return null;
        }
        while (true) {
            int i2 = length - 1;
            float f2 = fArr[length];
            if (predicate.invoke(Float.valueOf(f2)).booleanValue()) {
                return Float.valueOf(f2);
            }
            if (i2 < 0) {
                return null;
            }
            length = i2;
        }
    }

    @Nullable
    public static final Integer lastOrNull(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        if (iArr.length == 0) {
            return null;
        }
        return Integer.valueOf(iArr[iArr.length - 1]);
    }

    @Nullable
    public static final Integer lastOrNull(@NotNull int[] iArr, @NotNull Function1<? super Integer, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = iArr.length - 1;
        if (length < 0) {
            return null;
        }
        while (true) {
            int i2 = length - 1;
            int i3 = iArr[length];
            if (predicate.invoke(Integer.valueOf(i3)).booleanValue()) {
                return Integer.valueOf(i3);
            }
            if (i2 < 0) {
                return null;
            }
            length = i2;
        }
    }

    @Nullable
    public static final Long lastOrNull(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        if (jArr.length == 0) {
            return null;
        }
        return Long.valueOf(jArr[jArr.length - 1]);
    }

    @Nullable
    public static final Long lastOrNull(@NotNull long[] jArr, @NotNull Function1<? super Long, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = jArr.length - 1;
        if (length < 0) {
            return null;
        }
        while (true) {
            int i2 = length - 1;
            long j2 = jArr[length];
            if (predicate.invoke(Long.valueOf(j2)).booleanValue()) {
                return Long.valueOf(j2);
            }
            if (i2 < 0) {
                return null;
            }
            length = i2;
        }
    }

    @Nullable
    public static final <T> T lastOrNull(@NotNull T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        if (tArr.length == 0) {
            return null;
        }
        return tArr[tArr.length - 1];
    }

    @Nullable
    public static final <T> T lastOrNull(@NotNull T[] tArr, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = tArr.length - 1;
        if (length < 0) {
            return null;
        }
        while (true) {
            int i2 = length - 1;
            T t2 = tArr[length];
            if (predicate.invoke(t2).booleanValue()) {
                return t2;
            }
            if (i2 < 0) {
                return null;
            }
            length = i2;
        }
    }

    @Nullable
    public static final Short lastOrNull(@NotNull short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        if (sArr.length == 0) {
            return null;
        }
        return Short.valueOf(sArr[sArr.length - 1]);
    }

    @Nullable
    public static final Short lastOrNull(@NotNull short[] sArr, @NotNull Function1<? super Short, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = sArr.length - 1;
        if (length < 0) {
            return null;
        }
        while (true) {
            int i2 = length - 1;
            short s2 = sArr[length];
            if (predicate.invoke(Short.valueOf(s2)).booleanValue()) {
                return Short.valueOf(s2);
            }
            if (i2 < 0) {
                return null;
            }
            length = i2;
        }
    }

    @NotNull
    public static final <R> List<R> map(@NotNull byte[] bArr, @NotNull Function1<? super Byte, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList(bArr.length);
        for (byte b2 : bArr) {
            arrayList.add(transform.invoke(Byte.valueOf(b2)));
        }
        return arrayList;
    }

    @NotNull
    public static final <R> List<R> map(@NotNull char[] cArr, @NotNull Function1<? super Character, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList(cArr.length);
        for (char c2 : cArr) {
            arrayList.add(transform.invoke(Character.valueOf(c2)));
        }
        return arrayList;
    }

    @NotNull
    public static final <R> List<R> map(@NotNull double[] dArr, @NotNull Function1<? super Double, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList(dArr.length);
        for (double d2 : dArr) {
            arrayList.add(transform.invoke(Double.valueOf(d2)));
        }
        return arrayList;
    }

    @NotNull
    public static final <R> List<R> map(@NotNull float[] fArr, @NotNull Function1<? super Float, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList(fArr.length);
        for (float f2 : fArr) {
            arrayList.add(transform.invoke(Float.valueOf(f2)));
        }
        return arrayList;
    }

    @NotNull
    public static final <R> List<R> map(@NotNull int[] iArr, @NotNull Function1<? super Integer, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList(iArr.length);
        for (int i2 : iArr) {
            arrayList.add(transform.invoke(Integer.valueOf(i2)));
        }
        return arrayList;
    }

    @NotNull
    public static final <R> List<R> map(@NotNull long[] jArr, @NotNull Function1<? super Long, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList(jArr.length);
        for (long j2 : jArr) {
            arrayList.add(transform.invoke(Long.valueOf(j2)));
        }
        return arrayList;
    }

    @NotNull
    public static final <T, R> List<R> map(@NotNull T[] tArr, @NotNull Function1<? super T, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList(tArr.length);
        for (T t2 : tArr) {
            arrayList.add(transform.invoke(t2));
        }
        return arrayList;
    }

    @NotNull
    public static final <R> List<R> map(@NotNull short[] sArr, @NotNull Function1<? super Short, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList(sArr.length);
        for (short s2 : sArr) {
            arrayList.add(transform.invoke(Short.valueOf(s2)));
        }
        return arrayList;
    }

    @NotNull
    public static final <R> List<R> map(@NotNull boolean[] zArr, @NotNull Function1<? super Boolean, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList(zArr.length);
        for (boolean z : zArr) {
            arrayList.add(transform.invoke(Boolean.valueOf(z)));
        }
        return arrayList;
    }

    @NotNull
    public static final <R> List<R> mapIndexed(@NotNull byte[] bArr, @NotNull Function2<? super Integer, ? super Byte, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList(bArr.length);
        int length = bArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            arrayList.add(transform.invoke(Integer.valueOf(i3), Byte.valueOf(bArr[i2])));
            i2++;
            i3++;
        }
        return arrayList;
    }

    @NotNull
    public static final <R> List<R> mapIndexed(@NotNull char[] cArr, @NotNull Function2<? super Integer, ? super Character, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList(cArr.length);
        int length = cArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            arrayList.add(transform.invoke(Integer.valueOf(i3), Character.valueOf(cArr[i2])));
            i2++;
            i3++;
        }
        return arrayList;
    }

    @NotNull
    public static final <R> List<R> mapIndexed(@NotNull double[] dArr, @NotNull Function2<? super Integer, ? super Double, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList(dArr.length);
        int length = dArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            arrayList.add(transform.invoke(Integer.valueOf(i3), Double.valueOf(dArr[i2])));
            i2++;
            i3++;
        }
        return arrayList;
    }

    @NotNull
    public static final <R> List<R> mapIndexed(@NotNull float[] fArr, @NotNull Function2<? super Integer, ? super Float, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList(fArr.length);
        int length = fArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            arrayList.add(transform.invoke(Integer.valueOf(i3), Float.valueOf(fArr[i2])));
            i2++;
            i3++;
        }
        return arrayList;
    }

    @NotNull
    public static final <R> List<R> mapIndexed(@NotNull int[] iArr, @NotNull Function2<? super Integer, ? super Integer, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList(iArr.length);
        int length = iArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            arrayList.add(transform.invoke(Integer.valueOf(i3), Integer.valueOf(iArr[i2])));
            i2++;
            i3++;
        }
        return arrayList;
    }

    @NotNull
    public static final <R> List<R> mapIndexed(@NotNull long[] jArr, @NotNull Function2<? super Integer, ? super Long, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList(jArr.length);
        int length = jArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            arrayList.add(transform.invoke(Integer.valueOf(i3), Long.valueOf(jArr[i2])));
            i2++;
            i3++;
        }
        return arrayList;
    }

    @NotNull
    public static final <T, R> List<R> mapIndexed(@NotNull T[] tArr, @NotNull Function2<? super Integer, ? super T, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList(tArr.length);
        int length = tArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            arrayList.add(transform.invoke(Integer.valueOf(i3), tArr[i2]));
            i2++;
            i3++;
        }
        return arrayList;
    }

    @NotNull
    public static final <R> List<R> mapIndexed(@NotNull short[] sArr, @NotNull Function2<? super Integer, ? super Short, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList(sArr.length);
        int length = sArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            arrayList.add(transform.invoke(Integer.valueOf(i3), Short.valueOf(sArr[i2])));
            i2++;
            i3++;
        }
        return arrayList;
    }

    @NotNull
    public static final <R> List<R> mapIndexed(@NotNull boolean[] zArr, @NotNull Function2<? super Integer, ? super Boolean, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList(zArr.length);
        int length = zArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            arrayList.add(transform.invoke(Integer.valueOf(i3), Boolean.valueOf(zArr[i2])));
            i2++;
            i3++;
        }
        return arrayList;
    }

    @NotNull
    public static final <T, R> List<R> mapIndexedNotNull(@NotNull T[] tArr, @NotNull Function2<? super Integer, ? super T, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList();
        int length = tArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            int i4 = i3 + 1;
            R invoke = transform.invoke(Integer.valueOf(i3), tArr[i2]);
            if (invoke != null) {
                arrayList.add(invoke);
            }
            i2++;
            i3 = i4;
        }
        return arrayList;
    }

    @NotNull
    public static final <T, R, C extends Collection<? super R>> C mapIndexedNotNullTo(@NotNull T[] tArr, @NotNull C destination, @NotNull Function2<? super Integer, ? super T, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int length = tArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            int i4 = i3 + 1;
            R invoke = transform.invoke(Integer.valueOf(i3), tArr[i2]);
            if (invoke != null) {
                destination.add(invoke);
            }
            i2++;
            i3 = i4;
        }
        return destination;
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C mapIndexedTo(@NotNull byte[] bArr, @NotNull C destination, @NotNull Function2<? super Integer, ? super Byte, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int length = bArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            destination.add(transform.invoke(Integer.valueOf(i3), Byte.valueOf(bArr[i2])));
            i2++;
            i3++;
        }
        return destination;
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C mapIndexedTo(@NotNull char[] cArr, @NotNull C destination, @NotNull Function2<? super Integer, ? super Character, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int length = cArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            destination.add(transform.invoke(Integer.valueOf(i3), Character.valueOf(cArr[i2])));
            i2++;
            i3++;
        }
        return destination;
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C mapIndexedTo(@NotNull double[] dArr, @NotNull C destination, @NotNull Function2<? super Integer, ? super Double, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int length = dArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            destination.add(transform.invoke(Integer.valueOf(i3), Double.valueOf(dArr[i2])));
            i2++;
            i3++;
        }
        return destination;
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C mapIndexedTo(@NotNull float[] fArr, @NotNull C destination, @NotNull Function2<? super Integer, ? super Float, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int length = fArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            destination.add(transform.invoke(Integer.valueOf(i3), Float.valueOf(fArr[i2])));
            i2++;
            i3++;
        }
        return destination;
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C mapIndexedTo(@NotNull int[] iArr, @NotNull C destination, @NotNull Function2<? super Integer, ? super Integer, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int length = iArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            destination.add(transform.invoke(Integer.valueOf(i3), Integer.valueOf(iArr[i2])));
            i2++;
            i3++;
        }
        return destination;
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C mapIndexedTo(@NotNull long[] jArr, @NotNull C destination, @NotNull Function2<? super Integer, ? super Long, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int length = jArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            destination.add(transform.invoke(Integer.valueOf(i3), Long.valueOf(jArr[i2])));
            i2++;
            i3++;
        }
        return destination;
    }

    @NotNull
    public static final <T, R, C extends Collection<? super R>> C mapIndexedTo(@NotNull T[] tArr, @NotNull C destination, @NotNull Function2<? super Integer, ? super T, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int length = tArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            destination.add(transform.invoke(Integer.valueOf(i3), tArr[i2]));
            i2++;
            i3++;
        }
        return destination;
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C mapIndexedTo(@NotNull short[] sArr, @NotNull C destination, @NotNull Function2<? super Integer, ? super Short, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int length = sArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            destination.add(transform.invoke(Integer.valueOf(i3), Short.valueOf(sArr[i2])));
            i2++;
            i3++;
        }
        return destination;
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C mapIndexedTo(@NotNull boolean[] zArr, @NotNull C destination, @NotNull Function2<? super Integer, ? super Boolean, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int length = zArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            destination.add(transform.invoke(Integer.valueOf(i3), Boolean.valueOf(zArr[i2])));
            i2++;
            i3++;
        }
        return destination;
    }

    @NotNull
    public static final <T, R> List<R> mapNotNull(@NotNull T[] tArr, @NotNull Function1<? super T, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList();
        for (T t2 : tArr) {
            R invoke = transform.invoke(t2);
            if (invoke != null) {
                arrayList.add(invoke);
            }
        }
        return arrayList;
    }

    @NotNull
    public static final <T, R, C extends Collection<? super R>> C mapNotNullTo(@NotNull T[] tArr, @NotNull C destination, @NotNull Function1<? super T, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        for (T t2 : tArr) {
            R invoke = transform.invoke(t2);
            if (invoke != null) {
                destination.add(invoke);
            }
        }
        return destination;
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C mapTo(@NotNull byte[] bArr, @NotNull C destination, @NotNull Function1<? super Byte, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        for (byte b2 : bArr) {
            destination.add(transform.invoke(Byte.valueOf(b2)));
        }
        return destination;
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C mapTo(@NotNull char[] cArr, @NotNull C destination, @NotNull Function1<? super Character, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        for (char c2 : cArr) {
            destination.add(transform.invoke(Character.valueOf(c2)));
        }
        return destination;
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C mapTo(@NotNull double[] dArr, @NotNull C destination, @NotNull Function1<? super Double, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        for (double d2 : dArr) {
            destination.add(transform.invoke(Double.valueOf(d2)));
        }
        return destination;
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C mapTo(@NotNull float[] fArr, @NotNull C destination, @NotNull Function1<? super Float, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        for (float f2 : fArr) {
            destination.add(transform.invoke(Float.valueOf(f2)));
        }
        return destination;
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C mapTo(@NotNull int[] iArr, @NotNull C destination, @NotNull Function1<? super Integer, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        for (int i2 : iArr) {
            destination.add(transform.invoke(Integer.valueOf(i2)));
        }
        return destination;
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C mapTo(@NotNull long[] jArr, @NotNull C destination, @NotNull Function1<? super Long, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        for (long j2 : jArr) {
            destination.add(transform.invoke(Long.valueOf(j2)));
        }
        return destination;
    }

    @NotNull
    public static final <T, R, C extends Collection<? super R>> C mapTo(@NotNull T[] tArr, @NotNull C destination, @NotNull Function1<? super T, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        for (T t2 : tArr) {
            destination.add(transform.invoke(t2));
        }
        return destination;
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C mapTo(@NotNull short[] sArr, @NotNull C destination, @NotNull Function1<? super Short, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        for (short s2 : sArr) {
            destination.add(transform.invoke(Short.valueOf(s2)));
        }
        return destination;
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C mapTo(@NotNull boolean[] zArr, @NotNull C destination, @NotNull Function1<? super Boolean, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(transform, "transform");
        for (boolean z : zArr) {
            destination.add(transform.invoke(Boolean.valueOf(z)));
        }
        return destination;
    }

    @Deprecated(message = "Use maxByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ <R extends Comparable<? super R>> Boolean maxBy(boolean[] zArr, Function1<? super Boolean, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (zArr.length == 0) {
            return null;
        }
        boolean z = zArr[0];
        int lastIndex = getLastIndex(zArr);
        if (lastIndex != 0) {
            R invoke = selector.invoke(Boolean.valueOf(z));
            if (1 <= lastIndex) {
                while (true) {
                    boolean z2 = zArr[i2];
                    R invoke2 = selector.invoke(Boolean.valueOf(z2));
                    if (invoke.compareTo(invoke2) < 0) {
                        z = z2;
                        invoke = invoke2;
                    }
                    if (i2 == lastIndex) {
                        break;
                    }
                    i2++;
                }
            }
        }
        return Boolean.valueOf(z);
    }

    @Deprecated(message = "Use maxByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ <R extends Comparable<? super R>> Byte maxBy(byte[] bArr, Function1<? super Byte, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (bArr.length == 0) {
            return null;
        }
        byte b2 = bArr[0];
        lastIndex = getLastIndex(bArr);
        if (lastIndex != 0) {
            R invoke = selector.invoke(Byte.valueOf(b2));
            if (1 <= lastIndex) {
                while (true) {
                    byte b3 = bArr[i2];
                    R invoke2 = selector.invoke(Byte.valueOf(b3));
                    if (invoke.compareTo(invoke2) < 0) {
                        b2 = b3;
                        invoke = invoke2;
                    }
                    if (i2 == lastIndex) {
                        break;
                    }
                    i2++;
                }
            }
        }
        return Byte.valueOf(b2);
    }

    @Deprecated(message = "Use maxByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ <R extends Comparable<? super R>> Character maxBy(char[] cArr, Function1<? super Character, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (cArr.length == 0) {
            return null;
        }
        char c2 = cArr[0];
        int lastIndex = getLastIndex(cArr);
        if (lastIndex != 0) {
            R invoke = selector.invoke(Character.valueOf(c2));
            if (1 <= lastIndex) {
                while (true) {
                    char c3 = cArr[i2];
                    R invoke2 = selector.invoke(Character.valueOf(c3));
                    if (invoke.compareTo(invoke2) < 0) {
                        c2 = c3;
                        invoke = invoke2;
                    }
                    if (i2 == lastIndex) {
                        break;
                    }
                    i2++;
                }
            }
        }
        return Character.valueOf(c2);
    }

    @Deprecated(message = "Use maxByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ <R extends Comparable<? super R>> Double maxBy(double[] dArr, Function1<? super Double, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (dArr.length == 0) {
            return null;
        }
        double d2 = dArr[0];
        int lastIndex = getLastIndex(dArr);
        if (lastIndex != 0) {
            R invoke = selector.invoke(Double.valueOf(d2));
            if (1 <= lastIndex) {
                while (true) {
                    double d3 = dArr[i2];
                    R invoke2 = selector.invoke(Double.valueOf(d3));
                    if (invoke.compareTo(invoke2) < 0) {
                        d2 = d3;
                        invoke = invoke2;
                    }
                    if (i2 == lastIndex) {
                        break;
                    }
                    i2++;
                }
            }
        }
        return Double.valueOf(d2);
    }

    @Deprecated(message = "Use maxByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ <R extends Comparable<? super R>> Float maxBy(float[] fArr, Function1<? super Float, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (fArr.length == 0) {
            return null;
        }
        float f2 = fArr[0];
        int lastIndex = getLastIndex(fArr);
        if (lastIndex != 0) {
            R invoke = selector.invoke(Float.valueOf(f2));
            if (1 <= lastIndex) {
                while (true) {
                    float f3 = fArr[i2];
                    R invoke2 = selector.invoke(Float.valueOf(f3));
                    if (invoke.compareTo(invoke2) < 0) {
                        f2 = f3;
                        invoke = invoke2;
                    }
                    if (i2 == lastIndex) {
                        break;
                    }
                    i2++;
                }
            }
        }
        return Float.valueOf(f2);
    }

    @Deprecated(message = "Use maxByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ <R extends Comparable<? super R>> Integer maxBy(int[] iArr, Function1<? super Integer, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (iArr.length == 0) {
            return null;
        }
        int i3 = iArr[0];
        lastIndex = getLastIndex(iArr);
        if (lastIndex != 0) {
            R invoke = selector.invoke(Integer.valueOf(i3));
            if (1 <= lastIndex) {
                while (true) {
                    int i4 = iArr[i2];
                    R invoke2 = selector.invoke(Integer.valueOf(i4));
                    if (invoke.compareTo(invoke2) < 0) {
                        i3 = i4;
                        invoke = invoke2;
                    }
                    if (i2 == lastIndex) {
                        break;
                    }
                    i2++;
                }
            }
        }
        return Integer.valueOf(i3);
    }

    @Deprecated(message = "Use maxByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ <R extends Comparable<? super R>> Long maxBy(long[] jArr, Function1<? super Long, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (jArr.length == 0) {
            return null;
        }
        long j2 = jArr[0];
        lastIndex = getLastIndex(jArr);
        if (lastIndex != 0) {
            R invoke = selector.invoke(Long.valueOf(j2));
            if (1 <= lastIndex) {
                while (true) {
                    long j3 = jArr[i2];
                    R invoke2 = selector.invoke(Long.valueOf(j3));
                    if (invoke.compareTo(invoke2) < 0) {
                        j2 = j3;
                        invoke = invoke2;
                    }
                    if (i2 == lastIndex) {
                        break;
                    }
                    i2++;
                }
            }
        }
        return Long.valueOf(j2);
    }

    @Deprecated(message = "Use maxByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ <T, R extends Comparable<? super R>> T maxBy(T[] tArr, Function1<? super T, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (tArr.length == 0) {
            return null;
        }
        T t2 = tArr[0];
        lastIndex = getLastIndex(tArr);
        if (lastIndex != 0) {
            R invoke = selector.invoke(t2);
            if (1 <= lastIndex) {
                while (true) {
                    T t3 = tArr[i2];
                    R invoke2 = selector.invoke(t3);
                    if (invoke.compareTo(invoke2) < 0) {
                        t2 = t3;
                        invoke = invoke2;
                    }
                    if (i2 == lastIndex) {
                        break;
                    }
                    i2++;
                }
            }
        }
        return t2;
    }

    @Deprecated(message = "Use maxByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ <R extends Comparable<? super R>> Short maxBy(short[] sArr, Function1<? super Short, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (sArr.length == 0) {
            return null;
        }
        short s2 = sArr[0];
        lastIndex = getLastIndex(sArr);
        if (lastIndex != 0) {
            R invoke = selector.invoke(Short.valueOf(s2));
            if (1 <= lastIndex) {
                while (true) {
                    short s3 = sArr[i2];
                    R invoke2 = selector.invoke(Short.valueOf(s3));
                    if (invoke.compareTo(invoke2) < 0) {
                        s2 = s3;
                        invoke = invoke2;
                    }
                    if (i2 == lastIndex) {
                        break;
                    }
                    i2++;
                }
            }
        }
        return Short.valueOf(s2);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final <R extends Comparable<? super R>> Boolean maxByOrNull(@NotNull boolean[] zArr, @NotNull Function1<? super Boolean, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (zArr.length == 0) {
            return null;
        }
        boolean z = zArr[0];
        int lastIndex = getLastIndex(zArr);
        if (lastIndex == 0) {
            return Boolean.valueOf(z);
        }
        R invoke = selector.invoke(Boolean.valueOf(z));
        if (1 <= lastIndex) {
            while (true) {
                boolean z2 = zArr[i2];
                R invoke2 = selector.invoke(Boolean.valueOf(z2));
                if (invoke.compareTo(invoke2) < 0) {
                    z = z2;
                    invoke = invoke2;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Boolean.valueOf(z);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final <R extends Comparable<? super R>> Byte maxByOrNull(@NotNull byte[] bArr, @NotNull Function1<? super Byte, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (bArr.length == 0) {
            return null;
        }
        byte b2 = bArr[0];
        lastIndex = getLastIndex(bArr);
        if (lastIndex == 0) {
            return Byte.valueOf(b2);
        }
        R invoke = selector.invoke(Byte.valueOf(b2));
        if (1 <= lastIndex) {
            while (true) {
                byte b3 = bArr[i2];
                R invoke2 = selector.invoke(Byte.valueOf(b3));
                if (invoke.compareTo(invoke2) < 0) {
                    b2 = b3;
                    invoke = invoke2;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Byte.valueOf(b2);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final <R extends Comparable<? super R>> Character maxByOrNull(@NotNull char[] cArr, @NotNull Function1<? super Character, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (cArr.length == 0) {
            return null;
        }
        char c2 = cArr[0];
        int lastIndex = getLastIndex(cArr);
        if (lastIndex == 0) {
            return Character.valueOf(c2);
        }
        R invoke = selector.invoke(Character.valueOf(c2));
        if (1 <= lastIndex) {
            while (true) {
                char c3 = cArr[i2];
                R invoke2 = selector.invoke(Character.valueOf(c3));
                if (invoke.compareTo(invoke2) < 0) {
                    c2 = c3;
                    invoke = invoke2;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Character.valueOf(c2);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final <R extends Comparable<? super R>> Double maxByOrNull(@NotNull double[] dArr, @NotNull Function1<? super Double, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (dArr.length == 0) {
            return null;
        }
        double d2 = dArr[0];
        int lastIndex = getLastIndex(dArr);
        if (lastIndex == 0) {
            return Double.valueOf(d2);
        }
        R invoke = selector.invoke(Double.valueOf(d2));
        if (1 <= lastIndex) {
            while (true) {
                double d3 = dArr[i2];
                R invoke2 = selector.invoke(Double.valueOf(d3));
                if (invoke.compareTo(invoke2) < 0) {
                    d2 = d3;
                    invoke = invoke2;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Double.valueOf(d2);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final <R extends Comparable<? super R>> Float maxByOrNull(@NotNull float[] fArr, @NotNull Function1<? super Float, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (fArr.length == 0) {
            return null;
        }
        float f2 = fArr[0];
        int lastIndex = getLastIndex(fArr);
        if (lastIndex == 0) {
            return Float.valueOf(f2);
        }
        R invoke = selector.invoke(Float.valueOf(f2));
        if (1 <= lastIndex) {
            while (true) {
                float f3 = fArr[i2];
                R invoke2 = selector.invoke(Float.valueOf(f3));
                if (invoke.compareTo(invoke2) < 0) {
                    f2 = f3;
                    invoke = invoke2;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Float.valueOf(f2);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final <R extends Comparable<? super R>> Integer maxByOrNull(@NotNull int[] iArr, @NotNull Function1<? super Integer, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (iArr.length == 0) {
            return null;
        }
        int i3 = iArr[0];
        lastIndex = getLastIndex(iArr);
        if (lastIndex == 0) {
            return Integer.valueOf(i3);
        }
        R invoke = selector.invoke(Integer.valueOf(i3));
        if (1 <= lastIndex) {
            while (true) {
                int i4 = iArr[i2];
                R invoke2 = selector.invoke(Integer.valueOf(i4));
                if (invoke.compareTo(invoke2) < 0) {
                    i3 = i4;
                    invoke = invoke2;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Integer.valueOf(i3);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final <R extends Comparable<? super R>> Long maxByOrNull(@NotNull long[] jArr, @NotNull Function1<? super Long, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (jArr.length == 0) {
            return null;
        }
        long j2 = jArr[0];
        lastIndex = getLastIndex(jArr);
        if (lastIndex == 0) {
            return Long.valueOf(j2);
        }
        R invoke = selector.invoke(Long.valueOf(j2));
        if (1 <= lastIndex) {
            while (true) {
                long j3 = jArr[i2];
                R invoke2 = selector.invoke(Long.valueOf(j3));
                if (invoke.compareTo(invoke2) < 0) {
                    j2 = j3;
                    invoke = invoke2;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Long.valueOf(j2);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final <T, R extends Comparable<? super R>> T maxByOrNull(@NotNull T[] tArr, @NotNull Function1<? super T, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (tArr.length == 0) {
            return null;
        }
        T t2 = tArr[0];
        lastIndex = getLastIndex(tArr);
        if (lastIndex == 0) {
            return t2;
        }
        R invoke = selector.invoke(t2);
        if (1 <= lastIndex) {
            while (true) {
                T t3 = tArr[i2];
                R invoke2 = selector.invoke(t3);
                if (invoke.compareTo(invoke2) < 0) {
                    t2 = t3;
                    invoke = invoke2;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return t2;
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final <R extends Comparable<? super R>> Short maxByOrNull(@NotNull short[] sArr, @NotNull Function1<? super Short, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (sArr.length == 0) {
            return null;
        }
        short s2 = sArr[0];
        lastIndex = getLastIndex(sArr);
        if (lastIndex == 0) {
            return Short.valueOf(s2);
        }
        R invoke = selector.invoke(Short.valueOf(s2));
        if (1 <= lastIndex) {
            while (true) {
                short s3 = sArr[i2];
                R invoke2 = selector.invoke(Short.valueOf(s3));
                if (invoke.compareTo(invoke2) < 0) {
                    s2 = s3;
                    invoke = invoke2;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Short.valueOf(s2);
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final double maxOf(byte[] bArr, Function1<? super Byte, Double> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (bArr.length == 0) {
            throw new NoSuchElementException();
        }
        double doubleValue = selector.invoke(Byte.valueOf(bArr[0])).doubleValue();
        lastIndex = getLastIndex(bArr);
        if (1 <= lastIndex) {
            while (true) {
                doubleValue = Math.max(doubleValue, selector.invoke(Byte.valueOf(bArr[i2])).doubleValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return doubleValue;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final double maxOf(char[] cArr, Function1<? super Character, Double> selector) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (cArr.length == 0) {
            throw new NoSuchElementException();
        }
        double doubleValue = selector.invoke(Character.valueOf(cArr[0])).doubleValue();
        int lastIndex = getLastIndex(cArr);
        if (1 <= lastIndex) {
            while (true) {
                doubleValue = Math.max(doubleValue, selector.invoke(Character.valueOf(cArr[i2])).doubleValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return doubleValue;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final double maxOf(double[] dArr, Function1<? super Double, Double> selector) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (dArr.length == 0) {
            throw new NoSuchElementException();
        }
        double doubleValue = selector.invoke(Double.valueOf(dArr[0])).doubleValue();
        int lastIndex = getLastIndex(dArr);
        if (1 <= lastIndex) {
            while (true) {
                doubleValue = Math.max(doubleValue, selector.invoke(Double.valueOf(dArr[i2])).doubleValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return doubleValue;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final double maxOf(float[] fArr, Function1<? super Float, Double> selector) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (fArr.length == 0) {
            throw new NoSuchElementException();
        }
        double doubleValue = selector.invoke(Float.valueOf(fArr[0])).doubleValue();
        int lastIndex = getLastIndex(fArr);
        if (1 <= lastIndex) {
            while (true) {
                doubleValue = Math.max(doubleValue, selector.invoke(Float.valueOf(fArr[i2])).doubleValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return doubleValue;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final double maxOf(int[] iArr, Function1<? super Integer, Double> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (iArr.length == 0) {
            throw new NoSuchElementException();
        }
        double doubleValue = selector.invoke(Integer.valueOf(iArr[0])).doubleValue();
        lastIndex = getLastIndex(iArr);
        if (1 <= lastIndex) {
            while (true) {
                doubleValue = Math.max(doubleValue, selector.invoke(Integer.valueOf(iArr[i2])).doubleValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return doubleValue;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final double maxOf(long[] jArr, Function1<? super Long, Double> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (jArr.length == 0) {
            throw new NoSuchElementException();
        }
        double doubleValue = selector.invoke(Long.valueOf(jArr[0])).doubleValue();
        lastIndex = getLastIndex(jArr);
        if (1 <= lastIndex) {
            while (true) {
                doubleValue = Math.max(doubleValue, selector.invoke(Long.valueOf(jArr[i2])).doubleValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return doubleValue;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <T> double maxOf(T[] tArr, Function1<? super T, Double> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (tArr.length == 0) {
            throw new NoSuchElementException();
        }
        double doubleValue = selector.invoke(tArr[0]).doubleValue();
        lastIndex = getLastIndex(tArr);
        if (1 <= lastIndex) {
            while (true) {
                doubleValue = Math.max(doubleValue, selector.invoke(tArr[i2]).doubleValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return doubleValue;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final double maxOf(short[] sArr, Function1<? super Short, Double> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (sArr.length == 0) {
            throw new NoSuchElementException();
        }
        double doubleValue = selector.invoke(Short.valueOf(sArr[0])).doubleValue();
        lastIndex = getLastIndex(sArr);
        if (1 <= lastIndex) {
            while (true) {
                doubleValue = Math.max(doubleValue, selector.invoke(Short.valueOf(sArr[i2])).doubleValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return doubleValue;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final double maxOf(boolean[] zArr, Function1<? super Boolean, Double> selector) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (zArr.length == 0) {
            throw new NoSuchElementException();
        }
        double doubleValue = selector.invoke(Boolean.valueOf(zArr[0])).doubleValue();
        int lastIndex = getLastIndex(zArr);
        if (1 <= lastIndex) {
            while (true) {
                doubleValue = Math.max(doubleValue, selector.invoke(Boolean.valueOf(zArr[i2])).doubleValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return doubleValue;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: maxOf */
    private static final float m538maxOf(byte[] bArr, Function1<? super Byte, Float> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (bArr.length == 0) {
            throw new NoSuchElementException();
        }
        float floatValue = selector.invoke(Byte.valueOf(bArr[0])).floatValue();
        lastIndex = getLastIndex(bArr);
        if (1 <= lastIndex) {
            while (true) {
                floatValue = Math.max(floatValue, selector.invoke(Byte.valueOf(bArr[i2])).floatValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return floatValue;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: maxOf */
    private static final float m539maxOf(char[] cArr, Function1<? super Character, Float> selector) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (cArr.length == 0) {
            throw new NoSuchElementException();
        }
        float floatValue = selector.invoke(Character.valueOf(cArr[0])).floatValue();
        int lastIndex = getLastIndex(cArr);
        if (1 <= lastIndex) {
            while (true) {
                floatValue = Math.max(floatValue, selector.invoke(Character.valueOf(cArr[i2])).floatValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return floatValue;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: maxOf */
    private static final float m540maxOf(double[] dArr, Function1<? super Double, Float> selector) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (dArr.length == 0) {
            throw new NoSuchElementException();
        }
        float floatValue = selector.invoke(Double.valueOf(dArr[0])).floatValue();
        int lastIndex = getLastIndex(dArr);
        if (1 <= lastIndex) {
            while (true) {
                floatValue = Math.max(floatValue, selector.invoke(Double.valueOf(dArr[i2])).floatValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return floatValue;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: maxOf */
    private static final float m541maxOf(float[] fArr, Function1<? super Float, Float> selector) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (fArr.length == 0) {
            throw new NoSuchElementException();
        }
        float floatValue = selector.invoke(Float.valueOf(fArr[0])).floatValue();
        int lastIndex = getLastIndex(fArr);
        if (1 <= lastIndex) {
            while (true) {
                floatValue = Math.max(floatValue, selector.invoke(Float.valueOf(fArr[i2])).floatValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return floatValue;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: maxOf */
    private static final float m542maxOf(int[] iArr, Function1<? super Integer, Float> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (iArr.length == 0) {
            throw new NoSuchElementException();
        }
        float floatValue = selector.invoke(Integer.valueOf(iArr[0])).floatValue();
        lastIndex = getLastIndex(iArr);
        if (1 <= lastIndex) {
            while (true) {
                floatValue = Math.max(floatValue, selector.invoke(Integer.valueOf(iArr[i2])).floatValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return floatValue;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: maxOf */
    private static final float m543maxOf(long[] jArr, Function1<? super Long, Float> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (jArr.length == 0) {
            throw new NoSuchElementException();
        }
        float floatValue = selector.invoke(Long.valueOf(jArr[0])).floatValue();
        lastIndex = getLastIndex(jArr);
        if (1 <= lastIndex) {
            while (true) {
                floatValue = Math.max(floatValue, selector.invoke(Long.valueOf(jArr[i2])).floatValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return floatValue;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: maxOf */
    private static final <T> float m544maxOf(T[] tArr, Function1<? super T, Float> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (tArr.length == 0) {
            throw new NoSuchElementException();
        }
        float floatValue = selector.invoke(tArr[0]).floatValue();
        lastIndex = getLastIndex(tArr);
        if (1 <= lastIndex) {
            while (true) {
                floatValue = Math.max(floatValue, selector.invoke(tArr[i2]).floatValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return floatValue;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: maxOf */
    private static final float m545maxOf(short[] sArr, Function1<? super Short, Float> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (sArr.length == 0) {
            throw new NoSuchElementException();
        }
        float floatValue = selector.invoke(Short.valueOf(sArr[0])).floatValue();
        lastIndex = getLastIndex(sArr);
        if (1 <= lastIndex) {
            while (true) {
                floatValue = Math.max(floatValue, selector.invoke(Short.valueOf(sArr[i2])).floatValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return floatValue;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: maxOf */
    private static final float m546maxOf(boolean[] zArr, Function1<? super Boolean, Float> selector) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (zArr.length == 0) {
            throw new NoSuchElementException();
        }
        float floatValue = selector.invoke(Boolean.valueOf(zArr[0])).floatValue();
        int lastIndex = getLastIndex(zArr);
        if (1 <= lastIndex) {
            while (true) {
                floatValue = Math.max(floatValue, selector.invoke(Boolean.valueOf(zArr[i2])).floatValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return floatValue;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: maxOf */
    private static final <R extends Comparable<? super R>> R m547maxOf(byte[] bArr, Function1<? super Byte, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (bArr.length == 0) {
            throw new NoSuchElementException();
        }
        R invoke = selector.invoke(Byte.valueOf(bArr[0]));
        lastIndex = getLastIndex(bArr);
        if (1 <= lastIndex) {
            while (true) {
                R invoke2 = selector.invoke(Byte.valueOf(bArr[i2]));
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: maxOf */
    private static final <R extends Comparable<? super R>> R m548maxOf(char[] cArr, Function1<? super Character, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (cArr.length == 0) {
            throw new NoSuchElementException();
        }
        R invoke = selector.invoke(Character.valueOf(cArr[0]));
        int lastIndex = getLastIndex(cArr);
        if (1 <= lastIndex) {
            while (true) {
                R invoke2 = selector.invoke(Character.valueOf(cArr[i2]));
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: maxOf */
    private static final <R extends Comparable<? super R>> R m549maxOf(double[] dArr, Function1<? super Double, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (dArr.length == 0) {
            throw new NoSuchElementException();
        }
        R invoke = selector.invoke(Double.valueOf(dArr[0]));
        int lastIndex = getLastIndex(dArr);
        if (1 <= lastIndex) {
            while (true) {
                R invoke2 = selector.invoke(Double.valueOf(dArr[i2]));
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: maxOf */
    private static final <R extends Comparable<? super R>> R m550maxOf(float[] fArr, Function1<? super Float, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (fArr.length == 0) {
            throw new NoSuchElementException();
        }
        R invoke = selector.invoke(Float.valueOf(fArr[0]));
        int lastIndex = getLastIndex(fArr);
        if (1 <= lastIndex) {
            while (true) {
                R invoke2 = selector.invoke(Float.valueOf(fArr[i2]));
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: maxOf */
    private static final <R extends Comparable<? super R>> R m551maxOf(int[] iArr, Function1<? super Integer, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (iArr.length == 0) {
            throw new NoSuchElementException();
        }
        R invoke = selector.invoke(Integer.valueOf(iArr[0]));
        lastIndex = getLastIndex(iArr);
        if (1 <= lastIndex) {
            while (true) {
                R invoke2 = selector.invoke(Integer.valueOf(iArr[i2]));
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: maxOf */
    private static final <R extends Comparable<? super R>> R m552maxOf(long[] jArr, Function1<? super Long, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (jArr.length == 0) {
            throw new NoSuchElementException();
        }
        R invoke = selector.invoke(Long.valueOf(jArr[0]));
        lastIndex = getLastIndex(jArr);
        if (1 <= lastIndex) {
            while (true) {
                R invoke2 = selector.invoke(Long.valueOf(jArr[i2]));
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: maxOf */
    private static final <T, R extends Comparable<? super R>> R m553maxOf(T[] tArr, Function1<? super T, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (tArr.length == 0) {
            throw new NoSuchElementException();
        }
        R invoke = selector.invoke(tArr[0]);
        lastIndex = getLastIndex(tArr);
        if (1 <= lastIndex) {
            while (true) {
                R invoke2 = selector.invoke(tArr[i2]);
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: maxOf */
    private static final <R extends Comparable<? super R>> R m554maxOf(short[] sArr, Function1<? super Short, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (sArr.length == 0) {
            throw new NoSuchElementException();
        }
        R invoke = selector.invoke(Short.valueOf(sArr[0]));
        lastIndex = getLastIndex(sArr);
        if (1 <= lastIndex) {
            while (true) {
                R invoke2 = selector.invoke(Short.valueOf(sArr[i2]));
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: maxOf */
    private static final <R extends Comparable<? super R>> R m555maxOf(boolean[] zArr, Function1<? super Boolean, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (zArr.length == 0) {
            throw new NoSuchElementException();
        }
        R invoke = selector.invoke(Boolean.valueOf(zArr[0]));
        int lastIndex = getLastIndex(zArr);
        if (1 <= lastIndex) {
            while (true) {
                R invoke2 = selector.invoke(Boolean.valueOf(zArr[i2]));
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <R extends Comparable<? super R>> R maxOfOrNull(byte[] bArr, Function1<? super Byte, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (bArr.length == 0) {
            return null;
        }
        R invoke = selector.invoke(Byte.valueOf(bArr[0]));
        lastIndex = getLastIndex(bArr);
        if (1 <= lastIndex) {
            while (true) {
                R invoke2 = selector.invoke(Byte.valueOf(bArr[i2]));
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <R extends Comparable<? super R>> R maxOfOrNull(char[] cArr, Function1<? super Character, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (cArr.length == 0) {
            return null;
        }
        R invoke = selector.invoke(Character.valueOf(cArr[0]));
        int lastIndex = getLastIndex(cArr);
        if (1 <= lastIndex) {
            while (true) {
                R invoke2 = selector.invoke(Character.valueOf(cArr[i2]));
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <R extends Comparable<? super R>> R maxOfOrNull(double[] dArr, Function1<? super Double, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (dArr.length == 0) {
            return null;
        }
        R invoke = selector.invoke(Double.valueOf(dArr[0]));
        int lastIndex = getLastIndex(dArr);
        if (1 <= lastIndex) {
            while (true) {
                R invoke2 = selector.invoke(Double.valueOf(dArr[i2]));
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <R extends Comparable<? super R>> R maxOfOrNull(float[] fArr, Function1<? super Float, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (fArr.length == 0) {
            return null;
        }
        R invoke = selector.invoke(Float.valueOf(fArr[0]));
        int lastIndex = getLastIndex(fArr);
        if (1 <= lastIndex) {
            while (true) {
                R invoke2 = selector.invoke(Float.valueOf(fArr[i2]));
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <R extends Comparable<? super R>> R maxOfOrNull(int[] iArr, Function1<? super Integer, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (iArr.length == 0) {
            return null;
        }
        R invoke = selector.invoke(Integer.valueOf(iArr[0]));
        lastIndex = getLastIndex(iArr);
        if (1 <= lastIndex) {
            while (true) {
                R invoke2 = selector.invoke(Integer.valueOf(iArr[i2]));
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <R extends Comparable<? super R>> R maxOfOrNull(long[] jArr, Function1<? super Long, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (jArr.length == 0) {
            return null;
        }
        R invoke = selector.invoke(Long.valueOf(jArr[0]));
        lastIndex = getLastIndex(jArr);
        if (1 <= lastIndex) {
            while (true) {
                R invoke2 = selector.invoke(Long.valueOf(jArr[i2]));
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <T, R extends Comparable<? super R>> R maxOfOrNull(T[] tArr, Function1<? super T, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (tArr.length == 0) {
            return null;
        }
        R invoke = selector.invoke(tArr[0]);
        lastIndex = getLastIndex(tArr);
        if (1 <= lastIndex) {
            while (true) {
                R invoke2 = selector.invoke(tArr[i2]);
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <R extends Comparable<? super R>> R maxOfOrNull(short[] sArr, Function1<? super Short, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (sArr.length == 0) {
            return null;
        }
        R invoke = selector.invoke(Short.valueOf(sArr[0]));
        lastIndex = getLastIndex(sArr);
        if (1 <= lastIndex) {
            while (true) {
                R invoke2 = selector.invoke(Short.valueOf(sArr[i2]));
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <R extends Comparable<? super R>> R maxOfOrNull(boolean[] zArr, Function1<? super Boolean, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (zArr.length == 0) {
            return null;
        }
        R invoke = selector.invoke(Boolean.valueOf(zArr[0]));
        int lastIndex = getLastIndex(zArr);
        if (1 <= lastIndex) {
            while (true) {
                R invoke2 = selector.invoke(Boolean.valueOf(zArr[i2]));
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: maxOfOrNull */
    private static final Double m556maxOfOrNull(byte[] bArr, Function1<? super Byte, Double> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (bArr.length == 0) {
            return null;
        }
        double doubleValue = selector.invoke(Byte.valueOf(bArr[0])).doubleValue();
        lastIndex = getLastIndex(bArr);
        if (1 <= lastIndex) {
            while (true) {
                doubleValue = Math.max(doubleValue, selector.invoke(Byte.valueOf(bArr[i2])).doubleValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Double.valueOf(doubleValue);
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: maxOfOrNull */
    private static final Double m557maxOfOrNull(char[] cArr, Function1<? super Character, Double> selector) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (cArr.length == 0) {
            return null;
        }
        double doubleValue = selector.invoke(Character.valueOf(cArr[0])).doubleValue();
        int lastIndex = getLastIndex(cArr);
        if (1 <= lastIndex) {
            while (true) {
                doubleValue = Math.max(doubleValue, selector.invoke(Character.valueOf(cArr[i2])).doubleValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Double.valueOf(doubleValue);
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: maxOfOrNull */
    private static final Double m558maxOfOrNull(double[] dArr, Function1<? super Double, Double> selector) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (dArr.length == 0) {
            return null;
        }
        double doubleValue = selector.invoke(Double.valueOf(dArr[0])).doubleValue();
        int lastIndex = getLastIndex(dArr);
        if (1 <= lastIndex) {
            while (true) {
                doubleValue = Math.max(doubleValue, selector.invoke(Double.valueOf(dArr[i2])).doubleValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Double.valueOf(doubleValue);
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: maxOfOrNull */
    private static final Double m559maxOfOrNull(float[] fArr, Function1<? super Float, Double> selector) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (fArr.length == 0) {
            return null;
        }
        double doubleValue = selector.invoke(Float.valueOf(fArr[0])).doubleValue();
        int lastIndex = getLastIndex(fArr);
        if (1 <= lastIndex) {
            while (true) {
                doubleValue = Math.max(doubleValue, selector.invoke(Float.valueOf(fArr[i2])).doubleValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Double.valueOf(doubleValue);
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: maxOfOrNull */
    private static final Double m560maxOfOrNull(int[] iArr, Function1<? super Integer, Double> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (iArr.length == 0) {
            return null;
        }
        double doubleValue = selector.invoke(Integer.valueOf(iArr[0])).doubleValue();
        lastIndex = getLastIndex(iArr);
        if (1 <= lastIndex) {
            while (true) {
                doubleValue = Math.max(doubleValue, selector.invoke(Integer.valueOf(iArr[i2])).doubleValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Double.valueOf(doubleValue);
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: maxOfOrNull */
    private static final Double m561maxOfOrNull(long[] jArr, Function1<? super Long, Double> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (jArr.length == 0) {
            return null;
        }
        double doubleValue = selector.invoke(Long.valueOf(jArr[0])).doubleValue();
        lastIndex = getLastIndex(jArr);
        if (1 <= lastIndex) {
            while (true) {
                doubleValue = Math.max(doubleValue, selector.invoke(Long.valueOf(jArr[i2])).doubleValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Double.valueOf(doubleValue);
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: maxOfOrNull */
    private static final <T> Double m562maxOfOrNull(T[] tArr, Function1<? super T, Double> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (tArr.length == 0) {
            return null;
        }
        double doubleValue = selector.invoke(tArr[0]).doubleValue();
        lastIndex = getLastIndex(tArr);
        if (1 <= lastIndex) {
            while (true) {
                doubleValue = Math.max(doubleValue, selector.invoke(tArr[i2]).doubleValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Double.valueOf(doubleValue);
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: maxOfOrNull */
    private static final Double m563maxOfOrNull(short[] sArr, Function1<? super Short, Double> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (sArr.length == 0) {
            return null;
        }
        double doubleValue = selector.invoke(Short.valueOf(sArr[0])).doubleValue();
        lastIndex = getLastIndex(sArr);
        if (1 <= lastIndex) {
            while (true) {
                doubleValue = Math.max(doubleValue, selector.invoke(Short.valueOf(sArr[i2])).doubleValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Double.valueOf(doubleValue);
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: maxOfOrNull */
    private static final Double m564maxOfOrNull(boolean[] zArr, Function1<? super Boolean, Double> selector) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (zArr.length == 0) {
            return null;
        }
        double doubleValue = selector.invoke(Boolean.valueOf(zArr[0])).doubleValue();
        int lastIndex = getLastIndex(zArr);
        if (1 <= lastIndex) {
            while (true) {
                doubleValue = Math.max(doubleValue, selector.invoke(Boolean.valueOf(zArr[i2])).doubleValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Double.valueOf(doubleValue);
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: maxOfOrNull */
    private static final Float m565maxOfOrNull(byte[] bArr, Function1<? super Byte, Float> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (bArr.length == 0) {
            return null;
        }
        float floatValue = selector.invoke(Byte.valueOf(bArr[0])).floatValue();
        lastIndex = getLastIndex(bArr);
        if (1 <= lastIndex) {
            while (true) {
                floatValue = Math.max(floatValue, selector.invoke(Byte.valueOf(bArr[i2])).floatValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Float.valueOf(floatValue);
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: maxOfOrNull */
    private static final Float m566maxOfOrNull(char[] cArr, Function1<? super Character, Float> selector) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (cArr.length == 0) {
            return null;
        }
        float floatValue = selector.invoke(Character.valueOf(cArr[0])).floatValue();
        int lastIndex = getLastIndex(cArr);
        if (1 <= lastIndex) {
            while (true) {
                floatValue = Math.max(floatValue, selector.invoke(Character.valueOf(cArr[i2])).floatValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Float.valueOf(floatValue);
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: maxOfOrNull */
    private static final Float m567maxOfOrNull(double[] dArr, Function1<? super Double, Float> selector) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (dArr.length == 0) {
            return null;
        }
        float floatValue = selector.invoke(Double.valueOf(dArr[0])).floatValue();
        int lastIndex = getLastIndex(dArr);
        if (1 <= lastIndex) {
            while (true) {
                floatValue = Math.max(floatValue, selector.invoke(Double.valueOf(dArr[i2])).floatValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Float.valueOf(floatValue);
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: maxOfOrNull */
    private static final Float m568maxOfOrNull(float[] fArr, Function1<? super Float, Float> selector) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (fArr.length == 0) {
            return null;
        }
        float floatValue = selector.invoke(Float.valueOf(fArr[0])).floatValue();
        int lastIndex = getLastIndex(fArr);
        if (1 <= lastIndex) {
            while (true) {
                floatValue = Math.max(floatValue, selector.invoke(Float.valueOf(fArr[i2])).floatValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Float.valueOf(floatValue);
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: maxOfOrNull */
    private static final Float m569maxOfOrNull(int[] iArr, Function1<? super Integer, Float> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (iArr.length == 0) {
            return null;
        }
        float floatValue = selector.invoke(Integer.valueOf(iArr[0])).floatValue();
        lastIndex = getLastIndex(iArr);
        if (1 <= lastIndex) {
            while (true) {
                floatValue = Math.max(floatValue, selector.invoke(Integer.valueOf(iArr[i2])).floatValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Float.valueOf(floatValue);
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: maxOfOrNull */
    private static final Float m570maxOfOrNull(long[] jArr, Function1<? super Long, Float> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (jArr.length == 0) {
            return null;
        }
        float floatValue = selector.invoke(Long.valueOf(jArr[0])).floatValue();
        lastIndex = getLastIndex(jArr);
        if (1 <= lastIndex) {
            while (true) {
                floatValue = Math.max(floatValue, selector.invoke(Long.valueOf(jArr[i2])).floatValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Float.valueOf(floatValue);
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: maxOfOrNull */
    private static final <T> Float m571maxOfOrNull(T[] tArr, Function1<? super T, Float> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (tArr.length == 0) {
            return null;
        }
        float floatValue = selector.invoke(tArr[0]).floatValue();
        lastIndex = getLastIndex(tArr);
        if (1 <= lastIndex) {
            while (true) {
                floatValue = Math.max(floatValue, selector.invoke(tArr[i2]).floatValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Float.valueOf(floatValue);
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: maxOfOrNull */
    private static final Float m572maxOfOrNull(short[] sArr, Function1<? super Short, Float> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (sArr.length == 0) {
            return null;
        }
        float floatValue = selector.invoke(Short.valueOf(sArr[0])).floatValue();
        lastIndex = getLastIndex(sArr);
        if (1 <= lastIndex) {
            while (true) {
                floatValue = Math.max(floatValue, selector.invoke(Short.valueOf(sArr[i2])).floatValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Float.valueOf(floatValue);
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: maxOfOrNull */
    private static final Float m573maxOfOrNull(boolean[] zArr, Function1<? super Boolean, Float> selector) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (zArr.length == 0) {
            return null;
        }
        float floatValue = selector.invoke(Boolean.valueOf(zArr[0])).floatValue();
        int lastIndex = getLastIndex(zArr);
        if (1 <= lastIndex) {
            while (true) {
                floatValue = Math.max(floatValue, selector.invoke(Boolean.valueOf(zArr[i2])).floatValue());
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <R> R maxOfWith(byte[] bArr, Comparator<? super R> comparator, Function1<? super Byte, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (bArr.length == 0) {
            throw new NoSuchElementException();
        }
        Object obj = (R) selector.invoke(Byte.valueOf(bArr[0]));
        lastIndex = getLastIndex(bArr);
        if (1 <= lastIndex) {
            while (true) {
                R invoke = selector.invoke(Byte.valueOf(bArr[i2]));
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <R> R maxOfWith(char[] cArr, Comparator<? super R> comparator, Function1<? super Character, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (cArr.length == 0) {
            throw new NoSuchElementException();
        }
        Object obj = (R) selector.invoke(Character.valueOf(cArr[0]));
        int lastIndex = getLastIndex(cArr);
        if (1 <= lastIndex) {
            while (true) {
                R invoke = selector.invoke(Character.valueOf(cArr[i2]));
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <R> R maxOfWith(double[] dArr, Comparator<? super R> comparator, Function1<? super Double, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (dArr.length == 0) {
            throw new NoSuchElementException();
        }
        Object obj = (R) selector.invoke(Double.valueOf(dArr[0]));
        int lastIndex = getLastIndex(dArr);
        if (1 <= lastIndex) {
            while (true) {
                R invoke = selector.invoke(Double.valueOf(dArr[i2]));
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <R> R maxOfWith(float[] fArr, Comparator<? super R> comparator, Function1<? super Float, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (fArr.length == 0) {
            throw new NoSuchElementException();
        }
        Object obj = (R) selector.invoke(Float.valueOf(fArr[0]));
        int lastIndex = getLastIndex(fArr);
        if (1 <= lastIndex) {
            while (true) {
                R invoke = selector.invoke(Float.valueOf(fArr[i2]));
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <R> R maxOfWith(int[] iArr, Comparator<? super R> comparator, Function1<? super Integer, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (iArr.length == 0) {
            throw new NoSuchElementException();
        }
        Object obj = (R) selector.invoke(Integer.valueOf(iArr[0]));
        lastIndex = getLastIndex(iArr);
        if (1 <= lastIndex) {
            while (true) {
                R invoke = selector.invoke(Integer.valueOf(iArr[i2]));
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <R> R maxOfWith(long[] jArr, Comparator<? super R> comparator, Function1<? super Long, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (jArr.length == 0) {
            throw new NoSuchElementException();
        }
        Object obj = (R) selector.invoke(Long.valueOf(jArr[0]));
        lastIndex = getLastIndex(jArr);
        if (1 <= lastIndex) {
            while (true) {
                R invoke = selector.invoke(Long.valueOf(jArr[i2]));
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <T, R> R maxOfWith(T[] tArr, Comparator<? super R> comparator, Function1<? super T, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (tArr.length == 0) {
            throw new NoSuchElementException();
        }
        Object obj = (R) selector.invoke(tArr[0]);
        lastIndex = getLastIndex(tArr);
        if (1 <= lastIndex) {
            while (true) {
                R invoke = selector.invoke(tArr[i2]);
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <R> R maxOfWith(short[] sArr, Comparator<? super R> comparator, Function1<? super Short, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (sArr.length == 0) {
            throw new NoSuchElementException();
        }
        Object obj = (R) selector.invoke(Short.valueOf(sArr[0]));
        lastIndex = getLastIndex(sArr);
        if (1 <= lastIndex) {
            while (true) {
                R invoke = selector.invoke(Short.valueOf(sArr[i2]));
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <R> R maxOfWith(boolean[] zArr, Comparator<? super R> comparator, Function1<? super Boolean, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (zArr.length == 0) {
            throw new NoSuchElementException();
        }
        Object obj = (R) selector.invoke(Boolean.valueOf(zArr[0]));
        int lastIndex = getLastIndex(zArr);
        if (1 <= lastIndex) {
            while (true) {
                R invoke = selector.invoke(Boolean.valueOf(zArr[i2]));
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <R> R maxOfWithOrNull(byte[] bArr, Comparator<? super R> comparator, Function1<? super Byte, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (bArr.length == 0) {
            return null;
        }
        Object obj = (R) selector.invoke(Byte.valueOf(bArr[0]));
        lastIndex = getLastIndex(bArr);
        if (1 <= lastIndex) {
            while (true) {
                R invoke = selector.invoke(Byte.valueOf(bArr[i2]));
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <R> R maxOfWithOrNull(char[] cArr, Comparator<? super R> comparator, Function1<? super Character, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (cArr.length == 0) {
            return null;
        }
        Object obj = (R) selector.invoke(Character.valueOf(cArr[0]));
        int lastIndex = getLastIndex(cArr);
        if (1 <= lastIndex) {
            while (true) {
                R invoke = selector.invoke(Character.valueOf(cArr[i2]));
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <R> R maxOfWithOrNull(double[] dArr, Comparator<? super R> comparator, Function1<? super Double, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (dArr.length == 0) {
            return null;
        }
        Object obj = (R) selector.invoke(Double.valueOf(dArr[0]));
        int lastIndex = getLastIndex(dArr);
        if (1 <= lastIndex) {
            while (true) {
                R invoke = selector.invoke(Double.valueOf(dArr[i2]));
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <R> R maxOfWithOrNull(float[] fArr, Comparator<? super R> comparator, Function1<? super Float, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (fArr.length == 0) {
            return null;
        }
        Object obj = (R) selector.invoke(Float.valueOf(fArr[0]));
        int lastIndex = getLastIndex(fArr);
        if (1 <= lastIndex) {
            while (true) {
                R invoke = selector.invoke(Float.valueOf(fArr[i2]));
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <R> R maxOfWithOrNull(int[] iArr, Comparator<? super R> comparator, Function1<? super Integer, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (iArr.length == 0) {
            return null;
        }
        Object obj = (R) selector.invoke(Integer.valueOf(iArr[0]));
        lastIndex = getLastIndex(iArr);
        if (1 <= lastIndex) {
            while (true) {
                R invoke = selector.invoke(Integer.valueOf(iArr[i2]));
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <R> R maxOfWithOrNull(long[] jArr, Comparator<? super R> comparator, Function1<? super Long, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (jArr.length == 0) {
            return null;
        }
        Object obj = (R) selector.invoke(Long.valueOf(jArr[0]));
        lastIndex = getLastIndex(jArr);
        if (1 <= lastIndex) {
            while (true) {
                R invoke = selector.invoke(Long.valueOf(jArr[i2]));
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <T, R> R maxOfWithOrNull(T[] tArr, Comparator<? super R> comparator, Function1<? super T, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (tArr.length == 0) {
            return null;
        }
        Object obj = (R) selector.invoke(tArr[0]);
        lastIndex = getLastIndex(tArr);
        if (1 <= lastIndex) {
            while (true) {
                R invoke = selector.invoke(tArr[i2]);
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <R> R maxOfWithOrNull(short[] sArr, Comparator<? super R> comparator, Function1<? super Short, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (sArr.length == 0) {
            return null;
        }
        Object obj = (R) selector.invoke(Short.valueOf(sArr[0]));
        lastIndex = getLastIndex(sArr);
        if (1 <= lastIndex) {
            while (true) {
                R invoke = selector.invoke(Short.valueOf(sArr[i2]));
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <R> R maxOfWithOrNull(boolean[] zArr, Comparator<? super R> comparator, Function1<? super Boolean, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (zArr.length == 0) {
            return null;
        }
        Object obj = (R) selector.invoke(Boolean.valueOf(zArr[0]));
        int lastIndex = getLastIndex(zArr);
        if (1 <= lastIndex) {
            while (true) {
                R invoke = selector.invoke(Boolean.valueOf(zArr[i2]));
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
    @Nullable
    public static final Byte maxOrNull(@NotNull byte[] bArr) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        int i2 = 1;
        if (bArr.length == 0) {
            return null;
        }
        byte b2 = bArr[0];
        lastIndex = getLastIndex(bArr);
        if (1 <= lastIndex) {
            while (true) {
                byte b3 = bArr[i2];
                if (b2 < b3) {
                    b2 = b3;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Byte.valueOf(b2);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Character maxOrNull(@NotNull char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        int i2 = 1;
        if (cArr.length == 0) {
            return null;
        }
        char c2 = cArr[0];
        int lastIndex = getLastIndex(cArr);
        if (1 <= lastIndex) {
            while (true) {
                char c3 = cArr[i2];
                if (Intrinsics.compare((int) c2, (int) c3) < 0) {
                    c2 = c3;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Character.valueOf(c2);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final <T extends Comparable<? super T>> T maxOrNull(@NotNull T[] tArr) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        int i2 = 1;
        if (tArr.length == 0) {
            return null;
        }
        T t2 = tArr[0];
        lastIndex = getLastIndex(tArr);
        if (1 <= lastIndex) {
            while (true) {
                T t3 = tArr[i2];
                if (t2.compareTo(t3) < 0) {
                    t2 = t3;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return t2;
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Double maxOrNull(@NotNull double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        int i2 = 1;
        if (dArr.length == 0) {
            return null;
        }
        double d2 = dArr[0];
        int lastIndex = getLastIndex(dArr);
        if (1 <= lastIndex) {
            while (true) {
                d2 = Math.max(d2, dArr[i2]);
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Double.valueOf(d2);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Double maxOrNull(@NotNull Double[] dArr) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        int i2 = 1;
        if (dArr.length == 0) {
            return null;
        }
        double doubleValue = dArr[0].doubleValue();
        lastIndex = getLastIndex(dArr);
        if (1 <= lastIndex) {
            while (true) {
                doubleValue = Math.max(doubleValue, dArr[i2].doubleValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Double.valueOf(doubleValue);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Float maxOrNull(@NotNull float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        int i2 = 1;
        if (fArr.length == 0) {
            return null;
        }
        float f2 = fArr[0];
        int lastIndex = getLastIndex(fArr);
        if (1 <= lastIndex) {
            while (true) {
                f2 = Math.max(f2, fArr[i2]);
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Float.valueOf(f2);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Float maxOrNull(@NotNull Float[] fArr) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        int i2 = 1;
        if (fArr.length == 0) {
            return null;
        }
        float floatValue = fArr[0].floatValue();
        lastIndex = getLastIndex(fArr);
        if (1 <= lastIndex) {
            while (true) {
                floatValue = Math.max(floatValue, fArr[i2].floatValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Float.valueOf(floatValue);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Integer maxOrNull(@NotNull int[] iArr) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        int i2 = 1;
        if (iArr.length == 0) {
            return null;
        }
        int i3 = iArr[0];
        lastIndex = getLastIndex(iArr);
        if (1 <= lastIndex) {
            while (true) {
                int i4 = iArr[i2];
                if (i3 < i4) {
                    i3 = i4;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Integer.valueOf(i3);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Long maxOrNull(@NotNull long[] jArr) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        int i2 = 1;
        if (jArr.length == 0) {
            return null;
        }
        long j2 = jArr[0];
        lastIndex = getLastIndex(jArr);
        if (1 <= lastIndex) {
            while (true) {
                long j3 = jArr[i2];
                if (j2 < j3) {
                    j2 = j3;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Long.valueOf(j2);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Short maxOrNull(@NotNull short[] sArr) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        int i2 = 1;
        if (sArr.length == 0) {
            return null;
        }
        short s2 = sArr[0];
        lastIndex = getLastIndex(sArr);
        if (1 <= lastIndex) {
            while (true) {
                short s3 = sArr[i2];
                if (s2 < s3) {
                    s2 = s3;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Short.valueOf(s2);
    }

    @Deprecated(message = "Use maxWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ Boolean maxWith(boolean[] zArr, Comparator comparator) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        return maxWithOrNull(zArr, comparator);
    }

    @Deprecated(message = "Use maxWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ Byte maxWith(byte[] bArr, Comparator comparator) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        return maxWithOrNull(bArr, (Comparator<? super Byte>) comparator);
    }

    @Deprecated(message = "Use maxWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ Character maxWith(char[] cArr, Comparator comparator) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        return maxWithOrNull(cArr, (Comparator<? super Character>) comparator);
    }

    @Deprecated(message = "Use maxWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ Double maxWith(double[] dArr, Comparator comparator) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        return maxWithOrNull(dArr, comparator);
    }

    @Deprecated(message = "Use maxWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ Float maxWith(float[] fArr, Comparator comparator) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        return maxWithOrNull(fArr, (Comparator<? super Float>) comparator);
    }

    @Deprecated(message = "Use maxWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ Integer maxWith(int[] iArr, Comparator comparator) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        return maxWithOrNull(iArr, (Comparator<? super Integer>) comparator);
    }

    @Deprecated(message = "Use maxWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ Long maxWith(long[] jArr, Comparator comparator) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        return maxWithOrNull(jArr, (Comparator<? super Long>) comparator);
    }

    @Deprecated(message = "Use maxWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ Object maxWith(Object[] objArr, Comparator comparator) {
        Intrinsics.checkNotNullParameter(objArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        return maxWithOrNull(objArr, comparator);
    }

    @Deprecated(message = "Use maxWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ Short maxWith(short[] sArr, Comparator comparator) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        return maxWithOrNull(sArr, (Comparator<? super Short>) comparator);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Boolean maxWithOrNull(@NotNull boolean[] zArr, @NotNull Comparator<? super Boolean> comparator) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        int i2 = 1;
        if (zArr.length == 0) {
            return null;
        }
        boolean z = zArr[0];
        int lastIndex = getLastIndex(zArr);
        if (1 <= lastIndex) {
            while (true) {
                boolean z2 = zArr[i2];
                if (comparator.compare(Boolean.valueOf(z), Boolean.valueOf(z2)) < 0) {
                    z = z2;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Boolean.valueOf(z);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Byte maxWithOrNull(@NotNull byte[] bArr, @NotNull Comparator<? super Byte> comparator) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        int i2 = 1;
        if (bArr.length == 0) {
            return null;
        }
        byte b2 = bArr[0];
        lastIndex = getLastIndex(bArr);
        if (1 <= lastIndex) {
            while (true) {
                byte b3 = bArr[i2];
                if (comparator.compare(Byte.valueOf(b2), Byte.valueOf(b3)) < 0) {
                    b2 = b3;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Byte.valueOf(b2);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Character maxWithOrNull(@NotNull char[] cArr, @NotNull Comparator<? super Character> comparator) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        int i2 = 1;
        if (cArr.length == 0) {
            return null;
        }
        char c2 = cArr[0];
        int lastIndex = getLastIndex(cArr);
        if (1 <= lastIndex) {
            while (true) {
                char c3 = cArr[i2];
                if (comparator.compare(Character.valueOf(c2), Character.valueOf(c3)) < 0) {
                    c2 = c3;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Character.valueOf(c2);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Double maxWithOrNull(@NotNull double[] dArr, @NotNull Comparator<? super Double> comparator) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        int i2 = 1;
        if (dArr.length == 0) {
            return null;
        }
        double d2 = dArr[0];
        int lastIndex = getLastIndex(dArr);
        if (1 <= lastIndex) {
            while (true) {
                double d3 = dArr[i2];
                if (comparator.compare(Double.valueOf(d2), Double.valueOf(d3)) < 0) {
                    d2 = d3;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Double.valueOf(d2);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Float maxWithOrNull(@NotNull float[] fArr, @NotNull Comparator<? super Float> comparator) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        int i2 = 1;
        if (fArr.length == 0) {
            return null;
        }
        float f2 = fArr[0];
        int lastIndex = getLastIndex(fArr);
        if (1 <= lastIndex) {
            while (true) {
                float f3 = fArr[i2];
                if (comparator.compare(Float.valueOf(f2), Float.valueOf(f3)) < 0) {
                    f2 = f3;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Float.valueOf(f2);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Integer maxWithOrNull(@NotNull int[] iArr, @NotNull Comparator<? super Integer> comparator) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        int i2 = 1;
        if (iArr.length == 0) {
            return null;
        }
        int i3 = iArr[0];
        lastIndex = getLastIndex(iArr);
        if (1 <= lastIndex) {
            while (true) {
                int i4 = iArr[i2];
                if (comparator.compare(Integer.valueOf(i3), Integer.valueOf(i4)) < 0) {
                    i3 = i4;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Integer.valueOf(i3);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Long maxWithOrNull(@NotNull long[] jArr, @NotNull Comparator<? super Long> comparator) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        int i2 = 1;
        if (jArr.length == 0) {
            return null;
        }
        long j2 = jArr[0];
        lastIndex = getLastIndex(jArr);
        if (1 <= lastIndex) {
            while (true) {
                long j3 = jArr[i2];
                if (comparator.compare(Long.valueOf(j2), Long.valueOf(j3)) < 0) {
                    j2 = j3;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Long.valueOf(j2);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final <T> T maxWithOrNull(@NotNull T[] tArr, @NotNull Comparator<? super T> comparator) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        int i2 = 1;
        if (tArr.length == 0) {
            return null;
        }
        T t2 = tArr[0];
        lastIndex = getLastIndex(tArr);
        if (1 <= lastIndex) {
            while (true) {
                T t3 = tArr[i2];
                if (comparator.compare(t2, t3) < 0) {
                    t2 = t3;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return t2;
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Short maxWithOrNull(@NotNull short[] sArr, @NotNull Comparator<? super Short> comparator) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        int i2 = 1;
        if (sArr.length == 0) {
            return null;
        }
        short s2 = sArr[0];
        lastIndex = getLastIndex(sArr);
        if (1 <= lastIndex) {
            while (true) {
                short s3 = sArr[i2];
                if (comparator.compare(Short.valueOf(s2), Short.valueOf(s3)) < 0) {
                    s2 = s3;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Short.valueOf(s2);
    }

    @Deprecated(message = "Use minByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ <R extends Comparable<? super R>> Boolean minBy(boolean[] zArr, Function1<? super Boolean, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (zArr.length == 0) {
            return null;
        }
        boolean z = zArr[0];
        int lastIndex = getLastIndex(zArr);
        if (lastIndex != 0) {
            R invoke = selector.invoke(Boolean.valueOf(z));
            if (1 <= lastIndex) {
                while (true) {
                    boolean z2 = zArr[i2];
                    R invoke2 = selector.invoke(Boolean.valueOf(z2));
                    if (invoke.compareTo(invoke2) > 0) {
                        z = z2;
                        invoke = invoke2;
                    }
                    if (i2 == lastIndex) {
                        break;
                    }
                    i2++;
                }
            }
        }
        return Boolean.valueOf(z);
    }

    @Deprecated(message = "Use minByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ <R extends Comparable<? super R>> Byte minBy(byte[] bArr, Function1<? super Byte, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (bArr.length == 0) {
            return null;
        }
        byte b2 = bArr[0];
        lastIndex = getLastIndex(bArr);
        if (lastIndex != 0) {
            R invoke = selector.invoke(Byte.valueOf(b2));
            if (1 <= lastIndex) {
                while (true) {
                    byte b3 = bArr[i2];
                    R invoke2 = selector.invoke(Byte.valueOf(b3));
                    if (invoke.compareTo(invoke2) > 0) {
                        b2 = b3;
                        invoke = invoke2;
                    }
                    if (i2 == lastIndex) {
                        break;
                    }
                    i2++;
                }
            }
        }
        return Byte.valueOf(b2);
    }

    @Deprecated(message = "Use minByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ <R extends Comparable<? super R>> Character minBy(char[] cArr, Function1<? super Character, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (cArr.length == 0) {
            return null;
        }
        char c2 = cArr[0];
        int lastIndex = getLastIndex(cArr);
        if (lastIndex != 0) {
            R invoke = selector.invoke(Character.valueOf(c2));
            if (1 <= lastIndex) {
                while (true) {
                    char c3 = cArr[i2];
                    R invoke2 = selector.invoke(Character.valueOf(c3));
                    if (invoke.compareTo(invoke2) > 0) {
                        c2 = c3;
                        invoke = invoke2;
                    }
                    if (i2 == lastIndex) {
                        break;
                    }
                    i2++;
                }
            }
        }
        return Character.valueOf(c2);
    }

    @Deprecated(message = "Use minByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ <R extends Comparable<? super R>> Double minBy(double[] dArr, Function1<? super Double, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (dArr.length == 0) {
            return null;
        }
        double d2 = dArr[0];
        int lastIndex = getLastIndex(dArr);
        if (lastIndex != 0) {
            R invoke = selector.invoke(Double.valueOf(d2));
            if (1 <= lastIndex) {
                while (true) {
                    double d3 = dArr[i2];
                    R invoke2 = selector.invoke(Double.valueOf(d3));
                    if (invoke.compareTo(invoke2) > 0) {
                        d2 = d3;
                        invoke = invoke2;
                    }
                    if (i2 == lastIndex) {
                        break;
                    }
                    i2++;
                }
            }
        }
        return Double.valueOf(d2);
    }

    @Deprecated(message = "Use minByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ <R extends Comparable<? super R>> Float minBy(float[] fArr, Function1<? super Float, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (fArr.length == 0) {
            return null;
        }
        float f2 = fArr[0];
        int lastIndex = getLastIndex(fArr);
        if (lastIndex != 0) {
            R invoke = selector.invoke(Float.valueOf(f2));
            if (1 <= lastIndex) {
                while (true) {
                    float f3 = fArr[i2];
                    R invoke2 = selector.invoke(Float.valueOf(f3));
                    if (invoke.compareTo(invoke2) > 0) {
                        f2 = f3;
                        invoke = invoke2;
                    }
                    if (i2 == lastIndex) {
                        break;
                    }
                    i2++;
                }
            }
        }
        return Float.valueOf(f2);
    }

    @Deprecated(message = "Use minByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ <R extends Comparable<? super R>> Integer minBy(int[] iArr, Function1<? super Integer, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (iArr.length == 0) {
            return null;
        }
        int i3 = iArr[0];
        lastIndex = getLastIndex(iArr);
        if (lastIndex != 0) {
            R invoke = selector.invoke(Integer.valueOf(i3));
            if (1 <= lastIndex) {
                while (true) {
                    int i4 = iArr[i2];
                    R invoke2 = selector.invoke(Integer.valueOf(i4));
                    if (invoke.compareTo(invoke2) > 0) {
                        i3 = i4;
                        invoke = invoke2;
                    }
                    if (i2 == lastIndex) {
                        break;
                    }
                    i2++;
                }
            }
        }
        return Integer.valueOf(i3);
    }

    @Deprecated(message = "Use minByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ <R extends Comparable<? super R>> Long minBy(long[] jArr, Function1<? super Long, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (jArr.length == 0) {
            return null;
        }
        long j2 = jArr[0];
        lastIndex = getLastIndex(jArr);
        if (lastIndex != 0) {
            R invoke = selector.invoke(Long.valueOf(j2));
            if (1 <= lastIndex) {
                while (true) {
                    long j3 = jArr[i2];
                    R invoke2 = selector.invoke(Long.valueOf(j3));
                    if (invoke.compareTo(invoke2) > 0) {
                        j2 = j3;
                        invoke = invoke2;
                    }
                    if (i2 == lastIndex) {
                        break;
                    }
                    i2++;
                }
            }
        }
        return Long.valueOf(j2);
    }

    @Deprecated(message = "Use minByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ <T, R extends Comparable<? super R>> T minBy(T[] tArr, Function1<? super T, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (tArr.length == 0) {
            return null;
        }
        T t2 = tArr[0];
        lastIndex = getLastIndex(tArr);
        if (lastIndex != 0) {
            R invoke = selector.invoke(t2);
            if (1 <= lastIndex) {
                while (true) {
                    T t3 = tArr[i2];
                    R invoke2 = selector.invoke(t3);
                    if (invoke.compareTo(invoke2) > 0) {
                        t2 = t3;
                        invoke = invoke2;
                    }
                    if (i2 == lastIndex) {
                        break;
                    }
                    i2++;
                }
            }
        }
        return t2;
    }

    @Deprecated(message = "Use minByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ <R extends Comparable<? super R>> Short minBy(short[] sArr, Function1<? super Short, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (sArr.length == 0) {
            return null;
        }
        short s2 = sArr[0];
        lastIndex = getLastIndex(sArr);
        if (lastIndex != 0) {
            R invoke = selector.invoke(Short.valueOf(s2));
            if (1 <= lastIndex) {
                while (true) {
                    short s3 = sArr[i2];
                    R invoke2 = selector.invoke(Short.valueOf(s3));
                    if (invoke.compareTo(invoke2) > 0) {
                        s2 = s3;
                        invoke = invoke2;
                    }
                    if (i2 == lastIndex) {
                        break;
                    }
                    i2++;
                }
            }
        }
        return Short.valueOf(s2);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final <R extends Comparable<? super R>> Boolean minByOrNull(@NotNull boolean[] zArr, @NotNull Function1<? super Boolean, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (zArr.length == 0) {
            return null;
        }
        boolean z = zArr[0];
        int lastIndex = getLastIndex(zArr);
        if (lastIndex == 0) {
            return Boolean.valueOf(z);
        }
        R invoke = selector.invoke(Boolean.valueOf(z));
        if (1 <= lastIndex) {
            while (true) {
                boolean z2 = zArr[i2];
                R invoke2 = selector.invoke(Boolean.valueOf(z2));
                if (invoke.compareTo(invoke2) > 0) {
                    z = z2;
                    invoke = invoke2;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Boolean.valueOf(z);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final <R extends Comparable<? super R>> Byte minByOrNull(@NotNull byte[] bArr, @NotNull Function1<? super Byte, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (bArr.length == 0) {
            return null;
        }
        byte b2 = bArr[0];
        lastIndex = getLastIndex(bArr);
        if (lastIndex == 0) {
            return Byte.valueOf(b2);
        }
        R invoke = selector.invoke(Byte.valueOf(b2));
        if (1 <= lastIndex) {
            while (true) {
                byte b3 = bArr[i2];
                R invoke2 = selector.invoke(Byte.valueOf(b3));
                if (invoke.compareTo(invoke2) > 0) {
                    b2 = b3;
                    invoke = invoke2;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Byte.valueOf(b2);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final <R extends Comparable<? super R>> Character minByOrNull(@NotNull char[] cArr, @NotNull Function1<? super Character, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (cArr.length == 0) {
            return null;
        }
        char c2 = cArr[0];
        int lastIndex = getLastIndex(cArr);
        if (lastIndex == 0) {
            return Character.valueOf(c2);
        }
        R invoke = selector.invoke(Character.valueOf(c2));
        if (1 <= lastIndex) {
            while (true) {
                char c3 = cArr[i2];
                R invoke2 = selector.invoke(Character.valueOf(c3));
                if (invoke.compareTo(invoke2) > 0) {
                    c2 = c3;
                    invoke = invoke2;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Character.valueOf(c2);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final <R extends Comparable<? super R>> Double minByOrNull(@NotNull double[] dArr, @NotNull Function1<? super Double, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (dArr.length == 0) {
            return null;
        }
        double d2 = dArr[0];
        int lastIndex = getLastIndex(dArr);
        if (lastIndex == 0) {
            return Double.valueOf(d2);
        }
        R invoke = selector.invoke(Double.valueOf(d2));
        if (1 <= lastIndex) {
            while (true) {
                double d3 = dArr[i2];
                R invoke2 = selector.invoke(Double.valueOf(d3));
                if (invoke.compareTo(invoke2) > 0) {
                    d2 = d3;
                    invoke = invoke2;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Double.valueOf(d2);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final <R extends Comparable<? super R>> Float minByOrNull(@NotNull float[] fArr, @NotNull Function1<? super Float, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (fArr.length == 0) {
            return null;
        }
        float f2 = fArr[0];
        int lastIndex = getLastIndex(fArr);
        if (lastIndex == 0) {
            return Float.valueOf(f2);
        }
        R invoke = selector.invoke(Float.valueOf(f2));
        if (1 <= lastIndex) {
            while (true) {
                float f3 = fArr[i2];
                R invoke2 = selector.invoke(Float.valueOf(f3));
                if (invoke.compareTo(invoke2) > 0) {
                    f2 = f3;
                    invoke = invoke2;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Float.valueOf(f2);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final <R extends Comparable<? super R>> Integer minByOrNull(@NotNull int[] iArr, @NotNull Function1<? super Integer, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (iArr.length == 0) {
            return null;
        }
        int i3 = iArr[0];
        lastIndex = getLastIndex(iArr);
        if (lastIndex == 0) {
            return Integer.valueOf(i3);
        }
        R invoke = selector.invoke(Integer.valueOf(i3));
        if (1 <= lastIndex) {
            while (true) {
                int i4 = iArr[i2];
                R invoke2 = selector.invoke(Integer.valueOf(i4));
                if (invoke.compareTo(invoke2) > 0) {
                    i3 = i4;
                    invoke = invoke2;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Integer.valueOf(i3);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final <R extends Comparable<? super R>> Long minByOrNull(@NotNull long[] jArr, @NotNull Function1<? super Long, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (jArr.length == 0) {
            return null;
        }
        long j2 = jArr[0];
        lastIndex = getLastIndex(jArr);
        if (lastIndex == 0) {
            return Long.valueOf(j2);
        }
        R invoke = selector.invoke(Long.valueOf(j2));
        if (1 <= lastIndex) {
            while (true) {
                long j3 = jArr[i2];
                R invoke2 = selector.invoke(Long.valueOf(j3));
                if (invoke.compareTo(invoke2) > 0) {
                    j2 = j3;
                    invoke = invoke2;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Long.valueOf(j2);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final <T, R extends Comparable<? super R>> T minByOrNull(@NotNull T[] tArr, @NotNull Function1<? super T, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (tArr.length == 0) {
            return null;
        }
        T t2 = tArr[0];
        lastIndex = getLastIndex(tArr);
        if (lastIndex == 0) {
            return t2;
        }
        R invoke = selector.invoke(t2);
        if (1 <= lastIndex) {
            while (true) {
                T t3 = tArr[i2];
                R invoke2 = selector.invoke(t3);
                if (invoke.compareTo(invoke2) > 0) {
                    t2 = t3;
                    invoke = invoke2;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return t2;
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final <R extends Comparable<? super R>> Short minByOrNull(@NotNull short[] sArr, @NotNull Function1<? super Short, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (sArr.length == 0) {
            return null;
        }
        short s2 = sArr[0];
        lastIndex = getLastIndex(sArr);
        if (lastIndex == 0) {
            return Short.valueOf(s2);
        }
        R invoke = selector.invoke(Short.valueOf(s2));
        if (1 <= lastIndex) {
            while (true) {
                short s3 = sArr[i2];
                R invoke2 = selector.invoke(Short.valueOf(s3));
                if (invoke.compareTo(invoke2) > 0) {
                    s2 = s3;
                    invoke = invoke2;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Short.valueOf(s2);
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final double minOf(byte[] bArr, Function1<? super Byte, Double> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (bArr.length == 0) {
            throw new NoSuchElementException();
        }
        double doubleValue = selector.invoke(Byte.valueOf(bArr[0])).doubleValue();
        lastIndex = getLastIndex(bArr);
        if (1 <= lastIndex) {
            while (true) {
                doubleValue = Math.min(doubleValue, selector.invoke(Byte.valueOf(bArr[i2])).doubleValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return doubleValue;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final double minOf(char[] cArr, Function1<? super Character, Double> selector) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (cArr.length == 0) {
            throw new NoSuchElementException();
        }
        double doubleValue = selector.invoke(Character.valueOf(cArr[0])).doubleValue();
        int lastIndex = getLastIndex(cArr);
        if (1 <= lastIndex) {
            while (true) {
                doubleValue = Math.min(doubleValue, selector.invoke(Character.valueOf(cArr[i2])).doubleValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return doubleValue;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final double minOf(double[] dArr, Function1<? super Double, Double> selector) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (dArr.length == 0) {
            throw new NoSuchElementException();
        }
        double doubleValue = selector.invoke(Double.valueOf(dArr[0])).doubleValue();
        int lastIndex = getLastIndex(dArr);
        if (1 <= lastIndex) {
            while (true) {
                doubleValue = Math.min(doubleValue, selector.invoke(Double.valueOf(dArr[i2])).doubleValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return doubleValue;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final double minOf(float[] fArr, Function1<? super Float, Double> selector) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (fArr.length == 0) {
            throw new NoSuchElementException();
        }
        double doubleValue = selector.invoke(Float.valueOf(fArr[0])).doubleValue();
        int lastIndex = getLastIndex(fArr);
        if (1 <= lastIndex) {
            while (true) {
                doubleValue = Math.min(doubleValue, selector.invoke(Float.valueOf(fArr[i2])).doubleValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return doubleValue;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final double minOf(int[] iArr, Function1<? super Integer, Double> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (iArr.length == 0) {
            throw new NoSuchElementException();
        }
        double doubleValue = selector.invoke(Integer.valueOf(iArr[0])).doubleValue();
        lastIndex = getLastIndex(iArr);
        if (1 <= lastIndex) {
            while (true) {
                doubleValue = Math.min(doubleValue, selector.invoke(Integer.valueOf(iArr[i2])).doubleValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return doubleValue;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final double minOf(long[] jArr, Function1<? super Long, Double> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (jArr.length == 0) {
            throw new NoSuchElementException();
        }
        double doubleValue = selector.invoke(Long.valueOf(jArr[0])).doubleValue();
        lastIndex = getLastIndex(jArr);
        if (1 <= lastIndex) {
            while (true) {
                doubleValue = Math.min(doubleValue, selector.invoke(Long.valueOf(jArr[i2])).doubleValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return doubleValue;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <T> double minOf(T[] tArr, Function1<? super T, Double> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (tArr.length == 0) {
            throw new NoSuchElementException();
        }
        double doubleValue = selector.invoke(tArr[0]).doubleValue();
        lastIndex = getLastIndex(tArr);
        if (1 <= lastIndex) {
            while (true) {
                doubleValue = Math.min(doubleValue, selector.invoke(tArr[i2]).doubleValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return doubleValue;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final double minOf(short[] sArr, Function1<? super Short, Double> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (sArr.length == 0) {
            throw new NoSuchElementException();
        }
        double doubleValue = selector.invoke(Short.valueOf(sArr[0])).doubleValue();
        lastIndex = getLastIndex(sArr);
        if (1 <= lastIndex) {
            while (true) {
                doubleValue = Math.min(doubleValue, selector.invoke(Short.valueOf(sArr[i2])).doubleValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return doubleValue;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final double minOf(boolean[] zArr, Function1<? super Boolean, Double> selector) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (zArr.length == 0) {
            throw new NoSuchElementException();
        }
        double doubleValue = selector.invoke(Boolean.valueOf(zArr[0])).doubleValue();
        int lastIndex = getLastIndex(zArr);
        if (1 <= lastIndex) {
            while (true) {
                doubleValue = Math.min(doubleValue, selector.invoke(Boolean.valueOf(zArr[i2])).doubleValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return doubleValue;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: minOf */
    private static final float m574minOf(byte[] bArr, Function1<? super Byte, Float> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (bArr.length == 0) {
            throw new NoSuchElementException();
        }
        float floatValue = selector.invoke(Byte.valueOf(bArr[0])).floatValue();
        lastIndex = getLastIndex(bArr);
        if (1 <= lastIndex) {
            while (true) {
                floatValue = Math.min(floatValue, selector.invoke(Byte.valueOf(bArr[i2])).floatValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return floatValue;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: minOf */
    private static final float m575minOf(char[] cArr, Function1<? super Character, Float> selector) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (cArr.length == 0) {
            throw new NoSuchElementException();
        }
        float floatValue = selector.invoke(Character.valueOf(cArr[0])).floatValue();
        int lastIndex = getLastIndex(cArr);
        if (1 <= lastIndex) {
            while (true) {
                floatValue = Math.min(floatValue, selector.invoke(Character.valueOf(cArr[i2])).floatValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return floatValue;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: minOf */
    private static final float m576minOf(double[] dArr, Function1<? super Double, Float> selector) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (dArr.length == 0) {
            throw new NoSuchElementException();
        }
        float floatValue = selector.invoke(Double.valueOf(dArr[0])).floatValue();
        int lastIndex = getLastIndex(dArr);
        if (1 <= lastIndex) {
            while (true) {
                floatValue = Math.min(floatValue, selector.invoke(Double.valueOf(dArr[i2])).floatValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return floatValue;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: minOf */
    private static final float m577minOf(float[] fArr, Function1<? super Float, Float> selector) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (fArr.length == 0) {
            throw new NoSuchElementException();
        }
        float floatValue = selector.invoke(Float.valueOf(fArr[0])).floatValue();
        int lastIndex = getLastIndex(fArr);
        if (1 <= lastIndex) {
            while (true) {
                floatValue = Math.min(floatValue, selector.invoke(Float.valueOf(fArr[i2])).floatValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return floatValue;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: minOf */
    private static final float m578minOf(int[] iArr, Function1<? super Integer, Float> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (iArr.length == 0) {
            throw new NoSuchElementException();
        }
        float floatValue = selector.invoke(Integer.valueOf(iArr[0])).floatValue();
        lastIndex = getLastIndex(iArr);
        if (1 <= lastIndex) {
            while (true) {
                floatValue = Math.min(floatValue, selector.invoke(Integer.valueOf(iArr[i2])).floatValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return floatValue;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: minOf */
    private static final float m579minOf(long[] jArr, Function1<? super Long, Float> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (jArr.length == 0) {
            throw new NoSuchElementException();
        }
        float floatValue = selector.invoke(Long.valueOf(jArr[0])).floatValue();
        lastIndex = getLastIndex(jArr);
        if (1 <= lastIndex) {
            while (true) {
                floatValue = Math.min(floatValue, selector.invoke(Long.valueOf(jArr[i2])).floatValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return floatValue;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: minOf */
    private static final <T> float m580minOf(T[] tArr, Function1<? super T, Float> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (tArr.length == 0) {
            throw new NoSuchElementException();
        }
        float floatValue = selector.invoke(tArr[0]).floatValue();
        lastIndex = getLastIndex(tArr);
        if (1 <= lastIndex) {
            while (true) {
                floatValue = Math.min(floatValue, selector.invoke(tArr[i2]).floatValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return floatValue;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: minOf */
    private static final float m581minOf(short[] sArr, Function1<? super Short, Float> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (sArr.length == 0) {
            throw new NoSuchElementException();
        }
        float floatValue = selector.invoke(Short.valueOf(sArr[0])).floatValue();
        lastIndex = getLastIndex(sArr);
        if (1 <= lastIndex) {
            while (true) {
                floatValue = Math.min(floatValue, selector.invoke(Short.valueOf(sArr[i2])).floatValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return floatValue;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: minOf */
    private static final float m582minOf(boolean[] zArr, Function1<? super Boolean, Float> selector) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (zArr.length == 0) {
            throw new NoSuchElementException();
        }
        float floatValue = selector.invoke(Boolean.valueOf(zArr[0])).floatValue();
        int lastIndex = getLastIndex(zArr);
        if (1 <= lastIndex) {
            while (true) {
                floatValue = Math.min(floatValue, selector.invoke(Boolean.valueOf(zArr[i2])).floatValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return floatValue;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: minOf */
    private static final <R extends Comparable<? super R>> R m583minOf(byte[] bArr, Function1<? super Byte, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (bArr.length == 0) {
            throw new NoSuchElementException();
        }
        R invoke = selector.invoke(Byte.valueOf(bArr[0]));
        lastIndex = getLastIndex(bArr);
        if (1 <= lastIndex) {
            while (true) {
                R invoke2 = selector.invoke(Byte.valueOf(bArr[i2]));
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: minOf */
    private static final <R extends Comparable<? super R>> R m584minOf(char[] cArr, Function1<? super Character, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (cArr.length == 0) {
            throw new NoSuchElementException();
        }
        R invoke = selector.invoke(Character.valueOf(cArr[0]));
        int lastIndex = getLastIndex(cArr);
        if (1 <= lastIndex) {
            while (true) {
                R invoke2 = selector.invoke(Character.valueOf(cArr[i2]));
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: minOf */
    private static final <R extends Comparable<? super R>> R m585minOf(double[] dArr, Function1<? super Double, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (dArr.length == 0) {
            throw new NoSuchElementException();
        }
        R invoke = selector.invoke(Double.valueOf(dArr[0]));
        int lastIndex = getLastIndex(dArr);
        if (1 <= lastIndex) {
            while (true) {
                R invoke2 = selector.invoke(Double.valueOf(dArr[i2]));
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: minOf */
    private static final <R extends Comparable<? super R>> R m586minOf(float[] fArr, Function1<? super Float, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (fArr.length == 0) {
            throw new NoSuchElementException();
        }
        R invoke = selector.invoke(Float.valueOf(fArr[0]));
        int lastIndex = getLastIndex(fArr);
        if (1 <= lastIndex) {
            while (true) {
                R invoke2 = selector.invoke(Float.valueOf(fArr[i2]));
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: minOf */
    private static final <R extends Comparable<? super R>> R m587minOf(int[] iArr, Function1<? super Integer, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (iArr.length == 0) {
            throw new NoSuchElementException();
        }
        R invoke = selector.invoke(Integer.valueOf(iArr[0]));
        lastIndex = getLastIndex(iArr);
        if (1 <= lastIndex) {
            while (true) {
                R invoke2 = selector.invoke(Integer.valueOf(iArr[i2]));
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: minOf */
    private static final <R extends Comparable<? super R>> R m588minOf(long[] jArr, Function1<? super Long, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (jArr.length == 0) {
            throw new NoSuchElementException();
        }
        R invoke = selector.invoke(Long.valueOf(jArr[0]));
        lastIndex = getLastIndex(jArr);
        if (1 <= lastIndex) {
            while (true) {
                R invoke2 = selector.invoke(Long.valueOf(jArr[i2]));
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: minOf */
    private static final <T, R extends Comparable<? super R>> R m589minOf(T[] tArr, Function1<? super T, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (tArr.length == 0) {
            throw new NoSuchElementException();
        }
        R invoke = selector.invoke(tArr[0]);
        lastIndex = getLastIndex(tArr);
        if (1 <= lastIndex) {
            while (true) {
                R invoke2 = selector.invoke(tArr[i2]);
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: minOf */
    private static final <R extends Comparable<? super R>> R m590minOf(short[] sArr, Function1<? super Short, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (sArr.length == 0) {
            throw new NoSuchElementException();
        }
        R invoke = selector.invoke(Short.valueOf(sArr[0]));
        lastIndex = getLastIndex(sArr);
        if (1 <= lastIndex) {
            while (true) {
                R invoke2 = selector.invoke(Short.valueOf(sArr[i2]));
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: minOf */
    private static final <R extends Comparable<? super R>> R m591minOf(boolean[] zArr, Function1<? super Boolean, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (zArr.length == 0) {
            throw new NoSuchElementException();
        }
        R invoke = selector.invoke(Boolean.valueOf(zArr[0]));
        int lastIndex = getLastIndex(zArr);
        if (1 <= lastIndex) {
            while (true) {
                R invoke2 = selector.invoke(Boolean.valueOf(zArr[i2]));
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <R extends Comparable<? super R>> R minOfOrNull(byte[] bArr, Function1<? super Byte, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (bArr.length == 0) {
            return null;
        }
        R invoke = selector.invoke(Byte.valueOf(bArr[0]));
        lastIndex = getLastIndex(bArr);
        if (1 <= lastIndex) {
            while (true) {
                R invoke2 = selector.invoke(Byte.valueOf(bArr[i2]));
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <R extends Comparable<? super R>> R minOfOrNull(char[] cArr, Function1<? super Character, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (cArr.length == 0) {
            return null;
        }
        R invoke = selector.invoke(Character.valueOf(cArr[0]));
        int lastIndex = getLastIndex(cArr);
        if (1 <= lastIndex) {
            while (true) {
                R invoke2 = selector.invoke(Character.valueOf(cArr[i2]));
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <R extends Comparable<? super R>> R minOfOrNull(double[] dArr, Function1<? super Double, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (dArr.length == 0) {
            return null;
        }
        R invoke = selector.invoke(Double.valueOf(dArr[0]));
        int lastIndex = getLastIndex(dArr);
        if (1 <= lastIndex) {
            while (true) {
                R invoke2 = selector.invoke(Double.valueOf(dArr[i2]));
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <R extends Comparable<? super R>> R minOfOrNull(float[] fArr, Function1<? super Float, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (fArr.length == 0) {
            return null;
        }
        R invoke = selector.invoke(Float.valueOf(fArr[0]));
        int lastIndex = getLastIndex(fArr);
        if (1 <= lastIndex) {
            while (true) {
                R invoke2 = selector.invoke(Float.valueOf(fArr[i2]));
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <R extends Comparable<? super R>> R minOfOrNull(int[] iArr, Function1<? super Integer, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (iArr.length == 0) {
            return null;
        }
        R invoke = selector.invoke(Integer.valueOf(iArr[0]));
        lastIndex = getLastIndex(iArr);
        if (1 <= lastIndex) {
            while (true) {
                R invoke2 = selector.invoke(Integer.valueOf(iArr[i2]));
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <R extends Comparable<? super R>> R minOfOrNull(long[] jArr, Function1<? super Long, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (jArr.length == 0) {
            return null;
        }
        R invoke = selector.invoke(Long.valueOf(jArr[0]));
        lastIndex = getLastIndex(jArr);
        if (1 <= lastIndex) {
            while (true) {
                R invoke2 = selector.invoke(Long.valueOf(jArr[i2]));
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <T, R extends Comparable<? super R>> R minOfOrNull(T[] tArr, Function1<? super T, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (tArr.length == 0) {
            return null;
        }
        R invoke = selector.invoke(tArr[0]);
        lastIndex = getLastIndex(tArr);
        if (1 <= lastIndex) {
            while (true) {
                R invoke2 = selector.invoke(tArr[i2]);
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <R extends Comparable<? super R>> R minOfOrNull(short[] sArr, Function1<? super Short, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (sArr.length == 0) {
            return null;
        }
        R invoke = selector.invoke(Short.valueOf(sArr[0]));
        lastIndex = getLastIndex(sArr);
        if (1 <= lastIndex) {
            while (true) {
                R invoke2 = selector.invoke(Short.valueOf(sArr[i2]));
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <R extends Comparable<? super R>> R minOfOrNull(boolean[] zArr, Function1<? super Boolean, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (zArr.length == 0) {
            return null;
        }
        R invoke = selector.invoke(Boolean.valueOf(zArr[0]));
        int lastIndex = getLastIndex(zArr);
        if (1 <= lastIndex) {
            while (true) {
                R invoke2 = selector.invoke(Boolean.valueOf(zArr[i2]));
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: minOfOrNull */
    private static final Double m592minOfOrNull(byte[] bArr, Function1<? super Byte, Double> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (bArr.length == 0) {
            return null;
        }
        double doubleValue = selector.invoke(Byte.valueOf(bArr[0])).doubleValue();
        lastIndex = getLastIndex(bArr);
        if (1 <= lastIndex) {
            while (true) {
                doubleValue = Math.min(doubleValue, selector.invoke(Byte.valueOf(bArr[i2])).doubleValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Double.valueOf(doubleValue);
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: minOfOrNull */
    private static final Double m593minOfOrNull(char[] cArr, Function1<? super Character, Double> selector) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (cArr.length == 0) {
            return null;
        }
        double doubleValue = selector.invoke(Character.valueOf(cArr[0])).doubleValue();
        int lastIndex = getLastIndex(cArr);
        if (1 <= lastIndex) {
            while (true) {
                doubleValue = Math.min(doubleValue, selector.invoke(Character.valueOf(cArr[i2])).doubleValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Double.valueOf(doubleValue);
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: minOfOrNull */
    private static final Double m594minOfOrNull(double[] dArr, Function1<? super Double, Double> selector) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (dArr.length == 0) {
            return null;
        }
        double doubleValue = selector.invoke(Double.valueOf(dArr[0])).doubleValue();
        int lastIndex = getLastIndex(dArr);
        if (1 <= lastIndex) {
            while (true) {
                doubleValue = Math.min(doubleValue, selector.invoke(Double.valueOf(dArr[i2])).doubleValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Double.valueOf(doubleValue);
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: minOfOrNull */
    private static final Double m595minOfOrNull(float[] fArr, Function1<? super Float, Double> selector) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (fArr.length == 0) {
            return null;
        }
        double doubleValue = selector.invoke(Float.valueOf(fArr[0])).doubleValue();
        int lastIndex = getLastIndex(fArr);
        if (1 <= lastIndex) {
            while (true) {
                doubleValue = Math.min(doubleValue, selector.invoke(Float.valueOf(fArr[i2])).doubleValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Double.valueOf(doubleValue);
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: minOfOrNull */
    private static final Double m596minOfOrNull(int[] iArr, Function1<? super Integer, Double> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (iArr.length == 0) {
            return null;
        }
        double doubleValue = selector.invoke(Integer.valueOf(iArr[0])).doubleValue();
        lastIndex = getLastIndex(iArr);
        if (1 <= lastIndex) {
            while (true) {
                doubleValue = Math.min(doubleValue, selector.invoke(Integer.valueOf(iArr[i2])).doubleValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Double.valueOf(doubleValue);
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: minOfOrNull */
    private static final Double m597minOfOrNull(long[] jArr, Function1<? super Long, Double> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (jArr.length == 0) {
            return null;
        }
        double doubleValue = selector.invoke(Long.valueOf(jArr[0])).doubleValue();
        lastIndex = getLastIndex(jArr);
        if (1 <= lastIndex) {
            while (true) {
                doubleValue = Math.min(doubleValue, selector.invoke(Long.valueOf(jArr[i2])).doubleValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Double.valueOf(doubleValue);
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: minOfOrNull */
    private static final <T> Double m598minOfOrNull(T[] tArr, Function1<? super T, Double> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (tArr.length == 0) {
            return null;
        }
        double doubleValue = selector.invoke(tArr[0]).doubleValue();
        lastIndex = getLastIndex(tArr);
        if (1 <= lastIndex) {
            while (true) {
                doubleValue = Math.min(doubleValue, selector.invoke(tArr[i2]).doubleValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Double.valueOf(doubleValue);
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: minOfOrNull */
    private static final Double m599minOfOrNull(short[] sArr, Function1<? super Short, Double> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (sArr.length == 0) {
            return null;
        }
        double doubleValue = selector.invoke(Short.valueOf(sArr[0])).doubleValue();
        lastIndex = getLastIndex(sArr);
        if (1 <= lastIndex) {
            while (true) {
                doubleValue = Math.min(doubleValue, selector.invoke(Short.valueOf(sArr[i2])).doubleValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Double.valueOf(doubleValue);
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: minOfOrNull */
    private static final Double m600minOfOrNull(boolean[] zArr, Function1<? super Boolean, Double> selector) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (zArr.length == 0) {
            return null;
        }
        double doubleValue = selector.invoke(Boolean.valueOf(zArr[0])).doubleValue();
        int lastIndex = getLastIndex(zArr);
        if (1 <= lastIndex) {
            while (true) {
                doubleValue = Math.min(doubleValue, selector.invoke(Boolean.valueOf(zArr[i2])).doubleValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Double.valueOf(doubleValue);
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: minOfOrNull */
    private static final Float m601minOfOrNull(byte[] bArr, Function1<? super Byte, Float> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (bArr.length == 0) {
            return null;
        }
        float floatValue = selector.invoke(Byte.valueOf(bArr[0])).floatValue();
        lastIndex = getLastIndex(bArr);
        if (1 <= lastIndex) {
            while (true) {
                floatValue = Math.min(floatValue, selector.invoke(Byte.valueOf(bArr[i2])).floatValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Float.valueOf(floatValue);
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: minOfOrNull */
    private static final Float m602minOfOrNull(char[] cArr, Function1<? super Character, Float> selector) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (cArr.length == 0) {
            return null;
        }
        float floatValue = selector.invoke(Character.valueOf(cArr[0])).floatValue();
        int lastIndex = getLastIndex(cArr);
        if (1 <= lastIndex) {
            while (true) {
                floatValue = Math.min(floatValue, selector.invoke(Character.valueOf(cArr[i2])).floatValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Float.valueOf(floatValue);
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: minOfOrNull */
    private static final Float m603minOfOrNull(double[] dArr, Function1<? super Double, Float> selector) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (dArr.length == 0) {
            return null;
        }
        float floatValue = selector.invoke(Double.valueOf(dArr[0])).floatValue();
        int lastIndex = getLastIndex(dArr);
        if (1 <= lastIndex) {
            while (true) {
                floatValue = Math.min(floatValue, selector.invoke(Double.valueOf(dArr[i2])).floatValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Float.valueOf(floatValue);
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: minOfOrNull */
    private static final Float m604minOfOrNull(float[] fArr, Function1<? super Float, Float> selector) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (fArr.length == 0) {
            return null;
        }
        float floatValue = selector.invoke(Float.valueOf(fArr[0])).floatValue();
        int lastIndex = getLastIndex(fArr);
        if (1 <= lastIndex) {
            while (true) {
                floatValue = Math.min(floatValue, selector.invoke(Float.valueOf(fArr[i2])).floatValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Float.valueOf(floatValue);
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: minOfOrNull */
    private static final Float m605minOfOrNull(int[] iArr, Function1<? super Integer, Float> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (iArr.length == 0) {
            return null;
        }
        float floatValue = selector.invoke(Integer.valueOf(iArr[0])).floatValue();
        lastIndex = getLastIndex(iArr);
        if (1 <= lastIndex) {
            while (true) {
                floatValue = Math.min(floatValue, selector.invoke(Integer.valueOf(iArr[i2])).floatValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Float.valueOf(floatValue);
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: minOfOrNull */
    private static final Float m606minOfOrNull(long[] jArr, Function1<? super Long, Float> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (jArr.length == 0) {
            return null;
        }
        float floatValue = selector.invoke(Long.valueOf(jArr[0])).floatValue();
        lastIndex = getLastIndex(jArr);
        if (1 <= lastIndex) {
            while (true) {
                floatValue = Math.min(floatValue, selector.invoke(Long.valueOf(jArr[i2])).floatValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Float.valueOf(floatValue);
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: minOfOrNull */
    private static final <T> Float m607minOfOrNull(T[] tArr, Function1<? super T, Float> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (tArr.length == 0) {
            return null;
        }
        float floatValue = selector.invoke(tArr[0]).floatValue();
        lastIndex = getLastIndex(tArr);
        if (1 <= lastIndex) {
            while (true) {
                floatValue = Math.min(floatValue, selector.invoke(tArr[i2]).floatValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Float.valueOf(floatValue);
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: minOfOrNull */
    private static final Float m608minOfOrNull(short[] sArr, Function1<? super Short, Float> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (sArr.length == 0) {
            return null;
        }
        float floatValue = selector.invoke(Short.valueOf(sArr[0])).floatValue();
        lastIndex = getLastIndex(sArr);
        if (1 <= lastIndex) {
            while (true) {
                floatValue = Math.min(floatValue, selector.invoke(Short.valueOf(sArr[i2])).floatValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Float.valueOf(floatValue);
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: minOfOrNull */
    private static final Float m609minOfOrNull(boolean[] zArr, Function1<? super Boolean, Float> selector) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (zArr.length == 0) {
            return null;
        }
        float floatValue = selector.invoke(Boolean.valueOf(zArr[0])).floatValue();
        int lastIndex = getLastIndex(zArr);
        if (1 <= lastIndex) {
            while (true) {
                floatValue = Math.min(floatValue, selector.invoke(Boolean.valueOf(zArr[i2])).floatValue());
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <R> R minOfWith(byte[] bArr, Comparator<? super R> comparator, Function1<? super Byte, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (bArr.length == 0) {
            throw new NoSuchElementException();
        }
        Object obj = (R) selector.invoke(Byte.valueOf(bArr[0]));
        lastIndex = getLastIndex(bArr);
        if (1 <= lastIndex) {
            while (true) {
                R invoke = selector.invoke(Byte.valueOf(bArr[i2]));
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <R> R minOfWith(char[] cArr, Comparator<? super R> comparator, Function1<? super Character, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (cArr.length == 0) {
            throw new NoSuchElementException();
        }
        Object obj = (R) selector.invoke(Character.valueOf(cArr[0]));
        int lastIndex = getLastIndex(cArr);
        if (1 <= lastIndex) {
            while (true) {
                R invoke = selector.invoke(Character.valueOf(cArr[i2]));
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <R> R minOfWith(double[] dArr, Comparator<? super R> comparator, Function1<? super Double, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (dArr.length == 0) {
            throw new NoSuchElementException();
        }
        Object obj = (R) selector.invoke(Double.valueOf(dArr[0]));
        int lastIndex = getLastIndex(dArr);
        if (1 <= lastIndex) {
            while (true) {
                R invoke = selector.invoke(Double.valueOf(dArr[i2]));
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <R> R minOfWith(float[] fArr, Comparator<? super R> comparator, Function1<? super Float, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (fArr.length == 0) {
            throw new NoSuchElementException();
        }
        Object obj = (R) selector.invoke(Float.valueOf(fArr[0]));
        int lastIndex = getLastIndex(fArr);
        if (1 <= lastIndex) {
            while (true) {
                R invoke = selector.invoke(Float.valueOf(fArr[i2]));
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <R> R minOfWith(int[] iArr, Comparator<? super R> comparator, Function1<? super Integer, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (iArr.length == 0) {
            throw new NoSuchElementException();
        }
        Object obj = (R) selector.invoke(Integer.valueOf(iArr[0]));
        lastIndex = getLastIndex(iArr);
        if (1 <= lastIndex) {
            while (true) {
                R invoke = selector.invoke(Integer.valueOf(iArr[i2]));
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <R> R minOfWith(long[] jArr, Comparator<? super R> comparator, Function1<? super Long, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (jArr.length == 0) {
            throw new NoSuchElementException();
        }
        Object obj = (R) selector.invoke(Long.valueOf(jArr[0]));
        lastIndex = getLastIndex(jArr);
        if (1 <= lastIndex) {
            while (true) {
                R invoke = selector.invoke(Long.valueOf(jArr[i2]));
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <T, R> R minOfWith(T[] tArr, Comparator<? super R> comparator, Function1<? super T, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (tArr.length == 0) {
            throw new NoSuchElementException();
        }
        Object obj = (R) selector.invoke(tArr[0]);
        lastIndex = getLastIndex(tArr);
        if (1 <= lastIndex) {
            while (true) {
                R invoke = selector.invoke(tArr[i2]);
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <R> R minOfWith(short[] sArr, Comparator<? super R> comparator, Function1<? super Short, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (sArr.length == 0) {
            throw new NoSuchElementException();
        }
        Object obj = (R) selector.invoke(Short.valueOf(sArr[0]));
        lastIndex = getLastIndex(sArr);
        if (1 <= lastIndex) {
            while (true) {
                R invoke = selector.invoke(Short.valueOf(sArr[i2]));
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <R> R minOfWith(boolean[] zArr, Comparator<? super R> comparator, Function1<? super Boolean, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (zArr.length == 0) {
            throw new NoSuchElementException();
        }
        Object obj = (R) selector.invoke(Boolean.valueOf(zArr[0]));
        int lastIndex = getLastIndex(zArr);
        if (1 <= lastIndex) {
            while (true) {
                R invoke = selector.invoke(Boolean.valueOf(zArr[i2]));
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <R> R minOfWithOrNull(byte[] bArr, Comparator<? super R> comparator, Function1<? super Byte, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (bArr.length == 0) {
            return null;
        }
        Object obj = (R) selector.invoke(Byte.valueOf(bArr[0]));
        lastIndex = getLastIndex(bArr);
        if (1 <= lastIndex) {
            while (true) {
                R invoke = selector.invoke(Byte.valueOf(bArr[i2]));
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <R> R minOfWithOrNull(char[] cArr, Comparator<? super R> comparator, Function1<? super Character, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (cArr.length == 0) {
            return null;
        }
        Object obj = (R) selector.invoke(Character.valueOf(cArr[0]));
        int lastIndex = getLastIndex(cArr);
        if (1 <= lastIndex) {
            while (true) {
                R invoke = selector.invoke(Character.valueOf(cArr[i2]));
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <R> R minOfWithOrNull(double[] dArr, Comparator<? super R> comparator, Function1<? super Double, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (dArr.length == 0) {
            return null;
        }
        Object obj = (R) selector.invoke(Double.valueOf(dArr[0]));
        int lastIndex = getLastIndex(dArr);
        if (1 <= lastIndex) {
            while (true) {
                R invoke = selector.invoke(Double.valueOf(dArr[i2]));
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <R> R minOfWithOrNull(float[] fArr, Comparator<? super R> comparator, Function1<? super Float, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (fArr.length == 0) {
            return null;
        }
        Object obj = (R) selector.invoke(Float.valueOf(fArr[0]));
        int lastIndex = getLastIndex(fArr);
        if (1 <= lastIndex) {
            while (true) {
                R invoke = selector.invoke(Float.valueOf(fArr[i2]));
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <R> R minOfWithOrNull(int[] iArr, Comparator<? super R> comparator, Function1<? super Integer, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (iArr.length == 0) {
            return null;
        }
        Object obj = (R) selector.invoke(Integer.valueOf(iArr[0]));
        lastIndex = getLastIndex(iArr);
        if (1 <= lastIndex) {
            while (true) {
                R invoke = selector.invoke(Integer.valueOf(iArr[i2]));
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <R> R minOfWithOrNull(long[] jArr, Comparator<? super R> comparator, Function1<? super Long, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (jArr.length == 0) {
            return null;
        }
        Object obj = (R) selector.invoke(Long.valueOf(jArr[0]));
        lastIndex = getLastIndex(jArr);
        if (1 <= lastIndex) {
            while (true) {
                R invoke = selector.invoke(Long.valueOf(jArr[i2]));
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <T, R> R minOfWithOrNull(T[] tArr, Comparator<? super R> comparator, Function1<? super T, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (tArr.length == 0) {
            return null;
        }
        Object obj = (R) selector.invoke(tArr[0]);
        lastIndex = getLastIndex(tArr);
        if (1 <= lastIndex) {
            while (true) {
                R invoke = selector.invoke(tArr[i2]);
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <R> R minOfWithOrNull(short[] sArr, Comparator<? super R> comparator, Function1<? super Short, ? extends R> selector) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (sArr.length == 0) {
            return null;
        }
        Object obj = (R) selector.invoke(Short.valueOf(sArr[0]));
        lastIndex = getLastIndex(sArr);
        if (1 <= lastIndex) {
            while (true) {
                R invoke = selector.invoke(Short.valueOf(sArr[i2]));
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
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <R> R minOfWithOrNull(boolean[] zArr, Comparator<? super R> comparator, Function1<? super Boolean, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 1;
        if (zArr.length == 0) {
            return null;
        }
        Object obj = (R) selector.invoke(Boolean.valueOf(zArr[0]));
        int lastIndex = getLastIndex(zArr);
        if (1 <= lastIndex) {
            while (true) {
                R invoke = selector.invoke(Boolean.valueOf(zArr[i2]));
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
    @Nullable
    public static final Byte minOrNull(@NotNull byte[] bArr) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        int i2 = 1;
        if (bArr.length == 0) {
            return null;
        }
        byte b2 = bArr[0];
        lastIndex = getLastIndex(bArr);
        if (1 <= lastIndex) {
            while (true) {
                byte b3 = bArr[i2];
                if (b2 > b3) {
                    b2 = b3;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Byte.valueOf(b2);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Character minOrNull(@NotNull char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        int i2 = 1;
        if (cArr.length == 0) {
            return null;
        }
        char c2 = cArr[0];
        int lastIndex = getLastIndex(cArr);
        if (1 <= lastIndex) {
            while (true) {
                char c3 = cArr[i2];
                if (Intrinsics.compare((int) c2, (int) c3) > 0) {
                    c2 = c3;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Character.valueOf(c2);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final <T extends Comparable<? super T>> T minOrNull(@NotNull T[] tArr) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        int i2 = 1;
        if (tArr.length == 0) {
            return null;
        }
        T t2 = tArr[0];
        lastIndex = getLastIndex(tArr);
        if (1 <= lastIndex) {
            while (true) {
                T t3 = tArr[i2];
                if (t2.compareTo(t3) > 0) {
                    t2 = t3;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return t2;
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Double minOrNull(@NotNull double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        int i2 = 1;
        if (dArr.length == 0) {
            return null;
        }
        double d2 = dArr[0];
        int lastIndex = getLastIndex(dArr);
        if (1 <= lastIndex) {
            while (true) {
                d2 = Math.min(d2, dArr[i2]);
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Double.valueOf(d2);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Double minOrNull(@NotNull Double[] dArr) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        int i2 = 1;
        if (dArr.length == 0) {
            return null;
        }
        double doubleValue = dArr[0].doubleValue();
        lastIndex = getLastIndex(dArr);
        if (1 <= lastIndex) {
            while (true) {
                doubleValue = Math.min(doubleValue, dArr[i2].doubleValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Double.valueOf(doubleValue);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Float minOrNull(@NotNull float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        int i2 = 1;
        if (fArr.length == 0) {
            return null;
        }
        float f2 = fArr[0];
        int lastIndex = getLastIndex(fArr);
        if (1 <= lastIndex) {
            while (true) {
                f2 = Math.min(f2, fArr[i2]);
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Float.valueOf(f2);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Float minOrNull(@NotNull Float[] fArr) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        int i2 = 1;
        if (fArr.length == 0) {
            return null;
        }
        float floatValue = fArr[0].floatValue();
        lastIndex = getLastIndex(fArr);
        if (1 <= lastIndex) {
            while (true) {
                floatValue = Math.min(floatValue, fArr[i2].floatValue());
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Float.valueOf(floatValue);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Integer minOrNull(@NotNull int[] iArr) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        int i2 = 1;
        if (iArr.length == 0) {
            return null;
        }
        int i3 = iArr[0];
        lastIndex = getLastIndex(iArr);
        if (1 <= lastIndex) {
            while (true) {
                int i4 = iArr[i2];
                if (i3 > i4) {
                    i3 = i4;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Integer.valueOf(i3);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Long minOrNull(@NotNull long[] jArr) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        int i2 = 1;
        if (jArr.length == 0) {
            return null;
        }
        long j2 = jArr[0];
        lastIndex = getLastIndex(jArr);
        if (1 <= lastIndex) {
            while (true) {
                long j3 = jArr[i2];
                if (j2 > j3) {
                    j2 = j3;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Long.valueOf(j2);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Short minOrNull(@NotNull short[] sArr) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        int i2 = 1;
        if (sArr.length == 0) {
            return null;
        }
        short s2 = sArr[0];
        lastIndex = getLastIndex(sArr);
        if (1 <= lastIndex) {
            while (true) {
                short s3 = sArr[i2];
                if (s2 > s3) {
                    s2 = s3;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Short.valueOf(s2);
    }

    @Deprecated(message = "Use minWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ Boolean minWith(boolean[] zArr, Comparator comparator) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        return minWithOrNull(zArr, comparator);
    }

    @Deprecated(message = "Use minWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ Byte minWith(byte[] bArr, Comparator comparator) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        return minWithOrNull(bArr, (Comparator<? super Byte>) comparator);
    }

    @Deprecated(message = "Use minWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ Character minWith(char[] cArr, Comparator comparator) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        return minWithOrNull(cArr, (Comparator<? super Character>) comparator);
    }

    @Deprecated(message = "Use minWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ Double minWith(double[] dArr, Comparator comparator) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        return minWithOrNull(dArr, comparator);
    }

    @Deprecated(message = "Use minWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ Float minWith(float[] fArr, Comparator comparator) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        return minWithOrNull(fArr, (Comparator<? super Float>) comparator);
    }

    @Deprecated(message = "Use minWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ Integer minWith(int[] iArr, Comparator comparator) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        return minWithOrNull(iArr, (Comparator<? super Integer>) comparator);
    }

    @Deprecated(message = "Use minWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ Long minWith(long[] jArr, Comparator comparator) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        return minWithOrNull(jArr, (Comparator<? super Long>) comparator);
    }

    @Deprecated(message = "Use minWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ Object minWith(Object[] objArr, Comparator comparator) {
        Intrinsics.checkNotNullParameter(objArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        return minWithOrNull(objArr, comparator);
    }

    @Deprecated(message = "Use minWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final /* synthetic */ Short minWith(short[] sArr, Comparator comparator) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        return minWithOrNull(sArr, (Comparator<? super Short>) comparator);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Boolean minWithOrNull(@NotNull boolean[] zArr, @NotNull Comparator<? super Boolean> comparator) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        int i2 = 1;
        if (zArr.length == 0) {
            return null;
        }
        boolean z = zArr[0];
        int lastIndex = getLastIndex(zArr);
        if (1 <= lastIndex) {
            while (true) {
                boolean z2 = zArr[i2];
                if (comparator.compare(Boolean.valueOf(z), Boolean.valueOf(z2)) > 0) {
                    z = z2;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Boolean.valueOf(z);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Byte minWithOrNull(@NotNull byte[] bArr, @NotNull Comparator<? super Byte> comparator) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        int i2 = 1;
        if (bArr.length == 0) {
            return null;
        }
        byte b2 = bArr[0];
        lastIndex = getLastIndex(bArr);
        if (1 <= lastIndex) {
            while (true) {
                byte b3 = bArr[i2];
                if (comparator.compare(Byte.valueOf(b2), Byte.valueOf(b3)) > 0) {
                    b2 = b3;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Byte.valueOf(b2);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Character minWithOrNull(@NotNull char[] cArr, @NotNull Comparator<? super Character> comparator) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        int i2 = 1;
        if (cArr.length == 0) {
            return null;
        }
        char c2 = cArr[0];
        int lastIndex = getLastIndex(cArr);
        if (1 <= lastIndex) {
            while (true) {
                char c3 = cArr[i2];
                if (comparator.compare(Character.valueOf(c2), Character.valueOf(c3)) > 0) {
                    c2 = c3;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Character.valueOf(c2);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Double minWithOrNull(@NotNull double[] dArr, @NotNull Comparator<? super Double> comparator) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        int i2 = 1;
        if (dArr.length == 0) {
            return null;
        }
        double d2 = dArr[0];
        int lastIndex = getLastIndex(dArr);
        if (1 <= lastIndex) {
            while (true) {
                double d3 = dArr[i2];
                if (comparator.compare(Double.valueOf(d2), Double.valueOf(d3)) > 0) {
                    d2 = d3;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Double.valueOf(d2);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Float minWithOrNull(@NotNull float[] fArr, @NotNull Comparator<? super Float> comparator) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        int i2 = 1;
        if (fArr.length == 0) {
            return null;
        }
        float f2 = fArr[0];
        int lastIndex = getLastIndex(fArr);
        if (1 <= lastIndex) {
            while (true) {
                float f3 = fArr[i2];
                if (comparator.compare(Float.valueOf(f2), Float.valueOf(f3)) > 0) {
                    f2 = f3;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Float.valueOf(f2);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Integer minWithOrNull(@NotNull int[] iArr, @NotNull Comparator<? super Integer> comparator) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        int i2 = 1;
        if (iArr.length == 0) {
            return null;
        }
        int i3 = iArr[0];
        lastIndex = getLastIndex(iArr);
        if (1 <= lastIndex) {
            while (true) {
                int i4 = iArr[i2];
                if (comparator.compare(Integer.valueOf(i3), Integer.valueOf(i4)) > 0) {
                    i3 = i4;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Integer.valueOf(i3);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Long minWithOrNull(@NotNull long[] jArr, @NotNull Comparator<? super Long> comparator) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        int i2 = 1;
        if (jArr.length == 0) {
            return null;
        }
        long j2 = jArr[0];
        lastIndex = getLastIndex(jArr);
        if (1 <= lastIndex) {
            while (true) {
                long j3 = jArr[i2];
                if (comparator.compare(Long.valueOf(j2), Long.valueOf(j3)) > 0) {
                    j2 = j3;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Long.valueOf(j2);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final <T> T minWithOrNull(@NotNull T[] tArr, @NotNull Comparator<? super T> comparator) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        int i2 = 1;
        if (tArr.length == 0) {
            return null;
        }
        T t2 = tArr[0];
        lastIndex = getLastIndex(tArr);
        if (1 <= lastIndex) {
            while (true) {
                T t3 = tArr[i2];
                if (comparator.compare(t2, t3) > 0) {
                    t2 = t3;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return t2;
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Short minWithOrNull(@NotNull short[] sArr, @NotNull Comparator<? super Short> comparator) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        int i2 = 1;
        if (sArr.length == 0) {
            return null;
        }
        short s2 = sArr[0];
        lastIndex = getLastIndex(sArr);
        if (1 <= lastIndex) {
            while (true) {
                short s3 = sArr[i2];
                if (comparator.compare(Short.valueOf(s2), Short.valueOf(s3)) > 0) {
                    s2 = s3;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Short.valueOf(s2);
    }

    public static final boolean none(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        return bArr.length == 0;
    }

    public static final boolean none(@NotNull byte[] bArr, @NotNull Function1<? super Byte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (byte b2 : bArr) {
            if (predicate.invoke(Byte.valueOf(b2)).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    public static final boolean none(@NotNull char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        return cArr.length == 0;
    }

    public static final boolean none(@NotNull char[] cArr, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (char c2 : cArr) {
            if (predicate.invoke(Character.valueOf(c2)).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    public static final boolean none(@NotNull double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        return dArr.length == 0;
    }

    public static final boolean none(@NotNull double[] dArr, @NotNull Function1<? super Double, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (double d2 : dArr) {
            if (predicate.invoke(Double.valueOf(d2)).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    public static final boolean none(@NotNull float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        return fArr.length == 0;
    }

    public static final boolean none(@NotNull float[] fArr, @NotNull Function1<? super Float, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (float f2 : fArr) {
            if (predicate.invoke(Float.valueOf(f2)).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    public static final boolean none(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        return iArr.length == 0;
    }

    public static final boolean none(@NotNull int[] iArr, @NotNull Function1<? super Integer, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (int i2 : iArr) {
            if (predicate.invoke(Integer.valueOf(i2)).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    public static final boolean none(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        return jArr.length == 0;
    }

    public static final boolean none(@NotNull long[] jArr, @NotNull Function1<? super Long, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (long j2 : jArr) {
            if (predicate.invoke(Long.valueOf(j2)).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    public static final <T> boolean none(@NotNull T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        return tArr.length == 0;
    }

    public static final <T> boolean none(@NotNull T[] tArr, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (T t2 : tArr) {
            if (predicate.invoke(t2).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    public static final boolean none(@NotNull short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        return sArr.length == 0;
    }

    public static final boolean none(@NotNull short[] sArr, @NotNull Function1<? super Short, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (short s2 : sArr) {
            if (predicate.invoke(Short.valueOf(s2)).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    public static final boolean none(@NotNull boolean[] zArr) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        return zArr.length == 0;
    }

    public static final boolean none(@NotNull boolean[] zArr, @NotNull Function1<? super Boolean, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (boolean z : zArr) {
            if (predicate.invoke(Boolean.valueOf(z)).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final byte[] onEach(byte[] bArr, Function1<? super Byte, Unit> action) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        for (byte b2 : bArr) {
            action.invoke(Byte.valueOf(b2));
        }
        return bArr;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final char[] onEach(char[] cArr, Function1<? super Character, Unit> action) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        for (char c2 : cArr) {
            action.invoke(Character.valueOf(c2));
        }
        return cArr;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final double[] onEach(double[] dArr, Function1<? super Double, Unit> action) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        for (double d2 : dArr) {
            action.invoke(Double.valueOf(d2));
        }
        return dArr;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final float[] onEach(float[] fArr, Function1<? super Float, Unit> action) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        for (float f2 : fArr) {
            action.invoke(Float.valueOf(f2));
        }
        return fArr;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final int[] onEach(int[] iArr, Function1<? super Integer, Unit> action) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        for (int i2 : iArr) {
            action.invoke(Integer.valueOf(i2));
        }
        return iArr;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final long[] onEach(long[] jArr, Function1<? super Long, Unit> action) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        for (long j2 : jArr) {
            action.invoke(Long.valueOf(j2));
        }
        return jArr;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final <T> T[] onEach(T[] tArr, Function1<? super T, Unit> action) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        for (T t2 : tArr) {
            action.invoke(t2);
        }
        return tArr;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final short[] onEach(short[] sArr, Function1<? super Short, Unit> action) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        for (short s2 : sArr) {
            action.invoke(Short.valueOf(s2));
        }
        return sArr;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final boolean[] onEach(boolean[] zArr, Function1<? super Boolean, Unit> action) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        for (boolean z : zArr) {
            action.invoke(Boolean.valueOf(z));
        }
        return zArr;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final byte[] onEachIndexed(byte[] bArr, Function2<? super Integer, ? super Byte, Unit> action) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        int length = bArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            action.invoke(Integer.valueOf(i3), Byte.valueOf(bArr[i2]));
            i2++;
            i3++;
        }
        return bArr;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final char[] onEachIndexed(char[] cArr, Function2<? super Integer, ? super Character, Unit> action) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        int length = cArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            action.invoke(Integer.valueOf(i3), Character.valueOf(cArr[i2]));
            i2++;
            i3++;
        }
        return cArr;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final double[] onEachIndexed(double[] dArr, Function2<? super Integer, ? super Double, Unit> action) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        int length = dArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            action.invoke(Integer.valueOf(i3), Double.valueOf(dArr[i2]));
            i2++;
            i3++;
        }
        return dArr;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final float[] onEachIndexed(float[] fArr, Function2<? super Integer, ? super Float, Unit> action) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        int length = fArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            action.invoke(Integer.valueOf(i3), Float.valueOf(fArr[i2]));
            i2++;
            i3++;
        }
        return fArr;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final int[] onEachIndexed(int[] iArr, Function2<? super Integer, ? super Integer, Unit> action) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        int length = iArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            action.invoke(Integer.valueOf(i3), Integer.valueOf(iArr[i2]));
            i2++;
            i3++;
        }
        return iArr;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final long[] onEachIndexed(long[] jArr, Function2<? super Integer, ? super Long, Unit> action) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        int length = jArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            action.invoke(Integer.valueOf(i3), Long.valueOf(jArr[i2]));
            i2++;
            i3++;
        }
        return jArr;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final <T> T[] onEachIndexed(T[] tArr, Function2<? super Integer, ? super T, Unit> action) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        int length = tArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            action.invoke(Integer.valueOf(i3), tArr[i2]);
            i2++;
            i3++;
        }
        return tArr;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final short[] onEachIndexed(short[] sArr, Function2<? super Integer, ? super Short, Unit> action) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        int length = sArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            action.invoke(Integer.valueOf(i3), Short.valueOf(sArr[i2]));
            i2++;
            i3++;
        }
        return sArr;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final boolean[] onEachIndexed(boolean[] zArr, Function2<? super Integer, ? super Boolean, Unit> action) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        int length = zArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            action.invoke(Integer.valueOf(i3), Boolean.valueOf(zArr[i2]));
            i2++;
            i3++;
        }
        return zArr;
    }

    @NotNull
    public static final Pair<List<Byte>, List<Byte>> partition(@NotNull byte[] bArr, @NotNull Function1<? super Byte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (byte b2 : bArr) {
            boolean booleanValue = predicate.invoke(Byte.valueOf(b2)).booleanValue();
            Byte valueOf = Byte.valueOf(b2);
            if (booleanValue) {
                arrayList.add(valueOf);
            } else {
                arrayList2.add(valueOf);
            }
        }
        return new Pair<>(arrayList, arrayList2);
    }

    @NotNull
    public static final Pair<List<Character>, List<Character>> partition(@NotNull char[] cArr, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (char c2 : cArr) {
            boolean booleanValue = predicate.invoke(Character.valueOf(c2)).booleanValue();
            Character valueOf = Character.valueOf(c2);
            if (booleanValue) {
                arrayList.add(valueOf);
            } else {
                arrayList2.add(valueOf);
            }
        }
        return new Pair<>(arrayList, arrayList2);
    }

    @NotNull
    public static final Pair<List<Double>, List<Double>> partition(@NotNull double[] dArr, @NotNull Function1<? super Double, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (double d2 : dArr) {
            boolean booleanValue = predicate.invoke(Double.valueOf(d2)).booleanValue();
            Double valueOf = Double.valueOf(d2);
            if (booleanValue) {
                arrayList.add(valueOf);
            } else {
                arrayList2.add(valueOf);
            }
        }
        return new Pair<>(arrayList, arrayList2);
    }

    @NotNull
    public static final Pair<List<Float>, List<Float>> partition(@NotNull float[] fArr, @NotNull Function1<? super Float, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (float f2 : fArr) {
            boolean booleanValue = predicate.invoke(Float.valueOf(f2)).booleanValue();
            Float valueOf = Float.valueOf(f2);
            if (booleanValue) {
                arrayList.add(valueOf);
            } else {
                arrayList2.add(valueOf);
            }
        }
        return new Pair<>(arrayList, arrayList2);
    }

    @NotNull
    public static final Pair<List<Integer>, List<Integer>> partition(@NotNull int[] iArr, @NotNull Function1<? super Integer, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (int i2 : iArr) {
            boolean booleanValue = predicate.invoke(Integer.valueOf(i2)).booleanValue();
            Integer valueOf = Integer.valueOf(i2);
            if (booleanValue) {
                arrayList.add(valueOf);
            } else {
                arrayList2.add(valueOf);
            }
        }
        return new Pair<>(arrayList, arrayList2);
    }

    @NotNull
    public static final Pair<List<Long>, List<Long>> partition(@NotNull long[] jArr, @NotNull Function1<? super Long, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (long j2 : jArr) {
            boolean booleanValue = predicate.invoke(Long.valueOf(j2)).booleanValue();
            Long valueOf = Long.valueOf(j2);
            if (booleanValue) {
                arrayList.add(valueOf);
            } else {
                arrayList2.add(valueOf);
            }
        }
        return new Pair<>(arrayList, arrayList2);
    }

    @NotNull
    public static final <T> Pair<List<T>, List<T>> partition(@NotNull T[] tArr, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (T t2 : tArr) {
            if (predicate.invoke(t2).booleanValue()) {
                arrayList.add(t2);
            } else {
                arrayList2.add(t2);
            }
        }
        return new Pair<>(arrayList, arrayList2);
    }

    @NotNull
    public static final Pair<List<Short>, List<Short>> partition(@NotNull short[] sArr, @NotNull Function1<? super Short, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (short s2 : sArr) {
            boolean booleanValue = predicate.invoke(Short.valueOf(s2)).booleanValue();
            Short valueOf = Short.valueOf(s2);
            if (booleanValue) {
                arrayList.add(valueOf);
            } else {
                arrayList2.add(valueOf);
            }
        }
        return new Pair<>(arrayList, arrayList2);
    }

    @NotNull
    public static final Pair<List<Boolean>, List<Boolean>> partition(@NotNull boolean[] zArr, @NotNull Function1<? super Boolean, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (boolean z : zArr) {
            boolean booleanValue = predicate.invoke(Boolean.valueOf(z)).booleanValue();
            Boolean valueOf = Boolean.valueOf(z);
            if (booleanValue) {
                arrayList.add(valueOf);
            } else {
                arrayList2.add(valueOf);
            }
        }
        return new Pair<>(arrayList, arrayList2);
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final byte random(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        return random(bArr, (Random) Random.Default);
    }

    @SinceKotlin(version = "1.3")
    public static final byte random(@NotNull byte[] bArr, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(random, "random");
        if (bArr.length == 0) {
            throw new NoSuchElementException("Array is empty.");
        }
        return bArr[random.nextInt(bArr.length)];
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final char random(char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        return random(cArr, (Random) Random.Default);
    }

    @SinceKotlin(version = "1.3")
    public static final char random(@NotNull char[] cArr, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(random, "random");
        if (cArr.length == 0) {
            throw new NoSuchElementException("Array is empty.");
        }
        return cArr[random.nextInt(cArr.length)];
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final double random(double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        return random(dArr, Random.Default);
    }

    @SinceKotlin(version = "1.3")
    public static final double random(@NotNull double[] dArr, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(random, "random");
        if (dArr.length == 0) {
            throw new NoSuchElementException("Array is empty.");
        }
        return dArr[random.nextInt(dArr.length)];
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final float random(float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        return random(fArr, (Random) Random.Default);
    }

    @SinceKotlin(version = "1.3")
    public static final float random(@NotNull float[] fArr, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(random, "random");
        if (fArr.length == 0) {
            throw new NoSuchElementException("Array is empty.");
        }
        return fArr[random.nextInt(fArr.length)];
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final int random(int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        return random(iArr, (Random) Random.Default);
    }

    @SinceKotlin(version = "1.3")
    public static final int random(@NotNull int[] iArr, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(random, "random");
        if (iArr.length == 0) {
            throw new NoSuchElementException("Array is empty.");
        }
        return iArr[random.nextInt(iArr.length)];
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final long random(long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        return random(jArr, (Random) Random.Default);
    }

    @SinceKotlin(version = "1.3")
    public static final long random(@NotNull long[] jArr, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(random, "random");
        if (jArr.length == 0) {
            throw new NoSuchElementException("Array is empty.");
        }
        return jArr[random.nextInt(jArr.length)];
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final <T> T random(T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        return (T) random(tArr, Random.Default);
    }

    @SinceKotlin(version = "1.3")
    public static final <T> T random(@NotNull T[] tArr, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(random, "random");
        if (tArr.length == 0) {
            throw new NoSuchElementException("Array is empty.");
        }
        return tArr[random.nextInt(tArr.length)];
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final short random(short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        return random(sArr, (Random) Random.Default);
    }

    @SinceKotlin(version = "1.3")
    public static final short random(@NotNull short[] sArr, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(random, "random");
        if (sArr.length == 0) {
            throw new NoSuchElementException("Array is empty.");
        }
        return sArr[random.nextInt(sArr.length)];
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final boolean random(boolean[] zArr) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        return random(zArr, Random.Default);
    }

    @SinceKotlin(version = "1.3")
    public static final boolean random(@NotNull boolean[] zArr, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(random, "random");
        if (zArr.length == 0) {
            throw new NoSuchElementException("Array is empty.");
        }
        return zArr[random.nextInt(zArr.length)];
    }

    @SinceKotlin(version = "1.4")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @InlineOnly
    private static final Boolean randomOrNull(boolean[] zArr) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        return randomOrNull(zArr, Random.Default);
    }

    @SinceKotlin(version = "1.4")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @Nullable
    public static final Boolean randomOrNull(@NotNull boolean[] zArr, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(random, "random");
        if (zArr.length == 0) {
            return null;
        }
        return Boolean.valueOf(zArr[random.nextInt(zArr.length)]);
    }

    @SinceKotlin(version = "1.4")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @InlineOnly
    private static final Byte randomOrNull(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        return randomOrNull(bArr, (Random) Random.Default);
    }

    @SinceKotlin(version = "1.4")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @Nullable
    public static final Byte randomOrNull(@NotNull byte[] bArr, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(random, "random");
        if (bArr.length == 0) {
            return null;
        }
        return Byte.valueOf(bArr[random.nextInt(bArr.length)]);
    }

    @SinceKotlin(version = "1.4")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @InlineOnly
    private static final Character randomOrNull(char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        return randomOrNull(cArr, (Random) Random.Default);
    }

    @SinceKotlin(version = "1.4")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @Nullable
    public static final Character randomOrNull(@NotNull char[] cArr, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(random, "random");
        if (cArr.length == 0) {
            return null;
        }
        return Character.valueOf(cArr[random.nextInt(cArr.length)]);
    }

    @SinceKotlin(version = "1.4")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @InlineOnly
    private static final Double randomOrNull(double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        return randomOrNull(dArr, Random.Default);
    }

    @SinceKotlin(version = "1.4")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @Nullable
    public static final Double randomOrNull(@NotNull double[] dArr, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(random, "random");
        if (dArr.length == 0) {
            return null;
        }
        return Double.valueOf(dArr[random.nextInt(dArr.length)]);
    }

    @SinceKotlin(version = "1.4")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @InlineOnly
    private static final Float randomOrNull(float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        return randomOrNull(fArr, (Random) Random.Default);
    }

    @SinceKotlin(version = "1.4")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @Nullable
    public static final Float randomOrNull(@NotNull float[] fArr, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(random, "random");
        if (fArr.length == 0) {
            return null;
        }
        return Float.valueOf(fArr[random.nextInt(fArr.length)]);
    }

    @SinceKotlin(version = "1.4")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @InlineOnly
    private static final Integer randomOrNull(int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        return randomOrNull(iArr, (Random) Random.Default);
    }

    @SinceKotlin(version = "1.4")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @Nullable
    public static final Integer randomOrNull(@NotNull int[] iArr, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(random, "random");
        if (iArr.length == 0) {
            return null;
        }
        return Integer.valueOf(iArr[random.nextInt(iArr.length)]);
    }

    @SinceKotlin(version = "1.4")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @InlineOnly
    private static final Long randomOrNull(long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        return randomOrNull(jArr, (Random) Random.Default);
    }

    @SinceKotlin(version = "1.4")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @Nullable
    public static final Long randomOrNull(@NotNull long[] jArr, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(random, "random");
        if (jArr.length == 0) {
            return null;
        }
        return Long.valueOf(jArr[random.nextInt(jArr.length)]);
    }

    @SinceKotlin(version = "1.4")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @InlineOnly
    private static final <T> T randomOrNull(T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        return (T) randomOrNull(tArr, Random.Default);
    }

    @SinceKotlin(version = "1.4")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @Nullable
    public static final <T> T randomOrNull(@NotNull T[] tArr, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(random, "random");
        if (tArr.length == 0) {
            return null;
        }
        return tArr[random.nextInt(tArr.length)];
    }

    @SinceKotlin(version = "1.4")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @InlineOnly
    private static final Short randomOrNull(short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        return randomOrNull(sArr, (Random) Random.Default);
    }

    @SinceKotlin(version = "1.4")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @Nullable
    public static final Short randomOrNull(@NotNull short[] sArr, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(random, "random");
        if (sArr.length == 0) {
            return null;
        }
        return Short.valueOf(sArr[random.nextInt(sArr.length)]);
    }

    public static final byte reduce(@NotNull byte[] bArr, @NotNull Function2<? super Byte, ? super Byte, Byte> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int i2 = 1;
        if (bArr.length == 0) {
            throw new UnsupportedOperationException("Empty array can't be reduced.");
        }
        byte b2 = bArr[0];
        lastIndex = getLastIndex(bArr);
        if (1 <= lastIndex) {
            while (true) {
                b2 = operation.invoke(Byte.valueOf(b2), Byte.valueOf(bArr[i2])).byteValue();
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return b2;
    }

    public static final char reduce(@NotNull char[] cArr, @NotNull Function2<? super Character, ? super Character, Character> operation) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int i2 = 1;
        if (cArr.length == 0) {
            throw new UnsupportedOperationException("Empty array can't be reduced.");
        }
        char c2 = cArr[0];
        int lastIndex = getLastIndex(cArr);
        if (1 <= lastIndex) {
            while (true) {
                c2 = operation.invoke(Character.valueOf(c2), Character.valueOf(cArr[i2])).charValue();
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return c2;
    }

    public static final double reduce(@NotNull double[] dArr, @NotNull Function2<? super Double, ? super Double, Double> operation) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int i2 = 1;
        if (dArr.length == 0) {
            throw new UnsupportedOperationException("Empty array can't be reduced.");
        }
        double d2 = dArr[0];
        int lastIndex = getLastIndex(dArr);
        if (1 <= lastIndex) {
            while (true) {
                d2 = operation.invoke(Double.valueOf(d2), Double.valueOf(dArr[i2])).doubleValue();
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return d2;
    }

    public static final float reduce(@NotNull float[] fArr, @NotNull Function2<? super Float, ? super Float, Float> operation) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int i2 = 1;
        if (fArr.length == 0) {
            throw new UnsupportedOperationException("Empty array can't be reduced.");
        }
        float f2 = fArr[0];
        int lastIndex = getLastIndex(fArr);
        if (1 <= lastIndex) {
            while (true) {
                f2 = operation.invoke(Float.valueOf(f2), Float.valueOf(fArr[i2])).floatValue();
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return f2;
    }

    public static final int reduce(@NotNull int[] iArr, @NotNull Function2<? super Integer, ? super Integer, Integer> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int i2 = 1;
        if (iArr.length == 0) {
            throw new UnsupportedOperationException("Empty array can't be reduced.");
        }
        int i3 = iArr[0];
        lastIndex = getLastIndex(iArr);
        if (1 <= lastIndex) {
            while (true) {
                i3 = operation.invoke(Integer.valueOf(i3), Integer.valueOf(iArr[i2])).intValue();
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return i3;
    }

    public static final long reduce(@NotNull long[] jArr, @NotNull Function2<? super Long, ? super Long, Long> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int i2 = 1;
        if (jArr.length == 0) {
            throw new UnsupportedOperationException("Empty array can't be reduced.");
        }
        long j2 = jArr[0];
        lastIndex = getLastIndex(jArr);
        if (1 <= lastIndex) {
            while (true) {
                j2 = operation.invoke(Long.valueOf(j2), Long.valueOf(jArr[i2])).longValue();
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return j2;
    }

    public static final <S, T extends S> S reduce(@NotNull T[] tArr, @NotNull Function2<? super S, ? super T, ? extends S> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int i2 = 1;
        if (tArr.length == 0) {
            throw new UnsupportedOperationException("Empty array can't be reduced.");
        }
        S s2 = (Object) tArr[0];
        lastIndex = getLastIndex(tArr);
        if (1 <= lastIndex) {
            while (true) {
                s2 = operation.invoke(s2, (Object) tArr[i2]);
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return (S) s2;
    }

    public static final short reduce(@NotNull short[] sArr, @NotNull Function2<? super Short, ? super Short, Short> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int i2 = 1;
        if (sArr.length == 0) {
            throw new UnsupportedOperationException("Empty array can't be reduced.");
        }
        short s2 = sArr[0];
        lastIndex = getLastIndex(sArr);
        if (1 <= lastIndex) {
            while (true) {
                s2 = operation.invoke(Short.valueOf(s2), Short.valueOf(sArr[i2])).shortValue();
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return s2;
    }

    public static final boolean reduce(@NotNull boolean[] zArr, @NotNull Function2<? super Boolean, ? super Boolean, Boolean> operation) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int i2 = 1;
        if (zArr.length == 0) {
            throw new UnsupportedOperationException("Empty array can't be reduced.");
        }
        boolean z = zArr[0];
        int lastIndex = getLastIndex(zArr);
        if (1 <= lastIndex) {
            while (true) {
                z = operation.invoke(Boolean.valueOf(z), Boolean.valueOf(zArr[i2])).booleanValue();
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return z;
    }

    public static final byte reduceIndexed(@NotNull byte[] bArr, @NotNull Function3<? super Integer, ? super Byte, ? super Byte, Byte> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int i2 = 1;
        if (bArr.length == 0) {
            throw new UnsupportedOperationException("Empty array can't be reduced.");
        }
        byte b2 = bArr[0];
        lastIndex = getLastIndex(bArr);
        if (1 <= lastIndex) {
            while (true) {
                b2 = operation.invoke(Integer.valueOf(i2), Byte.valueOf(b2), Byte.valueOf(bArr[i2])).byteValue();
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return b2;
    }

    public static final char reduceIndexed(@NotNull char[] cArr, @NotNull Function3<? super Integer, ? super Character, ? super Character, Character> operation) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int i2 = 1;
        if (cArr.length == 0) {
            throw new UnsupportedOperationException("Empty array can't be reduced.");
        }
        char c2 = cArr[0];
        int lastIndex = getLastIndex(cArr);
        if (1 <= lastIndex) {
            while (true) {
                c2 = operation.invoke(Integer.valueOf(i2), Character.valueOf(c2), Character.valueOf(cArr[i2])).charValue();
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return c2;
    }

    public static final double reduceIndexed(@NotNull double[] dArr, @NotNull Function3<? super Integer, ? super Double, ? super Double, Double> operation) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int i2 = 1;
        if (dArr.length == 0) {
            throw new UnsupportedOperationException("Empty array can't be reduced.");
        }
        double d2 = dArr[0];
        int lastIndex = getLastIndex(dArr);
        if (1 <= lastIndex) {
            while (true) {
                d2 = operation.invoke(Integer.valueOf(i2), Double.valueOf(d2), Double.valueOf(dArr[i2])).doubleValue();
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return d2;
    }

    public static final float reduceIndexed(@NotNull float[] fArr, @NotNull Function3<? super Integer, ? super Float, ? super Float, Float> operation) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int i2 = 1;
        if (fArr.length == 0) {
            throw new UnsupportedOperationException("Empty array can't be reduced.");
        }
        float f2 = fArr[0];
        int lastIndex = getLastIndex(fArr);
        if (1 <= lastIndex) {
            while (true) {
                f2 = operation.invoke(Integer.valueOf(i2), Float.valueOf(f2), Float.valueOf(fArr[i2])).floatValue();
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return f2;
    }

    public static final int reduceIndexed(@NotNull int[] iArr, @NotNull Function3<? super Integer, ? super Integer, ? super Integer, Integer> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int i2 = 1;
        if (iArr.length == 0) {
            throw new UnsupportedOperationException("Empty array can't be reduced.");
        }
        int i3 = iArr[0];
        lastIndex = getLastIndex(iArr);
        if (1 <= lastIndex) {
            while (true) {
                i3 = operation.invoke(Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(iArr[i2])).intValue();
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return i3;
    }

    public static final long reduceIndexed(@NotNull long[] jArr, @NotNull Function3<? super Integer, ? super Long, ? super Long, Long> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int i2 = 1;
        if (jArr.length == 0) {
            throw new UnsupportedOperationException("Empty array can't be reduced.");
        }
        long j2 = jArr[0];
        lastIndex = getLastIndex(jArr);
        if (1 <= lastIndex) {
            while (true) {
                j2 = operation.invoke(Integer.valueOf(i2), Long.valueOf(j2), Long.valueOf(jArr[i2])).longValue();
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return j2;
    }

    public static final <S, T extends S> S reduceIndexed(@NotNull T[] tArr, @NotNull Function3<? super Integer, ? super S, ? super T, ? extends S> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int i2 = 1;
        if (tArr.length == 0) {
            throw new UnsupportedOperationException("Empty array can't be reduced.");
        }
        S s2 = (Object) tArr[0];
        lastIndex = getLastIndex(tArr);
        if (1 <= lastIndex) {
            while (true) {
                s2 = operation.invoke(Integer.valueOf(i2), s2, (Object) tArr[i2]);
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return (S) s2;
    }

    public static final short reduceIndexed(@NotNull short[] sArr, @NotNull Function3<? super Integer, ? super Short, ? super Short, Short> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int i2 = 1;
        if (sArr.length == 0) {
            throw new UnsupportedOperationException("Empty array can't be reduced.");
        }
        short s2 = sArr[0];
        lastIndex = getLastIndex(sArr);
        if (1 <= lastIndex) {
            while (true) {
                s2 = operation.invoke(Integer.valueOf(i2), Short.valueOf(s2), Short.valueOf(sArr[i2])).shortValue();
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return s2;
    }

    public static final boolean reduceIndexed(@NotNull boolean[] zArr, @NotNull Function3<? super Integer, ? super Boolean, ? super Boolean, Boolean> operation) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int i2 = 1;
        if (zArr.length == 0) {
            throw new UnsupportedOperationException("Empty array can't be reduced.");
        }
        boolean z = zArr[0];
        int lastIndex = getLastIndex(zArr);
        if (1 <= lastIndex) {
            while (true) {
                z = operation.invoke(Integer.valueOf(i2), Boolean.valueOf(z), Boolean.valueOf(zArr[i2])).booleanValue();
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return z;
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Boolean reduceIndexedOrNull(@NotNull boolean[] zArr, @NotNull Function3<? super Integer, ? super Boolean, ? super Boolean, Boolean> operation) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int i2 = 1;
        if (zArr.length == 0) {
            return null;
        }
        boolean z = zArr[0];
        int lastIndex = getLastIndex(zArr);
        if (1 <= lastIndex) {
            while (true) {
                z = operation.invoke(Integer.valueOf(i2), Boolean.valueOf(z), Boolean.valueOf(zArr[i2])).booleanValue();
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Boolean.valueOf(z);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Byte reduceIndexedOrNull(@NotNull byte[] bArr, @NotNull Function3<? super Integer, ? super Byte, ? super Byte, Byte> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int i2 = 1;
        if (bArr.length == 0) {
            return null;
        }
        byte b2 = bArr[0];
        lastIndex = getLastIndex(bArr);
        if (1 <= lastIndex) {
            while (true) {
                b2 = operation.invoke(Integer.valueOf(i2), Byte.valueOf(b2), Byte.valueOf(bArr[i2])).byteValue();
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Byte.valueOf(b2);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Character reduceIndexedOrNull(@NotNull char[] cArr, @NotNull Function3<? super Integer, ? super Character, ? super Character, Character> operation) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int i2 = 1;
        if (cArr.length == 0) {
            return null;
        }
        char c2 = cArr[0];
        int lastIndex = getLastIndex(cArr);
        if (1 <= lastIndex) {
            while (true) {
                c2 = operation.invoke(Integer.valueOf(i2), Character.valueOf(c2), Character.valueOf(cArr[i2])).charValue();
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Character.valueOf(c2);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Double reduceIndexedOrNull(@NotNull double[] dArr, @NotNull Function3<? super Integer, ? super Double, ? super Double, Double> operation) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int i2 = 1;
        if (dArr.length == 0) {
            return null;
        }
        double d2 = dArr[0];
        int lastIndex = getLastIndex(dArr);
        if (1 <= lastIndex) {
            while (true) {
                d2 = operation.invoke(Integer.valueOf(i2), Double.valueOf(d2), Double.valueOf(dArr[i2])).doubleValue();
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Double.valueOf(d2);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Float reduceIndexedOrNull(@NotNull float[] fArr, @NotNull Function3<? super Integer, ? super Float, ? super Float, Float> operation) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int i2 = 1;
        if (fArr.length == 0) {
            return null;
        }
        float f2 = fArr[0];
        int lastIndex = getLastIndex(fArr);
        if (1 <= lastIndex) {
            while (true) {
                f2 = operation.invoke(Integer.valueOf(i2), Float.valueOf(f2), Float.valueOf(fArr[i2])).floatValue();
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Float.valueOf(f2);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Integer reduceIndexedOrNull(@NotNull int[] iArr, @NotNull Function3<? super Integer, ? super Integer, ? super Integer, Integer> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int i2 = 1;
        if (iArr.length == 0) {
            return null;
        }
        int i3 = iArr[0];
        lastIndex = getLastIndex(iArr);
        if (1 <= lastIndex) {
            while (true) {
                i3 = operation.invoke(Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(iArr[i2])).intValue();
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Integer.valueOf(i3);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Long reduceIndexedOrNull(@NotNull long[] jArr, @NotNull Function3<? super Integer, ? super Long, ? super Long, Long> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int i2 = 1;
        if (jArr.length == 0) {
            return null;
        }
        long j2 = jArr[0];
        lastIndex = getLastIndex(jArr);
        if (1 <= lastIndex) {
            while (true) {
                j2 = operation.invoke(Integer.valueOf(i2), Long.valueOf(j2), Long.valueOf(jArr[i2])).longValue();
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Long.valueOf(j2);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final <S, T extends S> S reduceIndexedOrNull(@NotNull T[] tArr, @NotNull Function3<? super Integer, ? super S, ? super T, ? extends S> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int i2 = 1;
        if (tArr.length == 0) {
            return null;
        }
        S s2 = (Object) tArr[0];
        lastIndex = getLastIndex(tArr);
        if (1 <= lastIndex) {
            while (true) {
                s2 = operation.invoke(Integer.valueOf(i2), s2, (Object) tArr[i2]);
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return (S) s2;
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Short reduceIndexedOrNull(@NotNull short[] sArr, @NotNull Function3<? super Integer, ? super Short, ? super Short, Short> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int i2 = 1;
        if (sArr.length == 0) {
            return null;
        }
        short s2 = sArr[0];
        lastIndex = getLastIndex(sArr);
        if (1 <= lastIndex) {
            while (true) {
                s2 = operation.invoke(Integer.valueOf(i2), Short.valueOf(s2), Short.valueOf(sArr[i2])).shortValue();
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Short.valueOf(s2);
    }

    @SinceKotlin(version = "1.4")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @Nullable
    public static final Boolean reduceOrNull(@NotNull boolean[] zArr, @NotNull Function2<? super Boolean, ? super Boolean, Boolean> operation) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int i2 = 1;
        if (zArr.length == 0) {
            return null;
        }
        boolean z = zArr[0];
        int lastIndex = getLastIndex(zArr);
        if (1 <= lastIndex) {
            while (true) {
                z = operation.invoke(Boolean.valueOf(z), Boolean.valueOf(zArr[i2])).booleanValue();
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Boolean.valueOf(z);
    }

    @SinceKotlin(version = "1.4")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @Nullable
    public static final Byte reduceOrNull(@NotNull byte[] bArr, @NotNull Function2<? super Byte, ? super Byte, Byte> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int i2 = 1;
        if (bArr.length == 0) {
            return null;
        }
        byte b2 = bArr[0];
        lastIndex = getLastIndex(bArr);
        if (1 <= lastIndex) {
            while (true) {
                b2 = operation.invoke(Byte.valueOf(b2), Byte.valueOf(bArr[i2])).byteValue();
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Byte.valueOf(b2);
    }

    @SinceKotlin(version = "1.4")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @Nullable
    public static final Character reduceOrNull(@NotNull char[] cArr, @NotNull Function2<? super Character, ? super Character, Character> operation) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int i2 = 1;
        if (cArr.length == 0) {
            return null;
        }
        char c2 = cArr[0];
        int lastIndex = getLastIndex(cArr);
        if (1 <= lastIndex) {
            while (true) {
                c2 = operation.invoke(Character.valueOf(c2), Character.valueOf(cArr[i2])).charValue();
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Character.valueOf(c2);
    }

    @SinceKotlin(version = "1.4")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @Nullable
    public static final Double reduceOrNull(@NotNull double[] dArr, @NotNull Function2<? super Double, ? super Double, Double> operation) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int i2 = 1;
        if (dArr.length == 0) {
            return null;
        }
        double d2 = dArr[0];
        int lastIndex = getLastIndex(dArr);
        if (1 <= lastIndex) {
            while (true) {
                d2 = operation.invoke(Double.valueOf(d2), Double.valueOf(dArr[i2])).doubleValue();
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Double.valueOf(d2);
    }

    @SinceKotlin(version = "1.4")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @Nullable
    public static final Float reduceOrNull(@NotNull float[] fArr, @NotNull Function2<? super Float, ? super Float, Float> operation) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int i2 = 1;
        if (fArr.length == 0) {
            return null;
        }
        float f2 = fArr[0];
        int lastIndex = getLastIndex(fArr);
        if (1 <= lastIndex) {
            while (true) {
                f2 = operation.invoke(Float.valueOf(f2), Float.valueOf(fArr[i2])).floatValue();
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Float.valueOf(f2);
    }

    @SinceKotlin(version = "1.4")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @Nullable
    public static final Integer reduceOrNull(@NotNull int[] iArr, @NotNull Function2<? super Integer, ? super Integer, Integer> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int i2 = 1;
        if (iArr.length == 0) {
            return null;
        }
        int i3 = iArr[0];
        lastIndex = getLastIndex(iArr);
        if (1 <= lastIndex) {
            while (true) {
                i3 = operation.invoke(Integer.valueOf(i3), Integer.valueOf(iArr[i2])).intValue();
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Integer.valueOf(i3);
    }

    @SinceKotlin(version = "1.4")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @Nullable
    public static final Long reduceOrNull(@NotNull long[] jArr, @NotNull Function2<? super Long, ? super Long, Long> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int i2 = 1;
        if (jArr.length == 0) {
            return null;
        }
        long j2 = jArr[0];
        lastIndex = getLastIndex(jArr);
        if (1 <= lastIndex) {
            while (true) {
                j2 = operation.invoke(Long.valueOf(j2), Long.valueOf(jArr[i2])).longValue();
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Long.valueOf(j2);
    }

    @SinceKotlin(version = "1.4")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @Nullable
    public static final <S, T extends S> S reduceOrNull(@NotNull T[] tArr, @NotNull Function2<? super S, ? super T, ? extends S> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int i2 = 1;
        if (tArr.length == 0) {
            return null;
        }
        S s2 = (Object) tArr[0];
        lastIndex = getLastIndex(tArr);
        if (1 <= lastIndex) {
            while (true) {
                s2 = operation.invoke(s2, (Object) tArr[i2]);
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return (S) s2;
    }

    @SinceKotlin(version = "1.4")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @Nullable
    public static final Short reduceOrNull(@NotNull short[] sArr, @NotNull Function2<? super Short, ? super Short, Short> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int i2 = 1;
        if (sArr.length == 0) {
            return null;
        }
        short s2 = sArr[0];
        lastIndex = getLastIndex(sArr);
        if (1 <= lastIndex) {
            while (true) {
                s2 = operation.invoke(Short.valueOf(s2), Short.valueOf(sArr[i2])).shortValue();
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return Short.valueOf(s2);
    }

    public static final byte reduceRight(@NotNull byte[] bArr, @NotNull Function2<? super Byte, ? super Byte, Byte> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        lastIndex = getLastIndex(bArr);
        if (lastIndex >= 0) {
            byte b2 = bArr[lastIndex];
            for (int i2 = lastIndex - 1; i2 >= 0; i2--) {
                b2 = operation.invoke(Byte.valueOf(bArr[i2]), Byte.valueOf(b2)).byteValue();
            }
            return b2;
        }
        throw new UnsupportedOperationException("Empty array can't be reduced.");
    }

    public static final char reduceRight(@NotNull char[] cArr, @NotNull Function2<? super Character, ? super Character, Character> operation) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int lastIndex = getLastIndex(cArr);
        if (lastIndex >= 0) {
            char c2 = cArr[lastIndex];
            for (int i2 = lastIndex - 1; i2 >= 0; i2--) {
                c2 = operation.invoke(Character.valueOf(cArr[i2]), Character.valueOf(c2)).charValue();
            }
            return c2;
        }
        throw new UnsupportedOperationException("Empty array can't be reduced.");
    }

    public static final double reduceRight(@NotNull double[] dArr, @NotNull Function2<? super Double, ? super Double, Double> operation) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int lastIndex = getLastIndex(dArr);
        if (lastIndex >= 0) {
            double d2 = dArr[lastIndex];
            for (int i2 = lastIndex - 1; i2 >= 0; i2--) {
                d2 = operation.invoke(Double.valueOf(dArr[i2]), Double.valueOf(d2)).doubleValue();
            }
            return d2;
        }
        throw new UnsupportedOperationException("Empty array can't be reduced.");
    }

    public static final float reduceRight(@NotNull float[] fArr, @NotNull Function2<? super Float, ? super Float, Float> operation) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int lastIndex = getLastIndex(fArr);
        if (lastIndex >= 0) {
            float f2 = fArr[lastIndex];
            for (int i2 = lastIndex - 1; i2 >= 0; i2--) {
                f2 = operation.invoke(Float.valueOf(fArr[i2]), Float.valueOf(f2)).floatValue();
            }
            return f2;
        }
        throw new UnsupportedOperationException("Empty array can't be reduced.");
    }

    public static final int reduceRight(@NotNull int[] iArr, @NotNull Function2<? super Integer, ? super Integer, Integer> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        lastIndex = getLastIndex(iArr);
        if (lastIndex >= 0) {
            int i2 = iArr[lastIndex];
            for (int i3 = lastIndex - 1; i3 >= 0; i3--) {
                i2 = operation.invoke(Integer.valueOf(iArr[i3]), Integer.valueOf(i2)).intValue();
            }
            return i2;
        }
        throw new UnsupportedOperationException("Empty array can't be reduced.");
    }

    public static final long reduceRight(@NotNull long[] jArr, @NotNull Function2<? super Long, ? super Long, Long> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        lastIndex = getLastIndex(jArr);
        if (lastIndex >= 0) {
            long j2 = jArr[lastIndex];
            for (int i2 = lastIndex - 1; i2 >= 0; i2--) {
                j2 = operation.invoke(Long.valueOf(jArr[i2]), Long.valueOf(j2)).longValue();
            }
            return j2;
        }
        throw new UnsupportedOperationException("Empty array can't be reduced.");
    }

    public static final <S, T extends S> S reduceRight(@NotNull T[] tArr, @NotNull Function2<? super T, ? super S, ? extends S> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        lastIndex = getLastIndex(tArr);
        if (lastIndex >= 0) {
            Object obj = (S) tArr[lastIndex];
            for (int i2 = lastIndex - 1; i2 >= 0; i2--) {
                obj = (S) operation.invoke((Object) tArr[i2], obj);
            }
            return (S) obj;
        }
        throw new UnsupportedOperationException("Empty array can't be reduced.");
    }

    public static final short reduceRight(@NotNull short[] sArr, @NotNull Function2<? super Short, ? super Short, Short> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        lastIndex = getLastIndex(sArr);
        if (lastIndex >= 0) {
            short s2 = sArr[lastIndex];
            for (int i2 = lastIndex - 1; i2 >= 0; i2--) {
                s2 = operation.invoke(Short.valueOf(sArr[i2]), Short.valueOf(s2)).shortValue();
            }
            return s2;
        }
        throw new UnsupportedOperationException("Empty array can't be reduced.");
    }

    public static final boolean reduceRight(@NotNull boolean[] zArr, @NotNull Function2<? super Boolean, ? super Boolean, Boolean> operation) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int lastIndex = getLastIndex(zArr);
        if (lastIndex >= 0) {
            boolean z = zArr[lastIndex];
            for (int i2 = lastIndex - 1; i2 >= 0; i2--) {
                z = operation.invoke(Boolean.valueOf(zArr[i2]), Boolean.valueOf(z)).booleanValue();
            }
            return z;
        }
        throw new UnsupportedOperationException("Empty array can't be reduced.");
    }

    public static final byte reduceRightIndexed(@NotNull byte[] bArr, @NotNull Function3<? super Integer, ? super Byte, ? super Byte, Byte> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        lastIndex = getLastIndex(bArr);
        if (lastIndex >= 0) {
            byte b2 = bArr[lastIndex];
            for (int i2 = lastIndex - 1; i2 >= 0; i2--) {
                b2 = operation.invoke(Integer.valueOf(i2), Byte.valueOf(bArr[i2]), Byte.valueOf(b2)).byteValue();
            }
            return b2;
        }
        throw new UnsupportedOperationException("Empty array can't be reduced.");
    }

    public static final char reduceRightIndexed(@NotNull char[] cArr, @NotNull Function3<? super Integer, ? super Character, ? super Character, Character> operation) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int lastIndex = getLastIndex(cArr);
        if (lastIndex >= 0) {
            char c2 = cArr[lastIndex];
            for (int i2 = lastIndex - 1; i2 >= 0; i2--) {
                c2 = operation.invoke(Integer.valueOf(i2), Character.valueOf(cArr[i2]), Character.valueOf(c2)).charValue();
            }
            return c2;
        }
        throw new UnsupportedOperationException("Empty array can't be reduced.");
    }

    public static final double reduceRightIndexed(@NotNull double[] dArr, @NotNull Function3<? super Integer, ? super Double, ? super Double, Double> operation) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int lastIndex = getLastIndex(dArr);
        if (lastIndex >= 0) {
            double d2 = dArr[lastIndex];
            for (int i2 = lastIndex - 1; i2 >= 0; i2--) {
                d2 = operation.invoke(Integer.valueOf(i2), Double.valueOf(dArr[i2]), Double.valueOf(d2)).doubleValue();
            }
            return d2;
        }
        throw new UnsupportedOperationException("Empty array can't be reduced.");
    }

    public static final float reduceRightIndexed(@NotNull float[] fArr, @NotNull Function3<? super Integer, ? super Float, ? super Float, Float> operation) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int lastIndex = getLastIndex(fArr);
        if (lastIndex >= 0) {
            float f2 = fArr[lastIndex];
            for (int i2 = lastIndex - 1; i2 >= 0; i2--) {
                f2 = operation.invoke(Integer.valueOf(i2), Float.valueOf(fArr[i2]), Float.valueOf(f2)).floatValue();
            }
            return f2;
        }
        throw new UnsupportedOperationException("Empty array can't be reduced.");
    }

    public static final int reduceRightIndexed(@NotNull int[] iArr, @NotNull Function3<? super Integer, ? super Integer, ? super Integer, Integer> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        lastIndex = getLastIndex(iArr);
        if (lastIndex >= 0) {
            int i2 = iArr[lastIndex];
            for (int i3 = lastIndex - 1; i3 >= 0; i3--) {
                i2 = operation.invoke(Integer.valueOf(i3), Integer.valueOf(iArr[i3]), Integer.valueOf(i2)).intValue();
            }
            return i2;
        }
        throw new UnsupportedOperationException("Empty array can't be reduced.");
    }

    public static final long reduceRightIndexed(@NotNull long[] jArr, @NotNull Function3<? super Integer, ? super Long, ? super Long, Long> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        lastIndex = getLastIndex(jArr);
        if (lastIndex >= 0) {
            long j2 = jArr[lastIndex];
            for (int i2 = lastIndex - 1; i2 >= 0; i2--) {
                j2 = operation.invoke(Integer.valueOf(i2), Long.valueOf(jArr[i2]), Long.valueOf(j2)).longValue();
            }
            return j2;
        }
        throw new UnsupportedOperationException("Empty array can't be reduced.");
    }

    public static final <S, T extends S> S reduceRightIndexed(@NotNull T[] tArr, @NotNull Function3<? super Integer, ? super T, ? super S, ? extends S> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        lastIndex = getLastIndex(tArr);
        if (lastIndex >= 0) {
            Object obj = (S) tArr[lastIndex];
            for (int i2 = lastIndex - 1; i2 >= 0; i2--) {
                obj = (S) operation.invoke(Integer.valueOf(i2), (Object) tArr[i2], obj);
            }
            return (S) obj;
        }
        throw new UnsupportedOperationException("Empty array can't be reduced.");
    }

    public static final short reduceRightIndexed(@NotNull short[] sArr, @NotNull Function3<? super Integer, ? super Short, ? super Short, Short> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        lastIndex = getLastIndex(sArr);
        if (lastIndex >= 0) {
            short s2 = sArr[lastIndex];
            for (int i2 = lastIndex - 1; i2 >= 0; i2--) {
                s2 = operation.invoke(Integer.valueOf(i2), Short.valueOf(sArr[i2]), Short.valueOf(s2)).shortValue();
            }
            return s2;
        }
        throw new UnsupportedOperationException("Empty array can't be reduced.");
    }

    public static final boolean reduceRightIndexed(@NotNull boolean[] zArr, @NotNull Function3<? super Integer, ? super Boolean, ? super Boolean, Boolean> operation) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int lastIndex = getLastIndex(zArr);
        if (lastIndex >= 0) {
            boolean z = zArr[lastIndex];
            for (int i2 = lastIndex - 1; i2 >= 0; i2--) {
                z = operation.invoke(Integer.valueOf(i2), Boolean.valueOf(zArr[i2]), Boolean.valueOf(z)).booleanValue();
            }
            return z;
        }
        throw new UnsupportedOperationException("Empty array can't be reduced.");
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Boolean reduceRightIndexedOrNull(@NotNull boolean[] zArr, @NotNull Function3<? super Integer, ? super Boolean, ? super Boolean, Boolean> operation) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int lastIndex = getLastIndex(zArr);
        if (lastIndex < 0) {
            return null;
        }
        boolean z = zArr[lastIndex];
        for (int i2 = lastIndex - 1; i2 >= 0; i2--) {
            z = operation.invoke(Integer.valueOf(i2), Boolean.valueOf(zArr[i2]), Boolean.valueOf(z)).booleanValue();
        }
        return Boolean.valueOf(z);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Byte reduceRightIndexedOrNull(@NotNull byte[] bArr, @NotNull Function3<? super Integer, ? super Byte, ? super Byte, Byte> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        lastIndex = getLastIndex(bArr);
        if (lastIndex < 0) {
            return null;
        }
        byte b2 = bArr[lastIndex];
        for (int i2 = lastIndex - 1; i2 >= 0; i2--) {
            b2 = operation.invoke(Integer.valueOf(i2), Byte.valueOf(bArr[i2]), Byte.valueOf(b2)).byteValue();
        }
        return Byte.valueOf(b2);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Character reduceRightIndexedOrNull(@NotNull char[] cArr, @NotNull Function3<? super Integer, ? super Character, ? super Character, Character> operation) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int lastIndex = getLastIndex(cArr);
        if (lastIndex < 0) {
            return null;
        }
        char c2 = cArr[lastIndex];
        for (int i2 = lastIndex - 1; i2 >= 0; i2--) {
            c2 = operation.invoke(Integer.valueOf(i2), Character.valueOf(cArr[i2]), Character.valueOf(c2)).charValue();
        }
        return Character.valueOf(c2);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Double reduceRightIndexedOrNull(@NotNull double[] dArr, @NotNull Function3<? super Integer, ? super Double, ? super Double, Double> operation) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int lastIndex = getLastIndex(dArr);
        if (lastIndex < 0) {
            return null;
        }
        double d2 = dArr[lastIndex];
        for (int i2 = lastIndex - 1; i2 >= 0; i2--) {
            d2 = operation.invoke(Integer.valueOf(i2), Double.valueOf(dArr[i2]), Double.valueOf(d2)).doubleValue();
        }
        return Double.valueOf(d2);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Float reduceRightIndexedOrNull(@NotNull float[] fArr, @NotNull Function3<? super Integer, ? super Float, ? super Float, Float> operation) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int lastIndex = getLastIndex(fArr);
        if (lastIndex < 0) {
            return null;
        }
        float f2 = fArr[lastIndex];
        for (int i2 = lastIndex - 1; i2 >= 0; i2--) {
            f2 = operation.invoke(Integer.valueOf(i2), Float.valueOf(fArr[i2]), Float.valueOf(f2)).floatValue();
        }
        return Float.valueOf(f2);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Integer reduceRightIndexedOrNull(@NotNull int[] iArr, @NotNull Function3<? super Integer, ? super Integer, ? super Integer, Integer> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        lastIndex = getLastIndex(iArr);
        if (lastIndex < 0) {
            return null;
        }
        int i2 = iArr[lastIndex];
        for (int i3 = lastIndex - 1; i3 >= 0; i3--) {
            i2 = operation.invoke(Integer.valueOf(i3), Integer.valueOf(iArr[i3]), Integer.valueOf(i2)).intValue();
        }
        return Integer.valueOf(i2);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Long reduceRightIndexedOrNull(@NotNull long[] jArr, @NotNull Function3<? super Integer, ? super Long, ? super Long, Long> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        lastIndex = getLastIndex(jArr);
        if (lastIndex < 0) {
            return null;
        }
        long j2 = jArr[lastIndex];
        for (int i2 = lastIndex - 1; i2 >= 0; i2--) {
            j2 = operation.invoke(Integer.valueOf(i2), Long.valueOf(jArr[i2]), Long.valueOf(j2)).longValue();
        }
        return Long.valueOf(j2);
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final <S, T extends S> S reduceRightIndexedOrNull(@NotNull T[] tArr, @NotNull Function3<? super Integer, ? super T, ? super S, ? extends S> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        lastIndex = getLastIndex(tArr);
        if (lastIndex < 0) {
            return null;
        }
        Object obj = (S) tArr[lastIndex];
        for (int i2 = lastIndex - 1; i2 >= 0; i2--) {
            obj = (S) operation.invoke(Integer.valueOf(i2), (Object) tArr[i2], obj);
        }
        return (S) obj;
    }

    @SinceKotlin(version = "1.4")
    @Nullable
    public static final Short reduceRightIndexedOrNull(@NotNull short[] sArr, @NotNull Function3<? super Integer, ? super Short, ? super Short, Short> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        lastIndex = getLastIndex(sArr);
        if (lastIndex < 0) {
            return null;
        }
        short s2 = sArr[lastIndex];
        for (int i2 = lastIndex - 1; i2 >= 0; i2--) {
            s2 = operation.invoke(Integer.valueOf(i2), Short.valueOf(sArr[i2]), Short.valueOf(s2)).shortValue();
        }
        return Short.valueOf(s2);
    }

    @SinceKotlin(version = "1.4")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @Nullable
    public static final Boolean reduceRightOrNull(@NotNull boolean[] zArr, @NotNull Function2<? super Boolean, ? super Boolean, Boolean> operation) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int lastIndex = getLastIndex(zArr);
        if (lastIndex < 0) {
            return null;
        }
        boolean z = zArr[lastIndex];
        for (int i2 = lastIndex - 1; i2 >= 0; i2--) {
            z = operation.invoke(Boolean.valueOf(zArr[i2]), Boolean.valueOf(z)).booleanValue();
        }
        return Boolean.valueOf(z);
    }

    @SinceKotlin(version = "1.4")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @Nullable
    public static final Byte reduceRightOrNull(@NotNull byte[] bArr, @NotNull Function2<? super Byte, ? super Byte, Byte> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        lastIndex = getLastIndex(bArr);
        if (lastIndex < 0) {
            return null;
        }
        byte b2 = bArr[lastIndex];
        for (int i2 = lastIndex - 1; i2 >= 0; i2--) {
            b2 = operation.invoke(Byte.valueOf(bArr[i2]), Byte.valueOf(b2)).byteValue();
        }
        return Byte.valueOf(b2);
    }

    @SinceKotlin(version = "1.4")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @Nullable
    public static final Character reduceRightOrNull(@NotNull char[] cArr, @NotNull Function2<? super Character, ? super Character, Character> operation) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int lastIndex = getLastIndex(cArr);
        if (lastIndex < 0) {
            return null;
        }
        char c2 = cArr[lastIndex];
        for (int i2 = lastIndex - 1; i2 >= 0; i2--) {
            c2 = operation.invoke(Character.valueOf(cArr[i2]), Character.valueOf(c2)).charValue();
        }
        return Character.valueOf(c2);
    }

    @SinceKotlin(version = "1.4")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @Nullable
    public static final Double reduceRightOrNull(@NotNull double[] dArr, @NotNull Function2<? super Double, ? super Double, Double> operation) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int lastIndex = getLastIndex(dArr);
        if (lastIndex < 0) {
            return null;
        }
        double d2 = dArr[lastIndex];
        for (int i2 = lastIndex - 1; i2 >= 0; i2--) {
            d2 = operation.invoke(Double.valueOf(dArr[i2]), Double.valueOf(d2)).doubleValue();
        }
        return Double.valueOf(d2);
    }

    @SinceKotlin(version = "1.4")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @Nullable
    public static final Float reduceRightOrNull(@NotNull float[] fArr, @NotNull Function2<? super Float, ? super Float, Float> operation) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        int lastIndex = getLastIndex(fArr);
        if (lastIndex < 0) {
            return null;
        }
        float f2 = fArr[lastIndex];
        for (int i2 = lastIndex - 1; i2 >= 0; i2--) {
            f2 = operation.invoke(Float.valueOf(fArr[i2]), Float.valueOf(f2)).floatValue();
        }
        return Float.valueOf(f2);
    }

    @SinceKotlin(version = "1.4")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @Nullable
    public static final Integer reduceRightOrNull(@NotNull int[] iArr, @NotNull Function2<? super Integer, ? super Integer, Integer> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        lastIndex = getLastIndex(iArr);
        if (lastIndex < 0) {
            return null;
        }
        int i2 = iArr[lastIndex];
        for (int i3 = lastIndex - 1; i3 >= 0; i3--) {
            i2 = operation.invoke(Integer.valueOf(iArr[i3]), Integer.valueOf(i2)).intValue();
        }
        return Integer.valueOf(i2);
    }

    @SinceKotlin(version = "1.4")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @Nullable
    public static final Long reduceRightOrNull(@NotNull long[] jArr, @NotNull Function2<? super Long, ? super Long, Long> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        lastIndex = getLastIndex(jArr);
        if (lastIndex < 0) {
            return null;
        }
        long j2 = jArr[lastIndex];
        for (int i2 = lastIndex - 1; i2 >= 0; i2--) {
            j2 = operation.invoke(Long.valueOf(jArr[i2]), Long.valueOf(j2)).longValue();
        }
        return Long.valueOf(j2);
    }

    @SinceKotlin(version = "1.4")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @Nullable
    public static final <S, T extends S> S reduceRightOrNull(@NotNull T[] tArr, @NotNull Function2<? super T, ? super S, ? extends S> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        lastIndex = getLastIndex(tArr);
        if (lastIndex < 0) {
            return null;
        }
        Object obj = (S) tArr[lastIndex];
        for (int i2 = lastIndex - 1; i2 >= 0; i2--) {
            obj = (S) operation.invoke((Object) tArr[i2], obj);
        }
        return (S) obj;
    }

    @SinceKotlin(version = "1.4")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @Nullable
    public static final Short reduceRightOrNull(@NotNull short[] sArr, @NotNull Function2<? super Short, ? super Short, Short> operation) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        lastIndex = getLastIndex(sArr);
        if (lastIndex < 0) {
            return null;
        }
        short s2 = sArr[lastIndex];
        for (int i2 = lastIndex - 1; i2 >= 0; i2--) {
            s2 = operation.invoke(Short.valueOf(sArr[i2]), Short.valueOf(s2)).shortValue();
        }
        return Short.valueOf(s2);
    }

    @NotNull
    public static final <T> T[] requireNoNulls(@NotNull T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        for (T t2 : tArr) {
            if (t2 == null) {
                throw new IllegalArgumentException("null element found in " + tArr + '.');
            }
        }
        return tArr;
    }

    public static void reverse(@NotNull byte[] bArr) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        int length = (bArr.length / 2) - 1;
        if (length < 0) {
            return;
        }
        lastIndex = getLastIndex(bArr);
        int i2 = 0;
        if (length < 0) {
            return;
        }
        while (true) {
            byte b2 = bArr[i2];
            bArr[i2] = bArr[lastIndex];
            bArr[lastIndex] = b2;
            lastIndex--;
            if (i2 == length) {
                return;
            }
            i2++;
        }
    }

    @SinceKotlin(version = "1.4")
    public static void reverse(@NotNull byte[] bArr, int i2, int i3) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(i2, i3, bArr.length);
        int i4 = (i2 + i3) / 2;
        if (i2 == i4) {
            return;
        }
        int i5 = i3 - 1;
        while (i2 < i4) {
            byte b2 = bArr[i2];
            bArr[i2] = bArr[i5];
            bArr[i5] = b2;
            i5--;
            i2++;
        }
    }

    public static final void reverse(@NotNull char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        int length = (cArr.length / 2) - 1;
        if (length < 0) {
            return;
        }
        int lastIndex = getLastIndex(cArr);
        int i2 = 0;
        if (length < 0) {
            return;
        }
        while (true) {
            char c2 = cArr[i2];
            cArr[i2] = cArr[lastIndex];
            cArr[lastIndex] = c2;
            lastIndex--;
            if (i2 == length) {
                return;
            }
            i2++;
        }
    }

    @SinceKotlin(version = "1.4")
    public static final void reverse(@NotNull char[] cArr, int i2, int i3) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(i2, i3, cArr.length);
        int i4 = (i2 + i3) / 2;
        if (i2 == i4) {
            return;
        }
        int i5 = i3 - 1;
        while (i2 < i4) {
            char c2 = cArr[i2];
            cArr[i2] = cArr[i5];
            cArr[i5] = c2;
            i5--;
            i2++;
        }
    }

    public static final void reverse(@NotNull double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        int length = (dArr.length / 2) - 1;
        if (length < 0) {
            return;
        }
        int lastIndex = getLastIndex(dArr);
        int i2 = 0;
        if (length < 0) {
            return;
        }
        while (true) {
            double d2 = dArr[i2];
            dArr[i2] = dArr[lastIndex];
            dArr[lastIndex] = d2;
            lastIndex--;
            if (i2 == length) {
                return;
            }
            i2++;
        }
    }

    @SinceKotlin(version = "1.4")
    public static final void reverse(@NotNull double[] dArr, int i2, int i3) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(i2, i3, dArr.length);
        int i4 = (i2 + i3) / 2;
        if (i2 == i4) {
            return;
        }
        int i5 = i3 - 1;
        while (i2 < i4) {
            double d2 = dArr[i2];
            dArr[i2] = dArr[i5];
            dArr[i5] = d2;
            i5--;
            i2++;
        }
    }

    public static final void reverse(@NotNull float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        int length = (fArr.length / 2) - 1;
        if (length < 0) {
            return;
        }
        int lastIndex = getLastIndex(fArr);
        int i2 = 0;
        if (length < 0) {
            return;
        }
        while (true) {
            float f2 = fArr[i2];
            fArr[i2] = fArr[lastIndex];
            fArr[lastIndex] = f2;
            lastIndex--;
            if (i2 == length) {
                return;
            }
            i2++;
        }
    }

    @SinceKotlin(version = "1.4")
    public static final void reverse(@NotNull float[] fArr, int i2, int i3) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(i2, i3, fArr.length);
        int i4 = (i2 + i3) / 2;
        if (i2 == i4) {
            return;
        }
        int i5 = i3 - 1;
        while (i2 < i4) {
            float f2 = fArr[i2];
            fArr[i2] = fArr[i5];
            fArr[i5] = f2;
            i5--;
            i2++;
        }
    }

    public static void reverse(@NotNull int[] iArr) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        int length = (iArr.length / 2) - 1;
        if (length < 0) {
            return;
        }
        lastIndex = getLastIndex(iArr);
        int i2 = 0;
        if (length < 0) {
            return;
        }
        while (true) {
            int i3 = iArr[i2];
            iArr[i2] = iArr[lastIndex];
            iArr[lastIndex] = i3;
            lastIndex--;
            if (i2 == length) {
                return;
            }
            i2++;
        }
    }

    @SinceKotlin(version = "1.4")
    public static void reverse(@NotNull int[] iArr, int i2, int i3) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(i2, i3, iArr.length);
        int i4 = (i2 + i3) / 2;
        if (i2 == i4) {
            return;
        }
        int i5 = i3 - 1;
        while (i2 < i4) {
            int i6 = iArr[i2];
            iArr[i2] = iArr[i5];
            iArr[i5] = i6;
            i5--;
            i2++;
        }
    }

    public static void reverse(@NotNull long[] jArr) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        int length = (jArr.length / 2) - 1;
        if (length < 0) {
            return;
        }
        lastIndex = getLastIndex(jArr);
        int i2 = 0;
        if (length < 0) {
            return;
        }
        while (true) {
            long j2 = jArr[i2];
            jArr[i2] = jArr[lastIndex];
            jArr[lastIndex] = j2;
            lastIndex--;
            if (i2 == length) {
                return;
            }
            i2++;
        }
    }

    @SinceKotlin(version = "1.4")
    public static void reverse(@NotNull long[] jArr, int i2, int i3) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(i2, i3, jArr.length);
        int i4 = (i2 + i3) / 2;
        if (i2 == i4) {
            return;
        }
        int i5 = i3 - 1;
        while (i2 < i4) {
            long j2 = jArr[i2];
            jArr[i2] = jArr[i5];
            jArr[i5] = j2;
            i5--;
            i2++;
        }
    }

    public static final <T> void reverse(@NotNull T[] tArr) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        int length = (tArr.length / 2) - 1;
        if (length < 0) {
            return;
        }
        lastIndex = getLastIndex(tArr);
        int i2 = 0;
        if (length < 0) {
            return;
        }
        while (true) {
            T t2 = tArr[i2];
            tArr[i2] = tArr[lastIndex];
            tArr[lastIndex] = t2;
            lastIndex--;
            if (i2 == length) {
                return;
            }
            i2++;
        }
    }

    @SinceKotlin(version = "1.4")
    public static final <T> void reverse(@NotNull T[] tArr, int i2, int i3) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(i2, i3, tArr.length);
        int i4 = (i2 + i3) / 2;
        if (i2 == i4) {
            return;
        }
        int i5 = i3 - 1;
        while (i2 < i4) {
            T t2 = tArr[i2];
            tArr[i2] = tArr[i5];
            tArr[i5] = t2;
            i5--;
            i2++;
        }
    }

    public static void reverse(@NotNull short[] sArr) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        int length = (sArr.length / 2) - 1;
        if (length < 0) {
            return;
        }
        lastIndex = getLastIndex(sArr);
        int i2 = 0;
        if (length < 0) {
            return;
        }
        while (true) {
            short s2 = sArr[i2];
            sArr[i2] = sArr[lastIndex];
            sArr[lastIndex] = s2;
            lastIndex--;
            if (i2 == length) {
                return;
            }
            i2++;
        }
    }

    @SinceKotlin(version = "1.4")
    public static void reverse(@NotNull short[] sArr, int i2, int i3) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(i2, i3, sArr.length);
        int i4 = (i2 + i3) / 2;
        if (i2 == i4) {
            return;
        }
        int i5 = i3 - 1;
        while (i2 < i4) {
            short s2 = sArr[i2];
            sArr[i2] = sArr[i5];
            sArr[i5] = s2;
            i5--;
            i2++;
        }
    }

    public static final void reverse(@NotNull boolean[] zArr) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        int length = (zArr.length / 2) - 1;
        if (length < 0) {
            return;
        }
        int lastIndex = getLastIndex(zArr);
        int i2 = 0;
        if (length < 0) {
            return;
        }
        while (true) {
            boolean z = zArr[i2];
            zArr[i2] = zArr[lastIndex];
            zArr[lastIndex] = z;
            lastIndex--;
            if (i2 == length) {
                return;
            }
            i2++;
        }
    }

    @SinceKotlin(version = "1.4")
    public static final void reverse(@NotNull boolean[] zArr, int i2, int i3) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(i2, i3, zArr.length);
        int i4 = (i2 + i3) / 2;
        if (i2 == i4) {
            return;
        }
        int i5 = i3 - 1;
        while (i2 < i4) {
            boolean z = zArr[i2];
            zArr[i2] = zArr[i5];
            zArr[i5] = z;
            i5--;
            i2++;
        }
    }

    @NotNull
    public static final List<Byte> reversed(@NotNull byte[] bArr) {
        List<Byte> emptyList;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        if (bArr.length == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        List<Byte> mutableList = toMutableList(bArr);
        CollectionsKt___CollectionsJvmKt.reverse(mutableList);
        return mutableList;
    }

    @NotNull
    public static final List<Character> reversed(@NotNull char[] cArr) {
        List<Character> emptyList;
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        if (cArr.length == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        List<Character> mutableList = toMutableList(cArr);
        CollectionsKt___CollectionsJvmKt.reverse(mutableList);
        return mutableList;
    }

    @NotNull
    public static final List<Double> reversed(@NotNull double[] dArr) {
        List<Double> emptyList;
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        if (dArr.length == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        List<Double> mutableList = toMutableList(dArr);
        CollectionsKt___CollectionsJvmKt.reverse(mutableList);
        return mutableList;
    }

    @NotNull
    public static final List<Float> reversed(@NotNull float[] fArr) {
        List<Float> emptyList;
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        if (fArr.length == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        List<Float> mutableList = toMutableList(fArr);
        CollectionsKt___CollectionsJvmKt.reverse(mutableList);
        return mutableList;
    }

    @NotNull
    public static final List<Integer> reversed(@NotNull int[] iArr) {
        List<Integer> emptyList;
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        if (iArr.length == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        List<Integer> mutableList = toMutableList(iArr);
        CollectionsKt___CollectionsJvmKt.reverse(mutableList);
        return mutableList;
    }

    @NotNull
    public static final List<Long> reversed(@NotNull long[] jArr) {
        List<Long> emptyList;
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        if (jArr.length == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        List<Long> mutableList = toMutableList(jArr);
        CollectionsKt___CollectionsJvmKt.reverse(mutableList);
        return mutableList;
    }

    @NotNull
    public static final <T> List<T> reversed(@NotNull T[] tArr) {
        List<T> mutableList;
        List<T> emptyList;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        if (tArr.length == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        mutableList = toMutableList(tArr);
        CollectionsKt___CollectionsJvmKt.reverse(mutableList);
        return mutableList;
    }

    @NotNull
    public static final List<Short> reversed(@NotNull short[] sArr) {
        List<Short> emptyList;
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        if (sArr.length == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        List<Short> mutableList = toMutableList(sArr);
        CollectionsKt___CollectionsJvmKt.reverse(mutableList);
        return mutableList;
    }

    @NotNull
    public static final List<Boolean> reversed(@NotNull boolean[] zArr) {
        List<Boolean> emptyList;
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        if (zArr.length == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        List<Boolean> mutableList = toMutableList(zArr);
        CollectionsKt___CollectionsJvmKt.reverse(mutableList);
        return mutableList;
    }

    @NotNull
    public static byte[] reversedArray(@NotNull byte[] bArr) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        int i2 = 0;
        if (bArr.length == 0) {
            return bArr;
        }
        byte[] bArr2 = new byte[bArr.length];
        lastIndex = getLastIndex(bArr);
        if (lastIndex >= 0) {
            while (true) {
                bArr2[lastIndex - i2] = bArr[i2];
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return bArr2;
    }

    @NotNull
    public static final char[] reversedArray(@NotNull char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        int i2 = 0;
        if (cArr.length == 0) {
            return cArr;
        }
        char[] cArr2 = new char[cArr.length];
        int lastIndex = getLastIndex(cArr);
        if (lastIndex >= 0) {
            while (true) {
                cArr2[lastIndex - i2] = cArr[i2];
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return cArr2;
    }

    @NotNull
    public static final double[] reversedArray(@NotNull double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        int i2 = 0;
        if (dArr.length == 0) {
            return dArr;
        }
        double[] dArr2 = new double[dArr.length];
        int lastIndex = getLastIndex(dArr);
        if (lastIndex >= 0) {
            while (true) {
                dArr2[lastIndex - i2] = dArr[i2];
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return dArr2;
    }

    @NotNull
    public static final float[] reversedArray(@NotNull float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        int i2 = 0;
        if (fArr.length == 0) {
            return fArr;
        }
        float[] fArr2 = new float[fArr.length];
        int lastIndex = getLastIndex(fArr);
        if (lastIndex >= 0) {
            while (true) {
                fArr2[lastIndex - i2] = fArr[i2];
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return fArr2;
    }

    @NotNull
    public static int[] reversedArray(@NotNull int[] iArr) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        int i2 = 0;
        if (iArr.length == 0) {
            return iArr;
        }
        int[] iArr2 = new int[iArr.length];
        lastIndex = getLastIndex(iArr);
        if (lastIndex >= 0) {
            while (true) {
                iArr2[lastIndex - i2] = iArr[i2];
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return iArr2;
    }

    @NotNull
    public static long[] reversedArray(@NotNull long[] jArr) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        int i2 = 0;
        if (jArr.length == 0) {
            return jArr;
        }
        long[] jArr2 = new long[jArr.length];
        lastIndex = getLastIndex(jArr);
        if (lastIndex >= 0) {
            while (true) {
                jArr2[lastIndex - i2] = jArr[i2];
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return jArr2;
    }

    @NotNull
    public static final <T> T[] reversedArray(@NotNull T[] tArr) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        int i2 = 0;
        if (tArr.length == 0) {
            return tArr;
        }
        T[] tArr2 = (T[]) ArraysKt__ArraysJVMKt.arrayOfNulls(tArr, tArr.length);
        lastIndex = getLastIndex(tArr);
        if (lastIndex >= 0) {
            while (true) {
                tArr2[lastIndex - i2] = tArr[i2];
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return tArr2;
    }

    @NotNull
    public static short[] reversedArray(@NotNull short[] sArr) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        int i2 = 0;
        if (sArr.length == 0) {
            return sArr;
        }
        short[] sArr2 = new short[sArr.length];
        lastIndex = getLastIndex(sArr);
        if (lastIndex >= 0) {
            while (true) {
                sArr2[lastIndex - i2] = sArr[i2];
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return sArr2;
    }

    @NotNull
    public static final boolean[] reversedArray(@NotNull boolean[] zArr) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        int i2 = 0;
        if (zArr.length == 0) {
            return zArr;
        }
        boolean[] zArr2 = new boolean[zArr.length];
        int lastIndex = getLastIndex(zArr);
        if (lastIndex >= 0) {
            while (true) {
                zArr2[lastIndex - i2] = zArr[i2];
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        }
        return zArr2;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final <R> List<R> runningFold(byte[] bArr, R r2, Function2<? super R, ? super Byte, ? extends R> operation) {
        List<R> listOf;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (bArr.length == 0) {
            listOf = CollectionsKt__CollectionsJVMKt.listOf(r2);
            return listOf;
        }
        ArrayList arrayList = new ArrayList(bArr.length + 1);
        arrayList.add(r2);
        for (byte b2 : bArr) {
            r2 = operation.invoke(r2, Byte.valueOf(b2));
            arrayList.add(r2);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final <R> List<R> runningFold(char[] cArr, R r2, Function2<? super R, ? super Character, ? extends R> operation) {
        List<R> listOf;
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (cArr.length == 0) {
            listOf = CollectionsKt__CollectionsJVMKt.listOf(r2);
            return listOf;
        }
        ArrayList arrayList = new ArrayList(cArr.length + 1);
        arrayList.add(r2);
        for (char c2 : cArr) {
            r2 = operation.invoke(r2, Character.valueOf(c2));
            arrayList.add(r2);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final <R> List<R> runningFold(double[] dArr, R r2, Function2<? super R, ? super Double, ? extends R> operation) {
        List<R> listOf;
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (dArr.length == 0) {
            listOf = CollectionsKt__CollectionsJVMKt.listOf(r2);
            return listOf;
        }
        ArrayList arrayList = new ArrayList(dArr.length + 1);
        arrayList.add(r2);
        for (double d2 : dArr) {
            r2 = operation.invoke(r2, Double.valueOf(d2));
            arrayList.add(r2);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final <R> List<R> runningFold(float[] fArr, R r2, Function2<? super R, ? super Float, ? extends R> operation) {
        List<R> listOf;
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (fArr.length == 0) {
            listOf = CollectionsKt__CollectionsJVMKt.listOf(r2);
            return listOf;
        }
        ArrayList arrayList = new ArrayList(fArr.length + 1);
        arrayList.add(r2);
        for (float f2 : fArr) {
            r2 = operation.invoke(r2, Float.valueOf(f2));
            arrayList.add(r2);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final <R> List<R> runningFold(int[] iArr, R r2, Function2<? super R, ? super Integer, ? extends R> operation) {
        List<R> listOf;
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (iArr.length == 0) {
            listOf = CollectionsKt__CollectionsJVMKt.listOf(r2);
            return listOf;
        }
        ArrayList arrayList = new ArrayList(iArr.length + 1);
        arrayList.add(r2);
        for (int i2 : iArr) {
            r2 = operation.invoke(r2, Integer.valueOf(i2));
            arrayList.add(r2);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final <R> List<R> runningFold(long[] jArr, R r2, Function2<? super R, ? super Long, ? extends R> operation) {
        List<R> listOf;
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (jArr.length == 0) {
            listOf = CollectionsKt__CollectionsJVMKt.listOf(r2);
            return listOf;
        }
        ArrayList arrayList = new ArrayList(jArr.length + 1);
        arrayList.add(r2);
        for (long j2 : jArr) {
            r2 = operation.invoke(r2, Long.valueOf(j2));
            arrayList.add(r2);
        }
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SinceKotlin(version = "1.4")
    @NotNull
    public static final <T, R> List<R> runningFold(@NotNull T[] tArr, R r2, @NotNull Function2<? super R, ? super T, ? extends R> operation) {
        List<R> listOf;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (tArr.length == 0) {
            listOf = CollectionsKt__CollectionsJVMKt.listOf(r2);
            return listOf;
        }
        ArrayList arrayList = new ArrayList(tArr.length + 1);
        arrayList.add(r2);
        for (R.animator animatorVar : tArr) {
            r2 = operation.invoke(r2, animatorVar);
            arrayList.add(r2);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final <R> List<R> runningFold(short[] sArr, R r2, Function2<? super R, ? super Short, ? extends R> operation) {
        List<R> listOf;
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (sArr.length == 0) {
            listOf = CollectionsKt__CollectionsJVMKt.listOf(r2);
            return listOf;
        }
        ArrayList arrayList = new ArrayList(sArr.length + 1);
        arrayList.add(r2);
        for (short s2 : sArr) {
            r2 = operation.invoke(r2, Short.valueOf(s2));
            arrayList.add(r2);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final <R> List<R> runningFold(boolean[] zArr, R r2, Function2<? super R, ? super Boolean, ? extends R> operation) {
        List<R> listOf;
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (zArr.length == 0) {
            listOf = CollectionsKt__CollectionsJVMKt.listOf(r2);
            return listOf;
        }
        ArrayList arrayList = new ArrayList(zArr.length + 1);
        arrayList.add(r2);
        for (boolean z : zArr) {
            r2 = operation.invoke(r2, Boolean.valueOf(z));
            arrayList.add(r2);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final <R> List<R> runningFoldIndexed(byte[] bArr, R r2, Function3<? super Integer, ? super R, ? super Byte, ? extends R> operation) {
        List<R> listOf;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (bArr.length == 0) {
            listOf = CollectionsKt__CollectionsJVMKt.listOf(r2);
            return listOf;
        }
        ArrayList arrayList = new ArrayList(bArr.length + 1);
        arrayList.add(r2);
        int length = bArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            r2 = operation.invoke(Integer.valueOf(i2), r2, Byte.valueOf(bArr[i2]));
            arrayList.add(r2);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final <R> List<R> runningFoldIndexed(char[] cArr, R r2, Function3<? super Integer, ? super R, ? super Character, ? extends R> operation) {
        List<R> listOf;
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (cArr.length == 0) {
            listOf = CollectionsKt__CollectionsJVMKt.listOf(r2);
            return listOf;
        }
        ArrayList arrayList = new ArrayList(cArr.length + 1);
        arrayList.add(r2);
        int length = cArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            r2 = operation.invoke(Integer.valueOf(i2), r2, Character.valueOf(cArr[i2]));
            arrayList.add(r2);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final <R> List<R> runningFoldIndexed(double[] dArr, R r2, Function3<? super Integer, ? super R, ? super Double, ? extends R> operation) {
        List<R> listOf;
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (dArr.length == 0) {
            listOf = CollectionsKt__CollectionsJVMKt.listOf(r2);
            return listOf;
        }
        ArrayList arrayList = new ArrayList(dArr.length + 1);
        arrayList.add(r2);
        int length = dArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            r2 = operation.invoke(Integer.valueOf(i2), r2, Double.valueOf(dArr[i2]));
            arrayList.add(r2);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final <R> List<R> runningFoldIndexed(float[] fArr, R r2, Function3<? super Integer, ? super R, ? super Float, ? extends R> operation) {
        List<R> listOf;
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (fArr.length == 0) {
            listOf = CollectionsKt__CollectionsJVMKt.listOf(r2);
            return listOf;
        }
        ArrayList arrayList = new ArrayList(fArr.length + 1);
        arrayList.add(r2);
        int length = fArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            r2 = operation.invoke(Integer.valueOf(i2), r2, Float.valueOf(fArr[i2]));
            arrayList.add(r2);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final <R> List<R> runningFoldIndexed(int[] iArr, R r2, Function3<? super Integer, ? super R, ? super Integer, ? extends R> operation) {
        List<R> listOf;
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (iArr.length == 0) {
            listOf = CollectionsKt__CollectionsJVMKt.listOf(r2);
            return listOf;
        }
        ArrayList arrayList = new ArrayList(iArr.length + 1);
        arrayList.add(r2);
        int length = iArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            r2 = operation.invoke(Integer.valueOf(i2), r2, Integer.valueOf(iArr[i2]));
            arrayList.add(r2);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final <R> List<R> runningFoldIndexed(long[] jArr, R r2, Function3<? super Integer, ? super R, ? super Long, ? extends R> operation) {
        List<R> listOf;
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (jArr.length == 0) {
            listOf = CollectionsKt__CollectionsJVMKt.listOf(r2);
            return listOf;
        }
        ArrayList arrayList = new ArrayList(jArr.length + 1);
        arrayList.add(r2);
        int length = jArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            r2 = operation.invoke(Integer.valueOf(i2), r2, Long.valueOf(jArr[i2]));
            arrayList.add(r2);
        }
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SinceKotlin(version = "1.4")
    @NotNull
    public static final <T, R> List<R> runningFoldIndexed(@NotNull T[] tArr, R r2, @NotNull Function3<? super Integer, ? super R, ? super T, ? extends R> operation) {
        List<R> listOf;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (tArr.length == 0) {
            listOf = CollectionsKt__CollectionsJVMKt.listOf(r2);
            return listOf;
        }
        ArrayList arrayList = new ArrayList(tArr.length + 1);
        arrayList.add(r2);
        int length = tArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            r2 = operation.invoke(Integer.valueOf(i2), r2, tArr[i2]);
            arrayList.add(r2);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final <R> List<R> runningFoldIndexed(short[] sArr, R r2, Function3<? super Integer, ? super R, ? super Short, ? extends R> operation) {
        List<R> listOf;
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (sArr.length == 0) {
            listOf = CollectionsKt__CollectionsJVMKt.listOf(r2);
            return listOf;
        }
        ArrayList arrayList = new ArrayList(sArr.length + 1);
        arrayList.add(r2);
        int length = sArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            r2 = operation.invoke(Integer.valueOf(i2), r2, Short.valueOf(sArr[i2]));
            arrayList.add(r2);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final <R> List<R> runningFoldIndexed(boolean[] zArr, R r2, Function3<? super Integer, ? super R, ? super Boolean, ? extends R> operation) {
        List<R> listOf;
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (zArr.length == 0) {
            listOf = CollectionsKt__CollectionsJVMKt.listOf(r2);
            return listOf;
        }
        ArrayList arrayList = new ArrayList(zArr.length + 1);
        arrayList.add(r2);
        int length = zArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            r2 = operation.invoke(Integer.valueOf(i2), r2, Boolean.valueOf(zArr[i2]));
            arrayList.add(r2);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final List<Byte> runningReduce(byte[] bArr, Function2<? super Byte, ? super Byte, Byte> operation) {
        List<Byte> emptyList;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (bArr.length == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        byte b2 = bArr[0];
        ArrayList arrayList = new ArrayList(bArr.length);
        arrayList.add(Byte.valueOf(b2));
        int length = bArr.length;
        for (int i2 = 1; i2 < length; i2++) {
            b2 = operation.invoke(Byte.valueOf(b2), Byte.valueOf(bArr[i2])).byteValue();
            arrayList.add(Byte.valueOf(b2));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final List<Character> runningReduce(char[] cArr, Function2<? super Character, ? super Character, Character> operation) {
        List<Character> emptyList;
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (cArr.length == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        char c2 = cArr[0];
        ArrayList arrayList = new ArrayList(cArr.length);
        arrayList.add(Character.valueOf(c2));
        int length = cArr.length;
        for (int i2 = 1; i2 < length; i2++) {
            c2 = operation.invoke(Character.valueOf(c2), Character.valueOf(cArr[i2])).charValue();
            arrayList.add(Character.valueOf(c2));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final List<Double> runningReduce(double[] dArr, Function2<? super Double, ? super Double, Double> operation) {
        List<Double> emptyList;
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (dArr.length == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        double d2 = dArr[0];
        ArrayList arrayList = new ArrayList(dArr.length);
        arrayList.add(Double.valueOf(d2));
        int length = dArr.length;
        for (int i2 = 1; i2 < length; i2++) {
            d2 = operation.invoke(Double.valueOf(d2), Double.valueOf(dArr[i2])).doubleValue();
            arrayList.add(Double.valueOf(d2));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final List<Float> runningReduce(float[] fArr, Function2<? super Float, ? super Float, Float> operation) {
        List<Float> emptyList;
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (fArr.length == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        float f2 = fArr[0];
        ArrayList arrayList = new ArrayList(fArr.length);
        arrayList.add(Float.valueOf(f2));
        int length = fArr.length;
        for (int i2 = 1; i2 < length; i2++) {
            f2 = operation.invoke(Float.valueOf(f2), Float.valueOf(fArr[i2])).floatValue();
            arrayList.add(Float.valueOf(f2));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final List<Integer> runningReduce(int[] iArr, Function2<? super Integer, ? super Integer, Integer> operation) {
        List<Integer> emptyList;
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (iArr.length == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        int i2 = iArr[0];
        ArrayList arrayList = new ArrayList(iArr.length);
        arrayList.add(Integer.valueOf(i2));
        int length = iArr.length;
        for (int i3 = 1; i3 < length; i3++) {
            i2 = operation.invoke(Integer.valueOf(i2), Integer.valueOf(iArr[i3])).intValue();
            arrayList.add(Integer.valueOf(i2));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final List<Long> runningReduce(long[] jArr, Function2<? super Long, ? super Long, Long> operation) {
        List<Long> emptyList;
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (jArr.length == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        long j2 = jArr[0];
        ArrayList arrayList = new ArrayList(jArr.length);
        arrayList.add(Long.valueOf(j2));
        int length = jArr.length;
        for (int i2 = 1; i2 < length; i2++) {
            j2 = operation.invoke(Long.valueOf(j2), Long.valueOf(jArr[i2])).longValue();
            arrayList.add(Long.valueOf(j2));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @NotNull
    public static final <S, T extends S> List<S> runningReduce(@NotNull T[] tArr, @NotNull Function2<? super S, ? super T, ? extends S> operation) {
        List<S> emptyList;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (tArr.length == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        S s2 = (Object) tArr[0];
        ArrayList arrayList = new ArrayList(tArr.length);
        arrayList.add(s2);
        int length = tArr.length;
        for (int i2 = 1; i2 < length; i2++) {
            s2 = operation.invoke(s2, (Object) tArr[i2]);
            arrayList.add(s2);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final List<Short> runningReduce(short[] sArr, Function2<? super Short, ? super Short, Short> operation) {
        List<Short> emptyList;
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (sArr.length == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        short s2 = sArr[0];
        ArrayList arrayList = new ArrayList(sArr.length);
        arrayList.add(Short.valueOf(s2));
        int length = sArr.length;
        for (int i2 = 1; i2 < length; i2++) {
            s2 = operation.invoke(Short.valueOf(s2), Short.valueOf(sArr[i2])).shortValue();
            arrayList.add(Short.valueOf(s2));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final List<Boolean> runningReduce(boolean[] zArr, Function2<? super Boolean, ? super Boolean, Boolean> operation) {
        List<Boolean> emptyList;
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (zArr.length == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        boolean z = zArr[0];
        ArrayList arrayList = new ArrayList(zArr.length);
        arrayList.add(Boolean.valueOf(z));
        int length = zArr.length;
        for (int i2 = 1; i2 < length; i2++) {
            z = operation.invoke(Boolean.valueOf(z), Boolean.valueOf(zArr[i2])).booleanValue();
            arrayList.add(Boolean.valueOf(z));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final List<Byte> runningReduceIndexed(byte[] bArr, Function3<? super Integer, ? super Byte, ? super Byte, Byte> operation) {
        List<Byte> emptyList;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (bArr.length == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        byte b2 = bArr[0];
        ArrayList arrayList = new ArrayList(bArr.length);
        arrayList.add(Byte.valueOf(b2));
        int length = bArr.length;
        for (int i2 = 1; i2 < length; i2++) {
            b2 = operation.invoke(Integer.valueOf(i2), Byte.valueOf(b2), Byte.valueOf(bArr[i2])).byteValue();
            arrayList.add(Byte.valueOf(b2));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final List<Character> runningReduceIndexed(char[] cArr, Function3<? super Integer, ? super Character, ? super Character, Character> operation) {
        List<Character> emptyList;
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (cArr.length == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        char c2 = cArr[0];
        ArrayList arrayList = new ArrayList(cArr.length);
        arrayList.add(Character.valueOf(c2));
        int length = cArr.length;
        for (int i2 = 1; i2 < length; i2++) {
            c2 = operation.invoke(Integer.valueOf(i2), Character.valueOf(c2), Character.valueOf(cArr[i2])).charValue();
            arrayList.add(Character.valueOf(c2));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final List<Double> runningReduceIndexed(double[] dArr, Function3<? super Integer, ? super Double, ? super Double, Double> operation) {
        List<Double> emptyList;
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (dArr.length == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        double d2 = dArr[0];
        ArrayList arrayList = new ArrayList(dArr.length);
        arrayList.add(Double.valueOf(d2));
        int length = dArr.length;
        for (int i2 = 1; i2 < length; i2++) {
            d2 = operation.invoke(Integer.valueOf(i2), Double.valueOf(d2), Double.valueOf(dArr[i2])).doubleValue();
            arrayList.add(Double.valueOf(d2));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final List<Float> runningReduceIndexed(float[] fArr, Function3<? super Integer, ? super Float, ? super Float, Float> operation) {
        List<Float> emptyList;
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (fArr.length == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        float f2 = fArr[0];
        ArrayList arrayList = new ArrayList(fArr.length);
        arrayList.add(Float.valueOf(f2));
        int length = fArr.length;
        for (int i2 = 1; i2 < length; i2++) {
            f2 = operation.invoke(Integer.valueOf(i2), Float.valueOf(f2), Float.valueOf(fArr[i2])).floatValue();
            arrayList.add(Float.valueOf(f2));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final List<Integer> runningReduceIndexed(int[] iArr, Function3<? super Integer, ? super Integer, ? super Integer, Integer> operation) {
        List<Integer> emptyList;
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (iArr.length == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        int i2 = iArr[0];
        ArrayList arrayList = new ArrayList(iArr.length);
        arrayList.add(Integer.valueOf(i2));
        int length = iArr.length;
        for (int i3 = 1; i3 < length; i3++) {
            i2 = operation.invoke(Integer.valueOf(i3), Integer.valueOf(i2), Integer.valueOf(iArr[i3])).intValue();
            arrayList.add(Integer.valueOf(i2));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final List<Long> runningReduceIndexed(long[] jArr, Function3<? super Integer, ? super Long, ? super Long, Long> operation) {
        List<Long> emptyList;
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (jArr.length == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        long j2 = jArr[0];
        ArrayList arrayList = new ArrayList(jArr.length);
        arrayList.add(Long.valueOf(j2));
        int length = jArr.length;
        for (int i2 = 1; i2 < length; i2++) {
            j2 = operation.invoke(Integer.valueOf(i2), Long.valueOf(j2), Long.valueOf(jArr[i2])).longValue();
            arrayList.add(Long.valueOf(j2));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @NotNull
    public static final <S, T extends S> List<S> runningReduceIndexed(@NotNull T[] tArr, @NotNull Function3<? super Integer, ? super S, ? super T, ? extends S> operation) {
        List<S> emptyList;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (tArr.length == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        S s2 = (Object) tArr[0];
        ArrayList arrayList = new ArrayList(tArr.length);
        arrayList.add(s2);
        int length = tArr.length;
        for (int i2 = 1; i2 < length; i2++) {
            s2 = operation.invoke(Integer.valueOf(i2), s2, (Object) tArr[i2]);
            arrayList.add(s2);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final List<Short> runningReduceIndexed(short[] sArr, Function3<? super Integer, ? super Short, ? super Short, Short> operation) {
        List<Short> emptyList;
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (sArr.length == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        short s2 = sArr[0];
        ArrayList arrayList = new ArrayList(sArr.length);
        arrayList.add(Short.valueOf(s2));
        int length = sArr.length;
        for (int i2 = 1; i2 < length; i2++) {
            s2 = operation.invoke(Integer.valueOf(i2), Short.valueOf(s2), Short.valueOf(sArr[i2])).shortValue();
            arrayList.add(Short.valueOf(s2));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final List<Boolean> runningReduceIndexed(boolean[] zArr, Function3<? super Integer, ? super Boolean, ? super Boolean, Boolean> operation) {
        List<Boolean> emptyList;
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (zArr.length == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        boolean z = zArr[0];
        ArrayList arrayList = new ArrayList(zArr.length);
        arrayList.add(Boolean.valueOf(z));
        int length = zArr.length;
        for (int i2 = 1; i2 < length; i2++) {
            z = operation.invoke(Integer.valueOf(i2), Boolean.valueOf(z), Boolean.valueOf(zArr[i2])).booleanValue();
            arrayList.add(Boolean.valueOf(z));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @InlineOnly
    private static final <R> List<R> scan(byte[] bArr, R r2, Function2<? super R, ? super Byte, ? extends R> operation) {
        List<R> listOf;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (bArr.length == 0) {
            listOf = CollectionsKt__CollectionsJVMKt.listOf(r2);
            return listOf;
        }
        ArrayList arrayList = new ArrayList(bArr.length + 1);
        arrayList.add(r2);
        for (byte b2 : bArr) {
            r2 = operation.invoke(r2, Byte.valueOf(b2));
            arrayList.add(r2);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @InlineOnly
    private static final <R> List<R> scan(char[] cArr, R r2, Function2<? super R, ? super Character, ? extends R> operation) {
        List<R> listOf;
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (cArr.length == 0) {
            listOf = CollectionsKt__CollectionsJVMKt.listOf(r2);
            return listOf;
        }
        ArrayList arrayList = new ArrayList(cArr.length + 1);
        arrayList.add(r2);
        for (char c2 : cArr) {
            r2 = operation.invoke(r2, Character.valueOf(c2));
            arrayList.add(r2);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @InlineOnly
    private static final <R> List<R> scan(double[] dArr, R r2, Function2<? super R, ? super Double, ? extends R> operation) {
        List<R> listOf;
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (dArr.length == 0) {
            listOf = CollectionsKt__CollectionsJVMKt.listOf(r2);
            return listOf;
        }
        ArrayList arrayList = new ArrayList(dArr.length + 1);
        arrayList.add(r2);
        for (double d2 : dArr) {
            r2 = operation.invoke(r2, Double.valueOf(d2));
            arrayList.add(r2);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @InlineOnly
    private static final <R> List<R> scan(float[] fArr, R r2, Function2<? super R, ? super Float, ? extends R> operation) {
        List<R> listOf;
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (fArr.length == 0) {
            listOf = CollectionsKt__CollectionsJVMKt.listOf(r2);
            return listOf;
        }
        ArrayList arrayList = new ArrayList(fArr.length + 1);
        arrayList.add(r2);
        for (float f2 : fArr) {
            r2 = operation.invoke(r2, Float.valueOf(f2));
            arrayList.add(r2);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @InlineOnly
    private static final <R> List<R> scan(int[] iArr, R r2, Function2<? super R, ? super Integer, ? extends R> operation) {
        List<R> listOf;
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (iArr.length == 0) {
            listOf = CollectionsKt__CollectionsJVMKt.listOf(r2);
            return listOf;
        }
        ArrayList arrayList = new ArrayList(iArr.length + 1);
        arrayList.add(r2);
        for (int i2 : iArr) {
            r2 = operation.invoke(r2, Integer.valueOf(i2));
            arrayList.add(r2);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @InlineOnly
    private static final <R> List<R> scan(long[] jArr, R r2, Function2<? super R, ? super Long, ? extends R> operation) {
        List<R> listOf;
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (jArr.length == 0) {
            listOf = CollectionsKt__CollectionsJVMKt.listOf(r2);
            return listOf;
        }
        ArrayList arrayList = new ArrayList(jArr.length + 1);
        arrayList.add(r2);
        for (long j2 : jArr) {
            r2 = operation.invoke(r2, Long.valueOf(j2));
            arrayList.add(r2);
        }
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SinceKotlin(version = "1.4")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @NotNull
    public static final <T, R> List<R> scan(@NotNull T[] tArr, R r2, @NotNull Function2<? super R, ? super T, ? extends R> operation) {
        List<R> listOf;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (tArr.length == 0) {
            listOf = CollectionsKt__CollectionsJVMKt.listOf(r2);
            return listOf;
        }
        ArrayList arrayList = new ArrayList(tArr.length + 1);
        arrayList.add(r2);
        for (R.animator animatorVar : tArr) {
            r2 = operation.invoke(r2, animatorVar);
            arrayList.add(r2);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @InlineOnly
    private static final <R> List<R> scan(short[] sArr, R r2, Function2<? super R, ? super Short, ? extends R> operation) {
        List<R> listOf;
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (sArr.length == 0) {
            listOf = CollectionsKt__CollectionsJVMKt.listOf(r2);
            return listOf;
        }
        ArrayList arrayList = new ArrayList(sArr.length + 1);
        arrayList.add(r2);
        for (short s2 : sArr) {
            r2 = operation.invoke(r2, Short.valueOf(s2));
            arrayList.add(r2);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @InlineOnly
    private static final <R> List<R> scan(boolean[] zArr, R r2, Function2<? super R, ? super Boolean, ? extends R> operation) {
        List<R> listOf;
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (zArr.length == 0) {
            listOf = CollectionsKt__CollectionsJVMKt.listOf(r2);
            return listOf;
        }
        ArrayList arrayList = new ArrayList(zArr.length + 1);
        arrayList.add(r2);
        for (boolean z : zArr) {
            r2 = operation.invoke(r2, Boolean.valueOf(z));
            arrayList.add(r2);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @InlineOnly
    private static final <R> List<R> scanIndexed(byte[] bArr, R r2, Function3<? super Integer, ? super R, ? super Byte, ? extends R> operation) {
        List<R> listOf;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (bArr.length == 0) {
            listOf = CollectionsKt__CollectionsJVMKt.listOf(r2);
            return listOf;
        }
        ArrayList arrayList = new ArrayList(bArr.length + 1);
        arrayList.add(r2);
        int length = bArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            r2 = operation.invoke(Integer.valueOf(i2), r2, Byte.valueOf(bArr[i2]));
            arrayList.add(r2);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @InlineOnly
    private static final <R> List<R> scanIndexed(char[] cArr, R r2, Function3<? super Integer, ? super R, ? super Character, ? extends R> operation) {
        List<R> listOf;
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (cArr.length == 0) {
            listOf = CollectionsKt__CollectionsJVMKt.listOf(r2);
            return listOf;
        }
        ArrayList arrayList = new ArrayList(cArr.length + 1);
        arrayList.add(r2);
        int length = cArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            r2 = operation.invoke(Integer.valueOf(i2), r2, Character.valueOf(cArr[i2]));
            arrayList.add(r2);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @InlineOnly
    private static final <R> List<R> scanIndexed(double[] dArr, R r2, Function3<? super Integer, ? super R, ? super Double, ? extends R> operation) {
        List<R> listOf;
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (dArr.length == 0) {
            listOf = CollectionsKt__CollectionsJVMKt.listOf(r2);
            return listOf;
        }
        ArrayList arrayList = new ArrayList(dArr.length + 1);
        arrayList.add(r2);
        int length = dArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            r2 = operation.invoke(Integer.valueOf(i2), r2, Double.valueOf(dArr[i2]));
            arrayList.add(r2);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @InlineOnly
    private static final <R> List<R> scanIndexed(float[] fArr, R r2, Function3<? super Integer, ? super R, ? super Float, ? extends R> operation) {
        List<R> listOf;
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (fArr.length == 0) {
            listOf = CollectionsKt__CollectionsJVMKt.listOf(r2);
            return listOf;
        }
        ArrayList arrayList = new ArrayList(fArr.length + 1);
        arrayList.add(r2);
        int length = fArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            r2 = operation.invoke(Integer.valueOf(i2), r2, Float.valueOf(fArr[i2]));
            arrayList.add(r2);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @InlineOnly
    private static final <R> List<R> scanIndexed(int[] iArr, R r2, Function3<? super Integer, ? super R, ? super Integer, ? extends R> operation) {
        List<R> listOf;
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (iArr.length == 0) {
            listOf = CollectionsKt__CollectionsJVMKt.listOf(r2);
            return listOf;
        }
        ArrayList arrayList = new ArrayList(iArr.length + 1);
        arrayList.add(r2);
        int length = iArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            r2 = operation.invoke(Integer.valueOf(i2), r2, Integer.valueOf(iArr[i2]));
            arrayList.add(r2);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @InlineOnly
    private static final <R> List<R> scanIndexed(long[] jArr, R r2, Function3<? super Integer, ? super R, ? super Long, ? extends R> operation) {
        List<R> listOf;
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (jArr.length == 0) {
            listOf = CollectionsKt__CollectionsJVMKt.listOf(r2);
            return listOf;
        }
        ArrayList arrayList = new ArrayList(jArr.length + 1);
        arrayList.add(r2);
        int length = jArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            r2 = operation.invoke(Integer.valueOf(i2), r2, Long.valueOf(jArr[i2]));
            arrayList.add(r2);
        }
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SinceKotlin(version = "1.4")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @NotNull
    public static final <T, R> List<R> scanIndexed(@NotNull T[] tArr, R r2, @NotNull Function3<? super Integer, ? super R, ? super T, ? extends R> operation) {
        List<R> listOf;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (tArr.length == 0) {
            listOf = CollectionsKt__CollectionsJVMKt.listOf(r2);
            return listOf;
        }
        ArrayList arrayList = new ArrayList(tArr.length + 1);
        arrayList.add(r2);
        int length = tArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            r2 = operation.invoke(Integer.valueOf(i2), r2, tArr[i2]);
            arrayList.add(r2);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @InlineOnly
    private static final <R> List<R> scanIndexed(short[] sArr, R r2, Function3<? super Integer, ? super R, ? super Short, ? extends R> operation) {
        List<R> listOf;
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (sArr.length == 0) {
            listOf = CollectionsKt__CollectionsJVMKt.listOf(r2);
            return listOf;
        }
        ArrayList arrayList = new ArrayList(sArr.length + 1);
        arrayList.add(r2);
        int length = sArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            r2 = operation.invoke(Integer.valueOf(i2), r2, Short.valueOf(sArr[i2]));
            arrayList.add(r2);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @InlineOnly
    private static final <R> List<R> scanIndexed(boolean[] zArr, R r2, Function3<? super Integer, ? super R, ? super Boolean, ? extends R> operation) {
        List<R> listOf;
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        if (zArr.length == 0) {
            listOf = CollectionsKt__CollectionsJVMKt.listOf(r2);
            return listOf;
        }
        ArrayList arrayList = new ArrayList(zArr.length + 1);
        arrayList.add(r2);
        int length = zArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            r2 = operation.invoke(Integer.valueOf(i2), r2, Boolean.valueOf(zArr[i2]));
            arrayList.add(r2);
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.4")
    public static final void shuffle(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        shuffle(bArr, (Random) Random.Default);
    }

    @SinceKotlin(version = "1.4")
    public static final void shuffle(@NotNull byte[] bArr, @NotNull Random random) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(random, "random");
        for (lastIndex = getLastIndex(bArr); lastIndex > 0; lastIndex--) {
            int nextInt = random.nextInt(lastIndex + 1);
            byte b2 = bArr[lastIndex];
            bArr[lastIndex] = bArr[nextInt];
            bArr[nextInt] = b2;
        }
    }

    @SinceKotlin(version = "1.4")
    public static final void shuffle(@NotNull char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        shuffle(cArr, (Random) Random.Default);
    }

    @SinceKotlin(version = "1.4")
    public static final void shuffle(@NotNull char[] cArr, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(random, "random");
        for (int lastIndex = getLastIndex(cArr); lastIndex > 0; lastIndex--) {
            int nextInt = random.nextInt(lastIndex + 1);
            char c2 = cArr[lastIndex];
            cArr[lastIndex] = cArr[nextInt];
            cArr[nextInt] = c2;
        }
    }

    @SinceKotlin(version = "1.4")
    public static final void shuffle(@NotNull double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        shuffle(dArr, Random.Default);
    }

    @SinceKotlin(version = "1.4")
    public static final void shuffle(@NotNull double[] dArr, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(random, "random");
        for (int lastIndex = getLastIndex(dArr); lastIndex > 0; lastIndex--) {
            int nextInt = random.nextInt(lastIndex + 1);
            double d2 = dArr[lastIndex];
            dArr[lastIndex] = dArr[nextInt];
            dArr[nextInt] = d2;
        }
    }

    @SinceKotlin(version = "1.4")
    public static final void shuffle(@NotNull float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        shuffle(fArr, (Random) Random.Default);
    }

    @SinceKotlin(version = "1.4")
    public static final void shuffle(@NotNull float[] fArr, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(random, "random");
        for (int lastIndex = getLastIndex(fArr); lastIndex > 0; lastIndex--) {
            int nextInt = random.nextInt(lastIndex + 1);
            float f2 = fArr[lastIndex];
            fArr[lastIndex] = fArr[nextInt];
            fArr[nextInt] = f2;
        }
    }

    @SinceKotlin(version = "1.4")
    public static final void shuffle(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        shuffle(iArr, (Random) Random.Default);
    }

    @SinceKotlin(version = "1.4")
    public static final void shuffle(@NotNull int[] iArr, @NotNull Random random) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(random, "random");
        for (lastIndex = getLastIndex(iArr); lastIndex > 0; lastIndex--) {
            int nextInt = random.nextInt(lastIndex + 1);
            int i2 = iArr[lastIndex];
            iArr[lastIndex] = iArr[nextInt];
            iArr[nextInt] = i2;
        }
    }

    @SinceKotlin(version = "1.4")
    public static final void shuffle(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        shuffle(jArr, (Random) Random.Default);
    }

    @SinceKotlin(version = "1.4")
    public static final void shuffle(@NotNull long[] jArr, @NotNull Random random) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(random, "random");
        for (lastIndex = getLastIndex(jArr); lastIndex > 0; lastIndex--) {
            int nextInt = random.nextInt(lastIndex + 1);
            long j2 = jArr[lastIndex];
            jArr[lastIndex] = jArr[nextInt];
            jArr[nextInt] = j2;
        }
    }

    @SinceKotlin(version = "1.4")
    public static final <T> void shuffle(@NotNull T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        shuffle(tArr, Random.Default);
    }

    @SinceKotlin(version = "1.4")
    public static final <T> void shuffle(@NotNull T[] tArr, @NotNull Random random) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(random, "random");
        for (lastIndex = getLastIndex(tArr); lastIndex > 0; lastIndex--) {
            int nextInt = random.nextInt(lastIndex + 1);
            T t2 = tArr[lastIndex];
            tArr[lastIndex] = tArr[nextInt];
            tArr[nextInt] = t2;
        }
    }

    @SinceKotlin(version = "1.4")
    public static final void shuffle(@NotNull short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        shuffle(sArr, (Random) Random.Default);
    }

    @SinceKotlin(version = "1.4")
    public static final void shuffle(@NotNull short[] sArr, @NotNull Random random) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(random, "random");
        for (lastIndex = getLastIndex(sArr); lastIndex > 0; lastIndex--) {
            int nextInt = random.nextInt(lastIndex + 1);
            short s2 = sArr[lastIndex];
            sArr[lastIndex] = sArr[nextInt];
            sArr[nextInt] = s2;
        }
    }

    @SinceKotlin(version = "1.4")
    public static final void shuffle(@NotNull boolean[] zArr) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        shuffle(zArr, Random.Default);
    }

    @SinceKotlin(version = "1.4")
    public static final void shuffle(@NotNull boolean[] zArr, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(random, "random");
        for (int lastIndex = getLastIndex(zArr); lastIndex > 0; lastIndex--) {
            int nextInt = random.nextInt(lastIndex + 1);
            boolean z = zArr[lastIndex];
            zArr[lastIndex] = zArr[nextInt];
            zArr[nextInt] = z;
        }
    }

    public static byte single(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        int length = bArr.length;
        if (length != 0) {
            if (length == 1) {
                return bArr[0];
            }
            throw new IllegalArgumentException("Array has more than one element.");
        }
        throw new NoSuchElementException("Array is empty.");
    }

    public static final byte single(@NotNull byte[] bArr, @NotNull Function1<? super Byte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        Byte b2 = null;
        boolean z = false;
        for (byte b3 : bArr) {
            if (predicate.invoke(Byte.valueOf(b3)).booleanValue()) {
                if (z) {
                    throw new IllegalArgumentException("Array contains more than one matching element.");
                }
                b2 = Byte.valueOf(b3);
                z = true;
            }
        }
        if (z) {
            Objects.requireNonNull(b2, "null cannot be cast to non-null type kotlin.Byte");
            return b2.byteValue();
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    public static char single(@NotNull char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        int length = cArr.length;
        if (length != 0) {
            if (length == 1) {
                return cArr[0];
            }
            throw new IllegalArgumentException("Array has more than one element.");
        }
        throw new NoSuchElementException("Array is empty.");
    }

    public static final char single(@NotNull char[] cArr, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        Character ch = null;
        boolean z = false;
        for (char c2 : cArr) {
            if (predicate.invoke(Character.valueOf(c2)).booleanValue()) {
                if (z) {
                    throw new IllegalArgumentException("Array contains more than one matching element.");
                }
                ch = Character.valueOf(c2);
                z = true;
            }
        }
        if (z) {
            Objects.requireNonNull(ch, "null cannot be cast to non-null type kotlin.Char");
            return ch.charValue();
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    public static final double single(@NotNull double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        int length = dArr.length;
        if (length != 0) {
            if (length == 1) {
                return dArr[0];
            }
            throw new IllegalArgumentException("Array has more than one element.");
        }
        throw new NoSuchElementException("Array is empty.");
    }

    public static final double single(@NotNull double[] dArr, @NotNull Function1<? super Double, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        Double d2 = null;
        boolean z = false;
        for (double d3 : dArr) {
            if (predicate.invoke(Double.valueOf(d3)).booleanValue()) {
                if (z) {
                    throw new IllegalArgumentException("Array contains more than one matching element.");
                }
                d2 = Double.valueOf(d3);
                z = true;
            }
        }
        if (z) {
            Objects.requireNonNull(d2, "null cannot be cast to non-null type kotlin.Double");
            return d2.doubleValue();
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    public static final float single(@NotNull float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        int length = fArr.length;
        if (length != 0) {
            if (length == 1) {
                return fArr[0];
            }
            throw new IllegalArgumentException("Array has more than one element.");
        }
        throw new NoSuchElementException("Array is empty.");
    }

    public static final float single(@NotNull float[] fArr, @NotNull Function1<? super Float, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        Float f2 = null;
        boolean z = false;
        for (float f3 : fArr) {
            if (predicate.invoke(Float.valueOf(f3)).booleanValue()) {
                if (z) {
                    throw new IllegalArgumentException("Array contains more than one matching element.");
                }
                f2 = Float.valueOf(f3);
                z = true;
            }
        }
        if (z) {
            Objects.requireNonNull(f2, "null cannot be cast to non-null type kotlin.Float");
            return f2.floatValue();
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    public static int single(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        int length = iArr.length;
        if (length != 0) {
            if (length == 1) {
                return iArr[0];
            }
            throw new IllegalArgumentException("Array has more than one element.");
        }
        throw new NoSuchElementException("Array is empty.");
    }

    public static final int single(@NotNull int[] iArr, @NotNull Function1<? super Integer, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        Integer num = null;
        boolean z = false;
        for (int i2 : iArr) {
            if (predicate.invoke(Integer.valueOf(i2)).booleanValue()) {
                if (z) {
                    throw new IllegalArgumentException("Array contains more than one matching element.");
                }
                num = Integer.valueOf(i2);
                z = true;
            }
        }
        if (z) {
            Objects.requireNonNull(num, "null cannot be cast to non-null type kotlin.Int");
            return num.intValue();
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    public static long single(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        int length = jArr.length;
        if (length != 0) {
            if (length == 1) {
                return jArr[0];
            }
            throw new IllegalArgumentException("Array has more than one element.");
        }
        throw new NoSuchElementException("Array is empty.");
    }

    public static final long single(@NotNull long[] jArr, @NotNull Function1<? super Long, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        Long l2 = null;
        boolean z = false;
        for (long j2 : jArr) {
            if (predicate.invoke(Long.valueOf(j2)).booleanValue()) {
                if (z) {
                    throw new IllegalArgumentException("Array contains more than one matching element.");
                }
                l2 = Long.valueOf(j2);
                z = true;
            }
        }
        if (z) {
            Objects.requireNonNull(l2, "null cannot be cast to non-null type kotlin.Long");
            return l2.longValue();
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    public static final <T> T single(@NotNull T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        int length = tArr.length;
        if (length != 0) {
            if (length == 1) {
                return tArr[0];
            }
            throw new IllegalArgumentException("Array has more than one element.");
        }
        throw new NoSuchElementException("Array is empty.");
    }

    public static final <T> T single(@NotNull T[] tArr, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        T t2 = null;
        boolean z = false;
        for (T t3 : tArr) {
            if (predicate.invoke(t3).booleanValue()) {
                if (z) {
                    throw new IllegalArgumentException("Array contains more than one matching element.");
                }
                z = true;
                t2 = t3;
            }
        }
        if (z) {
            return t2;
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    public static short single(@NotNull short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        int length = sArr.length;
        if (length != 0) {
            if (length == 1) {
                return sArr[0];
            }
            throw new IllegalArgumentException("Array has more than one element.");
        }
        throw new NoSuchElementException("Array is empty.");
    }

    public static final short single(@NotNull short[] sArr, @NotNull Function1<? super Short, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        Short sh = null;
        boolean z = false;
        for (short s2 : sArr) {
            if (predicate.invoke(Short.valueOf(s2)).booleanValue()) {
                if (z) {
                    throw new IllegalArgumentException("Array contains more than one matching element.");
                }
                sh = Short.valueOf(s2);
                z = true;
            }
        }
        if (z) {
            Objects.requireNonNull(sh, "null cannot be cast to non-null type kotlin.Short");
            return sh.shortValue();
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    public static final boolean single(@NotNull boolean[] zArr) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        int length = zArr.length;
        if (length != 0) {
            if (length == 1) {
                return zArr[0];
            }
            throw new IllegalArgumentException("Array has more than one element.");
        }
        throw new NoSuchElementException("Array is empty.");
    }

    public static final boolean single(@NotNull boolean[] zArr, @NotNull Function1<? super Boolean, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        Boolean bool = null;
        boolean z = false;
        for (boolean z2 : zArr) {
            if (predicate.invoke(Boolean.valueOf(z2)).booleanValue()) {
                if (z) {
                    throw new IllegalArgumentException("Array contains more than one matching element.");
                }
                bool = Boolean.valueOf(z2);
                z = true;
            }
        }
        if (z) {
            Objects.requireNonNull(bool, "null cannot be cast to non-null type kotlin.Boolean");
            return bool.booleanValue();
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    @Nullable
    public static final Boolean singleOrNull(@NotNull boolean[] zArr) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        if (zArr.length == 1) {
            return Boolean.valueOf(zArr[0]);
        }
        return null;
    }

    @Nullable
    public static final Boolean singleOrNull(@NotNull boolean[] zArr, @NotNull Function1<? super Boolean, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        boolean z = false;
        Boolean bool = null;
        for (boolean z2 : zArr) {
            if (predicate.invoke(Boolean.valueOf(z2)).booleanValue()) {
                if (z) {
                    return null;
                }
                bool = Boolean.valueOf(z2);
                z = true;
            }
        }
        if (z) {
            return bool;
        }
        return null;
    }

    @Nullable
    public static final Byte singleOrNull(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        if (bArr.length == 1) {
            return Byte.valueOf(bArr[0]);
        }
        return null;
    }

    @Nullable
    public static final Byte singleOrNull(@NotNull byte[] bArr, @NotNull Function1<? super Byte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        boolean z = false;
        Byte b2 = null;
        for (byte b3 : bArr) {
            if (predicate.invoke(Byte.valueOf(b3)).booleanValue()) {
                if (z) {
                    return null;
                }
                b2 = Byte.valueOf(b3);
                z = true;
            }
        }
        if (z) {
            return b2;
        }
        return null;
    }

    @Nullable
    public static final Character singleOrNull(@NotNull char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        if (cArr.length == 1) {
            return Character.valueOf(cArr[0]);
        }
        return null;
    }

    @Nullable
    public static final Character singleOrNull(@NotNull char[] cArr, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        boolean z = false;
        Character ch = null;
        for (char c2 : cArr) {
            if (predicate.invoke(Character.valueOf(c2)).booleanValue()) {
                if (z) {
                    return null;
                }
                ch = Character.valueOf(c2);
                z = true;
            }
        }
        if (z) {
            return ch;
        }
        return null;
    }

    @Nullable
    public static final Double singleOrNull(@NotNull double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        if (dArr.length == 1) {
            return Double.valueOf(dArr[0]);
        }
        return null;
    }

    @Nullable
    public static final Double singleOrNull(@NotNull double[] dArr, @NotNull Function1<? super Double, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        boolean z = false;
        Double d2 = null;
        for (double d3 : dArr) {
            if (predicate.invoke(Double.valueOf(d3)).booleanValue()) {
                if (z) {
                    return null;
                }
                d2 = Double.valueOf(d3);
                z = true;
            }
        }
        if (z) {
            return d2;
        }
        return null;
    }

    @Nullable
    public static final Float singleOrNull(@NotNull float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        if (fArr.length == 1) {
            return Float.valueOf(fArr[0]);
        }
        return null;
    }

    @Nullable
    public static final Float singleOrNull(@NotNull float[] fArr, @NotNull Function1<? super Float, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        boolean z = false;
        Float f2 = null;
        for (float f3 : fArr) {
            if (predicate.invoke(Float.valueOf(f3)).booleanValue()) {
                if (z) {
                    return null;
                }
                f2 = Float.valueOf(f3);
                z = true;
            }
        }
        if (z) {
            return f2;
        }
        return null;
    }

    @Nullable
    public static final Integer singleOrNull(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        if (iArr.length == 1) {
            return Integer.valueOf(iArr[0]);
        }
        return null;
    }

    @Nullable
    public static final Integer singleOrNull(@NotNull int[] iArr, @NotNull Function1<? super Integer, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        boolean z = false;
        Integer num = null;
        for (int i2 : iArr) {
            if (predicate.invoke(Integer.valueOf(i2)).booleanValue()) {
                if (z) {
                    return null;
                }
                num = Integer.valueOf(i2);
                z = true;
            }
        }
        if (z) {
            return num;
        }
        return null;
    }

    @Nullable
    public static final Long singleOrNull(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        if (jArr.length == 1) {
            return Long.valueOf(jArr[0]);
        }
        return null;
    }

    @Nullable
    public static final Long singleOrNull(@NotNull long[] jArr, @NotNull Function1<? super Long, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        boolean z = false;
        Long l2 = null;
        for (long j2 : jArr) {
            if (predicate.invoke(Long.valueOf(j2)).booleanValue()) {
                if (z) {
                    return null;
                }
                l2 = Long.valueOf(j2);
                z = true;
            }
        }
        if (z) {
            return l2;
        }
        return null;
    }

    @Nullable
    public static <T> T singleOrNull(@NotNull T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        if (tArr.length == 1) {
            return tArr[0];
        }
        return null;
    }

    @Nullable
    public static final <T> T singleOrNull(@NotNull T[] tArr, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        boolean z = false;
        T t2 = null;
        for (T t3 : tArr) {
            if (predicate.invoke(t3).booleanValue()) {
                if (z) {
                    return null;
                }
                z = true;
                t2 = t3;
            }
        }
        if (z) {
            return t2;
        }
        return null;
    }

    @Nullable
    public static final Short singleOrNull(@NotNull short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        if (sArr.length == 1) {
            return Short.valueOf(sArr[0]);
        }
        return null;
    }

    @Nullable
    public static final Short singleOrNull(@NotNull short[] sArr, @NotNull Function1<? super Short, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        boolean z = false;
        Short sh = null;
        for (short s2 : sArr) {
            if (predicate.invoke(Short.valueOf(s2)).booleanValue()) {
                if (z) {
                    return null;
                }
                sh = Short.valueOf(s2);
                z = true;
            }
        }
        if (z) {
            return sh;
        }
        return null;
    }

    @NotNull
    public static final List<Byte> slice(@NotNull byte[] bArr, @NotNull Iterable<Integer> indices) {
        int collectionSizeOrDefault;
        List<Byte> emptyList;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(indices, 10);
        if (collectionSizeOrDefault == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (Integer num : indices) {
            arrayList.add(Byte.valueOf(bArr[num.intValue()]));
        }
        return arrayList;
    }

    @NotNull
    public static final List<Byte> slice(@NotNull byte[] bArr, @NotNull IntRange indices) {
        byte[] copyOfRange;
        List<Byte> emptyList;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        if (indices.isEmpty()) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        copyOfRange = ArraysKt___ArraysJvmKt.copyOfRange(bArr, indices.getStart().intValue(), indices.getEndInclusive().intValue() + 1);
        return ArraysKt___ArraysJvmKt.asList(copyOfRange);
    }

    @NotNull
    public static final List<Character> slice(@NotNull char[] cArr, @NotNull Iterable<Integer> indices) {
        int collectionSizeOrDefault;
        List<Character> emptyList;
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(indices, 10);
        if (collectionSizeOrDefault == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (Integer num : indices) {
            arrayList.add(Character.valueOf(cArr[num.intValue()]));
        }
        return arrayList;
    }

    @NotNull
    public static final List<Character> slice(@NotNull char[] cArr, @NotNull IntRange indices) {
        List<Character> emptyList;
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        if (indices.isEmpty()) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        return ArraysKt___ArraysJvmKt.asList(ArraysKt___ArraysJvmKt.copyOfRange(cArr, indices.getStart().intValue(), indices.getEndInclusive().intValue() + 1));
    }

    @NotNull
    public static final List<Double> slice(@NotNull double[] dArr, @NotNull Iterable<Integer> indices) {
        int collectionSizeOrDefault;
        List<Double> emptyList;
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(indices, 10);
        if (collectionSizeOrDefault == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (Integer num : indices) {
            arrayList.add(Double.valueOf(dArr[num.intValue()]));
        }
        return arrayList;
    }

    @NotNull
    public static final List<Double> slice(@NotNull double[] dArr, @NotNull IntRange indices) {
        List<Double> asList;
        List<Double> emptyList;
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        if (indices.isEmpty()) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        asList = ArraysKt___ArraysJvmKt.asList(ArraysKt___ArraysJvmKt.copyOfRange(dArr, indices.getStart().intValue(), indices.getEndInclusive().intValue() + 1));
        return asList;
    }

    @NotNull
    public static final List<Float> slice(@NotNull float[] fArr, @NotNull Iterable<Integer> indices) {
        int collectionSizeOrDefault;
        List<Float> emptyList;
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(indices, 10);
        if (collectionSizeOrDefault == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (Integer num : indices) {
            arrayList.add(Float.valueOf(fArr[num.intValue()]));
        }
        return arrayList;
    }

    @NotNull
    public static final List<Float> slice(@NotNull float[] fArr, @NotNull IntRange indices) {
        List<Float> emptyList;
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        if (indices.isEmpty()) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        return ArraysKt___ArraysJvmKt.asList(ArraysKt___ArraysJvmKt.copyOfRange(fArr, indices.getStart().intValue(), indices.getEndInclusive().intValue() + 1));
    }

    @NotNull
    public static final List<Integer> slice(@NotNull int[] iArr, @NotNull Iterable<Integer> indices) {
        int collectionSizeOrDefault;
        List<Integer> emptyList;
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(indices, 10);
        if (collectionSizeOrDefault == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (Integer num : indices) {
            arrayList.add(Integer.valueOf(iArr[num.intValue()]));
        }
        return arrayList;
    }

    @NotNull
    public static final List<Integer> slice(@NotNull int[] iArr, @NotNull IntRange indices) {
        int[] copyOfRange;
        List<Integer> asList;
        List<Integer> emptyList;
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        if (indices.isEmpty()) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        copyOfRange = ArraysKt___ArraysJvmKt.copyOfRange(iArr, indices.getStart().intValue(), indices.getEndInclusive().intValue() + 1);
        asList = ArraysKt___ArraysJvmKt.asList(copyOfRange);
        return asList;
    }

    @NotNull
    public static final List<Long> slice(@NotNull long[] jArr, @NotNull Iterable<Integer> indices) {
        int collectionSizeOrDefault;
        List<Long> emptyList;
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(indices, 10);
        if (collectionSizeOrDefault == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (Integer num : indices) {
            arrayList.add(Long.valueOf(jArr[num.intValue()]));
        }
        return arrayList;
    }

    @NotNull
    public static final List<Long> slice(@NotNull long[] jArr, @NotNull IntRange indices) {
        long[] copyOfRange;
        List<Long> asList;
        List<Long> emptyList;
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        if (indices.isEmpty()) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        copyOfRange = ArraysKt___ArraysJvmKt.copyOfRange(jArr, indices.getStart().intValue(), indices.getEndInclusive().intValue() + 1);
        asList = ArraysKt___ArraysJvmKt.asList(copyOfRange);
        return asList;
    }

    @NotNull
    public static final <T> List<T> slice(@NotNull T[] tArr, @NotNull Iterable<Integer> indices) {
        int collectionSizeOrDefault;
        List<T> emptyList;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(indices, 10);
        if (collectionSizeOrDefault == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (Integer num : indices) {
            arrayList.add(tArr[num.intValue()]);
        }
        return arrayList;
    }

    @NotNull
    public static final <T> List<T> slice(@NotNull T[] tArr, @NotNull IntRange indices) {
        Object[] copyOfRange;
        List<T> asList;
        List<T> emptyList;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        if (indices.isEmpty()) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        copyOfRange = ArraysKt___ArraysJvmKt.copyOfRange(tArr, indices.getStart().intValue(), indices.getEndInclusive().intValue() + 1);
        asList = ArraysKt___ArraysJvmKt.asList(copyOfRange);
        return asList;
    }

    @NotNull
    public static final List<Short> slice(@NotNull short[] sArr, @NotNull Iterable<Integer> indices) {
        int collectionSizeOrDefault;
        List<Short> emptyList;
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(indices, 10);
        if (collectionSizeOrDefault == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (Integer num : indices) {
            arrayList.add(Short.valueOf(sArr[num.intValue()]));
        }
        return arrayList;
    }

    @NotNull
    public static final List<Short> slice(@NotNull short[] sArr, @NotNull IntRange indices) {
        short[] copyOfRange;
        List<Short> emptyList;
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        if (indices.isEmpty()) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        copyOfRange = ArraysKt___ArraysJvmKt.copyOfRange(sArr, indices.getStart().intValue(), indices.getEndInclusive().intValue() + 1);
        return ArraysKt___ArraysJvmKt.asList(copyOfRange);
    }

    @NotNull
    public static final List<Boolean> slice(@NotNull boolean[] zArr, @NotNull Iterable<Integer> indices) {
        int collectionSizeOrDefault;
        List<Boolean> emptyList;
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(indices, 10);
        if (collectionSizeOrDefault == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (Integer num : indices) {
            arrayList.add(Boolean.valueOf(zArr[num.intValue()]));
        }
        return arrayList;
    }

    @NotNull
    public static final List<Boolean> slice(@NotNull boolean[] zArr, @NotNull IntRange indices) {
        List<Boolean> emptyList;
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        if (indices.isEmpty()) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        return ArraysKt___ArraysJvmKt.asList(ArraysKt___ArraysJvmKt.copyOfRange(zArr, indices.getStart().intValue(), indices.getEndInclusive().intValue() + 1));
    }

    @NotNull
    public static byte[] sliceArray(@NotNull byte[] bArr, @NotNull Collection<Integer> indices) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        byte[] bArr2 = new byte[indices.size()];
        int i2 = 0;
        for (Integer num : indices) {
            bArr2[i2] = bArr[num.intValue()];
            i2++;
        }
        return bArr2;
    }

    @NotNull
    public static byte[] sliceArray(@NotNull byte[] bArr, @NotNull IntRange indices) {
        byte[] copyOfRange;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        if (indices.isEmpty()) {
            return new byte[0];
        }
        copyOfRange = ArraysKt___ArraysJvmKt.copyOfRange(bArr, indices.getStart().intValue(), indices.getEndInclusive().intValue() + 1);
        return copyOfRange;
    }

    @NotNull
    public static final char[] sliceArray(@NotNull char[] cArr, @NotNull Collection<Integer> indices) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        char[] cArr2 = new char[indices.size()];
        int i2 = 0;
        for (Integer num : indices) {
            cArr2[i2] = cArr[num.intValue()];
            i2++;
        }
        return cArr2;
    }

    @NotNull
    public static final char[] sliceArray(@NotNull char[] cArr, @NotNull IntRange indices) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        return indices.isEmpty() ? new char[0] : ArraysKt___ArraysJvmKt.copyOfRange(cArr, indices.getStart().intValue(), indices.getEndInclusive().intValue() + 1);
    }

    @NotNull
    public static final double[] sliceArray(@NotNull double[] dArr, @NotNull Collection<Integer> indices) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        double[] dArr2 = new double[indices.size()];
        int i2 = 0;
        for (Integer num : indices) {
            dArr2[i2] = dArr[num.intValue()];
            i2++;
        }
        return dArr2;
    }

    @NotNull
    public static final double[] sliceArray(@NotNull double[] dArr, @NotNull IntRange indices) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        return indices.isEmpty() ? new double[0] : ArraysKt___ArraysJvmKt.copyOfRange(dArr, indices.getStart().intValue(), indices.getEndInclusive().intValue() + 1);
    }

    @NotNull
    public static final float[] sliceArray(@NotNull float[] fArr, @NotNull Collection<Integer> indices) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        float[] fArr2 = new float[indices.size()];
        int i2 = 0;
        for (Integer num : indices) {
            fArr2[i2] = fArr[num.intValue()];
            i2++;
        }
        return fArr2;
    }

    @NotNull
    public static final float[] sliceArray(@NotNull float[] fArr, @NotNull IntRange indices) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        return indices.isEmpty() ? new float[0] : ArraysKt___ArraysJvmKt.copyOfRange(fArr, indices.getStart().intValue(), indices.getEndInclusive().intValue() + 1);
    }

    @NotNull
    public static int[] sliceArray(@NotNull int[] iArr, @NotNull Collection<Integer> indices) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        int[] iArr2 = new int[indices.size()];
        int i2 = 0;
        for (Integer num : indices) {
            iArr2[i2] = iArr[num.intValue()];
            i2++;
        }
        return iArr2;
    }

    @NotNull
    public static int[] sliceArray(@NotNull int[] iArr, @NotNull IntRange indices) {
        int[] copyOfRange;
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        if (indices.isEmpty()) {
            return new int[0];
        }
        copyOfRange = ArraysKt___ArraysJvmKt.copyOfRange(iArr, indices.getStart().intValue(), indices.getEndInclusive().intValue() + 1);
        return copyOfRange;
    }

    @NotNull
    public static long[] sliceArray(@NotNull long[] jArr, @NotNull Collection<Integer> indices) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        long[] jArr2 = new long[indices.size()];
        int i2 = 0;
        for (Integer num : indices) {
            jArr2[i2] = jArr[num.intValue()];
            i2++;
        }
        return jArr2;
    }

    @NotNull
    public static long[] sliceArray(@NotNull long[] jArr, @NotNull IntRange indices) {
        long[] copyOfRange;
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        if (indices.isEmpty()) {
            return new long[0];
        }
        copyOfRange = ArraysKt___ArraysJvmKt.copyOfRange(jArr, indices.getStart().intValue(), indices.getEndInclusive().intValue() + 1);
        return copyOfRange;
    }

    @NotNull
    public static final <T> T[] sliceArray(@NotNull T[] tArr, @NotNull Collection<Integer> indices) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        T[] tArr2 = (T[]) ArraysKt__ArraysJVMKt.arrayOfNulls(tArr, indices.size());
        int i2 = 0;
        for (Integer num : indices) {
            tArr2[i2] = tArr[num.intValue()];
            i2++;
        }
        return tArr2;
    }

    @NotNull
    public static final <T> T[] sliceArray(@NotNull T[] tArr, @NotNull IntRange indices) {
        Object[] copyOfRange;
        Object[] copyOfRange2;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        if (indices.isEmpty()) {
            copyOfRange2 = ArraysKt___ArraysJvmKt.copyOfRange(tArr, 0, 0);
            return (T[]) copyOfRange2;
        }
        copyOfRange = ArraysKt___ArraysJvmKt.copyOfRange(tArr, indices.getStart().intValue(), indices.getEndInclusive().intValue() + 1);
        return (T[]) copyOfRange;
    }

    @NotNull
    public static short[] sliceArray(@NotNull short[] sArr, @NotNull Collection<Integer> indices) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        short[] sArr2 = new short[indices.size()];
        int i2 = 0;
        for (Integer num : indices) {
            sArr2[i2] = sArr[num.intValue()];
            i2++;
        }
        return sArr2;
    }

    @NotNull
    public static short[] sliceArray(@NotNull short[] sArr, @NotNull IntRange indices) {
        short[] copyOfRange;
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        if (indices.isEmpty()) {
            return new short[0];
        }
        copyOfRange = ArraysKt___ArraysJvmKt.copyOfRange(sArr, indices.getStart().intValue(), indices.getEndInclusive().intValue() + 1);
        return copyOfRange;
    }

    @NotNull
    public static final boolean[] sliceArray(@NotNull boolean[] zArr, @NotNull Collection<Integer> indices) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        boolean[] zArr2 = new boolean[indices.size()];
        int i2 = 0;
        for (Integer num : indices) {
            zArr2[i2] = zArr[num.intValue()];
            i2++;
        }
        return zArr2;
    }

    @NotNull
    public static final boolean[] sliceArray(@NotNull boolean[] zArr, @NotNull IntRange indices) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        return indices.isEmpty() ? new boolean[0] : ArraysKt___ArraysJvmKt.copyOfRange(zArr, indices.getStart().intValue(), indices.getEndInclusive().intValue() + 1);
    }

    public static final <T, R extends Comparable<? super R>> void sortBy(@NotNull T[] tArr, @NotNull Function1<? super T, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (tArr.length > 1) {
            ArraysKt___ArraysJvmKt.sortWith(tArr, new ComparisonsKt__ComparisonsKt$compareBy$2(selector));
        }
    }

    public static final <T, R extends Comparable<? super R>> void sortByDescending(@NotNull T[] tArr, @NotNull Function1<? super T, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (tArr.length > 1) {
            ArraysKt___ArraysJvmKt.sortWith(tArr, new ComparisonsKt__ComparisonsKt$compareByDescending$1(selector));
        }
    }

    public static final void sortDescending(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        if (bArr.length > 1) {
            ArraysKt___ArraysJvmKt.sort(bArr);
            reverse(bArr);
        }
    }

    @SinceKotlin(version = "1.4")
    public static final void sortDescending(@NotNull byte[] bArr, int i2, int i3) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        ArraysKt___ArraysJvmKt.sort(bArr, i2, i3);
        reverse(bArr, i2, i3);
    }

    public static final void sortDescending(@NotNull char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        if (cArr.length > 1) {
            ArraysKt___ArraysJvmKt.sort(cArr);
            reverse(cArr);
        }
    }

    @SinceKotlin(version = "1.4")
    public static final void sortDescending(@NotNull char[] cArr, int i2, int i3) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        ArraysKt___ArraysJvmKt.sort(cArr, i2, i3);
        reverse(cArr, i2, i3);
    }

    public static final void sortDescending(@NotNull double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        if (dArr.length > 1) {
            ArraysKt___ArraysJvmKt.sort(dArr);
            reverse(dArr);
        }
    }

    @SinceKotlin(version = "1.4")
    public static final void sortDescending(@NotNull double[] dArr, int i2, int i3) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        ArraysKt___ArraysJvmKt.sort(dArr, i2, i3);
        reverse(dArr, i2, i3);
    }

    public static final void sortDescending(@NotNull float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        if (fArr.length > 1) {
            ArraysKt___ArraysJvmKt.sort(fArr);
            reverse(fArr);
        }
    }

    @SinceKotlin(version = "1.4")
    public static final void sortDescending(@NotNull float[] fArr, int i2, int i3) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        ArraysKt___ArraysJvmKt.sort(fArr, i2, i3);
        reverse(fArr, i2, i3);
    }

    public static final void sortDescending(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        if (iArr.length > 1) {
            ArraysKt___ArraysJvmKt.sort(iArr);
            reverse(iArr);
        }
    }

    @SinceKotlin(version = "1.4")
    public static final void sortDescending(@NotNull int[] iArr, int i2, int i3) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        ArraysKt___ArraysJvmKt.sort(iArr, i2, i3);
        reverse(iArr, i2, i3);
    }

    public static final void sortDescending(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        if (jArr.length > 1) {
            ArraysKt___ArraysJvmKt.sort(jArr);
            reverse(jArr);
        }
    }

    @SinceKotlin(version = "1.4")
    public static final void sortDescending(@NotNull long[] jArr, int i2, int i3) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        ArraysKt___ArraysJvmKt.sort(jArr, i2, i3);
        reverse(jArr, i2, i3);
    }

    public static final <T extends Comparable<? super T>> void sortDescending(@NotNull T[] tArr) {
        Comparator reverseOrder;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        reverseOrder = ComparisonsKt__ComparisonsKt.reverseOrder();
        ArraysKt___ArraysJvmKt.sortWith(tArr, reverseOrder);
    }

    @SinceKotlin(version = "1.4")
    public static final <T extends Comparable<? super T>> void sortDescending(@NotNull T[] tArr, int i2, int i3) {
        Comparator reverseOrder;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        reverseOrder = ComparisonsKt__ComparisonsKt.reverseOrder();
        ArraysKt___ArraysJvmKt.sortWith(tArr, reverseOrder, i2, i3);
    }

    public static final void sortDescending(@NotNull short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        if (sArr.length > 1) {
            ArraysKt___ArraysJvmKt.sort(sArr);
            reverse(sArr);
        }
    }

    @SinceKotlin(version = "1.4")
    public static final void sortDescending(@NotNull short[] sArr, int i2, int i3) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        ArraysKt___ArraysJvmKt.sort(sArr, i2, i3);
        reverse(sArr, i2, i3);
    }

    @NotNull
    public static final List<Byte> sorted(@NotNull byte[] bArr) {
        List<Byte> asList;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Byte[] typedArray = ArraysKt___ArraysJvmKt.toTypedArray(bArr);
        ArraysKt___ArraysJvmKt.sort((Object[]) typedArray);
        asList = ArraysKt___ArraysJvmKt.asList(typedArray);
        return asList;
    }

    @NotNull
    public static final List<Character> sorted(@NotNull char[] cArr) {
        List<Character> asList;
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Character[] typedArray = ArraysKt___ArraysJvmKt.toTypedArray(cArr);
        ArraysKt___ArraysJvmKt.sort((Object[]) typedArray);
        asList = ArraysKt___ArraysJvmKt.asList(typedArray);
        return asList;
    }

    @NotNull
    public static final List<Double> sorted(@NotNull double[] dArr) {
        List<Double> asList;
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Double[] typedArray = ArraysKt___ArraysJvmKt.toTypedArray(dArr);
        ArraysKt___ArraysJvmKt.sort((Object[]) typedArray);
        asList = ArraysKt___ArraysJvmKt.asList(typedArray);
        return asList;
    }

    @NotNull
    public static final List<Float> sorted(@NotNull float[] fArr) {
        List<Float> asList;
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Float[] typedArray = ArraysKt___ArraysJvmKt.toTypedArray(fArr);
        ArraysKt___ArraysJvmKt.sort((Object[]) typedArray);
        asList = ArraysKt___ArraysJvmKt.asList(typedArray);
        return asList;
    }

    @NotNull
    public static final List<Integer> sorted(@NotNull int[] iArr) {
        List<Integer> asList;
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Integer[] typedArray = ArraysKt___ArraysJvmKt.toTypedArray(iArr);
        ArraysKt___ArraysJvmKt.sort((Object[]) typedArray);
        asList = ArraysKt___ArraysJvmKt.asList(typedArray);
        return asList;
    }

    @NotNull
    public static final List<Long> sorted(@NotNull long[] jArr) {
        List<Long> asList;
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Long[] typedArray = ArraysKt___ArraysJvmKt.toTypedArray(jArr);
        ArraysKt___ArraysJvmKt.sort((Object[]) typedArray);
        asList = ArraysKt___ArraysJvmKt.asList(typedArray);
        return asList;
    }

    @NotNull
    public static final <T extends Comparable<? super T>> List<T> sorted(@NotNull T[] tArr) {
        List<T> asList;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        asList = ArraysKt___ArraysJvmKt.asList(sortedArray(tArr));
        return asList;
    }

    @NotNull
    public static final List<Short> sorted(@NotNull short[] sArr) {
        List<Short> asList;
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Short[] typedArray = ArraysKt___ArraysJvmKt.toTypedArray(sArr);
        ArraysKt___ArraysJvmKt.sort((Object[]) typedArray);
        asList = ArraysKt___ArraysJvmKt.asList(typedArray);
        return asList;
    }

    @NotNull
    public static final byte[] sortedArray(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        if (bArr.length == 0) {
            return bArr;
        }
        byte[] copyOf = Arrays.copyOf(bArr, bArr.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
        ArraysKt___ArraysJvmKt.sort(copyOf);
        return copyOf;
    }

    @NotNull
    public static final char[] sortedArray(@NotNull char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        if (cArr.length == 0) {
            return cArr;
        }
        char[] copyOf = Arrays.copyOf(cArr, cArr.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
        ArraysKt___ArraysJvmKt.sort(copyOf);
        return copyOf;
    }

    @NotNull
    public static final double[] sortedArray(@NotNull double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        if (dArr.length == 0) {
            return dArr;
        }
        double[] copyOf = Arrays.copyOf(dArr, dArr.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
        ArraysKt___ArraysJvmKt.sort(copyOf);
        return copyOf;
    }

    @NotNull
    public static final float[] sortedArray(@NotNull float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        if (fArr.length == 0) {
            return fArr;
        }
        float[] copyOf = Arrays.copyOf(fArr, fArr.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
        ArraysKt___ArraysJvmKt.sort(copyOf);
        return copyOf;
    }

    @NotNull
    public static final int[] sortedArray(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        if (iArr.length == 0) {
            return iArr;
        }
        int[] copyOf = Arrays.copyOf(iArr, iArr.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
        ArraysKt___ArraysJvmKt.sort(copyOf);
        return copyOf;
    }

    @NotNull
    public static final long[] sortedArray(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        if (jArr.length == 0) {
            return jArr;
        }
        long[] copyOf = Arrays.copyOf(jArr, jArr.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
        ArraysKt___ArraysJvmKt.sort(copyOf);
        return copyOf;
    }

    @NotNull
    public static final <T extends Comparable<? super T>> T[] sortedArray(@NotNull T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        if (tArr.length == 0) {
            return tArr;
        }
        Object[] copyOf = Arrays.copyOf(tArr, tArr.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
        T[] tArr2 = (T[]) ((Comparable[]) copyOf);
        ArraysKt___ArraysJvmKt.sort((Object[]) tArr2);
        return tArr2;
    }

    @NotNull
    public static final short[] sortedArray(@NotNull short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        if (sArr.length == 0) {
            return sArr;
        }
        short[] copyOf = Arrays.copyOf(sArr, sArr.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
        ArraysKt___ArraysJvmKt.sort(copyOf);
        return copyOf;
    }

    @NotNull
    public static final byte[] sortedArrayDescending(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        if (bArr.length == 0) {
            return bArr;
        }
        byte[] copyOf = Arrays.copyOf(bArr, bArr.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
        sortDescending(copyOf);
        return copyOf;
    }

    @NotNull
    public static final char[] sortedArrayDescending(@NotNull char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        if (cArr.length == 0) {
            return cArr;
        }
        char[] copyOf = Arrays.copyOf(cArr, cArr.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
        sortDescending(copyOf);
        return copyOf;
    }

    @NotNull
    public static final double[] sortedArrayDescending(@NotNull double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        if (dArr.length == 0) {
            return dArr;
        }
        double[] copyOf = Arrays.copyOf(dArr, dArr.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
        sortDescending(copyOf);
        return copyOf;
    }

    @NotNull
    public static final float[] sortedArrayDescending(@NotNull float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        if (fArr.length == 0) {
            return fArr;
        }
        float[] copyOf = Arrays.copyOf(fArr, fArr.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
        sortDescending(copyOf);
        return copyOf;
    }

    @NotNull
    public static final int[] sortedArrayDescending(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        if (iArr.length == 0) {
            return iArr;
        }
        int[] copyOf = Arrays.copyOf(iArr, iArr.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
        sortDescending(copyOf);
        return copyOf;
    }

    @NotNull
    public static final long[] sortedArrayDescending(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        if (jArr.length == 0) {
            return jArr;
        }
        long[] copyOf = Arrays.copyOf(jArr, jArr.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
        sortDescending(copyOf);
        return copyOf;
    }

    @NotNull
    public static final <T extends Comparable<? super T>> T[] sortedArrayDescending(@NotNull T[] tArr) {
        Comparator reverseOrder;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        if (tArr.length == 0) {
            return tArr;
        }
        Object[] copyOf = Arrays.copyOf(tArr, tArr.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
        T[] tArr2 = (T[]) ((Comparable[]) copyOf);
        reverseOrder = ComparisonsKt__ComparisonsKt.reverseOrder();
        ArraysKt___ArraysJvmKt.sortWith(tArr2, reverseOrder);
        return tArr2;
    }

    @NotNull
    public static final short[] sortedArrayDescending(@NotNull short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        if (sArr.length == 0) {
            return sArr;
        }
        short[] copyOf = Arrays.copyOf(sArr, sArr.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
        sortDescending(copyOf);
        return copyOf;
    }

    @NotNull
    public static final <T> T[] sortedArrayWith(@NotNull T[] tArr, @NotNull Comparator<? super T> comparator) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (tArr.length == 0) {
            return tArr;
        }
        T[] tArr2 = (T[]) Arrays.copyOf(tArr, tArr.length);
        Intrinsics.checkNotNullExpressionValue(tArr2, "copyOf(this, size)");
        ArraysKt___ArraysJvmKt.sortWith(tArr2, comparator);
        return tArr2;
    }

    @NotNull
    public static final <R extends Comparable<? super R>> List<Byte> sortedBy(@NotNull byte[] bArr, @NotNull Function1<? super Byte, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        return sortedWith(bArr, (Comparator<? super Byte>) new ComparisonsKt__ComparisonsKt$compareBy$2(selector));
    }

    @NotNull
    public static final <R extends Comparable<? super R>> List<Character> sortedBy(@NotNull char[] cArr, @NotNull Function1<? super Character, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        return sortedWith(cArr, (Comparator<? super Character>) new ComparisonsKt__ComparisonsKt$compareBy$2(selector));
    }

    @NotNull
    public static final <R extends Comparable<? super R>> List<Double> sortedBy(@NotNull double[] dArr, @NotNull Function1<? super Double, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        return sortedWith(dArr, new ComparisonsKt__ComparisonsKt$compareBy$2(selector));
    }

    @NotNull
    public static final <R extends Comparable<? super R>> List<Float> sortedBy(@NotNull float[] fArr, @NotNull Function1<? super Float, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        return sortedWith(fArr, (Comparator<? super Float>) new ComparisonsKt__ComparisonsKt$compareBy$2(selector));
    }

    @NotNull
    public static final <R extends Comparable<? super R>> List<Integer> sortedBy(@NotNull int[] iArr, @NotNull Function1<? super Integer, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        return sortedWith(iArr, (Comparator<? super Integer>) new ComparisonsKt__ComparisonsKt$compareBy$2(selector));
    }

    @NotNull
    public static final <R extends Comparable<? super R>> List<Long> sortedBy(@NotNull long[] jArr, @NotNull Function1<? super Long, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        return sortedWith(jArr, (Comparator<? super Long>) new ComparisonsKt__ComparisonsKt$compareBy$2(selector));
    }

    @NotNull
    public static final <T, R extends Comparable<? super R>> List<T> sortedBy(@NotNull T[] tArr, @NotNull Function1<? super T, ? extends R> selector) {
        List<T> sortedWith;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        sortedWith = sortedWith(tArr, new ComparisonsKt__ComparisonsKt$compareBy$2(selector));
        return sortedWith;
    }

    @NotNull
    public static final <R extends Comparable<? super R>> List<Short> sortedBy(@NotNull short[] sArr, @NotNull Function1<? super Short, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        return sortedWith(sArr, (Comparator<? super Short>) new ComparisonsKt__ComparisonsKt$compareBy$2(selector));
    }

    @NotNull
    public static final <R extends Comparable<? super R>> List<Boolean> sortedBy(@NotNull boolean[] zArr, @NotNull Function1<? super Boolean, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        return sortedWith(zArr, new ComparisonsKt__ComparisonsKt$compareBy$2(selector));
    }

    @NotNull
    public static final <R extends Comparable<? super R>> List<Byte> sortedByDescending(@NotNull byte[] bArr, @NotNull Function1<? super Byte, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        return sortedWith(bArr, (Comparator<? super Byte>) new ComparisonsKt__ComparisonsKt$compareByDescending$1(selector));
    }

    @NotNull
    public static final <R extends Comparable<? super R>> List<Character> sortedByDescending(@NotNull char[] cArr, @NotNull Function1<? super Character, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        return sortedWith(cArr, (Comparator<? super Character>) new ComparisonsKt__ComparisonsKt$compareByDescending$1(selector));
    }

    @NotNull
    public static final <R extends Comparable<? super R>> List<Double> sortedByDescending(@NotNull double[] dArr, @NotNull Function1<? super Double, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        return sortedWith(dArr, new ComparisonsKt__ComparisonsKt$compareByDescending$1(selector));
    }

    @NotNull
    public static final <R extends Comparable<? super R>> List<Float> sortedByDescending(@NotNull float[] fArr, @NotNull Function1<? super Float, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        return sortedWith(fArr, (Comparator<? super Float>) new ComparisonsKt__ComparisonsKt$compareByDescending$1(selector));
    }

    @NotNull
    public static final <R extends Comparable<? super R>> List<Integer> sortedByDescending(@NotNull int[] iArr, @NotNull Function1<? super Integer, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        return sortedWith(iArr, (Comparator<? super Integer>) new ComparisonsKt__ComparisonsKt$compareByDescending$1(selector));
    }

    @NotNull
    public static final <R extends Comparable<? super R>> List<Long> sortedByDescending(@NotNull long[] jArr, @NotNull Function1<? super Long, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        return sortedWith(jArr, (Comparator<? super Long>) new ComparisonsKt__ComparisonsKt$compareByDescending$1(selector));
    }

    @NotNull
    public static final <T, R extends Comparable<? super R>> List<T> sortedByDescending(@NotNull T[] tArr, @NotNull Function1<? super T, ? extends R> selector) {
        List<T> sortedWith;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        sortedWith = sortedWith(tArr, new ComparisonsKt__ComparisonsKt$compareByDescending$1(selector));
        return sortedWith;
    }

    @NotNull
    public static final <R extends Comparable<? super R>> List<Short> sortedByDescending(@NotNull short[] sArr, @NotNull Function1<? super Short, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        return sortedWith(sArr, (Comparator<? super Short>) new ComparisonsKt__ComparisonsKt$compareByDescending$1(selector));
    }

    @NotNull
    public static final <R extends Comparable<? super R>> List<Boolean> sortedByDescending(@NotNull boolean[] zArr, @NotNull Function1<? super Boolean, ? extends R> selector) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        return sortedWith(zArr, new ComparisonsKt__ComparisonsKt$compareByDescending$1(selector));
    }

    @NotNull
    public static final List<Byte> sortedDescending(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        byte[] copyOf = Arrays.copyOf(bArr, bArr.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
        ArraysKt___ArraysJvmKt.sort(copyOf);
        return reversed(copyOf);
    }

    @NotNull
    public static final List<Character> sortedDescending(@NotNull char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        char[] copyOf = Arrays.copyOf(cArr, cArr.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
        ArraysKt___ArraysJvmKt.sort(copyOf);
        return reversed(copyOf);
    }

    @NotNull
    public static final List<Double> sortedDescending(@NotNull double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        double[] copyOf = Arrays.copyOf(dArr, dArr.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
        ArraysKt___ArraysJvmKt.sort(copyOf);
        return reversed(copyOf);
    }

    @NotNull
    public static final List<Float> sortedDescending(@NotNull float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        float[] copyOf = Arrays.copyOf(fArr, fArr.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
        ArraysKt___ArraysJvmKt.sort(copyOf);
        return reversed(copyOf);
    }

    @NotNull
    public static final List<Integer> sortedDescending(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        int[] copyOf = Arrays.copyOf(iArr, iArr.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
        ArraysKt___ArraysJvmKt.sort(copyOf);
        return reversed(copyOf);
    }

    @NotNull
    public static final List<Long> sortedDescending(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        long[] copyOf = Arrays.copyOf(jArr, jArr.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
        ArraysKt___ArraysJvmKt.sort(copyOf);
        return reversed(copyOf);
    }

    @NotNull
    public static final <T extends Comparable<? super T>> List<T> sortedDescending(@NotNull T[] tArr) {
        Comparator reverseOrder;
        List<T> sortedWith;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        reverseOrder = ComparisonsKt__ComparisonsKt.reverseOrder();
        sortedWith = sortedWith(tArr, reverseOrder);
        return sortedWith;
    }

    @NotNull
    public static final List<Short> sortedDescending(@NotNull short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        short[] copyOf = Arrays.copyOf(sArr, sArr.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
        ArraysKt___ArraysJvmKt.sort(copyOf);
        return reversed(copyOf);
    }

    @NotNull
    public static final List<Byte> sortedWith(@NotNull byte[] bArr, @NotNull Comparator<? super Byte> comparator) {
        List<Byte> asList;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Byte[] typedArray = ArraysKt___ArraysJvmKt.toTypedArray(bArr);
        ArraysKt___ArraysJvmKt.sortWith(typedArray, comparator);
        asList = ArraysKt___ArraysJvmKt.asList(typedArray);
        return asList;
    }

    @NotNull
    public static final List<Character> sortedWith(@NotNull char[] cArr, @NotNull Comparator<? super Character> comparator) {
        List<Character> asList;
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Character[] typedArray = ArraysKt___ArraysJvmKt.toTypedArray(cArr);
        ArraysKt___ArraysJvmKt.sortWith(typedArray, comparator);
        asList = ArraysKt___ArraysJvmKt.asList(typedArray);
        return asList;
    }

    @NotNull
    public static final List<Double> sortedWith(@NotNull double[] dArr, @NotNull Comparator<? super Double> comparator) {
        List<Double> asList;
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Double[] typedArray = ArraysKt___ArraysJvmKt.toTypedArray(dArr);
        ArraysKt___ArraysJvmKt.sortWith(typedArray, comparator);
        asList = ArraysKt___ArraysJvmKt.asList(typedArray);
        return asList;
    }

    @NotNull
    public static final List<Float> sortedWith(@NotNull float[] fArr, @NotNull Comparator<? super Float> comparator) {
        List<Float> asList;
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Float[] typedArray = ArraysKt___ArraysJvmKt.toTypedArray(fArr);
        ArraysKt___ArraysJvmKt.sortWith(typedArray, comparator);
        asList = ArraysKt___ArraysJvmKt.asList(typedArray);
        return asList;
    }

    @NotNull
    public static final List<Integer> sortedWith(@NotNull int[] iArr, @NotNull Comparator<? super Integer> comparator) {
        List<Integer> asList;
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Integer[] typedArray = ArraysKt___ArraysJvmKt.toTypedArray(iArr);
        ArraysKt___ArraysJvmKt.sortWith(typedArray, comparator);
        asList = ArraysKt___ArraysJvmKt.asList(typedArray);
        return asList;
    }

    @NotNull
    public static final List<Long> sortedWith(@NotNull long[] jArr, @NotNull Comparator<? super Long> comparator) {
        List<Long> asList;
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Long[] typedArray = ArraysKt___ArraysJvmKt.toTypedArray(jArr);
        ArraysKt___ArraysJvmKt.sortWith(typedArray, comparator);
        asList = ArraysKt___ArraysJvmKt.asList(typedArray);
        return asList;
    }

    @NotNull
    public static <T> List<T> sortedWith(@NotNull T[] tArr, @NotNull Comparator<? super T> comparator) {
        List<T> asList;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        asList = ArraysKt___ArraysJvmKt.asList(sortedArrayWith(tArr, comparator));
        return asList;
    }

    @NotNull
    public static final List<Short> sortedWith(@NotNull short[] sArr, @NotNull Comparator<? super Short> comparator) {
        List<Short> asList;
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Short[] typedArray = ArraysKt___ArraysJvmKt.toTypedArray(sArr);
        ArraysKt___ArraysJvmKt.sortWith(typedArray, comparator);
        asList = ArraysKt___ArraysJvmKt.asList(typedArray);
        return asList;
    }

    @NotNull
    public static final List<Boolean> sortedWith(@NotNull boolean[] zArr, @NotNull Comparator<? super Boolean> comparator) {
        List<Boolean> asList;
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Boolean[] typedArray = ArraysKt___ArraysJvmKt.toTypedArray(zArr);
        ArraysKt___ArraysJvmKt.sortWith(typedArray, comparator);
        asList = ArraysKt___ArraysJvmKt.asList(typedArray);
        return asList;
    }

    @NotNull
    public static final Set<Byte> subtract(@NotNull byte[] bArr, @NotNull Iterable<Byte> other) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Set<Byte> mutableSet = toMutableSet(bArr);
        CollectionsKt__MutableCollectionsKt.removeAll(mutableSet, other);
        return mutableSet;
    }

    @NotNull
    public static final Set<Character> subtract(@NotNull char[] cArr, @NotNull Iterable<Character> other) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Set<Character> mutableSet = toMutableSet(cArr);
        CollectionsKt__MutableCollectionsKt.removeAll(mutableSet, other);
        return mutableSet;
    }

    @NotNull
    public static final Set<Double> subtract(@NotNull double[] dArr, @NotNull Iterable<Double> other) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Set<Double> mutableSet = toMutableSet(dArr);
        CollectionsKt__MutableCollectionsKt.removeAll(mutableSet, other);
        return mutableSet;
    }

    @NotNull
    public static final Set<Float> subtract(@NotNull float[] fArr, @NotNull Iterable<Float> other) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Set<Float> mutableSet = toMutableSet(fArr);
        CollectionsKt__MutableCollectionsKt.removeAll(mutableSet, other);
        return mutableSet;
    }

    @NotNull
    public static final Set<Integer> subtract(@NotNull int[] iArr, @NotNull Iterable<Integer> other) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Set<Integer> mutableSet = toMutableSet(iArr);
        CollectionsKt__MutableCollectionsKt.removeAll(mutableSet, other);
        return mutableSet;
    }

    @NotNull
    public static final Set<Long> subtract(@NotNull long[] jArr, @NotNull Iterable<Long> other) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Set<Long> mutableSet = toMutableSet(jArr);
        CollectionsKt__MutableCollectionsKt.removeAll(mutableSet, other);
        return mutableSet;
    }

    @NotNull
    public static final <T> Set<T> subtract(@NotNull T[] tArr, @NotNull Iterable<? extends T> other) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Set<T> mutableSet = toMutableSet(tArr);
        CollectionsKt__MutableCollectionsKt.removeAll(mutableSet, other);
        return mutableSet;
    }

    @NotNull
    public static final Set<Short> subtract(@NotNull short[] sArr, @NotNull Iterable<Short> other) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Set<Short> mutableSet = toMutableSet(sArr);
        CollectionsKt__MutableCollectionsKt.removeAll(mutableSet, other);
        return mutableSet;
    }

    @NotNull
    public static final Set<Boolean> subtract(@NotNull boolean[] zArr, @NotNull Iterable<Boolean> other) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Set<Boolean> mutableSet = toMutableSet(zArr);
        CollectionsKt__MutableCollectionsKt.removeAll(mutableSet, other);
        return mutableSet;
    }

    public static final double sum(@NotNull double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        double d2 = 0.0d;
        for (double d3 : dArr) {
            d2 += d3;
        }
        return d2;
    }

    public static final float sum(@NotNull float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        float f2 = 0.0f;
        for (float f3 : fArr) {
            f2 += f3;
        }
        return f2;
    }

    public static final int sum(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        int i2 = 0;
        for (byte b2 : bArr) {
            i2 += b2;
        }
        return i2;
    }

    public static int sum(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        int i2 = 0;
        for (int i3 : iArr) {
            i2 += i3;
        }
        return i2;
    }

    public static final int sum(@NotNull short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        int i2 = 0;
        for (short s2 : sArr) {
            i2 += s2;
        }
        return i2;
    }

    public static long sum(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        long j2 = 0;
        for (long j3 : jArr) {
            j2 += j3;
        }
        return j2;
    }

    @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = {}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static final int sumBy(@NotNull byte[] bArr, @NotNull Function1<? super Byte, Integer> selector) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 0;
        for (byte b2 : bArr) {
            i2 += selector.invoke(Byte.valueOf(b2)).intValue();
        }
        return i2;
    }

    @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = {}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static final int sumBy(@NotNull char[] cArr, @NotNull Function1<? super Character, Integer> selector) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 0;
        for (char c2 : cArr) {
            i2 += selector.invoke(Character.valueOf(c2)).intValue();
        }
        return i2;
    }

    @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = {}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static final int sumBy(@NotNull double[] dArr, @NotNull Function1<? super Double, Integer> selector) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 0;
        for (double d2 : dArr) {
            i2 += selector.invoke(Double.valueOf(d2)).intValue();
        }
        return i2;
    }

    @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = {}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static final int sumBy(@NotNull float[] fArr, @NotNull Function1<? super Float, Integer> selector) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 0;
        for (float f2 : fArr) {
            i2 += selector.invoke(Float.valueOf(f2)).intValue();
        }
        return i2;
    }

    @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = {}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static final int sumBy(@NotNull int[] iArr, @NotNull Function1<? super Integer, Integer> selector) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 0;
        for (int i3 : iArr) {
            i2 += selector.invoke(Integer.valueOf(i3)).intValue();
        }
        return i2;
    }

    @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = {}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static final int sumBy(@NotNull long[] jArr, @NotNull Function1<? super Long, Integer> selector) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 0;
        for (long j2 : jArr) {
            i2 += selector.invoke(Long.valueOf(j2)).intValue();
        }
        return i2;
    }

    @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = {}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static final <T> int sumBy(@NotNull T[] tArr, @NotNull Function1<? super T, Integer> selector) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 0;
        for (T t2 : tArr) {
            i2 += selector.invoke(t2).intValue();
        }
        return i2;
    }

    @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = {}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static final int sumBy(@NotNull short[] sArr, @NotNull Function1<? super Short, Integer> selector) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 0;
        for (short s2 : sArr) {
            i2 += selector.invoke(Short.valueOf(s2)).intValue();
        }
        return i2;
    }

    @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = {}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static final int sumBy(@NotNull boolean[] zArr, @NotNull Function1<? super Boolean, Integer> selector) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 0;
        for (boolean z : zArr) {
            i2 += selector.invoke(Boolean.valueOf(z)).intValue();
        }
        return i2;
    }

    @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = {}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static final double sumByDouble(@NotNull byte[] bArr, @NotNull Function1<? super Byte, Double> selector) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        double d2 = 0.0d;
        for (byte b2 : bArr) {
            d2 += selector.invoke(Byte.valueOf(b2)).doubleValue();
        }
        return d2;
    }

    @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = {}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static final double sumByDouble(@NotNull char[] cArr, @NotNull Function1<? super Character, Double> selector) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        double d2 = 0.0d;
        for (char c2 : cArr) {
            d2 += selector.invoke(Character.valueOf(c2)).doubleValue();
        }
        return d2;
    }

    @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = {}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static final double sumByDouble(@NotNull double[] dArr, @NotNull Function1<? super Double, Double> selector) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        double d2 = 0.0d;
        for (double d3 : dArr) {
            d2 += selector.invoke(Double.valueOf(d3)).doubleValue();
        }
        return d2;
    }

    @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = {}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static final double sumByDouble(@NotNull float[] fArr, @NotNull Function1<? super Float, Double> selector) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        double d2 = 0.0d;
        for (float f2 : fArr) {
            d2 += selector.invoke(Float.valueOf(f2)).doubleValue();
        }
        return d2;
    }

    @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = {}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static final double sumByDouble(@NotNull int[] iArr, @NotNull Function1<? super Integer, Double> selector) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        double d2 = 0.0d;
        for (int i2 : iArr) {
            d2 += selector.invoke(Integer.valueOf(i2)).doubleValue();
        }
        return d2;
    }

    @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = {}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static final double sumByDouble(@NotNull long[] jArr, @NotNull Function1<? super Long, Double> selector) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        double d2 = 0.0d;
        for (long j2 : jArr) {
            d2 += selector.invoke(Long.valueOf(j2)).doubleValue();
        }
        return d2;
    }

    @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = {}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static final <T> double sumByDouble(@NotNull T[] tArr, @NotNull Function1<? super T, Double> selector) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        double d2 = 0.0d;
        for (T t2 : tArr) {
            d2 += selector.invoke(t2).doubleValue();
        }
        return d2;
    }

    @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = {}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static final double sumByDouble(@NotNull short[] sArr, @NotNull Function1<? super Short, Double> selector) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        double d2 = 0.0d;
        for (short s2 : sArr) {
            d2 += selector.invoke(Short.valueOf(s2)).doubleValue();
        }
        return d2;
    }

    @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = {}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static final double sumByDouble(@NotNull boolean[] zArr, @NotNull Function1<? super Boolean, Double> selector) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        double d2 = 0.0d;
        for (boolean z : zArr) {
            d2 += selector.invoke(Boolean.valueOf(z)).doubleValue();
        }
        return d2;
    }

    @JvmName(name = "sumOfByte")
    public static final int sumOfByte(@NotNull Byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        int i2 = 0;
        for (Byte b2 : bArr) {
            i2 += b2.byteValue();
        }
        return i2;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfDouble")
    @OverloadResolutionByLambdaReturnType
    private static final double sumOfDouble(byte[] bArr, Function1<? super Byte, Double> selector) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        double d2 = 0.0d;
        for (byte b2 : bArr) {
            d2 += selector.invoke(Byte.valueOf(b2)).doubleValue();
        }
        return d2;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfDouble")
    @OverloadResolutionByLambdaReturnType
    private static final double sumOfDouble(char[] cArr, Function1<? super Character, Double> selector) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        double d2 = 0.0d;
        for (char c2 : cArr) {
            d2 += selector.invoke(Character.valueOf(c2)).doubleValue();
        }
        return d2;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfDouble")
    @OverloadResolutionByLambdaReturnType
    private static final double sumOfDouble(double[] dArr, Function1<? super Double, Double> selector) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        double d2 = 0.0d;
        for (double d3 : dArr) {
            d2 += selector.invoke(Double.valueOf(d3)).doubleValue();
        }
        return d2;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfDouble")
    @OverloadResolutionByLambdaReturnType
    private static final double sumOfDouble(float[] fArr, Function1<? super Float, Double> selector) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        double d2 = 0.0d;
        for (float f2 : fArr) {
            d2 += selector.invoke(Float.valueOf(f2)).doubleValue();
        }
        return d2;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfDouble")
    @OverloadResolutionByLambdaReturnType
    private static final double sumOfDouble(int[] iArr, Function1<? super Integer, Double> selector) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        double d2 = 0.0d;
        for (int i2 : iArr) {
            d2 += selector.invoke(Integer.valueOf(i2)).doubleValue();
        }
        return d2;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfDouble")
    @OverloadResolutionByLambdaReturnType
    private static final double sumOfDouble(long[] jArr, Function1<? super Long, Double> selector) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        double d2 = 0.0d;
        for (long j2 : jArr) {
            d2 += selector.invoke(Long.valueOf(j2)).doubleValue();
        }
        return d2;
    }

    @JvmName(name = "sumOfDouble")
    public static final double sumOfDouble(@NotNull Double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        double d2 = 0.0d;
        for (Double d3 : dArr) {
            d2 += d3.doubleValue();
        }
        return d2;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfDouble")
    @OverloadResolutionByLambdaReturnType
    private static final <T> double sumOfDouble(T[] tArr, Function1<? super T, Double> selector) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        double d2 = 0.0d;
        for (T t2 : tArr) {
            d2 += selector.invoke(t2).doubleValue();
        }
        return d2;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfDouble")
    @OverloadResolutionByLambdaReturnType
    private static final double sumOfDouble(short[] sArr, Function1<? super Short, Double> selector) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        double d2 = 0.0d;
        for (short s2 : sArr) {
            d2 += selector.invoke(Short.valueOf(s2)).doubleValue();
        }
        return d2;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfDouble")
    @OverloadResolutionByLambdaReturnType
    private static final double sumOfDouble(boolean[] zArr, Function1<? super Boolean, Double> selector) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        double d2 = 0.0d;
        for (boolean z : zArr) {
            d2 += selector.invoke(Boolean.valueOf(z)).doubleValue();
        }
        return d2;
    }

    @JvmName(name = "sumOfFloat")
    public static final float sumOfFloat(@NotNull Float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        float f2 = 0.0f;
        for (Float f3 : fArr) {
            f2 += f3.floatValue();
        }
        return f2;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfInt")
    @OverloadResolutionByLambdaReturnType
    private static final int sumOfInt(byte[] bArr, Function1<? super Byte, Integer> selector) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 0;
        for (byte b2 : bArr) {
            i2 += selector.invoke(Byte.valueOf(b2)).intValue();
        }
        return i2;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfInt")
    @OverloadResolutionByLambdaReturnType
    private static final int sumOfInt(char[] cArr, Function1<? super Character, Integer> selector) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 0;
        for (char c2 : cArr) {
            i2 += selector.invoke(Character.valueOf(c2)).intValue();
        }
        return i2;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfInt")
    @OverloadResolutionByLambdaReturnType
    private static final int sumOfInt(double[] dArr, Function1<? super Double, Integer> selector) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 0;
        for (double d2 : dArr) {
            i2 += selector.invoke(Double.valueOf(d2)).intValue();
        }
        return i2;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfInt")
    @OverloadResolutionByLambdaReturnType
    private static final int sumOfInt(float[] fArr, Function1<? super Float, Integer> selector) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 0;
        for (float f2 : fArr) {
            i2 += selector.invoke(Float.valueOf(f2)).intValue();
        }
        return i2;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfInt")
    @OverloadResolutionByLambdaReturnType
    private static final int sumOfInt(int[] iArr, Function1<? super Integer, Integer> selector) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 0;
        for (int i3 : iArr) {
            i2 += selector.invoke(Integer.valueOf(i3)).intValue();
        }
        return i2;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfInt")
    @OverloadResolutionByLambdaReturnType
    private static final int sumOfInt(long[] jArr, Function1<? super Long, Integer> selector) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 0;
        for (long j2 : jArr) {
            i2 += selector.invoke(Long.valueOf(j2)).intValue();
        }
        return i2;
    }

    @JvmName(name = "sumOfInt")
    public static final int sumOfInt(@NotNull Integer[] numArr) {
        Intrinsics.checkNotNullParameter(numArr, "<this>");
        int i2 = 0;
        for (Integer num : numArr) {
            i2 += num.intValue();
        }
        return i2;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfInt")
    @OverloadResolutionByLambdaReturnType
    private static final <T> int sumOfInt(T[] tArr, Function1<? super T, Integer> selector) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 0;
        for (T t2 : tArr) {
            i2 += selector.invoke(t2).intValue();
        }
        return i2;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfInt")
    @OverloadResolutionByLambdaReturnType
    private static final int sumOfInt(short[] sArr, Function1<? super Short, Integer> selector) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 0;
        for (short s2 : sArr) {
            i2 += selector.invoke(Short.valueOf(s2)).intValue();
        }
        return i2;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfInt")
    @OverloadResolutionByLambdaReturnType
    private static final int sumOfInt(boolean[] zArr, Function1<? super Boolean, Integer> selector) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int i2 = 0;
        for (boolean z : zArr) {
            i2 += selector.invoke(Boolean.valueOf(z)).intValue();
        }
        return i2;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfLong")
    @OverloadResolutionByLambdaReturnType
    private static final long sumOfLong(byte[] bArr, Function1<? super Byte, Long> selector) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        long j2 = 0;
        for (byte b2 : bArr) {
            j2 += selector.invoke(Byte.valueOf(b2)).longValue();
        }
        return j2;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfLong")
    @OverloadResolutionByLambdaReturnType
    private static final long sumOfLong(char[] cArr, Function1<? super Character, Long> selector) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        long j2 = 0;
        for (char c2 : cArr) {
            j2 += selector.invoke(Character.valueOf(c2)).longValue();
        }
        return j2;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfLong")
    @OverloadResolutionByLambdaReturnType
    private static final long sumOfLong(double[] dArr, Function1<? super Double, Long> selector) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        long j2 = 0;
        for (double d2 : dArr) {
            j2 += selector.invoke(Double.valueOf(d2)).longValue();
        }
        return j2;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfLong")
    @OverloadResolutionByLambdaReturnType
    private static final long sumOfLong(float[] fArr, Function1<? super Float, Long> selector) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        long j2 = 0;
        for (float f2 : fArr) {
            j2 += selector.invoke(Float.valueOf(f2)).longValue();
        }
        return j2;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfLong")
    @OverloadResolutionByLambdaReturnType
    private static final long sumOfLong(int[] iArr, Function1<? super Integer, Long> selector) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        long j2 = 0;
        for (int i2 : iArr) {
            j2 += selector.invoke(Integer.valueOf(i2)).longValue();
        }
        return j2;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfLong")
    @OverloadResolutionByLambdaReturnType
    private static final long sumOfLong(long[] jArr, Function1<? super Long, Long> selector) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        long j2 = 0;
        for (long j3 : jArr) {
            j2 += selector.invoke(Long.valueOf(j3)).longValue();
        }
        return j2;
    }

    @JvmName(name = "sumOfLong")
    public static final long sumOfLong(@NotNull Long[] lArr) {
        Intrinsics.checkNotNullParameter(lArr, "<this>");
        long j2 = 0;
        for (Long l2 : lArr) {
            j2 += l2.longValue();
        }
        return j2;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfLong")
    @OverloadResolutionByLambdaReturnType
    private static final <T> long sumOfLong(T[] tArr, Function1<? super T, Long> selector) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        long j2 = 0;
        for (T t2 : tArr) {
            j2 += selector.invoke(t2).longValue();
        }
        return j2;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfLong")
    @OverloadResolutionByLambdaReturnType
    private static final long sumOfLong(short[] sArr, Function1<? super Short, Long> selector) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        long j2 = 0;
        for (short s2 : sArr) {
            j2 += selector.invoke(Short.valueOf(s2)).longValue();
        }
        return j2;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @JvmName(name = "sumOfLong")
    @OverloadResolutionByLambdaReturnType
    private static final long sumOfLong(boolean[] zArr, Function1<? super Boolean, Long> selector) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        long j2 = 0;
        for (boolean z : zArr) {
            j2 += selector.invoke(Boolean.valueOf(z)).longValue();
        }
        return j2;
    }

    @JvmName(name = "sumOfShort")
    public static final int sumOfShort(@NotNull Short[] shArr) {
        Intrinsics.checkNotNullParameter(shArr, "<this>");
        int i2 = 0;
        for (Short sh : shArr) {
            i2 += sh.shortValue();
        }
        return i2;
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    @JvmName(name = "sumOfUInt")
    @OverloadResolutionByLambdaReturnType
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    private static final int sumOfUInt(byte[] bArr, Function1<? super Byte, UInt> selector) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int m281constructorimpl = UInt.m281constructorimpl(0);
        for (byte b2 : bArr) {
            m281constructorimpl = UInt.m281constructorimpl(m281constructorimpl + selector.invoke(Byte.valueOf(b2)).m332unboximpl());
        }
        return m281constructorimpl;
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    @JvmName(name = "sumOfUInt")
    @OverloadResolutionByLambdaReturnType
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    private static final int sumOfUInt(char[] cArr, Function1<? super Character, UInt> selector) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int m281constructorimpl = UInt.m281constructorimpl(0);
        for (char c2 : cArr) {
            m281constructorimpl = UInt.m281constructorimpl(m281constructorimpl + selector.invoke(Character.valueOf(c2)).m332unboximpl());
        }
        return m281constructorimpl;
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    @JvmName(name = "sumOfUInt")
    @OverloadResolutionByLambdaReturnType
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    private static final int sumOfUInt(double[] dArr, Function1<? super Double, UInt> selector) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int m281constructorimpl = UInt.m281constructorimpl(0);
        for (double d2 : dArr) {
            m281constructorimpl = UInt.m281constructorimpl(m281constructorimpl + selector.invoke(Double.valueOf(d2)).m332unboximpl());
        }
        return m281constructorimpl;
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    @JvmName(name = "sumOfUInt")
    @OverloadResolutionByLambdaReturnType
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    private static final int sumOfUInt(float[] fArr, Function1<? super Float, UInt> selector) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int m281constructorimpl = UInt.m281constructorimpl(0);
        for (float f2 : fArr) {
            m281constructorimpl = UInt.m281constructorimpl(m281constructorimpl + selector.invoke(Float.valueOf(f2)).m332unboximpl());
        }
        return m281constructorimpl;
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    @JvmName(name = "sumOfUInt")
    @OverloadResolutionByLambdaReturnType
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    private static final int sumOfUInt(int[] iArr, Function1<? super Integer, UInt> selector) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int m281constructorimpl = UInt.m281constructorimpl(0);
        for (int i2 : iArr) {
            m281constructorimpl = UInt.m281constructorimpl(m281constructorimpl + selector.invoke(Integer.valueOf(i2)).m332unboximpl());
        }
        return m281constructorimpl;
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    @JvmName(name = "sumOfUInt")
    @OverloadResolutionByLambdaReturnType
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    private static final int sumOfUInt(long[] jArr, Function1<? super Long, UInt> selector) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int m281constructorimpl = UInt.m281constructorimpl(0);
        for (long j2 : jArr) {
            m281constructorimpl = UInt.m281constructorimpl(m281constructorimpl + selector.invoke(Long.valueOf(j2)).m332unboximpl());
        }
        return m281constructorimpl;
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    @JvmName(name = "sumOfUInt")
    @OverloadResolutionByLambdaReturnType
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    private static final <T> int sumOfUInt(T[] tArr, Function1<? super T, UInt> selector) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int m281constructorimpl = UInt.m281constructorimpl(0);
        for (T t2 : tArr) {
            m281constructorimpl = UInt.m281constructorimpl(m281constructorimpl + selector.invoke(t2).m332unboximpl());
        }
        return m281constructorimpl;
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    @JvmName(name = "sumOfUInt")
    @OverloadResolutionByLambdaReturnType
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    private static final int sumOfUInt(short[] sArr, Function1<? super Short, UInt> selector) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int m281constructorimpl = UInt.m281constructorimpl(0);
        for (short s2 : sArr) {
            m281constructorimpl = UInt.m281constructorimpl(m281constructorimpl + selector.invoke(Short.valueOf(s2)).m332unboximpl());
        }
        return m281constructorimpl;
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    @JvmName(name = "sumOfUInt")
    @OverloadResolutionByLambdaReturnType
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    private static final int sumOfUInt(boolean[] zArr, Function1<? super Boolean, UInt> selector) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int m281constructorimpl = UInt.m281constructorimpl(0);
        for (boolean z : zArr) {
            m281constructorimpl = UInt.m281constructorimpl(m281constructorimpl + selector.invoke(Boolean.valueOf(z)).m332unboximpl());
        }
        return m281constructorimpl;
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    @JvmName(name = "sumOfULong")
    @OverloadResolutionByLambdaReturnType
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    private static final long sumOfULong(byte[] bArr, Function1<? super Byte, ULong> selector) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        long m359constructorimpl = ULong.m359constructorimpl(0L);
        for (byte b2 : bArr) {
            m359constructorimpl = ULong.m359constructorimpl(m359constructorimpl + selector.invoke(Byte.valueOf(b2)).m410unboximpl());
        }
        return m359constructorimpl;
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    @JvmName(name = "sumOfULong")
    @OverloadResolutionByLambdaReturnType
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    private static final long sumOfULong(char[] cArr, Function1<? super Character, ULong> selector) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        long m359constructorimpl = ULong.m359constructorimpl(0L);
        for (char c2 : cArr) {
            m359constructorimpl = ULong.m359constructorimpl(m359constructorimpl + selector.invoke(Character.valueOf(c2)).m410unboximpl());
        }
        return m359constructorimpl;
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    @JvmName(name = "sumOfULong")
    @OverloadResolutionByLambdaReturnType
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    private static final long sumOfULong(double[] dArr, Function1<? super Double, ULong> selector) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        long m359constructorimpl = ULong.m359constructorimpl(0L);
        for (double d2 : dArr) {
            m359constructorimpl = ULong.m359constructorimpl(m359constructorimpl + selector.invoke(Double.valueOf(d2)).m410unboximpl());
        }
        return m359constructorimpl;
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    @JvmName(name = "sumOfULong")
    @OverloadResolutionByLambdaReturnType
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    private static final long sumOfULong(float[] fArr, Function1<? super Float, ULong> selector) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        long m359constructorimpl = ULong.m359constructorimpl(0L);
        for (float f2 : fArr) {
            m359constructorimpl = ULong.m359constructorimpl(m359constructorimpl + selector.invoke(Float.valueOf(f2)).m410unboximpl());
        }
        return m359constructorimpl;
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    @JvmName(name = "sumOfULong")
    @OverloadResolutionByLambdaReturnType
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    private static final long sumOfULong(int[] iArr, Function1<? super Integer, ULong> selector) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        long m359constructorimpl = ULong.m359constructorimpl(0L);
        for (int i2 : iArr) {
            m359constructorimpl = ULong.m359constructorimpl(m359constructorimpl + selector.invoke(Integer.valueOf(i2)).m410unboximpl());
        }
        return m359constructorimpl;
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    @JvmName(name = "sumOfULong")
    @OverloadResolutionByLambdaReturnType
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    private static final long sumOfULong(long[] jArr, Function1<? super Long, ULong> selector) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        long m359constructorimpl = ULong.m359constructorimpl(0L);
        for (long j2 : jArr) {
            m359constructorimpl = ULong.m359constructorimpl(m359constructorimpl + selector.invoke(Long.valueOf(j2)).m410unboximpl());
        }
        return m359constructorimpl;
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    @JvmName(name = "sumOfULong")
    @OverloadResolutionByLambdaReturnType
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    private static final <T> long sumOfULong(T[] tArr, Function1<? super T, ULong> selector) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        long m359constructorimpl = ULong.m359constructorimpl(0L);
        for (T t2 : tArr) {
            m359constructorimpl = ULong.m359constructorimpl(m359constructorimpl + selector.invoke(t2).m410unboximpl());
        }
        return m359constructorimpl;
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    @JvmName(name = "sumOfULong")
    @OverloadResolutionByLambdaReturnType
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    private static final long sumOfULong(short[] sArr, Function1<? super Short, ULong> selector) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        long m359constructorimpl = ULong.m359constructorimpl(0L);
        for (short s2 : sArr) {
            m359constructorimpl = ULong.m359constructorimpl(m359constructorimpl + selector.invoke(Short.valueOf(s2)).m410unboximpl());
        }
        return m359constructorimpl;
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    @JvmName(name = "sumOfULong")
    @OverloadResolutionByLambdaReturnType
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    private static final long sumOfULong(boolean[] zArr, Function1<? super Boolean, ULong> selector) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        long m359constructorimpl = ULong.m359constructorimpl(0L);
        for (boolean z : zArr) {
            m359constructorimpl = ULong.m359constructorimpl(m359constructorimpl + selector.invoke(Boolean.valueOf(z)).m410unboximpl());
        }
        return m359constructorimpl;
    }

    @NotNull
    public static final List<Byte> take(@NotNull byte[] bArr, int i2) {
        List<Byte> listOf;
        List<Byte> emptyList;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        if (!(i2 >= 0)) {
            throw new IllegalArgumentException(("Requested element count " + i2 + " is less than zero.").toString());
        } else if (i2 == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        } else if (i2 >= bArr.length) {
            return toList(bArr);
        } else {
            if (i2 == 1) {
                listOf = CollectionsKt__CollectionsJVMKt.listOf(Byte.valueOf(bArr[0]));
                return listOf;
            }
            ArrayList arrayList = new ArrayList(i2);
            int i3 = 0;
            for (byte b2 : bArr) {
                arrayList.add(Byte.valueOf(b2));
                i3++;
                if (i3 == i2) {
                    break;
                }
            }
            return arrayList;
        }
    }

    @NotNull
    public static final List<Character> take(@NotNull char[] cArr, int i2) {
        List<Character> listOf;
        List<Character> emptyList;
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        if (!(i2 >= 0)) {
            throw new IllegalArgumentException(("Requested element count " + i2 + " is less than zero.").toString());
        } else if (i2 == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        } else if (i2 >= cArr.length) {
            return toList(cArr);
        } else {
            if (i2 == 1) {
                listOf = CollectionsKt__CollectionsJVMKt.listOf(Character.valueOf(cArr[0]));
                return listOf;
            }
            ArrayList arrayList = new ArrayList(i2);
            int i3 = 0;
            for (char c2 : cArr) {
                arrayList.add(Character.valueOf(c2));
                i3++;
                if (i3 == i2) {
                    break;
                }
            }
            return arrayList;
        }
    }

    @NotNull
    public static final List<Double> take(@NotNull double[] dArr, int i2) {
        List<Double> listOf;
        List<Double> emptyList;
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        if (!(i2 >= 0)) {
            throw new IllegalArgumentException(("Requested element count " + i2 + " is less than zero.").toString());
        } else if (i2 == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        } else if (i2 >= dArr.length) {
            return toList(dArr);
        } else {
            if (i2 == 1) {
                listOf = CollectionsKt__CollectionsJVMKt.listOf(Double.valueOf(dArr[0]));
                return listOf;
            }
            ArrayList arrayList = new ArrayList(i2);
            int i3 = 0;
            for (double d2 : dArr) {
                arrayList.add(Double.valueOf(d2));
                i3++;
                if (i3 == i2) {
                    break;
                }
            }
            return arrayList;
        }
    }

    @NotNull
    public static final List<Float> take(@NotNull float[] fArr, int i2) {
        List<Float> listOf;
        List<Float> emptyList;
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        if (!(i2 >= 0)) {
            throw new IllegalArgumentException(("Requested element count " + i2 + " is less than zero.").toString());
        } else if (i2 == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        } else if (i2 >= fArr.length) {
            return toList(fArr);
        } else {
            if (i2 == 1) {
                listOf = CollectionsKt__CollectionsJVMKt.listOf(Float.valueOf(fArr[0]));
                return listOf;
            }
            ArrayList arrayList = new ArrayList(i2);
            int i3 = 0;
            for (float f2 : fArr) {
                arrayList.add(Float.valueOf(f2));
                i3++;
                if (i3 == i2) {
                    break;
                }
            }
            return arrayList;
        }
    }

    @NotNull
    public static final List<Integer> take(@NotNull int[] iArr, int i2) {
        List<Integer> listOf;
        List<Integer> emptyList;
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        if (!(i2 >= 0)) {
            throw new IllegalArgumentException(("Requested element count " + i2 + " is less than zero.").toString());
        } else if (i2 == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        } else if (i2 >= iArr.length) {
            return toList(iArr);
        } else {
            if (i2 == 1) {
                listOf = CollectionsKt__CollectionsJVMKt.listOf(Integer.valueOf(iArr[0]));
                return listOf;
            }
            ArrayList arrayList = new ArrayList(i2);
            int i3 = 0;
            for (int i4 : iArr) {
                arrayList.add(Integer.valueOf(i4));
                i3++;
                if (i3 == i2) {
                    break;
                }
            }
            return arrayList;
        }
    }

    @NotNull
    public static final List<Long> take(@NotNull long[] jArr, int i2) {
        List<Long> listOf;
        List<Long> emptyList;
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        if (!(i2 >= 0)) {
            throw new IllegalArgumentException(("Requested element count " + i2 + " is less than zero.").toString());
        } else if (i2 == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        } else if (i2 >= jArr.length) {
            return toList(jArr);
        } else {
            if (i2 == 1) {
                listOf = CollectionsKt__CollectionsJVMKt.listOf(Long.valueOf(jArr[0]));
                return listOf;
            }
            ArrayList arrayList = new ArrayList(i2);
            int i3 = 0;
            for (long j2 : jArr) {
                arrayList.add(Long.valueOf(j2));
                i3++;
                if (i3 == i2) {
                    break;
                }
            }
            return arrayList;
        }
    }

    @NotNull
    public static final <T> List<T> take(@NotNull T[] tArr, int i2) {
        List<T> listOf;
        List<T> list;
        List<T> emptyList;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        if (!(i2 >= 0)) {
            throw new IllegalArgumentException(("Requested element count " + i2 + " is less than zero.").toString());
        } else if (i2 == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        } else if (i2 >= tArr.length) {
            list = toList(tArr);
            return list;
        } else if (i2 == 1) {
            listOf = CollectionsKt__CollectionsJVMKt.listOf(tArr[0]);
            return listOf;
        } else {
            ArrayList arrayList = new ArrayList(i2);
            int i3 = 0;
            for (T t2 : tArr) {
                arrayList.add(t2);
                i3++;
                if (i3 == i2) {
                    break;
                }
            }
            return arrayList;
        }
    }

    @NotNull
    public static final List<Short> take(@NotNull short[] sArr, int i2) {
        List<Short> listOf;
        List<Short> emptyList;
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        if (!(i2 >= 0)) {
            throw new IllegalArgumentException(("Requested element count " + i2 + " is less than zero.").toString());
        } else if (i2 == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        } else if (i2 >= sArr.length) {
            return toList(sArr);
        } else {
            if (i2 == 1) {
                listOf = CollectionsKt__CollectionsJVMKt.listOf(Short.valueOf(sArr[0]));
                return listOf;
            }
            ArrayList arrayList = new ArrayList(i2);
            int i3 = 0;
            for (short s2 : sArr) {
                arrayList.add(Short.valueOf(s2));
                i3++;
                if (i3 == i2) {
                    break;
                }
            }
            return arrayList;
        }
    }

    @NotNull
    public static final List<Boolean> take(@NotNull boolean[] zArr, int i2) {
        List<Boolean> listOf;
        List<Boolean> emptyList;
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        if (!(i2 >= 0)) {
            throw new IllegalArgumentException(("Requested element count " + i2 + " is less than zero.").toString());
        } else if (i2 == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        } else if (i2 >= zArr.length) {
            return toList(zArr);
        } else {
            if (i2 == 1) {
                listOf = CollectionsKt__CollectionsJVMKt.listOf(Boolean.valueOf(zArr[0]));
                return listOf;
            }
            ArrayList arrayList = new ArrayList(i2);
            int i3 = 0;
            for (boolean z : zArr) {
                arrayList.add(Boolean.valueOf(z));
                i3++;
                if (i3 == i2) {
                    break;
                }
            }
            return arrayList;
        }
    }

    @NotNull
    public static final List<Byte> takeLast(@NotNull byte[] bArr, int i2) {
        List<Byte> listOf;
        List<Byte> emptyList;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        if (!(i2 >= 0)) {
            throw new IllegalArgumentException(("Requested element count " + i2 + " is less than zero.").toString());
        } else if (i2 == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        } else {
            int length = bArr.length;
            if (i2 >= length) {
                return toList(bArr);
            }
            if (i2 == 1) {
                listOf = CollectionsKt__CollectionsJVMKt.listOf(Byte.valueOf(bArr[length - 1]));
                return listOf;
            }
            ArrayList arrayList = new ArrayList(i2);
            for (int i3 = length - i2; i3 < length; i3++) {
                arrayList.add(Byte.valueOf(bArr[i3]));
            }
            return arrayList;
        }
    }

    @NotNull
    public static final List<Character> takeLast(@NotNull char[] cArr, int i2) {
        List<Character> listOf;
        List<Character> emptyList;
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        if (!(i2 >= 0)) {
            throw new IllegalArgumentException(("Requested element count " + i2 + " is less than zero.").toString());
        } else if (i2 == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        } else {
            int length = cArr.length;
            if (i2 >= length) {
                return toList(cArr);
            }
            if (i2 == 1) {
                listOf = CollectionsKt__CollectionsJVMKt.listOf(Character.valueOf(cArr[length - 1]));
                return listOf;
            }
            ArrayList arrayList = new ArrayList(i2);
            for (int i3 = length - i2; i3 < length; i3++) {
                arrayList.add(Character.valueOf(cArr[i3]));
            }
            return arrayList;
        }
    }

    @NotNull
    public static final List<Double> takeLast(@NotNull double[] dArr, int i2) {
        List<Double> listOf;
        List<Double> emptyList;
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        if (!(i2 >= 0)) {
            throw new IllegalArgumentException(("Requested element count " + i2 + " is less than zero.").toString());
        } else if (i2 == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        } else {
            int length = dArr.length;
            if (i2 >= length) {
                return toList(dArr);
            }
            if (i2 == 1) {
                listOf = CollectionsKt__CollectionsJVMKt.listOf(Double.valueOf(dArr[length - 1]));
                return listOf;
            }
            ArrayList arrayList = new ArrayList(i2);
            for (int i3 = length - i2; i3 < length; i3++) {
                arrayList.add(Double.valueOf(dArr[i3]));
            }
            return arrayList;
        }
    }

    @NotNull
    public static final List<Float> takeLast(@NotNull float[] fArr, int i2) {
        List<Float> listOf;
        List<Float> emptyList;
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        if (!(i2 >= 0)) {
            throw new IllegalArgumentException(("Requested element count " + i2 + " is less than zero.").toString());
        } else if (i2 == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        } else {
            int length = fArr.length;
            if (i2 >= length) {
                return toList(fArr);
            }
            if (i2 == 1) {
                listOf = CollectionsKt__CollectionsJVMKt.listOf(Float.valueOf(fArr[length - 1]));
                return listOf;
            }
            ArrayList arrayList = new ArrayList(i2);
            for (int i3 = length - i2; i3 < length; i3++) {
                arrayList.add(Float.valueOf(fArr[i3]));
            }
            return arrayList;
        }
    }

    @NotNull
    public static final List<Integer> takeLast(@NotNull int[] iArr, int i2) {
        List<Integer> listOf;
        List<Integer> emptyList;
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        if (!(i2 >= 0)) {
            throw new IllegalArgumentException(("Requested element count " + i2 + " is less than zero.").toString());
        } else if (i2 == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        } else {
            int length = iArr.length;
            if (i2 >= length) {
                return toList(iArr);
            }
            if (i2 == 1) {
                listOf = CollectionsKt__CollectionsJVMKt.listOf(Integer.valueOf(iArr[length - 1]));
                return listOf;
            }
            ArrayList arrayList = new ArrayList(i2);
            for (int i3 = length - i2; i3 < length; i3++) {
                arrayList.add(Integer.valueOf(iArr[i3]));
            }
            return arrayList;
        }
    }

    @NotNull
    public static final List<Long> takeLast(@NotNull long[] jArr, int i2) {
        List<Long> listOf;
        List<Long> emptyList;
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        if (!(i2 >= 0)) {
            throw new IllegalArgumentException(("Requested element count " + i2 + " is less than zero.").toString());
        } else if (i2 == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        } else {
            int length = jArr.length;
            if (i2 >= length) {
                return toList(jArr);
            }
            if (i2 == 1) {
                listOf = CollectionsKt__CollectionsJVMKt.listOf(Long.valueOf(jArr[length - 1]));
                return listOf;
            }
            ArrayList arrayList = new ArrayList(i2);
            for (int i3 = length - i2; i3 < length; i3++) {
                arrayList.add(Long.valueOf(jArr[i3]));
            }
            return arrayList;
        }
    }

    @NotNull
    public static final <T> List<T> takeLast(@NotNull T[] tArr, int i2) {
        List<T> listOf;
        List<T> list;
        List<T> emptyList;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        if (!(i2 >= 0)) {
            throw new IllegalArgumentException(("Requested element count " + i2 + " is less than zero.").toString());
        } else if (i2 == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        } else {
            int length = tArr.length;
            if (i2 >= length) {
                list = toList(tArr);
                return list;
            } else if (i2 == 1) {
                listOf = CollectionsKt__CollectionsJVMKt.listOf(tArr[length - 1]);
                return listOf;
            } else {
                ArrayList arrayList = new ArrayList(i2);
                for (int i3 = length - i2; i3 < length; i3++) {
                    arrayList.add(tArr[i3]);
                }
                return arrayList;
            }
        }
    }

    @NotNull
    public static final List<Short> takeLast(@NotNull short[] sArr, int i2) {
        List<Short> listOf;
        List<Short> emptyList;
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        if (!(i2 >= 0)) {
            throw new IllegalArgumentException(("Requested element count " + i2 + " is less than zero.").toString());
        } else if (i2 == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        } else {
            int length = sArr.length;
            if (i2 >= length) {
                return toList(sArr);
            }
            if (i2 == 1) {
                listOf = CollectionsKt__CollectionsJVMKt.listOf(Short.valueOf(sArr[length - 1]));
                return listOf;
            }
            ArrayList arrayList = new ArrayList(i2);
            for (int i3 = length - i2; i3 < length; i3++) {
                arrayList.add(Short.valueOf(sArr[i3]));
            }
            return arrayList;
        }
    }

    @NotNull
    public static final List<Boolean> takeLast(@NotNull boolean[] zArr, int i2) {
        List<Boolean> listOf;
        List<Boolean> emptyList;
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        if (!(i2 >= 0)) {
            throw new IllegalArgumentException(("Requested element count " + i2 + " is less than zero.").toString());
        } else if (i2 == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        } else {
            int length = zArr.length;
            if (i2 >= length) {
                return toList(zArr);
            }
            if (i2 == 1) {
                listOf = CollectionsKt__CollectionsJVMKt.listOf(Boolean.valueOf(zArr[length - 1]));
                return listOf;
            }
            ArrayList arrayList = new ArrayList(i2);
            for (int i3 = length - i2; i3 < length; i3++) {
                arrayList.add(Boolean.valueOf(zArr[i3]));
            }
            return arrayList;
        }
    }

    @NotNull
    public static final List<Byte> takeLastWhile(@NotNull byte[] bArr, @NotNull Function1<? super Byte, Boolean> predicate) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (lastIndex = getLastIndex(bArr); -1 < lastIndex; lastIndex--) {
            if (!predicate.invoke(Byte.valueOf(bArr[lastIndex])).booleanValue()) {
                return drop(bArr, lastIndex + 1);
            }
        }
        return toList(bArr);
    }

    @NotNull
    public static final List<Character> takeLastWhile(@NotNull char[] cArr, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (int lastIndex = getLastIndex(cArr); -1 < lastIndex; lastIndex--) {
            if (!predicate.invoke(Character.valueOf(cArr[lastIndex])).booleanValue()) {
                return drop(cArr, lastIndex + 1);
            }
        }
        return toList(cArr);
    }

    @NotNull
    public static final List<Double> takeLastWhile(@NotNull double[] dArr, @NotNull Function1<? super Double, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (int lastIndex = getLastIndex(dArr); -1 < lastIndex; lastIndex--) {
            if (!predicate.invoke(Double.valueOf(dArr[lastIndex])).booleanValue()) {
                return drop(dArr, lastIndex + 1);
            }
        }
        return toList(dArr);
    }

    @NotNull
    public static final List<Float> takeLastWhile(@NotNull float[] fArr, @NotNull Function1<? super Float, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (int lastIndex = getLastIndex(fArr); -1 < lastIndex; lastIndex--) {
            if (!predicate.invoke(Float.valueOf(fArr[lastIndex])).booleanValue()) {
                return drop(fArr, lastIndex + 1);
            }
        }
        return toList(fArr);
    }

    @NotNull
    public static final List<Integer> takeLastWhile(@NotNull int[] iArr, @NotNull Function1<? super Integer, Boolean> predicate) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (lastIndex = getLastIndex(iArr); -1 < lastIndex; lastIndex--) {
            if (!predicate.invoke(Integer.valueOf(iArr[lastIndex])).booleanValue()) {
                return drop(iArr, lastIndex + 1);
            }
        }
        return toList(iArr);
    }

    @NotNull
    public static final List<Long> takeLastWhile(@NotNull long[] jArr, @NotNull Function1<? super Long, Boolean> predicate) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (lastIndex = getLastIndex(jArr); -1 < lastIndex; lastIndex--) {
            if (!predicate.invoke(Long.valueOf(jArr[lastIndex])).booleanValue()) {
                return drop(jArr, lastIndex + 1);
            }
        }
        return toList(jArr);
    }

    @NotNull
    public static final <T> List<T> takeLastWhile(@NotNull T[] tArr, @NotNull Function1<? super T, Boolean> predicate) {
        int lastIndex;
        List<T> list;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (lastIndex = getLastIndex(tArr); -1 < lastIndex; lastIndex--) {
            if (!predicate.invoke(tArr[lastIndex]).booleanValue()) {
                return drop(tArr, lastIndex + 1);
            }
        }
        list = toList(tArr);
        return list;
    }

    @NotNull
    public static final List<Short> takeLastWhile(@NotNull short[] sArr, @NotNull Function1<? super Short, Boolean> predicate) {
        int lastIndex;
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (lastIndex = getLastIndex(sArr); -1 < lastIndex; lastIndex--) {
            if (!predicate.invoke(Short.valueOf(sArr[lastIndex])).booleanValue()) {
                return drop(sArr, lastIndex + 1);
            }
        }
        return toList(sArr);
    }

    @NotNull
    public static final List<Boolean> takeLastWhile(@NotNull boolean[] zArr, @NotNull Function1<? super Boolean, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (int lastIndex = getLastIndex(zArr); -1 < lastIndex; lastIndex--) {
            if (!predicate.invoke(Boolean.valueOf(zArr[lastIndex])).booleanValue()) {
                return drop(zArr, lastIndex + 1);
            }
        }
        return toList(zArr);
    }

    @NotNull
    public static final List<Byte> takeWhile(@NotNull byte[] bArr, @NotNull Function1<? super Byte, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        for (byte b2 : bArr) {
            if (!predicate.invoke(Byte.valueOf(b2)).booleanValue()) {
                break;
            }
            arrayList.add(Byte.valueOf(b2));
        }
        return arrayList;
    }

    @NotNull
    public static final List<Character> takeWhile(@NotNull char[] cArr, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        for (char c2 : cArr) {
            if (!predicate.invoke(Character.valueOf(c2)).booleanValue()) {
                break;
            }
            arrayList.add(Character.valueOf(c2));
        }
        return arrayList;
    }

    @NotNull
    public static final List<Double> takeWhile(@NotNull double[] dArr, @NotNull Function1<? super Double, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        for (double d2 : dArr) {
            if (!predicate.invoke(Double.valueOf(d2)).booleanValue()) {
                break;
            }
            arrayList.add(Double.valueOf(d2));
        }
        return arrayList;
    }

    @NotNull
    public static final List<Float> takeWhile(@NotNull float[] fArr, @NotNull Function1<? super Float, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        for (float f2 : fArr) {
            if (!predicate.invoke(Float.valueOf(f2)).booleanValue()) {
                break;
            }
            arrayList.add(Float.valueOf(f2));
        }
        return arrayList;
    }

    @NotNull
    public static final List<Integer> takeWhile(@NotNull int[] iArr, @NotNull Function1<? super Integer, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        for (int i2 : iArr) {
            if (!predicate.invoke(Integer.valueOf(i2)).booleanValue()) {
                break;
            }
            arrayList.add(Integer.valueOf(i2));
        }
        return arrayList;
    }

    @NotNull
    public static final List<Long> takeWhile(@NotNull long[] jArr, @NotNull Function1<? super Long, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        for (long j2 : jArr) {
            if (!predicate.invoke(Long.valueOf(j2)).booleanValue()) {
                break;
            }
            arrayList.add(Long.valueOf(j2));
        }
        return arrayList;
    }

    @NotNull
    public static final <T> List<T> takeWhile(@NotNull T[] tArr, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        for (T t2 : tArr) {
            if (!predicate.invoke(t2).booleanValue()) {
                break;
            }
            arrayList.add(t2);
        }
        return arrayList;
    }

    @NotNull
    public static final List<Short> takeWhile(@NotNull short[] sArr, @NotNull Function1<? super Short, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        for (short s2 : sArr) {
            if (!predicate.invoke(Short.valueOf(s2)).booleanValue()) {
                break;
            }
            arrayList.add(Short.valueOf(s2));
        }
        return arrayList;
    }

    @NotNull
    public static final List<Boolean> takeWhile(@NotNull boolean[] zArr, @NotNull Function1<? super Boolean, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        for (boolean z : zArr) {
            if (!predicate.invoke(Boolean.valueOf(z)).booleanValue()) {
                break;
            }
            arrayList.add(Boolean.valueOf(z));
        }
        return arrayList;
    }

    @NotNull
    public static final boolean[] toBooleanArray(@NotNull Boolean[] boolArr) {
        Intrinsics.checkNotNullParameter(boolArr, "<this>");
        int length = boolArr.length;
        boolean[] zArr = new boolean[length];
        for (int i2 = 0; i2 < length; i2++) {
            zArr[i2] = boolArr[i2].booleanValue();
        }
        return zArr;
    }

    @NotNull
    public static final byte[] toByteArray(@NotNull Byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        for (int i2 = 0; i2 < length; i2++) {
            bArr2[i2] = bArr[i2].byteValue();
        }
        return bArr2;
    }

    @NotNull
    public static final char[] toCharArray(@NotNull Character[] chArr) {
        Intrinsics.checkNotNullParameter(chArr, "<this>");
        int length = chArr.length;
        char[] cArr = new char[length];
        for (int i2 = 0; i2 < length; i2++) {
            cArr[i2] = chArr[i2].charValue();
        }
        return cArr;
    }

    @NotNull
    public static final <C extends Collection<? super Byte>> C toCollection(@NotNull byte[] bArr, @NotNull C destination) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        for (byte b2 : bArr) {
            destination.add(Byte.valueOf(b2));
        }
        return destination;
    }

    @NotNull
    public static final <C extends Collection<? super Character>> C toCollection(@NotNull char[] cArr, @NotNull C destination) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        for (char c2 : cArr) {
            destination.add(Character.valueOf(c2));
        }
        return destination;
    }

    @NotNull
    public static final <C extends Collection<? super Double>> C toCollection(@NotNull double[] dArr, @NotNull C destination) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        for (double d2 : dArr) {
            destination.add(Double.valueOf(d2));
        }
        return destination;
    }

    @NotNull
    public static final <C extends Collection<? super Float>> C toCollection(@NotNull float[] fArr, @NotNull C destination) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        for (float f2 : fArr) {
            destination.add(Float.valueOf(f2));
        }
        return destination;
    }

    @NotNull
    public static final <C extends Collection<? super Integer>> C toCollection(@NotNull int[] iArr, @NotNull C destination) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        for (int i2 : iArr) {
            destination.add(Integer.valueOf(i2));
        }
        return destination;
    }

    @NotNull
    public static final <C extends Collection<? super Long>> C toCollection(@NotNull long[] jArr, @NotNull C destination) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        for (long j2 : jArr) {
            destination.add(Long.valueOf(j2));
        }
        return destination;
    }

    @NotNull
    public static <T, C extends Collection<? super T>> C toCollection(@NotNull T[] tArr, @NotNull C destination) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        for (T t2 : tArr) {
            destination.add(t2);
        }
        return destination;
    }

    @NotNull
    public static final <C extends Collection<? super Short>> C toCollection(@NotNull short[] sArr, @NotNull C destination) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        for (short s2 : sArr) {
            destination.add(Short.valueOf(s2));
        }
        return destination;
    }

    @NotNull
    public static final <C extends Collection<? super Boolean>> C toCollection(@NotNull boolean[] zArr, @NotNull C destination) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        for (boolean z : zArr) {
            destination.add(Boolean.valueOf(z));
        }
        return destination;
    }

    @NotNull
    public static final double[] toDoubleArray(@NotNull Double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        int length = dArr.length;
        double[] dArr2 = new double[length];
        for (int i2 = 0; i2 < length; i2++) {
            dArr2[i2] = dArr[i2].doubleValue();
        }
        return dArr2;
    }

    @NotNull
    public static final float[] toFloatArray(@NotNull Float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        int length = fArr.length;
        float[] fArr2 = new float[length];
        for (int i2 = 0; i2 < length; i2++) {
            fArr2[i2] = fArr[i2].floatValue();
        }
        return fArr2;
    }

    @NotNull
    public static final HashSet<Byte> toHashSet(@NotNull byte[] bArr) {
        int mapCapacity;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(bArr.length);
        return (HashSet) toCollection(bArr, new HashSet(mapCapacity));
    }

    @NotNull
    public static final HashSet<Character> toHashSet(@NotNull char[] cArr) {
        int coerceAtMost;
        int mapCapacity;
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        coerceAtMost = RangesKt___RangesKt.coerceAtMost(cArr.length, 128);
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(coerceAtMost);
        return (HashSet) toCollection(cArr, new HashSet(mapCapacity));
    }

    @NotNull
    public static final HashSet<Double> toHashSet(@NotNull double[] dArr) {
        int mapCapacity;
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(dArr.length);
        return (HashSet) toCollection(dArr, new HashSet(mapCapacity));
    }

    @NotNull
    public static final HashSet<Float> toHashSet(@NotNull float[] fArr) {
        int mapCapacity;
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(fArr.length);
        return (HashSet) toCollection(fArr, new HashSet(mapCapacity));
    }

    @NotNull
    public static final HashSet<Integer> toHashSet(@NotNull int[] iArr) {
        int mapCapacity;
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(iArr.length);
        return (HashSet) toCollection(iArr, new HashSet(mapCapacity));
    }

    @NotNull
    public static final HashSet<Long> toHashSet(@NotNull long[] jArr) {
        int mapCapacity;
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(jArr.length);
        return (HashSet) toCollection(jArr, new HashSet(mapCapacity));
    }

    @NotNull
    public static final <T> HashSet<T> toHashSet(@NotNull T[] tArr) {
        int mapCapacity;
        Collection collection;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(tArr.length);
        collection = toCollection(tArr, new HashSet(mapCapacity));
        return (HashSet) collection;
    }

    @NotNull
    public static final HashSet<Short> toHashSet(@NotNull short[] sArr) {
        int mapCapacity;
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(sArr.length);
        return (HashSet) toCollection(sArr, new HashSet(mapCapacity));
    }

    @NotNull
    public static final HashSet<Boolean> toHashSet(@NotNull boolean[] zArr) {
        int mapCapacity;
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(zArr.length);
        return (HashSet) toCollection(zArr, new HashSet(mapCapacity));
    }

    @NotNull
    public static final int[] toIntArray(@NotNull Integer[] numArr) {
        Intrinsics.checkNotNullParameter(numArr, "<this>");
        int length = numArr.length;
        int[] iArr = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = numArr[i2].intValue();
        }
        return iArr;
    }

    @NotNull
    public static final List<Byte> toList(@NotNull byte[] bArr) {
        List<Byte> emptyList;
        List<Byte> listOf;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        int length = bArr.length;
        if (length == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        } else if (length != 1) {
            return toMutableList(bArr);
        } else {
            listOf = CollectionsKt__CollectionsJVMKt.listOf(Byte.valueOf(bArr[0]));
            return listOf;
        }
    }

    @NotNull
    public static final List<Character> toList(@NotNull char[] cArr) {
        List<Character> emptyList;
        List<Character> listOf;
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        int length = cArr.length;
        if (length == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        } else if (length != 1) {
            return toMutableList(cArr);
        } else {
            listOf = CollectionsKt__CollectionsJVMKt.listOf(Character.valueOf(cArr[0]));
            return listOf;
        }
    }

    @NotNull
    public static final List<Double> toList(@NotNull double[] dArr) {
        List<Double> emptyList;
        List<Double> listOf;
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        int length = dArr.length;
        if (length == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        } else if (length != 1) {
            return toMutableList(dArr);
        } else {
            listOf = CollectionsKt__CollectionsJVMKt.listOf(Double.valueOf(dArr[0]));
            return listOf;
        }
    }

    @NotNull
    public static final List<Float> toList(@NotNull float[] fArr) {
        List<Float> emptyList;
        List<Float> listOf;
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        int length = fArr.length;
        if (length == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        } else if (length != 1) {
            return toMutableList(fArr);
        } else {
            listOf = CollectionsKt__CollectionsJVMKt.listOf(Float.valueOf(fArr[0]));
            return listOf;
        }
    }

    @NotNull
    public static final List<Integer> toList(@NotNull int[] iArr) {
        List<Integer> emptyList;
        List<Integer> listOf;
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        int length = iArr.length;
        if (length == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        } else if (length != 1) {
            return toMutableList(iArr);
        } else {
            listOf = CollectionsKt__CollectionsJVMKt.listOf(Integer.valueOf(iArr[0]));
            return listOf;
        }
    }

    @NotNull
    public static final List<Long> toList(@NotNull long[] jArr) {
        List<Long> emptyList;
        List<Long> listOf;
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        int length = jArr.length;
        if (length == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        } else if (length != 1) {
            return toMutableList(jArr);
        } else {
            listOf = CollectionsKt__CollectionsJVMKt.listOf(Long.valueOf(jArr[0]));
            return listOf;
        }
    }

    @NotNull
    public static <T> List<T> toList(@NotNull T[] tArr) {
        List<T> emptyList;
        List<T> listOf;
        List<T> mutableList;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        int length = tArr.length;
        if (length == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        } else if (length != 1) {
            mutableList = toMutableList(tArr);
            return mutableList;
        } else {
            listOf = CollectionsKt__CollectionsJVMKt.listOf(tArr[0]);
            return listOf;
        }
    }

    @NotNull
    public static final List<Short> toList(@NotNull short[] sArr) {
        List<Short> emptyList;
        List<Short> listOf;
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        int length = sArr.length;
        if (length == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        } else if (length != 1) {
            return toMutableList(sArr);
        } else {
            listOf = CollectionsKt__CollectionsJVMKt.listOf(Short.valueOf(sArr[0]));
            return listOf;
        }
    }

    @NotNull
    public static final List<Boolean> toList(@NotNull boolean[] zArr) {
        List<Boolean> emptyList;
        List<Boolean> listOf;
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        int length = zArr.length;
        if (length == 0) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        } else if (length != 1) {
            return toMutableList(zArr);
        } else {
            listOf = CollectionsKt__CollectionsJVMKt.listOf(Boolean.valueOf(zArr[0]));
            return listOf;
        }
    }

    @NotNull
    public static final long[] toLongArray(@NotNull Long[] lArr) {
        Intrinsics.checkNotNullParameter(lArr, "<this>");
        int length = lArr.length;
        long[] jArr = new long[length];
        for (int i2 = 0; i2 < length; i2++) {
            jArr[i2] = lArr[i2].longValue();
        }
        return jArr;
    }

    @NotNull
    public static final List<Byte> toMutableList(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        ArrayList arrayList = new ArrayList(bArr.length);
        for (byte b2 : bArr) {
            arrayList.add(Byte.valueOf(b2));
        }
        return arrayList;
    }

    @NotNull
    public static final List<Character> toMutableList(@NotNull char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        ArrayList arrayList = new ArrayList(cArr.length);
        for (char c2 : cArr) {
            arrayList.add(Character.valueOf(c2));
        }
        return arrayList;
    }

    @NotNull
    public static final List<Double> toMutableList(@NotNull double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        ArrayList arrayList = new ArrayList(dArr.length);
        for (double d2 : dArr) {
            arrayList.add(Double.valueOf(d2));
        }
        return arrayList;
    }

    @NotNull
    public static final List<Float> toMutableList(@NotNull float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        ArrayList arrayList = new ArrayList(fArr.length);
        for (float f2 : fArr) {
            arrayList.add(Float.valueOf(f2));
        }
        return arrayList;
    }

    @NotNull
    public static final List<Integer> toMutableList(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        ArrayList arrayList = new ArrayList(iArr.length);
        for (int i2 : iArr) {
            arrayList.add(Integer.valueOf(i2));
        }
        return arrayList;
    }

    @NotNull
    public static final List<Long> toMutableList(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        ArrayList arrayList = new ArrayList(jArr.length);
        for (long j2 : jArr) {
            arrayList.add(Long.valueOf(j2));
        }
        return arrayList;
    }

    @NotNull
    public static <T> List<T> toMutableList(@NotNull T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        return new ArrayList(CollectionsKt__CollectionsKt.asCollection(tArr));
    }

    @NotNull
    public static final List<Short> toMutableList(@NotNull short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        ArrayList arrayList = new ArrayList(sArr.length);
        for (short s2 : sArr) {
            arrayList.add(Short.valueOf(s2));
        }
        return arrayList;
    }

    @NotNull
    public static final List<Boolean> toMutableList(@NotNull boolean[] zArr) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        ArrayList arrayList = new ArrayList(zArr.length);
        for (boolean z : zArr) {
            arrayList.add(Boolean.valueOf(z));
        }
        return arrayList;
    }

    @NotNull
    public static final Set<Byte> toMutableSet(@NotNull byte[] bArr) {
        int mapCapacity;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(bArr.length);
        return (Set) toCollection(bArr, new LinkedHashSet(mapCapacity));
    }

    @NotNull
    public static final Set<Character> toMutableSet(@NotNull char[] cArr) {
        int coerceAtMost;
        int mapCapacity;
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        coerceAtMost = RangesKt___RangesKt.coerceAtMost(cArr.length, 128);
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(coerceAtMost);
        return (Set) toCollection(cArr, new LinkedHashSet(mapCapacity));
    }

    @NotNull
    public static final Set<Double> toMutableSet(@NotNull double[] dArr) {
        int mapCapacity;
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(dArr.length);
        return (Set) toCollection(dArr, new LinkedHashSet(mapCapacity));
    }

    @NotNull
    public static final Set<Float> toMutableSet(@NotNull float[] fArr) {
        int mapCapacity;
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(fArr.length);
        return (Set) toCollection(fArr, new LinkedHashSet(mapCapacity));
    }

    @NotNull
    public static final Set<Integer> toMutableSet(@NotNull int[] iArr) {
        int mapCapacity;
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(iArr.length);
        return (Set) toCollection(iArr, new LinkedHashSet(mapCapacity));
    }

    @NotNull
    public static final Set<Long> toMutableSet(@NotNull long[] jArr) {
        int mapCapacity;
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(jArr.length);
        return (Set) toCollection(jArr, new LinkedHashSet(mapCapacity));
    }

    @NotNull
    public static final <T> Set<T> toMutableSet(@NotNull T[] tArr) {
        int mapCapacity;
        Collection collection;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(tArr.length);
        collection = toCollection(tArr, new LinkedHashSet(mapCapacity));
        return (Set) collection;
    }

    @NotNull
    public static final Set<Short> toMutableSet(@NotNull short[] sArr) {
        int mapCapacity;
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(sArr.length);
        return (Set) toCollection(sArr, new LinkedHashSet(mapCapacity));
    }

    @NotNull
    public static final Set<Boolean> toMutableSet(@NotNull boolean[] zArr) {
        int mapCapacity;
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(zArr.length);
        return (Set) toCollection(zArr, new LinkedHashSet(mapCapacity));
    }

    @NotNull
    public static final Set<Byte> toSet(@NotNull byte[] bArr) {
        Set<Byte> emptySet;
        Set<Byte> of;
        int mapCapacity;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        int length = bArr.length;
        if (length == 0) {
            emptySet = SetsKt__SetsKt.emptySet();
            return emptySet;
        } else if (length != 1) {
            mapCapacity = MapsKt__MapsJVMKt.mapCapacity(bArr.length);
            return (Set) toCollection(bArr, new LinkedHashSet(mapCapacity));
        } else {
            of = SetsKt__SetsJVMKt.setOf(Byte.valueOf(bArr[0]));
            return of;
        }
    }

    @NotNull
    public static final Set<Character> toSet(@NotNull char[] cArr) {
        Set<Character> emptySet;
        Set<Character> of;
        int coerceAtMost;
        int mapCapacity;
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        int length = cArr.length;
        if (length == 0) {
            emptySet = SetsKt__SetsKt.emptySet();
            return emptySet;
        } else if (length == 1) {
            of = SetsKt__SetsJVMKt.setOf(Character.valueOf(cArr[0]));
            return of;
        } else {
            coerceAtMost = RangesKt___RangesKt.coerceAtMost(cArr.length, 128);
            mapCapacity = MapsKt__MapsJVMKt.mapCapacity(coerceAtMost);
            return (Set) toCollection(cArr, new LinkedHashSet(mapCapacity));
        }
    }

    @NotNull
    public static final Set<Double> toSet(@NotNull double[] dArr) {
        Set<Double> emptySet;
        Set<Double> of;
        int mapCapacity;
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        int length = dArr.length;
        if (length == 0) {
            emptySet = SetsKt__SetsKt.emptySet();
            return emptySet;
        } else if (length != 1) {
            mapCapacity = MapsKt__MapsJVMKt.mapCapacity(dArr.length);
            return (Set) toCollection(dArr, new LinkedHashSet(mapCapacity));
        } else {
            of = SetsKt__SetsJVMKt.setOf(Double.valueOf(dArr[0]));
            return of;
        }
    }

    @NotNull
    public static final Set<Float> toSet(@NotNull float[] fArr) {
        Set<Float> emptySet;
        Set<Float> of;
        int mapCapacity;
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        int length = fArr.length;
        if (length == 0) {
            emptySet = SetsKt__SetsKt.emptySet();
            return emptySet;
        } else if (length != 1) {
            mapCapacity = MapsKt__MapsJVMKt.mapCapacity(fArr.length);
            return (Set) toCollection(fArr, new LinkedHashSet(mapCapacity));
        } else {
            of = SetsKt__SetsJVMKt.setOf(Float.valueOf(fArr[0]));
            return of;
        }
    }

    @NotNull
    public static final Set<Integer> toSet(@NotNull int[] iArr) {
        Set<Integer> emptySet;
        Set<Integer> of;
        int mapCapacity;
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        int length = iArr.length;
        if (length == 0) {
            emptySet = SetsKt__SetsKt.emptySet();
            return emptySet;
        } else if (length != 1) {
            mapCapacity = MapsKt__MapsJVMKt.mapCapacity(iArr.length);
            return (Set) toCollection(iArr, new LinkedHashSet(mapCapacity));
        } else {
            of = SetsKt__SetsJVMKt.setOf(Integer.valueOf(iArr[0]));
            return of;
        }
    }

    @NotNull
    public static final Set<Long> toSet(@NotNull long[] jArr) {
        Set<Long> emptySet;
        Set<Long> of;
        int mapCapacity;
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        int length = jArr.length;
        if (length == 0) {
            emptySet = SetsKt__SetsKt.emptySet();
            return emptySet;
        } else if (length != 1) {
            mapCapacity = MapsKt__MapsJVMKt.mapCapacity(jArr.length);
            return (Set) toCollection(jArr, new LinkedHashSet(mapCapacity));
        } else {
            of = SetsKt__SetsJVMKt.setOf(Long.valueOf(jArr[0]));
            return of;
        }
    }

    @NotNull
    public static <T> Set<T> toSet(@NotNull T[] tArr) {
        Set<T> emptySet;
        Set<T> of;
        int mapCapacity;
        Collection collection;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        int length = tArr.length;
        if (length == 0) {
            emptySet = SetsKt__SetsKt.emptySet();
            return emptySet;
        } else if (length == 1) {
            of = SetsKt__SetsJVMKt.setOf(tArr[0]);
            return of;
        } else {
            mapCapacity = MapsKt__MapsJVMKt.mapCapacity(tArr.length);
            collection = toCollection(tArr, new LinkedHashSet(mapCapacity));
            return (Set) collection;
        }
    }

    @NotNull
    public static final Set<Short> toSet(@NotNull short[] sArr) {
        Set<Short> emptySet;
        Set<Short> of;
        int mapCapacity;
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        int length = sArr.length;
        if (length == 0) {
            emptySet = SetsKt__SetsKt.emptySet();
            return emptySet;
        } else if (length != 1) {
            mapCapacity = MapsKt__MapsJVMKt.mapCapacity(sArr.length);
            return (Set) toCollection(sArr, new LinkedHashSet(mapCapacity));
        } else {
            of = SetsKt__SetsJVMKt.setOf(Short.valueOf(sArr[0]));
            return of;
        }
    }

    @NotNull
    public static final Set<Boolean> toSet(@NotNull boolean[] zArr) {
        Set<Boolean> emptySet;
        Set<Boolean> of;
        int mapCapacity;
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        int length = zArr.length;
        if (length == 0) {
            emptySet = SetsKt__SetsKt.emptySet();
            return emptySet;
        } else if (length != 1) {
            mapCapacity = MapsKt__MapsJVMKt.mapCapacity(zArr.length);
            return (Set) toCollection(zArr, new LinkedHashSet(mapCapacity));
        } else {
            of = SetsKt__SetsJVMKt.setOf(Boolean.valueOf(zArr[0]));
            return of;
        }
    }

    @NotNull
    public static final short[] toShortArray(@NotNull Short[] shArr) {
        Intrinsics.checkNotNullParameter(shArr, "<this>");
        int length = shArr.length;
        short[] sArr = new short[length];
        for (int i2 = 0; i2 < length; i2++) {
            sArr[i2] = shArr[i2].shortValue();
        }
        return sArr;
    }

    @NotNull
    public static final Set<Byte> union(@NotNull byte[] bArr, @NotNull Iterable<Byte> other) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Set<Byte> mutableSet = toMutableSet(bArr);
        CollectionsKt__MutableCollectionsKt.addAll(mutableSet, other);
        return mutableSet;
    }

    @NotNull
    public static final Set<Character> union(@NotNull char[] cArr, @NotNull Iterable<Character> other) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Set<Character> mutableSet = toMutableSet(cArr);
        CollectionsKt__MutableCollectionsKt.addAll(mutableSet, other);
        return mutableSet;
    }

    @NotNull
    public static final Set<Double> union(@NotNull double[] dArr, @NotNull Iterable<Double> other) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Set<Double> mutableSet = toMutableSet(dArr);
        CollectionsKt__MutableCollectionsKt.addAll(mutableSet, other);
        return mutableSet;
    }

    @NotNull
    public static final Set<Float> union(@NotNull float[] fArr, @NotNull Iterable<Float> other) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Set<Float> mutableSet = toMutableSet(fArr);
        CollectionsKt__MutableCollectionsKt.addAll(mutableSet, other);
        return mutableSet;
    }

    @NotNull
    public static final Set<Integer> union(@NotNull int[] iArr, @NotNull Iterable<Integer> other) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Set<Integer> mutableSet = toMutableSet(iArr);
        CollectionsKt__MutableCollectionsKt.addAll(mutableSet, other);
        return mutableSet;
    }

    @NotNull
    public static final Set<Long> union(@NotNull long[] jArr, @NotNull Iterable<Long> other) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Set<Long> mutableSet = toMutableSet(jArr);
        CollectionsKt__MutableCollectionsKt.addAll(mutableSet, other);
        return mutableSet;
    }

    @NotNull
    public static final <T> Set<T> union(@NotNull T[] tArr, @NotNull Iterable<? extends T> other) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Set<T> mutableSet = toMutableSet(tArr);
        CollectionsKt__MutableCollectionsKt.addAll(mutableSet, other);
        return mutableSet;
    }

    @NotNull
    public static final Set<Short> union(@NotNull short[] sArr, @NotNull Iterable<Short> other) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Set<Short> mutableSet = toMutableSet(sArr);
        CollectionsKt__MutableCollectionsKt.addAll(mutableSet, other);
        return mutableSet;
    }

    @NotNull
    public static final Set<Boolean> union(@NotNull boolean[] zArr, @NotNull Iterable<Boolean> other) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Set<Boolean> mutableSet = toMutableSet(zArr);
        CollectionsKt__MutableCollectionsKt.addAll(mutableSet, other);
        return mutableSet;
    }

    @NotNull
    public static final Iterable<IndexedValue<Byte>> withIndex(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        return new IndexingIterable(new ArraysKt___ArraysKt$withIndex$2(bArr));
    }

    @NotNull
    public static final Iterable<IndexedValue<Character>> withIndex(@NotNull char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        return new IndexingIterable(new ArraysKt___ArraysKt$withIndex$9(cArr));
    }

    @NotNull
    public static final Iterable<IndexedValue<Double>> withIndex(@NotNull double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        return new IndexingIterable(new ArraysKt___ArraysKt$withIndex$7(dArr));
    }

    @NotNull
    public static final Iterable<IndexedValue<Float>> withIndex(@NotNull float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        return new IndexingIterable(new ArraysKt___ArraysKt$withIndex$6(fArr));
    }

    @NotNull
    public static final Iterable<IndexedValue<Integer>> withIndex(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        return new IndexingIterable(new ArraysKt___ArraysKt$withIndex$4(iArr));
    }

    @NotNull
    public static final Iterable<IndexedValue<Long>> withIndex(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        return new IndexingIterable(new ArraysKt___ArraysKt$withIndex$5(jArr));
    }

    @NotNull
    public static <T> Iterable<IndexedValue<T>> withIndex(@NotNull T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        return new IndexingIterable(new ArraysKt___ArraysKt$withIndex$1(tArr));
    }

    @NotNull
    public static final Iterable<IndexedValue<Short>> withIndex(@NotNull short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        return new IndexingIterable(new ArraysKt___ArraysKt$withIndex$3(sArr));
    }

    @NotNull
    public static final Iterable<IndexedValue<Boolean>> withIndex(@NotNull boolean[] zArr) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        return new IndexingIterable(new ArraysKt___ArraysKt$withIndex$8(zArr));
    }

    @NotNull
    public static final <R> List<Pair<Byte, R>> zip(@NotNull byte[] bArr, @NotNull Iterable<? extends R> other) {
        int collectionSizeOrDefault;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        int length = bArr.length;
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(other, 10);
        ArrayList arrayList = new ArrayList(Math.min(collectionSizeOrDefault, length));
        int i2 = 0;
        for (R r2 : other) {
            if (i2 >= length) {
                break;
            }
            arrayList.add(TuplesKt.to(Byte.valueOf(bArr[i2]), r2));
            i2++;
        }
        return arrayList;
    }

    @NotNull
    public static final <R, V> List<V> zip(@NotNull byte[] bArr, @NotNull Iterable<? extends R> other, @NotNull Function2<? super Byte, ? super R, ? extends V> transform) {
        int collectionSizeOrDefault;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int length = bArr.length;
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(other, 10);
        ArrayList arrayList = new ArrayList(Math.min(collectionSizeOrDefault, length));
        Iterator<? extends R> it = other.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            Object obj = (R) it.next();
            if (i2 >= length) {
                break;
            }
            arrayList.add(transform.invoke(Byte.valueOf(bArr[i2]), obj));
            i2++;
        }
        return arrayList;
    }

    @NotNull
    public static final List<Pair<Byte, Byte>> zip(@NotNull byte[] bArr, @NotNull byte[] other) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        int min = Math.min(bArr.length, other.length);
        ArrayList arrayList = new ArrayList(min);
        for (int i2 = 0; i2 < min; i2++) {
            arrayList.add(TuplesKt.to(Byte.valueOf(bArr[i2]), Byte.valueOf(other[i2])));
        }
        return arrayList;
    }

    @NotNull
    public static final <V> List<V> zip(@NotNull byte[] bArr, @NotNull byte[] other, @NotNull Function2<? super Byte, ? super Byte, ? extends V> transform) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int min = Math.min(bArr.length, other.length);
        ArrayList arrayList = new ArrayList(min);
        for (int i2 = 0; i2 < min; i2++) {
            arrayList.add(transform.invoke(Byte.valueOf(bArr[i2]), Byte.valueOf(other[i2])));
        }
        return arrayList;
    }

    @NotNull
    public static final <R> List<Pair<Byte, R>> zip(@NotNull byte[] bArr, @NotNull R[] other) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        int min = Math.min(bArr.length, other.length);
        ArrayList arrayList = new ArrayList(min);
        for (int i2 = 0; i2 < min; i2++) {
            byte b2 = bArr[i2];
            arrayList.add(TuplesKt.to(Byte.valueOf(b2), other[i2]));
        }
        return arrayList;
    }

    @NotNull
    public static final <R, V> List<V> zip(@NotNull byte[] bArr, @NotNull R[] other, @NotNull Function2<? super Byte, ? super R, ? extends V> transform) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int min = Math.min(bArr.length, other.length);
        ArrayList arrayList = new ArrayList(min);
        for (int i2 = 0; i2 < min; i2++) {
            arrayList.add(transform.invoke(Byte.valueOf(bArr[i2]), other[i2]));
        }
        return arrayList;
    }

    @NotNull
    public static final <R> List<Pair<Character, R>> zip(@NotNull char[] cArr, @NotNull Iterable<? extends R> other) {
        int collectionSizeOrDefault;
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        int length = cArr.length;
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(other, 10);
        ArrayList arrayList = new ArrayList(Math.min(collectionSizeOrDefault, length));
        int i2 = 0;
        for (R r2 : other) {
            if (i2 >= length) {
                break;
            }
            arrayList.add(TuplesKt.to(Character.valueOf(cArr[i2]), r2));
            i2++;
        }
        return arrayList;
    }

    @NotNull
    public static final <R, V> List<V> zip(@NotNull char[] cArr, @NotNull Iterable<? extends R> other, @NotNull Function2<? super Character, ? super R, ? extends V> transform) {
        int collectionSizeOrDefault;
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int length = cArr.length;
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(other, 10);
        ArrayList arrayList = new ArrayList(Math.min(collectionSizeOrDefault, length));
        Iterator<? extends R> it = other.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            Object obj = (R) it.next();
            if (i2 >= length) {
                break;
            }
            arrayList.add(transform.invoke(Character.valueOf(cArr[i2]), obj));
            i2++;
        }
        return arrayList;
    }

    @NotNull
    public static final List<Pair<Character, Character>> zip(@NotNull char[] cArr, @NotNull char[] other) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        int min = Math.min(cArr.length, other.length);
        ArrayList arrayList = new ArrayList(min);
        for (int i2 = 0; i2 < min; i2++) {
            arrayList.add(TuplesKt.to(Character.valueOf(cArr[i2]), Character.valueOf(other[i2])));
        }
        return arrayList;
    }

    @NotNull
    public static final <V> List<V> zip(@NotNull char[] cArr, @NotNull char[] other, @NotNull Function2<? super Character, ? super Character, ? extends V> transform) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int min = Math.min(cArr.length, other.length);
        ArrayList arrayList = new ArrayList(min);
        for (int i2 = 0; i2 < min; i2++) {
            arrayList.add(transform.invoke(Character.valueOf(cArr[i2]), Character.valueOf(other[i2])));
        }
        return arrayList;
    }

    @NotNull
    public static final <R> List<Pair<Character, R>> zip(@NotNull char[] cArr, @NotNull R[] other) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        int min = Math.min(cArr.length, other.length);
        ArrayList arrayList = new ArrayList(min);
        for (int i2 = 0; i2 < min; i2++) {
            char c2 = cArr[i2];
            arrayList.add(TuplesKt.to(Character.valueOf(c2), other[i2]));
        }
        return arrayList;
    }

    @NotNull
    public static final <R, V> List<V> zip(@NotNull char[] cArr, @NotNull R[] other, @NotNull Function2<? super Character, ? super R, ? extends V> transform) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int min = Math.min(cArr.length, other.length);
        ArrayList arrayList = new ArrayList(min);
        for (int i2 = 0; i2 < min; i2++) {
            arrayList.add(transform.invoke(Character.valueOf(cArr[i2]), other[i2]));
        }
        return arrayList;
    }

    @NotNull
    public static final <R> List<Pair<Double, R>> zip(@NotNull double[] dArr, @NotNull Iterable<? extends R> other) {
        int collectionSizeOrDefault;
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        int length = dArr.length;
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(other, 10);
        ArrayList arrayList = new ArrayList(Math.min(collectionSizeOrDefault, length));
        int i2 = 0;
        for (R r2 : other) {
            if (i2 >= length) {
                break;
            }
            arrayList.add(TuplesKt.to(Double.valueOf(dArr[i2]), r2));
            i2++;
        }
        return arrayList;
    }

    @NotNull
    public static final <R, V> List<V> zip(@NotNull double[] dArr, @NotNull Iterable<? extends R> other, @NotNull Function2<? super Double, ? super R, ? extends V> transform) {
        int collectionSizeOrDefault;
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int length = dArr.length;
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(other, 10);
        ArrayList arrayList = new ArrayList(Math.min(collectionSizeOrDefault, length));
        Iterator<? extends R> it = other.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            Object obj = (R) it.next();
            if (i2 >= length) {
                break;
            }
            arrayList.add(transform.invoke(Double.valueOf(dArr[i2]), obj));
            i2++;
        }
        return arrayList;
    }

    @NotNull
    public static final List<Pair<Double, Double>> zip(@NotNull double[] dArr, @NotNull double[] other) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        int min = Math.min(dArr.length, other.length);
        ArrayList arrayList = new ArrayList(min);
        for (int i2 = 0; i2 < min; i2++) {
            arrayList.add(TuplesKt.to(Double.valueOf(dArr[i2]), Double.valueOf(other[i2])));
        }
        return arrayList;
    }

    @NotNull
    public static final <V> List<V> zip(@NotNull double[] dArr, @NotNull double[] other, @NotNull Function2<? super Double, ? super Double, ? extends V> transform) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int min = Math.min(dArr.length, other.length);
        ArrayList arrayList = new ArrayList(min);
        for (int i2 = 0; i2 < min; i2++) {
            arrayList.add(transform.invoke(Double.valueOf(dArr[i2]), Double.valueOf(other[i2])));
        }
        return arrayList;
    }

    @NotNull
    public static final <R> List<Pair<Double, R>> zip(@NotNull double[] dArr, @NotNull R[] other) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        int min = Math.min(dArr.length, other.length);
        ArrayList arrayList = new ArrayList(min);
        for (int i2 = 0; i2 < min; i2++) {
            double d2 = dArr[i2];
            arrayList.add(TuplesKt.to(Double.valueOf(d2), other[i2]));
        }
        return arrayList;
    }

    @NotNull
    public static final <R, V> List<V> zip(@NotNull double[] dArr, @NotNull R[] other, @NotNull Function2<? super Double, ? super R, ? extends V> transform) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int min = Math.min(dArr.length, other.length);
        ArrayList arrayList = new ArrayList(min);
        for (int i2 = 0; i2 < min; i2++) {
            arrayList.add(transform.invoke(Double.valueOf(dArr[i2]), other[i2]));
        }
        return arrayList;
    }

    @NotNull
    public static final <R> List<Pair<Float, R>> zip(@NotNull float[] fArr, @NotNull Iterable<? extends R> other) {
        int collectionSizeOrDefault;
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        int length = fArr.length;
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(other, 10);
        ArrayList arrayList = new ArrayList(Math.min(collectionSizeOrDefault, length));
        int i2 = 0;
        for (R r2 : other) {
            if (i2 >= length) {
                break;
            }
            arrayList.add(TuplesKt.to(Float.valueOf(fArr[i2]), r2));
            i2++;
        }
        return arrayList;
    }

    @NotNull
    public static final <R, V> List<V> zip(@NotNull float[] fArr, @NotNull Iterable<? extends R> other, @NotNull Function2<? super Float, ? super R, ? extends V> transform) {
        int collectionSizeOrDefault;
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int length = fArr.length;
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(other, 10);
        ArrayList arrayList = new ArrayList(Math.min(collectionSizeOrDefault, length));
        Iterator<? extends R> it = other.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            Object obj = (R) it.next();
            if (i2 >= length) {
                break;
            }
            arrayList.add(transform.invoke(Float.valueOf(fArr[i2]), obj));
            i2++;
        }
        return arrayList;
    }

    @NotNull
    public static final List<Pair<Float, Float>> zip(@NotNull float[] fArr, @NotNull float[] other) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        int min = Math.min(fArr.length, other.length);
        ArrayList arrayList = new ArrayList(min);
        for (int i2 = 0; i2 < min; i2++) {
            arrayList.add(TuplesKt.to(Float.valueOf(fArr[i2]), Float.valueOf(other[i2])));
        }
        return arrayList;
    }

    @NotNull
    public static final <V> List<V> zip(@NotNull float[] fArr, @NotNull float[] other, @NotNull Function2<? super Float, ? super Float, ? extends V> transform) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int min = Math.min(fArr.length, other.length);
        ArrayList arrayList = new ArrayList(min);
        for (int i2 = 0; i2 < min; i2++) {
            arrayList.add(transform.invoke(Float.valueOf(fArr[i2]), Float.valueOf(other[i2])));
        }
        return arrayList;
    }

    @NotNull
    public static final <R> List<Pair<Float, R>> zip(@NotNull float[] fArr, @NotNull R[] other) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        int min = Math.min(fArr.length, other.length);
        ArrayList arrayList = new ArrayList(min);
        for (int i2 = 0; i2 < min; i2++) {
            float f2 = fArr[i2];
            arrayList.add(TuplesKt.to(Float.valueOf(f2), other[i2]));
        }
        return arrayList;
    }

    @NotNull
    public static final <R, V> List<V> zip(@NotNull float[] fArr, @NotNull R[] other, @NotNull Function2<? super Float, ? super R, ? extends V> transform) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int min = Math.min(fArr.length, other.length);
        ArrayList arrayList = new ArrayList(min);
        for (int i2 = 0; i2 < min; i2++) {
            arrayList.add(transform.invoke(Float.valueOf(fArr[i2]), other[i2]));
        }
        return arrayList;
    }

    @NotNull
    public static final <R> List<Pair<Integer, R>> zip(@NotNull int[] iArr, @NotNull Iterable<? extends R> other) {
        int collectionSizeOrDefault;
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        int length = iArr.length;
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(other, 10);
        ArrayList arrayList = new ArrayList(Math.min(collectionSizeOrDefault, length));
        int i2 = 0;
        for (R r2 : other) {
            if (i2 >= length) {
                break;
            }
            arrayList.add(TuplesKt.to(Integer.valueOf(iArr[i2]), r2));
            i2++;
        }
        return arrayList;
    }

    @NotNull
    public static final <R, V> List<V> zip(@NotNull int[] iArr, @NotNull Iterable<? extends R> other, @NotNull Function2<? super Integer, ? super R, ? extends V> transform) {
        int collectionSizeOrDefault;
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int length = iArr.length;
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(other, 10);
        ArrayList arrayList = new ArrayList(Math.min(collectionSizeOrDefault, length));
        Iterator<? extends R> it = other.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            Object obj = (R) it.next();
            if (i2 >= length) {
                break;
            }
            arrayList.add(transform.invoke(Integer.valueOf(iArr[i2]), obj));
            i2++;
        }
        return arrayList;
    }

    @NotNull
    public static final List<Pair<Integer, Integer>> zip(@NotNull int[] iArr, @NotNull int[] other) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        int min = Math.min(iArr.length, other.length);
        ArrayList arrayList = new ArrayList(min);
        for (int i2 = 0; i2 < min; i2++) {
            arrayList.add(TuplesKt.to(Integer.valueOf(iArr[i2]), Integer.valueOf(other[i2])));
        }
        return arrayList;
    }

    @NotNull
    public static final <V> List<V> zip(@NotNull int[] iArr, @NotNull int[] other, @NotNull Function2<? super Integer, ? super Integer, ? extends V> transform) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int min = Math.min(iArr.length, other.length);
        ArrayList arrayList = new ArrayList(min);
        for (int i2 = 0; i2 < min; i2++) {
            arrayList.add(transform.invoke(Integer.valueOf(iArr[i2]), Integer.valueOf(other[i2])));
        }
        return arrayList;
    }

    @NotNull
    public static final <R> List<Pair<Integer, R>> zip(@NotNull int[] iArr, @NotNull R[] other) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        int min = Math.min(iArr.length, other.length);
        ArrayList arrayList = new ArrayList(min);
        for (int i2 = 0; i2 < min; i2++) {
            int i3 = iArr[i2];
            arrayList.add(TuplesKt.to(Integer.valueOf(i3), other[i2]));
        }
        return arrayList;
    }

    @NotNull
    public static final <R, V> List<V> zip(@NotNull int[] iArr, @NotNull R[] other, @NotNull Function2<? super Integer, ? super R, ? extends V> transform) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int min = Math.min(iArr.length, other.length);
        ArrayList arrayList = new ArrayList(min);
        for (int i2 = 0; i2 < min; i2++) {
            arrayList.add(transform.invoke(Integer.valueOf(iArr[i2]), other[i2]));
        }
        return arrayList;
    }

    @NotNull
    public static final <R> List<Pair<Long, R>> zip(@NotNull long[] jArr, @NotNull Iterable<? extends R> other) {
        int collectionSizeOrDefault;
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        int length = jArr.length;
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(other, 10);
        ArrayList arrayList = new ArrayList(Math.min(collectionSizeOrDefault, length));
        int i2 = 0;
        for (R r2 : other) {
            if (i2 >= length) {
                break;
            }
            arrayList.add(TuplesKt.to(Long.valueOf(jArr[i2]), r2));
            i2++;
        }
        return arrayList;
    }

    @NotNull
    public static final <R, V> List<V> zip(@NotNull long[] jArr, @NotNull Iterable<? extends R> other, @NotNull Function2<? super Long, ? super R, ? extends V> transform) {
        int collectionSizeOrDefault;
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int length = jArr.length;
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(other, 10);
        ArrayList arrayList = new ArrayList(Math.min(collectionSizeOrDefault, length));
        Iterator<? extends R> it = other.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            Object obj = (R) it.next();
            if (i2 >= length) {
                break;
            }
            arrayList.add(transform.invoke(Long.valueOf(jArr[i2]), obj));
            i2++;
        }
        return arrayList;
    }

    @NotNull
    public static final List<Pair<Long, Long>> zip(@NotNull long[] jArr, @NotNull long[] other) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        int min = Math.min(jArr.length, other.length);
        ArrayList arrayList = new ArrayList(min);
        for (int i2 = 0; i2 < min; i2++) {
            arrayList.add(TuplesKt.to(Long.valueOf(jArr[i2]), Long.valueOf(other[i2])));
        }
        return arrayList;
    }

    @NotNull
    public static final <V> List<V> zip(@NotNull long[] jArr, @NotNull long[] other, @NotNull Function2<? super Long, ? super Long, ? extends V> transform) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int min = Math.min(jArr.length, other.length);
        ArrayList arrayList = new ArrayList(min);
        for (int i2 = 0; i2 < min; i2++) {
            arrayList.add(transform.invoke(Long.valueOf(jArr[i2]), Long.valueOf(other[i2])));
        }
        return arrayList;
    }

    @NotNull
    public static final <R> List<Pair<Long, R>> zip(@NotNull long[] jArr, @NotNull R[] other) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        int min = Math.min(jArr.length, other.length);
        ArrayList arrayList = new ArrayList(min);
        for (int i2 = 0; i2 < min; i2++) {
            long j2 = jArr[i2];
            arrayList.add(TuplesKt.to(Long.valueOf(j2), other[i2]));
        }
        return arrayList;
    }

    @NotNull
    public static final <R, V> List<V> zip(@NotNull long[] jArr, @NotNull R[] other, @NotNull Function2<? super Long, ? super R, ? extends V> transform) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int min = Math.min(jArr.length, other.length);
        ArrayList arrayList = new ArrayList(min);
        for (int i2 = 0; i2 < min; i2++) {
            arrayList.add(transform.invoke(Long.valueOf(jArr[i2]), other[i2]));
        }
        return arrayList;
    }

    @NotNull
    public static final <T, R> List<Pair<T, R>> zip(@NotNull T[] tArr, @NotNull Iterable<? extends R> other) {
        int collectionSizeOrDefault;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        int length = tArr.length;
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(other, 10);
        ArrayList arrayList = new ArrayList(Math.min(collectionSizeOrDefault, length));
        int i2 = 0;
        for (R r2 : other) {
            if (i2 >= length) {
                break;
            }
            arrayList.add(TuplesKt.to(tArr[i2], r2));
            i2++;
        }
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public static final <T, R, V> List<V> zip(@NotNull T[] tArr, @NotNull Iterable<? extends R> other, @NotNull Function2<? super T, ? super R, ? extends V> transform) {
        int collectionSizeOrDefault;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int length = tArr.length;
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(other, 10);
        ArrayList arrayList = new ArrayList(Math.min(collectionSizeOrDefault, length));
        Iterator<? extends R> it = other.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            Object obj = (R) it.next();
            if (i2 >= length) {
                break;
            }
            arrayList.add(transform.invoke(tArr[i2], obj));
            i2++;
        }
        return arrayList;
    }

    @NotNull
    public static <T, R> List<Pair<T, R>> zip(@NotNull T[] tArr, @NotNull R[] other) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        int min = Math.min(tArr.length, other.length);
        ArrayList arrayList = new ArrayList(min);
        for (int i2 = 0; i2 < min; i2++) {
            arrayList.add(TuplesKt.to(tArr[i2], other[i2]));
        }
        return arrayList;
    }

    @NotNull
    public static final <T, R, V> List<V> zip(@NotNull T[] tArr, @NotNull R[] other, @NotNull Function2<? super T, ? super R, ? extends V> transform) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int min = Math.min(tArr.length, other.length);
        ArrayList arrayList = new ArrayList(min);
        for (int i2 = 0; i2 < min; i2++) {
            arrayList.add(transform.invoke(tArr[i2], other[i2]));
        }
        return arrayList;
    }

    @NotNull
    public static final <R> List<Pair<Short, R>> zip(@NotNull short[] sArr, @NotNull Iterable<? extends R> other) {
        int collectionSizeOrDefault;
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        int length = sArr.length;
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(other, 10);
        ArrayList arrayList = new ArrayList(Math.min(collectionSizeOrDefault, length));
        int i2 = 0;
        for (R r2 : other) {
            if (i2 >= length) {
                break;
            }
            arrayList.add(TuplesKt.to(Short.valueOf(sArr[i2]), r2));
            i2++;
        }
        return arrayList;
    }

    @NotNull
    public static final <R, V> List<V> zip(@NotNull short[] sArr, @NotNull Iterable<? extends R> other, @NotNull Function2<? super Short, ? super R, ? extends V> transform) {
        int collectionSizeOrDefault;
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int length = sArr.length;
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(other, 10);
        ArrayList arrayList = new ArrayList(Math.min(collectionSizeOrDefault, length));
        Iterator<? extends R> it = other.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            Object obj = (R) it.next();
            if (i2 >= length) {
                break;
            }
            arrayList.add(transform.invoke(Short.valueOf(sArr[i2]), obj));
            i2++;
        }
        return arrayList;
    }

    @NotNull
    public static final <R> List<Pair<Short, R>> zip(@NotNull short[] sArr, @NotNull R[] other) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        int min = Math.min(sArr.length, other.length);
        ArrayList arrayList = new ArrayList(min);
        for (int i2 = 0; i2 < min; i2++) {
            short s2 = sArr[i2];
            arrayList.add(TuplesKt.to(Short.valueOf(s2), other[i2]));
        }
        return arrayList;
    }

    @NotNull
    public static final <R, V> List<V> zip(@NotNull short[] sArr, @NotNull R[] other, @NotNull Function2<? super Short, ? super R, ? extends V> transform) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int min = Math.min(sArr.length, other.length);
        ArrayList arrayList = new ArrayList(min);
        for (int i2 = 0; i2 < min; i2++) {
            arrayList.add(transform.invoke(Short.valueOf(sArr[i2]), other[i2]));
        }
        return arrayList;
    }

    @NotNull
    public static final List<Pair<Short, Short>> zip(@NotNull short[] sArr, @NotNull short[] other) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        int min = Math.min(sArr.length, other.length);
        ArrayList arrayList = new ArrayList(min);
        for (int i2 = 0; i2 < min; i2++) {
            arrayList.add(TuplesKt.to(Short.valueOf(sArr[i2]), Short.valueOf(other[i2])));
        }
        return arrayList;
    }

    @NotNull
    public static final <V> List<V> zip(@NotNull short[] sArr, @NotNull short[] other, @NotNull Function2<? super Short, ? super Short, ? extends V> transform) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int min = Math.min(sArr.length, other.length);
        ArrayList arrayList = new ArrayList(min);
        for (int i2 = 0; i2 < min; i2++) {
            arrayList.add(transform.invoke(Short.valueOf(sArr[i2]), Short.valueOf(other[i2])));
        }
        return arrayList;
    }

    @NotNull
    public static final <R> List<Pair<Boolean, R>> zip(@NotNull boolean[] zArr, @NotNull Iterable<? extends R> other) {
        int collectionSizeOrDefault;
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        int length = zArr.length;
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(other, 10);
        ArrayList arrayList = new ArrayList(Math.min(collectionSizeOrDefault, length));
        int i2 = 0;
        for (R r2 : other) {
            if (i2 >= length) {
                break;
            }
            arrayList.add(TuplesKt.to(Boolean.valueOf(zArr[i2]), r2));
            i2++;
        }
        return arrayList;
    }

    @NotNull
    public static final <R, V> List<V> zip(@NotNull boolean[] zArr, @NotNull Iterable<? extends R> other, @NotNull Function2<? super Boolean, ? super R, ? extends V> transform) {
        int collectionSizeOrDefault;
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int length = zArr.length;
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(other, 10);
        ArrayList arrayList = new ArrayList(Math.min(collectionSizeOrDefault, length));
        Iterator<? extends R> it = other.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            Object obj = (R) it.next();
            if (i2 >= length) {
                break;
            }
            arrayList.add(transform.invoke(Boolean.valueOf(zArr[i2]), obj));
            i2++;
        }
        return arrayList;
    }

    @NotNull
    public static final <R> List<Pair<Boolean, R>> zip(@NotNull boolean[] zArr, @NotNull R[] other) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        int min = Math.min(zArr.length, other.length);
        ArrayList arrayList = new ArrayList(min);
        for (int i2 = 0; i2 < min; i2++) {
            boolean z = zArr[i2];
            arrayList.add(TuplesKt.to(Boolean.valueOf(z), other[i2]));
        }
        return arrayList;
    }

    @NotNull
    public static final <R, V> List<V> zip(@NotNull boolean[] zArr, @NotNull R[] other, @NotNull Function2<? super Boolean, ? super R, ? extends V> transform) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int min = Math.min(zArr.length, other.length);
        ArrayList arrayList = new ArrayList(min);
        for (int i2 = 0; i2 < min; i2++) {
            arrayList.add(transform.invoke(Boolean.valueOf(zArr[i2]), other[i2]));
        }
        return arrayList;
    }

    @NotNull
    public static final List<Pair<Boolean, Boolean>> zip(@NotNull boolean[] zArr, @NotNull boolean[] other) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        int min = Math.min(zArr.length, other.length);
        ArrayList arrayList = new ArrayList(min);
        for (int i2 = 0; i2 < min; i2++) {
            arrayList.add(TuplesKt.to(Boolean.valueOf(zArr[i2]), Boolean.valueOf(other[i2])));
        }
        return arrayList;
    }

    @NotNull
    public static final <V> List<V> zip(@NotNull boolean[] zArr, @NotNull boolean[] other, @NotNull Function2<? super Boolean, ? super Boolean, ? extends V> transform) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int min = Math.min(zArr.length, other.length);
        ArrayList arrayList = new ArrayList(min);
        for (int i2 = 0; i2 < min; i2++) {
            arrayList.add(transform.invoke(Boolean.valueOf(zArr[i2]), Boolean.valueOf(other[i2])));
        }
        return arrayList;
    }
}
