package org.hamcrest.number;

import org.hamcrest.Matcher;
import org.hamcrest.comparator.ComparatorMatcherBuilder;
/* loaded from: classes4.dex */
public class OrderingComparison {
    private OrderingComparison() {
    }

    public static <T extends Comparable<T>> Matcher<T> comparesEqualTo(T t2) {
        return ComparatorMatcherBuilder.usingNaturalOrdering().comparesEqualTo(t2);
    }

    public static <T extends Comparable<T>> Matcher<T> greaterThan(T t2) {
        return ComparatorMatcherBuilder.usingNaturalOrdering().greaterThan(t2);
    }

    public static <T extends Comparable<T>> Matcher<T> greaterThanOrEqualTo(T t2) {
        return ComparatorMatcherBuilder.usingNaturalOrdering().greaterThanOrEqualTo(t2);
    }

    public static <T extends Comparable<T>> Matcher<T> lessThan(T t2) {
        return ComparatorMatcherBuilder.usingNaturalOrdering().lessThan(t2);
    }

    public static <T extends Comparable<T>> Matcher<T> lessThanOrEqualTo(T t2) {
        return ComparatorMatcherBuilder.usingNaturalOrdering().lessThanOrEqualTo(t2);
    }
}
