package kotlin.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt___SequencesKt;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class BrittleContainsOptimizationKt {
    @NotNull
    public static final <T> Collection<T> convertToSetForSetOperation(@NotNull Iterable<? extends T> iterable) {
        List list;
        HashSet hashSet;
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        if (iterable instanceof Set) {
            return (Collection) iterable;
        }
        if (iterable instanceof Collection) {
            Collection<T> collection = (Collection) iterable;
            if (!safeToConvertToSet(collection)) {
                return collection;
            }
        } else if (!CollectionSystemProperties.brittleContainsOptimizationEnabled) {
            list = CollectionsKt___CollectionsKt.toList(iterable);
            return list;
        }
        hashSet = CollectionsKt___CollectionsKt.toHashSet(iterable);
        return hashSet;
    }

    @NotNull
    public static final <T> Collection<T> convertToSetForSetOperation(@NotNull Sequence<? extends T> sequence) {
        List list;
        HashSet hashSet;
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        if (CollectionSystemProperties.brittleContainsOptimizationEnabled) {
            hashSet = SequencesKt___SequencesKt.toHashSet(sequence);
            return hashSet;
        }
        list = SequencesKt___SequencesKt.toList(sequence);
        return list;
    }

    @NotNull
    public static final <T> Collection<T> convertToSetForSetOperation(@NotNull T[] tArr) {
        List asList;
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        if (CollectionSystemProperties.brittleContainsOptimizationEnabled) {
            return ArraysKt___ArraysKt.toHashSet(tArr);
        }
        asList = ArraysKt___ArraysJvmKt.asList(tArr);
        return asList;
    }

    @NotNull
    public static final <T> Collection<T> convertToSetForSetOperationWith(@NotNull Iterable<? extends T> iterable, @NotNull Iterable<? extends T> source) {
        List list;
        HashSet hashSet;
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(source, "source");
        if (!(iterable instanceof Set)) {
            if (iterable instanceof Collection) {
                if (!(source instanceof Collection) || ((Collection) source).size() >= 2) {
                    Collection<T> collection = (Collection) iterable;
                    if (!safeToConvertToSet(collection)) {
                        return collection;
                    }
                }
            } else if (!CollectionSystemProperties.brittleContainsOptimizationEnabled) {
                list = CollectionsKt___CollectionsKt.toList(iterable);
                return list;
            }
            hashSet = CollectionsKt___CollectionsKt.toHashSet(iterable);
            return hashSet;
        }
        return (Collection) iterable;
    }

    private static final <T> boolean safeToConvertToSet(Collection<? extends T> collection) {
        return CollectionSystemProperties.brittleContainsOptimizationEnabled && collection.size() > 2 && (collection instanceof ArrayList);
    }
}
