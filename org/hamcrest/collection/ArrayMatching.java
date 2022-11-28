package org.hamcrest.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.hamcrest.Matcher;
import org.hamcrest.core.IsEqual;
import org.hamcrest.internal.NullSafety;
/* loaded from: classes4.dex */
public class ArrayMatching {
    public static <E> Matcher<E[]> arrayContaining(List<Matcher<? super E>> list) {
        return new ArrayAsIterableMatcher(new IsIterableContainingInOrder(list), list, "");
    }

    @SafeVarargs
    public static <E> Matcher<E[]> arrayContaining(E... eArr) {
        return arrayContaining(asEqualMatchers(eArr));
    }

    @SafeVarargs
    public static <E> Matcher<E[]> arrayContaining(Matcher<? super E>... matcherArr) {
        return arrayContaining(NullSafety.nullSafe(matcherArr));
    }

    public static <E> Matcher<E[]> arrayContainingInAnyOrder(Collection<Matcher<? super E>> collection) {
        return new ArrayAsIterableMatcher(new IsIterableContainingInAnyOrder(collection), collection, "in any order");
    }

    @SafeVarargs
    public static <E> Matcher<E[]> arrayContainingInAnyOrder(E... eArr) {
        return arrayContainingInAnyOrder(asEqualMatchers(eArr));
    }

    @SafeVarargs
    public static <E> Matcher<E[]> arrayContainingInAnyOrder(Matcher<? super E>... matcherArr) {
        return arrayContainingInAnyOrder(Arrays.asList(matcherArr));
    }

    public static <E> List<Matcher<? super E>> asEqualMatchers(E[] eArr) {
        ArrayList arrayList = new ArrayList();
        for (E e2 : eArr) {
            arrayList.add(IsEqual.equalTo(e2));
        }
        return arrayList;
    }

    public static <T> Matcher<T[]> hasItemInArray(T t2) {
        return hasItemInArray(IsEqual.equalTo(t2));
    }

    public static <T> Matcher<T[]> hasItemInArray(Matcher<? super T> matcher) {
        return new HasItemInArray(matcher);
    }
}
