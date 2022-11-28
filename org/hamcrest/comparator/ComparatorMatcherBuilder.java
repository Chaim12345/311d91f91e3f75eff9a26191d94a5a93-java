package org.hamcrest.comparator;

import java.util.Comparator;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
/* loaded from: classes4.dex */
public final class ComparatorMatcherBuilder<T> {
    private final Comparator<T> comparator;
    private final boolean includeComparatorInDescription;

    /* loaded from: classes4.dex */
    private static final class ComparatorMatcher<T> extends TypeSafeMatcher<T> {
        private static final int EQUAL = 0;
        private static final int GREATER_THAN = 1;
        private static final int LESS_THAN = -1;
        private static final String[] comparisonDescriptions = {"less than", "equal to", "greater than"};
        private final Comparator<T> comparator;
        private final T expected;
        private final boolean includeComparatorInDescription;
        private final int maxCompare;
        private final int minCompare;

        private ComparatorMatcher(Comparator<T> comparator, T t2, int i2, int i3, boolean z) {
            this.comparator = comparator;
            this.expected = t2;
            this.minCompare = i2;
            this.maxCompare = i3;
            this.includeComparatorInDescription = z;
        }

        private static String asText(int i2) {
            return comparisonDescriptions[Integer.signum(i2) + 1];
        }

        @Override // org.hamcrest.TypeSafeMatcher
        public void describeMismatchSafely(T t2, Description description) {
            description.appendValue(t2).appendText(" was ").appendText(asText(this.comparator.compare(t2, this.expected))).appendText(" ").appendValue(this.expected);
            if (this.includeComparatorInDescription) {
                description.appendText(" when compared by ").appendValue(this.comparator);
            }
        }

        @Override // org.hamcrest.SelfDescribing
        public void describeTo(Description description) {
            description.appendText("a value ").appendText(asText(this.minCompare));
            if (this.minCompare != this.maxCompare) {
                description.appendText(" or ").appendText(asText(this.maxCompare));
            }
            description.appendText(" ").appendValue(this.expected);
            if (this.includeComparatorInDescription) {
                description.appendText(" when compared by ").appendValue(this.comparator);
            }
        }

        @Override // org.hamcrest.TypeSafeMatcher
        public boolean matchesSafely(T t2) {
            try {
                int signum = Integer.signum(this.comparator.compare(t2, this.expected));
                if (this.minCompare <= signum) {
                    return signum <= this.maxCompare;
                }
                return false;
            } catch (ClassCastException unused) {
                return false;
            }
        }
    }

    private ComparatorMatcherBuilder(Comparator<T> comparator, boolean z) {
        this.comparator = comparator;
        this.includeComparatorInDescription = z;
    }

    public static <T> ComparatorMatcherBuilder<T> comparedBy(Comparator<T> comparator) {
        return new ComparatorMatcherBuilder<>(comparator, true);
    }

    public static <T extends Comparable<T>> ComparatorMatcherBuilder<T> usingNaturalOrdering() {
        return new ComparatorMatcherBuilder<>(new Comparator<T>() { // from class: org.hamcrest.comparator.ComparatorMatcherBuilder.1
            /* JADX WARN: Incorrect types in method signature: (TT;TT;)I */
            @Override // java.util.Comparator
            public int compare(Comparable comparable, Comparable comparable2) {
                return comparable.compareTo(comparable2);
            }
        }, false);
    }

    public Matcher<T> comparesEqualTo(T t2) {
        return new ComparatorMatcher(this.comparator, t2, 0, 0, this.includeComparatorInDescription);
    }

    public Matcher<T> greaterThan(T t2) {
        return new ComparatorMatcher(this.comparator, t2, 1, 1, this.includeComparatorInDescription);
    }

    public Matcher<T> greaterThanOrEqualTo(T t2) {
        return new ComparatorMatcher(this.comparator, t2, 0, 1, this.includeComparatorInDescription);
    }

    public Matcher<T> lessThan(T t2) {
        return new ComparatorMatcher(this.comparator, t2, -1, -1, this.includeComparatorInDescription);
    }

    public Matcher<T> lessThanOrEqualTo(T t2) {
        return new ComparatorMatcher(this.comparator, t2, -1, 0, this.includeComparatorInDescription);
    }
}
