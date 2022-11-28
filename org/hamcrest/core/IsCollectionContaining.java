package org.hamcrest.core;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;
@Deprecated
/* loaded from: classes4.dex */
public class IsCollectionContaining<T> extends TypeSafeDiagnosingMatcher<Iterable<? super T>> {
    private final IsIterableContaining<T> delegate;

    public IsCollectionContaining(Matcher<? super T> matcher) {
        this.delegate = new IsIterableContaining<>(matcher);
    }

    public static <T> Matcher<Iterable<? super T>> hasItem(T t2) {
        return IsIterableContaining.hasItem(t2);
    }

    public static <T> Matcher<Iterable<? super T>> hasItem(Matcher<? super T> matcher) {
        return IsIterableContaining.hasItem((Matcher) matcher);
    }

    @SafeVarargs
    public static <T> Matcher<Iterable<T>> hasItems(T... tArr) {
        return IsIterableContaining.hasItems(tArr);
    }

    @SafeVarargs
    public static <T> Matcher<Iterable<T>> hasItems(Matcher<? super T>... matcherArr) {
        return IsIterableContaining.hasItems((Matcher[]) matcherArr);
    }

    @Override // org.hamcrest.SelfDescribing
    public void describeTo(Description description) {
        this.delegate.describeTo(description);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.hamcrest.TypeSafeDiagnosingMatcher
    public boolean matchesSafely(Iterable iterable, Description description) {
        return this.delegate.matchesSafely(iterable, description);
    }
}
