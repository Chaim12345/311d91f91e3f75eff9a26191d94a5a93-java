package org.hamcrest.core;

import java.util.Arrays;
import org.hamcrest.Description;
import org.hamcrest.DiagnosingMatcher;
import org.hamcrest.Matcher;
/* loaded from: classes4.dex */
public class AllOf<T> extends DiagnosingMatcher<T> {
    private final Iterable<Matcher<? super T>> matchers;

    public AllOf(Iterable<Matcher<? super T>> iterable) {
        this.matchers = iterable;
    }

    @SafeVarargs
    public AllOf(Matcher<? super T>... matcherArr) {
        this(Arrays.asList(matcherArr));
    }

    public static <T> Matcher<T> allOf(Iterable<Matcher<? super T>> iterable) {
        return new AllOf(iterable);
    }

    @SafeVarargs
    public static <T> Matcher<T> allOf(Matcher<? super T>... matcherArr) {
        return allOf(Arrays.asList(matcherArr));
    }

    @Override // org.hamcrest.SelfDescribing
    public void describeTo(Description description) {
        description.appendList("(", " and ", ")", this.matchers);
    }

    @Override // org.hamcrest.DiagnosingMatcher
    public boolean matches(Object obj, Description description) {
        for (Matcher<? super T> matcher : this.matchers) {
            if (!matcher.matches(obj)) {
                description.appendDescriptionOf(matcher).appendText(" ");
                matcher.describeMismatch(obj, description);
                return false;
            }
        }
        return true;
    }
}
